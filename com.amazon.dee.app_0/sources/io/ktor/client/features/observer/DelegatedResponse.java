package io.ktor.client.features.observer;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: DelegatedCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0001¢\u0006\u0002\u0010\tJ\b\u0010(\u001a\u00020)H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u00020!X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0012\u0010$\u001a\u00020%X\u0096\u0005¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lio/ktor/client/features/observer/DelegatedResponse;", "Lio/ktor/client/response/HttpResponse;", "content", "Lkotlinx/coroutines/io/ByteReadChannel;", "call", "Lio/ktor/client/call/HttpClientCall;", "shouldClose", "", "origin", "(Lkotlinx/coroutines/io/ByteReadChannel;Lio/ktor/client/call/HttpClientCall;ZLio/ktor/client/response/HttpResponse;)V", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "getContent", "()Lkotlinx/coroutines/io/ByteReadChannel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "executionContext", "Lkotlinx/coroutines/Job;", "getExecutionContext", "()Lkotlinx/coroutines/Job;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "requestTime", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "responseTime", "getResponseTime", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "version", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "close", "", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DelegatedResponse implements HttpResponse {
    private final /* synthetic */ HttpResponse $$delegate_0;
    @NotNull
    private final HttpClientCall call;
    @NotNull
    private final ByteReadChannel content;
    private final boolean shouldClose;

    public DelegatedResponse(@NotNull ByteReadChannel content, @NotNull HttpClientCall call, boolean z, @NotNull HttpResponse origin) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        this.$$delegate_0 = origin;
        this.content = content;
        this.call = call;
        this.shouldClose = z;
    }

    @Override // io.ktor.client.response.HttpResponse, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.shouldClose) {
            HttpResponse.DefaultImpls.close(this);
        }
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public ByteReadChannel getContent() {
        return this.content;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public Job getExecutionContext() {
        return this.$$delegate_0.getExecutionContext();
    }

    @Override // io.ktor.http.HttpMessage
    @NotNull
    public Headers getHeaders() {
        return this.$$delegate_0.getHeaders();
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public GMTDate getRequestTime() {
        return this.$$delegate_0.getRequestTime();
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public GMTDate getResponseTime() {
        return this.$$delegate_0.getResponseTime();
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public HttpStatusCode getStatus() {
        return this.$$delegate_0.getStatus();
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public HttpProtocolVersion getVersion() {
        return this.$$delegate_0.getVersion();
    }
}
