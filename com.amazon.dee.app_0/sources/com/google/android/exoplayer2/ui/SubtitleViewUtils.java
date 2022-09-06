package com.google.android.exoplayer2.ui;
/* loaded from: classes2.dex */
final class SubtitleViewUtils {
    private SubtitleViewUtils() {
    }

    public static float resolveTextSize(int i, float f, int i2, int i3) {
        float f2;
        if (f == -3.4028235E38f) {
            return -3.4028235E38f;
        }
        if (i == 0) {
            f2 = i3;
        } else if (i != 1) {
            if (i == 2) {
                return f;
            }
            return -3.4028235E38f;
        } else {
            f2 = i2;
        }
        return f * f2;
    }
}
