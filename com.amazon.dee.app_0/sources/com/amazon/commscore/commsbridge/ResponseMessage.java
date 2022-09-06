package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.commsbridge.BridgeMessage;
/* loaded from: classes12.dex */
class ResponseMessage extends BridgeMessage {
    private final Throwable error;

    private ResponseMessage(String str, String str2, Object obj, Throwable th) {
        super(str, str2, BridgeMessage.MessageType.RESPONSE, obj);
        this.error = th;
    }

    public static ResponseMessage create(@NonNull RequestMessage requestMessage, @Nullable Object obj) {
        return new ResponseMessage(requestMessage.getName(), requestMessage.getId(), obj, null);
    }

    @Nullable
    public Throwable getError() {
        return this.error;
    }

    public static ResponseMessage create(@NonNull RequestMessage requestMessage, @NonNull Throwable th) {
        return new ResponseMessage(requestMessage.getName(), requestMessage.getId(), null, th);
    }
}
