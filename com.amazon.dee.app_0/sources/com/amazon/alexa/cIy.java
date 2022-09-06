package com.amazon.alexa;

import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: AttachmentIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class cIy implements StronglyTypedString {
    public static cIy zZm() {
        return zZm(UUID.randomUUID().toString());
    }

    public static cIy zZm(String str) {
        return new ZAO(str);
    }
}
