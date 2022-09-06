package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ActiveCallFragment_MembersInjector implements MembersInjector<ActiveCallFragment> {
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<DriveModeSharedPreferencesUseCase> mDriveModeSharedPreferencesUseCaseProvider;
    private final Provider<BaseCallingPresenter> mPresenterProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;

    public ActiveCallFragment_MembersInjector(Provider<CallTimerManager> provider, Provider<SipClientState> provider2, Provider<CallHistoryHelper> provider3, Provider<CallManager> provider4, Provider<CapabilitiesManager> provider5, Provider<TelecomBridge> provider6, Provider<TelecomCallAudioRouteObservable> provider7, Provider<BaseCallingPresenter> provider8, Provider<RealTimeTextEnablementAuthority> provider9, Provider<CommsIdentityManager> provider10, Provider<DriveModeSharedPreferencesUseCase> provider11) {
        this.callTimerManagerProvider = provider;
        this.sipClientStateProvider = provider2;
        this.callHistoryHelperProvider = provider3;
        this.callManagerProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.telecomBridgeProvider = provider6;
        this.telecomCallAudioRouteObservableProvider = provider7;
        this.mPresenterProvider = provider8;
        this.realTimeTextEnablementAuthorityProvider = provider9;
        this.commsIdentityManagerProvider = provider10;
        this.mDriveModeSharedPreferencesUseCaseProvider = provider11;
    }

    public static MembersInjector<ActiveCallFragment> create(Provider<CallTimerManager> provider, Provider<SipClientState> provider2, Provider<CallHistoryHelper> provider3, Provider<CallManager> provider4, Provider<CapabilitiesManager> provider5, Provider<TelecomBridge> provider6, Provider<TelecomCallAudioRouteObservable> provider7, Provider<BaseCallingPresenter> provider8, Provider<RealTimeTextEnablementAuthority> provider9, Provider<CommsIdentityManager> provider10, Provider<DriveModeSharedPreferencesUseCase> provider11) {
        return new ActiveCallFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectCallHistoryHelper(ActiveCallFragment activeCallFragment, CallHistoryHelper callHistoryHelper) {
        activeCallFragment.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallManager(ActiveCallFragment activeCallFragment, CallManager callManager) {
        activeCallFragment.callManager = callManager;
    }

    public static void injectCallTimerManager(ActiveCallFragment activeCallFragment, CallTimerManager callTimerManager) {
        activeCallFragment.callTimerManager = callTimerManager;
    }

    public static void injectCapabilitiesManager(ActiveCallFragment activeCallFragment, CapabilitiesManager capabilitiesManager) {
        activeCallFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(ActiveCallFragment activeCallFragment, CommsIdentityManager commsIdentityManager) {
        activeCallFragment.commsIdentityManager = commsIdentityManager;
    }

    public static void injectMDriveModeSharedPreferencesUseCase(ActiveCallFragment activeCallFragment, DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        activeCallFragment.mDriveModeSharedPreferencesUseCase = driveModeSharedPreferencesUseCase;
    }

    public static void injectMPresenter(ActiveCallFragment activeCallFragment, BaseCallingPresenter baseCallingPresenter) {
        activeCallFragment.mPresenter = baseCallingPresenter;
    }

    public static void injectRealTimeTextEnablementAuthority(ActiveCallFragment activeCallFragment, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        activeCallFragment.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    public static void injectSipClientState(ActiveCallFragment activeCallFragment, SipClientState sipClientState) {
        activeCallFragment.sipClientState = sipClientState;
    }

    public static void injectTelecomBridge(ActiveCallFragment activeCallFragment, TelecomBridge telecomBridge) {
        activeCallFragment.telecomBridge = telecomBridge;
    }

    public static void injectTelecomCallAudioRouteObservable(ActiveCallFragment activeCallFragment, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        activeCallFragment.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActiveCallFragment activeCallFragment) {
        activeCallFragment.callTimerManager = this.callTimerManagerProvider.mo10268get();
        activeCallFragment.sipClientState = this.sipClientStateProvider.mo10268get();
        activeCallFragment.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        activeCallFragment.callManager = this.callManagerProvider.mo10268get();
        activeCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        activeCallFragment.telecomBridge = this.telecomBridgeProvider.mo10268get();
        activeCallFragment.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
        activeCallFragment.mPresenter = this.mPresenterProvider.mo10268get();
        activeCallFragment.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
        activeCallFragment.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        activeCallFragment.mDriveModeSharedPreferencesUseCase = this.mDriveModeSharedPreferencesUseCaseProvider.mo10268get();
    }
}
