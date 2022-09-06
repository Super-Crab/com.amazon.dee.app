package com.amazon.photos.uploader.internal.workers;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.DirectExecutor;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CoroutineWorkerUtil.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\bJ#\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;", "", "()V", "awaitResult", "", "result", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/work/Operation$State$SUCCESS;", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForWorker", "workManager", "Landroidx/work/WorkManager;", "maxWorkers", "", "waitForWorker$AndroidPhotosUploader_release", "(Landroidx/work/WorkManager;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "WorkInfoObserver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CoroutineWorkerUtil {

    /* compiled from: CoroutineWorkerUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil$WorkInfoObserver;", "Landroidx/lifecycle/Observer;", "", "Landroidx/work/WorkInfo;", "countDownLatch", "Ljava/util/concurrent/CountDownLatch;", "uploadWorkLiveData", "Landroidx/lifecycle/LiveData;", "maxWorkers", "", "(Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;Ljava/util/concurrent/CountDownLatch;Landroidx/lifecycle/LiveData;I)V", "onChanged", "", "list", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class WorkInfoObserver implements Observer<List<? extends WorkInfo>> {
        private final CountDownLatch countDownLatch;
        private final int maxWorkers;
        final /* synthetic */ CoroutineWorkerUtil this$0;
        private final LiveData<List<WorkInfo>> uploadWorkLiveData;

        public WorkInfoObserver(@NotNull CoroutineWorkerUtil coroutineWorkerUtil, @NotNull CountDownLatch countDownLatch, LiveData<List<WorkInfo>> uploadWorkLiveData, int i) {
            Intrinsics.checkParameterIsNotNull(countDownLatch, "countDownLatch");
            Intrinsics.checkParameterIsNotNull(uploadWorkLiveData, "uploadWorkLiveData");
            this.this$0 = coroutineWorkerUtil;
            this.countDownLatch = countDownLatch;
            this.uploadWorkLiveData = uploadWorkLiveData;
            this.maxWorkers = i;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(List<? extends WorkInfo> list) {
            onChanged2((List<WorkInfo>) list);
        }

        /* renamed from: onChanged  reason: avoid collision after fix types in other method */
        public void onChanged2(@Nullable List<WorkInfo> list) {
            if (list != null) {
                int i = 0;
                if (!list.isEmpty()) {
                    for (WorkInfo workInfo : list) {
                        if (SchedulerWorkerKt.IN_PROGRESS_WORKER_STATES.contains(workInfo.getState()) && (i = i + 1) < 0) {
                            CollectionsKt__CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                if (i >= this.maxWorkers) {
                    return;
                }
                this.countDownLatch.countDown();
                this.uploadWorkLiveData.removeObserver(this);
            }
        }
    }

    @Nullable
    public final Object awaitResult(@NotNull final ListenableFuture<Operation.State.SUCCESS> listenableFuture, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        listenableFuture.addListener(new Runnable() { // from class: com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil$awaitResult$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    V v = listenableFuture.get();
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m10378constructorimpl(v));
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    Throwable cause = th.getCause();
                    if (cause == null) {
                        cause = th;
                    }
                    if (th instanceof CancellationException) {
                        Boolean.valueOf(CancellableContinuation.this.cancel(cause));
                        return;
                    }
                    CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                    Result.Companion companion2 = Result.Companion;
                    cancellableContinuation2.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(cause)));
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }, DirectExecutor.INSTANCE);
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return result == coroutine_suspended2 ? result : Unit.INSTANCE;
    }

    @Nullable
    public final Object waitForWorker$AndroidPhotosUploader_release(@NotNull WorkManager workManager, int i, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        final LiveData<List<WorkInfo>> workInfosByTagLiveData = workManager.getWorkInfosByTagLiveData(SchedulerWorkerKt.UPLOAD_TAG);
        Intrinsics.checkExpressionValueIsNotNull(workInfosByTagLiveData, "workManager.getWorkInfosByTagLiveData(UPLOAD_TAG)");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final WorkInfoObserver workInfoObserver = new WorkInfoObserver(this, countDownLatch, workInfosByTagLiveData, i);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil$waitForWorker$2
            @Override // java.lang.Runnable
            public final void run() {
                LiveData.this.observeForever(workInfoObserver);
            }
        });
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new CoroutineWorkerUtil$waitForWorker$3(countDownLatch, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return withContext == coroutine_suspended ? withContext : Unit.INSTANCE;
    }
}
