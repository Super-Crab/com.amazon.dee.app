package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.Map;
@GwtCompatible
/* loaded from: classes3.dex */
class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    final C singleColumnKey;
    final R singleRowKey;
    final V singleValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableTable(R r, C c, V v) {
        this.singleRowKey = (R) Preconditions.checkNotNull(r);
        this.singleColumnKey = (C) Preconditions.checkNotNull(c);
        this.singleValue = (V) Preconditions.checkNotNull(v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    /* renamed from: column  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Map mo8030column(Object obj) {
        return mo8030column((SingletonImmutableTable<R, C, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableTable
    ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, new int[]{0}, new int[]{0});
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return 1;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    /* renamed from: column */
    public ImmutableMap<R, V> mo8030column(C c) {
        Preconditions.checkNotNull(c);
        if (containsColumn(c)) {
            return ImmutableMap.of(this.singleRowKey, (Object) this.singleValue);
        }
        return ImmutableMap.of();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    /* renamed from: columnMap */
    public ImmutableMap<C, Map<R, V>> mo8036columnMap() {
        return ImmutableMap.of(this.singleColumnKey, ImmutableMap.of(this.singleRowKey, (Object) this.singleValue));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    /* renamed from: createCellSet */
    public ImmutableSet<Table.Cell<R, C, V>> mo8032createCellSet() {
        return ImmutableSet.of(ImmutableTable.cellOf(this.singleRowKey, this.singleColumnKey, this.singleValue));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    /* renamed from: createValues */
    public ImmutableCollection<V> mo8033createValues() {
        return ImmutableSet.of(this.singleValue);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    /* renamed from: rowMap */
    public ImmutableMap<R, Map<C, V>> mo8096rowMap() {
        return ImmutableMap.of(this.singleRowKey, ImmutableMap.of(this.singleColumnKey, (Object) this.singleValue));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingletonImmutableTable(Table.Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }
}
