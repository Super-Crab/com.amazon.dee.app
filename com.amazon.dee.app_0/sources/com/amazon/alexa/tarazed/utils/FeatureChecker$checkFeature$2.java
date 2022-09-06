package com.amazon.alexa.tarazed.utils;

import com.amazon.alexa.tarazed.utils.FeatureChecker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeatureChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeature$2", f = "FeatureChecker.kt", i = {0}, l = {140}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes10.dex */
public final class FeatureChecker$checkFeature$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ boolean $defaultValue;
    final /* synthetic */ String $featureName;
    Object L$0;
    Object L$1;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ FeatureChecker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureChecker$checkFeature$2(FeatureChecker featureChecker, String str, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = featureChecker;
        this.$featureName = str;
        this.$defaultValue = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FeatureChecker$checkFeature$2 featureChecker$checkFeature$2 = new FeatureChecker$checkFeature$2(this.this$0, this.$featureName, this.$defaultValue, completion);
        featureChecker$checkFeature$2.p$ = (CoroutineScope) obj;
        return featureChecker$checkFeature$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((FeatureChecker$checkFeature$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Continuation intercepted;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.p$;
            this.L$1 = this;
            this.label = 1;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(this);
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
            TarazedSessionLogger tarazedSessionLogger = this.this$0.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Checking for feature ");
            outline107.append(this.$featureName);
            outline107.append("...");
            tarazedSessionLogger.i("TarazedFtCheck", outline107.toString());
            FeatureChecker.FeatureUpdateListener featureUpdateListener = new FeatureChecker.FeatureUpdateListener(this.this$0, cancellableContinuationImpl, this.$featureName, this.$defaultValue);
            cancellableContinuationImpl.invokeOnCancellation(new FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1(featureUpdateListener, this));
            this.this$0.featureService.observeFeature(this.$featureName, featureUpdateListener);
            obj = cancellableContinuationImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (obj == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            FeatureChecker$checkFeature$2 featureChecker$checkFeature$2 = (FeatureChecker$checkFeature$2) this.L$1;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
