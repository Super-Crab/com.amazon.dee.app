package com.amazon.deecomms.common.service;

import com.amazon.deecomms.api.CommsIdentityManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ProvisioningManager_MembersInjector implements MembersInjector<ProvisioningManager> {
    private final Provider<CommsIdentityManager> mCommsIdentityManagerProvider;

    public ProvisioningManager_MembersInjector(Provider<CommsIdentityManager> provider) {
        this.mCommsIdentityManagerProvider = provider;
    }

    public static MembersInjector<ProvisioningManager> create(Provider<CommsIdentityManager> provider) {
        return new ProvisioningManager_MembersInjector(provider);
    }

    public static void injectMCommsIdentityManager(ProvisioningManager provisioningManager, CommsIdentityManager commsIdentityManager) {
        provisioningManager.mCommsIdentityManager = commsIdentityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ProvisioningManager provisioningManager) {
        provisioningManager.mCommsIdentityManager = this.mCommsIdentityManagerProvider.mo10268get();
    }
}
