package kotlin.coroutines.experimental;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SequenceBuilder.kt */
@SinceKotlin(version = "1.1")
@RestrictsSuspension
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001f\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0012"}, d2 = {"Lkotlin/coroutines/experimental/SequenceBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "yield", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "yieldAll", AlexaMetricsConstants.MetricsComponents.ELEMENTS, "", "(Ljava/lang/Iterable;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "iterator", "", "(Ljava/util/Iterator;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "sequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes9.dex */
public abstract class SequenceBuilder<T> {
    @Nullable
    public abstract Object yield(T t, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Iterable<? extends T> iterable, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Object yieldAll = yieldAll(iterable.iterator(), continuation);
            coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
            return yieldAll == coroutine_suspended ? yieldAll : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public abstract Object yieldAll(@NotNull Iterator<? extends T> it2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public final Object yieldAll(@NotNull Sequence<? extends T> sequence, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object yieldAll = yieldAll(sequence.iterator(), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
        return yieldAll == coroutine_suspended ? yieldAll : Unit.INSTANCE;
    }
}
