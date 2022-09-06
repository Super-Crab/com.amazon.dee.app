package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;
/* loaded from: classes4.dex */
public class DefaultThreadContextMap implements ThreadContextMap, ReadOnlyStringMap {
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    private static boolean inheritableMap = false;
    private static final long serialVersionUID = 8218007901108944053L;
    private final ThreadLocal<Map<String, String>> localMap;
    private final boolean useMap;

    static {
        init();
    }

    public DefaultThreadContextMap() {
        this(true);
    }

    static ThreadLocal<Map<String, String>> createThreadLocalMap(final boolean z) {
        if (inheritableMap) {
            return new InheritableThreadLocal<Map<String, String>>() { // from class: org.apache.logging.log4j.spi.DefaultThreadContextMap.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.InheritableThreadLocal
                public Map<String, String> childValue(Map<String, String> map) {
                    if (map == null || !z) {
                        return null;
                    }
                    return Collections.unmodifiableMap(new HashMap(map));
                }
            };
        }
        return new ThreadLocal<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
        inheritableMap = PropertiesUtil.getProperties().getBooleanProperty("isThreadContextMapInheritable");
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
        this.localMap.remove();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean containsKey(String str) {
        Map<String, String> map = this.localMap.get();
        return map != null && map.containsKey(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (((obj instanceof DefaultThreadContextMap) && this.useMap != ((DefaultThreadContextMap) obj).useMap) || !(obj instanceof ThreadContextMap)) {
            return false;
        }
        Map<String, String> map = this.localMap.get();
        Map<String, String> immutableMapOrNull = ((ThreadContextMap) obj).getImmutableMapOrNull();
        if (map == null) {
            if (immutableMapOrNull != null) {
                return false;
            }
        } else if (!map.equals(immutableMapOrNull)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> void forEach(BiConsumer<String, ? super V> biConsumer) {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public String get(String str) {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        Map<String, String> map = this.localMap.get();
        return map == null ? new HashMap() : new HashMap(map);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        return this.localMap.get();
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> V getValue(String str) {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return null;
        }
        return (V) map.get(str);
    }

    public int hashCode() {
        Map<String, String> map = this.localMap.get();
        return Boolean.valueOf(this.useMap).hashCode() + (((map == null ? 0 : map.hashCode()) + 31) * 31);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean isEmpty() {
        Map<String, String> map = this.localMap.get();
        return map == null || map.size() == 0;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(String str, String str2) {
        if (!this.useMap) {
            return;
        }
        Map<String, String> map = this.localMap.get();
        HashMap hashMap = map == null ? new HashMap(1) : new HashMap(map);
        hashMap.put(str, str2);
        this.localMap.set(Collections.unmodifiableMap(hashMap));
    }

    public void putAll(Map<String, String> map) {
        if (!this.useMap) {
            return;
        }
        Map<String, String> map2 = this.localMap.get();
        HashMap hashMap = map2 == null ? new HashMap(map.size()) : new HashMap(map2);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        this.localMap.set(Collections.unmodifiableMap(hashMap));
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(String str) {
        Map<String, String> map = this.localMap.get();
        if (map != null) {
            HashMap hashMap = new HashMap(map);
            hashMap.remove(str);
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    public void removeAll(Iterable<String> iterable) {
        Map<String, String> map = this.localMap.get();
        if (map != null) {
            HashMap hashMap = new HashMap(map);
            for (String str : iterable) {
                hashMap.remove(str);
            }
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public int size() {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public Map<String, String> toMap() {
        return getCopy();
    }

    public String toString() {
        Map<String, String> map = this.localMap.get();
        return map == null ? "{}" : map.toString();
    }

    public DefaultThreadContextMap(boolean z) {
        this.useMap = z;
        this.localMap = createThreadLocalMap(z);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V, S> void forEach(TriConsumer<String, ? super V, S> triConsumer, S s) {
        Map<String, String> map = this.localMap.get();
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            triConsumer.accept(entry.getKey(), entry.getValue(), s);
        }
    }
}
