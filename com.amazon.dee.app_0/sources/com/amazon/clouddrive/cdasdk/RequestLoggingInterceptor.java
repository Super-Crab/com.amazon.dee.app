package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.RequestLoggingConfig;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
/* loaded from: classes11.dex */
public class RequestLoggingInterceptor implements Interceptor {
    private static final String TAG = "RequestLoggingInterceptor";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    @NonNull
    private final RequestLoggingConfig configuration;
    @NonNull
    private final Logger logger;
    @NonNull
    private final SystemUtil systemUtil;

    /* renamed from: com.amazon.clouddrive.cdasdk.RequestLoggingInterceptor$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$clouddrive$cdasdk$RequestLoggingConfig$Verbosity = new int[RequestLoggingConfig.Verbosity.values().length];

        static {
            try {
                $SwitchMap$com$amazon$clouddrive$cdasdk$RequestLoggingConfig$Verbosity[RequestLoggingConfig.Verbosity.Debug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$clouddrive$cdasdk$RequestLoggingConfig$Verbosity[RequestLoggingConfig.Verbosity.Verbose.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$clouddrive$cdasdk$RequestLoggingConfig$Verbosity[RequestLoggingConfig.Verbosity.Info.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestLoggingInterceptor(@NonNull RequestLoggingConfig requestLoggingConfig, @NonNull Logger logger, @NonNull SystemUtil systemUtil) {
        this.configuration = requestLoggingConfig;
        this.logger = logger;
        this.systemUtil = systemUtil;
    }

    private static boolean bodyHasUnknownEncoding(@NonNull Headers headers) {
        String str = headers.get("Content-Encoding");
        return str != null && !str.equalsIgnoreCase(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY) && !str.equalsIgnoreCase("gzip");
    }

    private static String getBasicLogDetails(@NonNull Request request, long j) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(request.method());
        outline107.append("] [");
        outline107.append(j);
        outline107.append("ms] ");
        outline107.append(request.url());
        return outline107.toString();
    }

    private static String getHeadersDetails(@NonNull Headers headers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'");
            outline107.append(headers.name(i));
            outline107.append(":");
            outline107.append(headers.value(i));
            outline107.append("' ");
            sb.append(outline107.toString());
        }
        return sb.toString();
    }

    private static String getRequestBodyDetails(@NonNull Request request) {
        if (request.body() != null) {
            String outline72 = GeneratedOutlineSupport1.outline72("", "\n[Request Body] ");
            if (bodyHasUnknownEncoding(request.headers())) {
                return GeneratedOutlineSupport1.outline72(outline72, "(unknown encoding body omitted)");
            }
            try {
                Buffer buffer = new Buffer();
                if (request.body() instanceof ProgressReportingRequestBody) {
                    ((ProgressReportingRequestBody) request.body()).writeTo(buffer, false);
                } else {
                    request.body().writeTo(buffer);
                }
                Charset charset = UTF8;
                MediaType contentType = request.body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                if (charset == null) {
                    charset = UTF8;
                }
                String str = outline72 + " [" + request.body().contentLength() + " bytes] ";
                if (isPlaintext(buffer)) {
                    return str + buffer.readString(charset);
                }
                return str + "(binary body omitted)";
            } catch (IOException unused) {
                return GeneratedOutlineSupport1.outline72(outline72, "(failed to read request body)");
            }
        }
        return GeneratedOutlineSupport1.outline72("", "\n[No Request Body]");
    }

    private static String getRequestHeaderDetails(@NonNull Request request) {
        StringBuilder sb = new StringBuilder();
        if (request.headers().size() > 0) {
            sb.append("\n[Request Headers] ");
            sb.append(getHeadersDetails(request.headers()));
        } else {
            sb.append("\n[No Request Headers]");
        }
        return sb.toString();
    }

    private static String getResponseBodyDetails(@NonNull Response response) {
        String str;
        if (response.body() == null || !HttpHeaders.hasBody(response)) {
            return "\n[No Response Body]";
        }
        try {
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Long l = null;
            if ("gzip".equalsIgnoreCase(response.headers().get("Content-Encoding"))) {
                l = Long.valueOf(buffer.size());
                GzipSource gzipSource = new GzipSource(buffer.clone());
                buffer = new Buffer();
                buffer.writeAll(gzipSource);
                gzipSource.close();
            }
            if (l != null) {
                str = "\n[Response Body] [" + l + " gzipped-bytes] ";
            } else {
                str = "\n[Response Body] [" + buffer.size() + " bytes] ";
            }
            Charset charset = UTF8;
            MediaType contentType = response.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (charset == null) {
                charset = UTF8;
            }
            if (!isPlaintext(buffer)) {
                return str + "(binary body omitted)";
            } else if (response.body().contentLength() == 0) {
                return str;
            } else {
                return str + buffer.clone().readString(charset);
            }
        } catch (IOException unused) {
            return GeneratedOutlineSupport1.outline72("\n[Response Body] ", "(failed to read response body)");
        }
    }

    private static String getResponseCodeDetails(@NonNull Response response) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" [");
        outline107.append(response.code());
        outline107.append("]");
        return outline107.toString();
    }

    private static String getResponseHeaderDetails(@NonNull Response response) {
        StringBuilder sb = new StringBuilder();
        if (response.headers().size() > 0) {
            sb.append("\n[Response Headers] ");
            sb.append(getHeadersDetails(response.headers()));
        } else {
            sb.append("\n[No Response Headers]");
        }
        return sb.toString();
    }

    private static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private void logAtAppropriateLevel(@Nullable String str) {
        if (str == null) {
            return;
        }
        int ordinal = this.configuration.getVerbosity().ordinal();
        if (ordinal == 0) {
            this.logger.d(TAG, str);
        } else if (ordinal == 1) {
            this.logger.v(TAG, str);
        } else if (ordinal != 2) {
        } else {
            this.logger.i(TAG, str);
        }
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        long elapsedRealtime = this.systemUtil.elapsedRealtime();
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        long elapsedRealtime2 = this.systemUtil.elapsedRealtime() - elapsedRealtime;
        if (this.configuration.getDetailLevel() == RequestLoggingConfig.DetailLevel.None) {
            return proceed;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(getBasicLogDetails(request, elapsedRealtime2));
        outline107.append(getResponseCodeDetails(proceed));
        String sb = outline107.toString();
        if (this.configuration.getDetailLevel() == RequestLoggingConfig.DetailLevel.Full) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(sb);
            outline1072.append(getRequestHeaderDetails(request));
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(outline1072.toString());
            outline1073.append(getRequestBodyDetails(request));
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(outline1073.toString());
            outline1074.append(getResponseHeaderDetails(proceed));
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107(outline1074.toString());
            outline1075.append(getResponseBodyDetails(proceed));
            sb = outline1075.toString();
        }
        logAtAppropriateLevel(sb);
        return proceed;
    }
}
