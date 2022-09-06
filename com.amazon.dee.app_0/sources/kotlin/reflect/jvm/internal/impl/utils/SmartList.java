package kotlin.reflect.jvm.internal.impl.utils;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private Object myElem;
    private int mySize;

    /* loaded from: classes4.dex */
    private static class EmptyIterator<T> implements Iterator<T> {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        private EmptyIterator() {
        }

        public static <T> EmptyIterator<T> getInstance() {
            return INSTANCE;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes4.dex */
    private class SingletonIterator extends SingletonIteratorBase<E> {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = ((AbstractList) SmartList.this).modCount;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected void checkCoModification() {
            if (((AbstractList) SmartList.this).modCount == this.myInitialModCount) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ModCount: ");
            outline107.append(((AbstractList) SmartList.this).modCount);
            outline107.append("; expected: ");
            outline107.append(this.myInitialModCount);
            throw new ConcurrentModificationException(outline107.toString());
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected E getElement() {
            return (E) SmartList.this.myElem;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    /* loaded from: classes4.dex */
    private static abstract class SingletonIteratorBase<T> implements Iterator<T> {
        private boolean myVisited;

        private SingletonIteratorBase() {
        }

        protected abstract void checkCoModification();

        protected abstract T getElement();

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (!this.myVisited) {
                this.myVisited = true;
                checkCoModification();
                return getElement();
            }
            throw new NoSuchElementException();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = "a";
                break;
            default:
                objArr[0] = AlexaMetricsConstants.MetricsComponents.ELEMENTS;
                break;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "iterator";
        } else if (i == 5 || i == 6 || i == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
        }
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i == 2 || i == 3 || i == 5 || i == 6 || i == 7) {
            throw new IllegalStateException(format);
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        Object[] objArr;
        int i = this.mySize;
        if (i == 0) {
            this.myElem = e;
        } else if (i == 1) {
            this.myElem = new Object[]{this.myElem, e};
        } else {
            Object[] objArr2 = (Object[]) this.myElem;
            int length = objArr2.length;
            if (i >= length) {
                int i2 = ((length * 3) / 2) + 1;
                int i3 = i + 1;
                if (i2 >= i3) {
                    i3 = i2;
                }
                objArr = new Object[i3];
                this.myElem = objArr;
                System.arraycopy(objArr2, 0, objArr, 0, length);
            } else {
                objArr = objArr2;
            }
            objArr[this.mySize] = e;
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
            outline109.append(this.mySize);
            throw new IndexOutOfBoundsException(outline109.toString());
        } else if (i2 == 1) {
            return (E) this.myElem;
        } else {
            return (E) ((Object[]) this.myElem)[i];
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<E> iterator() {
        int i = this.mySize;
        if (i == 0) {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(2);
            }
            return emptyIterator;
        } else if (i == 1) {
            return new SingletonIterator();
        } else {
            Iterator<E> it2 = super.iterator();
            if (it2 == null) {
                $$$reportNull$$$0(3);
            }
            return it2;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        int i2;
        E e;
        if (i >= 0 && i < (i2 = this.mySize)) {
            if (i2 == 1) {
                e = (E) this.myElem;
                this.myElem = null;
            } else {
                Object[] objArr = (Object[]) this.myElem;
                Object obj = objArr[i];
                if (i2 == 2) {
                    this.myElem = objArr[1 - i];
                } else {
                    int i3 = (i2 - i) - 1;
                    if (i3 > 0) {
                        System.arraycopy(objArr, i + 1, objArr, i, i3);
                    }
                    objArr[this.mySize - 1] = null;
                }
                e = (E) obj;
            }
            this.mySize--;
            ((AbstractList) this).modCount++;
            return e;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
        outline109.append(this.mySize);
        throw new IndexOutOfBoundsException(outline109.toString());
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
            outline109.append(this.mySize);
            throw new IndexOutOfBoundsException(outline109.toString());
        } else if (i2 == 1) {
            E e2 = (E) this.myElem;
            this.myElem = e;
            return e2;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            E e3 = (E) objArr[i];
            objArr[i] = e;
            return e3;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] tArr) {
        if (tArr == 0) {
            $$$reportNull$$$0(4);
        }
        int length = tArr.length;
        int i = this.mySize;
        if (i == 1) {
            if (length != 0) {
                tArr[0] = this.myElem;
            } else {
                T[] tArr2 = (T[]) ((Object[]) GeneratedOutlineSupport1.outline26(tArr, 1));
                tArr2[0] = this.myElem;
                return tArr2;
            }
        } else if (length < i) {
            T[] tArr3 = (T[]) Arrays.copyOf((Object[]) this.myElem, i, tArr.getClass());
            if (tArr3 == null) {
                $$$reportNull$$$0(6);
            }
            return tArr3;
        } else if (i != 0) {
            System.arraycopy(this.myElem, 0, tArr, 0, i);
        }
        int i2 = this.mySize;
        if (length > i2) {
            tArr[i2] = 0;
        }
        return tArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int i2;
        if (i >= 0 && i <= (i2 = this.mySize)) {
            if (i2 == 0) {
                this.myElem = e;
            } else if (i2 == 1 && i == 0) {
                this.myElem = new Object[]{e, this.myElem};
            } else {
                int i3 = this.mySize;
                Object[] objArr = new Object[i3 + 1];
                if (i3 == 1) {
                    objArr[0] = this.myElem;
                } else {
                    Object[] objArr2 = (Object[]) this.myElem;
                    System.arraycopy(objArr2, 0, objArr, 0, i);
                    System.arraycopy(objArr2, i, objArr, i + 1, this.mySize - i);
                }
                objArr[i] = e;
                this.myElem = objArr;
            }
            this.mySize++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
        outline109.append(this.mySize);
        throw new IndexOutOfBoundsException(outline109.toString());
    }
}
