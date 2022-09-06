package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
/* loaded from: classes4.dex */
public class DefaultKeyValue<K, V> extends AbstractKeyValue<K, V> {
    public DefaultKeyValue() {
        super(null, null);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultKeyValue)) {
            return false;
        }
        DefaultKeyValue defaultKeyValue = (DefaultKeyValue) obj;
        if (mo12677getKey() != null ? mo12677getKey().equals(defaultKeyValue.mo12677getKey()) : defaultKeyValue.mo12677getKey() == null) {
            if (mo12678getValue() == null) {
                if (defaultKeyValue.mo12678getValue() == null) {
                    return true;
                }
            } else if (mo12678getValue().equals(defaultKeyValue.mo12678getValue())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = mo12677getKey() == null ? 0 : mo12677getKey().hashCode();
        if (mo12678getValue() != null) {
            i = mo12678getValue().hashCode();
        }
        return hashCode ^ i;
    }

    @Override // org.apache.commons.collections4.keyvalue.AbstractKeyValue
    public K setKey(K k) {
        if (k != this) {
            return (K) super.setKey(k);
        }
        throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a key.");
    }

    @Override // org.apache.commons.collections4.keyvalue.AbstractKeyValue, java.util.Map.Entry
    public V setValue(V v) {
        if (v != this) {
            return (V) super.setValue(v);
        }
        throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a value.");
    }

    public Map.Entry<K, V> toMapEntry() {
        return new DefaultMapEntry(this);
    }

    public DefaultKeyValue(K k, V v) {
        super(k, v);
    }

    public DefaultKeyValue(KeyValue<? extends K, ? extends V> keyValue) {
        super(keyValue.mo12677getKey(), keyValue.mo12678getValue());
    }

    public DefaultKeyValue(Map.Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }
}
