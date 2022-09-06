package com.amazon.deecomms.common.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public abstract class PlatformUtils {
    public static PlatformUtils getInstance() {
        if (Utils.isNougatAndAbove()) {
            return new PlatformUtils24();
        }
        int i = Build.VERSION.SDK_INT;
        if (Utils.isLollipopAndAbove()) {
            return new PlatformUtils21();
        }
        return new PlatformUtils19();
    }

    public boolean isDoNotDisturbActive(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Constants.DND_MODE, 0) != 0;
    }

    public abstract void performRingerVibration(Context context);

    public abstract void stopRingerVibration(Context context);
}
