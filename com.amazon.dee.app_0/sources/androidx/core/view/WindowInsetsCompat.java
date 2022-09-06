package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.WindowInsets;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class WindowInsetsCompat {
    private final Object mInsets;

    private WindowInsetsCompat(Object obj) {
        this.mInsets = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object unwrap(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat == null) {
            return null;
        }
        return windowInsetsCompat.mInsets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WindowInsetsCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new WindowInsetsCompat(obj);
    }

    public WindowInsetsCompat consumeDisplayCutout() {
        return Build.VERSION.SDK_INT >= 28 ? new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeDisplayCutout()) : this;
    }

    public WindowInsetsCompat consumeStableInsets() {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeStableInsets());
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).consumeSystemWindowInsets());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WindowInsetsCompat.class != obj.getClass()) {
            return false;
        }
        Object obj2 = this.mInsets;
        Object obj3 = ((WindowInsetsCompat) obj).mInsets;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        if (Build.VERSION.SDK_INT >= 28) {
            return DisplayCutoutCompat.wrap(((WindowInsets) this.mInsets).getDisplayCutout());
        }
        return null;
    }

    public int getStableInsetBottom() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetBottom();
    }

    public int getStableInsetLeft() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetLeft();
    }

    public int getStableInsetRight() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetRight();
    }

    public int getStableInsetTop() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetTop();
    }

    public int getSystemWindowInsetBottom() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetBottom();
    }

    public int getSystemWindowInsetLeft() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetLeft();
    }

    public int getSystemWindowInsetRight() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetRight();
    }

    public int getSystemWindowInsetTop() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetTop();
    }

    public boolean hasInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasInsets();
    }

    public boolean hasStableInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasStableInsets();
    }

    public boolean hasSystemWindowInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasSystemWindowInsets();
    }

    public int hashCode() {
        Object obj = this.mInsets;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isConsumed() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).isConsumed();
    }

    public boolean isRound() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).isRound();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).replaceSystemWindowInsets(i, i2, i3, i4));
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        int i = Build.VERSION.SDK_INT;
        this.mInsets = windowInsetsCompat == null ? null : new WindowInsets((WindowInsets) windowInsetsCompat.mInsets);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).replaceSystemWindowInsets(rect));
    }
}
