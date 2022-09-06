package com.amazon.dee.app.dependencies;

import com.google.android.gms.common.GoogleApiAvailability;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class GoogleApiModule_ProvideGoogleApiAvailabilityFactory implements Factory<GoogleApiAvailability> {
    private final GoogleApiModule module;

    public GoogleApiModule_ProvideGoogleApiAvailabilityFactory(GoogleApiModule googleApiModule) {
        this.module = googleApiModule;
    }

    public static GoogleApiModule_ProvideGoogleApiAvailabilityFactory create(GoogleApiModule googleApiModule) {
        return new GoogleApiModule_ProvideGoogleApiAvailabilityFactory(googleApiModule);
    }

    public static GoogleApiAvailability provideInstance(GoogleApiModule googleApiModule) {
        return proxyProvideGoogleApiAvailability(googleApiModule);
    }

    public static GoogleApiAvailability proxyProvideGoogleApiAvailability(GoogleApiModule googleApiModule) {
        return (GoogleApiAvailability) Preconditions.checkNotNull(googleApiModule.provideGoogleApiAvailability(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GoogleApiAvailability mo10268get() {
        return provideInstance(this.module);
    }
}
