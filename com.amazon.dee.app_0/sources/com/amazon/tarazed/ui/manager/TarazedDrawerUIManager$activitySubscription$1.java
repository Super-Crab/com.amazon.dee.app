package com.amazon.tarazed.ui.manager;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.border.StaticBorderManager;
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
/* compiled from: TarazedDrawerUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "action", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$activitySubscription$1", f = "TarazedDrawerUIManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class TarazedDrawerUIManager$activitySubscription$1 extends SuspendLambda implements Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> {
    int label;
    private Activity p$0;
    private ActivityLifecycleAction p$1;
    final /* synthetic */ TarazedDrawerUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDrawerUIManager$activitySubscription$1(TarazedDrawerUIManager tarazedDrawerUIManager, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tarazedDrawerUIManager;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Activity activity, @NotNull ActivityLifecycleAction action, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        TarazedDrawerUIManager$activitySubscription$1 tarazedDrawerUIManager$activitySubscription$1 = new TarazedDrawerUIManager$activitySubscription$1(this.this$0, continuation);
        tarazedDrawerUIManager$activitySubscription$1.p$0 = activity;
        tarazedDrawerUIManager$activitySubscription$1.p$1 = action;
        return tarazedDrawerUIManager$activitySubscription$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Activity activity, ActivityLifecycleAction activityLifecycleAction, Continuation<? super Unit> continuation) {
        return ((TarazedDrawerUIManager$activitySubscription$1) create(activity, activityLifecycleAction, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ViewGroupManager viewGroupManager;
        StaticBorderManager staticBorderManager;
        ViewGroupManager viewGroupManager2;
        StaticBorderManager staticBorderManager2;
        StaticBorderManager staticBorderManager3;
        ViewGroupManager viewGroupManager3;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Activity activity = this.p$0;
            ActivityLifecycleAction activityLifecycleAction = this.p$1;
            if (!this.this$0.isEnabled() || activityLifecycleAction != ActivityLifecycleAction.PAUSE) {
                viewGroupManager = this.this$0.viewGroupManager;
                viewGroupManager.updateContext(activity);
                staticBorderManager = this.this$0.borderManager;
                staticBorderManager.updateContext$TarazedAndroidLibrary_release(activity);
                if (this.this$0.isEnabled()) {
                    viewGroupManager2 = this.this$0.viewGroupManager;
                    viewGroupManager2.addViewGroupsToWindowManager();
                    staticBorderManager2 = this.this$0.borderManager;
                    staticBorderManager2.addBorder();
                }
                return Unit.INSTANCE;
            }
            staticBorderManager3 = this.this$0.borderManager;
            staticBorderManager3.removeBorder();
            viewGroupManager3 = this.this$0.viewGroupManager;
            viewGroupManager3.removeViewGroupsFromWindowManager();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
