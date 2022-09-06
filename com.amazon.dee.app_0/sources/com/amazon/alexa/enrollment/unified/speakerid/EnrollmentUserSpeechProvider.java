package com.amazon.alexa.enrollment.unified.speakerid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.compat.AlexaDialogTurn;
import com.amazon.alexa.api.compat.AlexaNextDialogTurn;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.SpeakerIDEnrollmentMetricsReporter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.WakeWordData;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public class EnrollmentUserSpeechProvider implements AlexaUserSpeechProvider {
    static final String INVOCATION_TYPE = "HandsFree.VoiceEnrollment";
    static final String ON_DIALOG_FINISHED = "onDialogFinished";
    static final String ON_DIALOG_REQUESTED = "onDialogRequested";
    static final String ON_DIALOG_REQUEST_DENIED = "onDialogRequestDenied";
    static final String ON_DIALOG_STARTED = "onDialogStarted";
    static final String ON_DIALOG_TURN_FINISHED = "onDialogTurnFinished";
    static final String ON_DIALOG_TURN_REQUESTED = "onDialogTurnRequested";
    static final String ON_DIALOG_TURN_STARTED = "onDialogTurnStarted";
    private static final String TAG = "EnrollmentUserSpeechProvider";
    private final DialogListener mDialogListener;
    private final SpeakerIDEnrollmentMetricsReporter mMetricsReporter;
    private final WakeWordData mWakeWordData;

    /* loaded from: classes7.dex */
    public interface DialogListener {
        void onDialogFinished();

        void onDialogRequestDenied();

        void onDialogRequested();

        void onStopRecording();
    }

    public EnrollmentUserSpeechProvider(@NonNull WakeWordData wakeWordData, @NonNull DialogListener dialogListener, @NonNull SpeakerIDEnrollmentMetricsReporter speakerIDEnrollmentMetricsReporter) {
        this.mWakeWordData = wakeWordData;
        this.mDialogListener = dialogListener;
        this.mMetricsReporter = speakerIDEnrollmentMetricsReporter;
    }

    @Nullable
    private AlexaDataSink getWakeWordDataSink(@NonNull WakeWordData wakeWordData) {
        AlexaDataSink alexaDataSink = null;
        try {
            byte[] metadata = wakeWordData.getMetadata();
            if (metadata == null) {
                return null;
            }
            AlexaDataSink alexaDataSink2 = new AlexaDataSink();
            try {
                OutputStream openForWriting = alexaDataSink2.openForWriting();
                openForWriting.write(metadata);
                openForWriting.flush();
                openForWriting.close();
                return alexaDataSink2;
            } catch (IOException e) {
                e = e;
                alexaDataSink = alexaDataSink2;
                Log.w(TAG, "Unable to write wake word metadata to data sink", e);
                return alexaDataSink;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public /* synthetic */ void lambda$onDialogRequested$0$EnrollmentUserSpeechProvider() {
        Log.d(TAG, "stopRecording");
        this.mDialogListener.onStopRecording();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogFinished() {
        Log.d(TAG, ON_DIALOG_FINISHED);
        this.mDialogListener.onDialogFinished();
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_FINISHED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequestDenied() {
        Log.e(TAG, ON_DIALOG_REQUEST_DENIED);
        this.mDialogListener.onDialogRequestDenied();
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_REQUEST_DENIED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequested(@NonNull AlexaDialogTurn alexaDialogTurn) {
        Log.d(TAG, ON_DIALOG_REQUESTED);
        this.mDialogListener.onDialogRequested();
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_REQUESTED);
        AlexaDataSink wakeWordDataSink = getWakeWordDataSink(this.mWakeWordData);
        AlexaAudioMetadata alexaAudioMetadata = new AlexaAudioMetadata(AlexaProfile.NEAR_FIELD, null, RecordingRunnable.USER_SPEECH_AUDIO_FORMAT);
        AlexaDialogExtras build = AlexaDialogExtras.builder().suppressWakeSound(true).suppressWakewordVerification(true).setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions.builder().setHintsEnabled(false).setTheme(AlexaUserInterfaceOptions.Theme.MINIMAL).build()).setInvocationType(INVOCATION_TYPE).build();
        AlexaDialogTurnStopCallback alexaDialogTurnStopCallback = new AlexaDialogTurnStopCallback() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$EnrollmentUserSpeechProvider$ZEHeRSgUvL7Jr_1j8wt74c8_MTc
            @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
            public final void stopRecording() {
                EnrollmentUserSpeechProvider.this.lambda$onDialogRequested$0$EnrollmentUserSpeechProvider();
            }
        };
        if (wakeWordDataSink == null) {
            alexaDialogTurn.startTurn(alexaAudioMetadata, this.mWakeWordData.getAudioSink(), alexaDialogTurnStopCallback, build);
        } else {
            alexaDialogTurn.startTurn(alexaAudioMetadata, this.mWakeWordData.getAudioSink(), wakeWordDataSink, alexaDialogTurnStopCallback, build);
        }
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogStarted() {
        Log.d(TAG, ON_DIALOG_STARTED);
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_STARTED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnFinished() {
        Log.d(TAG, ON_DIALOG_TURN_FINISHED);
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_TURN_FINISHED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnRequested(@NonNull AlexaNextDialogTurn alexaNextDialogTurn) {
        Log.d(TAG, ON_DIALOG_TURN_REQUESTED);
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_TURN_REQUESTED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnStarted() {
        Log.d(TAG, ON_DIALOG_TURN_STARTED);
        this.mMetricsReporter.reportDialogListenerStatus(ON_DIALOG_TURN_STARTED);
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void pauseWakeWordDetection() {
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void resumeWakeWordDetection() {
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
    }
}
