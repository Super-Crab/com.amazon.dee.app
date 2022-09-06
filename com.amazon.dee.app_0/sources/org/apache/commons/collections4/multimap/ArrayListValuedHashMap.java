package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;
/* loaded from: classes4.dex */
public class ArrayListValuedHashMap<K, V> extends AbstractListValuedMap<K, V> implements Serializable {
    private static final int DEFAULT_INITIAL_LIST_CAPACITY = 3;
    private static final int DEFAULT_INITIAL_MAP_CAPACITY = 16;
    private static final long serialVersionUID = 20151118;
    private final int initialListCapacity;

    public ArrayListValuedHashMap() {
        this(16, 3);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setMap(new HashMap());
        doReadObject(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    public void trimToSize() {
        Iterator<List<V>> it2 = getMap().values().iterator();
        while (it2.hasNext()) {
            ((ArrayList) it2.next()).trimToSize();
        }
    }

    public ArrayListValuedHashMap(int i) {
        this(16, i);
    }

    public ArrayListValuedHashMap(int i, int i2) {
        super(new HashMap(i));
        this.initialListCapacity = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractListValuedMap, org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: createCollection */
    public ArrayList<V> mo12744createCollection() {
        return new ArrayList<>(this.initialListCapacity);
    }

    public ArrayListValuedHashMap(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        this(multiValuedMap.size(), 3);
        super.putAll(multiValuedMap);
    }

    public ArrayListValuedHashMap(Map<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }
}
