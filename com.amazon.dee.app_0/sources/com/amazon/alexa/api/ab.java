package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum ab implements AlexaMessageType {
    UNKNOWN,
    PREPROCESS,
    PROCESS,
    CANCEL,
    GET_CAPABILITIES,
    GET_DIRECTIVE_CONSTRAINTS;

    public static ab a(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}
