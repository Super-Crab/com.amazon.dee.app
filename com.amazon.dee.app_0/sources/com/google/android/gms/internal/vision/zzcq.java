package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
/* loaded from: classes2.dex */
final class zzcq implements zzdw {
    private static final zzcq zzkq = new zzcq();

    private zzcq() {
    }

    public static zzcq zzbs() {
        return zzkq;
    }

    @Override // com.google.android.gms.internal.vision.zzdw
    public final boolean zza(Class<?> cls) {
        return zzcr.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.vision.zzdw
    public final zzdv zzb(Class<?> cls) {
        if (!zzcr.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzdv) zzcr.zzc(cls.asSubclass(zzcr.class)).zza(zzcr.zzd.zzla, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
