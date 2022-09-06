package io.ktor.client.request;

import com.amazon.alexa.routing.api.RouteParameter;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLProtocol;
import io.ktor.http.URLUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a&\u0010\u0007\u001a\u00020\u0002*\u00020\b2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\u0002\u001aP\u0010\u0007\u001a\u00020\u0002*\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u0019\b\u0002\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\u0002\u001a\u0012\u0010\u0010\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u0012\u0010\u0010\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0013\u001a#\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a\u0012\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u000b\u001aM\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u0019\b\u0002\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006¨\u0006\u0016"}, d2 = {HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/HeadersBuilder;", "Lio/ktor/client/request/HttpRequestBuilder;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "invoke", "Lio/ktor/client/request/HttpRequestBuilder$Companion;", "Lio/ktor/http/URLBuilder;", "scheme", "", "host", "port", "", RouteParameter.PATH, "takeFrom", "request", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/request/HttpRequestData;", "url", "urlString", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpRequestKt {
    @NotNull
    public static final HeadersBuilder headers(@NotNull HttpRequestBuilder receiver$0, @NotNull Function1<? super HeadersBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HeadersBuilder headers = receiver$0.getHeaders();
        block.mo12165invoke(headers);
        return headers;
    }

    @NotNull
    public static final HttpRequestBuilder invoke(@NotNull HttpRequestBuilder.Companion receiver$0, @NotNull Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        url(httpRequestBuilder, block);
        return httpRequestBuilder;
    }

    @NotNull
    public static /* synthetic */ HttpRequestBuilder invoke$default(HttpRequestBuilder.Companion companion, String str, String str2, int i, String str3, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "http";
        }
        if ((i2 & 2) != 0) {
            str2 = AndroidInfoHelpers.DEVICE_LOCALHOST;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = 0;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = "/";
        }
        String str5 = str3;
        if ((i2 & 16) != 0) {
            function1 = HttpRequestKt$invoke$2.INSTANCE;
        }
        return invoke(companion, str, str4, i3, str5, function1);
    }

    @NotNull
    public static final HttpRequestBuilder takeFrom(@NotNull HttpRequestBuilder receiver$0, @NotNull HttpRequest request) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(request, "request");
        receiver$0.setMethod(request.getMethod());
        receiver$0.setBody(request.getContent());
        URLUtilsKt.takeFrom(receiver$0.getUrl(), request.getUrl());
        receiver$0.getHeaders().appendAll(request.getHeaders());
        return receiver$0;
    }

    public static final void url(@NotNull HttpRequestBuilder receiver$0, @NotNull Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        block.mo12165invoke(receiver$0.getUrl());
    }

    public static /* synthetic */ void url$default(HttpRequestBuilder httpRequestBuilder, String str, String str2, int i, String str3, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "http";
        }
        if ((i2 & 2) != 0) {
            str2 = AndroidInfoHelpers.DEVICE_LOCALHOST;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = 0;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = "/";
        }
        String str5 = str3;
        if ((i2 & 16) != 0) {
            function1 = HttpRequestKt$url$1.INSTANCE;
        }
        url(httpRequestBuilder, str, str4, i3, str5, function1);
    }

    @NotNull
    public static final HttpRequestBuilder invoke(@NotNull HttpRequestBuilder.Companion receiver$0, @NotNull String scheme, @NotNull String host, int i, @NotNull String path, @NotNull Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(scheme, "scheme");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        url(httpRequestBuilder, scheme, host, i, path, block);
        return httpRequestBuilder;
    }

    public static final void url(@NotNull HttpRequestBuilder receiver$0, @NotNull String scheme, @NotNull String host, int i, @NotNull String path, @NotNull Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(scheme, "scheme");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(block, "block");
        URLBuilder url = receiver$0.getUrl();
        url.setProtocol(URLProtocol.Companion.createOrDefault(scheme));
        url.setHost(host);
        url.setPort(i);
        url.setEncodedPath(path);
        block.mo12165invoke(receiver$0.getUrl());
    }

    @NotNull
    public static final HttpRequestBuilder takeFrom(@NotNull HttpRequestBuilder receiver$0, @NotNull HttpRequestData request) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(request, "request");
        receiver$0.setMethod(request.getMethod());
        receiver$0.setBody(request.getBody());
        URLUtilsKt.takeFrom(receiver$0.getUrl(), request.getUrl());
        receiver$0.getHeaders().appendAll(request.getHeaders());
        return receiver$0;
    }

    public static final void url(@NotNull HttpRequestBuilder receiver$0, @NotNull String urlString) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        URLParserKt.takeFrom(receiver$0.getUrl(), urlString);
    }
}
