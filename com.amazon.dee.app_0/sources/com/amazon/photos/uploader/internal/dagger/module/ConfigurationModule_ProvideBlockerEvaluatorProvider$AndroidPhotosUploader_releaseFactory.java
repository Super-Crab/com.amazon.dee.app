package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory implements Factory<BlockerEvaluatorProvider> {
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;
    private final Provider<UploadRequestUpdatesNotifier> uploadRequestNotifierProvider;

    public ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider, Provider<UploadRequestUpdatesNotifier> provider2) {
        this.module = configurationModule;
        this.schedulingCallbackProvider = provider;
        this.uploadRequestNotifierProvider = provider2;
    }

    public static ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider, Provider<UploadRequestUpdatesNotifier> provider2) {
        return new ConfigurationModule_ProvideBlockerEvaluatorProvider$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static BlockerEvaluatorProvider provideBlockerEvaluatorProvider$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SchedulingCallback schedulingCallback, UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        return (BlockerEvaluatorProvider) Preconditions.checkNotNull(configurationModule.provideBlockerEvaluatorProvider$AndroidPhotosUploader_release(schedulingCallback, uploadRequestUpdatesNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BlockerEvaluatorProvider mo10268get() {
        return provideBlockerEvaluatorProvider$AndroidPhotosUploader_release(this.module, this.schedulingCallbackProvider.mo10268get(), this.uploadRequestNotifierProvider.mo10268get());
    }
}
