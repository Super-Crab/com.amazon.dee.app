package com.amazon.alexa.presence.eventbus;

import android.content.Context;
import com.amazon.alexa.presence.PresenceController;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PushNotificationSubscriber_Factory implements Factory<PushNotificationSubscriber> {
    private final Provider<Context> contextProvider;
    private final Provider<PresenceController> controllerProvider;
    private final Provider<EventMessageFilter> eventMessageFilterProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public PushNotificationSubscriber_Factory(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<MetricsServiceV2> provider3, Provider<PresenceController> provider4) {
        this.contextProvider = provider;
        this.eventMessageFilterProvider = provider2;
        this.metricsServiceV2Provider = provider3;
        this.controllerProvider = provider4;
    }

    public static PushNotificationSubscriber_Factory create(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<MetricsServiceV2> provider3, Provider<PresenceController> provider4) {
        return new PushNotificationSubscriber_Factory(provider, provider2, provider3, provider4);
    }

    public static PushNotificationSubscriber newPushNotificationSubscriber(Context context, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new PushNotificationSubscriber(context, eventMessageFilter, metricsServiceV2, presenceController);
    }

    public static PushNotificationSubscriber provideInstance(Provider<Context> provider, Provider<EventMessageFilter> provider2, Provider<MetricsServiceV2> provider3, Provider<PresenceController> provider4) {
        return new PushNotificationSubscriber(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PushNotificationSubscriber mo10268get() {
        return provideInstance(this.contextProvider, this.eventMessageFilterProvider, this.metricsServiceV2Provider, this.controllerProvider);
    }
}
