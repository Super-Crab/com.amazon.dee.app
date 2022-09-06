package com.google.android.gms.internal.vision;

import amazon.communication.connection.Channels;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;
/* loaded from: classes2.dex */
final class zzeb<T> implements zzen<T> {
    private static final int[] zznc = new int[0];
    private static final Unsafe zznd = zzfl.zzdz();
    private final int[] zzne;
    private final Object[] zznf;
    private final int zzng;
    private final int zznh;
    private final zzdx zzni;
    private final boolean zznj;
    private final boolean zznk;
    private final boolean zznl;
    private final boolean zznm;
    private final int[] zznn;
    private final int zzno;
    private final int zznp;
    private final zzef zznq;
    private final zzdh zznr;
    private final zzff<?, ?> zzns;
    private final zzcg<?> zznt;
    private final zzds zznu;

    private zzeb(int[] iArr, Object[] objArr, int i, int i2, zzdx zzdxVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzef zzefVar, zzdh zzdhVar, zzff<?, ?> zzffVar, zzcg<?> zzcgVar, zzds zzdsVar) {
        this.zzne = iArr;
        this.zznf = objArr;
        this.zzng = i;
        this.zznh = i2;
        this.zznk = zzdxVar instanceof zzcr;
        this.zznl = z;
        this.zznj = zzcgVar != null && zzcgVar.zze(zzdxVar);
        this.zznm = false;
        this.zznn = iArr2;
        this.zzno = i3;
        this.zznp = i4;
        this.zznq = zzefVar;
        this.zznr = zzdhVar;
        this.zzns = zzffVar;
        this.zznt = zzcgVar;
        this.zzni = zzdxVar;
        this.zznu = zzdsVar;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbl zzblVar) throws IOException {
        return zzbk.zza(i, bArr, i2, i3, zzo(obj), zzblVar);
    }

