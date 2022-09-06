package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class CoralServiceModule_ProvideCoralServiceFactory implements Factory<CoralService> {
    private static final CoralServiceModule_ProvideCoralServiceFactory INSTANCE = new CoralServiceModule_ProvideCoralServiceFactory();

    public static CoralServiceModule_ProvideCoralServiceFactory create() {
        return INSTANCE;
    }

    public static CoralService provideInstance() {
        return proxyProvideCoralService();
    }

    public static CoralService proxyProvideCoralService() {
        return (CoralService) Preconditions.checkNotNull(CoralServiceModule.provideCoralService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance();
    }
}
