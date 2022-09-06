package com.amazon.alexa.voice.tta.typing;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesDefaultTtaMessageHandlerFactory implements Factory<DefaultTtaMessageHandler> {
    private final Provider<TtaMessageSanitizer> messageSanitizerProvider;
    private final TypingModule module;

    public TypingModule_ProvidesDefaultTtaMessageHandlerFactory(TypingModule typingModule, Provider<TtaMessageSanitizer> provider) {
        this.module = typingModule;
        this.messageSanitizerProvider = provider;
    }

    public static TypingModule_ProvidesDefaultTtaMessageHandlerFactory create(TypingModule typingModule, Provider<TtaMessageSanitizer> provider) {
        return new TypingModule_ProvidesDefaultTtaMessageHandlerFactory(typingModule, provider);
    }

    public static DefaultTtaMessageHandler provideInstance(TypingModule typingModule, Provider<TtaMessageSanitizer> provider) {
        return proxyProvidesDefaultTtaMessageHandler(typingModule, provider.mo10268get());
    }

    public static DefaultTtaMessageHandler proxyProvidesDefaultTtaMessageHandler(TypingModule typingModule, TtaMessageSanitizer ttaMessageSanitizer) {
        return (DefaultTtaMessageHandler) Preconditions.checkNotNull(typingModule.providesDefaultTtaMessageHandler(ttaMessageSanitizer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultTtaMessageHandler mo10268get() {
        return provideInstance(this.module, this.messageSanitizerProvider);
    }
}
