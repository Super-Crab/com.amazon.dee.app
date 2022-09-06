package com.amazon.alexa.accessory.notificationpublisher.timers;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
final class InvitationActionDelayTimer extends BaseTimer {
    private static final long ACT_DELAY_TIMER_DURATION = TimeUnit.SECONDS.toMillis(5);
    private static final String LOGTAG = "InvitationActionDelayTimer";

    /* JADX INFO: Access modifiers changed from: package-private */
    public InvitationActionDelayTimer() {
        super(LOGTAG);
        this.durationInMillis = ACT_DELAY_TIMER_DURATION;
        this.timerType = 4;
    }
}
