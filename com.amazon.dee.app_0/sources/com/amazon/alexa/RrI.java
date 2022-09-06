package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: RequestIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class RrI {
    public static RrI zZm() {
        return new Fkl(UUID.randomUUID().toString(), null);
    }

    public boolean BIo() {
        return ((Fkl) this).BIo != null;
    }

    public static RrI zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        return new Fkl(dialogRequestIdentifier.getValue(), dialogRequestIdentifier);
    }
}
