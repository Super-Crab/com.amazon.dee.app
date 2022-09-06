package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzv {
    @GuardedBy("MessengerIpcClient.class")
    private static zzv zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    @GuardedBy("this")
    private zzw zzd = new zzw(this);
    @GuardedBy("this")
    private int zze = 1;

    @VisibleForTesting
    private zzv(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzc = scheduledExecutorService;
        this.zzb = context.getApplicationContext();
    }

    public static synchronized zzv zza(Context context) {
        zzv zzvVar;
        synchronized (zzv.class) {
            if (zza == null) {
                zza = new zzv(context, com.google.android.gms.internal.firebase_messaging.zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), com.google.android.gms.internal.firebase_messaging.zzf.zza));
            }
            zzvVar = zza;
        }
        return zzvVar;
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzaj(zza(), 1, bundle));
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzae(zza(), 2, bundle));
    }

    private final synchronized <T> Task<T> zza(zzah<T> zzahVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzahVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            sb.toString();
        }
        if (!this.zzd.zza((zzah<?>) zzahVar)) {
            this.zzd = new zzw(this);
            this.zzd.zza((zzah<?>) zzahVar);
        }
        return zzahVar.zzb.getTask();
    }

    private final synchronized int zza() {
        int i;
        i = this.zze;
        this.zze = i + 1;
        return i;
    }
}
