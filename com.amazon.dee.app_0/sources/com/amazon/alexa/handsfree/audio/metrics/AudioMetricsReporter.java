package com.amazon.alexa.handsfree.audio.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.metrics.speakerverification.SVClassificationEvent;
import com.amazon.alexa.handsfree.audio.metrics.speakerverification.SpeakerVerificationMobilyticsMetadata;
import com.amazon.alexa.handsfree.audio.metrics.wakeword.WakeWordInvocationEvent;
import com.amazon.alexa.handsfree.audio.metrics.wakeword.WakeWordMobilyticsMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.BroadcastingMetricsReporter;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class AudioMetricsReporter extends BroadcastingMetricsReporter {
    static final String ABANDON_FOCUS_FAILURE = "ABANDON_FOCUS_FAILURE";
    static final String ABANDON_FOCUS_SUCCESS = "ABANDON_FOCUS_SUCCESS";
    static final String AUDIO_READING_FORCE_STOPPED = "AUDIO_READING_FORCE_STOPPED";
    static final String DIALOG_FINISHED = "DIALOG_FINISHED";
    static final String DIALOG_TURN_REQUESTED = "DIALOG_TURN_REQUESTED";
    static final String DIALOG_TURN_STARTED = "DIALOG_TURN_STARTED";
    static final String GET_FILE_DESCRIPTOR_FAILURE = "GET_FILE_DESCRIPTOR_FAILURE";
    static final String GET_FILE_DESCRIPTOR_SUCCESS = "GET_FILE_DESCRIPTOR_SUCCESS";
    @VisibleForTesting
    static final String INIT_SPEAKER_VERIFIER = "INIT_SPEAKER_VERIFIER";
    @VisibleForTesting
    static final String PROFILE_NULL = "PROFILE_NULL";
    @VisibleForTesting
    static final String PRYON_NOT_SUPPORT_WW_LOCALE = "PRYON_NOT_SUPPORT_WW_LOCALE";
    @VisibleForTesting
    static final String REQUEST_DIALOG = "REQUEST_DIALOG";
    static final String REQUEST_FOCUS_FAILURE = "REQUEST_FOCUS_FAILURE";
    static final String REQUEST_FOCUS_SUCCESS = "REQUEST_FOCUS_SUCCESS";
    @VisibleForTesting
    static final String RESET_AUDIO = "RESET_AUDIO";
    private static final String SEPARATOR = ":";
    private static final String SPEAKER_VERIFICATION_PREFIX = "SpeakerVerification";
    static final String START_DIALOG_TURN = "START_DIALOG_TURN";
    static final String STOP_RECORDING = "STOP_RECORDING";
    @VisibleForTesting
    static final String SV_ALEXA_SERVICES_CONNECTION_SUCCESS = "SV_ALEXA_SERVICES_CONNECTION_SUCCESS";
    @VisibleForTesting
    static final String SV_ARTIFACT_MODEL_NULL = "SV_ARTIFACT_MODEL_NULL";
    @VisibleForTesting
    static final String SV_MODEL_LOCALE_NULL = "SV_MODEL_LOCALE_NULL";
    @VisibleForTesting
    static final String SV_MODEL_NULL = "SV_MODEL_NULL";
    @VisibleForTesting
    static final String SV_WW_LOCALE_MISMATCH = "SV_WW_LOCALE_MISMATCH";
    static final String UTTERANCE_PROVIDER_NULL = "UTTERANCE_PROVIDER_NULL";
    @VisibleForTesting
    static final String VERIFY_PRYONLITE_INIT_FAILED = "VERIFY_PRYONLITE_INIT_FAILED";
    @VisibleForTesting
    static final String VERIFY_PRYONLITE_PUSH_AUDIO_FAILED = "VERIFY_PRYONLITE_PUSH_AUDIO_FAILED";
    @VisibleForTesting
    static final String VERIFY_SPEAKER_VERIFIER = "SPEAKER_VERIFIED";
    @VisibleForTesting
    static final String WW_MODEL_LOCALE_NULL = "WW_MODEL_LOCALE_NULL";
    @VisibleForTesting
    static final String WW_MODEL_NULL = "WW_MODEL_NULL";

    public AudioMetricsReporter(@NonNull Context context) {
        super(context);
    }

    public void abandonFocusFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(ABANDON_FOCUS_FAILURE), str, false);
    }

    public void abandonFocusSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(ABANDON_FOCUS_SUCCESS), str, true);
    }

    @VisibleForTesting
    String createMetricName(@NonNull String str) {
        return GeneratedOutlineSupport1.outline72("SpeakerVerification:", str);
    }

    @VisibleForTesting
    SVClassificationEvent createSVClassificationEvent(@NonNull String str, @NonNull WakeWordMobilyticsMetadata wakeWordMobilyticsMetadata, @NonNull SpeakerVerificationMobilyticsMetadata speakerVerificationMobilyticsMetadata) {
        return new SVClassificationEvent(str, wakeWordMobilyticsMetadata, speakerVerificationMobilyticsMetadata);
    }

    @VisibleForTesting
    WakeWordInvocationEvent createWakeWordEvent(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        return new WakeWordInvocationEvent(str, str2, str3);
    }

    public void getFileDescriptorFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(GET_FILE_DESCRIPTOR_FAILURE), str, false);
    }

    public void getFileDescriptorSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(GET_FILE_DESCRIPTOR_SUCCESS), str, true);
    }

    public void requestFocusFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(REQUEST_FOCUS_FAILURE), str, false);
    }

    public void requestFocusSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(REQUEST_FOCUS_SUCCESS), str, true);
    }

    public void sendAlexaServicesConnectionEstablishedFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(SV_ALEXA_SERVICES_CONNECTION_SUCCESS), str, false);
    }

    public void sendAlexaServicesConnectionEstablishedSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(SV_ALEXA_SERVICES_CONNECTION_SUCCESS), str, true);
    }

    public void sendArtifactModelNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(SV_ARTIFACT_MODEL_NULL), str);
    }

    public void sendDialogFinished(@NonNull String str) {
        sendPerformanceMetric(createMetricName(DIALOG_FINISHED), str);
    }

    public void sendDialogRequestFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(REQUEST_DIALOG), str, false);
    }

    public void sendDialogRequestSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(REQUEST_DIALOG), str, true);
    }

    public void sendDialogTurnRequested(@NonNull String str) {
        sendPerformanceMetric(createMetricName(DIALOG_TURN_REQUESTED), str);
    }

    public void sendDialogTurnStarted(@NonNull String str) {
        sendPerformanceMetric(createMetricName(DIALOG_TURN_STARTED), str);
    }

    public void sendForceStopAudioReading(@NonNull String str) {
        sendPerformanceMetric(createMetricName(AUDIO_READING_FORCE_STOPPED), str);
    }

    public void sendLocaleMismatchMetric(@NonNull String str) {
        sendPerformanceMetric(createMetricName(SV_WW_LOCALE_MISMATCH), str);
    }

    public void sendProfileNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(PROFILE_NULL), str);
    }

    public void sendPryonNotSupportwwLocale(@NonNull String str) {
        sendPerformanceMetric(createMetricName(PRYON_NOT_SUPPORT_WW_LOCALE), str);
    }

    public void sendPryonliteInitFailed(@NonNull String str) {
        sendPerformanceMetric(createMetricName(VERIFY_PRYONLITE_INIT_FAILED), str);
    }

    public void sendPryonlitePushAudioFailed(@NonNull String str) {
        sendPerformanceMetric(createMetricName(VERIFY_PRYONLITE_PUSH_AUDIO_FAILED), str);
    }

    public void sendResetAudioFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(RESET_AUDIO), str, false);
    }

    public void sendResetAudioSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(RESET_AUDIO), str, true);
    }

    public void sendSVAcceptedEvent(@NonNull WakeWordMobilyticsMetadata wakeWordMobilyticsMetadata, @NonNull SpeakerVerificationMobilyticsMetadata speakerVerificationMobilyticsMetadata) {
        sendMetric(createSVClassificationEvent(SpeakerVerificationMobilyticsMetadata.SV_ACCEPTED, wakeWordMobilyticsMetadata, speakerVerificationMobilyticsMetadata));
    }

    public void sendSVModelLocaleNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(SV_MODEL_LOCALE_NULL), str);
    }

    public void sendSVModelNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(SV_MODEL_NULL), str);
    }

    public void sendSVRejectedEvent(@NonNull WakeWordMobilyticsMetadata wakeWordMobilyticsMetadata, @NonNull SpeakerVerificationMobilyticsMetadata speakerVerificationMobilyticsMetadata) {
        sendMetric(createSVClassificationEvent(SpeakerVerificationMobilyticsMetadata.SV_REJECTED, wakeWordMobilyticsMetadata, speakerVerificationMobilyticsMetadata));
    }

    public void sendSpeakerVerifiedFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(VERIFY_SPEAKER_VERIFIER), str, false);
    }

    public void sendSpeakerVerifiedSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(VERIFY_SPEAKER_VERIFIER), str, true);
    }

    public void sendSpeakerVerifierInitFailure(@NonNull String str) {
        sendPercentileMetric(createMetricName(INIT_SPEAKER_VERIFIER), str, false);
    }

    public void sendSpeakerVerifierInitSuccess(@NonNull String str) {
        sendPercentileMetric(createMetricName(INIT_SPEAKER_VERIFIER), str, true);
    }

    public void sendStartDialogTurn(@NonNull String str) {
        sendPerformanceMetric(createMetricName(START_DIALOG_TURN), str);
    }

    public void sendStopRecording(@NonNull String str) {
        sendPerformanceMetric(createMetricName(STOP_RECORDING), str);
    }

    public void sendWWModelLocaleNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(WW_MODEL_LOCALE_NULL), str);
    }

    public void sendWWModelNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(WW_MODEL_NULL), str);
    }

    public void sendWakeWordAbandonedEvent(@Nullable String str) {
        sendMetric(createWakeWordEvent(WakeWordInvocationEvent.WW_ABANDONED, str, null));
    }

    public void sendWakeWordAcceptedEvent(@NonNull String str, @NonNull String str2) {
        sendMetric(createWakeWordEvent(WakeWordInvocationEvent.WW_ACCEPTED, str, str2));
    }

    public void utteranceProviderServiceIsNull(@NonNull String str) {
        sendPerformanceMetric(createMetricName(UTTERANCE_PROVIDER_NULL), str);
    }
}
