package org.apache.commons.collections4.list;

import java.util.List;
import org.apache.commons.collections4.Factory;
/* loaded from: classes4.dex */
public class LazyList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -1708388017160694542L;
    private final Factory<? extends E> factory;

    protected LazyList(List<E> list, Factory<? extends E> factory) {
        super(list);
        if (factory != null) {
            this.factory = factory;
            return;
        }
        throw new IllegalArgumentException("Factory must not be null");
    }

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return new LazyList<>(list, factory);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E get(int i) {
        int size = mo12761decorated().size();
        if (i < size) {
            E e = mo12761decorated().get(i);
            if (e != null) {
                return e;
            }
            E mo12724create = this.factory.mo12724create();
            mo12761decorated().set(i, mo12724create);
            return mo12724create;
        }
        while (size < i) {
            mo12761decorated().add(null);
            size++;
        }
        E mo12724create2 = this.factory.mo12724create();
        mo12761decorated().add(mo12724create2);
        return mo12724create2;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public List<E> subList(int i, int i2) {
        return new LazyList(mo12761decorated().subList(i, i2), this.factory);
    }
}
