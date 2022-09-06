package com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class FFSWhiteListJobService_MembersInjector implements MembersInjector<FFSWhiteListJobService> {
    private final Provider<WhiteListPolicyCoordinator> mWhiteListPolicyCoordinatorProvider;

    public FFSWhiteListJobService_MembersInjector(Provider<WhiteListPolicyCoordinator> provider) {
        this.mWhiteListPolicyCoordinatorProvider = provider;
    }

    public static MembersInjector<FFSWhiteListJobService> create(Provider<WhiteListPolicyCoordinator> provider) {
        return new FFSWhiteListJobService_MembersInjector(provider);
    }

    public static void injectMWhiteListPolicyCoordinator(FFSWhiteListJobService fFSWhiteListJobService, WhiteListPolicyCoordinator whiteListPolicyCoordinator) {
        fFSWhiteListJobService.mWhiteListPolicyCoordinator = whiteListPolicyCoordinator;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FFSWhiteListJobService fFSWhiteListJobService) {
        injectMWhiteListPolicyCoordinator(fFSWhiteListJobService, this.mWhiteListPolicyCoordinatorProvider.mo10268get());
    }
}
