package com.google.android.exoplayer2.util;

import androidx.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes2.dex */
public final class CopyOnWriteMultiset<E> implements Iterable<E> {
    private final Object lock = new Object();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private final Map<E, Integer> elementCounts = new HashMap();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private Set<E> elementSet = Collections.emptySet();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private List<E> elements = Collections.emptyList();

    public void add(E e) {
        synchronized (this.lock) {
            ArrayList arrayList = new ArrayList(this.elements);
            arrayList.add(e);
            this.elements = Collections.unmodifiableList(arrayList);
            Integer num = this.elementCounts.get(e);
            if (num == null) {
                HashSet hashSet = new HashSet(this.elementSet);
                hashSet.add(e);
                this.elementSet = Collections.unmodifiableSet(hashSet);
            }
            Map<E, Integer> map = this.elementCounts;
            int i = 1;
            if (num != null) {
                i = 1 + num.intValue();
            }
            map.put(e, Integer.valueOf(i));
        }
    }

    public Set<E> elementSet() {
        Set<E> set;
        synchronized (this.lock) {
            set = this.elementSet;
        }
        return set;
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        Iterator<E> it2;
        synchronized (this.lock) {
            it2 = this.elements.iterator();
        }
        return it2;
    }

    public void remove(E e) {
        synchronized (this.lock) {
            Integer num = this.elementCounts.get(e);
            if (num == null) {
                return;
            }
            ArrayList arrayList = new ArrayList(this.elements);
            arrayList.remove(e);
            this.elements = Collections.unmodifiableList(arrayList);
            if (num.intValue() == 1) {
                this.elementCounts.remove(e);
                HashSet hashSet = new HashSet(this.elementSet);
                hashSet.remove(e);
                this.elementSet = Collections.unmodifiableSet(hashSet);
            } else {
                this.elementCounts.put(e, Integer.valueOf(num.intValue() - 1));
            }
        }
    }
}
