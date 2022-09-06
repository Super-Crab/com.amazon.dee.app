package com.amazon.alexa.redesign.dependency;

import com.facebook.react.ReactInstanceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideReactInstanceManagerFactory implements Factory<ReactInstanceManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideReactInstanceManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideReactInstanceManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideReactInstanceManagerFactory(applicationModule);
    }

    public static ReactInstanceManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvideReactInstanceManager(applicationModule);
    }

    public static ReactInstanceManager proxyProvideReactInstanceManager(ApplicationModule applicationModule) {
        return (ReactInstanceManager) Preconditions.checkNotNull(applicationModule.provideReactInstanceManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactInstanceManager mo10268get() {
        return provideInstance(this.module);
    }
}
