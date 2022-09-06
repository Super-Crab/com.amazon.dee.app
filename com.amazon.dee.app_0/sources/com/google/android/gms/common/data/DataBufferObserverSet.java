package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes2.dex */
public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {
    private HashSet<DataBufferObserver> zalm = new HashSet<>();

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void addObserver(DataBufferObserver dataBufferObserver) {
        this.zalm.add(dataBufferObserver);
    }

    public final void clear() {
        this.zalm.clear();
    }

    public final boolean hasObservers() {
        return !this.zalm.isEmpty();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataChanged() {
        Iterator<DataBufferObserver> it2 = this.zalm.iterator();
        while (it2.hasNext()) {
            it2.next().onDataChanged();
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeChanged(int i, int i2) {
        Iterator<DataBufferObserver> it2 = this.zalm.iterator();
        while (it2.hasNext()) {
            it2.next().onDataRangeChanged(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeInserted(int i, int i2) {
        Iterator<DataBufferObserver> it2 = this.zalm.iterator();
        while (it2.hasNext()) {
            it2.next().onDataRangeInserted(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeMoved(int i, int i2, int i3) {
        Iterator<DataBufferObserver> it2 = this.zalm.iterator();
        while (it2.hasNext()) {
            it2.next().onDataRangeMoved(i, i2, i3);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeRemoved(int i, int i2) {
        Iterator<DataBufferObserver> it2 = this.zalm.iterator();
        while (it2.hasNext()) {
            it2.next().onDataRangeRemoved(i, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void removeObserver(DataBufferObserver dataBufferObserver) {
        this.zalm.remove(dataBufferObserver);
    }
}
