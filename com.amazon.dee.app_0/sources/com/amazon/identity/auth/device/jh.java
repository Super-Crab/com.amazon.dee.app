package com.amazon.identity.auth.device;

import java.util.Timer;
import java.util.TimerTask;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class jh {
    private final Timer rC;
    private boolean rD;
    private boolean rE;

    public jh() {
        this(new Timer());
    }

    public synchronized void cancel() {
        this.rC.cancel();
        this.rD = true;
    }

    public synchronized void gN() {
        this.rE = true;
    }

    public synchronized boolean gO() {
        return this.rE;
    }

    public synchronized void schedule(TimerTask timerTask, long j) {
        io.i("TaskScheduler", "Schedule a delayed task");
        if (this.rD) {
            io.i("TaskScheduler", "The timer has been canceled, skip scheduling");
        } else {
            this.rC.schedule(timerTask, j);
        }
    }

    jh(Timer timer) {
        this.rC = timer;
        this.rD = false;
        this.rE = false;
    }
}
