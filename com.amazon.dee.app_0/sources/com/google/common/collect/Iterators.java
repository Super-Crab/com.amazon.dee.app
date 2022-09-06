package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public final class Iterators {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ArrayItr<T> extends AbstractIndexedListIterator<T> {
        static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr(new Object[0], 0, 0, 0);
        private final T[] array;
        private final int offset;

        ArrayItr(T[] tArr, int i, int i2, int i3) {
            super(i2, i3);
            this.array = tArr;
            this.offset = i;
        }

        @Override // com.google.common.collect.AbstractIndexedListIterator
        /* renamed from: get */
        protected T mo8026get(int i) {
            return this.array[this.offset + i];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ConcatenatedIterator<T> implements Iterator<T> {
        private Iterator<? extends T> iterator = Iterators.emptyIterator();
        @NullableDecl
        private Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;
        @NullableDecl
        private Iterator<? extends T> toRemove;
        private Iterator<? extends Iterator<? extends T>> topMetaIterator;

        ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> it2) {
            this.topMetaIterator = (Iterator) Preconditions.checkNotNull(it2);
        }

        @NullableDecl
        private Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it2 = this.topMetaIterator;
                if (it2 != null && it2.hasNext()) {
                    return this.topMetaIterator;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.metaIterators;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.topMetaIterator = this.metaIterators.removeFirst();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (!((Iterator) Preconditions.checkNotNull(this.iterator)).hasNext()) {
                this.topMetaIterator = getTopMetaIterator();
                Iterator<? extends Iterator<? extends T>> it2 = this.topMetaIterator;
                if (it2 == null) {
                    return false;
                }
                this.iterator = it2.next();
                Iterator<? extends T> it3 = this.iterator;
                if (it3 instanceof ConcatenatedIterator) {
                    ConcatenatedIterator concatenatedIterator = (ConcatenatedIterator) it3;
                    this.iterator = concatenatedIterator.iterator;
                    if (this.metaIterators == null) {
                        this.metaIterators = new ArrayDeque();
                    }
                    this.metaIterators.addFirst(this.topMetaIterator);
                    if (concatenatedIterator.metaIterators != null) {
                        while (!concatenatedIterator.metaIterators.isEmpty()) {
                            this.metaIterators.addFirst(concatenatedIterator.metaIterators.removeLast());
                        }
                    }
                    this.topMetaIterator = concatenatedIterator.topMetaIterator;
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                Iterator<? extends T> it2 = this.iterator;
                this.toRemove = it2;
                return it2.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove != null);
            this.toRemove.remove();
            this.toRemove = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum EmptyModifiableIterator implements Iterator<Object> {
        INSTANCE;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class MergingIterator<T> extends UnmodifiableIterator<T> {
        final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterable, final Comparator<? super T> comparator) {
            this.queue = new PriorityQueue(2, new Comparator<PeekingIterator<T>>(this) { // from class: com.google.common.collect.Iterators.MergingIterator.1
                @Override // java.util.Comparator
                public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                    return compare((PeekingIterator) ((PeekingIterator) obj), (PeekingIterator) ((PeekingIterator) obj2));
                }

                public int compare(PeekingIterator<T> peekingIterator, PeekingIterator<T> peekingIterator2) {
                    return comparator.compare(peekingIterator.peek(), peekingIterator2.peek());
                }
            });
            for (Iterator<? extends T> it2 : iterable) {
                if (it2.hasNext()) {
                    this.queue.add(Iterators.peekingIterator(it2));
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            PeekingIterator<T> remove = this.queue.remove();
            T next = remove.next();
            if (remove.hasNext()) {
                this.queue.add(remove);
            }
            return next;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PeekingImpl<E> implements PeekingIterator<E> {
        private boolean hasPeeked;
        private final Iterator<? extends E> iterator;
        @NullableDecl
        private E peekedElement;

        public PeekingImpl(Iterator<? extends E> it2) {
            this.iterator = (Iterator) Preconditions.checkNotNull(it2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E e = this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return e;
        }

        @Override // com.google.common.collect.PeekingIterator
        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return this.peekedElement;
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }
    }

    private Iterators() {
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it2) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(it2);
        boolean z = false;
        while (it2.hasNext()) {
            z |= collection.add(it2.next());
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static int advance(Iterator<?> it2, int i) {
        Preconditions.checkNotNull(it2);
        int i2 = 0;
        Preconditions.checkArgument(i >= 0, "numberToAdvance must be nonnegative");
        while (i2 < i && it2.hasNext()) {
            it2.next();
            i2++;
        }
        return i2;
    }

    public static <T> boolean all(Iterator<T> it2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (it2.hasNext()) {
            if (!predicate.apply(it2.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean any(Iterator<T> it2, Predicate<? super T> predicate) {
        return indexOf(it2, predicate) != -1;
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> it2) {
        Preconditions.checkNotNull(it2);
        return new Enumeration<T>() { // from class: com.google.common.collect.Iterators.11
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return it2.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // java.util.Enumeration
            public T nextElement() {
                return it2.next();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ListIterator<T> cast(Iterator<T> it2) {
        return (ListIterator) it2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNonnegative(int i) {
        if (i >= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("position (");
        sb.append(i);
        sb.append(") must not be negative");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear(Iterator<?> it2) {
        Preconditions.checkNotNull(it2);
        while (it2.hasNext()) {
            it2.next();
            it2.remove();
        }
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it2, Iterator<? extends T> it3) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        return concat(consumingForArray(it2, it3));
    }

    static <T> Iterator<T> concatNoDefensiveCopy(Iterator<? extends T>... itArr) {
        for (Iterator it2 : (Iterator[]) Preconditions.checkNotNull(itArr)) {
            Preconditions.checkNotNull(it2);
        }
        return concat(consumingForArray(itArr));
    }

    private static <T> Iterator<T> consumingForArray(final T... tArr) {
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.3
            int index = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < tArr.length;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public T next() {
                if (hasNext()) {
                    Object[] objArr = tArr;
                    int i = this.index;
                    T t = objArr[i];
                    objArr[i] = 0;
                    this.index = i + 1;
                    return t;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> it2) {
        Preconditions.checkNotNull(it2);
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.8
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it2.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                ?? next = it2.next();
                it2.remove();
                return next;
            }

            public String toString() {
                return "Iterators.consumingIterator(...)";
            }
        };
    }

    public static boolean contains(Iterator<?> it2, @NullableDecl Object obj) {
        if (obj == null) {
            while (it2.hasNext()) {
                if (it2.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it2.hasNext()) {
            if (obj.equals(it2.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> Iterator<T> cycle(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterator<T>() { // from class: com.google.common.collect.Iterators.2
            Iterator<T> iterator = Iterators.emptyModifiableIterator();

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iterator.hasNext() || iterable.iterator().hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                if (!this.iterator.hasNext()) {
                    this.iterator = iterable.iterator();
                    if (!this.iterator.hasNext()) {
                        throw new NoSuchElementException();
                    }
                }
                return this.iterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.iterator.remove();
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean elementsEqual(java.util.Iterator<?> r3, java.util.Iterator<?> r4) {
        /*
        L0:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L1d
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 != 0) goto Le
            return r1
        Le:
            java.lang.Object r0 = r3.next()
            java.lang.Object r2 = r4.next()
            boolean r0 = com.google.common.base.Objects.equal(r0, r2)
            if (r0 != 0) goto L0
            return r1
        L1d:
            boolean r3 = r4.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.elementsEqual(java.util.Iterator, java.util.Iterator):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return emptyListIterator();
    }

    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return (UnmodifiableListIterator<T>) ArrayItr.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> it2, final Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(predicate);
        return new AbstractIterator<T>() { // from class: com.google.common.collect.Iterators.5
            /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: computeNext */
            protected T mo8230computeNext() {
                while (it2.hasNext()) {
                    ?? next = it2.next();
                    if (predicate.apply(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        };
    }

    public static <T> T find(Iterator<T> it2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(predicate);
        while (it2.hasNext()) {
            T next = it2.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return forArray(tArr, 0, tArr.length, 0);
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.10
            @Override // java.util.Iterator
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                return enumeration.nextElement();
            }
        };
    }

    public static int frequency(Iterator<?> it2, @NullableDecl Object obj) {
        int i = 0;
        while (contains(it2, obj)) {
            i++;
        }
        return i;
    }

    public static <T> T get(Iterator<T> it2, int i) {
        checkNonnegative(i);
        int advance = advance(it2, i);
        if (it2.hasNext()) {
            return it2.next();
        }
        StringBuilder sb = new StringBuilder(91);
        sb.append("position (");
        sb.append(i);
        sb.append(") must be less than the number of elements that remained (");
        sb.append(advance);
        sb.append(")");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static <T> T getLast(Iterator<T> it2) {
        T next;
        do {
            next = it2.next();
        } while (it2.hasNext());
        return next;
    }

    @NullableDecl
    public static <T> T getNext(Iterator<? extends T> it2, @NullableDecl T t) {
        return it2.hasNext() ? it2.next() : t;
    }

    public static <T> T getOnlyElement(Iterator<T> it2) {
        T next = it2.next();
        if (!it2.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && it2.hasNext(); i++) {
            sb.append(", ");
            sb.append(it2.next());
        }
        if (it2.hasNext()) {
            sb.append(", ...");
        }
        sb.append(Typography.greater);
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> int indexOf(Iterator<T> it2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (it2.hasNext()) {
            if (predicate.apply(it2.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T> Iterator<T> limit(final Iterator<T> it2, final int i) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new Iterator<T>() { // from class: com.google.common.collect.Iterators.7
            private int count;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.count < i && it2.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                if (hasNext()) {
                    this.count++;
                    return it2.next();
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                it2.remove();
            }
        };
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator(iterable, comparator);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> it2, int i) {
        return partitionImpl(it2, i, true);
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> it2, int i) {
        return partitionImpl(it2, i, false);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> it2, final int i, final boolean z) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkArgument(i > 0);
        return new UnmodifiableIterator<List<T>>() { // from class: com.google.common.collect.Iterators.4
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it2.hasNext();
            }

            @Override // java.util.Iterator
            public List<T> next() {
                if (hasNext()) {
                    Object[] objArr = new Object[i];
                    int i2 = 0;
                    while (i2 < i && it2.hasNext()) {
                        objArr[i2] = it2.next();
                        i2++;
                    }
                    for (int i3 = i2; i3 < i; i3++) {
                        objArr[i3] = null;
                    }
                    List<T> unmodifiableList = Collections.unmodifiableList(Arrays.asList(objArr));
                    return (z || i2 == i) ? unmodifiableList : unmodifiableList.subList(0, i2);
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it2) {
        if (it2 instanceof PeekingImpl) {
            return (PeekingImpl) it2;
        }
        return new PeekingImpl(it2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NullableDecl
    public static <T> T pollNext(Iterator<T> it2) {
        if (it2.hasNext()) {
            T next = it2.next();
            it2.remove();
            return next;
        }
        return null;
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterator<?> it2, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterator<T> it2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean z = false;
        while (it2.hasNext()) {
            if (predicate.apply(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterator<?> it2, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it2.hasNext()) {
            if (!collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(@NullableDecl final T t) {
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.9
            boolean done;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.done;
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                if (!this.done) {
                    this.done = true;
                    return t;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static int size(Iterator<?> it2) {
        long j = 0;
        while (it2.hasNext()) {
            it2.next();
            j++;
        }
        return Ints.saturatedCast(j);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterator<? extends T> it2, Class<T> cls) {
        return (T[]) Iterables.toArray(Lists.newArrayList(it2), cls);
    }

    public static String toString(Iterator<?> it2) {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        boolean z = true;
        while (it2.hasNext()) {
            if (!z) {
                outline104.append(", ");
            }
            z = false;
            outline104.append(it2.next());
        }
        outline104.append(JsonReaderKt.END_LIST);
        return outline104.toString();
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it2, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator<F, T>(it2) { // from class: com.google.common.collect.Iterators.6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
            @Override // com.google.common.collect.TransformedIterator
            /* renamed from: transform */
            public T mo7907transform(F f) {
                return function.mo8172apply(f);
            }
        };
    }

    public static <T> Optional<T> tryFind(Iterator<T> it2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(predicate);
        while (it2.hasNext()) {
            T next = it2.next();
            if (predicate.apply(next)) {
                return Optional.of(next);
            }
        }
        return Optional.absent();
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<? extends T> it2) {
        Preconditions.checkNotNull(it2);
        if (it2 instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) it2;
        }
        return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it2.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // java.util.Iterator
            public T next() {
                return it2.next();
            }
        };
    }

    static <T> UnmodifiableListIterator<T> forArray(T[] tArr, int i, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0);
        Preconditions.checkPositionIndexes(i, i + i2, tArr.length);
        Preconditions.checkPositionIndex(i3, i2);
        if (i2 == 0) {
            return emptyListIterator();
        }
        return new ArrayItr(tArr, i, i2, i3);
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList(tArr));
    }

    @NullableDecl
    public static <T> T getLast(Iterator<? extends T> it2, @NullableDecl T t) {
        return it2.hasNext() ? (T) getLast(it2) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        Preconditions.checkNotNull(it4);
        return concat(consumingForArray(it2, it3, it4));
    }

    @GwtIncompatible
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> it2, Class<T> cls) {
        return filter(it2, Predicates.instanceOf(cls));
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> peekingIterator) {
        return (PeekingIterator) Preconditions.checkNotNull(peekingIterator);
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> unmodifiableIterator) {
        return (UnmodifiableIterator) Preconditions.checkNotNull(unmodifiableIterator);
    }

    @NullableDecl
    public static <T> T get(Iterator<? extends T> it2, int i, @NullableDecl T t) {
        checkNonnegative(i);
        advance(it2, i);
        return (T) getNext(it2, t);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @NullableDecl
    public static <T> T find(Iterator<? extends T> it2, Predicate<? super T> predicate, @NullableDecl T t) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(predicate);
        while (it2.hasNext()) {
            T next = it2.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        return t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4, Iterator<? extends T> it5) {
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        Preconditions.checkNotNull(it4);
        Preconditions.checkNotNull(it5);
        return concat(consumingForArray(it2, it3, it4, it5));
    }

    @NullableDecl
    public static <T> T getOnlyElement(Iterator<? extends T> it2, @NullableDecl T t) {
        return it2.hasNext() ? (T) getOnlyElement(it2) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T>... itArr) {
        return concatNoDefensiveCopy((Iterator[]) Arrays.copyOf(itArr, itArr.length));
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> it2) {
        return new ConcatenatedIterator(it2);
    }
}
