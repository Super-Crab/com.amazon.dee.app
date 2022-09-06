package com.amazon.whisperjoin.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
/* loaded from: classes13.dex */
public class FireOSUtil {
    static final String AMAZON_BUILD_CONFIGURATION_TABLET = "tablet";
    static final String AMAZON_DSHS_ZIGBEE_PACKAGE_NAME = "com.amazon.device.smarthome.adapters.zigbee";
    static final String AMAZON_FEATURE_FIREOS = "com.amazon.software.fireos";
    static final String AMAZON_FEATURE_FIRE_TV = "amazon.hardware.fire_tv";
    static final String BUILD_CONFIGURATION = "ro.build.configuration";
    private final PackageManager mPackageManager;

    public FireOSUtil(Context context) {
        this.mPackageManager = context.getPackageManager();
    }

    public boolean isFireTvDevice() {
        return this.mPackageManager.hasSystemFeature(AMAZON_FEATURE_FIRE_TV);
    }

    public boolean isPoweredDevice() {
        if (this.mPackageManager.hasSystemFeature(AMAZON_FEATURE_FIREOS)) {
            String property = System.getProperty(BUILD_CONFIGURATION);
            return property == null || !AMAZON_BUILD_CONFIGURATION_TABLET.equals(property);
        }
        return false;
    }

    public boolean isZigbeeDevice() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.mPackageManager.getApplicationInfo(AMAZON_DSHS_ZIGBEE_PACKAGE_NAME, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return applicationInfo != null;
    }
}
