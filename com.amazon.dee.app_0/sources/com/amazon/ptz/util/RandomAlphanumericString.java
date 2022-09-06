package com.amazon.ptz.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
/* loaded from: classes13.dex */
public class RandomAlphanumericString {
    private static final char[] CHARACTERS;
    private static final String DIGITS = "0123456789";
    private static final Random RANDOM;
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ENGLISH);

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(UPPER);
        outline107.append(LOWER);
        outline107.append(DIGITS);
        CHARACTERS = outline107.toString().toCharArray();
        RANDOM = new SecureRandom();
    }

    public static String getString(int i) {
        if (i >= 1) {
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < i; i2++) {
                char[] cArr2 = CHARACTERS;
                cArr[i2] = cArr2[RANDOM.nextInt(cArr2.length)];
            }
            return new String(cArr);
        }
        throw new IllegalArgumentException("Invalid attempt to generate a random alphanumeric string of length less than one");
    }
}
