package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FalcoSettingContractorModule_ProvideAudioProviderServiceIntentFactory implements Factory<Intent> {
    private final Provider<Context> contextProvider;
    private final FalcoSettingContractorModule module;
    private final Provider<VendorAPIWrapperProvider> vendorAPIWrapperProvider;

    public FalcoSettingContractorModule_ProvideAudioProviderServiceIntentFactory(FalcoSettingContractorModule falcoSettingContractorModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2) {
        this.module = falcoSettingContractorModule;
        this.contextProvider = provider;
        this.vendorAPIWrapperProvider = provider2;
    }

    public static FalcoSettingContractorModule_ProvideAudioProviderServiceIntentFactory create(FalcoSettingContractorModule falcoSettingContractorModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2) {
        return new FalcoSettingContractorModule_ProvideAudioProviderServiceIntentFactory(falcoSettingContractorModule, provider, provider2);
    }

    @Nullable
    public static Intent provideInstance(FalcoSettingContractorModule falcoSettingContractorModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2) {
        return proxyProvideAudioProviderServiceIntent(falcoSettingContractorModule, provider.mo10268get(), provider2.mo10268get());
    }

    @Nullable
    public static Intent proxyProvideAudioProviderServiceIntent(FalcoSettingContractorModule falcoSettingContractorModule, Context context, VendorAPIWrapperProvider vendorAPIWrapperProvider) {
        return falcoSettingContractorModule.provideAudioProviderServiceIntent(context, vendorAPIWrapperProvider);
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public Intent mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.vendorAPIWrapperProvider);
    }
}
