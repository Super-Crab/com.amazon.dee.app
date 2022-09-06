package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.interprocess.network.AccessoryNetworkService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AccessoryNetworkStatusMonitor implements NetworkStatusMonitor {
    private final AccessoryNetworkService accessoryNetworkService;
    private Disposable disposable;
    private final Set<NetworkStatusMonitor.Observer> observers;

    public AccessoryNetworkStatusMonitor(AccessoryNetworkService accessoryNetworkService) {
        Preconditions.notNull(accessoryNetworkService, "accessoryNetworkService");
        this.accessoryNetworkService = accessoryNetworkService;
        this.observers = new HashSet();
        this.disposable = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers(boolean z) {
        Preconditions.mainThread();
        for (NetworkStatusMonitor.Observer observer : new HashSet(this.observers)) {
            observer.onNetworkStatusChanged(z);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor
    public void addObserver(NetworkStatusMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.disposable = this.accessoryNetworkService.onConnectivityChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryNetworkStatusMonitor$k1hqg00RbAlxnO2hvAZRzl-Py-0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryNetworkStatusMonitor.this.notifyObservers(((Boolean) obj).booleanValue());
                }
            });
        }
        if (this.observers.add(observer)) {
            observer.onNetworkStatusChanged(isConnected());
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor
    public boolean isConnected() {
        Preconditions.mainThread();
        return this.accessoryNetworkService.isConnected();
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor
    public void removeObserver(NetworkStatusMonitor.Observer observer) {
        Disposable disposable;
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty() || (disposable = this.disposable) == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }
}
