package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class RawStringPayload implements Payload, ComponentStatePayload {
    public static RawStringPayload create(String str) {
        return new AutoValue_RawStringPayload(str);
    }

    public abstract String getValue();
}
