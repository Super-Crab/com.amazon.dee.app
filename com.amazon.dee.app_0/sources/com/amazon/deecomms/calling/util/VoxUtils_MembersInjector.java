package com.amazon.deecomms.calling.util;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoxUtils_MembersInjector implements MembersInjector<VoxUtils> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;

    public VoxUtils_MembersInjector(Provider<PCCContextProvider> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3) {
        this.pccContextProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
    }

    public static MembersInjector<VoxUtils> create(Provider<PCCContextProvider> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3) {
        return new VoxUtils_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCapabilitiesManager(VoxUtils voxUtils, CapabilitiesManager capabilitiesManager) {
        voxUtils.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(VoxUtils voxUtils, CommsIdentityManager commsIdentityManager) {
        voxUtils.commsIdentityManager = commsIdentityManager;
    }

    public static void injectPccContextProvider(VoxUtils voxUtils, PCCContextProvider pCCContextProvider) {
        voxUtils.pccContextProvider = pCCContextProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoxUtils voxUtils) {
        voxUtils.pccContextProvider = this.pccContextProvider.mo10268get();
        voxUtils.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        voxUtils.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
