package com.amazon.alexa.voice.tta.typing;

import android.view.ViewGroup;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTypingRouterFactory implements Factory<Router> {
    private final Provider<Component> componentProvider;
    private final Provider<ViewGroup> containerProvider;
    private final TypingModule module;

    public TypingModule_ProvidesTypingRouterFactory(TypingModule typingModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        this.module = typingModule;
        this.containerProvider = provider;
        this.componentProvider = provider2;
    }

    public static TypingModule_ProvidesTypingRouterFactory create(TypingModule typingModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return new TypingModule_ProvidesTypingRouterFactory(typingModule, provider, provider2);
    }

    public static Router provideInstance(TypingModule typingModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return proxyProvidesTypingRouter(typingModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static Router proxyProvidesTypingRouter(TypingModule typingModule, ViewGroup viewGroup, Component component) {
        return (Router) Preconditions.checkNotNull(typingModule.providesTypingRouter(viewGroup, component), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Router mo10268get() {
        return provideInstance(this.module, this.containerProvider, this.componentProvider);
    }
}
