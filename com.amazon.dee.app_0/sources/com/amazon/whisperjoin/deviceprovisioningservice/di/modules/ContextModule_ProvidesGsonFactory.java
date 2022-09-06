package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesGsonFactory implements Factory<Gson> {
    private final ContextModule module;

    public ContextModule_ProvidesGsonFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public static ContextModule_ProvidesGsonFactory create(ContextModule contextModule) {
        return new ContextModule_ProvidesGsonFactory(contextModule);
    }

    public static Gson provideInstance(ContextModule contextModule) {
        return proxyProvidesGson(contextModule);
    }

    public static Gson proxyProvidesGson(ContextModule contextModule) {
        return (Gson) Preconditions.checkNotNull(contextModule.providesGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
