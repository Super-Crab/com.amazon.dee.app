package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.alexa.HalloConstants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CompletedExceptionally.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\"\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a.\u0010\b\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"recoverResult", "Lkotlin/Result;", ExifInterface.GPS_DIRECTION_TRUE, "state", "", "uCont", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toState", "(Ljava/lang/Object;)Ljava/lang/Object;", HalloConstants.CALLER_SIDE, "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CompletedExceptionallyKt {
    @NotNull
    public static final <T> Object recoverResult(@Nullable Object obj, @NotNull Continuation<? super T> continuation) {
        if (obj instanceof CompletedExceptionally) {
            Result.Companion companion = Result.Companion;
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.access$recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
            }
            return Result.m10378constructorimpl(ResultKt.createFailure(th));
        }
        Result.Companion companion2 = Result.Companion;
        return Result.m10378constructorimpl(obj);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj) {
        Throwable m10381exceptionOrNullimpl = Result.m10381exceptionOrNullimpl(obj);
        return m10381exceptionOrNullimpl == null ? obj : new CompletedExceptionally(m10381exceptionOrNullimpl, false, 2, null);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @NotNull CancellableContinuation<?> cancellableContinuation) {
        Throwable m10381exceptionOrNullimpl = Result.m10381exceptionOrNullimpl(obj);
        if (m10381exceptionOrNullimpl == null) {
            return obj;
        }
        if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation instanceof CoroutineStackFrame)) {
            m10381exceptionOrNullimpl = StackTraceRecoveryKt.access$recoverFromStackFrame(m10381exceptionOrNullimpl, (CoroutineStackFrame) cancellableContinuation);
        }
        return new CompletedExceptionally(m10381exceptionOrNullimpl, false, 2, null);
    }
}
