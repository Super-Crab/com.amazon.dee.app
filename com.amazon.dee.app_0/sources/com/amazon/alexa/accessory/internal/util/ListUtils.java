package com.amazon.alexa.accessory.internal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes.dex */
public final class ListUtils {

    /* loaded from: classes.dex */
    public static final class ListDifference<T> {
        public final List<T> added;
        public final List<T> removed;

        public ListDifference(List<T> list, List<T> list2) {
            Preconditions.notNull(list, "removed");
            Preconditions.notNull(list2, "added");
            this.removed = Collections.unmodifiableList(list);
            this.added = Collections.unmodifiableList(list2);
        }
    }

    private ListUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> ListDifference<T> diff(List<T> list, List<T> list2) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet(list2);
        for (T t : list) {
            if (!hashSet.remove(t)) {
                arrayList.add(t);
            }
        }
        return new ListDifference<>(arrayList, new ArrayList(hashSet));
    }
}
