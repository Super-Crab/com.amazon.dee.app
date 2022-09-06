package io.ktor.client.response;

import io.ktor.client.call.HttpClientCall;
import io.ktor.http.HttpMessage;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import java.io.Closeable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00060\u0003j\u0002`\u0004J\b\u0010!\u001a\u00020\"H\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000e8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lio/ktor/client/response/HttpResponse;", "Lio/ktor/http/HttpMessage;", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "call", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "content", "Lkotlinx/coroutines/io/ByteReadChannel;", "getContent", "()Lkotlinx/coroutines/io/ByteReadChannel;", "executionContext", "Lkotlinx/coroutines/Job;", "executionContext$annotations", "()V", "getExecutionContext", "()Lkotlinx/coroutines/Job;", "requestTime", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "responseTime", "getResponseTime", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "version", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "close", "", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface HttpResponse extends HttpMessage, CoroutineScope, Closeable {

    /* compiled from: HttpResponse.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void close(HttpResponse httpResponse) {
            CoroutineContext.Element element = httpResponse.getCoroutineContext().get(Job.Key);
            if (element != null) {
                ((CompletableDeferred) element).complete(Unit.INSTANCE);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletableDeferred<kotlin.Unit>");
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "executionContext is deprecated. Use coroutineContext instead.", replaceWith = @ReplaceWith(expression = "coroutineContext", imports = {}))
        public static /* synthetic */ void executionContext$annotations() {
        }

        @NotNull
        public static Job getExecutionContext(HttpResponse httpResponse) {
            CoroutineContext.Element element = httpResponse.getCoroutineContext().get(Job.Key);
            if (element == null) {
                Intrinsics.throwNpe();
            }
            return (Job) element;
        }
    }

    void close();

    @NotNull
    HttpClientCall getCall();

    @NotNull
    ByteReadChannel getContent();

    @NotNull
    Job getExecutionContext();

    @NotNull
    GMTDate getRequestTime();

    @NotNull
    GMTDate getResponseTime();

    @NotNull
    HttpStatusCode getStatus();

    @NotNull
    HttpProtocolVersion getVersion();
}
