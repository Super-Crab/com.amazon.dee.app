package com.amazon.tarazed.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ActivityTracker.kt */
@LibraryScope
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 82\u00020\u0001:\u00018B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u0019H\u0002J\u001a\u0010&\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0018\u0010,\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020(H\u0016J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010/\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u000e\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J;\u00103\u001a\u00020\u001b2(\u00104\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J\u000e\u00106\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J;\u00107\u001a\u00020\u001b2(\u00104\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000RO\u0010\u0016\u001a*\u0012&\u0012$\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00180\u00178\u0006@\u0006X\u0087\u000eø\u0001\u0000¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"Lcom/amazon/tarazed/activity/ActivityTracker;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatcherProvider", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;)V", "<set-?>", "Landroid/app/Activity;", "currentActivity", "getCurrentActivity", "()Landroid/app/Activity;", "", "isActivityRunning", "()Z", "registered", "subscribers", "", "Lkotlin/Function3;", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "Lkotlin/coroutines/Continuation;", "", "", "subscribers$annotations", "()V", "getSubscribers", "()Ljava/util/List;", "setSubscribers", "(Ljava/util/List;)V", "notifySubscribers", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "action", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "registerLifecycleCallbacks", "context", "Landroid/content/Context;", "subscribe", "subscription", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterLifecycleCallbacks", "unsubscribe", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ActivityTracker implements Application.ActivityLifecycleCallbacks {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_DUPLICATE_CALLBACKS_REGISTRATION = "DuplicateCallbacksRegistration";
    private static final String METRIC_DUPLICATE_CALLBACKS_UNREGISTRATION = "DuplicateCallbacksUnregistration";
    private static final String TAG = "ActivityTracker";
    private final CoroutineScope coroutineScope;
    @Nullable
    private Activity currentActivity;
    private final DeviceInfoUtility deviceInfoUtility;
    private final DispatcherProvider dispatcherProvider;
    private boolean isActivityRunning;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private boolean registered;
    @NotNull
    private List<Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object>> subscribers;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ActivityTracker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/activity/ActivityTracker$Companion;", "", "()V", "METRIC_DUPLICATE_CALLBACKS_REGISTRATION", "", "METRIC_DUPLICATE_CALLBACKS_UNREGISTRATION", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public ActivityTracker(@NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedMetricsHelper metricsHelper, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatcherProvider) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatcherProvider, "dispatcherProvider");
        this.logger = logger;
        this.deviceInfoUtility = deviceInfoUtility;
        this.metricsHelper = metricsHelper;
        this.coroutineScope = coroutineScope;
        this.dispatcherProvider = dispatcherProvider;
        List<Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object>> synchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkExpressionValueIsNotNull(synchronizedList, "Collections\n        .syn…ecycleAction) -> Unit>())");
        this.subscribers = synchronizedList;
    }

    private final void notifySubscribers(Activity activity, ActivityLifecycleAction activityLifecycleAction) {
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "activity.window.decorView");
        if (decorView.getWindowToken() != null) {
            BuildersKt.launch$default(this.coroutineScope, this.dispatcherProvider.main(), null, new ActivityTracker$notifySubscribers$1(this, activity, activityLifecycleAction, null), 2, null);
        } else {
            decorView.addOnAttachStateChangeListener(new ActivityTracker$notifySubscribers$2(this, activity, activityLifecycleAction));
        }
    }

    @VisibleForTesting
    public static /* synthetic */ void subscribers$annotations() {
    }

    @Nullable
    public final Activity getCurrentActivity() {
        return this.currentActivity;
    }

    @NotNull
    public final List<Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object>> getSubscribers() {
        return this.subscribers;
    }

    public final boolean isActivityRunning() {
        return this.isActivityRunning;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Activity paused: " + activity);
        this.isActivityRunning = false;
        notifySubscribers(activity, ActivityLifecycleAction.PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Activity resumed: " + activity);
        this.currentActivity = activity;
        this.isActivityRunning = true;
        notifySubscribers(activity, ActivityLifecycleAction.RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    public final void registerLifecycleCallbacks(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.deviceInfoUtility.is1PDevice()) {
            this.logger.i(TAG, "Device is 1P, activity lifecycle callbacks are not needed");
        } else if (this.registered) {
            this.logger.w(TAG, "Activity lifecycle callbacks have already been registered");
            this.metricsHelper.addCount(TAG, METRIC_DUPLICATE_CALLBACKS_REGISTRATION, 1.0d);
        } else {
            this.registered = true;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                ((Application) applicationContext).registerActivityLifecycleCallbacks(this);
                this.logger.i(TAG, "Registered activity lifecycle callbacks");
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
        }
    }

    public final void setSubscribers(@NotNull List<Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.subscribers = list;
    }

    @Nullable
    public final Object subscribe(@NotNull Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> function3, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(this.dispatcherProvider.main(), new ActivityTracker$subscribe$2(this, function3, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void unregisterLifecycleCallbacks(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.deviceInfoUtility.is1PDevice()) {
            this.logger.i(TAG, "Device is 1P, activity lifecycle callbacks are not needed");
        } else if (!this.registered) {
            this.logger.w(TAG, "Activity lifecycle callbacks are already not registered");
            this.metricsHelper.addCount(TAG, METRIC_DUPLICATE_CALLBACKS_UNREGISTRATION, 1.0d);
        } else {
            this.registered = false;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                ((Application) applicationContext).unregisterActivityLifecycleCallbacks(this);
                this.currentActivity = null;
                this.logger.i(TAG, "Unregistered activity lifecycle callbacks");
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
        }
    }

    @Nullable
    public final Object unsubscribe(@NotNull Function3<? super Activity, ? super ActivityLifecycleAction, ? super Continuation<? super Unit>, ? extends Object> function3, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(this.dispatcherProvider.main(), new ActivityTracker$unsubscribe$2(this, function3, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
