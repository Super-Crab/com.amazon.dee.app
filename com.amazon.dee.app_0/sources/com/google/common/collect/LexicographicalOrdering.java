package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(serializable = true)
/* loaded from: classes3.dex */
final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T> elementOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LexicographicalOrdering(Comparator<? super T> comparator) {
        this.elementOrder = comparator;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return compare((Iterable) ((Iterable) obj), (Iterable) ((Iterable) obj2));
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LexicographicalOrdering)) {
            return false;
        }
        return this.elementOrder.equals(((LexicographicalOrdering) obj).elementOrder);
    }

    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    public String toString() {
        String valueOf = String.valueOf(this.elementOrder);
        return GeneratedOutlineSupport1.outline29(valueOf.length() + 18, valueOf, ".lexicographical()");
    }

    public int compare(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it2 = iterable2.iterator();
        for (T t : iterable) {
            if (!it2.hasNext()) {
                return 1;
            }
            int compare = this.elementOrder.compare(t, it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return it2.hasNext() ? -1 : 0;
    }
}
