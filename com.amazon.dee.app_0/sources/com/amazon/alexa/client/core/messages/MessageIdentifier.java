package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import java.util.UUID;
@AutoValue
/* loaded from: classes6.dex */
public abstract class MessageIdentifier implements StronglyTypedString {
    public static MessageIdentifier create(String str) {
        return new AutoValue_MessageIdentifier(str);
    }

    public static MessageIdentifier createRandom() {
        return create(UUID.randomUUID().toString());
    }
}
