package io.ktor.client.features.cookies;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.Url;
import io.ktor.util.pipeline.PipelineContext;
import java.util.List;
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
/* compiled from: HttpCookies.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.cookies.HttpCookies$Companion$install$1", f = "HttpCookies.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class HttpCookies$Companion$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpCookies $feature;
    Object L$0;
    int label;
    private PipelineContext p$;
    private Object p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$Companion$install$1(HttpCookies httpCookies, Continuation continuation) {
        super(3, continuation);
        this.$feature = httpCookies;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<Object, HttpRequestBuilder> receiver$0, @NotNull Object it2, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(it2, "it");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpCookies$Companion$install$1 httpCookies$Companion$install$1 = new HttpCookies$Companion$install$1(this.$feature, continuation);
        httpCookies$Companion$install$1.p$ = receiver$0;
        httpCookies$Companion$install$1.p$0 = it2;
        return httpCookies$Companion$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        return ((HttpCookies$Companion$install$1) create(pipelineContext, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        PipelineContext pipelineContext;
        String renderClientCookies;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            pipelineContext = (PipelineContext) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            PipelineContext pipelineContext2 = this.p$;
            HttpCookies httpCookies = this.$feature;
            Url build = URLBuilderKt.clone(((HttpRequestBuilder) pipelineContext2.getContext()).getUrl()).build();
            this.L$0 = pipelineContext2;
            this.label = 1;
            Object obj2 = httpCookies.get(build, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            pipelineContext = pipelineContext2;
            obj = obj2;
        } else {
            throw ((Result.Failure) obj).exception;
        }
        HeadersBuilder headers = ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders();
        String cookie = HttpHeaders.INSTANCE.getCookie();
        renderClientCookies = HttpCookiesKt.renderClientCookies((List) obj);
        headers.set(cookie, renderClientCookies);
        return Unit.INSTANCE;
    }
}
