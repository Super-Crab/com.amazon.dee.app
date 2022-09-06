package com.amazon.photos.autosave.internal.dagger.module;

import com.amazon.photos.uploader.UploadManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory implements Factory<UploadManager> {
    private final AutosaveModule module;

    public AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory(AutosaveModule autosaveModule) {
        this.module = autosaveModule;
    }

    public static AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory create(AutosaveModule autosaveModule) {
        return new AutosaveModule_ProvideUploadManager$AndroidPhotosAutosave_releaseFactory(autosaveModule);
    }

    public static UploadManager provideUploadManager$AndroidPhotosAutosave_release(AutosaveModule autosaveModule) {
        return (UploadManager) Preconditions.checkNotNull(autosaveModule.provideUploadManager$AndroidPhotosAutosave_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UploadManager mo10268get() {
        return provideUploadManager$AndroidPhotosAutosave_release(this.module);
    }
}
