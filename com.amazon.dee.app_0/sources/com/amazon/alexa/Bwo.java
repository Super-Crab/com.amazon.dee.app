package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.utils.validation.Preconditions;
/* compiled from: BaseComponentStateProvider.java */
/* loaded from: classes.dex */
public abstract class Bwo implements dRG {
    public final ComponentStateHeader zZm;

    public Bwo(Namespace namespace, Name name) {
        Preconditions.notNull(namespace, "namespace is null");
        Preconditions.notNull(name, "name is null");
        this.zZm = ComponentStateHeader.zZm(namespace, name);
    }

    @Override // com.amazon.alexa.dRG
    public ComponentStateHeader zZm() {
        return this.zZm;
    }
}
