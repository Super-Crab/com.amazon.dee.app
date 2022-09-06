package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory implements Factory<PauseResumeState> {
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;

    public ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider) {
        this.module = configurationModule;
        this.schedulingCallbackProvider = provider;
    }

    public static ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider) {
        return new ConfigurationModule_ProvidePauseResume$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static PauseResumeState providePauseResume$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SchedulingCallback schedulingCallback) {
        return (PauseResumeState) Preconditions.checkNotNull(configurationModule.providePauseResume$AndroidPhotosUploader_release(schedulingCallback), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PauseResumeState mo10268get() {
        return providePauseResume$AndroidPhotosUploader_release(this.module, this.schedulingCallbackProvider.mo10268get());
    }
}
