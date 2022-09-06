package com.amazon.deecomms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Vibrator;
import com.amazon.deecomms.common.Constants;
@TargetApi(24)
/* loaded from: classes12.dex */
class PlatformUtils24 extends PlatformUtils23 {
    @Override // com.amazon.deecomms.common.util.PlatformUtils23, com.amazon.deecomms.common.util.PlatformUtils21, com.amazon.deecomms.common.util.PlatformUtils19, com.amazon.deecomms.common.util.PlatformUtils
    public void performRingerVibration(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (!isDoNotDisturbActive(context)) {
            vibrator.vibrate(Constants.CALL_VIBRATION_PATTERN, 0, new AudioAttributes.Builder().setUsage(6).setContentType(4).build());
        }
    }
}
