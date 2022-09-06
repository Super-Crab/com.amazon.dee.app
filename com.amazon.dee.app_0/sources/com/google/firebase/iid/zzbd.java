package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
final class zzbd {
    final Intent zza;
    private final BroadcastReceiver.PendingResult zzb;
    private boolean zzc = false;
    private final ScheduledFuture<?> zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd(final Intent intent, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.zza = intent;
        this.zzb = pendingResult;
        this.zzd = scheduledExecutorService.schedule(new Runnable(this, intent) { // from class: com.google.firebase.iid.zzbc
            private final zzbd zza;
            private final Intent zzb;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = intent;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzbd zzbdVar = this.zza;
                String action = this.zzb.getAction();
                StringBuilder sb = new StringBuilder(GeneratedOutlineSupport1.outline6(action, 61));
                sb.append("Service took too long to process intent: ");
                sb.append(action);
                sb.append(" App may get closed.");
                Log.w("FirebaseInstanceId", sb.toString());
                zzbdVar.zza();
            }
        }, 9000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza() {
        if (!this.zzc) {
            this.zzb.finish();
            this.zzd.cancel(false);
            this.zzc = true;
        }
    }
}
