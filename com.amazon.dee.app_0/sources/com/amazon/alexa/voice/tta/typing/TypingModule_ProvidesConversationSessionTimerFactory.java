package com.amazon.alexa.voice.tta.typing;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesConversationSessionTimerFactory implements Factory<ConversationSessionTimer> {
    private final TypingModule module;

    public TypingModule_ProvidesConversationSessionTimerFactory(TypingModule typingModule) {
        this.module = typingModule;
    }

    public static TypingModule_ProvidesConversationSessionTimerFactory create(TypingModule typingModule) {
        return new TypingModule_ProvidesConversationSessionTimerFactory(typingModule);
    }

    public static ConversationSessionTimer provideInstance(TypingModule typingModule) {
        return proxyProvidesConversationSessionTimer(typingModule);
    }

    public static ConversationSessionTimer proxyProvidesConversationSessionTimer(TypingModule typingModule) {
        return (ConversationSessionTimer) Preconditions.checkNotNull(typingModule.providesConversationSessionTimer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationSessionTimer mo10268get() {
        return provideInstance(this.module);
    }
}
