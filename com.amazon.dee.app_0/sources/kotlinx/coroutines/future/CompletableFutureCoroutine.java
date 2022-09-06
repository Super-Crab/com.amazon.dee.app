package kotlinx.coroutines.future;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Future.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0012\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00018\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/future/CompletableFutureCoroutine;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/AbstractCoroutine;", "Ljava/util/function/BiConsumer;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "completion", "Ljava/util/concurrent/CompletableFuture;", "(Lkotlin/coroutines/CoroutineContext;Ljava/util/concurrent/CompletableFuture;)V", "accept", "", "value", ADMRegistrationConstants.CALL_EXCEPTION, "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "onCompleted", "(Ljava/lang/Object;)V", "onCompletedExceptionally", "kotlinx-coroutines-jdk8"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class CompletableFutureCoroutine<T> extends AbstractCoroutine<T> implements BiConsumer<T, Throwable> {
    private final CompletableFuture<T> completion;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompletableFutureCoroutine(@NotNull CoroutineContext context, @NotNull CompletableFuture<T> completion) {
        super(context, false, 2, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        this.completion = completion;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.BiConsumer
    public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th) {
        accept2((CompletableFutureCoroutine<T>) obj, th);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void onCompleted(T t) {
        this.completion.complete(t);
    }

    protected void onCompletedExceptionally(@NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        if (!this.completion.completeExceptionally(exception)) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.parentContext, exception, this);
        }
    }

    /* renamed from: accept  reason: avoid collision after fix types in other method */
    public void accept2(@Nullable T t, @Nullable Throwable th) {
        mo12309cancel();
    }
}
