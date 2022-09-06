package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum ApiType_CaptionListenerMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_RECEIVED_CAPTION_COM_AMAZON_ALEXA_API_CAPTION_RESPONSE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiType_CaptionListenerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}
