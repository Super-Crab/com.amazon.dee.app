package com.amazon.tarazed.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
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
/* compiled from: AbstractDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.dialog.AbstractDialog$finish$1", f = "AbstractDialog.kt", i = {0}, l = {114}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class AbstractDialog$finish$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AbstractDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractDialog$finish$1(AbstractDialog abstractDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = abstractDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AbstractDialog$finish$1 abstractDialog$finish$1 = new AbstractDialog$finish$1(this.this$0, completion);
        abstractDialog$finish$1.p$ = (CoroutineScope) obj;
        return abstractDialog$finish$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AbstractDialog$finish$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> function3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!this.this$0.getDeviceInfoUtility().is1PDevice()) {
                ActivityTracker activityTracker$TarazedAndroidLibrary_release = this.this$0.getActivityTracker$TarazedAndroidLibrary_release();
                function3 = this.this$0.activitySubscription;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (activityTracker$TarazedAndroidLibrary_release.unsubscribe(function3, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            AlertDialog alertDialog$TarazedAndroidLibrary_release = this.this$0.getAlertDialog$TarazedAndroidLibrary_release();
            if (alertDialog$TarazedAndroidLibrary_release != null) {
                alertDialog$TarazedAndroidLibrary_release.dismiss();
            }
        } catch (IllegalArgumentException e) {
            this.this$0.getLogger().w("AbstractDialog", "IllegalArgumentException caught while dismissing dialog. This is likely on a 3P device and the dialog's view is not attached to the window manager", e);
            this.this$0.getMetricsHelper().addCount("AbstractDialog", "DialogDestroyedActivity", 1.0d);
        }
        this.this$0.setAlertDialog$TarazedAndroidLibrary_release(null);
        return Unit.INSTANCE;
    }
}
