package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class GarbageFreeSortedArrayThreadContextMap implements ReadOnlyThreadContextMap, ObjectThreadContextMap {
    protected static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    protected static final String PROPERTY_NAME_INITIAL_CAPACITY = "log4j2.ThreadContext.initial.capacity";
    private static volatile boolean inheritableMap;
    private static volatile int initialCapacity;
    protected final ThreadLocal<StringMap> localMap = createThreadLocalMap();

    static {
        init();
    }

    private ThreadLocal<StringMap> createThreadLocalMap() {
        if (inheritableMap) {
            return new InheritableThreadLocal<StringMap>() { // from class: org.apache.logging.log4j.spi.GarbageFreeSortedArrayThreadContextMap.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.InheritableThreadLocal
                public StringMap childValue(StringMap stringMap) {
                    if (stringMap != null) {
                        return GarbageFreeSortedArrayThreadContextMap.this.createStringMap(stringMap);
                    }
                    return null;
                }
            };
        }
        return new ThreadLocal<>();
    }

    private StringMap getThreadLocalMap() {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            StringMap createStringMap = createStringMap();
            this.localMap.set(createStringMap);
            return createStringMap;
        }
        return stringMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
        PropertiesUtil properties = PropertiesUtil.getProperties();
        initialCapacity = properties.getIntegerProperty(PROPERTY_NAME_INITIAL_CAPACITY, 16);
        inheritableMap = properties.getBooleanProperty("isThreadContextMapInheritable");
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            stringMap.clear();
        }
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public boolean containsKey(String str) {
        StringMap stringMap = this.localMap.get();
        return stringMap != null && stringMap.containsKey(str);
    }

    protected StringMap createStringMap() {
        return new SortedArrayStringMap(initialCapacity);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThreadContextMap)) {
            return false;
        }
        Map<String, String> immutableMapOrNull = getImmutableMapOrNull();
        Map<String, String> immutableMapOrNull2 = ((ThreadContextMap) obj).getImmutableMapOrNull();
        if (immutableMapOrNull == null) {
            if (immutableMapOrNull2 != null) {
                return false;
            }
        } else if (!immutableMapOrNull.equals(immutableMapOrNull2)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public String get(String str) {
        return (String) getValue(str);
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        StringMap stringMap = this.localMap.get();
        return stringMap == null ? new HashMap() : stringMap.toMap();
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            return null;
        }
        return Collections.unmodifiableMap(stringMap.toMap());
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap2
    public StringMap getReadOnlyContextData() {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            StringMap createStringMap = createStringMap();
            this.localMap.set(createStringMap);
            return createStringMap;
        }
        return stringMap;
    }

    @Override // org.apache.logging.log4j.spi.ObjectThreadContextMap
    public <V> V getValue(String str) {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            return null;
        }
        return (V) stringMap.getValue(str);
    }

    public int hashCode() {
        StringMap stringMap = this.localMap.get();
        return 31 + (stringMap == null ? 0 : stringMap.hashCode());
    }

    @Override // org.apache.logging.log4j.spi.ReadOnlyThreadContextMap, org.apache.logging.log4j.spi.ThreadContextMap
    public boolean isEmpty() {
        StringMap stringMap = this.localMap.get();
        return stringMap == null || stringMap.size() == 0;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(String str, String str2) {
        getThreadLocalMap().putValue(str, str2);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap2
    public void putAll(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        StringMap threadLocalMap = getThreadLocalMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            threadLocalMap.putValue(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.apache.logging.log4j.spi.ObjectThreadContextMap
    public <V> void putAllValues(Map<String, V> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        StringMap threadLocalMap = getThreadLocalMap();
        for (Map.Entry<String, V> entry : map.entrySet()) {
            threadLocalMap.putValue(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.apache.logging.log4j.spi.ObjectThreadContextMap
    public void putValue(String str, Object obj) {
        getThreadLocalMap().putValue(str, obj);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(String str) {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            stringMap.remove(str);
        }
    }

    @Override // org.apache.logging.log4j.spi.CleanableThreadContextMap
    public void removeAll(Iterable<String> iterable) {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            for (String str : iterable) {
                stringMap.remove(str);
            }
        }
    }

    public String toString() {
        StringMap stringMap = this.localMap.get();
        return stringMap == null ? "{}" : stringMap.toString();
    }

    protected StringMap createStringMap(ReadOnlyStringMap readOnlyStringMap) {
        return new SortedArrayStringMap(readOnlyStringMap);
    }
}
