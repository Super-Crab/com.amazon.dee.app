package com.amazon.alexa.voice.downchannel;

import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DownchannelModule_ProvideVoiceMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<DownchannelController> downchannelControllerProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public DownchannelModule_ProvideVoiceMessagingReceiverFactory(Provider<DownchannelController> provider, Provider<MetricsService> provider2) {
        this.downchannelControllerProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static DownchannelModule_ProvideVoiceMessagingReceiverFactory create(Provider<DownchannelController> provider, Provider<MetricsService> provider2) {
        return new DownchannelModule_ProvideVoiceMessagingReceiverFactory(provider, provider2);
    }

    public static MessagingReceiver provideInstance(Provider<DownchannelController> provider, Provider<MetricsService> provider2) {
        return proxyProvideVoiceMessagingReceiver(provider.mo10268get(), provider2.mo10268get());
    }

    public static MessagingReceiver proxyProvideVoiceMessagingReceiver(Object obj, MetricsService metricsService) {
        return (MessagingReceiver) Preconditions.checkNotNull(DownchannelModule.provideVoiceMessagingReceiver((DownchannelController) obj, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.downchannelControllerProvider, this.metricsServiceProvider);
    }
}
