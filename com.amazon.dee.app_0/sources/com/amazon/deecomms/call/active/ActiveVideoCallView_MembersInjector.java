package com.amazon.deecomms.call.active;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ActiveVideoCallView_MembersInjector implements MembersInjector<ActiveVideoCallView> {
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommandProcessor> commandProcessorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;

    public ActiveVideoCallView_MembersInjector(Provider<CallTimerManager> provider, Provider<SipClientState> provider2, Provider<CallHistoryHelper> provider3, Provider<CommandProcessor> provider4, Provider<CapabilitiesManager> provider5, Provider<TelecomBridge> provider6, Provider<TelecomCallAudioRouteObservable> provider7, Provider<RealTimeTextEnablementAuthority> provider8, Provider<CommsIdentityManager> provider9, Provider<EnhancedProcessingStateObservable> provider10) {
        this.callTimerManagerProvider = provider;
        this.sipClientStateProvider = provider2;
        this.callHistoryHelperProvider = provider3;
        this.commandProcessorProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.telecomBridgeProvider = provider6;
        this.telecomCallAudioRouteObservableProvider = provider7;
        this.realTimeTextEnablementAuthorityProvider = provider8;
        this.commsIdentityManagerProvider = provider9;
        this.enhancedProcessingStateObservableProvider = provider10;
    }

    public static MembersInjector<ActiveVideoCallView> create(Provider<CallTimerManager> provider, Provider<SipClientState> provider2, Provider<CallHistoryHelper> provider3, Provider<CommandProcessor> provider4, Provider<CapabilitiesManager> provider5, Provider<TelecomBridge> provider6, Provider<TelecomCallAudioRouteObservable> provider7, Provider<RealTimeTextEnablementAuthority> provider8, Provider<CommsIdentityManager> provider9, Provider<EnhancedProcessingStateObservable> provider10) {
        return new ActiveVideoCallView_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static void injectCallHistoryHelper(ActiveVideoCallView activeVideoCallView, CallHistoryHelper callHistoryHelper) {
        activeVideoCallView.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallTimerManager(ActiveVideoCallView activeVideoCallView, CallTimerManager callTimerManager) {
        activeVideoCallView.callTimerManager = callTimerManager;
    }

    public static void injectCapabilitiesManager(ActiveVideoCallView activeVideoCallView, CapabilitiesManager capabilitiesManager) {
        activeVideoCallView.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommandProcessor(ActiveVideoCallView activeVideoCallView, CommandProcessor commandProcessor) {
        activeVideoCallView.commandProcessor = commandProcessor;
    }

    public static void injectCommsIdentityManager(ActiveVideoCallView activeVideoCallView, CommsIdentityManager commsIdentityManager) {
        activeVideoCallView.commsIdentityManager = commsIdentityManager;
    }

    public static void injectEnhancedProcessingStateObservable(ActiveVideoCallView activeVideoCallView, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        activeVideoCallView.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    public static void injectRealTimeTextEnablementAuthority(ActiveVideoCallView activeVideoCallView, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        activeVideoCallView.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    public static void injectSipClientState(ActiveVideoCallView activeVideoCallView, SipClientState sipClientState) {
        activeVideoCallView.sipClientState = sipClientState;
    }

    public static void injectTelecomBridge(ActiveVideoCallView activeVideoCallView, TelecomBridge telecomBridge) {
        activeVideoCallView.telecomBridge = telecomBridge;
    }

    public static void injectTelecomCallAudioRouteObservable(ActiveVideoCallView activeVideoCallView, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        activeVideoCallView.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActiveVideoCallView activeVideoCallView) {
        activeVideoCallView.callTimerManager = this.callTimerManagerProvider.mo10268get();
        activeVideoCallView.sipClientState = this.sipClientStateProvider.mo10268get();
        activeVideoCallView.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        activeVideoCallView.commandProcessor = this.commandProcessorProvider.mo10268get();
        activeVideoCallView.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        activeVideoCallView.telecomBridge = this.telecomBridgeProvider.mo10268get();
        activeVideoCallView.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
        activeVideoCallView.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
        activeVideoCallView.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        activeVideoCallView.enhancedProcessingStateObservable = this.enhancedProcessingStateObservableProvider.mo10268get();
    }
}
