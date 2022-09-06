package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory implements Factory<PersistentLogger> {
    private final Provider<EventDao> eventDaoProvider;
    private final ConfigurationModule module;

    public ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<EventDao> provider) {
        this.module = configurationModule;
        this.eventDaoProvider = provider;
    }

    public static ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<EventDao> provider) {
        return new ConfigurationModule_ProvidePersistentLogger$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static PersistentLogger providePersistentLogger$AndroidPhotosUploader_release(ConfigurationModule configurationModule, EventDao eventDao) {
        return (PersistentLogger) Preconditions.checkNotNull(configurationModule.providePersistentLogger$AndroidPhotosUploader_release(eventDao), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentLogger mo10268get() {
        return providePersistentLogger$AndroidPhotosUploader_release(this.module, this.eventDaoProvider.mo10268get());
    }
}
