package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hg {
    protected static final long qd = jj.d(2, TimeUnit.MILLISECONDS);
    private static hg qe;
    private c qg;
    private AtomicLong qh = new AtomicLong(0);
    private final Executor mExecutor = new ir("MAPTokenOperationThreadPool");
    private final Queue<c> qf = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public class a extends c {
        final jh qi;

        a(hg hgVar, d dVar, Callback callback) {
            this(dVar, callback, new jh());
        }

        @Override // com.amazon.identity.auth.device.hg.c
        protected void gg() {
            io.i("TokenJobQueue", String.format("Scheduled running blocking job %s.", gi()));
            this.qi.schedule(new TimerTask() { // from class: com.amazon.identity.auth.device.hg.a.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    a.this.qi.gN();
                    io.i("TokenJobQueue", "Calling scheduleNext in blocking task's scheduler");
                    hg.this.gf();
                }
            }, hg.qd);
        }

        @Override // com.amazon.identity.auth.device.hg.c
        protected void gh() {
            super.gh();
            io.i("TokenJobQueue", "Cancel time out");
            this.qi.cancel();
            if (!this.qi.gO()) {
                this.qi.gN();
                io.i("TokenJobQueue", "Calling scheduleNext in postRunning in a blocking job: " + gi());
                hg.this.gf();
            }
        }

        private a(d dVar, Callback callback, jh jhVar) {
            super(dVar, callback);
            this.qi = jhVar;
            hg.this.qh.set(new Date().getTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public class b extends c {
        b(d dVar, Callback callback) {
            super(dVar, callback);
        }

        @Override // com.amazon.identity.auth.device.hg.c
        protected void gg() {
            io.i("TokenJobQueue", String.format("Scheduled running concurrent job %s.", gi()));
            hg.this.gf();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public abstract class c {
        final Callback g;
        final d ql;

        c(d dVar, Callback callback) {
            this.g = callback;
            this.ql = dVar;
        }

        protected abstract void gg();

        protected void gh() {
            io.i("TokenJobQueue", String.format("Finish executing task %s.", gi()));
        }

        protected String gi() {
            return this.ql.gi();
        }

        protected void run() {
            io.i("TokenJobQueue", "Begin executing task " + gi());
            final Callback callback = new Callback() { // from class: com.amazon.identity.auth.device.hg.c.1
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle) {
                    io.dm("TokenJobQueue");
                    c.this.gh();
                    c.this.g.onError(bundle);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle) {
                    io.dm("TokenJobQueue");
                    c.this.gh();
                    c.this.g.onSuccess(bundle);
                }
            };
            try {
                hg.this.mExecutor.execute(new Runnable() { // from class: com.amazon.identity.auth.device.hg.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.ql.g(callback);
                        } catch (Exception e) {
                            io.e("TokenJobQueue", "MAP didn't handle exception correctly. This should never happen!", e);
                            mq.incrementCounterAndRecord(GeneratedOutlineSupport1.outline41(e, new StringBuilder("MAPTokenJobQueueUnhandledException:")), new String[0]);
                            MAPErrorCallbackHelper.onError(callback, MAPError.CommonError.INTERNAL_ERROR);
                            c.this.gh();
                        }
                    }
                });
            } finally {
                io.dm("TokenJobQueue");
                gg();
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface d {
        void g(Callback callback);

        String gi();

        boolean gj();
    }

    private hg() {
    }

    public static synchronized hg gd() {
        hg hgVar;
        synchronized (hg.class) {
            if (qe == null) {
                qe = new hg();
            }
            hgVar = qe;
        }
        return hgVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void gf() {
        io.dm("TokenJobQueue");
        this.qg = this.qf.poll();
        if (this.qg != null) {
            io.i("TokenJobQueue", String.format("Popping task %s off TokenJobQueue and process it.", this.qg.gi()));
            this.qg.run();
        }
    }

    public synchronized long ge() {
        return this.qh.get();
    }

    public synchronized void a(d dVar, Callback callback) {
        io.i("TokenJobQueue", String.format("Pushing task %s into TokenJobQueue.", dVar.gi()));
        this.qf.offer(b(dVar, callback));
        new StringBuilder("Job queue size: ").append(this.qf.size());
        io.dm("TokenJobQueue");
        if (this.qg == null) {
            io.i("TokenJobQueue", "No active job, scheduling next");
            gf();
        }
    }

    c b(d dVar, Callback callback) {
        if (dVar.gj()) {
            return new a(this, dVar, callback);
        }
        return new b(dVar, callback);
    }
}
