package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes5.dex */
public final class NullnessUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private NullnessUtil() {
        throw new AssertionError("shouldn't be instantiated");
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T t) {
        return t;
    }

    private static <T> T[] castNonNullArray(T[] tArr) {
        for (T t : tArr) {
            checkIfArray(t);
        }
        return tArr;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullDeep(T[] tArr) {
        return (T[]) castNonNullArray(tArr);
    }

    private static void checkIfArray(Object obj) {
        Class<?> componentType = obj.getClass().getComponentType();
        if (componentType == null || componentType.isPrimitive()) {
            return;
        }
        castNonNullArray((Object[]) obj);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][] castNonNullDeep(T[][] tArr) {
        return (T[][]) ((Object[][]) castNonNullArray(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][] castNonNullDeep(T[][][] tArr) {
        return (T[][][]) ((Object[][][]) castNonNullArray(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][] castNonNullDeep(T[][][][] tArr) {
        return (T[][][][]) ((Object[][][][]) castNonNullArray(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][][] castNonNullDeep(T[][][][][] tArr) {
        return (T[][][][][]) ((Object[][][][][]) castNonNullArray(tArr));
    }
}
