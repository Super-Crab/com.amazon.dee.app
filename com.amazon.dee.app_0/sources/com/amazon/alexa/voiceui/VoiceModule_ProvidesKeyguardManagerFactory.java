package com.amazon.alexa.voiceui;

import android.app.KeyguardManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesKeyguardManagerFactory implements Factory<KeyguardManager> {
    private final Provider<Context> contextProvider;
    private final VoiceModule module;

    public VoiceModule_ProvidesKeyguardManagerFactory(VoiceModule voiceModule, Provider<Context> provider) {
        this.module = voiceModule;
        this.contextProvider = provider;
    }

    public static VoiceModule_ProvidesKeyguardManagerFactory create(VoiceModule voiceModule, Provider<Context> provider) {
        return new VoiceModule_ProvidesKeyguardManagerFactory(voiceModule, provider);
    }

    public static KeyguardManager provideInstance(VoiceModule voiceModule, Provider<Context> provider) {
        return proxyProvidesKeyguardManager(voiceModule, provider.mo10268get());
    }

    public static KeyguardManager proxyProvidesKeyguardManager(VoiceModule voiceModule, Context context) {
        return (KeyguardManager) Preconditions.checkNotNull(voiceModule.providesKeyguardManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KeyguardManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
