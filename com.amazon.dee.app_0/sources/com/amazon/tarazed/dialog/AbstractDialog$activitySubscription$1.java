package com.amazon.tarazed.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.dialog.AbstractDialog;
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
/* compiled from: AbstractDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/app/Activity;", "action", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.dialog.AbstractDialog$activitySubscription$1", f = "AbstractDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class AbstractDialog$activitySubscription$1 extends SuspendLambda implements Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> {
    int label;
    private Activity p$0;
    private ActivityLifecycleAction p$1;
    final /* synthetic */ AbstractDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractDialog$activitySubscription$1(AbstractDialog abstractDialog, Continuation continuation) {
        super(3, continuation);
        this.this$0 = abstractDialog;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Activity activity, @NotNull ActivityLifecycleAction action, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(activity, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        AbstractDialog$activitySubscription$1 abstractDialog$activitySubscription$1 = new AbstractDialog$activitySubscription$1(this.this$0, continuation);
        abstractDialog$activitySubscription$1.p$0 = activity;
        abstractDialog$activitySubscription$1.p$1 = action;
        return abstractDialog$activitySubscription$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Activity activity, ActivityLifecycleAction activityLifecycleAction, Continuation<? super Unit> continuation) {
        return ((AbstractDialog$activitySubscription$1) create(activity, activityLifecycleAction, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int i = AbstractDialog.WhenMappings.$EnumSwitchMapping$0[this.p$1.ordinal()];
            if (i == 1) {
                AlertDialog alertDialog$TarazedAndroidLibrary_release = this.this$0.getAlertDialog$TarazedAndroidLibrary_release();
                if (alertDialog$TarazedAndroidLibrary_release != null) {
                    alertDialog$TarazedAndroidLibrary_release.dismiss();
                }
            } else if (i == 2) {
                this.this$0.showInternal();
            } else if (i == 3 && this.this$0.getActivityTracker$TarazedAndroidLibrary_release().isActivityRunning()) {
                this.this$0.showInternal();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
