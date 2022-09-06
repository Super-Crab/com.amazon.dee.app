package org.apache.commons.collections4.map;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
@Deprecated
/* loaded from: classes4.dex */
public class MultiValueMap<K, V> extends AbstractMapDecorator<K, Object> implements MultiMap<K, V>, Serializable {
    private static final long serialVersionUID = -2214159910087182007L;
    private final Factory<? extends Collection<V>> collectionFactory;
    private transient Collection<V> valuesView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class ReflectionFactory<T extends Collection<?>> implements Factory<T>, Serializable {
        private static final long serialVersionUID = 2986114157496788874L;
        private final Class<T> clazz;

        public ReflectionFactory(Class<T> cls) {
            this.clazz = cls;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Class<T> cls = this.clazz;
            if (cls == null || Collection.class.isAssignableFrom(cls)) {
                return;
            }
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.commons.collections4.Factory
        /* renamed from: create  reason: collision with other method in class */
        public T mo12724create() {
            try {
                return this.clazz.newInstance();
            } catch (Exception e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot instantiate class: ");
                outline107.append(this.clazz);
                throw new FunctorException(outline107.toString(), e);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MultiValueMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            IteratorChain iteratorChain = new IteratorChain();
            for (K k : MultiValueMap.this.keySet()) {
                iteratorChain.addIterator(new ValuesIterator(k));
            }
            return iteratorChain;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MultiValueMap.this.totalSize();
        }
    }

    /* loaded from: classes4.dex */
    private class ValuesIterator implements Iterator<V> {
        private final Iterator<V> iterator;
        private final Object key;
        private final Collection<V> values;

        public ValuesIterator(Object obj) {
            this.key = obj;
            this.values = MultiValueMap.this.getCollection(obj);
            this.iterator = this.values.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                MultiValueMap.this.mo12668remove(this.key);
            }
        }
    }

    public MultiValueMap() {
        this(new HashMap(), new ReflectionFactory(ArrayList.class));
    }

    public static <K, V> MultiValueMap<K, V> multiValueMap(Map<K, ? super Collection<V>> map) {
        return multiValueMap(map, ArrayList.class);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        mo12722decorated().clear();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        Set<Map.Entry<K, V>> entrySet = mo12722decorated().entrySet();
        if (entrySet != null) {
            for (Map.Entry<K, V> entry : entrySet) {
                if (((Collection) entry.getValue()).contains(obj)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    protected Collection<V> createCollection(int i) {
        return this.collectionFactory.mo12724create();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, Object>> entrySet() {
        return super.entrySet();
    }

    public Collection<V> getCollection(Object obj) {
        return (Collection) mo12722decorated().get(obj);
    }

    public Iterator<V> iterator(Object obj) {
        if (!containsKey(obj)) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public Object put(K k, Object obj) {
        Collection<V> collection = getCollection(k);
        boolean z = true;
        if (collection == null) {
            Collection<V> createCollection = createCollection(1);
            createCollection.add(obj);
            if (createCollection.size() > 0) {
                mo12722decorated().put(k, createCollection);
            } else {
                z = false;
            }
        } else {
            z = collection.add(obj);
        }
        if (z) {
            return obj;
        }
        return null;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ?> map) {
        if (map instanceof MultiMap) {
            for (Map.Entry<K, Object> entry : ((MultiMap) map).entrySet()) {
                putAll(entry.getKey(), (Collection) entry.getValue());
            }
            return;
        }
        for (Map.Entry<? extends K, ?> entry2 : map.entrySet()) {
            put(entry2.getKey(), entry2.getValue());
        }
    }

    @Override // org.apache.commons.collections4.MultiMap
    public boolean removeMapping(Object obj, Object obj2) {
        Collection<V> collection = getCollection(obj);
        if (collection != null && collection.remove(obj2)) {
            if (!collection.isEmpty()) {
                return true;
            }
            mo12668remove(obj);
            return true;
        }
        return false;
    }

    public int size(Object obj) {
        Collection<V> collection = getCollection(obj);
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public int totalSize() {
        int i = 0;
        for (V v : mo12722decorated().values()) {
            i += CollectionUtils.size(v);
        }
        return i;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<Object> mo12691values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.valuesView = values;
        return values;
    }

    protected <C extends Collection<V>> MultiValueMap(Map<K, ? super C> map, Factory<C> factory) {
        super(map);
        if (factory != null) {
            this.collectionFactory = factory;
            return;
        }
        throw new IllegalArgumentException("The factory must not be null");
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Class<C> cls) {
        return new MultiValueMap<>(map, new ReflectionFactory(cls));
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Factory<C> factory) {
        return new MultiValueMap<>(map, factory);
    }

    public boolean containsValue(Object obj, Object obj2) {
        Collection<V> collection = getCollection(obj);
        if (collection == null) {
            return false;
        }
        return collection.contains(obj2);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        final Iterator it2 = new ArrayList(keySet()).iterator();
        return new LazyIteratorChain<Map.Entry<K, V>>() { // from class: org.apache.commons.collections4.map.MultiValueMap.1
            @Override // org.apache.commons.collections4.iterators.LazyIteratorChain
            protected Iterator<? extends Map.Entry<K, V>> nextIterator(int i) {
                if (!it2.hasNext()) {
                    return null;
                }
                final Object next = it2.next();
                return new TransformIterator(new ValuesIterator(next), new Transformer<V, Map.Entry<K, V>>() { // from class: org.apache.commons.collections4.map.MultiValueMap.1.1
                    @Override // org.apache.commons.collections4.Transformer
                    /* renamed from: transform */
                    public /* bridge */ /* synthetic */ Object mo12738transform(Object obj) {
                        return mo12738transform((C01231) obj);
                    }

                    @Override // org.apache.commons.collections4.Transformer
                    /* renamed from: transform  reason: collision with other method in class */
                    public Map.Entry<K, V> mo12738transform(final V v) {
                        return new Map.Entry<K, V>() { // from class: org.apache.commons.collections4.map.MultiValueMap.1.1.1
                            @Override // java.util.Map.Entry
                            public K getKey() {
                                return (K) next;
                            }

                            @Override // java.util.Map.Entry
                            public V getValue() {
                                return (V) v;
                            }

                            @Override // java.util.Map.Entry
                            public V setValue(V v2) {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }
                });
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean putAll(K k, Collection<V> collection) {
        if (collection == 0 || collection.size() == 0) {
            return false;
        }
        Collection<V> collection2 = getCollection(k);
        if (collection2 == null) {
            Collection<V> createCollection = createCollection(collection.size());
            createCollection.addAll(collection);
            if (createCollection.size() <= 0) {
                return false;
            }
            mo12722decorated().put(k, createCollection);
            return true;
        }
        return collection2.addAll(collection);
    }
}
