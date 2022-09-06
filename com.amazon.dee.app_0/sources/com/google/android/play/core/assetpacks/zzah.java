package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzah extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzc;
    final /* synthetic */ int zzd;
    final /* synthetic */ zzaw zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzah(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i, String str, com.google.android.play.core.tasks.zzi zziVar2, int i2) {
        super(zziVar);
        this.zze = zzawVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = zziVar2;
        this.zzd = i2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzz;
        Bundle zzA;
        try {
            zzasVar = this.zze.zzf;
            str = this.zze.zzc;
            zzz = zzaw.zzz(this.zza, this.zzb);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzh(str, zzz, zzA, new zzar(this.zze, this.zzc, this.zza, this.zzb, this.zzd));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "notifyModuleCompleted", new Object[0]);
        }
    }
}
