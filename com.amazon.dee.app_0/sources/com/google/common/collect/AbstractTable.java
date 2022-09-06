package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
abstract class AbstractTable<R, C, V> implements Table<R, C, V> {
    @NullableDecl
    @LazyInit
    private transient Set<Table.Cell<R, C, V>> cellSet;
    @NullableDecl
    @LazyInit
    private transient Collection<V> values;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class CellSet extends AbstractSet<Table.Cell<R, C, V>> {
        CellSet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractTable.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) obj;
                Map map = (Map) Maps.safeGet(AbstractTable.this.mo8096rowMap(), cell.getRowKey());
                return map != null && Collections2.safeContains(map.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Table.Cell<R, C, V>> iterator() {
            return AbstractTable.this.mo7853cellIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) obj;
                Map map = (Map) Maps.safeGet(AbstractTable.this.mo8096rowMap(), cell.getRowKey());
                return map != null && Collections2.safeRemove(map.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractTable.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AbstractTable.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractTable.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return AbstractTable.this.valuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractTable.this.size();
        }
    }

    /* renamed from: cellIterator */
    abstract Iterator<Table.Cell<R, C, V>> mo7853cellIterator();

    @Override // com.google.common.collect.Table
    /* renamed from: cellSet */
    public Set<Table.Cell<R, C, V>> mo7854cellSet() {
        Set<Table.Cell<R, C, V>> set = this.cellSet;
        if (set == null) {
            Set<Table.Cell<R, C, V>> mo8032createCellSet = mo8032createCellSet();
            this.cellSet = mo8032createCellSet;
            return mo8032createCellSet;
        }
        return set;
    }

    @Override // com.google.common.collect.Table
    public void clear() {
        Iterators.clear(mo7854cellSet().iterator());
    }

    @Override // com.google.common.collect.Table
    /* renamed from: columnKeySet */
    public Set<C> mo7856columnKeySet() {
        return mo8036columnMap().keySet();
    }

    @Override // com.google.common.collect.Table
    public boolean contains(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Map map = (Map) Maps.safeGet(mo8096rowMap(), obj);
        return map != null && Maps.safeContainsKey(map, obj2);
    }

    @Override // com.google.common.collect.Table
    public boolean containsColumn(@NullableDecl Object obj) {
        return Maps.safeContainsKey(mo8036columnMap(), obj);
    }

    @Override // com.google.common.collect.Table
    public boolean containsRow(@NullableDecl Object obj) {
        return Maps.safeContainsKey(mo8096rowMap(), obj);
    }

    @Override // com.google.common.collect.Table
    public boolean containsValue(@NullableDecl Object obj) {
        for (Map<C, V> map : mo8096rowMap().values()) {
            if (map.containsValue(obj)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: createCellSet */
    Set<Table.Cell<R, C, V>> mo8032createCellSet() {
        return new CellSet();
    }

    /* renamed from: createValues */
    Collection<V> mo8033createValues() {
        return new Values();
    }

    @Override // com.google.common.collect.Table
    public boolean equals(@NullableDecl Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @Override // com.google.common.collect.Table
    public V get(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Map map = (Map) Maps.safeGet(mo8096rowMap(), obj);
        if (map == null) {
            return null;
        }
        return (V) Maps.safeGet(map, obj2);
    }

    @Override // com.google.common.collect.Table
    public int hashCode() {
        return mo7854cellSet().hashCode();
    }

    @Override // com.google.common.collect.Table
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R r, C c, V v) {
        return mo8094row(r).put(c, v);
    }

    @Override // com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        for (Table.Cell<? extends R, ? extends C, ? extends V> cell : table.mo7854cellSet()) {
            put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Map map = (Map) Maps.safeGet(mo8096rowMap(), obj);
        if (map == null) {
            return null;
        }
        return (V) Maps.safeRemove(map, obj2);
    }

    @Override // com.google.common.collect.Table
    /* renamed from: rowKeySet */
    public Set<R> mo8095rowKeySet() {
        return mo8096rowMap().keySet();
    }

    public String toString() {
        return mo8096rowMap().toString();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: values */
    public Collection<V> mo7863values() {
        Collection<V> collection = this.values;
        if (collection == null) {
            Collection<V> mo8033createValues = mo8033createValues();
            this.values = mo8033createValues;
            return mo8033createValues;
        }
        return collection;
    }

    Iterator<V> valuesIterator() {
        return new TransformedIterator<Table.Cell<R, C, V>, V>(this, mo7854cellSet().iterator()) { // from class: com.google.common.collect.AbstractTable.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.TransformedIterator
            /* renamed from: transform */
            public /* bridge */ /* synthetic */ Object mo7907transform(Object obj) {
                return transform((Table.Cell<R, C, Object>) obj);
            }

            V transform(Table.Cell<R, C, V> cell) {
                return cell.getValue();
            }
        };
    }
}
