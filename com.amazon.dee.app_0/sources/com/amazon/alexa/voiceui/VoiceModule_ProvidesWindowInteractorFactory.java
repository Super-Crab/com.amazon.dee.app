package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.window.WindowInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesWindowInteractorFactory implements Factory<WindowInteractor> {
    private final VoiceModule module;

    public VoiceModule_ProvidesWindowInteractorFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesWindowInteractorFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesWindowInteractorFactory(voiceModule);
    }

    public static WindowInteractor provideInstance(VoiceModule voiceModule) {
        return proxyProvidesWindowInteractor(voiceModule);
    }

    public static WindowInteractor proxyProvidesWindowInteractor(VoiceModule voiceModule) {
        return (WindowInteractor) Preconditions.checkNotNull(voiceModule.providesWindowInteractor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WindowInteractor mo10268get() {
        return provideInstance(this.module);
    }
}
