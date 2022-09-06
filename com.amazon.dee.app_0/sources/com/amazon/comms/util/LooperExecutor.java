package com.amazon.comms.util;

import android.os.Handler;
import android.os.Looper;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes12.dex */
public class LooperExecutor extends Thread implements Executor {
    private static final CommsLogger log = CommsLogger.getLogger(LooperExecutor.class);
    private Handler handler;
    private final Object looperStartedEvent;
    private boolean running;
    private final List<Runnable> scheduledPeriodicRunnables;
    private long threadId;

    public LooperExecutor(String str) {
        super(str);
        this.looperStartedEvent = new Object();
        this.scheduledPeriodicRunnables = new LinkedList();
        this.handler = null;
        this.running = false;
    }

    public synchronized void cancel(Runnable runnable) {
        if (!this.running) {
            log.w("Running looper executor without calling requestStart()");
        } else {
            this.handler.removeCallbacks(runnable);
        }
    }

    public synchronized void cancelScheduledTasks() {
        if (!this.running) {
            log.w("Trying to cancel schedule tasks for non running executor");
            return;
        }
        for (Runnable runnable : this.scheduledPeriodicRunnables) {
            this.handler.removeCallbacks(runnable);
        }
        this.scheduledPeriodicRunnables.clear();
    }

    public boolean checkOnLooperThread() {
        return Thread.currentThread().getId() == this.threadId;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        synchronized (this) {
            if (!this.running) {
                String name = runnable.getClass().getName();
                CommsLogger commsLogger = log;
                commsLogger.w("Running looper executor without calling requestStart(), class:" + name);
                return;
            }
            boolean z = Thread.currentThread().getId() == this.threadId;
            if (z) {
                runnable.run();
                return;
            }
            synchronized (this) {
                this.handler.post(runnable);
            }
        }
    }

    public synchronized void post(Runnable runnable) {
        if (!this.running) {
            String name = runnable.getClass().getName();
            CommsLogger commsLogger = log;
            commsLogger.w("Running looper postDelayed without calling requestStart(), class:" + name);
            return;
        }
        this.handler.post(runnable);
    }

    public synchronized void postDelayedRunnable(Runnable runnable, long j) {
        if (!this.running) {
            log.w("Trying to post delayed task on non running executor");
        } else {
            this.handler.postDelayed(runnable, j);
        }
    }

    public synchronized void requestStart() {
        if (this.running) {
            return;
        }
        this.running = true;
        this.handler = null;
        start();
        synchronized (this.looperStartedEvent) {
            while (this.handler == null) {
                try {
                    this.looperStartedEvent.wait();
                } catch (InterruptedException unused) {
                    log.e("Can not start looper thread");
                    this.running = false;
                }
            }
        }
    }

    public synchronized void requestStop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        this.handler.post(new Runnable() { // from class: com.amazon.comms.util.LooperExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.myLooper().quitSafely();
                CommsLogger commsLogger = LooperExecutor.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Looper thread finished, threadname=");
                outline107.append(LooperExecutor.this.getName());
                commsLogger.d(outline107.toString());
            }
        });
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        synchronized (this.looperStartedEvent) {
            CommsLogger commsLogger = log;
            commsLogger.d("Looper thread started, threadname=" + getName());
            this.handler = new Handler();
            this.threadId = Thread.currentThread().getId();
            this.looperStartedEvent.notify();
        }
        Looper.loop();
    }

    public synchronized void scheduleAtFixedRate(final Runnable runnable, final long j) {
        if (!this.running) {
            log.w("Trying to schedule task for non running executor");
            return;
        }
        Runnable runnable2 = new Runnable() { // from class: com.amazon.comms.util.LooperExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                if (LooperExecutor.this.running) {
                    runnable.run();
                    if (LooperExecutor.this.handler.postDelayed(this, j)) {
                        return;
                    }
                    LooperExecutor.log.e("Failed to post a delayed runnable in the chain.");
                }
            }
        };
        this.scheduledPeriodicRunnables.add(runnable2);
        if (!this.handler.postDelayed(runnable2, j)) {
            log.e("Failed to post a delayed runnable.");
        }
    }

    public void execute(final String str, final Runnable runnable) {
        final long currentTimeMillis = System.currentTimeMillis();
        CommsLogger commsLogger = log;
        commsLogger.i("posting " + str);
        execute(new Runnable() { // from class: com.amazon.comms.util.LooperExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis2 = System.currentTimeMillis();
                CommsLogger commsLogger2 = LooperExecutor.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("executing ");
                outline107.append(str);
                outline107.append(" after ");
                outline107.append(currentTimeMillis2 - currentTimeMillis);
                outline107.append("ms");
                commsLogger2.i(outline107.toString());
                runnable.run();
                CommsLogger commsLogger3 = LooperExecutor.log;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("finished ");
                outline1072.append(str);
                outline1072.append(" took ");
                outline1072.append(System.currentTimeMillis() - currentTimeMillis2);
                outline1072.append("ms, executed after ");
                outline1072.append(currentTimeMillis2 - currentTimeMillis);
                outline1072.append("ms wait");
                commsLogger3.i(outline1072.toString());
            }
        });
    }
}
