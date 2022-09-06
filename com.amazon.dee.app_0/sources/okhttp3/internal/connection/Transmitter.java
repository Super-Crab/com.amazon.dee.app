package okhttp3.internal.connection;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.redesign.view.viewtypes.AlarmsTimersView;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Transmitter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u007f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001 \u0018\u00002\u00020\u0001:\u0001FB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010#\u001a\u00020$2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020\tJ\u0006\u0010'\u001a\u00020$J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020$J;\u0010-\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u0002H.H\u0000¢\u0006\u0004\b3\u00104J\u0006\u00105\u001a\u00020\tJ)\u00106\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u00102\u001a\u0002H.2\u0006\u00107\u001a\u00020\tH\u0002¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\tH\u0000¢\u0006\u0002\b=J\u0012\u0010\u001c\u001a\u0004\u0018\u00010/2\b\u00102\u001a\u0004\u0018\u00010/J\u000e\u0010>\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010?\u001a\u0004\u0018\u00010@J\u0006\u0010\u001f\u001a\u00020AJ\u0006\u0010\"\u001a\u00020$J\u0006\u0010B\u001a\u00020$J!\u0010C\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010D\u001a\u0002H.H\u0002¢\u0006\u0002\u0010ER\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\"\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lokhttp3/internal/connection/Transmitter;", "", "client", "Lokhttp3/OkHttpClient;", "call", "Lokhttp3/Call;", "(Lokhttp3/OkHttpClient;Lokhttp3/Call;)V", "callStackTrace", AlarmsTimersView.STATE_CANCELED, "", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "setConnection", "(Lokhttp3/internal/connection/RealConnection;)V", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "eventListener", "Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "exchangeRequestDone", "exchangeResponseDone", "isCanceled", "()Z", "noMoreExchanges", "request", "Lokhttp3/Request;", "timeout", "okhttp3/internal/connection/Transmitter$timeout$1", "Lokhttp3/internal/connection/Transmitter$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callStart", "canRetry", DeviceStateModule.CANCEL, "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "exchangeDoneDueToException", "exchangeMessageDone", ExifInterface.LONGITUDE_EAST, "Ljava/io/IOException;", "requestDone", "responseDone", "e", "exchangeMessageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "hasExchange", "maybeReleaseConnection", "force", "(Ljava/io/IOException;Z)Ljava/io/IOException;", "newExchange", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "newExchange$okhttp", "prepareToConnect", "releaseConnectionNoEvents", "Ljava/net/Socket;", "Lokio/Timeout;", "timeoutEnter", "timeoutExit", "cause", "(Ljava/io/IOException;)Ljava/io/IOException;", "TransmitterReference", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Transmitter {
    private final Call call;
    private Object callStackTrace;
    private boolean canceled;
    private final OkHttpClient client;
    @Nullable
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private boolean exchangeRequestDone;
    private boolean exchangeResponseDone;
    private boolean noMoreExchanges;
    private Request request;
    private final Transmitter$timeout$1 timeout;
    private boolean timeoutEarlyExit;

    /* compiled from: Transmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/Transmitter$TransmitterReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/Transmitter;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/Transmitter;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TransmitterReference extends WeakReference<Transmitter> {
        @Nullable
        private final Object callStackTrace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransmitterReference(@NotNull Transmitter referent, @Nullable Object obj) {
            super(referent);
            Intrinsics.checkParameterIsNotNull(referent, "referent");
            this.callStackTrace = obj;
        }

        @Nullable
        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }

    /* JADX WARN: Type inference failed for: r3v7, types: [okio.Timeout, okhttp3.internal.connection.Transmitter$timeout$1] */
    public Transmitter(@NotNull OkHttpClient client, @NotNull Call call) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(call, "call");
        this.client = client;
        this.call = call;
        this.connectionPool = this.client.connectionPool().getDelegate$okhttp();
        this.eventListener = this.client.eventListenerFactory().create(this.call);
        ?? r3 = new AsyncTimeout() { // from class: okhttp3.internal.connection.Transmitter$timeout$1
            @Override // okio.AsyncTimeout
            protected void timedOut() {
                Transmitter.this.cancel();
            }
        };
        r3.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.timeout = r3;
    }

    private final Address createAddress(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0019 A[Catch: all -> 0x0013, TryCatch #0 {all -> 0x0013, blocks: (B:6:0x000c, B:14:0x0019, B:16:0x0022, B:19:0x0028, B:21:0x002c, B:23:0x0032, B:25:0x0036, B:26:0x0038, B:28:0x003c, B:32:0x0043, B:53:0x0080, B:54:0x008b), top: B:57:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0080 A[Catch: all -> 0x0013, TRY_ENTER, TryCatch #0 {all -> 0x0013, blocks: (B:6:0x000c, B:14:0x0019, B:16:0x0022, B:19:0x0028, B:21:0x002c, B:23:0x0032, B:25:0x0036, B:26:0x0038, B:28:0x003c, B:32:0x0043, B:53:0x0080, B:54:0x008b), top: B:57:0x000c }] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, okhttp3.internal.connection.RealConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <E extends java.io.IOException> E maybeReleaseConnection(E r7, boolean r8) {
        /*
            r6 = this;
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            okhttp3.internal.connection.RealConnectionPool r1 = r6.connectionPool
            monitor-enter(r1)
            r2 = 0
            r3 = 1
            if (r8 == 0) goto L16
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch: java.lang.Throwable -> L13
            if (r4 != 0) goto L11
            goto L16
        L11:
            r4 = r2
            goto L17
        L13:
            r7 = move-exception
            goto L8c
        L16:
            r4 = r3
        L17:
            if (r4 == 0) goto L80
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch: java.lang.Throwable -> L13
            r0.element = r4     // Catch: java.lang.Throwable -> L13
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch: java.lang.Throwable -> L13
            r5 = 0
            if (r4 == 0) goto L31
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch: java.lang.Throwable -> L13
            if (r4 != 0) goto L31
            if (r8 != 0) goto L2c
            boolean r8 = r6.noMoreExchanges     // Catch: java.lang.Throwable -> L13
            if (r8 == 0) goto L31
        L2c:
            java.net.Socket r8 = r6.releaseConnectionNoEvents()     // Catch: java.lang.Throwable -> L13
            goto L32
        L31:
            r8 = r5
        L32:
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch: java.lang.Throwable -> L13
            if (r4 == 0) goto L38
            r0.element = r5     // Catch: java.lang.Throwable -> L13
        L38:
            boolean r4 = r6.noMoreExchanges     // Catch: java.lang.Throwable -> L13
            if (r4 == 0) goto L42
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch: java.lang.Throwable -> L13
            if (r4 != 0) goto L42
            r4 = r3
            goto L43
        L42:
            r4 = r2
        L43:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L13
            monitor-exit(r1)
            if (r8 == 0) goto L4b
            okhttp3.internal.Util.closeQuietly(r8)
        L4b:
            T r8 = r0.element
            r0 = r8
            okhttp3.Connection r0 = (okhttp3.Connection) r0
            if (r0 == 0) goto L60
            okhttp3.EventListener r0 = r6.eventListener
            okhttp3.Call r1 = r6.call
            okhttp3.Connection r8 = (okhttp3.Connection) r8
            if (r8 != 0) goto L5d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L5d:
            r0.connectionReleased(r1, r8)
        L60:
            if (r4 == 0) goto L7f
            if (r7 == 0) goto L65
            r2 = r3
        L65:
            java.io.IOException r7 = r6.timeoutExit(r7)
            if (r2 == 0) goto L78
            okhttp3.EventListener r8 = r6.eventListener
            okhttp3.Call r0 = r6.call
            if (r7 != 0) goto L74
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L74:
            r8.callFailed(r0, r7)
            goto L7f
        L78:
            okhttp3.EventListener r8 = r6.eventListener
            okhttp3.Call r0 = r6.call
            r8.callEnd(r0)
        L7f:
            return r7
        L80:
            java.lang.String r7 = "cannot release connection while it is in use"
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L13
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L13
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L13
            throw r8     // Catch: java.lang.Throwable -> L13
        L8c:
            monitor-exit(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.Transmitter.maybeReleaseConnection(java.io.IOException, boolean):java.io.IOException");
    }

    private final <E extends IOException> E timeoutExit(E e) {
        if (!this.timeoutEarlyExit && exit()) {
            InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
            if (e != null) {
                interruptedIOException.initCause(e);
            }
            return interruptedIOException;
        }
        return e;
    }

    public final void acquireConnectionNoEvents(@NotNull RealConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        RealConnectionPool realConnectionPool = this.connectionPool;
        if (Util.assertionsEnabled && !Thread.holdsLock(realConnectionPool)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            outline107.append(currentThread.getName());
            outline107.append(" MUST hold lock on ");
            outline107.append(realConnectionPool);
            throw new AssertionError(outline107.toString());
        }
        if (this.connection == null) {
            this.connection = connection;
            connection.getTransmitters().add(new TransmitterReference(this, this.callStackTrace));
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this.call);
    }

    public final boolean canRetry() {
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        if (exchangeFinder == null) {
            Intrinsics.throwNpe();
        }
        if (exchangeFinder.hasStreamFailure()) {
            ExchangeFinder exchangeFinder2 = this.exchangeFinder;
            if (exchangeFinder2 == null) {
                Intrinsics.throwNpe();
            }
            if (exchangeFinder2.hasRouteToTry()) {
                return true;
            }
        }
        return false;
    }

    public final void cancel() {
        Exchange exchange;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            exchange = this.exchange;
            ExchangeFinder exchangeFinder = this.exchangeFinder;
            if (exchangeFinder == null || (realConnection = exchangeFinder.connectingConnection()) == null) {
                realConnection = this.connection;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (exchange != null) {
            exchange.cancel();
        } else if (realConnection == null) {
        } else {
            realConnection.cancel();
        }
    }

    public final void exchangeDoneDueToException() {
        synchronized (this.connectionPool) {
            Exchange exchange = this.exchange;
            if (exchange != null) {
                exchange.detachWithViolence();
            }
            if (!this.noMoreExchanges) {
                this.exchange = null;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    public final <E extends IOException> E exchangeMessageDone$okhttp(@NotNull Exchange exchange, boolean z, boolean z2, E e) {
        boolean z3;
        Intrinsics.checkParameterIsNotNull(exchange, "exchange");
        synchronized (this.connectionPool) {
            boolean z4 = true;
            if (!Intrinsics.areEqual(exchange, this.exchange)) {
                return e;
            }
            if (z) {
                z3 = !this.exchangeRequestDone;
                this.exchangeRequestDone = true;
            } else {
                z3 = false;
            }
            if (z2) {
                if (!this.exchangeResponseDone) {
                    z3 = true;
                }
                this.exchangeResponseDone = true;
            }
            if (!this.exchangeRequestDone || !this.exchangeResponseDone || !z3) {
                z4 = false;
            } else {
                Exchange exchange2 = this.exchange;
                if (exchange2 == null) {
                    Intrinsics.throwNpe();
                }
                RealConnection connection = exchange2.connection();
                if (connection == null) {
                    Intrinsics.throwNpe();
                }
                connection.setSuccessCount$okhttp(connection.getSuccessCount$okhttp() + 1);
                this.exchange = null;
            }
            Unit unit = Unit.INSTANCE;
            return z4 ? (E) maybeReleaseConnection(e, false) : e;
        }
    }

    @Nullable
    public final RealConnection getConnection() {
        return this.connection;
    }

    public final boolean hasExchange() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.exchange != null;
        }
        return z;
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.canceled;
        }
        return z;
    }

    @NotNull
    public final Exchange newExchange$okhttp(@NotNull Interceptor.Chain chain, boolean z) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        synchronized (this.connectionPool) {
            boolean z2 = true;
            if (!this.noMoreExchanges) {
                if (this.exchange != null) {
                    z2 = false;
                }
                if (z2) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                }
            } else {
                throw new IllegalStateException("released".toString());
            }
        }
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        if (exchangeFinder == null) {
            Intrinsics.throwNpe();
        }
        ExchangeCodec find = exchangeFinder.find(this.client, chain, z);
        Call call = this.call;
        EventListener eventListener = this.eventListener;
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        if (exchangeFinder2 == null) {
            Intrinsics.throwNpe();
        }
        Exchange exchange = new Exchange(this, call, eventListener, exchangeFinder2, find);
        synchronized (this.connectionPool) {
            this.exchange = exchange;
            this.exchangeRequestDone = false;
            this.exchangeResponseDone = false;
        }
        return exchange;
    }

    @Nullable
    public final IOException noMoreExchanges(@Nullable IOException iOException) {
        synchronized (this.connectionPool) {
            this.noMoreExchanges = true;
            Unit unit = Unit.INSTANCE;
        }
        return maybeReleaseConnection(iOException, false);
    }

    public final void prepareToConnect(@NotNull Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Request request2 = this.request;
        if (request2 != null) {
            if (request2 == null) {
                Intrinsics.throwNpe();
            }
            if (Util.canReuseConnectionFor(request2.url(), request.url())) {
                ExchangeFinder exchangeFinder = this.exchangeFinder;
                if (exchangeFinder == null) {
                    Intrinsics.throwNpe();
                }
                if (exchangeFinder.hasRouteToTry()) {
                    return;
                }
            }
            if (this.exchange == null) {
                if (this.exchangeFinder != null) {
                    maybeReleaseConnection(null, true);
                    this.exchangeFinder = null;
                }
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        this.request = request;
        this.exchangeFinder = new ExchangeFinder(this, this.connectionPool, createAddress(request.url()), this.call, this.eventListener);
    }

    @Nullable
    public final Socket releaseConnectionNoEvents() {
        RealConnectionPool realConnectionPool = this.connectionPool;
        if (Util.assertionsEnabled && !Thread.holdsLock(realConnectionPool)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            outline107.append(currentThread.getName());
            outline107.append(" MUST hold lock on ");
            outline107.append(realConnectionPool);
            throw new AssertionError(outline107.toString());
        }
        RealConnection realConnection = this.connection;
        if (realConnection == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Reference<Transmitter>> it2 = realConnection.getTransmitters().iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(it2.next().get(), this)) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            z = true;
        }
        if (z) {
            RealConnection realConnection2 = this.connection;
            if (realConnection2 == null) {
                Intrinsics.throwNpe();
            }
            realConnection2.getTransmitters().remove(i);
            this.connection = null;
            if (realConnection2.getTransmitters().isEmpty()) {
                realConnection2.setIdleAtNanos$okhttp(System.nanoTime());
                if (this.connectionPool.connectionBecameIdle(realConnection2)) {
                    return realConnection2.socket();
                }
            }
            return null;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void setConnection(@Nullable RealConnection realConnection) {
        this.connection = realConnection;
    }

    @NotNull
    public final Timeout timeout() {
        return this.timeout;
    }

    public final void timeoutEarlyExit() {
        if (!this.timeoutEarlyExit) {
            this.timeoutEarlyExit = true;
            exit();
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void timeoutEnter() {
        enter();
    }
}
