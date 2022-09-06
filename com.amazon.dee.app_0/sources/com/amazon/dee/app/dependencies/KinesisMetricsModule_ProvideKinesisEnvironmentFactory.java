package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisEnvironmentFactory implements Factory<KinesisEnvironment> {
    private final KinesisMetricsModule module;

    public KinesisMetricsModule_ProvideKinesisEnvironmentFactory(KinesisMetricsModule kinesisMetricsModule) {
        this.module = kinesisMetricsModule;
    }

    public static KinesisMetricsModule_ProvideKinesisEnvironmentFactory create(KinesisMetricsModule kinesisMetricsModule) {
        return new KinesisMetricsModule_ProvideKinesisEnvironmentFactory(kinesisMetricsModule);
    }

    public static KinesisEnvironment provideInstance(KinesisMetricsModule kinesisMetricsModule) {
        return proxyProvideKinesisEnvironment(kinesisMetricsModule);
    }

    public static KinesisEnvironment proxyProvideKinesisEnvironment(KinesisMetricsModule kinesisMetricsModule) {
        return (KinesisEnvironment) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisEnvironment(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisEnvironment mo10268get() {
        return provideInstance(this.module);
    }
}
