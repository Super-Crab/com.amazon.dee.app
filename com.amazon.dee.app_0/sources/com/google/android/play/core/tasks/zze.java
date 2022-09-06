package com.google.android.play.core.tasks;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zze implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zzf zzfVar, Task task) {
        this.zzb = zzfVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnSuccessListener onSuccessListener;
        OnSuccessListener onSuccessListener2;
        obj = this.zzb.zzb;
        synchronized (obj) {
            zzf zzfVar = this.zzb;
            onSuccessListener = zzfVar.zzc;
            if (onSuccessListener != null) {
                onSuccessListener2 = zzfVar.zzc;
                onSuccessListener2.onSuccess(this.zza.getResult());
            }
        }
    }
}
