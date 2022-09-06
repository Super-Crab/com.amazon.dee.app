package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesMobilyticsFactory implements Factory<Mobilytics> {
    private final BaseModule module;

    public BaseModule_ProvidesMobilyticsFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesMobilyticsFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesMobilyticsFactory(baseModule);
    }

    public static Mobilytics provideInstance(BaseModule baseModule) {
        return proxyProvidesMobilytics(baseModule);
    }

    public static Mobilytics proxyProvidesMobilytics(BaseModule baseModule) {
        return (Mobilytics) Preconditions.checkNotNull(baseModule.providesMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module);
    }
}
