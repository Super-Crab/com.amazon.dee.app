package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes4.dex */
public interface Get<K, V> {
    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    Set<Map.Entry<K, V>> entrySet();

    /* renamed from: get */
    V mo12663get(Object obj);

    boolean isEmpty();

    Set<K> keySet();

    /* renamed from: remove */
    V mo12668remove(Object obj);

    int size();

    /* renamed from: values */
    Collection<V> mo12691values();
}
