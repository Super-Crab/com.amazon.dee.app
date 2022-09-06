package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.RouterDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesBackButtonPressHandlerFactory implements Factory<BackButtonPressHandler> {
    private final CardModule module;
    private final Provider<RouterDelegate> routerDelegateProvider;

    public CardModule_ProvidesBackButtonPressHandlerFactory(CardModule cardModule, Provider<RouterDelegate> provider) {
        this.module = cardModule;
        this.routerDelegateProvider = provider;
    }

    public static CardModule_ProvidesBackButtonPressHandlerFactory create(CardModule cardModule, Provider<RouterDelegate> provider) {
        return new CardModule_ProvidesBackButtonPressHandlerFactory(cardModule, provider);
    }

    public static BackButtonPressHandler provideInstance(CardModule cardModule, Provider<RouterDelegate> provider) {
        return proxyProvidesBackButtonPressHandler(cardModule, provider.mo10268get());
    }

    public static BackButtonPressHandler proxyProvidesBackButtonPressHandler(CardModule cardModule, RouterDelegate routerDelegate) {
        return (BackButtonPressHandler) Preconditions.checkNotNull(cardModule.providesBackButtonPressHandler(routerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BackButtonPressHandler mo10268get() {
        return provideInstance(this.module, this.routerDelegateProvider);
    }
}
