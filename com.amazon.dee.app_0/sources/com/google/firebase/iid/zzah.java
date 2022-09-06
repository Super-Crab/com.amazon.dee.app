package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
abstract class zzah<T> {
    final int zza;
    final TaskCompletionSource<T> zzb = new TaskCompletionSource<>();
    final int zzc;
    final Bundle zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(int i, int i2, Bundle bundle) {
        this.zza = i;
        this.zzc = i2;
        this.zzd = bundle;
    }

    public String toString() {
        int i = this.zzc;
        int i2 = this.zza;
        boolean zza = zza();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=");
        sb.append(zza);
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(valueOf2.length() + valueOf.length() + 16);
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            sb.toString();
        }
        this.zzb.setResult(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzag zzagVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzagVar);
            StringBuilder sb = new StringBuilder(valueOf2.length() + valueOf.length() + 14);
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            sb.toString();
        }
        this.zzb.setException(zzagVar);
    }
}
