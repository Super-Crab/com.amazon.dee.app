package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.RouterDelegate;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTypingRouterDelegateFactory implements Factory<RouterDelegate> {
    private final TypingModule module;
    private final Provider<Router> routerProvider;

    public TypingModule_ProvidesTypingRouterDelegateFactory(TypingModule typingModule, Provider<Router> provider) {
        this.module = typingModule;
        this.routerProvider = provider;
    }

    public static TypingModule_ProvidesTypingRouterDelegateFactory create(TypingModule typingModule, Provider<Router> provider) {
        return new TypingModule_ProvidesTypingRouterDelegateFactory(typingModule, provider);
    }

    public static RouterDelegate provideInstance(TypingModule typingModule, Provider<Router> provider) {
        return proxyProvidesTypingRouterDelegate(typingModule, provider.mo10268get());
    }

    public static RouterDelegate proxyProvidesTypingRouterDelegate(TypingModule typingModule, Router router) {
        return (RouterDelegate) Preconditions.checkNotNull(typingModule.providesTypingRouterDelegate(router), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RouterDelegate mo10268get() {
        return provideInstance(this.module, this.routerProvider);
    }
}
