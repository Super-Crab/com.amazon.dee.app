package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.dao.RequestDao;
import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory implements Factory<RequestDao> {
    private final DatabaseModule module;
    private final Provider<RunningRequestProvider> runningRequestProvider;

    public DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule, Provider<RunningRequestProvider> provider) {
        this.module = databaseModule;
        this.runningRequestProvider = provider;
    }

    public static DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule, Provider<RunningRequestProvider> provider) {
        return new DatabaseModule_ProvideRequestDao$AndroidPhotosUploader_releaseFactory(databaseModule, provider);
    }

    public static RequestDao provideRequestDao$AndroidPhotosUploader_release(DatabaseModule databaseModule, RunningRequestProvider runningRequestProvider) {
        return (RequestDao) Preconditions.checkNotNull(databaseModule.provideRequestDao$AndroidPhotosUploader_release(runningRequestProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RequestDao mo10268get() {
        return provideRequestDao$AndroidPhotosUploader_release(this.module, this.runningRequestProvider.mo10268get());
    }
}
