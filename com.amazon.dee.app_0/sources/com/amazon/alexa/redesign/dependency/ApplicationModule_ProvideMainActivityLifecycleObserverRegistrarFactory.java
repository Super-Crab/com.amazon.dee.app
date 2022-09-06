package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory implements Factory<MainActivityLifecycleObserverRegistrar> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory(applicationModule);
    }

    public static MainActivityLifecycleObserverRegistrar provideInstance(ApplicationModule applicationModule) {
        return proxyProvideMainActivityLifecycleObserverRegistrar(applicationModule);
    }

    public static MainActivityLifecycleObserverRegistrar proxyProvideMainActivityLifecycleObserverRegistrar(ApplicationModule applicationModule) {
        return (MainActivityLifecycleObserverRegistrar) Preconditions.checkNotNull(applicationModule.provideMainActivityLifecycleObserverRegistrar(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainActivityLifecycleObserverRegistrar mo10268get() {
        return provideInstance(this.module);
    }
}
