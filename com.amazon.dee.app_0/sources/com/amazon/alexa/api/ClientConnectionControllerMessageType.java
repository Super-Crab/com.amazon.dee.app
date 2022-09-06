package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum ClientConnectionControllerMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_FORCE_DISCONNECT,
    ON_START_SERVICE;

    public static ClientConnectionControllerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}
