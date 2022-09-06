package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.commons.collections4.multiset.AbstractMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;
/* loaded from: classes4.dex */
public abstract class AbstractMultiValuedMap<K, V> implements MultiValuedMap<K, V> {
    private transient AbstractMultiValuedMap<K, V>.AsMap asMapView;
    private transient AbstractMultiValuedMap<K, V>.EntryValues entryValuesView;
    private transient MultiSet<K> keysMultiSetView;
    private transient Map<K, Collection<V>> map;
    private transient Collection<V> valuesView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class AsMap extends AbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> decoratedMap;

        /* loaded from: classes4.dex */
        class AsMapEntrySet extends AbstractSet<Map.Entry<K, Collection<V>>> {
            AsMapEntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public void clear() {
                AsMap.this.clear();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return AsMap.this.decoratedMap.entrySet().contains(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                AsMap asMap = AsMap.this;
                return new AsMapEntrySetIterator(asMap.decoratedMap.entrySet().iterator());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMultiValuedMap.this.mo12741remove(((Map.Entry) obj).getKey());
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return AsMap.this.size();
            }
        }

        /* loaded from: classes4.dex */
        class AsMapEntrySetIterator extends AbstractIteratorDecorator<Map.Entry<K, Collection<V>>> {
            AsMapEntrySetIterator(Iterator<Map.Entry<K, Collection<V>>> it2) {
                super(it2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
            /* renamed from: next  reason: collision with other method in class */
            public Map.Entry<K, Collection<V>> mo12737next() {
                Object key = ((Map.Entry) super.mo12737next()).getKey();
                return new UnmodifiableMapEntry(key, AbstractMultiValuedMap.this.mo12742wrappedCollection(key));
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.decoratedMap = map;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.decoratedMap.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            return new AsMapEntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(Object obj) {
            return this == obj || this.decoratedMap.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.decoratedMap.hashCode();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return AbstractMultiValuedMap.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.decoratedMap.size();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            return this.decoratedMap.toString();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object obj) {
            if (this.decoratedMap.get(obj) == null) {
                return null;
            }
            return AbstractMultiValuedMap.this.mo12742wrappedCollection(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object obj) {
            Collection<V> remove = this.decoratedMap.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> mo12744createCollection = AbstractMultiValuedMap.this.mo12744createCollection();
            mo12744createCollection.addAll(remove);
            remove.clear();
            return mo12744createCollection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class EntryValues extends AbstractCollection<Map.Entry<K, V>> {
        private EntryValues() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LazyIteratorChain<Map.Entry<K, V>>() { // from class: org.apache.commons.collections4.multimap.AbstractMultiValuedMap.EntryValues.1
                final Iterator<K> keyIterator;
                final Collection<K> keysCol;

                {
                    this.keysCol = new ArrayList(AbstractMultiValuedMap.this.getMap().keySet());
                    this.keyIterator = this.keysCol.iterator();
                }

                @Override // org.apache.commons.collections4.iterators.LazyIteratorChain
                protected Iterator<? extends Map.Entry<K, V>> nextIterator(int i) {
                    if (!this.keyIterator.hasNext()) {
                        return null;
                    }
                    final K next = this.keyIterator.next();
                    return new TransformIterator(new ValuesIterator(next), new Transformer<V, Map.Entry<K, V>>() { // from class: org.apache.commons.collections4.multimap.AbstractMultiValuedMap.EntryValues.1.1
                        @Override // org.apache.commons.collections4.Transformer
                        /* renamed from: transform */
                        public /* bridge */ /* synthetic */ Object mo12738transform(Object obj) {
                            return mo12738transform((C01251) obj);
                        }

                        @Override // org.apache.commons.collections4.Transformer
                        /* renamed from: transform  reason: collision with other method in class */
                        public Map.Entry<K, V> mo12738transform(V v) {
                            return new MultiValuedMapEntry(next, v);
                        }
                    });
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class KeysMultiSet extends AbstractMultiSet<K> {

        /* loaded from: classes4.dex */
        private final class MapEntryTransformer implements Transformer<Map.Entry<K, Collection<V>>, MultiSet.Entry<K>> {
            private MapEntryTransformer() {
            }

            @Override // org.apache.commons.collections4.Transformer
            /* renamed from: transform */
            public /* bridge */ /* synthetic */ Object mo12738transform(Object obj) {
                return transform((Map.Entry) ((Map.Entry) obj));
            }

            public MultiSet.Entry<K> transform(final Map.Entry<K, Collection<V>> entry) {
                return new AbstractMultiSet.AbstractEntry<K>() { // from class: org.apache.commons.collections4.multimap.AbstractMultiValuedMap.KeysMultiSet.MapEntryTransformer.1
                    @Override // org.apache.commons.collections4.MultiSet.Entry
                    public int getCount() {
                        return ((Collection) entry.getValue()).size();
                    }

                    @Override // org.apache.commons.collections4.MultiSet.Entry
                    public K getElement() {
                        return (K) entry.getKey();
                    }
                };
            }
        }

        private KeysMultiSet() {
        }

        @Override // org.apache.commons.collections4.multiset.AbstractMultiSet, java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractMultiValuedMap.this.getMap().containsKey(obj);
        }

        @Override // org.apache.commons.collections4.multiset.AbstractMultiSet
        protected Iterator<MultiSet.Entry<K>> createEntrySetIterator() {
            return IteratorUtils.transformedIterator(AbstractMultiValuedMap.this.map.entrySet().iterator(), new MapEntryTransformer());
        }

        @Override // org.apache.commons.collections4.multiset.AbstractMultiSet, org.apache.commons.collections4.MultiSet
        public int getCount(Object obj) {
            Collection<V> collection = AbstractMultiValuedMap.this.getMap().get(obj);
            if (collection != null) {
                return collection.size();
            }
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return AbstractMultiValuedMap.this.getMap().isEmpty();
        }

        @Override // org.apache.commons.collections4.multiset.AbstractMultiSet, java.util.AbstractCollection, java.util.Collection, org.apache.commons.collections4.MultiSet
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }

        @Override // org.apache.commons.collections4.multiset.AbstractMultiSet
        protected int uniqueElements() {
            return AbstractMultiValuedMap.this.getMap().size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class MultiValuedMapEntry extends AbstractMapEntry<K, V> {
        public MultiValuedMapEntry(K k, V v) {
            super(k, v);
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntry, org.apache.commons.collections4.keyvalue.AbstractKeyValue, java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes4.dex */
    private class MultiValuedMapIterator implements MapIterator<K, V> {
        private Map.Entry<K, V> current = null;

        /* renamed from: it  reason: collision with root package name */
        private final Iterator<Map.Entry<K, V>> f22it;

        public MultiValuedMapIterator() {
            this.f22it = AbstractMultiValuedMap.this.entries().iterator();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            Map.Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.getKey();
            }
            throw new IllegalStateException();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            Map.Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.getValue();
            }
            throw new IllegalStateException();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f22it.hasNext();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            this.current = this.f22it.next();
            return this.current.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            this.f22it.remove();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            Map.Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.setValue(v);
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            IteratorChain iteratorChain = new IteratorChain();
            for (K k : AbstractMultiValuedMap.this.keySet()) {
                iteratorChain.addIterator(new ValuesIterator(k));
            }
            return iteratorChain;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }
    }

    /* loaded from: classes4.dex */
    private class ValuesIterator implements Iterator<V> {
        private final Iterator<V> iterator;
        private final Object key;
        private final Collection<V> values;

        public ValuesIterator(Object obj) {
            this.key = obj;
            this.values = AbstractMultiValuedMap.this.getMap().get(obj);
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
                AbstractMultiValuedMap.this.mo12741remove(this.key);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMultiValuedMap() {
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public Map<K, Collection<V>> asMap() {
        AbstractMultiValuedMap<K, V>.AsMap asMap = this.asMapView;
        if (asMap != null) {
            return asMap;
        }
        AbstractMultiValuedMap<K, V>.AsMap asMap2 = new AsMap(this.map);
        this.asMapView = asMap2;
        return asMap2;
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public void clear() {
        getMap().clear();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean containsKey(Object obj) {
        return getMap().containsKey(obj);
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean containsMapping(Object obj, Object obj2) {
        Collection<V> collection = getMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    /* renamed from: createCollection */
    protected abstract Collection<V> mo12744createCollection();

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Collection mo12740get = mo12740get(objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            for (int i2 = 0; i2 < readInt2; i2++) {
                mo12740get.add(objectInputStream.readObject());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Map.Entry<K, Collection<V>> entry : this.map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            for (V v : entry.getValue()) {
                objectOutputStream.writeObject(v);
            }
        }
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public Collection<Map.Entry<K, V>> entries() {
        AbstractMultiValuedMap<K, V>.EntryValues entryValues = this.entryValuesView;
        if (entryValues != null) {
            return entryValues;
        }
        AbstractMultiValuedMap<K, V>.EntryValues entryValues2 = new EntryValues();
        this.entryValuesView = entryValues2;
        return entryValues2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultiValuedMap)) {
            return false;
        }
        return asMap().equals(((MultiValuedMap) obj).asMap());
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get */
    public Collection<V> mo12740get(K k) {
        return mo12742wrappedCollection(k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<K, ? extends Collection<V>> getMap() {
        return this.map;
    }

    public int hashCode() {
        return getMap().hashCode();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean isEmpty() {
        return getMap().isEmpty();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public Set<K> keySet() {
        return getMap().keySet();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public MultiSet<K> keys() {
        if (this.keysMultiSetView == null) {
            this.keysMultiSetView = UnmodifiableMultiSet.unmodifiableMultiSet(new KeysMultiSet());
        }
        return this.keysMultiSetView;
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public MapIterator<K, V> mapIterator() {
        if (size() == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new MultiValuedMapIterator();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean put(K k, V v) {
        Collection<V> collection = getMap().get(k);
        if (collection == null) {
            Collection<V> mo12744createCollection = mo12744createCollection();
            if (!mo12744createCollection.add(v)) {
                return false;
            }
            this.map.put(k, mo12744createCollection);
            return true;
        }
        return collection.add(v);
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(Map<? extends K, ? extends V> map) {
        if (map != null) {
            boolean z = false;
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                z |= put(entry.getKey(), entry.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: remove */
    public Collection<V> mo12741remove(Object obj) {
        return CollectionUtils.emptyIfNull(getMap().remove(obj));
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean removeMapping(Object obj, Object obj2) {
        Collection<V> collection = getMap().get(obj);
        if (collection == null) {
            return false;
        }
        boolean remove = collection.remove(obj2);
        if (collection.isEmpty()) {
            getMap().remove(obj);
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void setMap(Map<K, ? extends Collection<V>> map) {
        this.map = map;
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public int size() {
        int i = 0;
        for (Collection<V> collection : getMap().values()) {
            i += collection.size();
        }
        return i;
    }

    public String toString() {
        return getMap().toString();
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.valuesView = values;
        return values;
    }

    /* renamed from: wrappedCollection */
    Collection<V> mo12742wrappedCollection(K k) {
        return new WrappedCollection(k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractMultiValuedMap(Map<K, ? extends Collection<V>> map) {
        if (map != 0) {
            this.map = map;
            return;
        }
        throw new NullPointerException("Map must not be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class WrappedCollection implements Collection<V> {
        protected final K key;

        public WrappedCollection(K k) {
            this.key = k;
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                mo12734getMapping = AbstractMultiValuedMap.this.mo12744createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, mo12734getMapping);
            }
            return mo12734getMapping.add(v);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                mo12734getMapping = AbstractMultiValuedMap.this.mo12744createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, mo12734getMapping);
            }
            return mo12734getMapping.addAll(collection);
        }

        @Override // java.util.Collection
        public void clear() {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping != null) {
                mo12734getMapping.clear();
                AbstractMultiValuedMap.this.mo12741remove(this.key);
            }
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return false;
            }
            return mo12734getMapping.contains(obj);
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return false;
            }
            return mo12734getMapping.containsAll(collection);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: getMapping */
        public Collection<V> mo12734getMapping() {
            return AbstractMultiValuedMap.this.getMap().get(this.key);
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return true;
            }
            return mo12734getMapping.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            if (mo12734getMapping() == null) {
                return IteratorUtils.EMPTY_ITERATOR;
            }
            return new ValuesIterator(this.key);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return false;
            }
            boolean remove = mo12734getMapping.remove(obj);
            if (mo12734getMapping.isEmpty()) {
                AbstractMultiValuedMap.this.mo12741remove(this.key);
            }
            return remove;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return false;
            }
            boolean removeAll = mo12734getMapping.removeAll(collection);
            if (mo12734getMapping.isEmpty()) {
                AbstractMultiValuedMap.this.mo12741remove(this.key);
            }
            return removeAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return false;
            }
            boolean retainAll = mo12734getMapping.retainAll(collection);
            if (mo12734getMapping.isEmpty()) {
                AbstractMultiValuedMap.this.mo12741remove(this.key);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public int size() {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return 0;
            }
            return mo12734getMapping.size();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return CollectionUtils.EMPTY_COLLECTION.toArray();
            }
            return mo12734getMapping.toArray();
        }

        public String toString() {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return CollectionUtils.EMPTY_COLLECTION.toString();
            }
            return mo12734getMapping.toString();
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            Collection<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return (T[]) CollectionUtils.EMPTY_COLLECTION.toArray(tArr);
            }
            return (T[]) mo12734getMapping.toArray(tArr);
        }
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        if (multiValuedMap != null) {
            boolean z = false;
            for (Map.Entry<? extends K, ? extends V> entry : multiValuedMap.entries()) {
                z |= put(entry.getKey(), entry.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }

    @Override // org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        if (iterable != null) {
            if (iterable instanceof Collection) {
                Collection<? extends V> collection = (Collection) iterable;
                return !collection.isEmpty() && mo12740get(k).addAll(collection);
            }
            Iterator<? extends V> it2 = iterable.iterator();
            return it2.hasNext() && CollectionUtils.addAll(mo12740get(k), it2);
        }
        throw new NullPointerException("Values must not be null.");
    }
}
