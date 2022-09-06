package com.amazon.alexa.accessorykit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Supplier;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier;
import com.amazon.alexa.accessory.BuildStageProvider;
import com.amazon.alexa.accessory.DefaultAccessoryServiceConfigurationSupplier;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.avsclient.AccessoryDiagnosticsObserver;
import com.amazon.alexa.accessory.avsclient.AlexaScoPrioritizer;
import com.amazon.alexa.accessory.avsclient.IfAnyScoPrioritizer;
import com.amazon.alexa.accessory.avsclient.PhoneStateScoPrioritizer;
import com.amazon.alexa.accessory.avsclient.bootup.AccessoryBootupService;
import com.amazon.alexa.accessory.avsclient.bootup.DefaultDownChannelStatusMonitor;
import com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor;
import com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusPlugin;
import com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier;
import com.amazon.alexa.accessory.avsclient.context.AlexaIOComponentsSupplier;
import com.amazon.alexa.accessory.avsclient.context.AlexaTrustedStatesSupplier;
import com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher;
import com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier;
import com.amazon.alexa.accessory.avsclient.context.PhoneSecurityStateSupplier;
import com.amazon.alexa.accessory.avsclient.context.SingleBluetoothProfileWatcher;
import com.amazon.alexa.accessory.avsclient.locale.AccessoryLocaleSynchronizer;
import com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier;
import com.amazon.alexa.accessory.avsclient.locale.UnmatchedLocaleNotifier;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver;
import com.amazon.alexa.accessory.avsclient.multiturn_delay.DefaultMultiturnDelayProvider;
import com.amazon.alexa.accessory.avsclient.utils.GsonJsonConverter;
import com.amazon.alexa.accessory.avsclient.utils.JsonConverter;
import com.amazon.alexa.accessory.capabilities.calling.CallRecipient;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsObserver;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.frames.TopContact;
import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.UnavailableCallRecipient;
import com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultA2dpPlayingMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultScoStatusMonitor;
import com.amazon.alexa.accessory.internal.monitor.DefaultVolumeChangedMonitor;
import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor;
import com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor;
import com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor;
import com.amazon.alexa.accessory.internal.session.DefaultSessionSupplier;
import com.amazon.alexa.accessory.internal.util.CircularMemoryProcessor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsSupplier;
import com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor;
import com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.FocusFilter;
import com.amazon.alexa.accessory.notifications.NotificationInteractor;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.registration.DMSRegistrationExecutor;
import com.amazon.alexa.accessory.registration.FileSystemRegistrationSupplier;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.DefaultDeviceAccountExecutor;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.registration.deviceaccount.FileSystemDeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessory.repositories.state.MemoryPhoneStateSupplier;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.plugins.A2dpPlayingPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.CallNotificationPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.MessageStatusPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.NetworkStatusPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.NotificationFilterStatusPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.RemoteNotificationPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.ScoStatusPlugin;
import com.amazon.alexa.accessory.repositories.state.plugins.VolumeChangedPlugin;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessory.speech.AccessorySpeechRecognizerV2;
import com.amazon.alexa.accessory.speech.AlexaConnectionAdvocate;
import com.amazon.alexa.accessory.speech.ambient_sound.DefaultAmbientSoundDispatcher;
import com.amazon.alexa.accessory.speech.bootup.AlexaReadinessSupplier;
import com.amazon.alexa.accessory.speech.context.AlexaServicesContextProvider;
import com.amazon.alexa.accessory.speech.events.statechange.StateChangeAccessoryManager;
import com.amazon.alexa.accessory.speech.events.statechange.StateChangeEventGenerator;
import com.amazon.alexa.accessory.speech.events.statechange.StateChangeEventSender;
import com.amazon.alexa.accessory.speech.events.statechange.StateReportEventGenerator;
import com.amazon.alexa.accessory.speech.events.statechange.StateReportEventSender;
import com.amazon.alexa.accessory.speech.locale.AlexaLocaleSupplier;
import com.amazon.alexa.accessory.speech.monitor.AvsRemoteNotificationMonitor;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.context.AccessoryContextProvider;
import com.amazon.alexa.accessory.speechapi.csm.CsmAlexaConnection;
import com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.cbl.CblInteractor;
import com.amazon.alexa.accessorykit.cbl.DefaultCblInteractor;
import com.amazon.alexa.accessorykit.endpoint.AlexaEndpointProvider;
import com.amazon.alexa.accessorykit.factory.DefaultDependenciesProvider;
import com.amazon.alexa.accessorykit.factory.DependenciesProvider;
import com.amazon.alexa.accessorykit.features.AccessoryFeatureChecker;
import com.amazon.alexa.accessorykit.findmy.AccessoryAndroidLocationProvider;
import com.amazon.alexa.accessorykit.findmy.FindMyAccessoryManager;
import com.amazon.alexa.accessorykit.findmy.LocationProvider;
import com.amazon.alexa.accessorykit.findmy.reporter.DefaultAccessoryLocationReporter;
import com.amazon.alexa.accessorykit.findmy.setting.DefaultSettingProvider;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupInteractor;
import com.amazon.alexa.accessorykit.finishsetup.InterProcessFASViewCoordinator;
import com.amazon.alexa.accessorykit.finishsetup.metrics.DefaultFinishSetupMetricsRecorder;
import com.amazon.alexa.accessorykit.finishsetup.persistence.AccessoryPersistentStorage;
import com.amazon.alexa.accessorykit.finishsetup.persistence.DefaultRecordSupplier;
import com.amazon.alexa.accessorykit.finishsetup.presenters.NotificationPresenter;
import com.amazon.alexa.accessorykit.inputevents.BroadcastInputEventsHandler;
import com.amazon.alexa.accessorykit.interprocess.DefaultCallNotificationStateMonitor;
import com.amazon.alexa.accessorykit.interprocess.DefaultMessageNotificationStateMonitor;
import com.amazon.alexa.accessorykit.interprocess.InterprocessAccessorySessionListener;
import com.amazon.alexa.accessorykit.interprocess.InterprocessMarketplaceService;
import com.amazon.alexa.accessorykit.interprocess.InterprocessModeStatusChecker;
import com.amazon.alexa.accessorykit.interprocess.InterprocessPhoneStatePlugin;
import com.amazon.alexa.accessorykit.interprocess.InterprocessPrivacyModeEmitter;
import com.amazon.alexa.accessorykit.interprocess.InterprocessUserSupplier;
import com.amazon.alexa.accessorykit.interprocess.ModeStatusEmitter;
import com.amazon.alexa.accessorykit.interprocess.PhoneStateBroadcastAdapter;
import com.amazon.alexa.accessorykit.interprocess.environment.AccessoryEnvironmentService;
import com.amazon.alexa.accessorykit.interprocess.environment.EnvironmentServiceEmitter;
import com.amazon.alexa.accessorykit.interprocess.environment.InterprocessAccessoryEnvironmentService;
import com.amazon.alexa.accessorykit.interprocess.feature.InterprocessFeatureChecker;
import com.amazon.alexa.accessorykit.interprocess.identity.FileBackedPersonSupplier;
import com.amazon.alexa.accessorykit.interprocess.identity.PersonBroadcastEmitter;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsDeviceProvider;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsEndpointPicker;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsUserProvider;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.FileBackedMobilyticsDeviceSupplier;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.MobilyticsDeviceBroadcastEmitter;
import com.amazon.alexa.accessorykit.interprocess.network.AccessoryNetworkService;
import com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.metrics.RebounceMetricsObserver;
import com.amazon.alexa.accessorykit.metrics.SessionMetricsListener;
import com.amazon.alexa.accessorykit.mode.ModeStatusWrapper;
import com.amazon.alexa.accessorykit.nearmiss.MlisHttpClient;
import com.amazon.alexa.accessorykit.notifications.DefaultNotificationInteractor;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.map.AccountManagerFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.dee.app.dependencies.ServiceModule;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.Bugsnag;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.google.gson.Gson;
import dagger.Lazy;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
/* loaded from: classes6.dex */
public final class AccessoriesFactory {
    private static final String API_SERVICE_INTENT_LAUNCH_ACTION = "com.amazon.launch.apiservice";
    private static final long FEATURE_CHECKER_TIMEOUT_SECONDS = 4;
    private static final long MAIN_PROCESS_EMIT_DELAY_MILLIS = 5000;
    private static final String TAG = "AccessoriesFactory";
    private static CblInteractor cblInteractor = null;
    public static FeatureChecker featureChecker = null;
    private static FinishSetupInteractor finishSetupInteractor = null;
    private static FocusFilter focusFilter = null;
    private static ModeStatusEmitter modeStatusEmitter = null;
    private static TopContact topContact = null;
    private static final long userSupplierTimeoutSeconds = 30;
    private static Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private static DependenciesProvider dependenciesProvider = new DefaultDependenciesProvider();
    private static String appName = "com.amazon.dee.app";

