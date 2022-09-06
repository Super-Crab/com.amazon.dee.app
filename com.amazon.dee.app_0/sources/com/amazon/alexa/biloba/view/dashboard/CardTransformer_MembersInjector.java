package com.amazon.alexa.biloba.view.dashboard;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CardTransformer_MembersInjector implements MembersInjector<CardTransformer> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;

    public CardTransformer_MembersInjector(Provider<BilobaMetricsService> provider) {
        this.bilobaMetricsServiceProvider = provider;
    }

    public static MembersInjector<CardTransformer> create(Provider<BilobaMetricsService> provider) {
        return new CardTransformer_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CardTransformer cardTransformer) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(cardTransformer, this.bilobaMetricsServiceProvider.mo10268get());
    }
}
