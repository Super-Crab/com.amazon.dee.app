package io.ktor.client.engine.android;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: AndroidHttpResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010#\u001a\u00020$H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lio/ktor/client/engine/android/AndroidHttpResponse;", "Lio/ktor/client/response/HttpResponse;", "call", "Lio/ktor/client/call/HttpClientCall;", "content", "Lkotlinx/coroutines/io/ByteReadChannel;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "requestTime", "Lio/ktor/util/date/GMTDate;", "responseTime", "status", "Lio/ktor/http/HttpStatusCode;", "version", "Lio/ktor/http/HttpProtocolVersion;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "connection", "Ljava/net/HttpURLConnection;", "(Lio/ktor/client/call/HttpClientCall;Lkotlinx/coroutines/io/ByteReadChannel;Lio/ktor/http/Headers;Lio/ktor/util/date/GMTDate;Lio/ktor/util/date/GMTDate;Lio/ktor/http/HttpStatusCode;Lio/ktor/http/HttpProtocolVersion;Lkotlin/coroutines/CoroutineContext;Ljava/net/HttpURLConnection;)V", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "getContent", "()Lkotlinx/coroutines/io/ByteReadChannel;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getHeaders", "()Lio/ktor/http/Headers;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "getResponseTime", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "close", "", "ktor-client-android"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AndroidHttpResponse implements HttpResponse {
    @NotNull
    private final HttpClientCall call;
    private final HttpURLConnection connection;
    @NotNull
    private final ByteReadChannel content;
    @NotNull
    private final CoroutineContext coroutineContext;
    @NotNull
    private final Headers headers;
    @NotNull
    private final GMTDate requestTime;
    @NotNull
    private final GMTDate responseTime;
    @NotNull
    private final HttpStatusCode status;
    @NotNull
    private final HttpProtocolVersion version;

    public AndroidHttpResponse(@NotNull HttpClientCall call, @NotNull ByteReadChannel content, @NotNull Headers headers, @NotNull GMTDate requestTime, @NotNull GMTDate responseTime, @NotNull HttpStatusCode status, @NotNull HttpProtocolVersion version, @NotNull CoroutineContext coroutineContext, @NotNull HttpURLConnection connection) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(requestTime, "requestTime");
        Intrinsics.checkParameterIsNotNull(responseTime, "responseTime");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        this.call = call;
        this.content = content;
        this.headers = headers;
        this.requestTime = requestTime;
        this.responseTime = responseTime;
        this.status = status;
        this.version = version;
        this.coroutineContext = coroutineContext;
        this.connection = connection;
    }

    @Override // io.ktor.client.response.HttpResponse, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        HttpResponse.DefaultImpls.close(this);
        Job job = (Job) getCoroutineContext().get(Job.Key);
        if (job != null) {
            job.invokeOnCompletion(new AndroidHttpResponse$close$1(this));
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
        return this.coroutineContext;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public Job getExecutionContext() {
        return HttpResponse.DefaultImpls.getExecutionContext(this);
    }

    @Override // io.ktor.http.HttpMessage
    @NotNull
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public GMTDate getRequestTime() {
        return this.requestTime;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public GMTDate getResponseTime() {
        return this.responseTime;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public HttpStatusCode getStatus() {
        return this.status;
    }

    @Override // io.ktor.client.response.HttpResponse
    @NotNull
    public HttpProtocolVersion getVersion() {
        return this.version;
    }
}
