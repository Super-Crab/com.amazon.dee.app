package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.metrics.kinesis.KinesisManager;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisSessionClientFactory implements Factory<AppSessionClient> {
    private final Provider<KinesisManager> kinesisManagerProvider;
    private final KinesisMetricsModule module;

    public KinesisMetricsModule_ProvideKinesisSessionClientFactory(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        this.module = kinesisMetricsModule;
        this.kinesisManagerProvider = provider;
    }

    public static KinesisMetricsModule_ProvideKinesisSessionClientFactory create(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        return new KinesisMetricsModule_ProvideKinesisSessionClientFactory(kinesisMetricsModule, provider);
    }

    public static AppSessionClient provideInstance(KinesisMetricsModule kinesisMetricsModule, Provider<KinesisManager> provider) {
        return proxyProvideKinesisSessionClient(kinesisMetricsModule, provider.mo10268get());
    }

    public static AppSessionClient proxyProvideKinesisSessionClient(KinesisMetricsModule kinesisMetricsModule, KinesisManager kinesisManager) {
        return (AppSessionClient) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisSessionClient(kinesisManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppSessionClient mo10268get() {
        return provideInstance(this.module, this.kinesisManagerProvider);
    }
}
