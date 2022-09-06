package com.amazon.dee.app.dependencies;

import com.google.firebase.iid.FirebaseInstanceId;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class GoogleApiModule_ProvideInstanceIDFactory implements Factory<FirebaseInstanceId> {
    private final GoogleApiModule module;

    public GoogleApiModule_ProvideInstanceIDFactory(GoogleApiModule googleApiModule) {
        this.module = googleApiModule;
    }

    public static GoogleApiModule_ProvideInstanceIDFactory create(GoogleApiModule googleApiModule) {
        return new GoogleApiModule_ProvideInstanceIDFactory(googleApiModule);
    }

    public static FirebaseInstanceId provideInstance(GoogleApiModule googleApiModule) {
        return proxyProvideInstanceID(googleApiModule);
    }

    public static FirebaseInstanceId proxyProvideInstanceID(GoogleApiModule googleApiModule) {
        return (FirebaseInstanceId) Preconditions.checkNotNull(googleApiModule.provideInstanceID(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FirebaseInstanceId mo10268get() {
        return provideInstance(this.module);
    }
}
