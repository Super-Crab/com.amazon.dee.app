package com.google.android.gms.internal.vision;

import java.util.Iterator;
/* loaded from: classes2.dex */
final class zzfk implements Iterator<String> {
    private final /* synthetic */ zzfi zzoy;
    private Iterator<String> zzoz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfk(zzfi zzfiVar) {
        zzdg zzdgVar;
        this.zzoy = zzfiVar;
        zzdgVar = this.zzoy.zzov;
        this.zzoz = zzdgVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzoz.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzoz.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
