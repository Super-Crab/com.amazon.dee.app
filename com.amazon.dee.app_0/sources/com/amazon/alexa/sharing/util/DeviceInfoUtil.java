package com.amazon.alexa.sharing.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.Constants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class DeviceInfoUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceInfoUtil.class);
    private static Double deviceScreenSize;
    private static Boolean isPhoneDevice;

    private double calculateScreenSize(@NonNull Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        int i = displayMetrics.densityDpi;
        double d = displayMetrics.widthPixels / i;
        double d2 = displayMetrics.heightPixels / i;
        if (!Double.isNaN(d) && !Double.isNaN(d2) && !Double.isInfinite(d) && !Double.isInfinite(d2)) {
            double sqrt = Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(d, 2.0d));
            CommsLogger commsLogger = LOG;
            commsLogger.d("Device Screen size is: " + sqrt);
            return sqrt;
        }
        CommsLogger commsLogger2 = LOG;
        commsLogger2.e("Device dimensions are invalid. H: " + d2 + ", W: " + d);
        return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    private DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public double getDeviceScreenSize(@NonNull Context context) {
        if (deviceScreenSize == null) {
            deviceScreenSize = Double.valueOf(calculateScreenSize(context));
        }
        return deviceScreenSize.doubleValue();
    }

    @SuppressLint({"AnnotateVersionCheck"})
    public boolean isBelowLolliPop() {
        int i = Build.VERSION.SDK_INT;
        return false;
    }

    @SuppressLint({"AnnotateVersionCheck"})
    public boolean isKitKatAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @SuppressLint({"AnnotateVersionCheck"})
    public boolean isMAndAbove() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public boolean isPhone(@NonNull Context context) {
        if (isPhoneDevice == null) {
            isPhoneDevice = Boolean.valueOf(6.5d > getDeviceScreenSize(context));
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Is device a phone: ");
        outline107.append(isPhoneDevice);
        commsLogger.d(outline107.toString());
        return isPhoneDevice.booleanValue();
    }

    @SuppressLint({"AnnotateVersionCheck"})
    public boolean isQAndAbove() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public void resetStatus() {
        isPhoneDevice = null;
        deviceScreenSize = null;
    }
}
