package com.amazon.alexa.accessory.notificationpublisher.notificationlistener;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.INotificationListenerServiceBridge;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class NotificationServiceConnection implements ServiceConnection, NotificationServiceCommunicator {
    private static final String TAG = NotificationServiceConnection.class.getSimpleName();
    private boolean bound = false;
    private INotificationListenerServiceBridge serviceBridge;

    private synchronized boolean isConnectionEstablished() {
        boolean z;
        z = this.bound && this.serviceBridge != null;
        String str = TAG;
        Log.i(str, "isConnectionEstablished - retVal = " + z);
        return z;
    }

    private boolean isConnectionEstablishedWithMetrics() {
        boolean isConnectionEstablished = isConnectionEstablished();
        if (!isConnectionEstablished && FeatureToggleModule.getInstance().hasConnectedEnabledDevices()) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_LISTENER_SERVICE_CONNECTION_BROKEN);
        }
        return isConnectionEstablished;
    }

    private void recordMetricForServiceException() {
        if (FeatureToggleModule.getInstance().hasConnectedEnabledDevices()) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.NOTIFICATION_LISTENER_SERVICE_REMOTE_EXCEPTION);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public synchronized ServiceConnection getServiceConnection() {
        return this;
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str = TAG;
        Log.i(str, "onServiceConnected - " + componentName.flattenToShortString());
        this.serviceBridge = INotificationListenerServiceBridge.Stub.asInterface(iBinder);
        this.bound = true;
        String str2 = TAG;
        Log.i(str2, "onServiceConnected - Result of set application running is: " + safeCallSetApplicationRunning(true));
        String str3 = TAG;
        Log.i(str3, "onServiceConnected - Result of on device connection changed is: " + safeCallOnDeviceConnectionChanged(FeatureToggleModule.getInstance().getDeviceConnectedState()));
        ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent = DependencyProvider.getExternalNotificationsCapabilityAgent();
        if (externalNotificationsCapabilityAgent != null) {
            externalNotificationsCapabilityAgent.uploadActiveNotifications();
        }
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceDisconnected(ComponentName componentName) {
        String str = TAG;
        Log.i(str, "onServiceDisconnected - " + componentName.flattenToShortString());
        this.serviceBridge = null;
        this.bound = false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public boolean safeCallDoesNotificationExistInStatusBar(String str) {
        Log.d(TAG, "safeCallDoesNotificationExistInStatusBar");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.doesNotificationExistInStatusBar(str);
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "safeCallDoesNotificationExistInStatusBar - RemoteException: " + e);
                return true;
            }
        }
        return true;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public boolean safeCallDoesNotificationSupportReply(String str) {
        Log.d(TAG, "safeCallDoesNotificationSupportReply");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.doesNotificationSupportReply(str);
            } catch (RemoteException e) {
                String str2 = TAG;
                Log.e(str2, "safeCallDoesNotificationSupportReply - RemoteException: " + e);
                return false;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public List<StatusBarNotification> safeCallGetActiveNotificationsWithKeys(String[] strArr) {
        Log.d(TAG, "safeCallGetActiveNotificationsWithKeys");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.getActiveNotificationsWithKeys(strArr);
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "safeCallGetActiveNotificationsWithKeys - RemoteException: " + e);
                return null;
            }
        }
        return null;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public synchronized int safeCallGetInterruptionFilter() {
        Log.d(TAG, "safeCallGetInterruptionFilter");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.getInterruptionFilter();
            } catch (RemoteException e) {
                recordMetricForServiceException();
                String str = TAG;
                Log.e(str, "safeCallGetInterruptionFilter - RemoteException: " + e);
            }
        }
        return 3;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public synchronized boolean safeCallIsListenerConnected() {
        Log.d(TAG, "safeCallIsListenerConnected");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.isListenerConnected();
            } catch (RemoteException e) {
                recordMetricForServiceException();
                String str = TAG;
                Log.e(str, "safeCallIsListenerConnected - RemoteException: " + e);
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public boolean safeCallIsReplyNotification(String str, String str2, String str3) {
        Log.d(TAG, "safeCallIsReplyNotification");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.isReplyNotification(str, str2, str3);
            } catch (RemoteException e) {
                String str4 = TAG;
                Log.e(str4, "safeCallIsReplyNotification - RemoteException: " + e);
                return false;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public synchronized boolean safeCallOnDeviceConnectionChanged(boolean z) {
        Log.d(TAG, "safeCallUpdateZionAccessoryConnected");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                this.serviceBridge.onDeviceConnectionChanged(z);
                return true;
            } catch (RemoteException e) {
                recordMetricForServiceException();
                String str = TAG;
                Log.e(str, "safeCallOnDeviceConnectionChanged - RemoteException: " + e);
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public boolean safeCallSendReply(String str, String str2) {
        Log.d(TAG, "safeCallSendReply");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                return this.serviceBridge.sendReply(str, str2);
            } catch (RemoteException e) {
                String str3 = TAG;
                Log.e(str3, "safeCallSendReply - RemoteException: " + e);
                return false;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator
    public synchronized boolean safeCallSetApplicationRunning(boolean z) {
        Log.d(TAG, "safeCallSetApplicationRunning");
        if (isConnectionEstablishedWithMetrics()) {
            try {
                this.serviceBridge.setApplicationRunning(z);
                return true;
            } catch (RemoteException e) {
                recordMetricForServiceException();
                String str = TAG;
                Log.e(str, "safeCallSetApplicationRunning - RemoteException: " + e);
            }
        }
        return false;
    }
}
