package com.amazon.deecomms.api;

import android.telecom.TelecomManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.FeatureFlagManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.notifications.PushNotificationManager;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class InternalCommsManager_MembersInjector implements MembersInjector<InternalCommsManager> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<AudioRecorder> audioRecorderProvider;
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallManager> callManagerLazyProvider;
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsInternal> commsInternalProvider;
    private final Provider<ContactsOperationsManager> contactsOperationsManagerProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<FeatureFlagManager> featureFlagManagerProvider;
    private final Provider<NameChangeObservable> nameChangeObservableProvider;
    private final Provider<PCCDirectiveHandler> pCCDirectiveHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomManager> telecomManagerProvider;
    private final Provider<VoxUtils> voxUtilsProvider;

    public InternalCommsManager_MembersInjector(Provider<FeatureFlagManager> provider, Provider<AudioRecorder> provider2, Provider<PushNotificationManager> provider3, Provider<ApplicationManager> provider4, Provider<CommsConnectivityMonitor> provider5, Provider<ContactsOperationsManager> provider6, Provider<CapabilitiesManager> provider7, Provider<TelecomManager> provider8, Provider<PCCContextProvider> provider9, Provider<CallManager> provider10, Provider<CommsInternal> provider11, Provider<CommsIdentityManager> provider12, Provider<ProvisioningManager> provider13, Provider<VoxUtils> provider14, Provider<PCCDirectiveHandler> provider15, Provider<CallingFacade> provider16, Provider<NameChangeObservable> provider17, Provider<EnhancedProcessingStateObservable> provider18, Provider<SipClientState> provider19, Provider<CallContext> provider20) {
        this.featureFlagManagerProvider = provider;
        this.audioRecorderProvider = provider2;
        this.pushNotificationManagerProvider = provider3;
        this.applicationManagerProvider = provider4;
        this.commsConnectivityMonitorProvider = provider5;
        this.contactsOperationsManagerProvider = provider6;
        this.capabilitiesManagerProvider = provider7;
        this.telecomManagerProvider = provider8;
        this.pccContextProvider = provider9;
        this.callManagerLazyProvider = provider10;
        this.commsInternalProvider = provider11;
        this.commsIdentityManagerProvider = provider12;
        this.provisioningManagerProvider = provider13;
        this.voxUtilsProvider = provider14;
        this.pCCDirectiveHandlerProvider = provider15;
        this.callingFacadeProvider = provider16;
        this.nameChangeObservableProvider = provider17;
        this.enhancedProcessingStateObservableProvider = provider18;
        this.sipClientStateProvider = provider19;
        this.callContextProvider = provider20;
    }

    public static MembersInjector<InternalCommsManager> create(Provider<FeatureFlagManager> provider, Provider<AudioRecorder> provider2, Provider<PushNotificationManager> provider3, Provider<ApplicationManager> provider4, Provider<CommsConnectivityMonitor> provider5, Provider<ContactsOperationsManager> provider6, Provider<CapabilitiesManager> provider7, Provider<TelecomManager> provider8, Provider<PCCContextProvider> provider9, Provider<CallManager> provider10, Provider<CommsInternal> provider11, Provider<CommsIdentityManager> provider12, Provider<ProvisioningManager> provider13, Provider<VoxUtils> provider14, Provider<PCCDirectiveHandler> provider15, Provider<CallingFacade> provider16, Provider<NameChangeObservable> provider17, Provider<EnhancedProcessingStateObservable> provider18, Provider<SipClientState> provider19, Provider<CallContext> provider20) {
        return new InternalCommsManager_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20);
    }

    public static void injectApplicationManager(InternalCommsManager internalCommsManager, ApplicationManager applicationManager) {
        internalCommsManager.applicationManager = applicationManager;
    }

    public static void injectAudioRecorder(InternalCommsManager internalCommsManager, AudioRecorder audioRecorder) {
        internalCommsManager.audioRecorder = audioRecorder;
    }

    public static void injectCallContext(InternalCommsManager internalCommsManager, CallContext callContext) {
        internalCommsManager.callContext = callContext;
    }

    public static void injectCallManagerLazy(InternalCommsManager internalCommsManager, Lazy<CallManager> lazy) {
        internalCommsManager.callManagerLazy = lazy;
    }

    public static void injectCallingFacade(InternalCommsManager internalCommsManager, CallingFacade callingFacade) {
        internalCommsManager.callingFacade = callingFacade;
    }

    public static void injectCapabilitiesManager(InternalCommsManager internalCommsManager, CapabilitiesManager capabilitiesManager) {
        internalCommsManager.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsConnectivityMonitor(InternalCommsManager internalCommsManager, CommsConnectivityMonitor commsConnectivityMonitor) {
        internalCommsManager.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectCommsIdentityManager(InternalCommsManager internalCommsManager, CommsIdentityManager commsIdentityManager) {
        internalCommsManager.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsInternal(InternalCommsManager internalCommsManager, CommsInternal commsInternal) {
        internalCommsManager.commsInternal = commsInternal;
    }

    public static void injectContactsOperationsManager(InternalCommsManager internalCommsManager, ContactsOperationsManager contactsOperationsManager) {
        internalCommsManager.contactsOperationsManager = contactsOperationsManager;
    }

    public static void injectEnhancedProcessingStateObservable(InternalCommsManager internalCommsManager, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        internalCommsManager.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    public static void injectFeatureFlagManager(InternalCommsManager internalCommsManager, FeatureFlagManager featureFlagManager) {
        internalCommsManager.featureFlagManager = featureFlagManager;
    }

    public static void injectNameChangeObservable(InternalCommsManager internalCommsManager, NameChangeObservable nameChangeObservable) {
        internalCommsManager.nameChangeObservable = nameChangeObservable;
    }

    public static void injectPCCDirectiveHandler(InternalCommsManager internalCommsManager, PCCDirectiveHandler pCCDirectiveHandler) {
        internalCommsManager.pCCDirectiveHandler = pCCDirectiveHandler;
    }

    public static void injectPccContextProvider(InternalCommsManager internalCommsManager, PCCContextProvider pCCContextProvider) {
        internalCommsManager.pccContextProvider = pCCContextProvider;
    }

    public static void injectProvisioningManager(InternalCommsManager internalCommsManager, ProvisioningManager provisioningManager) {
        internalCommsManager.provisioningManager = provisioningManager;
    }

    public static void injectPushNotificationManager(InternalCommsManager internalCommsManager, PushNotificationManager pushNotificationManager) {
        internalCommsManager.pushNotificationManager = pushNotificationManager;
    }

    public static void injectSipClientState(InternalCommsManager internalCommsManager, SipClientState sipClientState) {
        internalCommsManager.sipClientState = sipClientState;
    }

    public static void injectTelecomManager(InternalCommsManager internalCommsManager, TelecomManager telecomManager) {
        internalCommsManager.telecomManager = telecomManager;
    }

    public static void injectVoxUtils(InternalCommsManager internalCommsManager, VoxUtils voxUtils) {
        internalCommsManager.voxUtils = voxUtils;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InternalCommsManager internalCommsManager) {
        internalCommsManager.featureFlagManager = this.featureFlagManagerProvider.mo10268get();
        internalCommsManager.audioRecorder = this.audioRecorderProvider.mo10268get();
        internalCommsManager.pushNotificationManager = this.pushNotificationManagerProvider.mo10268get();
        internalCommsManager.applicationManager = this.applicationManagerProvider.mo10268get();
        internalCommsManager.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
        internalCommsManager.contactsOperationsManager = this.contactsOperationsManagerProvider.mo10268get();
        internalCommsManager.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        internalCommsManager.telecomManager = this.telecomManagerProvider.mo10268get();
        internalCommsManager.pccContextProvider = this.pccContextProvider.mo10268get();
        internalCommsManager.callManagerLazy = DoubleCheck.lazy(this.callManagerLazyProvider);
        internalCommsManager.commsInternal = this.commsInternalProvider.mo10268get();
        internalCommsManager.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        internalCommsManager.provisioningManager = this.provisioningManagerProvider.mo10268get();
        internalCommsManager.voxUtils = this.voxUtilsProvider.mo10268get();
        internalCommsManager.pCCDirectiveHandler = this.pCCDirectiveHandlerProvider.mo10268get();
        internalCommsManager.callingFacade = this.callingFacadeProvider.mo10268get();
        internalCommsManager.nameChangeObservable = this.nameChangeObservableProvider.mo10268get();
        internalCommsManager.enhancedProcessingStateObservable = this.enhancedProcessingStateObservableProvider.mo10268get();
        internalCommsManager.sipClientState = this.sipClientStateProvider.mo10268get();
        internalCommsManager.callContext = this.callContextProvider.mo10268get();
    }
}
