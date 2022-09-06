package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
@DoNotStrip
/* loaded from: classes2.dex */
public class NativeBlurFilter {
    static {
        NativeFiltersLoader.load();
    }

    public static void iterativeBoxBlur(Bitmap bitmap, int iterations, int blurRadius) {
        Preconditions.checkNotNull(bitmap);
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(iterations > 0));
        if (blurRadius <= 0) {
            z = false;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        nativeIterativeBoxBlur(bitmap, iterations, blurRadius);
    }

    @DoNotStrip
    private static native void nativeIterativeBoxBlur(Bitmap bitmap, int iterations, int blurRadius);
}
