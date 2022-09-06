package com.amazon.deecomms.calling.commsbridgehandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class GetCallInformationHandler implements RequestHandler<String> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetCallInformationHandler.class);
    @NonNull
    private final ICallingAPI callingAPI;

    public GetCallInformationHandler(@NonNull ICallingAPI iCallingAPI) {
        this.callingAPI = iCallingAPI;
    }

    @Override // com.amazon.commscore.api.commsbridge.RequestHandler
    public void handleRequest(@Nullable String str, @NonNull ResponseResolver responseResolver) {
        responseResolver.resolve(this.callingAPI.getCallInformation());
    }
}
