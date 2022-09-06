package com.airbnb.lottie.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class GammaEvaluator {
    private static float EOCF_sRGB(float f) {
        return f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    private static float OECF_sRGB(float f) {
        return f <= 0.0031308f ? f * 12.92f : (float) ((Math.pow(f, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    public static int evaluate(float f, int i, int i2) {
        float f2 = ((i >> 24) & 255) / 255.0f;
        float EOCF_sRGB = EOCF_sRGB(((i >> 16) & 255) / 255.0f);
        float EOCF_sRGB2 = EOCF_sRGB(((i >> 8) & 255) / 255.0f);
        float EOCF_sRGB3 = EOCF_sRGB((i & 255) / 255.0f);
        float EOCF_sRGB4 = EOCF_sRGB(((i2 >> 16) & 255) / 255.0f);
        float EOCF_sRGB5 = EOCF_sRGB(((i2 >> 8) & 255) / 255.0f);
        float EOCF_sRGB6 = EOCF_sRGB((i2 & 255) / 255.0f);
        float outline0 = GeneratedOutlineSupport1.outline0(((i2 >> 24) & 255) / 255.0f, f2, f, f2);
        float outline02 = GeneratedOutlineSupport1.outline0(EOCF_sRGB4, EOCF_sRGB, f, EOCF_sRGB);
        float outline03 = GeneratedOutlineSupport1.outline0(EOCF_sRGB5, EOCF_sRGB2, f, EOCF_sRGB2);
        float outline04 = GeneratedOutlineSupport1.outline0(EOCF_sRGB6, EOCF_sRGB3, f, EOCF_sRGB3);
        int round = Math.round(OECF_sRGB(outline02) * 255.0f) << 16;
        return Math.round(OECF_sRGB(outline04) * 255.0f) | round | (Math.round(outline0 * 255.0f) << 24) | (Math.round(OECF_sRGB(outline03) * 255.0f) << 8);
    }
}
