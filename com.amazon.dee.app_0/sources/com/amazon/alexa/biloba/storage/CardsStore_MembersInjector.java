package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.CardApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.view.dashboard.CardTransformer;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CardsStore_MembersInjector implements MembersInjector<CardsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CardApi> cardApiProvider;
    private final Provider<CardTransformer> cardTransformerProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public CardsStore_MembersInjector(Provider<CardTransformer> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CardApi> provider5) {
        this.cardTransformerProvider = provider;
        this.crashReporterProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.bilobaMetricsServiceProvider = provider4;
        this.cardApiProvider = provider5;
    }

    public static MembersInjector<CardsStore> create(Provider<CardTransformer> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CardApi> provider5) {
        return new CardsStore_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectBilobaMetricsService(CardsStore cardsStore, Lazy<BilobaMetricsService> lazy) {
        cardsStore.bilobaMetricsService = lazy;
    }

    public static void injectCardApi(CardsStore cardsStore, Lazy<CardApi> lazy) {
        cardsStore.cardApi = lazy;
    }

    public static void injectCardTransformer(CardsStore cardsStore, Lazy<CardTransformer> lazy) {
        cardsStore.cardTransformer = lazy;
    }

    public static void injectCrashMetadata(CardsStore cardsStore, Lazy<CrashMetadata> lazy) {
        cardsStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(CardsStore cardsStore, Lazy<CrashReporter> lazy) {
        cardsStore.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CardsStore cardsStore) {
        injectCardTransformer(cardsStore, DoubleCheck.lazy(this.cardTransformerProvider));
        injectCrashReporter(cardsStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(cardsStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(cardsStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCardApi(cardsStore, DoubleCheck.lazy(this.cardApiProvider));
    }
}
