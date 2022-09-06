package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideFusedLocationProviderClientFactory implements Factory<FusedLocationProviderClient> {
    private final Provider<Context> contextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideFusedLocationProviderClientFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.contextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideFusedLocationProviderClientFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideFusedLocationProviderClientFactory(staticReleasePackageModule, provider);
    }

    public static FusedLocationProviderClient provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideFusedLocationProviderClient(staticReleasePackageModule, provider.mo10268get());
    }

    public static FusedLocationProviderClient proxyProvideFusedLocationProviderClient(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (FusedLocationProviderClient) Preconditions.checkNotNull(staticReleasePackageModule.provideFusedLocationProviderClient(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FusedLocationProviderClient mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
