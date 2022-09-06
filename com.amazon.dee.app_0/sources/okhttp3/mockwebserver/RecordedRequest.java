package okhttp3.mockwebserver;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.routing.api.RouteParameter;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.TlsVersion;
import okio.Buffer;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RecordedRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001BQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u0010\u00105\u001a\u0004\u0018\u00010\u00032\u0006\u00106\u001a\u00020\u0003J\b\u00107\u001a\u00020\u0003H\u0007J\b\u00108\u001a\u00020\u0003H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010$\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0013\u0010'\u001a\u0004\u0018\u00010(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010-\u001a\u0004\u0018\u00010.8F¢\u0006\u0006\u001a\u0004\b/\u00100R\u001a\u00101\u001a\u00020\u00038GX\u0087\u0004¢\u0006\f\u0012\u0004\b2\u00103\u001a\u0004\b4\u0010#¨\u00069"}, d2 = {"Lokhttp3/mockwebserver/RecordedRequest;", "", "requestLine", "", HttpClientModule.ElementsRequestKey.HEADERS, "Lokhttp3/Headers;", "chunkSizes", "", "", "bodySize", "", "body", "Lokio/Buffer;", AccessoryMetricsConstants.SEQUENCE_NUMBER, "socket", "Ljava/net/Socket;", "failure", "Ljava/io/IOException;", "(Ljava/lang/String;Lokhttp3/Headers;Ljava/util/List;JLokio/Buffer;ILjava/net/Socket;Ljava/io/IOException;)V", "getBody", "()Lokio/Buffer;", "getBodySize", "()J", "getChunkSizes", "()Ljava/util/List;", "getFailure", "()Ljava/io/IOException;", "handshake", "Lokhttp3/Handshake;", "getHandshake", "()Lokhttp3/Handshake;", "getHeaders", "()Lokhttp3/Headers;", MetricsConstants.NativeFetch.METHOD, "getMethod", "()Ljava/lang/String;", RouteParameter.PATH, "getPath", "getRequestLine", "requestUrl", "Lokhttp3/HttpUrl;", "getRequestUrl", "()Lokhttp3/HttpUrl;", "getSequenceNumber", "()I", "tlsVersion", "Lokhttp3/TlsVersion;", "getTlsVersion", "()Lokhttp3/TlsVersion;", "utf8Body", "utf8Body$annotations", "()V", "-deprecated_utf8Body", "getHeader", "name", "getUtf8Body", "toString", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecordedRequest {
    @NotNull
    private final Buffer body;
    private final long bodySize;
    @NotNull
    private final List<Integer> chunkSizes;
    @Nullable
    private final IOException failure;
    @Nullable
    private final Handshake handshake;
    @NotNull
    private final Headers headers;
    @Nullable
    private final String method;
    @Nullable
    private final String path;
    @NotNull
    private final String requestLine;
    @Nullable
    private final HttpUrl requestUrl;
    private final int sequenceNumber;

    @JvmOverloads
    public RecordedRequest(@NotNull String str, @NotNull Headers headers, @NotNull List<Integer> list, long j, @NotNull Buffer buffer, int i, @NotNull Socket socket) {
        this(str, headers, list, j, buffer, i, socket, null, 128, null);
    }

    @JvmOverloads
    public RecordedRequest(@NotNull String requestLine, @NotNull Headers headers, @NotNull List<Integer> chunkSizes, long j, @NotNull Buffer body, int i, @NotNull Socket socket, @Nullable IOException iOException) {
        int indexOf$default;
        int indexOf$default2;
        boolean startsWith$default;
        boolean contains$default;
        Intrinsics.checkParameterIsNotNull(requestLine, "requestLine");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(chunkSizes, "chunkSizes");
        Intrinsics.checkParameterIsNotNull(body, "body");
        Intrinsics.checkParameterIsNotNull(socket, "socket");
        this.requestLine = requestLine;
        this.headers = headers;
        this.chunkSizes = chunkSizes;
        this.bodySize = j;
        this.body = body;
        this.sequenceNumber = i;
        this.failure = iOException;
        boolean z = socket instanceof SSLSocket;
        if (z) {
            try {
                Handshake.Companion companion = Handshake.Companion;
                SSLSession session = ((SSLSocket) socket).getSession();
                Intrinsics.checkExpressionValueIsNotNull(session, "socket.session");
                this.handshake = companion.get(session);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.handshake = null;
        }
        if (this.requestLine.length() > 0) {
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) this.requestLine, (char) Chars.SPACE, 0, false, 6, (Object) null);
            int i2 = indexOf$default + 1;
            indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) this.requestLine, (char) Chars.SPACE, i2, false, 4, (Object) null);
            String str = this.requestLine;
            if (str != null) {
                String substring = str.substring(0, indexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                this.method = substring;
                String str2 = this.requestLine;
                if (str2 != null) {
                    String substring2 = str2.substring(i2, indexOf$default2);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    startsWith$default = StringsKt__StringsJVMKt.startsWith$default(substring2, "/", false, 2, null);
                    substring2 = !startsWith$default ? "/" : substring2;
                    this.path = substring2;
                    String str3 = z ? "https" : "http";
                    InetAddress inetAddress = socket.getLocalAddress();
                    Intrinsics.checkExpressionValueIsNotNull(inetAddress, "inetAddress");
                    String hostname = inetAddress.getHostName();
                    if (inetAddress instanceof Inet6Address) {
                        Intrinsics.checkExpressionValueIsNotNull(hostname, "hostname");
                        contains$default = StringsKt__StringsKt.contains$default((CharSequence) hostname, (char) JsonReaderKt.COLON, false, 2, (Object) null);
                        if (contains$default) {
                            hostname = JsonReaderKt.BEGIN_LIST + hostname + JsonReaderKt.END_LIST;
                        }
                    }
                    int localPort = socket.getLocalPort();
                    this.requestUrl = HttpUrl.Companion.parse(str3 + "://" + hostname + JsonReaderKt.COLON + localPort + substring2);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        this.requestUrl = null;
        this.method = null;
        this.path = null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use body.readUtf8()", replaceWith = @ReplaceWith(expression = "body.readUtf8()", imports = {}))
    public static /* synthetic */ void utf8Body$annotations() {
    }

    @JvmName(name = "-deprecated_utf8Body")
    @NotNull
    /* renamed from: -deprecated_utf8Body  reason: not valid java name */
    public final String m12581deprecated_utf8Body() {
        return this.body.readUtf8();
    }

    @NotNull
    public final Buffer getBody() {
        return this.body;
    }

    public final long getBodySize() {
        return this.bodySize;
    }

    @NotNull
    public final List<Integer> getChunkSizes() {
        return this.chunkSizes;
    }

    @Nullable
    public final IOException getFailure() {
        return this.failure;
    }

    @Nullable
    public final Handshake getHandshake() {
        return this.handshake;
    }

    @Nullable
    public final String getHeader(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return (String) CollectionsKt.firstOrNull((List<? extends Object>) this.headers.values(name));
    }

    @NotNull
    public final Headers getHeaders() {
        return this.headers;
    }

    @Nullable
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final String getPath() {
        return this.path;
    }

    @NotNull
    public final String getRequestLine() {
        return this.requestLine;
    }

    @Nullable
    public final HttpUrl getRequestUrl() {
        return this.requestUrl;
    }

    public final int getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Nullable
    public final TlsVersion getTlsVersion() {
        Handshake handshake = this.handshake;
        if (handshake != null) {
            return handshake.tlsVersion();
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use body.readUtf8()", replaceWith = @ReplaceWith(expression = "body.readUtf8()", imports = {}))
    @NotNull
    public final String getUtf8Body() {
        return this.body.readUtf8();
    }

    @NotNull
    public String toString() {
        return this.requestLine;
    }

    public /* synthetic */ RecordedRequest(String str, Headers headers, List list, long j, Buffer buffer, int i, Socket socket, IOException iOException, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, headers, list, j, buffer, i, socket, (i2 & 128) != 0 ? null : iOException);
    }
}
