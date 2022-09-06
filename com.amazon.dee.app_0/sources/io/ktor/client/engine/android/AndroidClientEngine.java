package io.ktor.client.engine.android;

import androidx.core.app.FrameMetricsAggregator;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.HttpEngineCall;
import io.ktor.client.engine.HttpClientJvmEngine;
import io.ktor.client.engine.UtilsKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AndroidClientEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010\u0007\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/client/engine/android/AndroidClientEngine;", "Lio/ktor/client/engine/HttpClientJvmEngine;", "config", "Lio/ktor/client/engine/android/AndroidEngineConfig;", "(Lio/ktor/client/engine/android/AndroidEngineConfig;)V", "getConfig", "()Lio/ktor/client/engine/android/AndroidEngineConfig;", "execute", "Lio/ktor/client/call/HttpEngineCall;", "call", "Lio/ktor/client/call/HttpClientCall;", "data", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/request/HttpRequestData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProxyAwareConnection", "Ljava/net/HttpURLConnection;", "url", "", "Lio/ktor/client/engine/android/AndroidHttpResponse;", "Lio/ktor/client/engine/android/AndroidHttpRequest;", "callContext", "Lkotlin/coroutines/CoroutineContext;", "ktor-client-android"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AndroidClientEngine extends HttpClientJvmEngine {
    @NotNull
    private final AndroidEngineConfig config;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidClientEngine(@NotNull AndroidEngineConfig config) {
        super("ktor-android");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.config = config;
    }

    private final HttpURLConnection getProxyAwareConnection(String str) {
        URLConnection openConnection;
        URL url = new URL(str);
        Proxy proxy = mo10273getConfig().getProxy();
        if (proxy == null || (openConnection = url.openConnection(proxy)) == null) {
            openConnection = url.openConnection();
        }
        if (openConnection != null) {
            return (HttpURLConnection) openConnection;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    @Nullable
    public Object execute(@NotNull HttpClientCall httpClientCall, @NotNull HttpRequestData httpRequestData, @NotNull Continuation<? super HttpEngineCall> continuation) {
        return BuildersKt.withContext(getCoroutineContext(), new AndroidClientEngine$execute$2(this, httpRequestData, httpClientCall, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AndroidHttpResponse execute(@NotNull AndroidHttpRequest androidHttpRequest, CoroutineContext coroutineContext) {
        GMTDate GMTDate$default = DateJvmKt.GMTDate$default(null, 1, null);
        String buildString = URLUtilsKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), androidHttpRequest.getUrl()).buildString();
        OutgoingContent content = androidHttpRequest.getContent();
        String str = androidHttpRequest.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        Long valueOf = str != null ? Long.valueOf(Long.parseLong(str)) : content.getContentLength();
        HttpURLConnection proxyAwareConnection = getProxyAwareConnection(buildString);
        proxyAwareConnection.setConnectTimeout(mo10273getConfig().getConnectTimeout());
        proxyAwareConnection.setReadTimeout(mo10273getConfig().getSocketTimeout());
        if (proxyAwareConnection instanceof HttpsURLConnection) {
            mo10273getConfig().getSslManager().mo12165invoke(proxyAwareConnection);
        }
        proxyAwareConnection.setRequestMethod(androidHttpRequest.getMethod().getValue());
        proxyAwareConnection.setUseCaches(false);
        proxyAwareConnection.setInstanceFollowRedirects(false);
        UtilsKt.mergeHeaders(androidHttpRequest.getHeaders(), content, new AndroidClientEngine$execute$connection$1$1(proxyAwareConnection));
        if (!(content instanceof OutgoingContent.NoContent)) {
            if (valueOf != null) {
                proxyAwareConnection.addRequestProperty(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(valueOf.longValue()));
            } else {
                proxyAwareConnection.addRequestProperty(HttpHeaders.INSTANCE.getTransferEncoding(), "chunked");
            }
            if (valueOf != null) {
                proxyAwareConnection.setFixedLengthStreamingMode((int) valueOf.longValue());
            } else {
                proxyAwareConnection.setChunkedStreamingMode(0);
            }
            proxyAwareConnection.setDoOutput(true);
            OutputStream outputStream = proxyAwareConnection.getOutputStream();
            Intrinsics.checkExpressionValueIsNotNull(outputStream, "outputStream");
            AndroidClientEngineKt.writeTo(content, outputStream, coroutineContext);
        }
        proxyAwareConnection.connect();
        ByteReadChannel content2 = AndroidClientEngineKt.content(proxyAwareConnection, coroutineContext);
        Map<String, List<String>> headerFields = proxyAwareConnection.getHeaderFields();
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        if (headerFields != null) {
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                if (key != null) {
                    Intrinsics.checkExpressionValueIsNotNull(values, "values");
                    headersBuilder.appendAll(key, values);
                }
            }
        }
        Headers mo10292build = headersBuilder.mo10292build();
        HttpClientCall call = androidHttpRequest.getCall();
        GMTDate GMTDate$default2 = DateJvmKt.GMTDate$default(null, 1, null);
        int responseCode = proxyAwareConnection.getResponseCode();
        String responseMessage = proxyAwareConnection.getResponseMessage();
        Intrinsics.checkExpressionValueIsNotNull(responseMessage, "connection.responseMessage");
        return new AndroidHttpResponse(call, content2, mo10292build, GMTDate$default, GMTDate$default2, new HttpStatusCode(responseCode, responseMessage), HttpProtocolVersion.Companion.getHTTP_1_1(), coroutineContext, proxyAwareConnection);
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    @NotNull
    /* renamed from: getConfig  reason: collision with other method in class */
    public AndroidEngineConfig mo10273getConfig() {
        return this.config;
    }
}
