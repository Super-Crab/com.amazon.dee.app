package com.here.sdk.mapview;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.here.sdk.mapview.NetworkChangesObserver;
/* loaded from: classes3.dex */
class NetworkChangeCallback extends ConnectivityManager.NetworkCallback {
    NetworkChangesObserver.Listener mObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 23)
    public NetworkChangeCallback(NetworkChangesObserver.Listener listener, ConnectivityManager connectivityManager) {
        this.mObserver = listener;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            this.mObserver.onDisconnected();
            return;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities == null || !networkCapabilities.hasCapability(12)) {
            this.mObserver.onDisconnected();
        } else {
            this.mObserver.onConnected();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        this.mObserver.onConnected();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        this.mObserver.onDisconnected();
    }
}
