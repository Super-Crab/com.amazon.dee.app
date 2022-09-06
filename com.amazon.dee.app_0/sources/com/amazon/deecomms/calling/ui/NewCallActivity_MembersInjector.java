package com.amazon.deecomms.calling.ui;

import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class NewCallActivity_MembersInjector implements MembersInjector<NewCallActivity> {
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallDowngradedObservable> callDowngradedObservableProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallMediaControlFacade> callMediaControlFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommandProcessor> commandProcessorProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;
    private final Provider<AlexaAudioPlayer> mAlexaAudioPlayerProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;

    public NewCallActivity_MembersInjector(Provider<CallContext> provider, Provider<AlexaAudioPlayer> provider2, Provider<CallManager> provider3, Provider<CallMediaControlFacade> provider4, Provider<CapabilitiesManager> provider5, Provider<CommandProcessor> provider6, Provider<CommsNotificationManager> provider7, Provider<DeviceCallingService> provider8, Provider<RealTimeTextEnablementAuthority> provider9, Provider<SipClientState> provider10, Provider<TelecomCallAudioRouteObservable> provider11, Provider<CallDowngradedObservable> provider12) {
        this.callContextProvider = provider;
        this.mAlexaAudioPlayerProvider = provider2;
        this.callManagerProvider = provider3;
        this.callMediaControlFacadeProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.commandProcessorProvider = provider6;
        this.commsNotificationManagerProvider = provider7;
        this.deviceCallingServiceProvider = provider8;
        this.realTimeTextEnablementAuthorityProvider = provider9;
        this.sipClientStateProvider = provider10;
        this.telecomCallAudioRouteObservableProvider = provider11;
        this.callDowngradedObservableProvider = provider12;
    }

    public static MembersInjector<NewCallActivity> create(Provider<CallContext> provider, Provider<AlexaAudioPlayer> provider2, Provider<CallManager> provider3, Provider<CallMediaControlFacade> provider4, Provider<CapabilitiesManager> provider5, Provider<CommandProcessor> provider6, Provider<CommsNotificationManager> provider7, Provider<DeviceCallingService> provider8, Provider<RealTimeTextEnablementAuthority> provider9, Provider<SipClientState> provider10, Provider<TelecomCallAudioRouteObservable> provider11, Provider<CallDowngradedObservable> provider12) {
        return new NewCallActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static void injectCallContext(NewCallActivity newCallActivity, CallContext callContext) {
        newCallActivity.callContext = callContext;
    }

    public static void injectCallDowngradedObservable(NewCallActivity newCallActivity, CallDowngradedObservable callDowngradedObservable) {
        newCallActivity.callDowngradedObservable = callDowngradedObservable;
    }

    public static void injectCallManager(NewCallActivity newCallActivity, CallManager callManager) {
        newCallActivity.callManager = callManager;
    }

    public static void injectCallMediaControlFacade(NewCallActivity newCallActivity, CallMediaControlFacade callMediaControlFacade) {
        newCallActivity.callMediaControlFacade = callMediaControlFacade;
    }

    public static void injectCapabilitiesManager(NewCallActivity newCallActivity, CapabilitiesManager capabilitiesManager) {
        newCallActivity.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommandProcessor(NewCallActivity newCallActivity, CommandProcessor commandProcessor) {
        newCallActivity.commandProcessor = commandProcessor;
    }

    public static void injectCommsNotificationManager(NewCallActivity newCallActivity, CommsNotificationManager commsNotificationManager) {
        newCallActivity.commsNotificationManager = commsNotificationManager;
    }

    public static void injectDeviceCallingService(NewCallActivity newCallActivity, DeviceCallingService deviceCallingService) {
        newCallActivity.deviceCallingService = deviceCallingService;
    }

    public static void injectMAlexaAudioPlayer(NewCallActivity newCallActivity, AlexaAudioPlayer alexaAudioPlayer) {
        newCallActivity.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectRealTimeTextEnablementAuthority(NewCallActivity newCallActivity, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        newCallActivity.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    public static void injectSipClientState(NewCallActivity newCallActivity, SipClientState sipClientState) {
        newCallActivity.sipClientState = sipClientState;
    }

    public static void injectTelecomCallAudioRouteObservable(NewCallActivity newCallActivity, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        newCallActivity.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NewCallActivity newCallActivity) {
        newCallActivity.callContext = this.callContextProvider.mo10268get();
        newCallActivity.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        newCallActivity.callManager = this.callManagerProvider.mo10268get();
        newCallActivity.callMediaControlFacade = this.callMediaControlFacadeProvider.mo10268get();
        newCallActivity.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        newCallActivity.commandProcessor = this.commandProcessorProvider.mo10268get();
        newCallActivity.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        newCallActivity.deviceCallingService = this.deviceCallingServiceProvider.mo10268get();
        newCallActivity.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
        newCallActivity.sipClientState = this.sipClientStateProvider.mo10268get();
        newCallActivity.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
        newCallActivity.callDowngradedObservable = this.callDowngradedObservableProvider.mo10268get();
    }
}
