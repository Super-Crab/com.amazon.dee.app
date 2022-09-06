package com.amazon.alexa.alertsca;

import android.app.AlarmManager;
import android.content.Context;
import com.amazon.alexa.alertsca.AlertReschedulingBootCompletedReceiver;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesAlarmManagerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesApplicationContextFactory;
import com.amazon.alexa.alertsca.dependencies.DataModule;
import com.amazon.alexa.alertsca.dependencies.DataModule_ProvidesCacheDirectoryFileFactory;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule;
import com.amazon.alexa.alertsca.dependencies.GsonModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMetricsServiceFactory;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMobilyticsFactory;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DaggerAlertReschedulingBootCompletedReceiver_Injector implements AlertReschedulingBootCompletedReceiver.Injector {
    private Provider<AlertsAnalyzer> alertsAnalyzerProvider;
    private Provider<AlertsDatabaseHelper> alertsDatabaseHelperProvider;
    private Provider<AlertsScheduler> alertsSchedulerProvider;
    private Provider<AlertsStore> alertsStoreProvider;
    private Provider<AssetDownloader> assetDownloaderProvider;
    private Provider<MetricsService> provideMetricsServiceProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private Provider<AlarmManager> providesAlarmManagerProvider;
    private Provider<Context> providesApplicationContextProvider;
    private Provider<File> providesCacheDirectoryFileProvider;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;
        private DataModule dataModule;
        private MetricsModule metricsModule;

        @Deprecated
        public Builder alertsEventBusModule(AlertsEventBusModule alertsEventBusModule) {
            Preconditions.checkNotNull(alertsEventBusModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public AlertReschedulingBootCompletedReceiver.Injector build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.dataModule == null) {
                this.dataModule = new DataModule();
            }
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            return new DaggerAlertReschedulingBootCompletedReceiver_Injector(this);
        }

        public Builder dataModule(DataModule dataModule) {
            this.dataModule = (DataModule) Preconditions.checkNotNull(dataModule);
            return this;
        }

        @Deprecated
        public Builder executorModule(ExecutorModule executorModule) {
            Preconditions.checkNotNull(executorModule);
            return this;
        }

        @Deprecated
        public Builder gsonModule(GsonModule gsonModule) {
            Preconditions.checkNotNull(gsonModule);
            return this;
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

    private void initialize(Builder builder) {
        this.providesApplicationContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesApplicationContextFactory.create(builder.applicationModule));
        this.alertsDatabaseHelperProvider = DoubleCheck.provider(AlertsDatabaseHelper_Factory.create(this.providesApplicationContextProvider));
        this.providesCacheDirectoryFileProvider = DoubleCheck.provider(DataModule_ProvidesCacheDirectoryFileFactory.create(builder.dataModule, this.providesApplicationContextProvider));
        this.assetDownloaderProvider = DoubleCheck.provider(AssetDownloader_Factory.create(this.providesCacheDirectoryFileProvider));
        this.alertsStoreProvider = DoubleCheck.provider(AlertsStore_Factory.create(this.alertsDatabaseHelperProvider, this.assetDownloaderProvider));
        this.alertsAnalyzerProvider = DoubleCheck.provider(AlertsAnalyzer_Factory.create());
        this.providesAlarmManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesAlarmManagerFactory.create(builder.applicationModule));
        this.provideMobilyticsProvider = DoubleCheck.provider(MetricsModule_ProvideMobilyticsFactory.create(builder.metricsModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(MetricsModule_ProvideMetricsServiceFactory.create(builder.metricsModule, this.provideMobilyticsProvider));
        this.alertsSchedulerProvider = DoubleCheck.provider(AlertsScheduler_Factory.create(this.providesAlarmManagerProvider, this.provideMetricsServiceProvider));
    }

    @CanIgnoreReturnValue
    private AlertReschedulingBootCompletedReceiver injectAlertReschedulingBootCompletedReceiver(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver) {
        AlertReschedulingBootCompletedReceiver_MembersInjector.injectAlertsStore(alertReschedulingBootCompletedReceiver, this.alertsStoreProvider.mo10268get());
        AlertReschedulingBootCompletedReceiver_MembersInjector.injectAlertsAnalyzer(alertReschedulingBootCompletedReceiver, this.alertsAnalyzerProvider.mo10268get());
        AlertReschedulingBootCompletedReceiver_MembersInjector.injectAlertsScheduler(alertReschedulingBootCompletedReceiver, this.alertsSchedulerProvider.mo10268get());
        return alertReschedulingBootCompletedReceiver;
    }

    @Override // com.amazon.alexa.alertsca.AlertReschedulingBootCompletedReceiver.Injector
    public void inject(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver) {
        injectAlertReschedulingBootCompletedReceiver(alertReschedulingBootCompletedReceiver);
    }

    private DaggerAlertReschedulingBootCompletedReceiver_Injector(Builder builder) {
        initialize(builder);
    }
}
