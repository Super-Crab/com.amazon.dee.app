package io.ktor.client.engine.android;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: AndroidHttpRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lio/ktor/client/engine/android/AndroidHttpRequest;", "Lio/ktor/client/request/HttpRequest;", "call", "Lio/ktor/client/call/HttpClientCall;", "data", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/request/HttpRequestData;)V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "content", "Lio/ktor/http/content/OutgoingContent;", "getContent", "()Lio/ktor/http/content/OutgoingContent;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", MetricsConstants.NativeFetch.METHOD, "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "url", "Lio/ktor/http/Url;", "getUrl", "()Lio/ktor/http/Url;", "ktor-client-android"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AndroidHttpRequest implements HttpRequest {
    @NotNull
    private final Attributes attributes;
    @NotNull
    private final HttpClientCall call;
    @NotNull
    private final OutgoingContent content;
    @NotNull
    private final Headers headers;
    @NotNull
    private final HttpMethod method;
    @NotNull
    private final Url url;

    public AndroidHttpRequest(@NotNull HttpClientCall call, @NotNull HttpRequestData data) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.call = call;
        this.url = data.getUrl();
        Object body = data.getBody();
        if (body != null) {
            this.content = (OutgoingContent) body;
            this.headers = data.getHeaders();
            this.method = data.getMethod();
            this.attributes = data.getAttributes();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type io.ktor.http.content.OutgoingContent");
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public OutgoingContent getContent() {
        return this.content;
    }

    @Override // io.ktor.client.request.HttpRequest, kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return HttpRequest.DefaultImpls.getCoroutineContext(this);
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public Job getExecutionContext() {
        return HttpRequest.DefaultImpls.getExecutionContext(this);
    }

    @Override // io.ktor.http.HttpMessage
    @NotNull
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.ktor.client.request.HttpRequest
    @NotNull
    public Url getUrl() {
        return this.url;
    }
}
