package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
/* loaded from: classes4.dex */
public class WhileClosure<E> implements Closure<E> {
    private final Closure<? super E> iClosure;
    private final boolean iDoLoop;
    private final Predicate<? super E> iPredicate;

    public WhileClosure(Predicate<? super E> predicate, Closure<? super E> closure, boolean z) {
        this.iPredicate = predicate;
        this.iClosure = closure;
        this.iDoLoop = z;
    }

    public static <E> Closure<E> whileClosure(Predicate<? super E> predicate, Closure<? super E> closure, boolean z) {
        if (predicate != null) {
            if (closure != null) {
                return new WhileClosure(predicate, closure, z);
            }
            throw new NullPointerException("Closure must not be null");
        }
        throw new NullPointerException("Predicate must not be null");
    }

    @Override // org.apache.commons.collections4.Closure
    public void execute(E e) {
        if (this.iDoLoop) {
            this.iClosure.execute(e);
        }
        while (this.iPredicate.evaluate(e)) {
            this.iClosure.execute(e);
        }
    }

    public Closure<? super E> getClosure() {
        return this.iClosure;
    }

    public Predicate<? super E> getPredicate() {
        return this.iPredicate;
    }

    public boolean isDoLoop() {
        return this.iDoLoop;
    }
}
