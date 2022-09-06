package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideHandsFreeSetupFactory implements Factory<HandsFreeSetup> {
    private final Provider<Context> contextProvider;
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideHandsFreeSetupFactory(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        this.module = voiceUiModule;
        this.contextProvider = provider;
    }

    public static VoiceUiModule_ProvideHandsFreeSetupFactory create(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return new VoiceUiModule_ProvideHandsFreeSetupFactory(voiceUiModule, provider);
    }

    public static HandsFreeSetup provideInstance(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return proxyProvideHandsFreeSetup(voiceUiModule, provider.mo10268get());
    }

    public static HandsFreeSetup proxyProvideHandsFreeSetup(VoiceUiModule voiceUiModule, Context context) {
        return (HandsFreeSetup) Preconditions.checkNotNull(voiceUiModule.provideHandsFreeSetup(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeSetup mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
