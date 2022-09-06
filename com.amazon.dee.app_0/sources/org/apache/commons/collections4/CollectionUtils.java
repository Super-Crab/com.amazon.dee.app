package org.apache.commons.collections4;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableBoundedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.PermutationIterator;
/* loaded from: classes4.dex */
public class CollectionUtils {
    public static final Collection EMPTY_COLLECTION = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class CardinalityHelper<O> {
        final Map<O, Integer> cardinalityA;
        final Map<O, Integer> cardinalityB;

        public CardinalityHelper(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
            this.cardinalityA = CollectionUtils.getCardinalityMap(iterable);
            this.cardinalityB = CollectionUtils.getCardinalityMap(iterable2);
        }

        private int getFreq(Object obj, Map<?, Integer> map) {
            Integer num = map.get(obj);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }

        public int freqA(Object obj) {
            return getFreq(obj, this.cardinalityA);
        }

        public int freqB(Object obj) {
            return getFreq(obj, this.cardinalityB);
        }

        public final int max(Object obj) {
            return Math.max(freqA(obj), freqB(obj));
        }

        public final int min(Object obj) {
            return Math.min(freqA(obj), freqB(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class EquatorWrapper<O> {
        private final Equator<? super O> equator;
        private final O object;

        public EquatorWrapper(Equator<? super O> equator, O o) {
            this.equator = equator;
            this.object = o;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EquatorWrapper)) {
                return false;
            }
            return this.equator.equate((O) this.object, (Object) ((EquatorWrapper) obj).getObject());
        }

        public O getObject() {
            return this.object;
        }

        public int hashCode() {
            return this.equator.hash((O) this.object);
        }
    }

    /* loaded from: classes4.dex */
    private static class SetOperationCardinalityHelper<O> extends CardinalityHelper<O> implements Iterable<O> {
        private final Set<O> elements;
        private final List<O> newList;

        public SetOperationCardinalityHelper(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
            super(iterable, iterable2);
            this.elements = new HashSet();
            CollectionUtils.addAll(this.elements, iterable);
            CollectionUtils.addAll(this.elements, iterable2);
            this.newList = new ArrayList(this.elements.size());
        }

        @Override // java.lang.Iterable
        public Iterator<O> iterator() {
            return this.elements.iterator();
        }

        public Collection<O> list() {
            return this.newList;
        }

        public void setCardinality(O o, int i) {
            for (int i2 = 0; i2 < i; i2++) {
                this.newList.add(o);
            }
        }
    }

    private CollectionUtils() {
    }

    public static <C> boolean addAll(Collection<C> collection, Iterable<? extends C> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        return addAll(collection, iterable.iterator());
    }

    public static <T> boolean addIgnoreNull(Collection<T> collection, T t) {
        if (collection != null) {
            return t != null && collection.add(t);
        }
        throw new NullPointerException("The collection must not be null");
    }

    @Deprecated
    public static <O> int cardinality(O o, Iterable<? super O> iterable) {
        if (iterable != null) {
            return IterableUtils.frequency(iterable, o);
        }
        throw new NullPointerException("coll must not be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkIndexBounds(int i) {
        if (i >= 0) {
            return;
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("Index cannot be negative: ", i));
    }

    public static <O extends Comparable<? super O>> List<O> collate(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
        return collate(iterable, iterable2, ComparatorUtils.naturalComparator(), true);
    }

    public static <I, O> Collection<O> collect(Iterable<I> iterable, Transformer<? super I, ? extends O> transformer) {
        return collect(iterable, transformer, iterable instanceof Collection ? new ArrayList(((Collection) iterable).size()) : new ArrayList());
    }

    public static boolean containsAll(Collection<?> collection, Collection<?> collection2) {
        boolean z;
        if (collection2.isEmpty()) {
            return true;
        }
        HashSet hashSet = new HashSet();
        for (Object obj : collection2) {
            if (!hashSet.contains(obj)) {
                for (Object obj2 : collection) {
                    hashSet.add(obj2);
                    if (obj == null) {
                        if (obj2 == null) {
                            z = true;
                            break;
                        }
                    } else if (obj.equals(obj2)) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z) {
                    return false;
                }
            }
        }
        return true;
    }

    public static <T> boolean containsAny(Collection<?> collection, T... tArr) {
        if (collection.size() < tArr.length) {
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (ArrayUtils.contains(tArr, it2.next())) {
                    return true;
                }
            }
        } else {
            for (T t : tArr) {
                if (collection.contains(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Deprecated
    public static <C> int countMatches(Iterable<C> iterable, Predicate<? super C> predicate) {
        if (predicate == null) {
            return 0;
        }
        return (int) IterableUtils.countMatches(iterable, predicate);
    }

    public static <O> Collection<O> disjunction(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
        SetOperationCardinalityHelper setOperationCardinalityHelper = new SetOperationCardinalityHelper(iterable, iterable2);
        Iterator<O> it2 = setOperationCardinalityHelper.iterator();
        while (it2.hasNext()) {
            O next = it2.next();
            setOperationCardinalityHelper.setCardinality(next, setOperationCardinalityHelper.max(next) - setOperationCardinalityHelper.min(next));
        }
        return setOperationCardinalityHelper.list();
    }

    public static <T> Collection<T> emptyCollection() {
        return EMPTY_COLLECTION;
    }

    public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
        return collection == null ? emptyCollection() : collection;
    }

    @Deprecated
    public static <C> boolean exists(Iterable<C> iterable, Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAny(iterable, predicate);
    }

    public static <E> E extractSingleton(Collection<E> collection) {
        if (collection != null) {
            if (collection.size() == 1) {
                return collection.iterator().next();
            }
            throw new IllegalArgumentException("Can extract singleton only when collection size == 1");
        }
        throw new NullPointerException("Collection must not be null.");
    }

    public static <T> boolean filter(Iterable<T> iterable, Predicate<? super T> predicate) {
        boolean z = false;
        if (iterable != null && predicate != null) {
            Iterator<T> it2 = iterable.iterator();
            while (it2.hasNext()) {
                if (!predicate.evaluate(it2.next())) {
                    it2.remove();
                    z = true;
                }
            }
        }
        return z;
    }

    public static <T> boolean filterInverse(Iterable<T> iterable, Predicate<? super T> predicate) {
        return filter(iterable, predicate == null ? null : PredicateUtils.notPredicate(predicate));
    }

    @Deprecated
    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (predicate != null) {
            return (T) IterableUtils.find(iterable, predicate);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(Iterable<T> iterable, C c) {
        if (c != null) {
            return (T) IterableUtils.forEachButLast(iterable, c);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(Iterable<T> iterable, C c) {
        if (c != null) {
            IterableUtils.forEach(iterable, c);
        }
        return c;
    }

    @Deprecated
    public static <T> T get(Iterator<T> it2, int i) {
        return (T) IteratorUtils.get(it2, i);
    }

    public static <O> Map<O, Integer> getCardinalityMap(Iterable<? extends O> iterable) {
        HashMap hashMap = new HashMap();
        for (O o : iterable) {
            Integer num = (Integer) hashMap.get(o);
            if (num == null) {
                hashMap.put(o, 1);
            } else {
                hashMap.put(o, Integer.valueOf(num.intValue() + 1));
            }
        }
        return hashMap;
    }

    public static <O> Collection<O> intersection(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
        SetOperationCardinalityHelper setOperationCardinalityHelper = new SetOperationCardinalityHelper(iterable, iterable2);
        Iterator<O> it2 = setOperationCardinalityHelper.iterator();
        while (it2.hasNext()) {
            O next = it2.next();
            setOperationCardinalityHelper.setCardinality(next, setOperationCardinalityHelper.min(next));
        }
        return setOperationCardinalityHelper.list();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEqualCollection(Collection<?> collection, Collection<?> collection2) {
        if (collection.size() != collection2.size()) {
            return false;
        }
        CardinalityHelper cardinalityHelper = new CardinalityHelper(collection, collection2);
        if (cardinalityHelper.cardinalityA.size() != cardinalityHelper.cardinalityB.size()) {
            return false;
        }
        for (Object obj : cardinalityHelper.cardinalityA.keySet()) {
            if (cardinalityHelper.freqA(obj) != cardinalityHelper.freqB(obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFull(Collection<? extends Object> collection) {
        if (collection != null) {
            if (collection instanceof BoundedCollection) {
                return ((BoundedCollection) collection).isFull();
            }
            try {
                return UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection).isFull();
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        throw new NullPointerException("The collection must not be null");
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isProperSubCollection(Collection<?> collection, Collection<?> collection2) {
        return collection.size() < collection2.size() && isSubCollection(collection, collection2);
    }

    public static boolean isSubCollection(Collection<?> collection, Collection<?> collection2) {
        CardinalityHelper cardinalityHelper = new CardinalityHelper(collection, collection2);
        for (Object obj : collection) {
            if (cardinalityHelper.freqA(obj) > cardinalityHelper.freqB(obj)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static <C> boolean matchesAll(Iterable<C> iterable, Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAll(iterable, predicate);
    }

    public static int maxSize(Collection<? extends Object> collection) {
        if (collection != null) {
            if (collection instanceof BoundedCollection) {
                return ((BoundedCollection) collection).maxSize();
            }
            try {
                return UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection).maxSize();
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }
        throw new NullPointerException("The collection must not be null");
    }

    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        PermutationIterator permutationIterator = new PermutationIterator(collection);
        ArrayList arrayList = new ArrayList();
        while (permutationIterator.hasNext()) {
            arrayList.add(permutationIterator.next());
        }
        return arrayList;
    }

    public static <C> Collection<C> predicatedCollection(Collection<C> collection, Predicate<? super C> predicate) {
        return PredicatedCollection.predicatedCollection(collection, predicate);
    }

    public static <E> Collection<E> removeAll(Collection<E> collection, Collection<?> collection2) {
        return ListUtils.removeAll(collection, collection2);
    }

    public static <C> Collection<C> retainAll(Collection<C> collection, Collection<?> collection2) {
        return ListUtils.retainAll(collection, collection2);
    }

    public static void reverseArray(Object[] objArr) {
        int length = objArr.length - 1;
        for (int i = 0; length > i; i++) {
            Object obj = objArr[length];
            objArr[length] = objArr[i];
            objArr[i] = obj;
            length--;
        }
    }

    public static <O> Collection<O> select(Iterable<? extends O> iterable, Predicate<? super O> predicate) {
        return select(iterable, predicate, iterable instanceof Collection ? new ArrayList(((Collection) iterable).size()) : new ArrayList());
    }

    public static <O> Collection<O> selectRejected(Iterable<? extends O> iterable, Predicate<? super O> predicate) {
        return selectRejected(iterable, predicate, iterable instanceof Collection ? new ArrayList(((Collection) iterable).size()) : new ArrayList());
    }

    public static int size(Object obj) {
        int i = 0;
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Map) {
            return ((Map) obj).size();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Iterable) {
            return IterableUtils.size((Iterable) obj);
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj instanceof Iterator) {
            return IteratorUtils.size((Iterator) obj);
        }
        if (obj instanceof Enumeration) {
            Enumeration enumeration = (Enumeration) obj;
            while (enumeration.hasMoreElements()) {
                i++;
                enumeration.nextElement();
            }
            return i;
        }
        try {
            return Array.getLength(obj);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Unsupported object type: ")));
        }
    }

    public static boolean sizeIsEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Iterable) {
            return IterableUtils.isEmpty((Iterable) obj);
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        } else if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        } else {
            if (obj instanceof Enumeration) {
                return !((Enumeration) obj).hasMoreElements();
            }
            try {
                return Array.getLength(obj) == 0;
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Unsupported object type: ")));
            }
        }
    }

    public static <O> Collection<O> subtract(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
        return subtract(iterable, iterable2, TruePredicate.truePredicate());
    }

    @Deprecated
    public static <C> Collection<C> synchronizedCollection(Collection<C> collection) {
        return SynchronizedCollection.synchronizedCollection(collection);
    }

    public static <C> void transform(Collection<C> collection, Transformer<? super C, ? extends C> transformer) {
        if (collection == null || transformer == null) {
            return;
        }
        if (collection instanceof List) {
            ListIterator listIterator = ((List) collection).listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(transformer.mo12738transform((Object) listIterator.next()));
            }
            return;
        }
        Collection<? extends C> collect = collect(collection, transformer);
        collection.clear();
        collection.addAll(collect);
    }

    public static <E> Collection<E> transformingCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        return TransformedCollection.transformingCollection(collection, transformer);
    }

    public static <O> Collection<O> union(Iterable<? extends O> iterable, Iterable<? extends O> iterable2) {
        SetOperationCardinalityHelper setOperationCardinalityHelper = new SetOperationCardinalityHelper(iterable, iterable2);
        Iterator<O> it2 = setOperationCardinalityHelper.iterator();
        while (it2.hasNext()) {
            O next = it2.next();
            setOperationCardinalityHelper.setCardinality(next, setOperationCardinalityHelper.max(next));
        }
        return setOperationCardinalityHelper.list();
    }

    @Deprecated
    public static <C> Collection<C> unmodifiableCollection(Collection<? extends C> collection) {
        return UnmodifiableCollection.unmodifiableCollection(collection);
    }

    public static <O extends Comparable<? super O>> List<O> collate(Iterable<? extends O> iterable, Iterable<? extends O> iterable2, boolean z) {
        return collate(iterable, iterable2, ComparatorUtils.naturalComparator(), z);
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(Iterator<T> it2, C c) {
        if (c != null) {
            return (T) IteratorUtils.forEachButLast(it2, c);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(Iterator<T> it2, C c) {
        if (c != null) {
            IteratorUtils.forEach(it2, c);
        }
        return c;
    }

    @Deprecated
    public static <T> T get(Iterable<T> iterable, int i) {
        return (T) IterableUtils.get(iterable, i);
    }

    public static <E> Collection<E> removeAll(Iterable<E> iterable, Iterable<? extends E> iterable2, final Equator<? super E> equator) {
        Set set = (Set) collect(iterable2, new Transformer<E, EquatorWrapper<E>>() { // from class: org.apache.commons.collections4.CollectionUtils.3
            @Override // org.apache.commons.collections4.Transformer
            /* renamed from: transform */
            public /* bridge */ /* synthetic */ Object mo12738transform(Object obj) {
                return mo12738transform((AnonymousClass3) obj);
            }

            @Override // org.apache.commons.collections4.Transformer
            /* renamed from: transform  reason: collision with other method in class */
            public EquatorWrapper<E> mo12738transform(E e) {
                return new EquatorWrapper<>(Equator.this, e);
            }
        }, new HashSet());
        ArrayList arrayList = new ArrayList();
        for (E e : iterable) {
            if (!set.contains(new EquatorWrapper(equator, e))) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> retainAll(Iterable<E> iterable, Iterable<? extends E> iterable2, final Equator<? super E> equator) {
        Set set = (Set) collect(iterable2, new Transformer<E, EquatorWrapper<E>>() { // from class: org.apache.commons.collections4.CollectionUtils.2
            @Override // org.apache.commons.collections4.Transformer
            /* renamed from: transform */
            public /* bridge */ /* synthetic */ Object mo12738transform(Object obj) {
                return mo12738transform((AnonymousClass2) obj);
            }

            @Override // org.apache.commons.collections4.Transformer
            /* renamed from: transform  reason: collision with other method in class */
            public EquatorWrapper<E> mo12738transform(E e) {
                return new EquatorWrapper<>(Equator.this, e);
            }
        }, new HashSet());
        ArrayList arrayList = new ArrayList();
        for (E e : iterable) {
            if (set.contains(new EquatorWrapper(equator, e))) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <O> List<O> collate(Iterable<? extends O> iterable, Iterable<? extends O> iterable2, Comparator<? super O> comparator) {
        return collate(iterable, iterable2, comparator, true);
    }

    public static <I, O> Collection<O> collect(Iterator<I> it2, Transformer<? super I, ? extends O> transformer) {
        return collect(it2, transformer, new ArrayList());
    }

    public static Object get(Object obj, int i) {
        if (i >= 0) {
            if (obj instanceof Map) {
                return IteratorUtils.get(((Map) obj).entrySet().iterator(), i);
            }
            if (obj instanceof Object[]) {
                return ((Object[]) obj)[i];
            }
            if (obj instanceof Iterator) {
                return IteratorUtils.get((Iterator) obj, i);
            }
            if (obj instanceof Iterable) {
                return IterableUtils.get((Iterable) obj, i);
            }
            if (obj instanceof Enumeration) {
                return EnumerationUtils.get((Enumeration) obj, i);
            }
            if (obj != null) {
                try {
                    return Array.get(obj, i);
                } catch (IllegalArgumentException unused) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Unsupported object type: ")));
                }
            }
            throw new IllegalArgumentException("Unsupported object type: null");
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("Index cannot be negative: ", i));
    }

    public static <O, R extends Collection<? super O>> R select(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r) {
        if (iterable != null && predicate != null) {
            Iterator<? extends O> it2 = iterable.iterator();
            while (it2.hasNext()) {
                Object obj = (O) it2.next();
                if (predicate.evaluate(obj)) {
                    r.add(obj);
                }
            }
        }
        return r;
    }

    public static <O, R extends Collection<? super O>> R selectRejected(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r) {
        if (iterable != null && predicate != null) {
            Iterator<? extends O> it2 = iterable.iterator();
            while (it2.hasNext()) {
                Object obj = (O) it2.next();
                if (!predicate.evaluate(obj)) {
                    r.add(obj);
                }
            }
        }
        return r;
    }

    public static <O> Collection<O> subtract(Iterable<? extends O> iterable, Iterable<? extends O> iterable2, Predicate<O> predicate) {
        ArrayList arrayList = new ArrayList();
        HashBag hashBag = new HashBag();
        for (O o : iterable2) {
            if (predicate.evaluate(o)) {
                hashBag.add(o);
            }
        }
        for (O o2 : iterable) {
            if (!hashBag.remove(o2, 1)) {
                arrayList.add(o2);
            }
        }
        return arrayList;
    }

    public static <C> boolean addAll(Collection<C> collection, Iterator<? extends C> it2) {
        boolean z = false;
        while (it2.hasNext()) {
            z |= collection.add(it2.next());
        }
        return z;
    }

    public static <O> List<O> collate(Iterable<? extends O> iterable, Iterable<? extends O> iterable2, Comparator<? super O> comparator, boolean z) {
        int i;
        if (iterable == null || iterable2 == null) {
            throw new NullPointerException("The collections must not be null");
        }
        if (comparator != null) {
            if (!(iterable instanceof Collection) || !(iterable2 instanceof Collection)) {
                i = 10;
            } else {
                i = Math.max(1, ((Collection) iterable2).size() + ((Collection) iterable).size());
            }
            CollatingIterator collatingIterator = new CollatingIterator(comparator, iterable.iterator(), iterable2.iterator());
            if (z) {
                return IteratorUtils.toList(collatingIterator, i);
            }
            ArrayList arrayList = new ArrayList(i);
            Object obj = null;
            while (collatingIterator.hasNext()) {
                Object next = collatingIterator.next();
                if (obj == null || !obj.equals(next)) {
                    arrayList.add(next);
                }
                obj = next;
            }
            arrayList.trimToSize();
            return arrayList;
        }
        throw new NullPointerException("The comparator must not be null");
    }

    public static <I, O, R extends Collection<? super O>> R collect(Iterable<? extends I> iterable, Transformer<? super I, ? extends O> transformer, R r) {
        return iterable != null ? (R) collect(iterable.iterator(), transformer, r) : r;
    }

    public static <I, O, R extends Collection<? super O>> R collect(Iterator<? extends I> it2, Transformer<? super I, ? extends O> transformer, R r) {
        if (it2 != null && transformer != null) {
            while (it2.hasNext()) {
                r.add(transformer.mo12738transform((I) it2.next()));
            }
        }
        return r;
    }

    public static <C> boolean addAll(Collection<C> collection, Enumeration<? extends C> enumeration) {
        boolean z = false;
        while (enumeration.hasMoreElements()) {
            z |= collection.add(enumeration.nextElement());
        }
        return z;
    }

    public static boolean containsAny(Collection<?> collection, Collection<?> collection2) {
        if (collection.size() < collection2.size()) {
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (collection2.contains(it2.next())) {
                    return true;
                }
            }
            return false;
        }
        Iterator<?> it3 = collection2.iterator();
        while (it3.hasNext()) {
            if (collection.contains(it3.next())) {
                return true;
            }
        }
        return false;
    }

    public static <E> boolean isEqualCollection(Collection<? extends E> collection, Collection<? extends E> collection2, final Equator<? super E> equator) {
        if (equator != null) {
            if (collection.size() != collection2.size()) {
                return false;
            }
            Transformer transformer = new Transformer() { // from class: org.apache.commons.collections4.CollectionUtils.1
                @Override // org.apache.commons.collections4.Transformer
                /* renamed from: transform  reason: collision with other method in class */
                public EquatorWrapper<?> mo12738transform(Object obj) {
                    return new EquatorWrapper<>(Equator.this, obj);
                }
            };
            return isEqualCollection(collect(collection, transformer), collect(collection2, transformer));
        }
        throw new NullPointerException("Equator must not be null.");
    }

    public static <O, R extends Collection<? super O>> R select(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r, R r2) {
        if (iterable != null && predicate != null) {
            Iterator<? extends O> it2 = iterable.iterator();
            while (it2.hasNext()) {
                Object obj = (O) it2.next();
                if (predicate.evaluate(obj)) {
                    r.add(obj);
                } else {
                    r2.add(obj);
                }
            }
        }
        return r;
    }

    public static <C> boolean addAll(Collection<C> collection, C... cArr) {
        boolean z = false;
        for (C c : cArr) {
            z |= collection.add(c);
        }
        return z;
    }

    public static <K, V> Map.Entry<K, V> get(Map<K, V> map, int i) {
        checkIndexBounds(i);
        return (Map.Entry) get((Iterable<Object>) map.entrySet(), i);
    }
}
