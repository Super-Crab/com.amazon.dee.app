package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SendMessageTaskContainer.java */
@Singleton
/* loaded from: classes.dex */
public class lSb {
    public final Map<eOP, RrI> BIo;
    public final ScheduledExecutorService jiA;
    public final Queue<RrI> zQM;
    public final Map<RrI, JjI> zZm;
    public final Map<JjI, ScheduledFuture<?>> zyO;

    @Inject
    public lSb() {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor("send-message-timeout-scheduler");
        this.zZm = new HashMap();
        this.BIo = new HashMap();
        this.zQM = new LinkedList();
        this.zyO = new HashMap();
        this.jiA = newSingleThreadScheduledExecutor;
    }

    public synchronized mDr BIo() {
        return zZm(this.zQM.remove());
    }

    public synchronized void zZm(JjI jjI, RrI rrI, Runnable runnable, long j, TimeUnit timeUnit) {
        this.zQM.add(rrI);
        this.zZm.put(rrI, jjI);
        if (jjI.zQM()) {
            this.BIo.put(((WXj) jjI).zzR, rrI);
        }
        this.zyO.put(jjI, this.jiA.schedule(new Xvx(this, rrI, runnable), j, timeUnit));
    }

    @Nullable
    public synchronized mDr zZm(eOP eop) {
        RrI remove = this.BIo.remove(eop);
        if (remove != null) {
            this.zQM.remove(remove);
            return zZm(remove);
        }
        return null;
    }

    @Nullable
    public synchronized mDr zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        RrI zZm = RrI.zZm(dialogRequestIdentifier);
        if (this.zQM.remove(zZm)) {
            return zZm(zZm);
        }
        return null;
    }

    public final mDr zZm(RrI rrI) {
        JjI remove = this.zZm.remove(rrI);
        if (remove != null) {
            if (remove.zQM()) {
                this.BIo.remove(((WXj) remove).zzR);
            }
            ScheduledFuture<?> remove2 = this.zyO.remove(remove);
            if (remove2 != null && !remove2.isDone()) {
                remove2.cancel(true);
            }
            return new C0195dTB(rrI, remove);
        }
        throw new IllegalStateException("SendMessageEvent is null for request id: " + rrI);
    }

    public synchronized boolean zZm() {
        return this.zQM.isEmpty();
    }
}
