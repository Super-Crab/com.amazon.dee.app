package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.protocols.features.FeatureQuery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesFeatureQueryFactory implements Factory<FeatureQuery> {
    private final BaseModule module;

    public BaseModule_ProvidesFeatureQueryFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesFeatureQueryFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesFeatureQueryFactory(baseModule);
    }

    public static FeatureQuery provideInstance(BaseModule baseModule) {
        return proxyProvidesFeatureQuery(baseModule);
    }

    public static FeatureQuery proxyProvidesFeatureQuery(BaseModule baseModule) {
        return (FeatureQuery) Preconditions.checkNotNull(baseModule.providesFeatureQuery(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureQuery mo10268get() {
        return provideInstance(this.module);
    }
}
