package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzdt implements zzds {
    @Override // com.google.android.gms.internal.vision.zzds
    public final int zzb(int i, Object obj, Object obj2) {
        zzdr zzdrVar = (zzdr) obj;
        if (zzdrVar.isEmpty()) {
            return 0;
        }
        Iterator it2 = zzdrVar.entrySet().iterator();
        if (!it2.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it2.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final Object zzb(Object obj, Object obj2) {
        zzdr zzdrVar = (zzdr) obj;
        zzdr zzdrVar2 = (zzdr) obj2;
        if (!zzdrVar2.isEmpty()) {
            if (!zzdrVar.isMutable()) {
                zzdrVar = zzdrVar.zzcq();
            }
            zzdrVar.zza(zzdrVar2);
        }
        return zzdrVar;
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final Map<?, ?> zzh(Object obj) {
        return (zzdr) obj;
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final Map<?, ?> zzi(Object obj) {
        return (zzdr) obj;
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final boolean zzj(Object obj) {
        return !((zzdr) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final Object zzk(Object obj) {
        ((zzdr) obj).zzao();
        return obj;
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final Object zzl(Object obj) {
        return zzdr.zzcp().zzcq();
    }

    @Override // com.google.android.gms.internal.vision.zzds
    public final zzdq<?, ?> zzm(Object obj) {
        throw new NoSuchMethodError();
    }
}
