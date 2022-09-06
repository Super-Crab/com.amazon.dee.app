package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class FalcoSettingContractorModule_ProvideSettingsSetupFlowContractFactory implements Factory<SettingsSetupFlowContract> {
    private final FalcoSettingContractorModule module;

    public FalcoSettingContractorModule_ProvideSettingsSetupFlowContractFactory(FalcoSettingContractorModule falcoSettingContractorModule) {
        this.module = falcoSettingContractorModule;
    }

    public static FalcoSettingContractorModule_ProvideSettingsSetupFlowContractFactory create(FalcoSettingContractorModule falcoSettingContractorModule) {
        return new FalcoSettingContractorModule_ProvideSettingsSetupFlowContractFactory(falcoSettingContractorModule);
    }

    public static SettingsSetupFlowContract provideInstance(FalcoSettingContractorModule falcoSettingContractorModule) {
        return proxyProvideSettingsSetupFlowContract(falcoSettingContractorModule);
    }

    public static SettingsSetupFlowContract proxyProvideSettingsSetupFlowContract(FalcoSettingContractorModule falcoSettingContractorModule) {
        return (SettingsSetupFlowContract) Preconditions.checkNotNull(falcoSettingContractorModule.provideSettingsSetupFlowContract(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SettingsSetupFlowContract mo10268get() {
        return provideInstance(this.module);
    }
}
