package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.ExtendedClient;
import com.google.auto.value.AutoValue;
/* compiled from: ClientDisconnectedEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class xZV extends Kqq.zZm {
    public static xZV zZm(@Nullable ExtendedClient extendedClient) {
        return new uyC(extendedClient, false);
    }
}
