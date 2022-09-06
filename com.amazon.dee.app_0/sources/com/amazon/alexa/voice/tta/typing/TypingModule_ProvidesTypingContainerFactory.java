package com.amazon.alexa.voice.tta.typing;

import android.view.ViewGroup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesTypingContainerFactory implements Factory<ViewGroup> {
    private final TypingModule module;

    public TypingModule_ProvidesTypingContainerFactory(TypingModule typingModule) {
        this.module = typingModule;
    }

    public static TypingModule_ProvidesTypingContainerFactory create(TypingModule typingModule) {
        return new TypingModule_ProvidesTypingContainerFactory(typingModule);
    }

    public static ViewGroup provideInstance(TypingModule typingModule) {
        return proxyProvidesTypingContainer(typingModule);
    }

    public static ViewGroup proxyProvidesTypingContainer(TypingModule typingModule) {
        return (ViewGroup) Preconditions.checkNotNull(typingModule.providesTypingContainer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewGroup mo10268get() {
        return provideInstance(this.module);
    }
}
