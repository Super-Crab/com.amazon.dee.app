package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.dependency.BilobaViewComponent;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.model.Configuration;
import com.amazon.alexa.biloba.model.Configuration_ProvideActivityApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideCardApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideCommsApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideEmergencySettingsApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideGroupApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideSettingsApiFactory;
import com.amazon.alexa.biloba.model.Configuration_ProvideTodaysActivitiesApiFactory;
import com.amazon.alexa.biloba.network.api.GroupV2Api_Factory;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper_Factory;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.ActivitiesStore;
import com.amazon.alexa.biloba.storage.ActivitiesStore_Factory;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo_Factory;
import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.storage.CardsStore_Factory;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CareActorsStoreV1;
import com.amazon.alexa.biloba.storage.CareActorsStoreV1_Factory;
import com.amazon.alexa.biloba.storage.CareActorsStoreV2;
import com.amazon.alexa.biloba.storage.CareActorsStoreV2_Factory;
import com.amazon.alexa.biloba.storage.CareActorsStore_Factory;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.storage.CommsStore_Factory;
import com.amazon.alexa.biloba.storage.DevicesStore;
import com.amazon.alexa.biloba.storage.DevicesStore_Factory;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.storage.SettingsStore_Factory;
import com.amazon.alexa.biloba.storage.TimeZoneStore;
import com.amazon.alexa.biloba.storage.TimeZoneStore_Factory;
import com.amazon.alexa.biloba.storage.TodaysActivitiesStore;
import com.amazon.alexa.biloba.storage.TodaysActivitiesStore_Factory;
import com.amazon.alexa.biloba.utils.CommsHelper_Factory;
import com.amazon.alexa.biloba.utils.RemoteAssistHelper_Factory;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewModelFactory;
import com.amazon.alexa.biloba.view.RootViewControllerFactory;
import com.amazon.alexa.biloba.view.RootViewControllerFactory_MembersInjector;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.biloba.view.account.MembershipViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewModel;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerViewModel;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.alerts.AlertsListViewModel;
import com.amazon.alexa.biloba.view.alerts.AlertsListViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView_MembersInjector;
import com.amazon.alexa.biloba.view.cards.DashboardBottomSheet;
import com.amazon.alexa.biloba.view.cards.DashboardBottomSheet_MembersInjector;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewModel;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.comms.EmergencyView;
import com.amazon.alexa.biloba.view.comms.EmergencyViewModel;
import com.amazon.alexa.biloba.view.comms.EmergencyViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.comms.EmergencyView_MembersInjector;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardViewModel;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView_MembersInjector;
import com.amazon.alexa.biloba.view.dashboard.CardTransformer_Factory;
import com.amazon.alexa.biloba.view.dashboard.CommsHandler_Factory;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.biloba.view.emergencyHelpline.EmergencyHelplineRoutingHelper_Factory;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewModelV3;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewModelV3_MembersInjector;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3_MembersInjector;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView_MembersInjector;
import com.amazon.alexa.biloba.view.recent.RecentActivityListViewModel;
import com.amazon.alexa.biloba.view.recent.RecentActivityListViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.startup.StartupView;
import com.amazon.alexa.biloba.view.startup.StartupViewModel;
import com.amazon.alexa.biloba.view.startup.StartupViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.startup.StartupView_MembersInjector;
import com.amazon.alexa.biloba.view.tips.TipsViewModel;
import com.amazon.alexa.biloba.view.tips.TipsViewModel_MembersInjector;
import com.amazon.alexa.biloba.view.webview.WebviewView;
import com.amazon.alexa.biloba.view.webview.WebviewViewRoutingHelper_Factory;
import com.amazon.alexa.biloba.view.webview.WebviewView_MembersInjector;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.imageloader.api.ImageLoader;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ErrorView_MembersInjector;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.http.CoralService;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DaggerBilobaComponent implements BilobaComponent {
    private Provider<ActivitiesStore> activitiesStoreProvider;
    private Provider<AlertConfigurationRepo> alertConfigurationRepoProvider;
    private CardTransformer_Factory cardTransformerProvider;
    private Provider<CardsStore> cardsStoreProvider;
    private Provider<CareActorsStore> careActorsStoreProvider;
    private Provider<CareActorsStoreV1> careActorsStoreV1Provider;
    private Provider<CareActorsStoreV2> careActorsStoreV2Provider;
    private CommsHandler_Factory commsHandlerProvider;
    private Provider<CommsStore> commsStoreProvider;
    private Provider<DeferredRoutingHelper> deferredRoutingHelperProvider;
    private Provider<DevicesStore> devicesStoreProvider;
    private GroupV2Api_Factory groupV2ApiProvider;
    private Configuration_ProvideActivityApiFactory provideActivityApiProvider;
    private AlertConfigurationModule_ProvideAlertConfigurationApiFactory provideAlertConfigurationApiProvider;
    private Provider<BilobaMetricsService> provideBilobaMetricsServiceProvider;
    private Provider<BilobaUrlResolver> provideBilobaUrlResolverProvider;
    private Configuration_ProvideCardApiFactory provideCardApiProvider;
    private Provider<CoachMarkFactory> provideCoachMarkFactoryProvider;
    private Configuration_ProvideCommsApiFactory provideCommsApiProvider;
    private Provider<CoralService> provideCoralServiceProvider;
    private Provider<CrashMetadata> provideCrashMetadataProvider;
    private Provider<CrashReporter> provideCrashReporterProvider;
    private Provider<DeviceInformation> provideDeviceInformationProvider;
    private AlertConfigurationModule_ProvideDevicesApiFactory provideDevicesApiProvider;
    private Provider<ElementLocalStorage> provideElementLocalStorageProvider;
    private Configuration_ProvideEmergencySettingsApiFactory provideEmergencySettingsApiProvider;
    private Provider<EnvironmentService> provideEnvironmentServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Configuration_ProvideGroupApiFactory provideGroupApiProvider;
    private Provider<Gson> provideGsonProvider;
    private PersonIdentityModule_ProvideIdentityLocalDataStoreFactory provideIdentityLocalDataStoreProvider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<ImageLoader> provideImageLoaderProvider;
    private Provider<MainActivityLifecycleObserverRegistrar> provideMainActivityLifecycleObserverRegistrarProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private PersonIdentityModule_ProvidePasscodeApiFactory providePasscodeApiProvider;
    private Provider<PersistentStorage.Factory> providePersistentStorageFactoryProvider;
    private Provider<PersonIdProvider> providePersonIdProvider;
    private Provider<RemoteManagementInactivityHandler> provideRemoteManagementInactivityHandlerProvider;
    private Provider<RoutingService> provideRoutingServiceProvider;
    private Provider<SchedulerProvider> provideSchedulerProvider;
    private Configuration_ProvideSettingsApiFactory provideSettingsApiProvider;
    private TimeZoneModule_ProvideTimeZoneApiFactory provideTimeZoneApiProvider;
    private Configuration_ProvideTodaysActivitiesApiFactory provideTodaysActivitiesApiProvider;
    private Provider<UrlHelper> provideUrlHelperProvider;
    private Provider<BilobaViewModelFactory> provideViewModelFactoryProvider;
    private RemoteAssistHelper_Factory remoteAssistHelperProvider;
    private Provider<SettingsStore> settingsStoreProvider;
    private Provider<TimeZoneStore> timeZoneStoreProvider;
    private Provider<TodaysActivitiesStore> todaysActivitiesStoreProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class BilobaViewComponentBuilder implements BilobaViewComponent.Factory {
        private BilobaViewComponentBuilder() {
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent.Factory
        public BilobaViewComponent create() {
            return new BilobaViewComponentImpl(this);
        }
    }

    /* loaded from: classes6.dex */
    private final class BilobaViewComponentImpl implements BilobaViewComponent {
        private CommsHandler_Factory commsHandlerProvider;
        private CommsHelper_Factory commsHelperProvider;
        private RemoteAssistHelper_Factory remoteAssistHelperProvider;
        private WebviewViewRoutingHelper_Factory webviewViewRoutingHelperProvider;

        private void initialize(BilobaViewComponentBuilder bilobaViewComponentBuilder) {
            this.commsHandlerProvider = CommsHandler_Factory.create(DaggerBilobaComponent.this.provideEventBusProvider);
            this.commsHelperProvider = CommsHelper_Factory.create(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider, DaggerBilobaComponent.this.careActorsStoreProvider, DaggerBilobaComponent.this.provideRoutingServiceProvider, this.commsHandlerProvider);
            this.remoteAssistHelperProvider = RemoteAssistHelper_Factory.create(DaggerBilobaComponent.this.provideRoutingServiceProvider, DaggerBilobaComponent.this.provideDeviceInformationProvider, DaggerBilobaComponent.this.provideRemoteManagementInactivityHandlerProvider, DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider, DaggerBilobaComponent.this.careActorsStoreProvider);
            this.webviewViewRoutingHelperProvider = WebviewViewRoutingHelper_Factory.create(DaggerBilobaComponent.this.careActorsStoreProvider, DaggerBilobaComponent.this.provideIdentityServiceProvider);
        }

        private AlertSettingsView injectAlertSettingsView(AlertSettingsView alertSettingsView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(alertSettingsView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            AlertSettingsView_MembersInjector.injectViewModelFactory(alertSettingsView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideViewModelFactoryProvider));
            return alertSettingsView;
        }

        private AlertSettingsViewModel injectAlertSettingsViewModel(AlertSettingsViewModel alertSettingsViewModel) {
            AlertSettingsViewModel_MembersInjector.injectCrashReporter(alertSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            AlertSettingsViewModel_MembersInjector.injectCrashMetadata(alertSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            AlertSettingsViewModel_MembersInjector.injectBilobaMetricsService(alertSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            AlertSettingsViewModel_MembersInjector.injectAlertConfigurationRepo(alertSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.alertConfigurationRepoProvider));
            AlertSettingsViewModel_MembersInjector.injectCareActorsStore(alertSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return alertSettingsViewModel;
        }

        private AlertsListViewModel injectAlertsListViewModel(AlertsListViewModel alertsListViewModel) {
            AlertsListViewModel_MembersInjector.injectCrashReporter(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            AlertsListViewModel_MembersInjector.injectCrashMetadata(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            AlertsListViewModel_MembersInjector.injectBilobaMetricsService(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            AlertsListViewModel_MembersInjector.injectAlertConfigurationRepo(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.alertConfigurationRepoProvider));
            AlertsListViewModel_MembersInjector.injectCareActorsStore(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            AlertsListViewModel_MembersInjector.injectDevicesStore(alertsListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.devicesStoreProvider));
            return alertsListViewModel;
        }

        private BilobaDashboardView injectBilobaDashboardView(BilobaDashboardView bilobaDashboardView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(bilobaDashboardView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            BilobaDashboardView_MembersInjector.injectCommsHelper(bilobaDashboardView, DoubleCheck.lazy(this.commsHelperProvider));
            BilobaDashboardView_MembersInjector.injectRoutingService(bilobaDashboardView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            BilobaDashboardView_MembersInjector.injectCoachMarkFactory(bilobaDashboardView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCoachMarkFactoryProvider));
            BilobaDashboardView_MembersInjector.injectRemoteAssistHelper(bilobaDashboardView, DoubleCheck.lazy(this.remoteAssistHelperProvider));
            BilobaDashboardView_MembersInjector.injectUrlHelper(bilobaDashboardView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideUrlHelperProvider));
            return bilobaDashboardView;
        }

        private BilobaDashboardViewModel injectBilobaDashboardViewModel(BilobaDashboardViewModel bilobaDashboardViewModel) {
            BilobaDashboardViewModel_MembersInjector.injectCrashReporter(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            BilobaDashboardViewModel_MembersInjector.injectCrashMetadata(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            BilobaDashboardViewModel_MembersInjector.injectBilobaMetricsService(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            BilobaDashboardViewModel_MembersInjector.injectCareActorsStore(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            BilobaDashboardViewModel_MembersInjector.injectTodaysActivitiesStore(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.todaysActivitiesStoreProvider));
            BilobaDashboardViewModel_MembersInjector.injectCardsStore(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.cardsStoreProvider));
            BilobaDashboardViewModel_MembersInjector.injectSettingsStore(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.settingsStoreProvider));
            BilobaDashboardViewModel_MembersInjector.injectCommsStore(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.commsStoreProvider));
            BilobaDashboardViewModel_MembersInjector.injectFeatureServiceV2(bilobaDashboardViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideFeatureServiceV2Provider));
            return bilobaDashboardViewModel;
        }

        private BilobaViewWithMetrics injectBilobaViewWithMetrics(BilobaViewWithMetrics bilobaViewWithMetrics) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(bilobaViewWithMetrics, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            return bilobaViewWithMetrics;
        }

        private CommsSetupViewModel injectCommsSetupViewModel(CommsSetupViewModel commsSetupViewModel) {
            CommsSetupViewModel_MembersInjector.injectUrlResolver(commsSetupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaUrlResolverProvider));
            CommsSetupViewModel_MembersInjector.injectCrashReporter(commsSetupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            CommsSetupViewModel_MembersInjector.injectCrashMetadata(commsSetupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            CommsSetupViewModel_MembersInjector.injectBilobaMetricsService(commsSetupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            CommsSetupViewModel_MembersInjector.injectCareActorsStore(commsSetupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return commsSetupViewModel;
        }

        private ConfirmationViewModel injectConfirmationViewModel(ConfirmationViewModel confirmationViewModel) {
            ConfirmationViewModel_MembersInjector.injectBilobaMetricsService(confirmationViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            ConfirmationViewModel_MembersInjector.injectEnvironmentService(confirmationViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideEnvironmentServiceProvider));
            ConfirmationViewModel_MembersInjector.injectRoutingService(confirmationViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            return confirmationViewModel;
        }

        private DashboardBottomSheet injectDashboardBottomSheet(DashboardBottomSheet dashboardBottomSheet) {
            DashboardBottomSheet_MembersInjector.injectCardsStore(dashboardBottomSheet, DoubleCheck.lazy(DaggerBilobaComponent.this.cardsStoreProvider));
            return dashboardBottomSheet;
        }

        private EmergencyContactViewModel injectEmergencyContactViewModel(EmergencyContactViewModel emergencyContactViewModel) {
            EmergencyContactViewModel_MembersInjector.injectUrlResolver(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaUrlResolverProvider));
            EmergencyContactViewModel_MembersInjector.injectCrashReporter(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            EmergencyContactViewModel_MembersInjector.injectCrashMetadata(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            EmergencyContactViewModel_MembersInjector.injectBilobaMetricsService(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            EmergencyContactViewModel_MembersInjector.injectCommsStore(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.commsStoreProvider));
            EmergencyContactViewModel_MembersInjector.injectCareActorsStore(emergencyContactViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return emergencyContactViewModel;
        }

        private EmergencyView injectEmergencyView(EmergencyView emergencyView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(emergencyView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            EmergencyView_MembersInjector.injectUrlHelper(emergencyView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideUrlHelperProvider));
            return emergencyView;
        }

        private EmergencyViewModel injectEmergencyViewModel(EmergencyViewModel emergencyViewModel) {
            EmergencyViewModel_MembersInjector.injectUrlResolver(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaUrlResolverProvider));
            EmergencyViewModel_MembersInjector.injectCrashReporter(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            EmergencyViewModel_MembersInjector.injectCrashMetadata(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            EmergencyViewModel_MembersInjector.injectBilobaMetricsService(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            EmergencyViewModel_MembersInjector.injectCommsStore(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.commsStoreProvider));
            EmergencyViewModel_MembersInjector.injectCareActorsStore(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            EmergencyViewModel_MembersInjector.injectEnvironmentService(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideEnvironmentServiceProvider));
            EmergencyViewModel_MembersInjector.injectFeatureServiceV2(emergencyViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideFeatureServiceV2Provider));
            return emergencyViewModel;
        }

        private ErrorView injectErrorView(ErrorView errorView) {
            ErrorView_MembersInjector.injectBilobaMetricsService(errorView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            return errorView;
        }

        private GettingStartedViewModelV3 injectGettingStartedViewModelV3(GettingStartedViewModelV3 gettingStartedViewModelV3) {
            GettingStartedViewModelV3_MembersInjector.injectBilobaMetricsService(gettingStartedViewModelV3, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            GettingStartedViewModelV3_MembersInjector.injectEnvironmentService(gettingStartedViewModelV3, DoubleCheck.lazy(DaggerBilobaComponent.this.provideEnvironmentServiceProvider));
            return gettingStartedViewModelV3;
        }

        private GettingStartedViewV3 injectGettingStartedViewV3(GettingStartedViewV3 gettingStartedViewV3) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(gettingStartedViewV3, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            GettingStartedViewV3_MembersInjector.injectImageLoader(gettingStartedViewV3, DoubleCheck.lazy(DaggerBilobaComponent.this.provideImageLoaderProvider));
            return gettingStartedViewV3;
        }

        private InfoModalView injectInfoModalView(InfoModalView infoModalView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(infoModalView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            InfoModalView_MembersInjector.injectCommsHelper(infoModalView, DoubleCheck.lazy(this.commsHelperProvider));
            InfoModalView_MembersInjector.injectCareActorsStore(infoModalView, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return infoModalView;
        }

        private InfoModalViewModel injectInfoModalViewModel(InfoModalViewModel infoModalViewModel) {
            InfoModalViewModel_MembersInjector.injectRoutingService(infoModalViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            return infoModalViewModel;
        }

        private MembershipViewModel injectMembershipViewModel(MembershipViewModel membershipViewModel) {
            MembershipViewModel_MembersInjector.injectCrashReporter(membershipViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            MembershipViewModel_MembersInjector.injectCrashMetadata(membershipViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            MembershipViewModel_MembersInjector.injectBilobaMetricsService(membershipViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            MembershipViewModel_MembersInjector.injectCareActorsStore(membershipViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return membershipViewModel;
        }

        private ProfileSettingsViewModel injectProfileSettingsViewModel(ProfileSettingsViewModel profileSettingsViewModel) {
            ProfileSettingsViewModel_MembersInjector.injectCareActorsStore(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            ProfileSettingsViewModel_MembersInjector.injectSettingsStore(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.settingsStoreProvider));
            ProfileSettingsViewModel_MembersInjector.injectCommsStore(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.commsStoreProvider));
            ProfileSettingsViewModel_MembersInjector.injectRoutingService(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            ProfileSettingsViewModel_MembersInjector.injectUrlHelper(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideUrlHelperProvider));
            ProfileSettingsViewModel_MembersInjector.injectFeatureServiceV2(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideFeatureServiceV2Provider));
            ProfileSettingsViewModel_MembersInjector.injectEnvironmentService(profileSettingsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideEnvironmentServiceProvider));
            return profileSettingsViewModel;
        }

        private RecentActivityListViewModel injectRecentActivityListViewModel(RecentActivityListViewModel recentActivityListViewModel) {
            RecentActivityListViewModel_MembersInjector.injectCrashReporter(recentActivityListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            RecentActivityListViewModel_MembersInjector.injectCrashMetadata(recentActivityListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            RecentActivityListViewModel_MembersInjector.injectBilobaMetricsService(recentActivityListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            RecentActivityListViewModel_MembersInjector.injectActivitiesStore(recentActivityListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.activitiesStoreProvider));
            RecentActivityListViewModel_MembersInjector.injectCareActorsStore(recentActivityListViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return recentActivityListViewModel;
        }

        private RootViewControllerFactory injectRootViewControllerFactory(RootViewControllerFactory rootViewControllerFactory) {
            RootViewControllerFactory_MembersInjector.injectBilobaMetricsService(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            RootViewControllerFactory_MembersInjector.injectCareActorsStore(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            RootViewControllerFactory_MembersInjector.injectRoutingService(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            RootViewControllerFactory_MembersInjector.injectEmergencyHelplineRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(EmergencyHelplineRoutingHelper_Factory.create()));
            RootViewControllerFactory_MembersInjector.injectWebviewViewRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(this.webviewViewRoutingHelperProvider));
            RootViewControllerFactory_MembersInjector.injectEventBus(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.provideEventBusProvider));
            RootViewControllerFactory_MembersInjector.injectStorageFactory(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.providePersistentStorageFactoryProvider));
            RootViewControllerFactory_MembersInjector.injectIdentityService(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.provideIdentityServiceProvider));
            RootViewControllerFactory_MembersInjector.injectDeferredRoutingHelper(rootViewControllerFactory, DoubleCheck.lazy(DaggerBilobaComponent.this.deferredRoutingHelperProvider));
            return rootViewControllerFactory;
        }

        private StartupView injectStartupView(StartupView startupView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(startupView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            StartupView_MembersInjector.injectRoutingService(startupView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            StartupView_MembersInjector.injectDeferredRoutingHelper(startupView, DoubleCheck.lazy(DaggerBilobaComponent.this.deferredRoutingHelperProvider));
            StartupView_MembersInjector.injectUrlHelper(startupView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideUrlHelperProvider));
            return startupView;
        }

        private StartupViewModel injectStartupViewModel(StartupViewModel startupViewModel) {
            StartupViewModel_MembersInjector.injectCrashReporter(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashReporterProvider));
            StartupViewModel_MembersInjector.injectCrashMetadata(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideCrashMetadataProvider));
            StartupViewModel_MembersInjector.injectBilobaMetricsService(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider));
            StartupViewModel_MembersInjector.injectCareActorsStore(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            StartupViewModel_MembersInjector.injectPasscodeApi(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.providePasscodeApiProvider));
            StartupViewModel_MembersInjector.injectIdentityLocalDataStore(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideIdentityLocalDataStoreProvider));
            StartupViewModel_MembersInjector.injectFeatureServiceV2(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideFeatureServiceV2Provider));
            StartupViewModel_MembersInjector.injectDeferredRoutingHelper(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.deferredRoutingHelperProvider));
            StartupViewModel_MembersInjector.injectRoutingService(startupViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            return startupViewModel;
        }

        private TimeZonePickerViewModel injectTimeZonePickerViewModel(TimeZonePickerViewModel timeZonePickerViewModel) {
            TimeZonePickerViewModel_MembersInjector.injectCareActorsStore(timeZonePickerViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            TimeZonePickerViewModel_MembersInjector.injectSettingsStore(timeZonePickerViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.settingsStoreProvider));
            TimeZonePickerViewModel_MembersInjector.injectTimeZoneStore(timeZonePickerViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.timeZoneStoreProvider));
            return timeZonePickerViewModel;
        }

        private TipsViewModel injectTipsViewModel(TipsViewModel tipsViewModel) {
            TipsViewModel_MembersInjector.injectCardsStore(tipsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.cardsStoreProvider));
            TipsViewModel_MembersInjector.injectCareActorsStore(tipsViewModel, DoubleCheck.lazy(DaggerBilobaComponent.this.careActorsStoreProvider));
            return tipsViewModel;
        }

        private WebviewView injectWebviewView(WebviewView webviewView) {
            BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(webviewView, (BilobaMetricsService) DaggerBilobaComponent.this.provideBilobaMetricsServiceProvider.mo10268get());
            WebviewView_MembersInjector.injectRoutingService(webviewView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideRoutingServiceProvider));
            WebviewView_MembersInjector.injectUrlHelper(webviewView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideUrlHelperProvider));
            WebviewView_MembersInjector.injectFeatureServiceV2(webviewView, DoubleCheck.lazy(DaggerBilobaComponent.this.provideFeatureServiceV2Provider));
            return webviewView;
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(BilobaViewWithMetrics bilobaViewWithMetrics) {
            injectBilobaViewWithMetrics(bilobaViewWithMetrics);
        }

        private BilobaViewComponentImpl(BilobaViewComponentBuilder bilobaViewComponentBuilder) {
            initialize(bilobaViewComponentBuilder);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(EmergencyView emergencyView) {
            injectEmergencyView(emergencyView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(StartupView startupView) {
            injectStartupView(startupView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(WebviewView webviewView) {
            injectWebviewView(webviewView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(BilobaDashboardView bilobaDashboardView) {
            injectBilobaDashboardView(bilobaDashboardView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(TipsViewModel tipsViewModel) {
            injectTipsViewModel(tipsViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(BilobaDashboardViewModel bilobaDashboardViewModel) {
            injectBilobaDashboardViewModel(bilobaDashboardViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(RecentActivityListViewModel recentActivityListViewModel) {
            injectRecentActivityListViewModel(recentActivityListViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(AlertsListViewModel alertsListViewModel) {
            injectAlertsListViewModel(alertsListViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(AlertSettingsViewModel alertSettingsViewModel) {
            injectAlertSettingsViewModel(alertSettingsViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(AlertSettingsView alertSettingsView) {
            injectAlertSettingsView(alertSettingsView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(ProfileSettingsViewModel profileSettingsViewModel) {
            injectProfileSettingsViewModel(profileSettingsViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(ConfirmationViewModel confirmationViewModel) {
            injectConfirmationViewModel(confirmationViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(MembershipViewModel membershipViewModel) {
            injectMembershipViewModel(membershipViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(StartupViewModel startupViewModel) {
            injectStartupViewModel(startupViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(EmergencyContactViewModel emergencyContactViewModel) {
            injectEmergencyContactViewModel(emergencyContactViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(EmergencyViewModel emergencyViewModel) {
            injectEmergencyViewModel(emergencyViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(CommsSetupViewModel commsSetupViewModel) {
            injectCommsSetupViewModel(commsSetupViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(RootViewControllerFactory rootViewControllerFactory) {
            injectRootViewControllerFactory(rootViewControllerFactory);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(TimeZonePickerViewModel timeZonePickerViewModel) {
            injectTimeZonePickerViewModel(timeZonePickerViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(GettingStartedViewModelV3 gettingStartedViewModelV3) {
            injectGettingStartedViewModelV3(gettingStartedViewModelV3);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(InfoModalViewModel infoModalViewModel) {
            injectInfoModalViewModel(infoModalViewModel);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(InfoModalView infoModalView) {
            injectInfoModalView(infoModalView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(ErrorView errorView) {
            injectErrorView(errorView);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(GettingStartedViewV3 gettingStartedViewV3) {
            injectGettingStartedViewV3(gettingStartedViewV3);
        }

        @Override // com.amazon.alexa.biloba.dependency.BilobaViewComponent
        public void inject(DashboardBottomSheet dashboardBottomSheet) {
            injectDashboardBottomSheet(dashboardBottomSheet);
        }
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        private AndroidModule androidModule;
        private ApplicationModule applicationModule;
        private ServiceModule serviceModule;

        @Deprecated
        public Builder alertConfigurationModule(AlertConfigurationModule alertConfigurationModule) {
            Preconditions.checkNotNull(alertConfigurationModule);
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

        public BilobaComponent build() {
            if (this.serviceModule == null) {
                this.serviceModule = new ServiceModule();
            }
            if (this.applicationModule == null) {
                this.applicationModule = new ApplicationModule();
            }
            if (this.androidModule == null) {
                this.androidModule = new AndroidModule();
            }
            return new DaggerBilobaComponent(this);
        }

        @Deprecated
        public Builder configuration(Configuration configuration) {
            Preconditions.checkNotNull(configuration);
            return this;
        }

        @Deprecated
        public Builder personIdentityModule(PersonIdentityModule personIdentityModule) {
            Preconditions.checkNotNull(personIdentityModule);
            return this;
        }

        public Builder serviceModule(ServiceModule serviceModule) {
            this.serviceModule = (ServiceModule) Preconditions.checkNotNull(serviceModule);
            return this;
        }

        @Deprecated
        public Builder storageModule(StorageModule storageModule) {
            Preconditions.checkNotNull(storageModule);
            return this;
        }

        @Deprecated
        public Builder subcomponentsModule(SubcomponentsModule subcomponentsModule) {
            Preconditions.checkNotNull(subcomponentsModule);
            return this;
        }

        @Deprecated
        public Builder timeZoneModule(TimeZoneModule timeZoneModule) {
            Preconditions.checkNotNull(timeZoneModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BilobaComponent create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideMobilyticsProvider = DoubleCheck.provider(ApplicationModule_ProvideMobilyticsFactory.create(builder.applicationModule));
        this.provideEnvironmentServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideEnvironmentServiceFactory.create(builder.applicationModule));
        this.provideBilobaMetricsServiceProvider = DoubleCheck.provider(ServiceModule_ProvideBilobaMetricsServiceFactory.create(builder.serviceModule, this.provideMobilyticsProvider, this.provideEnvironmentServiceProvider));
        this.provideCrashReporterProvider = DoubleCheck.provider(ApplicationModule_ProvideCrashReporterFactory.create(builder.applicationModule));
        this.provideCrashMetadataProvider = DoubleCheck.provider(ApplicationModule_ProvideCrashMetadataFactory.create(builder.applicationModule));
        this.provideCoralServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideCoralServiceFactory.create(builder.applicationModule));
        this.provideIdentityServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideIdentityServiceFactory.create(builder.applicationModule));
        this.providePersonIdProvider = DoubleCheck.provider(ServiceModule_ProvidePersonIdProviderFactory.create(builder.serviceModule, this.provideIdentityServiceProvider, this.provideBilobaMetricsServiceProvider));
        this.provideSchedulerProvider = DoubleCheck.provider(ServiceModule_ProvideSchedulerFactory.create(builder.serviceModule));
        this.provideGroupApiProvider = Configuration_ProvideGroupApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.provideGsonProvider = DoubleCheck.provider(AndroidModule_ProvideGsonFactory.create(builder.androidModule));
        this.careActorsStoreV1Provider = DoubleCheck.provider(CareActorsStoreV1_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideGroupApiProvider, this.provideGsonProvider));
        this.groupV2ApiProvider = GroupV2Api_Factory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.careActorsStoreV2Provider = DoubleCheck.provider(CareActorsStoreV2_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.groupV2ApiProvider, this.provideGsonProvider));
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(ApplicationModule_ProvideFeatureServiceV2Factory.create(builder.applicationModule));
        this.careActorsStoreProvider = DoubleCheck.provider(CareActorsStore_Factory.create(this.careActorsStoreV1Provider, this.careActorsStoreV2Provider, this.provideFeatureServiceV2Provider));
        this.provideUrlHelperProvider = DoubleCheck.provider(ServiceModule_ProvideUrlHelperFactory.create(builder.serviceModule, this.provideEnvironmentServiceProvider, this.careActorsStoreProvider, this.provideFeatureServiceV2Provider));
        this.provideRoutingServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideRoutingServiceFactory.create(builder.applicationModule));
        this.deferredRoutingHelperProvider = DoubleCheck.provider(DeferredRoutingHelper_Factory.create(this.provideRoutingServiceProvider));
        this.provideEventBusProvider = DoubleCheck.provider(ApplicationModule_ProvideEventBusFactory.create(builder.applicationModule));
        this.provideCoachMarkFactoryProvider = DoubleCheck.provider(ApplicationModule_ProvideCoachMarkFactoryFactory.create(builder.applicationModule));
        this.provideDeviceInformationProvider = DoubleCheck.provider(ApplicationModule_ProvideDeviceInformationFactory.create(builder.applicationModule));
        this.provideMainActivityLifecycleObserverRegistrarProvider = DoubleCheck.provider(ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory.create(builder.applicationModule));
        this.provideRemoteManagementInactivityHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideRemoteManagementInactivityHandlerFactory.create(builder.serviceModule, this.provideEventBusProvider, this.provideIdentityServiceProvider, this.provideMainActivityLifecycleObserverRegistrarProvider, this.provideRoutingServiceProvider, this.careActorsStoreProvider));
        this.remoteAssistHelperProvider = RemoteAssistHelper_Factory.create(this.provideRoutingServiceProvider, this.provideDeviceInformationProvider, this.provideRemoteManagementInactivityHandlerProvider, this.provideBilobaMetricsServiceProvider, this.careActorsStoreProvider);
        this.commsHandlerProvider = CommsHandler_Factory.create(this.provideEventBusProvider);
        this.cardTransformerProvider = CardTransformer_Factory.create(this.careActorsStoreProvider, this.provideRoutingServiceProvider, EmergencyHelplineRoutingHelper_Factory.create(), this.remoteAssistHelperProvider, this.commsHandlerProvider, this.provideBilobaMetricsServiceProvider);
        this.provideCardApiProvider = Configuration_ProvideCardApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.cardsStoreProvider = DoubleCheck.provider(CardsStore_Factory.create(this.cardTransformerProvider, this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideCardApiProvider));
        this.provideTodaysActivitiesApiProvider = Configuration_ProvideTodaysActivitiesApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.todaysActivitiesStoreProvider = DoubleCheck.provider(TodaysActivitiesStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideTodaysActivitiesApiProvider));
        this.provideSettingsApiProvider = Configuration_ProvideSettingsApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.provideElementLocalStorageProvider = DoubleCheck.provider(ApplicationModule_ProvideElementLocalStorageFactory.create(builder.applicationModule));
        this.settingsStoreProvider = DoubleCheck.provider(SettingsStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideSettingsApiProvider, this.provideElementLocalStorageProvider, this.provideFeatureServiceV2Provider));
        this.provideCommsApiProvider = Configuration_ProvideCommsApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.provideEmergencySettingsApiProvider = Configuration_ProvideEmergencySettingsApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.commsStoreProvider = DoubleCheck.provider(CommsStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideCommsApiProvider, this.provideEmergencySettingsApiProvider));
        this.provideActivityApiProvider = Configuration_ProvideActivityApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.activitiesStoreProvider = DoubleCheck.provider(ActivitiesStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideActivityApiProvider));
        this.provideAlertConfigurationApiProvider = AlertConfigurationModule_ProvideAlertConfigurationApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.alertConfigurationRepoProvider = DoubleCheck.provider(AlertConfigurationRepo_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideAlertConfigurationApiProvider));
        this.provideDevicesApiProvider = AlertConfigurationModule_ProvideDevicesApiFactory.create(this.provideCoralServiceProvider, this.providePersonIdProvider, this.provideSchedulerProvider);
        this.devicesStoreProvider = DoubleCheck.provider(DevicesStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideDevicesApiProvider));
        this.provideViewModelFactoryProvider = DoubleCheck.provider(ApplicationModule_ProvideViewModelFactoryFactory.create(builder.applicationModule));
        this.providePasscodeApiProvider = PersonIdentityModule_ProvidePasscodeApiFactory.create(this.provideCoralServiceProvider, this.provideSchedulerProvider);
        this.providePersistentStorageFactoryProvider = DoubleCheck.provider(ApplicationModule_ProvidePersistentStorageFactoryFactory.create(builder.applicationModule));
        this.provideIdentityLocalDataStoreProvider = PersonIdentityModule_ProvideIdentityLocalDataStoreFactory.create(this.providePersistentStorageFactoryProvider, this.provideIdentityServiceProvider);
        this.provideBilobaUrlResolverProvider = DoubleCheck.provider(ServiceModule_ProvideBilobaUrlResolverFactory.create(builder.serviceModule, this.provideEnvironmentServiceProvider));
        this.provideTimeZoneApiProvider = TimeZoneModule_ProvideTimeZoneApiFactory.create(this.provideCoralServiceProvider, this.provideSchedulerProvider);
        this.timeZoneStoreProvider = DoubleCheck.provider(TimeZoneStore_Factory.create(this.provideCrashReporterProvider, this.provideCrashMetadataProvider, this.provideBilobaMetricsServiceProvider, this.provideTimeZoneApiProvider));
        this.provideImageLoaderProvider = DoubleCheck.provider(ApplicationModule_ProvideImageLoaderFactory.create(builder.applicationModule));
    }

    @Override // com.amazon.alexa.biloba.dependency.BilobaComponent
    public BilobaViewComponent.Factory bilobaViewComponent() {
        return new BilobaViewComponentBuilder();
    }

    private DaggerBilobaComponent(Builder builder) {
        initialize(builder);
    }
}
