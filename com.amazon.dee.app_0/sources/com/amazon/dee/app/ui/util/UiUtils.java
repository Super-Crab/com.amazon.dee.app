package com.amazon.dee.app.ui.util;

import android.content.Context;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes12.dex */
public final class UiUtils {
    private UiUtils() {
    }

    public static boolean isLightMode(Context context) {
        return ThemeUtil.isLightMode(context);
    }

    public static int transformDpiToPx(Context context, int i) {
        return (int) Math.ceil(i * context.getResources().getDisplayMetrics().density);
    }

    public static int transformSpToPx(Context context, int i) {
        return (int) Math.ceil(i * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
