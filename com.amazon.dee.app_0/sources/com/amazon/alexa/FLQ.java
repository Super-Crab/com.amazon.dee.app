package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
/* compiled from: WakeWordClientPresenceTracker.java */
@Singleton
/* loaded from: classes.dex */
public class FLQ extends InternalWakeWordPrecondition {
    public final Map<ExtendedClient, OvX> zZm = new HashMap();
    public final Object BIo = new Object();

    @Nullable
    public OvX BIo(ExtendedClient extendedClient) {
        OvX ovX;
        synchronized (this.BIo) {
            ovX = this.zZm.get(extendedClient);
        }
        return ovX;
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        boolean z;
        synchronized (this.BIo) {
            z = !this.zZm.isEmpty();
        }
        return z;
    }

    @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
    public void teardown() {
        synchronized (this.BIo) {
            this.zZm.clear();
        }
    }

    public OvX zQM(ExtendedClient extendedClient) {
        OvX remove;
        synchronized (this.BIo) {
            remove = this.zZm.remove(extendedClient);
        }
        if (remove != null) {
            notifyStateChanged();
        }
        return remove;
    }

    public OvX zZm(ExtendedClient extendedClient) {
        OvX ovX;
        synchronized (this.BIo) {
            ovX = new OvX(extendedClient);
            this.zZm.put(extendedClient, ovX);
        }
        notifyStateChanged();
        return ovX;
    }
}
