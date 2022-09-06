package com.amazon.tarazed.dagger.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.work.WorkManager;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.activity.ActivityTracker_Factory;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.amazon.tarazed.activity.TarazedPrimerActivity_MembersInjector;
import com.amazon.tarazed.appevent.AppEventSender;
import com.amazon.tarazed.appevent.AppEventSender_MembersInjector;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleObserver;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.arcus.ArcusPeriodicSchedulerWorker;
import com.amazon.tarazed.arcus.ArcusPeriodicSchedulerWorker_MembersInjector;
import com.amazon.tarazed.arcus.ArcusSyncWorker;
import com.amazon.tarazed.arcus.ArcusSyncWorker_MembersInjector;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.rotation.android.DisplayRotationListener;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.modules.LibraryModule;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideArcusHelperFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideContextFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideDisplayRotationListenerFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideMainLooperHandlerFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideRemoteConfigurationManagerFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideSharedPreferencesFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideTarazedAppLifeCycleObserverFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideTarazedControllerFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideTarazedEventDispatcherFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideTarazedIoTManagerFactory;
import com.amazon.tarazed.dagger.modules.LibraryModule_ProvideWorkManagerFactory;
import com.amazon.tarazed.dagger.subcomponents.DebugSubcomponent;
import com.amazon.tarazed.init.TarazedInitializer;
import com.amazon.tarazed.init.TarazedInitializer_MembersInjector;
import com.amazon.tarazed.receiver.ToggleQAModeReceiver;
import com.amazon.tarazed.receiver.ToggleQAModeReceiver_MembersInjector;
import com.amazon.tarazed.sessionmanager.HeadsUpNotificationManager_Factory;
import com.amazon.tarazed.sessionmanager.SessionClientCacheService;
import com.amazon.tarazed.sessionmanager.SessionClientCacheService_MembersInjector;
import com.amazon.tarazed.sessionmanager.TarazedController;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager_Factory;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService_MembersInjector;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.WindowParamsHelper;
import com.amazon.tarazed.ui.manager.TarazedUIManagerFactory;
import com.amazon.tarazed.ui.manager.TarazedUIManagerFactory_Factory;
import com.amazon.tarazed.ui.tv.MoveTVUIHandler_Factory;
import com.amazon.tarazed.ui.tv.TVUIManager;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession_Factory;
import com.amazon.tarazed.utility.ContextHelper;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazon.tarazed.worker.logging.upload.TarazedLogUploadWorker;
import com.amazon.tarazed.worker.logging.upload.TarazedLogUploadWorker_MembersInjector;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class DaggerLibraryComponent implements LibraryComponent {
    private Provider<ActivityTracker> activityTrackerProvider;
    private Provider<BrowserPresenceDetectorToResumeSuspendedSession> browserPresenceDetectorToResumeSuspendedSessionProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getBizMetricsHelper getBizMetricsHelperProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope getCoroutineScopeProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtilityAndroid getDeviceInfoUtilityAndroidProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtility getDeviceInfoUtilityProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider getDispatcherProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getLogger getLoggerProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper getMetricsHelperProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger getSessionLoggerProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getSessionNotifier getSessionNotifierProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getTVIndicatorManager getTVIndicatorManagerProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getViewGroupManager getViewGroupManagerProvider;
    private GlobalComponent globalComponent;
    private HeadsUpNotificationManager_Factory headsUpNotificationManagerProvider;
    private MoveTVUIHandler_Factory moveTVUIHandlerProvider;
    private Provider<ArcusHelper> provideArcusHelperProvider;
    private Provider<Context> provideContextProvider;
    private Provider<DisplayRotationListener> provideDisplayRotationListenerProvider;
    private Provider<Handler> provideMainLooperHandlerProvider;
    private Provider<RemoteConfigurationManager> provideRemoteConfigurationManagerProvider;
    private Provider<SharedPreferences> provideSharedPreferencesProvider;
    private Provider<TarazedAppLifeCycleObserver> provideTarazedAppLifeCycleObserverProvider;
    private Provider<TarazedController> provideTarazedControllerProvider;
    private Provider<TarazedEventDispatcher> provideTarazedEventDispatcherProvider;
    private Provider<TarazedIoTManager> provideTarazedIoTManagerProvider;
    private Provider<WorkManager> provideWorkManagerProvider;
    private Provider<TarazedResourceManager> tarazedResourceManagerProvider;
    private Provider<TarazedUIManagerFactory> tarazedUIManagerFactoryProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private GlobalComponent globalComponent;
        private LibraryModule libraryModule;

        public LibraryComponent build() {
            Preconditions.checkBuilderRequirement(this.libraryModule, LibraryModule.class);
            Preconditions.checkBuilderRequirement(this.globalComponent, GlobalComponent.class);
            return new DaggerLibraryComponent(this);
        }

        public Builder globalComponent(GlobalComponent globalComponent) {
            this.globalComponent = (GlobalComponent) Preconditions.checkNotNull(globalComponent);
            return this;
        }

        public Builder libraryModule(LibraryModule libraryModule) {
            this.libraryModule = (LibraryModule) Preconditions.checkNotNull(libraryModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public final class DebugSubcomponentBuilder implements DebugSubcomponent.Builder {
        private DebugSubcomponentBuilder() {
        }

        @Override // com.amazon.tarazed.dagger.subcomponents.DebugSubcomponent.Builder
        public DebugSubcomponent build() {
            return new DebugSubcomponentImpl(this);
        }
    }

    /* loaded from: classes13.dex */
    private final class DebugSubcomponentImpl implements DebugSubcomponent {
        private DebugSubcomponentImpl(DebugSubcomponentBuilder debugSubcomponentBuilder) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getBizMetricsHelper implements Provider<BizMetricsHelper> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getBizMetricsHelper(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public BizMetricsHelper mo10268get() {
            return (BizMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getBizMetricsHelper(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope implements Provider<CoroutineScope> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        @Override // javax.inject.Provider
        /* renamed from: get  reason: collision with other method in class */
        public CoroutineScope mo10268get() {
            return (CoroutineScope) Preconditions.checkNotNull(this.globalComponent.getCoroutineScope(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtility implements Provider<DeviceInfoUtility> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtility(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeviceInfoUtility mo10268get() {
            return (DeviceInfoUtility) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtility(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtilityAndroid implements Provider<DeviceInfoUtilityAndroid> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtilityAndroid(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeviceInfoUtilityAndroid mo10268get() {
            return (DeviceInfoUtilityAndroid) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtilityAndroid(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider implements Provider<DispatcherProvider> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DispatcherProvider mo10268get() {
            return (DispatcherProvider) Preconditions.checkNotNull(this.globalComponent.getDispatcherProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getLogger implements Provider<TarazedLogger> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getLogger(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedLogger mo10268get() {
            return (TarazedLogger) Preconditions.checkNotNull(this.globalComponent.getLogger(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper implements Provider<TarazedMetricsHelper> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedMetricsHelper mo10268get() {
            return (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger implements Provider<TarazedSessionLogger> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedSessionLogger mo10268get() {
            return (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getSessionNotifier implements Provider<TarazedSessionNotifier> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getSessionNotifier(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedSessionNotifier mo10268get() {
            return (TarazedSessionNotifier) Preconditions.checkNotNull(this.globalComponent.getSessionNotifier(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getTVIndicatorManager implements Provider<TVUIManager> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getTVIndicatorManager(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TVUIManager mo10268get() {
            return (TVUIManager) Preconditions.checkNotNull(this.globalComponent.getTVIndicatorManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getViewGroupManager implements Provider<ViewGroupManager> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getViewGroupManager(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ViewGroupManager mo10268get() {
            return (ViewGroupManager) Preconditions.checkNotNull(this.globalComponent.getViewGroupManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.getDeviceInfoUtilityAndroidProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtilityAndroid(builder.globalComponent);
        this.provideRemoteConfigurationManagerProvider = DoubleCheck.provider(LibraryModule_ProvideRemoteConfigurationManagerFactory.create(builder.libraryModule, this.getDeviceInfoUtilityAndroidProvider));
        this.provideWorkManagerProvider = DoubleCheck.provider(LibraryModule_ProvideWorkManagerFactory.create(builder.libraryModule));
        this.getLoggerProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getLogger(builder.globalComponent);
        this.getMetricsHelperProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper(builder.globalComponent);
        this.provideArcusHelperProvider = DoubleCheck.provider(LibraryModule_ProvideArcusHelperFactory.create(builder.libraryModule, this.provideWorkManagerProvider, this.provideRemoteConfigurationManagerProvider, this.getLoggerProvider, this.getMetricsHelperProvider));
        this.getSessionLoggerProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger(builder.globalComponent);
        this.getDeviceInfoUtilityProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getDeviceInfoUtility(builder.globalComponent);
        this.getCoroutineScopeProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope(builder.globalComponent);
        this.getDispatcherProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider(builder.globalComponent);
        this.activityTrackerProvider = DoubleCheck.provider(ActivityTracker_Factory.create(this.getSessionLoggerProvider, this.getDeviceInfoUtilityProvider, this.getMetricsHelperProvider, this.getCoroutineScopeProvider, this.getDispatcherProvider));
        this.provideTarazedEventDispatcherProvider = DoubleCheck.provider(LibraryModule_ProvideTarazedEventDispatcherFactory.create(builder.libraryModule, this.getSessionLoggerProvider, this.getMetricsHelperProvider));
        this.getSessionNotifierProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getSessionNotifier(builder.globalComponent);
        this.provideTarazedIoTManagerProvider = DoubleCheck.provider(LibraryModule_ProvideTarazedIoTManagerFactory.create(builder.libraryModule, this.provideTarazedEventDispatcherProvider, this.getSessionLoggerProvider, this.getMetricsHelperProvider, this.getSessionNotifierProvider));
        this.getBizMetricsHelperProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getBizMetricsHelper(builder.globalComponent);
        this.provideDisplayRotationListenerProvider = DoubleCheck.provider(LibraryModule_ProvideDisplayRotationListenerFactory.create(builder.libraryModule, this.getSessionLoggerProvider, this.getMetricsHelperProvider));
        this.getViewGroupManagerProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getViewGroupManager(builder.globalComponent);
        this.getTVIndicatorManagerProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getTVIndicatorManager(builder.globalComponent);
        this.moveTVUIHandlerProvider = MoveTVUIHandler_Factory.create(this.getTVIndicatorManagerProvider);
        this.provideSharedPreferencesProvider = DoubleCheck.provider(LibraryModule_ProvideSharedPreferencesFactory.create(builder.libraryModule));
        this.provideMainLooperHandlerProvider = DoubleCheck.provider(LibraryModule_ProvideMainLooperHandlerFactory.create(builder.libraryModule));
        this.provideContextProvider = DoubleCheck.provider(LibraryModule_ProvideContextFactory.create(builder.libraryModule));
        this.tarazedUIManagerFactoryProvider = DoubleCheck.provider(TarazedUIManagerFactory_Factory.create(this.provideContextProvider, this.getViewGroupManagerProvider, this.getSessionLoggerProvider, this.getMetricsHelperProvider, this.getSessionNotifierProvider, this.getTVIndicatorManagerProvider, this.provideMainLooperHandlerProvider, this.activityTrackerProvider, this.getCoroutineScopeProvider, this.getDispatcherProvider, this.provideArcusHelperProvider, this.getBizMetricsHelperProvider));
        this.tarazedResourceManagerProvider = DoubleCheck.provider(TarazedResourceManager_Factory.create(this.getSessionLoggerProvider, this.getMetricsHelperProvider, this.getBizMetricsHelperProvider, this.getSessionNotifierProvider, this.getDeviceInfoUtilityProvider, this.provideTarazedEventDispatcherProvider, this.provideTarazedIoTManagerProvider, this.provideDisplayRotationListenerProvider, this.getViewGroupManagerProvider, this.moveTVUIHandlerProvider, this.provideArcusHelperProvider, this.provideSharedPreferencesProvider, this.provideMainLooperHandlerProvider, this.tarazedUIManagerFactoryProvider, this.getCoroutineScopeProvider, this.getDispatcherProvider, this.activityTrackerProvider));
        this.browserPresenceDetectorToResumeSuspendedSessionProvider = DoubleCheck.provider(BrowserPresenceDetectorToResumeSuspendedSession_Factory.create(this.provideContextProvider, this.getSessionLoggerProvider, this.getDeviceInfoUtilityProvider, this.getMetricsHelperProvider, this.getSessionNotifierProvider, this.provideTarazedEventDispatcherProvider, this.provideTarazedIoTManagerProvider));
        this.headsUpNotificationManagerProvider = HeadsUpNotificationManager_Factory.create(this.provideContextProvider, this.activityTrackerProvider, this.getDeviceInfoUtilityAndroidProvider, this.getSessionNotifierProvider);
        this.provideTarazedControllerProvider = DoubleCheck.provider(LibraryModule_ProvideTarazedControllerFactory.create(builder.libraryModule, this.tarazedResourceManagerProvider, this.getMetricsHelperProvider, this.getSessionLoggerProvider, this.getDeviceInfoUtilityAndroidProvider, this.browserPresenceDetectorToResumeSuspendedSessionProvider, this.getCoroutineScopeProvider, this.getDispatcherProvider, this.headsUpNotificationManagerProvider));
        this.provideTarazedAppLifeCycleObserverProvider = DoubleCheck.provider(LibraryModule_ProvideTarazedAppLifeCycleObserverFactory.create(builder.libraryModule, this.getLoggerProvider, this.getCoroutineScopeProvider, this.getDispatcherProvider, this.provideArcusHelperProvider, this.getSessionNotifierProvider, this.getBizMetricsHelperProvider));
    }

    private AppEventSender injectAppEventSender(AppEventSender appEventSender) {
        AppEventSender_MembersInjector.injectSetIotManager(appEventSender, this.provideTarazedIoTManagerProvider.mo10268get());
        AppEventSender_MembersInjector.injectSetLogger(appEventSender, (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method"));
        AppEventSender_MembersInjector.injectSetEventDispatcher(appEventSender, this.provideTarazedEventDispatcherProvider.mo10268get());
        return appEventSender;
    }

    private ArcusPeriodicSchedulerWorker injectArcusPeriodicSchedulerWorker(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker) {
        ArcusPeriodicSchedulerWorker_MembersInjector.injectArcusHelper(arcusPeriodicSchedulerWorker, this.provideArcusHelperProvider.mo10268get());
        ArcusPeriodicSchedulerWorker_MembersInjector.injectLogger(arcusPeriodicSchedulerWorker, (TarazedLogger) Preconditions.checkNotNull(this.globalComponent.getLogger(), "Cannot return null from a non-@Nullable component method"));
        ArcusPeriodicSchedulerWorker_MembersInjector.injectMetrics(arcusPeriodicSchedulerWorker, (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method"));
        return arcusPeriodicSchedulerWorker;
    }

    private ArcusSyncWorker injectArcusSyncWorker(ArcusSyncWorker arcusSyncWorker) {
        ArcusSyncWorker_MembersInjector.injectDeviceInfoUtility(arcusSyncWorker, (DeviceInfoUtilityAndroid) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtilityAndroid(), "Cannot return null from a non-@Nullable component method"));
        ArcusSyncWorker_MembersInjector.injectRemoteConfigurationManager(arcusSyncWorker, this.provideRemoteConfigurationManagerProvider.mo10268get());
        ArcusSyncWorker_MembersInjector.injectLogger(arcusSyncWorker, (TarazedLogger) Preconditions.checkNotNull(this.globalComponent.getLogger(), "Cannot return null from a non-@Nullable component method"));
        ArcusSyncWorker_MembersInjector.injectMetrics(arcusSyncWorker, (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method"));
        ArcusSyncWorker_MembersInjector.injectArcusHelper(arcusSyncWorker, this.provideArcusHelperProvider.mo10268get());
        return arcusSyncWorker;
    }

    private ContextHelper injectContextHelper(ContextHelper contextHelper) {
        contextHelper.setDeviceInfoUtility$TarazedAndroidLibrary_release((DeviceInfoUtility) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtility(), "Cannot return null from a non-@Nullable component method"));
        return contextHelper;
    }

    private SessionClientCacheService injectSessionClientCacheService(SessionClientCacheService sessionClientCacheService) {
        SessionClientCacheService_MembersInjector.injectSessionClientCache(sessionClientCacheService, (SessionClientCache) Preconditions.checkNotNull(this.globalComponent.getSessionClientCache(), "Cannot return null from a non-@Nullable component method"));
        return sessionClientCacheService;
    }

    private TarazedAppLifeCycleOwner injectTarazedAppLifeCycleOwner(TarazedAppLifeCycleOwner tarazedAppLifeCycleOwner) {
        tarazedAppLifeCycleOwner.setObserver$TarazedAndroidLibrary_release(this.provideTarazedAppLifeCycleObserverProvider.mo10268get());
        tarazedAppLifeCycleOwner.setDeviceInfo$TarazedAndroidLibrary_release((DeviceInfoUtility) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtility(), "Cannot return null from a non-@Nullable component method"));
        return tarazedAppLifeCycleOwner;
    }

    private TarazedInitializer injectTarazedInitializer(TarazedInitializer tarazedInitializer) {
        TarazedInitializer_MembersInjector.injectSetLogger(tarazedInitializer, (TarazedLogger) Preconditions.checkNotNull(this.globalComponent.getLogger(), "Cannot return null from a non-@Nullable component method"));
        TarazedInitializer_MembersInjector.injectSetActivityTracker(tarazedInitializer, this.activityTrackerProvider.mo10268get());
        TarazedInitializer_MembersInjector.injectSetDeviceInfoUtility(tarazedInitializer, (DeviceInfoUtilityAndroid) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtilityAndroid(), "Cannot return null from a non-@Nullable component method"));
        return tarazedInitializer;
    }

    private TarazedLogUploadWorker injectTarazedLogUploadWorker(TarazedLogUploadWorker tarazedLogUploadWorker) {
        TarazedLogUploadWorker_MembersInjector.injectLogger(tarazedLogUploadWorker, (TarazedLogger) Preconditions.checkNotNull(this.globalComponent.getLogger(), "Cannot return null from a non-@Nullable component method"));
        TarazedLogUploadWorker_MembersInjector.injectMetrics(tarazedLogUploadWorker, (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method"));
        TarazedLogUploadWorker_MembersInjector.injectDeviceInfoUtility(tarazedLogUploadWorker, (DeviceInfoUtility) Preconditions.checkNotNull(this.globalComponent.getDeviceInfoUtility(), "Cannot return null from a non-@Nullable component method"));
        TarazedLogUploadWorker_MembersInjector.injectSharedPreferences(tarazedLogUploadWorker, this.provideSharedPreferencesProvider);
        return tarazedLogUploadWorker;
    }

    private TarazedPrimerActivity injectTarazedPrimerActivity(TarazedPrimerActivity tarazedPrimerActivity) {
        TarazedPrimerActivity_MembersInjector.injectArcusHelper(tarazedPrimerActivity, this.provideArcusHelperProvider.mo10268get());
        TarazedPrimerActivity_MembersInjector.injectBizMetricsHelper(tarazedPrimerActivity, (BizMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getBizMetricsHelper(), "Cannot return null from a non-@Nullable component method"));
        TarazedPrimerActivity_MembersInjector.injectLogger(tarazedPrimerActivity, (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method"));
        TarazedPrimerActivity_MembersInjector.injectCoroutineScope(tarazedPrimerActivity, (CoroutineScope) Preconditions.checkNotNull(this.globalComponent.getCoroutineScope(), "Cannot return null from a non-@Nullable component method"));
        TarazedPrimerActivity_MembersInjector.injectMetricsHelper(tarazedPrimerActivity, (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method"));
        return tarazedPrimerActivity;
    }

    private TarazedSessionAndroidService injectTarazedSessionAndroidService(TarazedSessionAndroidService tarazedSessionAndroidService) {
        TarazedSessionAndroidService_MembersInjector.injectLogger(tarazedSessionAndroidService, (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method"));
        TarazedSessionAndroidService_MembersInjector.injectResourceManager(tarazedSessionAndroidService, this.tarazedResourceManagerProvider.mo10268get());
        TarazedSessionAndroidService_MembersInjector.injectController(tarazedSessionAndroidService, this.provideTarazedControllerProvider.mo10268get());
        TarazedSessionAndroidService_MembersInjector.injectActivityTracker(tarazedSessionAndroidService, this.activityTrackerProvider.mo10268get());
        return tarazedSessionAndroidService;
    }

    private ToggleQAModeReceiver injectToggleQAModeReceiver(ToggleQAModeReceiver toggleQAModeReceiver) {
        ToggleQAModeReceiver_MembersInjector.injectLogger(toggleQAModeReceiver, (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method"));
        ToggleQAModeReceiver_MembersInjector.injectSharedPreferences(toggleQAModeReceiver, this.provideSharedPreferencesProvider.mo10268get());
        return toggleQAModeReceiver;
    }

    private WindowParamsHelper injectWindowParamsHelper(WindowParamsHelper windowParamsHelper) {
        windowParamsHelper.setActivityTracker$TarazedAndroidLibrary_release(this.activityTrackerProvider.mo10268get());
        windowParamsHelper.setSharedPreferences$TarazedAndroidLibrary_release(this.provideSharedPreferencesProvider.mo10268get());
        return windowParamsHelper;
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public ActivityTracker activityTracker() {
        return this.activityTrackerProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public DebugSubcomponent.Builder debugSubcomponentBuilder() {
        return new DebugSubcomponentBuilder();
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public TarazedResourceManager getResourceManager() {
        return this.tarazedResourceManagerProvider.mo10268get();
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(ArcusSyncWorker arcusSyncWorker) {
        injectArcusSyncWorker(arcusSyncWorker);
    }

    private DaggerLibraryComponent(Builder builder) {
        this.globalComponent = builder.globalComponent;
        initialize(builder);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(ArcusPeriodicSchedulerWorker arcusPeriodicSchedulerWorker) {
        injectArcusPeriodicSchedulerWorker(arcusPeriodicSchedulerWorker);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(TarazedInitializer tarazedInitializer) {
        injectTarazedInitializer(tarazedInitializer);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(AppEventSender appEventSender) {
        injectAppEventSender(appEventSender);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(TarazedSessionAndroidService tarazedSessionAndroidService) {
        injectTarazedSessionAndroidService(tarazedSessionAndroidService);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(TarazedLogUploadWorker tarazedLogUploadWorker) {
        injectTarazedLogUploadWorker(tarazedLogUploadWorker);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(SessionClientCacheService sessionClientCacheService) {
        injectSessionClientCacheService(sessionClientCacheService);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(ContextHelper contextHelper) {
        injectContextHelper(contextHelper);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(TarazedAppLifeCycleOwner tarazedAppLifeCycleOwner) {
        injectTarazedAppLifeCycleOwner(tarazedAppLifeCycleOwner);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(TarazedPrimerActivity tarazedPrimerActivity) {
        injectTarazedPrimerActivity(tarazedPrimerActivity);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(WindowParamsHelper windowParamsHelper) {
        injectWindowParamsHelper(windowParamsHelper);
    }

    @Override // com.amazon.tarazed.dagger.components.LibraryComponent
    public void inject(ToggleQAModeReceiver toggleQAModeReceiver) {
        injectToggleQAModeReceiver(toggleQAModeReceiver);
    }
}
