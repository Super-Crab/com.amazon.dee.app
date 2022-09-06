package com.amazon.dee.app.dependencies;

import com.amazon.alexa.photos.PhotosFeatureGuardian;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvidePhotosFeatureGuardianFactory implements Factory<PhotosFeatureGuardian> {
    private final CloudDriveModule module;

    public CloudDriveModule_ProvidePhotosFeatureGuardianFactory(CloudDriveModule cloudDriveModule) {
        this.module = cloudDriveModule;
    }

    public static CloudDriveModule_ProvidePhotosFeatureGuardianFactory create(CloudDriveModule cloudDriveModule) {
        return new CloudDriveModule_ProvidePhotosFeatureGuardianFactory(cloudDriveModule);
    }

    public static PhotosFeatureGuardian provideInstance(CloudDriveModule cloudDriveModule) {
        return proxyProvidePhotosFeatureGuardian(cloudDriveModule);
    }

    public static PhotosFeatureGuardian proxyProvidePhotosFeatureGuardian(CloudDriveModule cloudDriveModule) {
        return (PhotosFeatureGuardian) Preconditions.checkNotNull(cloudDriveModule.providePhotosFeatureGuardian(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotosFeatureGuardian mo10268get() {
        return provideInstance(this.module);
    }
}
