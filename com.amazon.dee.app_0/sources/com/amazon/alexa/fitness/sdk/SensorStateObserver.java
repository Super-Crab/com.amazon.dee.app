package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.location.utils.MetricsUtil;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SensorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&R8\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "", "sensorIdToLastKnownUnavailableTime", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getSensorIdToLastKnownUnavailableTime", "()Ljava/util/HashMap;", "setSensorIdToLastKnownUnavailableTime", "(Ljava/util/HashMap;)V", "onAvailabilityChanged", "", "sensorId", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "timestamp", "error", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SensorStateObserver {

    /* compiled from: SensorProvider.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onAvailabilityChanged$default(SensorStateObserver sensorStateObserver, String str, SensorAvailability sensorAvailability, long j, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 8) != 0) {
                    th = null;
                }
                sensorStateObserver.onAvailabilityChanged(str, sensorAvailability, j, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onAvailabilityChanged");
        }
    }

    @NotNull
    HashMap<String, Long> getSensorIdToLastKnownUnavailableTime();

    void onAvailabilityChanged(@NotNull String str, @NotNull SensorAvailability sensorAvailability, long j, @Nullable Throwable th);

    void setSensorIdToLastKnownUnavailableTime(@NotNull HashMap<String, Long> hashMap);
}
