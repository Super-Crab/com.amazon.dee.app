package com.amazon.tarazed.activity;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.Nullable;
/* compiled from: ActivityTracker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/tarazed/activity/ActivityTracker$notifySubscribers$2", "Landroid/view/View$OnAttachStateChangeListener;", "onViewAttachedToWindow", "", "v", "Landroid/view/View;", "onViewDetachedFromWindow", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ActivityTracker$notifySubscribers$2 implements View.OnAttachStateChangeListener {
    final /* synthetic */ ActivityLifecycleAction $action;
    final /* synthetic */ Activity $activity;
    final /* synthetic */ ActivityTracker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityTracker$notifySubscribers$2(ActivityTracker activityTracker, Activity activity, ActivityLifecycleAction activityLifecycleAction) {
        this.this$0 = activityTracker;
        this.$activity = activity;
        this.$action = activityLifecycleAction;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(@Nullable View view) {
        CoroutineScope coroutineScope;
        DispatcherProvider dispatcherProvider;
        Window window = this.$activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        window.getDecorView().removeOnAttachStateChangeListener(this);
        coroutineScope = this.this$0.coroutineScope;
        dispatcherProvider = this.this$0.dispatcherProvider;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, dispatcherProvider.main(), null, new ActivityTracker$notifySubscribers$2$onViewAttachedToWindow$1(this, null), 2, null);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(@Nullable View view) {
    }
}
