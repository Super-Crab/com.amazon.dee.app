package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.CardApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.view.dashboard.CardTransformer;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CardsStore_Factory implements Factory<CardsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CardApi> cardApiProvider;
    private final Provider<CardTransformer> cardTransformerProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public CardsStore_Factory(Provider<CardTransformer> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CardApi> provider5) {
        this.cardTransformerProvider = provider;
        this.crashReporterProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.bilobaMetricsServiceProvider = provider4;
        this.cardApiProvider = provider5;
    }

    public static CardsStore_Factory create(Provider<CardTransformer> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CardApi> provider5) {
        return new CardsStore_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CardsStore newCardsStore() {
        return new CardsStore();
    }

    public static CardsStore provideInstance(Provider<CardTransformer> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CardApi> provider5) {
        CardsStore cardsStore = new CardsStore();
        CardsStore_MembersInjector.injectCardTransformer(cardsStore, DoubleCheck.lazy(provider));
        CardsStore_MembersInjector.injectCrashReporter(cardsStore, DoubleCheck.lazy(provider2));
        CardsStore_MembersInjector.injectCrashMetadata(cardsStore, DoubleCheck.lazy(provider3));
        CardsStore_MembersInjector.injectBilobaMetricsService(cardsStore, DoubleCheck.lazy(provider4));
        CardsStore_MembersInjector.injectCardApi(cardsStore, DoubleCheck.lazy(provider5));
        return cardsStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardsStore mo10268get() {
        return provideInstance(this.cardTransformerProvider, this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.cardApiProvider);
    }
}
