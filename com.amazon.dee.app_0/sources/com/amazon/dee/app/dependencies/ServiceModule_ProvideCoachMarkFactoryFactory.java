package com.amazon.dee.app.dependencies;

import com.amazon.alexa.growth.CoachMarkFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCoachMarkFactoryFactory implements Factory<CoachMarkFactory> {
    private final ServiceModule module;

    public ServiceModule_ProvideCoachMarkFactoryFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideCoachMarkFactoryFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideCoachMarkFactoryFactory(serviceModule);
    }

    public static CoachMarkFactory provideInstance(ServiceModule serviceModule) {
        return proxyProvideCoachMarkFactory(serviceModule);
    }

    public static CoachMarkFactory proxyProvideCoachMarkFactory(ServiceModule serviceModule) {
        return (CoachMarkFactory) Preconditions.checkNotNull(serviceModule.provideCoachMarkFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoachMarkFactory mo10268get() {
        return provideInstance(this.module);
    }
}
