package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzdd<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzmh;

    public zzdd(Iterator<Map.Entry<K, Object>> it2) {
        this.zzmh = it2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzmh.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.zzmh.next();
        return next.getValue() instanceof zzda ? new zzdc(next) : next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzmh.remove();
    }
}
