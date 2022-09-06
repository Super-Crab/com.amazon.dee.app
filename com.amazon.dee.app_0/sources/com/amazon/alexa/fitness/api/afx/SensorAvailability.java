package com.amazon.alexa.fitness.api.afx;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "", "()V", "Available", DriveModeMetrics.NetworkStatus.UNAVAILABLE, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability$Available;", "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability$Unavailable;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class SensorAvailability {

    /* compiled from: FitnessSessionOrchestrator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/SensorAvailability$Available;", "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Available extends SensorAvailability {
        public static final Available INSTANCE = new Available();

        private Available() {
            super(null);
        }
    }

    /* compiled from: FitnessSessionOrchestrator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/SensorAvailability$Unavailable;", "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/fitness/api/afx/SensorUnavailabilityReason;", "(Lcom/amazon/alexa/fitness/api/afx/SensorUnavailabilityReason;)V", "getReason", "()Lcom/amazon/alexa/fitness/api/afx/SensorUnavailabilityReason;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Unavailable extends SensorAvailability {
        @NotNull
        private final SensorUnavailabilityReason reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unavailable(@NotNull SensorUnavailabilityReason reason) {
            super(null);
            Intrinsics.checkParameterIsNotNull(reason, "reason");
            this.reason = reason;
        }

        @NotNull
        public final SensorUnavailabilityReason getReason() {
            return this.reason;
        }
    }

    private SensorAvailability() {
    }

    public /* synthetic */ SensorAvailability(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
