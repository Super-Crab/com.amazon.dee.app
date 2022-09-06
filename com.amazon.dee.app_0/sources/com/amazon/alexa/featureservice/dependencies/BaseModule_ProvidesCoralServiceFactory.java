package com.amazon.alexa.featureservice.dependencies;

import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesCoralServiceFactory implements Factory<CoralService> {
    private final BaseModule module;

    public BaseModule_ProvidesCoralServiceFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesCoralServiceFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesCoralServiceFactory(baseModule);
    }

    public static CoralService provideInstance(BaseModule baseModule) {
        return proxyProvidesCoralService(baseModule);
    }

    public static CoralService proxyProvidesCoralService(BaseModule baseModule) {
        return (CoralService) Preconditions.checkNotNull(baseModule.providesCoralService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance(this.module);
    }
}
