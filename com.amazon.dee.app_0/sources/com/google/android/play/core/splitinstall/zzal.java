package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.ArrayList;
import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzal extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ Collection zza;
    final /* synthetic */ Collection zzb;
    final /* synthetic */ com.google.android.play.core.tasks.zzi zzc;
    final /* synthetic */ zzbc zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzal(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, Collection collection, Collection collection2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.zzd = zzbcVar;
        this.zza = collection;
        this.zzb = collection2;
        this.zzc = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        ArrayList zzm = zzbc.zzm(this.zza);
        zzm.addAll(zzbc.zzl(this.zzb));
        try {
            str = this.zzd.zzd;
            ((zzca) this.zzd.zza.zze()).zzj(str, zzm, zzbc.zza(), new zzba(this.zzd, this.zzc));
        } catch (RemoteException e) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e, "startInstall(%s,%s)", this.zza, this.zzb);
            this.zzc.zzd(new RuntimeException(e));
        }
    }
}
