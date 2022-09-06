package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.BidiMap;
/* loaded from: classes4.dex */
public class DualLinkedHashBidiMap<K, V> extends AbstractDualBidiMap<K, V> implements Serializable {
    private static final long serialVersionUID = 721969328361810L;

    public DualLinkedHashBidiMap() {
        super(new LinkedHashMap(), new LinkedHashMap());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.normalMap = new LinkedHashMap();
        this.reverseMap = new LinkedHashMap();
        putAll((Map) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.normalMap);
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractDualBidiMap
    /* renamed from: createBidiMap */
    protected BidiMap<V, K> mo12649createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap) {
        return new DualLinkedHashBidiMap(map, map2, bidiMap);
    }

    public DualLinkedHashBidiMap(Map<? extends K, ? extends V> map) {
        super(new LinkedHashMap(), new LinkedHashMap());
        putAll(map);
    }

    protected DualLinkedHashBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        super(map, map2, bidiMap);
    }
}
