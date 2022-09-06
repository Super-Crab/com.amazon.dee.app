package com.google.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public class RegexCache {
    private LRUCache<String, Pattern> cache;

    /* loaded from: classes3.dex */
    private static class LRUCache<K, V> {
        private LinkedHashMap<K, V> map;
        private int size;

        public LRUCache(int i) {
            this.size = i;
            this.map = new LinkedHashMap<K, V>(((i * 4) / 3) + 1, 0.75f, true) { // from class: com.google.i18n.phonenumbers.RegexCache.LRUCache.1
                @Override // java.util.LinkedHashMap
                protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
                    return size() > LRUCache.this.size;
                }
            };
        }

        public synchronized boolean containsKey(K k) {
            return this.map.containsKey(k);
        }

        public synchronized V get(K k) {
            return this.map.get(k);
        }

        public synchronized void put(K k, V v) {
            this.map.put(k, v);
        }
    }

    public RegexCache(int i) {
        this.cache = new LRUCache<>(i);
    }

    boolean containsRegex(String str) {
        return this.cache.containsKey(str);
    }

    public Pattern getPatternForRegex(String str) {
        Pattern pattern = this.cache.get(str);
        if (pattern == null) {
            Pattern compile = Pattern.compile(str);
            this.cache.put(str, compile);
            return compile;
        }
        return pattern;
    }
}
