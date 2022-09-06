package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.concurrent.ExecutorService;
/* compiled from: InteractionLifecycleFactory.java */
/* loaded from: classes.dex */
public class YnK {
    public final ExecutorService zZm = ManagedExecutorFactory.newSingleThreadExecutor("interaction-life-cycle-factory");

    public tYL zZm(jDH jdh, PJz pJz, DialogRequestIdentifier dialogRequestIdentifier, ZIZ ziz) {
        return new tYL(jdh, pJz, dialogRequestIdentifier, this.zZm, ziz);
    }

    public AOq zZm(AbstractC0239xsr abstractC0239xsr) {
        return new AOq(abstractC0239xsr, this.zZm);
    }
}
