package com.google.common.util.concurrent;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes3.dex */
public final class ListenerCallQueue<L> {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface Event<L> {
        void call(L l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        @GuardedBy("this")
        boolean isThreadScheduled;
        final L listener;
        @GuardedBy("this")
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
        @GuardedBy("this")
        final Queue<Object> labelQueue = Queues.newArrayDeque();

        PerListenerQueue(L l, Executor executor) {
            this.listener = (L) Preconditions.checkNotNull(l);
            this.executor = (Executor) Preconditions.checkNotNull(executor);
        }

        synchronized void add(Event<L> event, Object obj) {
            this.waitQueue.add(event);
            this.labelQueue.add(obj);
        }

        void dispatch() {
            boolean z;
            synchronized (this) {
                z = true;
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        Logger logger = ListenerCallQueue.logger;
                        Level level = Level.SEVERE;
                        String valueOf = String.valueOf(this.listener);
                        String valueOf2 = String.valueOf(this.executor);
                        logger.log(level, GeneratedOutlineSupport1.outline31(valueOf2.length() + valueOf.length() + 42, "Exception while running callbacks for ", valueOf, " on ", valueOf2), (Throwable) e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
            r2.call(r9.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
            r4 = com.google.common.util.concurrent.ListenerCallQueue.logger;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r9.listener);
            r3 = java.lang.String.valueOf(r3);
            r8 = new java.lang.StringBuilder((r6.length() + 37) + r3.length());
            r8.append("Exception while executing callback: ");
            r8.append(r6);
            r8.append(" ");
            r8.append(r3);
            r4.log(r5, r8.toString(), (java.lang.Throwable) r2);
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r9 = this;
            L0:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch: java.lang.Throwable -> L63
                boolean r2 = r9.isThreadScheduled     // Catch: java.lang.Throwable -> L60
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L60
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch: java.lang.Throwable -> L60
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L60
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L60
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch: java.lang.Throwable -> L60
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L60
                if (r2 != 0) goto L20
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L60
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L1c
                return
            L1c:
                r1 = move-exception
                r2 = r1
                r1 = r0
                goto L61
            L20:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
                L r4 = r9.listener     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L63
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L63
                goto L0
            L27:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.access$000()     // Catch: java.lang.Throwable -> L63
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L63
                L r6 = r9.listener     // Catch: java.lang.Throwable -> L63
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L63
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L63
                int r7 = r6.length()     // Catch: java.lang.Throwable -> L63
                int r7 = r7 + 37
                int r8 = r3.length()     // Catch: java.lang.Throwable -> L63
                int r7 = r7 + r8
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L63
                java.lang.String r7 = "Exception while executing callback: "
                r8.append(r7)     // Catch: java.lang.Throwable -> L63
                r8.append(r6)     // Catch: java.lang.Throwable -> L63
                java.lang.String r6 = " "
                r8.append(r6)     // Catch: java.lang.Throwable -> L63
                r8.append(r3)     // Catch: java.lang.Throwable -> L63
                java.lang.String r3 = r8.toString()     // Catch: java.lang.Throwable -> L63
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L63
                goto L0
            L60:
                r2 = move-exception
            L61:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
                throw r2     // Catch: java.lang.Throwable -> L63
            L63:
                r2 = move-exception
                if (r1 == 0) goto L6e
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L6b
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L6b
                goto L6e
            L6b:
                r0 = move-exception
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L6b
                throw r0
            L6e:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }

    private void enqueueHelper(Event<L> event, Object obj) {
        Preconditions.checkNotNull(event, "event");
        Preconditions.checkNotNull(obj, "label");
        synchronized (this.listeners) {
            for (PerListenerQueue<L> perListenerQueue : this.listeners) {
                perListenerQueue.add(event, obj);
            }
        }
    }

    public void addListener(L l, Executor executor) {
        Preconditions.checkNotNull(l, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(l, executor));
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String str) {
        enqueueHelper(event, str);
    }
}
