package com.amazon.alexa.voice.tta.simba;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SimbaModule_ProvideSimbaClientFactory implements Factory<SimbaClient> {
    private final Provider<Gson> gsonProvider;
    private final SimbaModule module;

    public SimbaModule_ProvideSimbaClientFactory(SimbaModule simbaModule, Provider<Gson> provider) {
        this.module = simbaModule;
        this.gsonProvider = provider;
    }

    public static SimbaModule_ProvideSimbaClientFactory create(SimbaModule simbaModule, Provider<Gson> provider) {
        return new SimbaModule_ProvideSimbaClientFactory(simbaModule, provider);
    }

    public static SimbaClient provideInstance(SimbaModule simbaModule, Provider<Gson> provider) {
        return proxyProvideSimbaClient(simbaModule, provider.mo10268get());
    }

    public static SimbaClient proxyProvideSimbaClient(SimbaModule simbaModule, Gson gson) {
        return (SimbaClient) Preconditions.checkNotNull(simbaModule.provideSimbaClient(gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SimbaClient mo10268get() {
        return provideInstance(this.module, this.gsonProvider);
    }
}
