package com.google.android.play.core.assetpacks;

import android.os.Bundle;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzar extends zzal {
    final int zzc;
    final String zzd;
    final int zze;
    final /* synthetic */ zzaw zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzar(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i, String str, int i2) {
        super(zzawVar, zziVar);
        this.zzf = zzawVar;
        this.zzc = i;
        this.zzd = str;
        this.zze = i2;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzd(Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzf.zzf;
        zzasVar.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzagVar = zzaw.zza;
        zzagVar.zzb("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(i));
        int i2 = this.zze;
        if (i2 > 0) {
            this.zzf.zzD(this.zzc, this.zzd, i2 - 1);
        }
    }
}
