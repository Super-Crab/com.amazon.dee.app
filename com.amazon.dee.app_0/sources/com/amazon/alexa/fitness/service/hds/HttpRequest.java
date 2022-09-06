package com.amazon.alexa.fitness.service.hds;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J)\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HttpRequest;", "", "payload", "", HttpClientModule.ElementsRequestKey.HEADERS, "", "(Ljava/lang/String;Ljava/util/Map;)V", "getHeaders", "()Ljava/util/Map;", "getPayload", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HttpRequest {
    @NotNull
    private final Map<String, String> headers;
    @NotNull
    private final String payload;

    public HttpRequest(@NotNull String payload, @NotNull Map<String, String> headers) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        this.payload = payload;
        this.headers = headers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpRequest copy$default(HttpRequest httpRequest, String str, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = httpRequest.payload;
        }
        if ((i & 2) != 0) {
            map = httpRequest.headers;
        }
        return httpRequest.copy(str, map);
    }

    @NotNull
    public final String component1() {
        return this.payload;
    }

    @NotNull
    public final Map<String, String> component2() {
        return this.headers;
    }

    @NotNull
    public final HttpRequest copy(@NotNull String payload, @NotNull Map<String, String> headers) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        return new HttpRequest(payload, headers);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof HttpRequest)) {
                return false;
            }
            HttpRequest httpRequest = (HttpRequest) obj;
            return Intrinsics.areEqual(this.payload, httpRequest.payload) && Intrinsics.areEqual(this.headers, httpRequest.headers);
        }
        return true;
    }

    @NotNull
    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    @NotNull
    public final String getPayload() {
        return this.payload;
    }

    public int hashCode() {
        String str = this.payload;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, String> map = this.headers;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpRequest(payload=");
        outline107.append(this.payload);
        outline107.append(", headers=");
        outline107.append(this.headers);
        outline107.append(")");
        return outline107.toString();
    }
}
