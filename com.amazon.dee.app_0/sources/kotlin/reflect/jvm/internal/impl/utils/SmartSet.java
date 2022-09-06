package kotlin.reflect.jvm.internal.impl.utils;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;
/* compiled from: SmartSet.kt */
/* loaded from: classes4.dex */
public final class SmartSet<T> extends AbstractSet<T> {
    public static final Companion Companion = new Companion(null);
    private Object data;
    private int size;

    /* compiled from: SmartSet.kt */
    /* loaded from: classes4.dex */
    private static final class ArrayIterator<T> implements Iterator<T>, KMutableIterator {
        private final Iterator<T> arrayIterator;

        public ArrayIterator(@NotNull T[] array) {
            Intrinsics.checkParameterIsNotNull(array, "array");
            this.arrayIterator = ArrayIteratorKt.iterator(array);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.arrayIterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.arrayIterator.next();
        }

        @Override // java.util.Iterator
        @NotNull
        /* renamed from: remove */
        public Void mo12150remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: SmartSet.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create() {
            return new SmartSet<>(null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final <T> SmartSet<T> create(@NotNull Collection<? extends T> set) {
            Intrinsics.checkParameterIsNotNull(set, "set");
            SmartSet<T> smartSet = new SmartSet<>(null);
            smartSet.addAll(set);
            return smartSet;
        }
    }

    /* compiled from: SmartSet.kt */
    /* loaded from: classes4.dex */
    private static final class SingletonIterator<T> implements Iterator<T>, KMutableIterator {
        private final T element;
        private boolean hasNext = true;

        public SingletonIterator(T t) {
            this.element = t;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.hasNext) {
                this.hasNext = false;
                return this.element;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        @NotNull
        /* renamed from: remove */
        public Void mo12151remove() {
            throw new UnsupportedOperationException();
        }
    }

    private SmartSet() {
    }

    @JvmStatic
    @NotNull
    public static final <T> SmartSet<T> create() {
        return Companion.create();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        boolean contains;
        Object[] objArr;
        LinkedHashSet linkedSetOf;
        if (size() == 0) {
            this.data = t;
        } else if (size() == 1) {
            if (Intrinsics.areEqual(this.data, t)) {
                return false;
            }
            this.data = new Object[]{this.data, t};
        } else if (size() < 5) {
            Object obj = this.data;
            if (obj != null) {
                Object[] objArr2 = (Object[]) obj;
                contains = ArraysKt___ArraysKt.contains(objArr2, t);
                if (contains) {
                    return false;
                }
                if (size() == 4) {
                    linkedSetOf = SetsKt__SetsKt.linkedSetOf(Arrays.copyOf(objArr2, objArr2.length));
                    linkedSetOf.add(t);
                    objArr = linkedSetOf;
                } else {
                    Object[] copyOf = Arrays.copyOf(objArr2, size() + 1);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                    copyOf[copyOf.length - 1] = t;
                    objArr = copyOf;
                }
                this.data = objArr;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            Object obj2 = this.data;
            if (obj2 != null) {
                if (!TypeIntrinsics.asMutableSet(obj2).add(t)) {
                    return false;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
            }
        }
        setSize(size() + 1);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.data = null;
        setSize(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        boolean contains;
        if (size() == 0) {
            return false;
        }
        if (size() == 1) {
            return Intrinsics.areEqual(this.data, obj);
        }
        if (size() >= 5) {
            Object obj2 = this.data;
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Set<T>");
            }
            return ((Set) obj2).contains(obj);
        }
        Object obj3 = this.data;
        if (obj3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        contains = ArraysKt___ArraysKt.contains((Object[]) obj3, obj);
        return contains;
    }

    public int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    @NotNull
    public Iterator<T> iterator() {
        if (size() == 0) {
            return Collections.emptySet().iterator();
        }
        if (size() == 1) {
            return new SingletonIterator(this.data);
        }
        if (size() < 5) {
            Object obj = this.data;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return new ArrayIterator((Object[]) obj);
        }
        Object obj2 = this.data;
        if (obj2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableSet<T>");
        }
        return TypeIntrinsics.asMutableSet(obj2).iterator();
    }

    public void setSize(int i) {
        this.size = i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }

    public /* synthetic */ SmartSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
