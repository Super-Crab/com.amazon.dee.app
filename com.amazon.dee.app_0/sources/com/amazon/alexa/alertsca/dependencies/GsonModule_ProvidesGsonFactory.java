package com.amazon.alexa.alertsca.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class GsonModule_ProvidesGsonFactory implements Factory<Gson> {
    private static final GsonModule_ProvidesGsonFactory INSTANCE = new GsonModule_ProvidesGsonFactory();

    public static GsonModule_ProvidesGsonFactory create() {
        return INSTANCE;
    }

    public static Gson provideInstance() {
        return proxyProvidesGson();
    }

    public static Gson proxyProvidesGson() {
        return (Gson) Preconditions.checkNotNull(GsonModule.providesGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance();
    }
}
