package com.amazon.deecomms.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.comms.util.Size;
import com.amazon.deecomms.common.Constants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.Hashing;
/* loaded from: classes12.dex */
public class DeviceInfo {
    private static Double deviceScreenSize;
    private static String deviceTypePlusProps;
    private static Boolean isPhoneDevice;
    private static final CommsLogger mCommsLogger = CommsLogger.getLogger(Constants.LOG_TAG, DeviceInfo.class);

    private static double calculateScreenSize(@NonNull Context context) {
        DisplayMetrics displayMetrics;
        double d = getDisplayMetrics(context).densityDpi;
        double d2 = displayMetrics.widthPixels / d;
        double d3 = displayMetrics.heightPixels / d;
        if (Double.isNaN(d2) || Double.isNaN(d3) || Double.isInfinite(d2) || Double.isInfinite(d3)) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        double sqrt = Math.sqrt(Math.pow(d3, 2.0d) + Math.pow(d2, 2.0d));
        mCommsLogger.d("Device Screen size is: " + sqrt);
        return sqrt;
    }

    @SuppressLint({"HardwareIds"})
    private String getDSN(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        GeneratedOutlineSupport1.outline159("DSN : ", string, mCommsLogger);
        return string == null ? "" : string;
    }

    public static double getDeviceScreenSize(@NonNull Context context) {
        if (deviceScreenSize == null) {
            deviceScreenSize = Double.valueOf(calculateScreenSize(context));
        }
        return deviceScreenSize.doubleValue();
    }

    @NonNull
    public static String getDeviceTypeWithDeviceProperties(@NonNull Context context) {
        if (deviceTypePlusProps == null) {
            deviceTypePlusProps = Joiner.on("/").useForNull("").join("A2TF17PFR55MTB", Build.MANUFACTURER, Build.MODEL, Double.valueOf(getDeviceScreenSize(context)));
        }
        return deviceTypePlusProps;
    }

    private static DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static Size getScreenDimensions(@NonNull Context context, @NonNull Point point) {
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        int i = point.x;
        int i2 = point.y;
        CommsLogger commsLogger = mCommsLogger;
        commsLogger.i("Screen size:" + i + ReactProperties.HereMapMarker.X + i2);
        return new Size(i, i2);
    }

    public static boolean isPhone(@NonNull Context context) {
        if (isPhoneDevice == null) {
            isPhoneDevice = Boolean.valueOf(6.5d > getDeviceScreenSize(context));
        }
        CommsLogger commsLogger = mCommsLogger;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Is device a phone: ");
        outline1.append(isPhoneDevice);
        commsLogger.d(outline1.toString());
        return isPhoneDevice.booleanValue();
    }

    public String getUniqueDeviceId(Context context) {
        String hashCode = Hashing.md5().newHasher().mo8226putString((CharSequence) getDSN(context), Charsets.UTF_8).hash().toString();
        GeneratedOutlineSupport1.outline159("hash of DSN : ", hashCode, mCommsLogger);
        return hashCode;
    }
}
