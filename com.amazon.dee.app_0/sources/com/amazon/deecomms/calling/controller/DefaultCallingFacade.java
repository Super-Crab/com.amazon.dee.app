package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DefaultCallingFacade implements CallingFacade {
    private final CallInitiationOrchestrator callInitiationOrchestrator;

    @Inject
    public DefaultCallingFacade(@NonNull CallInitiationOrchestrator callInitiationOrchestrator) {
        this.callInitiationOrchestrator = callInitiationOrchestrator;
    }

    @Override // com.amazon.deecomms.calling.controller.CallingFacade
    public void beginCall(@NonNull BeginCallPayload beginCallPayload) {
        this.callInitiationOrchestrator.handleCallInitiationRequest(beginCallPayload);
    }

    @Override // com.amazon.deecomms.calling.controller.CallingFacade
    public void beginCall(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest) {
        this.callInitiationOrchestrator.handleCallInitiationRequest(initiateOutboundCallRequest);
    }
}
