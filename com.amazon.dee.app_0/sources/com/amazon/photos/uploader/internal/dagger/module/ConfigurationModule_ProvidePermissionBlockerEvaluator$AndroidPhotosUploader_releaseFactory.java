package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.device.PermissionBlockerEvaluator;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory implements Factory<PermissionBlockerEvaluator> {
    private final ConfigurationModule module;
    private final Provider<SchedulingCallback> schedulingCallbackProvider;
    private final Provider<SystemUtil> systemUtilProvider;

    public ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SystemUtil> provider, Provider<SchedulingCallback> provider2) {
        this.module = configurationModule;
        this.systemUtilProvider = provider;
        this.schedulingCallbackProvider = provider2;
    }

    public static ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SystemUtil> provider, Provider<SchedulingCallback> provider2) {
        return new ConfigurationModule_ProvidePermissionBlockerEvaluator$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2);
    }

    public static PermissionBlockerEvaluator providePermissionBlockerEvaluator$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SystemUtil systemUtil, SchedulingCallback schedulingCallback) {
        return (PermissionBlockerEvaluator) Preconditions.checkNotNull(configurationModule.providePermissionBlockerEvaluator$AndroidPhotosUploader_release(systemUtil, schedulingCallback), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionBlockerEvaluator mo10268get() {
        return providePermissionBlockerEvaluator$AndroidPhotosUploader_release(this.module, this.systemUtilProvider.mo10268get(), this.schedulingCallbackProvider.mo10268get());
    }
}
