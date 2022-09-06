package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzdo implements zzdw {
    private zzdw[] zzmv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdo(zzdw... zzdwVarArr) {
        this.zzmv = zzdwVarArr;
    }

    @Override // com.google.android.gms.internal.vision.zzdw
    public final boolean zza(Class<?> cls) {
        for (zzdw zzdwVar : this.zzmv) {
            if (zzdwVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzdw
    public final zzdv zzb(Class<?> cls) {
        zzdw[] zzdwVarArr;
        for (zzdw zzdwVar : this.zzmv) {
            if (zzdwVar.zza(cls)) {
                return zzdwVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
