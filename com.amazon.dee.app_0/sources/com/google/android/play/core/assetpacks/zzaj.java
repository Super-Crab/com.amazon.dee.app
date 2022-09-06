package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzaj extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ int zzd;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zze;
    final /* synthetic */ zzaw zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i, String str, String str2, int i2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzf = zzawVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = i2;
        this.zze = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        try {
            zzasVar = this.zzf.zzf;
            str = this.zzf.zzc;
            Bundle zzk = zzaw.zzk(this.zza, this.zzb, this.zzc, this.zzd);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzd(str, zzk, zzA, new zzan(this.zzf, this.zze));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzb("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.zzb, this.zzc, Integer.valueOf(this.zzd), Integer.valueOf(this.zza));
            this.zze.zzd(new RuntimeException(e));
        }
    }
}
