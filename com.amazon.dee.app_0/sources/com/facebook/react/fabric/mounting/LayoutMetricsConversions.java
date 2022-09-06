package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaMeasureMode;
/* loaded from: classes2.dex */
public class LayoutMetricsConversions {
    public static float getMaxSize(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 0) {
            return Float.POSITIVE_INFINITY;
        }
        return size;
    }

    public static float getMinSize(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        return 0.0f;
    }

    public static YogaMeasureMode getYogaMeasureMode(float f, float f2) {
        if (f == f2) {
            return YogaMeasureMode.EXACTLY;
        }
        if (Float.isInfinite(f2)) {
            return YogaMeasureMode.UNDEFINED;
        }
        return YogaMeasureMode.AT_MOST;
    }

    public static float getYogaSize(float f, float f2) {
        if (f == f2) {
            return PixelUtil.toPixelFromDIP(f2);
        }
        if (!Float.isInfinite(f2)) {
            return PixelUtil.toPixelFromDIP(f2);
        }
        return Float.POSITIVE_INFINITY;
    }
}
