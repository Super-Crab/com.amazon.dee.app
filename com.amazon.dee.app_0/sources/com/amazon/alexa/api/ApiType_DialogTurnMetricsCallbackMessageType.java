package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
enum ApiType_DialogTurnMetricsCallbackMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_USER_PERCEIVED_LATENCY_DATA_COM_AMAZON_ALEXA_API_USER_PERCEIVED_LATENCY_DATA;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiType_DialogTurnMetricsCallbackMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}
