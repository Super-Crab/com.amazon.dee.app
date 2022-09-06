package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class ApplicationModule_ProvideMobilyticsFactory implements Factory<LazyComponent<Mobilytics>> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideMobilyticsFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideMobilyticsFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideMobilyticsFactory(applicationModule);
    }

    public static LazyComponent<Mobilytics> provideInstance(ApplicationModule applicationModule) {
        return proxyProvideMobilytics(applicationModule);
    }

    public static LazyComponent<Mobilytics> proxyProvideMobilytics(ApplicationModule applicationModule) {
        return (LazyComponent) Preconditions.checkNotNull(applicationModule.provideMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LazyComponent<Mobilytics> mo10268get() {
        return provideInstance(this.module);
    }
}
