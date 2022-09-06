package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzdk extends zzdh {
    private zzdk() {
        super();
    }

    private static <E> zzcw<E> zzc(Object obj, long j) {
        return (zzcw) zzfl.zzo(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdh
    public final void zza(Object obj, long j) {
        zzc(obj, j).zzao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.gms.internal.vision.zzdh
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzcw<E> zzc = zzc(obj, j);
        zzcw<E> zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        zzcw<E> zzcwVar = zzc;
        zzcwVar = zzc;
        if (size > 0 && size2 > 0) {
            boolean zzan = zzc.zzan();
            zzcw<E> zzcwVar2 = zzc;
            if (!zzan) {
                zzcwVar2 = zzc.zzk(size2 + size);
            }
            zzcwVar2.addAll(zzc2);
            zzcwVar = zzcwVar2;
        }
        if (size > 0) {
            zzc2 = zzcwVar;
        }
        zzfl.zza(obj, j, zzc2);
    }
}
