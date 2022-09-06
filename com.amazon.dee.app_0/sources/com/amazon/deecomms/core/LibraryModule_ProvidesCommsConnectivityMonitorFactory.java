package com.amazon.deecomms.core;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsConnectivityMonitorFactory implements Factory<CommsConnectivityMonitor> {
    private final Provider<ConnectivityManager> connectivityManagerProvider;
    private final LibraryModule module;
    private final Provider<PackageManager> packageManagerProvider;
    private final Provider<TelephonyManager> telephonyManagerProvider;

    public LibraryModule_ProvidesCommsConnectivityMonitorFactory(LibraryModule libraryModule, Provider<ConnectivityManager> provider, Provider<PackageManager> provider2, Provider<TelephonyManager> provider3) {
        this.module = libraryModule;
        this.connectivityManagerProvider = provider;
        this.packageManagerProvider = provider2;
        this.telephonyManagerProvider = provider3;
    }

    public static LibraryModule_ProvidesCommsConnectivityMonitorFactory create(LibraryModule libraryModule, Provider<ConnectivityManager> provider, Provider<PackageManager> provider2, Provider<TelephonyManager> provider3) {
        return new LibraryModule_ProvidesCommsConnectivityMonitorFactory(libraryModule, provider, provider2, provider3);
    }

    public static CommsConnectivityMonitor provideInstance(LibraryModule libraryModule, Provider<ConnectivityManager> provider, Provider<PackageManager> provider2, Provider<TelephonyManager> provider3) {
        return (CommsConnectivityMonitor) Preconditions.checkNotNull(libraryModule.providesCommsConnectivityMonitor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsConnectivityMonitor proxyProvidesCommsConnectivityMonitor(LibraryModule libraryModule, ConnectivityManager connectivityManager, PackageManager packageManager, TelephonyManager telephonyManager) {
        return (CommsConnectivityMonitor) Preconditions.checkNotNull(libraryModule.providesCommsConnectivityMonitor(connectivityManager, packageManager, telephonyManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsConnectivityMonitor mo10268get() {
        return provideInstance(this.module, this.connectivityManagerProvider, this.packageManagerProvider, this.telephonyManagerProvider);
    }
}
