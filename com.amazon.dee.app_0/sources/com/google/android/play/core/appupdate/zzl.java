package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzah;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzl extends zzah {
    final /* synthetic */ String zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzq zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzq zzqVar, com.google.android.play.core.tasks.zzi zziVar, String str, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzqVar;
        this.zza = str;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        zzag zzagVar;
        String str;
        try {
            zzq zzqVar = this.zzc;
            str = zzqVar.zzd;
            ((com.google.android.play.core.internal.zzp) this.zzc.zza.zze()).zzd(str, zzq.zzb(zzqVar, this.zza), new zzp(this.zzc, this.zzb, this.zza));
        } catch (RemoteException e) {
            zzagVar = zzq.zzb;
            zzagVar.zzc(e, "requestUpdateInfo(%s)", this.zza);
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
