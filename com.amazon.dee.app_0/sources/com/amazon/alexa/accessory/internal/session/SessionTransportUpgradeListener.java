package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.capabilities.transport.TransportCapability;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
final class SessionTransportUpgradeListener implements TransportCapability.TransportUpgradeListener {
    private final BaseAccessorySession session;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionTransportUpgradeListener(BaseAccessorySession baseAccessorySession) {
        Preconditions.notNull(baseAccessorySession, "session");
        this.session = baseAccessorySession;
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportConnectRequested(Accessory accessory, TransportCapability.TransportConnectedListener transportConnectedListener) {
        Logger.d("Transport connect requested to " + accessory);
        this.session.setupSecondaryConnection(accessory, transportConnectedListener);
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportSwitchRequested() {
        Logger.d("Transport switch is being requested");
        this.session.prepareSwitchPrimaryConnection();
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportSwitchWillRetry(AccessoryTransport.Type type) {
        Logger.d("Transport switch will be retried with accessory over transport %s", type);
        this.session.onTransportSwitchWillRetry(type);
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportSwitched(Accessory accessory) {
        Logger.d("Transport switch success with " + accessory);
        this.session.switchPrimaryConnection(accessory);
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportUpgradeFailed(AccessoryTransport.Type type, Throwable th) {
        Logger.e("Transport upgrade failed", th);
        this.session.onTransportUpgradeFailed(type);
    }

    @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportUpgradeListener
    public void onTransportUpgradeRequested(AccessoryTransport.Type type) {
        Logger.d("Transport upgrade requested to %s", type);
        this.session.prepareSetupSecondaryConnection(type);
    }
}
