package io.ktor.client.features.observer;

import io.ktor.client.call.HttpClientCall;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: DelegatedCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"wrapWithContent", "Lio/ktor/client/call/HttpClientCall;", "content", "Lkotlinx/coroutines/io/ByteReadChannel;", "shouldCloseOrigin", "", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DelegatedCallKt {
    @NotNull
    public static final HttpClientCall wrapWithContent(@NotNull HttpClientCall receiver$0, @NotNull ByteReadChannel content, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(content, "content");
        HttpClientCall httpClientCall = new HttpClientCall(receiver$0.getClient());
        httpClientCall.setRequest$ktor_client_core(new DelegatedRequest(httpClientCall, receiver$0.getRequest()));
        httpClientCall.setResponse$ktor_client_core(new DelegatedResponse(content, httpClientCall, z, receiver$0.getResponse()));
        return httpClientCall;
    }
}
