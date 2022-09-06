package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesAlexaClientSdkApisFactory implements Factory<AlexaClientSdkApis> {
    private final Provider<AlexaMobileFrameworkApis> alexaMobileFrameworkApisProvider;
    private final Provider<Gson> gsonProvider;
    private final SdkModule module;

    public SdkModule_ProvidesAlexaClientSdkApisFactory(SdkModule sdkModule, Provider<AlexaMobileFrameworkApis> provider, Provider<Gson> provider2) {
        this.module = sdkModule;
        this.alexaMobileFrameworkApisProvider = provider;
        this.gsonProvider = provider2;
    }

    public static SdkModule_ProvidesAlexaClientSdkApisFactory create(SdkModule sdkModule, Provider<AlexaMobileFrameworkApis> provider, Provider<Gson> provider2) {
        return new SdkModule_ProvidesAlexaClientSdkApisFactory(sdkModule, provider, provider2);
    }

    public static AlexaClientSdkApis provideInstance(SdkModule sdkModule, Provider<AlexaMobileFrameworkApis> provider, Provider<Gson> provider2) {
        return proxyProvidesAlexaClientSdkApis(sdkModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AlexaClientSdkApis proxyProvidesAlexaClientSdkApis(SdkModule sdkModule, AlexaMobileFrameworkApis alexaMobileFrameworkApis, Gson gson) {
        return (AlexaClientSdkApis) Preconditions.checkNotNull(sdkModule.providesAlexaClientSdkApis(alexaMobileFrameworkApis, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaClientSdkApis mo10268get() {
        return provideInstance(this.module, this.alexaMobileFrameworkApisProvider, this.gsonProvider);
    }
}
