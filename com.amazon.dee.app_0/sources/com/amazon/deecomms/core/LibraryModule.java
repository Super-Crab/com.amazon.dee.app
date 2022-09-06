package com.amazon.deecomms.core;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.comms.metrics.MetricsManager;
import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.auth.CommsSharedPreferences;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallMetricsFactory;
import com.amazon.deecomms.calling.controller.CallPayloadValidator;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModelFactory;
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
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsDisposableManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.Endpointer;
import com.amazon.deecomms.common.network.PropertiesHelper;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.ConversationMessagingReceiver;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import com.amazon.deecomms.identity.CommsIdentityManagerImpl;
import com.amazon.deecomms.identity.CommsIdentityStore;
import com.amazon.deecomms.identity.CommsIdentityStoreV2Impl;
import com.amazon.deecomms.identity.MigrationCommsIdentityStore;
import com.amazon.deecomms.identity.ProfileSelectionServiceImpl;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioPlayer;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory;
import com.amazon.deecomms.media.photos.MediaContentManager;
import com.amazon.deecomms.messaging.controller.AudioStateManager;
import com.amazon.deecomms.messaging.tracking.ConversationMessageTracker;
import com.amazon.deecomms.messaging.tracking.SharedPrefConversationInfoStorage;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.state.DeviceStateManager;
import com.amazon.deecomms.notifications.InboundAnnouncementCoordinator;
import com.amazon.deecomms.notifications.NotificationLatencyMetricHelper;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import com.amazon.deecomms.notifications.util.OfflinePushNotificationRepository;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.services.OobeServiceImpl;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.deecomms.sharing.ShareSheetActivity;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSFetchManager;
import com.amazon.deecomms.util.BuildVersionProviderImpl;
import com.amazon.deecomms.util.IBuildVersionProvider;
import com.amazon.deecomms.util.TimeoutHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.internal.DoubleCheck;
import java.util.concurrent.Executors;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class LibraryModule {
    public static final String COMMS_ALEXA_SERVICE_CONNECTION = "commsAlexaServiceConnection";
    public static final String NATIVE_ALEXA_SERVICE_CONNECTION = "nativeAlexaServiceConnection";
    private final Handler mMetricHelperHandler = new Handler();

    @Provides
    @Singleton
    public CallStateListener provideCallStateListener(@NonNull Lazy<Context> lazy, @NonNull Lazy<CallManager> lazy2, @NonNull Lazy<CommsEventSender> lazy3, @NonNull @Named("nativeAlexaServiceConnection") Lazy<AlexaServicesConnection> lazy4, @NonNull Lazy<CommsAlexaServicesConnectionListener> lazy5, @NonNull Lazy<CapabilitiesManager> lazy6, @NonNull Lazy<PCCContextProvider> lazy7, @NonNull Lazy<PCCQueuedEvents> lazy8, @NonNull Lazy<PCCConnectionEndpointHandler> lazy9) {
        return new CallStateListener(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9);
    }

    @Provides
    @Singleton
    public MessagingReceiver provideConversationMessagingReceiver(@NonNull CommsManager commsManager, @NonNull EventBus eventBus) {
        return new ConversationMessagingReceiver(commsManager, eventBus);
    }

    @Provides
    @Singleton
    public DefaultNoCallPermissionHandler provideDefaultNoCallPermissionHandler(@NonNull Context context) {
        return new DefaultNoCallPermissionHandler(context);
    }

    @Provides
    @Singleton
    public DriveModeCallPermissionHandler provideDriveModeNoPermissionHandler(@NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsEventSender commsEventSender, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull PCCContextProvider pCCContextProvider, @NonNull Context context, @NonNull PCCQueuedEvents pCCQueuedEvents, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return new DriveModeCallPermissionHandler(alexaServicesConnection, capabilitiesManager, commsEventSender, commsAlexaServicesConnectionListener, pCCContextProvider, context, pCCQueuedEvents, pCCConnectionEndpointHandler);
    }

    @Provides
    @Singleton
    public NoCallPermissionHandler provideNoPermissionHandler(@NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsEventSender commsEventSender, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull PCCContextProvider pCCContextProvider, @NonNull Context context, @NonNull PCCQueuedEvents pCCQueuedEvents, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return new NoCallPermissionHandler(alexaServicesConnection, capabilitiesManager, commsEventSender, commsAlexaServicesConnectionListener, pCCContextProvider, context, pCCQueuedEvents, pCCConnectionEndpointHandler);
    }

    @Provides
    public TimeoutHelper provideTimeoutHelper() {
        return new TimeoutHelper();
    }

    @Provides
    @Singleton
    public A2AConnectionEndpointHandler providesA2AConnectionEnpointHandler(@NonNull CallManager callManager) {
        return new A2AConnectionEndpointHandler(callManager);
    }

    @Provides
    @Singleton
    public A2AQueuedEvents providesA2AUnsentEventsManager() {
        return new A2AQueuedEvents();
    }

    @Provides
    @Singleton
    public AcceptNativeCallHandler providesAcceptNativeCallHandler(@NonNull NoCallPermissionHandler noCallPermissionHandler, @NonNull PCCContextProvider pCCContextProvider, @NonNull TelecomManager telecomManager, @NonNull Context context) {
        return new AcceptNativeCallHandler(noCallPermissionHandler, pCCContextProvider, telecomManager, context);
    }

    @Provides
    @Singleton
    public AccessoriesHardwareIntentHandler providesAccessoriesHardwareIntentHandler() {
        return new AccessoriesHardwareIntentHandler();
    }

    @Provides
    @Singleton
    public ActivitiesManager providesActivitiesManager() {
        return new ActivitiesManager();
    }

    @Provides
    @Singleton
    public AlexaAudioPlayer providesAlexaAudioPlayer(@NonNull Context context) {
        return new AlexaAudioPlayer(context);
    }

    @Provides
    public AppUrl providesAppUrl(ArcusConfig arcusConfig) {
        return new AppUrl(new Endpointer(arcusConfig));
    }

    @Provides
    @Singleton
    public ArcusConfig providesArcusConfig(Context context, Stage stage) {
        return new ArcusConfig(context, stage);
    }

    @Provides
    @Singleton
    public AudioContentManager providesAudioContentManager(Context context) {
        return new AudioContentManager(context);
    }

    @Provides
    @Singleton
    public AudioPlayer providesAudioPlayer(Context context, AudioManager audioManager) {
        return new AudioPlayer(context, audioManager);
    }

    @Provides
    @Singleton
    public AudioRecorder providesAudioRecorder(AudioManager audioManager) {
        return new AudioRecorder(audioManager);
    }

    @Provides
    @Singleton
    public AudioStateManager providesAudioStateManager() {
        return new AudioStateManager();
    }

    @Provides
    @Singleton
    public IBuildVersionProvider providesBuildVersion() {
        return new BuildVersionProviderImpl();
    }

    @Provides
    public CallHelper providesCallHelper() {
        return new CallHelper();
    }

    @Provides
    @Singleton
    public CallHistoryHelper providesCallHistoryHelper() {
        return new CallHistoryHelper();
    }

    @Provides
    @Singleton
    public CallManager providesCallManager(Context context, @Named("CurrentCall") SipClientState sipClientState, ApplicationManager applicationManager, ModeSwitchHelper modeSwitchHelper, CallPayloadValidator callPayloadValidator, @Named("PreviousCall") SipClientState sipClientState2) {
        return new CallManager(context, sipClientState, sipClientState2, applicationManager, callPayloadValidator, modeSwitchHelper);
    }

    @Provides
    @Singleton
    public CallMetricsFactory providesCallMetricsFactory(@NonNull CallHistoryHelper callHistoryHelper, @NonNull ApplicationManager applicationManager, @NonNull Context context) {
        return new CallMetricsFactory(callHistoryHelper, applicationManager, context);
    }

    @Provides
    @Singleton
    public CallTimerManager providesCallTimerManager(Context context) {
        return new CallTimerManager(context);
    }

    @Provides
    @Singleton
    public CallUIHandler providesCallUIHandler(@NonNull ActivitiesManager activitiesManager, @NonNull CallInitiationAuthority callInitiationAuthority) {
        return new CallUIHandler(activitiesManager, callInitiationAuthority);
    }

    @Provides
    @Singleton
    public CapabilitiesManager providesCapabilitiesManager(FeatureFlagManager featureFlagManager, Context context) {
        return new CapabilitiesManager(featureFlagManager, context);
    }

    @Provides
    @Singleton
    public CommsAccessorySessionListener providesCommsAccessorySessionListener(@NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull Context context, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CallManager callManager, @NonNull CommsEventSender commsEventSender, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return new CommsAccessorySessionListener(alexaServicesConnection, commsAlexaServicesConnectionListener, context, capabilitiesManager, callManager, commsEventSender, pCCConnectionEndpointHandler);
    }

    @Provides
    @Singleton
    public CommsActivityMonitor providesCommsActivityMonitor(@NonNull Context context, @NonNull AlarmManager alarmManager, @NonNull ActivityManager activityManager) {
        return new CommsActivityMonitor(context, alarmManager, activityManager);
    }

    @Provides
    @Singleton
    @Named(COMMS_ALEXA_SERVICE_CONNECTION)
    public AlexaServicesConnection providesCommsAlexaServicesConnection(@NonNull Context context) {
        return new AlexaServicesConnection(context);
    }

    @Provides
    @Singleton
    public CommsAlexaServicesConnectionListener providesCommsAlexaServicesConnectionListener(@NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull Context context) {
        return new CommsAlexaServicesConnectionListener(alexaServicesConnection, context);
    }

    @Provides
    @Singleton
    public CommsAudioInteraction providesCommsAudioInteraction() {
        return new CommsAudioInteraction();
    }

    @Provides
    @Singleton
    public CommsConnectivityMonitor providesCommsConnectivityMonitor(ConnectivityManager connectivityManager, PackageManager packageManager, TelephonyManager telephonyManager) {
        return new CommsConnectivityMonitor(connectivityManager, packageManager, telephonyManager);
    }

    @Provides
    @Singleton
    public CommsDisposableManager providesCommsDisposableManager() {
        return new CommsDisposableManager();
    }

    @Provides
    @Singleton
    public CommsDriveModeCardProvider providesCommsDriveModeCardProvider(@NonNull Context context, @NonNull IEventSender iEventSender, @NonNull @Named("commsAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager) {
        return new CommsDriveModeCardProvider(context, iEventSender, alexaServicesConnection, capabilitiesManager);
    }

    @Provides
    @Singleton
    public CommsEventSender providesCommsEventSender(@NonNull CommsAudioInteraction commsAudioInteraction, @NonNull CommsAudioInteractionScheduler commsAudioInteractionScheduler) {
        return new CommsEventSender(commsAudioInteraction, commsAudioInteractionScheduler);
    }

    @Provides
    @Singleton
    public CommsIdentityManager providesCommsIdentityManager() {
        return new CommsIdentityManagerImpl();
    }

    @Provides
    @Singleton
    public CommsIdentityStoreV2Impl providesCommsIdentityStoreV2(Context context) {
        return new CommsIdentityStoreV2Impl(context);
    }

    @Provides
    @Singleton
    public CommsInternal providesCommsInternal() {
        return new CommsInternal();
    }

    @Provides
    @Singleton
    public CommsNotificationManager providesCommsNotificationManager(Context context, NotificationManager notificationManager, @Named("CurrentCall") SipClientState sipClientState) {
        return new CommsNotificationManager(context, notificationManager, sipClientState);
    }

    @Provides
    @Singleton
    public ContactsOperationsManager providesContactsOperationsManager(Context context) {
        return new ContactsOperationsManager(context);
    }

    @Provides
    @Singleton
    public ContentSharingService providesContentSharingService(@NonNull Lazy<CommsIdentityManager> lazy, @NonNull Lazy<CapabilitiesManager> lazy2, @NonNull Lazy<IdentityService> lazy3, @NonNull Lazy<Context> lazy4) {
        return new ContentSharingService(lazy, lazy2, lazy3, lazy4);
    }

    @Provides
    @Singleton
    public ConversationMessageTracker providesConversationMessageTracker(@NonNull Context context) {
        return new ConversationMessageTracker(new SharedPrefConversationInfoStorage(context));
    }

    @Provides
    @Singleton
    public DeviceMetadataStoreRegistrar providesDeviceMetadataStoreRegistrar(@NonNull Context context, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CapabilitiesManager capabilitiesManager) {
        return new DeviceMetadataStoreRegistrar(context, commsIdentityManager, capabilitiesManager);
    }

    @Provides
    @Singleton
    public DeviceStateManager providesDeviceStateManager() {
        return new DeviceStateManager();
    }

    @Provides
    @Singleton
    public DeviceUtils providesDeviceUtils(@NonNull Context context, @NonNull SecuredSharedPreference securedSharedPreference) {
        return new DeviceUtils(context, securedSharedPreference);
    }

    @Provides
    @Singleton
    public DevicesSource providesDevicesSource() {
        return new DevicesSource();
    }

    @Provides
    @Singleton
    public EndNativeCallHandler providesEndNativeCallHandler(@NonNull NoCallPermissionHandler noCallPermissionHandler, @NonNull PCCContextProvider pCCContextProvider, @NonNull TelecomManager telecomManager, @NonNull Context context) {
        return new EndNativeCallHandler(noCallPermissionHandler, pCCContextProvider, telecomManager, context);
    }

    @Provides
    @Singleton
    public EventBus providesEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }

    @Provides
    @Singleton
    public FeatureFlagManager providesFeatureFlagManager() {
        return new FeatureFlagManager(DoubleCheck.lazy(ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class)));
    }

    @Provides
    @Singleton
    public FileTransmitter providesFileTransmitter(Context context, IdentityService identityService, CommsIdentityManager commsIdentityManager, ClientConfiguration clientConfiguration, MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, EndpointsCache endpointsCache, AccountConfiguration accountConfiguration, EventBus eventBus) {
        return new FileTransmitter(context, clientConfiguration, mAPAuthenticatedURLConnectionFactory, endpointsCache, identityService, commsIdentityManager, accountConfiguration, Executors.newSingleThreadExecutor(), eventBus);
    }

    @Provides
    @Singleton
    public InCallCommandModelFactory providesInCallCommandModelFactory(@NonNull Context context, @NonNull CommsManager commsManager) {
        return new InCallCommandModelFactory(context, commsManager);
    }

    @Provides
    @Singleton
    public InboundAnnouncementCoordinator providesInboundAnnouncementsCoordinator(@NonNull Context context, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsAccessorySessionListener commsAccessorySessionListener, @NonNull CallManager callManager, @NonNull AudioRecorder audioRecorder, @NonNull MetricsService metricsService, @NonNull CommsIdentityManager commsIdentityManager) {
        return new InboundAnnouncementCoordinator(context, capabilitiesManager, commsAccessorySessionListener, callManager, audioRecorder, metricsService, commsIdentityManager);
    }

    @Provides
    @Singleton
    public CommsIdentityStore providesLegacyCommsIdentityStore(Context context) {
        return new CommsIdentityStore(context);
    }

    @Provides
    @Singleton
    public MakeNativeCallHandler providesMakeNativeCallHandler(@NonNull Context context) {
        return new MakeNativeCallHandler(context);
    }

    @Provides
    @Singleton
    public MediaContentManager providesMediaContentManager(Context context) {
        return new MediaContentManager(context);
    }

    @Provides
    @Singleton
    public MessagingControllerContextProvider providesMessagingControllerContextProvider() {
        return new MessagingControllerContextProvider();
    }

    @Provides
    @Singleton
    public MessagingControllerManager providesMessagingControllerManager(@NonNull CapabilitiesManager capabilitiesManager, @NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull MessagingControllerContextProvider messagingControllerContextProvider) {
        return new MessagingControllerManager(capabilitiesManager, alexaServicesConnection, messagingControllerContextProvider);
    }

    @Provides
    @Singleton
    public MetricsManager providesMetricsManager(Context context) {
        return new MetricsManager(context, Constants.LIB_NAME);
    }

    @Provides
    @Singleton
    public MetricsService providesMetricsService(@NonNull CommsIdentityManager commsIdentityManager, @NonNull ApplicationManager applicationManager, @NonNull DeviceUtils deviceUtils, @NonNull CommsInternal commsInternal, @NonNull Context context) {
        return new MetricsService(commsIdentityManager, applicationManager, deviceUtils, commsInternal, context);
    }

    @Provides
    @Singleton
    public MigrationCommsIdentityStore providesMigrationCommsIdentityStore() {
        return new MigrationCommsIdentityStore();
    }

    @Provides
    @Singleton
    @Named(NATIVE_ALEXA_SERVICE_CONNECTION)
    public AlexaServicesConnection providesMuffinAlexaServicesConnection(@NonNull Context context) {
        return new AlexaServicesConnection(context);
    }

    @Provides
    @Singleton
    public NotificationLatencyMetricHelper providesNotificationLatencyMetricHelper() {
        return new NotificationLatencyMetricHelper(this.mMetricHelperHandler);
    }

    @Provides
    @Singleton
    public OOBEPersonManager providesOOBEPersonUtil() {
        return new OOBEPersonManager();
    }

    @Provides
    @Singleton
    public OfflinePushNotificationRepository providesOfflinePushNotificationRepository() {
        return new OfflinePushNotificationRepository();
    }

    @Provides
    @Singleton
    public OobeService providesOobeService(@NonNull Context context, @NonNull Lazy<CommsIdentityManager> lazy, @NonNull Lazy<CommsDeviceSupport> lazy2, @NonNull Lazy<ConversationService> lazy3, @NonNull Lazy<CommsManager> lazy4, @NonNull Lazy<CapabilitiesManager> lazy5, @NonNull Lazy<EventBus> lazy6) {
        return new OobeServiceImpl(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
    }

    @Provides
    @Singleton
    public PCCConnectionEndpointHandler providesPCCConnectionEnpointHandler(@NonNull CapabilitiesManager capabilitiesManager, @NonNull CallManager callManager) {
        return new PCCConnectionEndpointHandler(capabilitiesManager, callManager);
    }

    @Provides
    @Singleton
    public PCCContextProvider providesPCCContextProvider() {
        return new PCCContextProvider();
    }

    @Provides
    @Singleton
    public PCCDirectiveHandler providesPCCDirectiveHandler(@NonNull CapabilitiesManager capabilitiesManager, @NonNull PCCContextProvider pCCContextProvider, @NonNull TelecomManager telecomManager, @NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CommsEventSender commsEventSender, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull AcceptNativeCallHandler acceptNativeCallHandler, @NonNull EndNativeCallHandler endNativeCallHandler, @NonNull NoCallPermissionHandler noCallPermissionHandler, @NonNull Context context) {
        return new PCCDirectiveHandler(capabilitiesManager, makeNativeCallHandler, acceptNativeCallHandler, endNativeCallHandler, noCallPermissionHandler);
    }

    @Provides
    @Singleton
    public PCCQueuedEvents providesPCCUnsentEventsManager(@NonNull CommsEventSender commsEventSender, @NonNull @Named("nativeAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        return new PCCQueuedEvents(commsEventSender, alexaServicesConnection, pCCConnectionEndpointHandler);
    }

    @Provides
    @Singleton
    public PhoneCallControllerManager providesPhoneCallControllerManager(@NonNull @Named("nativeAlexaServiceConnection") Lazy<AlexaServicesConnection> lazy, @NonNull Lazy<TelephonyManager> lazy2, @NonNull Lazy<CallStateListener> lazy3, @NonNull Lazy<Context> lazy4, @NonNull Lazy<PCCContextProvider> lazy5, @NonNull Lazy<PCCQueuedEvents> lazy6, @NonNull Lazy<CommsAudioInteractionScheduler> lazy7) {
        return new PhoneCallControllerManager(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7);
    }

    @Provides
    @Singleton
    public ProfileSelectionService providesProfileSelectionService(@NonNull Context context, @NonNull Lazy<CapabilitiesManager> lazy, @NonNull Lazy<CommsIdentityManager> lazy2, @NonNull Lazy<DeviceMetadataStoreRegistrar> lazy3, @NonNull Lazy<OOBEPersonManager> lazy4) {
        return new ProfileSelectionServiceImpl(context, lazy, lazy2, lazy3, lazy4);
    }

    @Provides
    @Singleton
    public ProvisioningManager providesProvisioningManager(@NonNull Context context, @NonNull EventBus eventBus, @NonNull CapabilitiesManager capabilitiesManager) {
        return new ProvisioningManager(context, eventBus, capabilitiesManager);
    }

    @Provides
    @Singleton
    public PushNotificationManager providesPushNotificationManager(Context context, SecuredSharedPreference securedSharedPreference, CommsIdentityManager commsIdentityManager, ModeSwitchHelper modeSwitchHelper, CapabilitiesManager capabilitiesManager, DeviceUtils deviceUtils) {
        return new PushNotificationManager(context, securedSharedPreference, commsIdentityManager, modeSwitchHelper, capabilitiesManager, new IdentityPreferencesProvider(), deviceUtils, new DriveModeSharedPreferencesUseCase(new CommsSharedPreferences(context)));
    }

    @Provides
    @Singleton
    public SMSFetchManager providesSMSFetchManager(@NonNull Context context) {
        return new SMSFetchManager(context);
    }

    @Provides
    public SecuredSharedPreference providesSecuredSharedPreference(@NonNull Context context) {
        return new SecuredSharedPreference(context);
    }

    @Provides
    @Singleton
    public ShareSheetActivity providesShareSheetActivity() {
        return new ShareSheetActivity();
    }

    @Provides
    @Singleton
    public Stage providesStage(Context context) {
        return Stage.toStage(PropertiesHelper.getProperty(context, "app_stage", "prod"));
    }

    @Provides
    @Singleton
    public ThemingUpdateUtil providesThemingUpdateUtil() {
        return new ThemingUpdateUtil();
    }

    @Provides
    @Singleton
    public TranscriptLatencyMetricHelper providesTranscriptUpdateLatencyMetricHelper() {
        return new TranscriptLatencyMetricHelper(this.mMetricHelperHandler);
    }

    @Provides
    @Singleton
    public UnreadMessageCounter providesUnreadMessageCounter(@NonNull Context context) {
        return new UnreadMessageCounter(context);
    }
}
