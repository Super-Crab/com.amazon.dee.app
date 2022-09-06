package io.ktor.client.features;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
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
/* compiled from: HttpSend.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.HttpSend$Feature$install$1", f = "HttpSend.kt", i = {0, 1, 1, 1, 1, 2, 2}, l = {58, 65, 74}, m = "invokeSuspend", n = {"sender", "sender", PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY, "callChanged", "interceptor", "sender", PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY}, s = {"L$1", "L$1", "L$2", "I$0", "L$3", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class HttpSend$Feature$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpSend $feature;
    final /* synthetic */ HttpClient $scope;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private PipelineContext p$;
    private Object p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpSend$Feature$install$1(HttpSend httpSend, HttpClient httpClient, Continuation continuation) {
        super(3, continuation);
        this.$feature = httpSend;
        this.$scope = httpClient;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<Object, HttpRequestBuilder> receiver$0, @NotNull Object content, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpSend$Feature$install$1 httpSend$Feature$install$1 = new HttpSend$Feature$install$1(this.$feature, this.$scope, continuation);
        httpSend$Feature$install$1.p$ = receiver$0;
        httpSend$Feature$install$1.p$0 = content;
        return httpSend$Feature$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        return ((HttpSend$Feature$install$1) create(pipelineContext, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ee  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00a0 -> B:35:0x00b2). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00d3 -> B:41:0x00d8). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.HttpSend$Feature$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
