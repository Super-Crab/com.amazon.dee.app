package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes3.dex */
public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private DaggerCollections() {
    }

    private static int calculateInitialCapacity(int expectedSize) {
        if (expectedSize < 3) {
            return expectedSize + 1;
        }
        if (expectedSize >= 1073741824) {
            return Integer.MAX_VALUE;
        }
        return (int) ((expectedSize / 0.75f) + 1.0f);
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> HashSet<T> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(calculateInitialCapacity(expectedSize));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return new LinkedHashMap<>(calculateInitialCapacity(expectedSize));
    }

    public static <T> List<T> presizedList(int size) {
        if (size == 0) {
            return Collections.emptyList();
        }
        return new ArrayList(size);
    }
}
