package com.amazon.deecomms.calling.core;

import android.media.AudioManager;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallingFragmentFactory_MembersInjector implements MembersInjector<CallingFragmentFactory> {
    private final Provider<AlexaInterface> alexaInterfaceProvider;
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<AudioStateObservable> audioStateObservableProvider;
    private final Provider<CallActionsDispatcher> callActionsDispatcherProvider;
    private final Provider<CallDowngradedObservable> callDowngradedObservableProvider;
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallMediaControlFacade> callMediaControlFacadeProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<EffectsState> effectsStateProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<NameChangeObservable> nameChangeObservableProvider;
    private final Provider<PipVisibilityObservable> pipVisibilityObservableProvider;
    private final Provider<RealTimeTextEnablementAuthority> realTimeTextEnablementAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;
    private final Provider<TelecomCallAudioManager> telecomCallAudioManagerProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;

    public CallingFragmentFactory_MembersInjector(Provider<SipClientState> provider, Provider<CallActionsDispatcher> provider2, Provider<CallHistoryHelper> provider3, Provider<CallTimerManager> provider4, Provider<CallMediaControlFacade> provider5, Provider<AudioStateObservable> provider6, Provider<NameChangeObservable> provider7, Provider<AlexaInterface> provider8, Provider<CapabilitiesManager> provider9, Provider<CallManager> provider10, Provider<RealTimeTextEnablementAuthority> provider11, Provider<TelecomCallAudioRouteObservable> provider12, Provider<TelecomCallAudioManager> provider13, Provider<EnhancedProcessingStateObservable> provider14, Provider<TelecomBridge> provider15, Provider<AudioManager> provider16, Provider<EffectsState> provider17, Provider<CallDowngradedObservable> provider18, Provider<PipVisibilityObservable> provider19) {
        this.sipClientStateProvider = provider;
        this.callActionsDispatcherProvider = provider2;
        this.callHistoryHelperProvider = provider3;
        this.callTimerManagerProvider = provider4;
        this.callMediaControlFacadeProvider = provider5;
        this.audioStateObservableProvider = provider6;
        this.nameChangeObservableProvider = provider7;
        this.alexaInterfaceProvider = provider8;
        this.capabilitiesManagerProvider = provider9;
        this.callManagerProvider = provider10;
        this.realTimeTextEnablementAuthorityProvider = provider11;
        this.telecomCallAudioRouteObservableProvider = provider12;
        this.telecomCallAudioManagerProvider = provider13;
        this.enhancedProcessingStateObservableProvider = provider14;
        this.telecomBridgeProvider = provider15;
        this.audioManagerProvider = provider16;
        this.effectsStateProvider = provider17;
        this.callDowngradedObservableProvider = provider18;
        this.pipVisibilityObservableProvider = provider19;
    }

    public static MembersInjector<CallingFragmentFactory> create(Provider<SipClientState> provider, Provider<CallActionsDispatcher> provider2, Provider<CallHistoryHelper> provider3, Provider<CallTimerManager> provider4, Provider<CallMediaControlFacade> provider5, Provider<AudioStateObservable> provider6, Provider<NameChangeObservable> provider7, Provider<AlexaInterface> provider8, Provider<CapabilitiesManager> provider9, Provider<CallManager> provider10, Provider<RealTimeTextEnablementAuthority> provider11, Provider<TelecomCallAudioRouteObservable> provider12, Provider<TelecomCallAudioManager> provider13, Provider<EnhancedProcessingStateObservable> provider14, Provider<TelecomBridge> provider15, Provider<AudioManager> provider16, Provider<EffectsState> provider17, Provider<CallDowngradedObservable> provider18, Provider<PipVisibilityObservable> provider19) {
        return new CallingFragmentFactory_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
    }

    public static void injectAlexaInterface(CallingFragmentFactory callingFragmentFactory, AlexaInterface alexaInterface) {
        callingFragmentFactory.alexaInterface = alexaInterface;
    }

    public static void injectAudioManager(CallingFragmentFactory callingFragmentFactory, AudioManager audioManager) {
        callingFragmentFactory.audioManager = audioManager;
    }

    public static void injectAudioStateObservable(CallingFragmentFactory callingFragmentFactory, AudioStateObservable audioStateObservable) {
        callingFragmentFactory.audioStateObservable = audioStateObservable;
    }

    public static void injectCallActionsDispatcher(CallingFragmentFactory callingFragmentFactory, CallActionsDispatcher callActionsDispatcher) {
        callingFragmentFactory.callActionsDispatcher = callActionsDispatcher;
    }

    public static void injectCallDowngradedObservable(CallingFragmentFactory callingFragmentFactory, CallDowngradedObservable callDowngradedObservable) {
        callingFragmentFactory.callDowngradedObservable = callDowngradedObservable;
    }

    public static void injectCallHistoryHelper(CallingFragmentFactory callingFragmentFactory, CallHistoryHelper callHistoryHelper) {
        callingFragmentFactory.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallManager(CallingFragmentFactory callingFragmentFactory, CallManager callManager) {
        callingFragmentFactory.callManager = callManager;
    }

    public static void injectCallMediaControlFacade(CallingFragmentFactory callingFragmentFactory, CallMediaControlFacade callMediaControlFacade) {
        callingFragmentFactory.callMediaControlFacade = callMediaControlFacade;
    }

    public static void injectCallTimerManager(CallingFragmentFactory callingFragmentFactory, CallTimerManager callTimerManager) {
        callingFragmentFactory.callTimerManager = callTimerManager;
    }

    public static void injectCapabilitiesManager(CallingFragmentFactory callingFragmentFactory, CapabilitiesManager capabilitiesManager) {
        callingFragmentFactory.capabilitiesManager = capabilitiesManager;
    }

    public static void injectEffectsState(CallingFragmentFactory callingFragmentFactory, EffectsState effectsState) {
        callingFragmentFactory.effectsState = effectsState;
    }

    public static void injectEnhancedProcessingStateObservable(CallingFragmentFactory callingFragmentFactory, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        callingFragmentFactory.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    public static void injectNameChangeObservable(CallingFragmentFactory callingFragmentFactory, NameChangeObservable nameChangeObservable) {
        callingFragmentFactory.nameChangeObservable = nameChangeObservable;
    }

    public static void injectPipVisibilityObservable(CallingFragmentFactory callingFragmentFactory, PipVisibilityObservable pipVisibilityObservable) {
        callingFragmentFactory.pipVisibilityObservable = pipVisibilityObservable;
    }

    public static void injectRealTimeTextEnablementAuthority(CallingFragmentFactory callingFragmentFactory, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        callingFragmentFactory.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
    }

    public static void injectSipClientState(CallingFragmentFactory callingFragmentFactory, SipClientState sipClientState) {
        callingFragmentFactory.sipClientState = sipClientState;
    }

    public static void injectTelecomBridge(CallingFragmentFactory callingFragmentFactory, TelecomBridge telecomBridge) {
        callingFragmentFactory.telecomBridge = telecomBridge;
    }

    public static void injectTelecomCallAudioManager(CallingFragmentFactory callingFragmentFactory, TelecomCallAudioManager telecomCallAudioManager) {
        callingFragmentFactory.telecomCallAudioManager = telecomCallAudioManager;
    }

    public static void injectTelecomCallAudioRouteObservable(CallingFragmentFactory callingFragmentFactory, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        callingFragmentFactory.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CallingFragmentFactory callingFragmentFactory) {
        callingFragmentFactory.sipClientState = this.sipClientStateProvider.mo10268get();
        callingFragmentFactory.callActionsDispatcher = this.callActionsDispatcherProvider.mo10268get();
        callingFragmentFactory.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        callingFragmentFactory.callTimerManager = this.callTimerManagerProvider.mo10268get();
        callingFragmentFactory.callMediaControlFacade = this.callMediaControlFacadeProvider.mo10268get();
        callingFragmentFactory.audioStateObservable = this.audioStateObservableProvider.mo10268get();
        callingFragmentFactory.nameChangeObservable = this.nameChangeObservableProvider.mo10268get();
        callingFragmentFactory.alexaInterface = this.alexaInterfaceProvider.mo10268get();
        callingFragmentFactory.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        callingFragmentFactory.callManager = this.callManagerProvider.mo10268get();
        callingFragmentFactory.realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthorityProvider.mo10268get();
        callingFragmentFactory.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
        callingFragmentFactory.telecomCallAudioManager = this.telecomCallAudioManagerProvider.mo10268get();
        callingFragmentFactory.enhancedProcessingStateObservable = this.enhancedProcessingStateObservableProvider.mo10268get();
        callingFragmentFactory.telecomBridge = this.telecomBridgeProvider.mo10268get();
        callingFragmentFactory.audioManager = this.audioManagerProvider.mo10268get();
        callingFragmentFactory.effectsState = this.effectsStateProvider.mo10268get();
        callingFragmentFactory.callDowngradedObservable = this.callDowngradedObservableProvider.mo10268get();
        callingFragmentFactory.pipVisibilityObservable = this.pipVisibilityObservableProvider.mo10268get();
    }
}
