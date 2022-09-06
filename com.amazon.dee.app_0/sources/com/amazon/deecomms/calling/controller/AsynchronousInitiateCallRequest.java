package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.InitiateOutboundCall;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
/* loaded from: classes12.dex */
class AsynchronousInitiateCallRequest extends InitiateCallRequest {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AsynchronousInitiateCallRequest.class);
    private final CommsIdentityManager commsIdentityManager;
    private InitiateOutboundCall initiateOutboundCall;
    private final InitiateOutboundCallRequest initiateOutboundCallRequest;

    public AsynchronousInitiateCallRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest, @NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler, @NonNull CommsIdentityManager commsIdentityManager) {
        super(validBeginCallPayloadHandler);
        this.initiateOutboundCallRequest = initiateOutboundCallRequest;
        this.commsIdentityManager = commsIdentityManager;
        this.initiateOutboundCall = new InitiateOutboundCall();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Optional<BeginCallPayload> doInBackground(Void... voidArr) {
        try {
            this.initiateOutboundCall.execute(this.commsIdentityManager.getCommsId("AsynchronousInitiateCallRequest.doInBackground", false), this.initiateOutboundCallRequest);
        } catch (ServiceException e) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Service exception occurred : ");
            outline1.append(e.getMessage());
            commsLogger.e(outline1.toString());
            showGenericErrorMessage();
        }
        return Optional.absent();
    }

    @VisibleForTesting
    public AsynchronousInitiateCallRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest, @NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull InitiateOutboundCall initiateOutboundCall) {
        super(validBeginCallPayloadHandler);
        this.initiateOutboundCallRequest = initiateOutboundCallRequest;
        this.commsIdentityManager = commsIdentityManager;
        this.initiateOutboundCall = initiateOutboundCall;
    }
}
