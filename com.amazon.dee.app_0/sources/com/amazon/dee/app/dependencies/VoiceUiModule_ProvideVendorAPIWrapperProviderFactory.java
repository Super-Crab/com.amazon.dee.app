package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideVendorAPIWrapperProviderFactory implements Factory<VendorAPIWrapperProvider> {
    private final Provider<Context> contextProvider;
    private final VoiceUiModule module;

    public VoiceUiModule_ProvideVendorAPIWrapperProviderFactory(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        this.module = voiceUiModule;
        this.contextProvider = provider;
    }

    public static VoiceUiModule_ProvideVendorAPIWrapperProviderFactory create(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return new VoiceUiModule_ProvideVendorAPIWrapperProviderFactory(voiceUiModule, provider);
    }

    public static VendorAPIWrapperProvider provideInstance(VoiceUiModule voiceUiModule, Provider<Context> provider) {
        return proxyProvideVendorAPIWrapperProvider(voiceUiModule, provider.mo10268get());
    }

    public static VendorAPIWrapperProvider proxyProvideVendorAPIWrapperProvider(VoiceUiModule voiceUiModule, Context context) {
        return (VendorAPIWrapperProvider) Preconditions.checkNotNull(voiceUiModule.provideVendorAPIWrapperProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VendorAPIWrapperProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
