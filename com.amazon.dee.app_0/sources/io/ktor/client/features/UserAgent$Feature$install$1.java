package io.ktor.client.features;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.UtilsKt;
import io.ktor.http.HttpHeaders;
import io.ktor.util.pipeline.PipelineContext;
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
/* compiled from: UserAgent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.UserAgent$Feature$install$1", f = "UserAgent.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UserAgent$Feature$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ UserAgent $feature;
    int label;
    private PipelineContext p$;
    private Object p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAgent$Feature$install$1(UserAgent userAgent, Continuation continuation) {
        super(3, continuation);
        this.$feature = userAgent;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<Object, HttpRequestBuilder> receiver$0, @NotNull Object it2, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(it2, "it");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        UserAgent$Feature$install$1 userAgent$Feature$install$1 = new UserAgent$Feature$install$1(this.$feature, continuation);
        userAgent$Feature$install$1.p$ = receiver$0;
        userAgent$Feature$install$1.p$0 = it2;
        return userAgent$Feature$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        return ((UserAgent$Feature$install$1) create(pipelineContext, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            if (!(obj instanceof Result.Failure)) {
                UtilsKt.header((HttpRequestBuilder) this.p$.getContext(), HttpHeaders.INSTANCE.getUserAgent(), this.$feature.getAgent());
                return Unit.INSTANCE;
            }
            throw ((Result.Failure) obj).exception;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
