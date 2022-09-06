package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzch extends zzcg<Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final int zza(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final void zza(zzfz zzfzVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.zzcg
    final void zza(Object obj, zzcj<Object> zzcjVar) {
        ((zzcr.zzc) obj).zzkx = zzcjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final zzcj<Object> zzb(Object obj) {
        return ((zzcr.zzc) obj).zzkx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final zzcj<Object> zzc(Object obj) {
        zzcj<Object> zzb = zzb(obj);
        if (zzb.isImmutable()) {
            zzcj<Object> zzcjVar = (zzcj) zzb.clone();
            zza(obj, zzcjVar);
            return zzcjVar;
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final void zzd(Object obj) {
        zzb(obj).zzao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzcg
    public final boolean zze(zzdx zzdxVar) {
        return zzdxVar instanceof zzcr.zzc;
    }
}
