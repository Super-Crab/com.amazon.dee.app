package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideMobilyticsFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideMobilyticsFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideMobilyticsFactory(applicationModule);
    }

    public static Mobilytics provideInstance(ApplicationModule applicationModule) {
        return proxyProvideMobilytics(applicationModule);
    }

    public static Mobilytics proxyProvideMobilytics(ApplicationModule applicationModule) {
        return (Mobilytics) Preconditions.checkNotNull(applicationModule.provideMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module);
    }
}
