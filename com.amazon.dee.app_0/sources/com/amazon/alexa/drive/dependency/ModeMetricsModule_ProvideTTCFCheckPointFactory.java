package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideTTCFCheckPointFactory implements Factory<LazyComponent<TTCFCheckpoint>> {
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideTTCFCheckPointFactory(ModeMetricsModule modeMetricsModule) {
        this.module = modeMetricsModule;
    }

    public static ModeMetricsModule_ProvideTTCFCheckPointFactory create(ModeMetricsModule modeMetricsModule) {
        return new ModeMetricsModule_ProvideTTCFCheckPointFactory(modeMetricsModule);
    }

    public static LazyComponent<TTCFCheckpoint> provideInstance(ModeMetricsModule modeMetricsModule) {
        return proxyProvideTTCFCheckPoint(modeMetricsModule);
    }

    public static LazyComponent<TTCFCheckpoint> proxyProvideTTCFCheckPoint(ModeMetricsModule modeMetricsModule) {
        return (LazyComponent) Preconditions.checkNotNull(modeMetricsModule.provideTTCFCheckPoint(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LazyComponent<TTCFCheckpoint> mo10268get() {
        return provideInstance(this.module);
    }
}
