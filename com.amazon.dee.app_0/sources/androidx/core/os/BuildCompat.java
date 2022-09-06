package androidx.core.os;

import android.os.Build;
/* loaded from: classes.dex */
public class BuildCompat {
    private BuildCompat() {
    }

    @Deprecated
    public static boolean isAtLeastN() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isAtLeastQ() {
        return Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'Q' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
    }
}
