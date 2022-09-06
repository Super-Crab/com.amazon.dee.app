package com.amazon.deecomms.alexa.connection.enpoint.a2a;

import androidx.annotation.NonNull;
import com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler;
import com.amazon.deecomms.calling.controller.CallManager;
/* loaded from: classes12.dex */
public class A2AConnectionEndpointHandler implements ConnectionEndpointHandler {
    final CallManager callManager;

    public A2AConnectionEndpointHandler(@NonNull CallManager callManager) {
        this.callManager = callManager;
    }

    @Override // com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler
    public boolean shouldDisconnect() {
        return !this.callManager.isInAlexaCallMode();
    }
}
