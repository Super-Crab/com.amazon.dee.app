package com.amazon.alexa.handsfree.storage.metrics;

import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.storage.metrics.database.MetricsCacheDatabaseHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class ProcessMetricsCacheService_MembersInjector implements MembersInjector<ProcessMetricsCacheService> {
    private final Provider<Initializer> mInitializerProvider;
    private final Provider<MetricSerializer> mMetricSerializerProvider;
    private final Provider<MetricsBuilderProvider> mMetricsBuilderProvider;
    private final Provider<MetricsCacheDatabaseHelper> mMetricsCacheDatabaseHelperProvider;
    private final Provider<MetricsEmitter> mMetricsEmitterProvider;

    public ProcessMetricsCacheService_MembersInjector(Provider<Initializer> provider, Provider<MetricsCacheDatabaseHelper> provider2, Provider<MetricsEmitter> provider3, Provider<MetricsBuilderProvider> provider4, Provider<MetricSerializer> provider5) {
        this.mInitializerProvider = provider;
        this.mMetricsCacheDatabaseHelperProvider = provider2;
        this.mMetricsEmitterProvider = provider3;
        this.mMetricsBuilderProvider = provider4;
        this.mMetricSerializerProvider = provider5;
    }

    public static MembersInjector<ProcessMetricsCacheService> create(Provider<Initializer> provider, Provider<MetricsCacheDatabaseHelper> provider2, Provider<MetricsEmitter> provider3, Provider<MetricsBuilderProvider> provider4, Provider<MetricSerializer> provider5) {
        return new ProcessMetricsCacheService_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectMInitializer(ProcessMetricsCacheService processMetricsCacheService, Initializer initializer) {
        processMetricsCacheService.mInitializer = initializer;
    }

    public static void injectMMetricSerializer(ProcessMetricsCacheService processMetricsCacheService, MetricSerializer metricSerializer) {
        processMetricsCacheService.mMetricSerializer = metricSerializer;
    }

    public static void injectMMetricsBuilderProvider(ProcessMetricsCacheService processMetricsCacheService, MetricsBuilderProvider metricsBuilderProvider) {
        processMetricsCacheService.mMetricsBuilderProvider = metricsBuilderProvider;
    }

    public static void injectMMetricsCacheDatabaseHelper(ProcessMetricsCacheService processMetricsCacheService, MetricsCacheDatabaseHelper metricsCacheDatabaseHelper) {
        processMetricsCacheService.mMetricsCacheDatabaseHelper = metricsCacheDatabaseHelper;
    }

    public static void injectMMetricsEmitter(ProcessMetricsCacheService processMetricsCacheService, MetricsEmitter metricsEmitter) {
        processMetricsCacheService.mMetricsEmitter = metricsEmitter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ProcessMetricsCacheService processMetricsCacheService) {
        injectMInitializer(processMetricsCacheService, this.mInitializerProvider.mo10268get());
        injectMMetricsCacheDatabaseHelper(processMetricsCacheService, this.mMetricsCacheDatabaseHelperProvider.mo10268get());
        injectMMetricsEmitter(processMetricsCacheService, this.mMetricsEmitterProvider.mo10268get());
        injectMMetricsBuilderProvider(processMetricsCacheService, this.mMetricsBuilderProvider.mo10268get());
        injectMMetricSerializer(processMetricsCacheService, this.mMetricSerializerProvider.mo10268get());
    }
}
