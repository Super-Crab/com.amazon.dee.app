package com.amazon.alexa.accessory.notificationpublisher.timers;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
class ReplayTimer extends BaseTimer {
    private static final String LOGTAG = "ReplayTimer";
    private static final long REPLAY_TIMER_DURATION = TimeUnit.HOURS.toMillis(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplayTimer() {
        super(LOGTAG);
        this.durationInMillis = REPLAY_TIMER_DURATION;
        this.timerType = 3;
    }
}
