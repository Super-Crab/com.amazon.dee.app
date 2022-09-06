package com.amazon.identity.frc.helper;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
/* loaded from: classes12.dex */
public final class WindowManagerUtil {
    private static final String TAG = "com.amazon.identity.frc.helper.WindowManagerUtil";

    private WindowManagerUtil() {
    }

    private static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            defaultDisplay.getMetrics(displayMetrics);
        } else {
            Log.e(TAG, "getDefaultDisplay() returned is null.");
        }
        return displayMetrics;
    }

    public static String getWindowHeight(Context context) {
        return Integer.toString(getMetrics(context).heightPixels);
    }

    public static String getWindowWidth(Context context) {
        return Integer.toString(getMetrics(context).widthPixels);
    }
}
