package com.amazon.alexa.presence;

import android.content.Context;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceApplicationLifecycleObserver_Factory implements Factory<PresenceApplicationLifecycleObserver> {
    private final Provider<Context> contextProvider;
    private final Provider<PresenceController> controllerProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public PresenceApplicationLifecycleObserver_Factory(Provider<MetricsServiceV2> provider, Provider<Context> provider2, Provider<PresenceController> provider3) {
        this.metricsServiceV2Provider = provider;
        this.contextProvider = provider2;
        this.controllerProvider = provider3;
    }

    public static PresenceApplicationLifecycleObserver_Factory create(Provider<MetricsServiceV2> provider, Provider<Context> provider2, Provider<PresenceController> provider3) {
        return new PresenceApplicationLifecycleObserver_Factory(provider, provider2, provider3);
    }

    public static PresenceApplicationLifecycleObserver newPresenceApplicationLifecycleObserver(MetricsServiceV2 metricsServiceV2, Context context, PresenceController presenceController) {
        return new PresenceApplicationLifecycleObserver(metricsServiceV2, context, presenceController);
    }

    public static PresenceApplicationLifecycleObserver provideInstance(Provider<MetricsServiceV2> provider, Provider<Context> provider2, Provider<PresenceController> provider3) {
        return new PresenceApplicationLifecycleObserver(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceApplicationLifecycleObserver mo10268get() {
        return provideInstance(this.metricsServiceV2Provider, this.contextProvider, this.controllerProvider);
    }
}
