package com.amazon.alexa.voice.wakeword;

import android.content.Context;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvideVoiceOverUtilityFactory implements Factory<VoiceOverUtility> {
    private final Provider<Context> contextProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public WakeWordModule_ProvideVoiceOverUtilityFactory(Provider<Context> provider, Provider<VoxMetricEventProcessingService> provider2) {
        this.contextProvider = provider;
        this.voxMetricEventProcessingServiceProvider = provider2;
    }

    public static WakeWordModule_ProvideVoiceOverUtilityFactory create(Provider<Context> provider, Provider<VoxMetricEventProcessingService> provider2) {
        return new WakeWordModule_ProvideVoiceOverUtilityFactory(provider, provider2);
    }

    public static VoiceOverUtility provideInstance(Provider<Context> provider, Provider<VoxMetricEventProcessingService> provider2) {
        return proxyProvideVoiceOverUtility(provider.mo10268get(), provider2.mo10268get());
    }

    public static VoiceOverUtility proxyProvideVoiceOverUtility(Context context, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return (VoiceOverUtility) Preconditions.checkNotNull(WakeWordModule.provideVoiceOverUtility(context, voxMetricEventProcessingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceOverUtility mo10268get() {
        return provideInstance(this.contextProvider, this.voxMetricEventProcessingServiceProvider);
    }
}
