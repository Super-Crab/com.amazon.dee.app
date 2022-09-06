package com.amazon.alexa;

import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: DownchannelIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class tux implements StronglyTypedString {
    public static tux zZm() {
        return new Mzn(UUID.randomUUID().toString());
    }

    public String toString() {
        return getValue();
    }
}
