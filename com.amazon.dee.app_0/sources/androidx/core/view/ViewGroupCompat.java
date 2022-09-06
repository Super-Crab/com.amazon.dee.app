package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class ViewGroupCompat {
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    private ViewGroupCompat() {
    }

    public static int getLayoutMode(@NonNull ViewGroup viewGroup) {
        int i = Build.VERSION.SDK_INT;
        return viewGroup.getLayoutMode();
    }

    public static int getNestedScrollAxes(@NonNull ViewGroup viewGroup) {
        int i = Build.VERSION.SDK_INT;
        return viewGroup.getNestedScrollAxes();
    }

    public static boolean isTransitionGroup(@NonNull ViewGroup viewGroup) {
        int i = Build.VERSION.SDK_INT;
        return viewGroup.isTransitionGroup();
    }

    @Deprecated
    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return viewGroup.onRequestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static void setLayoutMode(@NonNull ViewGroup viewGroup, int i) {
        int i2 = Build.VERSION.SDK_INT;
        viewGroup.setLayoutMode(i);
    }

    @Deprecated
    public static void setMotionEventSplittingEnabled(ViewGroup viewGroup, boolean z) {
        viewGroup.setMotionEventSplittingEnabled(z);
    }

    public static void setTransitionGroup(@NonNull ViewGroup viewGroup, boolean z) {
        int i = Build.VERSION.SDK_INT;
        viewGroup.setTransitionGroup(z);
    }
}
