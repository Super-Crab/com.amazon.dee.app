package com.google.android.play.core.tasks;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, Task task) {
        this.zzb = zzdVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnFailureListener onFailureListener;
        OnFailureListener onFailureListener2;
        obj = this.zzb.zzb;
        synchronized (obj) {
            zzd zzdVar = this.zzb;
            onFailureListener = zzdVar.zzc;
            if (onFailureListener != null) {
                onFailureListener2 = zzdVar.zzc;
                onFailureListener2.onFailure(this.zza.getException());
            }
        }
    }
}
