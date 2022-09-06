package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideTTCFRecordOnceFactory implements Factory<TTCFRecordOnce> {
    private final Provider<LazyComponent<TTCFCheckpoint>> checkPointProvider;
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideTTCFRecordOnceFactory(ModeMetricsModule modeMetricsModule, Provider<LazyComponent<TTCFCheckpoint>> provider) {
        this.module = modeMetricsModule;
        this.checkPointProvider = provider;
    }

    public static ModeMetricsModule_ProvideTTCFRecordOnceFactory create(ModeMetricsModule modeMetricsModule, Provider<LazyComponent<TTCFCheckpoint>> provider) {
        return new ModeMetricsModule_ProvideTTCFRecordOnceFactory(modeMetricsModule, provider);
    }

    public static TTCFRecordOnce provideInstance(ModeMetricsModule modeMetricsModule, Provider<LazyComponent<TTCFCheckpoint>> provider) {
        return proxyProvideTTCFRecordOnce(modeMetricsModule, provider.mo10268get());
    }

    public static TTCFRecordOnce proxyProvideTTCFRecordOnce(ModeMetricsModule modeMetricsModule, LazyComponent<TTCFCheckpoint> lazyComponent) {
        return (TTCFRecordOnce) Preconditions.checkNotNull(modeMetricsModule.provideTTCFRecordOnce(lazyComponent), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TTCFRecordOnce mo10268get() {
        return provideInstance(this.module, this.checkPointProvider);
    }
}
