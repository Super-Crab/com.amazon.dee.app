package io.ktor.client.call;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001aH\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072)\b\u0002\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aH\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102)\b\u0002\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"call", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/HttpClient;", "builder", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "url", "Lio/ktor/http/Url;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlString", "", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt {
    @Nullable
    public static final Object call(@NotNull HttpClient httpClient, @NotNull HttpRequestBuilder httpRequestBuilder, @NotNull Continuation<? super HttpClientCall> continuation) {
        return HttpClientCallKt.call(httpClient, new UtilsKt$call$2(httpRequestBuilder, null), continuation);
    }

    @Nullable
    public static /* synthetic */ Object call$default(HttpClient httpClient, String str, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new UtilsKt$call$4(null);
        }
        return call(httpClient, str, function2, continuation);
    }

    @Nullable
    public static final Object call(@NotNull HttpClient httpClient, @NotNull String str, @NotNull Function2<? super HttpRequestBuilder, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super HttpClientCall> continuation) {
        return HttpClientCallKt.call(httpClient, new UtilsKt$call$5(str, function2, null), continuation);
    }

    @Nullable
    public static /* synthetic */ Object call$default(HttpClient httpClient, Url url, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new UtilsKt$call$7(null);
        }
        return call(httpClient, url, function2, continuation);
    }

    @Nullable
    public static final Object call(@NotNull HttpClient httpClient, @NotNull Url url, @NotNull Function2<? super HttpRequestBuilder, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super HttpClientCall> continuation) {
        return HttpClientCallKt.call(httpClient, new UtilsKt$call$8(url, function2, null), continuation);
    }
}
