package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.util.AutomotiveDeviceRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideAutomotiveDeviceRegistryFactory implements Factory<AutomotiveDeviceRegistry> {
    private final ModeModule module;

    public ModeModule_ProvideAutomotiveDeviceRegistryFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideAutomotiveDeviceRegistryFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideAutomotiveDeviceRegistryFactory(modeModule);
    }

    public static AutomotiveDeviceRegistry provideInstance(ModeModule modeModule) {
        return proxyProvideAutomotiveDeviceRegistry(modeModule);
    }

    public static AutomotiveDeviceRegistry proxyProvideAutomotiveDeviceRegistry(ModeModule modeModule) {
        return (AutomotiveDeviceRegistry) Preconditions.checkNotNull(modeModule.provideAutomotiveDeviceRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutomotiveDeviceRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
