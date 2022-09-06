package com.amazon.deecomms.core;

import android.app.AlarmManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.PowerManager;
import android.telecom.Connection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.comms.metrics.MetricsManager;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.accessories.VipCallingHandler;
import com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor;
import com.amazon.deecomms.accessories.monitors.AlexaMessageNotificationMonitor;
import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsCapabilityAgent;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.InternalCommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.auth.CommsRequestAuthenticator;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.call.active.ActiveVideoCallView;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.core.CallingFragmentFactory;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.impl.CallingAPIPopulator;
import com.amazon.deecomms.calling.incallcommands.RingServiceBroadcastReceiver;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.DefaultNoCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PhoneCallControllerManager;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.calling.receiver.HeadsetPluggedBroadcastReceiver;
import com.amazon.deecomms.calling.receiver.PowerButtonPressReceiver;
import com.amazon.deecomms.calling.service.BluetoothHeadsetHelper;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.TelecomConnection;
import com.amazon.deecomms.calling.telecom.TelecomConnectionService;
import com.amazon.deecomms.calling.ui.ActiveCallFragment;
import com.amazon.deecomms.calling.ui.COBOWarningActivity;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.CallPermissionActivity;
import com.amazon.deecomms.calling.ui.CallingInitiationActivity;
import com.amazon.deecomms.calling.ui.DialPad;
import com.amazon.deecomms.calling.ui.DialPadButton;
import com.amazon.deecomms.calling.ui.DirectiveReceiverActivity;
import com.amazon.deecomms.calling.ui.EndCallFragment;
import com.amazon.deecomms.calling.ui.IncomingCallFragment;
import com.amazon.deecomms.calling.ui.InitiateCallService;
import com.amazon.deecomms.calling.ui.NativeCallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.ui.OutgoingCallFragment;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.ActiveEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.IncomingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.OutgoingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.calling.util.ProximityLockHelper;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsDisposableManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.Endpointer;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.IncomingPushAndroidService;
import com.amazon.deecomms.common.service.PerformCallReconnectTask;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.service.SendCallMetricsTask;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.DiagnosticScreen;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.contacts.operations.ContactsOperationHandler;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.contacts.ui.CallBottomSheetDialogFragment;
import com.amazon.deecomms.contacts.ui.ChildContactCardFragment;
import com.amazon.deecomms.contacts.ui.ContactBlockFragment;
import com.amazon.deecomms.contacts.ui.ContactCardFragment;
import com.amazon.deecomms.contacts.ui.ContactListAdapter;
import com.amazon.deecomms.contacts.ui.ContactListFragment;
import com.amazon.deecomms.contacts.ui.EditContactFragment;
import com.amazon.deecomms.contacts.ui.EditNicknameFragment;
import com.amazon.deecomms.contacts.ui.ManageContactsFragment;
import com.amazon.deecomms.contacts.ui.RelationshipListFragment;
import com.amazon.deecomms.contacts.ui.WhitelistContactFragment;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactStatusManager;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.MainActivityLoader;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.identity.CommsIdentityManagerImpl;
import com.amazon.deecomms.identity.CommsIdentityStore;
import com.amazon.deecomms.identity.CommsIdentityStoreV2Impl;
import com.amazon.deecomms.identity.MigrationCommsIdentityStore;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioPlayer;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.media.photos.MediaContentManager;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.messaging.service.AudioDownloadService;
import com.amazon.deecomms.messaging.sync.AudioMessageSender;
import com.amazon.deecomms.messaging.sync.MessageReadStatusMarkerService;
import com.amazon.deecomms.messaging.sync.TranscriptionUpdateService;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.state.DeviceStateManager;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.amazon.deecomms.notifications.NotificationActivatedReceiver;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.notifications.service.CreateNotificationService;
import com.amazon.deecomms.notifications.util.OfflinePushNotificationRepository;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.fragments.CVFFragment;
import com.amazon.deecomms.oobe.fragments.MainOOBEFragment;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.ConfigurationSyncService;
import com.amazon.deecomms.services.CommsServiceV2Impl;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.deecomms.sharing.ShareSheetActivity;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSFetchManager;
import com.amazon.deecomms.util.TimeoutHelper;
import dagger.Component;
import java.util.Map;
import java.util.Queue;
import javax.inject.Named;
import javax.inject.Singleton;
@Component(modules = {AlexaModule.class, AndroidModule.class, ApplicationModule.class, AudioModule.class, CloudDriveModule.class, LibraryModule.class, SipModule.class, TelecomModule.class, CommsCallingModule.class})
@Singleton
/* loaded from: classes12.dex */
public interface CommsComponent {
    AcceptNativeCallHandler getAcceptCallHandler();

