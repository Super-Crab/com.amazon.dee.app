package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.Context;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
/* loaded from: classes11.dex */
public final class StatusBar {
    @ColorRes
    @VisibleForTesting
    static final int DEFAULT_COLOR_RESOURCE_ID = R.color.voice_ui_od_background;
    @BoolRes
    @VisibleForTesting
    static final int ENABLEMENT_RESOURCE_ID = R.bool.shouldChangeStatusBarColor;

    private StatusBar() {
        throw new IllegalStateException("No instances, bruh!");
    }

    public static void tryFlood(@Nullable Component component, @NonNull Context context, @ColorInt int i) {
        boolean z = context.getResources().getBoolean(ENABLEMENT_RESOURCE_ID);
        boolean z2 = component != null && component.isRegistered(WindowInteractor.class);
        if (!z || !z2) {
            return;
        }
        ((WindowInteractor) component.get(WindowInteractor.class)).setStatusBarColor(i);
    }

    public static void tryFlood(@Nullable Component component, @NonNull Context context) {
        tryFlood(component, context, ContextCompat.getColor(context, DEFAULT_COLOR_RESOURCE_ID));
    }
}
