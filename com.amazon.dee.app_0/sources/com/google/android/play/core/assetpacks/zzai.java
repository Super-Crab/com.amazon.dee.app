package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzai extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzai(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzawVar;
        this.zza = i;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzB;
        Bundle zzA;
        try {
            zzasVar = this.zzc.zzf;
            str = this.zzc.zzc;
            zzB = zzaw.zzB(this.zza);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzi(str, zzB, zzA, new zzas(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "notifySessionFailed", new Object[0]);
        }
    }
}
