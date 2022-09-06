package com.amazon.commscore.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.commsbridge.dependencies.CommsBridgeServiceModule;
import com.amazon.commscore.commsbridge.dependencies.CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory;
import com.amazon.commscore.commsidentity.common.MAPAuthenticationInterceptor;
import com.amazon.commscore.commsidentity.common.TimeUtil_Factory;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.roomdb.CommsCoreIdentityDatabase;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesAcmsClientFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesAcmsOkHttpClientFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesCommsIdentityDaoFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesCommsIdentityProviderFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesConfigServiceFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesIdentityServiceFactory;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule_ProvidesMAPAuthInterceptorFactory;
import com.amazon.commscore.commsidentity.implementation.CommsCoreIdentityViewModel_Factory;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import com.amazon.commscore.commsidentity.repo.mapper.AccountForDirectedIdMapper_Factory;
import com.amazon.commscore.commsidentity.repo.mapper.IdentityV2Mapper_Factory;
import com.amazon.commscore.commsidentity.repo.mapper.NameMapper_Factory;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import com.amazon.commscore.featureflag.dependencies.FeatureFlagModule;
import com.amazon.commscore.featureflag.dependencies.FeatureFlagModule_ProvidesFeatureServiceFactory;
import com.amazon.commscore.metrics.dependencies.MetricServiceModule;
import com.amazon.commscore.metrics.dependencies.MetricServiceModule_ProvidesMetricsServiceFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class DaggerCommsCoreComponent implements CommsCoreComponent {
    private ApplicationModule applicationModule;
    private CommsCoreIdentityViewModel_Factory commsCoreIdentityViewModelProvider;
    private IdentityV2Mapper_Factory identityV2MapperProvider;
    private Provider<AcmsClient> providesAcmsClientProvider;
    private Provider<OkHttpClient> providesAcmsOkHttpClientProvider;
    private ApplicationModule_ProvidesApplicationContextFactory providesApplicationContextProvider;
    private Provider<CommsBridgeService> providesCommsBridgeServiceProvider;
    private Provider<AlexaCommsCoreIdentityService> providesCommsCoreIdentityServiceProvider;
    private Provider<CommsIdentityDao> providesCommsIdentityDaoProvider;
    private Provider<CommsCoreIdentityDatabase> providesCommsIdentityDatabaseProvider;
    private Provider<CommsIdentityProvider> providesCommsIdentityProvider;
    private Provider<AlexaCommsCoreRemoteConfigurationService> providesConfigServiceProvider;
    private AlexaModule_ProvidesEventBusFactory providesEventBusProvider;
    private Provider<AlexaCommsCoreFeatureService> providesFeatureServiceProvider;
    private Provider<IdentityService> providesIdentityServiceProvider;
    private Provider<MAPAuthenticationInterceptor> providesMAPAuthInterceptorProvider;
    private Provider<AlexaCommsCoreMetricsService> providesMetricsServiceProvider;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private AlexaModule alexaModule;
        private ApplicationModule applicationModule;
        private CommsBridgeServiceModule commsBridgeServiceModule;
        private CommsIdentityModule commsIdentityModule;
        private FeatureFlagModule featureFlagModule;
        private MetricServiceModule metricServiceModule;

        public Builder alexaModule(AlexaModule alexaModule) {
            this.alexaModule = (AlexaModule) Preconditions.checkNotNull(alexaModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public CommsCoreComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            Preconditions.checkBuilderRequirement(this.featureFlagModule, FeatureFlagModule.class);
            Preconditions.checkBuilderRequirement(this.metricServiceModule, MetricServiceModule.class);
            Preconditions.checkBuilderRequirement(this.commsIdentityModule, CommsIdentityModule.class);
            Preconditions.checkBuilderRequirement(this.alexaModule, AlexaModule.class);
            Preconditions.checkBuilderRequirement(this.commsBridgeServiceModule, CommsBridgeServiceModule.class);
            return new DaggerCommsCoreComponent(this);
        }

        public Builder commsBridgeServiceModule(CommsBridgeServiceModule commsBridgeServiceModule) {
            this.commsBridgeServiceModule = (CommsBridgeServiceModule) Preconditions.checkNotNull(commsBridgeServiceModule);
            return this;
        }

        public Builder commsIdentityModule(CommsIdentityModule commsIdentityModule) {
            this.commsIdentityModule = (CommsIdentityModule) Preconditions.checkNotNull(commsIdentityModule);
            return this;
        }

        public Builder featureFlagModule(FeatureFlagModule featureFlagModule) {
            this.featureFlagModule = (FeatureFlagModule) Preconditions.checkNotNull(featureFlagModule);
            return this;
        }

        public Builder metricServiceModule(MetricServiceModule metricServiceModule) {
            this.metricServiceModule = (MetricServiceModule) Preconditions.checkNotNull(metricServiceModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesFeatureServiceProvider = DoubleCheck.provider(FeatureFlagModule_ProvidesFeatureServiceFactory.create(builder.featureFlagModule));
        this.providesMetricsServiceProvider = DoubleCheck.provider(MetricServiceModule_ProvidesMetricsServiceFactory.create(builder.metricServiceModule));
        this.providesEventBusProvider = AlexaModule_ProvidesEventBusFactory.create(builder.alexaModule);
        this.providesIdentityServiceProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesIdentityServiceFactory.create(builder.commsIdentityModule));
        this.providesMAPAuthInterceptorProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesMAPAuthInterceptorFactory.create(builder.commsIdentityModule));
        this.providesAcmsOkHttpClientProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesAcmsOkHttpClientFactory.create(builder.commsIdentityModule, this.providesMAPAuthInterceptorProvider));
        this.providesConfigServiceProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesConfigServiceFactory.create(builder.commsIdentityModule));
        this.providesAcmsClientProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesAcmsClientFactory.create(builder.commsIdentityModule, this.providesAcmsOkHttpClientProvider, this.providesConfigServiceProvider));
        this.providesApplicationContextProvider = ApplicationModule_ProvidesApplicationContextFactory.create(builder.applicationModule);
        this.providesCommsIdentityDatabaseProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesCommsIdentityDatabaseFactory.create(builder.commsIdentityModule, this.providesApplicationContextProvider));
        this.providesCommsIdentityDaoProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesCommsIdentityDaoFactory.create(builder.commsIdentityModule, this.providesCommsIdentityDatabaseProvider));
        this.identityV2MapperProvider = IdentityV2Mapper_Factory.create(NameMapper_Factory.create());
        this.providesCommsIdentityProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesCommsIdentityProviderFactory.create(builder.commsIdentityModule, this.providesAcmsClientProvider, this.providesCommsIdentityDaoProvider, this.identityV2MapperProvider, AccountForDirectedIdMapper_Factory.create()));
        this.commsCoreIdentityViewModelProvider = CommsCoreIdentityViewModel_Factory.create(this.providesIdentityServiceProvider, this.providesCommsIdentityProvider);
        this.providesCommsCoreIdentityServiceProvider = DoubleCheck.provider(CommsIdentityModule_ProvidesCommsCoreIdentityServiceFactory.create(builder.commsIdentityModule, this.providesEventBusProvider, this.commsCoreIdentityViewModelProvider, TimeUtil_Factory.create()));
        this.providesCommsBridgeServiceProvider = DoubleCheck.provider(CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory.create(builder.commsBridgeServiceModule));
    }

    @Override // com.amazon.commscore.dependencies.CommsCoreComponent
    public CommsBridgeService getCommsBridgeService() {
        return this.providesCommsBridgeServiceProvider.mo10268get();
    }

    @Override // com.amazon.commscore.dependencies.CommsCoreComponent
    public Context getContext() {
        return ApplicationModule_ProvidesApplicationContextFactory.proxyProvidesApplicationContext(this.applicationModule);
    }

    @Override // com.amazon.commscore.dependencies.CommsCoreComponent
    public AlexaCommsCoreFeatureService getFeatureService() {
        return this.providesFeatureServiceProvider.mo10268get();
    }

    @Override // com.amazon.commscore.dependencies.CommsCoreComponent
    public AlexaCommsCoreIdentityService getIdentityService() {
        return this.providesCommsCoreIdentityServiceProvider.mo10268get();
    }

    @Override // com.amazon.commscore.dependencies.CommsCoreComponent
    public AlexaCommsCoreMetricsService getMetricsService() {
        return this.providesMetricsServiceProvider.mo10268get();
    }

    private DaggerCommsCoreComponent(Builder builder) {
        this.applicationModule = builder.applicationModule;
        initialize(builder);
    }
}
