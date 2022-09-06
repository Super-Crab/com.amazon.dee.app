package com.amazon.tarazed.activity;

import android.app.Activity;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ActivityTracker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.activity.ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1", f = "ActivityTracker.kt", i = {0, 0}, l = {205}, m = "invokeSuspend", n = {"$this$launch", "subscriber"}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
final class ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ActivityTracker$notifySubscribers$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1(ActivityTracker$notifySubscribers$2 activityTracker$notifySubscribers$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = activityTracker$notifySubscribers$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1 activityTracker$notifySubscribers$2$onViewAttachedToWindow$1 = new ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1(this.this$0, completion);
        activityTracker$notifySubscribers$2$onViewAttachedToWindow$1.p$ = (CoroutineScope) obj;
        return activityTracker$notifySubscribers$2$onViewAttachedToWindow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Iterator<Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object>> it2;
        CoroutineScope coroutineScope;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.p$;
            it2 = this.this$0.this$0.getSubscribers().iterator();
            coroutineScope = coroutineScope2;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            it2 = (Iterator) this.L$2;
            Function3 function3 = (Function3) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (it2.hasNext()) {
            Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> next = it2.next();
            ActivityTracker$notifySubscribers$2 activityTracker$notifySubscribers$2 = this.this$0;
            Activity activity = activityTracker$notifySubscribers$2.$activity;
            ActivityLifecycleAction activityLifecycleAction = activityTracker$notifySubscribers$2.$action;
            this.L$0 = coroutineScope;
            this.L$1 = next;
            this.L$2 = it2;
            this.label = 1;
            if (next.invoke(activity, activityLifecycleAction, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
