package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.amazon.device.messaging.ADMConstants;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.concurrent.GuardedBy;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzba zza;

    private final void zza(Context context, Intent intent) {
        int zza2;
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        int i = Build.VERSION.SDK_INT;
        if ("google.com/iid".equals(intent.getStringExtra(ADMConstants.EXTRA_FROM))) {
            String stringExtra = intent.getStringExtra("CMD");
            if (stringExtra != null) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(intent.getExtras());
                    StringBuilder sb = new StringBuilder(valueOf.length() + stringExtra.length() + 21);
                    sb.append("Received command: ");
                    sb.append(stringExtra);
                    sb.append(" - ");
                    sb.append(valueOf);
                    sb.toString();
                }
                if (!"RST".equals(stringExtra) && !"RST_FULL".equals(stringExtra)) {
                    if ("SYNC".equals(stringExtra)) {
                        FirebaseInstanceId.getInstance().zzg();
                    }
                } else {
                    FirebaseInstanceId.getInstance().zze();
                }
            }
            zza2 = -1;
        } else {
            String stringExtra2 = intent.getStringExtra("gcm.rawData64");
            if (stringExtra2 != null) {
                intent.putExtra("rawData", Base64.decode(stringExtra2, 0));
                intent.removeExtra("gcm.rawData64");
            }
            zza2 = zza(this, context, intent);
        }
        if (isOrderedBroadcast()) {
            setResultCode(zza2);
        }
    }

    private static int zzb(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
        Log.isLoggable("FirebaseInstanceId", 3);
        if (broadcastReceiver.isOrderedBroadcast()) {
            broadcastReceiver.setResultCode(-1);
        }
        zza(context, "com.google.firebase.MESSAGING_EVENT").zza(intent, broadcastReceiver.goAsync());
        return -1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (intent == null) {
            return;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
        Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
        if (intent2 != null) {
            zza(context, intent2);
        } else {
            zza(context, intent);
        }
    }

    @ShowFirstParty
    @SuppressLint({"InlinedApi"})
    public static int zza(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
        boolean z = true;
        boolean z2 = PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) == 0) {
            z = false;
        }
        if (z2 && !z) {
            return zzb(broadcastReceiver, context, intent);
        }
        int zza2 = zzaq.zza().zza(context, intent);
        if (!PlatformVersion.isAtLeastO() || zza2 != 402) {
            return zza2;
        }
        zzb(broadcastReceiver, context, intent);
        return 403;
    }

    private static synchronized zzba zza(Context context, String str) {
        zzba zzbaVar;
        synchronized (FirebaseInstanceIdReceiver.class) {
            if (zza == null) {
                zza = new zzba(context, str);
            }
            zzbaVar = zza;
        }
        return zzbaVar;
    }
}
