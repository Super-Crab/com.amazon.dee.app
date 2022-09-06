package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.metrics.KinesisMetricsConnector;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class KinesisMetricsModule_ProvideKinesisMetricsConnectorFactory implements Factory<KinesisMetricsConnector> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<IdentityService> identityServiceLazyProvider;
    private final Provider<KinesisEventClient> kinesisEventClientProvider;
    private final Provider<AppSessionClient> kinesisSessionClientProvider;
    private final KinesisMetricsModule module;

    public KinesisMetricsModule_ProvideKinesisMetricsConnectorFactory(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EnvironmentService> provider3, Provider<DeviceInformation> provider4, Provider<AppSessionClient> provider5, Provider<KinesisEventClient> provider6) {
        this.module = kinesisMetricsModule;
        this.contextProvider = provider;
        this.identityServiceLazyProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.deviceInformationProvider = provider4;
        this.kinesisSessionClientProvider = provider5;
        this.kinesisEventClientProvider = provider6;
    }

    public static KinesisMetricsModule_ProvideKinesisMetricsConnectorFactory create(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EnvironmentService> provider3, Provider<DeviceInformation> provider4, Provider<AppSessionClient> provider5, Provider<KinesisEventClient> provider6) {
        return new KinesisMetricsModule_ProvideKinesisMetricsConnectorFactory(kinesisMetricsModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static KinesisMetricsConnector provideInstance(KinesisMetricsModule kinesisMetricsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EnvironmentService> provider3, Provider<DeviceInformation> provider4, Provider<AppSessionClient> provider5, Provider<KinesisEventClient> provider6) {
        return proxyProvideKinesisMetricsConnector(kinesisMetricsModule, provider.mo10268get(), DoubleCheck.lazy(provider2), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static KinesisMetricsConnector proxyProvideKinesisMetricsConnector(KinesisMetricsModule kinesisMetricsModule, Context context, Lazy<IdentityService> lazy, EnvironmentService environmentService, DeviceInformation deviceInformation, Lazy<AppSessionClient> lazy2, Lazy<KinesisEventClient> lazy3) {
        return (KinesisMetricsConnector) Preconditions.checkNotNull(kinesisMetricsModule.provideKinesisMetricsConnector(context, lazy, environmentService, deviceInformation, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisMetricsConnector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceLazyProvider, this.environmentServiceProvider, this.deviceInformationProvider, this.kinesisSessionClientProvider, this.kinesisEventClientProvider);
    }
}
