package io.ktor.client.features;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"defaultRequest", "", "Lio/ktor/client/HttpClientConfig;", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DefaultRequestKt {
    public static final void defaultRequest(@NotNull HttpClientConfig<?> receiver$0, @NotNull Function1<? super HttpRequestBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        receiver$0.install(DefaultRequest.Feature, new DefaultRequestKt$defaultRequest$1(block));
    }
}
