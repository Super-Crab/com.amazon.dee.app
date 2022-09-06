package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class OutgoingCallFragment_MembersInjector implements MembersInjector<OutgoingCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<DriveModeSharedPreferencesUseCase> mDriveModeSharedPreferencesUseCaseProvider;
    private final Provider<BaseCallingPresenter> mPresenterProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public OutgoingCallFragment_MembersInjector(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2, Provider<BaseCallingPresenter> provider3, Provider<DriveModeSharedPreferencesUseCase> provider4) {
        this.sipClientStateProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.mPresenterProvider = provider3;
        this.mDriveModeSharedPreferencesUseCaseProvider = provider4;
    }

    public static MembersInjector<OutgoingCallFragment> create(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2, Provider<BaseCallingPresenter> provider3, Provider<DriveModeSharedPreferencesUseCase> provider4) {
        return new OutgoingCallFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectCapabilitiesManager(OutgoingCallFragment outgoingCallFragment, CapabilitiesManager capabilitiesManager) {
        outgoingCallFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectMDriveModeSharedPreferencesUseCase(OutgoingCallFragment outgoingCallFragment, DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        outgoingCallFragment.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    public static void injectMPresenter(OutgoingCallFragment outgoingCallFragment, BaseCallingPresenter baseCallingPresenter) {
        outgoingCallFragment.mPresenter = baseCallingPresenter;
    }

    public static void injectSipClientState(OutgoingCallFragment outgoingCallFragment, SipClientState sipClientState) {
        outgoingCallFragment.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OutgoingCallFragment outgoingCallFragment) {
        outgoingCallFragment.sipClientState = this.sipClientStateProvider.mo10268get();
        outgoingCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        outgoingCallFragment.mPresenter = this.mPresenterProvider.mo10268get();
        outgoingCallFragment.mDriveModeSharedPreferencesUseCase = this.mDriveModeSharedPreferencesUseCaseProvider.mo10268get();
    }
}
