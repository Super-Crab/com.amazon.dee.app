package com.amazon.alexa.alertsca.alertdisplay;

import com.amazon.alexa.alertsca.alertdisplay.AlertsDisplayActivity;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMetricsServiceFactory;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMobilyticsFactory;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DaggerAlertsDisplayActivity_Injector implements AlertsDisplayActivity.Injector {
    private Provider<MetricsService> provideMetricsServiceProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private MetricsModule metricsModule;

        public AlertsDisplayActivity.Injector build() {
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            return new DaggerAlertsDisplayActivity_Injector(this);
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AlertsDisplayActivity.Injector create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideMobilyticsProvider = DoubleCheck.provider(MetricsModule_ProvideMobilyticsFactory.create(builder.metricsModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(MetricsModule_ProvideMetricsServiceFactory.create(builder.metricsModule, this.provideMobilyticsProvider));
    }

    @CanIgnoreReturnValue
    private AlertsDisplayActivity injectAlertsDisplayActivity(AlertsDisplayActivity alertsDisplayActivity) {
        AlertsDisplayActivity_MembersInjector.injectMetricsService(alertsDisplayActivity, this.provideMetricsServiceProvider.mo10268get());
        return alertsDisplayActivity;
    }

    @Override // com.amazon.alexa.alertsca.alertdisplay.AlertsDisplayActivity.Injector
    public void inject(AlertsDisplayActivity alertsDisplayActivity) {
        injectAlertsDisplayActivity(alertsDisplayActivity);
    }

    private DaggerAlertsDisplayActivity_Injector(Builder builder) {
        initialize(builder);
    }
}
