package io.ktor.client.features;

import com.amazon.alexa.mobilytics.event.LogLevel;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultTransform.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.DefaultTransformKt$defaultTransformers$2", f = "DefaultTransform.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3}, l = {39, 41, 43, 44}, m = "invokeSuspend", n = {"$info_response", LogLevel.INFO, "response", "contentLength", "$info_response", LogLevel.INFO, "response", "contentLength", "$info_response", LogLevel.INFO, "response", "contentLength", "$info_response", LogLevel.INFO, "response", "contentLength", "readRemaining"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0", "L$3"})
/* loaded from: classes3.dex */
public final class DefaultTransformKt$defaultTransformers$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private PipelineContext p$;
    private HttpResponseContainer p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultTransformKt$defaultTransformers$2(Continuation continuation) {
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponseContainer, HttpClientCall> receiver$0, @NotNull HttpResponseContainer httpResponseContainer, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(httpResponseContainer, "<name for destructuring parameter 0>");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        DefaultTransformKt$defaultTransformers$2 defaultTransformKt$defaultTransformers$2 = new DefaultTransformKt$defaultTransformers$2(continuation);
        defaultTransformKt$defaultTransformers$2.p$ = receiver$0;
        defaultTransformKt$defaultTransformers$2.p$0 = httpResponseContainer;
        return defaultTransformKt$defaultTransformers$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        return ((DefaultTransformKt$defaultTransformers$2) create(pipelineContext, httpResponseContainer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0154 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.DefaultTransformKt$defaultTransformers$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
