package org.objenesis.strategy;

import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public interface InstantiatorStrategy {
    <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls);
}
