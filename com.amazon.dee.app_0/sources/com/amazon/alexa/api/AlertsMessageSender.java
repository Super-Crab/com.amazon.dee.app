package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class AlertsMessageSender extends AlexaMessageSender<e> implements AlertsListener {
    private static final String TAG = "AlertsMessageSender";
    private final ExtendedClient client;

    public AlertsMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlertsListener
    public void onAlertFinished(String str, AlertType alertType) {
        try {
            sendMessage(e.ON_ALERT_FINISHED, new c(this.client, str, alertType).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send onAlertFinished event ", e);
        }
    }

    @Override // com.amazon.alexa.api.AlertsListener
    public void onAlertStarted(String str, AlertType alertType) {
        try {
            sendMessage(e.ON_ALERT_STARTED, new c(this.client, str, alertType).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send onAlertStarted event ", e);
        }
    }
}
