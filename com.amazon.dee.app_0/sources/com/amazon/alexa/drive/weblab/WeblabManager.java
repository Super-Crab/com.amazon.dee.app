package com.amazon.alexa.drive.weblab;

import android.util.Log;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class WeblabManager {
    public static final String ALEXA_AUTO_ANDROID_AUTOMODE_2_0_LAUNCH = "ALEXA_AUTO_ANDROID_AUTOMODE_2_0_LAUNCH";
    public static final String ALEXA_AUTO_ANDROID_SMART_HOME_LOCK = "ALEXA_AUTO_ANDROID_SMART_HOME_LOCK";
    public static final String ALEXA_AUTO_ANDROID_SMART_HOME_TRANSITION = "ALEXA_AUTO_ANDROID_SMART_HOME_TRANSITION";
    public static final String AUTO_MODE_ANDROID_HERO_FEATURES_WEBLAB = "ALEXA_AUTO_ANDROID_RANCHERO_ENABLED";
    public static final String DRIVE_MODE_ANDROID_COMMS_NATIVE_WEBLAB = "ALEXA_AUTO_ANDROID_NATIVE_COMMS_ENABLED";
    private static final String TAG = "WeblabManager";
    private FeatureServiceV2 mFeatureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);
    private boolean mIsCommsNativeWeblabEnabled = isWeblabEnabled("ALEXA_AUTO_ANDROID_NATIVE_COMMS_ENABLED");
    private boolean mIsHeroFeaturesWeblabEnabled = isWeblabEnabled("ALEXA_AUTO_ANDROID_RANCHERO_ENABLED");
    private boolean mIsSmartHomeWeblabEnabled = isWeblabEnabled("ALEXA_AUTO_ANDROID_SMART_HOME_TRANSITION");
    private boolean mIsSmartHomeLockWeblabEnabled = isWeblabEnabled("ALEXA_AUTO_ANDROID_SMART_HOME_LOCK");
    private boolean mIsAutoMode20WeblabEnabled = isWeblabEnabled("ALEXA_AUTO_ANDROID_AUTOMODE_2_0_LAUNCH");

    private boolean isWeblabEnabled(String str) {
        FeatureServiceV2 featureServiceV2 = this.mFeatureServiceV2;
        if (featureServiceV2 != null) {
            boolean hasAccess = featureServiceV2.hasAccess(str, false);
            String str2 = TAG;
            Log.i(str2, "Weblab " + str + " enabled is: " + hasAccess);
            return hasAccess;
        }
        return false;
    }

    public boolean isAutoMode_2_0_WeblabEnabled() {
        return this.mIsAutoMode20WeblabEnabled;
    }

    public boolean isCommsNativeWeblabEnabled() {
        return this.mIsCommsNativeWeblabEnabled;
    }

    public boolean isHeroFeaturesWeblabEnabled() {
        return this.mIsHeroFeaturesWeblabEnabled;
    }

    public boolean isSmartHomeLockWeblabEnabled() {
        return this.mIsSmartHomeLockWeblabEnabled;
    }

    public boolean isSmartHomeWeblabEnabled() {
        return this.mIsSmartHomeWeblabEnabled;
    }
}
