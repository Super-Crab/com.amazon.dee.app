package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.commsbridge.BridgeMessage;
/* loaded from: classes12.dex */
class EventMessage extends BridgeMessage {
    EventMessage(String str, String str2, Object obj) {
        super(str, str2, BridgeMessage.MessageType.EVENT, obj);
    }

    public static EventMessage create(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        return new EventMessage(str, str2, obj);
    }
}
