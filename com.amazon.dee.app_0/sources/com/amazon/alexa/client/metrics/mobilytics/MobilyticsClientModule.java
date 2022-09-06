package com.amazon.alexa.client.metrics.mobilytics;

import android.content.Context;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.ConditionalMetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEventFactory;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class MobilyticsClientModule {
    @Provides
    public Mobilytics provideMobilytics(MobilyticsConfiguration mobilyticsConfiguration) {
        return MobilyticsClientFactory.create(mobilyticsConfiguration);
    }

    @Provides
    public MobilyticsConfiguration provideMobilyticsConfiguration(Context context, ClientConfiguration clientConfiguration, MobilyticsUserProviderImpl mobilyticsUserProviderImpl, MobilyticsDeviceProviderImpl mobilyticsDeviceProviderImpl) {
        return MobilyticsConfiguration.builder().withContext(context).withApplicationId(clientConfiguration.getAwsMobileAnalyticsApplicationId()).withServiceName(clientConfiguration.getMetricsServiceName()).withDomain(2).withDeviceProvider(mobilyticsDeviceProviderImpl).withUserProvider(mobilyticsUserProviderImpl).withDebug((context.getApplicationInfo().flags & 2) != 0).build();
    }

    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.MOBILYTICS_V2)
    public MetricsConnector providesMobilyticsConditionalMetricsConnector(final Lazy<MobilyticsMetricsConnector> lazy, @Named("mobilytics_v2") MetricsStatusProvider metricsStatusProvider) {
        lazy.getClass();
        return new ConditionalMetricsConnector(new ConditionalMetricsConnector.GetConnector() { // from class: com.amazon.alexa.client.metrics.mobilytics.-$$Lambda$x6fs8Emo2-mIpRpT7dJDyw5uVh8
            @Override // com.amazon.alexa.client.metrics.core.ConditionalMetricsConnector.GetConnector
            public final MetricsConnector get() {
                return (MetricsConnector) Lazy.this.mo358get();
            }
        }, metricsStatusProvider);
    }

    @Provides
    @Singleton
    public MobilyticsDeviceProviderImpl providesMobilyticsDeviceProvider(ClientConfiguration clientConfiguration) {
        return new MobilyticsDeviceProviderImpl(clientConfiguration);
    }

    @Provides
    @Singleton
    public MobilyticsEventFactory providesMobilyticsEventFactory() {
        return new DefaultMobilyticsEventFactory();
    }

    @Provides
    @Singleton
    public MobilyticsMetricsConnector providesMobilyticsMetricsConnector(Lazy<PreloadAttributionManager> lazy, Lazy<Mobilytics> lazy2, Lazy<MobilyticsEventFactory> lazy3, CrashReporter crashReporter, @Named("androidId") String str, Lazy<ClientConfiguration> lazy4, MobilyticsUserProviderImpl mobilyticsUserProviderImpl) {
        return new MobilyticsMetricsConnector(lazy, lazy2, lazy3, crashReporter, str, lazy4, mobilyticsUserProviderImpl);
    }

    @Provides
    @Singleton
    public MobilyticsUserProviderImpl providesMobilyticsUserProvider(AccountManager accountManager, MarketplaceAuthority marketplaceAuthority) {
        return new MobilyticsUserProviderImpl(accountManager, marketplaceAuthority);
    }
}
