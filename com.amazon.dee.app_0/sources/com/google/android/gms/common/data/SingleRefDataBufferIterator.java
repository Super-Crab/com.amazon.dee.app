package com.google.android.gms.common.data;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.NoSuchElementException;
@KeepForSdk
/* loaded from: classes2.dex */
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zamg;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    public T next() {
        if (hasNext()) {
            this.zall++;
            int i = this.zall;
            if (i == 0) {
                this.zamg = this.zalk.mo7472get(0);
                T t = this.zamg;
                if (!(t instanceof DataBufferRef)) {
                    String valueOf = String.valueOf(t.getClass());
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline30(valueOf.length() + 44, "DataBuffer reference of type ", valueOf, " is not movable"));
                }
            } else {
                ((DataBufferRef) this.zamg).zag(i);
            }
            return this.zamg;
        }
        throw new NoSuchElementException(GeneratedOutlineSupport1.outline27(46, "Cannot advance the iterator beyond ", this.zall));
    }
}
