package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.Priority;
import java.util.EnumMap;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class PriorityMapping {
    private static SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();
    private static EnumMap<Priority, Integer> PRIORITY_INT_MAP = new EnumMap<>(Priority.class);

    static {
        PRIORITY_INT_MAP.put((EnumMap<Priority, Integer>) Priority.DEFAULT, (Priority) 0);
        PRIORITY_INT_MAP.put((EnumMap<Priority, Integer>) Priority.VERY_LOW, (Priority) 1);
        PRIORITY_INT_MAP.put((EnumMap<Priority, Integer>) Priority.HIGHEST, (Priority) 2);
        for (Priority priority : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(PRIORITY_INT_MAP.get(priority).intValue(), priority);
        }
    }

    public static int toInt(@NonNull Priority priority) {
        Integer num = PRIORITY_INT_MAP.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    @NonNull
    public static Priority valueOf(int i) {
        Priority priority = PRIORITY_MAP.get(i);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown Priority for value ", i));
    }
}
