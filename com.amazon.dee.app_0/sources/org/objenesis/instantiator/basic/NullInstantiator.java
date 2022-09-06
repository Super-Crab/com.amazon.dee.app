package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public class NullInstantiator<T> implements ObjectInstantiator<T> {
    public NullInstantiator(Class<T> cls) {
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        return null;
    }
}
