package com.amazon.alexa.voiceui;

import com.amazon.alexa.voiceui.window.WindowManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesWindowControllerFactory implements Factory<WindowManager> {
    private final VoiceModule module;

    public VoiceModule_ProvidesWindowControllerFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesWindowControllerFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesWindowControllerFactory(voiceModule);
    }

    public static WindowManager provideInstance(VoiceModule voiceModule) {
        return proxyProvidesWindowController(voiceModule);
    }

    public static WindowManager proxyProvidesWindowController(VoiceModule voiceModule) {
        return (WindowManager) Preconditions.checkNotNull(voiceModule.providesWindowController(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WindowManager mo10268get() {
        return provideInstance(this.module);
    }
}
