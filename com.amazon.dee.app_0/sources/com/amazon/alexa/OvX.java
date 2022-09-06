package com.amazon.alexa;

import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
/* compiled from: ClientWakeWordPrecondition.java */
/* loaded from: classes.dex */
public class OvX extends InternalWakeWordPrecondition {
    public volatile boolean zZm;

    public OvX(ExtendedClient extendedClient) {
        Preconditions.notNull(extendedClient, "client can not be null");
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        return this.zZm;
    }

    @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
    public void teardown() {
    }

    public void zZm(boolean z) {
        this.zZm = z;
        notifyStateChanged();
    }
}
