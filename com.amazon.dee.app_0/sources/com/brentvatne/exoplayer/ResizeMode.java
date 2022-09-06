package com.brentvatne.exoplayer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
class ResizeMode {
    static final int RESIZE_MODE_CENTER_CROP = 4;
    static final int RESIZE_MODE_FILL = 3;
    static final int RESIZE_MODE_FIT = 0;
    static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    static final int RESIZE_MODE_FIXED_WIDTH = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Mode {
    }

    ResizeMode() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toResizeMode(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        return 0;
                    }
                }
            }
        }
        return i2;
    }
}
