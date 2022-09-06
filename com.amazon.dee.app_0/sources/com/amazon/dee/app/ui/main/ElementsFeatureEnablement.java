package com.amazon.dee.app.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.dee.app.util.Utils;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes12.dex */
public class ElementsFeatureEnablement {
    public static final String TAG = Utils.safeTag(ElementsFeatureEnablement.class.getSimpleName());
    public final boolean isA2SStoreFrontEnabled;
    public final boolean isAddDeviceEnabled;
    public final boolean isChannelsDevicesEnabled;
    public final boolean isChannelsHomeEnabled;
    public final boolean isCommsAllFeaturesEnabled;
    public final boolean isDeviceSettingsEnabled;
    public final boolean isElementsEnabled;
    public final boolean isListsEnabled;
    public final boolean isNowPlayingEnabled;
    public final boolean isRemindersAlarmsTimersEnabled;
    public final boolean isSettingsCalendarEnabled;
    public final boolean isSettingsEmailAndCalendarEnabled;
    public final boolean isSettingsEnabled;
    public final boolean isSettingsFlashBriefingEnabled;
    public final boolean isSettingsKidsPurchasingEnabled;
    public final boolean isSettingsListsEnabled;
    public final boolean isSettingsLocationsEnabled;
    public final boolean isSettingsMusicMediaEnabled;
    public final boolean isSettingsNotificationsEnabled;
    public final boolean isSettingsSportsUpdateEnabled;
    public final boolean isSettingsTerseEnabled;
    public final boolean isSettingsTrafficEnabled;
    public final boolean isSettingsVoicePurchasingEnabled;
    public final boolean isSettingsVoicecastEnabled;
    public final boolean lemur;
    public final boolean smartHome;

    public ElementsFeatureEnablement(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26) {
        this.isElementsEnabled = z;
        boolean z27 = true;
        this.smartHome = z && z2;
        this.lemur = this.smartHome && z3;
        this.isNowPlayingEnabled = z4;
        this.isDeviceSettingsEnabled = z5;
        this.isSettingsEnabled = z5 && z6;
        this.isSettingsNotificationsEnabled = z6 && z7;
        this.isSettingsFlashBriefingEnabled = z6 && z8;
        this.isSettingsVoicecastEnabled = z6 && z9;
        this.isSettingsTerseEnabled = z6 && z10;
        this.isSettingsListsEnabled = z6 && z11;
        this.isSettingsVoicePurchasingEnabled = z6 && z12;
        this.isSettingsKidsPurchasingEnabled = z6 && z12 && z13;
        this.isSettingsTrafficEnabled = z6 && z14;
        this.isSettingsSportsUpdateEnabled = z6 && z15;
        this.isSettingsCalendarEnabled = z6 && z16;
        this.isSettingsEmailAndCalendarEnabled = z6 && z17;
        this.isSettingsMusicMediaEnabled = z6 && z18;
        this.isSettingsLocationsEnabled = (!z6 || !z19) ? false : z27;
        this.isRemindersAlarmsTimersEnabled = z20;
        this.isA2SStoreFrontEnabled = z21;
        this.isListsEnabled = z22;
        this.isChannelsHomeEnabled = z23;
        this.isChannelsDevicesEnabled = z24;
        this.isCommsAllFeaturesEnabled = z25;
        this.isAddDeviceEnabled = z26;
    }

    @Nullable
    public static ElementsFeatureEnablement fromIdentityService(@NonNull IdentityService identityService) {
        Preconditions.checkNotNull(identityService);
        UserIdentity user = identityService.getUser(TAG);
        if (user == null) {
            return null;
        }
        Set unmodifiableSet = Collections.unmodifiableSet(user.getFeatures());
        return new ElementsFeatureEnablement(unmodifiableSet.contains("ELEMENTS"), unmodifiableSet.contains("ELEMENTS_SMARTHOME"), unmodifiableSet.contains("ELEMENTS_LEMUR"), unmodifiableSet.contains("ELEMENTS_NOW_PLAYING_ANDROID"), unmodifiableSet.contains("ELEMENTS_DEVICE_SETTINGS_ANDROID") || unmodifiableSet.contains(CommsDynamicFeature.COMMS_ALL_FEATURES.name()), unmodifiableSet.contains("ELEMENTS_SETTINGS_LANDING_PAGE_ANDROID"), unmodifiableSet.contains("ELEMENTS_SETTINGS_NOTIFICATIONS"), unmodifiableSet.contains("ELEMENTS_SETTINGS_FLASH_BRIEFING"), unmodifiableSet.contains("ELEMENTS_SETTINGS_VOICECAST"), unmodifiableSet.contains("ELEMENTS_SETTINGS_TERSE"), unmodifiableSet.contains("ELEMENTS_SETTINGS_LISTS"), unmodifiableSet.contains("ELEMENTS_SETTINGS_VOICE_PURCHASING"), unmodifiableSet.contains("MONETIZATION_KIDS_PURCHASE_SETTINGS"), unmodifiableSet.contains("ELEMENTS_SETTINGS_TRAFFIC"), unmodifiableSet.contains("ELEMENTS_SETTINGS_SPORTS_UPDATE"), unmodifiableSet.contains("ELEMENTS_SETTINGS_CALENDAR"), unmodifiableSet.contains("ALEXA_HHO_CONNECT_FEATURE"), unmodifiableSet.contains("ELEMENTS_SETTINGS_MUSICMEDIA"), unmodifiableSet.contains("ELEMENTS_LOCATIONS"), unmodifiableSet.contains("ELEMENTS_RAT_ANDROID"), unmodifiableSet.contains("ELEMENTS_CHANNELS_SKILLS_STORE"), unmodifiableSet.contains("ELEMENTS_LISTS_ANDROID"), unmodifiableSet.contains("CH_HOME_ANDROID"), unmodifiableSet.contains("ELEMENTS_DEVICES_CHANNEL_ANDROID"), unmodifiableSet.contains(CommsDynamicFeature.COMMS_ALL_FEATURES.name()), unmodifiableSet.contains("ALEXA_MOBILE_APP_GENERIC_FEATURE_4"));
    }
}
