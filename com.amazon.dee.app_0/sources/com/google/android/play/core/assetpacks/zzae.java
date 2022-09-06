package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzae extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ Map zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzae(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, Map map, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzawVar;
        this.zza = map;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        try {
            zzasVar = this.zzc.zzf;
            str = this.zzc.zzc;
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zze(str, zzaw.zzn(this.zza), new zzao(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "syncPacks", new Object[0]);
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
