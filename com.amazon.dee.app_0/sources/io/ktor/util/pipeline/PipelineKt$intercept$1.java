package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Pipeline.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u0010\u0012\u0006\b\u0001\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "TSubject", "", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "subject", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.pipeline.PipelineKt$intercept$1", f = "Pipeline.kt", i = {0}, l = {465}, m = "invokeSuspend", n = {"reinterpret"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class PipelineKt$intercept$1 extends SuspendLambda implements Function3<PipelineContext<? extends Object, TContext>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $block;
    Object L$0;
    int label;
    private PipelineContext p$;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PipelineKt$intercept$1(Function3 function3, Continuation continuation) {
        super(3, continuation);
        this.$block = function3;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<? extends Object, TContext> receiver$0, @NotNull Object subject, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        PipelineKt$intercept$1 pipelineKt$intercept$1 = new PipelineKt$intercept$1(this.$block, continuation);
        pipelineKt$intercept$1.p$ = receiver$0;
        pipelineKt$intercept$1.p$0 = subject;
        return pipelineKt$intercept$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Continuation<? super Unit> continuation) {
        return ((PipelineKt$intercept$1) create((PipelineContext) obj, obj2, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            if (!(obj instanceof Result.Failure)) {
                PipelineContext pipelineContext = this.p$;
                Object obj2 = this.p$0;
                Intrinsics.reifiedOperationMarker(2, "TSubject");
                if (obj2 != null) {
                    if (!(pipelineContext instanceof PipelineContext)) {
                        pipelineContext = null;
                    }
                    if (pipelineContext != null) {
                        Function3 function3 = this.$block;
                        this.L$0 = pipelineContext;
                        this.label = 1;
                        obj = function3.invoke(pipelineContext, obj2, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw ((Result.Failure) obj).exception;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        }
        Unit unit = (Unit) obj;
        return Unit.INSTANCE;
    }
}
