package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.blockers.MeteredConnectionQueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.BatteryStateBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.ChargingBlockerEvaluator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory implements Factory<Set<QueueBlockerEvaluator>> {
    private final Provider<BatteryStateBlockerEvaluator> batteryStateBlockerEvaluatorProvider;
    private final Provider<ChargingBlockerEvaluator> chargingBlockerEvaluatorProvider;
    private final Provider<MeteredConnectionQueueBlockerEvaluator> connectionBlockerEvaluatorProvider;
    private final ConfigurationModule module;

    public ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory(ConfigurationModule configurationModule, Provider<MeteredConnectionQueueBlockerEvaluator> provider, Provider<ChargingBlockerEvaluator> provider2, Provider<BatteryStateBlockerEvaluator> provider3) {
        this.module = configurationModule;
        this.connectionBlockerEvaluatorProvider = provider;
        this.chargingBlockerEvaluatorProvider = provider2;
        this.batteryStateBlockerEvaluatorProvider = provider3;
    }

    public static ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory create(ConfigurationModule configurationModule, Provider<MeteredConnectionQueueBlockerEvaluator> provider, Provider<ChargingBlockerEvaluator> provider2, Provider<BatteryStateBlockerEvaluator> provider3) {
        return new ConfigurationModule_ProvideQueueBlockerEvaluators$AndroidPhotosUploader_releaseFactory(configurationModule, provider, provider2, provider3);
    }

    public static Set<QueueBlockerEvaluator> provideQueueBlockerEvaluators$AndroidPhotosUploader_release(ConfigurationModule configurationModule, MeteredConnectionQueueBlockerEvaluator meteredConnectionQueueBlockerEvaluator, ChargingBlockerEvaluator chargingBlockerEvaluator, BatteryStateBlockerEvaluator batteryStateBlockerEvaluator) {
        return (Set) Preconditions.checkNotNull(configurationModule.provideQueueBlockerEvaluators$AndroidPhotosUploader_release(meteredConnectionQueueBlockerEvaluator, chargingBlockerEvaluator, batteryStateBlockerEvaluator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<QueueBlockerEvaluator> mo10268get() {
        return provideQueueBlockerEvaluators$AndroidPhotosUploader_release(this.module, this.connectionBlockerEvaluatorProvider.mo10268get(), this.chargingBlockerEvaluatorProvider.mo10268get(), this.batteryStateBlockerEvaluatorProvider.mo10268get());
    }
}
