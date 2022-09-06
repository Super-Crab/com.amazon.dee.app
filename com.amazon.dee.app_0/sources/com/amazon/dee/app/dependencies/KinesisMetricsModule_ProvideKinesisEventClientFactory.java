package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.metrics.kinesis.KinesisManager;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisEventClientFactory implements Factory<KinesisEventClient> {
    private final Provider<KinesisManager> kinesisManagerProvider;
    private final KinesisMetricsModule module;

    public KinesisMetricsModule_ProvideKinesisEventClientFactory(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        this.module = kinesisMetricsModule;
        this.kinesisManagerProvider = provider;
    }

    public static KinesisMetricsModule_ProvideKinesisEventClientFactory create(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        return new KinesisMetricsModule_ProvideKinesisEventClientFactory(kinesisMetricsModule, provider);
    }

    public static KinesisEventClient provideInstance(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        return proxyProvideKinesisEventClient(kinesisMetricsModule, provider.mo10268get());
    }

    public static KinesisEventClient proxyProvideKinesisEventClient(KinesisMetricsModule kinesisMetricsModule, KinesisManager kinesisManager) {
        return (KinesisEventClient) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisEventClient(kinesisManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisEventClient mo10268get() {
        return provideInstance(this.module, this.kinesisManagerProvider);
    }
}
