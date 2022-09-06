package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
final class zzdj extends zzdh {
    private static final Class<?> zzmq = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzdj() {
        super();
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzfl.zzo(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdh
    public final void zza(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzfl.zzo(obj, j);
        if (list instanceof zzdg) {
            unmodifiableList = ((zzdg) list).zzcl();
        } else if (zzmq.isAssignableFrom(list.getClass())) {
            return;
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzfl.zza(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzdh
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzdf zzdfVar;
        List zzb = zzb(obj2, j);
        int size = zzb.size();
        List zzb2 = zzb(obj, j);
        if (zzb2.isEmpty()) {
            zzb2 = zzb2 instanceof zzdg ? new zzdf(size) : new ArrayList(size);
            zzfl.zza(obj, j, zzb2);
        } else {
            if (zzmq.isAssignableFrom(zzb2.getClass())) {
                ArrayList arrayList = new ArrayList(zzb2.size() + size);
                arrayList.addAll(zzb2);
                zzdfVar = arrayList;
            } else if (zzb2 instanceof zzfi) {
                zzdf zzdfVar2 = new zzdf(zzb2.size() + size);
                zzdfVar2.addAll((zzfi) zzb2);
                zzdfVar = zzdfVar2;
            }
            zzfl.zza(obj, j, zzdfVar);
            zzb2 = zzdfVar;
        }
        int size2 = zzb2.size();
        int size3 = zzb.size();
        if (size2 > 0 && size3 > 0) {
            zzb2.addAll(zzb);
        }
        if (size2 > 0) {
            zzb = zzb2;
        }
        zzfl.zza(obj, j, zzb);
    }
}
