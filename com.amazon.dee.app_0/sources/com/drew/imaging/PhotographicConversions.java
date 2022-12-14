package com.drew.imaging;
/* loaded from: classes2.dex */
public final class PhotographicConversions {
    public static final double ROOT_TWO = Math.sqrt(2.0d);

    private PhotographicConversions() throws Exception {
        throw new Exception("Not intended for instantiation.");
    }

    public static double apertureToFStop(double d) {
        return Math.pow(ROOT_TWO, d);
    }

    public static double shutterSpeedToExposureTime(double d) {
        return (float) (1.0d / Math.exp(Math.log(2.0d) * d));
    }
}
