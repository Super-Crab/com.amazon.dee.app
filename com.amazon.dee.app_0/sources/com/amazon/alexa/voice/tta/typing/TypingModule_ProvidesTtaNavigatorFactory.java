package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTtaNavigatorFactory implements Factory<TtaNavigator> {
    private final TypingModule module;

    public TypingModule_ProvidesTtaNavigatorFactory(TypingModule typingModule) {
        this.module = typingModule;
    }

    public static TypingModule_ProvidesTtaNavigatorFactory create(TypingModule typingModule) {
        return new TypingModule_ProvidesTtaNavigatorFactory(typingModule);
    }

    public static TtaNavigator provideInstance(TypingModule typingModule) {
        return proxyProvidesTtaNavigator(typingModule);
    }

    public static TtaNavigator proxyProvidesTtaNavigator(TypingModule typingModule) {
        return (TtaNavigator) Preconditions.checkNotNull(typingModule.providesTtaNavigator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaNavigator mo10268get() {
        return provideInstance(this.module);
    }
}
