package com.google.android.gms.internal.vision;

import java.util.ListIterator;
/* loaded from: classes2.dex */
final class zzfj implements ListIterator<String> {
    private ListIterator<String> zzow;
    private final /* synthetic */ int zzox;
    private final /* synthetic */ zzfi zzoy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfj(zzfi zzfiVar, int i) {
        zzdg zzdgVar;
        this.zzoy = zzfiVar;
        this.zzox = i;
        zzdgVar = this.zzoy.zzov;
        this.zzow = zzdgVar.listIterator(this.zzox);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzow.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzow.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzow.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzow.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzow.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzow.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }
}
