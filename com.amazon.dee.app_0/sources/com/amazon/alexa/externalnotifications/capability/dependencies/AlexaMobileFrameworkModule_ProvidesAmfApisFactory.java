package com.amazon.alexa.externalnotifications.capability.dependencies;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AlexaMobileFrameworkModule_ProvidesAmfApisFactory implements Factory<AlexaMobileFrameworkApis> {
    private final Provider<Context> contextProvider;
    private final AlexaMobileFrameworkModule module;

    public AlexaMobileFrameworkModule_ProvidesAmfApisFactory(AlexaMobileFrameworkModule alexaMobileFrameworkModule, Provider<Context> provider) {
        this.module = alexaMobileFrameworkModule;
        this.contextProvider = provider;
    }

    public static AlexaMobileFrameworkModule_ProvidesAmfApisFactory create(AlexaMobileFrameworkModule alexaMobileFrameworkModule, Provider<Context> provider) {
        return new AlexaMobileFrameworkModule_ProvidesAmfApisFactory(alexaMobileFrameworkModule, provider);
    }

    public static AlexaMobileFrameworkApis provideInstance(AlexaMobileFrameworkModule alexaMobileFrameworkModule, Provider<Context> provider) {
        return proxyProvidesAmfApis(alexaMobileFrameworkModule, provider.mo10268get());
    }

    public static AlexaMobileFrameworkApis proxyProvidesAmfApis(AlexaMobileFrameworkModule alexaMobileFrameworkModule, Context context) {
        return (AlexaMobileFrameworkApis) Preconditions.checkNotNull(alexaMobileFrameworkModule.providesAmfApis(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileFrameworkApis mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
