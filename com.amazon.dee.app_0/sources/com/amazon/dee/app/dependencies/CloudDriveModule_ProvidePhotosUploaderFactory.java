package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.photos.PhotosFeatureGuardian;
import com.amazon.alexa.photos.UploadBundleManager;
import com.amazon.alexa.photos.api.PhotosUploader;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvidePhotosUploaderFactory implements Factory<PhotosUploader> {
    private final Provider<AmazonCloudDriveExtendedClient> cdClientProvider;
    private final Provider<CloudDriveMetrics> cloudDriveMetricsProvider;
    private final Provider<Context> contextProvider;
    private final CloudDriveModule module;
    private final Provider<PhotosFeatureGuardian> photosFeatureGuardianLazyProvider;
    private final Provider<SystemUtility> systemUtilityProvider;
    private final Provider<UploadBundleManager> uploadBundleManagerProvider;

    public CloudDriveModule_ProvidePhotosUploaderFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<AmazonCloudDriveExtendedClient> provider2, Provider<UploadBundleManager> provider3, Provider<PhotosFeatureGuardian> provider4, Provider<CloudDriveMetrics> provider5, Provider<SystemUtility> provider6) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
        this.cdClientProvider = provider2;
        this.uploadBundleManagerProvider = provider3;
        this.photosFeatureGuardianLazyProvider = provider4;
        this.cloudDriveMetricsProvider = provider5;
        this.systemUtilityProvider = provider6;
    }

    public static CloudDriveModule_ProvidePhotosUploaderFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<AmazonCloudDriveExtendedClient> provider2, Provider<UploadBundleManager> provider3, Provider<PhotosFeatureGuardian> provider4, Provider<CloudDriveMetrics> provider5, Provider<SystemUtility> provider6) {
        return new CloudDriveModule_ProvidePhotosUploaderFactory(cloudDriveModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PhotosUploader provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<AmazonCloudDriveExtendedClient> provider2, Provider<UploadBundleManager> provider3, Provider<PhotosFeatureGuardian> provider4, Provider<CloudDriveMetrics> provider5, Provider<SystemUtility> provider6) {
        return proxyProvidePhotosUploader(cloudDriveModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static PhotosUploader proxyProvidePhotosUploader(CloudDriveModule cloudDriveModule, Context context, Lazy<AmazonCloudDriveExtendedClient> lazy, Lazy<UploadBundleManager> lazy2, Lazy<PhotosFeatureGuardian> lazy3, Lazy<CloudDriveMetrics> lazy4, Lazy<SystemUtility> lazy5) {
        return (PhotosUploader) Preconditions.checkNotNull(cloudDriveModule.providePhotosUploader(context, lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotosUploader mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.cdClientProvider, this.uploadBundleManagerProvider, this.photosFeatureGuardianLazyProvider, this.cloudDriveMetricsProvider, this.systemUtilityProvider);
    }
}
