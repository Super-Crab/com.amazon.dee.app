package io.ktor.client.call;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLUtilsJvmKt;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: utilsJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/client/request/HttpRequestBuilder;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.call.UtilsJvmKt$call$3", f = "utilsJvm.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UtilsJvmKt$call$3 extends SuspendLambda implements Function2<HttpRequestBuilder, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ URL $url;
    int label;
    private HttpRequestBuilder p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UtilsJvmKt$call$3(URL url, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$url = url;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UtilsJvmKt$call$3 utilsJvmKt$call$3 = new UtilsJvmKt$call$3(this.$url, this.$block, completion);
        utilsJvmKt$call$3.p$ = (HttpRequestBuilder) obj;
        return utilsJvmKt$call$3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(HttpRequestBuilder httpRequestBuilder, Continuation<? super Unit> continuation) {
        return ((UtilsJvmKt$call$3) create(httpRequestBuilder, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            if (!(obj instanceof Result.Failure)) {
                HttpRequestBuilder httpRequestBuilder = this.p$;
                URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), this.$url);
                this.$block.mo12165invoke(httpRequestBuilder);
                return Unit.INSTANCE;
            }
            throw ((Result.Failure) obj).exception;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
