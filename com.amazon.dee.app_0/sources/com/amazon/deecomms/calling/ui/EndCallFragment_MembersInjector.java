package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EndCallFragment_MembersInjector implements MembersInjector<EndCallFragment> {
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CapabilitiesManager> mCapabilitiesManagerProvider;
    private final Provider<DriveModeSharedPreferencesUseCase> mDriveModeSharedPreferencesUseCaseProvider;

    public EndCallFragment_MembersInjector(Provider<CapabilitiesManager> provider, Provider<DriveModeSharedPreferencesUseCase> provider2, Provider<CallContext> provider3, Provider<CallInitiationAuthority> provider4) {
        this.mCapabilitiesManagerProvider = provider;
        this.mDriveModeSharedPreferencesUseCaseProvider = provider2;
        this.callContextProvider = provider3;
        this.callInitiationAuthorityProvider = provider4;
    }

    public static MembersInjector<EndCallFragment> create(Provider<CapabilitiesManager> provider, Provider<DriveModeSharedPreferencesUseCase> provider2, Provider<CallContext> provider3, Provider<CallInitiationAuthority> provider4) {
        return new EndCallFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectCallContext(EndCallFragment endCallFragment, CallContext callContext) {
        endCallFragment.callContext = callContext;
    }

    public static void injectCallInitiationAuthority(EndCallFragment endCallFragment, CallInitiationAuthority callInitiationAuthority) {
        endCallFragment.callInitiationAuthority = callInitiationAuthority;
    }

    public static void injectMCapabilitiesManager(EndCallFragment endCallFragment, CapabilitiesManager capabilitiesManager) {
        endCallFragment.mCapabilitiesManager = capabilitiesManager;
    }

    public static void injectMDriveModeSharedPreferencesUseCase(EndCallFragment endCallFragment, DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        endCallFragment.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EndCallFragment endCallFragment) {
        endCallFragment.mCapabilitiesManager = this.mCapabilitiesManagerProvider.mo10268get();
        endCallFragment.mDriveModeSharedPreferencesUseCase = this.mDriveModeSharedPreferencesUseCaseProvider.mo10268get();
        endCallFragment.callContext = this.callContextProvider.mo10268get();
        endCallFragment.callInitiationAuthority = this.callInitiationAuthorityProvider.mo10268get();
    }
}
