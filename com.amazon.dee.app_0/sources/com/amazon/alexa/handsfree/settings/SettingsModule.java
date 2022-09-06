package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.metrics.utils.AnonymousDeviceIdProvider;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.event.EventSubscriber;
import java.util.Objects;
/* loaded from: classes8.dex */
public enum SettingsModule {
    INSTANCE;
    
    @VisibleForTesting
    static final HandsFreeComponent SETTINGS_COMPONENT = HandsFreeComponent.HANDS_FREE_SETTINGS;
    private static final String TAG = "SettingsModule";
    private AnonymousDeviceIdProvider mAnonymousDeviceIdProvider;
    private Context mContext;
    private DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private HandsFreeUserIdentity mHandsFreeUserIdentity;
    private boolean mIsInitialized;
    private boolean mIsUserSignedIn;
    private SettingsContract mSettingsContract;
    private SettingsSetupFlowContract mSettingsSetupFlowContract;

    public AnonymousDeviceIdProvider getAnonymousDeviceIdProvider() {
        return this.mAnonymousDeviceIdProvider;
    }

    public SettingsContract getContract() {
        return this.mSettingsContract;
    }

    public DeviceTypeInformationProvider getDeviceTypeInformationProvider() {
        return this.mDeviceTypeInformationProvider;
    }

    public SettingsSetupFlowContract getSetupFlowContract() {
        return this.mSettingsSetupFlowContract;
    }

    public synchronized void initialize(@NonNull Context context, @NonNull SettingsSetupFlowContract settingsSetupFlowContract, @NonNull SettingsContract settingsContract, @NonNull AnonymousDeviceIdProvider anonymousDeviceIdProvider) {
        initialize(context, settingsSetupFlowContract, settingsContract, anonymousDeviceIdProvider, DeviceTypeInformationProvider.getInstance(context), new EventSubscriber(context));
    }

    public boolean isComponentsEnabled() {
        HandsFreeUserIdentity handsFreeUserIdentity;
        if (this.mDeviceTypeInformationProvider == null) {
            this.mDeviceTypeInformationProvider = DeviceTypeInformationProvider.getInstance(this.mContext);
        }
        DeviceInformation supportedDeviceInformation = this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(this.mContext);
        if (supportedDeviceInformation == null || !supportedDeviceInformation.isDeviceLaunched()) {
            return this.mIsUserSignedIn && (handsFreeUserIdentity = this.mHandsFreeUserIdentity) != null && handsFreeUserIdentity.hasComponent(SETTINGS_COMPONENT);
        }
        HandsFreeUserIdentity handsFreeUserIdentity2 = this.mHandsFreeUserIdentity;
        return handsFreeUserIdentity2 != null && handsFreeUserIdentity2.hasComponent(SETTINGS_COMPONENT);
    }

    public synchronized boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void setComponentsEnabled(boolean z, @NonNull HandsFreeUserIdentity handsFreeUserIdentity) {
        this.mIsUserSignedIn = z;
        this.mHandsFreeUserIdentity = handsFreeUserIdentity;
    }

    @VisibleForTesting
    void initialize(@NonNull Context context, @NonNull SettingsSetupFlowContract settingsSetupFlowContract, @NonNull SettingsContract settingsContract, @NonNull AnonymousDeviceIdProvider anonymousDeviceIdProvider, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull EventSubscriber eventSubscriber) {
        if (!this.mIsInitialized) {
            this.mSettingsSetupFlowContract = (SettingsSetupFlowContract) Objects.requireNonNull(settingsSetupFlowContract, "SettingsSetupFlowContract expected");
            this.mSettingsContract = (SettingsContract) Objects.requireNonNull(settingsContract, "SettingsContract expected");
            this.mAnonymousDeviceIdProvider = anonymousDeviceIdProvider;
            this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
            eventSubscriber.subscribe();
            this.mContext = context;
        }
        this.mIsInitialized = true;
    }
}
