package com.amazon.comms.config.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.util.SystemProperty;
/* loaded from: classes11.dex */
public class DeviceInfo {
    private static final String DEVICE_TYPE_PROPERTY_NAME = "ro.product.config.type";
    private static final String MAP_DEVICE_TYPE_KEY = "MAPDeviceType";
    public static final String PRODUCT_MANUFACTURER_AMAZON = "Amazon";
    private static String deviceType;
    private static final CommsLogger log = CommsLogger.getLogger(DeviceInfo.class);
    public static final String MANUFACTURER = Build.MANUFACTURER;

    public static String getDeviceType(Context context, @NonNull String str) {
        if (deviceType == null) {
            deviceType = SystemProperty.getSystemProperty(DEVICE_TYPE_PROPERTY_NAME, "");
            if (!MANUFACTURER.equalsIgnoreCase("Amazon")) {
                try {
                    String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(MAP_DEVICE_TYPE_KEY, str);
                    if (string.equals("A2J0R2SD7G9LPA")) {
                        deviceType = VegaDeviceTypeProviderUtil.getDeviceType();
                    } else {
                        deviceType = string;
                    }
                    CommsLogger commsLogger = log;
                    commsLogger.d("deviceType: " + deviceType);
                } catch (PackageManager.NameNotFoundException e) {
                    CommsLogger commsLogger2 = log;
                    commsLogger2.e("Failed to get ApplicationInfo, NameNotFound: " + e);
                }
            }
        }
        return deviceType;
    }

    public static boolean isAmazonDevice() {
        return MANUFACTURER.equalsIgnoreCase("Amazon");
    }
}
