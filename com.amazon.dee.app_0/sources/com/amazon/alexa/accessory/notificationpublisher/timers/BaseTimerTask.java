package com.amazon.alexa.accessory.notificationpublisher.timers;

import androidx.annotation.NonNull;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class BaseTimerTask extends TimerTask {
    private BaseTimer timer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseTimerTask(@NonNull BaseTimer baseTimer) {
        this.timer = baseTimer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.timer.timerExpired();
    }
}
