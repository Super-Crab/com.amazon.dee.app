package com.amazon.alexa.client.metrics.dcm;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.ConditionalMetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class DCMClientModule {
    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.DCM)
    public MetricsConnector providesDCMConditionalMetricsConnector(final Lazy<DCMMetricsConnector> lazy, @Named("DCM") MetricsStatusProvider metricsStatusProvider) {
        lazy.getClass();
        return new ConditionalMetricsConnector(new ConditionalMetricsConnector.GetConnector() { // from class: com.amazon.alexa.client.metrics.dcm.-$$Lambda$x6fs8Emo2-mIpRpT7dJDyw5uVh8
            @Override // com.amazon.alexa.client.metrics.core.ConditionalMetricsConnector.GetConnector
            public final MetricsConnector get() {
                return (MetricsConnector) Lazy.this.mo358get();
            }
        }, metricsStatusProvider);
    }
}
