package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes4.dex */
public class GrowthList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -3620001881672L;

    public GrowthList() {
        super(new ArrayList());
    }

    public static <E> GrowthList<E> growthList(List<E> list) {
        return new GrowthList<>(list);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public void add(int i, E e) {
        int size = mo12761decorated().size();
        if (i > size) {
            mo12761decorated().addAll(Collections.nCopies(i - size, null));
        }
        mo12761decorated().add(i, e);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        boolean z;
        int size = mo12761decorated().size();
        if (i > size) {
            mo12761decorated().addAll(Collections.nCopies(i - size, null));
            z = true;
        } else {
            z = false;
        }
        return mo12761decorated().addAll(i, collection) | z;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E set(int i, E e) {
        int size = mo12761decorated().size();
        if (i >= size) {
            mo12761decorated().addAll(Collections.nCopies((i - size) + 1, null));
        }
        return mo12761decorated().set(i, e);
    }

    public GrowthList(int i) {
        super(new ArrayList(i));
    }

    protected GrowthList(List<E> list) {
        super(list);
    }
}
