package com.amazon.dee.app.dependencies;

import com.amazon.alexa.photos.hva.HVAManager;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideHVAManagerFactory implements Factory<HVAManager> {
    private final Provider<CloudDriveMetrics> cloudDriveMetricsProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideHVAManagerFactory(CloudDriveModule cloudDriveModule, Provider<CloudDriveMetrics> provider) {
        this.module = cloudDriveModule;
        this.cloudDriveMetricsProvider = provider;
    }

    public static CloudDriveModule_ProvideHVAManagerFactory create(CloudDriveModule cloudDriveModule, Provider<CloudDriveMetrics> provider) {
        return new CloudDriveModule_ProvideHVAManagerFactory(cloudDriveModule, provider);
    }

    public static HVAManager provideInstance(CloudDriveModule cloudDriveModule, Provider<CloudDriveMetrics> provider) {
        return proxyProvideHVAManager(cloudDriveModule, DoubleCheck.lazy(provider));
    }

    public static HVAManager proxyProvideHVAManager(CloudDriveModule cloudDriveModule, Lazy<CloudDriveMetrics> lazy) {
        return (HVAManager) Preconditions.checkNotNull(cloudDriveModule.provideHVAManager(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HVAManager mo10268get() {
        return provideInstance(this.module, this.cloudDriveMetricsProvider);
    }
}
