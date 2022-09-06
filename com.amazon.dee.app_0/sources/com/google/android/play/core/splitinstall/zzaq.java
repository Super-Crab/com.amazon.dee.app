package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzaq extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaq(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, int i, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzbcVar;
        this.zza = i;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            zzbc zzbcVar = this.zzc;
            str = zzbcVar.zzd;
            ((zzca) this.zzc.zza.zze()).zzh(str, this.zza, new zzay(zzbcVar, this.zzb));
        } catch (RemoteException e) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e, "getSessionState(%d)", Integer.valueOf(this.zza));
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
