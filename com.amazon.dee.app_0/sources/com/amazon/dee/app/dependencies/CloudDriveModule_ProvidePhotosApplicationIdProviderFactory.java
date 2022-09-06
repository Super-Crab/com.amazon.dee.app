package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.photos.PhotosAppInfoProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvidePhotosApplicationIdProviderFactory implements Factory<PhotosAppInfoProvider> {
    private final Provider<Context> contextProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvidePhotosApplicationIdProviderFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
    }

    public static CloudDriveModule_ProvidePhotosApplicationIdProviderFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider) {
        return new CloudDriveModule_ProvidePhotosApplicationIdProviderFactory(cloudDriveModule, provider);
    }

    public static PhotosAppInfoProvider provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider) {
        return proxyProvidePhotosApplicationIdProvider(cloudDriveModule, provider.mo10268get());
    }

    public static PhotosAppInfoProvider proxyProvidePhotosApplicationIdProvider(CloudDriveModule cloudDriveModule, Context context) {
        return (PhotosAppInfoProvider) Preconditions.checkNotNull(cloudDriveModule.providePhotosApplicationIdProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotosAppInfoProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
