package okhttp3.mockwebserver;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.duplex.MwsDuplexAccess;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketProtocol;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Timeout;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.rules.ExternalResource;
/* compiled from: MockWebServer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u007f2\u00020\u00012\u00020\u0002:\u0007\u007f\u0080\u0001\u0081\u0001\u0082\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010G\u001a\u00020HH\u0002J\b\u0010I\u001a\u00020HH\u0014J\b\u0010J\u001a\u00020HH\u0014J\b\u0010K\u001a\u00020HH\u0016J\u0018\u0010L\u001a\u00020H2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\u001cH\u0002J\u000e\u0010O\u001a\u00020H2\u0006\u0010P\u001a\u00020QJ\r\u0010\"\u001a\u00020\rH\u0007¢\u0006\u0002\bRJ\r\u00103\u001a\u00020\rH\u0007¢\u0006\u0002\bSJ0\u0010T\u001a\u00020H2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u0002062\u0006\u0010P\u001a\u00020QH\u0002J\u0006\u0010Z\u001a\u00020HJ\u0010\u0010[\u001a\u00020H2\u0006\u0010\\\u001a\u00020\u001cH\u0002J\u0013\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,H\u0007¢\u0006\u0002\b]J\u0010\u0010^\u001a\u00020H2\u0006\u0010U\u001a\u00020VH\u0002J(\u0010_\u001a\u0002062\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010M\u001a\u00020\rH\u0002J\u0006\u0010`\u001a\u00020HJ\u0006\u0010a\u001a\u00020HJ\u0010\u0010b\u001a\u00020H2\u0006\u0010\\\u001a\u00020\u001cH\u0002J\u0015\u0010\n\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\bcJ\u0015\u0010)\u001a\u00020H2\u0006\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\bdJ\u001b\u00100\u001a\u00020H2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,H\u0007¢\u0006\u0002\b]J\u0015\u0010=\u001a\u00020H2\u0006\u0010:\u001a\u000209H\u0007¢\u0006\u0002\beJ\u0006\u0010f\u001a\u00020HJ\u0010\u0010g\u001a\u00020H2\u0006\u0010h\u001a\u00020\u0007H\u0002J\u0016\u0010i\u001a\u00020H2\u0006\u0010j\u001a\u00020k2\u0006\u0010!\u001a\u00020\rJ\u0010\u0010i\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010i\u001a\u00020H2\b\b\u0002\u0010!\u001a\u00020\rH\u0007J\u0006\u0010l\u001a\u000206J\u0018\u0010l\u001a\u0004\u0018\u0001062\u0006\u0010m\u001a\u00020\u00072\u0006\u0010n\u001a\u00020oJ8\u0010p\u001a\u00020H2\u0006\u0010q\u001a\u00020Q2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010r\u001a\u00020\u00072\u0006\u0010s\u001a\u00020&H\u0002J\u0006\u0010t\u001a\u00020uJ\b\u0010v\u001a\u00020\u0015H\u0016J\u000e\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020\u0015J\u0016\u0010z\u001a\u00020H2\u0006\u0010?\u001a\u00020@2\u0006\u0010F\u001a\u00020&J\u0018\u0010{\u001a\u00020H2\u0006\u0010W\u001a\u00020X2\u0006\u0010|\u001a\u00020}H\u0002J \u0010~\u001a\u00020H2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010W\u001a\u00020X2\u0006\u0010P\u001a\u00020QH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u001a\u001a&\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001c0\u001c \u001d*\u0012\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001c0\u001c\u0018\u00010\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u001f\u001a&\u0012\f\u0012\n \u001d*\u0004\u0018\u00010 0  \u001d*\u0012\u0012\f\u0012\n \u001d*\u0004\u0018\u00010 0 \u0018\u00010\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R2\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0011\u00102\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b3\u0010#R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020605X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010:\u001a\u0004\u0018\u0001092\b\u0010+\u001a\u0004\u0018\u0001098F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0083\u0001"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer;", "Lorg/junit/rules/ExternalResource;", "Ljava/io/Closeable;", "()V", "atomicRequestCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "bodyLimit", "", "getBodyLimit", "()J", "setBodyLimit", "(J)V", "clientAuth", "", "dispatcher", "Lokhttp3/mockwebserver/Dispatcher;", "getDispatcher", "()Lokhttp3/mockwebserver/Dispatcher;", "setDispatcher", "(Lokhttp3/mockwebserver/Dispatcher;)V", "hostName", "", "getHostName", "()Ljava/lang/String;", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "openClientSockets", "", "Ljava/net/Socket;", "kotlin.jvm.PlatformType", "", "openConnections", "Lokhttp3/internal/http2/Http2Connection;", "port", "getPort", "()I", "portField", "protocolNegotiationEnabled", "", "getProtocolNegotiationEnabled", "()Z", "setProtocolNegotiationEnabled", "(Z)V", "value", "", "Lokhttp3/Protocol;", "protocols", "()Ljava/util/List;", "setProtocols", "(Ljava/util/List;)V", "requestCount", "getRequestCount", "requestQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lokhttp3/mockwebserver/RecordedRequest;", "serverSocket", "Ljava/net/ServerSocket;", "Ljavax/net/ServerSocketFactory;", "serverSocketFactory", "getServerSocketFactory", "()Ljavax/net/ServerSocketFactory;", "setServerSocketFactory", "(Ljavax/net/ServerSocketFactory;)V", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "started", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunnerBackend", "Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "tunnelProxy", "acceptConnections", "", TtmlNode.ANNOTATION_POSITION_AFTER, TtmlNode.ANNOTATION_POSITION_BEFORE, "close", "dispatchBookkeepingRequest", AccessoryMetricsConstants.SEQUENCE_NUMBER, "socket", "enqueue", "response", "Lokhttp3/mockwebserver/MockResponse;", "-deprecated_port", "-deprecated_requestCount", "handleWebSocketUpgrade", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "request", "noClientAuth", "processHandshakeFailure", "raw", "-deprecated_protocols", "readEmptyLine", "readRequest", "requestClientAuth", "requireClientAuth", "serveConnection", "-deprecated_bodyLimit", "-deprecated_protocolNegotiationEnabled", "-deprecated_serverSocketFactory", "shutdown", "sleepIfDelayed", "delayMs", "start", "inetAddress", "Ljava/net/InetAddress;", "takeRequest", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "throttledTransfer", "policy", DecodeProducer.EXTRA_BITMAP_BYTES, "isRequest", "toProxyAddress", "Ljava/net/Proxy;", "toString", "url", "Lokhttp3/HttpUrl;", RouteParameter.PATH, "useHttps", "writeHeaders", HttpClientModule.ElementsRequestKey.HEADERS, "Lokhttp3/Headers;", "writeHttpResponse", "Companion", "Http2SocketHandler", "SocketHandler", "TruncatingBuffer", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MockWebServer extends ExternalResource implements Closeable {
    private static final int CLIENT_AUTH_NONE = 0;
    private static final int CLIENT_AUTH_REQUESTED = 1;
    private static final int CLIENT_AUTH_REQUIRED = 2;
    public static final Companion Companion = new Companion(null);
    private static final MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1 UNTRUSTED_TRUST_MANAGER;
    private static final Logger logger;
    private int clientAuth;
    private InetSocketAddress inetSocketAddress;
    private ServerSocket serverSocket;
    @Nullable
    private ServerSocketFactory serverSocketFactory;
    private SSLSocketFactory sslSocketFactory;
    private boolean started;
    private boolean tunnelProxy;
    private final TaskRunner.RealBackend taskRunnerBackend = new TaskRunner.RealBackend(Util.threadFactory("MockWebServer TaskRunner", false));
    private final TaskRunner taskRunner = new TaskRunner(this.taskRunnerBackend);
    private final LinkedBlockingQueue<RecordedRequest> requestQueue = new LinkedBlockingQueue<>();
    private final Set<Socket> openClientSockets = Collections.newSetFromMap(new ConcurrentHashMap());
    private final Set<Http2Connection> openConnections = Collections.newSetFromMap(new ConcurrentHashMap());
    private final AtomicInteger atomicRequestCount = new AtomicInteger();
    private long bodyLimit = Long.MAX_VALUE;
    @NotNull
    private Dispatcher dispatcher = new QueueDispatcher();
    private int portField = -1;
    private boolean protocolNegotiationEnabled = true;
    @NotNull
    private List<? extends Protocol> protocols = Util.immutableListOf(Protocol.HTTP_2, Protocol.HTTP_1_1);

    /* compiled from: MockWebServer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$Companion;", "", "()V", "CLIENT_AUTH_NONE", "", "CLIENT_AUTH_REQUESTED", "CLIENT_AUTH_REQUIRED", "UNTRUSTED_TRUST_MANAGER", "okhttp3/mockwebserver/MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1", "Lokhttp3/mockwebserver/MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1;", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: MockWebServer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J&\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$Http2SocketHandler;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "socket", "Ljava/net/Socket;", "protocol", "Lokhttp3/Protocol;", "(Lokhttp3/mockwebserver/MockWebServer;Ljava/net/Socket;Lokhttp3/Protocol;)V", AccessoryMetricsConstants.SEQUENCE_NUMBER, "Ljava/util/concurrent/atomic/AtomicInteger;", "onStream", "", "stream", "Lokhttp3/internal/http2/Http2Stream;", "pushPromises", "request", "Lokhttp3/mockwebserver/RecordedRequest;", "promises", "", "Lokhttp3/mockwebserver/PushPromise;", "readRequest", "writeResponse", "response", "Lokhttp3/mockwebserver/MockResponse;", "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private final class Http2SocketHandler extends Http2Connection.Listener {
        private final Protocol protocol;
        private final AtomicInteger sequenceNumber;
        private final Socket socket;
        final /* synthetic */ MockWebServer this$0;

        public Http2SocketHandler(@NotNull MockWebServer mockWebServer, @NotNull Socket socket, Protocol protocol) {
            Intrinsics.checkParameterIsNotNull(socket, "socket");
            Intrinsics.checkParameterIsNotNull(protocol, "protocol");
            this.this$0 = mockWebServer;
            this.socket = socket;
            this.protocol = protocol;
            this.sequenceNumber = new AtomicInteger();
        }

        private final void pushPromises(Http2Stream http2Stream, RecordedRequest recordedRequest, List<PushPromise> list) throws IOException {
            List emptyList;
            for (PushPromise pushPromise : list) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Header(Header.TARGET_AUTHORITY, this.this$0.url(pushPromise.path()).host()));
                arrayList.add(new Header(Header.TARGET_METHOD, pushPromise.method()));
                arrayList.add(new Header(Header.TARGET_PATH, pushPromise.path()));
                Iterator<Pair<? extends String, ? extends String>> it2 = pushPromise.headers().iterator();
                while (it2.hasNext()) {
                    Pair<? extends String, ? extends String> next = it2.next();
                    arrayList.add(new Header(next.component1(), next.component2()));
                }
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                this.this$0.requestQueue.add(new RecordedRequest(pushPromise.method() + Chars.SPACE + pushPromise.path() + " HTTP/1.1", pushPromise.headers(), emptyList, 0L, new Buffer(), this.sequenceNumber.getAndIncrement(), this.socket, null, 128, null));
                writeResponse(http2Stream.getConnection().pushStream(http2Stream.getId(), arrayList, pushPromise.response().getBody() != null), recordedRequest, pushPromise.response());
            }
        }

        private final RecordedRequest readRequest(Http2Stream http2Stream) throws IOException {
            List emptyList;
            List<Header> listOf;
            boolean equals;
            Headers takeHeaders = http2Stream.takeHeaders();
            Headers.Builder builder = new Headers.Builder();
            Iterator<Pair<? extends String, ? extends String>> it2 = takeHeaders.iterator();
            boolean z = true;
            String str = "<:path omitted>";
            String str2 = "<:method omitted>";
            boolean z2 = true;
            while (it2.hasNext()) {
                Pair<? extends String, ? extends String> next = it2.next();
                String component1 = next.component1();
                String component2 = next.component2();
                if (Intrinsics.areEqual(component1, Header.TARGET_METHOD_UTF8)) {
                    str2 = component2;
                } else if (Intrinsics.areEqual(component1, Header.TARGET_PATH_UTF8)) {
                    str = component2;
                } else {
                    Protocol protocol = this.protocol;
                    if (protocol != Protocol.HTTP_2 && protocol != Protocol.H2_PRIOR_KNOWLEDGE) {
                        throw new IllegalStateException();
                    }
                    builder.add(component1, component2);
                }
                if (Intrinsics.areEqual(component1, "expect")) {
                    equals = StringsKt__StringsJVMKt.equals(component2, "100-continue", true);
                    if (equals) {
                        z2 = false;
                    }
                }
            }
            Headers build = builder.build();
            MockResponse peek = this.this$0.getDispatcher().peek();
            if (z2 || peek.getSocketPolicy() != SocketPolicy.EXPECT_CONTINUE) {
                z = z2;
            } else {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(new Header(Header.RESPONSE_STATUS, ByteString.Companion.encodeUtf8("100 Continue")));
                http2Stream.writeHeaders(listOf, false, true);
                http2Stream.getConnection().flush();
            }
            Buffer buffer = new Buffer();
            String str3 = str2 + Chars.SPACE + str + " HTTP/1.1";
            IOException e = null;
            if (z && !peek.isDuplex()) {
                try {
                    String str4 = build.get("content-length");
                    this.this$0.throttledTransfer(peek, this.socket, Okio.buffer(http2Stream.getSource()), buffer, str4 != null ? Long.parseLong(str4) : Long.MAX_VALUE, true);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return new RecordedRequest(str3, build, emptyList, buffer.size(), buffer, this.sequenceNumber.getAndIncrement(), this.socket, e);
        }

        private final void writeResponse(Http2Stream http2Stream, RecordedRequest recordedRequest, MockResponse mockResponse) throws IOException {
            List split$default;
            http2Stream.getConnection().setSettings(mockResponse.getSettings());
            if (mockResponse.getSocketPolicy() == SocketPolicy.NO_RESPONSE) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            split$default = StringsKt__StringsKt.split$default((CharSequence) mockResponse.getStatus(), new char[]{Chars.SPACE}, false, 3, 2, (Object) null);
            long headersDelay = mockResponse.getHeadersDelay(TimeUnit.MILLISECONDS);
            long bodyDelay = mockResponse.getBodyDelay(TimeUnit.MILLISECONDS);
            if (split$default.size() >= 2) {
                arrayList.add(new Header(Header.RESPONSE_STATUS, (String) split$default.get(1)));
                Iterator<Pair<? extends String, ? extends String>> it2 = mockResponse.getHeaders().iterator();
                while (it2.hasNext()) {
                    Pair<? extends String, ? extends String> next = it2.next();
                    arrayList.add(new Header(next.component1(), next.component2()));
                }
                Headers trailers = mockResponse.getTrailers();
                this.this$0.sleepIfDelayed(headersDelay);
                Buffer body = mockResponse.getBody();
                boolean z2 = body == null && mockResponse.getPushPromises().isEmpty() && !mockResponse.isDuplex();
                boolean z3 = body == null || bodyDelay != 0;
                if (!z2 || trailers.size() == 0) {
                    z = true;
                }
                if (z) {
                    http2Stream.writeHeaders(arrayList, z2, z3);
                    if (trailers.size() > 0) {
                        http2Stream.enqueueTrailers(trailers);
                    }
                    pushPromises(http2Stream, recordedRequest, mockResponse.getPushPromises());
                    if (body != null) {
                        BufferedSink buffer = Okio.buffer(http2Stream.getSink());
                        try {
                            this.this$0.sleepIfDelayed(bodyDelay);
                            this.this$0.throttledTransfer(mockResponse, this.socket, body, buffer, body.size(), false);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer, null);
                            return;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(buffer, th);
                                throw th2;
                            }
                        }
                    } else if (!mockResponse.isDuplex()) {
                        if (z2) {
                            return;
                        }
                        http2Stream.close(ErrorCode.NO_ERROR, null);
                        return;
                    } else {
                        DuplexResponseBody duplexResponseBody = mockResponse.getDuplexResponseBody();
                        if (duplexResponseBody == null) {
                            Intrinsics.throwNpe();
                        }
                        duplexResponseBody.onRequest(recordedRequest, http2Stream);
                        return;
                    }
                }
                throw new IllegalArgumentException(("unsupported: no body and non-empty trailers " + trailers).toString());
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected status: ");
            outline107.append(mockResponse.getStatus());
            throw new AssertionError(outline107.toString());
        }

        @Override // okhttp3.internal.http2.Http2Connection.Listener
        public void onStream(@NotNull Http2Stream stream) throws IOException {
            Intrinsics.checkParameterIsNotNull(stream, "stream");
            MockResponse peek = this.this$0.getDispatcher().peek();
            if (peek.getSocketPolicy() == SocketPolicy.RESET_STREAM_AT_START) {
                this.this$0.dispatchBookkeepingRequest(this.sequenceNumber.getAndIncrement(), this.socket);
                ErrorCode fromHttp2 = ErrorCode.Companion.fromHttp2(peek.getHttp2ErrorCode());
                if (fromHttp2 == null) {
                    Intrinsics.throwNpe();
                }
                stream.close(fromHttp2, null);
                return;
            }
            RecordedRequest readRequest = readRequest(stream);
            this.this$0.atomicRequestCount.incrementAndGet();
            this.this$0.requestQueue.add(readRequest);
            if (readRequest.getFailure() != null) {
                return;
            }
            MockResponse dispatch = this.this$0.getDispatcher().dispatch(readRequest);
            if (dispatch.getSocketPolicy() == SocketPolicy.DISCONNECT_AFTER_REQUEST) {
                this.socket.close();
                return;
            }
            writeResponse(stream, readRequest, dispatch);
            if (MockWebServer.logger.isLoggable(Level.INFO)) {
                Logger logger = MockWebServer.logger;
                logger.info(this.this$0 + " received request: " + readRequest + Chars.SPACE + "and responded: " + dispatch + " protocol is " + this.protocol);
            }
            if (dispatch.getSocketPolicy() != SocketPolicy.DISCONNECT_AT_END) {
                return;
            }
            stream.getConnection().shutdown(ErrorCode.NO_ERROR);
        }
    }

    /* compiled from: MockWebServer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$SocketHandler;", "", "raw", "Ljava/net/Socket;", "(Lokhttp3/mockwebserver/MockWebServer;Ljava/net/Socket;)V", AccessoryMetricsConstants.SEQUENCE_NUMBER, "", "createTunnel", "", "handle", "processOneRequest", "", "socket", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class SocketHandler {
        private final Socket raw;
        private int sequenceNumber;
        final /* synthetic */ MockWebServer this$0;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SocketPolicy.values().length];

            static {
                $EnumSwitchMapping$0[SocketPolicy.DISCONNECT_AT_END.ordinal()] = 1;
                $EnumSwitchMapping$0[SocketPolicy.SHUTDOWN_INPUT_AT_END.ordinal()] = 2;
                $EnumSwitchMapping$0[SocketPolicy.SHUTDOWN_OUTPUT_AT_END.ordinal()] = 3;
                $EnumSwitchMapping$0[SocketPolicy.SHUTDOWN_SERVER_AFTER_RESPONSE.ordinal()] = 4;
            }
        }

        public SocketHandler(@NotNull MockWebServer mockWebServer, Socket raw) {
            Intrinsics.checkParameterIsNotNull(raw, "raw");
            this.this$0 = mockWebServer;
            this.raw = raw;
        }

        private final void createTunnel() throws IOException, InterruptedException {
            SocketPolicy socketPolicy;
            BufferedSource buffer = Okio.buffer(Okio.source(this.raw));
            BufferedSink buffer2 = Okio.buffer(Okio.sink(this.raw));
            do {
                socketPolicy = this.this$0.getDispatcher().peek().getSocketPolicy();
                if (!processOneRequest(this.raw, buffer, buffer2)) {
                    throw new IllegalStateException("Tunnel without any CONNECT!".toString());
                }
            } while (socketPolicy != SocketPolicy.UPGRADE_TO_SSL_AT_END);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d2  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00ef  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final boolean processOneRequest(java.net.Socket r11, okio.BufferedSource r12, okio.BufferedSink r13) throws java.io.IOException, java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 243
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.mockwebserver.MockWebServer.SocketHandler.processOneRequest(java.net.Socket, okio.BufferedSource, okio.BufferedSink):boolean");
        }

        public final void handle() throws Exception {
            Socket socket;
            SocketPolicy socketPolicy = this.this$0.getDispatcher().peek().getSocketPolicy();
            Protocol protocol = Protocol.HTTP_1_1;
            if (this.this$0.sslSocketFactory != null) {
                if (this.this$0.tunnelProxy) {
                    createTunnel();
                }
                if (socketPolicy == SocketPolicy.FAIL_HANDSHAKE) {
                    this.this$0.dispatchBookkeepingRequest(this.sequenceNumber, this.raw);
                    this.this$0.processHandshakeFailure(this.raw);
                    return;
                }
                SSLSocketFactory sSLSocketFactory = this.this$0.sslSocketFactory;
                if (sSLSocketFactory == null) {
                    Intrinsics.throwNpe();
                }
                Socket socket2 = this.raw;
                InetAddress inetAddress = socket2.getInetAddress();
                Intrinsics.checkExpressionValueIsNotNull(inetAddress, "raw.inetAddress");
                socket = sSLSocketFactory.createSocket(socket2, inetAddress.getHostAddress(), this.raw.getPort(), true);
                Intrinsics.checkExpressionValueIsNotNull(socket, "sslSocketFactory!!.creat…          raw.port, true)");
                SSLSocket sSLSocket = (SSLSocket) socket;
                sSLSocket.setUseClientMode(false);
                if (this.this$0.clientAuth != 2) {
                    if (this.this$0.clientAuth == 1) {
                        sSLSocket.setWantClientAuth(true);
                    }
                } else {
                    sSLSocket.setNeedClientAuth(true);
                }
                this.this$0.openClientSockets.add(socket);
                if (this.this$0.getProtocolNegotiationEnabled()) {
                    Platform.Companion.get().configureTlsExtensions(sSLSocket, null, this.this$0.protocols());
                }
                sSLSocket.startHandshake();
                if (this.this$0.getProtocolNegotiationEnabled()) {
                    String selectedProtocol = Platform.Companion.get().getSelectedProtocol(sSLSocket);
                    protocol = selectedProtocol != null ? Protocol.Companion.get(selectedProtocol) : Protocol.HTTP_1_1;
                    Platform.Companion.get().afterHandshake(sSLSocket);
                }
                this.this$0.openClientSockets.remove(this.raw);
            } else if (this.this$0.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                socket = this.raw;
                protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            } else {
                socket = this.raw;
            }
            if (socketPolicy == SocketPolicy.STALL_SOCKET_AT_START) {
                this.this$0.dispatchBookkeepingRequest(this.sequenceNumber, socket);
            } else if (protocol != Protocol.HTTP_2 && protocol != Protocol.H2_PRIOR_KNOWLEDGE) {
                if (protocol == Protocol.HTTP_1_1) {
                    do {
                    } while (processOneRequest(socket, Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket))));
                    if (this.sequenceNumber == 0) {
                        Logger logger = MockWebServer.logger;
                        logger.warning(this.this$0 + " connection from " + this.raw.getInetAddress() + " didn't make a request");
                    }
                    socket.close();
                    this.this$0.openClientSockets.remove(socket);
                    return;
                }
                throw new AssertionError();
            } else {
                Http2Connection build = Http2Connection.Builder.socket$default(new Http2Connection.Builder(false, this.this$0.taskRunner), socket, null, null, null, 14, null).listener(new Http2SocketHandler(this.this$0, socket, protocol)).build();
                Http2Connection.start$default(build, false, 1, null);
                this.this$0.openConnections.add(build);
                this.this$0.openClientSockets.remove(socket);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MockWebServer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$TruncatingBuffer;", "Lokio/Sink;", "remainingByteCount", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$mockwebserver", "()Lokio/Buffer;", "receivedByteCount", "getReceivedByteCount$mockwebserver", "()J", "setReceivedByteCount$mockwebserver", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "timeout", "Lokio/Timeout;", "write", "source", DecodeProducer.EXTRA_BITMAP_BYTES, "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TruncatingBuffer implements Sink {
        @NotNull
        private final Buffer buffer = new Buffer();
        private long receivedByteCount;
        private long remainingByteCount;

        public TruncatingBuffer(long j) {
            this.remainingByteCount = j;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        @NotNull
        public final Buffer getBuffer$mockwebserver() {
            return this.buffer;
        }

        public final long getReceivedByteCount$mockwebserver() {
            return this.receivedByteCount;
        }

        public final void setReceivedByteCount$mockwebserver(long j) {
            this.receivedByteCount = j;
        }

        @Override // okio.Sink
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12584timeout() {
            return Timeout.NONE;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) throws IOException {
            Intrinsics.checkParameterIsNotNull(source, "source");
            long min = Math.min(this.remainingByteCount, j);
            if (min > 0) {
                source.read(this.buffer, min);
            }
            long j2 = j - min;
            if (j2 > 0) {
                source.skip(j2);
            }
            this.remainingByteCount -= min;
            this.receivedByteCount += j;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [okhttp3.mockwebserver.MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1] */
    static {
        MwsDuplexAccess.instance = new MwsDuplexAccess() { // from class: okhttp3.mockwebserver.MockWebServer.Companion.1
            @Override // okhttp3.internal.duplex.MwsDuplexAccess
            public void setBody(@NotNull MockResponse mockResponse, @NotNull DuplexResponseBody duplexResponseBody) {
                Intrinsics.checkParameterIsNotNull(mockResponse, "mockResponse");
                Intrinsics.checkParameterIsNotNull(duplexResponseBody, "duplexResponseBody");
                mockResponse.setBody(duplexResponseBody);
            }
        };
        UNTRUSTED_TRUST_MANAGER = new X509TrustManager() { // from class: okhttp3.mockwebserver.MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1
            @Override // javax.net.ssl.X509TrustManager
            @NotNull
            public X509Certificate[] getAcceptedIssuers() {
                throw new AssertionError();
            }

            @Override // javax.net.ssl.X509TrustManager
            @NotNull
            /* renamed from: checkClientTrusted */
            public Void mo12575checkClientTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) throws CertificateException {
                Intrinsics.checkParameterIsNotNull(chain, "chain");
                Intrinsics.checkParameterIsNotNull(authType, "authType");
                throw new CertificateException();
            }

            @Override // javax.net.ssl.X509TrustManager
            @NotNull
            /* renamed from: checkServerTrusted */
            public Void mo12576checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) {
                Intrinsics.checkParameterIsNotNull(chain, "chain");
                Intrinsics.checkParameterIsNotNull(authType, "authType");
                throw new AssertionError();
            }
        };
        logger = Logger.getLogger(MockWebServer.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void acceptConnections() throws Exception {
        while (true) {
            try {
                ServerSocket serverSocket = this.serverSocket;
                if (serverSocket == null) {
                    Intrinsics.throwNpe();
                }
                Socket accept = serverSocket.accept();
                Intrinsics.checkExpressionValueIsNotNull(accept, "serverSocket!!.accept()");
                if (this.dispatcher.peek().getSocketPolicy() == SocketPolicy.DISCONNECT_AT_START) {
                    dispatchBookkeepingRequest(0, accept);
                    accept.close();
                } else {
                    this.openClientSockets.add(accept);
                    serveConnection(accept);
                }
            } catch (SocketException e) {
                Logger logger2 = logger;
                logger2.info(this + " done accepting connections: " + e.getMessage());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchBookkeepingRequest(int i, Socket socket) throws InterruptedException {
        List emptyList;
        Headers of = Headers.Companion.of(new String[0]);
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        RecordedRequest recordedRequest = new RecordedRequest("", of, emptyList, 0L, new Buffer(), i, socket, null, 128, null);
        this.atomicRequestCount.incrementAndGet();
        this.requestQueue.add(recordedRequest);
        this.dispatcher.dispatch(recordedRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleWebSocketUpgrade(Socket socket, final BufferedSource bufferedSource, final BufferedSink bufferedSink, RecordedRequest recordedRequest, MockResponse mockResponse) throws IOException {
        List split$default;
        String header = recordedRequest.getHeader(HttpHeaders.SEC_WEBSOCKET_KEY);
        WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
        if (header == null) {
            Intrinsics.throwNpe();
        }
        mockResponse.setHeader(HttpHeaders.SEC_WEBSOCKET_ACCEPT, webSocketProtocol.acceptHeader(header));
        writeHttpResponse(socket, bufferedSink, mockResponse);
        String str = recordedRequest.getTlsVersion() != null ? "https" : "http";
        String header2 = recordedRequest.getHeader("Host");
        Request.Builder builder = new Request.Builder();
        Request build = builder.url(str + "://" + header2 + '/').headers(recordedRequest.getHeaders()).build();
        split$default = StringsKt__StringsKt.split$default((CharSequence) mockResponse.getStatus(), new char[]{Chars.SPACE}, false, 3, 2, (Object) null);
        Response build2 = new Response.Builder().code(Integer.parseInt((String) split$default.get(1))).message((String) split$default.get(2)).headers(mockResponse.getHeaders()).request(build).protocol(Protocol.HTTP_1_1).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        RealWebSocket.Streams streams = new RealWebSocket.Streams(false, bufferedSource, bufferedSink) { // from class: okhttp3.mockwebserver.MockWebServer$handleWebSocketUpgrade$streams$1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                countDownLatch.countDown();
            }
        };
        TaskRunner taskRunner = this.taskRunner;
        WebSocketListener webSocketListener = mockResponse.getWebSocketListener();
        if (webSocketListener == null) {
            Intrinsics.throwNpe();
        }
        RealWebSocket realWebSocket = new RealWebSocket(taskRunner, build, webSocketListener, new SecureRandom(), 0L);
        WebSocketListener webSocketListener2 = mockResponse.getWebSocketListener();
        if (webSocketListener2 == null) {
            Intrinsics.throwNpe();
        }
        webSocketListener2.onOpen(realWebSocket, build2);
        StringBuilder sb = new StringBuilder();
        sb.append("MockWebServer WebSocket ");
        String path = recordedRequest.getPath();
        if (path == null) {
            Intrinsics.throwNpe();
        }
        sb.append(path);
        realWebSocket.initReaderAndWriter(sb.toString(), streams);
        try {
            try {
                realWebSocket.loopReader();
                countDownLatch.await();
            } catch (IOException e) {
                realWebSocket.failWebSocket(e, null);
            }
        } finally {
            Util.closeQuietly(bufferedSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processHandshakeFailure(Socket socket) throws Exception {
        SSLContext context = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
        context.init(null, new TrustManager[]{UNTRUSTED_TRUST_MANAGER}, new SecureRandom());
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        SSLSocketFactory socketFactory = context.getSocketFactory();
        InetAddress inetAddress = socket.getInetAddress();
        Intrinsics.checkExpressionValueIsNotNull(inetAddress, "raw.inetAddress");
        Socket createSocket = socketFactory.createSocket(socket, inetAddress.getHostAddress(), socket.getPort(), true);
        if (createSocket == null) {
            throw new TypeCastException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
        }
        SSLSocket sSLSocket = (SSLSocket) createSocket;
        try {
            sSLSocket.startHandshake();
            throw new AssertionError();
        } catch (IOException unused) {
            sSLSocket.close();
        }
    }

    private final void readEmptyLine(BufferedSource bufferedSource) throws IOException {
        String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
        if (readUtf8LineStrict.length() == 0) {
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Expected empty but was: ", readUtf8LineStrict).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f2 A[Catch: IOException -> 0x01ad, TryCatch #1 {IOException -> 0x01ad, blocks: (B:4:0x001c, B:10:0x002c, B:16:0x003e, B:18:0x004a, B:22:0x0052, B:23:0x0064, B:29:0x0079, B:41:0x00d8, B:43:0x00e0, B:50:0x00f2, B:51:0x010c, B:32:0x008e, B:34:0x0094, B:36:0x00a8, B:37:0x00ae, B:38:0x00ce, B:39:0x00d5, B:20:0x004e, B:52:0x010d, B:54:0x0119, B:57:0x0128, B:59:0x0133, B:61:0x014a, B:63:0x0154, B:66:0x016e, B:68:0x0178, B:73:0x019d, B:74:0x01a4, B:75:0x01a5, B:76:0x01ac), top: B:86:0x001c }] */
    /* JADX WARN: Type inference failed for: r21v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r21v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okhttp3.mockwebserver.RecordedRequest readRequest(java.net.Socket r23, okio.BufferedSource r24, okio.BufferedSink r25, int r26) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.mockwebserver.MockWebServer.readRequest(java.net.Socket, okio.BufferedSource, okio.BufferedSink, int):okhttp3.mockwebserver.RecordedRequest");
    }

    private final void serveConnection(final Socket socket) {
        TaskQueue newQueue = this.taskRunner.newQueue();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MockWebServer ");
        outline107.append(socket.getRemoteSocketAddress());
        final String sb = outline107.toString();
        newQueue.schedule(new Task(sb, false) { // from class: okhttp3.mockwebserver.MockWebServer$serveConnection$$inlined$execute$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    new MockWebServer.SocketHandler(this, socket).handle();
                    return -1L;
                } catch (IOException e) {
                    Logger logger2 = MockWebServer.logger;
                    logger2.info(this + " connection from " + socket.getInetAddress() + " failed: " + e);
                    return -1L;
                } catch (Exception e2) {
                    Logger logger3 = MockWebServer.logger;
                    Level level = Level.SEVERE;
                    logger3.log(level, this + " connection from " + socket.getInetAddress() + " crashed", (Throwable) e2);
                    return -1L;
                }
            }
        }, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sleepIfDelayed(long j) {
        if (j != 0) {
            Thread.sleep(j);
        }
    }

    public static /* synthetic */ void start$default(MockWebServer mockWebServer, int i, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        mockWebServer.start(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void throttledTransfer(MockResponse mockResponse, Socket socket, BufferedSource bufferedSource, BufferedSink bufferedSink, long j, boolean z) throws IOException {
        long j2 = 0;
        if (j == 0) {
            return;
        }
        Buffer buffer = new Buffer();
        long throttleBytesPerPeriod = mockResponse.getThrottleBytesPerPeriod();
        long throttlePeriod = mockResponse.getThrottlePeriod(TimeUnit.MILLISECONDS);
        long j3 = j / 2;
        boolean z2 = false;
        if (!z ? mockResponse.getSocketPolicy() == SocketPolicy.DISCONNECT_DURING_RESPONSE_BODY : mockResponse.getSocketPolicy() == SocketPolicy.DISCONNECT_DURING_REQUEST_BODY) {
            z2 = true;
        }
        long j4 = j;
        while (!socket.isClosed()) {
            long j5 = j4;
            long j6 = j2;
            while (j6 < throttleBytesPerPeriod) {
                long min = Math.min(j5, throttleBytesPerPeriod - j6);
                if (z2) {
                    min = Math.min(min, j5 - j3);
                }
                long read = bufferedSource.read(buffer, min);
                if (read == -1) {
                    return;
                }
                bufferedSink.write(buffer, read);
                bufferedSink.flush();
                j6 += read;
                j5 -= read;
                if (z2 && j5 == j3) {
                    socket.close();
                    return;
                } else if (j5 == 0) {
                    return;
                } else {
                    j2 = 0;
                }
            }
            sleepIfDelayed(throttlePeriod);
            j4 = j5;
            j2 = j2;
        }
    }

    private final void writeHeaders(BufferedSink bufferedSink, Headers headers) throws IOException {
        Iterator<Pair<? extends String, ? extends String>> it2 = headers.iterator();
        while (it2.hasNext()) {
            Pair<? extends String, ? extends String> next = it2.next();
            bufferedSink.mo12607writeUtf8(next.component1());
            bufferedSink.mo12607writeUtf8(RealTimeTextConstants.COLON_SPACE);
            bufferedSink.mo12607writeUtf8(next.component2());
            bufferedSink.mo12607writeUtf8("\r\n");
        }
        bufferedSink.mo12607writeUtf8("\r\n");
        bufferedSink.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeHttpResponse(Socket socket, BufferedSink bufferedSink, MockResponse mockResponse) throws IOException {
        boolean equals;
        sleepIfDelayed(mockResponse.getHeadersDelay(TimeUnit.MILLISECONDS));
        bufferedSink.mo12607writeUtf8(mockResponse.getStatus());
        bufferedSink.mo12607writeUtf8("\r\n");
        writeHeaders(bufferedSink, mockResponse.getHeaders());
        Buffer body = mockResponse.getBody();
        if (body != null) {
            sleepIfDelayed(mockResponse.getBodyDelay(TimeUnit.MILLISECONDS));
            throttledTransfer(mockResponse, socket, body, bufferedSink, body.size(), false);
            equals = StringsKt__StringsJVMKt.equals("chunked", mockResponse.getHeaders().get(HttpHeaders.TRANSFER_ENCODING), true);
            if (!equals) {
                return;
            }
            writeHeaders(bufferedSink, mockResponse.getTrailers());
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.bodyLimit = bodyLimit }", imports = {}))
    @JvmName(name = "-deprecated_bodyLimit")
    /* renamed from: -deprecated_bodyLimit  reason: not valid java name */
    public final void m12568deprecated_bodyLimit(long j) {
        this.bodyLimit = j;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}))
    @JvmName(name = "-deprecated_port")
    /* renamed from: -deprecated_port  reason: not valid java name */
    public final int m12569deprecated_port() {
        return getPort();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.protocolNegotiationEnabled = protocolNegotiationEnabled }", imports = {}))
    @JvmName(name = "-deprecated_protocolNegotiationEnabled")
    /* renamed from: -deprecated_protocolNegotiationEnabled  reason: not valid java name */
    public final void m12570deprecated_protocolNegotiationEnabled(boolean z) {
        this.protocolNegotiationEnabled = z;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.protocols = protocols }", imports = {}))
    @JvmName(name = "-deprecated_protocols")
    /* renamed from: -deprecated_protocols  reason: not valid java name */
    public final void m12572deprecated_protocols(@NotNull List<? extends Protocol> protocols) {
        Intrinsics.checkParameterIsNotNull(protocols, "protocols");
        setProtocols(protocols);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "requestCount", imports = {}))
    @JvmName(name = "-deprecated_requestCount")
    /* renamed from: -deprecated_requestCount  reason: not valid java name */
    public final int m12573deprecated_requestCount() {
        return getRequestCount();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.serverSocketFactory = serverSocketFactory }", imports = {}))
    @JvmName(name = "-deprecated_serverSocketFactory")
    /* renamed from: -deprecated_serverSocketFactory  reason: not valid java name */
    public final void m12574deprecated_serverSocketFactory(@NotNull ServerSocketFactory serverSocketFactory) {
        Intrinsics.checkParameterIsNotNull(serverSocketFactory, "serverSocketFactory");
        setServerSocketFactory(serverSocketFactory);
    }

    @Override // org.junit.rules.ExternalResource
    protected synchronized void after() {
        try {
            shutdown();
        } catch (IOException e) {
            logger.log(Level.WARNING, "MockWebServer shutdown failed", (Throwable) e);
        }
    }

    @Override // org.junit.rules.ExternalResource
    protected synchronized void before() {
        if (this.started) {
            return;
        }
        try {
            start$default(this, 0, 1, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        shutdown();
    }

    public final void enqueue(@NotNull MockResponse response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        Dispatcher dispatcher = this.dispatcher;
        if (dispatcher != null) {
            ((QueueDispatcher) dispatcher).enqueueResponse(response.clone());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type okhttp3.mockwebserver.QueueDispatcher");
    }

    public final long getBodyLimit() {
        return this.bodyLimit;
    }

    @NotNull
    public final Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @NotNull
    public final String getHostName() {
        before();
        InetSocketAddress inetSocketAddress = this.inetSocketAddress;
        if (inetSocketAddress == null) {
            Intrinsics.throwNpe();
        }
        InetAddress address = inetSocketAddress.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "inetSocketAddress!!.address");
        String canonicalHostName = address.getCanonicalHostName();
        Intrinsics.checkExpressionValueIsNotNull(canonicalHostName, "inetSocketAddress!!.address.canonicalHostName");
        return canonicalHostName;
    }

    public final int getPort() {
        before();
        return this.portField;
    }

    public final boolean getProtocolNegotiationEnabled() {
        return this.protocolNegotiationEnabled;
    }

    public final int getRequestCount() {
        return this.atomicRequestCount.get();
    }

    @Nullable
    public final ServerSocketFactory getServerSocketFactory() {
        if (this.serverSocketFactory == null && this.started) {
            this.serverSocketFactory = ServerSocketFactory.getDefault();
        }
        return this.serverSocketFactory;
    }

    public final void noClientAuth() {
        this.clientAuth = 0;
    }

    @JvmName(name = "protocols")
    @NotNull
    public final List<Protocol> protocols() {
        return this.protocols;
    }

    public final void requestClientAuth() {
        this.clientAuth = 1;
    }

    public final void requireClientAuth() {
        this.clientAuth = 2;
    }

    public final void setBodyLimit(long j) {
        this.bodyLimit = j;
    }

    public final void setDispatcher(@NotNull Dispatcher dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "<set-?>");
        this.dispatcher = dispatcher;
    }

    public final void setProtocolNegotiationEnabled(boolean z) {
        this.protocolNegotiationEnabled = z;
    }

    public final void setProtocols(@NotNull List<? extends Protocol> value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<? extends Protocol> immutableList = Util.toImmutableList(value);
        boolean z = false;
        if (!immutableList.contains(Protocol.H2_PRIOR_KNOWLEDGE) || immutableList.size() == 1) {
            if (immutableList.contains(Protocol.HTTP_1_1) || immutableList.contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                z = true;
            }
            if (z) {
                if (!immutableList.contains(null)) {
                    this.protocols = immutableList;
                    return;
                }
                throw new IllegalArgumentException("protocols must not contain null".toString());
            }
            throw new IllegalArgumentException(("protocols doesn't contain http/1.1: " + immutableList).toString());
        }
        throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + immutableList).toString());
    }

    public final void setServerSocketFactory(@Nullable ServerSocketFactory serverSocketFactory) {
        if (!this.started) {
            this.serverSocketFactory = serverSocketFactory;
            return;
        }
        throw new IllegalStateException("serverSocketFactory must not be set after start()".toString());
    }

    public final synchronized void shutdown() throws IOException {
        if (!this.started) {
            return;
        }
        if (this.serverSocket != null) {
            ServerSocket serverSocket = this.serverSocket;
            if (serverSocket == null) {
                Intrinsics.throwNpe();
            }
            serverSocket.close();
            for (TaskQueue taskQueue : this.taskRunner.activeQueues()) {
                if (!taskQueue.idleLatch().await(5L, TimeUnit.SECONDS)) {
                    throw new IOException("Gave up waiting for queue to shut down");
                }
            }
            this.taskRunnerBackend.shutdown();
            return;
        }
        throw new IllegalArgumentException("shutdown() before start()".toString());
    }

    @JvmOverloads
    public final void start() throws IOException {
        start$default(this, 0, 1, null);
    }

    @JvmOverloads
    public final void start(int i) throws IOException {
        InetAddress byName = InetAddress.getByName(AndroidInfoHelpers.DEVICE_LOCALHOST);
        Intrinsics.checkExpressionValueIsNotNull(byName, "InetAddress.getByName(\"localhost\")");
        start(byName, i);
    }

    @NotNull
    public final RecordedRequest takeRequest() throws InterruptedException {
        RecordedRequest take = this.requestQueue.take();
        Intrinsics.checkExpressionValueIsNotNull(take, "requestQueue.take()");
        return take;
    }

    @NotNull
    public final Proxy toProxyAddress() {
        before();
        InetSocketAddress inetSocketAddress = this.inetSocketAddress;
        if (inetSocketAddress == null) {
            Intrinsics.throwNpe();
        }
        InetAddress address = inetSocketAddress.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "inetSocketAddress!!.address");
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address.getCanonicalHostName(), getPort()));
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline85(GeneratedOutlineSupport1.outline107("MockWebServer["), this.portField, JsonReaderKt.END_LIST);
    }

    @NotNull
    public final HttpUrl url(@NotNull String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        HttpUrl resolve = new HttpUrl.Builder().scheme(this.sslSocketFactory != null ? "https" : "http").host(getHostName()).port(getPort()).build().resolve(path);
        if (resolve == null) {
            Intrinsics.throwNpe();
        }
        return resolve;
    }

    public final void useHttps(@NotNull SSLSocketFactory sslSocketFactory, boolean z) {
        Intrinsics.checkParameterIsNotNull(sslSocketFactory, "sslSocketFactory");
        this.sslSocketFactory = sslSocketFactory;
        this.tunnelProxy = z;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    @JvmName(name = "-deprecated_protocols")
    @NotNull
    /* renamed from: -deprecated_protocols  reason: not valid java name */
    public final List<Protocol> m12571deprecated_protocols() {
        return this.protocols;
    }

    public final void start(@NotNull InetAddress inetAddress, int i) throws IOException {
        Intrinsics.checkParameterIsNotNull(inetAddress, "inetAddress");
        start(new InetSocketAddress(inetAddress, i));
    }

    @Nullable
    public final RecordedRequest takeRequest(long j, @NotNull TimeUnit unit) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return this.requestQueue.poll(j, unit);
    }

    private final synchronized void start(InetSocketAddress inetSocketAddress) throws IOException {
        boolean z = true;
        if (!this.started) {
            this.started = true;
            this.inetSocketAddress = inetSocketAddress;
            ServerSocketFactory serverSocketFactory = getServerSocketFactory();
            if (serverSocketFactory == null) {
                Intrinsics.throwNpe();
            }
            this.serverSocket = serverSocketFactory.createServerSocket();
            ServerSocket serverSocket = this.serverSocket;
            if (serverSocket == null) {
                Intrinsics.throwNpe();
            }
            if (inetSocketAddress.getPort() == 0) {
                z = false;
            }
            serverSocket.setReuseAddress(z);
            ServerSocket serverSocket2 = this.serverSocket;
            if (serverSocket2 == null) {
                Intrinsics.throwNpe();
            }
            serverSocket2.bind(inetSocketAddress, 50);
            ServerSocket serverSocket3 = this.serverSocket;
            if (serverSocket3 == null) {
                Intrinsics.throwNpe();
            }
            this.portField = serverSocket3.getLocalPort();
            final String str = "MockWebServer " + this.portField;
            this.taskRunner.newQueue().schedule(new Task(str, false) { // from class: okhttp3.mockwebserver.MockWebServer$start$$inlined$execute$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    ServerSocket serverSocket4;
                    try {
                        Logger logger2 = MockWebServer.logger;
                        logger2.info(this + " starting to accept connections");
                        this.acceptConnections();
                    } catch (Throwable th) {
                        Logger logger3 = MockWebServer.logger;
                        Level level = Level.WARNING;
                        logger3.log(level, this + " failed unexpectedly", th);
                    }
                    serverSocket4 = this.serverSocket;
                    if (serverSocket4 != null) {
                        Util.closeQuietly(serverSocket4);
                    }
                    Iterator it2 = this.openClientSockets.iterator();
                    while (it2.hasNext()) {
                        Object next = it2.next();
                        Intrinsics.checkExpressionValueIsNotNull(next, "openClientSocket.next()");
                        Util.closeQuietly((Socket) next);
                        it2.remove();
                    }
                    Iterator it3 = this.openConnections.iterator();
                    while (it3.hasNext()) {
                        Object next2 = it3.next();
                        Intrinsics.checkExpressionValueIsNotNull(next2, "httpConnection.next()");
                        Util.closeQuietly((Closeable) next2);
                        it3.remove();
                    }
                    this.getDispatcher().shutdown();
                    return -1L;
                }
            }, 0L);
        } else {
            throw new IllegalArgumentException("start() already called".toString());
        }
    }
}
