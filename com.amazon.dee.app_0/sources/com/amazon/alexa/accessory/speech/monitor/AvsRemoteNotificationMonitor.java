package com.amazon.alexa.accessory.speech.monitor;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor;
import com.amazon.alexa.accessory.internal.monitor.RemoteNotificationStatus;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.listeners.AlertsListener;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AvsRemoteNotificationMonitor implements RemoteNotificationMonitor {
    private final AlexaConnection alexaConnection;
    private final Set<RemoteNotificationMonitor.Observer> observers = new HashSet();
    private final ConnectionListener connectionListener = new ServiceConnectionListener();
    private final AlertsListener alertsListener = new AvsAlertsListener();
    private final Map<AlertsListener.AlertType, RemoteNotificationStatus> alertTypeToNotificationStatusMap = new HashMap();
    private final Set<RemoteNotificationStatus> notificationStates = new HashSet();

    /* loaded from: classes6.dex */
    final class AvsAlertsListener implements AlertsListener {
        AvsAlertsListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.AlertsListener
        public void onAlertFinished(@NonNull String str, @NonNull AlertsListener.AlertType alertType) {
            if (AvsRemoteNotificationMonitor.this.alertTypeToNotificationStatusMap.containsKey(alertType)) {
                AvsRemoteNotificationMonitor.this.notificationStates.remove(AvsRemoteNotificationMonitor.this.alertTypeToNotificationStatusMap.get(alertType));
                AvsRemoteNotificationMonitor.this.notifyObservers();
                return;
            }
            Logger.e("Ignoring AVS AlertFinished event for unsupported alert type: %s, id: %s.", alertType, str);
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.AlertsListener
        public void onAlertStarted(@NonNull String str, @NonNull AlertsListener.AlertType alertType) {
            if (AvsRemoteNotificationMonitor.this.alertTypeToNotificationStatusMap.containsKey(alertType)) {
                AvsRemoteNotificationMonitor.this.notificationStates.add(AvsRemoteNotificationMonitor.this.alertTypeToNotificationStatusMap.get(alertType));
                AvsRemoteNotificationMonitor.this.notifyObservers();
                return;
            }
            Logger.e("Ignoring AVS AlertStarted event for unsupported alert type: %s, id: %s.", alertType, str);
        }
    }

    /* loaded from: classes6.dex */
    private final class ServiceConnectionListener implements ConnectionListener {
        private ServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnected() {
            AvsRemoteNotificationMonitor.this.alexaConnection.registerAlertsListener(AvsRemoteNotificationMonitor.this.alertsListener);
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onDisconnected() {
            AvsRemoteNotificationMonitor.this.alexaConnection.deregisterAlertsListener(AvsRemoteNotificationMonitor.this.alertsListener);
        }
    }

    public AvsRemoteNotificationMonitor(AlexaConnection alexaConnection) {
        this.alexaConnection = alexaConnection;
        this.alertTypeToNotificationStatusMap.put(AlertsListener.AlertType.ALARM, RemoteNotificationStatus.ALARM);
        this.alertTypeToNotificationStatusMap.put(AlertsListener.AlertType.REMINDER, RemoteNotificationStatus.REMINDER);
        this.alertTypeToNotificationStatusMap.put(AlertsListener.AlertType.TIMER, RemoteNotificationStatus.TIMER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers() {
        HashSet<RemoteNotificationMonitor.Observer> hashSet = new HashSet(this.observers);
        HashSet hashSet2 = new HashSet(this.notificationStates);
        for (RemoteNotificationMonitor.Observer observer : hashSet) {
            observer.onRemoteNotification(hashSet2);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor
    public void addObserver(RemoteNotificationMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.notificationStates.clear();
            this.alexaConnection.registerConnectionListener(this.connectionListener);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.RemoteNotificationMonitor
    public void removeObserver(RemoteNotificationMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.alexaConnection.deregisterConnectionListener(this.connectionListener);
        this.alexaConnection.deregisterAlertsListener(this.alertsListener);
    }
}
