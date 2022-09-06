package org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
/* loaded from: classes5.dex */
public class AccessibleInstantiator<T> extends ConstructorInstantiator<T> {
    public AccessibleInstantiator(Class<T> cls) {
        super(cls);
        Constructor<T> constructor = this.constructor;
        if (constructor != null) {
            constructor.setAccessible(true);
        }
    }
}
