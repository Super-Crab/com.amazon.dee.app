package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.internal.device.BatteryState;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory implements Factory<BatteryState> {
    private final ConfigurationModule module;
    private final Provider<SystemUtil> systemUtilProvider;

    public ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<SystemUtil> provider) {
        this.module = configurationModule;
        this.systemUtilProvider = provider;
    }

    public static ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<SystemUtil> provider) {
        return new ConfigurationModule_ProvideBatteryState$AndroidPhotosUploader_releaseFactory(configurationModule, provider);
    }

    public static BatteryState provideBatteryState$AndroidPhotosUploader_release(ConfigurationModule configurationModule, SystemUtil systemUtil) {
        return (BatteryState) Preconditions.checkNotNull(configurationModule.provideBatteryState$AndroidPhotosUploader_release(systemUtil), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryState mo10268get() {
        return provideBatteryState$AndroidPhotosUploader_release(this.module, this.systemUtilProvider.mo10268get());
    }
}