    private static int zza(zzen<?> zzenVar, int i, byte[] bArr, int i2, int i3, zzcw<?> zzcwVar, zzbl zzblVar) throws IOException {
        int zza = zza((zzen) zzenVar, bArr, i2, i3, zzblVar);
        while (true) {
            zzcwVar.add(zzblVar.zzgq);
            if (zza >= i3) {
                break;
            }
            int zza2 = zzbk.zza(bArr, zza, zzblVar);
            if (i != zzblVar.zzgo) {
                break;
            }
            zza = zza((zzen) zzenVar, bArr, zza2, i3, zzblVar);
        }
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzen zzenVar, byte[] bArr, int i, int i2, int i3, zzbl zzblVar) throws IOException {
        zzeb zzebVar = (zzeb) zzenVar;
        Object newInstance = zzebVar.newInstance();
        int zza = zzebVar.zza((zzeb) newInstance, bArr, i, i2, i3, zzblVar);
        zzebVar.zzd(newInstance);
        zzblVar.zzgq = newInstance;
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzen zzenVar, byte[] bArr, int i, int i2, zzbl zzblVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzbk.zza(i4, bArr, i3, zzblVar);
            i4 = zzblVar.zzgo;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzcx.zzcb();
        }
        Object newInstance = zzenVar.newInstance();
        int i6 = i4 + i5;
        zzenVar.zza(newInstance, bArr, i5, i6, zzblVar);
        zzenVar.zzd(newInstance);
        zzblVar.zzgq = newInstance;
        return i6;
    }

    private static <UT, UB> int zza(zzff<UT, UB> zzffVar, T t) {
        return zzffVar.zzn(zzffVar.zzr(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzbl zzblVar) throws IOException {
        Object valueOf;
        Object valueOf2;
        int zzb;
        long j2;
        int i9;
        Object valueOf3;
        int i10;
        Unsafe unsafe = zznd;
        long j3 = this.zzne[i8 + 2] & Channels.CHANNEL_FOR_ECHO_TEST;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    valueOf = Double.valueOf(zzbk.zzc(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    zzb = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    valueOf2 = Float.valueOf(zzbk.zzd(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    zzb = zzbk.zzb(bArr, i, zzblVar);
                    j2 = zzblVar.zzgp;
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    zzb = zzbk.zza(bArr, i, zzblVar);
                    i9 = zzblVar.zzgo;
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    valueOf = Long.valueOf(zzbk.zzb(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    zzb = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    valueOf2 = Integer.valueOf(zzbk.zza(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzb = zzbk.zzb(bArr, i, zzblVar);
                    valueOf3 = Boolean.valueOf(zzblVar.zzgp != 0);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    zzb = zzbk.zza(bArr, i, zzblVar);
                    i10 = zzblVar.zzgo;
                    if (i10 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    } else if ((i6 & 536870912) != 0 && !zzfn.zze(bArr, zzb, zzb + i10)) {
                        throw zzcx.zzcg();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, zzb, i10, zzct.UTF_8));
                        zzb += i10;
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    }
                }
                return i;
            case 60:
                if (i5 == 2) {
                    zzb = zza(zzag(i8), bArr, i, i2, zzblVar);
                    Object object = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzblVar.zzgq;
                    if (object != null) {
                        valueOf3 = zzct.zza(object, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    zzb = zzbk.zza(bArr, i, zzblVar);
                    i10 = zzblVar.zzgo;
                    if (i10 == 0) {
                        valueOf3 = zzbo.zzgt;
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    }
                    unsafe.putObject(t, j, zzbo.zzb(bArr, zzb, i10));
                    zzb += i10;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza = zzbk.zza(bArr, i, zzblVar);
                    int i11 = zzblVar.zzgo;
                    zzcv<?> zzai = zzai(i8);
                    if (zzai != null && zzai.zzaf(i11) == null) {
                        zzo(t).zzb(i3, Long.valueOf(i11));
                        return zza;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(i11));
                    zzb = zza;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    zzb = zzbk.zza(bArr, i, zzblVar);
                    i9 = zzbx.zzo(zzblVar.zzgo);
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    zzb = zzbk.zzb(bArr, i, zzblVar);
                    j2 = zzbx.zza(zzblVar.zzgp);
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    zzb = zza(zzag(i8), bArr, i, i2, (i3 & (-8)) | 4, zzblVar);
                    Object object2 = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzblVar.zzgq;
                    if (object2 != null) {
                        valueOf3 = zzct.zza(object2, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0260, code lost:
        if (r30.zzgp != 0) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0262, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0264, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0265, code lost:
        r11.addBoolean(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0268, code lost:
        if (r4 >= r20) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x026a, code lost:
        r6 = com.google.android.gms.internal.vision.zzbk.zza(r18, r4, r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0270, code lost:
        if (r21 != r30.zzgo) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0272, code lost:
        r4 = com.google.android.gms.internal.vision.zzbk.zzb(r18, r6, r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x027a, code lost:
        if (r30.zzgp == 0) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x013c, code lost:
        if (r4 == 0) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013e, code lost:
        r11.add(com.google.android.gms.internal.vision.zzbo.zzgt);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0144, code lost:
        r11.add(com.google.android.gms.internal.vision.zzbo.zzb(r18, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x014c, code lost:
        if (r1 >= r20) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x014e, code lost:
        r4 = com.google.android.gms.internal.vision.zzbk.zza(r18, r1, r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0154, code lost:
        if (r21 != r30.zzgo) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0156, code lost:
        r1 = com.google.android.gms.internal.vision.zzbk.zza(r18, r4, r30);
        r4 = r30.zzgo;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x015c, code lost:
        if (r4 < 0) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x015e, code lost:
        if (r4 != 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0165, code lost:
        throw com.google.android.gms.internal.vision.zzcx.zzcc();
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:263:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:265:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ad  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x01ef -> B:109:0x01f3). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:0x0205 -> B:104:0x01dc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:144:0x0264 -> B:145:0x0265). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:150:0x027a -> B:143:0x0262). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0144 -> B:67:0x014c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x015e -> B:65:0x013e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x01a7 -> B:89:0x01ab). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:94:0x01bd -> B:86:0x019c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.vision.zzbl r30) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.zzbl):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzbl zzblVar) throws IOException {
        Unsafe unsafe = zznd;
        Object zzah = zzah(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zznu.zzj(object)) {
            Object zzl = this.zznu.zzl(zzah);
            this.zznu.zzb(zzl, object);
            unsafe.putObject(t, j, zzl);
            object = zzl;
        }
        zzdq<?, ?> zzm = this.zznu.zzm(zzah);
        Map<?, ?> zzh = this.zznu.zzh(object);
        int zza = zzbk.zza(bArr, i, zzblVar);
        int i4 = zzblVar.zzgo;
        if (i4 < 0 || i4 > i2 - zza) {
            throw zzcx.zzcb();
        }
        int i5 = i4 + zza;
        Object obj = (K) zzm.zzmx;
        Object obj2 = (V) zzm.zzew;
        while (zza < i5) {
            int i6 = zza + 1;
            int i7 = bArr[zza];
            if (i7 < 0) {
                i6 = zzbk.zza(i7, bArr, i6, zzblVar);
                i7 = zzblVar.zzgo;
            }
            int i8 = i6;
            int i9 = i7 >>> 3;
            int i10 = i7 & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == zzm.zzmy.zzee()) {
                    zza = zza(bArr, i8, i2, zzm.zzmy, zzm.zzew.getClass(), zzblVar);
                    obj2 = zzblVar.zzgq;
                }
                zza = zzbk.zza(i7, bArr, i8, i2, zzblVar);
            } else if (i10 == zzm.zzmw.zzee()) {
                zza = zza(bArr, i8, i2, zzm.zzmw, (Class<?>) null, zzblVar);
                obj = (K) zzblVar.zzgq;
            } else {
                zza = zzbk.zza(i7, bArr, i8, i2, zzblVar);
            }
        }
        if (zza != i5) {
            throw zzcx.zzcf();
        }
        zzh.put(obj, obj2);
        return i5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x0335, code lost:
        if (r0 == r13) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0339, code lost:
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r6 = r18;
        r2 = r19;
        r7 = r22;
        r1 = r23;
        r3 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x037c, code lost:
        if (r0 == r15) goto L78;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(T r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.vision.zzbl r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1148
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzbl):int");
    }

    private static int zza(byte[] bArr, int i, int i2, zzft zzftVar, Class<?> cls, zzbl zzblVar) throws IOException {
        int zzb;
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        int i3;
        long j;
        switch (zzec.zzhz[zzftVar.ordinal()]) {
            case 1:
                zzb = zzbk.zzb(bArr, i, zzblVar);
                valueOf = Boolean.valueOf(zzblVar.zzgp != 0);
                zzblVar.zzgq = valueOf;
                return zzb;
            case 2:
                return zzbk.zze(bArr, i, zzblVar);
            case 3:
                valueOf2 = Double.valueOf(zzbk.zzc(bArr, i));
                zzblVar.zzgq = valueOf2;
                return i + 8;
            case 4:
            case 5:
                valueOf3 = Integer.valueOf(zzbk.zza(bArr, i));
                zzblVar.zzgq = valueOf3;
                return i + 4;
            case 6:
            case 7:
                valueOf2 = Long.valueOf(zzbk.zzb(bArr, i));
                zzblVar.zzgq = valueOf2;
                return i + 8;
            case 8:
                valueOf3 = Float.valueOf(zzbk.zzd(bArr, i));
                zzblVar.zzgq = valueOf3;
                return i + 4;
            case 9:
            case 10:
            case 11:
                zzb = zzbk.zza(bArr, i, zzblVar);
                i3 = zzblVar.zzgo;
                valueOf = Integer.valueOf(i3);
                zzblVar.zzgq = valueOf;
                return zzb;
            case 12:
            case 13:
                zzb = zzbk.zzb(bArr, i, zzblVar);
                j = zzblVar.zzgp;
                valueOf = Long.valueOf(j);
                zzblVar.zzgq = valueOf;
                return zzb;
            case 14:
                return zza((zzen) zzek.zzdc().zze(cls), bArr, i, i2, zzblVar);
            case 15:
                zzb = zzbk.zza(bArr, i, zzblVar);
                i3 = zzbx.zzo(zzblVar.zzgo);
                valueOf = Integer.valueOf(i3);
                zzblVar.zzgq = valueOf;
                return zzb;
            case 16:
                zzb = zzbk.zzb(bArr, i, zzblVar);
                j = zzbx.zza(zzblVar.zzgp);
                valueOf = Long.valueOf(j);
                zzblVar.zzgq = valueOf;
                return zzb;
            case 17:
                return zzbk.zzd(bArr, i, zzblVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0414  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.android.gms.internal.vision.zzeb<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.vision.zzdv r34, com.google.android.gms.internal.vision.zzef r35, com.google.android.gms.internal.vision.zzdh r36, com.google.android.gms.internal.vision.zzff<?, ?> r37, com.google.android.gms.internal.vision.zzcg<?> r38, com.google.android.gms.internal.vision.zzds r39) {
        /*
            Method dump skipped, instructions count: 1178
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Class, com.google.android.gms.internal.vision.zzdv, com.google.android.gms.internal.vision.zzef, com.google.android.gms.internal.vision.zzdh, com.google.android.gms.internal.vision.zzff, com.google.android.gms.internal.vision.zzcg, com.google.android.gms.internal.vision.zzds):com.google.android.gms.internal.vision.zzeb");
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzcv<?> zzcvVar, UB ub, zzff<UT, UB> zzffVar) {
        zzdq<?, ?> zzm = this.zznu.zzm(zzah(i));
        Iterator<Map.Entry<K, V>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<K, V> next = it2.next();
            if (zzcvVar.zzaf(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzffVar.zzdt();
                }
                zzbt zzm2 = zzbo.zzm(zzdp.zza(zzm, next.getKey(), next.getValue()));
                try {
                    zzdp.zza(zzm2.zzax(), zzm, next.getKey(), next.getValue());
                    zzffVar.zza((zzff<UT, UB>) ub, i2, zzm2.zzaw());
                    it2.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(arrays, name.length() + GeneratedOutlineSupport1.outline6(str, 40)), "Field ", str, " for ", name), " not found. Known fields are ", arrays));
        }
    }

    private static void zza(int i, Object obj, zzfz zzfzVar) throws IOException {
        if (obj instanceof String) {
            zzfzVar.zza(i, (String) obj);
        } else {
            zzfzVar.zza(i, (zzbo) obj);
        }
    }

    private static <UT, UB> void zza(zzff<UT, UB> zzffVar, T t, zzfz zzfzVar) throws IOException {
        zzffVar.zza(zzffVar.zzr(t), zzfzVar);
    }

    private final <K, V> void zza(zzfz zzfzVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfzVar.zza(i, this.zznu.zzm(zzah(i2)), this.zznu.zzi(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzaj = zzaj(i) & Channels.CHANNEL_FOR_ECHO_TEST;
        if (!zza((zzeb<T>) t2, i)) {
            return;
        }
        Object zzo = zzfl.zzo(t, zzaj);
        Object zzo2 = zzfl.zzo(t2, zzaj);
        if (zzo != null && zzo2 != null) {
            zzfl.zza(t, zzaj, zzct.zza(zzo, zzo2));
            zzb((zzeb<T>) t, i);
        } else if (zzo2 == null) {
        } else {
            zzfl.zza(t, zzaj, zzo2);
            zzb((zzeb<T>) t, i);
        }
    }

    private final boolean zza(T t, int i) {
        if (!this.zznl) {
            int zzak = zzak(i);
            return (zzfl.zzj(t, (long) (zzak & Channels.CHANNEL_FOR_ECHO_TEST)) & (1 << (zzak >>> 20))) != 0;
        }
        int zzaj = zzaj(i);
        long j = zzaj & Channels.CHANNEL_FOR_ECHO_TEST;
        switch ((zzaj & 267386880) >>> 20) {
            case 0:
                return zzfl.zzn(t, j) != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            case 1:
                return zzfl.zzm(t, j) != 0.0f;
            case 2:
                return zzfl.zzk(t, j) != 0;
            case 3:
                return zzfl.zzk(t, j) != 0;
            case 4:
                return zzfl.zzj(t, j) != 0;
            case 5:
                return zzfl.zzk(t, j) != 0;
            case 6:
                return zzfl.zzj(t, j) != 0;
            case 7:
                return zzfl.zzl(t, j);
            case 8:
                Object zzo = zzfl.zzo(t, j);
                if (zzo instanceof String) {
                    return !((String) zzo).isEmpty();
                } else if (!(zzo instanceof zzbo)) {
                    throw new IllegalArgumentException();
                } else {
                    return !zzbo.zzgt.equals(zzo);
                }
            case 9:
                return zzfl.zzo(t, j) != null;
            case 10:
                return !zzbo.zzgt.equals(zzfl.zzo(t, j));
            case 11:
                return zzfl.zzj(t, j) != 0;
            case 12:
                return zzfl.zzj(t, j) != 0;
            case 13:
                return zzfl.zzj(t, j) != 0;
            case 14:
                return zzfl.zzk(t, j) != 0;
            case 15:
                return zzfl.zzj(t, j) != 0;
            case 16:
                return zzfl.zzk(t, j) != 0;
            case 17:
                return zzfl.zzo(t, j) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfl.zzj(t, (long) (zzak(i2) & Channels.CHANNEL_FOR_ECHO_TEST)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zznl ? zza((zzeb<T>) t, i) : (i2 & i3) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzen zzenVar) {
        return zzenVar.zzp(zzfl.zzo(obj, i & Channels.CHANNEL_FOR_ECHO_TEST));
    }

    private final zzen zzag(int i) {
        int i2 = (i / 3) << 1;
        zzen zzenVar = (zzen) this.zznf[i2];
        if (zzenVar != null) {
            return zzenVar;
        }
        zzen<T> zze = zzek.zzdc().zze((Class) this.zznf[i2 + 1]);
        this.zznf[i2] = zze;
        return zze;
    }

    private final Object zzah(int i) {
        return this.zznf[(i / 3) << 1];
    }

    private final zzcv<?> zzai(int i) {
        return (zzcv) this.zznf[((i / 3) << 1) + 1];
    }

    private final int zzaj(int i) {
        return this.zzne[i + 1];
    }

    private final int zzak(int i) {
        return this.zzne[i + 2];
    }

    private final int zzal(int i) {
        if (i < this.zzng || i > this.zznh) {
            return -1;
        }
        return zzs(i, 0);
    }

    private final void zzb(T t, int i) {
        if (this.zznl) {
            return;
        }
        int zzak = zzak(i);
        long j = zzak & Channels.CHANNEL_FOR_ECHO_TEST;
        zzfl.zza((Object) t, j, zzfl.zzj(t, j) | (1 << (zzak >>> 20)));
    }

    private final void zzb(T t, int i, int i2) {
        zzfl.zza((Object) t, zzak(i2) & Channels.CHANNEL_FOR_ECHO_TEST, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzb(T r19, com.google.android.gms.internal.vision.zzfz r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzfz):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzaj = zzaj(i);
        int i2 = this.zzne[i];
        long j = zzaj & Channels.CHANNEL_FOR_ECHO_TEST;
        if (!zza((zzeb<T>) t2, i2, i)) {
            return;
        }
        Object zzo = zzfl.zzo(t, j);
        Object zzo2 = zzfl.zzo(t2, j);
        if (zzo != null && zzo2 != null) {
            zzfl.zza(t, j, zzct.zza(zzo, zzo2));
            zzb((zzeb<T>) t, i2, i);
        } else if (zzo2 == null) {
        } else {
            zzfl.zza(t, j, zzo2);
            zzb((zzeb<T>) t, i2, i);
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzeb<T>) t, i) == zza((zzeb<T>) t2, i);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfl.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfl.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfl.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfl.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfl.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfl.zzo(t, j)).booleanValue();
    }

    private static zzfg zzo(Object obj) {
        zzcr zzcrVar = (zzcr) obj;
        zzfg zzfgVar = zzcrVar.zzkr;
        if (zzfgVar == zzfg.zzdu()) {
            zzfg zzdv = zzfg.zzdv();
            zzcrVar.zzkr = zzdv;
            return zzdv;
        }
        return zzfgVar;
    }

    private final int zzr(int i, int i2) {
        if (i < this.zzng || i > this.zznh) {
            return -1;
        }
        return zzs(i, i2);
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzne.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzne[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a0, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.vision.zzep.zzd(com.google.android.gms.internal.vision.zzfl.zzo(r10, r6), com.google.android.gms.internal.vision.zzfl.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
        if (com.google.android.gms.internal.vision.zzep.zzd(com.google.android.gms.internal.vision.zzfl.zzo(r10, r6), com.google.android.gms.internal.vision.zzfl.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0096, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a8, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        if (com.google.android.gms.internal.vision.zzep.zzd(com.google.android.gms.internal.vision.zzfl.zzo(r10, r6), com.google.android.gms.internal.vision.zzfl.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f8, code lost:
        if (com.google.android.gms.internal.vision.zzep.zzd(com.google.android.gms.internal.vision.zzfl.zzo(r10, r6), com.google.android.gms.internal.vision.zzfl.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010e, code lost:
        if (com.google.android.gms.internal.vision.zzep.zzd(com.google.android.gms.internal.vision.zzfl.zzo(r10, r6), com.google.android.gms.internal.vision.zzfl.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0120, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzl(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzl(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0145, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0156, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0169, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x017c, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzk(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018d, code lost:
        if (com.google.android.gms.internal.vision.zzfl.zzj(r10, r6) == com.google.android.gms.internal.vision.zzfl.zzj(r11, r6)) goto L84;
     */
    @Override // com.google.android.gms.internal.vision.zzen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ce, code lost:
        if (r3 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e0, code lost:
        if (r3 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.gms.internal.vision.zzen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int hashCode(T r9) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.hashCode(java.lang.Object):int");
    }

    @Override // com.google.android.gms.internal.vision.zzen
    public final T newInstance() {
        return (T) this.zznq.newInstance(this.zzni);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0977  */
    @Override // com.google.android.gms.internal.vision.zzen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzfz r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, com.google.android.gms.internal.vision.zzfz):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f7, code lost:
        if (r0 == r15) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0214, code lost:
        if (r0 == r15) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0216, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01ca, code lost:
        if (r0 == r15) goto L125;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    @Override // com.google.android.gms.internal.vision.zzen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.vision.zzbl r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 634
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzbl):void");
    }

    @Override // com.google.android.gms.internal.vision.zzen
    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzne.length; i += 3) {
                int zzaj = zzaj(i);
                long j = 1048575 & zzaj;
                int i2 = this.zzne[i];
                switch ((zzaj & 267386880) >>> 20) {
                    case 0:
                        if (zza((zzeb<T>) t2, i)) {
                            zzfl.zza(t, j, zzfl.zzn(t2, j));
                            zzb((zzeb<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzeb<T>) t2, i)) {
                            zzfl.zza((Object) t, j, zzfl.zzm(t2, j));
                            zzb((zzeb<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 3:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 4:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 5:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 6:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 7:
                        if (zza((zzeb<T>) t2, i)) {
                            zzfl.zza(t, j, zzfl.zzl(t2, j));
                            zzb((zzeb<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza(t, j, zzfl.zzo(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 9:
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza(t, j, zzfl.zzo(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 11:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 12:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 13:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 14:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 15:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzj(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 16:
                        if (!zza((zzeb<T>) t2, i)) {
                            break;
                        }
                        zzfl.zza((Object) t, j, zzfl.zzk(t2, j));
                        zzb((zzeb<T>) t, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zznr.zza(t, t2, j);
                        break;
                    case 50:
                        zzep.zza(this.zznu, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza((zzeb<T>) t2, i2, i)) {
                            break;
                        }
                        zzfl.zza(t, j, zzfl.zzo(t2, j));
                        zzb((zzeb<T>) t, i2, i);
                        break;
                    case 60:
                    case 68:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza((zzeb<T>) t2, i2, i)) {
                            break;
                        }
                        zzfl.zza(t, j, zzfl.zzo(t2, j));
                        zzb((zzeb<T>) t, i2, i);
                        break;
                }
            }
            if (this.zznl) {
                return;
            }
            zzep.zza(this.zzns, t, t2);
            if (!this.zznj) {
                return;
            }
            zzep.zza(this.zznt, t, t2);
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.android.gms.internal.vision.zzen
    public final void zzd(T t) {
        int i;
        int i2 = this.zzno;
        while (true) {
            i = this.zznp;
            if (i2 >= i) {
                break;
            }
            long zzaj = zzaj(this.zznn[i2]) & Channels.CHANNEL_FOR_ECHO_TEST;
            Object zzo = zzfl.zzo(t, zzaj);
            if (zzo != null) {
                zzfl.zza(t, zzaj, this.zznu.zzk(zzo));
            }
            i2++;
        }
        int length = this.zznn.length;
        while (i < length) {
            this.zznr.zza(t, this.zznn[i]);
            i++;
        }
        this.zzns.zzd(t);
        if (this.zznj) {
            this.zznt.zzd(t);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0181, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0193, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a5, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01b6, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01c7, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01d8, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01e9, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020b, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x020d, code lost:
        r2.putInt(r18, r12, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0211, code lost:
        r10 = ((com.google.android.gms.internal.vision.zzca.zzv(r11) + com.google.android.gms.internal.vision.zzca.zzt(r13)) + r11) + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x033f, code lost:
        if ((r11 instanceof com.google.android.gms.internal.vision.zzbo) != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0349, code lost:
        r11 = com.google.android.gms.internal.vision.zzca.zzb(r13, (java.lang.String) r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0429, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0449, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L285;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x0451, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0471, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0479, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0489, code lost:
        if ((r5 instanceof com.google.android.gms.internal.vision.zzbo) != false) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0491, code lost:
        if (zza((com.google.android.gms.internal.vision.zzeb<T>) r18, r11, r9) != false) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0529, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x053b, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x054d, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x055f, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x0571, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x0583, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x0595, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x05a7, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x05b8, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x05c9, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x05da, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x05eb, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x05fc, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x060d, code lost:
        if (r17.zznm != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x060f, code lost:
        r2.putInt(r18, r10, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x0613, code lost:
        r3 = r3 + ((com.google.android.gms.internal.vision.zzca.zzv(r5) + com.google.android.gms.internal.vision.zzca.zzt(r11)) + r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x06cc, code lost:
        if ((r8 & r14) != 0) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x06ce, code lost:
        r5 = com.google.android.gms.internal.vision.zzca.zzc(r11, (com.google.android.gms.internal.vision.zzdx) r2.getObject(r18, r6), zzag(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x06fa, code lost:
        if ((r8 & r14) != 0) goto L285;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x06fc, code lost:
        r5 = com.google.android.gms.internal.vision.zzca.zzh(r11, 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x0708, code lost:
        if ((r8 & r14) != 0) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x070a, code lost:
        r5 = com.google.android.gms.internal.vision.zzca.zzm(r11, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x072d, code lost:
        if ((r8 & r14) != 0) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x072f, code lost:
        r5 = r2.getObject(r18, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0736, code lost:
        if ((r8 & r14) != 0) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x0738, code lost:
        r5 = com.google.android.gms.internal.vision.zzep.zzc(r11, r2.getObject(r18, r6), zzag(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0750, code lost:
        if ((r5 instanceof com.google.android.gms.internal.vision.zzbo) != false) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x0752, code lost:
        r5 = com.google.android.gms.internal.vision.zzca.zzc(r11, (com.google.android.gms.internal.vision.zzbo) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x0759, code lost:
        r5 = com.google.android.gms.internal.vision.zzca.zzb(r11, (java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x0762, code lost:
        if ((r8 & r14) != 0) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x0764, code lost:
        r5 = 1;
        r6 = com.google.android.gms.internal.vision.zzca.zzc(r11, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ab, code lost:
        if ((r11 instanceof com.google.android.gms.internal.vision.zzbo) != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0127, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0139, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x014b, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x015d, code lost:
        if (r17.zznm != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
        if (r17.zznm != false) goto L107;
     */
    @Override // com.google.android.gms.internal.vision.zzen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzn(T r18) {
        /*
            Method dump skipped, instructions count: 2306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzeb.zzn(java.lang.Object):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.vision.zzen] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.vision.zzen] */
    @Override // com.google.android.gms.internal.vision.zzen
    public final boolean zzp(T t) {
        int i;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i4 >= this.zzno) {
                return !this.zznj || this.zznt.zzb(t).isInitialized();
            }
            int i5 = this.zznn[i4];
            int i6 = this.zzne[i5];
            int zzaj = zzaj(i5);
            if (!this.zznl) {
                int i7 = this.zzne[i5 + 2];
                int i8 = i7 & Channels.CHANNEL_FOR_ECHO_TEST;
                i = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i2 = zznd.getInt(t, i8);
                    i3 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzaj) != 0) && !zza((zzeb<T>) t, i5, i2, i)) {
                return false;
            }
            int i9 = (267386880 & zzaj) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zza((zzeb<T>) t, i6, i5) && !zza(t, zzaj, zzag(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzi = this.zznu.zzi(zzfl.zzo(t, zzaj & Channels.CHANNEL_FOR_ECHO_TEST));
                            if (!zzi.isEmpty()) {
                                if (this.zznu.zzm(zzah(i5)).zzmy.zzed() == zzfy.MESSAGE) {
                                    zzen<T> zzenVar = 0;
                                    Iterator<?> it2 = zzi.values().iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        Object next = it2.next();
                                        if (zzenVar == null) {
                                            zzenVar = zzek.zzdc().zze(next.getClass());
                                        }
                                        boolean zzp = zzenVar.zzp(next);
                                        zzenVar = zzenVar;
                                        if (!zzp) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzfl.zzo(t, zzaj & Channels.CHANNEL_FOR_ECHO_TEST);
                if (!list.isEmpty()) {
                    ?? zzag = zzag(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        } else if (!zzag.zzp(list.get(i10))) {
                            z = false;
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza((zzeb<T>) t, i5, i2, i) && !zza(t, zzaj, zzag(i5))) {
                return false;
            }
            i4++;
        }
    }
}
