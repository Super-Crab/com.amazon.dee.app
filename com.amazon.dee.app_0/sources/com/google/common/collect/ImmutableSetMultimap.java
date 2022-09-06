package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes3.dex */
public class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> implements SetMultimap<K, V> {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    private final transient ImmutableSet<V> emptySet;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient ImmutableSet<Map.Entry<K, V>> entries;
    @RetainedWith
    @NullableDecl
    @LazyInit
    private transient ImmutableSetMultimap<V, K> inverse;

    /* loaded from: classes3.dex */
    public static final class Builder<K, V> extends ImmutableMultimap.Builder<K, V> {
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        Collection<V> newMutableValueCollection() {
            return Platform.preservesInsertionOrderOnAddsSet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7805put(Object obj, Object obj2) {
            return mo7805put((Builder<K, V>) obj, obj2);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        /* renamed from: build  reason: collision with other method in class */
        public ImmutableSetMultimap<K, V> mo7802build() {
            Collection entrySet = this.builderMap.entrySet();
            Comparator<? super K> comparator = this.keyComparator;
            if (comparator != null) {
                entrySet = Ordering.from(comparator).onKeys().immutableSortedCopy(entrySet);
            }
            return ImmutableSetMultimap.fromMapEntries(entrySet, this.valueComparator);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: orderKeysBy  reason: collision with other method in class */
        public Builder<K, V> mo7803orderKeysBy(Comparator<? super K> comparator) {
            super.mo7803orderKeysBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: orderValuesBy  reason: collision with other method in class */
        public Builder<K, V> mo7804orderValuesBy(Comparator<? super V> comparator) {
            super.mo7804orderValuesBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put  reason: collision with other method in class */
        public Builder<K, V> mo7805put(K k, V v) {
            super.mo7805put((Builder<K, V>) k, (K) v);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7809putAll(Object obj, Iterable iterable) {
            return mo7809putAll((Builder<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put  reason: collision with other method in class */
        public Builder<K, V> mo7806put(Map.Entry<? extends K, ? extends V> entry) {
            super.mo7806put((Map.Entry) entry);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7810putAll(Object obj, Object[] objArr) {
            return mo7810putAll((Builder<K, V>) obj, objArr);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        @Beta
        /* renamed from: putAll  reason: collision with other method in class */
        public Builder<K, V> mo7808putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.mo7808putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll  reason: collision with other method in class */
        public Builder<K, V> mo7809putAll(K k, Iterable<? extends V> iterable) {
            super.mo7809putAll((Builder<K, V>) k, (Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll  reason: collision with other method in class */
        public Builder<K, V> mo7810putAll(K k, V... vArr) {
            return mo7809putAll((Builder<K, V>) k, (Iterable) Arrays.asList(vArr));
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll  reason: collision with other method in class */
        public Builder<K, V> mo7807putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry<? extends K, Collection<? extends V>> entry : multimap.mo8101asMap().entrySet()) {
                mo7809putAll((Builder<K, V>) entry.getKey(), (Iterable) entry.getValue());
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        @Weak
        private final transient ImmutableSetMultimap<K, V> multimap;

        EntrySet(ImmutableSetMultimap<K, V> immutableSetMultimap) {
            this.multimap = immutableSetMultimap;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.multimap.containsEntry(entry.getKey(), entry.getValue());
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        /* renamed from: iterator */
        public UnmodifiableIterator<Map.Entry<K, V>> mo8029iterator() {
            return this.multimap.mo7751entryIterator();
        }
    }

    @GwtIncompatible
    /* loaded from: classes3.dex */
    private static final class SetFieldSettersHolder {
        static final Serialization.FieldSetter<ImmutableSetMultimap> EMPTY_SET_FIELD_SETTER = Serialization.getFieldSetter(ImmutableSetMultimap.class, "emptySet");

        private SetFieldSettersHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSetMultimap(ImmutableMap<K, ImmutableSet<V>> immutableMap, int i, @NullableDecl Comparator<? super V> comparator) {
        super(immutableMap, i);
        this.emptySet = emptySet(comparator);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableSetMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        return copyOf(multimap, null);
    }

    private static <V> ImmutableSet<V> emptySet(@NullableDecl Comparator<? super V> comparator) {
        if (comparator == null) {
            return ImmutableSet.of();
        }
        return ImmutableSortedSet.emptySet(comparator);
    }

    static <K, V> ImmutableSetMultimap<K, V> fromMapEntries(Collection<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> collection, @NullableDecl Comparator<? super V> comparator) {
        if (collection.isEmpty()) {
            return of();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        int i = 0;
        for (Map.Entry<? extends K, ? extends Collection<? extends V>> entry : collection) {
            K key = entry.getKey();
            ImmutableSet valueSet = valueSet(comparator, entry.getValue());
            if (!valueSet.isEmpty()) {
                builder.mo7828put(key, valueSet);
                i = valueSet.size() + i;
            }
        }
        return new ImmutableSetMultimap<>(builder.mo7826build(), i, comparator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImmutableSetMultimap<V, K> invert() {
        Builder builder = builder();
        UnmodifiableIterator mo8029iterator = mo8077entries().mo8029iterator();
        while (mo8029iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) mo8029iterator.next();
            builder.mo7805put((Builder) entry.getValue(), entry.getKey());
        }
        ImmutableSetMultimap<V, K> mo7802build = builder.mo7802build();
        mo7802build.inverse = this;
        return mo7802build;
    }

    public static <K, V> ImmutableSetMultimap<K, V> of() {
        return EmptyImmutableSetMultimap.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    ImmutableSet.Builder valuesBuilder = valuesBuilder(comparator);
                    for (int i3 = 0; i3 < readInt2; i3++) {
                        valuesBuilder.mo7849add((ImmutableSet.Builder) objectInputStream.readObject());
                    }
                    ImmutableSet mo7852build = valuesBuilder.mo7852build();
                    if (mo7852build.size() == readInt2) {
                        builder.mo7828put(readObject, mo7852build);
                        i += readInt2;
                    } else {
                        String valueOf = String.valueOf(readObject);
                        throw new InvalidObjectException(GeneratedOutlineSupport1.outline29(valueOf.length() + 40, "Duplicate key-value pairs exist for key ", valueOf));
                    }
                } else {
                    throw new InvalidObjectException(GeneratedOutlineSupport1.outline27(31, "Invalid value count ", readInt2));
                }
            }
            try {
                ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, (Object) builder.mo7826build());
                ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, i);
                SetFieldSettersHolder.EMPTY_SET_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableSetMultimap>) this, (Object) emptySet(comparator));
                return;
            } catch (IllegalArgumentException e) {
                throw ((InvalidObjectException) new InvalidObjectException(e.getMessage()).initCause(e));
            }
        }
        throw new InvalidObjectException(GeneratedOutlineSupport1.outline27(29, "Invalid key count ", readInt));
    }

    private static <V> ImmutableSet<V> valueSet(@NullableDecl Comparator<? super V> comparator, Collection<? extends V> collection) {
        if (comparator == null) {
            return ImmutableSet.copyOf((Collection) collection);
        }
        return ImmutableSortedSet.copyOf((Comparator) comparator, (Collection) collection);
    }

    private static <V> ImmutableSet.Builder<V> valuesBuilder(@NullableDecl Comparator<? super V> comparator) {
        if (comparator == null) {
            return new ImmutableSet.Builder<>();
        }
        return new ImmutableSortedSet.Builder(comparator);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(valueComparator());
        Serialization.writeMultimap(this, objectOutputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ ImmutableCollection mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableSetMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ ImmutableCollection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableSetMultimap<K, V>) obj, iterable);
    }

    @NullableDecl
    Comparator<? super V> valueComparator() {
        ImmutableSet<V> immutableSet = this.emptySet;
        if (immutableSet instanceof ImmutableSortedSet) {
            return ((ImmutableSortedSet) immutableSet).comparator();
        }
        return null;
    }

    private static <K, V> ImmutableSetMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap, Comparator<? super V> comparator) {
        Preconditions.checkNotNull(multimap);
        if (multimap.isEmpty() && comparator == null) {
            return of();
        }
        if (multimap instanceof ImmutableSetMultimap) {
            ImmutableSetMultimap<K, V> immutableSetMultimap = (ImmutableSetMultimap) multimap;
            if (!immutableSetMultimap.isPartialView()) {
                return immutableSetMultimap;
            }
        }
        return fromMapEntries(multimap.mo8101asMap().entrySet(), comparator);
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k, V v) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        return builder.mo7802build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableSetMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableMultimap
    /* renamed from: inverse  reason: collision with other method in class */
    public ImmutableSetMultimap<V, K> mo7797inverse() {
        ImmutableSetMultimap<V, K> immutableSetMultimap = this.inverse;
        if (immutableSetMultimap == null) {
            ImmutableSetMultimap<V, K> invert = invert();
            this.inverse = invert;
            return invert;
        }
        return immutableSetMultimap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ Collection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableSetMultimap<K, V>) obj, iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Set mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableSetMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Set mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableSetMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* renamed from: entries  reason: collision with other method in class */
    public ImmutableSet<Map.Entry<K, V>> mo8077entries() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entries;
        if (immutableSet == null) {
            EntrySet entrySet = new EntrySet(this);
            this.entries = entrySet;
            return entrySet;
        }
        return immutableSet;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public ImmutableSet<V> mo8104get(@NullableDecl K k) {
        return (ImmutableSet) MoreObjects.firstNonNull((ImmutableSet) this.map.mo7740get(k), this.emptySet);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: removeAll  reason: collision with other method in class */
    public ImmutableSet<V> mo8087removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues  reason: collision with other method in class */
    public ImmutableSet<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k, V v, K k2, V v2) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        return builder.mo7802build();
    }

    @Beta
    public static <K, V> ImmutableSetMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new Builder().mo7808putAll((Iterable) iterable).mo7802build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        return builder.mo7802build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        builder.mo7805put((Builder) k4, (K) v4);
        return builder.mo7802build();
    }

    public static <K, V> ImmutableSetMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        builder.mo7805put((Builder) k4, (K) v4);
        builder.mo7805put((Builder) k5, (K) v5);
        return builder.mo7802build();
    }
}
