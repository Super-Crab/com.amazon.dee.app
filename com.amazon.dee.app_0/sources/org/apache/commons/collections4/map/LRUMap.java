package org.apache.commons.collections4.map;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.AbstractLinkedMap;
/* loaded from: classes4.dex */
public class LRUMap<K, V> extends AbstractLinkedMap<K, V> implements BoundedMap<K, V>, Serializable, Cloneable {
    protected static final int DEFAULT_MAX_SIZE = 100;
    private static final long serialVersionUID = -612114643488955218L;
    private transient int maxSize;
    private boolean scanUntilRemovable;

    public LRUMap() {
        this(100, 0.75f, false);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void addMapping(int i, int i2, K k, V v) {
        if (isFull()) {
            AbstractLinkedMap.LinkEntry<K, V> linkEntry = this.header.after;
            boolean z = false;
            if (this.scanUntilRemovable) {
                while (true) {
                    if (linkEntry == this.header || linkEntry == null) {
                        break;
                    } else if (removeLRU(linkEntry)) {
                        z = true;
                        break;
                    } else {
                        linkEntry = linkEntry.after;
                    }
                }
                if (linkEntry == null) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Entry.after=null, header.after");
                    outline107.append(this.header.after);
                    outline107.append(" header.before");
                    outline107.append(this.header.before);
                    outline107.append(" key=");
                    outline107.append(k);
                    outline107.append(" value=");
                    outline107.append(v);
                    outline107.append(" size=");
                    outline107.append(this.size);
                    outline107.append(" maxSize=");
                    outline107.append(this.maxSize);
                    outline107.append(" Please check that your keys are immutable, and that you have used synchronization properly.");
                    outline107.append(" If so, then please report this to dev@commons.apache.org as a bug.");
                    throw new IllegalStateException(outline107.toString());
                }
            } else {
                z = removeLRU(linkEntry);
            }
            boolean z2 = z;
            AbstractLinkedMap.LinkEntry<K, V> linkEntry2 = linkEntry;
            if (!z2) {
                super.addMapping(i, i2, k, v);
                return;
            } else if (linkEntry2 != null) {
                reuseMapping(linkEntry2, i, i2, k, v);
                return;
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("reuse=null, header.after=");
                outline1072.append(this.header.after);
                outline1072.append(" header.before");
                outline1072.append(this.header.before);
                outline1072.append(" key=");
                outline1072.append(k);
                outline1072.append(" value=");
                outline1072.append(v);
                outline1072.append(" size=");
                outline1072.append(this.size);
                outline1072.append(" maxSize=");
                outline1072.append(this.maxSize);
                outline1072.append(" Please check that your keys are immutable, and that you have used synchronization properly.");
                outline1072.append(" If so, then please report this to dev@commons.apache.org as a bug.");
                throw new IllegalStateException(outline1072.toString());
            }
        }
        super.addMapping(i, i2, k, v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.maxSize = objectInputStream.readInt();
        super.doReadObject(objectInputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.maxSize);
        super.doWriteObject(objectOutputStream);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        return get(obj, true);
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public boolean isFull() {
        return this.size >= this.maxSize;
    }

    public boolean isScanUntilRemovable() {
        return this.scanUntilRemovable;
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public int maxSize() {
        return this.maxSize;
    }

    protected void moveToMRU(AbstractLinkedMap.LinkEntry<K, V> linkEntry) {
        AbstractLinkedMap.LinkEntry<K, V> linkEntry2 = linkEntry.after;
        AbstractLinkedMap.LinkEntry<K, V> linkEntry3 = this.header;
        if (linkEntry2 == linkEntry3) {
            if (linkEntry == linkEntry3) {
                throw new IllegalStateException("Can't move header to MRU (please report this to dev@commons.apache.org)");
            }
            return;
        }
        this.modCount++;
        AbstractLinkedMap.LinkEntry<K, V> linkEntry4 = linkEntry.before;
        if (linkEntry4 != null) {
            linkEntry4.after = linkEntry2;
            linkEntry.after.before = linkEntry4;
            linkEntry.after = linkEntry3;
            linkEntry.before = linkEntry3.before;
            linkEntry3.before.after = linkEntry;
            linkEntry3.before = linkEntry;
            return;
        }
        throw new IllegalStateException("Entry.before is null. Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug.");
    }

    protected boolean removeLRU(AbstractLinkedMap.LinkEntry<K, V> linkEntry) {
        return true;
    }

    protected void reuseMapping(AbstractLinkedMap.LinkEntry<K, V> linkEntry, int i, int i2, K k, V v) {
        AbstractHashedMap.HashEntry<K, V> hashEntry;
        boolean z = true;
        try {
            int hashIndex = hashIndex(linkEntry.hashCode, this.data.length);
            AbstractHashedMap.HashEntry<K, V> hashEntry2 = this.data[hashIndex];
            AbstractHashedMap.HashEntry<K, V> hashEntry3 = null;
            while (true) {
                hashEntry = hashEntry3;
                hashEntry3 = hashEntry2;
                if (hashEntry3 == linkEntry || hashEntry3 == null) {
                    break;
                }
                hashEntry2 = hashEntry3.next;
            }
            if (hashEntry3 != null) {
                this.modCount++;
                removeEntry(linkEntry, hashIndex, hashEntry);
                reuseEntry(linkEntry, i, i2, k, v);
                addEntry(linkEntry, i);
                return;
            }
            throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + this.data[hashIndex] + " previous=" + hashEntry + " key=" + k + " value=" + v + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug.");
        } catch (NullPointerException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("NPE, entry=");
            sb.append(linkEntry);
            sb.append(" entryIsHeader=");
            if (linkEntry != this.header) {
                z = false;
            }
            sb.append(z);
            sb.append(" key=");
            sb.append(k);
            sb.append(" value=");
            sb.append(v);
            sb.append(" size=");
            sb.append(this.size);
            sb.append(" maxSize=");
            sb.append(this.maxSize);
            sb.append(" Please check that your keys are immutable, and that you have used synchronization properly.");
            sb.append(" If so, then please report this to dev@commons.apache.org as a bug.");
            throw new IllegalStateException(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void updateEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, V v) {
        moveToMRU((AbstractLinkedMap.LinkEntry) hashEntry);
        hashEntry.setValue(v);
    }

    public LRUMap(int i) {
        this(i, 0.75f);
    }

    public V get(Object obj, boolean z) {
        AbstractLinkedMap.LinkEntry<K, V> mo12703getEntry = mo12703getEntry(obj);
        if (mo12703getEntry == null) {
            return null;
        }
        if (z) {
            moveToMRU(mo12703getEntry);
        }
        return mo12703getEntry.mo12678getValue();
    }

    public LRUMap(int i, int i2) {
        this(i, i2, 0.75f);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap
    /* renamed from: clone */
    public LRUMap<K, V> mo12718clone() {
        return (LRUMap) super.mo12718clone();
    }

    public LRUMap(int i, boolean z) {
        this(i, 0.75f, z);
    }

    public LRUMap(int i, float f) {
        this(i, f, false);
    }

    public LRUMap(int i, int i2, float f) {
        this(i, i2, f, false);
    }

    public LRUMap(int i, float f, boolean z) {
        this(i, i, f, z);
    }

    public LRUMap(int i, int i2, float f, boolean z) {
        super(i2, f);
        if (i >= 1) {
            if (i2 <= i) {
                this.maxSize = i;
                this.scanUntilRemovable = z;
                return;
            }
            throw new IllegalArgumentException("LRUMap initial size must not be greather than max size");
        }
        throw new IllegalArgumentException("LRUMap max size must be greater than 0");
    }

    public LRUMap(Map<? extends K, ? extends V> map) {
        this((Map) map, false);
    }

    public LRUMap(Map<? extends K, ? extends V> map, boolean z) {
        this(map.size(), 0.75f, z);
        putAll(map);
    }
}
