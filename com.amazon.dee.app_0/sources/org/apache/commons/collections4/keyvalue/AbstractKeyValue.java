package org.apache.commons.collections4.keyvalue;

import org.apache.commons.collections4.KeyValue;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public abstract class AbstractKeyValue<K, V> implements KeyValue<K, V> {
    private K key;
    private V value;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractKeyValue(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override // org.apache.commons.collections4.KeyValue
    /* renamed from: getKey */
    public K mo12677getKey() {
        return this.key;
    }

    @Override // org.apache.commons.collections4.KeyValue
    /* renamed from: getValue */
    public V mo12678getValue() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K setKey(K k) {
        K k2 = this.key;
        this.key = k;
        return k2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mo12677getKey());
        sb.append(Chars.EQ);
        sb.append(mo12678getValue());
        return sb.toString();
    }
}
