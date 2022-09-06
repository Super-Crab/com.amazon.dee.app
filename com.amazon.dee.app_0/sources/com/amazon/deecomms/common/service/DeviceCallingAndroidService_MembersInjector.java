package com.amazon.deecomms.common.service;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DeviceCallingAndroidService_MembersInjector implements MembersInjector<DeviceCallingAndroidService> {
    private final Provider<AlarmManager> alarmManagerProvider;
    private final Provider<ArcusConfig> arcusConfigProvider;
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CallTimerManager> callTimerManagerProvider;
    private final Provider<CallUIHandler> callUIHandlerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerAndMCapabilitiesManagerProvider;
    private final Provider<CommsActivityMonitor> commsActivityMonitorProvider;
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<AlexaAudioPlayer> mAlexaAudioPlayerProvider;
    private final Provider<AudioManager> mAudioManagerProvider;
    private final Provider<CommandProcessor> mCommandProcessorProvider;
    private final Provider<CommsNotificationManager> mCommsNotificationManagerProvider;
    private final Provider<ConnectivityManager> mConnectivityManagerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<DeviceCallingServiceParams> mDeviceCallingServiceParamsProvider;
    private final Provider<EventTracerFactory> mEventTracerFactoryProvider;
    private final Provider<ModeSwitchHelper> mModeSwitchHelperProvider;
    private final Provider<TelephonyManager> mTelephonyManagerProvider;
    private final Provider<NotificationManager> notificationManagerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<PowerManager> powerManagerProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerLazyProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public DeviceCallingAndroidService_MembersInjector(Provider<PowerManager> provider, Provider<NotificationManager> provider2, Provider<Context> provider3, Provider<ConnectivityManager> provider4, Provider<TelephonyManager> provider5, Provider<AudioManager> provider6, Provider<AlarmManager> provider7, Provider<CallManager> provider8, Provider<CallTimerManager> provider9, Provider<CommandProcessor> provider10, Provider<CommsConnectivityMonitor> provider11, Provider<CommsNotificationManager> provider12, Provider<DeviceCallingService> provider13, Provider<SipClientState> provider14, Provider<PushNotificationManager> provider15, Provider<EventTracerFactory> provider16, Provider<DeviceCallingServiceParams> provider17, Provider<ProvisioningManager> provider18, Provider<CommsIdentityManager> provider19, Provider<CapabilitiesManager> provider20, Provider<CommsActivityMonitor> provider21, Provider<CallUIHandler> provider22, Provider<ModeSwitchHelper> provider23, Provider<ArcusConfig> provider24, Provider<DeviceUtils> provider25, Provider<PCCContextProvider> provider26, Provider<AlexaAudioPlayer> provider27, Provider<CallHistoryHelper> provider28, Provider<CallContext> provider29, Provider<CallInitiationAuthority> provider30) {
        this.powerManagerProvider = provider;
        this.notificationManagerProvider = provider2;
        this.mContextProvider = provider3;
        this.mConnectivityManagerProvider = provider4;
        this.mTelephonyManagerProvider = provider5;
        this.mAudioManagerProvider = provider6;
        this.alarmManagerProvider = provider7;
        this.callManagerProvider = provider8;
        this.callTimerManagerProvider = provider9;
        this.mCommandProcessorProvider = provider10;
        this.commsConnectivityMonitorProvider = provider11;
        this.mCommsNotificationManagerProvider = provider12;
        this.deviceCallingServiceProvider = provider13;
        this.sipClientStateProvider = provider14;
        this.pushNotificationManagerLazyProvider = provider15;
        this.mEventTracerFactoryProvider = provider16;
        this.mDeviceCallingServiceParamsProvider = provider17;
        this.provisioningManagerProvider = provider18;
        this.commsIdentityManagerProvider = provider19;
        this.capabilitiesManagerAndMCapabilitiesManagerProvider = provider20;
        this.commsActivityMonitorProvider = provider21;
        this.callUIHandlerProvider = provider22;
        this.mModeSwitchHelperProvider = provider23;
        this.arcusConfigProvider = provider24;
        this.deviceUtilsProvider = provider25;
        this.pccContextProvider = provider26;
        this.mAlexaAudioPlayerProvider = provider27;
        this.callHistoryHelperProvider = provider28;
        this.callContextProvider = provider29;
        this.callInitiationAuthorityProvider = provider30;
    }

    public static MembersInjector<DeviceCallingAndroidService> create(Provider<PowerManager> provider, Provider<NotificationManager> provider2, Provider<Context> provider3, Provider<ConnectivityManager> provider4, Provider<TelephonyManager> provider5, Provider<AudioManager> provider6, Provider<AlarmManager> provider7, Provider<CallManager> provider8, Provider<CallTimerManager> provider9, Provider<CommandProcessor> provider10, Provider<CommsConnectivityMonitor> provider11, Provider<CommsNotificationManager> provider12, Provider<DeviceCallingService> provider13, Provider<SipClientState> provider14, Provider<PushNotificationManager> provider15, Provider<EventTracerFactory> provider16, Provider<DeviceCallingServiceParams> provider17, Provider<ProvisioningManager> provider18, Provider<CommsIdentityManager> provider19, Provider<CapabilitiesManager> provider20, Provider<CommsActivityMonitor> provider21, Provider<CallUIHandler> provider22, Provider<ModeSwitchHelper> provider23, Provider<ArcusConfig> provider24, Provider<DeviceUtils> provider25, Provider<PCCContextProvider> provider26, Provider<AlexaAudioPlayer> provider27, Provider<CallHistoryHelper> provider28, Provider<CallContext> provider29, Provider<CallInitiationAuthority> provider30) {
        return new DeviceCallingAndroidService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30);
    }

    public static void injectAlarmManager(DeviceCallingAndroidService deviceCallingAndroidService, AlarmManager alarmManager) {
        deviceCallingAndroidService.alarmManager = alarmManager;
    }

    public static void injectArcusConfig(DeviceCallingAndroidService deviceCallingAndroidService, ArcusConfig arcusConfig) {
        deviceCallingAndroidService.arcusConfig = arcusConfig;
    }

    public static void injectCallContext(DeviceCallingAndroidService deviceCallingAndroidService, CallContext callContext) {
        deviceCallingAndroidService.callContext = callContext;
    }

    public static void injectCallHistoryHelper(DeviceCallingAndroidService deviceCallingAndroidService, CallHistoryHelper callHistoryHelper) {
        deviceCallingAndroidService.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCallInitiationAuthority(DeviceCallingAndroidService deviceCallingAndroidService, CallInitiationAuthority callInitiationAuthority) {
        deviceCallingAndroidService.callInitiationAuthority = callInitiationAuthority;
    }

    public static void injectCallManager(DeviceCallingAndroidService deviceCallingAndroidService, CallManager callManager) {
        deviceCallingAndroidService.callManager = callManager;
    }

    public static void injectCallTimerManager(DeviceCallingAndroidService deviceCallingAndroidService, CallTimerManager callTimerManager) {
        deviceCallingAndroidService.callTimerManager = callTimerManager;
    }

    public static void injectCallUIHandler(DeviceCallingAndroidService deviceCallingAndroidService, CallUIHandler callUIHandler) {
        deviceCallingAndroidService.callUIHandler = callUIHandler;
    }

    public static void injectCapabilitiesManager(DeviceCallingAndroidService deviceCallingAndroidService, CapabilitiesManager capabilitiesManager) {
        deviceCallingAndroidService.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsActivityMonitor(DeviceCallingAndroidService deviceCallingAndroidService, CommsActivityMonitor commsActivityMonitor) {
        deviceCallingAndroidService.commsActivityMonitor = commsActivityMonitor;
    }

    public static void injectCommsConnectivityMonitor(DeviceCallingAndroidService deviceCallingAndroidService, CommsConnectivityMonitor commsConnectivityMonitor) {
        deviceCallingAndroidService.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectCommsIdentityManager(DeviceCallingAndroidService deviceCallingAndroidService, CommsIdentityManager commsIdentityManager) {
        deviceCallingAndroidService.commsIdentityManager = commsIdentityManager;
    }

    public static void injectDeviceCallingService(DeviceCallingAndroidService deviceCallingAndroidService, DeviceCallingService deviceCallingService) {
        deviceCallingAndroidService.deviceCallingService = deviceCallingService;
    }

    public static void injectDeviceUtils(DeviceCallingAndroidService deviceCallingAndroidService, DeviceUtils deviceUtils) {
        deviceCallingAndroidService.deviceUtils = deviceUtils;
    }

    public static void injectMAlexaAudioPlayer(DeviceCallingAndroidService deviceCallingAndroidService, AlexaAudioPlayer alexaAudioPlayer) {
        deviceCallingAndroidService.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectMAudioManager(DeviceCallingAndroidService deviceCallingAndroidService, AudioManager audioManager) {
        deviceCallingAndroidService.mAudioManager = audioManager;
    }

    public static void injectMCapabilitiesManager(DeviceCallingAndroidService deviceCallingAndroidService, CapabilitiesManager capabilitiesManager) {
        deviceCallingAndroidService.mCapabilitiesManager = capabilitiesManager;
    }

    public static void injectMCommandProcessor(DeviceCallingAndroidService deviceCallingAndroidService, CommandProcessor commandProcessor) {
        deviceCallingAndroidService.mCommandProcessor = commandProcessor;
    }

    public static void injectMCommsNotificationManager(DeviceCallingAndroidService deviceCallingAndroidService, CommsNotificationManager commsNotificationManager) {
        deviceCallingAndroidService.mCommsNotificationManager = commsNotificationManager;
    }

    public static void injectMConnectivityManager(DeviceCallingAndroidService deviceCallingAndroidService, ConnectivityManager connectivityManager) {
        deviceCallingAndroidService.mConnectivityManager = connectivityManager;
    }

    public static void injectMContext(DeviceCallingAndroidService deviceCallingAndroidService, Context context) {
        deviceCallingAndroidService.mContext = context;
    }

    public static void injectMDeviceCallingServiceParams(DeviceCallingAndroidService deviceCallingAndroidService, DeviceCallingServiceParams deviceCallingServiceParams) {
        deviceCallingAndroidService.mDeviceCallingServiceParams = deviceCallingServiceParams;
    }

    public static void injectMEventTracerFactory(DeviceCallingAndroidService deviceCallingAndroidService, EventTracerFactory eventTracerFactory) {
        deviceCallingAndroidService.mEventTracerFactory = eventTracerFactory;
    }

    public static void injectMModeSwitchHelper(DeviceCallingAndroidService deviceCallingAndroidService, ModeSwitchHelper modeSwitchHelper) {
        deviceCallingAndroidService.mModeSwitchHelper = modeSwitchHelper;
    }

    public static void injectMTelephonyManager(DeviceCallingAndroidService deviceCallingAndroidService, TelephonyManager telephonyManager) {
        deviceCallingAndroidService.mTelephonyManager = telephonyManager;
    }

    public static void injectNotificationManager(DeviceCallingAndroidService deviceCallingAndroidService, NotificationManager notificationManager) {
        deviceCallingAndroidService.notificationManager = notificationManager;
    }

    public static void injectPccContextProvider(DeviceCallingAndroidService deviceCallingAndroidService, PCCContextProvider pCCContextProvider) {
        deviceCallingAndroidService.pccContextProvider = pCCContextProvider;
    }

    public static void injectPowerManager(DeviceCallingAndroidService deviceCallingAndroidService, PowerManager powerManager) {
        deviceCallingAndroidService.powerManager = powerManager;
    }

    public static void injectProvisioningManager(DeviceCallingAndroidService deviceCallingAndroidService, ProvisioningManager provisioningManager) {
        deviceCallingAndroidService.provisioningManager = provisioningManager;
    }

    public static void injectPushNotificationManagerLazy(DeviceCallingAndroidService deviceCallingAndroidService, Lazy<PushNotificationManager> lazy) {
        deviceCallingAndroidService.pushNotificationManagerLazy = lazy;
    }

    public static void injectSipClientState(DeviceCallingAndroidService deviceCallingAndroidService, SipClientState sipClientState) {
        deviceCallingAndroidService.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DeviceCallingAndroidService deviceCallingAndroidService) {
        deviceCallingAndroidService.powerManager = this.powerManagerProvider.mo10268get();
        deviceCallingAndroidService.notificationManager = this.notificationManagerProvider.mo10268get();
        deviceCallingAndroidService.mContext = this.mContextProvider.mo10268get();
        deviceCallingAndroidService.mConnectivityManager = this.mConnectivityManagerProvider.mo10268get();
        deviceCallingAndroidService.mTelephonyManager = this.mTelephonyManagerProvider.mo10268get();
        deviceCallingAndroidService.mAudioManager = this.mAudioManagerProvider.mo10268get();
        deviceCallingAndroidService.alarmManager = this.alarmManagerProvider.mo10268get();
        deviceCallingAndroidService.callManager = this.callManagerProvider.mo10268get();
        deviceCallingAndroidService.callTimerManager = this.callTimerManagerProvider.mo10268get();
        deviceCallingAndroidService.mCommandProcessor = this.mCommandProcessorProvider.mo10268get();
        deviceCallingAndroidService.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
        deviceCallingAndroidService.mCommsNotificationManager = this.mCommsNotificationManagerProvider.mo10268get();
        deviceCallingAndroidService.deviceCallingService = this.deviceCallingServiceProvider.mo10268get();
        deviceCallingAndroidService.sipClientState = this.sipClientStateProvider.mo10268get();
        deviceCallingAndroidService.pushNotificationManagerLazy = DoubleCheck.lazy(this.pushNotificationManagerLazyProvider);
        deviceCallingAndroidService.mEventTracerFactory = this.mEventTracerFactoryProvider.mo10268get();
        deviceCallingAndroidService.mDeviceCallingServiceParams = this.mDeviceCallingServiceParamsProvider.mo10268get();
        deviceCallingAndroidService.provisioningManager = this.provisioningManagerProvider.mo10268get();
        deviceCallingAndroidService.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        deviceCallingAndroidService.mCapabilitiesManager = this.capabilitiesManagerAndMCapabilitiesManagerProvider.mo10268get();
        deviceCallingAndroidService.commsActivityMonitor = this.commsActivityMonitorProvider.mo10268get();
        deviceCallingAndroidService.callUIHandler = this.callUIHandlerProvider.mo10268get();
        deviceCallingAndroidService.mModeSwitchHelper = this.mModeSwitchHelperProvider.mo10268get();
        deviceCallingAndroidService.arcusConfig = this.arcusConfigProvider.mo10268get();
        deviceCallingAndroidService.capabilitiesManager = this.capabilitiesManagerAndMCapabilitiesManagerProvider.mo10268get();
        deviceCallingAndroidService.deviceUtils = this.deviceUtilsProvider.mo10268get();
        deviceCallingAndroidService.pccContextProvider = this.pccContextProvider.mo10268get();
        deviceCallingAndroidService.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        deviceCallingAndroidService.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
        deviceCallingAndroidService.callContext = this.callContextProvider.mo10268get();
        deviceCallingAndroidService.callInitiationAuthority = this.callInitiationAuthorityProvider.mo10268get();
    }
}
