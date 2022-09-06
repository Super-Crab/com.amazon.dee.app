package com.amazon.alexa.alertsca.dependencies;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaServicesModule_ProvideAmfApisFactory implements Factory<AlexaMobileFrameworkApis> {
    private final Provider<Context> contextProvider;

    public AlexaServicesModule_ProvideAmfApisFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AlexaServicesModule_ProvideAmfApisFactory create(Provider<Context> provider) {
        return new AlexaServicesModule_ProvideAmfApisFactory(provider);
    }

    public static AlexaMobileFrameworkApis provideInstance(Provider<Context> provider) {
        return proxyProvideAmfApis(provider.mo10268get());
    }

    public static AlexaMobileFrameworkApis proxyProvideAmfApis(Context context) {
        return (AlexaMobileFrameworkApis) Preconditions.checkNotNull(AlexaServicesModule.provideAmfApis(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileFrameworkApis mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
