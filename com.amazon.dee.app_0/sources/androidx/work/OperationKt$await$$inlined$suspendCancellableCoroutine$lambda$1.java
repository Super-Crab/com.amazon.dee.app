package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
/* compiled from: ListenableFuture.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "R", "run", "androidx/work/ListenableFutureKt$await$2$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $cancellableContinuation;
    final /* synthetic */ ListenableFuture $this_await$inlined;

    public OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1(CancellableContinuation cancellableContinuation, ListenableFuture listenableFuture) {
        this.$cancellableContinuation = cancellableContinuation;
        this.$this_await$inlined = listenableFuture;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            CancellableContinuation cancellableContinuation = this.$cancellableContinuation;
            V v = this.$this_await$inlined.get();
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m10378constructorimpl(v));
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.$cancellableContinuation.cancel(cause);
                return;
            }
            CancellableContinuation cancellableContinuation2 = this.$cancellableContinuation;
            Result.Companion companion2 = Result.Companion;
            cancellableContinuation2.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(cause)));
        }
    }
}
