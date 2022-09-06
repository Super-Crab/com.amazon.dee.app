package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideMetricsServiceV2Factory implements Factory<MetricsServiceV2> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvideMetricsServiceV2Factory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvideMetricsServiceV2Factory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvideMetricsServiceV2Factory(provider);
    }

    public static MetricsServiceV2 provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvideMetricsServiceV2(provider.mo10268get());
    }

    public static MetricsServiceV2 proxyProvideMetricsServiceV2(ComponentRegistry componentRegistry) {
        return (MetricsServiceV2) Preconditions.checkNotNull(PresenceModule.provideMetricsServiceV2(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2 mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
