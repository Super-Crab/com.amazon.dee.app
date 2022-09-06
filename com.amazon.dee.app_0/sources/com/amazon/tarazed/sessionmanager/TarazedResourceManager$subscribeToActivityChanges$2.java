package com.amazon.tarazed.sessionmanager;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.drew.metadata.iptc.IptcDirectory;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.ResultKt;
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
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "lifecycleAction", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager$subscribeToActivityChanges$2", f = "TarazedResourceManager.kt", i = {0, 0, 1, 1}, l = {570, IptcDirectory.TAG_TIME_CREATED}, m = "invokeSuspend", n = {MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "lifecycleAction", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "lifecycleAction"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$subscribeToActivityChanges$2 extends SuspendLambda implements Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private Activity p$0;
    private ActivityLifecycleAction p$1;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$subscribeToActivityChanges$2(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tarazedResourceManager;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Activity activity, @NotNull ActivityLifecycleAction lifecycleAction, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(lifecycleAction, "lifecycleAction");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        TarazedResourceManager$subscribeToActivityChanges$2 tarazedResourceManager$subscribeToActivityChanges$2 = new TarazedResourceManager$subscribeToActivityChanges$2(this.this$0, continuation);
        tarazedResourceManager$subscribeToActivityChanges$2.p$0 = activity;
        tarazedResourceManager$subscribeToActivityChanges$2.p$1 = lifecycleAction;
        return tarazedResourceManager$subscribeToActivityChanges$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Activity activity, ActivityLifecycleAction activityLifecycleAction, Continuation<? super Unit> continuation) {
        return ((TarazedResourceManager$subscribeToActivityChanges$2) create(activity, activityLifecycleAction, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Activity activity;
        ActivityLifecycleAction activityLifecycleAction;
        Provider provider;
        ActivityTracker activityTracker;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            activity = this.p$0;
            activityLifecycleAction = this.p$1;
            int i2 = TarazedResourceManager.WhenMappings.$EnumSwitchMapping$1[activityLifecycleAction.ordinal()];
            if ((i2 == 1 || i2 == 2) && this.this$0.getActivitySubscription$TarazedAndroidLibrary_release() != null) {
                TarazedSessionLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
                TarazedResourceManager.Companion unused = TarazedResourceManager.Companion;
                logger$TarazedAndroidLibrary_release.i("TarazedResourceManager", "Activity detected, initializing UI.");
                provider = this.this$0.viewGroupManager;
                ((ViewGroupManager) provider.mo10268get()).updateContext(activity);
                TarazedResourceManager tarazedResourceManager = this.this$0;
                this.L$0 = activity;
                this.L$1 = activityLifecycleAction;
                this.label = 1;
                if (tarazedResourceManager.initializeUIElements(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ActivityLifecycleAction activityLifecycleAction2 = (ActivityLifecycleAction) this.L$1;
            Activity activity2 = (Activity) this.L$0;
            ResultKt.throwOnFailure(obj);
            this.this$0.setActivitySubscription$TarazedAndroidLibrary_release(null);
            return Unit.INSTANCE;
        } else {
            activityLifecycleAction = (ActivityLifecycleAction) this.L$1;
            ResultKt.throwOnFailure(obj);
            activity = (Activity) this.L$0;
        }
        activityTracker = this.this$0.activityTracker;
        Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> activitySubscription$TarazedAndroidLibrary_release = this.this$0.getActivitySubscription$TarazedAndroidLibrary_release();
        if (activitySubscription$TarazedAndroidLibrary_release == null) {
            Intrinsics.throwNpe();
        }
        this.L$0 = activity;
        this.L$1 = activityLifecycleAction;
        this.label = 2;
        if (activityTracker.unsubscribe(activitySubscription$TarazedAndroidLibrary_release, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.setActivitySubscription$TarazedAndroidLibrary_release(null);
        return Unit.INSTANCE;
    }
}
