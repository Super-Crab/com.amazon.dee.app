package org.apache.logging.log4j.spi;

import java.util.Map;
/* loaded from: classes4.dex */
public interface ObjectThreadContextMap extends CleanableThreadContextMap {
    <V> V getValue(String str);

    <V> void putAllValues(Map<String, V> map);

    <V> void putValue(String str, V v);
}
