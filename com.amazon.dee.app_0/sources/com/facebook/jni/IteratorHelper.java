package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;
import java.util.Iterator;
import javax.annotation.Nullable;
@DoNotStrip
/* loaded from: classes2.dex */
public class IteratorHelper {
    @DoNotStrip
    @Nullable
    private Object mElement;
    private final Iterator mIterator;

    @DoNotStrip
    public IteratorHelper(Iterator it2) {
        this.mIterator = it2;
    }

    @DoNotStrip
    boolean hasNext() {
        if (this.mIterator.hasNext()) {
            this.mElement = this.mIterator.next();
            return true;
        }
        this.mElement = null;
        return false;
    }

    @DoNotStrip
    public IteratorHelper(Iterable iterable) {
        this.mIterator = iterable.iterator();
    }
}
