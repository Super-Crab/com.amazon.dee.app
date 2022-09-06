package com.amazon.deecomms.ndt.ui;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DeviceBottomSheet_MembersInjector implements MembersInjector<DeviceBottomSheet> {
    private final Provider<CapabilitiesManager> mCapabilitiesManagerProvider;

    public DeviceBottomSheet_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.mCapabilitiesManagerProvider = provider;
    }

    public static MembersInjector<DeviceBottomSheet> create(Provider<CapabilitiesManager> provider) {
        return new DeviceBottomSheet_MembersInjector(provider);
    }

    public static void injectMCapabilitiesManager(DeviceBottomSheet deviceBottomSheet, CapabilitiesManager capabilitiesManager) {
        deviceBottomSheet.mCapabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DeviceBottomSheet deviceBottomSheet) {
        deviceBottomSheet.mCapabilitiesManager = this.mCapabilitiesManagerProvider.mo10268get();
    }
}
