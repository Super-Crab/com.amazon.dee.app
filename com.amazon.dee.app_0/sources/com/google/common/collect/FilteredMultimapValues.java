package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
final class FilteredMultimapValues<K, V> extends AbstractCollection<V> {
    @Weak
    private final FilteredMultimap<K, V> multimap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredMultimapValues(FilteredMultimap<K, V> filteredMultimap) {
        this.multimap = (FilteredMultimap) Preconditions.checkNotNull(filteredMultimap);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.multimap.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(@NullableDecl Object obj) {
        return this.multimap.containsValue(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        return Maps.valueIterator(this.multimap.mo8077entries().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(@NullableDecl Object obj) {
        Predicate<? super Map.Entry<K, V>> entryPredicate = this.multimap.entryPredicate();
        Iterator<Map.Entry<K, V>> it2 = this.multimap.mo7647unfiltered().mo8077entries().iterator();
        while (it2.hasNext()) {
            Map.Entry<K, V> next = it2.next();
            if (entryPredicate.apply(next) && Objects.equal(next.getValue(), obj)) {
                it2.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return Iterables.removeIf(this.multimap.mo7647unfiltered().mo8077entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.in(collection))));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return Iterables.removeIf(this.multimap.mo7647unfiltered().mo8077entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection)))));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.multimap.size();
    }
}
