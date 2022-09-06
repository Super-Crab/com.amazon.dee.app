package com.amazon.deecomms.alexa.connection.enpoint.pcc;

import androidx.annotation.NonNull;
import com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
/* loaded from: classes12.dex */
public class PCCConnectionEndpointHandler implements ConnectionEndpointHandler {
    final CallManager callManager;
    final CapabilitiesManager capabilitiesManager;

    public PCCConnectionEndpointHandler(@NonNull CapabilitiesManager capabilitiesManager, @NonNull CallManager callManager) {
        this.capabilitiesManager = capabilitiesManager;
        this.callManager = callManager;
    }

    @Override // com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler
    public boolean shouldDisconnect() {
        return !Utils.areAccessoriesConnected() && !this.callManager.isPCCCallInProgress(this.capabilitiesManager);
    }
}
