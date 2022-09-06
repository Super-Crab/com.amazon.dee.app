package okhttp3.mockwebserver;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.http2.Settings;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import okio.Buffer;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MockResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b'\u0018\u0000 j2\u00020\u0001:\u0001jB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u000200J\u0016\u0010A\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u0016\u0010E\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u0006\u0010F\u001a\u00020\u0000J\b\u0010G\u001a\u00020\u0000H\u0016J\b\u0010H\u001a\u0004\u0018\u00010\u0004J\u000e\u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010\u0011\u001a\u00020\u000fH\u0007¢\u0006\u0002\bKJ\u000e\u0010L\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010\u001a\u001a\u00020\u0018H\u0007¢\u0006\u0002\bMJ\r\u0010-\u001a\u00020+H\u0007¢\u0006\u0002\bNJ\r\u00102\u001a\u000200H\u0007¢\u0006\u0002\bOJ\u000e\u0010P\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010;\u001a\u00020\u000fH\u0007¢\u0006\u0002\bQJ\u000e\u0010R\u001a\u00020\u00002\u0006\u0010C\u001a\u000200J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u000200J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\u0016\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u0002002\u0006\u0010W\u001a\u00020\u0018J\u0016\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u0018J\u0016\u0010X\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0016\u0010Z\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010]\u001a\u00020\u0018J\u000e\u0010^\u001a\u00020\u00002\u0006\u0010,\u001a\u00020+J\u000e\u0010_\u001a\u00020\u00002\u0006\u00101\u001a\u000200J\u000e\u0010`\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u000fJ\u001e\u0010a\u001a\u00020\u00002\u0006\u0010b\u001a\u00020\u00062\u0006\u0010c\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\b\u0010d\u001a\u000200H\u0016J\u000e\u0010e\u001a\u00020\u00002\u0006\u0010f\u001a\u00020\"J\u000e\u0010g\u001a\u00020\u00002\u0006\u0010(\u001a\u00020'J\u000e\u0010h\u001a\u00020\u00002\u0006\u0010i\u001a\u00020=R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0010\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0018@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u0019\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0$8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001e\u0010(\u001a\u00020'2\u0006\u0010\t\u001a\u00020'@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R$\u0010,\u001a\u00020+2\u0006\u0010\t\u001a\u00020+@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b,\u0010/R$\u00101\u001a\u0002002\u0006\u0010\t\u001a\u000200@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b1\u00104R\u001e\u00105\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010:\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010\u0012\"\u0004\b:\u0010\u0013R\u000e\u0010<\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010>\u001a\u0004\u0018\u00010=2\b\u0010\t\u001a\u0004\u0018\u00010=@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@¨\u0006k"}, d2 = {"Lokhttp3/mockwebserver/MockResponse;", "", "()V", "body", "Lokio/Buffer;", "bodyDelayAmount", "", "bodyDelayUnit", "Ljava/util/concurrent/TimeUnit;", "<set-?>", "Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "duplexResponseBody", "getDuplexResponseBody", "()Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "value", "Lokhttp3/Headers;", HttpClientModule.ElementsRequestKey.HEADERS, "getHeaders", "()Lokhttp3/Headers;", "(Lokhttp3/Headers;)V", "headersBuilder", "Lokhttp3/Headers$Builder;", "headersDelayAmount", "headersDelayUnit", "", "http2ErrorCode", "getHttp2ErrorCode", "()I", "(I)V", "isDuplex", "", "()Z", "promises", "", "Lokhttp3/mockwebserver/PushPromise;", "pushPromises", "", "getPushPromises", "()Ljava/util/List;", "Lokhttp3/internal/http2/Settings;", "settings", "getSettings", "()Lokhttp3/internal/http2/Settings;", "Lokhttp3/mockwebserver/SocketPolicy;", "socketPolicy", "getSocketPolicy", "()Lokhttp3/mockwebserver/SocketPolicy;", "(Lokhttp3/mockwebserver/SocketPolicy;)V", "", "status", "getStatus", "()Ljava/lang/String;", "(Ljava/lang/String;)V", "throttleBytesPerPeriod", "getThrottleBytesPerPeriod", "()J", "throttlePeriodAmount", "throttlePeriodUnit", "trailers", "getTrailers", "trailersBuilder", "Lokhttp3/WebSocketListener;", "webSocketListener", "getWebSocketListener", "()Lokhttp3/WebSocketListener;", "addHeader", "header", "name", "", "addHeaderLenient", "clearHeaders", "clone", "getBody", "getBodyDelay", "unit", "-deprecated_getHeaders", "getHeadersDelay", "-deprecated_getHttp2ErrorCode", "-deprecated_getSocketPolicy", "-deprecated_getStatus", "getThrottlePeriod", "-deprecated_getTrailers", "removeHeader", "setBody", "setBodyDelay", "delay", "setChunkedBody", "maxChunkSize", "setHeader", "setHeaders", "setHeadersDelay", "setHttp2ErrorCode", "setResponseCode", "code", "setSocketPolicy", "setStatus", "setTrailers", "throttleBody", "bytesPerPeriod", "period", "toString", "withPush", BaseJavaModule.METHOD_TYPE_PROMISE, "withSettings", "withWebSocketUpgrade", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Companion", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MockResponse implements Cloneable {
    private static final String CHUNKED_BODY_HEADER = "Transfer-encoding: chunked";
    public static final Companion Companion = new Companion(null);
    private Buffer body;
    private long bodyDelayAmount;
    private TimeUnit bodyDelayUnit;
    @Nullable
    private DuplexResponseBody duplexResponseBody;
    private long headersDelayAmount;
    private TimeUnit headersDelayUnit;
    private List<PushPromise> promises;
    @NotNull
    private Settings settings;
    @Nullable
    private WebSocketListener webSocketListener;
    @NotNull
    private String status = "";
    private Headers.Builder headersBuilder = new Headers.Builder();
    private Headers.Builder trailersBuilder = new Headers.Builder();
    private long throttleBytesPerPeriod = Long.MAX_VALUE;
    private long throttlePeriodAmount = 1;
    private TimeUnit throttlePeriodUnit = TimeUnit.SECONDS;
    @NotNull
    private SocketPolicy socketPolicy = SocketPolicy.KEEP_OPEN;
    private int http2ErrorCode = -1;

    /* compiled from: MockResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/mockwebserver/MockResponse$Companion;", "", "()V", "CHUNKED_BODY_HEADER", "", "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MockResponse() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.bodyDelayUnit = timeUnit;
        this.headersDelayUnit = timeUnit;
        this.promises = new ArrayList();
        this.settings = new Settings();
        setResponseCode(200);
        setHeader("Content-Length", 0L);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = HttpClientModule.ElementsRequestKey.HEADERS, imports = {}))
    @JvmName(name = "-deprecated_getHeaders")
    @NotNull
    /* renamed from: -deprecated_getHeaders  reason: not valid java name */
    public final Headers m12562deprecated_getHeaders() {
        return getHeaders();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "http2ErrorCode", imports = {}))
    @JvmName(name = "-deprecated_getHttp2ErrorCode")
    /* renamed from: -deprecated_getHttp2ErrorCode  reason: not valid java name */
    public final int m12563deprecated_getHttp2ErrorCode() {
        return this.http2ErrorCode;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "socketPolicy", imports = {}))
    @JvmName(name = "-deprecated_getSocketPolicy")
    @NotNull
    /* renamed from: -deprecated_getSocketPolicy  reason: not valid java name */
    public final SocketPolicy m12564deprecated_getSocketPolicy() {
        return this.socketPolicy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "status", imports = {}))
    @JvmName(name = "-deprecated_getStatus")
    @NotNull
    /* renamed from: -deprecated_getStatus  reason: not valid java name */
    public final String m12565deprecated_getStatus() {
        return this.status;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "trailers", imports = {}))
    @JvmName(name = "-deprecated_getTrailers")
    @NotNull
    /* renamed from: -deprecated_getTrailers  reason: not valid java name */
    public final Headers m12566deprecated_getTrailers() {
        return getTrailers();
    }

    @NotNull
    public final MockResponse addHeader(@NotNull String header) {
        Intrinsics.checkParameterIsNotNull(header, "header");
        this.headersBuilder.add(header);
        return this;
    }

    @NotNull
    public final MockResponse addHeaderLenient(@NotNull String name, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Internal.addHeaderLenient(this.headersBuilder, name, value.toString());
        return this;
    }

    @NotNull
    public final MockResponse clearHeaders() {
        this.headersBuilder = new Headers.Builder();
        return this;
    }

    @Nullable
    public final Buffer getBody() {
        Buffer buffer = this.body;
        if (buffer != null) {
            return buffer.clone();
        }
        return null;
    }

    public final long getBodyDelay(@NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return unit.convert(this.bodyDelayAmount, this.bodyDelayUnit);
    }

    @Nullable
    public final DuplexResponseBody getDuplexResponseBody() {
        return this.duplexResponseBody;
    }

    @NotNull
    public final Headers getHeaders() {
        return this.headersBuilder.build();
    }

    public final long getHeadersDelay(@NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return unit.convert(this.headersDelayAmount, this.headersDelayUnit);
    }

    public final int getHttp2ErrorCode() {
        return this.http2ErrorCode;
    }

    @NotNull
    public final List<PushPromise> getPushPromises() {
        return this.promises;
    }

    @NotNull
    public final Settings getSettings() {
        return this.settings;
    }

    @NotNull
    public final SocketPolicy getSocketPolicy() {
        return this.socketPolicy;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    public final long getThrottleBytesPerPeriod() {
        return this.throttleBytesPerPeriod;
    }

    public final long getThrottlePeriod(@NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return unit.convert(this.throttlePeriodAmount, this.throttlePeriodUnit);
    }

    @NotNull
    public final Headers getTrailers() {
        return this.trailersBuilder.build();
    }

    @Nullable
    public final WebSocketListener getWebSocketListener() {
        return this.webSocketListener;
    }

    @JvmName(name = HttpClientModule.ElementsRequestKey.HEADERS)
    public final void headers(@NotNull Headers value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.headersBuilder = value.newBuilder();
    }

    @JvmName(name = "http2ErrorCode")
    public final void http2ErrorCode(int i) {
        this.http2ErrorCode = i;
    }

    public final boolean isDuplex() {
        return this.duplexResponseBody != null;
    }

    @NotNull
    public final MockResponse removeHeader(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.headersBuilder.removeAll(name);
        return this;
    }

    @NotNull
    public final MockResponse setBody(@NotNull Buffer body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        setHeader("Content-Length", Long.valueOf(body.size()));
        this.body = body.clone();
        return this;
    }

    @NotNull
    public final MockResponse setBodyDelay(long j, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.bodyDelayAmount = j;
        this.bodyDelayUnit = unit;
        return this;
    }

    @NotNull
    public final MockResponse setChunkedBody(@NotNull Buffer body, int i) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        removeHeader("Content-Length");
        this.headersBuilder.add(CHUNKED_BODY_HEADER);
        Buffer buffer = new Buffer();
        while (!body.exhausted()) {
            long min = Math.min(body.size(), i);
            buffer.mo12598writeHexadecimalUnsignedLong(min);
            buffer.mo12607writeUtf8("\r\n");
            buffer.write(body, min);
            buffer.mo12607writeUtf8("\r\n");
        }
        buffer.mo12607writeUtf8("0\r\n");
        this.body = buffer;
        return this;
    }

    @NotNull
    public final MockResponse setHeader(@NotNull String name, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        removeHeader(name);
        addHeader(name, value);
        return this;
    }

    @NotNull
    public final MockResponse setHeaders(@NotNull Headers headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        headers(headers);
        return this;
    }

    @NotNull
    public final MockResponse setHeadersDelay(long j, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.headersDelayAmount = j;
        this.headersDelayUnit = unit;
        return this;
    }

    @NotNull
    public final MockResponse setHttp2ErrorCode(int i) {
        this.http2ErrorCode = i;
        return this;
    }

    @NotNull
    public final MockResponse setResponseCode(int i) {
        String str = (100 <= i && 199 >= i) ? "Informational" : (200 <= i && 299 >= i) ? "OK" : (300 <= i && 399 >= i) ? "Redirection" : (400 <= i && 499 >= i) ? "Client Error" : (500 <= i && 599 >= i) ? "Server Error" : "Mock Response";
        this.status = "HTTP/1.1 " + i + Chars.SPACE + str;
        return this;
    }

    @NotNull
    public final MockResponse setSocketPolicy(@NotNull SocketPolicy socketPolicy) {
        Intrinsics.checkParameterIsNotNull(socketPolicy, "socketPolicy");
        this.socketPolicy = socketPolicy;
        return this;
    }

    @NotNull
    public final MockResponse setStatus(@NotNull String status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.status = status;
        return this;
    }

    @NotNull
    public final MockResponse setTrailers(@NotNull Headers trailers) {
        Intrinsics.checkParameterIsNotNull(trailers, "trailers");
        trailers(trailers);
        return this;
    }

    @JvmName(name = "socketPolicy")
    public final void socketPolicy(@NotNull SocketPolicy socketPolicy) {
        Intrinsics.checkParameterIsNotNull(socketPolicy, "<set-?>");
        this.socketPolicy = socketPolicy;
    }

    @JvmName(name = "status")
    public final void status(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.status = str;
    }

    @NotNull
    public final MockResponse throttleBody(long j, long j2, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.throttleBytesPerPeriod = j;
        this.throttlePeriodAmount = j2;
        this.throttlePeriodUnit = unit;
        return this;
    }

    @NotNull
    public String toString() {
        return this.status;
    }

    @JvmName(name = "trailers")
    public final void trailers(@NotNull Headers value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.trailersBuilder = value.newBuilder();
    }

    @NotNull
    public final MockResponse withPush(@NotNull PushPromise promise) {
        Intrinsics.checkParameterIsNotNull(promise, "promise");
        this.promises.add(promise);
        return this;
    }

    @NotNull
    public final MockResponse withSettings(@NotNull Settings settings) {
        Intrinsics.checkParameterIsNotNull(settings, "settings");
        this.settings = settings;
        return this;
    }

    @NotNull
    public final MockResponse withWebSocketUpgrade(@NotNull WebSocketListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.status = "HTTP/1.1 101 Switching Protocols";
        setHeader("Connection", HttpHeaders.UPGRADE);
        setHeader(HttpHeaders.UPGRADE, "websocket");
        this.body = null;
        this.webSocketListener = listener;
        return this;
    }

    @NotNull
    public final MockResponse addHeader(@NotNull String name, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.headersBuilder.add(name, value.toString());
        return this;
    }

    @NotNull
    public MockResponse clone() {
        List<PushPromise> mutableList;
        Object clone = super.clone();
        if (clone != null) {
            MockResponse mockResponse = (MockResponse) clone;
            mockResponse.headersBuilder = this.headersBuilder.build().newBuilder();
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.promises);
            mockResponse.promises = mutableList;
            return mockResponse;
        }
        throw new TypeCastException("null cannot be cast to non-null type okhttp3.mockwebserver.MockResponse");
    }

    @NotNull
    public final MockResponse setBody(@NotNull String body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        return setBody(new Buffer().mo12607writeUtf8(body));
    }

    @NotNull
    public final MockResponse setBody(@NotNull DuplexResponseBody duplexResponseBody) {
        Intrinsics.checkParameterIsNotNull(duplexResponseBody, "duplexResponseBody");
        this.duplexResponseBody = duplexResponseBody;
        return this;
    }

    @NotNull
    public final MockResponse setChunkedBody(@NotNull String body, int i) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        return setChunkedBody(new Buffer().mo12607writeUtf8(body), i);
    }
}
