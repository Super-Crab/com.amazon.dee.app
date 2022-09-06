package com.amazon.alexa.tarazed.utils;

import com.amazon.alexa.tarazed.utils.FeatureChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeatureChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "cancellationThrowable", "", "invoke", "com/amazon/alexa/tarazed/utils/FeatureChecker$checkFeature$2$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ FeatureChecker.FeatureUpdateListener $listener;
    final /* synthetic */ FeatureChecker$checkFeature$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FeatureChecker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/amazon/alexa/tarazed/utils/FeatureChecker$checkFeature$2$1$1$1"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1.this.this$0.this$0.featureService.unsubscribe(FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1.this.$listener);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureChecker$checkFeature$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1(FeatureChecker.FeatureUpdateListener featureUpdateListener, FeatureChecker$checkFeature$2 featureChecker$checkFeature$2) {
        super(1);
        this.$listener = featureUpdateListener;
        this.this$0 = featureChecker$checkFeature$2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Continuation cancelled for "), this.this$0.$featureName, " check, unsubscribing");
        if (th != null) {
            this.this$0.this$0.logger.w("TarazedFtCheck", outline91, th);
        } else {
            this.this$0.this$0.logger.w("TarazedFtCheck", outline91);
        }
        BuildersKt__Builders_commonKt.launch$default(this.this$0.this$0.mainScope, null, null, new AnonymousClass1(null), 3, null);
    }
}
