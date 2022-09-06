package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesCommsMetricsServiceFactory implements Factory<AlexaCommsCoreMetricsService> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesCommsMetricsServiceFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesCommsMetricsServiceFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesCommsMetricsServiceFactory(alexaSharingModule);
    }

    public static AlexaCommsCoreMetricsService provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesCommsMetricsService(alexaSharingModule);
    }

    public static AlexaCommsCoreMetricsService proxyProvidesCommsMetricsService(AlexaSharingModule alexaSharingModule) {
        return (AlexaCommsCoreMetricsService) Preconditions.checkNotNull(alexaSharingModule.providesCommsMetricsService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreMetricsService mo10268get() {
        return provideInstance(this.module);
    }
}
