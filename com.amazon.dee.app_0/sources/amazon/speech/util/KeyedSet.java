package amazon.speech.util;

import amazon.speech.util.Keyed;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes.dex */
public class KeyedSet<K, V extends Keyed<K>> extends AbstractSet<V> {
    private final HashMap<K, V> mMap = new HashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return add((KeyedSet<K, V>) ((Keyed) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        try {
            return this.mMap.containsKey(((Keyed) obj).getKey());
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public boolean containsByKey(K k) {
        return getByKey(k) != null;
    }

    public V getByKey(K k) {
        return this.mMap.get(k);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<V> iterator() {
        return this.mMap.values().iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        try {
            return removeByKey(((Keyed) obj).getKey());
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public boolean removeByKey(K k) {
        return this.mMap.remove(k) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.mMap.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean add(V v) {
        Object key = v.getKey();
        if (this.mMap.containsKey(key)) {
            return false;
        }
        this.mMap.put(key, v);
        return true;
    }
}
