package com.amazon.alexa.fitness.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ConversionUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0004\n\u0000\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0007\u001a\n\u0010\t\u001a\u00020\u0007*\u00020\u0007\u001a\n\u0010\n\u001a\u00020\u0007*\u00020\u0007\u001a\n\u0010\u000b\u001a\u00020\u0007*\u00020\u0007\u001a\n\u0010\f\u001a\u00020\u0005*\u00020\u0005\u001a\n\u0010\r\u001a\u00020\u0007*\u00020\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"FEET_TO_METER_CONVERSION", "", "FEET_TO_MILE_CONVERSION", "MILE_TO_METER_CONVERSION", "MILLISECONDS_IN_A_SECOND", "", "feetToKm", "", "feetToMeters", "metersToFeet", "mphToMetersPerSecond", "mphToMinutesPerKm", "secondsToMilliseconds", "secondsToMinutes", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ConversionUtilsKt {
    private static final float FEET_TO_METER_CONVERSION = 0.3048f;
    private static final float FEET_TO_MILE_CONVERSION = 1.89394E-4f;
    private static final float MILE_TO_METER_CONVERSION = 1609.344f;
    private static final int MILLISECONDS_IN_A_SECOND = 1000;

    public static final double feetToKm(double d) {
        return (d * ((double) FEET_TO_METER_CONVERSION)) / 1000;
    }

    public static final double feetToMeters(double d) {
        return ConversionUtils.Companion.convertFeetToMeter(d);
    }

    public static final double metersToFeet(double d) {
        return ConversionUtils.Companion.convertMetersToFeet(d);
    }

    public static final double mphToMetersPerSecond(double d) {
        return ConversionUtils.Companion.convertMilesPerHourToMetersPerSecond(d);
    }

    public static final double mphToMinutesPerKm(double d) {
        return ConversionUtils.Companion.convertMilesPerHourToMinutesPerKilometer(d);
    }

    public static final int secondsToMilliseconds(int i) {
        return i * 1000;
    }

    public static final double secondsToMinutes(@NotNull Number secondsToMinutes) {
        Intrinsics.checkParameterIsNotNull(secondsToMinutes, "$this$secondsToMinutes");
        return secondsToMinutes.doubleValue() / 60;
    }
}
