package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
@VisibleForTesting
/* loaded from: classes3.dex */
final class zzau extends BroadcastReceiver {
    @Nullable
    private zzav zza;

    public zzau(zzav zzavVar) {
        this.zza = zzavVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        zzav zzavVar = this.zza;
        if (zzavVar != null && zzavVar.zzb()) {
            FirebaseInstanceId.zzd();
            FirebaseInstanceId.zza(this.zza, 0L);
            this.zza.zza().unregisterReceiver(this);
            this.zza = null;
        }
    }

    public final void zza() {
        FirebaseInstanceId.zzd();
        this.zza.zza().registerReceiver(this, new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION));
    }
}
