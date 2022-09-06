package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesSaveInstanceStateHandlerFactory implements Factory<SaveInstanceStateHandler> {
    private final CardModule module;
    private final Provider<RouterDelegate> routerDelegateProvider;

    public CardModule_ProvidesSaveInstanceStateHandlerFactory(CardModule cardModule, Provider<RouterDelegate> provider) {
        this.module = cardModule;
        this.routerDelegateProvider = provider;
    }

    public static CardModule_ProvidesSaveInstanceStateHandlerFactory create(CardModule cardModule, Provider<RouterDelegate> provider) {
        return new CardModule_ProvidesSaveInstanceStateHandlerFactory(cardModule, provider);
    }

    public static SaveInstanceStateHandler provideInstance(CardModule cardModule, Provider<RouterDelegate> provider) {
        return proxyProvidesSaveInstanceStateHandler(cardModule, provider.mo10268get());
    }

    public static SaveInstanceStateHandler proxyProvidesSaveInstanceStateHandler(CardModule cardModule, RouterDelegate routerDelegate) {
        return (SaveInstanceStateHandler) Preconditions.checkNotNull(cardModule.providesSaveInstanceStateHandler(routerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SaveInstanceStateHandler mo10268get() {
        return provideInstance(this.module, this.routerDelegateProvider);
    }
}
