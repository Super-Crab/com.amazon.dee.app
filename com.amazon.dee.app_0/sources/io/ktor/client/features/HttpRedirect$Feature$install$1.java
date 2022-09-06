package io.ktor.client.features;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.features.HttpRedirect;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpRedirect.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/features/Sender;", "origin", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.HttpRedirect$Feature$install$1", f = "HttpRedirect.kt", i = {}, l = {23}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class HttpRedirect$Feature$install$1 extends SuspendLambda implements Function3<Sender, HttpClientCall, Continuation<? super HttpClientCall>, Object> {
    int label;
    private Sender p$;
    private HttpClientCall p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRedirect$Feature$install$1(Continuation continuation) {
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Sender receiver$0, @NotNull HttpClientCall origin, @NotNull Continuation<? super HttpClientCall> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpRedirect$Feature$install$1 httpRedirect$Feature$install$1 = new HttpRedirect$Feature$install$1(continuation);
        httpRedirect$Feature$install$1.p$ = receiver$0;
        httpRedirect$Feature$install$1.p$0 = origin;
        return httpRedirect$Feature$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpClientCall httpClientCall, Continuation<? super HttpClientCall> continuation) {
        return ((HttpRedirect$Feature$install$1) create(sender, httpClientCall, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            Sender sender = this.p$;
            HttpClientCall httpClientCall = this.p$0;
            HttpRedirect.Feature feature = HttpRedirect.Feature;
            this.label = 1;
            obj = feature.handleCall(sender, httpClientCall, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return obj;
    }
}
