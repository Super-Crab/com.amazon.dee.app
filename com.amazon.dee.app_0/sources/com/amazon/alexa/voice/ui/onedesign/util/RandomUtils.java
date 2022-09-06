package com.amazon.alexa.voice.ui.onedesign.util;

import java.security.SecureRandom;
/* loaded from: classes11.dex */
public final class RandomUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    private RandomUtils() {
    }

    public static int getRandomNumber(int i) {
        return secureRandom.nextInt(i);
    }
}
