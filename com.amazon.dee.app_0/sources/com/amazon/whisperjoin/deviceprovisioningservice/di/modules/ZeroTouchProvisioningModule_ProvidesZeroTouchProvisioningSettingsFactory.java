package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory implements Factory<FFSArcusSettings> {
    private final ZeroTouchProvisioningModule module;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        this.module = zeroTouchProvisioningModule;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory(zeroTouchProvisioningModule);
    }

    public static FFSArcusSettings provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return proxyProvidesZeroTouchProvisioningSettings(zeroTouchProvisioningModule);
    }

    public static FFSArcusSettings proxyProvidesZeroTouchProvisioningSettings(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return (FFSArcusSettings) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchProvisioningSettings(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FFSArcusSettings mo10268get() {
        return provideInstance(this.module);
    }
}
