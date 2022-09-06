package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.cdasdk.CDClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory implements Factory<CDClient> {
    private final CdsUploaderConfigurationModule module;

    public CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        this.module = cdsUploaderConfigurationModule;
    }

    public static CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return new CdsUploaderConfigurationModule_ProvidesCDCClient$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule);
    }

    public static CDClient providesCDCClient$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule) {
        return (CDClient) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesCDCClient$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CDClient mo10268get() {
        return providesCDCClient$AndroidPhotosUploader_release(this.module);
    }
}
