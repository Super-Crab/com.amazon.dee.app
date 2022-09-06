package com.amazon.tarazed.activity;

import android.app.Activity;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActivityTracker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.activity.ActivityTracker$subscribe$2", f = "ActivityTracker.kt", i = {0}, l = {76}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class ActivityTracker$subscribe$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $subscription;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ActivityTracker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityTracker$subscribe$2(ActivityTracker activityTracker, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = activityTracker;
        this.$subscription = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ActivityTracker$subscribe$2 activityTracker$subscribe$2 = new ActivityTracker$subscribe$2(this.this$0, this.$subscription, completion);
        activityTracker$subscribe$2.p$ = (CoroutineScope) obj;
        return activityTracker$subscribe$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ActivityTracker$subscribe$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        DeviceInfoUtility deviceInfoUtility;
        TarazedSessionLogger tarazedSessionLogger;
        ActivityTracker.Companion unused;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            deviceInfoUtility = this.this$0.deviceInfoUtility;
            if (deviceInfoUtility.is1PDevice()) {
                tarazedSessionLogger = this.this$0.logger;
                unused = ActivityTracker.Companion;
                tarazedSessionLogger.i("ActivityTracker", "Device is a 1P device, ignoring activity changes");
                return Unit.INSTANCE;
            }
            this.this$0.getSubscribers().add(this.$subscription);
            if (this.this$0.getCurrentActivity() != null) {
                Function3 function3 = this.$subscription;
                Activity currentActivity = this.this$0.getCurrentActivity();
                if (currentActivity == null) {
                    Intrinsics.throwNpe();
                }
                ActivityLifecycleAction activityLifecycleAction = ActivityLifecycleAction.INITIAL;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (function3.invoke(currentActivity, activityLifecycleAction, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
