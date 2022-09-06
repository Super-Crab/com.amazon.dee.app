package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
enum bm implements AlexaMessageType {
    UNKNOWN,
    ON_SUCCESS,
    ON_FAILURE;

    public static bm a(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}
