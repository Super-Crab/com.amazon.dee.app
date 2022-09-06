package com.amazon.alexa.tarazed.utils;

import com.amazon.alexa.tarazed.utils.FeatureChecker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FeatureChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.alexa.tarazed.utils.FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1", f = "FeatureChecker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes10.dex */
final class FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ FeatureChecker.FeatureUpdateListener this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1(FeatureChecker.FeatureUpdateListener featureUpdateListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = featureUpdateListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1 featureChecker$FeatureUpdateListener$onFeatureUpdate$1 = new FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1(this.this$0, completion);
        featureChecker$FeatureUpdateListener$onFeatureUpdate$1.p$ = (CoroutineScope) obj;
        return featureChecker$FeatureUpdateListener$onFeatureUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.this$0.featureService.unsubscribe(this.this$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
