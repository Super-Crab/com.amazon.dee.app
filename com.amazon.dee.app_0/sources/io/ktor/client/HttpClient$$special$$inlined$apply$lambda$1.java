package io.ktor.client;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/ktor/client/HttpClient$sendPipeline$1$1"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.HttpClient$sendPipeline$1$1", f = "HttpClient.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {86, 97, 98}, m = "invokeSuspend", n = {"call", "requestData", "call", "requestData", "request", "response", "call", "requestData", "request", "response", "receivedCall"}, s = {"L$1", "L$2", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes3.dex */
public final class HttpClient$$special$$inlined$apply$lambda$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private PipelineContext p$;
    private Object p$0;
    final /* synthetic */ HttpClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HttpClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "cause", "", "invoke", "io/ktor/client/HttpClient$sendPipeline$1$1$1"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.client.HttpClient$$special$$inlined$apply$lambda$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        final /* synthetic */ HttpRequestData $requestData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HttpRequestData httpRequestData) {
            super(1);
            this.$requestData = httpRequestData;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            Job executionContext = this.$requestData.getExecutionContext();
            if (executionContext != null) {
                CompletableDeferred completableDeferred = (CompletableDeferred) executionContext;
                if (th == null) {
                    completableDeferred.complete(Unit.INSTANCE);
                    return;
                } else {
                    completableDeferred.completeExceptionally(th);
                    return;
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletableDeferred<kotlin.Unit>");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClient$$special$$inlined$apply$lambda$1(Continuation continuation, HttpClient httpClient) {
        super(3, continuation);
        this.this$0 = httpClient;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<Object, HttpRequestBuilder> receiver$0, @NotNull Object content, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpClient$$special$$inlined$apply$lambda$1 httpClient$$special$$inlined$apply$lambda$1 = new HttpClient$$special$$inlined$apply$lambda$1(continuation, this.this$0);
        httpClient$$special$$inlined$apply$lambda$1.p$ = receiver$0;
        httpClient$$special$$inlined$apply$lambda$1.p$0 = content;
        return httpClient$$special$$inlined$apply$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        return ((HttpClient$$special$$inlined$apply$lambda$1) create(pipelineContext, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0112 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient$$special$$inlined$apply$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
