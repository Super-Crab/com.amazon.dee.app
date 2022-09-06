package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class IfTransformer<I, O> implements Transformer<I, O>, Serializable {
    private static final long serialVersionUID = 8069309411242014252L;
    private final Transformer<? super I, ? extends O> iFalseTransformer;
    private final Predicate<? super I> iPredicate;
    private final Transformer<? super I, ? extends O> iTrueTransformer;

    public IfTransformer(Predicate<? super I> predicate, Transformer<? super I, ? extends O> transformer, Transformer<? super I, ? extends O> transformer2) {
        this.iPredicate = predicate;
        this.iTrueTransformer = transformer;
        this.iFalseTransformer = transformer2;
    }

    public static <I, O> Transformer<I, O> ifTransformer(Predicate<? super I> predicate, Transformer<? super I, ? extends O> transformer, Transformer<? super I, ? extends O> transformer2) {
        if (predicate != null) {
            if (transformer != null && transformer2 != null) {
                return new IfTransformer(predicate, transformer, transformer2);
            }
            throw new NullPointerException("Transformers must not be null");
        }
        throw new NullPointerException("Predicate must not be null");
    }

    public Transformer<? super I, ? extends O> getFalseTransformer() {
        return this.iFalseTransformer;
    }

    public Predicate<? super I> getPredicate() {
        return this.iPredicate;
    }

    public Transformer<? super I, ? extends O> getTrueTransformer() {
        return this.iTrueTransformer;
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public O mo12738transform(I i) {
        if (this.iPredicate.evaluate(i)) {
            return this.iTrueTransformer.mo12738transform(i);
        }
        return this.iFalseTransformer.mo12738transform(i);
    }

    public static <T> Transformer<T, T> ifTransformer(Predicate<? super T> predicate, Transformer<? super T, ? extends T> transformer) {
        if (predicate != null) {
            if (transformer != null) {
                return new IfTransformer(predicate, transformer, NOPTransformer.nopTransformer());
            }
            throw new NullPointerException("Transformer must not be null");
        }
        throw new NullPointerException("Predicate must not be null");
    }
}
