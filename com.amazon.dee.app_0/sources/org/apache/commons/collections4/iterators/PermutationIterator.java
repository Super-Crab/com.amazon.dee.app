package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: classes4.dex */
public class PermutationIterator<E> implements Iterator<List<E>> {
    private final boolean[] direction;
    private final int[] keys;
    private List<E> nextPermutation;
    private final Map<Integer, E> objectMap;

    public PermutationIterator(Collection<? extends E> collection) {
        if (collection != null) {
            this.keys = new int[collection.size()];
            this.direction = new boolean[collection.size()];
            Arrays.fill(this.direction, false);
            this.objectMap = new HashMap();
            int i = 1;
            for (E e : collection) {
                this.objectMap.put(Integer.valueOf(i), e);
                this.keys[i - 1] = i;
                i++;
            }
            this.nextPermutation = new ArrayList(collection);
            return;
        }
        throw new NullPointerException("The collection must not be null");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.nextPermutation != null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override // java.util.Iterator
    public List<E> next() {
        boolean[] zArr;
        if (hasNext()) {
            int i = 0;
            int i2 = -1;
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            while (true) {
                int[] iArr = this.keys;
                if (i3 >= iArr.length) {
                    break;
                }
                if (!this.direction[i3] || i3 >= iArr.length - 1 || iArr[i3] <= iArr[i3 + 1]) {
                    if (!this.direction[i3] && i3 > 0) {
                        int[] iArr2 = this.keys;
                        if (iArr2[i3] <= iArr2[i3 - 1]) {
                        }
                    }
                    i3++;
                }
                int[] iArr3 = this.keys;
                if (iArr3[i3] > i4) {
                    i4 = iArr3[i3];
                    i5 = i3;
                }
                i3++;
            }
            if (i4 == -1) {
                List<E> list = this.nextPermutation;
                this.nextPermutation = null;
                return list;
            }
            if (this.direction[i5]) {
                i2 = 1;
            }
            int[] iArr4 = this.keys;
            int i6 = iArr4[i5];
            int i7 = i2 + i5;
            iArr4[i5] = iArr4[i7];
            iArr4[i7] = i6;
            boolean[] zArr2 = this.direction;
            boolean z = zArr2[i5];
            zArr2[i5] = zArr2[i7];
            zArr2[i7] = z;
            ArrayList arrayList = new ArrayList();
            while (true) {
                int[] iArr5 = this.keys;
                if (i < iArr5.length) {
                    if (iArr5[i] > i4) {
                        this.direction[i] = !zArr[i];
                    }
                    arrayList.add(this.objectMap.get(Integer.valueOf(this.keys[i])));
                    i++;
                } else {
                    List<E> list2 = this.nextPermutation;
                    this.nextPermutation = arrayList;
                    return list2;
                }
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
