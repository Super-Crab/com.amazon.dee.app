package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider<Context> provider, Provider<CreationContextFactory> provider2) {
        this.applicationContextProvider = provider;
        this.creationContextFactoryProvider = provider2;
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> provider, Provider<CreationContextFactory> provider2) {
        return new MetadataBackendRegistry_Factory(provider, provider2);
    }

    public static MetadataBackendRegistry newInstance(Context context, Object obj) {
        return new MetadataBackendRegistry(context, (CreationContextFactory) obj);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetadataBackendRegistry mo10268get() {
        return new MetadataBackendRegistry(this.applicationContextProvider.mo10268get(), this.creationContextFactoryProvider.mo10268get());
    }
}
