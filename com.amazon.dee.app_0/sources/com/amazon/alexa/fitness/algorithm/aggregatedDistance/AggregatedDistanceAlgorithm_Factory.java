package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes.dex */
public final class AggregatedDistanceAlgorithm_Factory implements Factory<AggregatedDistanceAlgorithm> {
    private final Provider<FeatureService> featureServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<AfxMessageProcessor> messageProcessorProvider;

    public AggregatedDistanceAlgorithm_Factory(Provider<AfxMessageProcessor> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        this.messageProcessorProvider = provider;
        this.featureServiceProvider = provider2;
        this.logProvider = provider3;
    }

    public static AggregatedDistanceAlgorithm_Factory create(Provider<AfxMessageProcessor> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        return new AggregatedDistanceAlgorithm_Factory(provider, provider2, provider3);
    }

    public static AggregatedDistanceAlgorithm newAggregatedDistanceAlgorithm(AfxMessageProcessor afxMessageProcessor, FeatureService featureService, ILog iLog) {
        return new AggregatedDistanceAlgorithm(afxMessageProcessor, featureService, iLog);
    }

    public static AggregatedDistanceAlgorithm provideInstance(Provider<AfxMessageProcessor> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        return new AggregatedDistanceAlgorithm(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AggregatedDistanceAlgorithm mo10268get() {
        return provideInstance(this.messageProcessorProvider, this.featureServiceProvider, this.logProvider);
    }
}
