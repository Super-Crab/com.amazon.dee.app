package io.ktor.client.features;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponse;
import io.ktor.client.response.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExpectSuccess.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.ExpectSuccess$Companion$install$1", f = "ExpectSuccess.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class ExpectSuccess$Companion$install$1 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    int label;
    private PipelineContext p$;
    private HttpResponseContainer p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpectSuccess$Companion$install$1(Continuation continuation) {
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponseContainer, HttpClientCall> receiver$0, @NotNull HttpResponseContainer it2, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(it2, "it");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        ExpectSuccess$Companion$install$1 expectSuccess$Companion$install$1 = new ExpectSuccess$Companion$install$1(continuation);
        expectSuccess$Companion$install$1.p$ = receiver$0;
        expectSuccess$Companion$install$1.p$0 = it2;
        return expectSuccess$Companion$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        return ((ExpectSuccess$Companion$install$1) create(pipelineContext, httpResponseContainer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            if (!(obj instanceof Result.Failure)) {
                HttpResponse response = ((HttpClientCall) this.p$.getContext()).getResponse();
                if (response.getStatus().getValue() < 300) {
                    return Unit.INSTANCE;
                }
                throw new BadResponseStatusException(response.getStatus(), response);
            }
            throw ((Result.Failure) obj).exception;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
