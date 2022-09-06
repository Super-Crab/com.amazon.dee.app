package com.amazon.alexa.presence.bleconn.helpers;

import java.util.Random;
/* loaded from: classes9.dex */
public final class StringHelper {
    private static final Random RNG = new Random();
    private static final char[] ALPHA_DIGIT_CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private StringHelper() {
    }

    public static String randomString(int i) {
        return randomString(i, ALPHA_DIGIT_CHARSET);
    }

    public static String randomString(int i, char[] cArr) {
        char[] cArr2 = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr2[i2] = cArr[RNG.nextInt(cArr.length)];
        }
        return new String(cArr2);
    }
}
