package com.amazon.deecomms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import com.amazon.deecomms.common.Constants;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(19)
/* loaded from: classes12.dex */
public class PlatformUtils19 extends PlatformUtils {
    @Override // com.amazon.deecomms.common.util.PlatformUtils
    public void performRingerVibration(Context context) {
        if (((AudioManager) context.getSystemService("audio")).getRingerMode() == 1) {
            ((Vibrator) context.getSystemService("vibrator")).vibrate(Constants.CALL_VIBRATION_PATTERN, 0);
        }
    }

    @Override // com.amazon.deecomms.common.util.PlatformUtils
    public void stopRingerVibration(Context context) {
        ((Vibrator) context.getSystemService("vibrator")).cancel();
    }
}
