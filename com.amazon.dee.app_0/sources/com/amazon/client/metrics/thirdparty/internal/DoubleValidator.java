package com.amazon.client.metrics.thirdparty.internal;
/* loaded from: classes11.dex */
public class DoubleValidator {
    private DoubleValidator() {
    }

    public static void validateDouble(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(String.format("Invalid value %f.  Cannot be NaN or infinite.", Double.valueOf(d)));
        }
    }
}
