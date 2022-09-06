package com.facebook.common.references;

import java.lang.ref.SoftReference;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class OOMSoftReference<T> {
    @Nullable
    SoftReference<T> softRef1 = null;
    @Nullable
    SoftReference<T> softRef2 = null;
    @Nullable
    SoftReference<T> softRef3 = null;

    public void clear() {
        SoftReference<T> softReference = this.softRef1;
        if (softReference != null) {
            softReference.clear();
            this.softRef1 = null;
        }
        SoftReference<T> softReference2 = this.softRef2;
        if (softReference2 != null) {
            softReference2.clear();
            this.softRef2 = null;
        }
        SoftReference<T> softReference3 = this.softRef3;
        if (softReference3 != null) {
            softReference3.clear();
            this.softRef3 = null;
        }
    }

    @Nullable
    public T get() {
        SoftReference<T> softReference = this.softRef1;
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public void set(T hardReference) {
        this.softRef1 = new SoftReference<>(hardReference);
        this.softRef2 = new SoftReference<>(hardReference);
        this.softRef3 = new SoftReference<>(hardReference);
    }
}
