package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesAttentionSystemContractFactory implements Factory<AttentionSystemContract> {
    private final VoiceChromeModule module;
    private final Provider<VoiceChromeModel> voiceChromeModelProvider;

    public VoiceChromeModule_ProvidesAttentionSystemContractFactory(VoiceChromeModule voiceChromeModule, Provider<VoiceChromeModel> provider) {
        this.module = voiceChromeModule;
        this.voiceChromeModelProvider = provider;
    }

    public static VoiceChromeModule_ProvidesAttentionSystemContractFactory create(VoiceChromeModule voiceChromeModule, Provider<VoiceChromeModel> provider) {
        return new VoiceChromeModule_ProvidesAttentionSystemContractFactory(voiceChromeModule, provider);
    }

    public static AttentionSystemContract provideInstance(VoiceChromeModule voiceChromeModule, Provider<VoiceChromeModel> provider) {
        return proxyProvidesAttentionSystemContract(voiceChromeModule, provider.mo10268get());
    }

    public static AttentionSystemContract proxyProvidesAttentionSystemContract(VoiceChromeModule voiceChromeModule, Object obj) {
        return (AttentionSystemContract) Preconditions.checkNotNull(voiceChromeModule.providesAttentionSystemContract((VoiceChromeModel) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AttentionSystemContract mo10268get() {
        return provideInstance(this.module, this.voiceChromeModelProvider);
    }
}
