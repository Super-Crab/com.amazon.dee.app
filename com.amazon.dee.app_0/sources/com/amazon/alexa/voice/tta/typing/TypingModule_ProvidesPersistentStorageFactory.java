package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesPersistentStorageFactory implements Factory<PersistentStorage> {
    private final Provider<Context> contextProvider;
    private final TypingModule module;

    public TypingModule_ProvidesPersistentStorageFactory(TypingModule typingModule, Provider<Context> provider) {
        this.module = typingModule;
        this.contextProvider = provider;
    }

    public static TypingModule_ProvidesPersistentStorageFactory create(TypingModule typingModule, Provider<Context> provider) {
        return new TypingModule_ProvidesPersistentStorageFactory(typingModule, provider);
    }

    public static PersistentStorage provideInstance(TypingModule typingModule, Provider<Context> provider) {
        return proxyProvidesPersistentStorage(typingModule, provider.mo10268get());
    }

    public static PersistentStorage proxyProvidesPersistentStorage(TypingModule typingModule, Context context) {
        return (PersistentStorage) Preconditions.checkNotNull(typingModule.providesPersistentStorage(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
