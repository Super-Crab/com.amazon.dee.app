package com.amazon.rtcsc.service.deviceconfiguration;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.base.Strings;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class DeviceInfo {
    private static final String DEVICE_TYPE_PROPERTY_NAME = "ro.product.config.type";
    private static final String MAP_DEVICE_TYPE_KEY = "MAPDeviceType";
    public static final String PRODUCT_MANUFACTURER_AMAZON = "Amazon";
    private static String deviceType;
    private static RtcscLogger mLog = RtcscLogger.getLogger(DeviceInfo.class);
    public static final String MANUFACTURER = Build.MANUFACTURER;

    public static String getDeviceType(Context context, @NonNull String str) {
        if (str != null) {
            if (deviceType == null) {
                deviceType = getSystemProperty(DEVICE_TYPE_PROPERTY_NAME, "");
                if (!MANUFACTURER.equalsIgnoreCase("Amazon")) {
                    try {
                        deviceType = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(MAP_DEVICE_TYPE_KEY, str);
                    } catch (PackageManager.NameNotFoundException e) {
                        RtcscLogger rtcscLogger = mLog;
                        rtcscLogger.e("Failed to get ApplicationInfo, NameNotFound: " + e);
                    }
                }
            }
            RtcscLogger rtcscLogger2 = mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceType is: ");
            outline107.append(deviceType);
            rtcscLogger2.d(outline107.toString());
            return deviceType;
        }
        throw new IllegalArgumentException("defaultDeviceType is null");
    }

    public static String getSystemProperty(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod(MetricsConstants.Method.CACHE_GET, String.class).invoke(null, str);
        } catch (Exception e) {
            mLog.e("unable to read system property: Exception ", e);
            str3 = null;
        }
        return Strings.isNullOrEmpty(str3) ? str2 : str3;
    }

    public static boolean isAmazonDevice() {
        return MANUFACTURER.equalsIgnoreCase("Amazon");
    }
}
