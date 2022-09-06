package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardsRouterDelegateFactory implements Factory<RouterDelegate> {
    private final CardModule module;
    private final Provider<Router> routerProvider;

    public CardModule_ProvidesCardsRouterDelegateFactory(CardModule cardModule, Provider<Router> provider) {
        this.module = cardModule;
        this.routerProvider = provider;
    }

    public static CardModule_ProvidesCardsRouterDelegateFactory create(CardModule cardModule, Provider<Router> provider) {
        return new CardModule_ProvidesCardsRouterDelegateFactory(cardModule, provider);
    }

    public static RouterDelegate provideInstance(CardModule cardModule, Provider<Router> provider) {
        return proxyProvidesCardsRouterDelegate(cardModule, provider.mo10268get());
    }

    public static RouterDelegate proxyProvidesCardsRouterDelegate(CardModule cardModule, Router router) {
        return (RouterDelegate) Preconditions.checkNotNull(cardModule.providesCardsRouterDelegate(router), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RouterDelegate mo10268get() {
        return provideInstance(this.module, this.routerProvider);
    }
}
