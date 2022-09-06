package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class CloneTransformer<T> implements Transformer<T, T> {
    public static final Transformer INSTANCE = new CloneTransformer();

    private CloneTransformer() {
    }

    public static <T> Transformer<T, T> cloneTransformer() {
        return INSTANCE;
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public T mo12738transform(T t) {
        if (t == null) {
            return null;
        }
        return (T) PrototypeFactory.prototypeFactory(t).mo12724create();
    }
}
