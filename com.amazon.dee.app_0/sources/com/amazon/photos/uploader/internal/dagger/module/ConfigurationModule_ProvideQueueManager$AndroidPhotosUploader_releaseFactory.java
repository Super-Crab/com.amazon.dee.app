package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory implements Factory<QueueManager> {
    private final Provider<Metrics> metricsProvider;
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;

    public ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider, Provider<Metrics> provider2) {
        this.module = configurationModule;
        this.schedulingCallbackProvider = provider;
        this.metricsProvider = provider2;
    }

    public static ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SchedulingCallback> provider, Provider<Metrics> provider2) {
        return new ConfigurationModule_ProvideQueueManager$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static QueueManager provideQueueManager$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SchedulingCallback schedulingCallback, Metrics metrics) {
        return (QueueManager) Preconditions.checkNotNull(configurationModule.provideQueueManager$AndroidPhotosUploader_release(schedulingCallback, metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public QueueManager mo10268get() {
        return provideQueueManager$AndroidPhotosUploader_release(this.module, this.schedulingCallbackProvider.mo10268get(), this.metricsProvider.mo10268get());
    }
}
