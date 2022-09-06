package com.amazon.alexa.biloba.dependency;

import com.dee.app.data.api.ElementLocalStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideElementLocalStorageFactory implements Factory<ElementLocalStorage> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideElementLocalStorageFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideElementLocalStorageFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideElementLocalStorageFactory(applicationModule);
    }

    public static ElementLocalStorage provideInstance(ApplicationModule applicationModule) {
        return proxyProvideElementLocalStorage(applicationModule);
    }

    public static ElementLocalStorage proxyProvideElementLocalStorage(ApplicationModule applicationModule) {
        return (ElementLocalStorage) Preconditions.checkNotNull(applicationModule.provideElementLocalStorage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ElementLocalStorage mo10268get() {
        return provideInstance(this.module);
    }
}
