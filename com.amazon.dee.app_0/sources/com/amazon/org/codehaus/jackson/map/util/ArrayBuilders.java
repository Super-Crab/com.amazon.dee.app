package com.amazon.org.codehaus.jackson.map.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/* loaded from: classes13.dex */
public final class ArrayBuilders {
    BooleanBuilder _booleanBuilder = null;
    ByteBuilder _byteBuilder = null;
    ShortBuilder _shortBuilder = null;
    IntBuilder _intBuilder = null;
    LongBuilder _longBuilder = null;
    FloatBuilder _floatBuilder = null;
    DoubleBuilder _doubleBuilder = null;

    /* loaded from: classes13.dex */
    private static final class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
        private final T[] _array;
        private int _index = 0;

        public ArrayIterator(T[] tArr) {
            this._array = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._index < this._array.length;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this._index;
            T[] tArr = this._array;
            if (i < tArr.length) {
                this._index = i + 1;
                return tArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes13.dex */
    public static final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    /* loaded from: classes13.dex */
    public static final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        @Override // com.amazon.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public static <T> List<T> addToList(List<T> list, T t) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(t);
        return list;
    }

    public static <T> Iterable<T> arrayAsIterable(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    public static <T> Iterator<T> arrayAsIterator(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    public static <T> HashSet<T> arrayToSet(T[] tArr) {
        HashSet<T> hashSet = new HashSet<>();
        if (tArr != null) {
            for (T t : tArr) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }

    public static <T> T[] insertInList(T[] tArr, T t) {
        int length = tArr.length;
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1));
        if (length > 0) {
            System.arraycopy(tArr, 0, tArr2, 1, length);
        }
        tArr2[0] = t;
        return tArr2;
    }

    public static <T> T[] insertInListNoDup(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (tArr[i] == t) {
                if (i == 0) {
                    return tArr;
                }
                T[] tArr2 = (T[]) ((Object[]) GeneratedOutlineSupport1.outline26(tArr, length));
                System.arraycopy(tArr, 0, tArr2, 1, i);
                tArr[0] = t;
                return tArr2;
            }
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1));
        if (length > 0) {
            System.arraycopy(tArr, 0, tArr3, 1, length);
        }
        tArr3[0] = t;
        return tArr3;
    }

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }
}
