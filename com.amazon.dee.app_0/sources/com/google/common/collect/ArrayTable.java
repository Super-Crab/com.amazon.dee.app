package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    @NullableDecl
    private transient ArrayTable<R, C, V>.ColumnMap columnMap;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;
    @NullableDecl
    private transient ArrayTable<R, C, V>.RowMap rowMap;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class ArrayMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return this.keyIndex.containsKey(obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) { // from class: com.google.common.collect.ArrayTable.ArrayMap.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIndexedListIterator
                /* renamed from: get  reason: collision with other method in class */
                public Map.Entry<K, V> mo8026get(int i) {
                    return ArrayMap.this.getEntry(i);
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            Integer mo7740get = this.keyIndex.mo7740get(obj);
            if (mo7740get == null) {
                return null;
            }
            return mo7587getValue(mo7740get.intValue());
        }

        Map.Entry<K, V> getEntry(final int i) {
            Preconditions.checkElementIndex(i, size());
            return new AbstractMapEntry<K, V>() { // from class: com.google.common.collect.ArrayTable.ArrayMap.1
                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                /* renamed from: getKey */
                public K mo8108getKey() {
                    return (K) ArrayMap.this.getKey(i);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                /* renamed from: getValue */
                public V mo7742getValue() {
                    return (V) ArrayMap.this.mo7587getValue(i);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V setValue(V v) {
                    return (V) ArrayMap.this.setValue(i, v);
                }
            };
        }

        K getKey(int i) {
            return this.keyIndex.mo7738keySet().asList().get(i);
        }

        abstract String getKeyRole();

        @NullableDecl
        /* renamed from: getValue */
        abstract V mo7587getValue(int i);

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.keyIndex.mo7738keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            Integer mo7740get = this.keyIndex.mo7740get(k);
            if (mo7740get != null) {
                return setValue(mo7740get.intValue(), v);
            }
            String keyRole = getKeyRole();
            String valueOf = String.valueOf(k);
            String valueOf2 = String.valueOf(this.keyIndex.mo7738keySet());
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + GeneratedOutlineSupport1.outline6(keyRole, 9), keyRole, " ", valueOf, " not in ");
            outline106.append(valueOf2);
            throw new IllegalArgumentException(outline106.toString());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @NullableDecl
        abstract V setValue(int i, V v);

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.keyIndex.size();
        }

        private ArrayMap(ImmutableMap<K, Integer> immutableMap) {
            this.keyIndex = immutableMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class Column extends ArrayMap<R, V> {
        final int columnIndex;

        Column(int i) {
            super(ArrayTable.this.rowKeyToIndex);
            this.columnIndex = i;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* renamed from: getValue */
        V mo7587getValue(int i) {
            return (V) ArrayTable.this.at(i, this.columnIndex);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        V setValue(int i, V v) {
            return (V) ArrayTable.this.set(i, this.columnIndex, v);
        }
    }

    /* loaded from: classes3.dex */
    private class ColumnMap extends ArrayMap<C, Map<R, V>> {
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put((ColumnMap) obj, (Map) ((Map) obj2));
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* bridge */ /* synthetic */ Object setValue(int i, Object obj) {
            return setValue(i, (Map) ((Map) obj));
        }

        private ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* renamed from: getValue  reason: collision with other method in class */
        public Map<R, V> mo7587getValue(int i) {
            return new Column(i);
        }

        public Map<R, V> put(C c, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        Map<R, V> setValue(int i, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class Row extends ArrayMap<C, V> {
        final int rowIndex;

        Row(int i) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = i;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* renamed from: getValue */
        V mo7587getValue(int i) {
            return (V) ArrayTable.this.at(this.rowIndex, i);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        V setValue(int i, V v) {
            return (V) ArrayTable.this.set(this.rowIndex, i, v);
        }
    }

    /* loaded from: classes3.dex */
    private class RowMap extends ArrayMap<R, Map<C, V>> {
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put((RowMap) obj, (Map) ((Map) obj2));
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* bridge */ /* synthetic */ Object setValue(int i, Object obj) {
            return setValue(i, (Map) ((Map) obj));
        }

        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        /* renamed from: getValue  reason: collision with other method in class */
        public Map<C, V> mo7587getValue(int i) {
            return new Row(i);
        }

        public Map<C, V> put(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        Map<C, V> setValue(int i, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        this.rowList = ImmutableList.copyOf(iterable);
        this.columnList = ImmutableList.copyOf(iterable2);
        Preconditions.checkArgument(this.rowList.isEmpty() == this.columnList.isEmpty());
        this.rowKeyToIndex = Maps.indexMap(this.rowList);
        this.columnKeyToIndex = Maps.indexMap(this.columnList);
        this.array = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        eraseAll();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Table.Cell<R, C, V> getCell(final int i) {
        return new Tables.AbstractCell<R, C, V>() { // from class: com.google.common.collect.ArrayTable.2
            final int columnIndex;
            final int rowIndex;

            {
                this.rowIndex = i / ArrayTable.this.columnList.size();
                this.columnIndex = i % ArrayTable.this.columnList.size();
            }

            @Override // com.google.common.collect.Table.Cell
            public C getColumnKey() {
                return (C) ArrayTable.this.columnList.get(this.columnIndex);
            }

            @Override // com.google.common.collect.Table.Cell
            public R getRowKey() {
                return (R) ArrayTable.this.rowList.get(this.rowIndex);
            }

            @Override // com.google.common.collect.Table.Cell
            public V getValue() {
                return (V) ArrayTable.this.at(this.rowIndex, this.columnIndex);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V getValue(int i) {
        return at(i / this.columnList.size(), i % this.columnList.size());
    }

    public V at(int i, int i2) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        return this.array[i][i2];
    }

    @Override // com.google.common.collect.AbstractTable
    /* renamed from: cellIterator */
    Iterator<Table.Cell<R, C, V>> mo7853cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) { // from class: com.google.common.collect.ArrayTable.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIndexedListIterator
            /* renamed from: get */
            public Table.Cell<R, C, V> mo8026get(int i) {
                return ArrayTable.this.getCell(i);
            }
        };
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    /* renamed from: cellSet */
    public Set<Table.Cell<R, C, V>> mo7854cellSet() {
        return super.mo7854cellSet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: column */
    public Map<R, V> mo8030column(C c) {
        Preconditions.checkNotNull(c);
        Integer mo7740get = this.columnKeyToIndex.mo7740get(c);
        return mo7740get == null ? ImmutableMap.of() : new Column(mo7740get.intValue());
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    @Override // com.google.common.collect.Table
    /* renamed from: columnMap */
    public Map<C, Map<R, V>> mo8036columnMap() {
        ArrayTable<R, C, V>.ColumnMap columnMap = this.columnMap;
        if (columnMap == null) {
            ArrayTable<R, C, V>.ColumnMap columnMap2 = new ColumnMap();
            this.columnMap = columnMap2;
            return columnMap2;
        }
        return columnMap;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsColumn(@NullableDecl Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsRow(@NullableDecl Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsValue(@NullableDecl Object obj) {
        V[][] vArr;
        for (V[] vArr2 : this.array) {
            for (V v : vArr2) {
                if (Objects.equal(obj, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public V erase(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Integer mo7740get = this.rowKeyToIndex.mo7740get(obj);
        Integer mo7740get2 = this.columnKeyToIndex.mo7740get(obj2);
        if (mo7740get == null || mo7740get2 == null) {
            return null;
        }
        return set(mo7740get.intValue(), mo7740get2.intValue(), null);
    }

    public void eraseAll() {
        for (V[] vArr : this.array) {
            Arrays.fill(vArr, (Object) null);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Integer mo7740get = this.rowKeyToIndex.mo7740get(obj);
        Integer mo7740get2 = this.columnKeyToIndex.mo7740get(obj2);
        if (mo7740get == null || mo7740get2 == null) {
            return null;
        }
        return at(mo7740get.intValue(), mo7740get2.intValue());
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R r, C c, @NullableDecl V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Integer mo7740get = this.rowKeyToIndex.mo7740get(r);
        boolean z = true;
        Preconditions.checkArgument(mo7740get != null, "Row %s not in %s", r, this.rowList);
        Integer mo7740get2 = this.columnKeyToIndex.mo7740get(c);
        if (mo7740get2 == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Column %s not in %s", c, this.columnList);
        return set(mo7740get.intValue(), mo7740get2.intValue(), v);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    @Deprecated
    public V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: row */
    public Map<C, V> mo8094row(R r) {
        Preconditions.checkNotNull(r);
        Integer mo7740get = this.rowKeyToIndex.mo7740get(r);
        return mo7740get == null ? ImmutableMap.of() : new Row(mo7740get.intValue());
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    @Override // com.google.common.collect.Table
    /* renamed from: rowMap */
    public Map<R, Map<C, V>> mo8096rowMap() {
        ArrayTable<R, C, V>.RowMap rowMap = this.rowMap;
        if (rowMap == null) {
            ArrayTable<R, C, V>.RowMap rowMap2 = new RowMap();
            this.rowMap = rowMap2;
            return rowMap2;
        }
        return rowMap;
    }

    @CanIgnoreReturnValue
    public V set(int i, int i2, @NullableDecl V v) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        V[][] vArr = this.array;
        V v2 = vArr[i][i2];
        vArr[i][i2] = v;
        return v2;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.columnList.size() * this.rowList.size();
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance((Class<?>) cls, this.rowList.size(), this.columnList.size()));
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = this.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
        return vArr;
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    /* renamed from: values */
    public Collection<V> mo7863values() {
        return super.mo7863values();
    }

    @Override // com.google.common.collect.AbstractTable
    Iterator<V> valuesIterator() {
        return new AbstractIndexedListIterator<V>(size()) { // from class: com.google.common.collect.ArrayTable.3
            @Override // com.google.common.collect.AbstractIndexedListIterator
            /* renamed from: get */
            protected V mo8026get(int i) {
                return (V) ArrayTable.this.getValue(i);
            }
        };
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        if (table instanceof ArrayTable) {
            return new ArrayTable<>((ArrayTable) table);
        }
        return new ArrayTable<>(table);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    /* renamed from: columnKeySet */
    public ImmutableSet<C> mo7856columnKeySet() {
        return this.columnKeyToIndex.mo7738keySet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    /* renamed from: rowKeySet */
    public ImmutableSet<R> mo8095rowKeySet() {
        return this.rowKeyToIndex.mo7738keySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArrayTable(Table<R, C, V> table) {
        this(table.mo8095rowKeySet(), table.mo7856columnKeySet());
        putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        this.rowList = arrayTable.rowList;
        this.columnList = arrayTable.columnList;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        this.array = vArr;
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = arrayTable.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
    }
}
