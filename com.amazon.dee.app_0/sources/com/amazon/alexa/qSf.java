package com.amazon.alexa;

import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: DialogIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class qSf implements StronglyTypedString {
    static {
        zZm("UNKNOWN");
    }

    public static qSf zZm() {
        return zZm(UUID.randomUUID().toString());
    }

    public static qSf zZm(String str) {
        return new ljz(str);
    }
}
