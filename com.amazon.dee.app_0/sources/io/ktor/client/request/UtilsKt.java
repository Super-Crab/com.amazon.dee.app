package io.ktor.client.request;

import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u000f*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010\u0014\u001a\u001c\u0010\u0015\u001a\u00020\u000f*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010\u0014\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\t\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"value", "", "host", "Lio/ktor/client/request/HttpRequestBuilder;", "getHost", "(Lio/ktor/client/request/HttpRequestBuilder;)Ljava/lang/String;", "setHost", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;)V", "", "port", "getPort", "(Lio/ktor/client/request/HttpRequestBuilder;)I", "setPort", "(Lio/ktor/client/request/HttpRequestBuilder;I)V", "accept", "", "contentType", "Lio/ktor/http/ContentType;", "header", "key", "", "parameter", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt {
    public static final void accept(@NotNull HttpRequestBuilder receiver$0, @NotNull ContentType contentType) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        receiver$0.getHeaders().append(HttpHeaders.INSTANCE.getAccept(), contentType.toString());
    }

    @NotNull
    public static final String getHost(@NotNull HttpRequestBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getUrl().getHost();
    }

    public static final int getPort(@NotNull HttpRequestBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getUrl().getPort();
    }

    public static final void header(@NotNull HttpRequestBuilder receiver$0, @NotNull String key, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (obj != null) {
            receiver$0.getHeaders().append(key, obj.toString());
        }
    }

    public static final void parameter(@NotNull HttpRequestBuilder receiver$0, @NotNull String key, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (obj != null) {
            receiver$0.getUrl().getParameters().append(key, obj.toString());
        }
    }

    public static final void setHost(@NotNull HttpRequestBuilder receiver$0, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(value, "value");
        receiver$0.getUrl().setHost(value);
    }

    public static final void setPort(@NotNull HttpRequestBuilder receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.getUrl().setPort(i);
    }
}
