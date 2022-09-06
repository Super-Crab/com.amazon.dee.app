package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment;
import com.amazonaws.auth.AWSCredentialsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisIdentityCredentialsProviderFactory implements Factory<AWSCredentialsProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<KinesisEnvironment> kinesisEnvironmentProvider;
    private final KinesisMetricsModule module;

    public KinesisMetricsModule_ProvideKinesisIdentityCredentialsProviderFactory(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2) {
        this.module = kinesisMetricsModule;
        this.contextProvider = provider;
        this.kinesisEnvironmentProvider = provider2;
    }

    public static KinesisMetricsModule_ProvideKinesisIdentityCredentialsProviderFactory create(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2) {
        return new KinesisMetricsModule_ProvideKinesisIdentityCredentialsProviderFactory(kinesisMetricsModule, provider, provider2);
    }

    public static AWSCredentialsProvider provideInstance(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2) {
        return proxyProvideKinesisIdentityCredentialsProvider(kinesisMetricsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AWSCredentialsProvider proxyProvideKinesisIdentityCredentialsProvider(KinesisMetricsModule kinesisMetricsModule, Context context, KinesisEnvironment kinesisEnvironment) {
        return (AWSCredentialsProvider) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisIdentityCredentialsProvider(context, kinesisEnvironment), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AWSCredentialsProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.kinesisEnvironmentProvider);
    }
}
