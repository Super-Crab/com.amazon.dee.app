package org.apache.commons.collections4.keyvalue;

import java.util.Map;
/* loaded from: classes4.dex */
public abstract class AbstractMapEntry<K, V> extends AbstractKeyValue<K, V> implements Map.Entry<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapEntry(K k, V v) {
        super(k, v);
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (mo12677getKey() != null ? mo12677getKey().equals(entry.getKey()) : entry.getKey() == null) {
            if (mo12678getValue() == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (mo12678getValue().equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int i = 0;
        int hashCode = mo12677getKey() == null ? 0 : mo12677getKey().hashCode();
        if (mo12678getValue() != null) {
            i = mo12678getValue().hashCode();
        }
        return hashCode ^ i;
    }

    @Override // org.apache.commons.collections4.keyvalue.AbstractKeyValue, java.util.Map.Entry
    public V setValue(V v) {
        return (V) super.setValue(v);
    }
}
