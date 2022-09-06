package com.amazon.deecomms.calling.commsbridgehandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.api.ResponseCallback;
import com.amazon.deecomms.calling.api.Result;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class GetCallHistoryHandler implements RequestHandler<String> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetCallHistoryHandler.class);
    @NonNull
    private final ICallingAPI callingAPI;

    public GetCallHistoryHandler(@NonNull ICallingAPI iCallingAPI) {
        this.callingAPI = iCallingAPI;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleRequest$0(ResponseResolver responseResolver, Result result) {
        if (result instanceof Result.Success) {
            responseResolver.resolve(((Result.Success) result).getData());
        } else {
            responseResolver.reject(((Result.Error) result).getException());
        }
    }

    @Override // com.amazon.commscore.api.commsbridge.RequestHandler
    public void handleRequest(@Nullable String str, @NonNull final ResponseResolver responseResolver) {
        if (str == null) {
            LOG.e("Cannot process empty payload.");
        } else {
            this.callingAPI.getCallHistory(Integer.parseInt(str), new ResponseCallback() { // from class: com.amazon.deecomms.calling.commsbridgehandlers.-$$Lambda$GetCallHistoryHandler$IbVpSm3p_wnTWzBeN5vwzO-SKbo
                @Override // com.amazon.deecomms.calling.api.ResponseCallback
                public final void onComplete(Result result) {
                    GetCallHistoryHandler.lambda$handleRequest$0(ResponseResolver.this, result);
                }
            });
        }
    }
}
