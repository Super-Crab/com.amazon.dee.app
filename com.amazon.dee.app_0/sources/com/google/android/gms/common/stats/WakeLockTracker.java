package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.List;
@KeepForSdk
/* loaded from: classes2.dex */
public class WakeLockTracker {
    private static Boolean zzgd;
    private static WakeLockTracker zzgc = new WakeLockTracker();
    @VisibleForTesting
    private static boolean zzge = false;

    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return zzgc;
    }

    private static void zza(Context context, WakeLockEvent wakeLockEvent) {
        try {
            context.startService(new Intent().setComponent(LoggingConstants.zzfg).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", wakeLockEvent));
        } catch (Exception e) {
            Log.wtf("WakeLockTracker", e);
        }
    }

    private static boolean zzw() {
        if (zzgd == null) {
            zzgd = false;
        }
        return zzgd.booleanValue();
    }

    @KeepForSdk
    public void registerAcquireEvent(Context context, Intent intent, String str, String str2, String str3, int i, String str4) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 7, str, str2, str3, i, Arrays.asList(str4));
    }

    @KeepForSdk
    public void registerDeadlineEvent(Context context, String str, String str2, String str3, int i, List<String> list, boolean z, long j) {
        if (!zzw()) {
            return;
        }
        zza(context, new WakeLockEvent(System.currentTimeMillis(), 16, str, i, StatsUtils.zza(list), null, j, com.google.android.gms.common.util.zza.zzg(context), str2, StatsUtils.zzi(context.getPackageName()), com.google.android.gms.common.util.zza.zzh(context), 0L, str3, z));
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list) {
        registerEvent(context, str, i, str2, str3, str4, i2, list, 0L);
    }

    @KeepForSdk
    public void registerReleaseEvent(Context context, Intent intent) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 8, null, null, null, 0, null);
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list, long j) {
        if (!zzw()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            String valueOf = String.valueOf(str);
            Log.e("WakeLockTracker", valueOf.length() != 0 ? "missing wakeLock key. ".concat(valueOf) : new String("missing wakeLock key. "));
        } else if (7 != i && 8 != i && 10 != i && 11 != i) {
        } else {
            zza(context, new WakeLockEvent(System.currentTimeMillis(), i, str2, i2, StatsUtils.zza(list), str, SystemClock.elapsedRealtime(), com.google.android.gms.common.util.zza.zzg(context), str3, StatsUtils.zzi(context.getPackageName()), com.google.android.gms.common.util.zza.zzh(context), j, str4, false));
        }
    }
}
