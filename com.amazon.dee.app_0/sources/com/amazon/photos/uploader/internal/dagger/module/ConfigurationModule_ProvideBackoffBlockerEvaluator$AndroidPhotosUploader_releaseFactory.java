package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<BackoffBlockerEvaluator> {
    private final Provider<Metrics> metricsProvider;
    private final ConfigurationModule module;
    private final Provider<PersistentLogger> persistentLoggerProvider;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<UploadRequestUpdatesNotifier> uploadRequestNotifierProvider;

    public ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<Metrics> provider, Provider<SchedulingCallback> provider2, Provider<UploadRequestUpdatesNotifier> provider3, Provider<SystemUtil> provider4, Provider<PersistentLogger> provider5) {
        this.module = configurationModule;
        this.metricsProvider = provider;
        this.schedulingCallbackProvider = provider2;
        this.uploadRequestNotifierProvider = provider3;
        this.systemUtilProvider = provider4;
        this.persistentLoggerProvider = provider5;
    }

    public static ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<Metrics> provider, Provider<SchedulingCallback> provider2, Provider<UploadRequestUpdatesNotifier> provider3, Provider<SystemUtil> provider4, Provider<PersistentLogger> provider5) {
        return new ConfigurationModule_ProvideBackoffBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static BackoffBlockerEvaluator provideBackoffBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, Metrics metrics, SchedulingCallback schedulingCallback, UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier, SystemUtil systemUtil, PersistentLogger persistentLogger) {
        return (BackoffBlockerEvaluator) Preconditions.checkNotNull(configurationModule.provideBackoffBlockerEvaluator$AndroidPhotosUploader_release(metrics, schedulingCallback, uploadRequestUpdatesNotifier, systemUtil, persistentLogger), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BackoffBlockerEvaluator mo10268get() {
        return provideBackoffBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.metricsProvider.mo10268get(), this.schedulingCallbackProvider.mo10268get(), this.uploadRequestNotifierProvider.mo10268get(), this.systemUtilProvider.mo10268get(), this.persistentLoggerProvider.mo10268get());
    }
}
