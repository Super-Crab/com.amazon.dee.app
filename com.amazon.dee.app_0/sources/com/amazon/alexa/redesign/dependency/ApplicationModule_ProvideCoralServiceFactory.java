package com.amazon.alexa.redesign.dependency;

import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideCoralServiceFactory implements Factory<CoralService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideCoralServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideCoralServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideCoralServiceFactory(applicationModule);
    }

    public static CoralService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideCoralService(applicationModule);
    }

    public static CoralService proxyProvideCoralService(ApplicationModule applicationModule) {
        return (CoralService) Preconditions.checkNotNull(applicationModule.provideCoralService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance(this.module);
    }
}
