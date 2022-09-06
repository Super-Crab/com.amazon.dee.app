package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes2.dex */
final class zzfh extends zzff<zzfg, zzfg> {
    private static void zza(Object obj, zzfg zzfgVar) {
        ((zzcr) obj).zzkr = zzfgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zza(zzfg zzfgVar, int i, long j) {
        zzfgVar.zzb(i << 3, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zza(zzfg zzfgVar, int i, zzbo zzboVar) {
        zzfgVar.zzb((i << 3) | 2, zzboVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zza(zzfg zzfgVar, zzfz zzfzVar) throws IOException {
        zzfgVar.zzb(zzfzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zzc(zzfg zzfgVar, zzfz zzfzVar) throws IOException {
        zzfgVar.zza(zzfzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final void zzd(Object obj) {
        ((zzcr) obj).zzkr.zzao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ zzfg zzdt() {
        return zzfg.zzdv();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zze(Object obj, zzfg zzfgVar) {
        zza(obj, zzfgVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ void zzf(Object obj, zzfg zzfgVar) {
        zza(obj, zzfgVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ zzfg zzg(zzfg zzfgVar, zzfg zzfgVar2) {
        zzfg zzfgVar3 = zzfgVar;
        zzfg zzfgVar4 = zzfgVar2;
        return zzfgVar4.equals(zzfg.zzdu()) ? zzfgVar3 : zzfg.zza(zzfgVar3, zzfgVar4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ int zzn(zzfg zzfgVar) {
        return zzfgVar.zzbl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ zzfg zzr(Object obj) {
        return ((zzcr) obj).zzkr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzff
    public final /* synthetic */ int zzs(zzfg zzfgVar) {
        return zzfgVar.zzdw();
    }
}
