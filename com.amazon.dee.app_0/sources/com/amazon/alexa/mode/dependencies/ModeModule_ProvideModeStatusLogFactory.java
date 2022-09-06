package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.userstudy.ModeStatusLog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideModeStatusLogFactory implements Factory<ModeStatusLog> {
    private final ModeModule module;

    public ModeModule_ProvideModeStatusLogFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideModeStatusLogFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideModeStatusLogFactory(modeModule);
    }

    public static ModeStatusLog provideInstance(ModeModule modeModule) {
        return proxyProvideModeStatusLog(modeModule);
    }

    public static ModeStatusLog proxyProvideModeStatusLog(ModeModule modeModule) {
        return (ModeStatusLog) Preconditions.checkNotNull(modeModule.provideModeStatusLog(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ModeStatusLog mo10268get() {
        return provideInstance(this.module);
    }
}
