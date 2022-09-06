package io.ktor.client.call;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.response.HttpResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/ktor/client/call/HttpEngineCall;", "", "request", "Lio/ktor/client/request/HttpRequest;", "response", "Lio/ktor/client/response/HttpResponse;", "(Lio/ktor/client/request/HttpRequest;Lio/ktor/client/response/HttpResponse;)V", "getRequest", "()Lio/ktor/client/request/HttpRequest;", "getResponse", "()Lio/ktor/client/response/HttpResponse;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpEngineCall {
    @NotNull
    private final HttpRequest request;
    @NotNull
    private final HttpResponse response;

    public HttpEngineCall(@NotNull HttpRequest request, @NotNull HttpResponse response) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.request = request;
        this.response = response;
    }

    @NotNull
    public static /* synthetic */ HttpEngineCall copy$default(HttpEngineCall httpEngineCall, HttpRequest httpRequest, HttpResponse httpResponse, int i, Object obj) {
        if ((i & 1) != 0) {
            httpRequest = httpEngineCall.request;
        }
        if ((i & 2) != 0) {
            httpResponse = httpEngineCall.response;
        }
        return httpEngineCall.copy(httpRequest, httpResponse);
    }

    @NotNull
    public final HttpRequest component1() {
        return this.request;
    }

    @NotNull
    public final HttpResponse component2() {
        return this.response;
    }

    @NotNull
    public final HttpEngineCall copy(@NotNull HttpRequest request, @NotNull HttpResponse response) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(response, "response");
        return new HttpEngineCall(request, response);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof HttpEngineCall)) {
                return false;
            }
            HttpEngineCall httpEngineCall = (HttpEngineCall) obj;
            return Intrinsics.areEqual(this.request, httpEngineCall.request) && Intrinsics.areEqual(this.response, httpEngineCall.response);
        }
        return true;
    }

    @NotNull
    public final HttpRequest getRequest() {
        return this.request;
    }

    @NotNull
    public final HttpResponse getResponse() {
        return this.response;
    }

    public int hashCode() {
        HttpRequest httpRequest = this.request;
        int i = 0;
        int hashCode = (httpRequest != null ? httpRequest.hashCode() : 0) * 31;
        HttpResponse httpResponse = this.response;
        if (httpResponse != null) {
            i = httpResponse.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpEngineCall(request=");
        outline107.append(this.request);
        outline107.append(", response=");
        outline107.append(this.response);
        outline107.append(")");
        return outline107.toString();
    }
}
