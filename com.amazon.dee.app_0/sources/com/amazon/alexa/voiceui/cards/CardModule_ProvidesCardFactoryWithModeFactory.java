package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider;
import com.amazon.alexa.voice.ui.provider.CardFactoryWithMode;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardFactoryWithModeFactory implements Factory<CardFactoryWithMode> {
    private final Provider<CardCreationEventListenerProvider> cardCreationEventListenerProvider;
    private final CardModule module;

    public CardModule_ProvidesCardFactoryWithModeFactory(CardModule cardModule, Provider<CardCreationEventListenerProvider> provider) {
        this.module = cardModule;
        this.cardCreationEventListenerProvider = provider;
    }

    public static CardModule_ProvidesCardFactoryWithModeFactory create(CardModule cardModule, Provider<CardCreationEventListenerProvider> provider) {
        return new CardModule_ProvidesCardFactoryWithModeFactory(cardModule, provider);
    }

    public static CardFactoryWithMode provideInstance(CardModule cardModule, Provider<CardCreationEventListenerProvider> provider) {
        return proxyProvidesCardFactoryWithMode(cardModule, provider.mo10268get());
    }

    public static CardFactoryWithMode proxyProvidesCardFactoryWithMode(CardModule cardModule, CardCreationEventListenerProvider cardCreationEventListenerProvider) {
        return (CardFactoryWithMode) Preconditions.checkNotNull(cardModule.providesCardFactoryWithMode(cardCreationEventListenerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardFactoryWithMode mo10268get() {
        return provideInstance(this.module, this.cardCreationEventListenerProvider);
    }
}
