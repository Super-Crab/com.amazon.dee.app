package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.activity.ShareSheetActivity;
import com.amazon.alexa.sharing.activity.ShareSheetActivity_MembersInjector;
import com.amazon.alexa.sharing.comms.AlexaSharingClient;
import com.amazon.alexa.sharing.media.clouddrive.MAPAuthenticatedURLConnectionFactory;
import com.amazon.alexa.sharing.media.transmitter.FileTransmitter;
import com.amazon.alexa.sharing.presenter.FallbackStrategyPresenter;
import com.amazon.alexa.sharing.presenter.FallbackStrategyPresenter_Factory;
import com.amazon.alexa.sharing.presenter.ShareToSocialDeepLinkPresenter;
import com.amazon.alexa.sharing.presenter.ShareToSocialDeepLinkPresenter_Factory;
import com.amazon.alexa.sharing.sharingsdk.ContentSharingService;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class DaggerAlexaSharingComponent implements AlexaSharingComponent {
    private AlexaSharingModule alexaSharingModule;
    private Provider<FallbackStrategyPresenter> fallbackStrategyPresenterProvider;
    private AlexaSharingModule_ProvidesCommsBridgeServiceFactory providesCommsBridgeServiceProvider;
    private AlexaSharingModule_ProvidesCommsConfigServiceFactory providesCommsConfigServiceProvider;
    private AlexaSharingModule_ProvidesCommsIdentityServiceFactory providesCommsIdentityServiceProvider;
    private AlexaSharingModule_ProvidesCommsMetricsServiceFactory providesCommsMetricsServiceProvider;
    private AlexaSharingModule_ProvidesFeatureServiceV2Factory providesFeatureServiceV2Provider;
    private Provider<ShareToSocialDeepLinkPresenter> shareToSocialDeepLinkPresenterProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private AlexaSharingModule alexaSharingModule;

        public Builder alexaSharingModule(AlexaSharingModule alexaSharingModule) {
            this.alexaSharingModule = (AlexaSharingModule) Preconditions.checkNotNull(alexaSharingModule);
            return this;
        }

        public AlexaSharingComponent build() {
            Preconditions.checkBuilderRequirement(this.alexaSharingModule, AlexaSharingModule.class);
            return new DaggerAlexaSharingComponent(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AccountConfiguration getAccountConfiguration() {
        return AlexaSharingModule_ProvideAccountConfigurationFactory.proxyProvideAccountConfiguration(this.alexaSharingModule, getMAPAuthenticatedURLConnectionFactory(), getEndpointsCache(), getRequestAuthenticator());
    }

    private AmazonCloudDriveExtendedClient getAmazonCloudDriveExtendedClient() {
        return AlexaSharingModule_ProvideAmazonCloudDriveExtendedClientFactory.proxyProvideAmazonCloudDriveExtendedClient(this.alexaSharingModule, getAccountConfiguration(), getClientConfiguration());
    }

    private ClientConfiguration getClientConfiguration() {
        AlexaSharingModule alexaSharingModule = this.alexaSharingModule;
        return AlexaSharingModule_ProvideClientConfigurationFactory.proxyProvideClientConfiguration(alexaSharingModule, AlexaSharingModule_ProvideHttpClientFactory.proxyProvideHttpClient(alexaSharingModule));
    }

    private EndpointsCache getEndpointsCache() {
        AlexaSharingModule alexaSharingModule = this.alexaSharingModule;
        return AlexaSharingModule_ProvideEndpointsCacheFactory.proxyProvideEndpointsCache(alexaSharingModule, AlexaSharingModule_ProvidesContextFactory.proxyProvidesContext(alexaSharingModule), AlexaSharingModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(this.alexaSharingModule), AlexaSharingModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.alexaSharingModule));
    }

    private FeatureServiceUtil getFeatureServiceUtil() {
        return new FeatureServiceUtil(AlexaSharingModule_ProvidesFeatureServiceV2Factory.proxyProvidesFeatureServiceV2(this.alexaSharingModule));
    }

    private FileTransmitter getFileTransmitter() {
        return AlexaSharingModule_ProvidesFileTransmitterFactory.proxyProvidesFileTransmitter(this.alexaSharingModule, getAmazonCloudDriveExtendedClient(), DoubleCheck.lazy(this.providesCommsMetricsServiceProvider));
    }

    private MAPAuthenticatedURLConnectionFactory getMAPAuthenticatedURLConnectionFactory() {
        AlexaSharingModule alexaSharingModule = this.alexaSharingModule;
        return AlexaSharingModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory.proxyProvideMAPAuthenticatedURLConnectionFactory(alexaSharingModule, AlexaSharingModule_ProvidesContextFactory.proxyProvidesContext(alexaSharingModule), AlexaSharingModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(this.alexaSharingModule));
    }

    private RequestAuthenticator getRequestAuthenticator() {
        AlexaSharingModule alexaSharingModule = this.alexaSharingModule;
        return AlexaSharingModule_ProvideRequestAuthenticatorFactory.proxyProvideRequestAuthenticator(alexaSharingModule, AlexaSharingModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(alexaSharingModule));
    }

    private void initialize(Builder builder) {
        this.providesCommsBridgeServiceProvider = AlexaSharingModule_ProvidesCommsBridgeServiceFactory.create(builder.alexaSharingModule);
        this.providesCommsIdentityServiceProvider = AlexaSharingModule_ProvidesCommsIdentityServiceFactory.create(builder.alexaSharingModule);
        this.providesCommsMetricsServiceProvider = AlexaSharingModule_ProvidesCommsMetricsServiceFactory.create(builder.alexaSharingModule);
        this.providesCommsConfigServiceProvider = AlexaSharingModule_ProvidesCommsConfigServiceFactory.create(builder.alexaSharingModule);
        this.providesFeatureServiceV2Provider = AlexaSharingModule_ProvidesFeatureServiceV2Factory.create(builder.alexaSharingModule);
        this.shareToSocialDeepLinkPresenterProvider = DoubleCheck.provider(ShareToSocialDeepLinkPresenter_Factory.create(this.providesCommsMetricsServiceProvider));
        this.fallbackStrategyPresenterProvider = DoubleCheck.provider(FallbackStrategyPresenter_Factory.create(this.providesCommsMetricsServiceProvider));
    }

    private ShareSheetActivity injectShareSheetActivity(ShareSheetActivity shareSheetActivity) {
        ShareSheetActivity_MembersInjector.injectMetricsServiceLazy(shareSheetActivity, DoubleCheck.lazy(this.providesCommsMetricsServiceProvider));
        ShareSheetActivity_MembersInjector.injectPresenter(shareSheetActivity, this.shareToSocialDeepLinkPresenterProvider.mo10268get());
        ShareSheetActivity_MembersInjector.injectFallbackStrategyPresenter(shareSheetActivity, this.fallbackStrategyPresenterProvider.mo10268get());
        ShareSheetActivity_MembersInjector.injectFeatureServiceUtil(shareSheetActivity, getFeatureServiceUtil());
        return shareSheetActivity;
    }

    @Override // com.amazon.alexa.sharing.comms.dependencies.AlexaSharingComponent
    public ContentSharingService getContentSharingService() {
        return AlexaSharingModule_ProvidesContentSharingServiceFactory.proxyProvidesContentSharingService(this.alexaSharingModule, DoubleCheck.lazy(this.providesCommsMetricsServiceProvider), AlexaSharingModule_ProvidesFeatureServiceV2Factory.proxyProvidesFeatureServiceV2(this.alexaSharingModule), AlexaSharingModule_ProvidesCommsIdentityServiceFactory.proxyProvidesCommsIdentityService(this.alexaSharingModule), AlexaSharingModule_ProvidesContextFactory.proxyProvidesContext(this.alexaSharingModule));
    }

    @Override // com.amazon.alexa.sharing.comms.dependencies.AlexaSharingComponent
    public AlexaSharingClient getSharingClient() {
        return AlexaSharingModule_ProvidesAlexaSharingClientFactory.proxyProvidesAlexaSharingClient(this.alexaSharingModule, DoubleCheck.lazy(this.providesCommsBridgeServiceProvider), DoubleCheck.lazy(this.providesCommsIdentityServiceProvider), DoubleCheck.lazy(this.providesCommsMetricsServiceProvider), DoubleCheck.lazy(this.providesCommsConfigServiceProvider), DoubleCheck.lazy(this.providesFeatureServiceV2Provider), getFileTransmitter());
    }

    @Override // com.amazon.alexa.sharing.comms.dependencies.AlexaSharingComponent
    public void inject(ShareSheetActivity shareSheetActivity) {
        injectShareSheetActivity(shareSheetActivity);
    }

    @Override // com.amazon.alexa.sharing.comms.dependencies.AlexaSharingComponent
    public void inject(ContentSharingService contentSharingService) {
    }

    @Override // com.amazon.alexa.sharing.comms.dependencies.AlexaSharingComponent
    public void inject(Lazy<AlexaCommsCoreMetricsService> lazy) {
    }

    private DaggerAlexaSharingComponent(Builder builder) {
        this.alexaSharingModule = builder.alexaSharingModule;
        initialize(builder);
    }
}
