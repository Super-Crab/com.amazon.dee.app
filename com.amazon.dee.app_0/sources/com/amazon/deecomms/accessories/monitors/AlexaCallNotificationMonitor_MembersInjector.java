package com.amazon.deecomms.accessories.monitors;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.phonecallcontroller.CallStateListener;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaCallNotificationMonitor_MembersInjector implements MembersInjector<AlexaCallNotificationMonitor> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CallManager> mCallManagerProvider;
    private final Provider<CallStateListener> mCustomPhoneStateListenerProvider;
    private final Provider<DeviceCallingService> mDeviceCallingServiceProvider;
    private final Provider<TelephonyManager> mTelephonyManagerProvider;

    public AlexaCallNotificationMonitor_MembersInjector(Provider<DeviceCallingService> provider, Provider<TelephonyManager> provider2, Provider<CallManager> provider3, Provider<CapabilitiesManager> provider4, Provider<Context> provider5, Provider<CallStateListener> provider6) {
        this.mDeviceCallingServiceProvider = provider;
        this.mTelephonyManagerProvider = provider2;
        this.mCallManagerProvider = provider3;
        this.capabilitiesManagerProvider = provider4;
        this.contextProvider = provider5;
        this.mCustomPhoneStateListenerProvider = provider6;
    }

    public static MembersInjector<AlexaCallNotificationMonitor> create(Provider<DeviceCallingService> provider, Provider<TelephonyManager> provider2, Provider<CallManager> provider3, Provider<CapabilitiesManager> provider4, Provider<Context> provider5, Provider<CallStateListener> provider6) {
        return new AlexaCallNotificationMonitor_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectCapabilitiesManager(AlexaCallNotificationMonitor alexaCallNotificationMonitor, CapabilitiesManager capabilitiesManager) {
        alexaCallNotificationMonitor.capabilitiesManager = capabilitiesManager;
    }

    public static void injectContext(AlexaCallNotificationMonitor alexaCallNotificationMonitor, Context context) {
        alexaCallNotificationMonitor.context = context;
    }

    public static void injectMCallManager(AlexaCallNotificationMonitor alexaCallNotificationMonitor, CallManager callManager) {
        alexaCallNotificationMonitor.mCallManager = callManager;
    }

    public static void injectMCustomPhoneStateListener(AlexaCallNotificationMonitor alexaCallNotificationMonitor, CallStateListener callStateListener) {
        alexaCallNotificationMonitor.mCustomPhoneStateListener = callStateListener;
    }

    public static void injectMDeviceCallingService(AlexaCallNotificationMonitor alexaCallNotificationMonitor, DeviceCallingService deviceCallingService) {
        alexaCallNotificationMonitor.mDeviceCallingService = deviceCallingService;
    }

    public static void injectMTelephonyManager(AlexaCallNotificationMonitor alexaCallNotificationMonitor, TelephonyManager telephonyManager) {
        alexaCallNotificationMonitor.mTelephonyManager = telephonyManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaCallNotificationMonitor alexaCallNotificationMonitor) {
        alexaCallNotificationMonitor.mDeviceCallingService = this.mDeviceCallingServiceProvider.mo10268get();
        alexaCallNotificationMonitor.mTelephonyManager = this.mTelephonyManagerProvider.mo10268get();
        alexaCallNotificationMonitor.mCallManager = this.mCallManagerProvider.mo10268get();
        alexaCallNotificationMonitor.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        alexaCallNotificationMonitor.context = this.contextProvider.mo10268get();
        alexaCallNotificationMonitor.mCustomPhoneStateListener = this.mCustomPhoneStateListenerProvider.mo10268get();
    }
}
