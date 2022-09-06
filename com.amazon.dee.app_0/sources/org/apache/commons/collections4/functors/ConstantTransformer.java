package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class ConstantTransformer<I, O> implements Transformer<I, O>, Serializable {
    public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
    private static final long serialVersionUID = 6374440726369055124L;
    private final O iConstant;

    public ConstantTransformer(O o) {
        this.iConstant = o;
    }

    public static <I, O> Transformer<I, O> constantTransformer(O o) {
        if (o == null) {
            return nullTransformer();
        }
        return new ConstantTransformer(o);
    }

    public static <I, O> Transformer<I, O> nullTransformer() {
        return NULL_INSTANCE;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConstantTransformer)) {
            return false;
        }
        Object constant = ((ConstantTransformer) obj).getConstant();
        if (constant == getConstant()) {
            return true;
        }
        return constant != null && constant.equals(getConstant());
    }

    public O getConstant() {
        return this.iConstant;
    }

    public int hashCode() {
        int hashCode = "ConstantTransformer".hashCode() << 2;
        return getConstant() != null ? hashCode | getConstant().hashCode() : hashCode;
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public O mo12738transform(I i) {
        return this.iConstant;
    }
}
