package org.apache.commons.collections4.trie;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.trie.AbstractBitwiseTrie;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class AbstractPatriciaTrie<K, V> extends AbstractBitwiseTrie<K, V> {
    private static final long serialVersionUID = 5155253417231339498L;
    private volatile transient Set<Map.Entry<K, V>> entrySet;
    private volatile transient Set<K> keySet;
    protected transient int modCount;
    private transient TrieEntry<K, V> root;
    private transient int size;
    private volatile transient Collection<V> values;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        /* loaded from: classes4.dex */
        private class EntryIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<Map.Entry<K, V>> {
            private EntryIterator() {
                super();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return nextEntry();
            }
        }

        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TrieEntry<K, V> entry;
            return (obj instanceof Map.Entry) && (entry = AbstractPatriciaTrie.this.getEntry(((Map.Entry) obj).getKey())) != null && entry.equals(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if ((obj instanceof Map.Entry) && contains(obj)) {
                AbstractPatriciaTrie.this.mo12668remove(((Map.Entry) obj).getKey());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class KeySet extends AbstractSet<K> {

        /* loaded from: classes4.dex */
        private class KeyIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<K> {
            private KeyIterator() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return nextEntry().getKey();
            }
        }

        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return AbstractPatriciaTrie.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int size = size();
            AbstractPatriciaTrie.this.mo12668remove(obj);
            return size != size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }
    }

    /* loaded from: classes4.dex */
    private final class PrefixRangeEntrySet extends AbstractPatriciaTrie<K, V>.RangeEntrySet {
        private final AbstractPatriciaTrie<K, V>.PrefixRangeMap delegate;
        private int expectedModCount;
        private TrieEntry<K, V> prefixStart;

        /* loaded from: classes4.dex */
        private final class EntryIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<Map.Entry<K, V>> {
            private boolean lastOne;
            private final int lengthInBits;
            private final int offset;
            private final K prefix;
            private TrieEntry<K, V> subtree;

            EntryIterator(TrieEntry<K, V> trieEntry, K k, int i, int i2) {
                super();
                this.subtree = trieEntry;
                this.next = AbstractPatriciaTrie.this.followLeft(trieEntry);
                this.prefix = k;
                this.offset = i;
                this.lengthInBits = i2;
            }

            @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieIterator
            protected TrieEntry<K, V> findNext(TrieEntry<K, V> trieEntry) {
                return AbstractPatriciaTrie.this.nextEntryInSubtree(trieEntry, this.subtree);
            }

            @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieIterator, java.util.Iterator
            public void remove() {
                TrieEntry<K, V> trieEntry = this.subtree;
                int i = trieEntry.bitIndex;
                boolean z = this.current == trieEntry;
                super.remove();
                if (i != this.subtree.bitIndex || z) {
                    this.subtree = AbstractPatriciaTrie.this.subtree(this.prefix, this.offset, this.lengthInBits);
                }
                if (this.lengthInBits >= this.subtree.bitIndex) {
                    this.lastOne = true;
                }
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                TrieEntry<K, V> nextEntry = nextEntry();
                if (this.lastOne) {
                    this.next = null;
                }
                return nextEntry;
            }
        }

        /* loaded from: classes4.dex */
        private final class SingletonIterator implements Iterator<Map.Entry<K, V>> {
            private final TrieEntry<K, V> entry;
            private int hit = 0;

            public SingletonIterator(TrieEntry<K, V> trieEntry) {
                this.entry = trieEntry;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.hit == 0;
            }

            @Override // java.util.Iterator
            public void remove() {
                int i = this.hit;
                if (i == 1) {
                    this.hit = i + 1;
                    AbstractPatriciaTrie.this.removeEntry(this.entry);
                    return;
                }
                throw new IllegalStateException();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                int i = this.hit;
                if (i == 0) {
                    this.hit = i + 1;
                    return this.entry;
                }
                throw new NoSuchElementException();
            }
        }

        public PrefixRangeEntrySet(AbstractPatriciaTrie<K, V>.PrefixRangeMap prefixRangeMap) {
            super(prefixRangeMap);
            this.expectedModCount = 0;
            this.delegate = prefixRangeMap;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeEntrySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            AbstractPatriciaTrie abstractPatriciaTrie = AbstractPatriciaTrie.this;
            if (abstractPatriciaTrie.modCount != this.expectedModCount) {
                this.prefixStart = abstractPatriciaTrie.subtree(((PrefixRangeMap) this.delegate).prefix, ((PrefixRangeMap) this.delegate).offsetInBits, ((PrefixRangeMap) this.delegate).lengthInBits);
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            if (this.prefixStart != null) {
                int i = ((PrefixRangeMap) this.delegate).lengthInBits;
                TrieEntry<K, V> trieEntry = this.prefixStart;
                if (i > trieEntry.bitIndex) {
                    return new SingletonIterator(trieEntry);
                }
                return new EntryIterator(trieEntry, ((PrefixRangeMap) this.delegate).prefix, ((PrefixRangeMap) this.delegate).offsetInBits, ((PrefixRangeMap) this.delegate).lengthInBits);
            }
            return Collections.emptySet().iterator();
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeEntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.delegate.fixup();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class PrefixRangeMap extends AbstractPatriciaTrie<K, V>.RangeMap {
        private transient int expectedModCount;
        private K fromKey;
        private final int lengthInBits;
        private final int offsetInBits;
        private final K prefix;
        private int size;
        private K toKey;

        /* JADX INFO: Access modifiers changed from: private */
        public int fixup() {
            Map.Entry<K, V> entry;
            if (this.size == -1 || AbstractPatriciaTrie.this.modCount != this.expectedModCount) {
                Iterator<Map.Entry<K, V>> it2 = super.entrySet().iterator();
                this.size = 0;
                K k = null;
                if (it2.hasNext()) {
                    entry = it2.next();
                    this.size = 1;
                } else {
                    entry = null;
                }
                this.fromKey = entry == null ? null : entry.getKey();
                if (this.fromKey != null) {
                    TrieEntry<K, V> previousEntry = AbstractPatriciaTrie.this.previousEntry((TrieEntry) entry);
                    this.fromKey = previousEntry == null ? null : previousEntry.getKey();
                }
                this.toKey = this.fromKey;
                while (it2.hasNext()) {
                    this.size++;
                    entry = it2.next();
                }
                this.toKey = entry == null ? null : entry.getKey();
                if (this.toKey != null) {
                    TrieEntry<K, V> nextEntry = AbstractPatriciaTrie.this.nextEntry((TrieEntry) entry);
                    if (nextEntry != null) {
                        k = nextEntry.getKey();
                    }
                    this.toKey = k;
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Iterator<Map.Entry<K, V>> it2 = AbstractPatriciaTrie.this.entrySet().iterator();
            Set<K> keySet = keySet();
            while (it2.hasNext()) {
                if (keySet.contains(it2.next().getKey())) {
                    it2.remove();
                }
            }
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected Set<Map.Entry<K, V>> createEntrySet() {
            return new PrefixRangeEntrySet(this);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2) {
            return new RangeEntryMap(k, z, k2, z2);
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            TrieEntry<K, V> higherEntry;
            fixup();
            K k = this.fromKey;
            if (k == null) {
                higherEntry = AbstractPatriciaTrie.this.firstEntry();
            } else {
                higherEntry = AbstractPatriciaTrie.this.higherEntry(k);
            }
            K key = higherEntry != null ? higherEntry.getKey() : null;
            if (higherEntry == null || !AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix((K) this.prefix, this.offsetInBits, this.lengthInBits, key)) {
                throw new NoSuchElementException();
            }
            return key;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public K getFromKey() {
            return this.fromKey;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public K getToKey() {
            return this.toKey;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected boolean inFromRange(K k, boolean z) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix((K) this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected boolean inRange(K k) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix((K) this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected boolean inRange2(K k) {
            return inRange(k);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected boolean inToRange(K k, boolean z) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix((K) this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public boolean isFromInclusive() {
            return false;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public boolean isToInclusive() {
            return false;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            TrieEntry<K, V> lowerEntry;
            fixup();
            K k = this.toKey;
            if (k == null) {
                lowerEntry = AbstractPatriciaTrie.this.lastEntry();
            } else {
                lowerEntry = AbstractPatriciaTrie.this.lowerEntry(k);
            }
            K key = lowerEntry != null ? lowerEntry.getKey() : null;
            if (lowerEntry == null || !AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix((K) this.prefix, this.offsetInBits, this.lengthInBits, key)) {
                throw new NoSuchElementException();
            }
            return key;
        }

        private PrefixRangeMap(K k, int i, int i2) {
            super();
            this.fromKey = null;
            this.toKey = null;
            this.expectedModCount = 0;
            this.size = -1;
            this.prefix = k;
            this.offsetInBits = i;
            this.lengthInBits = i2;
        }
    }

    /* loaded from: classes4.dex */
    private class RangeEntryMap extends AbstractPatriciaTrie<K, V>.RangeMap {
        private final boolean fromInclusive;
        private final K fromKey;
        private final boolean toInclusive;
        private final K toKey;

        protected RangeEntryMap(AbstractPatriciaTrie abstractPatriciaTrie, K k, K k2) {
            this(k, true, k2, false);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected Set<Map.Entry<K, V>> createEntrySet() {
            return new RangeEntrySet(this);
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        protected SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2) {
            return new RangeEntryMap(k, z, k2, z2);
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            TrieEntry<K, V> higherEntry;
            K k = this.fromKey;
            if (k == null) {
                higherEntry = AbstractPatriciaTrie.this.firstEntry();
            } else if (this.fromInclusive) {
                higherEntry = AbstractPatriciaTrie.this.ceilingEntry(k);
            } else {
                higherEntry = AbstractPatriciaTrie.this.higherEntry(k);
            }
            K key = higherEntry != null ? higherEntry.getKey() : null;
            if (higherEntry == null || (this.toKey != null && !inToRange(key, false))) {
                throw new NoSuchElementException();
            }
            return key;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public K getFromKey() {
            return this.fromKey;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public K getToKey() {
            return this.toKey;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public boolean isFromInclusive() {
            return this.fromInclusive;
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.RangeMap
        public boolean isToInclusive() {
            return this.toInclusive;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            TrieEntry<K, V> lowerEntry;
            K k = this.toKey;
            if (k == null) {
                lowerEntry = AbstractPatriciaTrie.this.lastEntry();
            } else if (this.toInclusive) {
                lowerEntry = AbstractPatriciaTrie.this.floorEntry(k);
            } else {
                lowerEntry = AbstractPatriciaTrie.this.lowerEntry(k);
            }
            K key = lowerEntry != null ? lowerEntry.getKey() : null;
            if (lowerEntry == null || (this.fromKey != null && !inFromRange(key, false))) {
                throw new NoSuchElementException();
            }
            return key;
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected RangeEntryMap(K k, boolean z, K k2, boolean z2) {
            super();
            if (k == 0 && k2 == 0) {
                throw new IllegalArgumentException("must have a from or to!");
            }
            if (k != 0 && k2 != 0 && AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, k2) > 0) {
                throw new IllegalArgumentException("fromKey > toKey");
            }
            this.fromKey = k;
            this.fromInclusive = z;
            this.toKey = k2;
            this.toInclusive = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class RangeEntrySet extends AbstractSet<Map.Entry<K, V>> {
        private final AbstractPatriciaTrie<K, V>.RangeMap delegate;
        private transient int expectedModCount;
        private transient int size = -1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public final class EntryIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<Map.Entry<K, V>> {
            private final K excludedKey;

            @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieIterator, java.util.Iterator
            public boolean hasNext() {
                TrieEntry<K, V> trieEntry = this.next;
                return trieEntry != null && !AbstractBitwiseTrie.compare(trieEntry.key, this.excludedKey);
            }

            private EntryIterator(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2) {
                super(trieEntry);
                this.excludedKey = trieEntry2 != null ? trieEntry2.getKey() : null;
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                TrieEntry<K, V> trieEntry = this.next;
                if (trieEntry != null && !AbstractBitwiseTrie.compare(trieEntry.key, this.excludedKey)) {
                    return nextEntry();
                }
                throw new NoSuchElementException();
            }
        }

        public RangeEntrySet(AbstractPatriciaTrie<K, V>.RangeMap rangeMap) {
            if (rangeMap != null) {
                this.delegate = rangeMap;
                return;
            }
            throw new NullPointerException("delegate");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TrieEntry<K, V> entry;
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry2 = (Map.Entry) obj;
            Object key = entry2.getKey();
            return this.delegate.inRange(key) && (entry = AbstractPatriciaTrie.this.getEntry(key)) != null && AbstractBitwiseTrie.compare(entry.getValue(), entry2.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return !iterator().hasNext();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            TrieEntry<K, V> ceilingEntry;
            K fromKey = this.delegate.getFromKey();
            K toKey = this.delegate.getToKey();
            if (fromKey == null) {
                ceilingEntry = AbstractPatriciaTrie.this.firstEntry();
            } else {
                ceilingEntry = AbstractPatriciaTrie.this.ceilingEntry(fromKey);
            }
            return new EntryIterator(ceilingEntry, toKey != null ? AbstractPatriciaTrie.this.ceilingEntry(toKey) : null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            TrieEntry<K, V> entry;
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry2 = (Map.Entry) obj;
            Object key = entry2.getKey();
            if (!this.delegate.inRange(key) || (entry = AbstractPatriciaTrie.this.getEntry(key)) == null || !AbstractBitwiseTrie.compare(entry.getValue(), entry2.getValue())) {
                return false;
            }
            AbstractPatriciaTrie.this.removeEntry(entry);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            if (this.size == -1 || this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                this.size = 0;
                Iterator<Map.Entry<K, V>> it2 = iterator();
                while (it2.hasNext()) {
                    this.size++;
                    it2.next();
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public abstract class RangeMap extends AbstractMap<K, V> implements SortedMap<K, V> {
        private volatile transient Set<Map.Entry<K, V>> entrySet;

        private RangeMap() {
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return AbstractPatriciaTrie.this.comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return false;
            }
            return AbstractPatriciaTrie.this.containsKey(obj);
        }

        protected abstract Set<Map.Entry<K, V>> createEntrySet();

        protected abstract SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2);

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = createEntrySet();
            }
            return this.entrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return null;
            }
            return (V) AbstractPatriciaTrie.this.mo12663get(obj);
        }

        protected abstract K getFromKey();

        protected abstract K getToKey();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            if (inRange2(k)) {
                return createRangeMap(getFromKey(), isFromInclusive(), k, isToInclusive());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("ToKey is out of range: ", k));
        }

        protected boolean inFromRange(K k, boolean z) {
            Object fromKey = getFromKey();
            boolean isFromInclusive = isFromInclusive();
            int compare = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, fromKey);
            return (isFromInclusive || z) ? compare >= 0 : compare > 0;
        }

        protected boolean inRange(K k) {
            Object fromKey = getFromKey();
            Object toKey = getToKey();
            if (fromKey == null || inFromRange(k, false)) {
                return toKey == null || inToRange(k, false);
            }
            return false;
        }

        protected boolean inRange2(K k) {
            return (getFromKey() == null || inFromRange(k, false)) && (getToKey() == null || inToRange(k, true));
        }

        protected boolean inToRange(K k, boolean z) {
            Object toKey = getToKey();
            boolean isToInclusive = isToInclusive();
            int compare = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, toKey);
            return (isToInclusive || z) ? compare <= 0 : compare < 0;
        }

        protected abstract boolean isFromInclusive();

        protected abstract boolean isToInclusive();

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            if (inRange(k)) {
                return (V) AbstractPatriciaTrie.this.put(k, v);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("Key is out of range: ", k));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return null;
            }
            return (V) AbstractPatriciaTrie.this.mo12668remove(obj);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            if (inRange2(k)) {
                if (inRange2(k2)) {
                    return createRangeMap(k, isFromInclusive(), k2, isToInclusive());
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("ToKey is out of range: ", k2));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("FromKey is out of range: ", k));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            if (inRange2(k)) {
                return createRangeMap(k, isFromInclusive(), getToKey(), isToInclusive());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("FromKey is out of range: ", k));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Reference<E> {
        private E item;

        private Reference() {
        }

        public E get() {
            return this.item;
        }

        public void set(E e) {
            this.item = e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class TrieEntry<K, V> extends AbstractBitwiseTrie.BasicEntry<K, V> {
        private static final long serialVersionUID = 4596023148184140013L;
        protected int bitIndex;
        protected TrieEntry<K, V> left;
        protected TrieEntry<K, V> parent;
        protected TrieEntry<K, V> predecessor;
        protected TrieEntry<K, V> right;

        public TrieEntry(K k, V v, int i) {
            super(k, v);
            this.bitIndex = i;
            this.parent = null;
            this.left = this;
            this.right = null;
            this.predecessor = this;
        }

        public boolean isEmpty() {
            return this.key == null;
        }

        public boolean isExternalNode() {
            return !isInternalNode();
        }

        public boolean isInternalNode() {
            return (this.left == this || this.right == this) ? false : true;
        }

        @Override // org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.bitIndex == -1) {
                sb.append("RootEntry(");
            } else {
                sb.append("Entry(");
            }
            sb.append("key=");
            sb.append(getKey());
            sb.append(" [");
            sb.append(this.bitIndex);
            sb.append("], ");
            sb.append("value=");
            sb.append(getValue());
            sb.append(", ");
            TrieEntry<K, V> trieEntry = this.parent;
            if (trieEntry != null) {
                if (trieEntry.bitIndex == -1) {
                    sb.append("parent=");
                    sb.append("ROOT");
                } else {
                    sb.append("parent=");
                    sb.append(this.parent.getKey());
                    sb.append(" [");
                    sb.append(this.parent.bitIndex);
                    sb.append("]");
                }
            } else {
                sb.append("parent=");
                sb.append("null");
            }
            sb.append(", ");
            TrieEntry<K, V> trieEntry2 = this.left;
            if (trieEntry2 != null) {
                if (trieEntry2.bitIndex == -1) {
                    sb.append("left=");
                    sb.append("ROOT");
                } else {
                    sb.append("left=");
                    sb.append(this.left.getKey());
                    sb.append(" [");
                    sb.append(this.left.bitIndex);
                    sb.append("]");
                }
            } else {
                sb.append("left=");
                sb.append("null");
            }
            sb.append(", ");
            TrieEntry<K, V> trieEntry3 = this.right;
            if (trieEntry3 != null) {
                if (trieEntry3.bitIndex == -1) {
                    sb.append("right=");
                    sb.append("ROOT");
                } else {
                    sb.append("right=");
                    sb.append(this.right.getKey());
                    sb.append(" [");
                    sb.append(this.right.bitIndex);
                    sb.append("]");
                }
            } else {
                sb.append("right=");
                sb.append("null");
            }
            sb.append(", ");
            TrieEntry<K, V> trieEntry4 = this.predecessor;
            if (trieEntry4 != null) {
                if (trieEntry4.bitIndex == -1) {
                    sb.append("predecessor=");
                    sb.append("ROOT");
                } else {
                    sb.append("predecessor=");
                    sb.append(this.predecessor.getKey());
                    sb.append(" [");
                    sb.append(this.predecessor.bitIndex);
                    sb.append("]");
                }
            }
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class TrieMapIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<K> implements OrderedMapIterator<K, V> {
        protected TrieEntry<K, V> previous;

        private TrieMapIterator() {
            super();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            TrieEntry<K, V> trieEntry = this.current;
            if (trieEntry != null) {
                return trieEntry.getKey();
            }
            throw new IllegalStateException();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            TrieEntry<K, V> trieEntry = this.current;
            if (trieEntry != null) {
                return trieEntry.getValue();
            }
            throw new IllegalStateException();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return this.previous != null;
        }

        @Override // java.util.Iterator, org.apache.commons.collections4.MapIterator
        /* renamed from: next */
        public K mo12683next() {
            return nextEntry().getKey();
        }

        @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieIterator
        protected TrieEntry<K, V> nextEntry() {
            TrieEntry<K, V> nextEntry = super.nextEntry();
            this.previous = nextEntry;
            return nextEntry;
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            return previousEntry().getKey();
        }

        protected TrieEntry<K, V> previousEntry() {
            int i = this.expectedModCount;
            AbstractPatriciaTrie abstractPatriciaTrie = AbstractPatriciaTrie.this;
            if (i == abstractPatriciaTrie.modCount) {
                TrieEntry<K, V> trieEntry = this.previous;
                if (trieEntry != null) {
                    this.previous = abstractPatriciaTrie.previousEntry(trieEntry);
                    this.next = this.current;
                    this.current = trieEntry;
                    return this.current;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            TrieEntry<K, V> trieEntry = this.current;
            if (trieEntry != null) {
                return trieEntry.setValue(v);
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class Values extends AbstractCollection<V> {

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public class ValueIterator extends AbstractPatriciaTrie<K, V>.TrieIterator<V> {
            private ValueIterator() {
                super();
            }

            @Override // java.util.Iterator
            public V next() {
                return nextEntry().getValue();
            }
        }

        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractPatriciaTrie.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            Iterator<V> it2 = iterator();
            while (it2.hasNext()) {
                if (AbstractBitwiseTrie.compare(it2.next(), obj)) {
                    it2.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractPatriciaTrie.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        super(keyAnalyzer);
        this.root = new TrieEntry<>(null, null, -1);
        this.size = 0;
        this.modCount = 0;
    }

    private SortedMap<K, V> getPrefixMapByBits(K k, int i, int i2) {
        int i3 = i + i2;
        if (i3 <= lengthInBits(k)) {
            return i3 == 0 ? this : new PrefixRangeMap(k, i, i2);
        }
        throw new IllegalArgumentException(i + " + " + i2 + " > " + lengthInBits(k));
    }

    private void incrementModCount() {
        this.modCount++;
    }

    static boolean isValidUplink(TrieEntry<?, ?> trieEntry, TrieEntry<?, ?> trieEntry2) {
        return trieEntry != null && trieEntry.bitIndex <= trieEntry2.bitIndex && !trieEntry.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.root = new TrieEntry<>(null, null, -1);
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    private void removeExternalEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry != this.root) {
            if (trieEntry.isExternalNode()) {
                TrieEntry<K, V> trieEntry2 = trieEntry.parent;
                TrieEntry<K, V> trieEntry3 = trieEntry.left;
                if (trieEntry3 == trieEntry) {
                    trieEntry3 = trieEntry.right;
                }
                if (trieEntry2.left == trieEntry) {
                    trieEntry2.left = trieEntry3;
                } else {
                    trieEntry2.right = trieEntry3;
                }
                if (trieEntry3.bitIndex > trieEntry2.bitIndex) {
                    trieEntry3.parent = trieEntry2;
                    return;
                } else {
                    trieEntry3.predecessor = trieEntry2;
                    return;
                }
            }
            throw new IllegalArgumentException(trieEntry + " is not an external Entry!");
        }
        throw new IllegalArgumentException("Cannot delete root Entry!");
    }

    private void removeInternalEntry(TrieEntry<K, V> trieEntry) {
        TrieEntry<K, V> trieEntry2;
        if (trieEntry != this.root) {
            if (trieEntry.isInternalNode()) {
                TrieEntry<K, V> trieEntry3 = trieEntry.predecessor;
                trieEntry3.bitIndex = trieEntry.bitIndex;
                TrieEntry<K, V> trieEntry4 = trieEntry3.parent;
                TrieEntry<K, V> trieEntry5 = trieEntry3.left;
                if (trieEntry5 == trieEntry) {
                    trieEntry5 = trieEntry3.right;
                }
                if (trieEntry3.predecessor == trieEntry3 && (trieEntry2 = trieEntry3.parent) != trieEntry) {
                    trieEntry3.predecessor = trieEntry2;
                }
                if (trieEntry4.left == trieEntry3) {
                    trieEntry4.left = trieEntry5;
                } else {
                    trieEntry4.right = trieEntry5;
                }
                if (trieEntry5.bitIndex > trieEntry4.bitIndex) {
                    trieEntry5.parent = trieEntry4;
                }
                TrieEntry<K, V> trieEntry6 = trieEntry.left;
                if (trieEntry6.parent == trieEntry) {
                    trieEntry6.parent = trieEntry3;
                }
                TrieEntry<K, V> trieEntry7 = trieEntry.right;
                if (trieEntry7.parent == trieEntry) {
                    trieEntry7.parent = trieEntry3;
                }
                TrieEntry<K, V> trieEntry8 = trieEntry.parent;
                if (trieEntry8.left == trieEntry) {
                    trieEntry8.left = trieEntry3;
                } else {
                    trieEntry8.right = trieEntry3;
                }
                trieEntry3.parent = trieEntry.parent;
                trieEntry3.left = trieEntry.left;
                trieEntry3.right = trieEntry.right;
                if (isValidUplink(trieEntry3.left, trieEntry3)) {
                    trieEntry3.left.predecessor = trieEntry3;
                }
                if (!isValidUplink(trieEntry3.right, trieEntry3)) {
                    return;
                }
                trieEntry3.right.predecessor = trieEntry3;
                return;
            }
            throw new IllegalArgumentException(trieEntry + " is not an internal Entry!");
        }
        throw new IllegalArgumentException("Cannot delete root Entry!");
    }

    private boolean selectR(TrieEntry<K, V> trieEntry, int i, K k, int i2, Reference<Map.Entry<K, V>> reference) {
        int i3 = trieEntry.bitIndex;
        if (i3 <= i) {
            if (trieEntry.isEmpty()) {
                return true;
            }
            reference.set(trieEntry);
            return false;
        }
        if (!isBitSet(k, i3, i2)) {
            if (selectR(trieEntry.left, trieEntry.bitIndex, k, i2, reference)) {
                return selectR(trieEntry.right, trieEntry.bitIndex, k, i2, reference);
            }
        } else if (selectR(trieEntry.right, trieEntry.bitIndex, k, i2, reference)) {
            return selectR(trieEntry.left, trieEntry.bitIndex, k, i2, reference);
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    TrieEntry<K, V> addEntry(TrieEntry<K, V> trieEntry, int i) {
        TrieEntry<K, V> trieEntry2;
        TrieEntry<K, V> trieEntry3 = this.root;
        TrieEntry<K, V> trieEntry4 = trieEntry3.left;
        while (true) {
            TrieEntry<K, V> trieEntry5 = trieEntry4;
            trieEntry2 = trieEntry3;
            trieEntry3 = trieEntry5;
            int i2 = trieEntry3.bitIndex;
            if (i2 >= trieEntry.bitIndex || i2 <= trieEntry2.bitIndex) {
                break;
            } else if (!isBitSet(trieEntry.key, i2, i)) {
                trieEntry4 = trieEntry3.left;
            } else {
                trieEntry4 = trieEntry3.right;
            }
        }
        trieEntry.predecessor = trieEntry;
        if (!isBitSet(trieEntry.key, trieEntry.bitIndex, i)) {
            trieEntry.left = trieEntry;
            trieEntry.right = trieEntry3;
        } else {
            trieEntry.left = trieEntry3;
            trieEntry.right = trieEntry;
        }
        trieEntry.parent = trieEntry2;
        if (trieEntry3.bitIndex >= trieEntry.bitIndex) {
            trieEntry3.parent = trieEntry;
        }
        if (trieEntry3.bitIndex <= trieEntry2.bitIndex) {
            trieEntry3.predecessor = trieEntry;
        }
        if (trieEntry2 != this.root && isBitSet(trieEntry.key, trieEntry2.bitIndex, i)) {
            trieEntry2.right = trieEntry;
        } else {
            trieEntry2.left = trieEntry;
        }
        return trieEntry;
    }

    TrieEntry<K, V> ceilingEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits == 0) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return firstEntry();
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
        if (compareKeys(k, nearestEntryForKey.key)) {
            return nearestEntryForKey;
        }
        int bitIndex = bitIndex(k, nearestEntryForKey.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, V> trieEntry = new TrieEntry<>(k, null, bitIndex);
            addEntry(trieEntry, lengthInBits);
            incrementSize();
            TrieEntry<K, V> nextEntry = nextEntry(trieEntry);
            removeEntry(trieEntry);
            this.modCount -= 2;
            return nextEntry;
        } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!this.root.isEmpty()) {
                return this.root;
            }
            return firstEntry();
        } else if (!KeyAnalyzer.isEqualBitKey(bitIndex)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("invalid lookup: ", k));
        } else {
            return nearestEntryForKey;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        TrieEntry<K, V> trieEntry = this.root;
        trieEntry.key = null;
        trieEntry.bitIndex = -1;
        trieEntry.value = null;
        trieEntry.parent = null;
        trieEntry.left = trieEntry;
        trieEntry.right = null;
        trieEntry.predecessor = trieEntry;
        this.size = 0;
        incrementModCount();
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return getKeyAnalyzer();
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        K castKey = castKey(obj);
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(castKey, lengthInBits(castKey));
        return !nearestEntryForKey.isEmpty() && compareKeys(castKey, nearestEntryForKey.key);
    }

    void decrementSize() {
        this.size--;
        incrementModCount();
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet();
        }
        return this.entrySet;
    }

    TrieEntry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return followLeft(this.root);
    }

    @Override // java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        if (size() != 0) {
            return firstEntry().getKey();
        }
        throw new NoSuchElementException();
    }

    TrieEntry<K, V> floorEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits == 0) {
            if (this.root.isEmpty()) {
                return null;
            }
            return this.root;
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
        if (compareKeys(k, nearestEntryForKey.key)) {
            return nearestEntryForKey;
        }
        int bitIndex = bitIndex(k, nearestEntryForKey.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, V> trieEntry = new TrieEntry<>(k, null, bitIndex);
            addEntry(trieEntry, lengthInBits);
            incrementSize();
            TrieEntry<K, V> previousEntry = previousEntry(trieEntry);
            removeEntry(trieEntry);
            this.modCount -= 2;
            return previousEntry;
        } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (this.root.isEmpty()) {
                return null;
            }
            return this.root;
        } else if (!KeyAnalyzer.isEqualBitKey(bitIndex)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("invalid lookup: ", k));
        } else {
            return nearestEntryForKey;
        }
    }

    TrieEntry<K, V> followLeft(TrieEntry<K, V> trieEntry) {
        while (true) {
            TrieEntry<K, V> trieEntry2 = trieEntry.left;
            if (trieEntry2.isEmpty()) {
                trieEntry2 = trieEntry.right;
            }
            if (trieEntry2.bitIndex <= trieEntry.bitIndex) {
                return trieEntry2;
            }
            trieEntry = trieEntry2;
        }
    }

    TrieEntry<K, V> followRight(TrieEntry<K, V> trieEntry) {
        if (trieEntry.right == null) {
            return null;
        }
        while (true) {
            TrieEntry<K, V> trieEntry2 = trieEntry.right;
            if (trieEntry2.bitIndex <= trieEntry.bitIndex) {
                return trieEntry2;
            }
            trieEntry = trieEntry2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        TrieEntry<K, V> entry = getEntry(obj);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    TrieEntry<K, V> getEntry(Object obj) {
        K castKey = castKey(obj);
        if (castKey == null) {
            return null;
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(castKey, lengthInBits(castKey));
        if (!nearestEntryForKey.isEmpty() && compareKeys(castKey, nearestEntryForKey.key)) {
            return nearestEntryForKey;
        }
        return null;
    }

    TrieEntry<K, V> getNearestEntryForKey(K k, int i) {
        TrieEntry<K, V> trieEntry = this.root;
        TrieEntry<K, V> trieEntry2 = trieEntry.left;
        while (true) {
            TrieEntry<K, V> trieEntry3 = trieEntry2;
            TrieEntry<K, V> trieEntry4 = trieEntry;
            trieEntry = trieEntry3;
            int i2 = trieEntry.bitIndex;
            if (i2 <= trieEntry4.bitIndex) {
                return trieEntry;
            }
            if (!isBitSet(k, i2, i)) {
                trieEntry2 = trieEntry.left;
            } else {
                trieEntry2 = trieEntry.right;
            }
        }
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return new RangeEntryMap(this, null, k);
    }

    TrieEntry<K, V> higherEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits == 0) {
            if (!this.root.isEmpty()) {
                if (size() <= 1) {
                    return null;
                }
                return nextEntry(this.root);
            }
            return firstEntry();
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
        if (compareKeys(k, nearestEntryForKey.key)) {
            return nextEntry(nearestEntryForKey);
        }
        int bitIndex = bitIndex(k, nearestEntryForKey.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, V> trieEntry = new TrieEntry<>(k, null, bitIndex);
            addEntry(trieEntry, lengthInBits);
            incrementSize();
            TrieEntry<K, V> nextEntry = nextEntry(trieEntry);
            removeEntry(trieEntry);
            this.modCount -= 2;
            return nextEntry;
        } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!this.root.isEmpty()) {
                return firstEntry();
            }
            if (size() <= 1) {
                return null;
            }
            return nextEntry(firstEntry());
        } else if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return nextEntry(nearestEntryForKey);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("invalid lookup: ", k));
        }
    }

    void incrementSize() {
        this.size++;
        incrementModCount();
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet();
        }
        return this.keySet;
    }

    TrieEntry<K, V> lastEntry() {
        return followRight(this.root.left);
    }

    @Override // java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        TrieEntry<K, V> lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    TrieEntry<K, V> lowerEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits == 0) {
            return null;
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
        if (compareKeys(k, nearestEntryForKey.key)) {
            return previousEntry(nearestEntryForKey);
        }
        int bitIndex = bitIndex(k, nearestEntryForKey.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry<K, V> trieEntry = new TrieEntry<>(k, null, bitIndex);
            addEntry(trieEntry, lengthInBits);
            incrementSize();
            TrieEntry<K, V> previousEntry = previousEntry(trieEntry);
            removeEntry(trieEntry);
            this.modCount -= 2;
            return previousEntry;
        } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            return null;
        } else {
            if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
                return previousEntry(nearestEntryForKey);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("invalid lookup: ", k));
        }
    }

    TrieEntry<K, V> nextEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry == null) {
            return firstEntry();
        }
        return nextEntryImpl(trieEntry.predecessor, trieEntry, null);
    }

    TrieEntry<K, V> nextEntryImpl(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2, TrieEntry<K, V> trieEntry3) {
        TrieEntry<K, V> trieEntry4;
        TrieEntry<K, V> trieEntry5;
        if (trieEntry2 == null || trieEntry != trieEntry2.predecessor) {
            while (!trieEntry.left.isEmpty() && trieEntry2 != (trieEntry4 = trieEntry.left)) {
                if (isValidUplink(trieEntry4, trieEntry)) {
                    return trieEntry.left;
                }
                trieEntry = trieEntry.left;
            }
        }
        if (!trieEntry.isEmpty() && (trieEntry5 = trieEntry.right) != null) {
            if (trieEntry2 != trieEntry5) {
                if (isValidUplink(trieEntry5, trieEntry)) {
                    return trieEntry.right;
                }
                return nextEntryImpl(trieEntry.right, trieEntry2, trieEntry3);
            }
            while (true) {
                TrieEntry<K, V> trieEntry6 = trieEntry.parent;
                TrieEntry<K, V> trieEntry7 = trieEntry6.right;
                if (trieEntry != trieEntry7) {
                    if (trieEntry == trieEntry3 || trieEntry7 == null) {
                        return null;
                    }
                    if (trieEntry2 != trieEntry7 && isValidUplink(trieEntry7, trieEntry6)) {
                        return trieEntry.parent.right;
                    }
                    TrieEntry<K, V> trieEntry8 = trieEntry.parent;
                    TrieEntry<K, V> trieEntry9 = trieEntry8.right;
                    if (trieEntry9 != trieEntry8) {
                        return nextEntryImpl(trieEntry9, trieEntry2, trieEntry3);
                    }
                    return null;
                } else if (trieEntry == trieEntry3) {
                    return null;
                } else {
                    trieEntry = trieEntry6;
                }
            }
        } else {
            return null;
        }
    }

    TrieEntry<K, V> nextEntryInSubtree(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2) {
        if (trieEntry == null) {
            return firstEntry();
        }
        return nextEntryImpl(trieEntry.predecessor, trieEntry, trieEntry2);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(K k) {
        TrieEntry<K, V> nextEntry;
        if (k != null) {
            TrieEntry<K, V> entry = getEntry(k);
            if (entry != null && (nextEntry = nextEntry(entry)) != null) {
                return nextEntry.getKey();
            }
            return null;
        }
        throw new NullPointerException();
    }

    @Override // org.apache.commons.collections4.Trie
    public SortedMap<K, V> prefixMap(K k) {
        return getPrefixMapByBits(k, 0, lengthInBits(k));
    }

    TrieEntry<K, V> previousEntry(TrieEntry<K, V> trieEntry) {
        TrieEntry<K, V> trieEntry2 = trieEntry.predecessor;
        if (trieEntry2 != null) {
            if (trieEntry2.right == trieEntry) {
                if (isValidUplink(trieEntry2.left, trieEntry2)) {
                    return trieEntry.predecessor.left;
                }
                return followRight(trieEntry.predecessor.left);
            }
            while (true) {
                TrieEntry<K, V> trieEntry3 = trieEntry2.parent;
                if (trieEntry3 == null || trieEntry2 != trieEntry3.left) {
                    break;
                }
                trieEntry2 = trieEntry3;
            }
            TrieEntry<K, V> trieEntry4 = trieEntry2.parent;
            if (trieEntry4 == null) {
                return null;
            }
            if (isValidUplink(trieEntry4.left, trieEntry4)) {
                TrieEntry<K, V> trieEntry5 = trieEntry2.parent.left;
                TrieEntry<K, V> trieEntry6 = this.root;
                if (trieEntry5 != trieEntry6) {
                    return trieEntry5;
                }
                if (!trieEntry6.isEmpty()) {
                    return this.root;
                }
                return null;
            }
            return followRight(trieEntry2.parent.left);
        }
        throw new IllegalArgumentException("must have come from somewhere!");
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(K k) {
        TrieEntry<K, V> previousEntry;
        if (k != null) {
            TrieEntry<K, V> entry = getEntry(k);
            if (entry != null && (previousEntry = previousEntry(entry)) != null) {
                return previousEntry.getKey();
            }
            return null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (k != null) {
            int lengthInBits = lengthInBits(k);
            if (lengthInBits == 0) {
                if (this.root.isEmpty()) {
                    incrementSize();
                } else {
                    incrementModCount();
                }
                return this.root.setKeyValue(k, v);
            }
            TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
            if (compareKeys(k, nearestEntryForKey.key)) {
                if (nearestEntryForKey.isEmpty()) {
                    incrementSize();
                } else {
                    incrementModCount();
                }
                return nearestEntryForKey.setKeyValue(k, v);
            }
            int bitIndex = bitIndex(k, nearestEntryForKey.key);
            if (!KeyAnalyzer.isOutOfBoundsIndex(bitIndex)) {
                if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                    addEntry(new TrieEntry<>(k, v, bitIndex), lengthInBits);
                    incrementSize();
                    return null;
                } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                    if (this.root.isEmpty()) {
                        incrementSize();
                    } else {
                        incrementModCount();
                    }
                    return this.root.setKeyValue(k, v);
                } else if (KeyAnalyzer.isEqualBitKey(bitIndex) && nearestEntryForKey != this.root) {
                    incrementModCount();
                    return nearestEntryForKey.setKeyValue(k, v);
                }
            }
            throw new IllegalArgumentException("Failed to put: " + k + " -> " + v + ", " + bitIndex);
        }
        throw new NullPointerException("Key cannot be null");
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        if (obj == null) {
            return null;
        }
        K castKey = castKey(obj);
        int lengthInBits = lengthInBits(castKey);
        TrieEntry<K, V> trieEntry = this.root;
        TrieEntry<K, V> trieEntry2 = trieEntry.left;
        while (true) {
            TrieEntry<K, V> trieEntry3 = trieEntry2;
            TrieEntry<K, V> trieEntry4 = trieEntry;
            trieEntry = trieEntry3;
            int i = trieEntry.bitIndex;
            if (i <= trieEntry4.bitIndex) {
                break;
            } else if (!isBitSet(castKey, i, lengthInBits)) {
                trieEntry2 = trieEntry.left;
            } else {
                trieEntry2 = trieEntry.right;
            }
        }
        if (!trieEntry.isEmpty() && compareKeys(castKey, trieEntry.key)) {
            return removeEntry(trieEntry);
        }
        return null;
    }

    V removeEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry != this.root) {
            if (trieEntry.isInternalNode()) {
                removeInternalEntry(trieEntry);
            } else {
                removeExternalEntry(trieEntry);
            }
        }
        decrementSize();
        return trieEntry.setKeyValue(null, null);
    }

    public Map.Entry<K, V> select(K k) {
        int lengthInBits = lengthInBits(k);
        Reference<Map.Entry<K, V>> reference = new Reference<>();
        if (!selectR(this.root.left, -1, k, lengthInBits, reference)) {
            return reference.get();
        }
        return null;
    }

    public K selectKey(K k) {
        Map.Entry<K, V> select = select(k);
        if (select == null) {
            return null;
        }
        return select.getKey();
    }

    public V selectValue(K k) {
        Map.Entry<K, V> select = select(k);
        if (select == null) {
            return null;
        }
        return select.getValue();
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.size;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return new RangeEntryMap(this, k, k2);
    }

    TrieEntry<K, V> subtree(K k, int i, int i2) {
        TrieEntry<K, V> trieEntry;
        TrieEntry<K, V> trieEntry2 = this.root;
        TrieEntry<K, V> trieEntry3 = trieEntry2.left;
        while (true) {
            TrieEntry<K, V> trieEntry4 = trieEntry3;
            trieEntry = trieEntry2;
            trieEntry2 = trieEntry4;
            int i3 = trieEntry2.bitIndex;
            if (i3 <= trieEntry.bitIndex || i2 <= i3) {
                break;
            } else if (!isBitSet(k, i3 + i, i + i2)) {
                trieEntry3 = trieEntry2.left;
            } else {
                trieEntry3 = trieEntry2.right;
            }
        }
        if (trieEntry2.isEmpty()) {
            trieEntry2 = trieEntry;
        }
        if (trieEntry2.isEmpty()) {
            return null;
        }
        int i4 = i + i2;
        if (trieEntry2 == this.root && lengthInBits(trieEntry2.getKey()) < i4) {
            return null;
        }
        boolean isBitSet = isBitSet(k, i4 - 1, i4);
        K k2 = trieEntry2.key;
        if (isBitSet != isBitSet(k2, i2 - 1, lengthInBits(k2))) {
            return null;
        }
        int bitIndex = getKeyAnalyzer().bitIndex(k, i, i2, trieEntry2.key, 0, lengthInBits(trieEntry2.getKey()));
        if (bitIndex >= 0 && bitIndex < i2) {
            return null;
        }
        return trieEntry2;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return new RangeEntryMap(this, k, null);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        if (this.values == null) {
            this.values = new Values();
        }
        return this.values;
    }

    @Override // org.apache.commons.collections4.OrderedMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return new TrieMapIterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public abstract class TrieIterator<E> implements Iterator<E> {
        protected TrieEntry<K, V> current;
        protected int expectedModCount;
        protected TrieEntry<K, V> next;

        protected TrieIterator() {
            this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            this.next = AbstractPatriciaTrie.this.nextEntry(null);
        }

        protected TrieEntry<K, V> findNext(TrieEntry<K, V> trieEntry) {
            return AbstractPatriciaTrie.this.nextEntry(trieEntry);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        protected TrieEntry<K, V> nextEntry() {
            if (this.expectedModCount == AbstractPatriciaTrie.this.modCount) {
                TrieEntry<K, V> trieEntry = this.next;
                if (trieEntry != null) {
                    this.next = findNext(trieEntry);
                    this.current = trieEntry;
                    return trieEntry;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            TrieEntry<K, V> trieEntry = this.current;
            if (trieEntry != null) {
                int i = this.expectedModCount;
                AbstractPatriciaTrie abstractPatriciaTrie = AbstractPatriciaTrie.this;
                if (i == abstractPatriciaTrie.modCount) {
                    this.current = null;
                    abstractPatriciaTrie.removeEntry(trieEntry);
                    this.expectedModCount = AbstractPatriciaTrie.this.modCount;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }

        protected TrieIterator(TrieEntry<K, V> trieEntry) {
            this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            this.next = trieEntry;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer, Map<? extends K, ? extends V> map) {
        super(keyAnalyzer);
        this.root = new TrieEntry<>(null, null, -1);
        this.size = 0;
        this.modCount = 0;
        putAll(map);
    }
}
