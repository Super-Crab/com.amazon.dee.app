package com.amazon.alexa.alertsca.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import com.amazon.alexa.alertsca.AlertsEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ConnectivityAuthority_Factory implements Factory<ConnectivityAuthority> {
    private final Provider<AlertsEventBus> alertsEventBusProvider;
    private final Provider<ConnectivityManager> connectivityManagerProvider;
    private final Provider<Context> contextProvider;

    public ConnectivityAuthority_Factory(Provider<Context> provider, Provider<ConnectivityManager> provider2, Provider<AlertsEventBus> provider3) {
        this.contextProvider = provider;
        this.connectivityManagerProvider = provider2;
        this.alertsEventBusProvider = provider3;
    }

    public static ConnectivityAuthority_Factory create(Provider<Context> provider, Provider<ConnectivityManager> provider2, Provider<AlertsEventBus> provider3) {
        return new ConnectivityAuthority_Factory(provider, provider2, provider3);
    }

    public static ConnectivityAuthority newConnectivityAuthority(Context context, ConnectivityManager connectivityManager, AlertsEventBus alertsEventBus) {
        return new ConnectivityAuthority(context, connectivityManager, alertsEventBus);
    }

    public static ConnectivityAuthority provideInstance(Provider<Context> provider, Provider<ConnectivityManager> provider2, Provider<AlertsEventBus> provider3) {
        return new ConnectivityAuthority(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConnectivityAuthority mo10268get() {
        return provideInstance(this.contextProvider, this.connectivityManagerProvider, this.alertsEventBusProvider);
    }
}
