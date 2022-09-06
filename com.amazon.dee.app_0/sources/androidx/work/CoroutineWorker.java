package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CoroutineWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0016\u001a\u00020\u000fH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0019\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0#R\u001c\u0010\u0007\u001a\u00020\b8\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Landroidx/work/CoroutineWorker;", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "coroutineContext", "Lkotlinx/coroutines/CoroutineDispatcher;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlinx/coroutines/CoroutineDispatcher;", "future", "Landroidx/work/impl/utils/futures/SettableFuture;", "Landroidx/work/ListenableWorker$Result;", "getFuture$work_runtime_ktx_release", "()Landroidx/work/impl/utils/futures/SettableFuture;", "job", "Lkotlinx/coroutines/CompletableJob;", "getJob$work_runtime_ktx_release", "()Lkotlinx/coroutines/CompletableJob;", "doWork", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onStopped", "", "setForeground", "foregroundInfo", "Landroidx/work/ForegroundInfo;", "(Landroidx/work/ForegroundInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setProgress", "data", "Landroidx/work/Data;", "(Landroidx/work/Data;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startWork", "Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime-ktx_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {
    @NotNull
    private final CoroutineDispatcher coroutineContext;
    @NotNull
    private final SettableFuture<ListenableWorker.Result> future;
    @NotNull
    private final CompletableJob job;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(@NotNull Context appContext, @NotNull WorkerParameters params) {
        super(appContext, params);
        CompletableJob Job$default;
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.job = Job$default;
        SettableFuture<ListenableWorker.Result> create = SettableFuture.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "SettableFuture.create()");
        this.future = create;
        SettableFuture<ListenableWorker.Result> settableFuture = this.future;
        Runnable runnable = new Runnable() { // from class: androidx.work.CoroutineWorker.1
            @Override // java.lang.Runnable
            public final void run() {
                if (CoroutineWorker.this.getFuture$work_runtime_ktx_release().isCancelled()) {
                    Job.DefaultImpls.cancel$default((Job) CoroutineWorker.this.getJob$work_runtime_ktx_release(), (CancellationException) null, 1, (Object) null);
                }
            }
        };
        TaskExecutor taskExecutor = getTaskExecutor();
        Intrinsics.checkExpressionValueIsNotNull(taskExecutor, "taskExecutor");
        settableFuture.addListener(runnable, taskExecutor.getBackgroundExecutor());
        this.coroutineContext = Dispatchers.getDefault();
    }

    @Deprecated(message = "use withContext(...) inside doWork() instead.")
    public static /* synthetic */ void coroutineContext$annotations() {
    }

    @Nullable
    public abstract Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation);

    @NotNull
    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    @NotNull
    public final SettableFuture<ListenableWorker.Result> getFuture$work_runtime_ktx_release() {
        return this.future;
    }

    @NotNull
    public final CompletableJob getJob$work_runtime_ktx_release() {
        return this.job;
    }

    @Override // androidx.work.ListenableWorker
    public final void onStopped() {
        super.onStopped();
        this.future.cancel(false);
    }

    @Nullable
    public final Object setForeground(@NotNull ForegroundInfo foregroundInfo, @NotNull Continuation<? super Unit> continuation) {
        Object obj;
        Object coroutine_suspended;
        Continuation intercepted;
        Object coroutine_suspended2;
        final ListenableFuture<Void> foregroundAsync = setForegroundAsync(foregroundInfo);
        Intrinsics.checkExpressionValueIsNotNull(foregroundAsync, "setForegroundAsync(foregroundInfo)");
        if (foregroundAsync.isDone()) {
            try {
                obj = foregroundAsync.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        } else {
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
            foregroundAsync.addListener(new Runnable() { // from class: androidx.work.CoroutineWorker$await$$inlined$suspendCancellableCoroutine$lambda$2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                        V v = foregroundAsync.get();
                        Result.Companion companion = Result.Companion;
                        cancellableContinuation.resumeWith(Result.m10378constructorimpl(v));
                    } catch (Throwable th) {
                        Throwable cause2 = th.getCause();
                        if (cause2 == null) {
                            cause2 = th;
                        }
                        if (th instanceof CancellationException) {
                            CancellableContinuation.this.cancel(cause2);
                            return;
                        }
                        CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                        Result.Companion companion2 = Result.Companion;
                        cancellableContinuation2.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(cause2)));
                    }
                }
            }, DirectExecutor.INSTANCE);
            obj = cancellableContinuationImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (obj == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return obj == coroutine_suspended ? obj : Unit.INSTANCE;
    }

    @Nullable
    public final Object setProgress(@NotNull Data data, @NotNull Continuation<? super Unit> continuation) {
        Object obj;
        Object coroutine_suspended;
        Continuation intercepted;
        Object coroutine_suspended2;
        final ListenableFuture<Void> progressAsync = setProgressAsync(data);
        Intrinsics.checkExpressionValueIsNotNull(progressAsync, "setProgressAsync(data)");
        if (progressAsync.isDone()) {
            try {
                obj = progressAsync.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        } else {
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
            progressAsync.addListener(new Runnable() { // from class: androidx.work.CoroutineWorker$await$$inlined$suspendCancellableCoroutine$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                        V v = progressAsync.get();
                        Result.Companion companion = Result.Companion;
                        cancellableContinuation.resumeWith(Result.m10378constructorimpl(v));
                    } catch (Throwable th) {
                        Throwable cause2 = th.getCause();
                        if (cause2 == null) {
                            cause2 = th;
                        }
                        if (th instanceof CancellationException) {
                            CancellableContinuation.this.cancel(cause2);
                            return;
                        }
                        CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                        Result.Companion companion2 = Result.Companion;
                        cancellableContinuation2.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(cause2)));
                    }
                }
            }, DirectExecutor.INSTANCE);
            obj = cancellableContinuationImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (obj == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return obj == coroutine_suspended ? obj : Unit.INSTANCE;
    }

    @Override // androidx.work.ListenableWorker
    @NotNull
    public final ListenableFuture<ListenableWorker.Result> startWork() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(getCoroutineContext().plus(this.job)), null, null, new CoroutineWorker$startWork$1(this, null), 3, null);
        return this.future;
    }
}
