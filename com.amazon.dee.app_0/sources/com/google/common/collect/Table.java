package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableTable, HashBasedTable, or another implementation")
@GwtCompatible
/* loaded from: classes3.dex */
public interface Table<R, C, V> {

    /* loaded from: classes3.dex */
    public interface Cell<R, C, V> {
        boolean equals(@NullableDecl Object obj);

        @NullableDecl
        C getColumnKey();

        @NullableDecl
        R getRowKey();

        @NullableDecl
        V getValue();

        int hashCode();
    }

    /* renamed from: cellSet */
    Set<Cell<R, C, V>> mo7854cellSet();

    void clear();

    /* renamed from: column */
    Map<R, V> mo8030column(C c);

    /* renamed from: columnKeySet */
    Set<C> mo7856columnKeySet();

    /* renamed from: columnMap */
    Map<C, Map<R, V>> mo8036columnMap();

    boolean contains(@NullableDecl @CompatibleWith("R") Object obj, @NullableDecl @CompatibleWith("C") Object obj2);

    boolean containsColumn(@NullableDecl @CompatibleWith("C") Object obj);

    boolean containsRow(@NullableDecl @CompatibleWith("R") Object obj);

    boolean containsValue(@NullableDecl @CompatibleWith("V") Object obj);

    boolean equals(@NullableDecl Object obj);

    @NullableDecl
    V get(@NullableDecl @CompatibleWith("R") Object obj, @NullableDecl @CompatibleWith("C") Object obj2);

    int hashCode();

    boolean isEmpty();

    @CanIgnoreReturnValue
    @NullableDecl
    V put(R r, C c, V v);

    void putAll(Table<? extends R, ? extends C, ? extends V> table);

    @CanIgnoreReturnValue
    @NullableDecl
    V remove(@NullableDecl @CompatibleWith("R") Object obj, @NullableDecl @CompatibleWith("C") Object obj2);

    /* renamed from: row */
    Map<C, V> mo8094row(R r);

    /* renamed from: rowKeySet */
    Set<R> mo8095rowKeySet();

    /* renamed from: rowMap */
    Map<R, Map<C, V>> mo8096rowMap();

    int size();

    /* renamed from: values */
    Collection<V> mo7863values();
}
