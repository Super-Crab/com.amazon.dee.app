package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import com.amazon.alexa.presence.eventbus.PresenceSubscriber;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePresenceSubscriberFactory implements Factory<PresenceSubscriber> {
    private final Provider<PresenceController> controllerProvider;
    private final Provider<EventMessageFilter> eventMessageFilterProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;

    public PresenceModule_ProvidePresenceSubscriberFactory(PresenceModule presenceModule, Provider<EventMessageFilter> provider, Provider<MetricsServiceV2> provider2, Provider<PresenceController> provider3) {
        this.module = presenceModule;
        this.eventMessageFilterProvider = provider;
        this.metricsServiceV2Provider = provider2;
        this.controllerProvider = provider3;
    }

    public static PresenceModule_ProvidePresenceSubscriberFactory create(PresenceModule presenceModule, Provider<EventMessageFilter> provider, Provider<MetricsServiceV2> provider2, Provider<PresenceController> provider3) {
        return new PresenceModule_ProvidePresenceSubscriberFactory(presenceModule, provider, provider2, provider3);
    }

    public static PresenceSubscriber provideInstance(PresenceModule presenceModule, Provider<EventMessageFilter> provider, Provider<MetricsServiceV2> provider2, Provider<PresenceController> provider3) {
        return proxyProvidePresenceSubscriber(presenceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PresenceSubscriber proxyProvidePresenceSubscriber(PresenceModule presenceModule, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return (PresenceSubscriber) Preconditions.checkNotNull(presenceModule.providePresenceSubscriber(eventMessageFilter, metricsServiceV2, presenceController), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceSubscriber mo10268get() {
        return provideInstance(this.module, this.eventMessageFilterProvider, this.metricsServiceV2Provider, this.controllerProvider);
    }
}
