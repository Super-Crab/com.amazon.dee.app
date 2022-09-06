package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.photos.PhotosAppInfoProvider;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.hva.HVAManager;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.dee.app.services.clouddrive.MAPAuthenticatedURLConnectionFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvidePhotosUploadBundleManagerFactory implements Factory<UploadBundleManager> {
    private final Provider<PhotosAppInfoProvider> appInfoProvider;
    private final Provider<CloudDriveMetrics> cloudDriveMetricsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HVAManager> hvaManagerProvider;
    private final CloudDriveModule module;
    private final Provider<PhotosFeatureGuardian> photosFeatureGuardianProvider;
    private final Provider<MAPAuthenticatedURLConnectionFactory> urlConnectionFactoryProvider;

    public CloudDriveModule_ProvidePhotosUploadBundleManagerFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<HVAManager> provider2, Provider<PhotosAppInfoProvider> provider3, Provider<CloudDriveMetrics> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<PhotosFeatureGuardian> provider6) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
        this.hvaManagerProvider = provider2;
        this.appInfoProvider = provider3;
        this.cloudDriveMetricsProvider = provider4;
        this.urlConnectionFactoryProvider = provider5;
        this.photosFeatureGuardianProvider = provider6;
    }

    public static CloudDriveModule_ProvidePhotosUploadBundleManagerFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<HVAManager> provider2, Provider<PhotosAppInfoProvider> provider3, Provider<CloudDriveMetrics> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<PhotosFeatureGuardian> provider6) {
        return new CloudDriveModule_ProvidePhotosUploadBundleManagerFactory(cloudDriveModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static UploadBundleManager provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<HVAManager> provider2, Provider<PhotosAppInfoProvider> provider3, Provider<CloudDriveMetrics> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<PhotosFeatureGuardian> provider6) {
        return proxyProvidePhotosUploadBundleManager(cloudDriveModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static UploadBundleManager proxyProvidePhotosUploadBundleManager(CloudDriveModule cloudDriveModule, Context context, HVAManager hVAManager, PhotosAppInfoProvider photosAppInfoProvider, Lazy<CloudDriveMetrics> lazy, Lazy<MAPAuthenticatedURLConnectionFactory> lazy2, Lazy<PhotosFeatureGuardian> lazy3) {
        return (UploadBundleManager) Preconditions.checkNotNull(cloudDriveModule.providePhotosUploadBundleManager(context, hVAManager, photosAppInfoProvider, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadBundleManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.hvaManagerProvider, this.appInfoProvider, this.cloudDriveMetricsProvider, this.urlConnectionFactoryProvider, this.photosFeatureGuardianProvider);
    }
}
