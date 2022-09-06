package kotlinx.atomicfu;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a!\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH\u0086\b\u001a!\u0010\u0005\u001a\u00020\u0004*\u00020\n2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tH\u0086\b\u001a!\u0010\u0005\u001a\u00020\u000b*\u00020\f2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\tH\u0086\b\u001a2\u0010\u0005\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\tH\u0086\b¢\u0006\u0002\u0010\u000e\u001a!\u0010\u000f\u001a\u00020\u0010*\u00020\u00072\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\tH\u0086\b\u001a!\u0010\u000f\u001a\u00020\u0010*\u00020\n2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\tH\u0086\b\u001a!\u0010\u000f\u001a\u00020\u0010*\u00020\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00120\tH\u0086\b\u001a-\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00120\tH\u0086\b\u001a!\u0010\u0013\u001a\u00020\u0012*\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH\u0086\b\u001a!\u0010\u0013\u001a\u00020\u0012*\u00020\n2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tH\u0086\b\u001a!\u0010\u0013\u001a\u00020\u0012*\u00020\f2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\tH\u0086\b\u001a-\u0010\u0013\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\tH\u0086\b\u001a!\u0010\u0014\u001a\u00020\u0006*\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH\u0086\b\u001a!\u0010\u0014\u001a\u00020\u0004*\u00020\n2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tH\u0086\b\u001a!\u0010\u0014\u001a\u00020\u000b*\u00020\f2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\tH\u0086\b\u001a2\u0010\u0014\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\tH\u0086\b¢\u0006\u0002\u0010\u000e¨\u0006\u0015"}, d2 = {"atomicArrayOfNulls", "Lkotlinx/atomicfu/AtomicArray;", ExifInterface.GPS_DIRECTION_TRUE, "size", "", "getAndUpdate", "", "Lkotlinx/atomicfu/AtomicBoolean;", "function", "Lkotlin/Function1;", "Lkotlinx/atomicfu/AtomicInt;", "", "Lkotlinx/atomicfu/AtomicLong;", "Lkotlinx/atomicfu/AtomicRef;", "(Lkotlinx/atomicfu/AtomicRef;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "loop", "", "action", "", "update", "updateAndGet", "atomicfu"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicFU_commonKt {
    @NotNull
    public static final <T> AtomicArray<T> atomicArrayOfNulls(int i) {
        return new AtomicArray<>(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public static final <T> T getAndUpdate(@NotNull AtomicRef<T> receiver$0, @NotNull Function1<? super T, ? extends T> function) {
        ?? r0;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            r0 = (Object) receiver$0.getValue();
        } while (!receiver$0.compareAndSet(r0, function.mo12165invoke(r0)));
        return r0;
    }

    @NotNull
    public static final <T> Void loop(@NotNull AtomicRef<T> receiver$0, @NotNull Function1<? super T, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(action, "action");
        while (true) {
            action.mo12165invoke(receiver$0.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void update(@NotNull AtomicRef<T> receiver$0, @NotNull Function1<? super T, ? extends T> function) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            obj = (Object) receiver$0.getValue();
        } while (!receiver$0.compareAndSet(obj, function.mo12165invoke(obj)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T updateAndGet(@NotNull AtomicRef<T> receiver$0, @NotNull Function1<? super T, ? extends T> function) {
        Object obj;
        T mo12165invoke;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            obj = (Object) receiver$0.getValue();
            mo12165invoke = function.mo12165invoke(obj);
        } while (!receiver$0.compareAndSet(obj, mo12165invoke));
        return mo12165invoke;
    }

    @NotNull
    public static final Void loop(@NotNull AtomicBoolean receiver$0, @NotNull Function1<? super Boolean, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(action, "action");
        while (true) {
            action.mo12165invoke(Boolean.valueOf(receiver$0.getValue()));
        }
    }

    @NotNull
    public static final Void loop(@NotNull AtomicInt receiver$0, @NotNull Function1<? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(action, "action");
        while (true) {
            action.mo12165invoke(Integer.valueOf(receiver$0.getValue()));
        }
    }

    public static final boolean getAndUpdate(@NotNull AtomicBoolean receiver$0, @NotNull Function1<? super Boolean, Boolean> function) {
        boolean value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Boolean.valueOf(value)).booleanValue()));
        return value;
    }

    @NotNull
    public static final Void loop(@NotNull AtomicLong receiver$0, @NotNull Function1<? super Long, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(action, "action");
        while (true) {
            action.mo12165invoke(Long.valueOf(receiver$0.getValue()));
        }
    }

    public static final void update(@NotNull AtomicBoolean receiver$0, @NotNull Function1<? super Boolean, Boolean> function) {
        boolean value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Boolean.valueOf(value)).booleanValue()));
    }

    public static final boolean updateAndGet(@NotNull AtomicBoolean receiver$0, @NotNull Function1<? super Boolean, Boolean> function) {
        boolean value;
        boolean booleanValue;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
            booleanValue = function.mo12165invoke(Boolean.valueOf(value)).booleanValue();
        } while (!receiver$0.compareAndSet(value, booleanValue));
        return booleanValue;
    }

    public static final int getAndUpdate(@NotNull AtomicInt receiver$0, @NotNull Function1<? super Integer, Integer> function) {
        int value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Integer.valueOf(value)).intValue()));
        return value;
    }

    public static final void update(@NotNull AtomicInt receiver$0, @NotNull Function1<? super Integer, Integer> function) {
        int value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Integer.valueOf(value)).intValue()));
    }

    public static final int updateAndGet(@NotNull AtomicInt receiver$0, @NotNull Function1<? super Integer, Integer> function) {
        int value;
        int intValue;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
            intValue = function.mo12165invoke(Integer.valueOf(value)).intValue();
        } while (!receiver$0.compareAndSet(value, intValue));
        return intValue;
    }

    public static final long getAndUpdate(@NotNull AtomicLong receiver$0, @NotNull Function1<? super Long, Long> function) {
        long value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Long.valueOf(value)).longValue()));
        return value;
    }

    public static final void update(@NotNull AtomicLong receiver$0, @NotNull Function1<? super Long, Long> function) {
        long value;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
        } while (!receiver$0.compareAndSet(value, function.mo12165invoke(Long.valueOf(value)).longValue()));
    }

    public static final long updateAndGet(@NotNull AtomicLong receiver$0, @NotNull Function1<? super Long, Long> function) {
        long value;
        long longValue;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function, "function");
        do {
            value = receiver$0.getValue();
            longValue = function.mo12165invoke(Long.valueOf(value)).longValue();
        } while (!receiver$0.compareAndSet(value, longValue));
        return longValue;
    }
}
