package org.apache.commons.collections4;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.SequencesComparator;
/* loaded from: classes4.dex */
public class ListUtils {

    /* loaded from: classes4.dex */
    private static final class CharSequenceAsList extends AbstractList<Character> {
        private final CharSequence sequence;

        public CharSequenceAsList(CharSequence charSequence) {
            this.sequence = charSequence;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.sequence.length();
        }

        @Override // java.util.AbstractList, java.util.List
        /* renamed from: get */
        public Character mo12632get(int i) {
            return Character.valueOf(this.sequence.charAt(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class LcsVisitor<E> implements CommandVisitor<E> {
        private final ArrayList<E> sequence = new ArrayList<>();

        public List<E> getSubSequence() {
            return this.sequence;
        }

        @Override // org.apache.commons.collections4.sequence.CommandVisitor
        public void visitDeleteCommand(E e) {
        }

        @Override // org.apache.commons.collections4.sequence.CommandVisitor
        public void visitInsertCommand(E e) {
        }

        @Override // org.apache.commons.collections4.sequence.CommandVisitor
        public void visitKeepCommand(E e) {
            this.sequence.add(e);
        }
    }

    /* loaded from: classes4.dex */
    private static class Partition<T> extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.list.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return (int) Math.ceil(this.list.size() / this.size);
        }

        private Partition(List<T> list, int i) {
            this.list = list;
            this.size = i;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int i) {
            int size = size();
            if (i >= 0) {
                if (i < size) {
                    int i2 = this.size;
                    int i3 = i * i2;
                    return this.list.subList(i3, Math.min(i2 + i3, this.list.size()));
                }
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline53("Index ", i, " must be less than size ", size));
            }
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline52("Index ", i, " must not be negative"));
        }
    }

    private ListUtils() {
    }

    public static <T> List<T> defaultIfNull(List<T> list, List<T> list2) {
        return list == null ? list2 : list;
    }

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    public static <E> List<E> fixedSizeList(List<E> list) {
        return FixedSizeList.fixedSizeList(list);
    }

    public static int hashCodeForList(Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        int i = 1;
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            i = (i * 31) + (next == null ? 0 : next.hashCode());
        }
        return i;
    }

    public static <E> int indexOf(List<E> list, Predicate<E> predicate) {
        if (list == null || predicate == null) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (predicate.evaluate(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <E> List<E> intersection(List<? extends E> list, List<? extends E> list2) {
        ArrayList arrayList = new ArrayList();
        if (list.size() > list2.size()) {
            list2 = list;
            list = list2;
        }
        HashSet hashSet = new HashSet(list);
        for (E e : list2) {
            if (hashSet.contains(e)) {
                arrayList.add(e);
                hashSet.remove(e);
            }
        }
        return arrayList;
    }

    public static boolean isEqualList(Collection<?> collection, Collection<?> collection2) {
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        Iterator<?> it2 = collection.iterator();
        Iterator<?> it3 = collection2.iterator();
        while (it2.hasNext() && it3.hasNext()) {
            Object next = it2.next();
            Object next2 = it3.next();
            if (next == null) {
                if (next2 != null) {
                    return false;
                }
            } else if (!next.equals(next2)) {
                return false;
            }
        }
        return !it2.hasNext() && !it3.hasNext();
    }

    public static <E> List<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return LazyList.lazyList(list, factory);
    }

    public static <E> List<E> longestCommonSubsequence(List<E> list, List<E> list2) {
        return longestCommonSubsequence(list, list2, DefaultEquator.defaultEquator());
    }

    public static <T> List<List<T>> partition(List<T> list, int i) {
        if (list != null) {
            if (i > 0) {
                return new Partition(list, i);
            }
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        throw new NullPointerException("List must not be null");
    }

    public static <E> List<E> predicatedList(List<E> list, Predicate<E> predicate) {
        return PredicatedList.predicatedList(list, predicate);
    }

    public static <E> List<E> removeAll(Collection<E> collection, Collection<?> collection2) {
        ArrayList arrayList = new ArrayList();
        for (E e : collection) {
            if (!collection2.contains(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> List<E> retainAll(Collection<E> collection, Collection<?> collection2) {
        ArrayList arrayList = new ArrayList(Math.min(collection.size(), collection2.size()));
        for (E e : collection) {
            if (collection2.contains(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> List<E> select(Collection<? extends E> collection, Predicate<? super E> predicate) {
        return (List) CollectionUtils.select(collection, predicate, new ArrayList(collection.size()));
    }

    public static <E> List<E> selectRejected(Collection<? extends E> collection, Predicate<? super E> predicate) {
        return (List) CollectionUtils.selectRejected(collection, predicate, new ArrayList(collection.size()));
    }

    public static <E> List<E> subtract(List<E> list, List<? extends E> list2) {
        ArrayList arrayList = new ArrayList();
        HashBag hashBag = new HashBag(list2);
        for (E e : list) {
            if (!hashBag.remove(e, 1)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> List<E> sum(List<? extends E> list, List<? extends E> list2) {
        return subtract(union(list, list2), intersection(list, list2));
    }

    public static <E> List<E> synchronizedList(List<E> list) {
        return Collections.synchronizedList(list);
    }

    public static <E> List<E> transformedList(List<E> list, Transformer<? super E, ? extends E> transformer) {
        return TransformedList.transformingList(list, transformer);
    }

    public static <E> List<E> union(List<? extends E> list, List<? extends E> list2) {
        ArrayList arrayList = new ArrayList(list2.size() + list.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return arrayList;
    }

    public static <E> List<E> unmodifiableList(List<? extends E> list) {
        return UnmodifiableList.unmodifiableList(list);
    }

    public static <E> List<E> longestCommonSubsequence(List<E> list, List<E> list2, Equator<? super E> equator) {
        if (list == null || list2 == null) {
            throw new NullPointerException("List must not be null");
        }
        if (equator != null) {
            EditScript script = new SequencesComparator(list, list2, equator).getScript();
            LcsVisitor lcsVisitor = new LcsVisitor();
            script.visit(lcsVisitor);
            return lcsVisitor.getSubSequence();
        }
        throw new NullPointerException("Equator must not be null");
    }

    public static String longestCommonSubsequence(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null && charSequence2 != null) {
            List<Character> longestCommonSubsequence = longestCommonSubsequence(new CharSequenceAsList(charSequence), new CharSequenceAsList(charSequence2));
            StringBuilder sb = new StringBuilder();
            for (Character ch : longestCommonSubsequence) {
                sb.append(ch);
            }
            return sb.toString();
        }
        throw new NullPointerException("CharSequence must not be null");
    }
}
