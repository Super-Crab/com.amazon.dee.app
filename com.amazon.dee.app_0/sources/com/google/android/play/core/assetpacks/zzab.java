package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzab extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ String zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzab(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, String str, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzawVar;
        this.zza = str;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzz;
        Bundle zzA;
        try {
            zzasVar = this.zzc.zzf;
            str = this.zzc.zzc;
            zzz = zzaw.zzz(0, this.zza);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzj(str, zzz, zzA, new zzat(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "removePack(%s)", this.zza);
        }
    }
}
