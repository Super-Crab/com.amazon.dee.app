package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MapAuthModule_ProvidesMapProviderFactory implements Factory<MapAuthenticationProvider> {
    private final Provider<Context> contextProvider;
    private final MapAuthModule module;

    public MapAuthModule_ProvidesMapProviderFactory(MapAuthModule mapAuthModule, Provider<Context> provider) {
        this.module = mapAuthModule;
        this.contextProvider = provider;
    }

    public static MapAuthModule_ProvidesMapProviderFactory create(MapAuthModule mapAuthModule, Provider<Context> provider) {
        return new MapAuthModule_ProvidesMapProviderFactory(mapAuthModule, provider);
    }

    public static MapAuthenticationProvider provideInstance(MapAuthModule mapAuthModule, Provider<Context> provider) {
        return proxyProvidesMapProvider(mapAuthModule, provider.mo10268get());
    }

    public static MapAuthenticationProvider proxyProvidesMapProvider(MapAuthModule mapAuthModule, Context context) {
        return (MapAuthenticationProvider) Preconditions.checkNotNull(mapAuthModule.providesMapProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MapAuthenticationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
