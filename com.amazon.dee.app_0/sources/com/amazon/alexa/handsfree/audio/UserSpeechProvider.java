package com.amazon.alexa.handsfree.audio;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.api.compat.AlexaDialogTurn;
import com.amazon.alexa.api.compat.AlexaNextDialogTurn;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.compat.SupportedInitiationType;
import com.amazon.alexa.handsfree.audio.api.AudioEvent;
import com.amazon.alexa.handsfree.audio.api.AudioReaderCallback;
import com.amazon.alexa.handsfree.audio.api.OnReleaseListener;
import com.amazon.alexa.handsfree.audio.features.AudioEnrollmentTypeResolver;
import com.amazon.alexa.handsfree.audio.features.TypingAvailabilityResolver;
import com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter;
import com.amazon.alexa.handsfree.audio.speakerverification.WakeWordData;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.deecomms.calling.enums.CallProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public class UserSpeechProvider implements AlexaUserSpeechProvider {
    static final String ALEXA_1PSV_INVOCATION_TYPE = "HandsFree.1PSV";
    static final String ALEXA_3PSV_INVOCATION_TYPE = "HandsFree.AlexaApp";
    private static final String ALEXA_WAKE_WORD = "alexa";
    private static final long MAX_RESET_AUDIO_WAIT_TIME_IN_MILLIS = 2000;
    private static final String TAG = "UserSpeechProvider";
    private static final String USER_SPEECH_AUDIO_FORMAT = "AUDIO_L16_RATE_16000_CHANNELS_1";
    private AlexaAudioMetadata mAlexaAudioMetadata;
    private final AlexaAudioSinkWrapper mAlexaAudioSinkWrapper;
    private AlexaServicesConnection mAlexaServicesConnection;
    private final AudioMetricsReporter mAudioMetricsReporter;
    private final AudioReaderCallback mAudioReaderCallback;
    private final AudioReaderMetadata mAudioReaderMetadata;
    private Context mContext;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final EnrollmentTypeResolver mEnrollmentTypeResolver;
    private final ExecutorService mExecutorService;
    private OnReleaseListener mOnReleaseListener;
    private final TypingAvailabilityResolver mTypingAvailabilityResolver;
    private final AlexaAudioSinkWrapper mVerifiedAudioSinkWrapper;
    private WakeWordData mWakeWordData;

    public UserSpeechProvider(@NonNull Context context, @NonNull AudioReaderCallback audioReaderCallback, @NonNull AudioReaderMetadata audioReaderMetadata) {
        this(new AlexaAudioSinkWrapper(), new AlexaAudioMetadata(AlexaProfile.NEAR_FIELD, new AlexaWakeWord(CallProvider.Alexa, 0L, 0L), "AUDIO_L16_RATE_16000_CHANNELS_1"), audioReaderCallback, Executors.newSingleThreadExecutor(), audioReaderMetadata, null, DeviceTypeInformationProvider.getInstance(context), new AlexaAudioSinkWrapper(), context, new AudioMetricsReporter(context), new TypingAvailabilityResolver(context), new AudioEnrollmentTypeResolver(context));
    }

    private String getInvocationType() {
        return this.mEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV ? ALEXA_1PSV_INVOCATION_TYPE : ALEXA_3PSV_INVOCATION_TYPE;
    }

    @VisibleForTesting
    AlexaDataSink createWakeWordDataSink() throws IOException {
        return new AlexaDataSink();
    }

    @VisibleForTesting
    AlexaDialogRequest getAlexaDialogRequest() {
        return AlexaDialogRequest.builder().setInvocationType(getDeviceTypeInvocationType()).setLaunchType(LaunchType.WAKE_WORD).build();
    }

    @VisibleForTesting
    String getDeviceTypeInvocationType() {
        StringBuilder sb = new StringBuilder(getInvocationType());
        DeviceInformation supportedDeviceInformation = this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(this.mContext);
        if (supportedDeviceInformation != null) {
            sb.append('.');
            sb.append(supportedDeviceInformation.getType());
        }
        return sb.toString();
    }

    public AlexaAudioSinkWrapper getOriginalAudioSinkWrapper() {
        return this.mAlexaAudioSinkWrapper;
    }

    public AlexaAudioSinkWrapper getVerifiedAudioSink() {
        return this.mVerifiedAudioSinkWrapper;
    }

    @Nullable
    @VisibleForTesting
    AlexaDataSink getWakeWordDataSink() {
        try {
            byte[] metadata = this.mWakeWordData.getMetadata();
            if (metadata == null) {
                return null;
            }
            AlexaDataSink createWakeWordDataSink = createWakeWordDataSink();
            OutputStream openForWriting = createWakeWordDataSink.openForWriting();
            openForWriting.write(metadata);
            openForWriting.flush();
            openForWriting.close();
            return createWakeWordDataSink;
        } catch (IOException e) {
            Log.w(TAG, "Unable to write wake word metadata to data sink", e);
            return null;
        }
    }

    public /* synthetic */ void lambda$resetAudio$0$UserSpeechProvider() {
        Log.d(TAG, "Resetting original AlexaAudioSink.");
        this.mAlexaAudioSinkWrapper.reset();
        Log.d(TAG, "Resetting verified AlexaAudioSink.");
        this.mVerifiedAudioSinkWrapper.reset();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogFinished() {
        Log.i(TAG, "onDialogFinished");
        this.mAudioMetricsReporter.sendDialogFinished(TAG);
        AlexaServicesConnection alexaServicesConnection = this.mAlexaServicesConnection;
        if (alexaServicesConnection == null) {
            Log.e(TAG, "onDialogFinished: shouldn't happen, dialog finished without a connection");
            return;
        }
        AlexaServicesApis.UserSpeechProviders.deregister(alexaServicesConnection, this);
        this.mAlexaServicesConnection = null;
        this.mWakeWordData = null;
        OnReleaseListener onReleaseListener = this.mOnReleaseListener;
        if (onReleaseListener != null) {
            onReleaseListener.onRelease();
        } else {
            Log.e(TAG, "onReleaseListener was not set");
        }
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequestDenied() {
        Log.i(TAG, "onDialogRequestDenied");
        this.mAudioMetricsReporter.sendDialogRequestFailure(TAG);
        this.mAudioReaderCallback.onEvent(AudioEvent.AUDIO_RECORD_STOPPED);
        this.mAlexaServicesConnection = null;
        OnReleaseListener onReleaseListener = this.mOnReleaseListener;
        if (onReleaseListener != null) {
            onReleaseListener.onRelease();
        } else {
            Log.e(TAG, "onReleaseListener was not set");
        }
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequested(AlexaDialogTurn alexaDialogTurn) {
        Log.i(TAG, "onDialogRequested: start dialog turn");
        this.mAudioMetricsReporter.sendDialogRequestSuccess(TAG);
        boolean isUserVoiceRecognized = this.mAudioReaderMetadata.isUserVoiceRecognized();
        AlexaDialogExtras.Builder alexaUserInterfaceOptions = AlexaDialogExtras.builder().setUserVoiceVerified(isUserVoiceRecognized).setLaunchType(LaunchType.WAKE_WORD).setInvocationType(getDeviceTypeInvocationType()).setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions.builder().setTypingEnabled(this.mTypingAvailabilityResolver.isTypingEnabled()).build());
        if (this.mWakeWordData != null) {
            AlexaAudioMetadata.Builder builder = new AlexaAudioMetadata.Builder();
            builder.setAlexaProfile(this.mAlexaAudioMetadata.getAlexaProfile());
            builder.setAudioFormat(this.mAlexaAudioMetadata.getAlexaAudioFormat());
            builder.setAlexaWakeWord(new AlexaWakeWord(this.mWakeWordData.getWakeWord(), this.mWakeWordData.getBeginSampleIndex(), this.mWakeWordData.getEndSampleIndex()));
            this.mAlexaAudioMetadata = builder.build();
            AlexaDialogExtras build = alexaUserInterfaceOptions.suppressWakewordVerification(true).build();
            ReadableAlexaAudioSink readableAlexaAudioSink = this.mVerifiedAudioSinkWrapper.getReadableAlexaAudioSink();
            AlexaDataSink wakeWordDataSink = getWakeWordDataSink();
            Log.d(TAG, "onDialogRequested: Calling startTurn suppressing ww verification for 1PSV.");
            this.mAudioMetricsReporter.sendStartDialogTurn(TAG);
            alexaDialogTurn.startTurn(this.mAlexaAudioMetadata, readableAlexaAudioSink, wakeWordDataSink, onDialogRequestedStopCallback(), build);
            return;
        }
        AlexaDialogExtras build2 = alexaUserInterfaceOptions.suppressWakewordVerification(false).build();
        ReadableAlexaAudioSink readableAlexaAudioSink2 = this.mAlexaAudioSinkWrapper.getReadableAlexaAudioSink();
        Log.d(TAG, "onDialogRequested: Calling startTurn without suppressing ww verification.");
        alexaDialogTurn.startTurn(this.mAlexaAudioMetadata, readableAlexaAudioSink2, onDialogRequestedStopCallback(), build2);
    }

    @VisibleForTesting
    AlexaDialogTurnStopCallback onDialogRequestedStopCallback() {
        return new AlexaDialogTurnStopCallback() { // from class: com.amazon.alexa.handsfree.audio.UserSpeechProvider.2
            @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
            public void stopRecording() {
                Log.i(UserSpeechProvider.TAG, "stopRecording");
                UserSpeechProvider.this.mAudioMetricsReporter.sendStopRecording(UserSpeechProvider.TAG);
                UserSpeechProvider.this.mAudioReaderCallback.onEvent(AudioEvent.AUDIO_RECORD_STOPPED);
            }
        };
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogStarted() {
        Log.i(TAG, "onDialogStarted");
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnFinished() {
        Log.i(TAG, "onDialogTurnFinished");
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
        Log.i(TAG, "onDialogTurnRequested");
        this.mAudioMetricsReporter.sendDialogTurnRequested(TAG);
        final RecordingRunnable recordingRunnable = new RecordingRunnable(this.mAlexaAudioSinkWrapper);
        this.mExecutorService.submit(recordingRunnable);
        alexaNextDialogTurn.startTurn(this.mAlexaAudioSinkWrapper.getReadableAlexaAudioSink(), new AlexaDialogTurnStopCallback() { // from class: com.amazon.alexa.handsfree.audio.UserSpeechProvider.1
            @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
            public void stopRecording() {
                Log.i(UserSpeechProvider.TAG, "stopRecording");
                UserSpeechProvider.this.mAudioMetricsReporter.sendStopRecording(UserSpeechProvider.TAG);
                recordingRunnable.stop();
                UserSpeechProvider.this.mAlexaAudioSinkWrapper.reset();
            }
        });
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnStarted() {
        Log.i(TAG, "onDialogTurnStarted");
        this.mAudioMetricsReporter.sendDialogTurnStarted(TAG);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void pauseWakeWordDetection() {
    }

    public void recordAudio(byte[] bArr) {
        this.mAlexaAudioSinkWrapper.recordAudio(bArr);
    }

    public void requestDialog(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Log.i(TAG, "requestDialog()");
        this.mAlexaServicesConnection = alexaServicesConnection;
        AlexaServicesApis.UserSpeechProviders.register(this.mAlexaServicesConnection, this, AlexaUserSpeechProviderMetadata.create(Collections.singleton(SupportedInitiationType.WAKE_WORD), Collections.singleton("alexa"), AlexaUserSpeechProviderScope.SYSTEM));
        AlexaServicesApis.UserSpeechRecognizer.requestDialog(this.mAlexaServicesConnection, this, getAlexaDialogRequest());
    }

    public void resetAudio() {
        Log.d(TAG, "Submitting resetAudio executor service");
        Future<?> submit = this.mExecutorService.submit(new Runnable() { // from class: com.amazon.alexa.handsfree.audio.-$$Lambda$UserSpeechProvider$KWMzssgQxeyiZ6TwcdqgNJvkWzU
            @Override // java.lang.Runnable
            public final void run() {
                UserSpeechProvider.this.lambda$resetAudio$0$UserSpeechProvider();
            }
        });
        try {
            submit.get(2000L, TimeUnit.MILLISECONDS);
            Log.d(TAG, "Successfully reset both instances of AlexaAudioSinkWrapper");
            this.mAudioMetricsReporter.sendResetAudioSuccess(TAG);
        } catch (Exception e) {
            Log.e(TAG, "Failed to reset both instances of AlexaAudioSinkWrapper", e, new Object[0]);
            submit.cancel(true);
            this.mAudioMetricsReporter.sendResetAudioFailure(TAG);
        }
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void resumeWakeWordDetection() {
    }

    public void setOnReleaseListener(@NonNull OnReleaseListener onReleaseListener) {
        this.mOnReleaseListener = onReleaseListener;
    }

    public void setWakeWordData(@NonNull WakeWordData wakeWordData) {
        this.mWakeWordData = wakeWordData;
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        String str = TAG;
        Log.i(str, "setWakeWordDetectionEnabled: " + z);
    }

    public void stopAudioReading() {
        this.mAudioReaderCallback.onEvent(AudioEvent.AUDIO_RECORD_STOPPED);
    }

    public void wakeWordDetectedEvent() {
        this.mAudioReaderCallback.onEvent(AudioEvent.WAKE_WORD_DETECTED);
    }

    public UserSpeechProvider(@NonNull Context context, @NonNull AudioReaderCallback audioReaderCallback, @NonNull AudioReaderMetadata audioReaderMetadata, @NonNull ParcelFileDescriptor parcelFileDescriptor) {
        this(new AlexaAudioSinkWrapper(parcelFileDescriptor), new AlexaAudioMetadata(AlexaProfile.NEAR_FIELD, new AlexaWakeWord(CallProvider.Alexa, 0L, 0L), "AUDIO_L16_RATE_16000_CHANNELS_1"), audioReaderCallback, Executors.newSingleThreadExecutor(), audioReaderMetadata, null, DeviceTypeInformationProvider.getInstance(context), new AlexaAudioSinkWrapper(), context, new AudioMetricsReporter(context), new TypingAvailabilityResolver(context), new AudioEnrollmentTypeResolver(context));
    }

    @VisibleForTesting
    UserSpeechProvider(@NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AudioReaderCallback audioReaderCallback, @NonNull ExecutorService executorService, @NonNull AudioReaderMetadata audioReaderMetadata, @Nullable AlexaServicesConnection alexaServicesConnection, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper2, @NonNull Context context, @NonNull AudioMetricsReporter audioMetricsReporter, @NonNull TypingAvailabilityResolver typingAvailabilityResolver, @NonNull EnrollmentTypeResolver enrollmentTypeResolver) {
        this.mAlexaAudioSinkWrapper = alexaAudioSinkWrapper;
        this.mAlexaAudioMetadata = alexaAudioMetadata;
        this.mAudioReaderCallback = audioReaderCallback;
        this.mExecutorService = executorService;
        this.mAudioReaderMetadata = audioReaderMetadata;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mVerifiedAudioSinkWrapper = alexaAudioSinkWrapper2;
        this.mContext = context;
        this.mAudioMetricsReporter = audioMetricsReporter;
        this.mTypingAvailabilityResolver = typingAvailabilityResolver;
        this.mEnrollmentTypeResolver = enrollmentTypeResolver;
    }
}
