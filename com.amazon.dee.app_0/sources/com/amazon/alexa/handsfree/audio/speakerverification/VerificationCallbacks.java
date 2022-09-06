package com.amazon.alexa.handsfree.audio.speakerverification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.audio.api.OnReleaseListener;
import com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter;
import com.amazon.alexa.handsfree.audio.metrics.speakerverification.SpeakerVerificationMobilyticsMetadata;
import com.amazon.alexa.handsfree.audio.metrics.wakeword.WakeWordMobilyticsMetadata;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class VerificationCallbacks {
    @VisibleForTesting
    static final int CONNECTION_MAX_WAIT_TIME_SECONDS = 5;
    @VisibleForTesting
    static final int SPEAKER_VERIFIED_SCORE_BIN = 1;
    @VisibleForTesting
    static final String TAG = "VerificationCallbacks";
    private final CountDownLatch mCountDownLatch;
    private final AlexaServicesConnection mCurrentAlexaServicesConnection;
    private final AudioMetricsReporter mMetricsReporter;
    private final OnReleaseListener mOnReleaseListener;
    private final String mSVModelID;
    private final UserSpeechProvider mUserSpeechProvider;
    private final String mWWModelId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VerificationCallbacks(@NonNull UserSpeechProvider userSpeechProvider, @NonNull OnReleaseListener onReleaseListener, @NonNull CountDownLatch countDownLatch, @NonNull AlexaServicesConnection alexaServicesConnection, @Nullable String str, @Nullable String str2, @NonNull AudioMetricsReporter audioMetricsReporter) {
        this.mUserSpeechProvider = userSpeechProvider;
        this.mOnReleaseListener = onReleaseListener;
        this.mCountDownLatch = countDownLatch;
        this.mCurrentAlexaServicesConnection = alexaServicesConnection;
        this.mWWModelId = str;
        this.mSVModelID = str2;
        this.mMetricsReporter = audioMetricsReporter;
    }

    private SpeakerVerificationMobilyticsMetadata parseSpeakerVerificationData(@NonNull SpeakerVerificationClassificationData speakerVerificationClassificationData) {
        return new SpeakerVerificationMobilyticsMetadata(this.mSVModelID, speakerVerificationClassificationData.getRawScore(), speakerVerificationClassificationData.getSVAcceptThresholdLowerBound(), speakerVerificationClassificationData.getSVAcceptThresholdUpperBound());
    }

    private WakeWordMobilyticsMetadata parseWakeWordData(@NonNull SpeakerVerificationClassificationData speakerVerificationClassificationData) {
        return new WakeWordMobilyticsMetadata(this.mWWModelId, speakerVerificationClassificationData.getWakeWordConfidence());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWakewordDetected(@NonNull SpeakerVerificationClassificationData speakerVerificationClassificationData, @NonNull WakeWordData wakeWordData) {
        Log.i(TAG, "onWakewordDetected called.");
        this.mUserSpeechProvider.wakeWordDetectedEvent();
        WakeWordMobilyticsMetadata parseWakeWordData = parseWakeWordData(speakerVerificationClassificationData);
        this.mMetricsReporter.sendWakeWordAcceptedEvent(parseWakeWordData.getWWModelID(), parseWakeWordData.getWakeWordConfidence());
        SpeakerVerificationMobilyticsMetadata parseSpeakerVerificationData = parseSpeakerVerificationData(speakerVerificationClassificationData);
        if (speakerVerificationClassificationData.getScore() == 1) {
            this.mMetricsReporter.sendSVAcceptedEvent(parseWakeWordData, parseSpeakerVerificationData);
            this.mUserSpeechProvider.setWakeWordData(wakeWordData);
            try {
                if (this.mCountDownLatch.await(5L, TimeUnit.SECONDS)) {
                    Log.i(TAG, "speaker verified. requesting dialog");
                    this.mMetricsReporter.sendAlexaServicesConnectionEstablishedSuccess(TAG);
                    this.mMetricsReporter.sendSpeakerVerifiedSuccess(TAG);
                    this.mUserSpeechProvider.requestDialog(this.mCurrentAlexaServicesConnection);
                } else {
                    Log.e(TAG, "AlexaServices connection timed out.");
                    this.mMetricsReporter.sendAlexaServicesConnectionEstablishedFailure(TAG);
                }
                return;
            } catch (InterruptedException unused) {
                Log.e(TAG, "interrupted waiting for latch.");
                this.mMetricsReporter.sendAlexaServicesConnectionEstablishedFailure(TAG);
                return;
            }
        }
        Log.i(TAG, "speaker not verified. Stop Recording.");
        this.mMetricsReporter.sendSVRejectedEvent(parseWakeWordData, parseSpeakerVerificationData);
        this.mMetricsReporter.sendSpeakerVerifiedFailure(TAG);
        this.mUserSpeechProvider.stopAudioReading();
        this.mOnReleaseListener.onRelease();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWakewordNotDetected() {
        Log.i(TAG, "onWakewordNotDetected called.");
        this.mMetricsReporter.sendWakeWordAbandonedEvent(this.mWWModelId);
        this.mUserSpeechProvider.stopAudioReading();
        this.mOnReleaseListener.onRelease();
    }
}
