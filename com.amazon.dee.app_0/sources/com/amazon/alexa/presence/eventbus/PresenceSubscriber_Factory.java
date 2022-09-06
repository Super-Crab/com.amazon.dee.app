package com.amazon.alexa.presence.eventbus;

import android.content.Context;
import com.amazon.alexa.presence.PresenceController;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceSubscriber_Factory implements Factory<PresenceSubscriber> {
    private final Provider<Context> contextProvider;
    private final Provider<PresenceController> controllerProvider;
    private final Provider<EventMessageFilter> eventMessageFilterProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public PresenceSubscriber_Factory(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<PresenceController> provider3, Provider<MetricsServiceV2> provider4) {
        this.contextProvider = provider;
        this.eventMessageFilterProvider = provider2;
        this.controllerProvider = provider3;
        this.metricsServiceV2Provider = provider4;
    }

    public static PresenceSubscriber_Factory create(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<PresenceController> provider3, Provider<MetricsServiceV2> provider4) {
        return new PresenceSubscriber_Factory(provider, provider2, provider3, provider4);
    }

    public static PresenceSubscriber newPresenceSubscriber(Context context, EventMessageFilter eventMessageFilter, PresenceController presenceController, MetricsServiceV2 metricsServiceV2) {
        return new PresenceSubscriber(context, eventMessageFilter, presenceController, metricsServiceV2);
    }

    public static PresenceSubscriber provideInstance(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<PresenceController> provider3, Provider<MetricsServiceV2> provider4) {
        return new PresenceSubscriber(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceSubscriber mo10268get() {
        return provideInstance(this.contextProvider, this.eventMessageFilterProvider, this.controllerProvider, this.metricsServiceV2Provider);
    }
}
