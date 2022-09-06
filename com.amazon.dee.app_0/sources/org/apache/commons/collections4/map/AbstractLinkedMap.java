package org.apache.commons.collections4.map;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.map.AbstractHashedMap;
/* loaded from: classes4.dex */
public abstract class AbstractLinkedMap<K, V> extends AbstractHashedMap<K, V> implements OrderedMap<K, V> {
    transient LinkEntry<K, V> header;

    /* loaded from: classes4.dex */
    protected static class EntrySetIterator<K, V> extends LinkIterator<K, V> implements OrderedIterator<Map.Entry<K, V>>, ResettableIterator<Map.Entry<K, V>> {
        protected EntrySetIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return super.nextEntry();
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous  reason: collision with other method in class */
        public Map.Entry<K, V> mo12706previous() {
            return super.previousEntry();
        }
    }

    /* loaded from: classes4.dex */
    protected static class KeySetIterator<K> extends LinkIterator<K, Object> implements OrderedIterator<K>, ResettableIterator<K> {
        protected KeySetIterator(AbstractLinkedMap<K, ?> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        @Override // java.util.Iterator
        public K next() {
            return super.nextEntry().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            return super.previousEntry().mo12677getKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class LinkEntry<K, V> extends AbstractHashedMap.HashEntry<K, V> {
        protected LinkEntry<K, V> after;
        protected LinkEntry<K, V> before;

        protected LinkEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, Object obj, V v) {
            super(hashEntry, i, obj, v);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static abstract class LinkIterator<K, V> {
        protected int expectedModCount;
        protected LinkEntry<K, V> last;
        protected LinkEntry<K, V> next;
        protected final AbstractLinkedMap<K, V> parent;

        protected LinkIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            this.parent = abstractLinkedMap;
            this.next = abstractLinkedMap.header.after;
            this.expectedModCount = abstractLinkedMap.modCount;
        }

        protected LinkEntry<K, V> currentEntry() {
            return this.last;
        }

        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        public boolean hasPrevious() {
            return this.next.before != this.parent.header;
        }

        protected LinkEntry<K, V> nextEntry() {
            AbstractLinkedMap<K, V> abstractLinkedMap = this.parent;
            if (abstractLinkedMap.modCount == this.expectedModCount) {
                LinkEntry<K, V> linkEntry = this.next;
                if (linkEntry != abstractLinkedMap.header) {
                    this.last = linkEntry;
                    this.next = linkEntry.after;
                    return this.last;
                }
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            throw new ConcurrentModificationException();
        }

        protected LinkEntry<K, V> previousEntry() {
            AbstractLinkedMap<K, V> abstractLinkedMap = this.parent;
            if (abstractLinkedMap.modCount == this.expectedModCount) {
                LinkEntry<K, V> linkEntry = this.next.before;
                if (linkEntry != abstractLinkedMap.header) {
                    this.next = linkEntry;
                    this.last = linkEntry;
                    return this.last;
                }
                throw new NoSuchElementException("No previous() entry in the iteration");
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            LinkEntry<K, V> linkEntry = this.last;
            if (linkEntry != null) {
                AbstractLinkedMap<K, V> abstractLinkedMap = this.parent;
                if (abstractLinkedMap.modCount == this.expectedModCount) {
                    abstractLinkedMap.mo12668remove(linkEntry.mo12677getKey());
                    this.last = null;
                    this.expectedModCount = this.parent.modCount;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }

        public void reset() {
            this.last = null;
            this.next = this.parent.header.after;
        }

        public String toString() {
            if (this.last != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Iterator[");
                outline107.append(this.last.mo12677getKey());
                outline107.append(Config.Compare.EQUAL_TO);
                outline107.append(this.last.mo12678getValue());
                outline107.append("]");
                return outline107.toString();
            }
            return "Iterator[]";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class LinkMapIterator<K, V> extends LinkIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        protected LinkMapIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            LinkEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12677getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            LinkEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12678getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            return super.nextEntry().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            return super.previousEntry().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            LinkEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }
    }

    /* loaded from: classes4.dex */
    protected static class ValuesIterator<V> extends LinkIterator<Object, V> implements OrderedIterator<V>, ResettableIterator<V> {
        protected ValuesIterator(AbstractLinkedMap<?, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        @Override // java.util.Iterator
        public V next() {
            return super.nextEntry().mo12678getValue();
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public V mo12706previous() {
            return super.previousEntry().mo12678getValue();
        }
    }

    protected AbstractLinkedMap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void addEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i) {
        LinkEntry<K, V> linkEntry = (LinkEntry) hashEntry;
        LinkEntry<K, V> linkEntry2 = this.header;
        linkEntry.after = linkEntry2;
        linkEntry.before = linkEntry2.before;
        linkEntry2.before.after = linkEntry;
        linkEntry2.before = linkEntry;
        this.data[i] = linkEntry;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        super.clear();
        LinkEntry<K, V> linkEntry = this.header;
        linkEntry.after = linkEntry;
        linkEntry.before = linkEntry;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        if (obj == null) {
            LinkEntry<K, V> linkEntry = this.header;
            do {
                linkEntry = linkEntry.after;
                if (linkEntry == this.header) {
                    return false;
                }
            } while (linkEntry.mo12678getValue() != null);
            return true;
        }
        LinkEntry<K, V> linkEntry2 = this.header;
        do {
            linkEntry2 = linkEntry2.after;
            if (linkEntry2 == this.header) {
                return false;
            }
        } while (!isEqualValue(obj, linkEntry2.mo12678getValue()));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: createEntry */
    protected /* bridge */ /* synthetic */ AbstractHashedMap.HashEntry mo12709createEntry(AbstractHashedMap.HashEntry hashEntry, int i, Object obj, Object obj2) {
        return mo12709createEntry((AbstractHashedMap.HashEntry<int, Object>) hashEntry, i, (int) obj, obj2);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new EntrySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<K> createKeySetIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new KeySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<V> createValuesIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new ValuesIterator(this);
    }

    protected LinkEntry<K, V> entryAfter(LinkEntry<K, V> linkEntry) {
        return linkEntry.after;
    }

    protected LinkEntry<K, V> entryBefore(LinkEntry<K, V> linkEntry) {
        return linkEntry.before;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        if (this.size != 0) {
            return this.header.after.mo12677getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected void init() {
        this.header = mo12709createEntry((AbstractHashedMap.HashEntry<int, K>) null, -1, (int) null, (K) null);
        LinkEntry<K, V> linkEntry = this.header;
        linkEntry.after = linkEntry;
        linkEntry.before = linkEntry;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        if (this.size != 0) {
            return this.header.before.mo12677getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(Object obj) {
        LinkEntry<K, V> linkEntry;
        LinkEntry<K, V> mo12703getEntry = mo12703getEntry(obj);
        if (mo12703getEntry == null || (linkEntry = mo12703getEntry.after) == this.header) {
            return null;
        }
        return linkEntry.mo12677getKey();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(Object obj) {
        LinkEntry<K, V> linkEntry;
        LinkEntry<K, V> mo12703getEntry = mo12703getEntry(obj);
        if (mo12703getEntry == null || (linkEntry = mo12703getEntry.before) == this.header) {
            return null;
        }
        return linkEntry.mo12677getKey();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void removeEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, AbstractHashedMap.HashEntry<K, V> hashEntry2) {
        LinkEntry linkEntry = (LinkEntry) hashEntry;
        LinkEntry<K, V> linkEntry2 = linkEntry.before;
        linkEntry2.after = linkEntry.after;
        linkEntry.after.before = linkEntry2;
        linkEntry.after = null;
        linkEntry.before = null;
        super.removeEntry(hashEntry, i, hashEntry2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedMap(int i, float f, int i2) {
        super(i, f, i2);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: createEntry  reason: collision with other method in class */
    protected LinkEntry<K, V> mo12709createEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new LinkEntry<>(hashEntry, i, convertKey(k), v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: getEntry  reason: collision with other method in class */
    public LinkEntry<K, V> mo12703getEntry(Object obj) {
        return (LinkEntry) super.mo12703getEntry(obj);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        if (this.size == 0) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new LinkMapIterator(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedMap(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LinkEntry<K, V> getEntry(int i) {
        LinkEntry<K, V> linkEntry;
        if (i >= 0) {
            int i2 = this.size;
            if (i < i2) {
                if (i < i2 / 2) {
                    linkEntry = this.header.after;
                    for (int i3 = 0; i3 < i; i3++) {
                        linkEntry = linkEntry.after;
                    }
                } else {
                    linkEntry = this.header;
                    while (i2 > i) {
                        linkEntry = linkEntry.before;
                        i2--;
                    }
                }
                return linkEntry;
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index ", i, " is invalid for size ");
            outline109.append(this.size);
            throw new IndexOutOfBoundsException(outline109.toString());
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline52("Index ", i, " is less than zero"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedMap(int i, float f) {
        super(i, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }
}
