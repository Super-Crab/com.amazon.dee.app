package com.amazon.alexa.accessory.notificationpublisher.timers;

import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
class ActionDelayTimer extends BaseTimer {
    private static final long ACT_DELAY_TIMER_DURATION = TimeUnit.SECONDS.toMillis(3);
    private static final long EXTENDED_ACT_DELAY_TIMER_DURATION = TimeUnit.SECONDS.toMillis(5);
    private static final String LOGTAG = "ActionDelayTimer";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionDelayTimer() {
        super(LOGTAG);
        this.durationInMillis = ACT_DELAY_TIMER_DURATION;
        this.timerType = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.accessory.notificationpublisher.timers.BaseTimer
    public void updateDefaultTimeDuration() {
        String accessoryDeviceType = AccessoryProvider.getAccessoryDeviceType();
        if (accessoryDeviceType != null && accessoryDeviceType.equalsIgnoreCase("A3HVREY4JWAZ6K")) {
            Log.d(LOGTAG, "updateTimerDuration: EXTENDED");
            this.durationInMillis = EXTENDED_ACT_DELAY_TIMER_DURATION;
            return;
        }
        Log.d(LOGTAG, "updateTimerDuration: DEFAULT");
        this.durationInMillis = ACT_DELAY_TIMER_DURATION;
    }
}
