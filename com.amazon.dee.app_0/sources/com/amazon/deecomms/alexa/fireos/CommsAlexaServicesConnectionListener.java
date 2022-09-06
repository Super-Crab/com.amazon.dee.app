package com.amazon.deecomms.alexa.fireos;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class CommsAlexaServicesConnectionListener implements AlexaServicesConnection.ConnectionListener {
    @VisibleForTesting
    static final int ALEXA_SERVICES_CONNECTION_RETRY_ATTEMPTS = 3;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsAlexaServicesConnectionListener.class);
    private final AlexaServicesConnection alexaServicesConnection;
    @VisibleForTesting
    int connectionRetryAttempts = 3;
    private final Context context;

    public CommsAlexaServicesConnectionListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull Context context) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.context = context;
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        LOG.i("Alexa Service Connection is now connected.");
        this.connectionRetryAttempts = 3;
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.ALEXA_SERVICE_CONNECTION_CONNECTED));
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("ASC Connection failed. Reason: " + alexaConnectingFailedReason + " : " + str);
        int i = this.connectionRetryAttempts;
        if (i > 0) {
            this.connectionRetryAttempts = i - 1;
            LOG.i("Retrying AlexaServices connection...");
            this.alexaServicesConnection.connect();
            return;
        }
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED));
        this.connectionRetryAttempts = 3;
        LOG.i("De-register ASC Listener");
        this.alexaServicesConnection.deregisterListener(this);
        LOG.i("ASC connection failed to connect for PCC .Giving up trying to re-connect");
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        LOG.i("Alexa Service Disconnected");
        this.connectionRetryAttempts = 3;
        Intent intent = new Intent(Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED);
        LOG.i("De-register ASC Listener");
        this.alexaServicesConnection.deregisterListener(this);
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
    }
}
