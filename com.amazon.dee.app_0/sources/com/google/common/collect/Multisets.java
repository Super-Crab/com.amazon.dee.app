package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public final class Multisets {

    /* loaded from: classes3.dex */
    static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                return getCount() == entry.getCount() && Objects.equal(mo7624getElement(), entry.mo7624getElement());
            }
            return false;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            E mo7624getElement = mo7624getElement();
            return (mo7624getElement == null ? 0 : mo7624getElement.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String valueOf = String.valueOf(mo7624getElement());
            int count = getCount();
            if (count == 1) {
                return valueOf;
            }
            StringBuilder sb = new StringBuilder(valueOf.length() + 14);
            sb.append(valueOf);
            sb.append(" x ");
            sb.append(count);
            return sb.toString();
        }
    }

    /* loaded from: classes3.dex */
    private static final class DecreasingCount implements Comparator<Multiset.Entry<?>> {
        static final DecreasingCount INSTANCE = new DecreasingCount();

        private DecreasingCount() {
        }

        @Override // java.util.Comparator
        public int compare(Multiset.Entry<?> entry, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry.getCount();
        }
    }

    /* loaded from: classes3.dex */
    static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            mo8035multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return mo8035multiset().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return mo8035multiset().containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return mo8035multiset().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract Iterator<E> iterator();

        /* renamed from: multiset */
        abstract Multiset<E> mo8035multiset();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return mo8035multiset().remove(obj, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return mo8035multiset().mo7764entrySet().size();
        }
    }

    /* loaded from: classes3.dex */
    static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            mo7596multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                return entry.getCount() > 0 && mo7596multiset().count(entry.mo7624getElement()) == entry.getCount();
            }
            return false;
        }

        /* renamed from: multiset */
        abstract Multiset<E> mo7596multiset();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                E e = (E) entry.mo7624getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return mo7596multiset().setCount(e, count, 0);
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FilteredMultiset<E> extends ViewMultiset<E> {
        final Predicate<? super E> predicate;
        final Multiset<E> unfiltered;

        FilteredMultiset(Multiset<E> multiset, Predicate<? super E> predicate) {
            super();
            this.unfiltered = (Multiset) Preconditions.checkNotNull(multiset);
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int add(@NullableDecl E e, int i) {
            Preconditions.checkArgument(this.predicate.apply(e), "Element %s does not match predicate %s", e, this.predicate);
            return this.unfiltered.add(e, i);
        }

        @Override // com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            int count = this.unfiltered.count(obj);
            if (count <= 0 || !this.predicate.apply(obj)) {
                return 0;
            }
            return count;
        }

        @Override // com.google.common.collect.AbstractMultiset
        /* renamed from: createElementSet */
        Set<E> mo7573createElementSet() {
            return Sets.filter(this.unfiltered.mo8127elementSet(), this.predicate);
        }

        @Override // com.google.common.collect.AbstractMultiset
        Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.unfiltered.mo7764entrySet(), new Predicate<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.FilteredMultiset.1
                @Override // com.google.common.base.Predicate
                public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                    return apply((Multiset.Entry) ((Multiset.Entry) obj));
                }

                public boolean apply(Multiset.Entry<E> entry) {
                    return FilteredMultiset.this.predicate.apply(entry.mo7624getElement());
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultiset
        Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset
        Iterator<Multiset.Entry<E>> entryIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(@NullableDecl Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            if (!contains(obj)) {
                return 0;
            }
            return this.unfiltered.remove(obj, i);
        }

        @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        /* renamed from: iterator */
        public UnmodifiableIterator<E> mo7986iterator() {
            return Iterators.filter(this.unfiltered.mo7986iterator(), this.predicate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        @NullableDecl
        private final E element;

        ImmutableEntry(@NullableDecl E e, int i) {
            this.element = e;
            this.count = i;
            CollectPreconditions.checkNonnegative(i, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @NullableDecl
        /* renamed from: getElement */
        public final E mo7624getElement() {
            return this.element;
        }

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class MultisetIteratorImpl<E> implements Iterator<E> {
        private boolean canRemove;
        @NullableDecl
        private Multiset.Entry<E> currentEntry;
        private final Iterator<Multiset.Entry<E>> entryIterator;
        private int laterCount;
        private final Multiset<E> multiset;
        private int totalCount;

        MultisetIteratorImpl(Multiset<E> multiset, Iterator<Multiset.Entry<E>> it2) {
            this.multiset = multiset;
            this.entryIterator = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                if (this.laterCount == 0) {
                    this.currentEntry = this.entryIterator.next();
                    int count = this.currentEntry.getCount();
                    this.laterCount = count;
                    this.totalCount = count;
                }
                this.laterCount--;
                this.canRemove = true;
                return this.currentEntry.mo7624getElement();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                this.multiset.remove(this.currentEntry.mo7624getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    /* loaded from: classes3.dex */
    static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multiset<? extends E> delegate;
        @NullableDecl
        transient Set<E> elementSet;
        @NullableDecl
        transient Set<Multiset.Entry<E>> entrySet;

        /* JADX INFO: Access modifiers changed from: package-private */
        public UnmodifiableMultiset(Multiset<? extends E> multiset) {
            this.delegate = multiset;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            throw new UnsupportedOperationException();
        }

        /* renamed from: createElementSet */
        Set<E> mo8124createElementSet() {
            return Collections.unmodifiableSet(this.delegate.mo8127elementSet());
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        /* renamed from: elementSet */
        public Set<E> mo8127elementSet() {
            Set<E> set = this.elementSet;
            if (set == null) {
                Set<E> mo8124createElementSet = mo8124createElementSet();
                this.elementSet = mo8124createElementSet;
                return mo8124createElementSet;
            }
            return set;
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        /* renamed from: entrySet */
        public Set<Multiset.Entry<E>> mo7764entrySet() {
            Set<Multiset.Entry<E>> set = this.entrySet;
            if (set == null) {
                Set<Multiset.Entry<E>> unmodifiableSet = Collections.unmodifiableSet(this.delegate.mo7764entrySet());
                this.entrySet = unmodifiableSet;
                return unmodifiableSet;
            }
            return set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.delegate.mo7986iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int setCount(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int add(E e, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public boolean setCount(E e, int i, int i2) {
            throw new UnsupportedOperationException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public Multiset<E> mo8280delegate() {
            return (Multiset<? extends E>) this.delegate;
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class ViewMultiset<E> extends AbstractMultiset<E> {
        private ViewMultiset() {
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            mo8127elementSet().clear();
        }

        @Override // com.google.common.collect.AbstractMultiset
        int distinctElements() {
            return mo8127elementSet().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        /* renamed from: iterator */
        public Iterator<E> mo7986iterator() {
            return Multisets.iteratorImpl(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return Multisets.linearTimeSizeImpl(this);
        }
    }

    private Multisets() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> boolean addAllImpl(Multiset<E> multiset, Collection<? extends E> collection) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            return addAllImpl((Multiset) multiset, cast(collection));
        }
        if (!collection.isEmpty()) {
            return Iterators.addAll(multiset, collection.iterator());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        for (Multiset.Entry<?> entry : multiset2.mo7764entrySet()) {
            if (multiset.count(entry.mo7624getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) multiset.mo7764entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entryArr, DecreasingCount.INSTANCE);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entryArr));
    }

    @Beta
    public static <E> Multiset<E> difference(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public void clear() {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.common.collect.Multiset
            public int count(@NullableDecl Object obj) {
                int count = Multiset.this.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.max(0, count - multiset2.count(obj));
            }

            @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset
            int distinctElements() {
                return Iterators.size(entryIterator());
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<E> elementIterator() {
                final Iterator it2 = Multiset.this.mo7764entrySet().iterator();
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Multisets.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v0, types: [E, java.lang.Object] */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    protected E mo8230computeNext() {
                        while (it2.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it2.next();
                            ?? mo7624getElement = entry.mo7624getElement();
                            if (entry.getCount() > multiset2.count(mo7624getElement)) {
                                return mo7624getElement;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it2 = Multiset.this.mo7764entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.4.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    public Multiset.Entry<E> mo8230computeNext() {
                        while (it2.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it2.next();
                            Object mo7624getElement = entry.mo7624getElement();
                            int count = entry.getCount() - multiset2.count(mo7624getElement);
                            if (count > 0) {
                                return Multisets.immutableEntry(mo7624getElement, count);
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> it2) {
        return new TransformedIterator<Multiset.Entry<E>, E>(it2) { // from class: com.google.common.collect.Multisets.5
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.TransformedIterator
            /* renamed from: transform */
            public /* bridge */ /* synthetic */ Object mo7907transform(Object obj) {
                return transform((Multiset.Entry<Object>) obj);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [E, java.lang.Object] */
            E transform(Multiset.Entry<E> entry) {
                return entry.mo7624getElement();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(Multiset<?> multiset, @NullableDecl Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.mo7764entrySet().size() == multiset2.mo7764entrySet().size()) {
                for (Multiset.Entry entry : multiset2.mo7764entrySet()) {
                    if (multiset.count(entry.mo7624getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> multiset, Predicate<? super E> predicate) {
        if (multiset instanceof FilteredMultiset) {
            FilteredMultiset filteredMultiset = (FilteredMultiset) multiset;
            return new FilteredMultiset(filteredMultiset.unfiltered, Predicates.and(filteredMultiset.predicate, predicate));
        }
        return new FilteredMultiset(multiset, predicate);
    }

    public static <E> Multiset.Entry<E> immutableEntry(@NullableDecl E e, int i) {
        return new ImmutableEntry(e, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int inferDistinctElements(Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).mo8127elementSet().size();
        }
        return 11;
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                int count = Multiset.this.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.min(count, multiset2.count(obj));
            }

            @Override // com.google.common.collect.AbstractMultiset
            /* renamed from: createElementSet */
            Set<E> mo7573createElementSet() {
                return Sets.intersection(Multiset.this.mo8127elementSet(), multiset2.mo8127elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it2 = Multiset.this.mo7764entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.2.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    public Multiset.Entry<E> mo8230computeNext() {
                        while (it2.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it2.next();
                            Object mo7624getElement = entry.mo7624getElement();
                            int min = Math.min(entry.getCount(), multiset2.count(mo7624getElement));
                            if (min > 0) {
                                return Multisets.immutableEntry(mo7624getElement, min);
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.mo7764entrySet().iterator());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int linearTimeSizeImpl(Multiset<?> multiset) {
        long j = 0;
        for (Multiset.Entry<?> entry : multiset.mo7764entrySet()) {
            j += entry.getCount();
        }
        return Ints.saturatedCast(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean removeAllImpl(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).mo8127elementSet();
        }
        return multiset.mo8127elementSet().removeAll(collection);
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return removeOccurrences(multiset, (Multiset<?>) iterable);
        }
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(iterable);
        boolean z = false;
        Iterator<?> it2 = iterable.iterator();
        while (it2.hasNext()) {
            z |= multiset.remove(it2.next());
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean retainAllImpl(Multiset<?> multiset, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).mo8127elementSet();
        }
        return multiset.mo8127elementSet().retainAll(collection);
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        return retainOccurrencesImpl(multiset, multiset2);
    }

    private static <E> boolean retainOccurrencesImpl(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<E>> it2 = multiset.mo7764entrySet().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            Multiset.Entry<E> next = it2.next();
            int count = multiset2.count(next.mo7624getElement());
            if (count == 0) {
                it2.remove();
            } else if (count < next.getCount()) {
                multiset.setCount(next.mo7624getElement(), count);
            }
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> int setCountImpl(Multiset<E> multiset, E e, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        int count = multiset.count(e);
        int i2 = i - count;
        if (i2 > 0) {
            multiset.add(e, i2);
        } else if (i2 < 0) {
            multiset.remove(e, -i2);
        }
        return count;
    }

    @Beta
    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public boolean contains(@NullableDecl Object obj) {
                return Multiset.this.contains(obj) || multiset2.contains(obj);
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                return multiset2.count(obj) + Multiset.this.count(obj);
            }

            @Override // com.google.common.collect.AbstractMultiset
            /* renamed from: createElementSet */
            Set<E> mo7573createElementSet() {
                return Sets.union(Multiset.this.mo8127elementSet(), multiset2.mo8127elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it2 = Multiset.this.mo7764entrySet().iterator();
                final Iterator it3 = multiset2.mo7764entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.3.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    public Multiset.Entry<E> mo8230computeNext() {
                        if (it2.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it2.next();
                            Object mo7624getElement = entry.mo7624getElement();
                            return Multisets.immutableEntry(mo7624getElement, multiset2.count(mo7624getElement) + entry.getCount());
                        }
                        while (it3.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it3.next();
                            Object mo7624getElement2 = entry2.mo7624getElement();
                            if (!Multiset.this.contains(mo7624getElement2)) {
                                return Multisets.immutableEntry(mo7624getElement2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public boolean isEmpty() {
                return Multiset.this.isEmpty() && multiset2.isEmpty();
            }

            @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public int size() {
                return IntMath.saturatedAdd(Multiset.this.size(), multiset2.size());
            }
        };
    }

    @Beta
    public static <E> Multiset<E> union(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
            public boolean contains(@NullableDecl Object obj) {
                return Multiset.this.contains(obj) || multiset2.contains(obj);
            }

            @Override // com.google.common.collect.Multiset
            public int count(Object obj) {
                return Math.max(Multiset.this.count(obj), multiset2.count(obj));
            }

            @Override // com.google.common.collect.AbstractMultiset
            /* renamed from: createElementSet */
            Set<E> mo7573createElementSet() {
                return Sets.union(Multiset.this.mo8127elementSet(), multiset2.mo8127elementSet());
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<E> elementIterator() {
                throw new AssertionError("should never be called");
            }

            @Override // com.google.common.collect.AbstractMultiset
            Iterator<Multiset.Entry<E>> entryIterator() {
                final Iterator it2 = Multiset.this.mo7764entrySet().iterator();
                final Iterator it3 = multiset2.mo7764entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    public Multiset.Entry<E> mo8230computeNext() {
                        if (it2.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it2.next();
                            Object mo7624getElement = entry.mo7624getElement();
                            return Multisets.immutableEntry(mo7624getElement, Math.max(entry.getCount(), multiset2.count(mo7624getElement)));
                        }
                        while (it3.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it3.next();
                            Object mo7624getElement2 = entry2.mo7624getElement();
                            if (!Multiset.this.contains(mo7624getElement2)) {
                                return Multisets.immutableEntry(mo7624getElement2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) endOfData();
                    }
                };
            }

            @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
            public boolean isEmpty() {
                return Multiset.this.isEmpty() && multiset2.isEmpty();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        return ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new UnmodifiableMultiset((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new UnmodifiableSortedMultiset((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> immutableMultiset) {
        return (Multiset) Preconditions.checkNotNull(immutableMultiset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> boolean setCountImpl(Multiset<E> multiset, E e, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        if (multiset.count(e) == i) {
            multiset.setCount(e, i2);
            return true;
        }
        return false;
    }

    private static <E> boolean addAllImpl(Multiset<E> multiset, Multiset<? extends E> multiset2) {
        if (multiset2 instanceof AbstractMapBasedMultiset) {
            return addAllImpl((Multiset) multiset, (AbstractMapBasedMultiset) multiset2);
        }
        if (multiset2.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : multiset2.mo7764entrySet()) {
            multiset.add(entry.mo7624getElement(), entry.getCount());
        }
        return true;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<?>> it2 = multiset.mo7764entrySet().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            Multiset.Entry<?> next = it2.next();
            int count = multiset2.count(next.mo7624getElement());
            if (count >= next.getCount()) {
                it2.remove();
            } else if (count > 0) {
                multiset.remove(next.mo7624getElement(), count);
            }
            z = true;
        }
        return z;
    }

    private static <E> boolean addAllImpl(Multiset<E> multiset, AbstractMapBasedMultiset<? extends E> abstractMapBasedMultiset) {
        if (abstractMapBasedMultiset.isEmpty()) {
            return false;
        }
        abstractMapBasedMultiset.addTo(multiset);
        return true;
    }
}
