package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UtilModule_ProvidesLocationPermissionsHelperFactory implements Factory<LocationPermissionsHelper> {
    private final Provider<Context> contextProvider;
    private final UtilModule module;

    public UtilModule_ProvidesLocationPermissionsHelperFactory(UtilModule utilModule, Provider<Context> provider) {
        this.module = utilModule;
        this.contextProvider = provider;
    }

    public static UtilModule_ProvidesLocationPermissionsHelperFactory create(UtilModule utilModule, Provider<Context> provider) {
        return new UtilModule_ProvidesLocationPermissionsHelperFactory(utilModule, provider);
    }

    public static LocationPermissionsHelper provideInstance(UtilModule utilModule, Provider<Context> provider) {
        return proxyProvidesLocationPermissionsHelper(utilModule, provider.mo10268get());
    }

    public static LocationPermissionsHelper proxyProvidesLocationPermissionsHelper(UtilModule utilModule, Context context) {
        return (LocationPermissionsHelper) Preconditions.checkNotNull(utilModule.providesLocationPermissionsHelper(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationPermissionsHelper mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
