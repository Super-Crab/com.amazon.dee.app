package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.util.Analytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesAnalyticsFactory implements Factory<Analytics> {
    private final BaseModule module;

    public BaseModule_ProvidesAnalyticsFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesAnalyticsFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesAnalyticsFactory(baseModule);
    }

    public static Analytics provideInstance(BaseModule baseModule) {
        return proxyProvidesAnalytics(baseModule);
    }

    public static Analytics proxyProvidesAnalytics(BaseModule baseModule) {
        return (Analytics) Preconditions.checkNotNull(baseModule.providesAnalytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Analytics mo10268get() {
        return provideInstance(this.module);
    }
}
