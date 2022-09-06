package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.SetValuedMap;
/* loaded from: classes4.dex */
public abstract class AbstractSetValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements SetValuedMap<K, V> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class WrappedSet extends AbstractMultiValuedMap<K, V>.WrappedCollection implements Set<V> {
        public WrappedSet(K k) {
            super(k);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            Set set = (Set) mo12734getMapping();
            if (set == null) {
                return Collections.emptySet().equals(obj);
            }
            if (obj instanceof Set) {
                return SetUtils.isEqualSet(set, (Set) obj);
            }
            return false;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return SetUtils.hashCodeForSet((Set) mo12734getMapping());
        }
    }

    protected AbstractSetValuedMap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: createCollection  reason: collision with other method in class */
    public abstract Set<V> mo12744createCollection();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo12740get(Object obj) {
        return mo12740get((AbstractSetValuedMap<K, V>) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    public Map<K, Set<V>> getMap() {
        return super.getMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: wrappedCollection */
    /* bridge */ /* synthetic */ Collection mo12742wrappedCollection(Object obj) {
        return mo12742wrappedCollection((AbstractSetValuedMap<K, V>) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSetValuedMap(Map<K, ? extends Set<V>> map) {
        super(map);
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get  reason: collision with other method in class */
    public Set<V> mo12740get(K k) {
        return mo12742wrappedCollection((AbstractSetValuedMap<K, V>) k);
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap, org.apache.commons.collections4.MultiValuedMap
    /* renamed from: remove  reason: collision with other method in class */
    public Set<V> mo12741remove(Object obj) {
        return SetUtils.emptyIfNull(getMap().remove(obj));
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    /* renamed from: wrappedCollection  reason: collision with other method in class */
    Set<V> mo12742wrappedCollection(K k) {
        return new WrappedSet(k);
    }
}
