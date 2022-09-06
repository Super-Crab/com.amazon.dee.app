package com.amazon.deecomms.calling.controller;

import android.content.Context;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DeviceCallingServiceEventListener_MembersInjector implements MembersInjector<DeviceCallingServiceEventListener> {
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallMetricsFactory> callMetricsFactoryProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CallingAPIMonitor> callingAPIMonitorProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;
    private final Provider<AlexaAudioPlayer> mAlexaAudioPlayerProvider;
    private final Provider<ArcusConfig> mArcusConfigProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<AlexaInterface> mCurrentAlexaInterfaceProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<SipClientState> previousSipClientStateProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;
    private final Provider<RingTonePlaybackAuthority> ringTonePlaybackAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public DeviceCallingServiceEventListener_MembersInjector(Provider<Context> provider, Provider<CommsConnectivityMonitor> provider2, Provider<CommsIdentityManager> provider3, Provider<CallHistoryHelper> provider4, Provider<CallMetricsFactory> provider5, Provider<AlexaInterface> provider6, Provider<AlexaAudioPlayer> provider7, Provider<SipClientState> provider8, Provider<CallingAPIMonitor> provider9, Provider<CallInitiationAuthority> provider10, Provider<CapabilitiesManager> provider11, Provider<CallManager> provider12, Provider<ArcusConfig> provider13, Provider<CallTimerManager> provider14, Provider<SipClientState> provider15, Provider<ProvisioningManager> provider16, Provider<CommsNotificationManager> provider17, Provider<PCCContextProvider> provider18, Provider<RingTonePlaybackAuthority> provider19, Provider<CallContext> provider20, Provider<DeviceCallingService> provider21) {
        this.mContextProvider = provider;
        this.commsConnectivityMonitorProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.callHistoryHelperProvider = provider4;
        this.callMetricsFactoryProvider = provider5;
        this.mCurrentAlexaInterfaceProvider = provider6;
        this.mAlexaAudioPlayerProvider = provider7;
        this.previousSipClientStateProvider = provider8;
        this.callingAPIMonitorProvider = provider9;
        this.callInitiationAuthorityProvider = provider10;
        this.capabilitiesManagerProvider = provider11;
        this.callManagerProvider = provider12;
        this.mArcusConfigProvider = provider13;
        this.callTimerManagerProvider = provider14;
        this.sipClientStateProvider = provider15;
        this.provisioningManagerProvider = provider16;
        this.commsNotificationManagerProvider = provider17;
        this.pccContextProvider = provider18;
        this.ringTonePlaybackAuthorityProvider = provider19;
        this.callContextProvider = provider20;
        this.deviceCallingServiceProvider = provider21;
    }

    public static MembersInjector<DeviceCallingServiceEventListener> create(Provider<Context> provider, Provider<CommsConnectivityMonitor> provider2, Provider<CommsIdentityManager> provider3, Provider<CallHistoryHelper> provider4, Provider<CallMetricsFactory> provider5, Provider<AlexaInterface> provider6, Provider<AlexaAudioPlayer> provider7, Provider<SipClientState> provider8, Provider<CallingAPIMonitor> provider9, Provider<CallInitiationAuthority> provider10, Provider<CapabilitiesManager> provider11, Provider<CallManager> provider12, Provider<ArcusConfig> provider13, Provider<CallTimerManager> provider14, Provider<SipClientState> provider15, Provider<ProvisioningManager> provider16, Provider<CommsNotificationManager> provider17, Provider<PCCContextProvider> provider18, Provider<RingTonePlaybackAuthority> provider19, Provider<CallContext> provider20, Provider<DeviceCallingService> provider21) {
        return new DeviceCallingServiceEventListener_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21);
    }

    public static void injectCallContext(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallContext callContext) {
        deviceCallingServiceEventListener.callContext = callContext;
    }

    public static void injectCallHistoryHelper(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallHistoryHelper callHistoryHelper) {
        deviceCallingServiceEventListener.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallInitiationAuthority(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallInitiationAuthority callInitiationAuthority) {
        deviceCallingServiceEventListener.callInitiationAuthority = callInitiationAuthority;
    }

    public static void injectCallManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallManager callManager) {
        deviceCallingServiceEventListener.callManager = callManager;
    }

    public static void injectCallMetricsFactory(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallMetricsFactory callMetricsFactory) {
        deviceCallingServiceEventListener.callMetricsFactory = callMetricsFactory;
    }

    public static void injectCallTimerManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallTimerManager callTimerManager) {
        deviceCallingServiceEventListener.callTimerManager = callTimerManager;
    }

    public static void injectCallingAPIMonitor(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CallingAPIMonitor callingAPIMonitor) {
        deviceCallingServiceEventListener.callingAPIMonitor = callingAPIMonitor;
    }

    public static void injectCapabilitiesManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CapabilitiesManager capabilitiesManager) {
        deviceCallingServiceEventListener.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsConnectivityMonitor(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CommsConnectivityMonitor commsConnectivityMonitor) {
        deviceCallingServiceEventListener.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectCommsIdentityManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CommsIdentityManager commsIdentityManager) {
        deviceCallingServiceEventListener.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsNotificationManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, CommsNotificationManager commsNotificationManager) {
        deviceCallingServiceEventListener.commsNotificationManager = commsNotificationManager;
    }

    public static void injectDeviceCallingService(DeviceCallingServiceEventListener deviceCallingServiceEventListener, DeviceCallingService deviceCallingService) {
        deviceCallingServiceEventListener.deviceCallingService = deviceCallingService;
    }

    public static void injectMAlexaAudioPlayer(DeviceCallingServiceEventListener deviceCallingServiceEventListener, AlexaAudioPlayer alexaAudioPlayer) {
        deviceCallingServiceEventListener.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectMArcusConfig(DeviceCallingServiceEventListener deviceCallingServiceEventListener, ArcusConfig arcusConfig) {
        deviceCallingServiceEventListener.mArcusConfig = arcusConfig;
    }

    public static void injectMContext(DeviceCallingServiceEventListener deviceCallingServiceEventListener, Context context) {
        deviceCallingServiceEventListener.mContext = context;
    }

    public static void injectMCurrentAlexaInterface(DeviceCallingServiceEventListener deviceCallingServiceEventListener, AlexaInterface alexaInterface) {
        deviceCallingServiceEventListener.mCurrentAlexaInterface = alexaInterface;
    }

    public static void injectPccContextProvider(DeviceCallingServiceEventListener deviceCallingServiceEventListener, PCCContextProvider pCCContextProvider) {
        deviceCallingServiceEventListener.pccContextProvider = pCCContextProvider;
    }

    public static void injectPreviousSipClientState(DeviceCallingServiceEventListener deviceCallingServiceEventListener, SipClientState sipClientState) {
        deviceCallingServiceEventListener.previousSipClientState = sipClientState;
    }

    public static void injectProvisioningManager(DeviceCallingServiceEventListener deviceCallingServiceEventListener, ProvisioningManager provisioningManager) {
        deviceCallingServiceEventListener.provisioningManager = provisioningManager;
    }

    public static void injectRingTonePlaybackAuthority(DeviceCallingServiceEventListener deviceCallingServiceEventListener, RingTonePlaybackAuthority ringTonePlaybackAuthority) {
        deviceCallingServiceEventListener.ringTonePlaybackAuthority = ringTonePlaybackAuthority;
    }

    public static void injectSipClientState(DeviceCallingServiceEventListener deviceCallingServiceEventListener, SipClientState sipClientState) {
        deviceCallingServiceEventListener.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DeviceCallingServiceEventListener deviceCallingServiceEventListener) {
        deviceCallingServiceEventListener.mContext = this.mContextProvider.mo10268get();
        deviceCallingServiceEventListener.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
        deviceCallingServiceEventListener.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        deviceCallingServiceEventListener.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        deviceCallingServiceEventListener.callMetricsFactory = this.callMetricsFactoryProvider.mo10268get();
        deviceCallingServiceEventListener.mCurrentAlexaInterface = this.mCurrentAlexaInterfaceProvider.mo10268get();
        deviceCallingServiceEventListener.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        deviceCallingServiceEventListener.previousSipClientState = this.previousSipClientStateProvider.mo10268get();
        deviceCallingServiceEventListener.callingAPIMonitor = this.callingAPIMonitorProvider.mo10268get();
        deviceCallingServiceEventListener.callInitiationAuthority = this.callInitiationAuthorityProvider.mo10268get();
        deviceCallingServiceEventListener.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        deviceCallingServiceEventListener.callManager = this.callManagerProvider.mo10268get();
        deviceCallingServiceEventListener.mArcusConfig = this.mArcusConfigProvider.mo10268get();
        deviceCallingServiceEventListener.callTimerManager = this.callTimerManagerProvider.mo10268get();
        deviceCallingServiceEventListener.sipClientState = this.sipClientStateProvider.mo10268get();
        deviceCallingServiceEventListener.provisioningManager = this.provisioningManagerProvider.mo10268get();
        deviceCallingServiceEventListener.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        deviceCallingServiceEventListener.pccContextProvider = this.pccContextProvider.mo10268get();
        deviceCallingServiceEventListener.ringTonePlaybackAuthority = this.ringTonePlaybackAuthorityProvider.mo10268get();
        deviceCallingServiceEventListener.callContext = this.callContextProvider.mo10268get();
        deviceCallingServiceEventListener.deviceCallingService = this.deviceCallingServiceProvider.mo10268get();
    }
}
