package com.amazon.alexa.accessory.notificationpublisher.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
/* loaded from: classes.dex */
public final class FeatureAccessChecker {
    public static final String ALEXA_OTG_ASSET_DOWNLOAD_ANDROID_WEBLAB = "ALEXA_OTG_ASSET_DOWNLOAD_ANDROID";
    public static final String ALEXA_OTG_VIP_FILTER_ANDROID_WEBLAB = "ALEXA_OTG_VIP_FILTER_ANDROID";
    private static final String ALEXA_OTG_VIP_FILTER_MULTIDEVICE_USS_SETTINGS_ANDROID_WEBLAB = "ALEXA_OTG_VIP_FILTER_MULTIDEVICE_USS_SETTINGS_ANDROID";
    private static final String ALEXA_ZION_ANDROID_BLOCK_ACTIVE_PHONE_NOTIFICATION = "ALEXA_ZION_ANDROID_BLOCK_ACTIVE_PHONE_NOTIFICATION";
    private static final String ALEXA_ZION_ANDROID_FETCH_TEMPLATES_DEBUG_WEBLAB = "ALEXA_ZION_ANDROID_FETCH_TEMPLATES_DEBUG";
    private static final String ALEXA_ZION_ANDROID_REPLY = "ALEXA_ZION_ANDROID_REPLY";
    public static final String ANDROID_SETUP_WEBLAB = "ELEMENTS_1P_SETUP_ZION_ANDROID";
    private static final String GROUP_NOTIFICATION_USS_SETTINGS_WEBLAB = "ZION_GROUP_NOTIFICATIONS_USS_SETTINGS_ANDROID";
    private static final String REMOVE_WAKEUP_CALL_WEBLAB = "ALEXA_ZION_ANDROID_REMOVE_WAKEUP_CALL";
    private static final String TAG = "FeatureAccessChecker";
    private static final String VOICE_CAPABILITY = "ALEXA_OTG_NOTIFICATIONS_EXTERNAL_CAPABILITY_ANDROID";
    private static final String ZION_ANDROID_REMOVE_REPEATING_CONTACT_SAYS = "ZION_ANDROID_REMOVE_REPEATING_CONTACT_SAYS";

    private FeatureAccessChecker() {
    }

    @VisibleForTesting
    public static boolean checkFeatureAccess(@NonNull String str) {
        return checkFeatureAccess(DependencyProvider.getFeatureServiceV2(), str);
    }

    public static boolean hasAssetDownloadAccess() {
        return checkFeatureAccess(ALEXA_OTG_ASSET_DOWNLOAD_ANDROID_WEBLAB);
    }

    public static boolean hasBlockActivePhoneNotificationFeatureAccess() {
        return checkFeatureAccess(ALEXA_ZION_ANDROID_BLOCK_ACTIVE_PHONE_NOTIFICATION);
    }

    public static boolean hasFetchCloudTemplatesFromTestFolderAccess() {
        return checkFeatureAccess("ALEXA_ZION_ANDROID_FETCH_TEMPLATES_DEBUG");
    }

    public static boolean hasFocusFilterFeatureAccess() {
        return checkFeatureAccess("ELEMENTS_1P_SETUP_ZION_ANDROID");
    }

    public static boolean hasGroupNotificationUssSettingsFeatureAccess() {
        return checkFeatureAccess(GROUP_NOTIFICATION_USS_SETTINGS_WEBLAB);
    }

    public static boolean hasOtgVipFilterAccess() {
        return checkFeatureAccess("ALEXA_OTG_VIP_FILTER_ANDROID");
    }

    public static boolean hasOtgVipFilterUssSettingsAccess() {
        return checkFeatureAccess("ALEXA_OTG_VIP_FILTER_MULTIDEVICE_USS_SETTINGS_ANDROID");
    }

    public static boolean hasRemoveRepeatingContactSaysAccess() {
        return checkFeatureAccess("ZION_ANDROID_REMOVE_REPEATING_CONTACT_SAYS");
    }

    public static boolean hasRemoveWakeupCallFeatureAccess() {
        return checkFeatureAccess(REMOVE_WAKEUP_CALL_WEBLAB);
    }

    public static boolean hasVipFilterReplyFeatureAccess() {
        return checkFeatureAccess(ALEXA_ZION_ANDROID_REPLY);
    }

    public static boolean hasVoiceFeatureAccess() {
        return checkFeatureAccess(VOICE_CAPABILITY);
    }

    @VisibleForTesting
    public static boolean checkFeatureAccess(@Nullable FeatureServiceV2 featureServiceV2, @NonNull String str) {
        return featureServiceV2 != null && featureServiceV2.hasAccess(str, false);
    }
}
