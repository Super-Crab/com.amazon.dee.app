package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzcj<FieldDescriptorType extends zzcl<FieldDescriptorType>> {
    private static final zzcj zzhx = new zzcj(true);
    private boolean zzhv;
    private boolean zzhw = false;
    private final zzeq<FieldDescriptorType, Object> zzhu = zzeq.zzam(16);

    private zzcj() {
    }

    private zzcj(boolean z) {
        zzao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzft zzftVar, int i, Object obj) {
        int zzt = zzca.zzt(i);
        if (zzftVar == zzft.GROUP) {
            zzct.zzf((zzdx) obj);
            zzt <<= 1;
        }
        return zzt + zzb(zzftVar, obj);
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzhu.get(fielddescriptortype);
        return obj instanceof zzda ? zzda.zzci() : obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzca zzcaVar, zzft zzftVar, int i, Object obj) throws IOException {
        if (zzftVar == zzft.GROUP) {
            zzdx zzdxVar = (zzdx) obj;
            zzct.zzf(zzdxVar);
            zzcaVar.zzd(i, 3);
            zzdxVar.zzb(zzcaVar);
            zzcaVar.zzd(i, 4);
            return;
        }
        zzcaVar.zzd(i, zzftVar.zzee());
        switch (zzck.zzhz[zzftVar.ordinal()]) {
            case 1:
                zzcaVar.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzcaVar.zzc(((Float) obj).floatValue());
                return;
            case 3:
                zzcaVar.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzcaVar.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzcaVar.zzp(((Integer) obj).intValue());
                return;
            case 6:
                zzcaVar.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzcaVar.zzs(((Integer) obj).intValue());
                return;
            case 8:
                zzcaVar.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzdx) obj).zzb(zzcaVar);
                return;
            case 10:
                zzcaVar.zzb((zzdx) obj);
                return;
            case 11:
                if (obj instanceof zzbo) {
                    zzcaVar.zza((zzbo) obj);
                    return;
                } else {
                    zzcaVar.zzh((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzbo) {
                    zzcaVar.zza((zzbo) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzcaVar.zzd(bArr, 0, bArr.length);
                return;
            case 13:
                zzcaVar.zzq(((Integer) obj).intValue());
                return;
            case 14:
                zzcaVar.zzs(((Integer) obj).intValue());
                return;
            case 15:
                zzcaVar.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzcaVar.zzr(((Integer) obj).intValue());
                return;
            case 17:
                zzcaVar.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzcu) {
                    zzcaVar.zzp(((zzcu) obj).zzbn());
                    return;
                } else {
                    zzcaVar.zzp(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzbq()) {
            zza(fielddescriptortype.zzbo(), obj);
        } else if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                zza(fielddescriptortype.zzbo(), obj2);
            }
            obj = arrayList;
        }
        if (obj instanceof zzda) {
            this.zzhw = true;
        }
        this.zzhu.zza((zzeq<FieldDescriptorType, Object>) fielddescriptortype, (FieldDescriptorType) obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzcu) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzda) == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.gms.internal.vision.zzft r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.vision.zzct.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.vision.zzck.zzhy
            com.google.android.gms.internal.vision.zzfy r2 = r2.zzed()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L31;
                case 7: goto L28;
                case 8: goto L1e;
                case 9: goto L15;
                default: goto L14;
            }
        L14:
            goto L43
        L15:
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzdx
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzda
            if (r2 == 0) goto L43
            goto L26
        L1e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzcu
            if (r2 == 0) goto L43
        L26:
            r1 = r0
            goto L43
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzbo
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L43
            goto L26
        L31:
            boolean r0 = r3 instanceof java.lang.String
            goto L26
        L34:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L26
        L37:
            boolean r0 = r3 instanceof java.lang.Double
            goto L26
        L3a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L26
        L3d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L26
        L40:
            boolean r0 = r3 instanceof java.lang.Integer
            goto L26
        L43:
            if (r1 == 0) goto L46
            return
        L46:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzcj.zza(com.google.android.gms.internal.vision.zzft, java.lang.Object):void");
    }

    private static int zzb(zzcl<?> zzclVar, Object obj) {
        zzft zzbo = zzclVar.zzbo();
        int zzbn = zzclVar.zzbn();
        if (zzclVar.zzbq()) {
            int i = 0;
            List<Object> list = (List) obj;
            if (!zzclVar.zzbr()) {
                for (Object obj2 : list) {
                    i += zza(zzbo, zzbn, obj2);
                }
                return i;
            }
            for (Object obj3 : list) {
                i += zzb(zzbo, obj3);
            }
            return zzca.zzab(i) + zzca.zzt(zzbn) + i;
        }
        return zza(zzbo, zzbn, obj);
    }

    private static int zzb(zzft zzftVar, Object obj) {
        switch (zzck.zzhz[zzftVar.ordinal()]) {
            case 1:
                return zzca.zzb(((Double) obj).doubleValue());
            case 2:
                return zzca.zzd(((Float) obj).floatValue());
            case 3:
                return zzca.zze(((Long) obj).longValue());
            case 4:
                return zzca.zzf(((Long) obj).longValue());
            case 5:
                return zzca.zzu(((Integer) obj).intValue());
            case 6:
                return zzca.zzh(((Long) obj).longValue());
            case 7:
                return zzca.zzx(((Integer) obj).intValue());
            case 8:
                return zzca.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzca.zzd((zzdx) obj);
            case 10:
                return obj instanceof zzda ? zzca.zza((zzda) obj) : zzca.zzc((zzdx) obj);
            case 11:
                return obj instanceof zzbo ? zzca.zzb((zzbo) obj) : zzca.zzi((String) obj);
            case 12:
                return obj instanceof zzbo ? zzca.zzb((zzbo) obj) : zzca.zze((byte[]) obj);
            case 13:
                return zzca.zzv(((Integer) obj).intValue());
            case 14:
                return zzca.zzy(((Integer) obj).intValue());
            case 15:
                return zzca.zzi(((Long) obj).longValue());
            case 16:
                return zzca.zzw(((Integer) obj).intValue());
            case 17:
                return zzca.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzcu ? zzca.zzz(((zzcu) obj).zzbn()) : zzca.zzz(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzbp() == zzfy.MESSAGE) {
            boolean zzbq = key.zzbq();
            Object value = entry.getValue();
            if (zzbq) {
                for (zzdx zzdxVar : (List) value) {
                    if (!zzdxVar.isInitialized()) {
                        return false;
                    }
                }
            } else if (!(value instanceof zzdx)) {
                if (!(value instanceof zzda)) {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                return true;
            } else if (!((zzdx) value).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public static <T extends zzcl<T>> zzcj<T> zzbk() {
        return zzhx;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzda) {
            value = zzda.zzci();
        }
        if (key.zzbq()) {
            Object zza = zza((zzcj<FieldDescriptorType>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zze(obj));
            }
            this.zzhu.zza((zzeq<FieldDescriptorType, Object>) key, (FieldDescriptorType) zza);
        } else if (key.zzbp() != zzfy.MESSAGE) {
            this.zzhu.zza((zzeq<FieldDescriptorType, Object>) key, (FieldDescriptorType) zze(value));
        } else {
            Object zza2 = zza((zzcj<FieldDescriptorType>) key);
            if (zza2 == null) {
                this.zzhu.zza((zzeq<FieldDescriptorType, Object>) key, (FieldDescriptorType) zze(value));
            } else {
                this.zzhu.zza((zzeq<FieldDescriptorType, Object>) key, (FieldDescriptorType) (zza2 instanceof zzee ? key.zza((zzee) zza2, (zzee) value) : key.zza(((zzdx) zza2).zzbu(), (zzdx) value).zzca()));
            }
        }
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzbp() != zzfy.MESSAGE || key.zzbq() || key.zzbr()) {
            return zzb((zzcl<?>) key, value);
        }
        boolean z = value instanceof zzda;
        int zzbn = entry.getKey().zzbn();
        return z ? zzca.zzb(zzbn, (zzda) value) : zzca.zzb(zzbn, (zzdx) value);
    }

    private static Object zze(Object obj) {
        if (obj instanceof zzee) {
            return ((zzee) obj).zzcy();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzcj zzcjVar = new zzcj();
        for (int i = 0; i < this.zzhu.zzdl(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzan = this.zzhu.zzan(i);
            zzcjVar.zza((zzcj) zzan.getKey(), zzan.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzhu.zzdm()) {
            zzcjVar.zza((zzcj) entry.getKey(), entry.getValue());
        }
        zzcjVar.zzhw = this.zzhw;
        return zzcjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzhw ? new zzdd(this.zzhu.zzdn().iterator()) : this.zzhu.zzdn().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcj) {
            return this.zzhu.equals(((zzcj) obj).zzhu);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzhu.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEmpty() {
        return this.zzhu.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzhu.zzdl(); i++) {
            if (!zzb(this.zzhu.zzan(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzhu.zzdm()) {
            if (!zzb(entry)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzhw ? new zzdd(this.zzhu.entrySet().iterator()) : this.zzhu.entrySet().iterator();
    }

    public final void zza(zzcj<FieldDescriptorType> zzcjVar) {
        for (int i = 0; i < zzcjVar.zzhu.zzdl(); i++) {
            zzc(zzcjVar.zzhu.zzan(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : zzcjVar.zzhu.zzdm()) {
            zzc(entry);
        }
    }

    public final void zzao() {
        if (this.zzhv) {
            return;
        }
        this.zzhu.zzao();
        this.zzhv = true;
    }

    public final int zzbl() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzhu.zzdl(); i2++) {
            Map.Entry<FieldDescriptorType, Object> zzan = this.zzhu.zzan(i2);
            i += zzb((zzcl<?>) zzan.getKey(), zzan.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzhu.zzdm()) {
            i += zzb((zzcl<?>) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzbm() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzhu.zzdl(); i2++) {
            i += zzd(this.zzhu.zzan(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzhu.zzdm()) {
            i += zzd(entry);
        }
        return i;
    }
}
