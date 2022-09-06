package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzep {
    private static final Class<?> zzob = zzdj();
    private static final zzff<?, ?> zzoc = zzd(false);
    private static final zzff<?, ?> zzod = zzd(true);
    private static final zzff<?, ?> zzoe = new zzfh();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdl) {
            zzdl zzdlVar = (zzdl) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zze(zzdlVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzff<UT, UB> zzffVar) {
        if (ub == null) {
            ub = zzffVar.zzdt();
        }
        zzffVar.zza((zzff<UT, UB>) ub, i, i2);
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int i, List<Integer> list, zzcv<?> zzcvVar, UB ub, zzff<UT, UB> zzffVar) {
        UB ub2;
        int intValue;
        if (zzcvVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = list.get(i3).intValue();
                if (zzcvVar.zzaf(intValue2) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, intValue2, ub2, zzffVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it2 = list.iterator();
            loop1: while (true) {
                ub2 = ub;
                while (it2.hasNext()) {
                    intValue = it2.next().intValue();
                    if (zzcvVar.zzaf(intValue) == null) {
                        break;
                    }
                }
                ub = (UB) zza(i, intValue, ub2, zzffVar);
                it2.remove();
            }
        }
        return ub2;
    }

    public static void zza(int i, List<String> list, zzfz zzfzVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zza(i, list);
    }

    public static void zza(int i, List<?> list, zzfz zzfzVar, zzen zzenVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zza(i, list, zzenVar);
    }

    public static void zza(int i, List<Double> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzg(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzcl<FT>> void zza(zzcg<FT> zzcgVar, T t, T t2) {
        zzcj<FT> zzb = zzcgVar.zzb(t2);
        if (!zzb.isEmpty()) {
            zzcgVar.zzc(t).zza(zzb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zza(zzds zzdsVar, T t, T t2, long j) {
        zzfl.zza(t, j, zzdsVar.zzb(zzfl.zzo(t, j), zzfl.zzo(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zza(zzff<UT, UB> zzffVar, T t, T t2) {
        zzffVar.zze(t, zzffVar.zzg(zzffVar.zzr(t), zzffVar.zzr(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdl) {
            zzdl zzdlVar = (zzdl) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzf(zzdlVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzb(int i, List<zzbo> list, zzfz zzfzVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzb(i, list);
    }

    public static void zzb(int i, List<?> list, zzfz zzfzVar, zzen zzenVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzb(i, list, zzenVar);
    }

    public static void zzb(int i, List<Float> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzf(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, Object obj, zzen zzenVar) {
        return obj instanceof zzde ? zzca.zza(i, (zzde) obj) : zzca.zzb(i, (zzdx) obj, zzenVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        if (list instanceof zzdg) {
            zzdg zzdgVar = (zzdg) list;
            while (i2 < size) {
                Object raw = zzdgVar.getRaw(i2);
                zzt = (raw instanceof zzbo ? zzca.zzb((zzbo) raw) : zzca.zzi((String) raw)) + zzt;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzt = (obj instanceof zzbo ? zzca.zzb((zzbo) obj) : zzca.zzi((String) obj)) + zzt;
                i2++;
            }
        }
        return zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<?> list, zzen zzenVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzt = obj instanceof zzde ? zzca.zza((zzde) obj) + zzt : zzt + zzca.zza((zzdx) obj, zzenVar);
        }
        return zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdl) {
            zzdl zzdlVar = (zzdl) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzg(zzdlVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzc(int i, List<Long> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzc(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<zzbo> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzt = zzca.zzt(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzt += zzca.zzb(list.get(i2));
        }
        return zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<zzdx> list, zzen zzenVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzca.zzc(i, list.get(i3), zzenVar);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcs) {
            zzcs zzcsVar = (zzcs) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzz(zzcsVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzz(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    private static zzff<?, ?> zzd(boolean z) {
        try {
            Class<?> zzdk = zzdk();
            if (zzdk != null) {
                return (zzff) zzdk.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzd(int i, List<Long> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzd(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static zzff<?, ?> zzdg() {
        return zzoc;
    }

    public static zzff<?, ?> zzdh() {
        return zzod;
    }

    public static zzff<?, ?> zzdi() {
        return zzoe;
    }

    private static Class<?> zzdj() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdk() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcs) {
            zzcs zzcsVar = (zzcs) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzu(zzcsVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zze(int i, List<Long> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzn(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcs) {
            zzcs zzcsVar = (zzcs) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzv(zzcsVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzf(int i, List<Long> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zze(i, list, z);
    }

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (zzcr.class.isAssignableFrom(cls) || (cls2 = zzob) == null || cls2.isAssignableFrom(cls)) {
            return;
        }
        throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcs) {
            zzcs zzcsVar = (zzcs) list;
            i = 0;
            while (i2 < size) {
                i += zzca.zzw(zzcsVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzca.zzw(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void zzg(int i, List<Long> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzl(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static void zzh(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zza(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static void zzi(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzj(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(List<?> list) {
        return list.size();
    }

    public static void zzj(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzfz zzfzVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfzVar.zzi(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return (zzca.zzt(i) * list.size()) + zza(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zzb(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zzc(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zzd(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zze(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zzf(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzca.zzt(i) * size) + zzg(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzca.zzl(i, 0) * size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzca.zzg(i, 0L) * size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzca.zzc(i, true) * size;
    }
}
