package io.ktor.client.features;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
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
/* compiled from: DefaultTransform.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "body", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.DefaultTransformKt$defaultTransformers$1", f = "DefaultTransform.kt", i = {}, l = {25}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class DefaultTransformKt$defaultTransformers$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    int label;
    private PipelineContext p$;
    private Object p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultTransformKt$defaultTransformers$1(Continuation continuation) {
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<Object, HttpRequestBuilder> receiver$0, @NotNull Object body, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        DefaultTransformKt$defaultTransformers$1 defaultTransformKt$defaultTransformers$1 = new DefaultTransformKt$defaultTransformers$1(continuation);
        defaultTransformKt$defaultTransformers$1.p$ = receiver$0;
        defaultTransformKt$defaultTransformers$1.p$0 = body;
        return defaultTransformKt$defaultTransformers$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        return ((DefaultTransformKt$defaultTransformers$1) create(pipelineContext, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            PipelineContext pipelineContext = this.p$;
            Object obj2 = this.p$0;
            if (((HttpRequestBuilder) pipelineContext.getContext()).getHeaders().get(HttpHeaders.INSTANCE.getAccept()) == null) {
                ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders().append(HttpHeaders.INSTANCE.getAccept(), "*/*");
            }
            if (obj2 instanceof byte[]) {
                OutgoingContent.ByteArrayContent byteArrayContent = new OutgoingContent.ByteArrayContent(obj2) { // from class: io.ktor.client.features.DefaultTransformKt$defaultTransformers$1.1
                    final /* synthetic */ Object $body;
                    private final long contentLength;

                    {
                        this.$body = obj2;
                        this.contentLength = ((byte[]) obj2).length;
                    }

                    @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
                    @NotNull
                    public byte[] bytes() {
                        return (byte[]) this.$body;
                    }

                    @Override // io.ktor.http.content.OutgoingContent
                    @NotNull
                    public Long getContentLength() {
                        return Long.valueOf(this.contentLength);
                    }
                };
                this.label = 1;
                if (pipelineContext.proceedWith(byteArrayContent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return Unit.INSTANCE;
    }
}
