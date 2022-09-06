package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.impl.ConfigString;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PropertiesParser {
    PropertiesParser() {
    }

    static String exceptLastElement(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return null;
        }
        return str.substring(0, lastIndexOf);
    }

    private static <K, V> AbstractConfigObject fromEntrySet(ConfigOrigin configOrigin, Set<Map.Entry<K, V>> set) {
        return fromPathMap(configOrigin, getPathMap(set), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigObject fromPathMap(ConfigOrigin configOrigin, Map<?, ?> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (key instanceof String) {
                hashMap.put(Path.newPath((String) key), entry.getValue());
            } else {
                throw new ConfigException.BugOrBroken("Map has a non-string as a key, expecting a path expression as a String");
            }
        }
        return fromPathMap(configOrigin, hashMap, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigObject fromProperties(ConfigOrigin configOrigin, Properties properties) {
        return fromEntrySet(configOrigin, properties.entrySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigObject fromStringMap(ConfigOrigin configOrigin, Map<String, String> map) {
        return fromEntrySet(configOrigin, map.entrySet());
    }

    private static <K, V> Map<Path, Object> getPathMap(Set<Map.Entry<K, V>> set) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<K, V> entry : set) {
            K key = entry.getKey();
            if (key instanceof String) {
                hashMap.put(pathFromPropertyKey((String) key), entry.getValue());
            }
        }
        return hashMap;
    }

    static String lastElement(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigObject parse(Reader reader, ConfigOrigin configOrigin) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        return fromProperties(configOrigin, properties);
    }

    static Path pathFromPropertyKey(String str) {
        String lastElement = lastElement(str);
        String exceptLastElement = exceptLastElement(str);
        Path path = new Path(lastElement, null);
        while (exceptLastElement != null) {
            String lastElement2 = lastElement(exceptLastElement);
            exceptLastElement = exceptLastElement(exceptLastElement);
            path = new Path(lastElement2, path);
        }
        return path;
    }

    private static AbstractConfigObject fromPathMap(ConfigOrigin configOrigin, Map<Path, Object> map, boolean z) {
        ConfigMergeable fromAnyRef;
        HashSet<Path> hashSet = new HashSet();
        HashSet<Path> hashSet2 = new HashSet();
        for (Path path : map.keySet()) {
            hashSet2.add(path);
            for (Path parent = path.parent(); parent != null; parent = parent.parent()) {
                hashSet.add(parent);
            }
        }
        if (z) {
            hashSet2.removeAll(hashSet);
        } else {
            for (Path path2 : hashSet2) {
                if (hashSet.contains(path2)) {
                    throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("In the map, path '"), path2.render(), "' occurs as both the parent object of a value and as a value. Because Map has no defined ordering, this is a broken situation."));
                }
            }
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (Path path3 : hashSet) {
            hashMap2.put(path3, new HashMap());
        }
        for (Path path4 : hashSet2) {
            Path parent2 = path4.parent();
            Map map2 = parent2 != null ? (Map) hashMap2.get(parent2) : hashMap;
            String last = path4.last();
            Object obj = map.get(path4);
            if (z) {
                fromAnyRef = obj instanceof String ? new ConfigString.Quoted(configOrigin, (String) obj) : null;
            } else {
                fromAnyRef = ConfigImpl.fromAnyRef(map.get(path4), configOrigin, FromMapMode.KEYS_ARE_PATHS);
            }
            if (fromAnyRef != null) {
                map2.put(last, fromAnyRef);
            }
        }
        ArrayList<Path> arrayList = new ArrayList();
        arrayList.addAll(hashSet);
        Collections.sort(arrayList, new Comparator<Path>() { // from class: com.typesafe.config.impl.PropertiesParser.1
            @Override // java.util.Comparator
            public int compare(Path path5, Path path6) {
                return path6.length() - path5.length();
            }
        });
        for (Path path5 : arrayList) {
            Map map3 = (Map) hashMap2.get(path5);
            Path parent3 = path5.parent();
            (parent3 != null ? (Map) hashMap2.get(parent3) : hashMap).put(path5.last(), new SimpleConfigObject(configOrigin, map3, ResolveStatus.RESOLVED, false));
        }
        return new SimpleConfigObject(configOrigin, hashMap, ResolveStatus.RESOLVED, false);
    }
}
