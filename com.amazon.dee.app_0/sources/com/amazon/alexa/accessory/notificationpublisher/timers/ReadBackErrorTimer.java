package com.amazon.alexa.accessory.notificationpublisher.timers;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
final class ReadBackErrorTimer extends BaseTimer {
    private static final String LOGTAG = "ReadBackErrorTimer";
    private static final long READ_BACK_ERROR_TIMER_DURATION = TimeUnit.SECONDS.toMillis(10);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadBackErrorTimer() {
        super(LOGTAG);
        this.durationInMillis = READ_BACK_ERROR_TIMER_DURATION;
        this.timerType = 6;
    }
}
