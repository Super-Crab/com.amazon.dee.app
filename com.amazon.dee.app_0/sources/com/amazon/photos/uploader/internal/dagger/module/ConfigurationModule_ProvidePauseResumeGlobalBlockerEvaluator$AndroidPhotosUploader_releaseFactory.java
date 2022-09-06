package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.blockers.PauseGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<PauseGlobalBlockerEvaluator> {
    private final ConfigurationModule module;
    private final Provider<PauseResumeState> pauseResumeStateProvider;

    public ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<PauseResumeState> provider) {
        this.module = configurationModule;
        this.pauseResumeStateProvider = provider;
    }

    public static ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<PauseResumeState> provider) {
        return new ConfigurationModule_ProvidePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static PauseGlobalBlockerEvaluator providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, PauseResumeState pauseResumeState) {
        return (PauseGlobalBlockerEvaluator) Preconditions.checkNotNull(configurationModule.providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_release(pauseResumeState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PauseGlobalBlockerEvaluator mo10268get() {
        return providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.pauseResumeStateProvider.mo10268get());
    }
}
