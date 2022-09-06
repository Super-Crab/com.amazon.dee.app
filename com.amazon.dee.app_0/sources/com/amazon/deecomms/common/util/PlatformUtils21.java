package com.amazon.deecomms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import com.amazon.deecomms.common.Constants;
@TargetApi(21)
/* loaded from: classes12.dex */
class PlatformUtils21 extends PlatformUtils19 {
    @Override // com.amazon.deecomms.common.util.PlatformUtils19, com.amazon.deecomms.common.util.PlatformUtils
    public void performRingerVibration(Context context) {
        if (((AudioManager) context.getSystemService("audio")).getRingerMode() != 1 || isDoNotDisturbActive(context)) {
            return;
        }
        ((Vibrator) context.getSystemService("vibrator")).vibrate(Constants.CALL_VIBRATION_PATTERN, 0);
    }
}
