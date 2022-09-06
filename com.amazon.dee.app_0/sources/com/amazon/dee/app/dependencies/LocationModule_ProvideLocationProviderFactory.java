package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.location.LocationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LocationModule_ProvideLocationProviderFactory implements Factory<LocationProvider> {
    private final Provider<Context> contextProvider;
    private final LocationModule module;

    public LocationModule_ProvideLocationProviderFactory(LocationModule locationModule, Provider<Context> provider) {
        this.module = locationModule;
        this.contextProvider = provider;
    }

    public static LocationModule_ProvideLocationProviderFactory create(LocationModule locationModule, Provider<Context> provider) {
        return new LocationModule_ProvideLocationProviderFactory(locationModule, provider);
    }

    public static LocationProvider provideInstance(LocationModule locationModule, Provider<Context> provider) {
        return proxyProvideLocationProvider(locationModule, provider.mo10268get());
    }

    public static LocationProvider proxyProvideLocationProvider(LocationModule locationModule, Context context) {
        return (LocationProvider) Preconditions.checkNotNull(locationModule.provideLocationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
