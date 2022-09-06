package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideMuffinOobeMonitorFactory implements Factory<CatapultTtsDeviceMonitor> {
    private final ModeModule module;

    public ModeModule_ProvideMuffinOobeMonitorFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideMuffinOobeMonitorFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideMuffinOobeMonitorFactory(modeModule);
    }

    public static CatapultTtsDeviceMonitor provideInstance(ModeModule modeModule) {
        return proxyProvideMuffinOobeMonitor(modeModule);
    }

    public static CatapultTtsDeviceMonitor proxyProvideMuffinOobeMonitor(ModeModule modeModule) {
        return (CatapultTtsDeviceMonitor) Preconditions.checkNotNull(modeModule.provideMuffinOobeMonitor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CatapultTtsDeviceMonitor mo10268get() {
        return provideInstance(this.module);
    }
}
