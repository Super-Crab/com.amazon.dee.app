package io.reactivex.rxjava3.internal.functions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.BiPredicate;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ObjectHelper {
    static final BiPredicate<Object, Object> EQUALS = new BiObjectPredicate();

    /* loaded from: classes3.dex */
    static final class BiObjectPredicate implements BiPredicate<Object, Object> {
        BiObjectPredicate() {
        }

        @Override // io.reactivex.rxjava3.functions.BiPredicate
        public boolean test(Object o1, Object o2) {
            return Objects.equals(o1, o2);
        }
    }

    private ObjectHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> BiPredicate<T, T> equalsPredicate() {
        return (BiPredicate<T, T>) EQUALS;
    }

    public static int verifyPositive(int value, String paramName) {
        if (value > 0) {
            return value;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline74(paramName, " > 0 required but it was ", value));
    }

    public static long verifyPositive(long value, String paramName) {
        if (value > 0) {
            return value;
        }
        throw new IllegalArgumentException(paramName + " > 0 required but it was " + value);
    }
}
