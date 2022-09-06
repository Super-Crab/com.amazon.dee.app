package com.amazon.photos.uploader.internal.workers;

import androidx.work.ListenableWorker;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$2", f = "SchedulerWorker.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {104, 115}, m = "invokeSuspend", n = {"$this$coroutineScope", "startTime", "unblockedRequests", "$this$coroutineScope", "startTime", "unblockedRequests", "request", "requestFromDb", "workRequest"}, s = {"L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes13.dex */
public final class SchedulerWorker$mainTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ SchedulerWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SchedulerWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$2$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        final /* synthetic */ long $startTime;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j) {
            super(1);
            this.$startTime = j;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            if (th instanceof CancellationException) {
                SchedulerWorker$mainTask$2.this.this$0.getLogger().i$AndroidPhotosUploader_release(SchedulerWorker.TAG, "Scheduler Interrupted.");
                SchedulerWorker$mainTask$2.this.this$0.recordInterruptedMetric(System.currentTimeMillis() - this.$startTime);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerWorker$mainTask$2(SchedulerWorker schedulerWorker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerWorker$mainTask$2 schedulerWorker$mainTask$2 = new SchedulerWorker$mainTask$2(this.this$0, completion);
        schedulerWorker$mainTask$2.p$ = (CoroutineScope) obj;
        return schedulerWorker$mainTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((SchedulerWorker$mainTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0115 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0123  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00c8 -> B:29:0x0120). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00d0 -> B:29:0x0120). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0116 -> B:28:0x0119). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.internal.workers.SchedulerWorker$mainTask$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
