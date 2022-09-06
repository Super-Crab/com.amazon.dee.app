package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.RouterDelegate;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingView_Factory implements Factory<TypingView> {
    private final Provider<RouterDelegate> typingRouterProvider;

    public TypingView_Factory(Provider<RouterDelegate> provider) {
        this.typingRouterProvider = provider;
    }

    public static TypingView_Factory create(Provider<RouterDelegate> provider) {
        return new TypingView_Factory(provider);
    }

    public static TypingView newTypingView(RouterDelegate routerDelegate) {
        return new TypingView(routerDelegate);
    }

    public static TypingView provideInstance(Provider<RouterDelegate> provider) {
        return new TypingView(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypingView mo10268get() {
        return provideInstance(this.typingRouterProvider);
    }
}
