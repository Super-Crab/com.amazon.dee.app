package com.amazon.alexa.mobilytics.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideGsonFactory implements Factory<Gson> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideGsonFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideGsonFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideGsonFactory(mobilyticsModule);
    }

    public static Gson provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideGson(mobilyticsModule);
    }

    public static Gson proxyProvideGson(MobilyticsModule mobilyticsModule) {
        return (Gson) Preconditions.checkNotNull(mobilyticsModule.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
