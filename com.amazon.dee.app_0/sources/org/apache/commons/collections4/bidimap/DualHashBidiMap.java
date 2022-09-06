package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.BidiMap;
/* loaded from: classes4.dex */
public class DualHashBidiMap<K, V> extends AbstractDualBidiMap<K, V> implements Serializable {
    private static final long serialVersionUID = 721969328361808L;

    public DualHashBidiMap() {
        super(new HashMap(), new HashMap());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.normalMap = new HashMap();
        this.reverseMap = new HashMap();
        putAll((Map) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.normalMap);
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractDualBidiMap
    /* renamed from: createBidiMap */
    protected BidiMap<V, K> mo12649createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap) {
        return new DualHashBidiMap(map, map2, bidiMap);
    }

    public DualHashBidiMap(Map<? extends K, ? extends V> map) {
        super(new HashMap(), new HashMap());
        putAll(map);
    }

    protected DualHashBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        super(map, map2, bidiMap);
    }
}