    AccessoriesHardwareIntentHandler getAccessoriesHardwareIntentHandler();

    AccountConfiguration getAccountConfiguration();

    AlarmManager getAlarmManager();

    AlexaCommsService getAlexaCommsService();

    AppUrl getAppUrl();

    ApplicationManager getApplicationManager();

    ArcusConfig getArcusConfig();

    AudioContentManager getAudioContentManager();

    AudioManager getAudioManager();

    AudioPlayer getAudioPlayer();

    AudioRecorder getAudioRecorder();

    AudioStateManager getAudioStateManager();

    BluetoothHeadsetHelper getBluetoothHeadsetHelper();

    CallHelper getCallHelper();

    CallHistoryHelper getCallHistoryHelper();

    CallInitiationAuthority getCallInitiationAuthority();

    CallManager getCallManager();

    ICallingAPI getCallingAPI();

    CallingAPIMonitor getCallingAPIMonitor();

    CallingAPIPopulator getCallingAPIPopulator();

    CallingFacade getCallingFacade();

    CapabilitiesManager getCapabilitiesManager();

    ClientConfiguration getClientConfiguration();

    CommandProcessor getCommandProcessor();

    CommsAccessorySessionListener getCommsAccessorySessionListener();

    CommsActivityMonitor getCommsActivityMonitor();

    @Named(LibraryModule.COMMS_ALEXA_SERVICE_CONNECTION)
    AlexaServicesConnection getCommsAlexaServicesConnection();

    CommsAudioInteraction getCommsAudioInteraction();

    CommsConnectivityMonitor getCommsConnectivityMonitor();

    CommsDeviceSupport getCommsDeviceSupport();

    CommsDirectiveHandler getCommsDirectiveHandler();

    CommsDisposableManager getCommsDisposableManager();

    CommsDriveModeCardProvider getCommsDriveModeCardProvider();

    CommsDynamicFeatureService getCommsDynamicFeatureService();

    CommsIdentityManager getCommsIdentityManager();

    CommsIdentityStore getCommsIdentityStore();

    CommsIdentityStoreV2Impl getCommsIdentityStoreV2Impl();

    CommsInternal getCommsInternal();

    CommsManager getCommsManager();

    CommsNotificationManager getCommsNotificationManager();

    CommsRequestAuthenticator getCommsRequestAuthenticator();

    ContactsOperationsManager getContactsOperationsManager();

    ContentSharingService getContentSharingService();

    Context getContext();

    ConversationMessageTracker getConversationMessageTracker();

    ConversationService getConversationService();

    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState getCurrentCallSipClientState();

    DefaultNoCallPermissionHandler getDefaultNoCallPermissionHandler();

    DeviceStateManager getDeviceStateManager();

    DeviceUtils getDeviceUtils();

    DevicesSource getDevicesSource();

    DriveModeCallPermissionHandler getDriveModeCallingPermissionHandler();

    DriveModeEventHandler getDriveModeEventHandler();

    DriveModeSharedPreferencesUseCase getDriveModeSharedPreferencesUseCase();

    EndNativeCallHandler getEndNativeCallHandler();

    EndpointsCache getEndpointsCache();

    EventBus getEventBus();

    FeatureFilter getFeatureFilter();

    FeatureFlagManager getFeatureFlagManager();

    FileTransmitter getFileTransmitter();

    FireOSDirectiveHandlerService getFireOSDirectiveHandlerService();

    IdentityService getIdentityService();

    InitiationLogicFactory getInitiationLogicFactory();

