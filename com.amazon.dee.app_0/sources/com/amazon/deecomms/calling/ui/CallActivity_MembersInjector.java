package com.amazon.deecomms.calling.ui;

import android.content.Context;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallActivity_MembersInjector implements MembersInjector<CallActivity> {
    private final Provider<ActivitiesManager> activitiesManagerProvider;
    private final Provider<AlexaInterface> alexaInterfaceProvider;
    private final Provider<AudioStateObservable> audioStateObservableProvider;
    private final Provider<CallActionsDispatcher> callActionsDispatcherProvider;
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallMediaControlFacade> callMediaControlFacadeProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommandProcessor> commandProcessorProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;
    private final Provider<EffectsState> effectsStateProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<AlexaAudioPlayer> mAlexaAudioPlayerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<NameChangeObservable> nameChangeObservableProvider;
    private final Provider<PipVisibilityObservable> pipVisibilityObservableProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;
    private final Provider<TelecomCallAudioManager> telecomCallAudioManagerProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;

    public CallActivity_MembersInjector(Provider<Context> provider, Provider<DeviceCallingService> provider2, Provider<CallManager> provider3, Provider<CallHistoryHelper> provider4, Provider<CallTimerManager> provider5, Provider<ActivitiesManager> provider6, Provider<SipClientState> provider7, Provider<CommandProcessor> provider8, Provider<CommsNotificationManager> provider9, Provider<CapabilitiesManager> provider10, Provider<TelecomCallAudioRouteObservable> provider11, Provider<AlexaAudioPlayer> provider12, Provider<CallActionsDispatcher> provider13, Provider<CallMediaControlFacade> provider14, Provider<AudioStateObservable> provider15, Provider<AlexaInterface> provider16, Provider<RealTimeTextEnablementAuthority> provider17, Provider<NameChangeObservable> provider18, Provider<TelecomCallAudioManager> provider19, Provider<TelecomBridge> provider20, Provider<EnhancedProcessingStateObservable> provider21, Provider<PipVisibilityObservable> provider22, Provider<EffectsState> provider23) {
        this.mContextProvider = provider;
        this.deviceCallingServiceProvider = provider2;
        this.callManagerProvider = provider3;
        this.callHistoryHelperProvider = provider4;
        this.callTimerManagerProvider = provider5;
        this.activitiesManagerProvider = provider6;
        this.sipClientStateProvider = provider7;
        this.commandProcessorProvider = provider8;
        this.commsNotificationManagerProvider = provider9;
        this.capabilitiesManagerProvider = provider10;
        this.telecomCallAudioRouteObservableProvider = provider11;
        this.mAlexaAudioPlayerProvider = provider12;
        this.callActionsDispatcherProvider = provider13;
        this.callMediaControlFacadeProvider = provider14;
        this.audioStateObservableProvider = provider15;
        this.alexaInterfaceProvider = provider16;
        this.realTimeTextEnablementAuthorityProvider = provider17;
        this.nameChangeObservableProvider = provider18;
        this.telecomCallAudioManagerProvider = provider19;
        this.telecomBridgeProvider = provider20;
        this.enhancedProcessingStateObservableProvider = provider21;
        this.pipVisibilityObservableProvider = provider22;
        this.effectsStateProvider = provider23;
    }

    public static MembersInjector<CallActivity> create(Provider<Context> provider, Provider<DeviceCallingService> provider2, Provider<CallManager> provider3, Provider<CallHistoryHelper> provider4, Provider<CallTimerManager> provider5, Provider<ActivitiesManager> provider6, Provider<SipClientState> provider7, Provider<CommandProcessor> provider8, Provider<CommsNotificationManager> provider9, Provider<CapabilitiesManager> provider10, Provider<TelecomCallAudioRouteObservable> provider11, Provider<AlexaAudioPlayer> provider12, Provider<CallActionsDispatcher> provider13, Provider<CallMediaControlFacade> provider14, Provider<AudioStateObservable> provider15, Provider<AlexaInterface> provider16, Provider<RealTimeTextEnablementAuthority> provider17, Provider<NameChangeObservable> provider18, Provider<TelecomCallAudioManager> provider19, Provider<TelecomBridge> provider20, Provider<EnhancedProcessingStateObservable> provider21, Provider<PipVisibilityObservable> provider22, Provider<EffectsState> provider23) {
        return new CallActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23);
    }

    public static void injectActivitiesManager(CallActivity callActivity, ActivitiesManager activitiesManager) {
        callActivity.activitiesManager = activitiesManager;
    }

    public static void injectAlexaInterface(CallActivity callActivity, AlexaInterface alexaInterface) {
        callActivity.alexaInterface = alexaInterface;
    }

    public static void injectAudioStateObservable(CallActivity callActivity, AudioStateObservable audioStateObservable) {
        callActivity.audioStateObservable = audioStateObservable;
    }

    public static void injectCallActionsDispatcher(CallActivity callActivity, CallActionsDispatcher callActionsDispatcher) {
        callActivity.callActionsDispatcher = callActionsDispatcher;
    }

    public static void injectCallHistoryHelper(CallActivity callActivity, CallHistoryHelper callHistoryHelper) {
        callActivity.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallManager(CallActivity callActivity, CallManager callManager) {
        callActivity.callManager = callManager;
    }

    public static void injectCallMediaControlFacade(CallActivity callActivity, CallMediaControlFacade callMediaControlFacade) {
        callActivity.callMediaControlFacade = callMediaControlFacade;
    }

    public static void injectCallTimerManager(CallActivity callActivity, CallTimerManager callTimerManager) {
        callActivity.callTimerManager = callTimerManager;
    }

    public static void injectCapabilitiesManager(CallActivity callActivity, CapabilitiesManager capabilitiesManager) {
        callActivity.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommandProcessor(CallActivity callActivity, CommandProcessor commandProcessor) {
        callActivity.commandProcessor = commandProcessor;
    }

    public static void injectCommsNotificationManager(CallActivity callActivity, CommsNotificationManager commsNotificationManager) {
        callActivity.commsNotificationManager = commsNotificationManager;
    }

    public static void injectDeviceCallingService(CallActivity callActivity, DeviceCallingService deviceCallingService) {
        callActivity.deviceCallingService = deviceCallingService;
    }

    public static void injectEffectsState(CallActivity callActivity, EffectsState effectsState) {
        callActivity.effectsState = effectsState;
    }

    public static void injectEnhancedProcessingStateObservable(CallActivity callActivity, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        callActivity.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    public static void injectMAlexaAudioPlayer(CallActivity callActivity, AlexaAudioPlayer alexaAudioPlayer) {
        callActivity.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectMContext(CallActivity callActivity, Context context) {
        callActivity.mContext = context;
    }

    public static void injectNameChangeObservable(CallActivity callActivity, NameChangeObservable nameChangeObservable) {
        callActivity.nameChangeObservable = nameChangeObservable;
    }

    public static void injectPipVisibilityObservable(CallActivity callActivity, PipVisibilityObservable pipVisibilityObservable) {
        callActivity.pipVisibilityObservable = pipVisibilityObservable;
    }

    public static void injectRealTimeTextEnablementAuthority(CallActivity callActivity, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        callActivity.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    public static void injectSipClientState(CallActivity callActivity, SipClientState sipClientState) {
        callActivity.sipClientState = sipClientState;
    }

    public static void injectTelecomBridge(CallActivity callActivity, TelecomBridge telecomBridge) {
        callActivity.telecomBridge = telecomBridge;
    }

    public static void injectTelecomCallAudioManager(CallActivity callActivity, TelecomCallAudioManager telecomCallAudioManager) {
        callActivity.telecomCallAudioManager = telecomCallAudioManager;
    }

    public static void injectTelecomCallAudioRouteObservable(CallActivity callActivity, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        callActivity.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CallActivity callActivity) {
        callActivity.mContext = this.mContextProvider.mo10268get();
        callActivity.deviceCallingService = this.deviceCallingServiceProvider.mo10268get();
        callActivity.callManager = this.callManagerProvider.mo10268get();
        callActivity.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        callActivity.callTimerManager = this.callTimerManagerProvider.mo10268get();
        callActivity.activitiesManager = this.activitiesManagerProvider.mo10268get();
        callActivity.sipClientState = this.sipClientStateProvider.mo10268get();
        callActivity.commandProcessor = this.commandProcessorProvider.mo10268get();
        callActivity.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        callActivity.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        callActivity.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
        callActivity.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        callActivity.callActionsDispatcher = this.callActionsDispatcherProvider.mo10268get();
        callActivity.callMediaControlFacade = this.callMediaControlFacadeProvider.mo10268get();
        callActivity.audioStateObservable = this.audioStateObservableProvider.mo10268get();
        callActivity.alexaInterface = this.alexaInterfaceProvider.mo10268get();
        callActivity.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
        callActivity.nameChangeObservable = this.nameChangeObservableProvider.mo10268get();
        callActivity.telecomCallAudioManager = this.telecomCallAudioManagerProvider.mo10268get();
        callActivity.telecomBridge = this.telecomBridgeProvider.mo10268get();
        callActivity.enhancedProcessingStateObservable = this.enhancedProcessingStateObservableProvider.mo10268get();
        callActivity.pipVisibilityObservable = this.pipVisibilityObservableProvider.mo10268get();
        callActivity.effectsState = this.effectsStateProvider.mo10268get();
    }
}
