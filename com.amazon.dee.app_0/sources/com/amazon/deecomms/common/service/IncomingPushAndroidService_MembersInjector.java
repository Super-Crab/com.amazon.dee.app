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
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IncomingPushAndroidService_MembersInjector implements MembersInjector<IncomingPushAndroidService> {
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
    private final Provider<RingTonePlaybackAuthority> ringTonePlaybackAuthorityProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public IncomingPushAndroidService_MembersInjector(Provider<PowerManager> provider, Provider<NotificationManager> provider2, Provider<Context> provider3, Provider<ConnectivityManager> provider4, Provider<TelephonyManager> provider5, Provider<AudioManager> provider6, Provider<AlarmManager> provider7, Provider<CallManager> provider8, Provider<CallTimerManager> provider9, Provider<CommandProcessor> provider10, Provider<CommsConnectivityMonitor> provider11, Provider<CommsNotificationManager> provider12, Provider<DeviceCallingService> provider13, Provider<SipClientState> provider14, Provider<PushNotificationManager> provider15, Provider<EventTracerFactory> provider16, Provider<DeviceCallingServiceParams> provider17, Provider<ProvisioningManager> provider18, Provider<CommsIdentityManager> provider19, Provider<CapabilitiesManager> provider20, Provider<CommsActivityMonitor> provider21, Provider<CallUIHandler> provider22, Provider<ModeSwitchHelper> provider23, Provider<ArcusConfig> provider24, Provider<DeviceUtils> provider25, Provider<PCCContextProvider> provider26, Provider<AlexaAudioPlayer> provider27, Provider<CallHistoryHelper> provider28, Provider<CallContext> provider29, Provider<CallInitiationAuthority> provider30, Provider<RingTonePlaybackAuthority> provider31) {
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
        this.ringTonePlaybackAuthorityProvider = provider31;
    }

    public static MembersInjector<IncomingPushAndroidService> create(Provider<PowerManager> provider, Provider<NotificationManager> provider2, Provider<Context> provider3, Provider<ConnectivityManager> provider4, Provider<TelephonyManager> provider5, Provider<AudioManager> provider6, Provider<AlarmManager> provider7, Provider<CallManager> provider8, Provider<CallTimerManager> provider9, Provider<CommandProcessor> provider10, Provider<CommsConnectivityMonitor> provider11, Provider<CommsNotificationManager> provider12, Provider<DeviceCallingService> provider13, Provider<SipClientState> provider14, Provider<PushNotificationManager> provider15, Provider<EventTracerFactory> provider16, Provider<DeviceCallingServiceParams> provider17, Provider<ProvisioningManager> provider18, Provider<CommsIdentityManager> provider19, Provider<CapabilitiesManager> provider20, Provider<CommsActivityMonitor> provider21, Provider<CallUIHandler> provider22, Provider<ModeSwitchHelper> provider23, Provider<ArcusConfig> provider24, Provider<DeviceUtils> provider25, Provider<PCCContextProvider> provider26, Provider<AlexaAudioPlayer> provider27, Provider<CallHistoryHelper> provider28, Provider<CallContext> provider29, Provider<CallInitiationAuthority> provider30, Provider<RingTonePlaybackAuthority> provider31) {
        return new IncomingPushAndroidService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31);
    }

    public static void injectMAlexaAudioPlayer(IncomingPushAndroidService incomingPushAndroidService, AlexaAudioPlayer alexaAudioPlayer) {
        incomingPushAndroidService.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectRingTonePlaybackAuthority(IncomingPushAndroidService incomingPushAndroidService, RingTonePlaybackAuthority ringTonePlaybackAuthority) {
        incomingPushAndroidService.ringTonePlaybackAuthority = ringTonePlaybackAuthority;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(IncomingPushAndroidService incomingPushAndroidService) {
        DeviceCallingAndroidService_MembersInjector.injectPowerManager(incomingPushAndroidService, this.powerManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectNotificationManager(incomingPushAndroidService, this.notificationManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMContext(incomingPushAndroidService, this.mContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMConnectivityManager(incomingPushAndroidService, this.mConnectivityManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMTelephonyManager(incomingPushAndroidService, this.mTelephonyManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMAudioManager(incomingPushAndroidService, this.mAudioManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectAlarmManager(incomingPushAndroidService, this.alarmManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallManager(incomingPushAndroidService, this.callManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallTimerManager(incomingPushAndroidService, this.callTimerManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommandProcessor(incomingPushAndroidService, this.mCommandProcessorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsConnectivityMonitor(incomingPushAndroidService, this.commsConnectivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommsNotificationManager(incomingPushAndroidService, this.mCommsNotificationManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceCallingService(incomingPushAndroidService, this.deviceCallingServiceProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectSipClientState(incomingPushAndroidService, this.sipClientStateProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPushNotificationManagerLazy(incomingPushAndroidService, DoubleCheck.lazy(this.pushNotificationManagerLazyProvider));
        DeviceCallingAndroidService_MembersInjector.injectMEventTracerFactory(incomingPushAndroidService, this.mEventTracerFactoryProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMDeviceCallingServiceParams(incomingPushAndroidService, this.mDeviceCallingServiceParamsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectProvisioningManager(incomingPushAndroidService, this.provisioningManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsIdentityManager(incomingPushAndroidService, this.commsIdentityManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCapabilitiesManager(incomingPushAndroidService, this.capabilitiesManagerAndMCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsActivityMonitor(incomingPushAndroidService, this.commsActivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallUIHandler(incomingPushAndroidService, this.callUIHandlerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMModeSwitchHelper(incomingPushAndroidService, this.mModeSwitchHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectArcusConfig(incomingPushAndroidService, this.arcusConfigProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCapabilitiesManager(incomingPushAndroidService, this.capabilitiesManagerAndMCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceUtils(incomingPushAndroidService, this.deviceUtilsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPccContextProvider(incomingPushAndroidService, this.pccContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMAlexaAudioPlayer(incomingPushAndroidService, this.mAlexaAudioPlayerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallHistoryHelper(incomingPushAndroidService, this.callHistoryHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallContext(incomingPushAndroidService, this.callContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallInitiationAuthority(incomingPushAndroidService, this.callInitiationAuthorityProvider.mo10268get());
        incomingPushAndroidService.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        incomingPushAndroidService.ringTonePlaybackAuthority = this.ringTonePlaybackAuthorityProvider.mo10268get();
    }
}
