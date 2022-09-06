package com.google.android.gms.common.data;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;
@KeepForSdk
/* loaded from: classes2.dex */
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> zalk;
    protected int zall = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.zalk = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zall < this.zalk.getCount() - 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            DataBuffer<T> dataBuffer = this.zalk;
            int i = this.zall + 1;
            this.zall = i;
            return dataBuffer.mo7472get(i);
        }
        throw new NoSuchElementException(GeneratedOutlineSupport1.outline27(46, "Cannot advance the iterator beyond ", this.zall));
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
