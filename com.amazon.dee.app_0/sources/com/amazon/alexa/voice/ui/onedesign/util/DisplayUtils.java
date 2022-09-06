package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.Context;
/* loaded from: classes11.dex */
public final class DisplayUtils {
    private DisplayUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static int transformDpiToPx(Context context, int i) {
        return (int) Math.ceil(i * context.getResources().getDisplayMetrics().density);
    }
}
