package io.ktor.client.features;

import io.ktor.client.HttpClientConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserAgent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002\u001a\u000e\u0010\u0003\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002¨\u0006\u0004"}, d2 = {"BrowserUserAgent", "", "Lio/ktor/client/HttpClientConfig;", "CurlUserAgent", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UserAgentKt {
    public static final void BrowserUserAgent(@NotNull HttpClientConfig<?> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.install(UserAgent.Feature, UserAgentKt$BrowserUserAgent$1.INSTANCE);
    }

    public static final void CurlUserAgent(@NotNull HttpClientConfig<?> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.install(UserAgent.Feature, UserAgentKt$CurlUserAgent$1.INSTANCE);
    }
}
