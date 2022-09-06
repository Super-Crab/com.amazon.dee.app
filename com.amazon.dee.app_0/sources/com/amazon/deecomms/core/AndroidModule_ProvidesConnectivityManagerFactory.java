package com.amazon.deecomms.core;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesConnectivityManagerFactory implements Factory<ConnectivityManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesConnectivityManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesConnectivityManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesConnectivityManagerFactory(androidModule, provider);
    }

    public static ConnectivityManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (ConnectivityManager) Preconditions.checkNotNull(androidModule.providesConnectivityManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConnectivityManager proxyProvidesConnectivityManager(AndroidModule androidModule, Context context) {
        return (ConnectivityManager) Preconditions.checkNotNull(androidModule.providesConnectivityManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConnectivityManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
