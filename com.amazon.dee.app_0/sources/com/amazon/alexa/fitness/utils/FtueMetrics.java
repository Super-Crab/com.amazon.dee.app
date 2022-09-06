package com.amazon.alexa.fitness.utils;

import com.amazon.alexa.biloba.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FtueMetrics;", "", "()V", "LocationPermissions", "RouteTracking", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueMetrics {
    public static final FtueMetrics INSTANCE = new FtueMetrics();

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FtueMetrics$LocationPermissions;", "", "()V", "Later", "Lcom/amazon/alexa/fitness/utils/LocationPermissionsMetricComponent;", "getLater", "()Lcom/amazon/alexa/fitness/utils/LocationPermissionsMetricComponent;", MetricsConstants.UserInteractionMetrics.LEARN_MORE, "getLearnMore", MetricsConstants.UserInteractionMetrics.SETTINGS_PREFIX, "getSettings", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class LocationPermissions {
        public static final LocationPermissions INSTANCE = new LocationPermissions();
        @NotNull
        private static final LocationPermissionsMetricComponent LearnMore = new LocationPermissionsMetricComponent("learnMoreButton");
        @NotNull
        private static final LocationPermissionsMetricComponent Later = new LocationPermissionsMetricComponent("laterButton");
        @NotNull
        private static final LocationPermissionsMetricComponent Settings = new LocationPermissionsMetricComponent("settingsButton");

        private LocationPermissions() {
        }

        @NotNull
        public final LocationPermissionsMetricComponent getLater() {
            return Later;
        }

        @NotNull
        public final LocationPermissionsMetricComponent getLearnMore() {
            return LearnMore;
        }

        @NotNull
        public final LocationPermissionsMetricComponent getSettings() {
            return Settings;
        }
    }

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FtueMetrics$RouteTracking;", "", "()V", "Enable", "Lcom/amazon/alexa/fitness/utils/FtueTrackingMetricComponent;", "getEnable", "()Lcom/amazon/alexa/fitness/utils/FtueTrackingMetricComponent;", "Later", "getLater", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class RouteTracking {
        public static final RouteTracking INSTANCE = new RouteTracking();
        @NotNull
        private static final FtueTrackingMetricComponent Enable = new FtueTrackingMetricComponent("enableButton");
        @NotNull
        private static final FtueTrackingMetricComponent Later = new FtueTrackingMetricComponent("laterButton");

        private RouteTracking() {
        }

        @NotNull
        public final FtueTrackingMetricComponent getEnable() {
            return Enable;
        }

        @NotNull
        public final FtueTrackingMetricComponent getLater() {
            return Later;
        }
    }

    private FtueMetrics() {
    }
}
