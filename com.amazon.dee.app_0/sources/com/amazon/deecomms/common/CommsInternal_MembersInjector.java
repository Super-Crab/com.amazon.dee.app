package com.amazon.deecomms.common;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.common.controller.CommsDisposableManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsInternal_MembersInjector implements MembersInjector<CommsInternal> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsActivityMonitor> commsActivityMonitorProvider;
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<CommsDisposableManager> commsDisposableManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<ConversationMessageTracker> conversationMessageTrackerProvider;
    private final Provider<DeviceMetadataStoreRegistrar> deviceMetadataStoreRegistrarProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<CallUIHandler> mCallUIHandlerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<SecuredSharedPreference> mSecuredSharedPreferenceProvider;
    private final Provider<MessagingControllerManager> messagingControllerManagerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<PhoneCallControllerManager> phoneCallControllerManagerProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;

    public CommsInternal_MembersInjector(Provider<Context> provider, Provider<CallUIHandler> provider2, Provider<SecuredSharedPreference> provider3, Provider<CommsConnectivityMonitor> provider4, Provider<PushNotificationManager> provider5, Provider<ApplicationManager> provider6, Provider<CommsIdentityManager> provider7, Provider<ProvisioningManager> provider8, Provider<CommsActivityMonitor> provider9, Provider<CapabilitiesManager> provider10, Provider<PCCContextProvider> provider11, Provider<CommsDisposableManager> provider12, Provider<CommsNotificationManager> provider13, Provider<DeviceMetadataStoreRegistrar> provider14, Provider<DeviceUtils> provider15, Provider<MessagingControllerManager> provider16, Provider<PhoneCallControllerManager> provider17, Provider<ConversationMessageTracker> provider18, Provider<EventBus> provider19, Provider<CallInitiationAuthority> provider20) {
        this.mContextProvider = provider;
        this.mCallUIHandlerProvider = provider2;
        this.mSecuredSharedPreferenceProvider = provider3;
        this.commsConnectivityMonitorProvider = provider4;
        this.pushNotificationManagerProvider = provider5;
        this.applicationManagerProvider = provider6;
        this.commsIdentityManagerProvider = provider7;
        this.provisioningManagerProvider = provider8;
        this.commsActivityMonitorProvider = provider9;
        this.capabilitiesManagerProvider = provider10;
        this.pccContextProvider = provider11;
        this.commsDisposableManagerProvider = provider12;
        this.commsNotificationManagerProvider = provider13;
        this.deviceMetadataStoreRegistrarProvider = provider14;
        this.deviceUtilsProvider = provider15;
        this.messagingControllerManagerProvider = provider16;
        this.phoneCallControllerManagerProvider = provider17;
        this.conversationMessageTrackerProvider = provider18;
        this.eventBusProvider = provider19;
        this.callInitiationAuthorityProvider = provider20;
    }

    public static MembersInjector<CommsInternal> create(Provider<Context> provider, Provider<CallUIHandler> provider2, Provider<SecuredSharedPreference> provider3, Provider<CommsConnectivityMonitor> provider4, Provider<PushNotificationManager> provider5, Provider<ApplicationManager> provider6, Provider<CommsIdentityManager> provider7, Provider<ProvisioningManager> provider8, Provider<CommsActivityMonitor> provider9, Provider<CapabilitiesManager> provider10, Provider<PCCContextProvider> provider11, Provider<CommsDisposableManager> provider12, Provider<CommsNotificationManager> provider13, Provider<DeviceMetadataStoreRegistrar> provider14, Provider<DeviceUtils> provider15, Provider<MessagingControllerManager> provider16, Provider<PhoneCallControllerManager> provider17, Provider<ConversationMessageTracker> provider18, Provider<EventBus> provider19, Provider<CallInitiationAuthority> provider20) {
        return new CommsInternal_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20);
    }

    public static void injectApplicationManager(CommsInternal commsInternal, ApplicationManager applicationManager) {
        commsInternal.applicationManager = applicationManager;
    }

    public static void injectCallInitiationAuthority(CommsInternal commsInternal, CallInitiationAuthority callInitiationAuthority) {
        commsInternal.callInitiationAuthority = callInitiationAuthority;
    }

    public static void injectCapabilitiesManager(CommsInternal commsInternal, CapabilitiesManager capabilitiesManager) {
        commsInternal.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsActivityMonitor(CommsInternal commsInternal, CommsActivityMonitor commsActivityMonitor) {
        commsInternal.commsActivityMonitor = commsActivityMonitor;
    }

    public static void injectCommsConnectivityMonitor(CommsInternal commsInternal, CommsConnectivityMonitor commsConnectivityMonitor) {
        commsInternal.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectCommsDisposableManager(CommsInternal commsInternal, CommsDisposableManager commsDisposableManager) {
        commsInternal.commsDisposableManager = commsDisposableManager;
    }

    public static void injectCommsIdentityManager(CommsInternal commsInternal, CommsIdentityManager commsIdentityManager) {
        commsInternal.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsNotificationManager(CommsInternal commsInternal, CommsNotificationManager commsNotificationManager) {
        commsInternal.commsNotificationManager = commsNotificationManager;
    }

    public static void injectConversationMessageTracker(CommsInternal commsInternal, ConversationMessageTracker conversationMessageTracker) {
        commsInternal.conversationMessageTracker = conversationMessageTracker;
    }

    public static void injectDeviceMetadataStoreRegistrar(CommsInternal commsInternal, DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar) {
        commsInternal.deviceMetadataStoreRegistrar = deviceMetadataStoreRegistrar;
    }

    public static void injectDeviceUtils(CommsInternal commsInternal, DeviceUtils deviceUtils) {
        commsInternal.deviceUtils = deviceUtils;
    }

    public static void injectEventBus(CommsInternal commsInternal, EventBus eventBus) {
        commsInternal.eventBus = eventBus;
    }

    public static void injectMCallUIHandler(CommsInternal commsInternal, CallUIHandler callUIHandler) {
        commsInternal.mCallUIHandler = callUIHandler;
    }

    public static void injectMContext(CommsInternal commsInternal, Context context) {
        commsInternal.mContext = context;
    }

    public static void injectMSecuredSharedPreference(CommsInternal commsInternal, Lazy<SecuredSharedPreference> lazy) {
        commsInternal.mSecuredSharedPreference = lazy;
    }

    public static void injectMessagingControllerManager(CommsInternal commsInternal, MessagingControllerManager messagingControllerManager) {
        commsInternal.messagingControllerManager = messagingControllerManager;
    }

    public static void injectPccContextProvider(CommsInternal commsInternal, PCCContextProvider pCCContextProvider) {
        commsInternal.pccContextProvider = pCCContextProvider;
    }

    public static void injectPhoneCallControllerManager(CommsInternal commsInternal, PhoneCallControllerManager phoneCallControllerManager) {
        commsInternal.phoneCallControllerManager = phoneCallControllerManager;
    }

    public static void injectProvisioningManager(CommsInternal commsInternal, Lazy<ProvisioningManager> lazy) {
        commsInternal.provisioningManager = lazy;
    }

    public static void injectPushNotificationManager(CommsInternal commsInternal, PushNotificationManager pushNotificationManager) {
        commsInternal.pushNotificationManager = pushNotificationManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsInternal commsInternal) {
        commsInternal.mContext = this.mContextProvider.mo10268get();
        commsInternal.mCallUIHandler = this.mCallUIHandlerProvider.mo10268get();
        commsInternal.mSecuredSharedPreference = DoubleCheck.lazy(this.mSecuredSharedPreferenceProvider);
        commsInternal.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
        commsInternal.pushNotificationManager = this.pushNotificationManagerProvider.mo10268get();
        commsInternal.applicationManager = this.applicationManagerProvider.mo10268get();
        commsInternal.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        commsInternal.provisioningManager = DoubleCheck.lazy(this.provisioningManagerProvider);
        commsInternal.commsActivityMonitor = this.commsActivityMonitorProvider.mo10268get();
        commsInternal.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        commsInternal.pccContextProvider = this.pccContextProvider.mo10268get();
        commsInternal.commsDisposableManager = this.commsDisposableManagerProvider.mo10268get();
        commsInternal.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        commsInternal.deviceMetadataStoreRegistrar = this.deviceMetadataStoreRegistrarProvider.mo10268get();
        commsInternal.deviceUtils = this.deviceUtilsProvider.mo10268get();
        commsInternal.messagingControllerManager = this.messagingControllerManagerProvider.mo10268get();
        commsInternal.phoneCallControllerManager = this.phoneCallControllerManagerProvider.mo10268get();
        commsInternal.conversationMessageTracker = this.conversationMessageTrackerProvider.mo10268get();
        commsInternal.eventBus = this.eventBusProvider.mo10268get();
        commsInternal.callInitiationAuthority = this.callInitiationAuthorityProvider.mo10268get();
    }
}
