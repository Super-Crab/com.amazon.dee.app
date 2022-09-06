package com.amazon.alexa.voice.tta.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class DependenciesModule_ProvidesGsonFactory implements Factory<Gson> {
    private final DependenciesModule module;

    public DependenciesModule_ProvidesGsonFactory(DependenciesModule dependenciesModule) {
        this.module = dependenciesModule;
    }

    public static DependenciesModule_ProvidesGsonFactory create(DependenciesModule dependenciesModule) {
        return new DependenciesModule_ProvidesGsonFactory(dependenciesModule);
    }

    public static Gson provideInstance(DependenciesModule dependenciesModule) {
        return proxyProvidesGson(dependenciesModule);
    }

    public static Gson proxyProvidesGson(DependenciesModule dependenciesModule) {
        return (Gson) Preconditions.checkNotNull(dependenciesModule.providesGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
