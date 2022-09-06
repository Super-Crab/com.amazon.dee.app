package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.AbandonedRequestHandler;
import dagger.internal.Factory;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory implements Factory<AbandonedRequestHandler> {
    private final ConfigurationModule module;

    public ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule) {
        this.module = configurationModule;
    }

    public static ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule) {
        return new ConfigurationModule_ProvidesAbandonedRequestHandler$AndroidPhotosUploader_releaseFactory(configurationModule);
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public AbandonedRequestHandler mo10268get() {
        return this.module.providesAbandonedRequestHandler$AndroidPhotosUploader_release();
    }
}
