package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.enums.BeginCallStatusCode;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import com.amazon.deecomms.calling.model.InitiateOutboundCallResponseModel;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.InitiateOutboundCall;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
class SynchronousInitiateCallRequest extends InitiateCallRequest {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SynchronousInitiateCallRequest.class);
    private final CallInitiationOrchestrator callInitiationOrchestrator;
    private final CommsIdentityManager commsIdentityManager;
    private InitiateOutboundCall initiateOutboundCall;
    private final InitiateOutboundCallRequest initiateOutboundCallRequest;
    private final ObjectMapper objectMapper;

    public SynchronousInitiateCallRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest, @NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CallInitiationOrchestrator callInitiationOrchestrator) {
        super(validBeginCallPayloadHandler);
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.initiateOutboundCallRequest = initiateOutboundCallRequest;
        this.commsIdentityManager = commsIdentityManager;
        this.callInitiationOrchestrator = callInitiationOrchestrator;
        this.initiateOutboundCall = new InitiateOutboundCall();
    }

    @VisibleForTesting
    public Optional<InitiateOutboundCallResponseModel> buildResponseModel(@NonNull String str) {
        InitiateOutboundCallResponseModel initiateOutboundCallResponseModel;
        try {
            initiateOutboundCallResponseModel = (InitiateOutboundCallResponseModel) this.objectMapper.readValue(str, InitiateOutboundCallResponseModel.class);
            try {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Extracted payload: ");
                sb.append(initiateOutboundCallResponseModel);
                commsLogger.d(sb.toString());
            } catch (JsonProcessingException unused) {
                LOG.e("Error processing the response model");
                return Optional.fromNullable(initiateOutboundCallResponseModel);
            }
        } catch (JsonProcessingException unused2) {
            initiateOutboundCallResponseModel = null;
        }
        return Optional.fromNullable(initiateOutboundCallResponseModel);
    }

    @VisibleForTesting
    public boolean isCallInitiationRequestSuccessful(@NonNull Optional<InitiateOutboundCallResponseModel> optional) {
        if (optional.isPresent()) {
            return optional.get().getStatus() == BeginCallStatusCode.SUCCESS || optional.get().getStatus() == BeginCallStatusCode.SUCCESS_OMITTING_EXCESS_DEVICES;
        }
        return false;
    }

    @VisibleForTesting
    public boolean isValidAsynchronousResponse(@NonNull String str) {
        return StringUtils.isEmpty(str) || "{}".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Optional<BeginCallPayload> doInBackground(Void... voidArr) {
        try {
            String execute = this.initiateOutboundCall.execute(this.commsIdentityManager.getCommsId("SynchronousInitiateCallRequest.doInBackground", false), this.initiateOutboundCallRequest);
            if (isValidAsynchronousResponse(execute)) {
                return Optional.absent();
            }
            Optional<InitiateOutboundCallResponseModel> buildResponseModel = buildResponseModel(execute);
            if (!buildResponseModel.isPresent()) {
                LOG.e("Couldn't parse response model!");
                showGenericErrorMessage();
                return Optional.absent();
            } else if (isCallInitiationRequestSuccessful(buildResponseModel)) {
                return Optional.fromNullable((BeginCallPayload) this.objectMapper.readValue(buildResponseModel.get().getPayload(), BeginCallPayload.class));
            } else {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Failure in obtaining begin call payload : ");
                sb.append(buildResponseModel.get().getStatus());
                sb.append(" with message ");
                sb.append(buildResponseModel.get().getMessage());
                commsLogger.e(sb.toString());
                showGenericErrorMessage();
                return Optional.absent();
            }
        } catch (ServiceException | JsonProcessingException e) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Error occurred while parsing begin call flow : ");
            outline1.append(e.getMessage());
            outline1.append(".This is expected in the case of asynchronous begin call flow");
            commsLogger2.e(outline1.toString());
            showGenericErrorMessage();
            return Optional.absent();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(@NonNull Optional<BeginCallPayload> optional) {
        super.onPostExecute((SynchronousInitiateCallRequest) optional);
        if (optional.isPresent()) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Handling begin call execution through synchronous flow with callid :");
            outline1.append(optional.get().getCallId());
            commsLogger.i(outline1.toString());
            this.callInitiationOrchestrator.handleCallInitiationRequest(optional.get());
            return;
        }
        LOG.i("Begin Call payload missing. Must be using asynchronous begin call flow");
    }

    @VisibleForTesting
    public SynchronousInitiateCallRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest, @NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CallInitiationOrchestrator callInitiationOrchestrator, @NonNull InitiateOutboundCall initiateOutboundCall) {
        super(validBeginCallPayloadHandler);
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.initiateOutboundCallRequest = initiateOutboundCallRequest;
        this.commsIdentityManager = commsIdentityManager;
        this.callInitiationOrchestrator = callInitiationOrchestrator;
        this.initiateOutboundCall = initiateOutboundCall;
    }
}
