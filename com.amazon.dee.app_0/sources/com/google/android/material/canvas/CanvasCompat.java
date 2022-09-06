package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF rectF, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return canvas.saveLayerAlpha(rectF, i);
    }

    public static int saveLayerAlpha(Canvas canvas, float f, float f2, float f3, float f4, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return canvas.saveLayerAlpha(f, f2, f3, f4, i);
    }
}
