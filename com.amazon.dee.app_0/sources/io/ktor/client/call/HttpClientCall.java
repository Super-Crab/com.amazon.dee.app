package io.ktor.client.call;

import com.amazon.alexa.mobilytics.event.LogLevel;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.response.HttpResponse;
import io.ktor.client.response.HttpResponseConfig;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010 \u001a\u00020!H\u0016J\u0019\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@@X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0016@@X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lio/ktor/client/call/HttpClientCall;", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "client", "Lio/ktor/client/HttpClient;", "(Lio/ktor/client/HttpClient;)V", "getClient", "()Lio/ktor/client/HttpClient;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "received", "Lkotlinx/atomicfu/AtomicBoolean;", "<set-?>", "Lio/ktor/client/request/HttpRequest;", "request", "getRequest", "()Lio/ktor/client/request/HttpRequest;", "setRequest$ktor_client_core", "(Lio/ktor/client/request/HttpRequest;)V", "Lio/ktor/client/response/HttpResponse;", "response", "getResponse", "()Lio/ktor/client/response/HttpResponse;", "setResponse$ktor_client_core", "(Lio/ktor/client/response/HttpResponse;)V", "responseConfig", "Lio/ktor/client/response/HttpResponseConfig;", "getResponseConfig", "()Lio/ktor/client/response/HttpResponseConfig;", "close", "", "receive", "", LogLevel.INFO, "Lio/ktor/client/call/TypeInfo;", "(Lio/ktor/client/call/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientCall implements CoroutineScope, Closeable {
    private static final AtomicIntegerFieldUpdater received$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCall.class, "received");
    @NotNull
    private final HttpClient client;
    private volatile int received;
    @NotNull
    public HttpRequest request;
    @NotNull
    public HttpResponse response;
    @NotNull
    private final HttpResponseConfig responseConfig;

    public HttpClientCall(@NotNull HttpClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
        this.received = 0;
        this.responseConfig = this.client.getEngineConfig().getResponse();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        HttpResponse httpResponse = this.response;
        if (httpResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("response");
        }
        httpResponse.close();
    }

    @NotNull
    public final HttpClient getClient() {
        return this.client;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        HttpResponse httpResponse = this.response;
        if (httpResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("response");
        }
        return httpResponse.getCoroutineContext();
    }

    @NotNull
    public final HttpRequest getRequest() {
        HttpRequest httpRequest = this.request;
        if (httpRequest == null) {
            Intrinsics.throwUninitializedPropertyAccessException("request");
        }
        return httpRequest;
    }

    @NotNull
    public final HttpResponse getResponse() {
        HttpResponse httpResponse = this.response;
        if (httpResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("response");
        }
        return httpResponse;
    }

    @NotNull
    public final HttpResponseConfig getResponseConfig() {
        return this.responseConfig;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a4 A[Catch: all -> 0x003d, BadResponseStatusException -> 0x0040, TryCatch #1 {BadResponseStatusException -> 0x0040, blocks: (B:12:0x0033, B:44:0x0093, B:47:0x00a4, B:48:0x00b5, B:15:0x0038, B:16:0x003c, B:40:0x007d), top: B:63:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00be  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object receive(@org.jetbrains.annotations.NotNull io.ktor.client.call.TypeInfo r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<java.lang.Object> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.client.call.HttpClientCall$receive$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.call.HttpClientCall$receive$1 r0 = (io.ktor.client.call.HttpClientCall$receive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.call.HttpClientCall$receive$1 r0 = new io.ktor.client.call.HttpClientCall$receive$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            java.lang.String r4 = "response"
            if (r2 == 0) goto L4b
            if (r2 != r3) goto L43
            java.lang.Object r6 = r0.L$2
            io.ktor.client.response.HttpResponseContainer r6 = (io.ktor.client.response.HttpResponseContainer) r6
            java.lang.Object r6 = r0.L$1
            io.ktor.client.call.TypeInfo r6 = (io.ktor.client.call.TypeInfo) r6
            java.lang.Object r0 = r0.L$0
            io.ktor.client.call.HttpClientCall r0 = (io.ktor.client.call.HttpClientCall) r0
            boolean r1 = r7 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            if (r1 != 0) goto L38
            goto L93
        L38:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            java.lang.Throwable r7 = r7.exception     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            throw r7     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
        L3d:
            r7 = move-exception
            goto Lb8
        L40:
            r6 = move-exception
            goto Lc9
        L43:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L4b:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Ld0
            kotlin.reflect.KClass r7 = r6.getType()
            io.ktor.client.response.HttpResponse r2 = r5.response
            if (r2 != 0) goto L5a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L5a:
            boolean r7 = r7.isInstance(r2)
            if (r7 == 0) goto L68
            io.ktor.client.response.HttpResponse r6 = r5.response
            if (r6 != 0) goto L67
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L67:
            return r6
        L68:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = io.ktor.client.call.HttpClientCall.received$FU
            r2 = 0
            boolean r7 = r7.compareAndSet(r5, r2, r3)
            if (r7 == 0) goto Lca
            io.ktor.client.response.HttpResponseContainer r7 = new io.ktor.client.response.HttpResponseContainer
            io.ktor.client.response.HttpResponse r2 = r5.response
            if (r2 != 0) goto L7a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L7a:
            r7.<init>(r6, r2)
            io.ktor.client.HttpClient r2 = r5.client     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            io.ktor.client.response.HttpResponsePipeline r2 = r2.getResponsePipeline()     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            r0.L$0 = r5     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            r0.L$1 = r6     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            r0.L$2 = r7     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            r0.label = r3     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            java.lang.Object r7 = r2.execute(r5, r7, r0)     // Catch: io.ktor.client.features.BadResponseStatusException -> L40 java.lang.Throwable -> Lb6
            if (r7 != r1) goto L92
            return r1
        L92:
            r0 = r5
        L93:
            io.ktor.client.response.HttpResponseContainer r7 = (io.ktor.client.response.HttpResponseContainer) r7     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            java.lang.Object r7 = r7.getResponse()     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            kotlin.reflect.KClass r1 = r6.getType()     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            boolean r1 = r1.isInstance(r7)     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            if (r1 == 0) goto La4
            return r7
        La4:
            io.ktor.client.call.NoTransformationFoundException r1 = new io.ktor.client.call.NoTransformationFoundException     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            java.lang.Class r7 = r7.getClass()     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            kotlin.reflect.KClass r2 = r6.getType()     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            r1.<init>(r7, r2)     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
            throw r1     // Catch: java.lang.Throwable -> L3d io.ktor.client.features.BadResponseStatusException -> L40
        Lb6:
            r7 = move-exception
            r0 = r5
        Lb8:
            io.ktor.client.call.ReceivePipelineException r1 = new io.ktor.client.call.ReceivePipelineException
            io.ktor.client.response.HttpResponse r0 = r0.response
            if (r0 != 0) goto Lc1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        Lc1:
            io.ktor.client.call.HttpClientCall r0 = r0.getCall()
            r1.<init>(r0, r6, r7)
            throw r1
        Lc9:
            throw r6
        Lca:
            io.ktor.client.call.DoubleReceiveException r6 = new io.ktor.client.call.DoubleReceiveException
            r6.<init>(r5)
            throw r6
        Ld0:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.HttpClientCall.receive(io.ktor.client.call.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setRequest$ktor_client_core(@NotNull HttpRequest httpRequest) {
        Intrinsics.checkParameterIsNotNull(httpRequest, "<set-?>");
        this.request = httpRequest;
    }

    public final void setResponse$ktor_client_core(@NotNull HttpResponse httpResponse) {
        Intrinsics.checkParameterIsNotNull(httpResponse, "<set-?>");
        this.response = httpResponse;
    }
}
