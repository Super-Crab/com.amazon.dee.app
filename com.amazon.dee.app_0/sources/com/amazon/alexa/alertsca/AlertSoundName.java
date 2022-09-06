package com.amazon.alexa.alertsca;

import androidx.annotation.RawRes;
/* loaded from: classes6.dex */
enum AlertSoundName {
    ALARM_FOREGROUND_SOUND(R.raw.avs_med_system_alerts_melodic_01),
    ALARM_BACKGROUND_SOUND(R.raw.avs_med_system_alerts_melodic_01_short),
    TIMER_FOREGROUND_SOUND(R.raw.avs_med_system_alerts_melodic_02),
    TIMER_BACKGROUND_SOUND(R.raw.avs_med_system_alerts_melodic_02_short),
    REMINDER_FOREGROUND_SOUND(R.raw.avs_default_reminder),
    REMINDER_BACKGROUND_SOUND(R.raw.avs_default_reminder);
    
    private int resId;

    AlertSoundName(@RawRes int i) {
        this.resId = i;
    }

    @RawRes
    public int getResourceId() {
        return this.resId;
    }
}
