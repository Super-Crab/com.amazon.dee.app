package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityWindowInfo;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;

    private AccessibilityWindowInfoCompat(Object obj) {
        this.mInfo = obj;
    }

    public static AccessibilityWindowInfoCompat obtain() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }

    private static String typeToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY" : "TYPE_SYSTEM" : "TYPE_INPUT_METHOD" : "TYPE_APPLICATION";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessibilityWindowInfoCompat wrapNonNullInstance(Object obj) {
        if (obj != null) {
            return new AccessibilityWindowInfoCompat(obj);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccessibilityWindowInfoCompat.class != obj.getClass()) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        Object obj2 = this.mInfo;
        if (obj2 == null) {
            if (accessibilityWindowInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!obj2.equals(accessibilityWindowInfoCompat.mInfo)) {
            return false;
        }
        return true;
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        int i = Build.VERSION.SDK_INT;
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getAnchor());
    }

    public void getBoundsInScreen(Rect rect) {
        int i = Build.VERSION.SDK_INT;
        ((AccessibilityWindowInfo) this.mInfo).getBoundsInScreen(rect);
    }

    public AccessibilityWindowInfoCompat getChild(int i) {
        int i2 = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getChild(i));
    }

    public int getChildCount() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getChildCount();
    }

    public int getId() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getId();
    }

    public int getLayer() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getLayer();
    }

    public AccessibilityWindowInfoCompat getParent() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getParent());
    }

    public AccessibilityNodeInfoCompat getRoot() {
        int i = Build.VERSION.SDK_INT;
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getRoot());
    }

    public CharSequence getTitle() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getTitle();
    }

    public int getType() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).getType();
    }

    public int hashCode() {
        Object obj = this.mInfo;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isAccessibilityFocused() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isAccessibilityFocused();
    }

    public boolean isActive() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isActive();
    }

    public boolean isFocused() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityWindowInfo) this.mInfo).isFocused();
    }

    public void recycle() {
        int i = Build.VERSION.SDK_INT;
        ((AccessibilityWindowInfo) this.mInfo).recycle();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=");
        sb.append(getId());
        sb.append(", type=");
        sb.append(typeToString(getType()));
        sb.append(", layer=");
        sb.append(getLayer());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(isFocused());
        sb.append(", active=");
        sb.append(isActive());
        sb.append(", hasParent=");
        boolean z = true;
        sb.append(getParent() != null);
        sb.append(", hasChildren=");
        if (getChildCount() <= 0) {
            z = false;
        }
        sb.append(z);
        sb.append(JsonReaderKt.END_LIST);
        return sb.toString();
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        int i = Build.VERSION.SDK_INT;
        if (accessibilityWindowInfoCompat == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo) accessibilityWindowInfoCompat.mInfo));
    }
}
