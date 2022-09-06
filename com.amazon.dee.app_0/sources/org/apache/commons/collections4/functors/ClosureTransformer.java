package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class ClosureTransformer<T> implements Transformer<T, T>, Serializable {
    private static final long serialVersionUID = 478466901448617286L;
    private final Closure<? super T> iClosure;

    public ClosureTransformer(Closure<? super T> closure) {
        this.iClosure = closure;
    }

    public static <T> Transformer<T, T> closureTransformer(Closure<? super T> closure) {
        if (closure != null) {
            return new ClosureTransformer(closure);
        }
        throw new NullPointerException("Closure must not be null");
    }

    public Closure<? super T> getClosure() {
        return this.iClosure;
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public T mo12738transform(T t) {
        this.iClosure.execute(t);
        return t;
    }
}
