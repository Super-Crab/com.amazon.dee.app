package com.amazon.photos.uploader.blockers;

import androidx.work.Operation;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BackoffBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/work/Operation;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator$scheduleWakeup$1", f = "BackoffBlockerEvaluator.kt", i = {0}, l = {212}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class BackoffBlockerEvaluator$scheduleWakeup$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Operation>, Object> {
    final /* synthetic */ long $backOffTime;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ BackoffBlockerEvaluator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackoffBlockerEvaluator$scheduleWakeup$1(BackoffBlockerEvaluator backoffBlockerEvaluator, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = backoffBlockerEvaluator;
        this.$backOffTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BackoffBlockerEvaluator$scheduleWakeup$1 backoffBlockerEvaluator$scheduleWakeup$1 = new BackoffBlockerEvaluator$scheduleWakeup$1(this.this$0, this.$backOffTime, completion);
        backoffBlockerEvaluator$scheduleWakeup$1.p$ = (CoroutineScope) obj;
        return backoffBlockerEvaluator$scheduleWakeup$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Operation> continuation) {
        return ((BackoffBlockerEvaluator$scheduleWakeup$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            long j = this.$backOffTime;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return this.this$0.getSchedulingCallback().reevaluateBlockers();
    }
}
