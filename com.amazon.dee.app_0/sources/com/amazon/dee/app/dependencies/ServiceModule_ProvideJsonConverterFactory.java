package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.storage.JsonConverter;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideJsonConverterFactory implements Factory<JsonConverter> {
    private final Provider<Gson> gsonProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideJsonConverterFactory(ServiceModule serviceModule, Provider<Gson> provider) {
        this.module = serviceModule;
        this.gsonProvider = provider;
    }

    public static ServiceModule_ProvideJsonConverterFactory create(ServiceModule serviceModule, Provider<Gson> provider) {
        return new ServiceModule_ProvideJsonConverterFactory(serviceModule, provider);
    }

    public static JsonConverter provideInstance(ServiceModule serviceModule, Provider<Gson> provider) {
        return proxyProvideJsonConverter(serviceModule, provider.mo10268get());
    }

    public static JsonConverter proxyProvideJsonConverter(ServiceModule serviceModule, Gson gson) {
        return (JsonConverter) Preconditions.checkNotNull(serviceModule.provideJsonConverter(gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JsonConverter mo10268get() {
        return provideInstance(this.module, this.gsonProvider);
    }
}
