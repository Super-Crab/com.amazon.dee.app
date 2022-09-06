package amazon.speech.util;

import android.util.LruCache;
/* loaded from: classes.dex */
public class LruCacheWrapper<K, V> {
    private final LruCache<K, V> mWrapped;

    public LruCacheWrapper(LruCache<K, V> lruCache) {
        this.mWrapped = lruCache;
    }

    public V get(K k) {
        return this.mWrapped.get(k);
    }

    public void put(K k, V v) {
        this.mWrapped.put(k, v);
    }
}
