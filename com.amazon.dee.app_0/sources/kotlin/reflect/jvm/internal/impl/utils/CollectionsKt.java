package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: collections.kt */
/* loaded from: classes4.dex */
public final class CollectionsKt {
    public static final <T> void addIfNotNull(@NotNull Collection<T> addIfNotNull, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(addIfNotNull, "$this$addIfNotNull");
        if (t != null) {
            addIfNotNull.add(t);
        }
    }

    private static final int capacity(int i) {
        if (i < 3) {
            return 3;
        }
        return (i / 3) + i + 1;
    }

    @NotNull
    public static final <T> List<T> compact(@NotNull ArrayList<T> compact) {
        List<T> emptyList;
        List<T> listOf;
        Intrinsics.checkParameterIsNotNull(compact, "$this$compact");
        int size = compact.size();
        if (size == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (size != 1) {
            compact.trimToSize();
            return compact;
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(kotlin.collections.CollectionsKt.first((List<? extends Object>) compact));
            return listOf;
        }
    }

    @NotNull
    public static final <K> Map<K, Integer> mapToIndex(@NotNull Iterable<? extends K> mapToIndex) {
        Intrinsics.checkParameterIsNotNull(mapToIndex, "$this$mapToIndex");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (K k : mapToIndex) {
            linkedHashMap.put(k, Integer.valueOf(i));
            i++;
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        return new HashMap<>(capacity(i));
    }

    @NotNull
    public static final <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(capacity(i));
    }

    @NotNull
    public static final <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet<>(capacity(i));
    }
}
