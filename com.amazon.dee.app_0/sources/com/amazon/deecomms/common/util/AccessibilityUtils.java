package com.amazon.deecomms.common.util;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public final class AccessibilityUtils {
    private AccessibilityUtils() {
    }

    public static boolean isTalkBackEnabled(@NonNull Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }
}
