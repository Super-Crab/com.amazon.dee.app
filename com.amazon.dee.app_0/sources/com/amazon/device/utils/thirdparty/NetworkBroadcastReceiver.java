package com.amazon.device.utils.thirdparty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;
    private List<NetworkListener> mNetworkListenerList = new ArrayList();
    private boolean isRegistered = false;

    public NetworkBroadcastReceiver(Context context) {
        this.mContext = context;
    }

    private void tryRegister() {
        if (!this.isRegistered && this.mNetworkListenerList.size() > 0) {
            this.mContext.registerReceiver(this, GeneratedOutlineSupport1.outline10(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION));
            this.isRegistered = true;
        }
    }

    private void tryUnregister() {
        if (this.isRegistered && this.mNetworkListenerList.size() == 0) {
            this.mContext.unregisterReceiver(this);
            this.isRegistered = false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public synchronized void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION)) {
            return;
        }
        if (intent.getBooleanExtra("noConnectivity", true)) {
            return;
        }
        ArrayList<NetworkListener> arrayList = new ArrayList();
        if (NetworkManager.instance(this.mContext).isWifiConnected()) {
            for (NetworkListener networkListener : this.mNetworkListenerList) {
                if (networkListener.onWifiAvailable()) {
                    arrayList.add(networkListener);
                }
            }
        } else if (NetworkManager.instance(this.mContext).isEthernetConnected()) {
            for (NetworkListener networkListener2 : this.mNetworkListenerList) {
                if (networkListener2.onEthernetAvailable()) {
                    arrayList.add(networkListener2);
                }
            }
        } else if (NetworkManager.instance(this.mContext).isWanConnected()) {
            for (NetworkListener networkListener3 : this.mNetworkListenerList) {
                if (networkListener3.onWanAvailable()) {
                    arrayList.add(networkListener3);
                }
            }
        }
        for (NetworkListener networkListener4 : arrayList) {
            this.mNetworkListenerList.remove(networkListener4);
        }
        tryUnregister();
    }

    public synchronized void registerNetworkListener(NetworkListener networkListener) {
        if (!this.mNetworkListenerList.contains(networkListener)) {
            this.mNetworkListenerList.add(networkListener);
        }
        tryRegister();
    }

    public synchronized void unregisterNetworkListener(NetworkListener networkListener) {
        if (this.mNetworkListenerList.contains(networkListener)) {
            this.mNetworkListenerList.remove(networkListener);
        }
        tryUnregister();
    }
}
