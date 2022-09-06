package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes4.dex */
public class ComparatorChain<E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = -721644942746081630L;
    private final List<Comparator<E>> comparatorChain;
    private boolean isLocked;
    private BitSet orderingBits;

    public ComparatorChain() {
        this(new ArrayList(), new BitSet());
    }

    private void checkChainIntegrity() {
        if (this.comparatorChain.size() != 0) {
            return;
        }
        throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
    }

    private void checkLocked() {
        if (!this.isLocked) {
            return;
        }
        throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
    }

    public void addComparator(Comparator<E> comparator) {
        addComparator(comparator, false);
    }

    @Override // java.util.Comparator
    public int compare(E e, E e2) throws UnsupportedOperationException {
        if (!this.isLocked) {
            checkChainIntegrity();
            this.isLocked = true;
        }
        int i = 0;
        for (Comparator<E> comparator : this.comparatorChain) {
            int compare = comparator.compare(e, e2);
            if (compare != 0) {
                return this.orderingBits.get(i) ? compare > 0 ? -1 : 1 : compare;
            }
            i++;
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(ComparatorChain.class)) {
            return false;
        }
        ComparatorChain comparatorChain = (ComparatorChain) obj;
        BitSet bitSet = this.orderingBits;
        if (bitSet != null ? bitSet.equals(comparatorChain.orderingBits) : comparatorChain.orderingBits == null) {
            List<Comparator<E>> list = this.comparatorChain;
            List<Comparator<E>> list2 = comparatorChain.comparatorChain;
            if (list == null) {
                if (list2 == null) {
                    return true;
                }
            } else if (list.equals(list2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        List<Comparator<E>> list = this.comparatorChain;
        int i = 0;
        if (list != null) {
            i = 0 ^ list.hashCode();
        }
        BitSet bitSet = this.orderingBits;
        return bitSet != null ? i ^ bitSet.hashCode() : i;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setComparator(int i, Comparator<E> comparator) throws IndexOutOfBoundsException {
        setComparator(i, comparator, false);
    }

    public void setForwardSort(int i) {
        checkLocked();
        this.orderingBits.clear(i);
    }

    public void setReverseSort(int i) {
        checkLocked();
        this.orderingBits.set(i);
    }

    public int size() {
        return this.comparatorChain.size();
    }

    public ComparatorChain(Comparator<E> comparator) {
        this((Comparator) comparator, false);
    }

    public void addComparator(Comparator<E> comparator, boolean z) {
        checkLocked();
        this.comparatorChain.add(comparator);
        if (z) {
            this.orderingBits.set(this.comparatorChain.size() - 1);
        }
    }

    public void setComparator(int i, Comparator<E> comparator, boolean z) {
        checkLocked();
        this.comparatorChain.set(i, comparator);
        if (z) {
            this.orderingBits.set(i);
        } else {
            this.orderingBits.clear(i);
        }
    }

    public ComparatorChain(Comparator<E> comparator, boolean z) {
        this.orderingBits = null;
        this.isLocked = false;
        this.comparatorChain = new ArrayList(1);
        this.comparatorChain.add(comparator);
        this.orderingBits = new BitSet(1);
        if (z) {
            this.orderingBits.set(0);
        }
    }

    public ComparatorChain(List<Comparator<E>> list) {
        this(list, new BitSet(list.size()));
    }

    public ComparatorChain(List<Comparator<E>> list, BitSet bitSet) {
        this.orderingBits = null;
        this.isLocked = false;
        this.comparatorChain = list;
        this.orderingBits = bitSet;
    }
}
