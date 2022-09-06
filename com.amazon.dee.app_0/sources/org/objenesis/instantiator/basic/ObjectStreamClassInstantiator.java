package org.objenesis.instantiator.basic;

import java.io.ObjectStreamClass;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public class ObjectStreamClassInstantiator<T> implements ObjectInstantiator<T> {
    private static Method newInstanceMethod;
    private final ObjectStreamClass objStreamClass;

    public ObjectStreamClassInstantiator(Class<T> cls) {
        initialize();
        this.objStreamClass = ObjectStreamClass.lookup(cls);
    }

    private static void initialize() {
        if (newInstanceMethod == null) {
            try {
                newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[0]);
                newInstanceMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new ObjenesisException(e);
            } catch (RuntimeException e2) {
                throw new ObjenesisException(e2);
            }
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) newInstanceMethod.invoke(this.objStreamClass, new Object[0]);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
