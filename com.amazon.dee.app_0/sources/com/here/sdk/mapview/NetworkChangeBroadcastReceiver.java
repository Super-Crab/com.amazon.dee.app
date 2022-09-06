package com.here.sdk.mapview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.here.sdk.mapview.NetworkChangesObserver;
/* loaded from: classes3.dex */
class NetworkChangeBroadcastReceiver extends BroadcastReceiver {
    private BroadcastReceiver receiver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkChangeBroadcastReceiver(final NetworkChangesObserver.Listener listener) {
        this.receiver = new BroadcastReceiver() { // from class: com.here.sdk.mapview.NetworkChangeBroadcastReceiver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getBooleanExtra("noConnectivity", false)) {
                    listener.onDisconnected();
                } else {
                    listener.onConnected();
                }
            }
        };
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.receiver.onReceive(context, intent);
    }
}
