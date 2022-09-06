package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideJsonConverterFactory implements Factory<JsonConverter> {
    private final Provider<Gson> gsonProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideJsonConverterFactory(MobilyticsModule mobilyticsModule, Provider<Gson> provider) {
        this.module = mobilyticsModule;
        this.gsonProvider = provider;
    }

    public static MobilyticsModule_ProvideJsonConverterFactory create(MobilyticsModule mobilyticsModule, Provider<Gson> provider) {
        return new MobilyticsModule_ProvideJsonConverterFactory(mobilyticsModule, provider);
    }

    public static JsonConverter provideInstance(MobilyticsModule mobilyticsModule, Provider<Gson> provider) {
        return proxyProvideJsonConverter(mobilyticsModule, provider.mo10268get());
    }

    public static JsonConverter proxyProvideJsonConverter(MobilyticsModule mobilyticsModule, Gson gson) {
        return (JsonConverter) Preconditions.checkNotNull(mobilyticsModule.provideJsonConverter(gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JsonConverter mo10268get() {
        return provideInstance(this.module, this.gsonProvider);
    }
}
