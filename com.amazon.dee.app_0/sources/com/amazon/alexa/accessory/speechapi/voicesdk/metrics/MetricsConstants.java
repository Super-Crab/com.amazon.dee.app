package com.amazon.alexa.accessory.speechapi.voicesdk.metrics;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/metrics/MetricsConstants;", "", "()V", "COMPONENT_NAME", "", "UNKNOWN_DEVICE_TYPE", "AmbientSound", "ReportState", "VoxAlexaConnection", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MetricsConstants {
    @NotNull
    public static final String COMPONENT_NAME = "alexa_accessories";
    public static final MetricsConstants INSTANCE = new MetricsConstants();
    @NotNull
    public static final String UNKNOWN_DEVICE_TYPE = "UNKNOWN";

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/metrics/MetricsConstants$AmbientSound;", "", "()V", "VOX_AMBIENT_SOUND_PROCESSING_DIRECTIVE", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AmbientSound {
        public static final AmbientSound INSTANCE = new AmbientSound();
        @NotNull
        public static final String VOX_AMBIENT_SOUND_PROCESSING_DIRECTIVE = "VoxAmbientSoundProcessing";

        private AmbientSound() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/metrics/MetricsConstants$ReportState;", "", "()V", "REPORT_STATE_CAPABILITY_INITIALIZATION", "", "REPORT_STATE_DIRECTIVE", "REPORT_STATE_DIRECTIVE_PROCESSING", "REPORT_STATE_GENERATOR", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ReportState {
        public static final ReportState INSTANCE = new ReportState();
        @NotNull
        public static final String REPORT_STATE_CAPABILITY_INITIALIZATION = "ReportAccessoryStateCapabilityInitialization";
        @NotNull
        public static final String REPORT_STATE_DIRECTIVE = "ReportAccessoryStateDirective";
        @NotNull
        public static final String REPORT_STATE_DIRECTIVE_PROCESSING = "ReportAccessoryStateDirectiveProcessing";
        @NotNull
        public static final String REPORT_STATE_GENERATOR = "ReportAccessoryStateGenerator";

        private ReportState() {
        }
    }

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/metrics/MetricsConstants$VoxAlexaConnection;", "", "()V", "VOX_ALEXA_CONNECTION_FAILED_REASON", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class VoxAlexaConnection {
        public static final VoxAlexaConnection INSTANCE = new VoxAlexaConnection();
        @NotNull
        public static final String VOX_ALEXA_CONNECTION_FAILED_REASON = "VoxAlexaConnectionFailedReason";

        private VoxAlexaConnection() {
        }
    }

    private MetricsConstants() {
    }
}
