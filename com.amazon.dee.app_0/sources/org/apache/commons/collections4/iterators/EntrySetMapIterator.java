package org.apache.commons.collections4.iterators;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
/* loaded from: classes4.dex */
public class EntrySetMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
    private boolean canRemove = false;
    private Iterator<Map.Entry<K, V>> iterator;
    private Map.Entry<K, V> last;
    private final Map<K, V> map;

    public EntrySetMapIterator(Map<K, V> map) {
        this.map = map;
        this.iterator = map.entrySet().iterator();
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getKey */
    public K mo12681getKey() {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.getKey();
        }
        throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getValue */
    public V mo12682getValue() {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.getValue();
        }
        throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    /* renamed from: next */
    public K mo12683next() {
        this.last = this.iterator.next();
        this.canRemove = true;
        return this.last.getKey();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public void remove() {
        if (this.canRemove) {
            this.iterator.remove();
            this.last = null;
            this.canRemove = false;
            return;
        }
        throw new IllegalStateException("Iterator remove() can only be called once after next()");
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        this.iterator = this.map.entrySet().iterator();
        this.last = null;
        this.canRemove = false;
    }

    @Override // org.apache.commons.collections4.MapIterator
    public V setValue(V v) {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.setValue(v);
        }
        throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
    }

    public String toString() {
        if (this.last != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MapIterator[");
            outline107.append(mo12681getKey());
            outline107.append(Config.Compare.EQUAL_TO);
            outline107.append(mo12682getValue());
            outline107.append("]");
            return outline107.toString();
        }
        return "MapIterator[]";
    }
}
