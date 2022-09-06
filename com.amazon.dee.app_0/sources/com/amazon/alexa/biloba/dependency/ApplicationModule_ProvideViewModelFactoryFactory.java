package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.view.BilobaViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideViewModelFactoryFactory implements Factory<BilobaViewModelFactory> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideViewModelFactoryFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideViewModelFactoryFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideViewModelFactoryFactory(applicationModule);
    }

    public static BilobaViewModelFactory provideInstance(ApplicationModule applicationModule) {
        return proxyProvideViewModelFactory(applicationModule);
    }

    public static BilobaViewModelFactory proxyProvideViewModelFactory(ApplicationModule applicationModule) {
        return (BilobaViewModelFactory) Preconditions.checkNotNull(applicationModule.provideViewModelFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BilobaViewModelFactory mo10268get() {
        return provideInstance(this.module);
    }
}
