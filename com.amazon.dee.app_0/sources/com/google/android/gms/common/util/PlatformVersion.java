package com.google.android.gms.common.util;

import android.os.Build;
import androidx.core.os.BuildCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public final class PlatformVersion {
    private PlatformVersion() {
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycomb() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycombMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwichMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBean() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR2() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKat() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKatWatch() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipop() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipopMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastM() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastN() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastO() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @KeepForSdk
    public static boolean isAtLeastQ() {
        return BuildCompat.isAtLeastQ() || (Build.VERSION.CODENAME.equals("REL") && Build.VERSION.SDK_INT >= 29) || (Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'Q' && Build.VERSION.CODENAME.charAt(0) <= 'Z');
    }
}
