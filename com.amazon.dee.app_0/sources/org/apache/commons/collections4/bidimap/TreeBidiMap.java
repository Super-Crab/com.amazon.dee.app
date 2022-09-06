package org.apache.commons.collections4.bidimap;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class TreeBidiMap<K extends Comparable<K>, V extends Comparable<V>> implements OrderedBidiMap<K, V>, Serializable {
    private static final long serialVersionUID = 721969328361807L;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient TreeBidiMap<K, V>.Inverse inverse;
    private transient Set<K> keySet;
    private transient int modifications;
    private transient int nodeCount;
    private transient Node<K, V>[] rootNode;
    private transient Set<V> valuesSet;

    /* renamed from: org.apache.commons.collections4.bidimap.TreeBidiMap$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement = new int[DataElement.values().length];

        static {
            try {
                $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement[DataElement.KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement[DataElement.VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum DataElement {
        KEY("key"),
        VALUE("value");
        
        private final String description;

        DataElement(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class EntryView extends TreeBidiMap<K, V>.View<Map.Entry<K, V>> {
        EntryView() {
            super(DataElement.KEY);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node lookupKey = TreeBidiMap.this.lookupKey(entry.getKey());
            return lookupKey != null && lookupKey.mo12678getValue().equals(value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new ViewMapEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node lookupKey = TreeBidiMap.this.lookupKey(entry.getKey());
            if (lookupKey == null || !lookupKey.mo12678getValue().equals(value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(lookupKey);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class Inverse implements OrderedBidiMap<V, K> {
        private Set<Map.Entry<V, K>> inverseEntrySet;
        private Set<V> inverseKeySet;
        private Set<K> inverseValuesSet;

        Inverse() {
        }

        @Override // java.util.Map, org.apache.commons.collections4.Put
        public void clear() {
            TreeBidiMap.this.clear();
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean containsKey(Object obj) {
            return TreeBidiMap.this.containsValue(obj);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean containsValue(Object obj) {
            return TreeBidiMap.this.containsKey(obj);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public Set<Map.Entry<V, K>> entrySet() {
            if (this.inverseEntrySet == null) {
                this.inverseEntrySet = new InverseEntryView();
            }
            return this.inverseEntrySet;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return TreeBidiMap.this.doEquals(obj, DataElement.VALUE);
        }

        @Override // java.util.Map
        public int hashCode() {
            return TreeBidiMap.this.doHashCode(DataElement.VALUE);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean isEmpty() {
            return TreeBidiMap.this.isEmpty();
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public Set<V> keySet() {
            if (this.inverseKeySet == null) {
                this.inverseKeySet = new ValueView(DataElement.VALUE);
            }
            return this.inverseKeySet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.OrderedMap
        public /* bridge */ /* synthetic */ Object nextKey(Object obj) {
            return nextKey((Inverse) ((Comparable) obj));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.OrderedMap
        public /* bridge */ /* synthetic */ Object previousKey(Object obj) {
            return previousKey((Inverse) ((Comparable) obj));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put((Inverse) ((Comparable) obj), (Comparable) obj2);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Put
        public void putAll(Map<? extends V, ? extends K> map) {
            for (Map.Entry<? extends V, ? extends K> entry : map.entrySet()) {
                put((Inverse) entry.getKey(), (V) entry.getValue());
            }
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public int size() {
            return TreeBidiMap.this.size();
        }

        public String toString() {
            return TreeBidiMap.this.doToString(DataElement.VALUE);
        }

        @Override // org.apache.commons.collections4.OrderedMap
        /* renamed from: firstKey */
        public V mo12662firstKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                TreeBidiMap treeBidiMap = TreeBidiMap.this;
                return (V) treeBidiMap.leastNode(treeBidiMap.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).mo12678getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        /* renamed from: get */
        public K mo12663get(Object obj) {
            return (K) TreeBidiMap.this.mo12664getKey(obj);
        }

        @Override // org.apache.commons.collections4.BidiMap
        /* renamed from: getKey */
        public V mo12664getKey(Object obj) {
            return (V) TreeBidiMap.this.mo12663get(obj);
        }

        @Override // org.apache.commons.collections4.OrderedBidiMap, org.apache.commons.collections4.BidiMap
        /* renamed from: inverseBidiMap  reason: collision with other method in class */
        public OrderedBidiMap<K, V> mo12689inverseBidiMap() {
            return TreeBidiMap.this;
        }

        @Override // org.apache.commons.collections4.OrderedMap
        /* renamed from: lastKey */
        public V mo12666lastKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                TreeBidiMap treeBidiMap = TreeBidiMap.this;
                return (V) treeBidiMap.greatestNode(treeBidiMap.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).mo12678getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        @Override // org.apache.commons.collections4.IterableGet
        /* renamed from: mapIterator  reason: collision with other method in class */
        public OrderedMapIterator<V, K> mo12767mapIterator() {
            if (isEmpty()) {
                return EmptyOrderedMapIterator.emptyOrderedMapIterator();
            }
            return new InverseViewMapIterator(DataElement.VALUE);
        }

        public V nextKey(V v) {
            TreeBidiMap.checkKey(v);
            TreeBidiMap treeBidiMap = TreeBidiMap.this;
            Node nextGreater = treeBidiMap.nextGreater(treeBidiMap.lookup(v, DataElement.VALUE), DataElement.VALUE);
            if (nextGreater == null) {
                return null;
            }
            return (V) nextGreater.mo12678getValue();
        }

        public V previousKey(V v) {
            TreeBidiMap.checkKey(v);
            TreeBidiMap treeBidiMap = TreeBidiMap.this;
            Node nextSmaller = treeBidiMap.nextSmaller(treeBidiMap.lookup(v, DataElement.VALUE), DataElement.VALUE);
            if (nextSmaller == null) {
                return null;
            }
            return (V) nextSmaller.mo12678getValue();
        }

        public K put(V v, K k) {
            K k2 = (K) mo12663get((Object) v);
            TreeBidiMap.this.doPut(k, v);
            return k2;
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        /* renamed from: remove */
        public K mo12668remove(Object obj) {
            return (K) TreeBidiMap.this.mo12669removeValue(obj);
        }

        @Override // org.apache.commons.collections4.BidiMap
        /* renamed from: removeValue */
        public V mo12669removeValue(Object obj) {
            return (V) TreeBidiMap.this.mo12668remove(obj);
        }

        @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
        /* renamed from: values  reason: collision with other method in class */
        public Set<K> mo12691values() {
            if (this.inverseValuesSet == null) {
                this.inverseValuesSet = new KeyView(DataElement.VALUE);
            }
            return this.inverseValuesSet;
        }
    }

    /* loaded from: classes4.dex */
    class InverseEntryView extends TreeBidiMap<K, V>.View<Map.Entry<V, K>> {
        InverseEntryView() {
            super(DataElement.VALUE);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node lookupValue = TreeBidiMap.this.lookupValue(entry.getKey());
            return lookupValue != null && lookupValue.mo12677getKey().equals(value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<V, K>> iterator() {
            return new InverseViewMapEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node lookupValue = TreeBidiMap.this.lookupValue(entry.getKey());
            if (lookupValue == null || !lookupValue.mo12677getKey().equals(value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(lookupValue);
            return true;
        }
    }

    /* loaded from: classes4.dex */
    class InverseViewMapEntryIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedIterator<Map.Entry<V, K>> {
        InverseViewMapEntryIterator() {
            super(DataElement.VALUE);
        }

        private Map.Entry<V, K> createEntry(Node<K, V> node) {
            return new UnmodifiableMapEntry(node.mo12678getValue(), node.mo12677getKey());
        }

        @Override // java.util.Iterator
        public Map.Entry<V, K> next() {
            return createEntry(navigateNext());
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous  reason: collision with other method in class */
        public Map.Entry<V, K> mo12706previous() {
            return createEntry(navigatePrevious());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class InverseViewMapIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedMapIterator<V, K> {
        public InverseViewMapIterator(DataElement dataElement) {
            super(dataElement);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.MapIterator
        public /* bridge */ /* synthetic */ Object setValue(Object obj) {
            return setValue((InverseViewMapIterator) ((Comparable) obj));
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public V mo12681getKey() {
            Node<K, V> node = this.lastReturnedNode;
            if (node != null) {
                return node.mo12678getValue();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public K mo12682getValue() {
            Node<K, V> node = this.lastReturnedNode;
            if (node != null) {
                return node.mo12677getKey();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public V mo12683next() {
            return navigateNext().mo12678getValue();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public V mo12706previous() {
            return navigatePrevious().mo12678getValue();
        }

        public K setValue(K k) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class KeyView extends TreeBidiMap<K, V>.View<K> {
        public KeyView(DataElement dataElement) {
            super(dataElement);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.KEY);
            return TreeBidiMap.this.lookupKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new ViewMapIterator(this.orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return TreeBidiMap.this.doRemoveKey(obj) != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Node<K extends Comparable<K>, V extends Comparable<V>> implements Map.Entry<K, V>, KeyValue<K, V> {
        private int hashcodeValue;
        private final K key;
        private final V value;
        private final Node<K, V>[] leftNode = new Node[2];
        private final Node<K, V>[] rightNode = new Node[2];
        private final Node<K, V>[] parentNode = new Node[2];
        private final boolean[] blackColor = {true, true};
        private boolean calculatedHashCode = false;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void copyColor(Node<K, V> node, DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getData(DataElement dataElement) {
            int ordinal = dataElement.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return mo12678getValue();
                }
                throw new IllegalArgumentException();
            }
            return mo12677getKey();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getLeft(DataElement dataElement) {
            return this.leftNode[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getParent(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getRight(DataElement dataElement) {
            return this.rightNode[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isBlack(DataElement dataElement) {
            return this.blackColor[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isLeftChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRed(DataElement dataElement) {
            return !this.blackColor[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRightChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlack(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLeft(Node<K, V> node, DataElement dataElement) {
            this.leftNode[dataElement.ordinal()] = node;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParent(Node<K, V> node, DataElement dataElement) {
            this.parentNode[dataElement.ordinal()] = node;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRed(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRight(Node<K, V> node, DataElement dataElement) {
            this.rightNode[dataElement.ordinal()] = node;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void swapColors(Node<K, V> node, DataElement dataElement) {
            boolean[] zArr = this.blackColor;
            int ordinal = dataElement.ordinal();
            zArr[ordinal] = zArr[ordinal] ^ node.blackColor[dataElement.ordinal()];
            boolean[] zArr2 = node.blackColor;
            int ordinal2 = dataElement.ordinal();
            zArr2[ordinal2] = zArr2[ordinal2] ^ this.blackColor[dataElement.ordinal()];
            boolean[] zArr3 = this.blackColor;
            int ordinal3 = dataElement.ordinal();
            zArr3[ordinal3] = node.blackColor[dataElement.ordinal()] ^ zArr3[ordinal3];
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return mo12677getKey().equals(entry.getKey()) && mo12678getValue().equals(entry.getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.calculatedHashCode) {
                this.hashcodeValue = mo12677getKey().hashCode() ^ mo12678getValue().hashCode();
                this.calculatedHashCode = true;
            }
            return this.hashcodeValue;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map.Entry
        public /* bridge */ /* synthetic */ Object setValue(Object obj) {
            return setValue((Node<K, V>) ((Comparable) obj));
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getKey */
        public K mo12677getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getValue */
        public V mo12678getValue() {
            return this.value;
        }

        public V setValue(V v) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ValueView extends TreeBidiMap<K, V>.View<V> {
        public ValueView(DataElement dataElement) {
            super(dataElement);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.VALUE);
            return TreeBidiMap.this.lookupValue(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new InverseViewMapIterator(this.orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return TreeBidiMap.this.doRemoveValue(obj) != null;
        }
    }

    /* loaded from: classes4.dex */
    abstract class View<E> extends AbstractSet<E> {
        final DataElement orderType;

        View(DataElement dataElement) {
            this.orderType = dataElement;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TreeBidiMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return TreeBidiMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public abstract class ViewIterator {
        private int expectedModifications;
        private Node<K, V> nextNode;
        private final DataElement orderType;
        Node<K, V> lastReturnedNode = null;
        private Node<K, V> previousNode = null;

        ViewIterator(DataElement dataElement) {
            this.orderType = dataElement;
            this.expectedModifications = TreeBidiMap.this.modifications;
            this.nextNode = TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[dataElement.ordinal()], dataElement);
        }

        public final boolean hasNext() {
            return this.nextNode != null;
        }

        public boolean hasPrevious() {
            return this.previousNode != null;
        }

        protected Node<K, V> navigateNext() {
            if (this.nextNode != null) {
                if (TreeBidiMap.this.modifications == this.expectedModifications) {
                    Node<K, V> node = this.nextNode;
                    this.lastReturnedNode = node;
                    this.previousNode = node;
                    this.nextNode = TreeBidiMap.this.nextGreater(node, this.orderType);
                    return this.lastReturnedNode;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        protected Node<K, V> navigatePrevious() {
            if (this.previousNode != null) {
                if (TreeBidiMap.this.modifications == this.expectedModifications) {
                    this.nextNode = this.lastReturnedNode;
                    if (this.nextNode == null) {
                        this.nextNode = TreeBidiMap.this.nextGreater(this.previousNode, this.orderType);
                    }
                    Node<K, V> node = this.previousNode;
                    this.lastReturnedNode = node;
                    this.previousNode = TreeBidiMap.this.nextSmaller(node, this.orderType);
                    return this.lastReturnedNode;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        public final void remove() {
            if (this.lastReturnedNode != null) {
                if (TreeBidiMap.this.modifications == this.expectedModifications) {
                    TreeBidiMap.this.doRedBlackDelete(this.lastReturnedNode);
                    this.expectedModifications++;
                    this.lastReturnedNode = null;
                    Node<K, V> node = this.nextNode;
                    if (node != null) {
                        this.previousNode = TreeBidiMap.this.nextSmaller(node, this.orderType);
                        return;
                    }
                    TreeBidiMap treeBidiMap = TreeBidiMap.this;
                    this.previousNode = treeBidiMap.greatestNode(treeBidiMap.rootNode[this.orderType.ordinal()], this.orderType);
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes4.dex */
    class ViewMapEntryIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedIterator<Map.Entry<K, V>> {
        ViewMapEntryIterator() {
            super(DataElement.KEY);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return navigateNext();
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous  reason: collision with other method in class */
        public Map.Entry<K, V> mo12706previous() {
            return navigatePrevious();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ViewMapIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedMapIterator<K, V> {
        ViewMapIterator(DataElement dataElement) {
            super(dataElement);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.MapIterator
        public /* bridge */ /* synthetic */ Object setValue(Object obj) {
            return setValue((ViewMapIterator) ((Comparable) obj));
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            Node<K, V> node = this.lastReturnedNode;
            if (node != null) {
                return node.mo12677getKey();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            Node<K, V> node = this.lastReturnedNode;
            if (node != null) {
                return node.mo12678getValue();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            return navigateNext().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            return navigatePrevious().mo12677getKey();
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    public TreeBidiMap() {
        this.nodeCount = 0;
        this.modifications = 0;
        this.inverse = null;
        this.rootNode = new Node[2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkKey(Object obj) {
        checkNonNullComparable(obj, DataElement.KEY);
    }

    private static void checkKeyAndValue(Object obj, Object obj2) {
        checkKey(obj);
        checkValue(obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkNonNullComparable(Object obj, DataElement dataElement) {
        if (obj != null) {
            if (obj instanceof Comparable) {
                return;
            }
            throw new ClassCastException(dataElement + " must be Comparable");
        }
        throw new NullPointerException(dataElement + " cannot be null");
    }

    private static void checkValue(Object obj) {
        checkNonNullComparable(obj, DataElement.VALUE);
    }

    private static <T extends Comparable<T>> int compare(T t, T t2) {
        return t.compareTo(t2);
    }

    private void copyColor(Node<K, V> node, Node<K, V> node2, DataElement dataElement) {
        if (node2 != null) {
            if (node == null) {
                node2.setBlack(dataElement);
            } else {
                node2.copyColor(node, dataElement);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doEquals(Object obj, DataElement dataElement) {
        MapIterator<?, ?> mapIterator;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        if (this.nodeCount > 0) {
            try {
                mapIterator = getMapIterator(dataElement);
            } catch (ClassCastException | NullPointerException unused) {
            }
            while (mapIterator.hasNext()) {
                if (!mapIterator.mo12682getValue().equals(map.get(mapIterator.mo12683next()))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doHashCode(DataElement dataElement) {
        int i = 0;
        if (this.nodeCount > 0) {
            MapIterator<?, ?> mapIterator = getMapIterator(dataElement);
            while (mapIterator.hasNext()) {
                i += mapIterator.mo12683next().hashCode() ^ mapIterator.mo12682getValue().hashCode();
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPut(K k, V v) {
        checkKeyAndValue(k, v);
        doRemoveKey(k);
        doRemoveValue(v);
        Node<K, V> node = this.rootNode[DataElement.KEY.ordinal()];
        if (node == null) {
            Node<K, V> node2 = new Node<>(k, v);
            this.rootNode[DataElement.KEY.ordinal()] = node2;
            this.rootNode[DataElement.VALUE.ordinal()] = node2;
            grow();
            return;
        }
        while (true) {
            int compare = compare(k, node.mo12677getKey());
            if (compare == 0) {
                throw new IllegalArgumentException("Cannot store a duplicate key (\"" + k + "\") in this Map");
            } else if (compare < 0) {
                if (node.getLeft(DataElement.KEY) != null) {
                    node = node.getLeft(DataElement.KEY);
                } else {
                    Node<K, V> node3 = new Node<>(k, v);
                    insertValue(node3);
                    node.setLeft(node3, DataElement.KEY);
                    node3.setParent(node, DataElement.KEY);
                    doRedBlackInsert(node3, DataElement.KEY);
                    grow();
                    return;
                }
            } else if (node.getRight(DataElement.KEY) != null) {
                node = node.getRight(DataElement.KEY);
            } else {
                Node<K, V> node4 = new Node<>(k, v);
                insertValue(node4);
                node.setRight(node4, DataElement.KEY);
                node4.setParent(node, DataElement.KEY);
                doRedBlackInsert(node4, DataElement.KEY);
                grow();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRedBlackDelete(Node<K, V> node) {
        DataElement[] values;
        for (DataElement dataElement : DataElement.values()) {
            if (node.getLeft(dataElement) != null && node.getRight(dataElement) != null) {
                swapPosition(nextGreater(node, dataElement), node, dataElement);
            }
            Node<K, V> left = node.getLeft(dataElement) != null ? node.getLeft(dataElement) : node.getRight(dataElement);
            if (left != null) {
                left.setParent(node.getParent(dataElement), dataElement);
                if (node.getParent(dataElement) != null) {
                    if (node == node.getParent(dataElement).getLeft(dataElement)) {
                        node.getParent(dataElement).setLeft(left, dataElement);
                    } else {
                        node.getParent(dataElement).setRight(left, dataElement);
                    }
                } else {
                    this.rootNode[dataElement.ordinal()] = left;
                }
                node.setLeft(null, dataElement);
                node.setRight(null, dataElement);
                node.setParent(null, dataElement);
                if (isBlack(node, dataElement)) {
                    doRedBlackDeleteFixup(left, dataElement);
                }
            } else if (node.getParent(dataElement) == null) {
                this.rootNode[dataElement.ordinal()] = null;
            } else {
                if (isBlack(node, dataElement)) {
                    doRedBlackDeleteFixup(node, dataElement);
                }
                if (node.getParent(dataElement) != null) {
                    if (node == node.getParent(dataElement).getLeft(dataElement)) {
                        node.getParent(dataElement).setLeft(null, dataElement);
                    } else {
                        node.getParent(dataElement).setRight(null, dataElement);
                    }
                    node.setParent(null, dataElement);
                }
            }
        }
        shrink();
    }

    private void doRedBlackDeleteFixup(Node<K, V> node, DataElement dataElement) {
        while (node != this.rootNode[dataElement.ordinal()] && isBlack(node, dataElement)) {
            if (node.isLeftChild(dataElement)) {
                Node<K, V> rightChild = getRightChild(getParent(node, dataElement), dataElement);
                if (isRed(rightChild, dataElement)) {
                    makeBlack(rightChild, dataElement);
                    makeRed(getParent(node, dataElement), dataElement);
                    rotateLeft(getParent(node, dataElement), dataElement);
                    rightChild = getRightChild(getParent(node, dataElement), dataElement);
                }
                if (isBlack(getLeftChild(rightChild, dataElement), dataElement) && isBlack(getRightChild(rightChild, dataElement), dataElement)) {
                    makeRed(rightChild, dataElement);
                    node = getParent(node, dataElement);
                } else {
                    if (isBlack(getRightChild(rightChild, dataElement), dataElement)) {
                        makeBlack(getLeftChild(rightChild, dataElement), dataElement);
                        makeRed(rightChild, dataElement);
                        rotateRight(rightChild, dataElement);
                        rightChild = getRightChild(getParent(node, dataElement), dataElement);
                    }
                    copyColor(getParent(node, dataElement), rightChild, dataElement);
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(getRightChild(rightChild, dataElement), dataElement);
                    rotateLeft(getParent(node, dataElement), dataElement);
                    node = this.rootNode[dataElement.ordinal()];
                }
            } else {
                Node<K, V> leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                if (isRed(leftChild, dataElement)) {
                    makeBlack(leftChild, dataElement);
                    makeRed(getParent(node, dataElement), dataElement);
                    rotateRight(getParent(node, dataElement), dataElement);
                    leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                }
                if (isBlack(getRightChild(leftChild, dataElement), dataElement) && isBlack(getLeftChild(leftChild, dataElement), dataElement)) {
                    makeRed(leftChild, dataElement);
                    node = getParent(node, dataElement);
                } else {
                    if (isBlack(getLeftChild(leftChild, dataElement), dataElement)) {
                        makeBlack(getRightChild(leftChild, dataElement), dataElement);
                        makeRed(leftChild, dataElement);
                        rotateLeft(leftChild, dataElement);
                        leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                    }
                    copyColor(getParent(node, dataElement), leftChild, dataElement);
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(getLeftChild(leftChild, dataElement), dataElement);
                    rotateRight(getParent(node, dataElement), dataElement);
                    node = this.rootNode[dataElement.ordinal()];
                }
            }
        }
        makeBlack(node, dataElement);
    }

    private void doRedBlackInsert(Node<K, V> node, DataElement dataElement) {
        makeRed(node, dataElement);
        while (node != null && node != this.rootNode[dataElement.ordinal()] && isRed(node.getParent(dataElement), dataElement)) {
            if (node.isLeftChild(dataElement)) {
                Node<K, V> rightChild = getRightChild(getGrandParent(node, dataElement), dataElement);
                if (!isRed(rightChild, dataElement)) {
                    if (node.isRightChild(dataElement)) {
                        node = getParent(node, dataElement);
                        rotateLeft(node, dataElement);
                    }
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    if (getGrandParent(node, dataElement) != null) {
                        rotateRight(getGrandParent(node, dataElement), dataElement);
                    }
                } else {
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(rightChild, dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    node = getGrandParent(node, dataElement);
                }
            } else {
                Node<K, V> leftChild = getLeftChild(getGrandParent(node, dataElement), dataElement);
                if (!isRed(leftChild, dataElement)) {
                    if (node.isLeftChild(dataElement)) {
                        node = getParent(node, dataElement);
                        rotateRight(node, dataElement);
                    }
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    if (getGrandParent(node, dataElement) != null) {
                        rotateLeft(getGrandParent(node, dataElement), dataElement);
                    }
                } else {
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(leftChild, dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    node = getGrandParent(node, dataElement);
                }
            }
        }
        makeBlack(this.rootNode[dataElement.ordinal()], dataElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V doRemoveKey(Object obj) {
        Node<K, V> lookupKey = lookupKey(obj);
        if (lookupKey == null) {
            return null;
        }
        doRedBlackDelete(lookupKey);
        return lookupKey.mo12678getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public K doRemoveValue(Object obj) {
        Node<K, V> lookupValue = lookupValue(obj);
        if (lookupValue == null) {
            return null;
        }
        doRedBlackDelete(lookupValue);
        return lookupValue.mo12677getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String doToString(DataElement dataElement) {
        int i = this.nodeCount;
        if (i == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 32);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        MapIterator<?, ?> mapIterator = getMapIterator(dataElement);
        boolean hasNext = mapIterator.hasNext();
        while (hasNext) {
            Object mo12683next = mapIterator.mo12683next();
            Object mo12682getValue = mapIterator.mo12682getValue();
            if (mo12683next == this) {
                mo12683next = "(this Map)";
            }
            sb.append(mo12683next);
            sb.append(Chars.EQ);
            if (mo12682getValue == this) {
                mo12682getValue = "(this Map)";
            }
            sb.append(mo12682getValue);
            hasNext = mapIterator.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    private Node<K, V> getGrandParent(Node<K, V> node, DataElement dataElement) {
        return getParent(getParent(node, dataElement), dataElement);
    }

    private Node<K, V> getLeftChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getLeft(dataElement);
    }

    private MapIterator<?, ?> getMapIterator(DataElement dataElement) {
        int ordinal = dataElement.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new InverseViewMapIterator(DataElement.VALUE);
            }
            throw new IllegalArgumentException();
        }
        return new ViewMapIterator(DataElement.KEY);
    }

    private Node<K, V> getParent(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getParent(dataElement);
    }

    private Node<K, V> getRightChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getRight(dataElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> greatestNode(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            while (node.getRight(dataElement) != null) {
                node = node.getRight(dataElement);
            }
        }
        return node;
    }

    private void grow() {
        modify();
        this.nodeCount++;
    }

    private void insertValue(Node<K, V> node) throws IllegalArgumentException {
        Node<K, V> node2 = this.rootNode[DataElement.VALUE.ordinal()];
        while (true) {
            int compare = compare(node.mo12678getValue(), node2.mo12678getValue());
            if (compare != 0) {
                if (compare < 0) {
                    if (node2.getLeft(DataElement.VALUE) != null) {
                        node2 = node2.getLeft(DataElement.VALUE);
                    } else {
                        node2.setLeft(node, DataElement.VALUE);
                        node.setParent(node2, DataElement.VALUE);
                        doRedBlackInsert(node, DataElement.VALUE);
                        return;
                    }
                } else if (node2.getRight(DataElement.VALUE) != null) {
                    node2 = node2.getRight(DataElement.VALUE);
                } else {
                    node2.setRight(node, DataElement.VALUE);
                    node.setParent(node2, DataElement.VALUE);
                    doRedBlackInsert(node, DataElement.VALUE);
                    return;
                }
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline88(GeneratedOutlineSupport1.outline107("Cannot store a duplicate value (\""), node.getData(DataElement.VALUE), "\") in this Map"));
            }
        }
    }

    private static boolean isBlack(Node<?, ?> node, DataElement dataElement) {
        return node == null || node.isBlack(dataElement);
    }

    private static boolean isRed(Node<?, ?> node, DataElement dataElement) {
        return node != null && node.isRed(dataElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> leastNode(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            while (node.getLeft(dataElement) != null) {
                node = node.getLeft(dataElement);
            }
        }
        return node;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends Comparable<T>> Node<K, V> lookup(Object obj, DataElement dataElement) {
        Node<K, V> node = this.rootNode[dataElement.ordinal()];
        while (node != null) {
            int compare = compare((Comparable) obj, (Comparable) node.getData(dataElement));
            if (compare == 0) {
                return node;
            }
            node = compare < 0 ? node.getLeft(dataElement) : node.getRight(dataElement);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> lookupKey(Object obj) {
        return lookup(obj, DataElement.KEY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> lookupValue(Object obj) {
        return lookup(obj, DataElement.VALUE);
    }

    private static void makeBlack(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setBlack(dataElement);
        }
    }

    private static void makeRed(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setRed(dataElement);
        }
    }

    private void modify() {
        this.modifications++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> nextGreater(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        if (node.getRight(dataElement) != null) {
            return leastNode(node.getRight(dataElement), dataElement);
        }
        Node<K, V> parent = node.getParent(dataElement);
        while (true) {
            Node<K, V> node2 = parent;
            Node<K, V> node3 = node;
            node = node2;
            if (node == null || node3 != node.getRight(dataElement)) {
                return node;
            }
            parent = node.getParent(dataElement);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> nextSmaller(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        if (node.getLeft(dataElement) != null) {
            return greatestNode(node.getLeft(dataElement), dataElement);
        }
        Node<K, V> parent = node.getParent(dataElement);
        while (true) {
            Node<K, V> node2 = parent;
            Node<K, V> node3 = node;
            node = node2;
            if (node == null || node3 != node.getLeft(dataElement)) {
                return node;
            }
            parent = node.getParent(dataElement);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.rootNode = new Node[2];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put((TreeBidiMap<K, V>) ((Comparable) objectInputStream.readObject()), (Comparable) objectInputStream.readObject());
        }
    }

    private void rotateLeft(Node<K, V> node, DataElement dataElement) {
        Node<K, V> right = node.getRight(dataElement);
        node.setRight(right.getLeft(dataElement), dataElement);
        if (right.getLeft(dataElement) != null) {
            right.getLeft(dataElement).setParent(node, dataElement);
        }
        right.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) != null) {
            if (node.getParent(dataElement).getLeft(dataElement) == node) {
                node.getParent(dataElement).setLeft(right, dataElement);
            } else {
                node.getParent(dataElement).setRight(right, dataElement);
            }
        } else {
            this.rootNode[dataElement.ordinal()] = right;
        }
        right.setLeft(node, dataElement);
        node.setParent(right, dataElement);
    }

    private void rotateRight(Node<K, V> node, DataElement dataElement) {
        Node<K, V> left = node.getLeft(dataElement);
        node.setLeft(left.getRight(dataElement), dataElement);
        if (left.getRight(dataElement) != null) {
            left.getRight(dataElement).setParent(node, dataElement);
        }
        left.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) != null) {
            if (node.getParent(dataElement).getRight(dataElement) == node) {
                node.getParent(dataElement).setRight(left, dataElement);
            } else {
                node.getParent(dataElement).setLeft(left, dataElement);
            }
        } else {
            this.rootNode[dataElement.ordinal()] = left;
        }
        left.setRight(node, dataElement);
        node.setParent(left, dataElement);
    }

    private void shrink() {
        modify();
        this.nodeCount--;
    }

    private void swapPosition(Node<K, V> node, Node<K, V> node2, DataElement dataElement) {
        Node<K, V> parent = node.getParent(dataElement);
        Node left = node.getLeft(dataElement);
        Node right = node.getRight(dataElement);
        Node<K, V> parent2 = node2.getParent(dataElement);
        Node left2 = node2.getLeft(dataElement);
        Node right2 = node2.getRight(dataElement);
        boolean z = true;
        boolean z2 = node.getParent(dataElement) != null && node == node.getParent(dataElement).getLeft(dataElement);
        if (node2.getParent(dataElement) == null || node2 != node2.getParent(dataElement).getLeft(dataElement)) {
            z = false;
        }
        if (node == parent2) {
            node.setParent(node2, dataElement);
            if (z) {
                node2.setLeft(node, dataElement);
                node2.setRight(right, dataElement);
            } else {
                node2.setRight(node, dataElement);
                node2.setLeft(left, dataElement);
            }
        } else {
            node.setParent(parent2, dataElement);
            if (parent2 != null) {
                if (z) {
                    parent2.setLeft(node, dataElement);
                } else {
                    parent2.setRight(node, dataElement);
                }
            }
            node2.setLeft(left, dataElement);
            node2.setRight(right, dataElement);
        }
        if (node2 == parent) {
            node2.setParent(node, dataElement);
            if (z2) {
                node.setLeft(node2, dataElement);
                node.setRight(right2, dataElement);
            } else {
                node.setRight(node2, dataElement);
                node.setLeft(left2, dataElement);
            }
        } else {
            node2.setParent(parent, dataElement);
            if (parent != null) {
                if (z2) {
                    parent.setLeft(node2, dataElement);
                } else {
                    parent.setRight(node2, dataElement);
                }
            }
            node.setLeft(left2, dataElement);
            node.setRight(right2, dataElement);
        }
        if (node.getLeft(dataElement) != null) {
            node.getLeft(dataElement).setParent(node, dataElement);
        }
        if (node.getRight(dataElement) != null) {
            node.getRight(dataElement).setParent(node, dataElement);
        }
        if (node2.getLeft(dataElement) != null) {
            node2.getLeft(dataElement).setParent(node2, dataElement);
        }
        if (node2.getRight(dataElement) != null) {
            node2.getRight(dataElement).setParent(node2, dataElement);
        }
        node.swapColors(node2, dataElement);
        if (this.rootNode[dataElement.ordinal()] == node) {
            this.rootNode[dataElement.ordinal()] = node2;
        } else if (this.rootNode[dataElement.ordinal()] != node2) {
        } else {
            this.rootNode[dataElement.ordinal()] = node;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        modify();
        this.nodeCount = 0;
        this.rootNode[DataElement.KEY.ordinal()] = null;
        this.rootNode[DataElement.VALUE.ordinal()] = null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        checkKey(obj);
        return lookupKey(obj) != null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        checkValue(obj);
        return lookupValue(obj) != null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntryView();
        }
        return this.entrySet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return doEquals(obj, DataElement.KEY);
    }

    @Override // java.util.Map
    public int hashCode() {
        return doHashCode(DataElement.KEY);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return this.nodeCount == 0;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeyView(DataElement.KEY);
        }
        return this.keySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.OrderedMap
    public /* bridge */ /* synthetic */ Object nextKey(Object obj) {
        return nextKey((TreeBidiMap<K, V>) ((Comparable) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.OrderedMap
    public /* bridge */ /* synthetic */ Object previousKey(Object obj) {
        return previousKey((TreeBidiMap<K, V>) ((Comparable) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((TreeBidiMap<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put((TreeBidiMap<K, V>) entry.getKey(), (K) entry.getValue());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.nodeCount;
    }

    public String toString() {
        return doToString(DataElement.KEY);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        if (this.nodeCount != 0) {
            return leastNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).mo12677getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        checkKey(obj);
        Node<K, V> lookupKey = lookupKey(obj);
        if (lookupKey == null) {
            return null;
        }
        return lookupKey.mo12678getValue();
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: getKey */
    public K mo12664getKey(Object obj) {
        checkValue(obj);
        Node<K, V> lookupValue = lookupValue(obj);
        if (lookupValue == null) {
            return null;
        }
        return lookupValue.mo12677getKey();
    }

    @Override // org.apache.commons.collections4.OrderedBidiMap, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap  reason: collision with other method in class */
    public OrderedBidiMap<V, K> mo12689inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new Inverse();
        }
        return this.inverse;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        if (this.nodeCount != 0) {
            return greatestNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).mo12677getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        if (isEmpty()) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new ViewMapIterator(DataElement.KEY);
    }

    public K nextKey(K k) {
        checkKey(k);
        Node<K, V> nextGreater = nextGreater(lookupKey(k), DataElement.KEY);
        if (nextGreater == null) {
            return null;
        }
        return nextGreater.mo12677getKey();
    }

    public K previousKey(K k) {
        checkKey(k);
        Node<K, V> nextSmaller = nextSmaller(lookupKey(k), DataElement.KEY);
        if (nextSmaller == null) {
            return null;
        }
        return nextSmaller.mo12677getKey();
    }

    public V put(K k, V v) {
        V mo12663get = mo12663get((Object) k);
        doPut(k, v);
        return mo12663get;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        return doRemoveKey(obj);
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: removeValue */
    public K mo12669removeValue(Object obj) {
        return doRemoveValue(obj);
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values  reason: collision with other method in class */
    public Set<V> mo12691values() {
        if (this.valuesSet == null) {
            this.valuesSet = new ValueView(DataElement.KEY);
        }
        return this.valuesSet;
    }

    public TreeBidiMap(Map<? extends K, ? extends V> map) {
        this();
        putAll(map);
    }
}
