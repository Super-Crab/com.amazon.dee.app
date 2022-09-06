package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.google.auto.value.AutoValue;
/* compiled from: RequestProcessingCompletedEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class BQL extends Kqq.zZm {
    public static BQL zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        return new ISm(dialogRequestIdentifier);
    }
}
