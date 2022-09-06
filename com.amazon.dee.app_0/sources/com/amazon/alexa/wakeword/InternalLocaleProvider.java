package com.amazon.alexa.wakeword;

import android.os.ConditionVariable;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.Locale;
/* loaded from: classes11.dex */
public class InternalLocaleProvider implements LocaleProvider {
    private static final int CONNECTION_TIMEOUT = 3000;
    private final AlexaServicesConnection alexaServicesConnection;
    private final ConditionVariable connectionLock;

    /* loaded from: classes11.dex */
    private class ConnectionListener implements AlexaServicesConnection.ConnectionListener {
        private ConnectionListener() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            InternalLocaleProvider.this.connectionLock.open();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            InternalLocaleProvider.this.connectionLock.open();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            InternalLocaleProvider.this.connectionLock.open();
        }
    }

    public InternalLocaleProvider(AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "Connection is null");
        this.alexaServicesConnection = alexaServicesConnection;
        this.connectionLock = new ConditionVariable();
        alexaServicesConnection.registerListener(new ConnectionListener());
    }

    private void connect() {
        this.connectionLock.close();
        this.alexaServicesConnection.connect();
        if (this.connectionLock.block(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS)) {
            return;
        }
        throw new IllegalStateException("failed to connect to the alexa service");
    }

    @Override // com.amazon.alexa.wakeword.pryon.LocaleProvider
    public Locale getLocale() {
        if (this.alexaServicesConnection.isConnected()) {
            return AlexaServices.Settings.getLocale(this.alexaServicesConnection);
        }
        connect();
        if (this.alexaServicesConnection.isConnected()) {
            return AlexaServices.Settings.getLocale(this.alexaServicesConnection);
        }
        throw new IllegalStateException("failed to connect to the alexa service");
    }
}
