package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzey implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzol;
    private final /* synthetic */ zzeq zzom;
    private boolean zzoq;

    private zzey(zzeq zzeqVar) {
        this.zzom = zzeqVar;
        this.pos = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzey(zzeq zzeqVar, zzer zzerVar) {
        this(zzeqVar);
    }

    private final Iterator<Map.Entry<K, V>> zzdq() {
        Map map;
        if (this.zzol == null) {
            map = this.zzom.zzoh;
            this.zzol = map.entrySet().iterator();
        }
        return this.zzol;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i = this.pos + 1;
        list = this.zzom.zzog;
        if (i >= list.size()) {
            map = this.zzom.zzoh;
            if (map.isEmpty() || !zzdq().hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        Object next;
        List list2;
        this.zzoq = true;
        int i = this.pos + 1;
        this.pos = i;
        list = this.zzom.zzog;
        if (i < list.size()) {
            list2 = this.zzom.zzog;
            next = list2.get(this.pos);
        } else {
            next = zzdq().next();
        }
        return (Map.Entry) next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (this.zzoq) {
            this.zzoq = false;
            this.zzom.zzdo();
            int i = this.pos;
            list = this.zzom.zzog;
            if (i >= list.size()) {
                zzdq().remove();
                return;
            }
            zzeq zzeqVar = this.zzom;
            int i2 = this.pos;
            this.pos = i2 - 1;
            zzeqVar.zzao(i2);
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
