package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesStandardCardCreationEventListenerFactory implements Factory<CardCreationEventListener> {
    private final Provider<StandardCardCreationEventListener> eventListenerProvider;
    private final CardModule module;

    public CardModule_ProvidesStandardCardCreationEventListenerFactory(CardModule cardModule, Provider<StandardCardCreationEventListener> provider) {
        this.module = cardModule;
        this.eventListenerProvider = provider;
    }

    public static CardModule_ProvidesStandardCardCreationEventListenerFactory create(CardModule cardModule, Provider<StandardCardCreationEventListener> provider) {
        return new CardModule_ProvidesStandardCardCreationEventListenerFactory(cardModule, provider);
    }

    public static CardCreationEventListener provideInstance(CardModule cardModule, Provider<StandardCardCreationEventListener> provider) {
        return proxyProvidesStandardCardCreationEventListener(cardModule, provider.mo10268get());
    }

    public static CardCreationEventListener proxyProvidesStandardCardCreationEventListener(CardModule cardModule, Object obj) {
        return (CardCreationEventListener) Preconditions.checkNotNull(cardModule.providesStandardCardCreationEventListener((StandardCardCreationEventListener) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardCreationEventListener mo10268get() {
        return provideInstance(this.module, this.eventListenerProvider);
    }
}
