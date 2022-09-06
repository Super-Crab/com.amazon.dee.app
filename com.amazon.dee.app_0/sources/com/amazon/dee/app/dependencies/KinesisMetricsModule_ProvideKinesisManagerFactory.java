package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment;
import com.amazon.dee.app.services.metrics.kinesis.KinesisManager;
import com.amazonaws.auth.AWSCredentialsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisManagerFactory implements Factory<KinesisManager> {
    private final Provider<Context> contextProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<KinesisEnvironment> kinesisEnvironmentProvider;
    private final Provider<AWSCredentialsProvider> kinesisIdentityCredentialsProvider;
    private final KinesisMetricsModule module;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public KinesisMetricsModule_ProvideKinesisManagerFactory(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2, Provider<PersistentStorage.Factory> provider3, Provider<AWSCredentialsProvider> provider4, Provider<IdentityService> provider5) {
        this.module = kinesisMetricsModule;
        this.contextProvider = provider;
        this.kinesisEnvironmentProvider = provider2;
        this.storageFactoryProvider = provider3;
        this.kinesisIdentityCredentialsProvider = provider4;
        this.identityServiceProvider = provider5;
    }

    public static KinesisMetricsModule_ProvideKinesisManagerFactory create(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2, Provider<PersistentStorage.Factory> provider3, Provider<AWSCredentialsProvider> provider4, Provider<IdentityService> provider5) {
        return new KinesisMetricsModule_ProvideKinesisManagerFactory(kinesisMetricsModule, provider, provider2, provider3, provider4, provider5);
    }

    public static KinesisManager provideInstance(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<KinesisEnvironment> provider2, Provider<PersistentStorage.Factory> provider3, Provider<AWSCredentialsProvider> provider4, Provider<IdentityService> provider5) {
        return proxyProvideKinesisManager(kinesisMetricsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static KinesisManager proxyProvideKinesisManager(KinesisMetricsModule kinesisMetricsModule, Context context, KinesisEnvironment kinesisEnvironment, PersistentStorage.Factory factory, AWSCredentialsProvider aWSCredentialsProvider, IdentityService identityService) {
        return (KinesisManager) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisManager(context, kinesisEnvironment, factory, aWSCredentialsProvider, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.kinesisEnvironmentProvider, this.storageFactoryProvider, this.kinesisIdentityCredentialsProvider, this.identityServiceProvider);
    }
}
