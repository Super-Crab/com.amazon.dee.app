package com.amazon.blueshift.bluefront.android.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;
/* loaded from: classes11.dex */
public final class DeviceUtils {
    private static final String TAG = "com.amazon.blueshift.bluefront.android.common.DeviceUtils";

    private DeviceUtils() {
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "unknown";
        }
    }

    public static String getCarrierName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return (telephonyManager == null || Strings.isNullOrEmpty(telephonyManager.getNetworkOperatorName())) ? "NONE" : telephonyManager.getNetworkOperatorName();
    }

    public static Location getLocation(Context context) {
        return null;
    }
}
