package com.google.android.gms.internal.vision;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.google.android.gms.internal.vision.zzcr;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzcc implements zzfz {
    private final zzca zzgz;

    private zzcc(zzca zzcaVar) {
        this.zzgz = (zzca) zzct.zza(zzcaVar, ContactsModuleConstants.OUTPUT);
        this.zzgz.zzhk = this;
    }

    public static zzcc zza(zzca zzcaVar) {
        zzcc zzccVar = zzcaVar.zzhk;
        return zzccVar != null ? zzccVar : new zzcc(zzcaVar);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, double d) throws IOException {
        this.zzgz.zza(i, d);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, float f) throws IOException {
        this.zzgz.zza(i, f);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, long j) throws IOException {
        this.zzgz.zza(i, j);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, zzbo zzboVar) throws IOException {
        this.zzgz.zza(i, zzboVar);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final <K, V> void zza(int i, zzdq<K, V> zzdqVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzgz.zzd(i, 2);
            this.zzgz.zzq(zzdp.zza(zzdqVar, entry.getKey(), entry.getValue()));
            zzdp.zza(this.zzgz, zzdqVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbo) {
            this.zzgz.zzb(i, (zzbo) obj);
        } else {
            this.zzgz.zza(i, (zzdx) obj);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, Object obj, zzen zzenVar) throws IOException {
        this.zzgz.zza(i, (zzdx) obj, zzenVar);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, String str) throws IOException {
        this.zzgz.zza(i, str);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzdg)) {
            while (i2 < list.size()) {
                this.zzgz.zza(i, list.get(i2));
                i2++;
            }
            return;
        }
        zzdg zzdgVar = (zzdg) list;
        while (i2 < list.size()) {
            Object raw = zzdgVar.getRaw(i2);
            if (raw instanceof String) {
                this.zzgz.zza(i, (String) raw);
            } else {
                this.zzgz.zza(i, (zzbo) raw);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, List<?> list, zzen zzenVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzenVar);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zze(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzu(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzp(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzac(int i) throws IOException {
        this.zzgz.zzd(i, 3);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzad(int i) throws IOException {
        this.zzgz.zzd(i, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, long j) throws IOException {
        this.zzgz.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, Object obj, zzen zzenVar) throws IOException {
        zzca zzcaVar = this.zzgz;
        zzcaVar.zzd(i, 3);
        zzenVar.zza((zzdx) obj, zzcaVar.zzhk);
        zzcaVar.zzd(i, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, List<zzbo> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzgz.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, List<?> list, zzen zzenVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzenVar);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzh(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzx(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzs(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzb(int i, boolean z) throws IOException {
        this.zzgz.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final int zzbc() {
        return zzcr.zzd.zzlj;
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzc(int i, long j) throws IOException {
        this.zzgz.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zze(list.get(i4).longValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzb(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzf(list.get(i4).longValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzb(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zze(int i, int i2) throws IOException {
        this.zzgz.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzc(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzh(list.get(i4).longValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzd(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzf(int i, int i2) throws IOException {
        this.zzgz.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zza(i, list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzd(list.get(i4).floatValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzc(list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzg(int i, int i2) throws IOException {
        this.zzgz.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zza(i, list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzb(list.get(i4).doubleValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zza(list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzh(int i, int i2) throws IOException {
        this.zzgz.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zze(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzz(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzp(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzi(int i, long j) throws IOException {
        this.zzgz.zza(i, j);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzb(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzb(list.get(i4).booleanValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zza(list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzj(int i, long j) throws IOException {
        this.zzgz.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzf(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzv(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzq(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzh(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzy(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzs(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzc(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzi(list.get(i4).longValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzd(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzg(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzw(list.get(i4).intValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzr(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zzgz.zzb(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zzgz.zzd(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzca.zzg(list.get(i4).longValue());
        }
        this.zzgz.zzq(i3);
        while (i2 < list.size()) {
            this.zzgz.zzc(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzo(int i, int i2) throws IOException {
        this.zzgz.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.vision.zzfz
    public final void zzp(int i, int i2) throws IOException {
        this.zzgz.zze(i, i2);
    }
}
