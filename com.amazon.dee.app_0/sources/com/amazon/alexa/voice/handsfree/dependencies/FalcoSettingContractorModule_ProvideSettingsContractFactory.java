package com.amazon.alexa.voice.handsfree.dependencies;

import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FalcoSettingContractorModule_ProvideSettingsContractFactory implements Factory<SettingsContract> {
    private final FalcoSettingContractorModule module;
    private final Provider<VendorAPIWrapperProvider> vendorAPIWrapperProvider;

    public FalcoSettingContractorModule_ProvideSettingsContractFactory(FalcoSettingContractorModule falcoSettingContractorModule, Provider<VendorAPIWrapperProvider> provider) {
        this.module = falcoSettingContractorModule;
        this.vendorAPIWrapperProvider = provider;
    }

    public static FalcoSettingContractorModule_ProvideSettingsContractFactory create(FalcoSettingContractorModule falcoSettingContractorModule, Provider<VendorAPIWrapperProvider> provider) {
        return new FalcoSettingContractorModule_ProvideSettingsContractFactory(falcoSettingContractorModule, provider);
    }

    @Nullable
    public static SettingsContract provideInstance(FalcoSettingContractorModule falcoSettingContractorModule, Provider<VendorAPIWrapperProvider> provider) {
        return proxyProvideSettingsContract(falcoSettingContractorModule, provider.mo10268get());
    }

    @Nullable
    public static SettingsContract proxyProvideSettingsContract(FalcoSettingContractorModule falcoSettingContractorModule, VendorAPIWrapperProvider vendorAPIWrapperProvider) {
        return falcoSettingContractorModule.provideSettingsContract(vendorAPIWrapperProvider);
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public SettingsContract mo10268get() {
        return provideInstance(this.module, this.vendorAPIWrapperProvider);
    }
}
