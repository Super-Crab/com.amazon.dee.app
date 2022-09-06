package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLProtocol.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0004"}, d2 = {"isSecure", "", "Lio/ktor/http/URLProtocol;", "isWebsocket", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLProtocolKt {
    public static final boolean isSecure(@NotNull URLProtocol receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return Intrinsics.areEqual(receiver$0.getName(), "https") || Intrinsics.areEqual(receiver$0.getName(), "wss");
    }

    public static final boolean isWebsocket(@NotNull URLProtocol receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return Intrinsics.areEqual(receiver$0.getName(), "ws") || Intrinsics.areEqual(receiver$0.getName(), "wss");
    }
}
