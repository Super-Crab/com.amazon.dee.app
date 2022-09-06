package com.amazon.alexa.externalnotifications.capability.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class GsonModule_ProvidesGsonFactory implements Factory<Gson> {
    private final GsonModule module;

    public GsonModule_ProvidesGsonFactory(GsonModule gsonModule) {
        this.module = gsonModule;
    }

    public static GsonModule_ProvidesGsonFactory create(GsonModule gsonModule) {
        return new GsonModule_ProvidesGsonFactory(gsonModule);
    }

    public static Gson provideInstance(GsonModule gsonModule) {
        return proxyProvidesGson(gsonModule);
    }

    public static Gson proxyProvidesGson(GsonModule gsonModule) {
        return (Gson) Preconditions.checkNotNull(gsonModule.providesGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
