package org.objenesis;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ConcurrentHashMap;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;
/* loaded from: classes5.dex */
public class ObjenesisBase implements Objenesis {
    protected ConcurrentHashMap<String, ObjectInstantiator<?>> cache;
    protected final InstantiatorStrategy strategy;

    public ObjenesisBase(InstantiatorStrategy instantiatorStrategy) {
        this(instantiatorStrategy, true);
    }

    public ObjenesisBase(InstantiatorStrategy instantiatorStrategy, boolean z) {
        if (instantiatorStrategy != null) {
            this.strategy = instantiatorStrategy;
            this.cache = z ? new ConcurrentHashMap<>() : null;
            return;
        }
        throw new IllegalArgumentException("A strategy can't be null");
    }

    @Override // org.objenesis.Objenesis
    public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls) {
        ConcurrentHashMap<String, ObjectInstantiator<?>> concurrentHashMap = this.cache;
        if (concurrentHashMap == null) {
            return this.strategy.newInstantiatorOf(cls);
        }
        ObjectInstantiator<T> objectInstantiator = (ObjectInstantiator<T>) concurrentHashMap.get(cls.getName());
        if (objectInstantiator != null) {
            return objectInstantiator;
        }
        ObjectInstantiator<T> newInstantiatorOf = this.strategy.newInstantiatorOf(cls);
        ObjectInstantiator<T> objectInstantiator2 = (ObjectInstantiator<T>) this.cache.putIfAbsent(cls.getName(), newInstantiatorOf);
        return objectInstantiator2 == null ? newInstantiatorOf : objectInstantiator2;
    }

    @Override // org.objenesis.Objenesis
    public <T> T newInstance(Class<T> cls) {
        return getInstantiatorOf(cls).newInstance();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" using ");
        sb.append(this.strategy.getClass().getName());
        return GeneratedOutlineSupport1.outline91(sb, this.cache == null ? " without" : " with", " caching");
    }
}
