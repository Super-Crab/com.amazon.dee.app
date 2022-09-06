package com.amazon.alexa.accessory.notificationpublisher.timers;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
class ActiveWindowTimer extends BaseTimer {
    private static final long ACTIVE_WINDOW_TIMER_DURATION = TimeUnit.MINUTES.toMillis(5);
    private static final String LOGTAG = "ActiveWindowTimer";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActiveWindowTimer() {
        super(LOGTAG);
        this.durationInMillis = ACTIVE_WINDOW_TIMER_DURATION;
        this.timerType = 2;
    }
}
