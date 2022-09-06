package com.amazon.device.crashmanager.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class AmazonPackageLookup {
    private static final Map<String, String> PACKAGE_NAME_TO_FRIENDLY_NAME;
    public static final String SYSTEM_PACKAGE_PREFIX = "/system/";
    public static final String THIRD_PARTY_NAME = "ThirdPartyApp";
    private final String mMyPackageName;
    private final PackageManager mPackageManager;
    private static final DPLogger log = new DPLogger("AmazonPackageLookup");
    public static final Set<String> SYSTEM_PACKAGES = new HashSet();

    static {
        SYSTEM_PACKAGES.add("system_server");
        SYSTEM_PACKAGES.add("zygote");
        SYSTEM_PACKAGES.add("ath6kl");
        PACKAGE_NAME_TO_FRIENDLY_NAME = new HashMap();
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.adc", "ADC");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.venezia", "AmazonAppStore");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.avod", "AmazonVideo");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.mp3", "AmazonMp3");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.imdb.mobile", "IMDB");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle", "Kindle");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.dcp", "DCP");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle.otter", "Launcher");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.lab126.otter", "QuickSettings");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.gardemanger", "AppManager");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle.otter.oobe", AlexaMetricsConstants.MetricsComponents.OOBE);
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.otter.tutorial", "Tutorial");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle.otter.settings", MetricsConstants.UserInteractionMetrics.SETTINGS_PREFIX);
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.csapp", "CSApp");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.windowshop", "Shop");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.cloud9", "Silk");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.cloud9[AdobeFlash]", "SilkFlash");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.cloud9[WebKit]", "SilkWebkit");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.alphonso.pulse", "Pulse");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.email", "Email");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle.facebook", "Facebook");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("system_server", "SystemServer");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("/system/bin/mediaserver", "MediaServer");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.calendar", "com.android.calendar");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.contacts", "com.android.contacts");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.email", "com.android.email");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.exchange", "com.android.exchange");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.providers.contacts", "com.android.providers.contacts");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.android.systemui", "com.android.systemui");
        PACKAGE_NAME_TO_FRIENDLY_NAME.put("com.amazon.kindle.unifiedSearch", "com.amazon.kindle.unifiedSearch");
    }

    public AmazonPackageLookup(Context context) {
        if (context != null) {
            this.mPackageManager = context.getPackageManager();
            this.mMyPackageName = context.getPackageName();
            return;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private String canonicalizePackageName(String str) {
        if (str == null || !str.contains(":")) {
            return str;
        }
        String[] split = str.split(":");
        return split.length == 2 ? split[0] : str;
    }

    private int getUidForPackage(String str) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = this.mPackageManager.getApplicationInfo(str, 0);
        if (applicationInfo != null) {
            return applicationInfo.uid;
        }
        throw new PackageManager.NameNotFoundException();
    }

    public String generateFriendlyName(String str) {
        return isTrustedPackage(str) ? str : THIRD_PARTY_NAME;
    }

    public boolean isAmazonPackage(String str) {
        if (str == null) {
            return false;
        }
        return this.mPackageManager.checkSignatures(this.mMyPackageName, canonicalizePackageName(str)) == 0;
    }

    public boolean isSystemPackage(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(SYSTEM_PACKAGE_PREFIX) || SYSTEM_PACKAGES.contains(str)) {
            return true;
        }
        try {
            return this.mPackageManager.checkSignatures(1000, getUidForPackage(canonicalizePackageName(str))) == 0;
        } catch (PackageManager.NameNotFoundException unused) {
            log.warn("isSystemPackage", GeneratedOutlineSupport1.outline72("Name not found for package: ", str), new Object[0]);
            return false;
        }
    }

    public boolean isTrustedPackage(String str) {
        return isAmazonPackage(str) || isSystemPackage(str);
    }

    public String packageNameToAmazonFriendlyName(String str) {
        if (str == null) {
            return null;
        }
        String str2 = PACKAGE_NAME_TO_FRIENDLY_NAME.get(str);
        if (str2 != null) {
            return str2;
        }
        String generateFriendlyName = generateFriendlyName(str);
        PACKAGE_NAME_TO_FRIENDLY_NAME.put(str, generateFriendlyName);
        return generateFriendlyName;
    }
}
