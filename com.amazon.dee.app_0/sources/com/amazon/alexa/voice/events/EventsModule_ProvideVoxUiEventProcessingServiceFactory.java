package com.amazon.alexa.voice.events;

import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class EventsModule_ProvideVoxUiEventProcessingServiceFactory implements Factory<VoxUiEventProcessingService> {
    private final Provider<VoxMetricEventProcessingService> metricEventProcessingServiceProvider;

    public EventsModule_ProvideVoxUiEventProcessingServiceFactory(Provider<VoxMetricEventProcessingService> provider) {
        this.metricEventProcessingServiceProvider = provider;
    }

    public static EventsModule_ProvideVoxUiEventProcessingServiceFactory create(Provider<VoxMetricEventProcessingService> provider) {
        return new EventsModule_ProvideVoxUiEventProcessingServiceFactory(provider);
    }

    public static VoxUiEventProcessingService provideInstance(Provider<VoxMetricEventProcessingService> provider) {
        return proxyProvideVoxUiEventProcessingService(provider.mo10268get());
    }

    public static VoxUiEventProcessingService proxyProvideVoxUiEventProcessingService(VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return (VoxUiEventProcessingService) Preconditions.checkNotNull(EventsModule.provideVoxUiEventProcessingService(voxMetricEventProcessingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoxUiEventProcessingService mo10268get() {
        return provideInstance(this.metricEventProcessingServiceProvider);
    }
}
