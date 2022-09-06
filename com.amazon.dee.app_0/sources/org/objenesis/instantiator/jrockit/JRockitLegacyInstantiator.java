package org.objenesis.instantiator.jrockit;

import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public class JRockitLegacyInstantiator<T> implements ObjectInstantiator<T> {
    private static Method safeAllocObjectMethod;
    private final Class<T> type;

    public JRockitLegacyInstantiator(Class<T> cls) {
        initialize();
        this.type = cls;
    }

    private static void initialize() {
        if (safeAllocObjectMethod == null) {
            try {
                safeAllocObjectMethod = Class.forName("jrockit.vm.MemSystem").getDeclaredMethod("safeAllocObject", Class.class);
                safeAllocObjectMethod.setAccessible(true);
            } catch (ClassNotFoundException e) {
                throw new ObjenesisException(e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException(e2);
            } catch (RuntimeException e3) {
                throw new ObjenesisException(e3);
            }
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return this.type.cast(safeAllocObjectMethod.invoke(null, this.type));
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
