package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideAMPDInformationProviderFactory implements Factory<AMPDInformationProvider> {
    private final Provider<Context> contextProvider;
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideAMPDInformationProviderFactory(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        this.module = voiceUiModule;
        this.contextProvider = provider;
    }

    public static VoiceUiModule_ProvideAMPDInformationProviderFactory create(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return new VoiceUiModule_ProvideAMPDInformationProviderFactory(voiceUiModule, provider);
    }

    public static AMPDInformationProvider provideInstance(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return proxyProvideAMPDInformationProvider(voiceUiModule, provider.mo10268get());
    }

    public static AMPDInformationProvider proxyProvideAMPDInformationProvider(VoiceUiModule voiceUiModule, Context context) {
        return (AMPDInformationProvider) Preconditions.checkNotNull(voiceUiModule.provideAMPDInformationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AMPDInformationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
