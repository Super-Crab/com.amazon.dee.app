package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.FactoryTransformer;
/* loaded from: classes4.dex */
public class LazyMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 7990956402564206740L;
    protected final Transformer<? super K, ? extends V> factory;

    /* JADX INFO: Access modifiers changed from: protected */
    public LazyMap(Map<K, V> map, Factory<? extends V> factory) {
        super(map);
        if (factory != null) {
            this.factory = FactoryTransformer.factoryTransformer(factory);
            return;
        }
        throw new NullPointerException("Factory must not be null");
    }

    public static <K, V> LazyMap<K, V> lazyMap(Map<K, V> map, Factory<? extends V> factory) {
        return new LazyMap<>(map, factory);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        if (!this.map.containsKey(obj)) {
            V mo12738transform = this.factory.mo12738transform(obj);
            this.map.put(obj, mo12738transform);
            return mo12738transform;
        }
        return this.map.get(obj);
    }

    public static <V, K> LazyMap<K, V> lazyMap(Map<K, V> map, Transformer<? super K, ? extends V> transformer) {
        return new LazyMap<>(map, transformer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LazyMap(Map<K, V> map, Transformer<? super K, ? extends V> transformer) {
        super(map);
        if (transformer != null) {
            this.factory = transformer;
            return;
        }
        throw new NullPointerException("Factory must not be null");
    }
}
