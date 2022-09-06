package com.amazon.alexa.biloba.dependency;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class AndroidModule_ProvideGsonFactory implements Factory<Gson> {
    private final AndroidModule module;

    public AndroidModule_ProvideGsonFactory(AndroidModule androidModule) {
        this.module = androidModule;
    }

    public static AndroidModule_ProvideGsonFactory create(AndroidModule androidModule) {
        return new AndroidModule_ProvideGsonFactory(androidModule);
    }

    public static Gson provideInstance(AndroidModule androidModule) {
        return proxyProvideGson(androidModule);
    }

    public static Gson proxyProvideGson(AndroidModule androidModule) {
        return (Gson) Preconditions.checkNotNull(androidModule.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
