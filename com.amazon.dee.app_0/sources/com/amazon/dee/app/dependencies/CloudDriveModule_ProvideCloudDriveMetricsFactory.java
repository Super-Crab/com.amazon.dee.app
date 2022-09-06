package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideCloudDriveMetricsFactory implements Factory<CloudDriveMetrics> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideCloudDriveMetricsFactory(CloudDriveModule cloudDriveModule, Provider<Mobilytics> provider) {
        this.module = cloudDriveModule;
        this.mobilyticsProvider = provider;
    }

    public static CloudDriveModule_ProvideCloudDriveMetricsFactory create(CloudDriveModule cloudDriveModule, Provider<Mobilytics> provider) {
        return new CloudDriveModule_ProvideCloudDriveMetricsFactory(cloudDriveModule, provider);
    }

    public static CloudDriveMetrics provideInstance(CloudDriveModule cloudDriveModule, Provider<Mobilytics> provider) {
        return proxyProvideCloudDriveMetrics(cloudDriveModule, DoubleCheck.lazy(provider));
    }

    public static CloudDriveMetrics proxyProvideCloudDriveMetrics(CloudDriveModule cloudDriveModule, Lazy<Mobilytics> lazy) {
        return (CloudDriveMetrics) Preconditions.checkNotNull(cloudDriveModule.provideCloudDriveMetrics(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CloudDriveMetrics mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}
