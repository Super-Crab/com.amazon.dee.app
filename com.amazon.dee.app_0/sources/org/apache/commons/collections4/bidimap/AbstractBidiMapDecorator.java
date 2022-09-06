package org.apache.commons.collections4.bidimap;

import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.AbstractMapDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractBidiMapDecorator<K, V> extends AbstractMapDecorator<K, V> implements BidiMap<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBidiMapDecorator(BidiMap<K, V> bidiMap) {
        super(bidiMap);
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: getKey */
    public K mo12664getKey(Object obj) {
        return mo12722decorated().mo12664getKey(obj);
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    public BidiMap<V, K> mo12689inverseBidiMap() {
        return mo12722decorated().mo12689inverseBidiMap();
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        return mo12722decorated().mo12767mapIterator();
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: removeValue */
    public K mo12669removeValue(Object obj) {
        return mo12722decorated().mo12669removeValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public BidiMap<K, V> mo12722decorated() {
        return (BidiMap) super.mo12722decorated();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values  reason: collision with other method in class */
    public Set<V> mo12691values() {
        return mo12722decorated().mo12691values();
    }
}
