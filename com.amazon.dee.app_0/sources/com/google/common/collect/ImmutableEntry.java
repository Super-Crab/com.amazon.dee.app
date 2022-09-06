package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes3.dex */
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @NullableDecl
    final K key;
    @NullableDecl
    final V value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableEntry(@NullableDecl K k, @NullableDecl V v) {
        this.key = k;
        this.value = v;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    @NullableDecl
    /* renamed from: getKey */
    public final K mo8108getKey() {
        return this.key;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    @NullableDecl
    /* renamed from: getValue */
    public final V mo7742getValue() {
        return this.value;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }
}
