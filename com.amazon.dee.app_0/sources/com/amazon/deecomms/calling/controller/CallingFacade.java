package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
/* loaded from: classes12.dex */
public interface CallingFacade {
    void beginCall(@NonNull BeginCallPayload beginCallPayload);

    void beginCall(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest);
}
