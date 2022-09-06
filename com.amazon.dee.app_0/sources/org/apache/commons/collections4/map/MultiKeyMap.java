package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap;
/* loaded from: classes4.dex */
public class MultiKeyMap<K, V> extends AbstractMapDecorator<MultiKey<? extends K>, V> implements Serializable, Cloneable {
    private static final long serialVersionUID = -1788199231038721040L;

    public MultiKeyMap() {
        this(new HashedMap());
    }

    public static <K, V> MultiKeyMap<K, V> multiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> abstractHashedMap) {
        if (abstractHashedMap != null) {
            if (abstractHashedMap.size() <= 0) {
                return new MultiKeyMap<>(abstractHashedMap);
            }
            throw new IllegalArgumentException("Map must be empty");
        }
        throw new NullPointerException("Map must not be null");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    protected void checkKey(MultiKey<?> multiKey) {
        if (multiKey != null) {
            return;
        }
        throw new NullPointerException("Key must not be null");
    }

    public boolean containsKey(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2)) {
                return hashEntry.mo12678getValue();
            }
        }
        return null;
    }

    protected int hash(Object obj, Object obj2) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2) {
        MultiKey<? extends K> mo12677getKey = hashEntry.mo12677getKey();
        if (mo12677getKey.size() == 2 && (obj == mo12677getKey.getKey(0) || (obj != null && obj.equals(mo12677getKey.getKey(0))))) {
            if (obj2 == mo12677getKey.getKey(1)) {
                return true;
            }
            if (obj2 != null && obj2.equals(mo12677getKey.getKey(1))) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<MultiKey<? extends K>, V> mo12767mapIterator() {
        return mo12722decorated().mo12767mapIterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((MultiKey) ((MultiKey) obj), (MultiKey<? extends K>) obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends MultiKey<? extends K>, ? extends V> map) {
        for (MultiKey<?> multiKey : map.keySet()) {
            checkKey(multiKey);
        }
        super.putAll(map);
    }

    public boolean removeAll(Object obj) {
        MapIterator<MultiKey<? extends K>, V> mo12767mapIterator = mo12767mapIterator();
        boolean z = false;
        while (mo12767mapIterator.hasNext()) {
            MultiKey<? extends K> mo12683next = mo12767mapIterator.mo12683next();
            if (mo12683next.size() >= 1) {
                K key = mo12683next.getKey(0);
                if (obj == null) {
                    if (key == null) {
                        mo12767mapIterator.remove();
                        z = true;
                    }
                } else if (obj.equals(key)) {
                    mo12767mapIterator.remove();
                    z = true;
                }
            }
        }
        return z;
    }

    public V removeMultiKey(Object obj, Object obj2) {
        int hash = hash(obj, obj2);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry2 = mo12722decorated().data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hash && isEqualKey(hashEntry2, obj, obj2)) {
                V mo12678getValue = hashEntry2.mo12678getValue();
                mo12722decorated().removeMapping(hashEntry2, hashIndex, hashEntry);
                return mo12678getValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    protected MultiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> abstractHashedMap) {
        super(abstractHashedMap);
        this.map = abstractHashedMap;
    }

    public MultiKeyMap<K, V> clone() {
        try {
            return (MultiKeyMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public AbstractHashedMap<MultiKey<? extends K>, V> mo12722decorated() {
        return (AbstractHashedMap) super.mo12722decorated();
    }

    public V put(K k, K k2, V v) {
        int hash = hash(k, k2);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[hashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, k, k2)) {
                V mo12678getValue = hashEntry.mo12678getValue();
                mo12722decorated().updateEntry(hashEntry, v);
                return mo12678getValue;
            }
        }
        mo12722decorated().addMapping(hashIndex, hash, new MultiKey<>(k, k2), v);
        return null;
    }

    protected int hash(Object obj, Object obj2, Object obj3) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3) {
        MultiKey<? extends K> mo12677getKey = hashEntry.mo12677getKey();
        if (mo12677getKey.size() == 3 && ((obj == mo12677getKey.getKey(0) || (obj != null && obj.equals(mo12677getKey.getKey(0)))) && (obj2 == mo12677getKey.getKey(1) || (obj2 != null && obj2.equals(mo12677getKey.getKey(1)))))) {
            if (obj3 == mo12677getKey.getKey(2)) {
                return true;
            }
            if (obj3 != null && obj3.equals(mo12677getKey.getKey(2))) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3, Object obj4) {
        MultiKey<? extends K> mo12677getKey = hashEntry.mo12677getKey();
        if (mo12677getKey.size() == 4 && ((obj == mo12677getKey.getKey(0) || (obj != null && obj.equals(mo12677getKey.getKey(0)))) && ((obj2 == mo12677getKey.getKey(1) || (obj2 != null && obj2.equals(mo12677getKey.getKey(1)))) && (obj3 == mo12677getKey.getKey(2) || (obj3 != null && obj3.equals(mo12677getKey.getKey(2))))))) {
            if (obj4 == mo12677getKey.getKey(3)) {
                return true;
            }
            if (obj4 != null && obj4.equals(mo12677getKey.getKey(3))) {
                return true;
            }
        }
        return false;
    }

    public V get(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return hashEntry.mo12678getValue();
            }
        }
        return null;
    }

    protected int hash(Object obj, Object obj2, Object obj3, Object obj4) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        if (obj4 != null) {
            i ^= obj4.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r8, java.lang.Object r9) {
        /*
            r7 = this;
            org.apache.commons.collections4.MapIterator r0 = r7.mo12767mapIterator()
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L3d
            java.lang.Object r3 = r0.mo12683next()
            org.apache.commons.collections4.keyvalue.MultiKey r3 = (org.apache.commons.collections4.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 2
            r6 = 1
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r8 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6
        L29:
            java.lang.Object r3 = r3.getKey(r6)
            if (r9 != 0) goto L32
            if (r3 != 0) goto L6
            goto L38
        L32:
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L6
        L38:
            r0.remove()
            r2 = r6
            goto L6
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object):boolean");
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        MultiKey<? extends K> mo12677getKey = hashEntry.mo12677getKey();
        if (mo12677getKey.size() == 5 && ((obj == mo12677getKey.getKey(0) || (obj != null && obj.equals(mo12677getKey.getKey(0)))) && ((obj2 == mo12677getKey.getKey(1) || (obj2 != null && obj2.equals(mo12677getKey.getKey(1)))) && ((obj3 == mo12677getKey.getKey(2) || (obj3 != null && obj3.equals(mo12677getKey.getKey(2)))) && (obj4 == mo12677getKey.getKey(3) || (obj4 != null && obj4.equals(mo12677getKey.getKey(3)))))))) {
            if (obj5 == mo12677getKey.getKey(4)) {
                return true;
            }
            if (obj5 != null && obj5.equals(mo12677getKey.getKey(4))) {
                return true;
            }
        }
        return false;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3) {
        int hash = hash(obj, obj2, obj3);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry2 = mo12722decorated().data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hash && isEqualKey(hashEntry2, obj, obj2, obj3)) {
                V mo12678getValue = hashEntry2.mo12678getValue();
                mo12722decorated().removeMapping(hashEntry2, hashIndex, hashEntry);
                return mo12678getValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        for (AbstractHashedMap.HashEntry hashEntry = (AbstractHashedMap.HashEntry<K, V>) ((AbstractHashedMap.HashEntry<MultiKey<? extends K>, V>[]) mo12722decorated().data)[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = (AbstractHashedMap.HashEntry<K, V>) hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return true;
            }
        }
        return false;
    }

    protected int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int i = 0;
        if (obj != null) {
            i = 0 ^ obj.hashCode();
        }
        if (obj2 != null) {
            i ^= obj2.hashCode();
        }
        if (obj3 != null) {
            i ^= obj3.hashCode();
        }
        if (obj4 != null) {
            i ^= obj4.hashCode();
        }
        if (obj5 != null) {
            i ^= obj5.hashCode();
        }
        int i2 = i + (~(i << 9));
        int i3 = (i2 >>> 14) ^ i2;
        int i4 = i3 + (i3 << 4);
        return i4 ^ (i4 >>> 10);
    }

    public V put(K k, K k2, K k3, V v) {
        int hash = hash(k, k2, k3);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[hashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, k, k2, k3)) {
                V mo12678getValue = hashEntry.mo12678getValue();
                mo12722decorated().updateEntry(hashEntry, v);
                return mo12678getValue;
            }
        }
        mo12722decorated().addMapping(hashIndex, hash, new MultiKey<>(k, k2, k3), v);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V get(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        for (AbstractHashedMap.HashEntry hashEntry = (AbstractHashedMap.HashEntry<K, V>) ((AbstractHashedMap.HashEntry<MultiKey<? extends K>, V>[]) mo12722decorated().data)[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = (AbstractHashedMap.HashEntry<K, V>) hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return hashEntry.mo12678getValue();
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x003f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r8, java.lang.Object r9, java.lang.Object r10) {
        /*
            r7 = this;
            org.apache.commons.collections4.MapIterator r0 = r7.mo12767mapIterator()
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L4d
            java.lang.Object r3 = r0.mo12683next()
            org.apache.commons.collections4.keyvalue.MultiKey r3 = (org.apache.commons.collections4.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 3
            r6 = 1
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r8 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6
        L29:
            java.lang.Object r4 = r3.getKey(r6)
            if (r9 != 0) goto L32
            if (r4 != 0) goto L6
            goto L38
        L32:
            boolean r4 = r9.equals(r4)
            if (r4 == 0) goto L6
        L38:
            r4 = 2
            java.lang.Object r3 = r3.getKey(r4)
            if (r10 != 0) goto L42
            if (r3 != 0) goto L6
            goto L48
        L42:
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L6
        L48:
            r0.remove()
            r2 = r6
            goto L6
        L4d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        for (AbstractHashedMap.HashEntry hashEntry = (AbstractHashedMap.HashEntry<K, V>) ((AbstractHashedMap.HashEntry<MultiKey<? extends K>, V>[]) mo12722decorated().data)[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = (AbstractHashedMap.HashEntry<K, V>) hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return true;
            }
        }
        return false;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3, Object obj4) {
        int hash = hash(obj, obj2, obj3, obj4);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry2 = mo12722decorated().data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hash && isEqualKey(hashEntry2, obj, obj2, obj3, obj4)) {
                V mo12678getValue = hashEntry2.mo12678getValue();
                mo12722decorated().removeMapping(hashEntry2, hashIndex, hashEntry);
                return mo12678getValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V get(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        for (AbstractHashedMap.HashEntry hashEntry = (AbstractHashedMap.HashEntry<K, V>) ((AbstractHashedMap.HashEntry<MultiKey<? extends K>, V>[]) mo12722decorated().data)[mo12722decorated().hashIndex(hash, mo12722decorated().data.length)]; hashEntry != null; hashEntry = (AbstractHashedMap.HashEntry<K, V>) hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return hashEntry.mo12678getValue();
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x004f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x003f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r8, java.lang.Object r9, java.lang.Object r10, java.lang.Object r11) {
        /*
            r7 = this;
            org.apache.commons.collections4.MapIterator r0 = r7.mo12767mapIterator()
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L5d
            java.lang.Object r3 = r0.mo12683next()
            org.apache.commons.collections4.keyvalue.MultiKey r3 = (org.apache.commons.collections4.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 4
            r6 = 1
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r8 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L6
        L29:
            java.lang.Object r4 = r3.getKey(r6)
            if (r9 != 0) goto L32
            if (r4 != 0) goto L6
            goto L38
        L32:
            boolean r4 = r9.equals(r4)
            if (r4 == 0) goto L6
        L38:
            r4 = 2
            java.lang.Object r4 = r3.getKey(r4)
            if (r10 != 0) goto L42
            if (r4 != 0) goto L6
            goto L48
        L42:
            boolean r4 = r10.equals(r4)
            if (r4 == 0) goto L6
        L48:
            r4 = 3
            java.lang.Object r3 = r3.getKey(r4)
            if (r11 != 0) goto L52
            if (r3 != 0) goto L6
            goto L58
        L52:
            boolean r3 = r11.equals(r3)
            if (r3 == 0) goto L6
        L58:
            r0.remove()
            r2 = r6
            goto L6
        L5d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):boolean");
    }

    public V put(K k, K k2, K k3, K k4, V v) {
        int hash = hash(k, k2, k3, k4);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[hashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, k, k2, k3, k4)) {
                V v2 = (V) hashEntry.mo12678getValue();
                mo12722decorated().updateEntry(hashEntry, v);
                return v2;
            }
        }
        mo12722decorated().addMapping(hashIndex, hash, new MultiKey<>(k, k2, k3, k4), v);
        return null;
    }

    public V removeMultiKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> hashEntry2 = mo12722decorated().data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hash && isEqualKey(hashEntry2, obj, obj2, obj3, obj4, obj5)) {
                V mo12678getValue = hashEntry2.mo12678getValue();
                mo12722decorated().removeMapping(hashEntry2, hashIndex, hashEntry);
                return mo12678getValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    public V put(K k, K k2, K k3, K k4, K k5, V v) {
        int hash = hash(k, k2, k3, k4, k5);
        int hashIndex = mo12722decorated().hashIndex(hash, mo12722decorated().data.length);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = mo12722decorated().data[hashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(hashEntry, k, k2, k3, k4, k5)) {
                V v2 = (V) hashEntry.mo12678getValue();
                mo12722decorated().updateEntry(hashEntry, v);
                return v2;
            }
        }
        mo12722decorated().addMapping(hashIndex, hash, new MultiKey<>(k, k2, k3, k4, k5), v);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V put(MultiKey<? extends K> multiKey, V v) {
        checkKey(multiKey);
        return (V) super.put((MultiKeyMap<K, V>) multiKey, (MultiKey<? extends K>) v);
    }
}
