package com.amazon.alexa.accessory.notificationpublisher.utils;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class LRUHashMap<K, V> extends LinkedHashMap<K, V> {
    private int maxEntries;

    public LRUHashMap() {
        this(50);
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.maxEntries;
    }

    public void setMaxEntries(int i) {
        this.maxEntries = i;
    }

    public LRUHashMap(int i) {
        super(i);
        this.maxEntries = i;
    }
}
