package com.amazon.deecomms.calling.commsbridgehandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.deecomms.calling.api.ICallingAPI;
/* loaded from: classes12.dex */
public class EndCallHandler implements RequestHandler<String> {
    @NonNull
    private final ICallingAPI callingAPI;

    public EndCallHandler(@NonNull ICallingAPI iCallingAPI) {
        this.callingAPI = iCallingAPI;
    }

    @Override // com.amazon.commscore.api.commsbridge.RequestHandler
    public void handleRequest(@Nullable String str, @NonNull ResponseResolver responseResolver) {
        this.callingAPI.endCall(str);
    }
}