    private AccessoriesFactory() {
        throw new IllegalStateException("No instances!");
    }

    @VisibleForTesting
    static void accessoriesObjectInit(long j, AccessoryMetricsService accessoryMetricsService) {
        long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - j, TimeUnit.NANOSECONDS);
        Logger.d("%s: accessories instantiation time (ms): %d", TAG, Long.valueOf(convert));
        accessoryMetricsService.recordTime(MetricsConstants.ColdStart.ACCESSORIES_INSTANTIATION_DURATION, MetricsConstants.ColdStart.ACCESSORIES_OBJECT, convert, null);
    }

    @VisibleForTesting
    static void accessoriesProcessIntegrationsInit(long j, AccessoryMetricsService accessoryMetricsService) {
        long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - j, TimeUnit.NANOSECONDS);
        Logger.d("%s: accessories-process integrations instantiation time (ms): %d", TAG, Long.valueOf(convert));
        accessoryMetricsService.recordTime(MetricsConstants.ColdStart.ACCESSORIES_INSTANTIATION_DURATION, MetricsConstants.ColdStart.ACCESSORIES_PROCESS_INTEGRATIONS, convert, null);
    }

    public static void activateAccessories(final Context context, final IdentityService identityService, Lazy<Mobilytics> lazy, final CallNotificationStateMonitor callNotificationStateMonitor, final MessageNotificationStateMonitor messageNotificationStateMonitor, Lazy<OkHttpClient> lazy2, Lazy<Cache<AppDataCacheEntry>> lazy3) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(lazy, DefaultKinesisConnector.COMPONENT);
        Preconditions.notNull(callNotificationStateMonitor, "callNotificationStateMonitor");
        Preconditions.notNull(messageNotificationStateMonitor, "messageNotificationStateMonitor");
        Preconditions.notNull(lazy2, "httpClient");
        Preconditions.notNull(lazy3, ServiceModule.DATA_STORE);
        long nanoTime = System.nanoTime();
        initializeAccessoryLogger();
        AccessoryMetricsService createAccessoryMetricsService = dependenciesProvider.createAccessoryMetricsService(lazy);
        featureChecker = new AccessoryFeatureChecker(ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class));
        Preconditions.notNull(ComponentRegistry.getInstance().get(EventBus.class), "eventBus");
        Preconditions.precondition(ComponentRegistry.getInstance().get(EventBus.class).isPresent(), "eventBus");
        EventBus eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).get();
        final AlexaUserSupplier alexaUserSupplier = new AlexaUserSupplier(identityService, createAccessoryMetricsService, userSupplierTimeoutSeconds, eventBus);
        mainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$V5LBy7mNCkNkabqHzwaps2TpM4U
            @Override // java.lang.Runnable
            public final void run() {
                AccessoriesFactory.emitMainProcessUserChangedEvents(context, alexaUserSupplier, identityService);
            }
        }, 5000L);
        modeStatusEmitter = new ModeStatusEmitter(context, eventBus, new ModeStatusWrapper((ModeService) ComponentRegistry.getInstance().get(ModeService.class).get(), featureChecker));
        mainThreadHandler.postDelayed($$Lambda$AccessoriesFactory$aMetvcboXwQOeSdN8M2r7jDZ0g.INSTANCE, 5000L);
        dependenciesProvider.initFocusFilterDependencies(context, lazy2, lazy3);
        final EnvironmentServiceEmitter environmentServiceEmitter = new EnvironmentServiceEmitter(context, (EnvironmentService) ComponentRegistry.getInstance().get(EnvironmentService.class).get(), alexaUserSupplier);
        mainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$O1glTiysl2XLVKxTQnZvi4NIY7c
            @Override // java.lang.Runnable
            public final void run() {
                EnvironmentServiceEmitter.this.activate();
            }
        }, 5000L);
        final NotificationFilterStatusPlugin notificationFilterStatusPlugin = new NotificationFilterStatusPlugin(new NotificationFilterServiceStatusMonitor(FeatureToggleModule.getInstance()));
        mainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$tFm57g7OhWOejSFXg62MH06C_6Q
            @Override // java.lang.Runnable
            public final void run() {
                AccessoriesFactory.emitMainProcessPhoneStates(context, AccessoriesFactory.createCallNotificationStatusPlugin(callNotificationStateMonitor), notificationFilterStatusPlugin, AccessoriesFactory.createMessageNotificationStatusPlugin(messageNotificationStateMonitor));
            }
        }, 5000L);
        ApplicationLifecycleObserverEventEmitter applicationLifecycleObserverEventEmitter = new ApplicationLifecycleObserverEventEmitter(context);
        ApplicationLifecycleObserverForFASCardPresenter applicationLifecycleObserverForFASCardPresenter = new ApplicationLifecycleObserverForFASCardPresenter(context);
        ApplicationLifecycleService applicationLifecycleService = (ApplicationLifecycleService) GeneratedOutlineSupport1.outline20(ApplicationLifecycleService.class);
        applicationLifecycleService.addObserver(applicationLifecycleObserverEventEmitter);
        applicationLifecycleService.addObserver(applicationLifecycleObserverForFASCardPresenter);
        mainThreadHandler.post($$Lambda$AccessoriesFactory$Ny5yUU0mZIZWltywT617OsPNQ4.INSTANCE);
        mainThreadHandler.post($$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZnVM.INSTANCE);
        ComponentRegistry.getInstance().get(ConnectedAccessoryInquirer.class).get();
        mainProcessIntegrationsInit(nanoTime, createAccessoryMetricsService);
    }

    private static void activateAccessoryBootupService(ReadinessSupplier readinessSupplier, SessionSupplier sessionSupplier, AccessoryMetricsService accessoryMetricsService) {
        new AccessoryBootupService(readinessSupplier, sessionSupplier, accessoryMetricsService).activate();
    }

    private static void activateAccessoryLocaleSynchronizer(Context context, LocaleSupplier localeSupplier, SessionSupplier sessionSupplier, NotificationInteractor notificationInteractor, FeatureChecker featureChecker2, UserSupplier userSupplier, DeviceSupplier deviceSupplier) {
        new AccessoryLocaleSynchronizer(localeSupplier, sessionSupplier, new UnmatchedLocaleNotifier(context, notificationInteractor, featureChecker2, userSupplier, deviceSupplier)).activate();
    }

    private static void activateFindMyAccessoryManager(Context context, UserSupplier userSupplier, SessionSupplier sessionSupplier, MarketplaceService marketplaceService, BuildStageProvider buildStageProvider, AccessoryMetricsService accessoryMetricsService, DeviceSupplier deviceSupplier, FeatureChecker featureChecker2, DeviceAccountSupplier deviceAccountSupplier, AccessoryEnvironmentService accessoryEnvironmentService) {
        LocationProvider createAccessoryGooglePlayLocationProvider;
        if (isFireOS()) {
            createAccessoryGooglePlayLocationProvider = new AccessoryAndroidLocationProvider(context, (LocationManager) context.getSystemService("location"));
        } else {
            createAccessoryGooglePlayLocationProvider = dependenciesProvider.createAccessoryGooglePlayLocationProvider(context);
        }
        AlexaEndpointProvider alexaEndpointProvider = new AlexaEndpointProvider(accessoryEnvironmentService);
        new FindMyAccessoryManager(sessionSupplier, deviceSupplier, createAccessoryGooglePlayLocationProvider, new DefaultAccessoryLocationReporter(userSupplier, marketplaceService, buildStageProvider, alexaEndpointProvider), accessoryMetricsService, featureChecker2, new DefaultSettingProvider(userSupplier, alexaEndpointProvider), deviceAccountSupplier).activate();
    }

    private static void activateStateChangeAccessoryManager(SessionSupplier sessionSupplier, Gson gson, AlexaConnection alexaConnection, AccessoryMetricsService accessoryMetricsService, UserSupplier userSupplier, RegistrationSupplier registrationSupplier, FeatureChecker featureChecker2) {
        new StateChangeAccessoryManager(sessionSupplier, new StateChangeEventSender(alexaConnection, sessionSupplier), new StateChangeEventGenerator(createJsonConverter(gson)), accessoryMetricsService, userSupplier, registrationSupplier, featureChecker2).activate();
    }

    private static A2dpPlayingMonitor createA2dpPlayingMonitor(Context context) {
        return new DefaultA2dpPlayingMonitor(context);
    }

    private static A2dpPlayingPlugin createA2dpPlayingPlugin(A2dpPlayingMonitor a2dpPlayingMonitor) {
        return new A2dpPlayingPlugin(a2dpPlayingMonitor);
    }

    public static Accessories createAccessories(final Context context, AlexaServicesConnection alexaServicesConnection, String str, String str2, boolean z, int i) {
        AlexaConnection voxAlexaConnection;
        Preconditions.notNull(context, "context");
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection");
        Preconditions.notNull(str, "mobilyticsApplicationId");
        Preconditions.notNull(str2, "mobilyticsServiceName");
        long nanoTime = System.nanoTime();
        appName = context.getPackageName();
        initializeAccessoryLogger();
        initializeBugsnagNotification();
        Gson gson = new Gson();
        InterprocessFeatureChecker createInterProcessFeatureChecker = dependenciesProvider.createInterProcessFeatureChecker(context);
        AccountManagerWrapper accountManagerWrapper = new AccountManagerWrapper(new AccountManagerWrapper.AccountManagerProducer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$8yEmxJfpA9qE24EfWRIcDl1EuTY
            @Override // com.amazon.alexa.accessorykit.interprocess.utils.AccountManagerWrapper.AccountManagerProducer
            public final AccountManager getAccountManager() {
                AccountManager create;
                create = AccountManagerFactory.create(context);
                return create;
            }
        });
        InterprocessUserSupplier interprocessUserSupplier = new InterprocessUserSupplier(context, accountManagerWrapper);
        InterprocessMarketplaceService interprocessMarketplaceService = new InterprocessMarketplaceService(accountManagerWrapper);
        AccessoryMobilyticsUserProvider activate = new AccessoryMobilyticsUserProvider(interprocessUserSupplier, new FileBackedPersonSupplier(context), interprocessMarketplaceService, createInterProcessFeatureChecker).activate();
        AccessoryMobilyticsDeviceProvider accessoryMobilyticsDeviceProvider = new AccessoryMobilyticsDeviceProvider(new FileBackedMobilyticsDeviceSupplier(context));
        AccessoryMetricsService createAccessoryMetricsService = dependenciesProvider.createAccessoryMetricsService(dependenciesProvider.createMobilytics(MobilyticsConfiguration.builder().withContext(context).withApplicationId(str).withServiceName(str2).withDebug(z).withDomain(i).withDeviceProvider(accessoryMobilyticsDeviceProvider).withUserProvider(activate).withEndpointPicker(new AccessoryMobilyticsEndpointPicker(context))));
        accountManagerWrapper.setAccessoryMetricsService(createAccessoryMetricsService);
        interprocessUserSupplier.setAccessoryMetricsService(createAccessoryMetricsService);
        final InterprocessAccessoryEnvironmentService interprocessAccessoryEnvironmentService = new InterprocessAccessoryEnvironmentService(context);
        BuildStageProvider buildStageProvider = new BuildStageProvider() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$elQooPW2r6S1_3Kfx-eEA5U9sF0
            @Override // com.amazon.alexa.accessory.BuildStageProvider
            public final BuildStageProvider.BuildStage getBuildStage() {
                BuildStageProvider.BuildStage buildStage;
                buildStage = BuildStageProvider.BuildStage.getBuildStage(AccessoryEnvironmentService.this.getBuildStage());
                return buildStage;
            }
        };
        AlexaDeviceManufacturerSupplier alexaDeviceManufacturerSupplier = new AlexaDeviceManufacturerSupplier();
        AccessoryMetricsSupplier accessoryMetricsSupplier = new AccessoryMetricsSupplier();
        MetricsObserver createMetricsObserver = createMetricsObserver(createAccessoryMetricsService, accessoryMetricsSupplier);
        SingleBluetoothProfileWatcher singleBluetoothProfileWatcher = new SingleBluetoothProfileWatcher(context);
        MlisClient createMlisClient = createMlisClient(context, interprocessUserSupplier);
        DeviceSupplier createDeviceSupplier = dependenciesProvider.createDeviceSupplier(context);
        FileSystemRegistrationSupplier fileSystemRegistrationSupplier = new FileSystemRegistrationSupplier(context, interprocessUserSupplier, createDeviceSupplier, alexaDeviceManufacturerSupplier, new DMSRegistrationExecutor(buildStageProvider, createDeviceSupplier));
        DefaultSessionSupplier defaultSessionSupplier = new DefaultSessionSupplier();
        defaultSessionSupplier.addSessionListener(new SessionMetricsListener(createAccessoryMetricsService, createDeviceSupplier));
        defaultSessionSupplier.addSessionListener(new InterprocessAccessorySessionListener(context));
        AlexaIOComponentsSupplier alexaIOComponentsSupplier = new AlexaIOComponentsSupplier(fileSystemRegistrationSupplier, defaultSessionSupplier, interprocessUserSupplier);
        if (shouldUseCsmAlexaConnection(context)) {
            Logger.d("AccessoriesFactory: using CsmAlexaConnection");
            voxAlexaConnection = new CsmAlexaConnection(context);
        } else {
            Logger.d("AccessoriesFactory: using VoxAlexaConnection");
            voxAlexaConnection = new VoxAlexaConnection(alexaServicesConnection);
        }
        AlexaConnection alexaConnection = voxAlexaConnection;
        AccessorySpeechRecognizerV2 createSpeechRecognizerV2 = createSpeechRecognizerV2(alexaConnection, createAccessoryContextProviderForSpeechApi(gson, singleBluetoothProfileWatcher, alexaIOComponentsSupplier, defaultSessionSupplier, fileSystemRegistrationSupplier, context, alexaConnection), createMlisClient, fileSystemRegistrationSupplier, defaultSessionSupplier, context, createInterProcessFeatureChecker);
        AlexaReadinessSupplier alexaReadinessSupplier = new AlexaReadinessSupplier(alexaConnection);
        LocaleSupplier createLocaleSupplier = createLocaleSupplier(alexaConnection);
        activateStateChangeAccessoryManager(defaultSessionSupplier, gson, alexaConnection, createAccessoryMetricsService, interprocessUserSupplier, fileSystemRegistrationSupplier, createInterProcessFeatureChecker);
        RemoteNotificationMonitor createRemoteNotificationMonitor = createRemoteNotificationMonitor(alexaConnection);
        alexaConnection.bindAmbientSoundDispatcher(new DefaultAmbientSoundDispatcher(defaultSessionSupplier, createSpeechRecognizerV2));
        CallRecipient createCallRecipient = createCallRecipient();
        NonHfpCallController createNonHfpCallController = dependenciesProvider.createNonHfpCallController();
        DiagnosticsObserver createDiagnosticsObserver = createDiagnosticsObserver();
        AccessoryNetworkStatusMonitor accessoryNetworkStatusMonitor = new AccessoryNetworkStatusMonitor(new AccessoryNetworkService(context));
        InterprocessPhoneStatePlugin interprocessPhoneStatePlugin = new InterprocessPhoneStatePlugin(context, StateFeature.CALL_NOTIFICATION, StateOuterClass.State.newBuilder().setFeature(StateFeature.CALL_NOTIFICATION.toInteger()).setInteger(DefaultCallNotificationStateMonitor.CallNotificationStatus.NO_ACTIVITY.intRepresentation).mo10084build(), createCallNotificationStatusPlugin(new DefaultCallNotificationStateMonitor(context)));
        PhoneStateSupplier createPhoneStateSupplier = createPhoneStateSupplier(context, accessoryNetworkStatusMonitor, createRemoteNotificationMonitor, alexaReadinessSupplier, interprocessPhoneStatePlugin);
        activateAccessoryLocaleSynchronizer(context, createLocaleSupplier, defaultSessionSupplier, new DefaultNotificationInteractor(context), createInterProcessFeatureChecker, interprocessUserSupplier, createDeviceSupplier);
        BroadcastInputEventsHandler broadcastInputEventsHandler = new BroadcastInputEventsHandler(context);
        activateAccessoryBootupService(alexaReadinessSupplier, defaultSessionSupplier, createAccessoryMetricsService);
        AlexaEndpointProvider alexaEndpointProvider = new AlexaEndpointProvider(interprocessAccessoryEnvironmentService);
        DeviceAccountSupplier createDeviceAccountSupplier = createDeviceAccountSupplier(context, interprocessAccessoryEnvironmentService, interprocessUserSupplier, createDeviceSupplier, alexaEndpointProvider);
        activateFindMyAccessoryManager(context, interprocessUserSupplier, defaultSessionSupplier, interprocessMarketplaceService, buildStageProvider, createAccessoryMetricsService, createDeviceSupplier, createInterProcessFeatureChecker, createDeviceAccountSupplier, interprocessAccessoryEnvironmentService);
        initializeDEMMetricsTrigger(context, interprocessUserSupplier, accessoryMetricsSupplier, defaultSessionSupplier, accessoryNetworkStatusMonitor);
        new InterprocessPrivacyModeEmitter(defaultSessionSupplier, interprocessPhoneStatePlugin, context).activate();
        initializeReportAccessoryStateDependencies(alexaConnection, fileSystemRegistrationSupplier, defaultSessionSupplier, interprocessUserSupplier, gson, createInterProcessFeatureChecker, context);
        Accessories build = new Accessories.Builder(context).speechRecognizer(createSpeechRecognizerV2).callRecipient(createCallRecipient).diagnosticsObserver(createDiagnosticsObserver).metricsObserver(createMetricsObserver).phoneStateSupplier(createPhoneStateSupplier).buildStageProvider(buildStageProvider).deviceSupplier(createDeviceSupplier).registrationSupplier(fileSystemRegistrationSupplier).userSupplier(interprocessUserSupplier).sessionSupplier(defaultSessionSupplier).inputEventsHandler(broadcastInputEventsHandler).accessoryMetricsService(createAccessoryMetricsService).deviceAccountSupplier(createDeviceAccountSupplier).featureChecker(createInterProcessFeatureChecker).accessoryServiceConfigurationSupplier(createAccessoryServiceConfigurationSupplier(context)).nonHfpCallController(createNonHfpCallController).davsClient(dependenciesProvider.createDavsClient(context, interprocessUserSupplier, alexaEndpointProvider, createDeviceAccountSupplier)).deviceManufacturerSupplier(alexaDeviceManufacturerSupplier).build();
        accessoriesObjectInit(nanoTime, createAccessoryMetricsService);
        initializeIPCFinishSetupInteractor(context, build, interprocessUserSupplier, createAccessoryMetricsService, gson);
        new AlexaConnectionAdvocate(alexaConnection, build).activate();
        accessoriesProcessIntegrationsInit(nanoTime, createAccessoryMetricsService);
        initializeCblInteractor(context, build, createInterProcessFeatureChecker, interprocessUserSupplier);
        return build;
    }

    private static AccessoryContextProvider createAccessoryContextProviderForSpeechApi(Gson gson, BluetoothProfileWatcher bluetoothProfileWatcher, IOComponentsSupplier iOComponentsSupplier, SessionSupplier sessionSupplier, RegistrationSupplier registrationSupplier, Context context, AlexaConnection alexaConnection) {
        return new AlexaServicesContextProvider(createJsonConverter(gson), bluetoothProfileWatcher, iOComponentsSupplier, new AlexaTrustedStatesSupplier(sessionSupplier, registrationSupplier, new PhoneSecurityStateSupplier(context)), alexaConnection);
    }

    private static AccessoryServiceConfigurationSupplier createAccessoryServiceConfigurationSupplier(Context context) {
        return new DefaultAccessoryServiceConfigurationSupplier(context) { // from class: com.amazon.alexa.accessorykit.AccessoriesFactory.1
            @Override // com.amazon.alexa.accessory.DefaultAccessoryServiceConfigurationSupplier, com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
            @NonNull
            public List<String> blockedRfcommNamePrefixes() {
                return Collections.singletonList(AlexaDeviceManufacturerSupplier.LENOVO_SMART_DOCK_NAME_PREFIX);
            }
        };
    }

    private static CallNotificationPlugin createCallNotificationStatusPlugin(CallNotificationStateMonitor callNotificationStateMonitor) {
        return new CallNotificationPlugin(callNotificationStateMonitor);
    }

    private static CallRecipient createCallRecipient() {
        return new UnavailableCallRecipient();
    }

    private static DeviceAccountSupplier createDeviceAccountSupplier(Context context, AccessoryEnvironmentService accessoryEnvironmentService, UserSupplier userSupplier, DeviceSupplierV2 deviceSupplierV2, EndpointProvider endpointProvider) {
        return new FileSystemDeviceAccountSupplier(context, userSupplier, new DefaultDeviceAccountExecutor(endpointProvider), deviceSupplierV2);
    }

    private static DiagnosticsObserver createDiagnosticsObserver() {
        return new AccessoryDiagnosticsObserver();
    }

    private static DownChannelStatusMonitor createDownChannelStatusMonitor(ReadinessSupplier readinessSupplier) {
        DefaultDownChannelStatusMonitor defaultDownChannelStatusMonitor = new DefaultDownChannelStatusMonitor(readinessSupplier);
        defaultDownChannelStatusMonitor.activate();
        return defaultDownChannelStatusMonitor;
    }

    private static DownChannelStatusPlugin createDownChannelStatusPlugin(DownChannelStatusMonitor downChannelStatusMonitor) {
        return new DownChannelStatusPlugin(downChannelStatusMonitor);
    }

    private static JsonConverter createJsonConverter(Gson gson) {
        return new GsonJsonConverter(gson);
    }

    private static LocaleSupplier createLocaleSupplier(AlexaConnection alexaConnection) {
        return new AlexaLocaleSupplier(alexaConnection);
    }

    private static MessageStatusPlugin createMessageNotificationStatusPlugin(MessageNotificationStateMonitor messageNotificationStateMonitor) {
        return new MessageStatusPlugin(messageNotificationStateMonitor);
    }

    private static MetricsObserver createMetricsObserver(AccessoryMetricsService accessoryMetricsService, AccessoryMetricsSupplier accessoryMetricsSupplier) {
        return new RebounceMetricsObserver(new AccessoryMetricsObserver(accessoryMetricsService), accessoryMetricsSupplier);
    }

    private static MlisClient createMlisClient(Context context, UserSupplier userSupplier) {
        return new MlisHttpClient(context, userSupplier);
    }

    private static NetworkStatusPlugin createNetworkStatusPlugin(NetworkStatusMonitor networkStatusMonitor) {
        return new NetworkStatusPlugin(networkStatusMonitor);
    }

    private static PhoneStateSupplier createPhoneStateSupplier(Context context, NetworkStatusMonitor networkStatusMonitor, RemoteNotificationMonitor remoteNotificationMonitor, ReadinessSupplier readinessSupplier, PhoneStatePlugin phoneStatePlugin) {
        return createPhoneStateSupplierFromPlugins(createVolumeChangedPlugin(createVolumeChangedMonitor(context)), createNetworkStatusPlugin(networkStatusMonitor), phoneStatePlugin, new InterprocessPhoneStatePlugin(context, StateFeature.MESSAGE_NOTIFICATION, StateOuterClass.State.newBuilder().setFeature(StateFeature.MESSAGE_NOTIFICATION.toInteger()).setInteger(0).mo10084build(), createMessageNotificationStatusPlugin(new DefaultMessageNotificationStateMonitor(context))), createA2dpPlayingPlugin(createA2dpPlayingMonitor(context)), createScoStatusPlugin(createScoStatusMonitor(context)), createRemoteNotificationPlugin(remoteNotificationMonitor), createDownChannelStatusPlugin(createDownChannelStatusMonitor(readinessSupplier)), new InterprocessPhoneStatePlugin(context, StateFeature.NOTIFICATION_FORWARDING_ENABLED, StateOuterClass.State.newBuilder().setFeature(StateFeature.NOTIFICATION_FORWARDING_ENABLED.toInteger()).setBoolean(false).mo10084build()));
    }

    private static PhoneStateSupplier createPhoneStateSupplierFromPlugins(VolumeChangedPlugin volumeChangedPlugin, NetworkStatusPlugin networkStatusPlugin, PhoneStatePlugin phoneStatePlugin, PhoneStatePlugin phoneStatePlugin2, A2dpPlayingPlugin a2dpPlayingPlugin, ScoStatusPlugin scoStatusPlugin, RemoteNotificationPlugin remoteNotificationPlugin, DownChannelStatusPlugin downChannelStatusPlugin, PhoneStatePlugin phoneStatePlugin3) {
        return new MemoryPhoneStateSupplier.Builder().addPlugin(StateFeature.VOLUME_CHANGED_NOTIFICATION, volumeChangedPlugin).addPlugin(StateFeature.DEVICE_NETWORK_CONNECTIVITY_STATUS, networkStatusPlugin).addPlugin(StateFeature.BLUETOOTH_A2DP_ACTIVE, a2dpPlayingPlugin).addPlugin(StateFeature.BLUETOOTH_HFP_ACTIVE, scoStatusPlugin).addPlugin(StateFeature.CALL_NOTIFICATION, phoneStatePlugin).addPlugin(StateFeature.MESSAGE_NOTIFICATION, phoneStatePlugin2).addPlugin(StateFeature.REMOTE_NOTIFICATION, remoteNotificationPlugin).addPlugin(StateFeature.CONNECTION_SUCCEEDED, downChannelStatusPlugin).addPlugin(StateFeature.NOTIFICATION_FORWARDING_ENABLED, phoneStatePlugin3).build();
    }

    private static RemoteNotificationMonitor createRemoteNotificationMonitor(AlexaConnection alexaConnection) {
        return new AvsRemoteNotificationMonitor(alexaConnection);
    }

    private static RemoteNotificationPlugin createRemoteNotificationPlugin(RemoteNotificationMonitor remoteNotificationMonitor) {
        return new RemoteNotificationPlugin(remoteNotificationMonitor);
    }

    private static ScoPrioritizer createScoPrioritizer(SessionSupplier sessionSupplier, Context context) {
        try {
            return new IfAnyScoPrioritizer(new PhoneStateScoPrioritizer(context), new AlexaScoPrioritizer(sessionSupplier, new SingleBluetoothProfileWatcher(context), context));
        } catch (Exception unused) {
            return null;
        }
    }

    private static ScoStatusMonitor createScoStatusMonitor(Context context) {
        return new DefaultScoStatusMonitor(context);
    }

    private static ScoStatusPlugin createScoStatusPlugin(ScoStatusMonitor scoStatusMonitor) {
        return new ScoStatusPlugin(scoStatusMonitor);
    }

    private static AccessorySpeechRecognizerV2 createSpeechRecognizerV2(AlexaConnection alexaConnection, AccessoryContextProvider accessoryContextProvider, MlisClient mlisClient, RegistrationSupplier registrationSupplier, SessionSupplier sessionSupplier, Context context, FeatureChecker featureChecker2) {
        DefaultMultiturnDelayProvider defaultMultiturnDelayProvider = new DefaultMultiturnDelayProvider(context);
        return AccessorySpeechRecognizerV2.newBuilder().setAlexaConnection(alexaConnection).setContextProvider(accessoryContextProvider).setMlisClient(mlisClient).setModeStatusChecker(new InterprocessModeStatusChecker(context).activate()).setMultiturnDelayProvider(defaultMultiturnDelayProvider).setScoPrioritizer(createScoPrioritizer(sessionSupplier, context)).setRegistrationSupplier(registrationSupplier).setSessionSupplier(sessionSupplier).setContext(context).setFeatureChecker(featureChecker2).build();
    }

    private static VolumeChangedMonitor createVolumeChangedMonitor(Context context) {
        return new DefaultVolumeChangedMonitor(context);
    }

    private static VolumeChangedPlugin createVolumeChangedPlugin(VolumeChangedMonitor volumeChangedMonitor) {
        return new VolumeChangedPlugin(volumeChangedMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void emitMainProcessPhoneStates(Context context, PhoneStatePlugin... phoneStatePluginArr) {
        for (PhoneStatePlugin phoneStatePlugin : phoneStatePluginArr) {
            new PhoneStateBroadcastAdapter(context, phoneStatePlugin);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public static void emitMainProcessUserChangedEvents(final Context context, UserSupplier userSupplier, IdentityService identityService) {
        userSupplier.queryUser().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$olMSLeB_BSy_yPUzhVqhCsuUZ_k
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoriesFactory.lambda$emitMainProcessUserChangedEvents$15(context, (User) obj);
            }
        });
        new PersonBroadcastEmitter(context, userSupplier, identityService).activate();
        new MobilyticsDeviceBroadcastEmitter(context, userSupplier, DeviceDataStore.getInstance(context)).activate();
    }

    public static String getAppName() {
        return appName;
    }

    @VisibleForTesting
    static Observable<Integer> getObserveSessionCountObservable(final Supplier<com.amazon.alexa.accessoryclient.client.accessories.Accessories> supplier) {
        return Observable.defer(new io.reactivex.rxjava3.functions.Supplier() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$S-L3HF0mzONm0ZgBlDYJAvumRO0
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AccessoriesFactory.lambda$getObserveSessionCountObservable$14(Supplier.this);
            }
        });
    }

    private static void initializeAccessoryLogger() {
        Logger.setProcessor(new CircularMemoryProcessor());
        Logger.setLevel(Logger.Level.DEBUG);
    }

    private static void initializeBugsnagNotification() {
        RxJavaPlugins.setErrorHandler($$Lambda$AccessoriesFactory$EvoBWvXZ0UZq_HrMODmw3aZhM4g.INSTANCE);
    }

    private static void initializeCblInteractor(Context context, Accessories accessories, InterprocessFeatureChecker interprocessFeatureChecker, UserSupplier userSupplier) {
        cblInteractor = DefaultCblInteractor.newBuilder().setAccessoryExplorer(accessories.getExplorer()).setContext(context).setFeatureChecker(interprocessFeatureChecker).setNotificationInteractor(new DefaultNotificationInteractor(context)).setSessionFactory(accessories.getSessionFactory()).setSessionSupplier(accessories.getSessionSupplier()).setUserSupplier(userSupplier).build();
    }

    private static void initializeDEMMetricsTrigger(final Context context, InterprocessUserSupplier interprocessUserSupplier, final AccessoryMetricsSupplier accessoryMetricsSupplier, final SessionSupplier sessionSupplier, final NetworkStatusMonitor networkStatusMonitor) {
        interprocessUserSupplier.queryUser().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$Hhpw91R8OiQfnxC7S3_zKpYI5HM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoriesFactory.lambda$initializeDEMMetricsTrigger$7(context, networkStatusMonitor, sessionSupplier, accessoryMetricsSupplier, (User) obj);
            }
        }, $$Lambda$AccessoriesFactory$lFNlhi3YYAwVlYyWK6SPF2bYSPs.INSTANCE);
    }

    @SuppressLint({"CheckResult"})
    private static void initializeIPCFinishSetupInteractor(Context context, Accessories accessories, UserSupplier userSupplier, AccessoryMetricsService accessoryMetricsService, Gson gson) {
        if (isFireOS()) {
            Logger.d("%s: Skipping FAS since its FireOS", TAG);
            return;
        }
        DefaultNotificationInteractor defaultNotificationInteractor = new DefaultNotificationInteractor(context);
        DefaultFinishSetupMetricsRecorder defaultFinishSetupMetricsRecorder = new DefaultFinishSetupMetricsRecorder(accessoryMetricsService);
        finishSetupInteractor = FinishSetupInteractor.newBuilder().setAccessoryExplorer(accessories.getExplorer()).setSessionSupplier(accessories.getSessionSupplier()).setViewCoordinator(InterProcessFASViewCoordinator.newBuilder().setDeviceSupplier(accessories.getDeviceSupplier()).setFinishSetupRecordSupplier(new DefaultRecordSupplier(new AccessoryPersistentStorage.Factory(context, createJsonConverter(gson)))).setNotificationPresenter(new NotificationPresenter(context, defaultNotificationInteractor, defaultFinishSetupMetricsRecorder)).setMetricsRecorder(defaultFinishSetupMetricsRecorder).setContext(context).build()).build();
        userSupplier.queryUser().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$AccessoriesFactory$jyoC7slXYVthutGvdOuO0CtwJS4.INSTANCE, $$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE.INSTANCE);
    }

    private static void initializeReportAccessoryStateDependencies(AlexaConnection alexaConnection, RegistrationSupplier registrationSupplier, SessionSupplier sessionSupplier, UserSupplier userSupplier, Gson gson, FeatureChecker featureChecker2, Context context) {
        alexaConnection.setReportAccessoryStateDependencies(new StateReportEventGenerator(registrationSupplier, sessionSupplier, userSupplier, createJsonConverter(gson), context), new StateReportEventSender(alexaConnection, sessionSupplier), featureChecker2);
    }

    private static boolean isCSMAlexaConnectionAvailableOnFireOS(Context context) {
        if (context.getPackageManager().queryIntentServices(new Intent(API_SERVICE_INTENT_LAUNCH_ACTION), 64).isEmpty()) {
            Logger.i("%s API service not registered. CSM SDK not supported on this device.", TAG);
            return false;
        }
        return true;
    }

    private static boolean isFOSWith64BitArchitecture() {
        try {
            String[] strArr = Build.SUPPORTED_64_BIT_ABIS;
            boolean z = strArr != null && strArr.length > 0;
            Logger.i("%s is current FOS 64 bit architecture: %b", TAG, Boolean.valueOf(z));
            return isFireOS() && z;
        } catch (NullPointerException e) {
            Logger.e("%s got exception while fetching OS architecture.", e, TAG);
            return true;
        }
    }

    private static boolean isFireOS() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$emitMainProcessUserChangedEvents$15(Context context, User user) throws Throwable {
        Logger.d("emitMainProcessUserChangedEvents detect user change, sending broadcasts");
        context.sendBroadcast(InterprocessUserSupplier.InterprocessAccountChangedListener.ACCOUNT_CHANGED_INTENT, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$getObserveSessionCountObservable$14(Supplier supplier) throws Throwable {
        final com.amazon.alexa.accessoryclient.client.accessories.Accessories accessories = (com.amazon.alexa.accessoryclient.client.accessories.Accessories) supplier.get();
        return Observable.merge(accessories.getSessionSupplier().observeSessionCreated().map($$Lambda$AccessoriesFactory$EbNOptxpwOEoXM1XC69eHdDNqYQ.INSTANCE), accessories.getSessionSupplier().observeSessionReleased().map($$Lambda$AccessoriesFactory$K79ed_nWxmqTYHIsdHOS4ONA5o.INSTANCE), Observable.just(0)).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$R0NYROf4gaRcpLkXqioOfrNMV8A
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource map;
                Integer num = (Integer) obj;
                map = com.amazon.alexa.accessoryclient.client.accessories.Accessories.this.getSessionSupplier().getActiveSessions().map($$Lambda$tl1dl60b0pxNGk7EfmeLcvemr48.INSTANCE);
                return map;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initializeBugsnagNotification$10(final Throwable th) throws Throwable {
        Logger.e("%s: Caught RxJava unhandled exception", th, TAG);
        oneInOneHundred(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$HK7y24H6H5zBJaDWFxqLZUeNJvU
            @Override // java.lang.Runnable
            public final void run() {
                AccessoriesFactory.lambda$null$9(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initializeDEMMetricsTrigger$7(Context context, NetworkStatusMonitor networkStatusMonitor, SessionSupplier sessionSupplier, AccessoryMetricsSupplier accessoryMetricsSupplier, User user) throws Throwable {
        if (user.equals(User.ABSENT)) {
            Logger.d("%s User is ABSENT. Cannot initialize DeviceEngagementMetrics.", TAG);
            return;
        }
        Logger.d("%s User is PRESENT. Initialize DeviceEngagementMetrics.", TAG);
        dependenciesProvider.createTriggerDeviceEngagementMetrics(context, networkStatusMonitor, sessionSupplier, accessoryMetricsSupplier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initializeIPCFinishSetupInteractor$16(User user) throws Throwable {
        if (user.equals(User.ABSENT)) {
            finishSetupInteractor.deactivate();
        } else {
            finishSetupInteractor.activate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$null$11(Accessory accessory) throws Throwable {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$null$12(Accessory accessory) throws Throwable {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$9(Throwable th) {
        Logger.e("%s: Caught RxJava unhandled exception, propagating it to BugSnag", th, TAG);
        Bugsnag.notify(th);
    }

    @VisibleForTesting
    static void mainProcessIntegrationsInit(long j, AccessoryMetricsService accessoryMetricsService) {
        long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - j, TimeUnit.NANOSECONDS);
        Logger.d("%s: main-process integrations instantiation time (ms): %d", TAG, Long.valueOf(convert));
        accessoryMetricsService.recordTime(MetricsConstants.ColdStart.ACCESSORIES_INSTANTIATION_DURATION, MetricsConstants.ColdStart.MAIN_PROCESS_INTEGRATIONS, convert, null);
    }

    @VisibleForTesting
    static void oneInOneHundred(Runnable runnable) {
        if (new Random().nextInt(100) == 0) {
            runnable.run();
        }
    }

    @VisibleForTesting
    static void resetDependenciesProvider() {
        dependenciesProvider = new DefaultDependenciesProvider();
    }

    @VisibleForTesting
    static void resetMainThreadHandler() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @VisibleForTesting
    static void setDependenciesProvider(DependenciesProvider dependenciesProvider2) {
        dependenciesProvider = dependenciesProvider2;
    }

    @VisibleForTesting
    static void setMainThreadHandler(Handler handler) {
        mainThreadHandler = handler;
    }

    private static boolean shouldUseCsmAlexaConnection(Context context) {
        return isFireOS() && !isFOSWith64BitArchitecture() && isCSMAlexaConnectionAvailableOnFireOS(context);
    }

    @VisibleForTesting
    static void timeToGetFeatureSync(long j, AccessoryMetricsService accessoryMetricsService) {
        long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - j, TimeUnit.NANOSECONDS);
        Logger.d("%s: Get feature sync time (ms): %d", TAG, Long.valueOf(convert));
        accessoryMetricsService.recordTime(MetricsConstants.ColdStart.ACCESSORIES_INSTANTIATION_DURATION, MetricsConstants.ColdStart.FEATURE_CHECKER_SYNC_LATENCY, convert, null);
    }
}
