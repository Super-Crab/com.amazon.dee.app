package okhttp3.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpLoggingInterceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003??\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010\u000b\u001a\u00020\tH\u0007??\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e??\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@GX\u0086\u000e??\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\n\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004??\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001??\u0006 "}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "headersToRedact", "", "", "<set-?>", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", ModelTransformer.KEY_BATTERY_LEVEL, "getLevel", "()Lokhttp3/logging/HttpLoggingInterceptor$Level;", "(Lokhttp3/logging/HttpLoggingInterceptor$Level;)V", "bodyHasUnknownEncoding", "", HttpClientModule.ElementsRequestKey.HEADERS, "Lokhttp3/Headers;", "-deprecated_level", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logHeader", "", ContextChain.TAG_INFRA, "", "redactHeader", "name", "setLevel", org.apache.logging.log4j.Level.CATEGORY, "Logger", "okhttp-logging-interceptor"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private volatile Set<String> headersToRedact;
    @NotNull
    private volatile Level level;
    private final Logger logger;

    /* compiled from: HttpLoggingInterceptor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002??\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006??\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "okhttp-logging-interceptor"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* compiled from: HttpLoggingInterceptor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001??\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "", "log", "", "message", "", "Companion", "okhttp-logging-interceptor"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface Logger {
        public static final Companion Companion = new Companion(null);
        @JvmField
        @NotNull
        public static final Logger DEFAULT = new Logger() { // from class: okhttp3.logging.HttpLoggingInterceptor$Logger$Companion$DEFAULT$1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(@NotNull String message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                Platform.log$default(Platform.Companion.get(), message, 0, null, 6, null);
            }
        };

        /* compiled from: HttpLoggingInterceptor.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002??\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004??\u0001\u0000??\u0006\u0002\n\u0000??\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001??\u0006\u0005"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger$Companion;", "", "()V", ChipIconTitleViewHolder.STATE_DEFAULT, "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "okhttp-logging-interceptor"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        void log(@NotNull String str);
    }

    @JvmOverloads
    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    @JvmOverloads
    public HttpLoggingInterceptor(@NotNull Logger logger) {
        Set<String> emptySet;
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
        emptySet = SetsKt__SetsKt.emptySet();
        this.headersToRedact = emptySet;
        this.level = Level.NONE;
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        boolean equals;
        boolean equals2;
        String str = headers.get("Content-Encoding");
        if (str != null) {
            equals = StringsKt__StringsJVMKt.equals(str, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, true);
            if (equals) {
                return false;
            }
            equals2 = StringsKt__StringsJVMKt.equals(str, "gzip", true);
            return !equals2;
        }
        return false;
    }

    private final void logHeader(Headers headers, int i) {
        String value = this.headersToRedact.contains(headers.name(i)) ? "??????" : headers.value(i);
        Logger logger = this.logger;
        logger.log(headers.name(i) + RealTimeTextConstants.COLON_SPACE + value);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = ModelTransformer.KEY_BATTERY_LEVEL, imports = {}))
    @JvmName(name = "-deprecated_level")
    @NotNull
    /* renamed from: -deprecated_level  reason: not valid java name */
    public final Level m12561deprecated_level() {
        return this.level;
    }

    @NotNull
    public final Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String str;
        String str2;
        String sb;
        char c;
        boolean equals;
        Long l;
        Charset UTF_8;
        Charset UTF_82;
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody body = request.body();
        Connection connection = chain.connection();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("--> ");
        outline107.append(request.method());
        outline107.append(Chars.SPACE);
        outline107.append(request.url());
        if (connection != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(" ");
            outline1072.append(connection.protocol());
            str = outline1072.toString();
        } else {
            str = "";
        }
        outline107.append(str);
        String sb2 = outline107.toString();
        if (!z2 && body != 0) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(sb2, " (");
            outline113.append(body.contentLength());
            outline113.append("-byte body)");
            sb2 = outline113.toString();
        }
        this.logger.log(sb2);
        if (z2) {
            Headers headers = request.headers();
            if (body != null) {
                MediaType contentType = body.contentType();
                if (contentType != null && headers.get("Content-Type") == null) {
                    this.logger.log("Content-Type: " + contentType);
                }
                if (body.contentLength() != -1 && headers.get("Content-Length") == null) {
                    Logger logger = this.logger;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Content-Length: ");
                    outline1073.append(body.contentLength());
                    logger.log(outline1073.toString());
                }
            }
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                logHeader(headers, i);
            }
            if (z && body != null) {
                if (bodyHasUnknownEncoding(request.headers())) {
                    Logger logger2 = this.logger;
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("--> END ");
                    outline1074.append(request.method());
                    outline1074.append(" (encoded body omitted)");
                    logger2.log(outline1074.toString());
                } else if (body.isDuplex()) {
                    Logger logger3 = this.logger;
                    StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("--> END ");
                    outline1075.append(request.method());
                    outline1075.append(" (duplex request body omitted)");
                    logger3.log(outline1075.toString());
                } else if (body.isOneShot()) {
                    Logger logger4 = this.logger;
                    StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("--> END ");
                    outline1076.append(request.method());
                    outline1076.append(" (one-shot body omitted)");
                    logger4.log(outline1076.toString());
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    MediaType contentType2 = body.contentType();
                    if (contentType2 == null || (UTF_82 = contentType2.charset(StandardCharsets.UTF_8)) == null) {
                        UTF_82 = StandardCharsets.UTF_8;
                        Intrinsics.checkExpressionValueIsNotNull(UTF_82, "UTF_8");
                    }
                    this.logger.log("");
                    if (Utf8Kt.isProbablyUtf8(buffer)) {
                        this.logger.log(buffer.readString(UTF_82));
                        Logger logger5 = this.logger;
                        StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("--> END ");
                        outline1077.append(request.method());
                        outline1077.append(" (");
                        outline1077.append(body.contentLength());
                        outline1077.append("-byte body)");
                        logger5.log(outline1077.toString());
                    } else {
                        Logger logger6 = this.logger;
                        StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("--> END ");
                        outline1078.append(request.method());
                        outline1078.append(" (binary ");
                        outline1078.append(body.contentLength());
                        outline1078.append("-byte body omitted)");
                        logger6.log(outline1078.toString());
                    }
                }
            } else {
                Logger logger7 = this.logger;
                StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("--> END ");
                outline1079.append(request.method());
                logger7.log(outline1079.toString());
            }
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            if (body2 == null) {
                Intrinsics.throwNpe();
            }
            long contentLength = body2.contentLength();
            String str3 = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger logger8 = this.logger;
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("<-- ");
            outline10710.append(proceed.code());
            if (proceed.message().length() == 0) {
                c = Chars.SPACE;
                str2 = "-byte body omitted)";
                sb = "";
            } else {
                String message = proceed.message();
                StringBuilder sb3 = new StringBuilder();
                str2 = "-byte body omitted)";
                sb3.append(String.valueOf((char) Chars.SPACE));
                sb3.append(message);
                sb = sb3.toString();
                c = ' ';
            }
            outline10710.append(sb);
            outline10710.append(c);
            outline10710.append(proceed.request().url());
            outline10710.append(" (");
            outline10710.append(millis);
            outline10710.append("ms");
            outline10710.append(!z2 ? GeneratedOutlineSupport1.outline75(", ", str3, " body") : "");
            outline10710.append(')');
            logger8.log(outline10710.toString());
            if (z2) {
                Headers headers2 = proceed.headers();
                int size2 = headers2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    logHeader(headers2, i2);
                }
                if (z && HttpHeaders.promisesBody(proceed)) {
                    if (bodyHasUnknownEncoding(proceed.headers())) {
                        this.logger.log("<-- END HTTP (encoded body omitted)");
                    } else {
                        BufferedSource source = body2.source();
                        source.request(Long.MAX_VALUE);
                        Buffer buffer2 = source.getBuffer();
                        equals = StringsKt__StringsJVMKt.equals("gzip", headers2.get("Content-Encoding"), true);
                        if (equals) {
                            l = Long.valueOf(buffer2.size());
                            GzipSource gzipSource = new GzipSource(buffer2.clone());
                            try {
                                buffer2 = new Buffer();
                                buffer2.writeAll(gzipSource);
                                CloseableKt.closeFinally(gzipSource, null);
                            } finally {
                            }
                        } else {
                            l = null;
                        }
                        MediaType contentType3 = body2.contentType();
                        if (contentType3 == null || (UTF_8 = contentType3.charset(StandardCharsets.UTF_8)) == null) {
                            UTF_8 = StandardCharsets.UTF_8;
                            Intrinsics.checkExpressionValueIsNotNull(UTF_8, "UTF_8");
                        }
                        if (!Utf8Kt.isProbablyUtf8(buffer2)) {
                            this.logger.log("");
                            Logger logger9 = this.logger;
                            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("<-- END HTTP (binary ");
                            outline10711.append(buffer2.size());
                            outline10711.append(str2);
                            logger9.log(outline10711.toString());
                            return proceed;
                        }
                        if (contentLength != 0) {
                            this.logger.log("");
                            this.logger.log(buffer2.clone().readString(UTF_8));
                        }
                        if (l != null) {
                            Logger logger10 = this.logger;
                            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("<-- END HTTP (");
                            outline10712.append(buffer2.size());
                            outline10712.append("-byte, ");
                            outline10712.append(l);
                            outline10712.append("-gzipped-byte body)");
                            logger10.log(outline10712.toString());
                        } else {
                            Logger logger11 = this.logger;
                            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("<-- END HTTP (");
                            outline10713.append(buffer2.size());
                            outline10713.append("-byte body)");
                            logger11.log(outline10713.toString());
                        }
                    }
                } else {
                    this.logger.log("<-- END HTTP");
                }
            }
            return proceed;
        } catch (Exception e) {
            this.logger.log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    @JvmName(name = ModelTransformer.KEY_BATTERY_LEVEL)
    public final void level(@NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(level, "<set-?>");
        this.level = level;
    }

    public final void redactHeader(@NotNull String name) {
        Comparator<String> case_insensitive_order;
        Intrinsics.checkParameterIsNotNull(name, "name");
        case_insensitive_order = StringsKt__StringsJVMKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE);
        TreeSet treeSet = new TreeSet(case_insensitive_order);
        CollectionsKt__MutableCollectionsKt.addAll(treeSet, this.headersToRedact);
        treeSet.add(name);
        this.headersToRedact = treeSet;
    }

    @NotNull
    public final HttpLoggingInterceptor setLevel(@NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        this.level = level;
        return this;
    }

    public /* synthetic */ HttpLoggingInterceptor(Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Logger.DEFAULT : logger);
    }
}
