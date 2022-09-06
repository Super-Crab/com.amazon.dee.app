package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformingComparator<I, O> implements Comparator<I>, Serializable {
    private static final long serialVersionUID = 3456940356043606220L;
    private final Comparator<O> decorated;
    private final Transformer<? super I, ? extends O> transformer;

    public TransformingComparator(Transformer<? super I, ? extends O> transformer) {
        this(transformer, ComparatorUtils.NATURAL_COMPARATOR);
    }

    @Override // java.util.Comparator
    public int compare(I i, I i2) {
        return this.decorated.compare(this.transformer.mo12738transform(i), this.transformer.mo12738transform(i2));
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(TransformingComparator.class)) {
            return false;
        }
        TransformingComparator transformingComparator = (TransformingComparator) obj;
        Comparator<O> comparator = this.decorated;
        if (comparator != null ? comparator.equals(transformingComparator.decorated) : transformingComparator.decorated == null) {
            Transformer<? super I, ? extends O> transformer = this.transformer;
            Transformer<? super I, ? extends O> transformer2 = transformingComparator.transformer;
            if (transformer == null) {
                if (transformer2 == null) {
                    return true;
                }
            } else if (transformer.equals(transformer2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Comparator<O> comparator = this.decorated;
        int i = 0;
        int hashCode = (629 + (comparator == null ? 0 : comparator.hashCode())) * 37;
        Transformer<? super I, ? extends O> transformer = this.transformer;
        if (transformer != null) {
            i = transformer.hashCode();
        }
        return hashCode + i;
    }

    public TransformingComparator(Transformer<? super I, ? extends O> transformer, Comparator<O> comparator) {
        this.decorated = comparator;
        this.transformer = transformer;
    }
}
