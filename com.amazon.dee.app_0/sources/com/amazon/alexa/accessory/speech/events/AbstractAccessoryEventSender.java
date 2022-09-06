package com.amazon.alexa.accessory.speech.events;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.ApiCallbacks;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
/* loaded from: classes6.dex */
public abstract class AbstractAccessoryEventSender implements AccessoryEventSender {
    private static final String TAG = "AbstractAccessoryEventSender:";
    private final AlexaConnection alexaConnection;
    private final String eventName;
    private final Handler mainThreadHandler;
    private final SessionSupplier sessionSupplier;

    public AbstractAccessoryEventSender(AlexaConnection alexaConnection, SessionSupplier sessionSupplier, String str) {
        Preconditions.notNull(alexaConnection, "alexaConnection");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(str, JsonFields.EVENT_NAME);
        this.alexaConnection = alexaConnection;
        this.sessionSupplier = sessionSupplier;
        this.eventName = str;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectAlexaServicesConnectionIfConnectedToSendEvent() {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.events.-$$Lambda$AbstractAccessoryEventSender$2VBPrx7JIFAIUo3b30c1illRoXw
            @Override // java.lang.Runnable
            public final void run() {
                AbstractAccessoryEventSender.this.lambda$disconnectAlexaServicesConnectionIfConnectedToSendEvent$0$AbstractAccessoryEventSender();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageEvent(final MessageEvent messageEvent, final String str) {
        this.alexaConnection.sendEvent(messageEvent, new ApiCallbacks() { // from class: com.amazon.alexa.accessory.speech.events.AbstractAccessoryEventSender.2
            @Override // com.amazon.alexa.accessory.speechapi.events.ApiCallbacks
            public void onFailure(Exception exc) {
                Logger.e("%s Failed to send %s event", exc, AbstractAccessoryEventSender.TAG, AbstractAccessoryEventSender.this.eventName);
                AbstractAccessoryEventSender.this.disconnectAlexaServicesConnectionIfConnectedToSendEvent();
                AbstractAccessoryEventSender.this.recordSendEventFailure(str);
            }

            @Override // com.amazon.alexa.accessory.speechapi.events.ApiCallbacks
            public void onSuccess() {
                Logger.d("%s Successfully sent %s event with id: %s", AbstractAccessoryEventSender.TAG, AbstractAccessoryEventSender.this.eventName, messageEvent.getMessageHeader().getMessageId());
                AbstractAccessoryEventSender.this.disconnectAlexaServicesConnectionIfConnectedToSendEvent();
                AbstractAccessoryEventSender.this.recordSendEventSuccess(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ensureAlexaConnectionAndSendEvent(final MessageEvent messageEvent, final String str) {
        ConnectionListener connectionListener = new ConnectionListener() { // from class: com.amazon.alexa.accessory.speech.events.AbstractAccessoryEventSender.1
            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnected() {
                Logger.d("%s AlexaConnection:onConnected callback received, sending event", AbstractAccessoryEventSender.TAG);
                AbstractAccessoryEventSender.this.alexaConnection.deregisterConnectionListener(this);
                AbstractAccessoryEventSender.this.sendMessageEvent(messageEvent, str);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str2) {
                Logger.d("%s AlexaConnection:onConnectingFailed when sending event with reason %s", AbstractAccessoryEventSender.TAG, connectingFailedReason);
                AbstractAccessoryEventSender.this.alexaConnection.deregisterConnectionListener(this);
                AbstractAccessoryEventSender.this.recordSendEventFailure(str);
            }

            @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
            public void onDisconnected() {
                AbstractAccessoryEventSender.this.alexaConnection.deregisterConnectionListener(this);
                AbstractAccessoryEventSender.this.recordSendEventFailure(str);
            }
        };
        if (this.alexaConnection.isConnected()) {
            Logger.d("%s AlexaConnection is connected, sending event", TAG);
            sendMessageEvent(messageEvent, str);
            return;
        }
        Logger.d("%s AlexaConnection is not connected, connecting to send event", TAG);
        this.alexaConnection.registerConnectionListener(connectionListener);
        this.alexaConnection.connect();
    }

    public /* synthetic */ void lambda$disconnectAlexaServicesConnectionIfConnectedToSendEvent$0$AbstractAccessoryEventSender() {
        if (this.sessionSupplier.getActiveSessions().size() != 0 || !this.alexaConnection.isConnected()) {
            return;
        }
        this.alexaConnection.disconnect();
    }

    protected abstract void recordSendEventFailure(String str);

    protected abstract void recordSendEventSuccess(String str);
}
