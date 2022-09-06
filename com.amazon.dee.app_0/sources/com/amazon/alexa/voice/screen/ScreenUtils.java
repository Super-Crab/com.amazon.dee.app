package com.amazon.alexa.voice.screen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class ScreenUtils {
    private static final double MAX_PHONE_SIZE_IN_INCHES = 6.5d;
    private static final String TAG = "com.amazon.alexa.voice.screen.ScreenUtils";
    private static Double deviceScreenSize;
    private static Boolean phoneFormFactor;

    private ScreenUtils() {
    }

    private static double calculateScreenSize(Context context) {
        DisplayMetrics displayMetrics;
        int i = getDisplayMetrics(context).densityDpi;
        double d = displayMetrics.widthPixels / i;
        double d2 = displayMetrics.heightPixels / i;
        if (Double.isNaN(d) || Double.isNaN(d2) || Double.isInfinite(d) || Double.isInfinite(d2)) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        double sqrt = Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(d, 2.0d));
        String str = "Screen size is: " + sqrt;
        return sqrt;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static double getScreenSize(Context context) {
        if (deviceScreenSize == null) {
            deviceScreenSize = Double.valueOf(calculateScreenSize(context));
        }
        return deviceScreenSize.doubleValue();
    }

    public static boolean isPhoneFormFactor(Context context) {
        if (phoneFormFactor == null) {
            phoneFormFactor = Boolean.valueOf(6.5d > getScreenSize(context));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isPhoneFormFactor: ");
        outline107.append(phoneFormFactor);
        outline107.toString();
        return phoneFormFactor.booleanValue();
    }
}
