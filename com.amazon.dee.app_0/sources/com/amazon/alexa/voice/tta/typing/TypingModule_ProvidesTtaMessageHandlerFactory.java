package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTtaMessageHandlerFactory implements Factory<TtaMessageHandler> {
    private final Provider<DefaultTtaMessageHandler> defaultTtaMessageHandlerProvider;
    private final TypingModule module;

    public TypingModule_ProvidesTtaMessageHandlerFactory(TypingModule typingModule, Provider<DefaultTtaMessageHandler> provider) {
        this.module = typingModule;
        this.defaultTtaMessageHandlerProvider = provider;
    }

    public static TypingModule_ProvidesTtaMessageHandlerFactory create(TypingModule typingModule, Provider<DefaultTtaMessageHandler> provider) {
        return new TypingModule_ProvidesTtaMessageHandlerFactory(typingModule, provider);
    }

    public static TtaMessageHandler provideInstance(TypingModule typingModule, Provider<DefaultTtaMessageHandler> provider) {
        return proxyProvidesTtaMessageHandler(typingModule, provider.mo10268get());
    }

    public static TtaMessageHandler proxyProvidesTtaMessageHandler(TypingModule typingModule, Object obj) {
        return (TtaMessageHandler) Preconditions.checkNotNull(typingModule.providesTtaMessageHandler((DefaultTtaMessageHandler) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaMessageHandler mo10268get() {
        return provideInstance(this.module, this.defaultTtaMessageHandlerProvider);
    }
}
