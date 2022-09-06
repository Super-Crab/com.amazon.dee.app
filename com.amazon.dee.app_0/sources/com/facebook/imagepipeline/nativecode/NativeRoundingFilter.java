package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
@DoNotStrip
/* loaded from: classes2.dex */
public class NativeRoundingFilter {
    static {
        NativeFiltersLoader.load();
    }

    public static void addRoundedCorners(Bitmap bitmap, int radiusTopLeft, int radiusTopRight, int radiusBottomRight, int radiusBottomLeft) {
        nativeAddRoundedCornersFilter(bitmap, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft);
    }

    @DoNotStrip
    private static native void nativeAddRoundedCornersFilter(Bitmap bitmap, int radiusTopLeft, int radiusTopRight, int radiusBottomRight, int radiusBottomLeft);

    @DoNotStrip
    private static native void nativeToCircleFastFilter(Bitmap bitmap, boolean antiAliased);

    @DoNotStrip
    private static native void nativeToCircleFilter(Bitmap bitmap, boolean antiAliased);

    @DoNotStrip
    private static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int colorARGB, int borderWidthPx, boolean antiAliased);

    public static void toCircle(Bitmap bitmap) {
        toCircle(bitmap, false);
    }

    public static void toCircleFast(Bitmap bitmap) {
        toCircleFast(bitmap, false);
    }

    public static void toCircleWithBorder(Bitmap bitmap, int colorARGB, int borderWidthPx, boolean antiAliased) {
        Preconditions.checkNotNull(bitmap);
        if (bitmap.getWidth() < 3 || bitmap.getHeight() < 3) {
            return;
        }
        nativeToCircleWithBorderFilter(bitmap, colorARGB, borderWidthPx, antiAliased);
    }

    @DoNotStrip
    public static void toCircle(Bitmap bitmap, boolean antiAliased) {
        Preconditions.checkNotNull(bitmap);
        if (bitmap.getWidth() < 3 || bitmap.getHeight() < 3) {
            return;
        }
        nativeToCircleFilter(bitmap, antiAliased);
    }

    @DoNotStrip
    public static void toCircleFast(Bitmap bitmap, boolean antiAliased) {
        Preconditions.checkNotNull(bitmap);
        if (bitmap.getWidth() < 3 || bitmap.getHeight() < 3) {
            return;
        }
        nativeToCircleFastFilter(bitmap, antiAliased);
    }
}
