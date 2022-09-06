package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class PredicateTransformer<T> implements Transformer<T, Boolean>, Serializable {
    private static final long serialVersionUID = 5278818408044349346L;
    private final Predicate<? super T> iPredicate;

    public PredicateTransformer(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    public static <T> Transformer<T, Boolean> predicateTransformer(Predicate<? super T> predicate) {
        if (predicate != null) {
            return new PredicateTransformer(predicate);
        }
        throw new IllegalArgumentException("Predicate must not be null");
    }

    public Predicate<? super T> getPredicate() {
        return this.iPredicate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Boolean mo12738transform(Object obj) {
        return mo12738transform((PredicateTransformer<T>) obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public Boolean mo12738transform(T t) {
        return Boolean.valueOf(this.iPredicate.evaluate(t));
    }
}
