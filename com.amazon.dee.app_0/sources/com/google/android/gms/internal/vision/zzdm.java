package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdm implements zzeo {
    private static final zzdw zzmu = new zzdn();
    private final zzdw zzmt;

    public zzdm() {
        this(new zzdo(zzcq.zzbs(), zzco()));
    }

    private zzdm(zzdw zzdwVar) {
        this.zzmt = (zzdw) zzct.zza(zzdwVar, "messageInfoFactory");
    }

    private static boolean zza(zzdv zzdvVar) {
        return zzdvVar.zzcv() == zzcr.zzd.zzlg;
    }

    private static zzdw zzco() {
        try {
            return (zzdw) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzmu;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzeo
    public final <T> zzen<T> zzd(Class<T> cls) {
        zzep.zzf((Class<?>) cls);
        zzdv zzb = this.zzmt.zzb(cls);
        if (zzb.zzcw()) {
            return zzcr.class.isAssignableFrom(cls) ? zzed.zza(zzep.zzdi(), zzci.zzbi(), zzb.zzcx()) : zzed.zza(zzep.zzdg(), zzci.zzbj(), zzb.zzcx());
        } else if (!zzcr.class.isAssignableFrom(cls)) {
            boolean zza = zza(zzb);
            zzef zzcz = zzeh.zzcz();
            zzdh zzcm = zzdh.zzcm();
            return zza ? zzeb.zza(cls, zzb, zzcz, zzcm, zzep.zzdg(), zzci.zzbj(), zzdu.zzcs()) : zzeb.zza(cls, zzb, zzcz, zzcm, zzep.zzdh(), (zzcg<?>) null, zzdu.zzcs());
        } else {
            boolean zza2 = zza(zzb);
            zzef zzda = zzeh.zzda();
            zzdh zzcn = zzdh.zzcn();
            zzff<?, ?> zzdi = zzep.zzdi();
            return zza2 ? zzeb.zza(cls, zzb, zzda, zzcn, zzdi, zzci.zzbi(), zzdu.zzct()) : zzeb.zza(cls, zzb, zzda, zzcn, zzdi, (zzcg<?>) null, zzdu.zzct());
        }
    }
}
