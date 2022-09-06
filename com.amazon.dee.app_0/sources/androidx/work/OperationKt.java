package androidx.work;

import androidx.work.Operation;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Operation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"await", "Landroidx/work/Operation$State$SUCCESS;", "kotlin.jvm.PlatformType", "Landroidx/work/Operation;", "(Landroidx/work/Operation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OperationKt {
    @Nullable
    public static final Object await(@NotNull Operation operation, @NotNull Continuation<? super Operation.State.SUCCESS> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        ListenableFuture<Operation.State.SUCCESS> result = operation.getResult();
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        if (result.isDone()) {
            try {
                return result.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        }
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        result.addListener(new OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, result), DirectExecutor.INSTANCE);
        Object result2 = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result2 != coroutine_suspended) {
            return result2;
        }
        DebugProbesKt.probeCoroutineSuspended(continuation);
        return result2;
    }

    @Nullable
    private static final Object await$$forInline(@NotNull Operation operation, @NotNull Continuation continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        ListenableFuture<Operation.State.SUCCESS> result = operation.getResult();
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        if (result.isDone()) {
            try {
                return result.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        }
        InlineMarker.mark(0);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        result.addListener(new OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, result), DirectExecutor.INSTANCE);
        Object result2 = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result2 == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result2;
    }
}
