package com.amazon.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public class ConnectivityMonitorImpl extends BroadcastReceiver implements ConnectivityMonitor {
    private static final DPLogger log = new DPLogger("TComm.ConnectivityMonitorImpl");
    protected ConnectivityManagerWrapper mConnectivityManager;
    protected final Context mContext;
    protected final IntentFilter mFilter;
    protected boolean mIsStarted = false;
    protected final CopyOnWriteArrayList<ConnectivityChangedHandler> mHandlers = new CopyOnWriteArrayList<>();

    public ConnectivityMonitorImpl(Context context, ConnectivityManagerWrapper connectivityManagerWrapper) {
        if (context != null) {
            if (connectivityManagerWrapper != null) {
                this.mContext = context;
                this.mFilter = new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
                this.mFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
                this.mConnectivityManager = connectivityManagerWrapper;
                return;
            }
            throw new IllegalArgumentException("connectivityManager must not be null");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    public static boolean isNetworkActive(NetworkInfo.State state) {
        return state == NetworkInfo.State.CONNECTED;
    }

    private boolean isNetworkAvailable(int i) {
        return isNetworkActive(getNetworkState(i));
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public void deregisterConnectivityChangedHandler(ConnectivityChangedHandler connectivityChangedHandler) {
        if (connectivityChangedHandler != null) {
            FailFast.expectNotNull(this.mHandlers);
            this.mHandlers.remove(connectivityChangedHandler);
            return;
        }
        throw new IllegalArgumentException("handler must not be null");
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public NetworkInfo.State getNetworkState(int i) {
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(i);
        return networkInfo != null ? networkInfo.getState() : NetworkInfo.State.UNKNOWN;
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public boolean isConnectivityPossible() {
        NetworkInfo.State networkState = getNetworkState(0);
        NetworkInfo.State networkState2 = getNetworkState(1);
        NetworkInfo.State networkState3 = getNetworkState(9);
        log.debug("isConnectivityPossible", "Determining if connectivity is possible based on the following values.", "mobileNetworkState", networkState, "wifiNetworkState", networkState2, "lanNetworkState", networkState3);
        return isNetworkActive(networkState) || isNetworkActive(networkState2) || isNetworkActive(networkState3) || (this.mConnectivityManager.getActiveNetworkInfo() != null && this.mConnectivityManager.getActiveNetworkInfo().isConnected());
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public boolean isEthernetAvailable() {
        return isNetworkAvailable(9);
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public boolean isMobileAvailable() {
        return isNetworkAvailable(0);
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public boolean isWiFiAvailable() {
        return isNetworkAvailable(1);
    }

    protected void notifyConnectivityChangedHandlers() {
        FailFast.expectNotNull(this.mHandlers);
        log.verbose("notifyConnectivityChangedHandlers", "Notifying connectivity changed handlers", "number of handlers", Integer.valueOf(this.mHandlers.size()));
        Iterator<ConnectivityChangedHandler> it2 = this.mHandlers.iterator();
        while (it2.hasNext()) {
            ConnectivityChangedHandler next = it2.next();
            FailFast.expectNotNull(next);
            next.onConnectivityChanged();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
        if (!BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION.equals(action) && (!"android.net.wifi.supplicant.STATE_CHANGE".equals(action) || supplicantState != SupplicantState.COMPLETED)) {
            return;
        }
        log.verbose("onReceive", "Received intent about connectivity change", "action", action, "supplicantState", supplicantState);
        notifyConnectivityChangedHandlers();
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public void registerConnectivityChangedHandler(ConnectivityChangedHandler connectivityChangedHandler) {
        if (connectivityChangedHandler != null) {
            FailFast.expectNotNull(this.mHandlers);
            this.mHandlers.addIfAbsent(connectivityChangedHandler);
            return;
        }
        throw new IllegalArgumentException("handler cannot be null.");
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public synchronized void start() {
        if (!this.mIsStarted) {
            log.verbose("start", "Starting ConnectivityMonitor.", new Object[0]);
            this.mContext.registerReceiver(this, this.mFilter);
            this.mIsStarted = true;
        } else {
            log.warn("start", "Attempted to start ConnectivityMonitor, but it was already running.", new Object[0]);
        }
    }

    @Override // com.amazon.communication.ConnectivityMonitor
    public synchronized void stop() {
        if (this.mIsStarted) {
            this.mContext.unregisterReceiver(this);
            log.verbose("stop", "ConnectivityMonitor stopped.", new Object[0]);
            this.mHandlers.clear();
            this.mIsStarted = false;
        } else {
            log.warn("stop", "Attempted to stop ConnectivityMonitor, but it was not running.", new Object[0]);
        }
    }
}
