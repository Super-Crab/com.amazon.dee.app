package com.amazon.deecomms.common.util;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.provider.Settings;
import com.amazon.deecomms.common.Constants;
@TargetApi(23)
/* loaded from: classes12.dex */
class PlatformUtils23 extends PlatformUtils21 {
    private static final int VIBRATE_WHEN_RINGING = 1;

    @Override // com.amazon.deecomms.common.util.PlatformUtils
    public boolean isDoNotDisturbActive(Context context) {
        int currentInterruptionFilter = ((NotificationManager) context.getSystemService("notification")).getCurrentInterruptionFilter();
        return (currentInterruptionFilter == 1 || currentInterruptionFilter == 0) ? false : true;
    }

    @Override // com.amazon.deecomms.common.util.PlatformUtils21, com.amazon.deecomms.common.util.PlatformUtils19, com.amazon.deecomms.common.util.PlatformUtils
    public void performRingerVibration(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "vibrate_when_ringing", 0);
        if (((AudioManager) context.getSystemService("audio")).getRingerMode() == 1 || (i == 1 && !isDoNotDisturbActive(context))) {
            ((Vibrator) context.getSystemService("vibrator")).vibrate(Constants.CALL_VIBRATION_PATTERN, 0);
        }
    }
}
