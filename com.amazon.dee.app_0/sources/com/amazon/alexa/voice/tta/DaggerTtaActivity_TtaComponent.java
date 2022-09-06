package com.amazon.alexa.voice.tta;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.voice.tta.TtaActivity;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule_ProvidesGsonFactory;
import com.amazon.alexa.voice.tta.features.FeatureChecker;
import com.amazon.alexa.voice.tta.features.FeaturesModule;
import com.amazon.alexa.voice.tta.features.FeaturesModule_ProvidesFeatureCheckerFactory;
import com.amazon.alexa.voice.tta.features.FeaturesModule_ProvidesFeatureFlagConsumerFactory;
import com.amazon.alexa.voice.tta.features.FeaturesModule_ProvidesFeatureQueryFactory;
import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.metrics.MetricsModule;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesEventClockFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesMetricEventProcessingServiceFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesSimbaEventProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaEventProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaEventSenderFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaIngressEventProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaUiEventProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.MetricsModule_ProvidesTtaUplEventProcessorFactory;
import com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor;
import com.amazon.alexa.voice.tta.metrics.TtaIngressEventProcessor;
import com.amazon.alexa.voice.tta.metrics.TtaUiEventProcessor;
import com.amazon.alexa.voice.tta.metrics.TtaUiEventWithCountProcessor;
import com.amazon.alexa.voice.tta.metrics.TtaUiEventWithTypeProcessor;
import com.amazon.alexa.voice.tta.metrics.TtaUplEventProcessor;
import com.amazon.alexa.voice.tta.permissions.PermissionsModule;
import com.amazon.alexa.voice.tta.permissions.PermissionsModule_ProvidesPermissionsUtilFactory;
import com.amazon.alexa.voice.tta.permissions.PermissionsUtil;
import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.sdk.AlexaPlayerInfoCardTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaTextResponseTracker;
import com.amazon.alexa.voice.tta.sdk.SdkModule;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesAlexaClientSdkApisFactory;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesAlexaMobileFrameworkApisFactory;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesAlexaStateTrackerFactory;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesAlexaTextResponseTrackerFactory;
import com.amazon.alexa.voice.tta.sdk.SdkModule_ProvidesUiEventReporterFactory;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.tta.search.DefaultTtaResultHandler;
import com.amazon.alexa.voice.tta.search.FrictionHelper;
import com.amazon.alexa.voice.tta.search.SearchInteractor;
import com.amazon.alexa.voice.tta.search.SearchInteractor_Factory;
import com.amazon.alexa.voice.tta.search.SearchModel;
import com.amazon.alexa.voice.tta.search.SearchModel_Factory;
import com.amazon.alexa.voice.tta.search.SearchModule;
import com.amazon.alexa.voice.tta.search.SearchModule_ProvidesDefaultTtaResultHandlerFactory;
import com.amazon.alexa.voice.tta.search.SearchModule_ProvidesFrictionHelperFactory;
import com.amazon.alexa.voice.tta.search.SearchModule_ProvidesTtaResultHandlerFactory;
import com.amazon.alexa.voice.tta.simba.SimbaModule;
import com.amazon.alexa.voice.tta.statemachine.AlexaMediator;
import com.amazon.alexa.voice.tta.statemachine.SearchWorkflow;
import com.amazon.alexa.voice.tta.statemachine.SimbaMediator;
import com.amazon.alexa.voice.tta.statemachine.StateMachineModule;
import com.amazon.alexa.voice.tta.statemachine.StateMachineModule_ProvidesAlexaMediatorFactory;
import com.amazon.alexa.voice.tta.statemachine.StateMachineModule_ProvidesSearchWorkflowFactory;
import com.amazon.alexa.voice.tta.statemachine.StateMachineModule_ProvidesSimbaMediatorFactory;
import com.amazon.alexa.voice.tta.suggestions.SuggestionsModule;
import com.amazon.alexa.voice.tta.suggestions.SuggestionsModule_ProvidesSuggestionsHandlerFactory;
import com.amazon.alexa.voice.tta.suggestions.SuggestionsModule_ProvidesTtaSuggestionHandlerFactory;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionHandler;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionModel;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionModel_Factory;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor_Factory;
import com.amazon.alexa.voice.tta.typing.ConversationSessionTimer;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import com.amazon.alexa.voice.tta.typing.MessageHistoryManager;
import com.amazon.alexa.voice.tta.typing.PersistentStorage;
import com.amazon.alexa.voice.tta.typing.TtaInteractorProvider;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.amazon.alexa.voice.tta.typing.TypingInteractor;
import com.amazon.alexa.voice.tta.typing.TypingInteractor_Factory;
import com.amazon.alexa.voice.tta.typing.TypingModel;
import com.amazon.alexa.voice.tta.typing.TypingModel_Factory;
import com.amazon.alexa.voice.tta.typing.TypingModule;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesAlexaRenderedCardReceiverFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesConversationSessionTimerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesDefaultTtaMessageHandlerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesIngressParametersProviderFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesMessageHistoryManagerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesPersistentStorageFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTtaInteractorProviderFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTtaMessageHandlerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTtaMessageSanitizerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTtaNavigatorFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTypingContainerFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTypingRouterDelegateFactory;
import com.amazon.alexa.voice.tta.typing.TypingModule_ProvidesTypingRouterFactory;
import com.amazon.alexa.voice.tta.typing.TypingPresenter;
import com.amazon.alexa.voice.tta.typing.TypingPresenter_Factory;
import com.amazon.alexa.voice.tta.typing.TypingView;
import com.amazon.alexa.voice.tta.typing.TypingView_Factory;
import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DaggerTtaActivity_TtaComponent implements TtaActivity.TtaComponent {
    private Provider<Activity> providesActivityProvider;
    private Provider<AlexaClientSdkApis> providesAlexaClientSdkApisProvider;
    private Provider<AlexaMediator> providesAlexaMediatorProvider;
    private Provider<AlexaMobileFrameworkApis> providesAlexaMobileFrameworkApisProvider;
    private Provider<AlexaPlayerInfoCardTracker> providesAlexaPlayerInfoCardTrackerProvider;
    private TypingModule_ProvidesAlexaRenderedCardReceiverFactory providesAlexaRenderedCardReceiverProvider;
    private Provider<AlexaStateTracker> providesAlexaStateTrackerProvider;
    private Provider<AlexaTextResponseTracker> providesAlexaTextResponseTrackerProvider;
    private Provider<Component> providesComponentProvider;
    private Provider<Context> providesContextProvider;
    private Provider<ConversationSessionTimer> providesConversationSessionTimerProvider;
    private Provider providesDefaultTtaMessageHandlerProvider;
    private Provider<DefaultTtaResultHandler> providesDefaultTtaResultHandlerProvider;
    private Provider<EventClock> providesEventClockProvider;
    private Provider<FeatureChecker> providesFeatureCheckerProvider;
    private Provider<FeatureFlagConsumer> providesFeatureFlagConsumerProvider;
    private Provider<FeatureQuery> providesFeatureQueryProvider;
    private Provider<FrictionHelper> providesFrictionHelperProvider;
    private Provider<Gson> providesGsonProvider;
    private Provider<IngressParameters.Provider> providesIngressParametersProvider;
    private Provider<MessageHistoryManager> providesMessageHistoryManagerProvider;
    private Provider<MetricEventProcessingService> providesMetricEventProcessingServiceProvider;
    private Provider<PermissionsUtil> providesPermissionsUtilProvider;
    private Provider<PersistentStorage> providesPersistentStorageProvider;
    private Provider<SearchWorkflow> providesSearchWorkflowProvider;
    private Provider<SimbaEventProcessor> providesSimbaEventProcessorProvider;
    private Provider<SimbaMediator> providesSimbaMediatorProvider;
    private Provider<SuggestionsHandler> providesSuggestionsHandlerProvider;
    private Provider<TtaUiEventWithTypeProcessor> providesTtaEventProcessorProvider;
    private Provider<TtaEventSender> providesTtaEventSenderProvider;
    private Provider<TtaIngressEventProcessor> providesTtaIngressEventProcessorProvider;
    private Provider<TtaInteractorProvider> providesTtaInteractorProvider;
    private Provider<TtaMessageHandler> providesTtaMessageHandlerProvider;
    private Provider<TtaMessageSanitizer> providesTtaMessageSanitizerProvider;
    private Provider<TtaNavigator> providesTtaNavigatorProvider;
    private Provider<TtaResultHandler> providesTtaResultHandlerProvider;
    private Provider<TtaSuggestionHandler> providesTtaSuggestionHandlerProvider;
    private Provider<TtaUiEventProcessor> providesTtaUiEventProcessorProvider;
    private Provider<TtaUiEventWithCountProcessor> providesTtaUiEventWithDataProcessorProvider;
    private Provider<TtaUplEventProcessor> providesTtaUplEventProcessorProvider;
    private Provider<ViewGroup> providesTypingContainerProvider;
    private Provider<RouterDelegate> providesTypingRouterDelegateProvider;
    private Provider<Router> providesTypingRouterProvider;
    private Provider<UiEventReporter> providesUiEventReporterProvider;
    private Provider<SearchInteractor> searchInteractorProvider;
    private Provider<SearchModel> searchModelProvider;
    private Provider<TtaSuggestionModel> ttaSuggestionModelProvider;
    private Provider<TtaSuggestionsInteractor> ttaSuggestionsInteractorProvider;
    private Provider<TypingInteractor> typingInteractorProvider;
    private Provider<TypingModel> typingModelProvider;
    private Provider<TypingPresenter> typingPresenterProvider;
    private Provider<TypingView> typingViewProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private DependenciesModule dependenciesModule;
        private FeaturesModule featuresModule;
        private MetricsModule metricsModule;
        private PermissionsModule permissionsModule;
        private SdkModule sdkModule;
        private SearchModule searchModule;
        private StateMachineModule stateMachineModule;
        private SuggestionsModule suggestionsModule;
        private TtaModule ttaModule;
        private TypingModule typingModule;

        public TtaActivity.TtaComponent build() {
            Preconditions.checkBuilderRequirement(this.typingModule, TypingModule.class);
            Preconditions.checkBuilderRequirement(this.ttaModule, TtaModule.class);
            if (this.searchModule == null) {
                this.searchModule = new SearchModule();
            }
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            if (this.sdkModule == null) {
                this.sdkModule = new SdkModule();
            }
            if (this.suggestionsModule == null) {
                this.suggestionsModule = new SuggestionsModule();
            }
            if (this.stateMachineModule == null) {
                this.stateMachineModule = new StateMachineModule();
            }
            if (this.dependenciesModule == null) {
                this.dependenciesModule = new DependenciesModule();
            }
            if (this.featuresModule == null) {
                this.featuresModule = new FeaturesModule();
            }
            if (this.permissionsModule == null) {
                this.permissionsModule = new PermissionsModule();
            }
            return new DaggerTtaActivity_TtaComponent(this);
        }

        public Builder dependenciesModule(DependenciesModule dependenciesModule) {
            this.dependenciesModule = (DependenciesModule) Preconditions.checkNotNull(dependenciesModule);
            return this;
        }

        public Builder featuresModule(FeaturesModule featuresModule) {
            this.featuresModule = (FeaturesModule) Preconditions.checkNotNull(featuresModule);
            return this;
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        public Builder permissionsModule(PermissionsModule permissionsModule) {
            this.permissionsModule = (PermissionsModule) Preconditions.checkNotNull(permissionsModule);
            return this;
        }

        public Builder sdkModule(SdkModule sdkModule) {
            this.sdkModule = (SdkModule) Preconditions.checkNotNull(sdkModule);
            return this;
        }

        public Builder searchModule(SearchModule searchModule) {
            this.searchModule = (SearchModule) Preconditions.checkNotNull(searchModule);
            return this;
        }

        @Deprecated
        public Builder simbaModule(SimbaModule simbaModule) {
            Preconditions.checkNotNull(simbaModule);
            return this;
        }

        public Builder stateMachineModule(StateMachineModule stateMachineModule) {
            this.stateMachineModule = (StateMachineModule) Preconditions.checkNotNull(stateMachineModule);
            return this;
        }

        public Builder suggestionsModule(SuggestionsModule suggestionsModule) {
            this.suggestionsModule = (SuggestionsModule) Preconditions.checkNotNull(suggestionsModule);
            return this;
        }

        public Builder ttaModule(TtaModule ttaModule) {
            this.ttaModule = (TtaModule) Preconditions.checkNotNull(ttaModule);
            return this;
        }

        public Builder typingModule(TypingModule typingModule) {
            this.typingModule = (TypingModule) Preconditions.checkNotNull(typingModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesTypingContainerProvider = DoubleCheck.provider(TypingModule_ProvidesTypingContainerFactory.create(builder.typingModule));
        this.providesTtaMessageSanitizerProvider = DoubleCheck.provider(TypingModule_ProvidesTtaMessageSanitizerFactory.create(builder.typingModule));
        this.providesDefaultTtaMessageHandlerProvider = DoubleCheck.provider(TypingModule_ProvidesDefaultTtaMessageHandlerFactory.create(builder.typingModule, this.providesTtaMessageSanitizerProvider));
        this.providesTtaMessageHandlerProvider = DoubleCheck.provider(TypingModule_ProvidesTtaMessageHandlerFactory.create(builder.typingModule, this.providesDefaultTtaMessageHandlerProvider));
        this.providesDefaultTtaResultHandlerProvider = DoubleCheck.provider(SearchModule_ProvidesDefaultTtaResultHandlerFactory.create(builder.searchModule));
        this.providesTtaResultHandlerProvider = DoubleCheck.provider(SearchModule_ProvidesTtaResultHandlerFactory.create(builder.searchModule, this.providesDefaultTtaResultHandlerProvider));
        this.providesTtaNavigatorProvider = DoubleCheck.provider(TypingModule_ProvidesTtaNavigatorFactory.create(builder.typingModule));
        this.providesContextProvider = DoubleCheck.provider(TtaModule_ProvidesContextFactory.create(builder.ttaModule));
        this.providesUiEventReporterProvider = DoubleCheck.provider(SdkModule_ProvidesUiEventReporterFactory.create(builder.sdkModule, this.providesContextProvider));
        this.providesTtaUiEventProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaUiEventProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesTtaIngressEventProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaIngressEventProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesTtaUplEventProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaUplEventProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesTtaEventProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaEventProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesSimbaEventProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesSimbaEventProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesTtaUiEventWithDataProcessorProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory.create(builder.metricsModule, this.providesUiEventReporterProvider));
        this.providesMetricEventProcessingServiceProvider = DoubleCheck.provider(MetricsModule_ProvidesMetricEventProcessingServiceFactory.create(builder.metricsModule, this.providesTtaUiEventProcessorProvider, this.providesTtaIngressEventProcessorProvider, this.providesTtaUplEventProcessorProvider, this.providesTtaEventProcessorProvider, this.providesSimbaEventProcessorProvider, this.providesTtaUiEventWithDataProcessorProvider));
        this.providesTtaEventSenderProvider = DoubleCheck.provider(MetricsModule_ProvidesTtaEventSenderFactory.create(builder.metricsModule, this.providesMetricEventProcessingServiceProvider));
        this.providesTtaSuggestionHandlerProvider = DoubleCheck.provider(SuggestionsModule_ProvidesTtaSuggestionHandlerFactory.create(builder.suggestionsModule));
        this.providesSuggestionsHandlerProvider = DoubleCheck.provider(SuggestionsModule_ProvidesSuggestionsHandlerFactory.create(builder.suggestionsModule, this.providesTtaSuggestionHandlerProvider));
        this.providesComponentProvider = DoubleCheck.provider(TtaModule_ProvidesComponentFactory.create(builder.ttaModule, this.providesTtaMessageHandlerProvider, this.providesTtaResultHandlerProvider, this.providesTtaNavigatorProvider, this.providesTtaEventSenderProvider, this.providesSuggestionsHandlerProvider));
        this.providesTypingRouterProvider = DoubleCheck.provider(TypingModule_ProvidesTypingRouterFactory.create(builder.typingModule, this.providesTypingContainerProvider, this.providesComponentProvider));
        this.providesTypingRouterDelegateProvider = DoubleCheck.provider(TypingModule_ProvidesTypingRouterDelegateFactory.create(builder.typingModule, this.providesTypingRouterProvider));
        this.typingViewProvider = DoubleCheck.provider(TypingView_Factory.create(this.providesTypingRouterDelegateProvider));
        this.providesPersistentStorageProvider = DoubleCheck.provider(TypingModule_ProvidesPersistentStorageFactory.create(builder.typingModule, this.providesContextProvider));
        this.providesEventClockProvider = DoubleCheck.provider(MetricsModule_ProvidesEventClockFactory.create(builder.metricsModule));
        this.providesActivityProvider = DoubleCheck.provider(TtaModule_ProvidesActivityFactory.create(builder.ttaModule));
        this.providesAlexaMobileFrameworkApisProvider = DoubleCheck.provider(SdkModule_ProvidesAlexaMobileFrameworkApisFactory.create(builder.sdkModule, this.providesContextProvider));
        this.providesGsonProvider = DoubleCheck.provider(DependenciesModule_ProvidesGsonFactory.create(builder.dependenciesModule));
        this.providesAlexaClientSdkApisProvider = DoubleCheck.provider(SdkModule_ProvidesAlexaClientSdkApisFactory.create(builder.sdkModule, this.providesAlexaMobileFrameworkApisProvider, this.providesGsonProvider));
        this.providesAlexaStateTrackerProvider = DoubleCheck.provider(SdkModule_ProvidesAlexaStateTrackerFactory.create(builder.sdkModule));
        this.providesAlexaTextResponseTrackerProvider = DoubleCheck.provider(SdkModule_ProvidesAlexaTextResponseTrackerFactory.create(builder.sdkModule, this.providesTtaMessageSanitizerProvider));
        this.providesAlexaRenderedCardReceiverProvider = TypingModule_ProvidesAlexaRenderedCardReceiverFactory.create(builder.typingModule, this.providesContextProvider);
        this.providesAlexaPlayerInfoCardTrackerProvider = DoubleCheck.provider(SdkModule_ProvidesAlexaPlayerInfoCardTrackerFactory.create(builder.sdkModule));
        this.providesAlexaMediatorProvider = DoubleCheck.provider(StateMachineModule_ProvidesAlexaMediatorFactory.create(builder.stateMachineModule, this.providesAlexaClientSdkApisProvider, this.providesAlexaStateTrackerProvider, this.providesAlexaTextResponseTrackerProvider, this.providesAlexaRenderedCardReceiverProvider, this.providesAlexaPlayerInfoCardTrackerProvider, this.providesTtaMessageSanitizerProvider, this.providesTtaEventSenderProvider));
        this.providesConversationSessionTimerProvider = DoubleCheck.provider(TypingModule_ProvidesConversationSessionTimerFactory.create(builder.typingModule));
        this.providesMessageHistoryManagerProvider = DoubleCheck.provider(TypingModule_ProvidesMessageHistoryManagerFactory.create(builder.typingModule, this.providesContextProvider, this.providesPersistentStorageProvider, this.providesConversationSessionTimerProvider, this.providesMetricEventProcessingServiceProvider));
        this.typingModelProvider = DoubleCheck.provider(TypingModel_Factory.create(this.providesAlexaMediatorProvider, this.providesMessageHistoryManagerProvider, this.providesContextProvider));
        this.providesIngressParametersProvider = DoubleCheck.provider(TypingModule_ProvidesIngressParametersProviderFactory.create(builder.typingModule, this.providesEventClockProvider));
        this.providesFeatureFlagConsumerProvider = DoubleCheck.provider(FeaturesModule_ProvidesFeatureFlagConsumerFactory.create(builder.featuresModule, this.providesContextProvider));
        this.providesFeatureQueryProvider = DoubleCheck.provider(FeaturesModule_ProvidesFeatureQueryFactory.create(builder.featuresModule, this.providesFeatureFlagConsumerProvider));
        this.providesFeatureCheckerProvider = DoubleCheck.provider(FeaturesModule_ProvidesFeatureCheckerFactory.create(builder.featuresModule, this.providesFeatureQueryProvider));
        this.typingInteractorProvider = DoubleCheck.provider(TypingInteractor_Factory.create(this.providesActivityProvider, this.typingModelProvider, this.providesDefaultTtaMessageHandlerProvider, this.providesMessageHistoryManagerProvider, this.providesMetricEventProcessingServiceProvider, this.providesIngressParametersProvider, this.providesFeatureCheckerProvider));
        this.providesFrictionHelperProvider = DoubleCheck.provider(SearchModule_ProvidesFrictionHelperFactory.create(builder.searchModule));
        this.providesSimbaMediatorProvider = DoubleCheck.provider(StateMachineModule_ProvidesSimbaMediatorFactory.create(builder.stateMachineModule, this.providesContextProvider, this.providesAlexaMediatorProvider, this.providesFrictionHelperProvider, this.providesTtaEventSenderProvider));
        this.providesPermissionsUtilProvider = DoubleCheck.provider(PermissionsModule_ProvidesPermissionsUtilFactory.create(builder.permissionsModule));
        this.searchModelProvider = DoubleCheck.provider(SearchModel_Factory.create(this.providesContextProvider, this.providesSimbaMediatorProvider, this.providesPermissionsUtilProvider));
        this.providesSearchWorkflowProvider = DoubleCheck.provider(StateMachineModule_ProvidesSearchWorkflowFactory.create(builder.stateMachineModule, this.providesAlexaMediatorProvider, this.providesSimbaMediatorProvider));
        this.searchInteractorProvider = DoubleCheck.provider(SearchInteractor_Factory.create(this.providesActivityProvider, this.searchModelProvider, this.providesSearchWorkflowProvider, this.providesDefaultTtaResultHandlerProvider, this.providesTtaNavigatorProvider, this.providesPersistentStorageProvider, this.providesPermissionsUtilProvider));
        this.ttaSuggestionModelProvider = DoubleCheck.provider(TtaSuggestionModel_Factory.create(this.providesSimbaMediatorProvider, this.providesContextProvider, this.providesAlexaClientSdkApisProvider));
        this.ttaSuggestionsInteractorProvider = DoubleCheck.provider(TtaSuggestionsInteractor_Factory.create(this.ttaSuggestionModelProvider, this.providesTtaSuggestionHandlerProvider, this.providesTtaNavigatorProvider, this.providesContextProvider));
        this.providesTtaInteractorProvider = DoubleCheck.provider(TypingModule_ProvidesTtaInteractorProviderFactory.create(builder.typingModule, this.typingInteractorProvider, this.searchInteractorProvider, this.ttaSuggestionsInteractorProvider));
        this.typingPresenterProvider = DoubleCheck.provider(TypingPresenter_Factory.create(this.typingViewProvider, this.providesPersistentStorageProvider, this.providesMetricEventProcessingServiceProvider, this.providesEventClockProvider, this.providesTtaInteractorProvider));
    }

    private TtaActivity injectTtaActivity(TtaActivity ttaActivity) {
        TtaActivity_MembersInjector.injectTypingPresenter(ttaActivity, this.typingPresenterProvider.mo10268get());
        return ttaActivity;
    }

    @Override // com.amazon.alexa.voice.tta.TtaActivity.TtaComponent
    public void inject(TtaActivity ttaActivity) {
        injectTtaActivity(ttaActivity);
    }

    private DaggerTtaActivity_TtaComponent(Builder builder) {
        initialize(builder);
    }
}
