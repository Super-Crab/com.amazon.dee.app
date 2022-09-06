package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesDriveModeCardCreationEventListenerFactory implements Factory<CardCreationEventListener> {
    private final Provider<DriveModeCardCreationEventListener> eventListenerProvider;
    private final CardModule module;

    public CardModule_ProvidesDriveModeCardCreationEventListenerFactory(CardModule cardModule, Provider<DriveModeCardCreationEventListener> provider) {
        this.module = cardModule;
        this.eventListenerProvider = provider;
    }

    public static CardModule_ProvidesDriveModeCardCreationEventListenerFactory create(CardModule cardModule, Provider<DriveModeCardCreationEventListener> provider) {
        return new CardModule_ProvidesDriveModeCardCreationEventListenerFactory(cardModule, provider);
    }

    public static CardCreationEventListener provideInstance(CardModule cardModule, Provider<DriveModeCardCreationEventListener> provider) {
        return proxyProvidesDriveModeCardCreationEventListener(cardModule, provider.mo10268get());
    }

    public static CardCreationEventListener proxyProvidesDriveModeCardCreationEventListener(CardModule cardModule, Object obj) {
        return (CardCreationEventListener) Preconditions.checkNotNull(cardModule.providesDriveModeCardCreationEventListener((DriveModeCardCreationEventListener) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardCreationEventListener mo10268get() {
        return provideInstance(this.module, this.eventListenerProvider);
    }
}
