package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Delay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0019\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001e\u0010\u0000\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0087@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0016\u0010\r\u001a\u00020\u0007*\u00020\nH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"delay", "Lkotlinx/coroutines/Delay;", "Lkotlin/coroutines/CoroutineContext;", "getDelay", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Delay;", "", "timeMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "duration", "Lkotlin/time/Duration;", "delay-p9JZ4hM", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDelayMillis", "toDelayMillis-LRDsOJo", "(D)J", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DelayKt {
    @Nullable
    public static final Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        if (j <= 0) {
            return Unit.INSTANCE;
        }
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        getDelay(cancellableContinuationImpl.getContext()).mo12303scheduleResumeAfterDelay(j, cancellableContinuationImpl);
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @ExperimentalTime
    @Nullable
    /* renamed from: delay-p9JZ4hM  reason: not valid java name */
    public static final Object m12235delayp9JZ4hM(double d, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object delay = delay(m12236toDelayMillisLRDsOJo(d), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return delay == coroutine_suspended ? delay : Unit.INSTANCE;
    }

    @NotNull
    public static final Delay getDelay(@NotNull CoroutineContext coroutineContext) {
        CoroutineContext.Element element = coroutineContext.get(ContinuationInterceptor.Key);
        if (!(element instanceof Delay)) {
            element = null;
        }
        Delay delay = (Delay) element;
        return delay != null ? delay : DefaultExecutorKt.getDefaultDelay();
    }

    @ExperimentalTime
    /* renamed from: toDelayMillis-LRDsOJo  reason: not valid java name */
    public static final long m12236toDelayMillisLRDsOJo(double d) {
        long coerceAtLeast;
        if (Duration.m12180compareToLRDsOJo(d, Duration.Companion.getZERO()) > 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(Duration.m12217toLongMillisecondsimpl(d), 1L);
            return coerceAtLeast;
        }
        return 0L;
    }
}
