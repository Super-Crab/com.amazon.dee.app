package com.amazon.alexa.voiceui;

import android.content.Context;
import android.os.PowerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesPowerManagerFactory implements Factory<PowerManager> {
    private final Provider<Context> contextProvider;
    private final VoiceModule module;

    public VoiceModule_ProvidesPowerManagerFactory(VoiceModule voiceModule, Provider<Context> provider) {
        this.module = voiceModule;
        this.contextProvider = provider;
    }

    public static VoiceModule_ProvidesPowerManagerFactory create(VoiceModule voiceModule, Provider<Context> provider) {
        return new VoiceModule_ProvidesPowerManagerFactory(voiceModule, provider);
    }

    public static PowerManager provideInstance(VoiceModule voiceModule, Provider<Context> provider) {
        return proxyProvidesPowerManager(voiceModule, provider.mo10268get());
    }

    public static PowerManager proxyProvidesPowerManager(VoiceModule voiceModule, Context context) {
        return (PowerManager) Preconditions.checkNotNull(voiceModule.providesPowerManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PowerManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
