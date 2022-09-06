package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.growth.CoachMarkFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideCoachMarkFactoryFactory implements Factory<CoachMarkFactory> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideCoachMarkFactoryFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideCoachMarkFactoryFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideCoachMarkFactoryFactory(applicationModule);
    }

    public static CoachMarkFactory provideInstance(ApplicationModule applicationModule) {
        return proxyProvideCoachMarkFactory(applicationModule);
    }

    public static CoachMarkFactory proxyProvideCoachMarkFactory(ApplicationModule applicationModule) {
        return (CoachMarkFactory) Preconditions.checkNotNull(applicationModule.provideCoachMarkFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoachMarkFactory mo10268get() {
        return provideInstance(this.module);
    }
}
