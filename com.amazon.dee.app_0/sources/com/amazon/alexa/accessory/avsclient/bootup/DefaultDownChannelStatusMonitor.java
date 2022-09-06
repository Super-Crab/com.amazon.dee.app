package com.amazon.alexa.accessory.avsclient.bootup;

import com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor;
import com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class DefaultDownChannelStatusMonitor implements DownChannelStatusMonitor {
    private static final String TAG = "DefaultDownChannelStatusMonitor";
    private final ReadinessSupplier readinessSupplier;
    private final List<DownChannelStatusMonitor.Observer> observers = new ArrayList();
    private final ReadinessListener readinessListener = new ReadinessListener();
    private final Object lock = new Object();
    private boolean isConnected = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ReadinessListener implements ReadinessSupplier.Listener {
        private ReadinessListener() {
        }

        @Override // com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier.Listener
        public void onReadyState(boolean z) {
            Logger.d("%s : ReadinessListener onReadyState - isReady : %b", DefaultDownChannelStatusMonitor.TAG, Boolean.valueOf(z));
            DefaultDownChannelStatusMonitor.this.notifyDownChannelStatusChanged(z);
        }
    }

    public DefaultDownChannelStatusMonitor(ReadinessSupplier readinessSupplier) {
        this.readinessSupplier = readinessSupplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDownChannelStatusChanged(boolean z) {
        this.isConnected = z;
        synchronized (this.lock) {
            for (int size = this.observers.size() - 1; size >= 0; size--) {
                this.observers.get(size).onDownChannelStatusChanged(z);
            }
        }
    }

    public void activate() {
        this.readinessSupplier.registerReadinessListener(this.readinessListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor
    public void addObserver(DownChannelStatusMonitor.Observer observer) {
        Preconditions.notNull(observer, "observer");
        synchronized (this.lock) {
            this.observers.add(observer);
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor
    public boolean getIsConnected() {
        return this.isConnected;
    }

    @Override // com.amazon.alexa.accessory.avsclient.bootup.DownChannelStatusMonitor
    public void removeObserver(DownChannelStatusMonitor.Observer observer) {
        Preconditions.notNull(observer, "observer");
        synchronized (this.lock) {
            this.observers.remove(observer);
        }
    }
}
