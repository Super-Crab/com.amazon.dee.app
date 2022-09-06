package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance = new ArgbEvaluatorCompat();

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    @Override // android.animation.TypeEvaluator
    public Integer evaluate(float f, Integer num, Integer num2) {
        int intValue = num.intValue();
        float f2 = ((intValue >> 24) & 255) / 255.0f;
        int intValue2 = num2.intValue();
        float pow = (float) Math.pow(((intValue >> 16) & 255) / 255.0f, 2.2d);
        float pow2 = (float) Math.pow(((intValue >> 8) & 255) / 255.0f, 2.2d);
        float pow3 = (float) Math.pow((intValue & 255) / 255.0f, 2.2d);
        float pow4 = (float) Math.pow(((intValue2 >> 16) & 255) / 255.0f, 2.2d);
        float outline0 = GeneratedOutlineSupport1.outline0(((intValue2 >> 24) & 255) / 255.0f, f2, f, f2);
        float outline02 = GeneratedOutlineSupport1.outline0(pow4, pow, f, pow);
        float outline03 = GeneratedOutlineSupport1.outline0((float) Math.pow(((intValue2 >> 8) & 255) / 255.0f, 2.2d), pow2, f, pow2);
        float outline04 = GeneratedOutlineSupport1.outline0((float) Math.pow((intValue2 & 255) / 255.0f, 2.2d), pow3, f, pow3);
        int round = Math.round(((float) Math.pow(outline02, 0.45454545454545453d)) * 255.0f) << 16;
        return Integer.valueOf(Math.round(((float) Math.pow(outline04, 0.45454545454545453d)) * 255.0f) | round | (Math.round(outline0 * 255.0f) << 24) | (Math.round(((float) Math.pow(outline03, 0.45454545454545453d)) * 255.0f) << 8));
    }
}
