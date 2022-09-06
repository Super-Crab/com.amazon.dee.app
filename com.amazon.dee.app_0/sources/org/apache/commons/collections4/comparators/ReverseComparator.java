package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.ComparatorUtils;
/* loaded from: classes4.dex */
public class ReverseComparator<E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = 2858887242028539265L;
    private final Comparator<? super E> comparator;

    public ReverseComparator() {
        this(null);
    }

    @Override // java.util.Comparator
    public int compare(E e, E e2) {
        return this.comparator.compare(e2, e);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(ReverseComparator.class)) {
            return false;
        }
        return this.comparator.equals(((ReverseComparator) obj).comparator);
    }

    public int hashCode() {
        return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
    }

    public ReverseComparator(Comparator<? super E> comparator) {
        this.comparator = comparator == null ? ComparatorUtils.NATURAL_COMPARATOR : comparator;
    }
}
