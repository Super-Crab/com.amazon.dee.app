package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.ListValuedMap;
/* loaded from: classes4.dex */
public abstract class AbstractListValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements ListValuedMap<K, V> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class WrappedList extends AbstractMultiValuedMap<K, V>.WrappedCollection implements List<V> {
        public WrappedList(K k) {
            super(k);
        }

        @Override // java.util.List
        public void add(int i, V v) {
            List<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                mo12734getMapping = AbstractListValuedMap.this.mo12744createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, mo12734getMapping);
            }
            mo12734getMapping.add(i, v);
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends V> collection) {
            List<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                List<V> mo12744createCollection = AbstractListValuedMap.this.mo12744createCollection();
                boolean addAll = mo12744createCollection.addAll(i, collection);
                if (addAll) {
                    AbstractListValuedMap.this.getMap().put(this.key, mo12744createCollection);
                }
                return addAll;
            }
            return mo12734getMapping.addAll(i, collection);
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            List<V> mo12734getMapping = mo12734getMapping();
            if (mo12734getMapping == null) {
                return Collections.emptyList().equals(obj);
            }
            if (obj instanceof List) {
                return ListUtils.isEqualList(mo12734getMapping, (List) obj);
            }
            return false;
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) ListUtils.emptyIfNull(mo12734getMapping()).get(i);
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            return ListUtils.hashCodeForList(mo12734getMapping());
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            return ListUtils.emptyIfNull(mo12734getMapping()).indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            return ListUtils.emptyIfNull(mo12734getMapping()).lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            return new ValuesListIterator(this.key);
        }

        @Override // java.util.List
        public V remove(int i) {
            List emptyIfNull = ListUtils.emptyIfNull(mo12734getMapping());
            V v = (V) emptyIfNull.remove(i);
            if (emptyIfNull.isEmpty()) {
                AbstractListValuedMap.this.mo12741remove((Object) this.key);
            }
            return v;
        }

        @Override // java.util.List
        public V set(int i, V v) {
            return (V) ListUtils.emptyIfNull(mo12734getMapping()).set(i, v);
        }

        @Override // java.util.List
        public List<V> subList(int i, int i2) {
            return ListUtils.emptyIfNull(mo12734getMapping()).subList(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap.WrappedCollection
        /* renamed from: getMapping  reason: collision with other method in class */
        public List<V> mo12734getMapping() {
            return AbstractListValuedMap.this.getMap().get(this.key);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i) {
            return new ValuesListIterator(this.key, i);
        }
    }

    protected AbstractListValuedMap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: createCollection  reason: collision with other method in class */
    public abstract List<V> mo12744createCollection();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo12740get(Object obj) {
        return mo12740get((AbstractListValuedMap<K, V>) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    public Map<K, List<V>> getMap() {
        return super.getMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: wrappedCollection */
    /* bridge */ /* synthetic */ Collection mo12742wrappedCollection(Object obj) {
        return mo12742wrappedCollection((AbstractListValuedMap<K, V>) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractListValuedMap(Map<K, ? extends List<V>> map) {
        super(map);
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get  reason: collision with other method in class */
    public List<V> mo12740get(K k) {
        return mo12742wrappedCollection((AbstractListValuedMap<K, V>) k);
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: remove  reason: collision with other method in class */
    public List<V> mo12741remove(Object obj) {
        return ListUtils.emptyIfNull(getMap().remove(obj));
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: wrappedCollection  reason: collision with other method in class */
    List<V> mo12742wrappedCollection(K k) {
        return new WrappedList(k);
    }

    /* loaded from: classes4.dex */
    private class ValuesListIterator implements ListIterator<V> {
        private ListIterator<V> iterator;
        private final K key;
        private List<V> values;

        public ValuesListIterator(K k) {
            this.key = k;
            this.values = ListUtils.emptyIfNull(AbstractListValuedMap.this.getMap().get(k));
            this.iterator = this.values.listIterator();
        }

        @Override // java.util.ListIterator
        public void add(V v) {
            if (AbstractListValuedMap.this.getMap().get(this.key) == null) {
                List<V> mo12744createCollection = AbstractListValuedMap.this.mo12744createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, mo12744createCollection);
                this.values = mo12744createCollection;
                this.iterator = mo12744createCollection.listIterator();
            }
            this.iterator.add(v);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public V next() {
            return this.iterator.next();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.iterator.nextIndex();
        }

        @Override // java.util.ListIterator
        public V previous() {
            return this.iterator.previous();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.iterator.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                AbstractListValuedMap.this.getMap().remove(this.key);
            }
        }

        @Override // java.util.ListIterator
        public void set(V v) {
            this.iterator.set(v);
        }

        public ValuesListIterator(K k, int i) {
            this.key = k;
            this.values = ListUtils.emptyIfNull(AbstractListValuedMap.this.getMap().get(k));
            this.iterator = this.values.listIterator(i);
        }
    }
}
