package io.ktor.http;

import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: RequestConnectionPoint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005¨\u0006\u0016"}, d2 = {"Lio/ktor/http/RequestConnectionPoint;", "", "host", "", "getHost", "()Ljava/lang/String;", MetricsConstants.NativeFetch.METHOD, "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "", "getPort", "()I", "remoteHost", "getRemoteHost", "scheme", "getScheme", "uri", "getUri", "version", "getVersion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface RequestConnectionPoint {
    @NotNull
    String getHost();

    @NotNull
    HttpMethod getMethod();

    int getPort();

    @NotNull
    String getRemoteHost();

    @NotNull
    String getScheme();

    @NotNull
    String getUri();

    @NotNull
    String getVersion();
}
