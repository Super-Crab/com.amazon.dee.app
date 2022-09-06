package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiModule_ProvideCardMetricsInteractorFactory implements Factory<CardMetricsInteractor> {
    private final Provider<MetricsBridge> metricsBridgeProvider;

    public UiModule_ProvideCardMetricsInteractorFactory(Provider<MetricsBridge> provider) {
        this.metricsBridgeProvider = provider;
    }

    public static UiModule_ProvideCardMetricsInteractorFactory create(Provider<MetricsBridge> provider) {
        return new UiModule_ProvideCardMetricsInteractorFactory(provider);
    }

    public static CardMetricsInteractor provideInstance(Provider<MetricsBridge> provider) {
        return proxyProvideCardMetricsInteractor(provider.mo10268get());
    }

    public static CardMetricsInteractor proxyProvideCardMetricsInteractor(MetricsBridge metricsBridge) {
        return (CardMetricsInteractor) Preconditions.checkNotNull(UiModule.provideCardMetricsInteractor(metricsBridge), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardMetricsInteractor mo10268get() {
        return provideInstance(this.metricsBridgeProvider);
    }
}
