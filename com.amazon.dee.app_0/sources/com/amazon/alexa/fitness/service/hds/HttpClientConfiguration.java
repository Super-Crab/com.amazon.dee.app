package com.amazon.alexa.fitness.service.hds;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HttpClientConfiguration;", "", "endpoint", "", "connectTimeoutInMs", "", "readTimeoutInMs", "(Ljava/lang/String;II)V", "getConnectTimeoutInMs", "()I", "getEndpoint", "()Ljava/lang/String;", "getReadTimeoutInMs", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HttpClientConfiguration {
    private final int connectTimeoutInMs;
    @NotNull
    private final String endpoint;
    private final int readTimeoutInMs;

    public HttpClientConfiguration(@NotNull String endpoint, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        this.endpoint = endpoint;
        this.connectTimeoutInMs = i;
        this.readTimeoutInMs = i2;
    }

    public static /* synthetic */ HttpClientConfiguration copy$default(HttpClientConfiguration httpClientConfiguration, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = httpClientConfiguration.endpoint;
        }
        if ((i3 & 2) != 0) {
            i = httpClientConfiguration.connectTimeoutInMs;
        }
        if ((i3 & 4) != 0) {
            i2 = httpClientConfiguration.readTimeoutInMs;
        }
        return httpClientConfiguration.copy(str, i, i2);
    }

    @NotNull
    public final String component1() {
        return this.endpoint;
    }

    public final int component2() {
        return this.connectTimeoutInMs;
    }

    public final int component3() {
        return this.readTimeoutInMs;
    }

    @NotNull
    public final HttpClientConfiguration copy(@NotNull String endpoint, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        return new HttpClientConfiguration(endpoint, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof HttpClientConfiguration)) {
                return false;
            }
            HttpClientConfiguration httpClientConfiguration = (HttpClientConfiguration) obj;
            return Intrinsics.areEqual(this.endpoint, httpClientConfiguration.endpoint) && this.connectTimeoutInMs == httpClientConfiguration.connectTimeoutInMs && this.readTimeoutInMs == httpClientConfiguration.readTimeoutInMs;
        }
        return true;
    }

    public final int getConnectTimeoutInMs() {
        return this.connectTimeoutInMs;
    }

    @NotNull
    public final String getEndpoint() {
        return this.endpoint;
    }

    public final int getReadTimeoutInMs() {
        return this.readTimeoutInMs;
    }

    public int hashCode() {
        String str = this.endpoint;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.connectTimeoutInMs) * 31) + this.readTimeoutInMs;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpClientConfiguration(endpoint=");
        outline107.append(this.endpoint);
        outline107.append(", connectTimeoutInMs=");
        outline107.append(this.connectTimeoutInMs);
        outline107.append(", readTimeoutInMs=");
        return GeneratedOutlineSupport1.outline86(outline107, this.readTimeoutInMs, ")");
    }
}
