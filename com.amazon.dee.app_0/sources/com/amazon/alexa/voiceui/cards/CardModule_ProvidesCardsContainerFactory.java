package com.amazon.alexa.voiceui.cards;

import android.view.ViewGroup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardsContainerFactory implements Factory<ViewGroup> {
    private final CardModule module;

    public CardModule_ProvidesCardsContainerFactory(CardModule cardModule) {
        this.module = cardModule;
    }

    public static CardModule_ProvidesCardsContainerFactory create(CardModule cardModule) {
        return new CardModule_ProvidesCardsContainerFactory(cardModule);
    }

    public static ViewGroup provideInstance(CardModule cardModule) {
        return proxyProvidesCardsContainer(cardModule);
    }

    public static ViewGroup proxyProvidesCardsContainer(CardModule cardModule) {
        return (ViewGroup) Preconditions.checkNotNull(cardModule.providesCardsContainer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewGroup mo10268get() {
        return provideInstance(this.module);
    }
}
