package com.reactnativecommunity.netinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
/* loaded from: classes3.dex */
public class AmazonFireDeviceConnectivityPoller {
    private static final String ACTION_CONNECTIVITY_CHECK = "com.amazon.tv.networkmonitor.CONNECTIVITY_CHECK";
    private static final String ACTION_INTERNET_DOWN = "com.amazon.tv.networkmonitor.INTERNET_DOWN";
    private static final String ACTION_INTERNET_UP = "com.amazon.tv.networkmonitor.INTERNET_UP";
    private static final long POLLING_INTERVAL_MS = 10000;
    private final ConnectivityChangedCallback callback;
    private final Context context;
    private Handler handler;
    private final Receiver receiver = new Receiver();
    private final Runnable checker = new PollerTask();
    private boolean pollerRunning = false;

    /* loaded from: classes3.dex */
    public interface ConnectivityChangedCallback {
        void onAmazonFireDeviceConnectivityChanged(boolean z);
    }

    /* loaded from: classes3.dex */
    private class PollerTask implements Runnable {
        private PollerTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!AmazonFireDeviceConnectivityPoller.this.pollerRunning) {
                return;
            }
            AmazonFireDeviceConnectivityPoller.this.context.sendBroadcast(new Intent(AmazonFireDeviceConnectivityPoller.ACTION_CONNECTIVITY_CHECK));
            AmazonFireDeviceConnectivityPoller.this.handler.postDelayed(AmazonFireDeviceConnectivityPoller.this.checker, 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class Receiver extends BroadcastReceiver {
        private Boolean lastIsConnected;
        boolean registered;

        private Receiver() {
            this.registered = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            String action = intent == null ? null : intent.getAction();
            if (AmazonFireDeviceConnectivityPoller.ACTION_INTERNET_DOWN.equals(action)) {
                z = false;
            } else if (!AmazonFireDeviceConnectivityPoller.ACTION_INTERNET_UP.equals(action)) {
                return;
            } else {
                z = true;
            }
            Boolean bool = this.lastIsConnected;
            if (bool == null || bool.booleanValue() != z) {
                this.lastIsConnected = Boolean.valueOf(z);
                AmazonFireDeviceConnectivityPoller.this.callback.onAmazonFireDeviceConnectivityChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AmazonFireDeviceConnectivityPoller(Context context, ConnectivityChangedCallback connectivityChangedCallback) {
        this.context = context;
        this.callback = connectivityChangedCallback;
    }

    private boolean isFireOsDevice() {
        return Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.startsWith("AF") || Build.MODEL.startsWith("KF"));
    }

    private void registerReceiver() {
        if (this.receiver.registered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_INTERNET_DOWN);
        intentFilter.addAction(ACTION_INTERNET_UP);
        this.context.registerReceiver(this.receiver, intentFilter);
        this.receiver.registered = true;
    }

    private void startPoller() {
        if (this.pollerRunning) {
            return;
        }
        this.handler = new Handler();
        this.pollerRunning = true;
        this.handler.post(this.checker);
    }

    private void stopPoller() {
        if (!this.pollerRunning) {
            return;
        }
        this.pollerRunning = false;
        this.handler.removeCallbacksAndMessages(null);
        this.handler = null;
    }

    private void unregisterReceiver() {
        Receiver receiver = this.receiver;
        if (!receiver.registered) {
            return;
        }
        this.context.unregisterReceiver(receiver);
        this.receiver.registered = false;
    }

    public void register() {
        if (!isFireOsDevice()) {
            return;
        }
        registerReceiver();
        startPoller();
    }

    public void unregister() {
        if (!isFireOsDevice()) {
            return;
        }
        stopPoller();
        unregisterReceiver();
    }
}
