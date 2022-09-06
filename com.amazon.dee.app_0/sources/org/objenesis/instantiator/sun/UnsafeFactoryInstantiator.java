package org.objenesis.instantiator.sun;

import java.lang.reflect.Field;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import sun.misc.Unsafe;
/* loaded from: classes5.dex */
public class UnsafeFactoryInstantiator<T> implements ObjectInstantiator<T> {
    private static Unsafe unsafe;
    private final Class<T> type;

    public UnsafeFactoryInstantiator(Class<T> cls) {
        if (unsafe == null) {
            try {
                Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                try {
                    unsafe = (Unsafe) declaredField.get(null);
                } catch (IllegalAccessException e) {
                    throw new ObjenesisException(e);
                }
            } catch (NoSuchFieldException e2) {
                throw new ObjenesisException(e2);
            }
        }
        this.type = cls;
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return this.type.cast(unsafe.allocateInstance(this.type));
        } catch (InstantiationException e) {
            throw new ObjenesisException(e);
        }
    }
}
