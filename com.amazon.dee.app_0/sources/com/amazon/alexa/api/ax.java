package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum ax implements AlexaMessageType {
    UNKNOWN,
    ON_LOCALES;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ax a(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}
