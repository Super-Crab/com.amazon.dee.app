package com.amazon.alexa.fitness.util;

import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ConversionUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/util/ConversionUtils;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ConversionUtils {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ConversionUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/util/ConversionUtils$Companion;", "", "()V", "convertFeetPerSecondToMilesPerHour", "", "speedInFeetPerSecond", "convertFeetToMeter", Metrics.DISTANCE_IN_FEET, "convertFeetToMiles", "convertMetersToFeet", "distanceInMeters", "convertMilesPerHourToMetersPerSecond", "speedInMilesPerHour", "convertMilesPerHourToMinutesPerKilometer", "convertMilesToFeet", "distanceInMiles", "convertTimeInSecondsToTimeInHours", "timeInSeconds", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        public final double convertFeetPerSecondToMilesPerHour(double d) {
            return ((d * 0.3048f) * 3600) / 1609.344f;
        }

        public final double convertFeetToMeter(double d) {
            return 0.3048f * d;
        }

        public final double convertFeetToMiles(double d) {
            return 1.89394E-4f * d;
        }

        public final double convertMetersToFeet(double d) {
            return d / 0.3048f;
        }

        public final double convertMilesPerHourToMetersPerSecond(double d) {
            return (1609.344f * d) / 3600;
        }

        public final double convertMilesPerHourToMinutesPerKilometer(double d) {
            if (d == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            }
            return 60 / ((d * 1609.344f) / 1000);
        }

        public final double convertMilesToFeet(double d) {
            return d / 1.89394E-4f;
        }

        public final double convertTimeInSecondsToTimeInHours(int i) {
            if (i != 0) {
                double d = 60;
                return (i / d) / d;
            }
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
