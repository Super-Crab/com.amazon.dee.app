package com.amazon.alexa.accessory.notificationpublisher.timers;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
final class ReadBackActDelayTimer extends BaseTimer {
    private static final String LOGTAG = "ReadBackActDelayTimer";
    private static final long READ_BACK_ACT_DELAY_TIMER_DURATION = TimeUnit.SECONDS.toMillis(3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadBackActDelayTimer() {
        super(LOGTAG);
        this.durationInMillis = READ_BACK_ACT_DELAY_TIMER_DURATION;
        this.timerType = 5;
    }
}
