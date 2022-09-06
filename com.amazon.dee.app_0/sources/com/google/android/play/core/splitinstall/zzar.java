package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzar extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ com.google.android.play.core.tasks.zzi zza;
    final /* synthetic */ zzbc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzar(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzb = zzbcVar;
        this.zza = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            zzbc zzbcVar = this.zzb;
            str = zzbcVar.zzd;
            ((zzca) this.zzb.zza.zze()).zzi(str, new zzaz(zzbcVar, this.zza));
        } catch (RemoteException e) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e, "getSessionStates", new Object[0]);
            this.zza.zzd(new RuntimeException(e));
        }
    }
}
