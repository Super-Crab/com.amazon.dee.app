package com.amazon.alexa.voice.tta.typing;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTtaMessageSanitizerFactory implements Factory<TtaMessageSanitizer> {
    private final TypingModule module;

    public TypingModule_ProvidesTtaMessageSanitizerFactory(TypingModule typingModule) {
        this.module = typingModule;
    }

    public static TypingModule_ProvidesTtaMessageSanitizerFactory create(TypingModule typingModule) {
        return new TypingModule_ProvidesTtaMessageSanitizerFactory(typingModule);
    }

    public static TtaMessageSanitizer provideInstance(TypingModule typingModule) {
        return proxyProvidesTtaMessageSanitizer(typingModule);
    }

    public static TtaMessageSanitizer proxyProvidesTtaMessageSanitizer(TypingModule typingModule) {
        return (TtaMessageSanitizer) Preconditions.checkNotNull(typingModule.providesTtaMessageSanitizer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaMessageSanitizer mo10268get() {
        return provideInstance(this.module);
    }
}
