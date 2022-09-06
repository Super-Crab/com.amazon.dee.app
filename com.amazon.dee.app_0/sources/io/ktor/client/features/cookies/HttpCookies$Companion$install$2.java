package io.ktor.client.features.cookies;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponse;
import io.ktor.http.Cookie;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.Url;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Iterator;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponse;", "Lio/ktor/client/call/HttpClientCall;", "response", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.cookies.HttpCookies$Companion$install$2", f = "HttpCookies.kt", i = {0, 0, 0, 0}, l = {62}, m = "invokeSuspend", n = {"url", "$receiver$iv", "element$iv", "it"}, s = {"L$0", "L$1", "L$3", "L$4"})
/* loaded from: classes3.dex */
public final class HttpCookies$Companion$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponse, HttpClientCall>, HttpResponse, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpCookies $feature;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private PipelineContext p$;
    private HttpResponse p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$Companion$install$2(HttpCookies httpCookies, Continuation continuation) {
        super(3, continuation);
        this.$feature = httpCookies;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponse, HttpClientCall> receiver$0, @NotNull HttpResponse response, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(response, "response");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpCookies$Companion$install$2 httpCookies$Companion$install$2 = new HttpCookies$Companion$install$2(this.$feature, continuation);
        httpCookies$Companion$install$2.p$ = receiver$0;
        httpCookies$Companion$install$2.p$0 = response;
        return httpCookies$Companion$install$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponse, HttpClientCall> pipelineContext, HttpResponse httpResponse, Continuation<? super Unit> continuation) {
        return ((HttpCookies$Companion$install$2) create(pipelineContext, httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Url url;
        HttpCookies$Companion$install$2 httpCookies$Companion$install$2;
        Iterable iterable;
        Iterator it2;
        CookiesStorage cookiesStorage;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Cookie cookie = (Cookie) this.L$4;
            it2 = (Iterator) this.L$2;
            iterable = (Iterable) this.L$1;
            url = (Url) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            httpCookies$Companion$install$2 = this;
        } else if (!(obj instanceof Result.Failure)) {
            PipelineContext pipelineContext = this.p$;
            HttpResponse httpResponse = this.p$0;
            Url url2 = ((HttpClientCall) pipelineContext.getContext()).getRequest().getUrl();
            List<Cookie> cookie2 = HttpMessagePropertiesKt.setCookie(httpResponse);
            url = url2;
            httpCookies$Companion$install$2 = this;
            iterable = cookie2;
            it2 = cookie2.iterator();
        } else {
            throw ((Result.Failure) obj).exception;
        }
        while (it2.hasNext()) {
            Object next = it2.next();
            Cookie cookie3 = (Cookie) next;
            cookiesStorage = httpCookies$Companion$install$2.$feature.storage;
            httpCookies$Companion$install$2.L$0 = url;
            httpCookies$Companion$install$2.L$1 = iterable;
            httpCookies$Companion$install$2.L$2 = it2;
            httpCookies$Companion$install$2.L$3 = next;
            httpCookies$Companion$install$2.L$4 = cookie3;
            httpCookies$Companion$install$2.label = 1;
            if (cookiesStorage.addCookie(url, cookie3, httpCookies$Companion$install$2) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
