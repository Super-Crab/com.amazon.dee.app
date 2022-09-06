package com.drew.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
public class Iterables {
    public static <E> List<E> toList(Iterable<E> iterable) {
        ArrayList arrayList = new ArrayList();
        for (E e : iterable) {
            arrayList.add(e);
        }
        return arrayList;
    }

    public static <E> Set<E> toSet(Iterable<E> iterable) {
        HashSet hashSet = new HashSet();
        for (E e : iterable) {
            hashSet.add(e);
        }
        return hashSet;
    }
}
