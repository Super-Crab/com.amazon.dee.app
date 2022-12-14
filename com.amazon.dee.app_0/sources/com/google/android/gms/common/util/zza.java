package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.amazon.alexa.accessorykit.ModelTransformer;
/* loaded from: classes2.dex */
public final class zza {
    private static long zzgv;
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static float zzgw = Float.NaN;

    @TargetApi(20)
    public static int zzg(Context context) {
        boolean isScreenOn;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
        int i = 0;
        int i2 = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        if (isScreenOn) {
            i = 2;
        }
        return i | i2;
    }

    public static synchronized float zzh(Context context) {
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzgv < 60000 && !Float.isNaN(zzgw)) {
                return zzgw;
            }
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
            if (registerReceiver != null) {
                zzgw = registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_LEVEL, -1) / registerReceiver.getIntExtra(ModelTransformer.KEY_BATTERY_SCALE, -1);
            }
            zzgv = SystemClock.elapsedRealtime();
            return zzgw;
        }
    }
}
