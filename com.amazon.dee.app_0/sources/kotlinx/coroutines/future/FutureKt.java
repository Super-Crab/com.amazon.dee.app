package kotlinx.coroutines.future;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Future.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a!\u0010\u0006\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a[\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f¢\u0006\u0002\b\u0012ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"asCompletableFuture", "Ljava/util/concurrent/CompletableFuture;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/Deferred;", "asDeferred", "Ljava/util/concurrent/CompletionStage;", "await", "(Ljava/util/concurrent/CompletionStage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "future", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Ljava/util/concurrent/CompletableFuture;", "kotlinx-coroutines-jdk8"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class FutureKt {
    @NotNull
    public static final <T> CompletableFuture<T> asCompletableFuture(@NotNull final Deferred<? extends T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        completableFuture.whenComplete((BiConsumer) new BiConsumer<T, Throwable>() { // from class: kotlinx.coroutines.future.FutureKt$asCompletableFuture$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th) {
                accept2((FutureKt$asCompletableFuture$1<T, U>) obj, th);
            }

            /* renamed from: accept  reason: avoid collision after fix types in other method */
            public final void accept2(T t, Throwable th) {
                Deferred.this.cancel(th);
            }
        });
        receiver$0.invokeOnCompletion(new FutureKt$asCompletableFuture$2(receiver$0, completableFuture));
        return completableFuture;
    }

    @NotNull
    public static final <T> Deferred<T> asDeferred(@NotNull CompletionStage<T> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if ((receiver$0 instanceof Future) && ((Future) receiver$0).isDone()) {
            try {
                return CompletableDeferredKt.CompletableDeferred(((Future) receiver$0).get());
            } catch (Throwable th) {
                ExecutionException executionException = !(th instanceof ExecutionException) ? null : th;
                ExecutionException executionException2 = th;
                if (executionException != null) {
                    Throwable cause = executionException.getCause();
                    executionException2 = th;
                    if (cause != null) {
                        executionException2 = cause;
                    }
                }
                CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                CompletableDeferred$default.cancel(executionException2);
                return CompletableDeferred$default;
            }
        }
        final CompletableDeferred CompletableDeferred$default2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        receiver$0.whenComplete(new BiConsumer<T, Throwable>() { // from class: kotlinx.coroutines.future.FutureKt$asDeferred$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th2) {
                accept2((FutureKt$asDeferred$2<T, U>) obj, th2);
            }

            /* renamed from: accept  reason: avoid collision after fix types in other method */
            public final void accept2(T t, Throwable th2) {
                if (th2 == null) {
                    CompletableDeferred.this.complete(t);
                } else {
                    CompletableDeferred.this.cancel(th2);
                }
            }
        });
        if (receiver$0 instanceof Future) {
            JobKt.cancelFutureOnCompletion(CompletableDeferred$default2, (Future) receiver$0);
        }
        return CompletableDeferred$default2;
    }

    @Nullable
    public static final <T> Object await(@NotNull CompletionStage<T> completionStage, @NotNull Continuation<? super T> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        if ((completionStage instanceof Future) && ((Future) completionStage).isDone()) {
            try {
                return ((Future) completionStage).get();
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
        ContinuationConsumer continuationConsumer = new ContinuationConsumer(cancellableContinuationImpl);
        completionStage.whenComplete(continuationConsumer);
        cancellableContinuationImpl.invokeOnCancellation(new FutureKt$await$$inlined$suspendCancellableCoroutine$lambda$1(continuationConsumer, completionStage));
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @NotNull
    public static final <T> CompletableFuture<T> future(@NotNull CoroutineScope receiver$0, @NotNull CoroutineContext context, @NotNull CoroutineStart start, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(block, "block");
        if (!start.isLazy()) {
            CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(receiver$0, context);
            CompletableFuture<T> completableFuture = new CompletableFuture<>();
            CompletableFutureCoroutine completableFutureCoroutine = new CompletableFutureCoroutine(newCoroutineContext, completableFuture);
            completableFuture.whenComplete((BiConsumer) completableFutureCoroutine);
            completableFutureCoroutine.start(start, completableFutureCoroutine, block);
            return completableFuture;
        }
        throw new IllegalArgumentException((start + " start is not supported").toString());
    }

    @NotNull
    public static /* synthetic */ CompletableFuture future$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return future(coroutineScope, coroutineContext, coroutineStart, function2);
    }
}
