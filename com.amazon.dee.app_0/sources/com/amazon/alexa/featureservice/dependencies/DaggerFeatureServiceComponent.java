package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase;
import com.amazon.alexa.featureservice.implementation.FeatureCache_Factory;
import com.amazon.alexa.featureservice.implementation.FeatureRegistryUtil_Factory;
import com.amazon.alexa.featureservice.implementation.FeatureServiceViewModel;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.featureservice.repo.mapper.FeatureCollectionMapper_Factory;
import com.amazon.alexa.featureservice.repo.mapper.FeatureDataMapper_Factory;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import com.amazon.alexa.featureservice.service.FeatureSubscriptionManager_Factory;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import com.amazon.alexa.featureservice.util.Analytics;
import com.amazon.alexa.featureservice.util.FeatureSubscriptionMap_Factory;
import com.amazon.alexa.featureservice.util.SafeEventBus;
import com.amazon.alexa.featureservice.util.TimeUtil_Factory;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.dee.app.http.CoralService;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DaggerFeatureServiceComponent implements FeatureServiceComponent {
    private FeatureCache_Factory featureCacheProvider;
    private FeatureCollectionMapper_Factory featureCollectionMapperProvider;
    private FeatureSubscriptionManager_Factory featureSubscriptionManagerProvider;
    private Provider<FeatureAppDatabase> provideRoomDatabaseProvider;
    private Provider<Analytics> providesAnalyticsProvider;
    private Provider<Context> providesContextProvider;
    private Provider<CoralService> providesCoralServiceProvider;
    private Provider<EnvironmentService> providesEnvironmentServiceProvider;
    private Provider<EventBus> providesEventBusProvider;
    private Provider<FeatureFlagDao> providesFeatureDaoProvider;
    private Provider<FeatureDataRepo> providesFeatureRepositoryProvider;
    private Provider<FeatureServiceClient> providesFeatureServiceClientProvider;
    private Provider<FeatureServiceV2> providesFeatureServiceProvider;
    private Provider<FeatureServiceViewModel> providesFeatureServiceViewModelProvider;
    private Provider<Gson> providesGsonInstanceProvider;
    private Provider<Mobilytics> providesMobilyticsProvider;
    private NetworkModule_ProvidesOkHttpClientFactory providesOkHttpClientProvider;
    private Provider<PlatformFeatureServiceV2> providesPlatformFeatureServiceProvider;
    private Provider<RecordTriggerService> providesRecordTriggerServiceProvider;
    private Provider<RxFeatureDao> providesRxFeatureDaoProvider;
    private Provider<SafeEventBus> providesSafeEventBusProvider;
    private Provider<SessionManager> providesSessionManagerProvider;
    private Provider<FeatureServiceConfiguration> providesTestConfigurationProvider;

    /* loaded from: classes7.dex */
    public static final class Builder {
        private BaseModule baseModule;
        private DatabaseModule databaseModule;
        private ImplementationModule implementationModule;
        private NetworkModule networkModule;
        private RepositoryModule repositoryModule;

        public Builder baseModule(BaseModule baseModule) {
            this.baseModule = (BaseModule) Preconditions.checkNotNull(baseModule);
            return this;
        }

        public FeatureServiceComponent build() {
            if (this.implementationModule == null) {
                this.implementationModule = new ImplementationModule();
            }
            if (this.repositoryModule == null) {
                this.repositoryModule = new RepositoryModule();
            }
            Preconditions.checkBuilderRequirement(this.networkModule, NetworkModule.class);
            Preconditions.checkBuilderRequirement(this.baseModule, BaseModule.class);
            if (this.databaseModule == null) {
                this.databaseModule = new DatabaseModule();
            }
            return new DaggerFeatureServiceComponent(this);
        }

        public Builder databaseModule(DatabaseModule databaseModule) {
            this.databaseModule = (DatabaseModule) Preconditions.checkNotNull(databaseModule);
            return this;
        }

        public Builder implementationModule(ImplementationModule implementationModule) {
            this.implementationModule = (ImplementationModule) Preconditions.checkNotNull(implementationModule);
            return this;
        }

        public Builder networkModule(NetworkModule networkModule) {
            this.networkModule = (NetworkModule) Preconditions.checkNotNull(networkModule);
            return this;
        }

        public Builder repositoryModule(RepositoryModule repositoryModule) {
            this.repositoryModule = (RepositoryModule) Preconditions.checkNotNull(repositoryModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesOkHttpClientProvider = NetworkModule_ProvidesOkHttpClientFactory.create(builder.networkModule);
        this.providesEnvironmentServiceProvider = DoubleCheck.provider(BaseModule_ProvidesEnvironmentServiceFactory.create(builder.baseModule));
        this.providesFeatureServiceClientProvider = DoubleCheck.provider(NetworkModule_ProvidesFeatureServiceClientFactory.create(builder.networkModule, this.providesOkHttpClientProvider, this.providesEnvironmentServiceProvider));
        this.providesContextProvider = DoubleCheck.provider(BaseModule_ProvidesContextFactory.create(builder.baseModule));
        this.provideRoomDatabaseProvider = DoubleCheck.provider(DatabaseModule_ProvideRoomDatabaseFactory.create(builder.databaseModule, this.providesContextProvider));
        this.providesFeatureDaoProvider = DoubleCheck.provider(DatabaseModule_ProvidesFeatureDaoFactory.create(builder.databaseModule, this.provideRoomDatabaseProvider));
        this.providesRxFeatureDaoProvider = DoubleCheck.provider(DatabaseModule_ProvidesRxFeatureDaoFactory.create(builder.databaseModule, this.providesFeatureDaoProvider));
        this.featureCollectionMapperProvider = FeatureCollectionMapper_Factory.create(FeatureDataMapper_Factory.create());
        this.providesFeatureRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvidesFeatureRepositoryFactory.create(builder.repositoryModule, this.providesFeatureServiceClientProvider, this.providesRxFeatureDaoProvider, this.featureCollectionMapperProvider));
        this.providesCoralServiceProvider = DoubleCheck.provider(BaseModule_ProvidesCoralServiceFactory.create(builder.baseModule));
        this.providesMobilyticsProvider = DoubleCheck.provider(BaseModule_ProvidesMobilyticsFactory.create(builder.baseModule));
        this.providesRecordTriggerServiceProvider = DoubleCheck.provider(ImplementationModule_ProvidesRecordTriggerServiceFactory.create(builder.implementationModule, this.providesCoralServiceProvider, this.providesMobilyticsProvider));
        this.providesGsonInstanceProvider = DoubleCheck.provider(BaseModule_ProvidesGsonInstanceFactory.create(builder.baseModule));
        this.featureCacheProvider = FeatureCache_Factory.create(this.providesGsonInstanceProvider);
        this.featureSubscriptionManagerProvider = FeatureSubscriptionManager_Factory.create(FeatureSubscriptionMap_Factory.create());
        this.providesAnalyticsProvider = DoubleCheck.provider(BaseModule_ProvidesAnalyticsFactory.create(builder.baseModule));
        this.providesSafeEventBusProvider = DoubleCheck.provider(BaseModule_ProvidesSafeEventBusFactory.create(builder.baseModule));
        this.providesTestConfigurationProvider = DoubleCheck.provider(ImplementationModule_ProvidesTestConfigurationFactory.create(builder.implementationModule));
        this.providesFeatureServiceViewModelProvider = DoubleCheck.provider(ImplementationModule_ProvidesFeatureServiceViewModelFactory.create(builder.implementationModule, this.providesFeatureRepositoryProvider, FeatureRegistryUtil_Factory.create(), this.providesRecordTriggerServiceProvider, this.featureCacheProvider, this.featureSubscriptionManagerProvider, this.providesAnalyticsProvider, this.providesSafeEventBusProvider, this.providesGsonInstanceProvider, TimeUtil_Factory.create(), this.providesTestConfigurationProvider));
        this.providesEventBusProvider = DoubleCheck.provider(BaseModule_ProvidesEventBusFactory.create(builder.baseModule));
        this.providesSessionManagerProvider = DoubleCheck.provider(ImplementationModule_ProvidesSessionManagerFactory.create(builder.implementationModule, this.providesEventBusProvider, this.providesTestConfigurationProvider));
        this.providesPlatformFeatureServiceProvider = DoubleCheck.provider(ImplementationModule_ProvidesPlatformFeatureServiceFactory.create(builder.implementationModule, this.providesFeatureServiceViewModelProvider, this.providesSessionManagerProvider, this.providesSafeEventBusProvider, this.providesAnalyticsProvider));
        this.providesFeatureServiceProvider = DoubleCheck.provider(ImplementationModule_ProvidesFeatureServiceFactory.create(builder.implementationModule, this.providesPlatformFeatureServiceProvider));
    }

    @Override // com.amazon.alexa.featureservice.dependencies.FeatureServiceComponent
    public FeatureServiceV2 getFeatureService() {
        return this.providesFeatureServiceProvider.mo10268get();
    }

    @Override // com.amazon.alexa.featureservice.dependencies.FeatureServiceComponent
    public FeatureServiceConfiguration getTestConfiguration() {
        return this.providesTestConfigurationProvider.mo10268get();
    }

    private DaggerFeatureServiceComponent(Builder builder) {
        initialize(builder);
    }
}
