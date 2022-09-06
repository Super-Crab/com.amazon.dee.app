package org.objenesis.instantiator.android;

import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public class Android17Instantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod = getNewInstanceMethod();
    private final Integer objectConstructorId = findConstructorIdForJavaLangObjectConstructor();
    private final Class<T> type;

    public Android17Instantiator(Class<T> cls) {
        this.type = cls;
    }

    private static Integer findConstructorIdForJavaLangObjectConstructor() {
        try {
            Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
            declaredMethod.setAccessible(true);
            return (Integer) declaredMethod.invoke(null, Object.class);
        } catch (IllegalAccessException e) {
            throw new ObjenesisException(e);
        } catch (NoSuchMethodException e2) {
            throw new ObjenesisException(e2);
        } catch (RuntimeException e3) {
            throw new ObjenesisException(e3);
        } catch (InvocationTargetException e4) {
            throw new ObjenesisException(e4);
        }
    }

    private static Method getNewInstanceMethod() {
        try {
            Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException(e);
        } catch (RuntimeException e2) {
            throw new ObjenesisException(e2);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return this.type.cast(this.newInstanceMethod.invoke(null, this.type, this.objectConstructorId));
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
