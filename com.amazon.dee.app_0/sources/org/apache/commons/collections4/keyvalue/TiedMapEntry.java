package org.apache.commons.collections4.keyvalue;

import com.amazon.alexa.mobilytics.configuration.Config;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.KeyValue;
/* loaded from: classes4.dex */
public class TiedMapEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V>, Serializable {
    private static final long serialVersionUID = -8453869361373831205L;
    private final K key;
    private final Map<K, V> map;

    public TiedMapEntry(Map<K, V> map, K k) {
        this.map = map;
        this.key = k;
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
        V mo12678getValue = mo12678getValue();
        K k = this.key;
        if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
            Object value = entry.getValue();
            if (mo12678getValue == null) {
                if (value == null) {
                    return true;
                }
            } else if (mo12678getValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    /* renamed from: getKey */
    public K mo12677getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    /* renamed from: getValue */
    public V mo12678getValue() {
        return this.map.get(this.key);
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        V mo12678getValue = mo12678getValue();
        int i = 0;
        int hashCode = mo12677getKey() == null ? 0 : mo12677getKey().hashCode();
        if (mo12678getValue != null) {
            i = mo12678getValue.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        if (v != this) {
            return this.map.put(this.key, v);
        }
        throw new IllegalArgumentException("Cannot set value to this map entry");
    }

    public String toString() {
        return mo12677getKey() + Config.Compare.EQUAL_TO + mo12678getValue();
    }
}
