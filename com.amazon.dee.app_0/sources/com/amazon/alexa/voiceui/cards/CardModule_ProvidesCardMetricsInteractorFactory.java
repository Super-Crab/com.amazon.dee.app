package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardMetricsInteractorFactory implements Factory<CardMetricsInteractor> {
    private final Provider<StandardCardMetricsInteractor> cardMetricsInteractorProvider;
    private final CardModule module;

    public CardModule_ProvidesCardMetricsInteractorFactory(CardModule cardModule, Provider<StandardCardMetricsInteractor> provider) {
        this.module = cardModule;
        this.cardMetricsInteractorProvider = provider;
    }

    public static CardModule_ProvidesCardMetricsInteractorFactory create(CardModule cardModule, Provider<StandardCardMetricsInteractor> provider) {
        return new CardModule_ProvidesCardMetricsInteractorFactory(cardModule, provider);
    }

    public static CardMetricsInteractor provideInstance(CardModule cardModule, Provider<StandardCardMetricsInteractor> provider) {
        return proxyProvidesCardMetricsInteractor(cardModule, provider.mo10268get());
    }

    public static CardMetricsInteractor proxyProvidesCardMetricsInteractor(CardModule cardModule, Object obj) {
        return (CardMetricsInteractor) Preconditions.checkNotNull(cardModule.providesCardMetricsInteractor((StandardCardMetricsInteractor) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardMetricsInteractor mo10268get() {
        return provideInstance(this.module, this.cardMetricsInteractorProvider);
    }
}
