package com.amazon.alexa.voiceui;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesBackgroundThreadFactory implements Factory<ExecutorService> {
    private final VoiceModule module;

    public VoiceModule_ProvidesBackgroundThreadFactory(VoiceModule voiceModule) {
        this.module = voiceModule;
    }

    public static VoiceModule_ProvidesBackgroundThreadFactory create(VoiceModule voiceModule) {
        return new VoiceModule_ProvidesBackgroundThreadFactory(voiceModule);
    }

    public static ExecutorService provideInstance(VoiceModule voiceModule) {
        return proxyProvidesBackgroundThread(voiceModule);
    }

    public static ExecutorService proxyProvidesBackgroundThread(VoiceModule voiceModule) {
        return (ExecutorService) Preconditions.checkNotNull(voiceModule.providesBackgroundThread(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public ExecutorService mo10268get() {
        return provideInstance(this.module);
    }
}
