package com.amazon.alexa.voiceui;

import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesDriveModeStateProviderFactory implements Factory<DriveModeStateProvider> {
    private final VoiceModule module;

    public VoiceModule_ProvidesDriveModeStateProviderFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesDriveModeStateProviderFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesDriveModeStateProviderFactory(voiceModule);
    }

    public static DriveModeStateProvider provideInstance(VoiceModule voiceModule) {
        return proxyProvidesDriveModeStateProvider(voiceModule);
    }

    public static DriveModeStateProvider proxyProvidesDriveModeStateProvider(VoiceModule voiceModule) {
        return (DriveModeStateProvider) Preconditions.checkNotNull(voiceModule.providesDriveModeStateProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeStateProvider mo10268get() {
        return provideInstance(this.module);
    }
}
