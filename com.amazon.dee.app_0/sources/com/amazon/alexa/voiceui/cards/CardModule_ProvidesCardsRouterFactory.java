package com.amazon.alexa.voiceui.cards;

import android.view.ViewGroup;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesCardsRouterFactory implements Factory<Router> {
    private final Provider<Component> componentProvider;
    private final Provider<ViewGroup> containerProvider;
    private final CardModule module;

    public CardModule_ProvidesCardsRouterFactory(CardModule cardModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        this.module = cardModule;
        this.containerProvider = provider;
        this.componentProvider = provider2;
    }

    public static CardModule_ProvidesCardsRouterFactory create(CardModule cardModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return new CardModule_ProvidesCardsRouterFactory(cardModule, provider, provider2);
    }

    public static Router provideInstance(CardModule cardModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return proxyProvidesCardsRouter(cardModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static Router proxyProvidesCardsRouter(CardModule cardModule, ViewGroup viewGroup, Component component) {
        return (Router) Preconditions.checkNotNull(cardModule.providesCardsRouter(viewGroup, component), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Router mo10268get() {
        return provideInstance(this.module, this.containerProvider, this.componentProvider);
    }
}
