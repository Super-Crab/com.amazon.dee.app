package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes3.dex */
public class ImmutableListMultimap<K, V> extends ImmutableMultimap<K, V> implements ListMultimap<K, V> {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    @RetainedWith
    @LazyInit
    private transient ImmutableListMultimap<V, K> inverse;

    /* loaded from: classes3.dex */
    public static final class Builder<K, V> extends ImmutableMultimap.Builder<K, V> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7805put(Object obj, Object obj2) {
            return mo7805put((Builder<K, V>) obj, obj2);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        /* renamed from: build */
        public ImmutableListMultimap<K, V> mo7802build() {
            return (ImmutableListMultimap) super.mo7802build();
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: orderKeysBy */
        public Builder<K, V> mo7803orderKeysBy(Comparator<? super K> comparator) {
            super.mo7803orderKeysBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: orderValuesBy */
        public Builder<K, V> mo7804orderValuesBy(Comparator<? super V> comparator) {
            super.mo7804orderValuesBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put */
        public Builder<K, V> mo7805put(K k, V v) {
            super.mo7805put((Builder<K, V>) k, (K) v);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7809putAll(Object obj, Iterable iterable) {
            return mo7809putAll((Builder<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: put */
        public Builder<K, V> mo7806put(Map.Entry<? extends K, ? extends V> entry) {
            super.mo7806put((Map.Entry) entry);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder mo7810putAll(Object obj, Object[] objArr) {
            return mo7810putAll((Builder<K, V>) obj, objArr);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        @Beta
        /* renamed from: putAll */
        public Builder<K, V> mo7808putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.mo7808putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public Builder<K, V> mo7809putAll(K k, Iterable<? extends V> iterable) {
            super.mo7809putAll((Builder<K, V>) k, (Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public Builder<K, V> mo7810putAll(K k, V... vArr) {
            super.mo7810putAll((Builder<K, V>) k, (Object[]) vArr);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        /* renamed from: putAll */
        public Builder<K, V> mo7807putAll(Multimap<? extends K, ? extends V> multimap) {
            super.mo7807putAll((Multimap) multimap);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableListMultimap(ImmutableMap<K, ImmutableList<V>> immutableMap, int i) {
        super(immutableMap, i);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableListMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        if (multimap.isEmpty()) {
            return of();
        }
        if (multimap instanceof ImmutableListMultimap) {
            ImmutableListMultimap<K, V> immutableListMultimap = (ImmutableListMultimap) multimap;
            if (!immutableListMultimap.isPartialView()) {
                return immutableListMultimap;
            }
        }
        return fromMapEntries(multimap.mo8101asMap().entrySet(), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> ImmutableListMultimap<K, V> fromMapEntries(Collection<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> collection, @NullableDecl Comparator<? super V> comparator) {
        ImmutableList sortedCopyOf;
        if (collection.isEmpty()) {
            return of();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        int i = 0;
        for (Map.Entry<? extends K, ? extends Collection<? extends V>> entry : collection) {
            K key = entry.getKey();
            Collection<? extends V> value = entry.getValue();
            if (comparator == null) {
                sortedCopyOf = ImmutableList.copyOf((Collection) value);
            } else {
                sortedCopyOf = ImmutableList.sortedCopyOf(comparator, value);
            }
            if (!sortedCopyOf.isEmpty()) {
                builder.mo7828put(key, sortedCopyOf);
                i = sortedCopyOf.size() + i;
            }
        }
        return new ImmutableListMultimap<>(builder.mo7826build(), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImmutableListMultimap<V, K> invert() {
        Builder builder = builder();
        UnmodifiableIterator it2 = mo8077entries().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            builder.mo7805put((Builder) entry.getValue(), entry.getKey());
        }
        ImmutableListMultimap<V, K> mo7802build = builder.mo7802build();
        mo7802build.inverse = this;
        return mo7802build;
    }

    public static <K, V> ImmutableListMultimap<K, V> of() {
        return EmptyImmutableListMultimap.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    ImmutableList.Builder builder2 = ImmutableList.builder();
                    for (int i3 = 0; i3 < readInt2; i3++) {
                        builder2.mo7849add((ImmutableList.Builder) objectInputStream.readObject());
                    }
                    builder.mo7828put(readObject, builder2.mo7852build());
                    i += readInt2;
                } else {
                    throw new InvalidObjectException(GeneratedOutlineSupport1.outline27(31, "Invalid value count ", readInt2));
                }
            }
            try {
                ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, (Object) builder.mo7826build());
                ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, i);
                return;
            } catch (IllegalArgumentException e) {
                throw ((InvalidObjectException) new InvalidObjectException(e.getMessage()).initCause(e));
            }
        }
        throw new InvalidObjectException(GeneratedOutlineSupport1.outline27(29, "Invalid key count ", readInt));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultimap(this, objectOutputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ ImmutableCollection mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ ImmutableCollection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        return builder.mo7802build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableListMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableMultimap
    /* renamed from: inverse */
    public ImmutableListMultimap<V, K> mo7797inverse() {
        ImmutableListMultimap<V, K> immutableListMultimap = this.inverse;
        if (immutableListMultimap == null) {
            ImmutableListMultimap<V, K> invert = invert();
            this.inverse = invert;
            return invert;
        }
        return immutableListMultimap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ Collection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ List mo8104get(@NullableDecl Object obj) {
        return mo8104get((ImmutableListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ List mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public ImmutableList<V> mo8104get(@NullableDecl K k) {
        ImmutableList<V> immutableList = (ImmutableList) this.map.mo7740get(k);
        return immutableList == null ? ImmutableList.of() : immutableList;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: removeAll  reason: collision with other method in class */
    public ImmutableList<V> mo8087removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    @Deprecated
    /* renamed from: replaceValues  reason: collision with other method in class */
    public ImmutableList<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        return builder.mo7802build();
    }

    @Beta
    public static <K, V> ImmutableListMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new Builder().mo7808putAll((Iterable) iterable).mo7802build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        return builder.mo7802build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        builder.mo7805put((Builder) k4, (K) v4);
        return builder.mo7802build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Builder builder = builder();
        builder.mo7805put((Builder) k, (K) v);
        builder.mo7805put((Builder) k2, (K) v2);
        builder.mo7805put((Builder) k3, (K) v3);
        builder.mo7805put((Builder) k4, (K) v4);
        builder.mo7805put((Builder) k5, (K) v5);
        return builder.mo7802build();
    }
}
