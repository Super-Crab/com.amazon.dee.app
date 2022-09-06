package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzes implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzol;
    private final /* synthetic */ zzeq zzom;

    private zzes(zzeq zzeqVar) {
        List list;
        this.zzom = zzeqVar;
        list = this.zzom.zzog;
        this.pos = list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzes(zzeq zzeqVar, zzer zzerVar) {
        this(zzeqVar);
    }

    private final Iterator<Map.Entry<K, V>> zzdq() {
        Map map;
        if (this.zzol == null) {
            map = this.zzom.zzoj;
            this.zzol = map.entrySet().iterator();
        }
        return this.zzol;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        int i = this.pos;
        if (i > 0) {
            list = this.zzom.zzog;
            if (i <= list.size()) {
                return true;
            }
        }
        return zzdq().hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        Object obj;
        if (zzdq().hasNext()) {
            obj = zzdq().next();
        } else {
            list = this.zzom.zzog;
            int i = this.pos - 1;
            this.pos = i;
            obj = list.get(i);
        }
        return (Map.Entry) obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
