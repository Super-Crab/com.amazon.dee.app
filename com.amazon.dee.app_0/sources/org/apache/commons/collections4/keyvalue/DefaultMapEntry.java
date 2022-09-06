package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
/* loaded from: classes4.dex */
public final class DefaultMapEntry<K, V> extends AbstractMapEntry<K, V> {
    public DefaultMapEntry(K k, V v) {
        super(k, v);
    }

    public DefaultMapEntry(KeyValue<? extends K, ? extends V> keyValue) {
        super(keyValue.mo12677getKey(), keyValue.mo12678getValue());
    }

    public DefaultMapEntry(Map.Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }
}
