package com.here.sdk.mapview;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
/* loaded from: classes3.dex */
class NetworkChangesObserver {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface Listener {
        void onConnected();

        void onDisconnected();
    }

    NetworkChangesObserver() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void observe(Context context, Listener listener) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (Build.VERSION.SDK_INT < 28 || connectivityManager == null) {
            context.registerReceiver(new NetworkChangeBroadcastReceiver(listener), new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION));
        } else {
            connectivityManager.registerDefaultNetworkCallback(new NetworkChangeCallback(listener, connectivityManager));
        }
    }
}
