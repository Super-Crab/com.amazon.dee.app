package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.dao.EventDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory implements Factory<EventDao> {
    private final DatabaseModule module;

    public DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory(DatabaseModule databaseModule) {
        this.module = databaseModule;
    }

    public static DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory create(DatabaseModule databaseModule) {
        return new DatabaseModule_ProvideEventDao$AndroidPhotosUploader_releaseFactory(databaseModule);
    }

    public static EventDao provideEventDao$AndroidPhotosUploader_release(DatabaseModule databaseModule) {
        return (EventDao) Preconditions.checkNotNull(databaseModule.provideEventDao$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventDao mo10268get() {
        return provideEventDao$AndroidPhotosUploader_release(this.module);
    }
}