    MainActivityLoader getMainActivityLoader();

    MakeNativeCallHandler getMakeCallHandler();

    MediaContentManager getMediaContentManager();

    MessagingControllerContextProvider getMessagingControllerContextProvider();

    MessagingControllerManager getMessagingControllerManager();

    MessagingReceiver getMessagingReceiver();

    MetricsManager getMetricsManager();

    MetricsService getMetricsService();

    MigrationCommsIdentityStore getMigrationCommsIdentityStore();

    @Named(LibraryModule.NATIVE_ALEXA_SERVICE_CONNECTION)
    AlexaServicesConnection getNativeCommsServicesConnection();

    NoCallPermissionHandler getNoCallingPermissionhandler();

    OOBEPersonManager getOOBEPersonManager();

    OfflinePushNotificationRepository getOfflinePushNotificationRepository();

    OkHttpClientWrapper getOkHttpClientWrapper();

    OobeService getOobeService();

    PCCContextProvider getPCCContextProvider();

    PackageManager getPackageManager();

    PhoneAccount getPhoneAccount();

    PhoneAccountHandle getPhoneAccountHandle();

    PhoneCallControllerManager getPhoneCallControllerManager();

    PowerManager getPowerManager();

    @Named(Constants.Dagger.PREVIOUS_CALL_SIPSTATE)
    SipClientState getPreviousCallSipClientState();

    ProvisioningManager getProvisioningManager();

    PushNotificationManager getPushNotificationManager();

    SMSFetchManager getSMSFetchManager();

    ShareSheetActivity getShareSheetActivity();

    Stage getStage();

    TelecomBridge getTelecomBridge();

    TelecomManager getTelecomManager();

    TelephonyManager getTelephonyManager();

    UnreadMessageCounter getUnreadMessageCounter();

    void inject(PhoneAccountHandle phoneAccountHandle);

    void inject(AccountConfiguration accountConfiguration);

    void inject(ClientConfiguration clientConfiguration);

    void inject(EndpointsCache endpointsCache);

    void inject(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler);

    void inject(VipCallingHandler vipCallingHandler);

    void inject(AlexaCallNotificationMonitor alexaCallNotificationMonitor);

    void inject(AlexaMessageNotificationMonitor alexaMessageNotificationMonitor);

    void inject(CommsAudioInteraction commsAudioInteraction);

    void inject(CommsCapabilityAgent commsCapabilityAgent);

    void inject(A2AConnectionEndpointHandler a2AConnectionEndpointHandler);

    void inject(A2AQueuedEvents a2AQueuedEvents);

    void inject(PCCQueuedEvents pCCQueuedEvents);

    void inject(InternalCommsManager internalCommsManager);

    void inject(CommsRequestAuthenticator commsRequestAuthenticator);

    void inject(ActiveVideoCallView activeVideoCallView);

    void inject(CallHelper callHelper);

    void inject(CallingFacade callingFacade);

    void inject(DeviceCallingServiceEventListener deviceCallingServiceEventListener);

    void inject(CallingFragmentFactory callingFragmentFactory);

    void inject(RingServiceBroadcastReceiver ringServiceBroadcastReceiver);

    void inject(EffectsMenuPresenter effectsMenuPresenter);

    void inject(PCCDirectiveHandler pCCDirectiveHandler);

    void inject(PhoneCallControllerManager phoneCallControllerManager);

    void inject(CallUIHandler callUIHandler);

    void inject(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver);

    void inject(PowerButtonPressReceiver powerButtonPressReceiver);

    void inject(TelecomBridge telecomBridge);

    void inject(TelecomConnection telecomConnection);

    void inject(TelecomConnectionService telecomConnectionService);

    void inject(ActiveCallFragment activeCallFragment);

    void inject(COBOWarningActivity cOBOWarningActivity);

    void inject(CallActivity callActivity);

    void inject(CallPermissionActivity callPermissionActivity);

    void inject(CallingInitiationActivity callingInitiationActivity);

    void inject(DialPad dialPad);

    void inject(DialPadButton dialPadButton);

