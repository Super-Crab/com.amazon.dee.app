package com.google.common.collect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableMap.of or another implementation")
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes3.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    @RetainedWith
    @LazyInit
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    @RetainedWith
    @LazyInit
    private transient ImmutableSet<K> keySet;
    @LazyInit
    private transient ImmutableSetMultimap<K, V> multimapView;
    @RetainedWith
    @LazyInit
    private transient ImmutableCollection<V> values;

    @DoNotMock
    /* loaded from: classes3.dex */
    public static class Builder<K, V> {
        Object[] alternatingKeysAndValues;
        boolean entriesUsed;
        int size;
        @NullableDecl
        Comparator<? super V> valueComparator;

        public Builder() {
            this(4);
        }

        private void ensureCapacity(int i) {
            int i2 = i * 2;
            Object[] objArr = this.alternatingKeysAndValues;
            if (i2 > objArr.length) {
                this.alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i2));
                this.entriesUsed = false;
            }
        }

        /* renamed from: build */
        public ImmutableMap<K, V> mo7826build() {
            sortEntries();
            this.entriesUsed = true;
            return RegularImmutableMap.create(this.size, this.alternatingKeysAndValues);
        }

        @CanIgnoreReturnValue
        @Beta
        /* renamed from: orderEntriesByValue */
        public Builder<K, V> mo7827orderEntriesByValue(Comparator<? super V> comparator) {
            Preconditions.checkState(this.valueComparator == null, "valueComparator was already set");
            this.valueComparator = (Comparator) Preconditions.checkNotNull(comparator, "valueComparator");
            return this;
        }

        @CanIgnoreReturnValue
        /* renamed from: put */
        public Builder<K, V> mo7828put(K k, V v) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(k, v);
            Object[] objArr = this.alternatingKeysAndValues;
            int i = this.size;
            objArr[i * 2] = k;
            objArr[(i * 2) + 1] = v;
            this.size = i + 1;
            return this;
        }

        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public Builder<K, V> mo7831putAll(Map<? extends K, ? extends V> map) {
            return mo7830putAll(map.entrySet());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void sortEntries() {
            int i;
            if (this.valueComparator != null) {
                if (this.entriesUsed) {
                    this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, this.size * 2);
                }
                Map.Entry[] entryArr = new Map.Entry[this.size];
                int i2 = 0;
                while (true) {
                    i = this.size;
                    if (i2 >= i) {
                        break;
                    }
                    Object[] objArr = this.alternatingKeysAndValues;
                    int i3 = i2 * 2;
                    entryArr[i2] = new AbstractMap.SimpleImmutableEntry(objArr[i3], objArr[i3 + 1]);
                    i2++;
                }
                Arrays.sort(entryArr, 0, i, Ordering.from(this.valueComparator).onResultOf(Maps.valueFunction()));
                for (int i4 = 0; i4 < this.size; i4++) {
                    int i5 = i4 * 2;
                    this.alternatingKeysAndValues[i5] = entryArr[i4].getKey();
                    this.alternatingKeysAndValues[i5 + 1] = entryArr[i4].getValue();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(int i) {
            this.alternatingKeysAndValues = new Object[i * 2];
            this.size = 0;
            this.entriesUsed = false;
        }

        @CanIgnoreReturnValue
        @Beta
        /* renamed from: putAll */
        public Builder<K, V> mo7830putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(((Collection) iterable).size() + this.size);
            }
            for (Map.Entry<? extends K, ? extends V> entry : iterable) {
                mo7829put(entry);
            }
            return this;
        }

        @CanIgnoreReturnValue
        /* renamed from: put */
        public Builder<K, V> mo7829put(Map.Entry<? extends K, ? extends V> entry) {
            return mo7828put(entry.getKey(), entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        @Override // com.google.common.collect.ImmutableMap
        ImmutableSet<Map.Entry<K, V>> createEntrySet() {
            return new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap.1EntrySetImpl
                @Override // com.google.common.collect.ImmutableMapEntrySet
                ImmutableMap<K, V> map() {
                    return IteratorBasedImmutableMap.this;
                }

                @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
                /* renamed from: iterator */
                public UnmodifiableIterator<Map.Entry<K, V>> mo8029iterator() {
                    return IteratorBasedImmutableMap.this.entryIterator();
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return new ImmutableMapKeySet(this);
        }

        @Override // com.google.common.collect.ImmutableMap
        /* renamed from: createValues */
        ImmutableCollection<V> mo7696createValues() {
            return new ImmutableMapValues(this);
        }

        abstract UnmodifiableIterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        /* renamed from: entrySet */
        public /* bridge */ /* synthetic */ Set mo7814entrySet() {
            return super.mo7737entrySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        /* renamed from: keySet */
        public /* bridge */ /* synthetic */ Set mo7817keySet() {
            return super.mo7738keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.BiMap
        /* renamed from: values */
        public /* bridge */ /* synthetic */ Collection mo7932values() {
            return super.values();
        }
    }

    /* loaded from: classes3.dex */
    private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        private MapViewOfValuesAsSingletonSets() {
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return ImmutableMap.this.containsKey(obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return ImmutableMap.this.mo7738keySet();
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> entryIterator() {
            final UnmodifiableIterator<Map.Entry<K, V>> mo8029iterator = ImmutableMap.this.mo7737entrySet().mo8029iterator();
            return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return mo8029iterator.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<K, ImmutableSet<V>> next() {
                    final Map.Entry entry = (Map.Entry) mo8029iterator.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>(this) { // from class: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1.1
                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        /* renamed from: getKey */
                        public K mo8108getKey() {
                            return (K) entry.getKey();
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        /* renamed from: getValue */
                        public ImmutableSet<V> mo7742getValue() {
                            return ImmutableSet.of(entry.getValue());
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableMap
        boolean isHashCodeFast() {
            return ImmutableMap.this.isHashCodeFast();
        }

        @Override // com.google.common.collect.ImmutableMap
        boolean isPartialView() {
            return ImmutableMap.this.isPartialView();
        }

        @Override // java.util.Map
        public int size() {
            return ImmutableMap.this.size();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        /* renamed from: get */
        public ImmutableSet<V> mo7740get(@NullableDecl Object obj) {
            Object mo7740get = ImmutableMap.this.mo7740get(obj);
            if (mo7740get == null) {
                return null;
            }
            return ImmutableSet.of(mo7740get);
        }
    }

    /* loaded from: classes3.dex */
    static class SerializedForm<K, V> implements Serializable {
        private static final boolean USE_LEGACY_SERIALIZATION = true;
        private static final long serialVersionUID = 0;
        private final Object keys;
        private final Object values;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SerializedForm(ImmutableMap<K, V> immutableMap) {
            Object[] objArr = new Object[immutableMap.size()];
            Object[] objArr2 = new Object[immutableMap.size()];
            UnmodifiableIterator<Map.Entry<K, V>> mo8029iterator = immutableMap.mo7737entrySet().mo8029iterator();
            int i = 0;
            while (mo8029iterator.hasNext()) {
                Map.Entry<K, V> next = mo8029iterator.next();
                objArr[i] = next.getKey();
                objArr2[i] = next.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        final Object legacyReadResolve() {
            Object[] objArr = (Object[]) this.keys;
            Object[] objArr2 = (Object[]) this.values;
            Builder<K, V> mo7832makeBuilder = mo7832makeBuilder(objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                mo7832makeBuilder.mo7828put((K) objArr[i], (V) objArr2[i]);
            }
            return mo7832makeBuilder.mo7826build();
        }

        /* renamed from: makeBuilder */
        Builder<K, V> mo7832makeBuilder(int i) {
            return new Builder<>(i);
        }

        final Object readResolve() {
            Object obj = this.keys;
            if (!(obj instanceof ImmutableSet)) {
                return legacyReadResolve();
            }
            ImmutableSet immutableSet = (ImmutableSet) obj;
            Builder<K, V> mo7832makeBuilder = mo7832makeBuilder(immutableSet.size());
            UnmodifiableIterator mo8029iterator = immutableSet.mo8029iterator();
            UnmodifiableIterator it2 = ((ImmutableCollection) this.values).iterator();
            while (mo8029iterator.hasNext()) {
                mo7832makeBuilder.mo7828put((K) mo8029iterator.next(), (V) it2.next());
            }
            return mo7832makeBuilder.mo7826build();
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    @Beta
    public static <K, V> Builder<K, V> builderWithExpectedSize(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder<>(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNoConflict(boolean z, String str, Map.Entry<?, ?> entry, Map.Entry<?, ?> entry2) {
        if (z) {
            return;
        }
        throw conflictException(str, entry, entry2);
    }

    static IllegalArgumentException conflictException(String str, Object obj, Object obj2) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        return new IllegalArgumentException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + GeneratedOutlineSupport1.outline6(str, 34), "Multiple entries with same ", str, RealTimeTextConstants.COLON_SPACE, valueOf), " and ", valueOf2));
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.isPartialView()) {
                return immutableMap;
            }
        }
        return copyOf(map.entrySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map.Entry<K, V> entryOf(K k, V v) {
        CollectPreconditions.checkEntryNotNull(k, v);
        return new AbstractMap.SimpleImmutableEntry(k, v);
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return ImmutableSetMultimap.of();
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), null);
        this.multimapView = immutableSetMultimap2;
        return immutableSetMultimap2;
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return mo7740get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    abstract ImmutableSet<K> createKeySet();

    /* renamed from: createValues */
    abstract ImmutableCollection<V> mo7696createValues();

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        return Maps.equalsImpl(this, obj);
    }

    @Override // java.util.Map
    /* renamed from: get */
    public abstract V mo7740get(@NullableDecl Object obj);

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V mo7740get = mo7740get(obj);
        return mo7740get != null ? mo7740get : v;
    }

    @Override // java.util.Map
    public int hashCode() {
        return Sets.hashCodeImpl(mo7737entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isPartialView();

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnmodifiableIterator<K> keyIterator() {
        final UnmodifiableIterator<Map.Entry<K, V>> mo8029iterator = mo7737entrySet().mo8029iterator();
        return new UnmodifiableIterator<K>(this) { // from class: com.google.common.collect.ImmutableMap.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return mo8029iterator.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return (K) ((Map.Entry) mo8029iterator.next()).getKey();
            }
        };
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v) {
        CollectPreconditions.checkEntryNotNull(k, v);
        return RegularImmutableMap.create(1, new Object[]{k, v});
    }

    @Override // java.util.Map
    /* renamed from: entrySet */
    public ImmutableSet<Map.Entry<K, V>> mo7737entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet == null) {
            ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
            this.entrySet = createEntrySet;
            return createEntrySet;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    /* renamed from: keySet */
    public ImmutableSet<K> mo7738keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet == null) {
            ImmutableSet<K> createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection == null) {
            ImmutableCollection<V> mo7696createValues = mo7696createValues();
            this.values = mo7696createValues;
            return mo7696createValues;
        }
        return immutableCollection;
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2) {
        CollectPreconditions.checkEntryNotNull(k, v);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        return RegularImmutableMap.create(2, new Object[]{k, v, k2, v2});
    }

    @Beta
    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Builder builder = new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4);
        builder.mo7830putAll(iterable);
        return builder.mo7826build();
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        CollectPreconditions.checkEntryNotNull(k, v);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        return RegularImmutableMap.create(3, new Object[]{k, v, k2, v2, k3, v3});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        CollectPreconditions.checkEntryNotNull(k, v);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        return RegularImmutableMap.create(4, new Object[]{k, v, k2, v2, k3, v3, k4, v4});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        CollectPreconditions.checkEntryNotNull(k, v);
        CollectPreconditions.checkEntryNotNull(k2, v2);
        CollectPreconditions.checkEntryNotNull(k3, v3);
        CollectPreconditions.checkEntryNotNull(k4, v4);
        CollectPreconditions.checkEntryNotNull(k5, v5);
        return RegularImmutableMap.create(5, new Object[]{k, v, k2, v2, k3, v3, k4, v4, k5, v5});
    }
}
