package io.ktor.client.features.observer;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.response.HttpResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ResponseObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004ø\u0001\u0000¢\u0006\u0002\u0010\b*B\u0010\t\"\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00042\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"ResponseObserver", "", "Lio/ktor/client/HttpClientConfig;", "block", "Lkotlin/Function2;", "Lio/ktor/client/response/HttpResponse;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/client/HttpClientConfig;Lkotlin/jvm/functions/Function2;)V", "ResponseHandler", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ResponseObserverKt {
    public static final void ResponseObserver(@NotNull HttpClientConfig<?> receiver$0, @NotNull Function2<? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        receiver$0.install(ResponseObserver.Feature, new ResponseObserverKt$ResponseObserver$1(block));
    }
}
