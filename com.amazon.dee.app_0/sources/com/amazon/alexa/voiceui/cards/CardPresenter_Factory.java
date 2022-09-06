package com.amazon.alexa.voiceui.cards;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardPresenter_Factory implements Factory<CardPresenter> {
    private final Provider<CardInteractor> cardInteractorProvider;
    private final Provider<CardView> cardViewProvider;

    public CardPresenter_Factory(Provider<CardInteractor> provider, Provider<CardView> provider2) {
        this.cardInteractorProvider = provider;
        this.cardViewProvider = provider2;
    }

    public static CardPresenter_Factory create(Provider<CardInteractor> provider, Provider<CardView> provider2) {
        return new CardPresenter_Factory(provider, provider2);
    }

    public static CardPresenter newCardPresenter(CardInteractor cardInteractor, CardView cardView) {
        return new CardPresenter(cardInteractor, cardView);
    }

    public static CardPresenter provideInstance(Provider<CardInteractor> provider, Provider<CardView> provider2) {
        return new CardPresenter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardPresenter mo10268get() {
        return provideInstance(this.cardInteractorProvider, this.cardViewProvider);
    }
}
