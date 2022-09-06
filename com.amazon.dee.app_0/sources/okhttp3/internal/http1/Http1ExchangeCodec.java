package okhttp3.internal.http1;

import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Address;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Http1ExchangeCodec.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 =2\u00020\u0001:\u0007:;<=>?@B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\b\u0010 \u001a\u00020\u0015H\u0016J\b\u0010!\u001a\u00020\u0018H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\fH\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u000eH\u0016J\u0010\u00103\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J\u000e\u00104\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020.J\u0010\u00108\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\f\u00109\u001a\u00020\u000e*\u00020\u001aH\u0002J\f\u00109\u001a\u00020\u000e*\u00020,H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "realConnection", "Lokhttp3/internal/connection/RealConnection;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealConnection;Lokio/BufferedSource;Lokio/BufferedSink;)V", "headerLimit", "", "isClosed", "", "()Z", "state", "", "trailers", "Lokhttp3/Headers;", DeviceStateModule.CANCEL, "", "connection", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "finishRequest", "flushRequest", "newChunkedSink", "newChunkedSource", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "newFixedLengthSource", "length", "newKnownLengthSink", "newUnknownLengthSource", "openResponseBodySource", "response", "Lokhttp3/Response;", "readHeaderLine", "", "readHeaders", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "skipConnectBody", "writeRequest", HttpClientModule.ElementsRequestKey.HEADERS, "requestLine", "writeRequestHeaders", "isChunked", "AbstractSource", "ChunkedSink", "ChunkedSource", "Companion", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Http1ExchangeCodec implements ExchangeCodec {
    public static final Companion Companion = new Companion(null);
    private static final int HEADER_LIMIT = 262144;
    private static final long NO_CHUNK_YET = -1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private final OkHttpClient client;
    private long headerLimit;
    private final RealConnection realConnection;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state;
    private Headers trailers;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¢\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\r\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J\b\u0010\t\u001a\u00020\u0015H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "read", "", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "responseBodyComplete", "", "responseBodyComplete$okhttp", "Lokio/Timeout;", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public abstract class AbstractSource implements Source {
        private boolean closed;
        @NotNull
        private final ForwardingTimeout timeout;

        public AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.mo12585timeout());
        }

        protected final boolean getClosed() {
            return this.closed;
        }

        @NotNull
        protected final ForwardingTimeout getTimeout() {
            return this.timeout;
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            try {
                return Http1ExchangeCodec.this.source.read(sink, j);
            } catch (IOException e) {
                RealConnection realConnection = Http1ExchangeCodec.this.realConnection;
                if (realConnection == null) {
                    Intrinsics.throwNpe();
                }
                realConnection.noNewExchanges();
                responseBodyComplete$okhttp();
                throw e;
            }
        }

        public final void responseBodyComplete$okhttp() {
            if (Http1ExchangeCodec.this.state == 6) {
                return;
            }
            if (Http1ExchangeCodec.this.state == 5) {
                Http1ExchangeCodec.this.detachTimeout(this.timeout);
                Http1ExchangeCodec.this.state = 6;
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
            outline107.append(Http1ExchangeCodec.this.state);
            throw new IllegalStateException(outline107.toString());
        }

        protected final void setClosed(boolean z) {
            this.closed = z;
        }

        @Override // okio.Source
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12585timeout() {
            return this.timeout;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.mo12584timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1ExchangeCodec.this.sink.mo12607writeUtf8("0\r\n\r\n");
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            if (this.closed) {
                return;
            }
            Http1ExchangeCodec.this.sink.flush();
        }

        @Override // okio.Sink
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12584timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            if (!this.closed) {
                if (j == 0) {
                    return;
                }
                Http1ExchangeCodec.this.sink.mo12598writeHexadecimalUnsignedLong(j);
                Http1ExchangeCodec.this.sink.mo12607writeUtf8("\r\n");
                Http1ExchangeCodec.this.sink.write(source, j);
                Http1ExchangeCodec.this.sink.mo12607writeUtf8("\r\n");
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", "", "hasMoreChunks", "", "close", "", "read", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "readChunkSize", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class ChunkedSource extends AbstractSource {
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        final /* synthetic */ Http1ExchangeCodec this$0;
        private final HttpUrl url;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChunkedSource(@NotNull Http1ExchangeCodec http1ExchangeCodec, HttpUrl url) {
            super();
            Intrinsics.checkParameterIsNotNull(url, "url");
            this.this$0 = http1ExchangeCodec;
            this.url = url;
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
            if (r1 != false) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void readChunkSize() {
            /*
                r7 = this;
                long r0 = r7.bytesRemainingInChunk
                r2 = -1
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 == 0) goto L11
                okhttp3.internal.http1.Http1ExchangeCodec r0 = r7.this$0
                okio.BufferedSource r0 = okhttp3.internal.http1.Http1ExchangeCodec.access$getSource$p(r0)
                r0.readUtf8LineStrict()
            L11:
                okhttp3.internal.http1.Http1ExchangeCodec r0 = r7.this$0     // Catch: java.lang.NumberFormatException -> Lb1
                okio.BufferedSource r0 = okhttp3.internal.http1.Http1ExchangeCodec.access$getSource$p(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                long r0 = r0.readHexadecimalUnsignedLong()     // Catch: java.lang.NumberFormatException -> Lb1
                r7.bytesRemainingInChunk = r0     // Catch: java.lang.NumberFormatException -> Lb1
                okhttp3.internal.http1.Http1ExchangeCodec r0 = r7.this$0     // Catch: java.lang.NumberFormatException -> Lb1
                okio.BufferedSource r0 = okhttp3.internal.http1.Http1ExchangeCodec.access$getSource$p(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r0 = r0.readUtf8LineStrict()     // Catch: java.lang.NumberFormatException -> Lb1
                if (r0 == 0) goto La9
                java.lang.CharSequence r0 = kotlin.text.StringsKt.trim(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r0 = r0.toString()     // Catch: java.lang.NumberFormatException -> Lb1
                long r1 = r7.bytesRemainingInChunk     // Catch: java.lang.NumberFormatException -> Lb1
                r3 = 0
                int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r1 < 0) goto L83
                int r1 = r0.length()     // Catch: java.lang.NumberFormatException -> Lb1
                r2 = 0
                if (r1 <= 0) goto L42
                r1 = 1
                goto L43
            L42:
                r1 = r2
            L43:
                if (r1 == 0) goto L4f
                java.lang.String r1 = ";"
                r5 = 2
                r6 = 0
                boolean r1 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r5, r6)     // Catch: java.lang.NumberFormatException -> Lb1
                if (r1 == 0) goto L83
            L4f:
                long r0 = r7.bytesRemainingInChunk
                int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                if (r0 != 0) goto L82
                r7.hasMoreChunks = r2
                okhttp3.internal.http1.Http1ExchangeCodec r0 = r7.this$0
                okhttp3.Headers r1 = okhttp3.internal.http1.Http1ExchangeCodec.access$readHeaders(r0)
                okhttp3.internal.http1.Http1ExchangeCodec.access$setTrailers$p(r0, r1)
                okhttp3.internal.http1.Http1ExchangeCodec r0 = r7.this$0
                okhttp3.OkHttpClient r0 = okhttp3.internal.http1.Http1ExchangeCodec.access$getClient$p(r0)
                if (r0 != 0) goto L6b
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L6b:
                okhttp3.CookieJar r0 = r0.cookieJar()
                okhttp3.HttpUrl r1 = r7.url
                okhttp3.internal.http1.Http1ExchangeCodec r2 = r7.this$0
                okhttp3.Headers r2 = okhttp3.internal.http1.Http1ExchangeCodec.access$getTrailers$p(r2)
                if (r2 != 0) goto L7c
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L7c:
                okhttp3.internal.http.HttpHeaders.receiveHeaders(r0, r1, r2)
                r7.responseBodyComplete$okhttp()
            L82:
                return
            L83:
                java.net.ProtocolException r1 = new java.net.ProtocolException     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> Lb1
                r2.<init>()     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r3 = "expected chunk size and optional extensions"
                r2.append(r3)     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r3 = " but was \""
                r2.append(r3)     // Catch: java.lang.NumberFormatException -> Lb1
                long r3 = r7.bytesRemainingInChunk     // Catch: java.lang.NumberFormatException -> Lb1
                r2.append(r3)     // Catch: java.lang.NumberFormatException -> Lb1
                r2.append(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                r0 = 34
                r2.append(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r0 = r2.toString()     // Catch: java.lang.NumberFormatException -> Lb1
                r1.<init>(r0)     // Catch: java.lang.NumberFormatException -> Lb1
                throw r1     // Catch: java.lang.NumberFormatException -> Lb1
            La9:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.NumberFormatException -> Lb1
                java.lang.String r1 = "null cannot be cast to non-null type kotlin.CharSequence"
                r0.<init>(r1)     // Catch: java.lang.NumberFormatException -> Lb1
                throw r0     // Catch: java.lang.NumberFormatException -> Lb1
            Lb1:
                r0 = move-exception
                java.net.ProtocolException r1 = new java.net.ProtocolException
                java.lang.String r0 = r0.getMessage()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http1.Http1ExchangeCodec.ChunkedSource.readChunkSize():void");
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed()) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                RealConnection realConnection = this.this$0.realConnection;
                if (realConnection == null) {
                    Intrinsics.throwNpe();
                }
                realConnection.noNewExchanges();
                responseBodyComplete$okhttp();
            }
            setClosed(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (j >= 0) {
                if (!getClosed()) {
                    if (!this.hasMoreChunks) {
                        return -1L;
                    }
                    long j2 = this.bytesRemainingInChunk;
                    if (j2 == 0 || j2 == -1) {
                        readChunkSize();
                        if (!this.hasMoreChunks) {
                            return -1L;
                        }
                    }
                    long read = super.read(sink, Math.min(j, this.bytesRemainingInChunk));
                    if (read == -1) {
                        RealConnection realConnection = this.this$0.realConnection;
                        if (realConnection == null) {
                            Intrinsics.throwNpe();
                        }
                        realConnection.noNewExchanges();
                        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                        responseBodyComplete$okhttp();
                        throw protocolException;
                    }
                    this.bytesRemainingInChunk -= read;
                    return read;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount < 0: ", j).toString());
        }
    }

    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "()V", "HEADER_LIMIT", "", "NO_CHUNK_YET", "", "STATE_CLOSED", StateManager.STATE_IDLE, "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "bytesRemaining", "", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "close", "", "read", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long j) {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                responseBodyComplete$okhttp();
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed()) {
                return;
            }
            if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                RealConnection realConnection = Http1ExchangeCodec.this.realConnection;
                if (realConnection == null) {
                    Intrinsics.throwNpe();
                }
                realConnection.noNewExchanges();
                responseBodyComplete$okhttp();
            }
            setClosed(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (j >= 0) {
                if (!getClosed()) {
                    long j2 = this.bytesRemaining;
                    if (j2 == 0) {
                        return -1L;
                    }
                    long read = super.read(sink, Math.min(j2, j));
                    if (read == -1) {
                        RealConnection realConnection = Http1ExchangeCodec.this.realConnection;
                        if (realConnection == null) {
                            Intrinsics.throwNpe();
                        }
                        realConnection.noNewExchanges();
                        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                        responseBodyComplete$okhttp();
                        throw protocolException;
                    }
                    this.bytesRemaining -= read;
                    if (this.bytesRemaining == 0) {
                        responseBodyComplete$okhttp();
                    }
                    return read;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount < 0: ", j).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0005\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class KnownLengthSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public KnownLengthSink() {
            this.timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.mo12584timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.closed) {
                return;
            }
            Http1ExchangeCodec.this.sink.flush();
        }

        @Override // okio.Sink
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12584timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            if (!this.closed) {
                Util.checkOffsetAndCount(source.size(), 0L, j);
                Http1ExchangeCodec.this.sink.write(source, j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Http1ExchangeCodec.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "inputExhausted", "", "close", "", "read", "", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed()) {
                return;
            }
            if (!this.inputExhausted) {
                responseBodyComplete$okhttp();
            }
            setClosed(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            if (j >= 0) {
                if (!getClosed()) {
                    if (this.inputExhausted) {
                        return -1L;
                    }
                    long read = super.read(sink, j);
                    if (read != -1) {
                        return read;
                    }
                    this.inputExhausted = true;
                    responseBodyComplete$okhttp();
                    return -1L;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount < 0: ", j).toString());
        }
    }

    public Http1ExchangeCodec(@Nullable OkHttpClient okHttpClient, @Nullable RealConnection realConnection, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.client = okHttpClient;
        this.realConnection = realConnection;
        this.source = source;
        this.sink = sink;
        this.headerLimit = 262144;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }

    private final boolean isChunked(@NotNull Response response) {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals("chunked", Response.header$default(response, HttpHeaders.TRANSFER_ENCODING, null, 2, null), true);
        return equals;
    }

    private final Sink newChunkedSink() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        if (z) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private final Source newChunkedSource(HttpUrl httpUrl) {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(this, httpUrl);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private final Source newFixedLengthSource(long j) {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private final Sink newKnownLengthSink() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        if (z) {
            this.state = 2;
            return new KnownLengthSink();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private final Source newUnknownLengthSource() {
        if (this.state == 4) {
            this.state = 5;
            RealConnection realConnection = this.realConnection;
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            realConnection.noNewExchanges();
            return new UnknownLengthSource();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private final String readHeaderLine() {
        String readUtf8LineStrict = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= readUtf8LineStrict.length();
        return readUtf8LineStrict;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        String readHeaderLine = readHeaderLine();
        while (true) {
            if (readHeaderLine.length() > 0) {
                builder.addLenient$okhttp(readHeaderLine);
                readHeaderLine = readHeaderLine();
            } else {
                return builder.build();
            }
        }
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        RealConnection realConnection = this.realConnection;
        if (realConnection != null) {
            realConnection.cancel();
        }
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @Nullable
    public RealConnection connection() {
        return this.realConnection;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Sink createRequestBody(@NotNull Request request, long j) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        if (request.body() == null || !request.body().isDuplex()) {
            if (isChunked(request)) {
                return newChunkedSink();
            }
            if (j != -1) {
                return newKnownLengthSink();
            }
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new ProtocolException("Duplex connections are not supported for HTTP/1");
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        this.sink.flush();
    }

    public final boolean isClosed() {
        return this.state == 6;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Source openResponseBodySource(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (!okhttp3.internal.http.HttpHeaders.promisesBody(response)) {
            return newFixedLengthSource(0L);
        }
        if (isChunked(response)) {
            return newChunkedSource(response.request().url());
        }
        long headersContentLength = Util.headersContentLength(response);
        if (headersContentLength != -1) {
            return newFixedLengthSource(headersContentLength);
        }
        return newUnknownLengthSource();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @Nullable
    public Response.Builder readResponseHeaders(boolean z) {
        String str;
        Route route;
        Address address;
        HttpUrl url;
        int i = this.state;
        boolean z2 = true;
        if (i != 1 && i != 3) {
            z2 = false;
        }
        if (z2) {
            try {
                StatusLine parse = StatusLine.Companion.parse(readHeaderLine());
                Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                if (parse.code == 100) {
                    this.state = 3;
                    return headers;
                }
                this.state = 4;
                return headers;
            } catch (EOFException e) {
                RealConnection realConnection = this.realConnection;
                if (realConnection == null || (route = realConnection.route()) == null || (address = route.address()) == null || (url = address.url()) == null || (str = url.redact()) == null) {
                    str = "unknown";
                }
                throw new IOException(GeneratedOutlineSupport1.outline72("unexpected end of stream on ", str), e);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (!okhttp3.internal.http.HttpHeaders.promisesBody(response)) {
            return 0L;
        }
        if (!isChunked(response)) {
            return Util.headersContentLength(response);
        }
        return -1L;
    }

    public final void skipConnectBody(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        long headersContentLength = Util.headersContentLength(response);
        if (headersContentLength == -1) {
            return;
        }
        Source newFixedLengthSource = newFixedLengthSource(headersContentLength);
        Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        newFixedLengthSource.close();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    @NotNull
    public Headers trailers() {
        if (this.state == 6) {
            Headers headers = this.trailers;
            return headers != null ? headers : Util.EMPTY_HEADERS;
        }
        throw new IllegalStateException("too early; can't read the trailers yet".toString());
    }

    public final void writeRequest(@NotNull Headers headers, @NotNull String requestLine) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(requestLine, "requestLine");
        if (this.state == 0) {
            this.sink.mo12607writeUtf8(requestLine).mo12607writeUtf8("\r\n");
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                this.sink.mo12607writeUtf8(headers.name(i)).mo12607writeUtf8(RealTimeTextConstants.COLON_SPACE).mo12607writeUtf8(headers.value(i)).mo12607writeUtf8("\r\n");
            }
            this.sink.mo12607writeUtf8("\r\n");
            this.state = 1;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("state: ");
        outline107.append(this.state);
        throw new IllegalStateException(outline107.toString().toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(@NotNull Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        RequestLine requestLine = RequestLine.INSTANCE;
        RealConnection realConnection = this.realConnection;
        if (realConnection == null) {
            Intrinsics.throwNpe();
        }
        Proxy.Type type = realConnection.route().proxy().type();
        Intrinsics.checkExpressionValueIsNotNull(type, "realConnection!!.route().proxy.type()");
        writeRequest(request.headers(), requestLine.get(request, type));
    }

    private final boolean isChunked(@NotNull Request request) {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals("chunked", request.header(HttpHeaders.TRANSFER_ENCODING), true);
        return equals;
    }
}
