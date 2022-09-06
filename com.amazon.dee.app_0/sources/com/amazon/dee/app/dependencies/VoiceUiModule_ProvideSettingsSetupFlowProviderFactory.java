package com.amazon.dee.app.dependencies;

import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideSettingsSetupFlowProviderFactory implements Factory<SettingsSetupFlowProvider> {
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideSettingsSetupFlowProviderFactory(VoiceUiModule voiceUiModule) {
        this.module = voiceUiModule;
    }

    public static VoiceUiModule_ProvideSettingsSetupFlowProviderFactory create(VoiceUiModule voiceUiModule) {
        return new VoiceUiModule_ProvideSettingsSetupFlowProviderFactory(voiceUiModule);
    }

    public static SettingsSetupFlowProvider provideInstance(VoiceUiModule voiceUiModule) {
        return proxyProvideSettingsSetupFlowProvider(voiceUiModule);
    }

    public static SettingsSetupFlowProvider proxyProvideSettingsSetupFlowProvider(VoiceUiModule voiceUiModule) {
        return (SettingsSetupFlowProvider) Preconditions.checkNotNull(voiceUiModule.provideSettingsSetupFlowProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SettingsSetupFlowProvider mo10268get() {
        return provideInstance(this.module);
    }
}
