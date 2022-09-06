package okhttp3;

import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.platform.Platform;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
/* compiled from: RealCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010\u001f\u001a\u00020\u0007H\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0005H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0006\u0010%\u001a\u00020!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lokhttp3/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "getClient", "()Lokhttp3/OkHttpClient;", "executed", "getExecuted", "()Z", "setExecuted", "(Z)V", "getForWebSocket", "getOriginalRequest", "()Lokhttp3/Request;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", DeviceStateModule.CANCEL, "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "getResponseWithInterceptorChain", "isCanceled", "isExecuted", "redactedUrl", "", "request", "timeout", "Lokio/Timeout;", "toLoggableString", "AsyncCall", "Companion", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RealCall implements Call {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final OkHttpClient client;
    private boolean executed;
    private final boolean forWebSocket;
    @NotNull
    private final Request originalRequest;
    private Transmitter transmitter;

    /* compiled from: RealCall.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\b2\n\u0010\u0012\u001a\u00060\u0000R\u00020\fJ\b\u0010\u0013\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/RealCall;Lokhttp3/Callback;)V", "callsPerHost", "Ljava/util/concurrent/atomic/AtomicInteger;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", MetricsConstants.Method.CACHE_GET, "Lokhttp3/RealCall;", "host", "", "request", "Lokhttp3/Request;", "reuseCallsPerHostFrom", "other", "run", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(@NotNull RealCall realCall, Callback responseCallback) {
            Intrinsics.checkParameterIsNotNull(responseCallback, "responseCallback");
            this.this$0 = realCall;
            this.responseCallback = responseCallback;
            this.callsPerHost = new AtomicInteger(0);
        }

        @NotNull
        public final AtomicInteger callsPerHost() {
            return this.callsPerHost;
        }

        public final void executeOn(@NotNull ExecutorService executorService) {
            Intrinsics.checkParameterIsNotNull(executorService, "executorService");
            Dispatcher dispatcher = this.this$0.getClient().dispatcher();
            if (Util.assertionsEnabled && Thread.holdsLock(dispatcher)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                outline107.append(currentThread.getName());
                outline107.append(" MUST NOT hold lock on ");
                outline107.append(dispatcher);
                throw new AssertionError(outline107.toString());
            }
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e);
                    RealCall.access$getTransmitter$p(this.this$0).noMoreExchanges(interruptedIOException);
                    this.responseCallback.onFailure(this.this$0, interruptedIOException);
                    this.this$0.getClient().dispatcher().finished$okhttp(this);
                }
            } catch (Throwable th) {
                this.this$0.getClient().dispatcher().finished$okhttp(this);
                throw th;
            }
        }

        @NotNull
        public final RealCall get() {
            return this.this$0;
        }

        @NotNull
        public final String host() {
            return this.this$0.getOriginalRequest().url().host();
        }

        @NotNull
        public final Request request() {
            return this.this$0.getOriginalRequest();
        }

        public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            this.callsPerHost = other.callsPerHost;
        }

        @Override // java.lang.Runnable
        public void run() {
            OkHttpClient client;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OkHttp ");
            outline107.append(this.this$0.redactedUrl());
            String sb = outline107.toString();
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
            String name = currentThread.getName();
            currentThread.setName(sb);
            boolean z = false;
            try {
                RealCall.access$getTransmitter$p(this.this$0).timeoutEnter();
                try {
                } catch (IOException e) {
                    e = e;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    this.responseCallback.onResponse(this.this$0, this.this$0.getResponseWithInterceptorChain());
                    client = this.this$0.getClient();
                } catch (IOException e2) {
                    e = e2;
                    z = true;
                    if (z) {
                        Platform.Companion.get().log("Callback failure for " + this.this$0.toLoggableString(), 4, e);
                    } else {
                        this.responseCallback.onFailure(this.this$0, e);
                    }
                    client = this.this$0.getClient();
                    client.dispatcher().finished$okhttp(this);
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                    this.this$0.cancel();
                    if (!z) {
                        IOException iOException = new IOException("canceled due to " + th);
                        iOException.addSuppressed(th);
                        this.responseCallback.onFailure(this.this$0, iOException);
                    }
                    throw th;
                }
                client.dispatcher().finished$okhttp(this);
            } finally {
                currentThread.setName(name);
            }
        }
    }

    /* compiled from: RealCall.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lokhttp3/RealCall$Companion;", "", "()V", "newRealCall", "Lokhttp3/RealCall;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RealCall newRealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean z) {
            Intrinsics.checkParameterIsNotNull(client, "client");
            Intrinsics.checkParameterIsNotNull(originalRequest, "originalRequest");
            RealCall realCall = new RealCall(client, originalRequest, z, null);
            realCall.transmitter = new Transmitter(client, realCall);
            return realCall;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
    }

    public static final /* synthetic */ Transmitter access$getTransmitter$p(RealCall realCall) {
        Transmitter transmitter = realCall.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter;
    }

    @Override // okhttp3.Call
    public void cancel() {
        Transmitter transmitter = this.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter.cancel();
    }

    @Override // okhttp3.Call
    public void enqueue(@NotNull Callback responseCallback) {
        Intrinsics.checkParameterIsNotNull(responseCallback, "responseCallback");
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter = this.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter.callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, responseCallback));
    }

    @Override // okhttp3.Call
    @NotNull
    public Response execute() {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter = this.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter.timeoutEnter();
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.callStart();
        try {
            this.client.dispatcher().executed$okhttp(this);
            return getResponseWithInterceptorChain();
        } finally {
            this.client.dispatcher().finished$okhttp(this);
        }
    }

    @NotNull
    public final OkHttpClient getClient() {
        return this.client;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    @NotNull
    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00c1  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okhttp3.Response getResponseWithInterceptorChain() throws java.io.IOException {
        /*
            r13 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            okhttp3.OkHttpClient r0 = r13.client
            java.util.List r0 = r0.interceptors()
            kotlin.collections.CollectionsKt.addAll(r1, r0)
            okhttp3.internal.http.RetryAndFollowUpInterceptor r0 = new okhttp3.internal.http.RetryAndFollowUpInterceptor
            okhttp3.OkHttpClient r2 = r13.client
            r0.<init>(r2)
            r1.add(r0)
            okhttp3.internal.http.BridgeInterceptor r0 = new okhttp3.internal.http.BridgeInterceptor
            okhttp3.OkHttpClient r2 = r13.client
            okhttp3.CookieJar r2 = r2.cookieJar()
            r0.<init>(r2)
            r1.add(r0)
            okhttp3.internal.cache.CacheInterceptor r0 = new okhttp3.internal.cache.CacheInterceptor
            okhttp3.OkHttpClient r2 = r13.client
            okhttp3.Cache r2 = r2.cache()
            r0.<init>(r2)
            r1.add(r0)
            okhttp3.internal.connection.ConnectInterceptor r0 = okhttp3.internal.connection.ConnectInterceptor.INSTANCE
            r1.add(r0)
            boolean r0 = r13.forWebSocket
            if (r0 != 0) goto L46
            okhttp3.OkHttpClient r0 = r13.client
            java.util.List r0 = r0.networkInterceptors()
            kotlin.collections.CollectionsKt.addAll(r1, r0)
        L46:
            okhttp3.internal.http.CallServerInterceptor r0 = new okhttp3.internal.http.CallServerInterceptor
            boolean r2 = r13.forWebSocket
            r0.<init>(r2)
            r1.add(r0)
            okhttp3.internal.http.RealInterceptorChain r10 = new okhttp3.internal.http.RealInterceptorChain
            okhttp3.internal.connection.Transmitter r2 = r13.transmitter
            java.lang.String r11 = "transmitter"
            if (r2 != 0) goto L5b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
        L5b:
            r3 = 0
            r4 = 0
            okhttp3.Request r5 = r13.originalRequest
            okhttp3.OkHttpClient r0 = r13.client
            int r7 = r0.connectTimeoutMillis()
            okhttp3.OkHttpClient r0 = r13.client
            int r8 = r0.readTimeoutMillis()
            okhttp3.OkHttpClient r0 = r13.client
            int r9 = r0.writeTimeoutMillis()
            r0 = r10
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r0 = 0
            r1 = 0
            okhttp3.Request r2 = r13.originalRequest     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            okhttp3.Response r2 = r10.proceed(r2)     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            okhttp3.internal.connection.Transmitter r3 = r13.transmitter     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            if (r3 != 0) goto L85
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
        L85:
            boolean r3 = r3.isCanceled()     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            if (r3 != 0) goto L96
            okhttp3.internal.connection.Transmitter r0 = r13.transmitter
            if (r0 != 0) goto L92
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
        L92:
            r0.noMoreExchanges(r1)
            return r2
        L96:
            okhttp3.internal.Util.closeQuietly(r2)     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
            throw r2     // Catch: java.lang.Throwable -> La1 java.io.IOException -> La3
        La1:
            r2 = move-exception
            goto Lbf
        La3:
            r0 = move-exception
            r2 = 1
            okhttp3.internal.connection.Transmitter r3 = r13.transmitter     // Catch: java.lang.Throwable -> Lbb
            if (r3 != 0) goto Lac
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)     // Catch: java.lang.Throwable -> Lbb
        Lac:
            java.io.IOException r0 = r3.noMoreExchanges(r0)     // Catch: java.lang.Throwable -> Lbb
            if (r0 != 0) goto Lba
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            r0.<init>(r3)     // Catch: java.lang.Throwable -> Lbb
            throw r0     // Catch: java.lang.Throwable -> Lbb
        Lba:
            throw r0     // Catch: java.lang.Throwable -> Lbb
        Lbb:
            r0 = move-exception
            r12 = r2
            r2 = r0
            r0 = r12
        Lbf:
            if (r0 != 0) goto Lcb
            okhttp3.internal.connection.Transmitter r0 = r13.transmitter
            if (r0 != 0) goto Lc8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
        Lc8:
            r0.noMoreExchanges(r1)
        Lcb:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.RealCall.getResponseWithInterceptorChain():okhttp3.Response");
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        Transmitter transmitter = this.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter.isCanceled();
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @NotNull
    public final String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    @Override // okhttp3.Call
    @NotNull
    public Request request() {
        return this.originalRequest;
    }

    public final void setExecuted(boolean z) {
        this.executed = z;
    }

    @Override // okhttp3.Call
    @NotNull
    public Timeout timeout() {
        Transmitter transmitter = this.transmitter;
        if (transmitter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter.timeout();
    }

    @NotNull
    public final String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : "call");
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }

    public /* synthetic */ RealCall(OkHttpClient okHttpClient, Request request, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(okHttpClient, request, z);
    }

    @Override // okhttp3.Call
    @NotNull
    /* renamed from: clone */
    public RealCall mo12536clone() {
        return Companion.newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }
}
