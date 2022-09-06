package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class NotificationFilterServiceStatusMonitor implements NotificationFilterStatusMonitor {
    private static final String TAG = "NotificationFilterServiceStatusMonitor";
    private Disposable disposable;
    private final FeatureToggleModule featureToggleInstance;
    private final Set<NotificationFilterStatusMonitor.Observer> observers;

    public NotificationFilterServiceStatusMonitor(FeatureToggleModule featureToggleModule) {
        Preconditions.notNull(featureToggleModule, "featureToggleInstance");
        this.observers = new HashSet();
        this.featureToggleInstance = featureToggleModule;
    }

    private void ensureListening() {
        if (this.disposable != null) {
            return;
        }
        this.disposable = this.featureToggleInstance.queryNotificationForwardingStatus().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$NotificationFilterServiceStatusMonitor$kctjWV4y-O592m0guqGU75rMPdU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                NotificationFilterServiceStatusMonitor.this.notifyObservers(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyObservers(boolean z) {
        HashSet<NotificationFilterStatusMonitor.Observer> hashSet = new HashSet(this.observers);
        Logger.d("NotificationFilterServiceStatusMonitor - notifyObservers of notification filter state: " + z);
        for (NotificationFilterStatusMonitor.Observer observer : hashSet) {
            observer.onForwardNotificationChanged(z);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor
    public synchronized void addObserver(NotificationFilterStatusMonitor.Observer observer) {
        Preconditions.notNull(observer, "observer");
        Logger.d("NotificationFilterServiceStatusMonitor - add observer of notification filter state");
        ensureListening();
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor
    public boolean isNotificationForwardingEnabled() {
        return this.featureToggleInstance.isFeatureEnabled();
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NotificationFilterStatusMonitor
    public synchronized void removeObserver(NotificationFilterStatusMonitor.Observer observer) {
        Preconditions.notNull(observer, "observer");
        this.observers.remove(observer);
    }
}
