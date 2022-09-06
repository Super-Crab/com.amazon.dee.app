package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzad extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ List zza;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzad(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzc = zzawVar;
        this.zza = list;
        this.zzb = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        ArrayList zzv = zzaw.zzv(this.zza);
        try {
            zzasVar = this.zzc.zzf;
            str = this.zzc.zzc;
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzc(str, zzv, zzA, new zzam(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e, "cancelDownloads(%s)", this.zza);
        }
    }
}
