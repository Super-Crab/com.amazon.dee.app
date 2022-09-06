package org.apache.commons.collections4.iterators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;
/* loaded from: classes4.dex */
public class ArrayIterator<E> implements ResettableIterator<E> {
    final Object array;
    final int endIndex;
    int index;
    final int startIndex;

    public ArrayIterator(Object obj) {
        this(obj, 0);
    }

    protected void checkBound(int i, int i2, String str) {
        if (i <= i2) {
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline75("Attempt to make an ArrayIterator that ", str, "s before the start of the array. "));
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline75("Attempt to make an ArrayIterator that ", str, "s beyond the end of the array. "));
    }

    public Object getArray() {
        return this.array;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.endIndex;
    }

    @Override // java.util.Iterator
    public E next() {
        if (hasNext()) {
            Object obj = this.array;
            int i = this.index;
            this.index = i + 1;
            return (E) Array.get(obj, i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        this.index = this.startIndex;
    }

    public ArrayIterator(Object obj, int i) {
        this(obj, i, Array.getLength(obj));
    }

    public ArrayIterator(Object obj, int i, int i2) {
        this.index = 0;
        this.array = obj;
        this.startIndex = i;
        this.endIndex = i2;
        this.index = i;
        int length = Array.getLength(obj);
        checkBound(i, length, "start");
        checkBound(i2, length, "end");
        if (i2 >= i) {
            return;
        }
        throw new IllegalArgumentException("End index must not be less than start index.");
    }
}
