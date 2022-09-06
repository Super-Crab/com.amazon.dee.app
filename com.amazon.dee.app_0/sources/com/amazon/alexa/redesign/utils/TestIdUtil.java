package com.amazon.alexa.redesign.utils;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public final class TestIdUtil {
    private TestIdUtil() {
    }

    @SuppressLint({"DefaultLocale"})
    private static String formatAutomationString(@NonNull String str) {
        return str.replace(" ", "_").toLowerCase();
    }

    public static void setTestId(@NonNull View view, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        view.setContentDescription(formatAutomationString(GeneratedOutlineSupport1.outline76(str, "_", str2, str3)));
    }
}
