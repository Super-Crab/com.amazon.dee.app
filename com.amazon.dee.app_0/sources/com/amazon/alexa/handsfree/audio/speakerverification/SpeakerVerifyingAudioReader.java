package com.amazon.alexa.handsfree.audio.speakerverification;

import android.content.Context;
import android.media.AudioManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.AlexaConnectionListenerFactory;
import com.amazon.alexa.handsfree.audio.AlexaServicesConnectionFactory;
import com.amazon.alexa.handsfree.audio.ConnectedListenerProvider;
import com.amazon.alexa.handsfree.audio.ReleaseListenerProvider;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public class SpeakerVerifyingAudioReader extends AudioReader {
    private static final long MAX_SPEAKER_VERIFY_TIME_IN_MILLIS = 20000;
    @VisibleForTesting
    static final String TAG = "SpeakerVerifyingAudioReader";
    private final CountDownLatch mCountDownLatch;
    private final ScheduledExecutorService mExecutorService;
    private final AudioMetricsReporter mMetricsReporter;
    private final SpeakerVerificationModelProvider mSpeakerVerificationModelProvider;
    private SpeakerVerifier mSpeakerVerifier;
    private final SpeakerVerifierFactory mSpeakerVerifierFactory;
    private ScheduledFuture<?> mTimeoutFuture;
    private final WakeWordModelProvider mWakeWordModelProvider;

    public SpeakerVerifyingAudioReader(@NonNull Context context) {
        this(new AlexaServicesConnectionFactory(), new AlexaConnectionListenerFactory(), new ReleaseListenerProvider(), new ConnectedListenerProvider(), null, new SpeakerVerifierFactory(), Executors.newScheduledThreadPool(2), new AudioMetricsReporter(context), new WakeWordModelProvider(context), new SpeakerVerificationModelProvider(), new CountDownLatch(1));
    }

    private void stopAudioReading(@NonNull UserSpeechProvider userSpeechProvider) {
        this.mSpeakerVerifier.stopAudioReading();
        userSpeechProvider.stopAudioReading();
        this.mMetricsReporter.sendForceStopAudioReading(TAG);
    }

    private void verify() throws IOException {
        this.mSpeakerVerifier.verify();
        this.mSpeakerVerifier.destroy();
    }

    @VisibleForTesting
    VerificationCallbacks createVerificationCallback(@NonNull Context context, @Nullable String str, @Nullable String str2) {
        return new VerificationCallbacks(getUserSpeechProvider(), getOnReleaseListener(), this.mCountDownLatch, getCurrentAlexaServicesConnection(), str, str2, new AudioMetricsReporter(context));
    }

    public /* synthetic */ void lambda$onWakeWordDetected$0$SpeakerVerifyingAudioReader(UserSpeechProvider userSpeechProvider) {
        ScheduledFuture<?> scheduledFuture;
        Log.d(TAG, "Verifying speaker.");
        try {
            try {
                verify();
                Log.d(TAG, "Successfully finished the Speaker Verifying process.");
                scheduledFuture = this.mTimeoutFuture;
                if (scheduledFuture == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to finish the Speaker Verifying process. Stopping audio reading", e, new Object[0]);
                stopAudioReading(userSpeechProvider);
                scheduledFuture = this.mTimeoutFuture;
                if (scheduledFuture == null) {
                    return;
                }
            }
            scheduledFuture.cancel(true);
            this.mTimeoutFuture = null;
        } catch (Throwable th) {
            ScheduledFuture<?> scheduledFuture2 = this.mTimeoutFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(true);
                this.mTimeoutFuture = null;
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$onWakeWordDetected$1$SpeakerVerifyingAudioReader(Future future, UserSpeechProvider userSpeechProvider) {
        if (!future.isDone()) {
            future.cancel(true);
            Log.i(TAG, "Verifying speaker spends more than 20s. Force stopped and stopping audio reading.");
            stopAudioReading(userSpeechProvider);
        }
    }

    @Override // com.amazon.alexa.handsfree.audio.api.AudioReader
    public void onAlexaConnectionConnected() {
        this.mCountDownLatch.countDown();
    }

    @Override // com.amazon.alexa.handsfree.audio.api.AudioReader
    public void onServiceDestroyed() {
        Log.i(TAG, "onServiceDestroyed");
        super.onServiceDestroyed();
        SpeakerVerifier speakerVerifier = this.mSpeakerVerifier;
        if (speakerVerifier != null) {
            speakerVerifier.destroy();
            this.mSpeakerVerifier = null;
        }
    }

    @Override // com.amazon.alexa.handsfree.audio.api.AudioReader
    public void onWakeWordDetected(@NonNull Context context, @NonNull final UserSpeechProvider userSpeechProvider) {
        ArtifactModel artifactModel;
        super.onWakeWordDetected(context, userSpeechProvider);
        try {
            try {
                artifactModel = this.mSpeakerVerificationModelProvider.getArtifactModel(context);
            } catch (Exception e) {
                Log.e(TAG, "failed to create or initialize speaker verifier.", e, new Object[0]);
                this.mMetricsReporter.sendSpeakerVerifierInitFailure(TAG);
            }
            if (artifactModel != null) {
                this.mSpeakerVerifier = this.mSpeakerVerifierFactory.createSpeakerVerifier(context, createVerificationCallback(context, this.mWakeWordModelProvider.getModelID(), artifactModel.getArtifactIdentifier()), userSpeechProvider, this.mWakeWordModelProvider);
                this.mSpeakerVerifier.initialize(artifactModel, (AudioManager) context.getSystemService("audio"));
                this.mMetricsReporter.sendSpeakerVerifierInitSuccess(TAG);
                final Future<?> submit = this.mExecutorService.submit(new Runnable() { // from class: com.amazon.alexa.handsfree.audio.speakerverification.-$$Lambda$SpeakerVerifyingAudioReader$aeF5zFOWKUOwt7VRSdaHcXMPgXw
                    @Override // java.lang.Runnable
                    public final void run() {
                        SpeakerVerifyingAudioReader.this.lambda$onWakeWordDetected$0$SpeakerVerifyingAudioReader(userSpeechProvider);
                    }
                });
                this.mTimeoutFuture = this.mExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.handsfree.audio.speakerverification.-$$Lambda$SpeakerVerifyingAudioReader$8by9ch4ur5BHPGwlm47QL9Vl-tI
                    @Override // java.lang.Runnable
                    public final void run() {
                        SpeakerVerifyingAudioReader.this.lambda$onWakeWordDetected$1$SpeakerVerifyingAudioReader(submit, userSpeechProvider);
                    }
                }, 20000L, TimeUnit.MILLISECONDS);
                return;
            }
            this.mMetricsReporter.sendArtifactModelNull(TAG);
            throw new IllegalStateException("The SV ArtifactModel is null.");
        } finally {
            this.mExecutorService.shutdown();
        }
    }

    @Override // com.amazon.alexa.handsfree.audio.api.AudioReader
    public void onWakeWordDetectionAborted(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "onWakeWordDetectionAborted: wake word detection aborted, message: " + str);
        super.onWakeWordDetectionAborted(str);
        SpeakerVerifier speakerVerifier = this.mSpeakerVerifier;
        if (speakerVerifier != null) {
            speakerVerifier.destroy();
            this.mSpeakerVerifier = null;
        }
    }

    @VisibleForTesting
    SpeakerVerifyingAudioReader(@NonNull AlexaServicesConnectionFactory alexaServicesConnectionFactory, @NonNull AlexaConnectionListenerFactory alexaConnectionListenerFactory, @NonNull ReleaseListenerProvider releaseListenerProvider, @NonNull ConnectedListenerProvider connectedListenerProvider, @Nullable UserSpeechProvider userSpeechProvider, @NonNull SpeakerVerifierFactory speakerVerifierFactory, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull AudioMetricsReporter audioMetricsReporter, @NonNull WakeWordModelProvider wakeWordModelProvider, @NonNull SpeakerVerificationModelProvider speakerVerificationModelProvider, @NonNull CountDownLatch countDownLatch) {
        super(alexaServicesConnectionFactory, alexaConnectionListenerFactory, releaseListenerProvider, connectedListenerProvider, userSpeechProvider);
        this.mCountDownLatch = countDownLatch;
        this.mSpeakerVerifierFactory = speakerVerifierFactory;
        this.mExecutorService = scheduledExecutorService;
        this.mMetricsReporter = audioMetricsReporter;
        this.mSpeakerVerificationModelProvider = speakerVerificationModelProvider;
        this.mWakeWordModelProvider = wakeWordModelProvider;
    }
}
