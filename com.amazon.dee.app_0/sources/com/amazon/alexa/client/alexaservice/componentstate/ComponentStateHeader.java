package com.amazon.alexa.client.alexaservice.componentstate;

import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ComponentStateHeader {
    public static ComponentStateHeader zZm(Namespace namespace, Name name) {
        return new AutoValue_ComponentStateHeader(namespace, name);
    }

    public abstract Namespace BIo();

    public abstract Name zZm();
}
