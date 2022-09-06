package com.amazon.alexa.sharing.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.sharing.Constants;
import com.amazon.comms.log.CommsLogger;
/* loaded from: classes10.dex */
public class ThemingUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ThemingUtil.class);

    public void applyBackgroundColorToView(View view, Context context, int i) {
        if (view == null) {
            LOG.e("Skipped theming due to null view received while processing applyBackgroundColorToView.");
        } else {
            view.setBackgroundColor(getColorFromAttribute(context, i));
        }
    }

    public void applyTintColorToDrawable(Drawable drawable, Context context, int i) {
        if (drawable == null) {
            LOG.e("Skipped theming due to null drawable received while processing applyTintColorToDrawable.");
        } else {
            drawable.setTint(getColorFromAttribute(context, i));
        }
    }

    public int getColorFromAttribute(Context context, int i) {
        return ThemeUtil.getColorFromAttribute(context, i);
    }

    @SuppressLint({"InlinedApi"})
    public int getSystemUiVisibility(Context context) {
        return isLightMode(context) ? 8192 : 0;
    }

    public boolean isLightMode(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        return i == 16 || i != 32;
    }
}
