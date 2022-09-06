package com.amazon.dee.app.dependencies;

import com.amazon.alexa.photos.api.PhotosChooser;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvidePhotosChooserFactory implements Factory<PhotosChooser> {
    private final CloudDriveModule module;

    public CloudDriveModule_ProvidePhotosChooserFactory(CloudDriveModule cloudDriveModule) {
        this.module = cloudDriveModule;
    }

    public static CloudDriveModule_ProvidePhotosChooserFactory create(CloudDriveModule cloudDriveModule) {
        return new CloudDriveModule_ProvidePhotosChooserFactory(cloudDriveModule);
    }

    public static PhotosChooser provideInstance(CloudDriveModule cloudDriveModule) {
        return proxyProvidePhotosChooser(cloudDriveModule);
    }

    public static PhotosChooser proxyProvidePhotosChooser(CloudDriveModule cloudDriveModule) {
        return (PhotosChooser) Preconditions.checkNotNull(cloudDriveModule.providePhotosChooser(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotosChooser mo10268get() {
        return provideInstance(this.module);
    }
}
