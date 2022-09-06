package com.amazon.alexa.voice.settings;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.sdk.AttentionSystemManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideEarconSettingsHandlerFactory implements Factory<EarconSettingsHandler> {
    private final Provider<AttentionSystemManager> attentionSystemManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public SettingsModule_ProvideEarconSettingsHandlerFactory(Provider<EventBus> provider, Provider<AttentionSystemManager> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<Context> provider4) {
        this.eventBusProvider = provider;
        this.attentionSystemManagerProvider = provider2;
        this.voxMetricEventProcessingServiceProvider = provider3;
        this.contextProvider = provider4;
    }

    public static SettingsModule_ProvideEarconSettingsHandlerFactory create(Provider<EventBus> provider, Provider<AttentionSystemManager> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<Context> provider4) {
        return new SettingsModule_ProvideEarconSettingsHandlerFactory(provider, provider2, provider3, provider4);
    }

    public static EarconSettingsHandler provideInstance(Provider<EventBus> provider, Provider<AttentionSystemManager> provider2, Provider<VoxMetricEventProcessingService> provider3, Provider<Context> provider4) {
        return proxyProvideEarconSettingsHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static EarconSettingsHandler proxyProvideEarconSettingsHandler(EventBus eventBus, AttentionSystemManager attentionSystemManager, VoxMetricEventProcessingService voxMetricEventProcessingService, Context context) {
        return (EarconSettingsHandler) Preconditions.checkNotNull(SettingsModule.provideEarconSettingsHandler(eventBus, attentionSystemManager, voxMetricEventProcessingService, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EarconSettingsHandler mo10268get() {
        return provideInstance(this.eventBusProvider, this.attentionSystemManagerProvider, this.voxMetricEventProcessingServiceProvider, this.contextProvider);
    }
}
