package okhttp3.mockwebserver;

import com.amazon.alexa.routing.api.RouteParameter;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
/* compiled from: PushPromise.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\rJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u000eJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u000fJ\r\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\b\u0010R\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\f¨\u0006\u0011"}, d2 = {"Lokhttp3/mockwebserver/PushPromise;", "", MetricsConstants.NativeFetch.METHOD, "", RouteParameter.PATH, HttpClientModule.ElementsRequestKey.HEADERS, "Lokhttp3/Headers;", "response", "Lokhttp3/mockwebserver/MockResponse;", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/Headers;Lokhttp3/mockwebserver/MockResponse;)V", "()Lokhttp3/Headers;", "()Ljava/lang/String;", "()Lokhttp3/mockwebserver/MockResponse;", "-deprecated_headers", "-deprecated_method", "-deprecated_path", "-deprecated_response", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PushPromise {
    @NotNull
    private final Headers headers;
    @NotNull
    private final String method;
    @NotNull
    private final String path;
    @NotNull
    private final MockResponse response;

    public PushPromise(@NotNull String method, @NotNull String path, @NotNull Headers headers, @NotNull MockResponse response) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.response = response;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = HttpClientModule.ElementsRequestKey.HEADERS, imports = {}))
    @JvmName(name = "-deprecated_headers")
    @NotNull
    /* renamed from: -deprecated_headers  reason: not valid java name */
    public final Headers m12577deprecated_headers() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = MetricsConstants.NativeFetch.METHOD, imports = {}))
    @JvmName(name = "-deprecated_method")
    @NotNull
    /* renamed from: -deprecated_method  reason: not valid java name */
    public final String m12578deprecated_method() {
        return this.method;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = RouteParameter.PATH, imports = {}))
    @JvmName(name = "-deprecated_path")
    @NotNull
    /* renamed from: -deprecated_path  reason: not valid java name */
    public final String m12579deprecated_path() {
        return this.path;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "response", imports = {}))
    @JvmName(name = "-deprecated_response")
    @NotNull
    /* renamed from: -deprecated_response  reason: not valid java name */
    public final MockResponse m12580deprecated_response() {
        return this.response;
    }

    @JvmName(name = HttpClientModule.ElementsRequestKey.HEADERS)
    @NotNull
    public final Headers headers() {
        return this.headers;
    }

    @JvmName(name = MetricsConstants.NativeFetch.METHOD)
    @NotNull
    public final String method() {
        return this.method;
    }

    @JvmName(name = RouteParameter.PATH)
    @NotNull
    public final String path() {
        return this.path;
    }

    @JvmName(name = "response")
    @NotNull
    public final MockResponse response() {
        return this.response;
    }
}
