package com.amazon.alexa.client.metrics.kinesis;

import android.content.Context;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import com.amazonaws.auth.AWSCredentialsProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class KinesisManager_Factory implements Factory<KinesisManager> {
    private final Provider<Context> appContextProvider;
    private final Provider<DirectedIDProvider> authorizationAuthorityDirectedIDProvider;
    private final Provider<AWSCredentialsProvider> awsCredentialsProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<PersistentStorage> persistentStorageProvider;

    public KinesisManager_Factory(Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<PersistentStorage> provider3, Provider<AWSCredentialsProvider> provider4, Provider<DirectedIDProvider> provider5, Provider<CrashReporter> provider6) {
        this.appContextProvider = provider;
        this.clientConfigurationProvider = provider2;
        this.persistentStorageProvider = provider3;
        this.awsCredentialsProvider = provider4;
        this.authorizationAuthorityDirectedIDProvider = provider5;
        this.crashReporterProvider = provider6;
    }

    public static KinesisManager_Factory create(Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<PersistentStorage> provider3, Provider<AWSCredentialsProvider> provider4, Provider<DirectedIDProvider> provider5, Provider<CrashReporter> provider6) {
        return new KinesisManager_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static KinesisManager newKinesisManager(Context context, Lazy<ClientConfiguration> lazy, Lazy<PersistentStorage> lazy2, Lazy<AWSCredentialsProvider> lazy3, Lazy<DirectedIDProvider> lazy4, CrashReporter crashReporter) {
        return new KinesisManager(context, lazy, lazy2, lazy3, lazy4, crashReporter);
    }

    public static KinesisManager provideInstance(Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<PersistentStorage> provider3, Provider<AWSCredentialsProvider> provider4, Provider<DirectedIDProvider> provider5, Provider<CrashReporter> provider6) {
        return new KinesisManager(provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisManager mo10268get() {
        return provideInstance(this.appContextProvider, this.clientConfigurationProvider, this.persistentStorageProvider, this.awsCredentialsProvider, this.authorizationAuthorityDirectedIDProvider, this.crashReporterProvider);
    }
}
