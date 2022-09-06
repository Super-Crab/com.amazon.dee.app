package com.amazon.alexa.growth.coachmark;

import android.content.Context;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class ThemeHelper {
    public int getColorFromAttribute(Context context, int i) {
        return ThemeUtil.getColorFromAttribute(context, i);
    }

    public boolean isLightMode(Context context) {
        return ThemeUtil.isLightMode(context);
    }
}
