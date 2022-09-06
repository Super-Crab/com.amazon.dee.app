package com.amazon.deecomms.core;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telecom.Connection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.metrics.MetricsManager;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler_MembersInjector;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.accessories.VipCallingHandler;
import com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor;
import com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor_MembersInjector;
import com.amazon.deecomms.accessories.monitors.AlexaMessageNotificationMonitor;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler_Factory;
import com.amazon.deecomms.alexa.CommsCapabilityAgent;
import com.amazon.deecomms.alexa.CommsCapabilityAgent_MembersInjector;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.alexa.CommsDirectiveHandler_Factory;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.alexa.voice.usecase.EventSenderUseCase_Factory;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.InternalCommsManager;
import com.amazon.deecomms.api.InternalCommsManager_MembersInjector;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.auth.CommsRequestAuthenticator;
import com.amazon.deecomms.auth.CommsSharedPreferences;
import com.amazon.deecomms.auth.CommsSharedPreferences_Factory;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.call.active.ActiveVideoCallView;
import com.amazon.deecomms.call.active.ActiveVideoCallView_MembersInjector;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority_Factory;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher_Factory;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable_Factory;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallHelper_MembersInjector;
import com.amazon.deecomms.calling.controller.CallInitiationOrchestrator;
import com.amazon.deecomms.calling.controller.CallInitiationOrchestrator_Factory;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallMetricsFactory;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener;
import com.amazon.deecomms.calling.controller.DeviceCallingServiceEventListener_MembersInjector;
import com.amazon.deecomms.calling.controller.ValidBeginCallPayloadHandler;
import com.amazon.deecomms.calling.controller.ValidBeginCallPayloadHandler_Factory;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.core.CallInitiationAuthority_Factory;
import com.amazon.deecomms.calling.core.CallingFragmentFactory;
import com.amazon.deecomms.calling.core.CallingFragmentFactory_MembersInjector;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor_Factory;
import com.amazon.deecomms.calling.impl.CallingAPIPopulator;
import com.amazon.deecomms.calling.impl.CallingAPIPopulator_Factory;
import com.amazon.deecomms.calling.incallcommands.RingServiceBroadcastReceiver;
import com.amazon.deecomms.calling.incallcommands.RingServiceBroadcastReceiver_MembersInjector;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModelFactory;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState_Factory;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory_Factory;
import com.amazon.deecomms.calling.model.BeginCallMapper;
import com.amazon.deecomms.calling.model.BeginCallMapper_Factory;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.CallContext_Factory;
import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.CallStateListener;
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
import com.amazon.deecomms.calling.receiver.HeadsetPluggedBroadcastReceiver_MembersInjector;
import com.amazon.deecomms.calling.receiver.PowerButtonPressReceiver;
import com.amazon.deecomms.calling.receiver.PowerButtonPressReceiver_MembersInjector;
import com.amazon.deecomms.calling.service.BluetoothHeadsetHelper;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.TelecomConnection;
import com.amazon.deecomms.calling.telecom.TelecomConnectionService;
import com.amazon.deecomms.calling.telecom.TelecomConnectionService_MembersInjector;
import com.amazon.deecomms.calling.telecom.TelecomConnection_MembersInjector;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.ActiveCallFragment;
import com.amazon.deecomms.calling.ui.ActiveCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.BaseCallingPresenter;
import com.amazon.deecomms.calling.ui.COBOWarningActivity;
import com.amazon.deecomms.calling.ui.COBOWarningActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.CallActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.CallPermissionActivity;
import com.amazon.deecomms.calling.ui.CallPermissionActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.CallingInitiationActivity;
import com.amazon.deecomms.calling.ui.CallingInitiationActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.DialPad;
import com.amazon.deecomms.calling.ui.DialPadButton;
import com.amazon.deecomms.calling.ui.DialPadButton_MembersInjector;
import com.amazon.deecomms.calling.ui.DialPad_MembersInjector;
import com.amazon.deecomms.calling.ui.DirectiveReceiverActivity;
import com.amazon.deecomms.calling.ui.DirectiveReceiverActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.EndCallFragment;
import com.amazon.deecomms.calling.ui.EndCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable_Factory;
import com.amazon.deecomms.calling.ui.IncomingCallFragment;
import com.amazon.deecomms.calling.ui.IncomingCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.InitiateCallService;
import com.amazon.deecomms.calling.ui.InitiateCallService_MembersInjector;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable_Factory;
import com.amazon.deecomms.calling.ui.NativeCallActivity;
import com.amazon.deecomms.calling.ui.NativeCallActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity_MembersInjector;
import com.amazon.deecomms.calling.ui.OutgoingCallFragment;
import com.amazon.deecomms.calling.ui.OutgoingCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable_Factory;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.ep.audio.ActiveEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.ActiveEnhancedProcessingAudioCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.ep.audio.IncomingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.IncomingEnhancedProcessingAudioCallFragment_MembersInjector;
import com.amazon.deecomms.calling.ui.ep.audio.OutgoingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.OutgoingEnhancedProcessingAudioCallFragment_MembersInjector;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.calling.util.CoboUtils_Dependencies_MembersInjector;
import com.amazon.deecomms.calling.util.ProximityLockHelper;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.calling.util.VoxUtils_MembersInjector;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.CommsInternal_MembersInjector;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.CommsMasterFragment_MembersInjector;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsDisposableManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.Endpointer;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService_MembersInjector;
import com.amazon.deecomms.common.service.IncomingPushAndroidService;
import com.amazon.deecomms.common.service.IncomingPushAndroidService_MembersInjector;
import com.amazon.deecomms.common.service.PerformCallReconnectTask;
import com.amazon.deecomms.common.service.PerformCallReconnectTask_MembersInjector;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.service.ProvisioningManager_MembersInjector;
import com.amazon.deecomms.common.service.SendCallMetricsTask;
import com.amazon.deecomms.common.service.SendCallMetricsTask_MembersInjector;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.DiagnosticScreen;
import com.amazon.deecomms.common.ui.DiagnosticScreen_MembersInjector;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.contacts.operations.ContactsOperationHandler;
import com.amazon.deecomms.contacts.operations.ContactsOperationHandler_MembersInjector;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.contacts.ui.CallBottomSheetDialogFragment;
import com.amazon.deecomms.contacts.ui.ChildContactCardFragment;
import com.amazon.deecomms.contacts.ui.ChildContactCardFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.ContactBlockFragment;
import com.amazon.deecomms.contacts.ui.ContactBlockFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.ContactCardFragment;
import com.amazon.deecomms.contacts.ui.ContactCardFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.ContactListAdapter;
import com.amazon.deecomms.contacts.ui.ContactListAdapter_MembersInjector;
import com.amazon.deecomms.contacts.ui.ContactListFragment;
import com.amazon.deecomms.contacts.ui.ContactListFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.EditContactFragment;
import com.amazon.deecomms.contacts.ui.EditContactFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.EditNicknameFragment;
import com.amazon.deecomms.contacts.ui.EditNicknameFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.ManageContactsFragment;
import com.amazon.deecomms.contacts.ui.ManageContactsFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.RelationshipListFragment;
import com.amazon.deecomms.contacts.ui.RelationshipListFragment_MembersInjector;
import com.amazon.deecomms.contacts.ui.WhitelistContactFragment;
import com.amazon.deecomms.contacts.ui.WhitelistContactFragment_MembersInjector;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactDownloader_MembersInjector;
import com.amazon.deecomms.contacts.util.ContactStatusManager;
import com.amazon.deecomms.contacts.util.ContactStatusManager_MembersInjector;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.contacts.util.GetOrCreateContact_MembersInjector;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService_Factory;
import com.amazon.deecomms.conversation.FireOSDirectiveHandlerService_MembersInjector;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import com.amazon.deecomms.core.decoupling.MainActivityLoader;
import com.amazon.deecomms.core.decoupling.MainActivityLoader_Factory;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler;
import com.amazon.deecomms.drivemode.eventhandler.DriveModeEventHandler_Factory;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase_Factory;
import com.amazon.deecomms.identity.CommsIdentityManagerImpl;
import com.amazon.deecomms.identity.CommsIdentityManagerImpl_MembersInjector;
import com.amazon.deecomms.identity.CommsIdentityStore;
import com.amazon.deecomms.identity.CommsIdentityStoreV2Impl;
import com.amazon.deecomms.identity.MigrationCommsIdentityStore;
import com.amazon.deecomms.identity.MigrationCommsIdentityStore_MembersInjector;
import com.amazon.deecomms.media.VideoStateController;
import com.amazon.deecomms.media.VideoStateController_Factory;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioInputController;
import com.amazon.deecomms.media.audio.AudioOutputController;
import com.amazon.deecomms.media.audio.AudioPlayer;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.media.audio.CallMediaControlFacade_Factory;
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority;
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority_Factory;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory;
import com.amazon.deecomms.media.photos.MediaContentManager;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper_MembersInjector;
import com.amazon.deecomms.messaging.service.AudioDownloadService;
import com.amazon.deecomms.messaging.service.AudioDownloadService_MembersInjector;
import com.amazon.deecomms.messaging.sync.AudioMessageSender;
import com.amazon.deecomms.messaging.sync.AudioMessageSender_MembersInjector;
import com.amazon.deecomms.messaging.sync.MessageReadStatusMarkerService;
import com.amazon.deecomms.messaging.sync.MessageReadStatusMarkerService_MembersInjector;
import com.amazon.deecomms.messaging.sync.TranscriptionUpdateService;
import com.amazon.deecomms.messaging.sync.TranscriptionUpdateService_MembersInjector;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.state.DeviceStateManager;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet_MembersInjector;
import com.amazon.deecomms.notifications.InboundAnnouncementCoordinator;
import com.amazon.deecomms.notifications.NotificationActivatedReceiver;
import com.amazon.deecomms.notifications.NotificationActivatedReceiver_MembersInjector;
import com.amazon.deecomms.notifications.NotificationLatencyMetricHelper;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import com.amazon.deecomms.notifications.service.CreateNotificationService;
import com.amazon.deecomms.notifications.service.CreateNotificationService_MembersInjector;
import com.amazon.deecomms.notifications.util.OfflinePushNotificationRepository;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.OOBEActivity_MembersInjector;
import com.amazon.deecomms.oobe.fragments.CVFFragment;
import com.amazon.deecomms.oobe.fragments.CVFFragment_MembersInjector;
import com.amazon.deecomms.oobe.fragments.MainOOBEFragment;
import com.amazon.deecomms.oobe.fragments.MainOOBEFragment_MembersInjector;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.amazon.deecomms.oobe.util.OOBEPersonManager_MembersInjector;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.perms.VoicePermissionsAuthority;
import com.amazon.deecomms.perms.VoicePermissionsAuthority_Factory;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.ConfigurationSyncService;
import com.amazon.deecomms.remoteConfig.ConfigurationSyncService_MembersInjector;
import com.amazon.deecomms.services.CommsServiceV2Impl;
import com.amazon.deecomms.services.CommsServiceV2Impl_MembersInjector;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.deecomms.sharing.ShareSheetActivity;
import com.amazon.deecomms.sharing.ShareSheetActivity_MembersInjector;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider_MembersInjector;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler_MembersInjector;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager_MembersInjector;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSFetchManager;
import com.amazon.deecomms.util.IBuildVersionProvider;
import com.amazon.deecomms.util.TimeoutHelper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Queue;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DaggerCommsComponent implements CommsComponent {
    private AndroidModule androidModule;
    private Provider<CallActionsDispatcher> callActionsDispatcherProvider;
    private Provider<CallContext> callContextProvider;
    private Provider<CallDowngradedObservable> callDowngradedObservableProvider;
    private Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private CallInitiationOrchestrator_Factory callInitiationOrchestratorProvider;
    private Provider<CallMediaControlFacade> callMediaControlFacadeProvider;
    private CallingAPIMonitor_Factory callingAPIMonitorProvider;
    private CallingAPIPopulator_Factory callingAPIPopulatorProvider;
    private CloudDriveModule cloudDriveModule;
    private CommsAudioInteractionScheduler_Factory commsAudioInteractionSchedulerProvider;
    private CommsDirectiveHandler_Factory commsDirectiveHandlerProvider;
    private CommsSharedPreferences_Factory commsSharedPreferencesProvider;
    private DriveModeEventHandler_Factory driveModeEventHandlerProvider;
    private DriveModeSharedPreferencesUseCase_Factory driveModeSharedPreferencesUseCaseProvider;
    private Provider<EffectsState> effectsStateProvider;
    private Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private EventSenderUseCase_Factory eventSenderUseCaseProvider;
    private InitiationLogicFactory_Factory initiationLogicFactoryProvider;
    private LibraryModule libraryModule;
    private Provider<NameChangeObservable> nameChangeObservableProvider;
    private Provider<PipVisibilityObservable> pipVisibilityObservableProvider;
    private CloudDriveModule_ProvideAccountConfigurationFactory provideAccountConfigurationProvider;
    private Provider<CallStateListener> provideCallStateListenerProvider;
    private CloudDriveModule_ProvideClientConfigurationFactory provideClientConfigurationProvider;
    private Provider<MessagingReceiver> provideConversationMessagingReceiverProvider;
    private Provider<DefaultNoCallPermissionHandler> provideDefaultNoCallPermissionHandlerProvider;
    private Provider<DriveModeCallPermissionHandler> provideDriveModeNoPermissionHandlerProvider;
    private CloudDriveModule_ProvideEndpointsCacheFactory provideEndpointsCacheProvider;
    private CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory provideMAPAuthenticatedURLConnectionFactoryProvider;
    private Provider<NoCallPermissionHandler> provideNoPermissionHandlerProvider;
    private CloudDriveModule_ProvideOkHttpClientWrapperFactory provideOkHttpClientWrapperProvider;
    private CloudDriveModule_ProvideRequestAuthenticatorFactory provideRequestAuthenticatorProvider;
    private Provider<A2AConnectionEndpointHandler> providesA2AConnectionEnpointHandlerProvider;
    private Provider<A2AQueuedEvents> providesA2AUnsentEventsManagerProvider;
    private Provider<AcceptNativeCallHandler> providesAcceptNativeCallHandlerProvider;
    private Provider<AccessoriesHardwareIntentHandler> providesAccessoriesHardwareIntentHandlerProvider;
    private Provider<ActivitiesManager> providesActivitiesManagerProvider;
    private AndroidModule_ProvidesActivityManagerFactory providesActivityManagerProvider;
    private AndroidModule_ProvidesAlarmManagerFactory providesAlarmManagerProvider;
    private Provider<AlexaAudioPlayer> providesAlexaAudioPlayerProvider;
    private Provider<AlexaCommsService> providesAlexaCommsServiceProvider;
    private Provider<AlexaInterface> providesAlexaInterfaceProvider;
    private Provider<ModeSwitchHelper> providesAlexaModeSwitchHelperProvider;
    private Provider<String> providesAppVersionProvider;
    private Provider<ApplicationManager> providesApplicationManagerProvider;
    private Provider<ArcusConfig> providesArcusConfigProvider;
    private Provider<AudioContentManager> providesAudioContentManagerProvider;
    private AndroidModule_ProvidesAudioManagerFactory providesAudioManagerProvider;
    private Provider<AudioPlayer> providesAudioPlayerProvider;
    private Provider<AudioRecorder> providesAudioRecorderProvider;
    private Provider<AudioOutputController> providesAudioStateControllerProvider;
    private Provider<AudioStateManager> providesAudioStateManagerProvider;
    private Provider<AudioStateObservable> providesAudioStateObservableProvider;
    private Provider<BluetoothHeadsetHelper> providesBluetoothHeadsetHelperProvider;
    private Provider<IBuildVersionProvider> providesBuildVersionProvider;
    private Provider<CallHistoryHelper> providesCallHistoryHelperProvider;
    private Provider<Map<String, Connection>> providesCallIdToTelecomConnectionProvider;
    private Provider<CallManager> providesCallManagerProvider;
    private Provider<CallMetricsFactory> providesCallMetricsFactoryProvider;
    private SipModule_ProvidesCallPayloadValidatorFactory providesCallPayloadValidatorProvider;
    private Provider<CallTimerManager> providesCallTimerManagerProvider;
    private Provider<CallUIHandler> providesCallUIHandlerProvider;
    private Provider<ICallingAPI> providesCallingAPIProvider;
    private SipModule_ProvidesCallingFacadeFactory providesCallingFacadeProvider;
    private Provider<CapabilitiesManager> providesCapabilitiesManagerProvider;
    private Provider<CommandProcessor> providesCommandProcessorProvider;
    private Provider<CommsAccessorySessionListener> providesCommsAccessorySessionListenerProvider;
    private Provider<CommsActivityMonitor> providesCommsActivityMonitorProvider;
    private Provider<CommsAlexaServicesConnectionListener> providesCommsAlexaServicesConnectionListenerProvider;
    private Provider<AlexaServicesConnection> providesCommsAlexaServicesConnectionProvider;
    private Provider<CommsAudioInteraction> providesCommsAudioInteractionProvider;
    private Provider<CommsConnectivityMonitor> providesCommsConnectivityMonitorProvider;
    private Provider<CommsDeviceSupport> providesCommsDeviceSupportProvider;
    private Provider<CommsDisposableManager> providesCommsDisposableManagerProvider;
    private Provider<CommsDriveModeCardProvider> providesCommsDriveModeCardProvider;
    private Provider<CommsDynamicFeatureService> providesCommsDynamicFeatureServiceProvider;
    private Provider<CommsEventSender> providesCommsEventSenderProvider;
    private Provider<CommsIdentityManager> providesCommsIdentityManagerProvider;
    private Provider<CommsIdentityStoreV2Impl> providesCommsIdentityStoreV2Provider;
    private Provider<CommsInternal> providesCommsInternalProvider;
    private Provider<CommsManager> providesCommsManagerProvider;
    private Provider<CommsNotificationManager> providesCommsNotificationManagerProvider;
    private AndroidModule_ProvidesConnectivityManagerFactory providesConnectivityManagerProvider;
    private Provider<ContactsOperationsManager> providesContactsOperationsManagerProvider;
    private Provider<ContentSharingService> providesContentSharingServiceProvider;
    private Provider<Context> providesContextProvider;
    private Provider<ConversationMessageTracker> providesConversationMessageTrackerProvider;
    private Provider<ConversationService> providesConversationServiceProvider;
    private Provider<DeviceCallingServiceParams> providesDeviceCallingServiceParamsProvider;
    private Provider<DeviceCallingService> providesDeviceCallingServiceProvider;
    private Provider<DeviceInformation> providesDeviceInformationProvider;
    private Provider<DeviceMetadataStoreRegistrar> providesDeviceMetadataStoreRegistrarProvider;
    private Provider<String> providesDeviceNameTemplateProvider;
    private Provider<DeviceStateManager> providesDeviceStateManagerProvider;
    private Provider<DeviceUtils> providesDeviceUtilsProvider;
    private Provider<DevicesSource> providesDevicesSourceProvider;
    private AlexaModule_ProvidesDriveModeServiceFactory providesDriveModeServiceProvider;
    private Provider<EncryptionUtils> providesEncryptionUtilsProvider;
    private Provider<EndNativeCallHandler> providesEndNativeCallHandlerProvider;
    private Provider<EventBus> providesEventBusProvider;
    private AlexaModule_ProvidesEventSenderFactory providesEventSenderProvider;
    private Provider<EventTracerFactory> providesEventTracerFactoryProvider;
    private Provider<FeatureFilter> providesFeatureFilterProvider;
    private Provider<FeatureFlagManager> providesFeatureFlagManagerProvider;
    private Provider<FileTransmitter> providesFileTransmitterProvider;
    private Provider<IdentityService> providesIdentityServiceProvider;
    private Provider<InCallCommandModelFactory> providesInCallCommandModelFactoryProvider;
    private Provider<InboundAnnouncementCoordinator> providesInboundAnnouncementsCoordinatorProvider;
    private Provider<CommsIdentityStore> providesLegacyCommsIdentityStoreProvider;
    private Provider<MakeNativeCallHandler> providesMakeNativeCallHandlerProvider;
    private Provider<MediaContentManager> providesMediaContentManagerProvider;
    private Provider<MessagingControllerContextProvider> providesMessagingControllerContextProvider;
    private Provider<MessagingControllerManager> providesMessagingControllerManagerProvider;
    private Provider<MetricsManager> providesMetricsManagerProvider;
    private Provider<MetricsService> providesMetricsServiceProvider;
    private Provider<AudioInputController> providesMicStateControllerProvider;
    private Provider<MigrationCommsIdentityStore> providesMigrationCommsIdentityStoreProvider;
    private Provider<AlexaServicesConnection> providesMuffinAlexaServicesConnectionProvider;
    private Provider<NotificationLatencyMetricHelper> providesNotificationLatencyMetricHelperProvider;
    private AndroidModule_ProvidesNotificationManagerFactory providesNotificationManagerProvider;
    private Provider<OOBEPersonManager> providesOOBEPersonUtilProvider;
    private Provider<OfflinePushNotificationRepository> providesOfflinePushNotificationRepositoryProvider;
    private Provider<OobeService> providesOobeServiceProvider;
    private Provider<PCCConnectionEndpointHandler> providesPCCConnectionEnpointHandlerProvider;
    private Provider<PCCContextProvider> providesPCCContextProvider;
    private Provider<PCCDirectiveHandler> providesPCCDirectiveHandlerProvider;
    private Provider<PCCQueuedEvents> providesPCCUnsentEventsManagerProvider;
    private AndroidModule_ProvidesPackageManagerFactory providesPackageManagerProvider;
    private Provider<PhoneAccountHandle> providesPhoneAccountHandleProvider;
    private Provider<PhoneAccount> providesPhoneAccountProvider;
    private Provider<PhoneCallControllerManager> providesPhoneCallControllerManagerProvider;
    private Provider<SipClientState> providesPreviousSipClientStateProvider;
    private Provider<ProfileSelectionService> providesProfileSelectionServiceProvider;
    private Provider<ProvisioningManager> providesProvisioningManagerProvider;
    private Provider<PushNotificationManager> providesPushNotificationManagerProvider;
    private Provider<SMSFetchManager> providesSMSFetchManagerProvider;
    private LibraryModule_ProvidesSecuredSharedPreferenceFactory providesSecuredSharedPreferenceProvider;
    private Provider<ShareSheetActivity> providesShareSheetActivityProvider;
    private Provider<SipClientState> providesSipClientStateProvider;
    private Provider<SipHeaders> providesSipHeadersProvider;
    private Provider<Stage> providesStageProvider;
    private Provider<TelecomBridge> providesTelecomBridgeProvider;
    private Provider<TelecomCallAudioRouteObservable> providesTelecomCallAudioRouteObservableProvider;
    private Provider<Queue<String>> providesTelecomCallIdsProvider;
    private Provider<Object> providesTelecomLockProvider;
    private AndroidModule_ProvidesTelecomManagerFactory providesTelecomManagerProvider;
    private AndroidModule_ProvidesTelephonyManagerFactory providesTelephonyManagerProvider;
    private Provider<ThemingUpdateUtil> providesThemingUpdateUtilProvider;
    private Provider<TranscriptLatencyMetricHelper> providesTranscriptUpdateLatencyMetricHelperProvider;
    private Provider<UnreadMessageCounter> providesUnreadMessageCounterProvider;
    private Provider<VoxUtils> providesVoxUtilsProvider;
    private RealTimeTextEnablementAuthority_Factory realTimeTextEnablementAuthorityProvider;
    private Provider<RingTonePlaybackAuthority> ringTonePlaybackAuthorityProvider;
    private SipModule sipModule;
    private ValidBeginCallPayloadHandler_Factory validBeginCallPayloadHandlerProvider;
    private Provider<VideoStateController> videoStateControllerProvider;
    private Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private AlexaModule alexaModule;
        private AndroidModule androidModule;
        private ApplicationModule applicationModule;
        private AudioModule audioModule;
        private CloudDriveModule cloudDriveModule;
        private CommsCallingModule commsCallingModule;
        private LibraryModule libraryModule;
        private SipModule sipModule;
        private TelecomModule telecomModule;

        private Builder() {
        }

        public Builder alexaModule(AlexaModule alexaModule) {
            this.alexaModule = (AlexaModule) Preconditions.checkNotNull(alexaModule);
            return this;
        }

        public Builder androidModule(AndroidModule androidModule) {
            this.androidModule = (AndroidModule) Preconditions.checkNotNull(androidModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public Builder audioModule(AudioModule audioModule) {
            this.audioModule = (AudioModule) Preconditions.checkNotNull(audioModule);
            return this;
        }

        public CommsComponent build() {
            if (this.libraryModule == null) {
                this.libraryModule = new LibraryModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.sipModule == null) {
                this.sipModule = new SipModule();
            }
            if (this.alexaModule == null) {
                this.alexaModule = new AlexaModule();
            }
            if (this.androidModule == null) {
                this.androidModule = new AndroidModule();
            }
            if (this.telecomModule == null) {
                this.telecomModule = new TelecomModule();
            }
            if (this.cloudDriveModule == null) {
                this.cloudDriveModule = new CloudDriveModule();
            }
            if (this.commsCallingModule == null) {
                this.commsCallingModule = new CommsCallingModule();
            }
            if (this.audioModule == null) {
                this.audioModule = new AudioModule();
            }
            return new DaggerCommsComponent(this);
        }

        public Builder cloudDriveModule(CloudDriveModule cloudDriveModule) {
            this.cloudDriveModule = (CloudDriveModule) Preconditions.checkNotNull(cloudDriveModule);
            return this;
        }

        public Builder commsCallingModule(CommsCallingModule commsCallingModule) {
            this.commsCallingModule = (CommsCallingModule) Preconditions.checkNotNull(commsCallingModule);
            return this;
        }

        public Builder libraryModule(LibraryModule libraryModule) {
            this.libraryModule = (LibraryModule) Preconditions.checkNotNull(libraryModule);
            return this;
        }

        public Builder sipModule(SipModule sipModule) {
            this.sipModule = (SipModule) Preconditions.checkNotNull(sipModule);
            return this;
        }

        public Builder telecomModule(TelecomModule telecomModule) {
            this.telecomModule = (TelecomModule) Preconditions.checkNotNull(telecomModule);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
        }
    }

    public static Builder builder() {
        return new Builder(null);
    }

    private BaseCallingPresenter getBaseCallingPresenter() {
        return new BaseCallingPresenter(getDriveModeSharedPreferencesUseCase());
    }

    private CallInitiationOrchestrator getCallInitiationOrchestrator() {
        return new CallInitiationOrchestrator(SipModule_ProvidesCallPayloadValidatorFactory.proxyProvidesCallPayloadValidator(this.sipModule), this.providesCommsIdentityManagerProvider.mo10268get(), new BeginCallMapper(), getValidBeginCallPayloadHandler(), this.providesCommsAlexaServicesConnectionProvider.mo10268get(), this.providesCommsAlexaServicesConnectionListenerProvider.mo10268get(), this.callContextProvider.mo10268get(), this.providesCapabilitiesManagerProvider.mo10268get());
    }

    private CommsAudioInteractionScheduler getCommsAudioInteractionScheduler() {
        return new CommsAudioInteractionScheduler(this.providesCommsAudioInteractionProvider.mo10268get(), this.providesCallManagerProvider.mo10268get(), this.providesCapabilitiesManagerProvider.mo10268get());
    }

    private CommsSharedPreferences getCommsSharedPreferences() {
        return new CommsSharedPreferences(this.providesContextProvider.mo10268get());
    }

    private ConnectivityManager getConnectivityManager() {
        return AndroidModule_ProvidesConnectivityManagerFactory.proxyProvidesConnectivityManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    private MAPAuthenticatedURLConnectionFactory getMAPAuthenticatedURLConnectionFactory() {
        return CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory.proxyProvideMAPAuthenticatedURLConnectionFactory(this.cloudDriveModule, this.providesContextProvider.mo10268get(), this.providesIdentityServiceProvider.mo10268get(), this.providesEventBusProvider.mo10268get());
    }

    private NotificationManager getNotificationManager() {
        return AndroidModule_ProvidesNotificationManagerFactory.proxyProvidesNotificationManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    private RealTimeTextEnablementAuthority getRealTimeTextEnablementAuthority() {
        return new RealTimeTextEnablementAuthority(this.providesCapabilitiesManagerProvider.mo10268get(), this.providesSipClientStateProvider.mo10268get());
    }

    private TelecomCallAudioManager getTelecomCallAudioManager() {
        return new TelecomCallAudioManager(this.providesTelecomBridgeProvider.mo10268get(), getAudioManager());
    }

    private ValidBeginCallPayloadHandler getValidBeginCallPayloadHandler() {
        return new ValidBeginCallPayloadHandler(this.providesContextProvider.mo10268get(), this.providesCommsIdentityManagerProvider.mo10268get(), this.providesSipClientStateProvider.mo10268get(), this.callInitiationAuthorityProvider.mo10268get(), this.callContextProvider.mo10268get());
    }

    private VipCallingHandler getVipCallingHandler() {
        return new VipCallingHandler(this.providesAlexaInterfaceProvider.mo10268get(), this.providesSipClientStateProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.providesAccessoriesHardwareIntentHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesAccessoriesHardwareIntentHandlerFactory.create(builder.libraryModule));
        this.providesContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesContextFactory.create(builder.applicationModule));
        this.providesMuffinAlexaServicesConnectionProvider = DoubleCheck.provider(LibraryModule_ProvidesMuffinAlexaServicesConnectionFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesFeatureFlagManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesFeatureFlagManagerFactory.create(builder.libraryModule));
        this.providesCapabilitiesManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCapabilitiesManagerFactory.create(builder.libraryModule, this.providesFeatureFlagManagerProvider, this.providesContextProvider));
        this.providesCommsAudioInteractionProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsAudioInteractionFactory.create(builder.libraryModule));
        this.providesSipClientStateProvider = DoubleCheck.provider(SipModule_ProvidesSipClientStateFactory.create(builder.sipModule));
        this.providesApplicationManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesApplicationManagerFactory.create(builder.applicationModule));
        this.providesAlexaModeSwitchHelperProvider = DoubleCheck.provider(AlexaModule_ProvidesAlexaModeSwitchHelperFactory.create(builder.alexaModule, this.providesContextProvider));
        this.providesCallPayloadValidatorProvider = SipModule_ProvidesCallPayloadValidatorFactory.create(builder.sipModule);
        this.providesPreviousSipClientStateProvider = DoubleCheck.provider(SipModule_ProvidesPreviousSipClientStateFactory.create(builder.sipModule));
        this.providesCallManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCallManagerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesSipClientStateProvider, this.providesApplicationManagerProvider, this.providesAlexaModeSwitchHelperProvider, this.providesCallPayloadValidatorProvider, this.providesPreviousSipClientStateProvider));
        this.commsAudioInteractionSchedulerProvider = CommsAudioInteractionScheduler_Factory.create(this.providesCommsAudioInteractionProvider, this.providesCallManagerProvider, this.providesCapabilitiesManagerProvider);
        this.providesCommsEventSenderProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsEventSenderFactory.create(builder.libraryModule, this.providesCommsAudioInteractionProvider, this.commsAudioInteractionSchedulerProvider));
        this.providesCommsAlexaServicesConnectionListenerProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsAlexaServicesConnectionListenerFactory.create(builder.libraryModule, this.providesMuffinAlexaServicesConnectionProvider, this.providesContextProvider));
        this.providesPCCContextProvider = DoubleCheck.provider(LibraryModule_ProvidesPCCContextProviderFactory.create(builder.libraryModule));
        this.providesPCCConnectionEnpointHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesPCCConnectionEnpointHandlerFactory.create(builder.libraryModule, this.providesCapabilitiesManagerProvider, this.providesCallManagerProvider));
        this.providesPCCUnsentEventsManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesPCCUnsentEventsManagerFactory.create(builder.libraryModule, this.providesCommsEventSenderProvider, this.providesMuffinAlexaServicesConnectionProvider, this.providesPCCConnectionEnpointHandlerProvider));
        this.provideNoPermissionHandlerProvider = DoubleCheck.provider(LibraryModule_ProvideNoPermissionHandlerFactory.create(builder.libraryModule, this.providesMuffinAlexaServicesConnectionProvider, this.providesCapabilitiesManagerProvider, this.providesCommsEventSenderProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.providesPCCContextProvider, this.providesContextProvider, this.providesPCCUnsentEventsManagerProvider, this.providesPCCConnectionEnpointHandlerProvider));
        this.providesTelecomManagerProvider = AndroidModule_ProvidesTelecomManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesAcceptNativeCallHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesAcceptNativeCallHandlerFactory.create(builder.libraryModule, this.provideNoPermissionHandlerProvider, this.providesPCCContextProvider, this.providesTelecomManagerProvider, this.providesContextProvider));
        this.providesCommsAlexaServicesConnectionProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsAlexaServicesConnectionFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesStageProvider = DoubleCheck.provider(LibraryModule_ProvidesStageFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesArcusConfigProvider = DoubleCheck.provider(LibraryModule_ProvidesArcusConfigFactory.create(builder.libraryModule, this.providesContextProvider, this.providesStageProvider));
        this.providesAudioContentManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesAudioContentManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesMediaContentManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesMediaContentManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesAudioManagerProvider = AndroidModule_ProvidesAudioManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesAudioPlayerProvider = DoubleCheck.provider(LibraryModule_ProvidesAudioPlayerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesAudioManagerProvider));
        this.providesAudioRecorderProvider = DoubleCheck.provider(LibraryModule_ProvidesAudioRecorderFactory.create(builder.libraryModule, this.providesAudioManagerProvider));
        this.providesAudioStateManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesAudioStateManagerFactory.create(builder.libraryModule));
        this.providesCallHistoryHelperProvider = DoubleCheck.provider(LibraryModule_ProvidesCallHistoryHelperFactory.create(builder.libraryModule));
        this.providesDeviceCallingServiceProvider = DoubleCheck.provider(SipModule_ProvidesDeviceCallingServiceFactory.create(builder.sipModule));
        this.providesEventTracerFactoryProvider = DoubleCheck.provider(SipModule_ProvidesEventTracerFactoryFactory.create(builder.sipModule, this.providesContextProvider));
        this.realTimeTextEnablementAuthorityProvider = RealTimeTextEnablementAuthority_Factory.create(this.providesCapabilitiesManagerProvider, this.providesSipClientStateProvider);
        this.providesCommandProcessorProvider = DoubleCheck.provider(SipModule_ProvidesCommandProcessorFactory.create(builder.sipModule, this.providesDeviceCallingServiceProvider, this.providesEventTracerFactoryProvider, this.providesSipClientStateProvider, this.providesCallManagerProvider, this.providesCapabilitiesManagerProvider, this.realTimeTextEnablementAuthorityProvider));
        this.providesCommsAccessorySessionListenerProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsAccessorySessionListenerFactory.create(builder.libraryModule, this.providesMuffinAlexaServicesConnectionProvider, this.providesContextProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.providesCapabilitiesManagerProvider, this.providesCallManagerProvider, this.providesCommsEventSenderProvider, this.providesPCCConnectionEnpointHandlerProvider));
        this.providesConnectivityManagerProvider = AndroidModule_ProvidesConnectivityManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesPackageManagerProvider = AndroidModule_ProvidesPackageManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesTelephonyManagerProvider = AndroidModule_ProvidesTelephonyManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesCommsConnectivityMonitorProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsConnectivityMonitorFactory.create(builder.libraryModule, this.providesConnectivityManagerProvider, this.providesPackageManagerProvider, this.providesTelephonyManagerProvider));
        this.providesCommsDisposableManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsDisposableManagerFactory.create(builder.libraryModule));
        this.providesLegacyCommsIdentityStoreProvider = DoubleCheck.provider(LibraryModule_ProvidesLegacyCommsIdentityStoreFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesCommsIdentityStoreV2Provider = DoubleCheck.provider(LibraryModule_ProvidesCommsIdentityStoreV2Factory.create(builder.libraryModule, this.providesContextProvider));
        this.providesCommsInternalProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsInternalFactory.create(builder.libraryModule));
        this.providesNotificationManagerProvider = AndroidModule_ProvidesNotificationManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesCommsNotificationManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsNotificationManagerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesNotificationManagerProvider, this.providesSipClientStateProvider));
        this.providesContactsOperationsManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesContactsOperationsManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesDevicesSourceProvider = DoubleCheck.provider(LibraryModule_ProvidesDevicesSourceFactory.create(builder.libraryModule));
        this.providesDeviceStateManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesDeviceStateManagerFactory.create(builder.libraryModule));
        this.providesSecuredSharedPreferenceProvider = LibraryModule_ProvidesSecuredSharedPreferenceFactory.create(builder.libraryModule, this.providesContextProvider);
        this.providesDeviceUtilsProvider = DoubleCheck.provider(LibraryModule_ProvidesDeviceUtilsFactory.create(builder.libraryModule, this.providesContextProvider, this.providesSecuredSharedPreferenceProvider));
        this.providesEndNativeCallHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesEndNativeCallHandlerFactory.create(builder.libraryModule, this.provideNoPermissionHandlerProvider, this.providesPCCContextProvider, this.providesTelecomManagerProvider, this.providesContextProvider));
        this.providesIdentityServiceProvider = DoubleCheck.provider(ApplicationModule_ProvidesIdentityServiceFactory.create(builder.applicationModule));
        this.providesMakeNativeCallHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesMakeNativeCallHandlerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesMessagingControllerContextProvider = DoubleCheck.provider(LibraryModule_ProvidesMessagingControllerContextProviderFactory.create(builder.libraryModule));
        this.providesMessagingControllerManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesMessagingControllerManagerFactory.create(builder.libraryModule, this.providesCapabilitiesManagerProvider, this.providesMuffinAlexaServicesConnectionProvider, this.providesMessagingControllerContextProvider));
        this.providesMetricsManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesMetricsManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesCommsIdentityManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsIdentityManagerFactory.create(builder.libraryModule));
        this.providesMetricsServiceProvider = DoubleCheck.provider(LibraryModule_ProvidesMetricsServiceFactory.create(builder.libraryModule, this.providesCommsIdentityManagerProvider, this.providesApplicationManagerProvider, this.providesDeviceUtilsProvider, this.providesCommsInternalProvider, this.providesContextProvider));
        this.provideDefaultNoCallPermissionHandlerProvider = DoubleCheck.provider(LibraryModule_ProvideDefaultNoCallPermissionHandlerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.provideDriveModeNoPermissionHandlerProvider = DoubleCheck.provider(LibraryModule_ProvideDriveModeNoPermissionHandlerFactory.create(builder.libraryModule, this.providesMuffinAlexaServicesConnectionProvider, this.providesCapabilitiesManagerProvider, this.providesCommsEventSenderProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.providesPCCContextProvider, this.providesContextProvider, this.providesPCCUnsentEventsManagerProvider, this.providesPCCConnectionEnpointHandlerProvider));
        this.provideCallStateListenerProvider = DoubleCheck.provider(LibraryModule_ProvideCallStateListenerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCallManagerProvider, this.providesCommsEventSenderProvider, this.providesMuffinAlexaServicesConnectionProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.providesCapabilitiesManagerProvider, this.providesPCCContextProvider, this.providesPCCUnsentEventsManagerProvider, this.providesPCCConnectionEnpointHandlerProvider));
        this.providesPhoneCallControllerManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesPhoneCallControllerManagerFactory.create(builder.libraryModule, this.providesMuffinAlexaServicesConnectionProvider, this.providesTelephonyManagerProvider, this.provideCallStateListenerProvider, this.providesContextProvider, this.providesPCCContextProvider, this.providesPCCUnsentEventsManagerProvider, this.commsAudioInteractionSchedulerProvider));
        this.providesEventBusProvider = DoubleCheck.provider(LibraryModule_ProvidesEventBusFactory.create(builder.libraryModule));
        this.providesProvisioningManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesProvisioningManagerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesEventBusProvider, this.providesCapabilitiesManagerProvider));
        this.providesPushNotificationManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesPushNotificationManagerFactory.create(builder.libraryModule, this.providesContextProvider, this.providesSecuredSharedPreferenceProvider, this.providesCommsIdentityManagerProvider, this.providesAlexaModeSwitchHelperProvider, this.providesCapabilitiesManagerProvider, this.providesDeviceUtilsProvider));
        this.providesOfflinePushNotificationRepositoryProvider = DoubleCheck.provider(LibraryModule_ProvidesOfflinePushNotificationRepositoryFactory.create(builder.libraryModule));
        this.providesMigrationCommsIdentityStoreProvider = DoubleCheck.provider(LibraryModule_ProvidesMigrationCommsIdentityStoreFactory.create(builder.libraryModule));
        this.providesOOBEPersonUtilProvider = DoubleCheck.provider(LibraryModule_ProvidesOOBEPersonUtilFactory.create(builder.libraryModule));
        this.providesEncryptionUtilsProvider = DoubleCheck.provider(ApplicationModule_ProvidesEncryptionUtilsFactory.create(builder.applicationModule, this.providesContextProvider));
        this.providesCommsDeviceSupportProvider = DoubleCheck.provider(ApplicationModule_ProvidesCommsDeviceSupportFactory.create(builder.applicationModule, this.providesEncryptionUtilsProvider));
        this.providesDeviceNameTemplateProvider = DoubleCheck.provider(ApplicationModule_ProvidesDeviceNameTemplateFactory.create(builder.applicationModule));
        this.providesConversationServiceProvider = DoubleCheck.provider(ApplicationModule_ProvidesConversationServiceFactory.create(builder.applicationModule, this.providesIdentityServiceProvider, this.providesDeviceNameTemplateProvider));
        this.providesCommsManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesCommsManagerFactory.create(builder.applicationModule, this.providesContextProvider));
        this.providesCommsDynamicFeatureServiceProvider = DoubleCheck.provider(ApplicationModule_ProvidesCommsDynamicFeatureServiceFactory.create(builder.applicationModule, this.providesCommsManagerProvider));
        this.providesDeviceInformationProvider = DoubleCheck.provider(ApplicationModule_ProvidesDeviceInformationFactory.create(builder.applicationModule));
        this.providesFeatureFilterProvider = DoubleCheck.provider(ApplicationModule_ProvidesFeatureFilterFactory.create(builder.applicationModule, this.providesCommsDeviceSupportProvider, this.providesDeviceInformationProvider));
        this.providesAlarmManagerProvider = AndroidModule_ProvidesAlarmManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesActivityManagerProvider = AndroidModule_ProvidesActivityManagerFactory.create(builder.androidModule, this.providesContextProvider);
        this.providesCommsActivityMonitorProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsActivityMonitorFactory.create(builder.libraryModule, this.providesContextProvider, this.providesAlarmManagerProvider, this.providesActivityManagerProvider));
        this.providesPhoneAccountHandleProvider = DoubleCheck.provider(TelecomModule_ProvidesPhoneAccountHandleFactory.create(builder.telecomModule, this.providesContextProvider));
        this.providesCallIdToTelecomConnectionProvider = DoubleCheck.provider(TelecomModule_ProvidesCallIdToTelecomConnectionFactory.create(builder.telecomModule));
        this.providesTelecomCallIdsProvider = DoubleCheck.provider(TelecomModule_ProvidesTelecomCallIdsFactory.create(builder.telecomModule));
        this.providesTelecomLockProvider = DoubleCheck.provider(TelecomModule_ProvidesTelecomLockFactory.create(builder.telecomModule));
        this.providesTelecomBridgeProvider = DoubleCheck.provider(TelecomModule_ProvidesTelecomBridgeFactory.create(builder.telecomModule, this.providesTelecomManagerProvider, this.providesPhoneAccountHandleProvider, this.providesCallIdToTelecomConnectionProvider, this.providesTelecomCallIdsProvider, this.providesTelecomLockProvider));
        this.providesPhoneAccountProvider = DoubleCheck.provider(TelecomModule_ProvidesPhoneAccountFactory.create(builder.telecomModule, this.providesPhoneAccountHandleProvider));
        this.providesBluetoothHeadsetHelperProvider = DoubleCheck.provider(SipModule_ProvidesBluetoothHeadsetHelperFactory.create(builder.sipModule, this.providesContextProvider, this.providesSipClientStateProvider));
        this.provideOkHttpClientWrapperProvider = CloudDriveModule_ProvideOkHttpClientWrapperFactory.create(builder.cloudDriveModule);
        this.provideClientConfigurationProvider = CloudDriveModule_ProvideClientConfigurationFactory.create(builder.cloudDriveModule, this.providesArcusConfigProvider, this.provideOkHttpClientWrapperProvider);
        this.provideMAPAuthenticatedURLConnectionFactoryProvider = CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory.create(builder.cloudDriveModule, this.providesContextProvider, this.providesIdentityServiceProvider, this.providesEventBusProvider);
        this.provideEndpointsCacheProvider = CloudDriveModule_ProvideEndpointsCacheFactory.create(builder.cloudDriveModule, this.providesContextProvider, this.providesIdentityServiceProvider, this.providesEventBusProvider);
        this.provideRequestAuthenticatorProvider = CloudDriveModule_ProvideRequestAuthenticatorFactory.create(builder.cloudDriveModule, this.providesApplicationManagerProvider);
        this.provideAccountConfigurationProvider = CloudDriveModule_ProvideAccountConfigurationFactory.create(builder.cloudDriveModule, this.provideMAPAuthenticatedURLConnectionFactoryProvider, this.provideEndpointsCacheProvider, this.provideRequestAuthenticatorProvider);
        this.providesFileTransmitterProvider = DoubleCheck.provider(LibraryModule_ProvidesFileTransmitterFactory.create(builder.libraryModule, this.providesContextProvider, this.providesIdentityServiceProvider, this.providesCommsIdentityManagerProvider, this.provideClientConfigurationProvider, this.provideMAPAuthenticatedURLConnectionFactoryProvider, this.provideEndpointsCacheProvider, this.provideAccountConfigurationProvider, this.providesEventBusProvider));
        this.providesOobeServiceProvider = DoubleCheck.provider(LibraryModule_ProvidesOobeServiceFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCommsIdentityManagerProvider, this.providesCommsDeviceSupportProvider, this.providesConversationServiceProvider, this.providesCommsManagerProvider, this.providesCapabilitiesManagerProvider, this.providesEventBusProvider));
        this.provideConversationMessagingReceiverProvider = DoubleCheck.provider(LibraryModule_ProvideConversationMessagingReceiverFactory.create(builder.libraryModule, this.providesCommsManagerProvider, this.providesEventBusProvider));
        this.providesConversationMessageTrackerProvider = DoubleCheck.provider(LibraryModule_ProvidesConversationMessageTrackerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesUnreadMessageCounterProvider = DoubleCheck.provider(LibraryModule_ProvidesUnreadMessageCounterFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesSMSFetchManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesSMSFetchManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.eventSenderUseCaseProvider = EventSenderUseCase_Factory.create(this.providesCommsAlexaServicesConnectionProvider);
    }

    private void initialize2(Builder builder) {
        this.providesBuildVersionProvider = DoubleCheck.provider(LibraryModule_ProvidesBuildVersionFactory.create(builder.libraryModule));
        this.voicePermissionsAuthorityProvider = DoubleCheck.provider(VoicePermissionsAuthority_Factory.create(this.providesContextProvider, this.providesBuildVersionProvider));
        this.providesEventSenderProvider = AlexaModule_ProvidesEventSenderFactory.create(builder.alexaModule, this.eventSenderUseCaseProvider, this.voicePermissionsAuthorityProvider, this.providesMetricsServiceProvider);
        this.providesCommsDriveModeCardProvider = DoubleCheck.provider(LibraryModule_ProvidesCommsDriveModeCardProviderFactory.create(builder.libraryModule, this.providesContextProvider, this.providesEventSenderProvider, this.providesCommsAlexaServicesConnectionProvider, this.providesCapabilitiesManagerProvider));
        this.providesContentSharingServiceProvider = DoubleCheck.provider(LibraryModule_ProvidesContentSharingServiceFactory.create(builder.libraryModule, this.providesCommsIdentityManagerProvider, this.providesCapabilitiesManagerProvider, this.providesIdentityServiceProvider, this.providesContextProvider));
        this.providesShareSheetActivityProvider = DoubleCheck.provider(LibraryModule_ProvidesShareSheetActivityFactory.create(builder.libraryModule));
        this.providesDriveModeServiceProvider = AlexaModule_ProvidesDriveModeServiceFactory.create(builder.alexaModule);
        this.commsSharedPreferencesProvider = CommsSharedPreferences_Factory.create(this.providesContextProvider);
        this.driveModeSharedPreferencesUseCaseProvider = DriveModeSharedPreferencesUseCase_Factory.create(this.commsSharedPreferencesProvider);
        this.driveModeEventHandlerProvider = DriveModeEventHandler_Factory.create(this.driveModeSharedPreferencesUseCaseProvider, this.providesMetricsServiceProvider);
        this.providesDeviceMetadataStoreRegistrarProvider = DoubleCheck.provider(LibraryModule_ProvidesDeviceMetadataStoreRegistrarFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCommsIdentityManagerProvider, this.providesCapabilitiesManagerProvider));
        this.providesProfileSelectionServiceProvider = DoubleCheck.provider(LibraryModule_ProvidesProfileSelectionServiceFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCapabilitiesManagerProvider, this.providesCommsIdentityManagerProvider, this.providesDeviceMetadataStoreRegistrarProvider, this.providesOOBEPersonUtilProvider));
        this.providesAlexaCommsServiceProvider = DoubleCheck.provider(ApplicationModule_ProvidesAlexaCommsServiceFactory.create(builder.applicationModule, this.providesIdentityServiceProvider, this.providesEventBusProvider, this.providesOobeServiceProvider, this.provideConversationMessagingReceiverProvider, this.providesContentSharingServiceProvider, this.providesConversationServiceProvider, this.providesCommsDeviceSupportProvider, this.providesCommsDynamicFeatureServiceProvider, this.providesCommsManagerProvider, this.providesFeatureFilterProvider, this.providesAccessoriesHardwareIntentHandlerProvider, this.providesAudioContentManagerProvider, this.providesDriveModeServiceProvider, this.driveModeEventHandlerProvider, this.providesCommsDriveModeCardProvider, this.providesPhoneCallControllerManagerProvider, MainActivityLoader_Factory.create(), this.providesProfileSelectionServiceProvider));
        this.callInitiationAuthorityProvider = DoubleCheck.provider(CallInitiationAuthority_Factory.create(this.providesSipClientStateProvider, this.providesCapabilitiesManagerProvider));
        this.callContextProvider = DoubleCheck.provider(CallContext_Factory.create());
        this.providesVoxUtilsProvider = DoubleCheck.provider(AlexaModule_ProvidesVoxUtilsFactory.create(builder.alexaModule));
        this.nameChangeObservableProvider = DoubleCheck.provider(NameChangeObservable_Factory.create());
        this.enhancedProcessingStateObservableProvider = DoubleCheck.provider(EnhancedProcessingStateObservable_Factory.create());
        this.validBeginCallPayloadHandlerProvider = ValidBeginCallPayloadHandler_Factory.create(this.providesContextProvider, this.providesCommsIdentityManagerProvider, this.providesSipClientStateProvider, this.callInitiationAuthorityProvider, this.callContextProvider);
        this.callInitiationOrchestratorProvider = CallInitiationOrchestrator_Factory.create(this.providesCallPayloadValidatorProvider, this.providesCommsIdentityManagerProvider, BeginCallMapper_Factory.create(), this.validBeginCallPayloadHandlerProvider, this.providesCommsAlexaServicesConnectionProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.callContextProvider, this.providesCapabilitiesManagerProvider);
        this.providesCallingFacadeProvider = SipModule_ProvidesCallingFacadeFactory.create(builder.sipModule, this.callInitiationOrchestratorProvider);
        this.initiationLogicFactoryProvider = InitiationLogicFactory_Factory.create(this.providesCapabilitiesManagerProvider, this.providesCallingFacadeProvider, this.providesCommsIdentityManagerProvider);
        this.callingAPIPopulatorProvider = CallingAPIPopulator_Factory.create(this.callContextProvider);
        this.callingAPIMonitorProvider = CallingAPIMonitor_Factory.create(this.callingAPIPopulatorProvider);
        this.commsDirectiveHandlerProvider = CommsDirectiveHandler_Factory.create(this.providesContextProvider, this.providesCommsIdentityManagerProvider, this.providesCallManagerProvider, this.providesVoxUtilsProvider, this.providesApplicationManagerProvider, this.providesCallingFacadeProvider, this.providesCapabilitiesManagerProvider, this.providesSipClientStateProvider, this.callContextProvider, this.nameChangeObservableProvider, this.enhancedProcessingStateObservableProvider);
        this.providesCallingAPIProvider = DoubleCheck.provider(CommsCallingModule_ProvidesCallingAPIFactory.create(builder.commsCallingModule, this.initiationLogicFactoryProvider, this.providesContextProvider, this.callingAPIMonitorProvider, this.callingAPIPopulatorProvider, this.commsDirectiveHandlerProvider, this.providesCommsIdentityManagerProvider, this.providesPushNotificationManagerProvider, this.providesCommsAccessorySessionListenerProvider, this.providesMakeNativeCallHandlerProvider, this.provideDriveModeNoPermissionHandlerProvider, this.providesEventBusProvider));
        this.providesAlexaAudioPlayerProvider = DoubleCheck.provider(LibraryModule_ProvidesAlexaAudioPlayerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesAlexaInterfaceProvider = DoubleCheck.provider(AlexaModule_ProvidesAlexaInterfaceFactory.create(builder.alexaModule, this.providesContextProvider));
        this.providesCallTimerManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesCallTimerManagerFactory.create(builder.libraryModule, this.providesContextProvider));
        this.providesTelecomCallAudioRouteObservableProvider = DoubleCheck.provider(TelecomModule_ProvidesTelecomCallAudioRouteObservableFactory.create(builder.telecomModule));
        this.providesActivitiesManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesActivitiesManagerFactory.create(builder.libraryModule));
        this.callActionsDispatcherProvider = DoubleCheck.provider(CallActionsDispatcher_Factory.create(this.providesContextProvider, this.providesSipClientStateProvider, this.providesCallManagerProvider, this.callInitiationAuthorityProvider, this.providesCapabilitiesManagerProvider));
        this.providesAudioStateObservableProvider = DoubleCheck.provider(AudioModule_ProvidesAudioStateObservableFactory.create(builder.audioModule));
        this.providesAudioStateControllerProvider = DoubleCheck.provider(AudioModule_ProvidesAudioStateControllerFactory.create(builder.audioModule, this.providesAudioStateObservableProvider, this.providesAudioManagerProvider, this.providesSipClientStateProvider));
        this.providesMicStateControllerProvider = DoubleCheck.provider(AudioModule_ProvidesMicStateControllerFactory.create(builder.audioModule, this.providesAudioManagerProvider, this.callContextProvider));
        this.videoStateControllerProvider = DoubleCheck.provider(VideoStateController_Factory.create(this.providesSipClientStateProvider));
        this.callMediaControlFacadeProvider = DoubleCheck.provider(CallMediaControlFacade_Factory.create(this.providesAudioStateControllerProvider, this.providesMicStateControllerProvider, this.videoStateControllerProvider, this.providesAudioManagerProvider));
        this.pipVisibilityObservableProvider = DoubleCheck.provider(PipVisibilityObservable_Factory.create());
        this.effectsStateProvider = DoubleCheck.provider(EffectsState_Factory.create());
        this.providesPCCDirectiveHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesPCCDirectiveHandlerFactory.create(builder.libraryModule, this.providesCapabilitiesManagerProvider, this.providesPCCContextProvider, this.providesTelecomManagerProvider, this.providesMuffinAlexaServicesConnectionProvider, this.providesCommsEventSenderProvider, this.providesCommsAlexaServicesConnectionListenerProvider, this.providesMakeNativeCallHandlerProvider, this.providesAcceptNativeCallHandlerProvider, this.providesEndNativeCallHandlerProvider, this.provideNoPermissionHandlerProvider, this.providesContextProvider));
        this.providesCallUIHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesCallUIHandlerFactory.create(builder.libraryModule, this.providesActivitiesManagerProvider, this.callInitiationAuthorityProvider));
        this.providesInboundAnnouncementsCoordinatorProvider = DoubleCheck.provider(LibraryModule_ProvidesInboundAnnouncementsCoordinatorFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCapabilitiesManagerProvider, this.providesCommsAccessorySessionListenerProvider, this.providesCallManagerProvider, this.providesAudioRecorderProvider, this.providesMetricsServiceProvider, this.providesCommsIdentityManagerProvider));
        this.providesThemingUpdateUtilProvider = DoubleCheck.provider(LibraryModule_ProvidesThemingUpdateUtilFactory.create(builder.libraryModule));
        this.providesAppVersionProvider = DoubleCheck.provider(ApplicationModule_ProvidesAppVersionFactory.create(builder.applicationModule, this.providesContextProvider));
        this.providesSipHeadersProvider = DoubleCheck.provider(SipModule_ProvidesSipHeadersFactory.create(builder.sipModule, this.providesAppVersionProvider));
        this.providesDeviceCallingServiceParamsProvider = DoubleCheck.provider(SipModule_ProvidesDeviceCallingServiceParamsFactory.create(builder.sipModule, this.providesContextProvider, this.providesSipHeadersProvider));
        this.ringTonePlaybackAuthorityProvider = DoubleCheck.provider(RingTonePlaybackAuthority_Factory.create(this.providesContextProvider));
        this.providesCallMetricsFactoryProvider = DoubleCheck.provider(LibraryModule_ProvidesCallMetricsFactoryFactory.create(builder.libraryModule, this.providesCallHistoryHelperProvider, this.providesApplicationManagerProvider, this.providesContextProvider));
        this.providesTranscriptUpdateLatencyMetricHelperProvider = DoubleCheck.provider(LibraryModule_ProvidesTranscriptUpdateLatencyMetricHelperFactory.create(builder.libraryModule));
        this.providesNotificationLatencyMetricHelperProvider = DoubleCheck.provider(LibraryModule_ProvidesNotificationLatencyMetricHelperFactory.create(builder.libraryModule));
        this.providesInCallCommandModelFactoryProvider = DoubleCheck.provider(LibraryModule_ProvidesInCallCommandModelFactoryFactory.create(builder.libraryModule, this.providesContextProvider, this.providesCommsManagerProvider));
        this.providesA2AConnectionEnpointHandlerProvider = DoubleCheck.provider(LibraryModule_ProvidesA2AConnectionEnpointHandlerFactory.create(builder.libraryModule, this.providesCallManagerProvider));
        this.providesA2AUnsentEventsManagerProvider = DoubleCheck.provider(LibraryModule_ProvidesA2AUnsentEventsManagerFactory.create(builder.libraryModule));
        this.callDowngradedObservableProvider = DoubleCheck.provider(CallDowngradedObservable_Factory.create());
    }

    private AccessoriesHardwareIntentHandler injectAccessoriesHardwareIntentHandler(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler) {
        AccessoriesHardwareIntentHandler_MembersInjector.injectMContext(accessoriesHardwareIntentHandler, this.providesContextProvider.mo10268get());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMTelephonyManager(accessoriesHardwareIntentHandler, getTelephonyManager());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMSipClientState(accessoriesHardwareIntentHandler, this.providesSipClientStateProvider.mo10268get());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMCallManager(accessoriesHardwareIntentHandler, this.providesCallManagerProvider.mo10268get());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMPccContextProvider(accessoriesHardwareIntentHandler, this.providesPCCContextProvider.mo10268get());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMAlexaAudioPlayer(accessoriesHardwareIntentHandler, this.providesAlexaAudioPlayerProvider.mo10268get());
        AccessoriesHardwareIntentHandler_MembersInjector.injectMVipCallingHandler(accessoriesHardwareIntentHandler, getVipCallingHandler());
        return accessoriesHardwareIntentHandler;
    }

    private ActiveCallFragment injectActiveCallFragment(ActiveCallFragment activeCallFragment) {
        ActiveCallFragment_MembersInjector.injectCallTimerManager(activeCallFragment, this.providesCallTimerManagerProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectSipClientState(activeCallFragment, this.providesSipClientStateProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectCallHistoryHelper(activeCallFragment, this.providesCallHistoryHelperProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectCallManager(activeCallFragment, this.providesCallManagerProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectCapabilitiesManager(activeCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectTelecomBridge(activeCallFragment, this.providesTelecomBridgeProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectTelecomCallAudioRouteObservable(activeCallFragment, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectMPresenter(activeCallFragment, getBaseCallingPresenter());
        ActiveCallFragment_MembersInjector.injectRealTimeTextEnablementAuthority(activeCallFragment, getRealTimeTextEnablementAuthority());
        ActiveCallFragment_MembersInjector.injectCommsIdentityManager(activeCallFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        ActiveCallFragment_MembersInjector.injectMDriveModeSharedPreferencesUseCase(activeCallFragment, getDriveModeSharedPreferencesUseCase());
        return activeCallFragment;
    }

    private ActiveEnhancedProcessingAudioCallFragment injectActiveEnhancedProcessingAudioCallFragment(ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment) {
        ActiveEnhancedProcessingAudioCallFragment_MembersInjector.injectCapabilitiesManager(activeEnhancedProcessingAudioCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        return activeEnhancedProcessingAudioCallFragment;
    }

    private ActiveEnhancedProcessingVideoCallFragment injectActiveEnhancedProcessingVideoCallFragment(ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment) {
        ActiveEnhancedProcessingVideoCallFragment_MembersInjector.injectCapabilitiesManager(activeEnhancedProcessingVideoCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        return activeEnhancedProcessingVideoCallFragment;
    }

    private ActiveVideoCallView injectActiveVideoCallView(ActiveVideoCallView activeVideoCallView) {
        ActiveVideoCallView_MembersInjector.injectCallTimerManager(activeVideoCallView, this.providesCallTimerManagerProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectSipClientState(activeVideoCallView, this.providesSipClientStateProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectCallHistoryHelper(activeVideoCallView, this.providesCallHistoryHelperProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectCommandProcessor(activeVideoCallView, this.providesCommandProcessorProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectCapabilitiesManager(activeVideoCallView, this.providesCapabilitiesManagerProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectTelecomBridge(activeVideoCallView, this.providesTelecomBridgeProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectTelecomCallAudioRouteObservable(activeVideoCallView, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectRealTimeTextEnablementAuthority(activeVideoCallView, getRealTimeTextEnablementAuthority());
        ActiveVideoCallView_MembersInjector.injectCommsIdentityManager(activeVideoCallView, this.providesCommsIdentityManagerProvider.mo10268get());
        ActiveVideoCallView_MembersInjector.injectEnhancedProcessingStateObservable(activeVideoCallView, this.enhancedProcessingStateObservableProvider.mo10268get());
        return activeVideoCallView;
    }

    private AlexaCallNotificationMonitor injectAlexaCallNotificationMonitor(AlexaCallNotificationMonitor alexaCallNotificationMonitor) {
        AlexaCallNotificationMonitor_MembersInjector.injectMDeviceCallingService(alexaCallNotificationMonitor, this.providesDeviceCallingServiceProvider.mo10268get());
        AlexaCallNotificationMonitor_MembersInjector.injectMTelephonyManager(alexaCallNotificationMonitor, getTelephonyManager());
        AlexaCallNotificationMonitor_MembersInjector.injectMCallManager(alexaCallNotificationMonitor, this.providesCallManagerProvider.mo10268get());
        AlexaCallNotificationMonitor_MembersInjector.injectCapabilitiesManager(alexaCallNotificationMonitor, this.providesCapabilitiesManagerProvider.mo10268get());
        AlexaCallNotificationMonitor_MembersInjector.injectContext(alexaCallNotificationMonitor, this.providesContextProvider.mo10268get());
        AlexaCallNotificationMonitor_MembersInjector.injectMCustomPhoneStateListener(alexaCallNotificationMonitor, this.provideCallStateListenerProvider.mo10268get());
        return alexaCallNotificationMonitor;
    }

    private AudioDownloadService injectAudioDownloadService(AudioDownloadService audioDownloadService) {
        AudioDownloadService_MembersInjector.injectAudioStateManager(audioDownloadService, this.providesAudioStateManagerProvider.mo10268get());
        return audioDownloadService;
    }

    private AudioMessageSender injectAudioMessageSender(AudioMessageSender audioMessageSender) {
        AudioMessageSender_MembersInjector.injectMTranscriptLatencyMetricHelper(audioMessageSender, this.providesTranscriptUpdateLatencyMetricHelperProvider.mo10268get());
        AudioMessageSender_MembersInjector.injectAudioContentManager(audioMessageSender, this.providesAudioContentManagerProvider.mo10268get());
        AudioMessageSender_MembersInjector.injectCommsIdentityManager(audioMessageSender, this.providesCommsIdentityManagerProvider.mo10268get());
        return audioMessageSender;
    }

    private COBOWarningActivity injectCOBOWarningActivity(COBOWarningActivity cOBOWarningActivity) {
        COBOWarningActivity_MembersInjector.injectInitiationLogicFactory(cOBOWarningActivity, getInitiationLogicFactory());
        return cOBOWarningActivity;
    }

    private CVFFragment injectCVFFragment(CVFFragment cVFFragment) {
        MainOOBEFragment_MembersInjector.injectCapabilitiesManager(cVFFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        MainOOBEFragment_MembersInjector.injectThemingUpdateUtil(cVFFragment, this.providesThemingUpdateUtilProvider.mo10268get());
        CVFFragment_MembersInjector.injectApplicationManager(cVFFragment, this.providesApplicationManagerProvider.mo10268get());
        return cVFFragment;
    }

    private CallActivity injectCallActivity(CallActivity callActivity) {
        CallActivity_MembersInjector.injectMContext(callActivity, this.providesContextProvider.mo10268get());
        CallActivity_MembersInjector.injectDeviceCallingService(callActivity, this.providesDeviceCallingServiceProvider.mo10268get());
        CallActivity_MembersInjector.injectCallManager(callActivity, this.providesCallManagerProvider.mo10268get());
        CallActivity_MembersInjector.injectCallHistoryHelper(callActivity, this.providesCallHistoryHelperProvider.mo10268get());
        CallActivity_MembersInjector.injectCallTimerManager(callActivity, this.providesCallTimerManagerProvider.mo10268get());
        CallActivity_MembersInjector.injectActivitiesManager(callActivity, this.providesActivitiesManagerProvider.mo10268get());
        CallActivity_MembersInjector.injectSipClientState(callActivity, this.providesSipClientStateProvider.mo10268get());
        CallActivity_MembersInjector.injectCommandProcessor(callActivity, this.providesCommandProcessorProvider.mo10268get());
        CallActivity_MembersInjector.injectCommsNotificationManager(callActivity, this.providesCommsNotificationManagerProvider.mo10268get());
        CallActivity_MembersInjector.injectCapabilitiesManager(callActivity, this.providesCapabilitiesManagerProvider.mo10268get());
        CallActivity_MembersInjector.injectTelecomCallAudioRouteObservable(callActivity, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        CallActivity_MembersInjector.injectMAlexaAudioPlayer(callActivity, this.providesAlexaAudioPlayerProvider.mo10268get());
        CallActivity_MembersInjector.injectCallActionsDispatcher(callActivity, this.callActionsDispatcherProvider.mo10268get());
        CallActivity_MembersInjector.injectCallMediaControlFacade(callActivity, this.callMediaControlFacadeProvider.mo10268get());
        CallActivity_MembersInjector.injectAudioStateObservable(callActivity, this.providesAudioStateObservableProvider.mo10268get());
        CallActivity_MembersInjector.injectAlexaInterface(callActivity, this.providesAlexaInterfaceProvider.mo10268get());
        CallActivity_MembersInjector.injectRealTimeTextEnablementAuthority(callActivity, getRealTimeTextEnablementAuthority());
        CallActivity_MembersInjector.injectNameChangeObservable(callActivity, this.nameChangeObservableProvider.mo10268get());
        CallActivity_MembersInjector.injectTelecomCallAudioManager(callActivity, getTelecomCallAudioManager());
        CallActivity_MembersInjector.injectTelecomBridge(callActivity, this.providesTelecomBridgeProvider.mo10268get());
        CallActivity_MembersInjector.injectEnhancedProcessingStateObservable(callActivity, this.enhancedProcessingStateObservableProvider.mo10268get());
        CallActivity_MembersInjector.injectPipVisibilityObservable(callActivity, this.pipVisibilityObservableProvider.mo10268get());
        CallActivity_MembersInjector.injectEffectsState(callActivity, this.effectsStateProvider.mo10268get());
        return callActivity;
    }

    private CallHelper injectCallHelper(CallHelper callHelper) {
        CallHelper_MembersInjector.injectMContext(callHelper, this.providesContextProvider.mo10268get());
        CallHelper_MembersInjector.injectSipClientState(callHelper, this.providesSipClientStateProvider.mo10268get());
        CallHelper_MembersInjector.injectCallManager(callHelper, this.providesCallManagerProvider.mo10268get());
        CallHelper_MembersInjector.injectCommsIdentityManager(callHelper, this.providesCommsIdentityManagerProvider.mo10268get());
        CallHelper_MembersInjector.injectPccContextProvider(callHelper, this.providesPCCContextProvider.mo10268get());
        CallHelper_MembersInjector.injectCapabilitiesManager(callHelper, this.providesCapabilitiesManagerProvider.mo10268get());
        return callHelper;
    }

    private CallPermissionActivity injectCallPermissionActivity(CallPermissionActivity callPermissionActivity) {
        CallPermissionActivity_MembersInjector.injectCommsIdentityManager(callPermissionActivity, this.providesCommsIdentityManagerProvider.mo10268get());
        CallPermissionActivity_MembersInjector.injectCapabilitiesManager(callPermissionActivity, this.providesCapabilitiesManagerProvider.mo10268get());
        CallPermissionActivity_MembersInjector.injectCallManager(callPermissionActivity, this.providesCallManagerProvider.mo10268get());
        CallPermissionActivity_MembersInjector.injectCurrentSipClientState(callPermissionActivity, this.providesSipClientStateProvider.mo10268get());
        return callPermissionActivity;
    }

    private CallingFragmentFactory injectCallingFragmentFactory(CallingFragmentFactory callingFragmentFactory) {
        CallingFragmentFactory_MembersInjector.injectSipClientState(callingFragmentFactory, this.providesSipClientStateProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallActionsDispatcher(callingFragmentFactory, this.callActionsDispatcherProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallHistoryHelper(callingFragmentFactory, this.providesCallHistoryHelperProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallTimerManager(callingFragmentFactory, this.providesCallTimerManagerProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallMediaControlFacade(callingFragmentFactory, this.callMediaControlFacadeProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectAudioStateObservable(callingFragmentFactory, this.providesAudioStateObservableProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectNameChangeObservable(callingFragmentFactory, this.nameChangeObservableProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectAlexaInterface(callingFragmentFactory, this.providesAlexaInterfaceProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCapabilitiesManager(callingFragmentFactory, this.providesCapabilitiesManagerProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallManager(callingFragmentFactory, this.providesCallManagerProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectRealTimeTextEnablementAuthority(callingFragmentFactory, getRealTimeTextEnablementAuthority());
        CallingFragmentFactory_MembersInjector.injectTelecomCallAudioRouteObservable(callingFragmentFactory, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectTelecomCallAudioManager(callingFragmentFactory, getTelecomCallAudioManager());
        CallingFragmentFactory_MembersInjector.injectEnhancedProcessingStateObservable(callingFragmentFactory, this.enhancedProcessingStateObservableProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectTelecomBridge(callingFragmentFactory, this.providesTelecomBridgeProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectAudioManager(callingFragmentFactory, getAudioManager());
        CallingFragmentFactory_MembersInjector.injectEffectsState(callingFragmentFactory, this.effectsStateProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectCallDowngradedObservable(callingFragmentFactory, this.callDowngradedObservableProvider.mo10268get());
        CallingFragmentFactory_MembersInjector.injectPipVisibilityObservable(callingFragmentFactory, this.pipVisibilityObservableProvider.mo10268get());
        return callingFragmentFactory;
    }

    private CallingInitiationActivity injectCallingInitiationActivity(CallingInitiationActivity callingInitiationActivity) {
        CallingInitiationActivity_MembersInjector.injectInitiationLogicFactory(callingInitiationActivity, getInitiationLogicFactory());
        CallingInitiationActivity_MembersInjector.injectCommsNotificationManager(callingInitiationActivity, this.providesCommsNotificationManagerProvider.mo10268get());
        return callingInitiationActivity;
    }

    private ChildContactCardFragment injectChildContactCardFragment(ChildContactCardFragment childContactCardFragment) {
        ChildContactCardFragment_MembersInjector.injectApplicationManager(childContactCardFragment, this.providesApplicationManagerProvider.mo10268get());
        ChildContactCardFragment_MembersInjector.injectMCommsIdentityManager(childContactCardFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        ChildContactCardFragment_MembersInjector.injectAppContext(childContactCardFragment, this.providesContextProvider.mo10268get());
        return childContactCardFragment;
    }

    private CommsCapabilityAgent injectCommsCapabilityAgent(CommsCapabilityAgent commsCapabilityAgent) {
        CommsCapabilityAgent_MembersInjector.injectCommsIdentityManager(commsCapabilityAgent, this.providesCommsIdentityManagerProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectCapabilitiesManager(commsCapabilityAgent, this.providesCapabilitiesManagerProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectPccContextProvider(commsCapabilityAgent, this.providesPCCContextProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectTelecomManager(commsCapabilityAgent, getTelecomManager());
        CommsCapabilityAgent_MembersInjector.injectContext(commsCapabilityAgent, this.providesContextProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectCallManagerLazy(commsCapabilityAgent, DoubleCheck.lazy(this.providesCallManagerProvider));
        CommsCapabilityAgent_MembersInjector.injectVoxUtils(commsCapabilityAgent, this.providesVoxUtilsProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectApplicationManager(commsCapabilityAgent, this.providesApplicationManagerProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectPCCDirectiveHandler(commsCapabilityAgent, this.providesPCCDirectiveHandlerProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectCallingFacade(commsCapabilityAgent, getCallingFacade());
        CommsCapabilityAgent_MembersInjector.injectSipClientState(commsCapabilityAgent, this.providesSipClientStateProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectNameChangeObservable(commsCapabilityAgent, this.nameChangeObservableProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectCallContext(commsCapabilityAgent, this.callContextProvider.mo10268get());
        CommsCapabilityAgent_MembersInjector.injectEnhancedProcessingStateObservable(commsCapabilityAgent, this.enhancedProcessingStateObservableProvider.mo10268get());
        return commsCapabilityAgent;
    }

    private CommsIdentityManagerImpl injectCommsIdentityManagerImpl(CommsIdentityManagerImpl commsIdentityManagerImpl) {
        CommsIdentityManagerImpl_MembersInjector.injectMigrationCommsIdentityStore(commsIdentityManagerImpl, this.providesMigrationCommsIdentityStoreProvider.mo10268get());
        CommsIdentityManagerImpl_MembersInjector.injectIdentityServiceLazy(commsIdentityManagerImpl, DoubleCheck.lazy(this.providesIdentityServiceProvider));
        return commsIdentityManagerImpl;
    }

    private CommsInternal injectCommsInternal(CommsInternal commsInternal) {
        CommsInternal_MembersInjector.injectMContext(commsInternal, this.providesContextProvider.mo10268get());
        CommsInternal_MembersInjector.injectMCallUIHandler(commsInternal, this.providesCallUIHandlerProvider.mo10268get());
        CommsInternal_MembersInjector.injectMSecuredSharedPreference(commsInternal, DoubleCheck.lazy(this.providesSecuredSharedPreferenceProvider));
        CommsInternal_MembersInjector.injectCommsConnectivityMonitor(commsInternal, this.providesCommsConnectivityMonitorProvider.mo10268get());
        CommsInternal_MembersInjector.injectPushNotificationManager(commsInternal, this.providesPushNotificationManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectApplicationManager(commsInternal, this.providesApplicationManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectCommsIdentityManager(commsInternal, this.providesCommsIdentityManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectProvisioningManager(commsInternal, DoubleCheck.lazy(this.providesProvisioningManagerProvider));
        CommsInternal_MembersInjector.injectCommsActivityMonitor(commsInternal, this.providesCommsActivityMonitorProvider.mo10268get());
        CommsInternal_MembersInjector.injectCapabilitiesManager(commsInternal, this.providesCapabilitiesManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectPccContextProvider(commsInternal, this.providesPCCContextProvider.mo10268get());
        CommsInternal_MembersInjector.injectCommsDisposableManager(commsInternal, this.providesCommsDisposableManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectCommsNotificationManager(commsInternal, this.providesCommsNotificationManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectDeviceMetadataStoreRegistrar(commsInternal, this.providesDeviceMetadataStoreRegistrarProvider.mo10268get());
        CommsInternal_MembersInjector.injectDeviceUtils(commsInternal, this.providesDeviceUtilsProvider.mo10268get());
        CommsInternal_MembersInjector.injectMessagingControllerManager(commsInternal, this.providesMessagingControllerManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectPhoneCallControllerManager(commsInternal, this.providesPhoneCallControllerManagerProvider.mo10268get());
        CommsInternal_MembersInjector.injectConversationMessageTracker(commsInternal, this.providesConversationMessageTrackerProvider.mo10268get());
        CommsInternal_MembersInjector.injectEventBus(commsInternal, this.providesEventBusProvider.mo10268get());
        CommsInternal_MembersInjector.injectCallInitiationAuthority(commsInternal, this.callInitiationAuthorityProvider.mo10268get());
        return commsInternal;
    }

    private CommsMasterFragment injectCommsMasterFragment(CommsMasterFragment commsMasterFragment) {
        CommsMasterFragment_MembersInjector.injectContactManager(commsMasterFragment, this.providesContactsOperationsManagerProvider.mo10268get());
        CommsMasterFragment_MembersInjector.injectCommsConnectivityMonitor(commsMasterFragment, this.providesCommsConnectivityMonitorProvider.mo10268get());
        CommsMasterFragment_MembersInjector.injectSipClientState(commsMasterFragment, this.providesSipClientStateProvider.mo10268get());
        CommsMasterFragment_MembersInjector.injectApplicationManager(commsMasterFragment, this.providesApplicationManagerProvider.mo10268get());
        CommsMasterFragment_MembersInjector.injectProvisioningManager(commsMasterFragment, this.providesProvisioningManagerProvider.mo10268get());
        CommsMasterFragment_MembersInjector.injectCommsIdentityManager(commsMasterFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        return commsMasterFragment;
    }

    private CommsServiceV2Impl injectCommsServiceV2Impl(CommsServiceV2Impl commsServiceV2Impl) {
        CommsServiceV2Impl_MembersInjector.injectAccessoriesHardwareIntentHandler(commsServiceV2Impl, DoubleCheck.lazy(this.providesAccessoriesHardwareIntentHandlerProvider));
        CommsServiceV2Impl_MembersInjector.injectAudioContentManager(commsServiceV2Impl, this.providesAudioContentManagerProvider.mo10268get());
        CommsServiceV2Impl_MembersInjector.injectProfileSelectionService(commsServiceV2Impl, this.providesProfileSelectionServiceProvider.mo10268get());
        CommsServiceV2Impl_MembersInjector.injectMModeSwitchedEventHandler(commsServiceV2Impl, DoubleCheck.lazy(this.driveModeEventHandlerProvider));
        CommsServiceV2Impl_MembersInjector.injectContentSharingService(commsServiceV2Impl, DoubleCheck.lazy(this.providesContentSharingServiceProvider));
        return commsServiceV2Impl;
    }

    private ConfigurationSyncService injectConfigurationSyncService(ConfigurationSyncService configurationSyncService) {
        ConfigurationSyncService_MembersInjector.injectStage(configurationSyncService, this.providesStageProvider.mo10268get());
        ConfigurationSyncService_MembersInjector.injectCommsConnectivityMonitor(configurationSyncService, this.providesCommsConnectivityMonitorProvider.mo10268get());
        return configurationSyncService;
    }

    private ContactBlockFragment injectContactBlockFragment(ContactBlockFragment contactBlockFragment) {
        ContactBlockFragment_MembersInjector.injectApplicationManager(contactBlockFragment, this.providesApplicationManagerProvider.mo10268get());
        ContactBlockFragment_MembersInjector.injectCommsIdentityManager(contactBlockFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        return contactBlockFragment;
    }

    private ContactCardFragment injectContactCardFragment(ContactCardFragment contactCardFragment) {
        ContactCardFragment_MembersInjector.injectMCommsIdentityManager(contactCardFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectCapabilitiesManager(contactCardFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectInitiationLogicFactory(contactCardFragment, getInitiationLogicFactory());
        ContactCardFragment_MembersInjector.injectCallingFacade(contactCardFragment, getCallingFacade());
        ContactCardFragment_MembersInjector.injectArcusConfig(contactCardFragment, this.providesArcusConfigProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectDevicesSource(contactCardFragment, this.providesDevicesSourceProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectAppContext(contactCardFragment, this.providesContextProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectApplicationManager(contactCardFragment, this.providesApplicationManagerProvider.mo10268get());
        ContactCardFragment_MembersInjector.injectDeviceUtils(contactCardFragment, this.providesDeviceUtilsProvider.mo10268get());
        return contactCardFragment;
    }

    private ContactDownloader injectContactDownloader(ContactDownloader contactDownloader) {
        ContactDownloader_MembersInjector.injectCapabilitiesManager(contactDownloader, this.providesCapabilitiesManagerProvider.mo10268get());
        return contactDownloader;
    }

    private ContactListAdapter injectContactListAdapter(ContactListAdapter contactListAdapter) {
        ContactListAdapter_MembersInjector.injectThemingUpdateUtil(contactListAdapter, this.providesThemingUpdateUtilProvider.mo10268get());
        return contactListAdapter;
    }

    private ContactListFragment injectContactListFragment(ContactListFragment contactListFragment) {
        ContactListFragment_MembersInjector.injectContactsManager(contactListFragment, this.providesContactsOperationsManagerProvider.mo10268get());
        ContactListFragment_MembersInjector.injectApplicationManager(contactListFragment, this.providesApplicationManagerProvider.mo10268get());
        ContactListFragment_MembersInjector.injectCommsIdentityManager(contactListFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        ContactListFragment_MembersInjector.injectProvisioningManager(contactListFragment, this.providesProvisioningManagerProvider.mo10268get());
        ContactListFragment_MembersInjector.injectCapabilitiesManager(contactListFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        ContactListFragment_MembersInjector.injectInitiationLogicFactory(contactListFragment, getInitiationLogicFactory());
        ContactListFragment_MembersInjector.injectCallingFacade(contactListFragment, getCallingFacade());
        ContactListFragment_MembersInjector.injectDevicesSource(contactListFragment, this.providesDevicesSourceProvider.mo10268get());
        ContactListFragment_MembersInjector.injectContactManager(contactListFragment, this.providesContactsOperationsManagerProvider.mo10268get());
        return contactListFragment;
    }

    private ContactStatusManager injectContactStatusManager(ContactStatusManager contactStatusManager) {
        ContactStatusManager_MembersInjector.injectContext(contactStatusManager, this.providesContextProvider.mo10268get());
        ContactStatusManager_MembersInjector.injectCommsIdentityManager(contactStatusManager, this.providesCommsIdentityManagerProvider.mo10268get());
        return contactStatusManager;
    }

    private ContactsOperationHandler injectContactsOperationHandler(ContactsOperationHandler contactsOperationHandler) {
        ContactsOperationHandler_MembersInjector.injectContext(contactsOperationHandler, this.providesContextProvider.mo10268get());
        ContactsOperationHandler_MembersInjector.injectContactsManager(contactsOperationHandler, this.providesContactsOperationsManagerProvider.mo10268get());
        ContactsOperationHandler_MembersInjector.injectCapabilitiesManager(contactsOperationHandler, this.providesCapabilitiesManagerProvider.mo10268get());
        return contactsOperationHandler;
    }

    private CreateNotificationService injectCreateNotificationService(CreateNotificationService createNotificationService) {
        CreateNotificationService_MembersInjector.injectCommsIdentityManager(createNotificationService, this.providesCommsIdentityManagerProvider.mo10268get());
        CreateNotificationService_MembersInjector.injectCommsNotificationManager(createNotificationService, this.providesCommsNotificationManagerProvider.mo10268get());
        CreateNotificationService_MembersInjector.injectModeSwitchHelper(createNotificationService, this.providesAlexaModeSwitchHelperProvider.mo10268get());
        CreateNotificationService_MembersInjector.injectActivityMonitor(createNotificationService, this.providesCommsActivityMonitorProvider.mo10268get());
        CreateNotificationService_MembersInjector.injectMAnnouncementCoordinator(createNotificationService, this.providesInboundAnnouncementsCoordinatorProvider.mo10268get());
        CreateNotificationService_MembersInjector.injectMMetricsService(createNotificationService, this.providesMetricsServiceProvider.mo10268get());
        return createNotificationService;
    }

    private CoboUtils.Dependencies injectDependencies(CoboUtils.Dependencies dependencies) {
        CoboUtils_Dependencies_MembersInjector.injectCapabilitiesManager(dependencies, this.providesCapabilitiesManagerProvider.mo10268get());
        return dependencies;
    }

    private DeviceBottomSheet injectDeviceBottomSheet(DeviceBottomSheet deviceBottomSheet) {
        DeviceBottomSheet_MembersInjector.injectMCapabilitiesManager(deviceBottomSheet, this.providesCapabilitiesManagerProvider.mo10268get());
        return deviceBottomSheet;
    }

    private DeviceCallingAndroidService injectDeviceCallingAndroidService(DeviceCallingAndroidService deviceCallingAndroidService) {
        DeviceCallingAndroidService_MembersInjector.injectPowerManager(deviceCallingAndroidService, getPowerManager());
        DeviceCallingAndroidService_MembersInjector.injectNotificationManager(deviceCallingAndroidService, getNotificationManager());
        DeviceCallingAndroidService_MembersInjector.injectMContext(deviceCallingAndroidService, this.providesContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMConnectivityManager(deviceCallingAndroidService, getConnectivityManager());
        DeviceCallingAndroidService_MembersInjector.injectMTelephonyManager(deviceCallingAndroidService, getTelephonyManager());
        DeviceCallingAndroidService_MembersInjector.injectMAudioManager(deviceCallingAndroidService, getAudioManager());
        DeviceCallingAndroidService_MembersInjector.injectAlarmManager(deviceCallingAndroidService, getAlarmManager());
        DeviceCallingAndroidService_MembersInjector.injectCallManager(deviceCallingAndroidService, this.providesCallManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallTimerManager(deviceCallingAndroidService, this.providesCallTimerManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommandProcessor(deviceCallingAndroidService, this.providesCommandProcessorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsConnectivityMonitor(deviceCallingAndroidService, this.providesCommsConnectivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommsNotificationManager(deviceCallingAndroidService, this.providesCommsNotificationManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceCallingService(deviceCallingAndroidService, this.providesDeviceCallingServiceProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectSipClientState(deviceCallingAndroidService, this.providesSipClientStateProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPushNotificationManagerLazy(deviceCallingAndroidService, DoubleCheck.lazy(this.providesPushNotificationManagerProvider));
        DeviceCallingAndroidService_MembersInjector.injectMEventTracerFactory(deviceCallingAndroidService, this.providesEventTracerFactoryProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMDeviceCallingServiceParams(deviceCallingAndroidService, this.providesDeviceCallingServiceParamsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectProvisioningManager(deviceCallingAndroidService, this.providesProvisioningManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsIdentityManager(deviceCallingAndroidService, this.providesCommsIdentityManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCapabilitiesManager(deviceCallingAndroidService, this.providesCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsActivityMonitor(deviceCallingAndroidService, this.providesCommsActivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallUIHandler(deviceCallingAndroidService, this.providesCallUIHandlerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMModeSwitchHelper(deviceCallingAndroidService, this.providesAlexaModeSwitchHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectArcusConfig(deviceCallingAndroidService, this.providesArcusConfigProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCapabilitiesManager(deviceCallingAndroidService, this.providesCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceUtils(deviceCallingAndroidService, this.providesDeviceUtilsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPccContextProvider(deviceCallingAndroidService, this.providesPCCContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMAlexaAudioPlayer(deviceCallingAndroidService, this.providesAlexaAudioPlayerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallHistoryHelper(deviceCallingAndroidService, this.providesCallHistoryHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallContext(deviceCallingAndroidService, this.callContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallInitiationAuthority(deviceCallingAndroidService, this.callInitiationAuthorityProvider.mo10268get());
        return deviceCallingAndroidService;
    }

    private DeviceCallingServiceEventListener injectDeviceCallingServiceEventListener(DeviceCallingServiceEventListener deviceCallingServiceEventListener) {
        DeviceCallingServiceEventListener_MembersInjector.injectMContext(deviceCallingServiceEventListener, this.providesContextProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCommsConnectivityMonitor(deviceCallingServiceEventListener, this.providesCommsConnectivityMonitorProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCommsIdentityManager(deviceCallingServiceEventListener, this.providesCommsIdentityManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallHistoryHelper(deviceCallingServiceEventListener, this.providesCallHistoryHelperProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallMetricsFactory(deviceCallingServiceEventListener, this.providesCallMetricsFactoryProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectMCurrentAlexaInterface(deviceCallingServiceEventListener, this.providesAlexaInterfaceProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectMAlexaAudioPlayer(deviceCallingServiceEventListener, this.providesAlexaAudioPlayerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectPreviousSipClientState(deviceCallingServiceEventListener, this.providesPreviousSipClientStateProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallingAPIMonitor(deviceCallingServiceEventListener, getCallingAPIMonitor());
        DeviceCallingServiceEventListener_MembersInjector.injectCallInitiationAuthority(deviceCallingServiceEventListener, this.callInitiationAuthorityProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCapabilitiesManager(deviceCallingServiceEventListener, this.providesCapabilitiesManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallManager(deviceCallingServiceEventListener, this.providesCallManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectMArcusConfig(deviceCallingServiceEventListener, this.providesArcusConfigProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallTimerManager(deviceCallingServiceEventListener, this.providesCallTimerManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectSipClientState(deviceCallingServiceEventListener, this.providesSipClientStateProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectProvisioningManager(deviceCallingServiceEventListener, this.providesProvisioningManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCommsNotificationManager(deviceCallingServiceEventListener, this.providesCommsNotificationManagerProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectPccContextProvider(deviceCallingServiceEventListener, this.providesPCCContextProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectRingTonePlaybackAuthority(deviceCallingServiceEventListener, this.ringTonePlaybackAuthorityProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectCallContext(deviceCallingServiceEventListener, this.callContextProvider.mo10268get());
        DeviceCallingServiceEventListener_MembersInjector.injectDeviceCallingService(deviceCallingServiceEventListener, this.providesDeviceCallingServiceProvider.mo10268get());
        return deviceCallingServiceEventListener;
    }

    private DiagnosticScreen injectDiagnosticScreen(DiagnosticScreen diagnosticScreen) {
        DiagnosticScreen_MembersInjector.injectDeviceCallingService(diagnosticScreen, this.providesDeviceCallingServiceProvider.mo10268get());
        DiagnosticScreen_MembersInjector.injectApplicationManager(diagnosticScreen, this.providesApplicationManagerProvider.mo10268get());
        DiagnosticScreen_MembersInjector.injectCommsInternal(diagnosticScreen, this.providesCommsInternalProvider.mo10268get());
        DiagnosticScreen_MembersInjector.injectCommsIdentityManager(diagnosticScreen, this.providesCommsIdentityManagerProvider.mo10268get());
        DiagnosticScreen_MembersInjector.injectCapabilitiesManager(diagnosticScreen, this.providesCapabilitiesManagerProvider.mo10268get());
        return diagnosticScreen;
    }

    private DialPad injectDialPad(DialPad dialPad) {
        DialPad_MembersInjector.injectSipClientState(dialPad, this.providesSipClientStateProvider.mo10268get());
        return dialPad;
    }

    private DialPadButton injectDialPadButton(DialPadButton dialPadButton) {
        DialPadButton_MembersInjector.injectMCapabilitiesManager(dialPadButton, this.providesCapabilitiesManagerProvider.mo10268get());
        return dialPadButton;
    }

    private DirectiveReceiverActivity injectDirectiveReceiverActivity(DirectiveReceiverActivity directiveReceiverActivity) {
        DirectiveReceiverActivity_MembersInjector.injectCommsConversationService(directiveReceiverActivity, this.providesConversationServiceProvider.mo10268get());
        return directiveReceiverActivity;
    }

    private EditContactFragment injectEditContactFragment(EditContactFragment editContactFragment) {
        EditContactFragment_MembersInjector.injectApplicationManager(editContactFragment, this.providesApplicationManagerProvider.mo10268get());
        EditContactFragment_MembersInjector.injectCommsInternal(editContactFragment, this.providesCommsInternalProvider.mo10268get());
        return editContactFragment;
    }

    private EditNicknameFragment injectEditNicknameFragment(EditNicknameFragment editNicknameFragment) {
        EditNicknameFragment_MembersInjector.injectApplicationManager(editNicknameFragment, this.providesApplicationManagerProvider.mo10268get());
        EditNicknameFragment_MembersInjector.injectArcusConfig(editNicknameFragment, this.providesArcusConfigProvider.mo10268get());
        EditNicknameFragment_MembersInjector.injectAppContext(editNicknameFragment, this.providesContextProvider.mo10268get());
        return editNicknameFragment;
    }

    private EndCallFragment injectEndCallFragment(EndCallFragment endCallFragment) {
        EndCallFragment_MembersInjector.injectMCapabilitiesManager(endCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        EndCallFragment_MembersInjector.injectMDriveModeSharedPreferencesUseCase(endCallFragment, getDriveModeSharedPreferencesUseCase());
        EndCallFragment_MembersInjector.injectCallContext(endCallFragment, this.callContextProvider.mo10268get());
        EndCallFragment_MembersInjector.injectCallInitiationAuthority(endCallFragment, this.callInitiationAuthorityProvider.mo10268get());
        return endCallFragment;
    }

    private FireOSDirectiveHandlerService injectFireOSDirectiveHandlerService(FireOSDirectiveHandlerService fireOSDirectiveHandlerService) {
        FireOSDirectiveHandlerService_MembersInjector.injectCommsConversationService(fireOSDirectiveHandlerService, this.providesConversationServiceProvider.mo10268get());
        return fireOSDirectiveHandlerService;
    }

    private GetOrCreateContact injectGetOrCreateContact(GetOrCreateContact getOrCreateContact) {
        GetOrCreateContact_MembersInjector.injectContext(getOrCreateContact, this.providesContextProvider.mo10268get());
        GetOrCreateContact_MembersInjector.injectContactsManager(getOrCreateContact, this.providesContactsOperationsManagerProvider.mo10268get());
        GetOrCreateContact_MembersInjector.injectCommsIdentityManager(getOrCreateContact, this.providesCommsIdentityManagerProvider.mo10268get());
        GetOrCreateContact_MembersInjector.injectCommsInternal(getOrCreateContact, this.providesCommsInternalProvider.mo10268get());
        return getOrCreateContact;
    }

    private HeadsetPluggedBroadcastReceiver injectHeadsetPluggedBroadcastReceiver(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver) {
        HeadsetPluggedBroadcastReceiver_MembersInjector.injectSipClientState(headsetPluggedBroadcastReceiver, this.providesSipClientStateProvider.mo10268get());
        HeadsetPluggedBroadcastReceiver_MembersInjector.injectCapabilitiesManager(headsetPluggedBroadcastReceiver, this.providesCapabilitiesManagerProvider.mo10268get());
        HeadsetPluggedBroadcastReceiver_MembersInjector.injectAudioManager(headsetPluggedBroadcastReceiver, getAudioManager());
        HeadsetPluggedBroadcastReceiver_MembersInjector.injectAudioOutputController(headsetPluggedBroadcastReceiver, this.providesAudioStateControllerProvider.mo10268get());
        return headsetPluggedBroadcastReceiver;
    }

    private IncomingCallFragment injectIncomingCallFragment(IncomingCallFragment incomingCallFragment) {
        IncomingCallFragment_MembersInjector.injectMCapabilitiesManager(incomingCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        IncomingCallFragment_MembersInjector.injectRealTimeTextEnablementAuthority(incomingCallFragment, getRealTimeTextEnablementAuthority());
        return incomingCallFragment;
    }

    private IncomingEnhancedProcessingAudioCallFragment injectIncomingEnhancedProcessingAudioCallFragment(IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment) {
        IncomingEnhancedProcessingAudioCallFragment_MembersInjector.injectCapabilitiesManager(incomingEnhancedProcessingAudioCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        return incomingEnhancedProcessingAudioCallFragment;
    }

    private IncomingPushAndroidService injectIncomingPushAndroidService(IncomingPushAndroidService incomingPushAndroidService) {
        DeviceCallingAndroidService_MembersInjector.injectPowerManager(incomingPushAndroidService, getPowerManager());
        DeviceCallingAndroidService_MembersInjector.injectNotificationManager(incomingPushAndroidService, getNotificationManager());
        DeviceCallingAndroidService_MembersInjector.injectMContext(incomingPushAndroidService, this.providesContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMConnectivityManager(incomingPushAndroidService, getConnectivityManager());
        DeviceCallingAndroidService_MembersInjector.injectMTelephonyManager(incomingPushAndroidService, getTelephonyManager());
        DeviceCallingAndroidService_MembersInjector.injectMAudioManager(incomingPushAndroidService, getAudioManager());
        DeviceCallingAndroidService_MembersInjector.injectAlarmManager(incomingPushAndroidService, getAlarmManager());
        DeviceCallingAndroidService_MembersInjector.injectCallManager(incomingPushAndroidService, this.providesCallManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallTimerManager(incomingPushAndroidService, this.providesCallTimerManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommandProcessor(incomingPushAndroidService, this.providesCommandProcessorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsConnectivityMonitor(incomingPushAndroidService, this.providesCommsConnectivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCommsNotificationManager(incomingPushAndroidService, this.providesCommsNotificationManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceCallingService(incomingPushAndroidService, this.providesDeviceCallingServiceProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectSipClientState(incomingPushAndroidService, this.providesSipClientStateProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPushNotificationManagerLazy(incomingPushAndroidService, DoubleCheck.lazy(this.providesPushNotificationManagerProvider));
        DeviceCallingAndroidService_MembersInjector.injectMEventTracerFactory(incomingPushAndroidService, this.providesEventTracerFactoryProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMDeviceCallingServiceParams(incomingPushAndroidService, this.providesDeviceCallingServiceParamsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectProvisioningManager(incomingPushAndroidService, this.providesProvisioningManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsIdentityManager(incomingPushAndroidService, this.providesCommsIdentityManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMCapabilitiesManager(incomingPushAndroidService, this.providesCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCommsActivityMonitor(incomingPushAndroidService, this.providesCommsActivityMonitorProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallUIHandler(incomingPushAndroidService, this.providesCallUIHandlerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMModeSwitchHelper(incomingPushAndroidService, this.providesAlexaModeSwitchHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectArcusConfig(incomingPushAndroidService, this.providesArcusConfigProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCapabilitiesManager(incomingPushAndroidService, this.providesCapabilitiesManagerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectDeviceUtils(incomingPushAndroidService, this.providesDeviceUtilsProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectPccContextProvider(incomingPushAndroidService, this.providesPCCContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectMAlexaAudioPlayer(incomingPushAndroidService, this.providesAlexaAudioPlayerProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallHistoryHelper(incomingPushAndroidService, this.providesCallHistoryHelperProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallContext(incomingPushAndroidService, this.callContextProvider.mo10268get());
        DeviceCallingAndroidService_MembersInjector.injectCallInitiationAuthority(incomingPushAndroidService, this.callInitiationAuthorityProvider.mo10268get());
        IncomingPushAndroidService_MembersInjector.injectMAlexaAudioPlayer(incomingPushAndroidService, this.providesAlexaAudioPlayerProvider.mo10268get());
        IncomingPushAndroidService_MembersInjector.injectRingTonePlaybackAuthority(incomingPushAndroidService, this.ringTonePlaybackAuthorityProvider.mo10268get());
        return incomingPushAndroidService;
    }

    private InitiateCallService injectInitiateCallService(InitiateCallService initiateCallService) {
        InitiateCallService_MembersInjector.injectSipClientState(initiateCallService, this.providesSipClientStateProvider.mo10268get());
        InitiateCallService_MembersInjector.injectCommsIdentityManager(initiateCallService, this.providesCommsIdentityManagerProvider.mo10268get());
        InitiateCallService_MembersInjector.injectCapabilitiesManager(initiateCallService, this.providesCapabilitiesManagerProvider.mo10268get());
        return initiateCallService;
    }

    private InternalCommsManager injectInternalCommsManager(InternalCommsManager internalCommsManager) {
        InternalCommsManager_MembersInjector.injectFeatureFlagManager(internalCommsManager, this.providesFeatureFlagManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectAudioRecorder(internalCommsManager, this.providesAudioRecorderProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectPushNotificationManager(internalCommsManager, this.providesPushNotificationManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectApplicationManager(internalCommsManager, this.providesApplicationManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCommsConnectivityMonitor(internalCommsManager, this.providesCommsConnectivityMonitorProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectContactsOperationsManager(internalCommsManager, this.providesContactsOperationsManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCapabilitiesManager(internalCommsManager, this.providesCapabilitiesManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectTelecomManager(internalCommsManager, getTelecomManager());
        InternalCommsManager_MembersInjector.injectPccContextProvider(internalCommsManager, this.providesPCCContextProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCallManagerLazy(internalCommsManager, DoubleCheck.lazy(this.providesCallManagerProvider));
        InternalCommsManager_MembersInjector.injectCommsInternal(internalCommsManager, this.providesCommsInternalProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCommsIdentityManager(internalCommsManager, this.providesCommsIdentityManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectProvisioningManager(internalCommsManager, this.providesProvisioningManagerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectVoxUtils(internalCommsManager, this.providesVoxUtilsProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectPCCDirectiveHandler(internalCommsManager, this.providesPCCDirectiveHandlerProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCallingFacade(internalCommsManager, getCallingFacade());
        InternalCommsManager_MembersInjector.injectNameChangeObservable(internalCommsManager, this.nameChangeObservableProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectEnhancedProcessingStateObservable(internalCommsManager, this.enhancedProcessingStateObservableProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectSipClientState(internalCommsManager, this.providesSipClientStateProvider.mo10268get());
        InternalCommsManager_MembersInjector.injectCallContext(internalCommsManager, this.callContextProvider.mo10268get());
        return internalCommsManager;
    }

    private MainOOBEFragment injectMainOOBEFragment(MainOOBEFragment mainOOBEFragment) {
        MainOOBEFragment_MembersInjector.injectCapabilitiesManager(mainOOBEFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        MainOOBEFragment_MembersInjector.injectThemingUpdateUtil(mainOOBEFragment, this.providesThemingUpdateUtilProvider.mo10268get());
        return mainOOBEFragment;
    }

    private ManageContactsFragment injectManageContactsFragment(ManageContactsFragment manageContactsFragment) {
        ManageContactsFragment_MembersInjector.injectApplicationManager(manageContactsFragment, this.providesApplicationManagerProvider.mo10268get());
        return manageContactsFragment;
    }

    private MessageReadStatusMarkerService injectMessageReadStatusMarkerService(MessageReadStatusMarkerService messageReadStatusMarkerService) {
        MessageReadStatusMarkerService_MembersInjector.injectCommsNotificationManager(messageReadStatusMarkerService, this.providesCommsNotificationManagerProvider.mo10268get());
        MessageReadStatusMarkerService_MembersInjector.injectCommsIdentityManager(messageReadStatusMarkerService, this.providesCommsIdentityManagerProvider.mo10268get());
        MessageReadStatusMarkerService_MembersInjector.injectProvisioningManager(messageReadStatusMarkerService, this.providesProvisioningManagerProvider.mo10268get());
        return messageReadStatusMarkerService;
    }

    private MessagingControllerContextProvider injectMessagingControllerContextProvider(MessagingControllerContextProvider messagingControllerContextProvider) {
        MessagingControllerContextProvider_MembersInjector.injectMContext(messagingControllerContextProvider, this.providesContextProvider.mo10268get());
        return messagingControllerContextProvider;
    }

    private MessagingControllerDirectiveHandler injectMessagingControllerDirectiveHandler(MessagingControllerDirectiveHandler messagingControllerDirectiveHandler) {
        MessagingControllerDirectiveHandler_MembersInjector.injectMMessagingControllerManager(messagingControllerDirectiveHandler, this.providesMessagingControllerManagerProvider.mo10268get());
        return messagingControllerDirectiveHandler;
    }

    private MessagingControllerManager injectMessagingControllerManager(MessagingControllerManager messagingControllerManager) {
        MessagingControllerManager_MembersInjector.injectMContext(messagingControllerManager, this.providesContextProvider.mo10268get());
        return messagingControllerManager;
    }

    private MessagingProviderWrapper injectMessagingProviderWrapper(MessagingProviderWrapper messagingProviderWrapper) {
        MessagingProviderWrapper_MembersInjector.injectMTranscriptLatencyMetricHelper(messagingProviderWrapper, this.providesTranscriptUpdateLatencyMetricHelperProvider.mo10268get());
        return messagingProviderWrapper;
    }

    private MigrationCommsIdentityStore injectMigrationCommsIdentityStore(MigrationCommsIdentityStore migrationCommsIdentityStore) {
        MigrationCommsIdentityStore_MembersInjector.injectContext(migrationCommsIdentityStore, this.providesContextProvider.mo10268get());
        MigrationCommsIdentityStore_MembersInjector.injectMLegacyCommsIdentityStore(migrationCommsIdentityStore, this.providesLegacyCommsIdentityStoreProvider.mo10268get());
        MigrationCommsIdentityStore_MembersInjector.injectMCommsIdentityStoreV2Impl(migrationCommsIdentityStore, this.providesCommsIdentityStoreV2Provider.mo10268get());
        return migrationCommsIdentityStore;
    }

    private NativeCallActivity injectNativeCallActivity(NativeCallActivity nativeCallActivity) {
        NativeCallActivity_MembersInjector.injectMakeNativeCallHandler(nativeCallActivity, this.providesMakeNativeCallHandlerProvider.mo10268get());
        NativeCallActivity_MembersInjector.injectAcceptNativeCallHandler(nativeCallActivity, this.providesAcceptNativeCallHandlerProvider.mo10268get());
        NativeCallActivity_MembersInjector.injectEndNativeCallHandler(nativeCallActivity, this.providesEndNativeCallHandlerProvider.mo10268get());
        NativeCallActivity_MembersInjector.injectNoCallPermissionHandler(nativeCallActivity, this.provideNoPermissionHandlerProvider.mo10268get());
        NativeCallActivity_MembersInjector.injectTimeoutHelper(nativeCallActivity, LibraryModule_ProvideTimeoutHelperFactory.proxyProvideTimeoutHelper(this.libraryModule));
        return nativeCallActivity;
    }

    private NewCallActivity injectNewCallActivity(NewCallActivity newCallActivity) {
        NewCallActivity_MembersInjector.injectCallContext(newCallActivity, this.callContextProvider.mo10268get());
        NewCallActivity_MembersInjector.injectMAlexaAudioPlayer(newCallActivity, this.providesAlexaAudioPlayerProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCallManager(newCallActivity, this.providesCallManagerProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCallMediaControlFacade(newCallActivity, this.callMediaControlFacadeProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCapabilitiesManager(newCallActivity, this.providesCapabilitiesManagerProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCommandProcessor(newCallActivity, this.providesCommandProcessorProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCommsNotificationManager(newCallActivity, this.providesCommsNotificationManagerProvider.mo10268get());
        NewCallActivity_MembersInjector.injectDeviceCallingService(newCallActivity, this.providesDeviceCallingServiceProvider.mo10268get());
        NewCallActivity_MembersInjector.injectRealTimeTextEnablementAuthority(newCallActivity, getRealTimeTextEnablementAuthority());
        NewCallActivity_MembersInjector.injectSipClientState(newCallActivity, this.providesSipClientStateProvider.mo10268get());
        NewCallActivity_MembersInjector.injectTelecomCallAudioRouteObservable(newCallActivity, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        NewCallActivity_MembersInjector.injectCallDowngradedObservable(newCallActivity, this.callDowngradedObservableProvider.mo10268get());
        return newCallActivity;
    }

    private NotificationActivatedReceiver injectNotificationActivatedReceiver(NotificationActivatedReceiver notificationActivatedReceiver) {
        NotificationActivatedReceiver_MembersInjector.injectMNotificationLatencyMetricHelper(notificationActivatedReceiver, this.providesNotificationLatencyMetricHelperProvider.mo10268get());
        return notificationActivatedReceiver;
    }

    private OOBEActivity injectOOBEActivity(OOBEActivity oOBEActivity) {
        OOBEActivity_MembersInjector.injectApplicationManager(oOBEActivity, this.providesApplicationManagerProvider.mo10268get());
        OOBEActivity_MembersInjector.injectCapabilitiesManager(oOBEActivity, this.providesCapabilitiesManagerProvider.mo10268get());
        OOBEActivity_MembersInjector.injectCommsIdentityManager(oOBEActivity, this.providesCommsIdentityManagerProvider.mo10268get());
        OOBEActivity_MembersInjector.injectDeviceMetadataStoreRegistrar(oOBEActivity, this.providesDeviceMetadataStoreRegistrarProvider.mo10268get());
        OOBEActivity_MembersInjector.injectMessagingControllerManager(oOBEActivity, this.providesMessagingControllerManagerProvider.mo10268get());
        OOBEActivity_MembersInjector.injectMcContextProvider(oOBEActivity, this.providesMessagingControllerContextProvider.mo10268get());
        OOBEActivity_MembersInjector.injectCommsAccessorySessionListener(oOBEActivity, this.providesCommsAccessorySessionListenerProvider.mo10268get());
        OOBEActivity_MembersInjector.injectEventBus(oOBEActivity, this.providesEventBusProvider.mo10268get());
        OOBEActivity_MembersInjector.injectOobeService(oOBEActivity, this.providesOobeServiceProvider.mo10268get());
        OOBEActivity_MembersInjector.injectOobePersonManager(oOBEActivity, this.providesOOBEPersonUtilProvider.mo10268get());
        OOBEActivity_MembersInjector.injectThemingUpdateUtil(oOBEActivity, this.providesThemingUpdateUtilProvider.mo10268get());
        return oOBEActivity;
    }

    private OOBEPersonManager injectOOBEPersonManager(OOBEPersonManager oOBEPersonManager) {
        OOBEPersonManager_MembersInjector.injectMContext(oOBEPersonManager, this.providesContextProvider.mo10268get());
        return oOBEPersonManager;
    }

    private OutgoingCallFragment injectOutgoingCallFragment(OutgoingCallFragment outgoingCallFragment) {
        OutgoingCallFragment_MembersInjector.injectSipClientState(outgoingCallFragment, this.providesSipClientStateProvider.mo10268get());
        OutgoingCallFragment_MembersInjector.injectCapabilitiesManager(outgoingCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        OutgoingCallFragment_MembersInjector.injectMPresenter(outgoingCallFragment, getBaseCallingPresenter());
        OutgoingCallFragment_MembersInjector.injectMDriveModeSharedPreferencesUseCase(outgoingCallFragment, getDriveModeSharedPreferencesUseCase());
        return outgoingCallFragment;
    }

    private OutgoingEnhancedProcessingAudioCallFragment injectOutgoingEnhancedProcessingAudioCallFragment(OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment) {
        OutgoingEnhancedProcessingAudioCallFragment_MembersInjector.injectCapabilitiesManager(outgoingEnhancedProcessingAudioCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        return outgoingEnhancedProcessingAudioCallFragment;
    }

    private OutgoingEnhancedProcessingVideoCallFragment injectOutgoingEnhancedProcessingVideoCallFragment(OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment) {
        OutgoingEnhancedProcessingVideoCallFragment_MembersInjector.injectCapabilitiesManager(outgoingEnhancedProcessingVideoCallFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        return outgoingEnhancedProcessingVideoCallFragment;
    }

    private PerformCallReconnectTask injectPerformCallReconnectTask(PerformCallReconnectTask performCallReconnectTask) {
        PerformCallReconnectTask_MembersInjector.injectClientState(performCallReconnectTask, this.providesSipClientStateProvider.mo10268get());
        PerformCallReconnectTask_MembersInjector.injectCommandProcessor(performCallReconnectTask, this.providesCommandProcessorProvider.mo10268get());
        return performCallReconnectTask;
    }

    private PowerButtonPressReceiver injectPowerButtonPressReceiver(PowerButtonPressReceiver powerButtonPressReceiver) {
        PowerButtonPressReceiver_MembersInjector.injectAudioPlayer(powerButtonPressReceiver, this.providesAlexaAudioPlayerProvider.mo10268get());
        return powerButtonPressReceiver;
    }

    private ProvisioningManager injectProvisioningManager(ProvisioningManager provisioningManager) {
        ProvisioningManager_MembersInjector.injectMCommsIdentityManager(provisioningManager, this.providesCommsIdentityManagerProvider.mo10268get());
        return provisioningManager;
    }

    private RelationshipListFragment injectRelationshipListFragment(RelationshipListFragment relationshipListFragment) {
        RelationshipListFragment_MembersInjector.injectApplicationManager(relationshipListFragment, this.providesApplicationManagerProvider.mo10268get());
        RelationshipListFragment_MembersInjector.injectAppContext(relationshipListFragment, this.providesContextProvider.mo10268get());
        return relationshipListFragment;
    }

    private RingServiceBroadcastReceiver injectRingServiceBroadcastReceiver(RingServiceBroadcastReceiver ringServiceBroadcastReceiver) {
        RingServiceBroadcastReceiver_MembersInjector.injectAlexaServicesConnection(ringServiceBroadcastReceiver, this.providesCommsAlexaServicesConnectionProvider.mo10268get());
        RingServiceBroadcastReceiver_MembersInjector.injectCommsAudioInteraction(ringServiceBroadcastReceiver, this.providesCommsAudioInteractionProvider.mo10268get());
        RingServiceBroadcastReceiver_MembersInjector.injectFactory(ringServiceBroadcastReceiver, this.providesInCallCommandModelFactoryProvider.mo10268get());
        RingServiceBroadcastReceiver_MembersInjector.injectCommsEventSender(ringServiceBroadcastReceiver, this.providesCommsEventSenderProvider.mo10268get());
        RingServiceBroadcastReceiver_MembersInjector.injectA2AEndpointHandler(ringServiceBroadcastReceiver, this.providesA2AConnectionEnpointHandlerProvider.mo10268get());
        RingServiceBroadcastReceiver_MembersInjector.injectCommsAudioInteractionScheduler(ringServiceBroadcastReceiver, getCommsAudioInteractionScheduler());
        RingServiceBroadcastReceiver_MembersInjector.injectA2AUnsentEventsManager(ringServiceBroadcastReceiver, this.providesA2AUnsentEventsManagerProvider.mo10268get());
        return ringServiceBroadcastReceiver;
    }

    private SendCallMetricsTask injectSendCallMetricsTask(SendCallMetricsTask sendCallMetricsTask) {
        SendCallMetricsTask_MembersInjector.injectCommsIdentityManager(sendCallMetricsTask, this.providesCommsIdentityManagerProvider.mo10268get());
        SendCallMetricsTask_MembersInjector.injectCallHistoryHelper(sendCallMetricsTask, this.providesCallHistoryHelperProvider.mo10268get());
        return sendCallMetricsTask;
    }

    private ShareSheetActivity injectShareSheetActivity(ShareSheetActivity shareSheetActivity) {
        ShareSheetActivity_MembersInjector.injectThemingUpdateUtil(shareSheetActivity, this.providesThemingUpdateUtilProvider.mo10268get());
        return shareSheetActivity;
    }

    private TelecomConnection injectTelecomConnection(TelecomConnection telecomConnection) {
        TelecomConnection_MembersInjector.injectContext(telecomConnection, this.providesContextProvider.mo10268get());
        TelecomConnection_MembersInjector.injectAudioManager(telecomConnection, getAudioManager());
        return telecomConnection;
    }

    private TelecomConnectionService injectTelecomConnectionService(TelecomConnectionService telecomConnectionService) {
        TelecomConnectionService_MembersInjector.injectCallIdToTelecomConnection(telecomConnectionService, this.providesCallIdToTelecomConnectionProvider.mo10268get());
        TelecomConnectionService_MembersInjector.injectTelecomCallIds(telecomConnectionService, this.providesTelecomCallIdsProvider.mo10268get());
        TelecomConnectionService_MembersInjector.injectTelecomLock(telecomConnectionService, this.providesTelecomLockProvider.mo10268get());
        TelecomConnectionService_MembersInjector.injectTelecomBridge(telecomConnectionService, this.providesTelecomBridgeProvider.mo10268get());
        TelecomConnectionService_MembersInjector.injectTelecomCallAudioRouteObservable(telecomConnectionService, this.providesTelecomCallAudioRouteObservableProvider.mo10268get());
        return telecomConnectionService;
    }

    private TranscriptionUpdateService injectTranscriptionUpdateService(TranscriptionUpdateService transcriptionUpdateService) {
        TranscriptionUpdateService_MembersInjector.injectMTranscriptLatencyMetricHelper(transcriptionUpdateService, this.providesTranscriptUpdateLatencyMetricHelperProvider.mo10268get());
        TranscriptionUpdateService_MembersInjector.injectCommsIdentityManager(transcriptionUpdateService, this.providesCommsIdentityManagerProvider.mo10268get());
        return transcriptionUpdateService;
    }

    private VoxUtils injectVoxUtils(VoxUtils voxUtils) {
        VoxUtils_MembersInjector.injectPccContextProvider(voxUtils, this.providesPCCContextProvider.mo10268get());
        VoxUtils_MembersInjector.injectCapabilitiesManager(voxUtils, this.providesCapabilitiesManagerProvider.mo10268get());
        VoxUtils_MembersInjector.injectCommsIdentityManager(voxUtils, this.providesCommsIdentityManagerProvider.mo10268get());
        return voxUtils;
    }

    private WhitelistContactFragment injectWhitelistContactFragment(WhitelistContactFragment whitelistContactFragment) {
        WhitelistContactFragment_MembersInjector.injectCommsIdentityManager(whitelistContactFragment, this.providesCommsIdentityManagerProvider.mo10268get());
        WhitelistContactFragment_MembersInjector.injectApplicationManager(whitelistContactFragment, this.providesApplicationManagerProvider.mo10268get());
        WhitelistContactFragment_MembersInjector.injectAppContext(whitelistContactFragment, this.providesContextProvider.mo10268get());
        WhitelistContactFragment_MembersInjector.injectCapabilitiesManager(whitelistContactFragment, this.providesCapabilitiesManagerProvider.mo10268get());
        WhitelistContactFragment_MembersInjector.injectThemingUpdateUtil(whitelistContactFragment, this.providesThemingUpdateUtilProvider.mo10268get());
        return whitelistContactFragment;
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AcceptNativeCallHandler getAcceptCallHandler() {
        return this.providesAcceptNativeCallHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AccessoriesHardwareIntentHandler getAccessoriesHardwareIntentHandler() {
        return this.providesAccessoriesHardwareIntentHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AccountConfiguration getAccountConfiguration() {
        return CloudDriveModule_ProvideAccountConfigurationFactory.proxyProvideAccountConfiguration(this.cloudDriveModule, getMAPAuthenticatedURLConnectionFactory(), getEndpointsCache(), getCommsRequestAuthenticator());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AlarmManager getAlarmManager() {
        return AndroidModule_ProvidesAlarmManagerFactory.proxyProvidesAlarmManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AlexaCommsService getAlexaCommsService() {
        return this.providesAlexaCommsServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AppUrl getAppUrl() {
        return LibraryModule_ProvidesAppUrlFactory.proxyProvidesAppUrl(this.libraryModule, this.providesArcusConfigProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ApplicationManager getApplicationManager() {
        return this.providesApplicationManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ArcusConfig getArcusConfig() {
        return this.providesArcusConfigProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AudioContentManager getAudioContentManager() {
        return this.providesAudioContentManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AudioManager getAudioManager() {
        return AndroidModule_ProvidesAudioManagerFactory.proxyProvidesAudioManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AudioPlayer getAudioPlayer() {
        return this.providesAudioPlayerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AudioRecorder getAudioRecorder() {
        return this.providesAudioRecorderProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AudioStateManager getAudioStateManager() {
        return this.providesAudioStateManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public BluetoothHeadsetHelper getBluetoothHeadsetHelper() {
        return this.providesBluetoothHeadsetHelperProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallHelper getCallHelper() {
        return LibraryModule_ProvidesCallHelperFactory.proxyProvidesCallHelper(this.libraryModule);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallHistoryHelper getCallHistoryHelper() {
        return this.providesCallHistoryHelperProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallInitiationAuthority getCallInitiationAuthority() {
        return this.callInitiationAuthorityProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallManager getCallManager() {
        return this.providesCallManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ICallingAPI getCallingAPI() {
        return this.providesCallingAPIProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallingAPIMonitor getCallingAPIMonitor() {
        return new CallingAPIMonitor(getCallingAPIPopulator());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallingAPIPopulator getCallingAPIPopulator() {
        return new CallingAPIPopulator(this.callContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CallingFacade getCallingFacade() {
        return SipModule_ProvidesCallingFacadeFactory.proxyProvidesCallingFacade(this.sipModule, getCallInitiationOrchestrator());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CapabilitiesManager getCapabilitiesManager() {
        return this.providesCapabilitiesManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ClientConfiguration getClientConfiguration() {
        return CloudDriveModule_ProvideClientConfigurationFactory.proxyProvideClientConfiguration(this.cloudDriveModule, this.providesArcusConfigProvider.mo10268get(), CloudDriveModule_ProvideOkHttpClientWrapperFactory.proxyProvideOkHttpClientWrapper(this.cloudDriveModule));
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommandProcessor getCommandProcessor() {
        return this.providesCommandProcessorProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsAccessorySessionListener getCommsAccessorySessionListener() {
        return this.providesCommsAccessorySessionListenerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsActivityMonitor getCommsActivityMonitor() {
        return this.providesCommsActivityMonitorProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AlexaServicesConnection getCommsAlexaServicesConnection() {
        return this.providesCommsAlexaServicesConnectionProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsAudioInteraction getCommsAudioInteraction() {
        return this.providesCommsAudioInteractionProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsConnectivityMonitor getCommsConnectivityMonitor() {
        return this.providesCommsConnectivityMonitorProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsDeviceSupport getCommsDeviceSupport() {
        return this.providesCommsDeviceSupportProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsDirectiveHandler getCommsDirectiveHandler() {
        return new CommsDirectiveHandler(this.providesContextProvider.mo10268get(), this.providesCommsIdentityManagerProvider.mo10268get(), DoubleCheck.lazy(this.providesCallManagerProvider), this.providesVoxUtilsProvider.mo10268get(), this.providesApplicationManagerProvider.mo10268get(), getCallingFacade(), this.providesCapabilitiesManagerProvider.mo10268get(), this.providesSipClientStateProvider.mo10268get(), this.callContextProvider.mo10268get(), this.nameChangeObservableProvider.mo10268get(), this.enhancedProcessingStateObservableProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsDisposableManager getCommsDisposableManager() {
        return this.providesCommsDisposableManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsDriveModeCardProvider getCommsDriveModeCardProvider() {
        return this.providesCommsDriveModeCardProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsDynamicFeatureService getCommsDynamicFeatureService() {
        return this.providesCommsDynamicFeatureServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsIdentityManager getCommsIdentityManager() {
        return this.providesCommsIdentityManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsIdentityStore getCommsIdentityStore() {
        return this.providesLegacyCommsIdentityStoreProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsIdentityStoreV2Impl getCommsIdentityStoreV2Impl() {
        return this.providesCommsIdentityStoreV2Provider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsInternal getCommsInternal() {
        return this.providesCommsInternalProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsManager getCommsManager() {
        return this.providesCommsManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsNotificationManager getCommsNotificationManager() {
        return this.providesCommsNotificationManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public CommsRequestAuthenticator getCommsRequestAuthenticator() {
        return CloudDriveModule_ProvideRequestAuthenticatorFactory.proxyProvideRequestAuthenticator(this.cloudDriveModule, this.providesApplicationManagerProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ContactsOperationsManager getContactsOperationsManager() {
        return this.providesContactsOperationsManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ContentSharingService getContentSharingService() {
        return this.providesContentSharingServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public Context getContext() {
        return this.providesContextProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ConversationMessageTracker getConversationMessageTracker() {
        return this.providesConversationMessageTrackerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ConversationService getConversationService() {
        return this.providesConversationServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public SipClientState getCurrentCallSipClientState() {
        return this.providesSipClientStateProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DefaultNoCallPermissionHandler getDefaultNoCallPermissionHandler() {
        return this.provideDefaultNoCallPermissionHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DeviceStateManager getDeviceStateManager() {
        return this.providesDeviceStateManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DeviceUtils getDeviceUtils() {
        return this.providesDeviceUtilsProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DevicesSource getDevicesSource() {
        return this.providesDevicesSourceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DriveModeCallPermissionHandler getDriveModeCallingPermissionHandler() {
        return this.provideDriveModeNoPermissionHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DriveModeEventHandler getDriveModeEventHandler() {
        return new DriveModeEventHandler(getDriveModeSharedPreferencesUseCase(), this.providesMetricsServiceProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public DriveModeSharedPreferencesUseCase getDriveModeSharedPreferencesUseCase() {
        return new DriveModeSharedPreferencesUseCase(getCommsSharedPreferences());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public EndNativeCallHandler getEndNativeCallHandler() {
        return this.providesEndNativeCallHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public EndpointsCache getEndpointsCache() {
        return CloudDriveModule_ProvideEndpointsCacheFactory.proxyProvideEndpointsCache(this.cloudDriveModule, this.providesContextProvider.mo10268get(), this.providesIdentityServiceProvider.mo10268get(), this.providesEventBusProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public EventBus getEventBus() {
        return this.providesEventBusProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public FeatureFilter getFeatureFilter() {
        return this.providesFeatureFilterProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public FeatureFlagManager getFeatureFlagManager() {
        return this.providesFeatureFlagManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public FileTransmitter getFileTransmitter() {
        return this.providesFileTransmitterProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public FireOSDirectiveHandlerService getFireOSDirectiveHandlerService() {
        return injectFireOSDirectiveHandlerService(FireOSDirectiveHandlerService_Factory.newFireOSDirectiveHandlerService());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public IdentityService getIdentityService() {
        return this.providesIdentityServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public InitiationLogicFactory getInitiationLogicFactory() {
        return new InitiationLogicFactory(this.providesCapabilitiesManagerProvider.mo10268get(), getCallingFacade(), this.providesCommsIdentityManagerProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MainActivityLoader getMainActivityLoader() {
        return new MainActivityLoader();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MakeNativeCallHandler getMakeCallHandler() {
        return this.providesMakeNativeCallHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MediaContentManager getMediaContentManager() {
        return this.providesMediaContentManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MessagingControllerContextProvider getMessagingControllerContextProvider() {
        return this.providesMessagingControllerContextProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MessagingControllerManager getMessagingControllerManager() {
        return this.providesMessagingControllerManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MessagingReceiver getMessagingReceiver() {
        return this.provideConversationMessagingReceiverProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MetricsManager getMetricsManager() {
        return this.providesMetricsManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MetricsService getMetricsService() {
        return this.providesMetricsServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public MigrationCommsIdentityStore getMigrationCommsIdentityStore() {
        return this.providesMigrationCommsIdentityStoreProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public AlexaServicesConnection getNativeCommsServicesConnection() {
        return this.providesMuffinAlexaServicesConnectionProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public NoCallPermissionHandler getNoCallingPermissionhandler() {
        return this.provideNoPermissionHandlerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public OOBEPersonManager getOOBEPersonManager() {
        return this.providesOOBEPersonUtilProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public OfflinePushNotificationRepository getOfflinePushNotificationRepository() {
        return this.providesOfflinePushNotificationRepositoryProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public OkHttpClientWrapper getOkHttpClientWrapper() {
        return CloudDriveModule_ProvideOkHttpClientWrapperFactory.proxyProvideOkHttpClientWrapper(this.cloudDriveModule);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public OobeService getOobeService() {
        return this.providesOobeServiceProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PCCContextProvider getPCCContextProvider() {
        return this.providesPCCContextProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PackageManager getPackageManager() {
        return AndroidModule_ProvidesPackageManagerFactory.proxyProvidesPackageManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PhoneAccount getPhoneAccount() {
        return this.providesPhoneAccountProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PhoneAccountHandle getPhoneAccountHandle() {
        return this.providesPhoneAccountHandleProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PhoneCallControllerManager getPhoneCallControllerManager() {
        return this.providesPhoneCallControllerManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PowerManager getPowerManager() {
        return AndroidModule_ProvidesPowerManagerFactory.proxyProvidesPowerManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public SipClientState getPreviousCallSipClientState() {
        return this.providesPreviousSipClientStateProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ProvisioningManager getProvisioningManager() {
        return this.providesProvisioningManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public PushNotificationManager getPushNotificationManager() {
        return this.providesPushNotificationManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public SMSFetchManager getSMSFetchManager() {
        return this.providesSMSFetchManagerProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public ShareSheetActivity getShareSheetActivity() {
        return this.providesShareSheetActivityProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public Stage getStage() {
        return this.providesStageProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public TelecomBridge getTelecomBridge() {
        return this.providesTelecomBridgeProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public TelecomManager getTelecomManager() {
        return AndroidModule_ProvidesTelecomManagerFactory.proxyProvidesTelecomManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public TelephonyManager getTelephonyManager() {
        return AndroidModule_ProvidesTelephonyManagerFactory.proxyProvidesTelephonyManager(this.androidModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public UnreadMessageCounter getUnreadMessageCounter() {
        return this.providesUnreadMessageCounterProvider.mo10268get();
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PhoneAccountHandle phoneAccountHandle) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AccountConfiguration accountConfiguration) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ClientConfiguration clientConfiguration) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(EndpointsCache endpointsCache) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler) {
        injectAccessoriesHardwareIntentHandler(accessoriesHardwareIntentHandler);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(VipCallingHandler vipCallingHandler) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AlexaMessageNotificationMonitor alexaMessageNotificationMonitor) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsAudioInteraction commsAudioInteraction) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(A2AConnectionEndpointHandler a2AConnectionEndpointHandler) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(A2AQueuedEvents a2AQueuedEvents) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PCCQueuedEvents pCCQueuedEvents) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsRequestAuthenticator commsRequestAuthenticator) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallingFacade callingFacade) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(EffectsMenuPresenter effectsMenuPresenter) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PCCDirectiveHandler pCCDirectiveHandler) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PhoneCallControllerManager phoneCallControllerManager) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallUIHandler callUIHandler) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(TelecomBridge telecomBridge) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ProximityLockHelper proximityLockHelper) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(Endpointer endpointer) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OkHttpClientWrapper okHttpClientWrapper) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallBottomSheetDialogFragment callBottomSheetDialogFragment) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(FeatureFlagManager featureFlagManager) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ReactBridgeSerializer reactBridgeSerializer) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PushNotificationManager pushNotificationManager) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ThemingUpdateUtil themingUpdateUtil) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContentSharingService contentSharingService) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(Map<String, Connection> map) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(Queue<String> queue) {
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public TimeoutHelper timeoutHelper() {
        return LibraryModule_ProvideTimeoutHelperFactory.proxyProvideTimeoutHelper(this.libraryModule);
    }

    private DaggerCommsComponent(Builder builder) {
        this.androidModule = builder.androidModule;
        this.libraryModule = builder.libraryModule;
        this.cloudDriveModule = builder.cloudDriveModule;
        this.sipModule = builder.sipModule;
        initialize(builder);
        initialize2(builder);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ActiveCallFragment activeCallFragment) {
        injectActiveCallFragment(activeCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ActiveVideoCallView activeVideoCallView) {
        injectActiveVideoCallView(activeVideoCallView);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AlexaCallNotificationMonitor alexaCallNotificationMonitor) {
        injectAlexaCallNotificationMonitor(alexaCallNotificationMonitor);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AudioDownloadService audioDownloadService) {
        injectAudioDownloadService(audioDownloadService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallActivity callActivity) {
        injectCallActivity(callActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallHelper callHelper) {
        injectCallHelper(callHelper);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallPermissionActivity callPermissionActivity) {
        injectCallPermissionActivity(callPermissionActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ChildContactCardFragment childContactCardFragment) {
        injectChildContactCardFragment(childContactCardFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CoboUtils.Dependencies dependencies) {
        injectDependencies(dependencies);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsCapabilityAgent commsCapabilityAgent) {
        injectCommsCapabilityAgent(commsCapabilityAgent);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsInternal commsInternal) {
        injectCommsInternal(commsInternal);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsMasterFragment commsMasterFragment) {
        injectCommsMasterFragment(commsMasterFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsServiceV2Impl commsServiceV2Impl) {
        injectCommsServiceV2Impl(commsServiceV2Impl);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ConfigurationSyncService configurationSyncService) {
        injectConfigurationSyncService(configurationSyncService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactCardFragment contactCardFragment) {
        injectContactCardFragment(contactCardFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactDownloader contactDownloader) {
        injectContactDownloader(contactDownloader);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactListFragment contactListFragment) {
        injectContactListFragment(contactListFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactBlockFragment contactBlockFragment) {
        injectContactBlockFragment(contactBlockFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactStatusManager contactStatusManager) {
        injectContactStatusManager(contactStatusManager);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactsOperationHandler contactsOperationHandler) {
        injectContactsOperationHandler(contactsOperationHandler);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CreateNotificationService createNotificationService) {
        injectCreateNotificationService(createNotificationService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CVFFragment cVFFragment) {
        injectCVFFragment(cVFFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DeviceBottomSheet deviceBottomSheet) {
        injectDeviceBottomSheet(deviceBottomSheet);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DeviceCallingAndroidService deviceCallingAndroidService) {
        injectDeviceCallingAndroidService(deviceCallingAndroidService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(IncomingPushAndroidService incomingPushAndroidService) {
        injectIncomingPushAndroidService(incomingPushAndroidService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DeviceCallingServiceEventListener deviceCallingServiceEventListener) {
        injectDeviceCallingServiceEventListener(deviceCallingServiceEventListener);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DiagnosticScreen diagnosticScreen) {
        injectDiagnosticScreen(diagnosticScreen);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DialPadButton dialPadButton) {
        injectDialPadButton(dialPadButton);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(EditContactFragment editContactFragment) {
        injectEditContactFragment(editContactFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(EditNicknameFragment editNicknameFragment) {
        injectEditNicknameFragment(editNicknameFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(EndCallFragment endCallFragment) {
        injectEndCallFragment(endCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(GetOrCreateContact getOrCreateContact) {
        injectGetOrCreateContact(getOrCreateContact);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver) {
        injectHeadsetPluggedBroadcastReceiver(headsetPluggedBroadcastReceiver);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(IncomingCallFragment incomingCallFragment) {
        injectIncomingCallFragment(incomingCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(InitiateCallService initiateCallService) {
        injectInitiateCallService(initiateCallService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(InternalCommsManager internalCommsManager) {
        injectInternalCommsManager(internalCommsManager);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MainOOBEFragment mainOOBEFragment) {
        injectMainOOBEFragment(mainOOBEFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ManageContactsFragment manageContactsFragment) {
        injectManageContactsFragment(manageContactsFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(AudioMessageSender audioMessageSender) {
        injectAudioMessageSender(audioMessageSender);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MessageReadStatusMarkerService messageReadStatusMarkerService) {
        injectMessageReadStatusMarkerService(messageReadStatusMarkerService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MessagingControllerContextProvider messagingControllerContextProvider) {
        injectMessagingControllerContextProvider(messagingControllerContextProvider);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MessagingControllerDirectiveHandler messagingControllerDirectiveHandler) {
        injectMessagingControllerDirectiveHandler(messagingControllerDirectiveHandler);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MessagingControllerManager messagingControllerManager) {
        injectMessagingControllerManager(messagingControllerManager);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MessagingProviderWrapper messagingProviderWrapper) {
        injectMessagingProviderWrapper(messagingProviderWrapper);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(NativeCallActivity nativeCallActivity) {
        injectNativeCallActivity(nativeCallActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(NotificationActivatedReceiver notificationActivatedReceiver) {
        injectNotificationActivatedReceiver(notificationActivatedReceiver);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OOBEActivity oOBEActivity) {
        injectOOBEActivity(oOBEActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OutgoingCallFragment outgoingCallFragment) {
        injectOutgoingCallFragment(outgoingCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PowerButtonPressReceiver powerButtonPressReceiver) {
        injectPowerButtonPressReceiver(powerButtonPressReceiver);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(PerformCallReconnectTask performCallReconnectTask) {
        injectPerformCallReconnectTask(performCallReconnectTask);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ProvisioningManager provisioningManager) {
        injectProvisioningManager(provisioningManager);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(RelationshipListFragment relationshipListFragment) {
        injectRelationshipListFragment(relationshipListFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(SendCallMetricsTask sendCallMetricsTask) {
        injectSendCallMetricsTask(sendCallMetricsTask);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(TranscriptionUpdateService transcriptionUpdateService) {
        injectTranscriptionUpdateService(transcriptionUpdateService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(WhitelistContactFragment whitelistContactFragment) {
        injectWhitelistContactFragment(whitelistContactFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ContactListAdapter contactListAdapter) {
        injectContactListAdapter(contactListAdapter);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(VoxUtils voxUtils) {
        injectVoxUtils(voxUtils);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(RingServiceBroadcastReceiver ringServiceBroadcastReceiver) {
        injectRingServiceBroadcastReceiver(ringServiceBroadcastReceiver);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(MigrationCommsIdentityStore migrationCommsIdentityStore) {
        injectMigrationCommsIdentityStore(migrationCommsIdentityStore);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CommsIdentityManagerImpl commsIdentityManagerImpl) {
        injectCommsIdentityManagerImpl(commsIdentityManagerImpl);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(TelecomConnectionService telecomConnectionService) {
        injectTelecomConnectionService(telecomConnectionService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(TelecomConnection telecomConnection) {
        injectTelecomConnection(telecomConnection);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(FireOSDirectiveHandlerService fireOSDirectiveHandlerService) {
        injectFireOSDirectiveHandlerService(fireOSDirectiveHandlerService);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ShareSheetActivity shareSheetActivity) {
        injectShareSheetActivity(shareSheetActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(NewCallActivity newCallActivity) {
        injectNewCallActivity(newCallActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DirectiveReceiverActivity directiveReceiverActivity) {
        injectDirectiveReceiverActivity(directiveReceiverActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallingInitiationActivity callingInitiationActivity) {
        injectCallingInitiationActivity(callingInitiationActivity);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(CallingFragmentFactory callingFragmentFactory) {
        injectCallingFragmentFactory(callingFragmentFactory);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment) {
        injectActiveEnhancedProcessingVideoCallFragment(activeEnhancedProcessingVideoCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment) {
        injectActiveEnhancedProcessingAudioCallFragment(activeEnhancedProcessingAudioCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment) {
        injectOutgoingEnhancedProcessingVideoCallFragment(outgoingEnhancedProcessingVideoCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment) {
        injectOutgoingEnhancedProcessingAudioCallFragment(outgoingEnhancedProcessingAudioCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment) {
        injectIncomingEnhancedProcessingAudioCallFragment(incomingEnhancedProcessingAudioCallFragment);
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(DialPad dialPad) {
        DialPad_MembersInjector.injectSipClientState(dialPad, this.providesSipClientStateProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(OOBEPersonManager oOBEPersonManager) {
        OOBEPersonManager_MembersInjector.injectMContext(oOBEPersonManager, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.deecomms.core.CommsComponent
    public void inject(COBOWarningActivity cOBOWarningActivity) {
        COBOWarningActivity_MembersInjector.injectInitiationLogicFactory(cOBOWarningActivity, getInitiationLogicFactory());
    }
}