    void inject(DirectiveReceiverActivity directiveReceiverActivity);

    void inject(EndCallFragment endCallFragment);

    void inject(IncomingCallFragment incomingCallFragment);

    void inject(InitiateCallService initiateCallService);

    void inject(NativeCallActivity nativeCallActivity);

    void inject(NewCallActivity newCallActivity);

    void inject(OutgoingCallFragment outgoingCallFragment);

    void inject(ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment);

    void inject(OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment);

    void inject(ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment);

    void inject(IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment);

    void inject(OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment);

    void inject(CoboUtils.Dependencies dependencies);

    void inject(ProximityLockHelper proximityLockHelper);

    void inject(VoxUtils voxUtils);

    void inject(CommsInternal commsInternal);

    void inject(CommsMasterFragment commsMasterFragment);

    void inject(Endpointer endpointer);

    void inject(OkHttpClientWrapper okHttpClientWrapper);

    void inject(DeviceCallingAndroidService deviceCallingAndroidService);

    void inject(IncomingPushAndroidService incomingPushAndroidService);

    void inject(PerformCallReconnectTask performCallReconnectTask);

    void inject(ProvisioningManager provisioningManager);

    void inject(SendCallMetricsTask sendCallMetricsTask);

    void inject(DiagnosticScreen diagnosticScreen);

    void inject(ContactsOperationHandler contactsOperationHandler);

    void inject(CallBottomSheetDialogFragment callBottomSheetDialogFragment);

    void inject(ChildContactCardFragment childContactCardFragment);

    void inject(ContactBlockFragment contactBlockFragment);

    void inject(ContactCardFragment contactCardFragment);

    void inject(ContactListAdapter contactListAdapter);

    void inject(ContactListFragment contactListFragment);

    void inject(EditContactFragment editContactFragment);

    void inject(EditNicknameFragment editNicknameFragment);

    void inject(ManageContactsFragment manageContactsFragment);

    void inject(RelationshipListFragment relationshipListFragment);

    void inject(WhitelistContactFragment whitelistContactFragment);

    void inject(ContactDownloader contactDownloader);

    void inject(ContactStatusManager contactStatusManager);

    void inject(GetOrCreateContact getOrCreateContact);

    void inject(FireOSDirectiveHandlerService fireOSDirectiveHandlerService);

    void inject(FeatureFlagManager featureFlagManager);

    void inject(CommsIdentityManagerImpl commsIdentityManagerImpl);

    void inject(MigrationCommsIdentityStore migrationCommsIdentityStore);

    void inject(MessagingProviderWrapper messagingProviderWrapper);

    void inject(AudioDownloadService audioDownloadService);

    void inject(AudioMessageSender audioMessageSender);

    void inject(MessageReadStatusMarkerService messageReadStatusMarkerService);

    void inject(TranscriptionUpdateService transcriptionUpdateService);

    void inject(ReactBridgeSerializer reactBridgeSerializer);

    void inject(DeviceBottomSheet deviceBottomSheet);

    void inject(NotificationActivatedReceiver notificationActivatedReceiver);

    void inject(PushNotificationManager pushNotificationManager);

    void inject(CreateNotificationService createNotificationService);

    void inject(OOBEActivity oOBEActivity);

    void inject(CVFFragment cVFFragment);

    void inject(MainOOBEFragment mainOOBEFragment);

    void inject(DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar);

    void inject(OOBEPersonManager oOBEPersonManager);

    void inject(ThemingUpdateUtil themingUpdateUtil);

    void inject(ConfigurationSyncService configurationSyncService);

    void inject(CommsServiceV2Impl commsServiceV2Impl);

    void inject(ContentSharingService contentSharingService);

    void inject(ShareSheetActivity shareSheetActivity);

    void inject(MessagingControllerContextProvider messagingControllerContextProvider);

    void inject(MessagingControllerDirectiveHandler messagingControllerDirectiveHandler);

    void inject(MessagingControllerManager messagingControllerManager);

    void inject(Map<String, Connection> map);

    void inject(Queue<String> queue);

    TimeoutHelper timeoutHelper();
}
