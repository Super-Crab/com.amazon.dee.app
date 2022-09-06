package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardCreationEventListenerProviderFactory implements Factory<CardCreationEventListenerProvider> {
    private final Provider<DriveModeCardCreationEventListener> driveModeCardCreationEventListenerProvider;
    private final CardModule module;
    private final Provider<StandardCardCreationEventListener> standardCardCreationEventListenerProvider;

    public CardModule_ProvidesCardCreationEventListenerProviderFactory(CardModule cardModule, Provider<StandardCardCreationEventListener> provider, Provider<DriveModeCardCreationEventListener> provider2) {
        this.module = cardModule;
        this.standardCardCreationEventListenerProvider = provider;
        this.driveModeCardCreationEventListenerProvider = provider2;
    }

    public static CardModule_ProvidesCardCreationEventListenerProviderFactory create(CardModule cardModule, Provider<StandardCardCreationEventListener> provider, Provider<DriveModeCardCreationEventListener> provider2) {
        return new CardModule_ProvidesCardCreationEventListenerProviderFactory(cardModule, provider, provider2);
    }

    public static CardCreationEventListenerProvider provideInstance(CardModule cardModule, Provider<StandardCardCreationEventListener> provider, Provider<DriveModeCardCreationEventListener> provider2) {
        return proxyProvidesCardCreationEventListenerProvider(cardModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static CardCreationEventListenerProvider proxyProvidesCardCreationEventListenerProvider(CardModule cardModule, Object obj, Object obj2) {
        return (CardCreationEventListenerProvider) Preconditions.checkNotNull(cardModule.providesCardCreationEventListenerProvider((StandardCardCreationEventListener) obj, (DriveModeCardCreationEventListener) obj2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardCreationEventListenerProvider mo10268get() {
        return provideInstance(this.module, this.standardCardCreationEventListenerProvider, this.driveModeCardCreationEventListenerProvider);
    }
}
