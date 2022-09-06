package com.amazon.alexa.accessory.speechapi.csm.metrics;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005\u0006\u0007\b\t\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants;", "", "()V", "COMPONENT_NAME", "", "UNKNOWN_DEVICE_TYPE", "AmbientSound", "CsmAlexaConnection", "CsmContext", "CsmSpeechMetrics", "ReportState", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MetricsConstants {
    @NotNull
    public static final String COMPONENT_NAME = "alexa_accessories";
    public static final MetricsConstants INSTANCE = new MetricsConstants();
    @NotNull
    public static final String UNKNOWN_DEVICE_TYPE = "UNKNOWN";

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants$AmbientSound;", "", "()V", "CSM_DIRECTIVE_PROCESSING", "", "CSM_DIRECTIVE_PROCESSING_ERROR", "CSM_DIRECTIVE_PROCESSING_INVALID_HEADER", "CSM_DIRECTIVE_PROCESSING_NO_INSTANCE", "CSM_DIRECTIVE_PROCESSING_START", "CSM_DIRECTIVE_PROCESSING_TIME", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AmbientSound {
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING = "CsmAmbientSoundDirectiveProcessing";
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING_ERROR = "CsmAmbientSoundDirectiveProcessingError";
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING_INVALID_HEADER = "CsmAmbientSoundDirectiveProcessingInvalidHeader";
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING_NO_INSTANCE = "CsmAmbientSoundDirectiveProcessingNoInstance";
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING_START = "CsmAmbientSoundDirectiveProcessingStart";
        @NotNull
        public static final String CSM_DIRECTIVE_PROCESSING_TIME = "CsmAmbientSoundDirectiveProcessingTime";
        public static final AmbientSound INSTANCE = new AmbientSound();

        private AmbientSound() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants$CsmAlexaConnection;", "", "()V", "CSM_ALEXA_CONNECTION_FAILED_REASON", "", "CSM_ALEXA_CONNECTION_SUCCESS", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CsmAlexaConnection {
        @NotNull
        public static final String CSM_ALEXA_CONNECTION_FAILED_REASON = "CsmAlexaConnectionFailedReason";
        @NotNull
        public static final String CSM_ALEXA_CONNECTION_SUCCESS = "CsmAlexaConnectionSuccess";
        public static final CsmAlexaConnection INSTANCE = new CsmAlexaConnection();

        private CsmAlexaConnection() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants$CsmContext;", "", "()V", "ADD_DEVICE_CONTEXT_COUNT", "", "ADD_DEVICE_CONTEXT_FAILED", "REMOVE_DEVICE_CONTEXT_COUNT", "REMOVE_DEVICE_CONTEXT_FAILED", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CsmContext {
        @NotNull
        public static final String ADD_DEVICE_CONTEXT_COUNT = "CsmAddDeviceContextCount";
        @NotNull
        public static final String ADD_DEVICE_CONTEXT_FAILED = "CsmAddDeviceContextFailed";
        public static final CsmContext INSTANCE = new CsmContext();
        @NotNull
        public static final String REMOVE_DEVICE_CONTEXT_COUNT = "CsmRemoveDeviceContextCount";
        @NotNull
        public static final String REMOVE_DEVICE_CONTEXT_FAILED = "CsmRemoveDeviceContextFailed";

        private CsmContext() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants$CsmSpeechMetrics;", "", "()V", "DIALOG_REQUESTED", "", "ERROR_ALEXA_EXCEPTION_IN_DIALOG_TURN", "ERROR_ALEXA_EXCEPTION_IN_NEXT_DIALOG_TURN", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CsmSpeechMetrics {
        @NotNull
        public static final String DIALOG_REQUESTED = "CsmOnDialogRequested";
        @NotNull
        public static final String ERROR_ALEXA_EXCEPTION_IN_DIALOG_TURN = "CsmErrorAlexaExceptionInDialogTurn";
        @NotNull
        public static final String ERROR_ALEXA_EXCEPTION_IN_NEXT_DIALOG_TURN = "CsmErrorAlexaExceptionInNextDialogTurn";
        public static final CsmSpeechMetrics INSTANCE = new CsmSpeechMetrics();

        private CsmSpeechMetrics() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/metrics/MetricsConstants$ReportState;", "", "()V", "REPORT_STATE_DIRECTIVE", "", "REPORT_STATE_DIRECTIVE_PROCESSING", "REPORT_STATE_DIRECTIVE_PROCESSING_ERROR", "REPORT_STATE_GENERATOR", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ReportState {
        public static final ReportState INSTANCE = new ReportState();
        @NotNull
        public static final String REPORT_STATE_DIRECTIVE = "ReportAccessoryStateDirective";
        @NotNull
        public static final String REPORT_STATE_DIRECTIVE_PROCESSING = "ReportAccessoryStateDirectiveProcessing";
        @NotNull
        public static final String REPORT_STATE_DIRECTIVE_PROCESSING_ERROR = "ReportAccessoryStateDirectiveProcessingError";
        @NotNull
        public static final String REPORT_STATE_GENERATOR = "ReportAccessoryStateGenerator";

        private ReportState() {
        }
    }

    private MetricsConstants() {
    }
}
