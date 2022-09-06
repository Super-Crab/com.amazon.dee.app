package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzak extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ com.google.android.play.core.tasks.zzi zza;
    final /* synthetic */ zzaw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzak(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzb = zzawVar;
        this.zza = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        try {
            zzasVar = this.zzb.zzg;
            str = this.zzb.zzc;
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzf(str, zzA, new zzap(this.zzb, this.zza));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "keepAlive", new Object[0]);
        }
    }
}
