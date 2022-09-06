package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaServicesModule_ProvidesAmfApisFactory implements Factory<AlexaMobileFrameworkApis> {
    private final Provider<Context> contextProvider;

    public AlexaServicesModule_ProvidesAmfApisFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AlexaServicesModule_ProvidesAmfApisFactory create(Provider<Context> provider) {
        return new AlexaServicesModule_ProvidesAmfApisFactory(provider);
    }

    public static AlexaMobileFrameworkApis provideInstance(Provider<Context> provider) {
        return proxyProvidesAmfApis(provider.mo10268get());
    }

    public static AlexaMobileFrameworkApis proxyProvidesAmfApis(Context context) {
        return (AlexaMobileFrameworkApis) Preconditions.checkNotNull(AlexaServicesModule.providesAmfApis(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileFrameworkApis mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
