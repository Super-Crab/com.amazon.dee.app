package com.amazon.deecomms.calling.commsbridgehandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.deecomms.calling.api.CallInfo;
import com.amazon.deecomms.calling.api.CallRequest;
import com.amazon.deecomms.calling.api.CallStateListener;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.api.exceptions.CallException;
import com.amazon.deecomms.common.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/* loaded from: classes12.dex */
public class InitiateCallHandler implements RequestHandler<String> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, InitiateCallHandler.class);
    @NonNull
    private final ICallingAPI callingAPI;
    @NonNull
    private final ObjectMapper objectMapper;

    public InitiateCallHandler(@NonNull ICallingAPI iCallingAPI, @NonNull ObjectMapper objectMapper) {
        this.callingAPI = iCallingAPI;
        this.objectMapper = objectMapper;
    }

    @Override // com.amazon.commscore.api.commsbridge.RequestHandler
    public void handleRequest(@Nullable String str, @NonNull ResponseResolver responseResolver) {
        LOG.i(str);
        try {
            this.callingAPI.initiateCall((CallRequest) this.objectMapper.readValue(str, CallRequest.class), new CallStateListener() { // from class: com.amazon.deecomms.calling.commsbridgehandlers.InitiateCallHandler.1
                @Override // com.amazon.deecomms.calling.api.CallStateListener
                public void onCallAccepted(CallInfo callInfo) {
                }

                @Override // com.amazon.deecomms.calling.api.CallStateListener
                public void onCallAdded(CallInfo callInfo) {
                }

                @Override // com.amazon.deecomms.calling.api.CallStateListener
                public void onCallEnded(CallInfo callInfo) {
                }

                @Override // com.amazon.deecomms.calling.api.CallStateListener
                public void onCallError(int i, String str2) {
                }
            });
        } catch (CallException e) {
            LOG.e("Cannot initiate call", e);
        } catch (JsonProcessingException e2) {
            LOG.e("Cannot deserialize payload", e2);
        }
    }
}
