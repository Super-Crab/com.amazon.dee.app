package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideMobilyticsFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideMobilyticsFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideMobilyticsFactory(staticReleasePackageModule, provider);
    }

    public static Mobilytics provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideMobilytics(staticReleasePackageModule, provider.mo10268get());
    }

    public static Mobilytics proxyProvideMobilytics(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (Mobilytics) Preconditions.checkNotNull(staticReleasePackageModule.provideMobilytics(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
