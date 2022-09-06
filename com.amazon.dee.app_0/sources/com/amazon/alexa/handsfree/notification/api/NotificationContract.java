package com.amazon.alexa.handsfree.notification.api;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class NotificationContract {
    private final ConfigurationProvider mConfigurationProvider;
    private final DeviceInformationProvider mDeviceInformationProvider;
    private final HandsFreeSetupStateProvider mHandsFreeSetupStateProvider;
    private final PFMProvider mPFMProvider;
    private final SettingsProvider mSettingsProvider;
    private final UVRSettingsProvider mUVRSettingsProvider;

    public NotificationContract(@NonNull ConfigurationProvider configurationProvider, @NonNull HandsFreeSetupStateProvider handsFreeSetupStateProvider, @NonNull UVRSettingsProvider uVRSettingsProvider, @NonNull SettingsProvider settingsProvider, @NonNull DeviceInformationProvider deviceInformationProvider, @NonNull PFMProvider pFMProvider) {
        this.mConfigurationProvider = configurationProvider;
        this.mHandsFreeSetupStateProvider = handsFreeSetupStateProvider;
        this.mUVRSettingsProvider = uVRSettingsProvider;
        this.mSettingsProvider = settingsProvider;
        this.mDeviceInformationProvider = deviceInformationProvider;
        this.mPFMProvider = pFMProvider;
    }

    public ConfigurationProvider getConfigurationProvider() {
        return this.mConfigurationProvider;
    }

    public DeviceInformationProvider getDeviceInformationProvider() {
        return this.mDeviceInformationProvider;
    }

    public HandsFreeSetupStateProvider getHandsFreeSetupStateProvider() {
        return this.mHandsFreeSetupStateProvider;
    }

    public PFMProvider getPFMProvider() {
        return this.mPFMProvider;
    }

    public SettingsProvider getSettingsProvider() {
        return this.mSettingsProvider;
    }

    public UVRSettingsProvider getUVRSettingsProvider() {
        return this.mUVRSettingsProvider;
    }
}
