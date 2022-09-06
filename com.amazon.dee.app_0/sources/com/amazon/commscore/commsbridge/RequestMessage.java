package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.commsbridge.BridgeMessage;
/* loaded from: classes12.dex */
class RequestMessage extends BridgeMessage {
    private RequestMessage(String str, String str2, Object obj) {
        super(str, str2, BridgeMessage.MessageType.REQUEST, obj);
    }

    public static RequestMessage create(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        return new RequestMessage(str, str2, obj);
    }
}
