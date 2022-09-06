package io.ktor.client.request;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.util.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0001\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/client/request/HttpRequestData;", "", "url", "Lio/ktor/http/Url;", MetricsConstants.NativeFetch.METHOD, "Lio/ktor/http/HttpMethod;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "body", "executionContext", "Lkotlinx/coroutines/Job;", "attributes", "Lio/ktor/util/Attributes;", "(Lio/ktor/http/Url;Lio/ktor/http/HttpMethod;Lio/ktor/http/Headers;Ljava/lang/Object;Lkotlinx/coroutines/Job;Lio/ktor/util/Attributes;)V", "getAttributes", "()Lio/ktor/util/Attributes;", "getBody", "()Ljava/lang/Object;", "getExecutionContext", "()Lkotlinx/coroutines/Job;", "getHeaders", "()Lio/ktor/http/Headers;", "getMethod", "()Lio/ktor/http/HttpMethod;", "getUrl", "()Lio/ktor/http/Url;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpRequestData {
    @NotNull
    private final Attributes attributes;
    @NotNull
    private final Object body;
    @NotNull
    private final Job executionContext;
    @NotNull
    private final Headers headers;
    @NotNull
    private final HttpMethod method;
    @NotNull
    private final Url url;

    public HttpRequestData(@NotNull Url url, @NotNull HttpMethod method, @NotNull Headers headers, @NotNull Object body, @NotNull Job executionContext, @NotNull Attributes attributes) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Intrinsics.checkParameterIsNotNull(executionContext, "executionContext");
        Intrinsics.checkParameterIsNotNull(attributes, "attributes");
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
        this.executionContext = executionContext;
        this.attributes = attributes;
    }

    @NotNull
    public final Attributes getAttributes() {
        return this.attributes;
    }

    @NotNull
    public final Object getBody() {
        return this.body;
    }

    @NotNull
    public final Job getExecutionContext() {
        return this.executionContext;
    }

    @NotNull
    public final Headers getHeaders() {
        return this.headers;
    }

    @NotNull
    public final HttpMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final Url getUrl() {
        return this.url;
    }
}
