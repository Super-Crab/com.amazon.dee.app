package com.amazon.alexa.featureservice.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesGsonInstanceFactory implements Factory<Gson> {
    private final BaseModule module;

    public BaseModule_ProvidesGsonInstanceFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesGsonInstanceFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesGsonInstanceFactory(baseModule);
    }

    public static Gson provideInstance(BaseModule baseModule) {
        return proxyProvidesGsonInstance(baseModule);
    }

    public static Gson proxyProvidesGsonInstance(BaseModule baseModule) {
        return (Gson) Preconditions.checkNotNull(baseModule.providesGsonInstance(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
