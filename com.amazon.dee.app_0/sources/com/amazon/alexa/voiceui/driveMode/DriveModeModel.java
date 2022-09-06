package com.amazon.alexa.voiceui.driveMode;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.voiceui.AlexaServicesApis;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class DriveModeModel {
    private final AlexaServicesApis alexaServicesApis;
    private final List<DriveModeListener> listeners = new ArrayList();
    @VisibleForTesting
    final ConnectionListener connectionListener = new ConnectionListener();
    @VisibleForTesting
    final InternalAutoModeListener autoModeListener = new InternalAutoModeListener();

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class ConnectionListener implements AlexaServicesConnection.ConnectionListener {
        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            DriveModeModel.this.alexaServicesApis.registerDriveModeListener(DriveModeModel.this.autoModeListener);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            DriveModeModel.this.alexaServicesApis.deregisterDriveModeListener(DriveModeModel.this.autoModeListener);
        }

        private ConnectionListener() {
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class InternalAutoModeListener implements AlexaDriveModeListener {
        @Override // com.amazon.alexa.api.AlexaDriveModeListener
        public void onDriveModeEnabled(boolean z) {
            synchronized (DriveModeModel.this.listeners) {
                for (DriveModeListener driveModeListener : DriveModeModel.this.listeners) {
                    driveModeListener.onAutoModeEnabled(z);
                }
            }
        }

        @Override // com.amazon.alexa.api.AlexaDriveModeListener
        public void onDriveModeState(DriveModeState driveModeState) {
            synchronized (DriveModeModel.this.listeners) {
                for (DriveModeListener driveModeListener : DriveModeModel.this.listeners) {
                    driveModeListener.onAutoModeState(driveModeState);
                }
            }
        }

        @Override // com.amazon.alexa.api.AlexaDriveModeListener
        public void onDriveModeThemeChanged(boolean z) {
            synchronized (DriveModeModel.this.listeners) {
                for (DriveModeListener driveModeListener : DriveModeModel.this.listeners) {
                    driveModeListener.onThemeChanged(z);
                }
            }
        }

        private InternalAutoModeListener() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DriveModeModel(AlexaServicesApis alexaServicesApis) {
        this.alexaServicesApis = alexaServicesApis;
    }

    public void addDriveModeListener(DriveModeListener driveModeListener) {
        synchronized (this.listeners) {
            this.listeners.add(driveModeListener);
        }
    }

    public void initialize() {
        this.alexaServicesApis.registerConnectionListener(this.connectionListener);
    }

    public void release() {
        this.alexaServicesApis.deregisterConnectionListener(this.connectionListener);
    }

    public void removeDriveModeListener(DriveModeListener driveModeListener) {
        synchronized (this.listeners) {
            this.listeners.remove(driveModeListener);
        }
    }
}
