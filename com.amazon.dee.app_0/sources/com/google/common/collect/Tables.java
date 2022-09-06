package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public final class Tables {
    private static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> UNMODIFIABLE_WRAPPER = new Function<Map<Object, Object>, Map<Object, Object>>() { // from class: com.google.common.collect.Tables.1
        @Override // com.google.common.base.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Map<Object, Object> mo8172apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    };

    /* loaded from: classes3.dex */
    static abstract class AbstractCell<R, C, V> implements Table.Cell<R, C, V> {
        @Override // com.google.common.collect.Table.Cell
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            return Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue());
        }

        @Override // com.google.common.collect.Table.Cell
        public int hashCode() {
            return Objects.hashCode(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            String valueOf = String.valueOf(getRowKey());
            String valueOf2 = String.valueOf(getColumnKey());
            String valueOf3 = String.valueOf(getValue());
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline106(valueOf3.length() + valueOf2.length() + valueOf.length() + 4, "(", valueOf, ",", valueOf2), ")=", valueOf3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ImmutableCell<R, C, V> extends AbstractCell<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        @NullableDecl
        private final C columnKey;
        @NullableDecl
        private final R rowKey;
        @NullableDecl
        private final V value;

        ImmutableCell(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
            this.rowKey = r;
            this.columnKey = c;
            this.value = v;
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.rowKey;
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TransformedTable<R, C, V1, V2> extends AbstractTable<R, C, V2> {
        final Table<R, C, V1> fromTable;
        final Function<? super V1, V2> function;

        TransformedTable(Table<R, C, V1> table, Function<? super V1, V2> function) {
            this.fromTable = (Table) Preconditions.checkNotNull(table);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> cellFunction() {
            return new Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.1
                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return apply((Table.Cell) ((Table.Cell) obj));
                }

                public Table.Cell<R, C, V2> apply(Table.Cell<R, C, V1> cell) {
                    return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), TransformedTable.this.function.mo8172apply(cell.getValue()));
                }
            };
        }

        @Override // com.google.common.collect.AbstractTable
        /* renamed from: cellIterator */
        Iterator<Table.Cell<R, C, V2>> mo7853cellIterator() {
            return Iterators.transform(this.fromTable.mo7854cellSet().iterator(), cellFunction());
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.fromTable.clear();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: column */
        public Map<R, V2> mo8030column(C c) {
            return Maps.transformValues(this.fromTable.mo8030column(c), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        /* renamed from: columnKeySet */
        public Set<C> mo7856columnKeySet() {
            return this.fromTable.mo7856columnKeySet();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: columnMap */
        public Map<C, Map<R, V2>> mo8036columnMap() {
            return Maps.transformValues(this.fromTable.mo8036columnMap(), new Function<Map<R, V1>, Map<R, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.3
                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return apply((Map) ((Map) obj));
                }

                public Map<R, V2> apply(Map<R, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.function);
                }
            });
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(Object obj, Object obj2) {
            return this.fromTable.contains(obj, obj2);
        }

        @Override // com.google.common.collect.AbstractTable
        /* renamed from: createValues */
        Collection<V2> mo8033createValues() {
            return Collections2.transform(this.fromTable.mo7863values(), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 get(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.mo8172apply((V1) this.fromTable.get(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 put(R r, C c, V2 v2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 remove(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.mo8172apply((V1) this.fromTable.remove(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.Table
        /* renamed from: row */
        public Map<C, V2> mo8094row(R r) {
            return Maps.transformValues(this.fromTable.mo8094row(r), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        /* renamed from: rowKeySet */
        public Set<R> mo8095rowKeySet() {
            return this.fromTable.mo8095rowKeySet();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: rowMap */
        public Map<R, Map<C, V2>> mo8096rowMap() {
            return Maps.transformValues(this.fromTable.mo8096rowMap(), new Function<Map<C, V1>, Map<C, V2>>() { // from class: com.google.common.collect.Tables.TransformedTable.2
                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return apply((Map) ((Map) obj));
                }

                public Map<C, V2> apply(Map<C, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.function);
                }
            });
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.fromTable.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TransposeTable<C, R, V> extends AbstractTable<C, R, V> {
        private static final Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> TRANSPOSE_CELL = new Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>>() { // from class: com.google.common.collect.Tables.TransposeTable.1
            @Override // com.google.common.base.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Table.Cell<?, ?, ?> mo8172apply(Table.Cell<?, ?, ?> cell) {
                return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            }
        };
        final Table<R, C, V> original;

        TransposeTable(Table<R, C, V> table) {
            this.original = (Table) Preconditions.checkNotNull(table);
        }

        @Override // com.google.common.collect.AbstractTable
        /* renamed from: cellIterator */
        Iterator<Table.Cell<C, R, V>> mo7853cellIterator() {
            return Iterators.transform(this.original.mo7854cellSet().iterator(), TRANSPOSE_CELL);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.original.clear();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: column */
        public Map<C, V> mo8030column(R r) {
            return this.original.mo8094row(r);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        /* renamed from: columnKeySet */
        public Set<R> mo7856columnKeySet() {
            return this.original.mo8095rowKeySet();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: columnMap */
        public Map<R, Map<C, V>> mo8036columnMap() {
            return this.original.mo8096rowMap();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.original.contains(obj2, obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsColumn(@NullableDecl Object obj) {
            return this.original.containsRow(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsRow(@NullableDecl Object obj) {
            return this.original.containsColumn(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsValue(@NullableDecl Object obj) {
            return this.original.containsValue(obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.original.get(obj2, obj);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V put(C c, R r, V v) {
            return this.original.put(r, c, v);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.original.putAll(Tables.transpose(table));
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            return this.original.remove(obj2, obj);
        }

        @Override // com.google.common.collect.Table
        /* renamed from: row */
        public Map<R, V> mo8094row(C c) {
            return this.original.mo8030column(c);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        /* renamed from: rowKeySet */
        public Set<C> mo8095rowKeySet() {
            return this.original.mo7856columnKeySet();
        }

        @Override // com.google.common.collect.Table
        /* renamed from: rowMap */
        public Map<C, Map<R, V>> mo8096rowMap() {
            return this.original.mo8036columnMap();
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.original.size();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        /* renamed from: values */
        public Collection<V> mo7863values() {
            return this.original.mo7863values();
        }
    }

    /* loaded from: classes3.dex */
    static final class UnmodifiableRowSortedMap<R, C, V> extends UnmodifiableTable<R, C, V> implements RowSortedTable<R, C, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableRowSortedMap(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
            super(rowSortedTable);
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: rowKeySet  reason: collision with other method in class */
        public SortedSet<R> mo8095rowKeySet() {
            return Collections.unmodifiableSortedSet(mo8280delegate().mo8095rowKeySet());
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: rowMap  reason: collision with other method in class */
        public SortedMap<R, Map<C, V>> mo8096rowMap() {
            return Collections.unmodifiableSortedMap(Maps.transformValues((SortedMap) mo8280delegate().mo8096rowMap(), Tables.unmodifiableWrapper()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public RowSortedTable<R, C, V> mo8280delegate() {
            return (RowSortedTable) super.mo8280delegate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class UnmodifiableTable<R, C, V> extends ForwardingTable<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Table<? extends R, ? extends C, ? extends V> delegate;

        UnmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
            this.delegate = (Table) Preconditions.checkNotNull(table);
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: cellSet */
        public Set<Table.Cell<R, C, V>> mo7854cellSet() {
            return Collections.unmodifiableSet(super.mo7854cellSet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: column */
        public Map<R, V> mo8030column(@NullableDecl C c) {
            return Collections.unmodifiableMap(super.mo8030column(c));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: columnKeySet */
        public Set<C> mo7856columnKeySet() {
            return Collections.unmodifiableSet(super.mo7856columnKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: columnMap */
        public Map<C, Map<R, V>> mo8036columnMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.mo8036columnMap(), Tables.unmodifiableWrapper()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V put(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: row */
        public Map<C, V> mo8094row(@NullableDecl R r) {
            return Collections.unmodifiableMap(super.mo8094row(r));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: rowKeySet */
        public Set<R> mo8095rowKeySet() {
            return Collections.unmodifiableSet(super.mo8095rowKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: rowMap */
        public Map<R, Map<C, V>> mo8096rowMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.mo8096rowMap(), Tables.unmodifiableWrapper()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        /* renamed from: values */
        public Collection<V> mo7863values() {
            return Collections.unmodifiableCollection(super.mo7863values());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public Table<R, C, V> mo8280delegate() {
            return (Table<? extends R, ? extends C, ? extends V>) this.delegate;
        }
    }

    private Tables() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(Table<?, ?, ?> table, @NullableDecl Object obj) {
        if (obj == table) {
            return true;
        }
        if (!(obj instanceof Table)) {
            return false;
        }
        return table.mo7854cellSet().equals(((Table) obj).mo7854cellSet());
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(@NullableDecl R r, @NullableDecl C c, @NullableDecl V v) {
        return new ImmutableCell(r, c, v);
    }

    @Beta
    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkNotNull(supplier);
        return new StandardTable(map, supplier);
    }

    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> table) {
        return Synchronized.table(table, null);
    }

    @Beta
    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> table, Function<? super V1, V2> function) {
        return new TransformedTable(table, function);
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        if (table instanceof TransposeTable) {
            return (Table<R, C, V>) ((TransposeTable) table).original;
        }
        return new TransposeTable(table);
    }

    @Beta
    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
        return new UnmodifiableRowSortedMap(rowSortedTable);
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new UnmodifiableTable(table);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> Function<Map<K, V>, Map<K, V>> unmodifiableWrapper() {
        return (Function<Map<K, V>, Map<K, V>>) UNMODIFIABLE_WRAPPER;
    }
}
