package com.google.android.exoplayer2.scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
/* loaded from: classes2.dex */
public final class RequirementsWatcher {
    private final Context context;
    private final Handler handler = Util.createHandlerForCurrentOrMainLooper();
    private final Listener listener;
    @Nullable
    private NetworkCallback networkCallback;
    private int notMetRequirements;
    @Nullable
    private DeviceStatusChangeReceiver receiver;
    private final Requirements requirements;

    /* loaded from: classes2.dex */
    private class DeviceStatusChangeReceiver extends BroadcastReceiver {
        private DeviceStatusChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                RequirementsWatcher.this.checkRequirements();
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface Listener {
        void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(24)
    /* loaded from: classes2.dex */
    public final class NetworkCallback extends ConnectivityManager.NetworkCallback {
        private boolean networkValidated;
        private boolean receivedCapabilitiesChange;

        private NetworkCallback() {
        }

        private void postCheckRequirements() {
            RequirementsWatcher.this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.scheduler.-$$Lambda$RequirementsWatcher$NetworkCallback$tlskepStWS7clkr7bPsi8F0mGcw
                @Override // java.lang.Runnable
                public final void run() {
                    RequirementsWatcher.NetworkCallback.this.lambda$postCheckRequirements$0$RequirementsWatcher$NetworkCallback();
                }
            });
        }

        private void postRecheckNotMetNetworkRequirements() {
            RequirementsWatcher.this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.scheduler.-$$Lambda$RequirementsWatcher$NetworkCallback$pFUeeeBbqeitAVRYu1Q9ghkq0mc
                @Override // java.lang.Runnable
                public final void run() {
                    RequirementsWatcher.NetworkCallback.this.lambda$postRecheckNotMetNetworkRequirements$1$RequirementsWatcher$NetworkCallback();
                }
            });
        }

        public /* synthetic */ void lambda$postCheckRequirements$0$RequirementsWatcher$NetworkCallback() {
            if (RequirementsWatcher.this.networkCallback != null) {
                RequirementsWatcher.this.checkRequirements();
            }
        }

        public /* synthetic */ void lambda$postRecheckNotMetNetworkRequirements$1$RequirementsWatcher$NetworkCallback() {
            if (RequirementsWatcher.this.networkCallback != null) {
                RequirementsWatcher.this.recheckNotMetNetworkRequirements();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            postCheckRequirements();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onBlockedStatusChanged(Network network, boolean z) {
            if (!z) {
                postRecheckNotMetNetworkRequirements();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            boolean hasCapability = networkCapabilities.hasCapability(16);
            if (!this.receivedCapabilitiesChange || this.networkValidated != hasCapability) {
                this.receivedCapabilitiesChange = true;
                this.networkValidated = hasCapability;
                postCheckRequirements();
            } else if (!hasCapability) {
            } else {
                postRecheckNotMetNetworkRequirements();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            postCheckRequirements();
        }
    }

    public RequirementsWatcher(Context context, Listener listener, Requirements requirements) {
        this.context = context.getApplicationContext();
        this.listener = listener;
        this.requirements = requirements;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkRequirements() {
        int notMetRequirements = this.requirements.getNotMetRequirements(this.context);
        if (this.notMetRequirements != notMetRequirements) {
            this.notMetRequirements = notMetRequirements;
            this.listener.onRequirementsStateChanged(this, notMetRequirements);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recheckNotMetNetworkRequirements() {
        if ((this.notMetRequirements & 3) == 0) {
            return;
        }
        checkRequirements();
    }

    @RequiresApi(24)
    private void registerNetworkCallbackV24() {
        this.networkCallback = new NetworkCallback();
        ((ConnectivityManager) Assertions.checkNotNull((ConnectivityManager) this.context.getSystemService("connectivity"))).registerDefaultNetworkCallback(this.networkCallback);
    }

    @RequiresApi(24)
    private void unregisterNetworkCallbackV24() {
        ((ConnectivityManager) Assertions.checkNotNull((ConnectivityManager) this.context.getSystemService("connectivity"))).unregisterNetworkCallback((ConnectivityManager.NetworkCallback) Assertions.checkNotNull(this.networkCallback));
        this.networkCallback = null;
    }

    public Requirements getRequirements() {
        return this.requirements;
    }

    public int start() {
        this.notMetRequirements = this.requirements.getNotMetRequirements(this.context);
        IntentFilter intentFilter = new IntentFilter();
        if (this.requirements.isNetworkRequired()) {
            if (Util.SDK_INT >= 24) {
                registerNetworkCallbackV24();
            } else {
                intentFilter.addAction(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
            }
        }
        if (this.requirements.isChargingRequired()) {
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        }
        if (this.requirements.isIdleRequired()) {
            if (Util.SDK_INT >= 23) {
                intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
            } else {
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
            }
        }
        if (this.requirements.isStorageNotLowRequired()) {
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        }
        this.receiver = new DeviceStatusChangeReceiver();
        this.context.registerReceiver(this.receiver, intentFilter, null, this.handler);
        return this.notMetRequirements;
    }

    public void stop() {
        this.context.unregisterReceiver((BroadcastReceiver) Assertions.checkNotNull(this.receiver));
        this.receiver = null;
        if (Util.SDK_INT < 24 || this.networkCallback == null) {
            return;
        }
        unregisterNetworkCallbackV24();
    }
}
