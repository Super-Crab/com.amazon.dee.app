package com.amazon.tarazed.ui.manager;

import android.app.Activity;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.border.DynamicBorderManager;
import com.amazon.tarazed.ui.menu.MenuBarManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedBarUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 32\u00020\u0001:\u00013BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H\u0016J\r\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-J\b\u0010.\u001a\u00020\u001aH\u0016J\u0011\u0010/\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0002\u00100J\b\u00101\u001a\u00020,H\u0002J\u0011\u00102\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0002\u00100R5\u0010\u0015\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0016X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R=\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0!8\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010'\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedBarUIManager;", "Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "borderManager", "Lcom/amazon/tarazed/ui/border/DynamicBorderManager;", "menuBarManager", "Lcom/amazon/tarazed/ui/menu/MenuBarManager;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/ui/ViewGroupManager;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/activity/ActivityTracker;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/ui/border/DynamicBorderManager;Lcom/amazon/tarazed/ui/menu/MenuBarManager;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "activitySubscription", "Lkotlin/Function3;", "Landroid/app/Activity;", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/jvm/functions/Function3;", "isEnabled", "", "isSessionEnded", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "sessionNotificationHandler$annotations", "()V", "getSessionNotificationHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "displayToast", "message", "", "initializeUI", "Lkotlinx/coroutines/Job;", "initializeUI$TarazedAndroidLibrary_release", "showSessionControls", "subscribeToActivityChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "teardownUI", "unsubscribeFromActivityChanges", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedBarUIManager implements TarazedUIManager {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRICS_VIEWGROUPMANAGER_UPDATE_CONTEXT_FAILED = "ViewGroupMgrUpdateContextError";
    private static final String TAG = "TarazedBarUIManager";
    private final Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> activitySubscription;
    private final ActivityTracker activityTracker;
    private final DynamicBorderManager borderManager;
    private final CoroutineScope coroutineScope;
    private final DispatcherProvider dispatchers;
    private boolean isEnabled;
    private boolean isSessionEnded;
    private final TarazedSessionLogger logger;
    private final MenuBarManager menuBarManager;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;
    private final ViewGroupManager viewGroupManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TarazedBarUIManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedBarUIManager$Companion;", "", "()V", "METRICS_VIEWGROUPMANAGER_UPDATE_CONTEXT_FAILED", "", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_SUSPENDED.ordinal()] = 2;
        }
    }

    public TarazedBarUIManager(@NotNull ViewGroupManager viewGroupManager, @NotNull TarazedSessionNotifier notifier, @NotNull TarazedSessionLogger logger, @NotNull ActivityTracker activityTracker, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull DynamicBorderManager borderManager, @NotNull MenuBarManager menuBarManager, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(borderManager, "borderManager");
        Intrinsics.checkParameterIsNotNull(menuBarManager, "menuBarManager");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.viewGroupManager = viewGroupManager;
        this.logger = logger;
        this.activityTracker = activityTracker;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.borderManager = borderManager;
        this.menuBarManager = menuBarManager;
        this.metricsHelper = metricsHelper;
        this.activitySubscription = new TarazedBarUIManager$activitySubscription$1(this, null);
        this.sessionNotificationHandler = new TarazedBarUIManager$sessionNotificationHandler$1(this, null);
        TarazedSessionNotifier.subscribe$default(notifier, this.sessionNotificationHandler, ListenerPriority.MEDIUM, false, 4, null);
        initializeUI$TarazedAndroidLibrary_release();
    }

    @VisibleForTesting
    public static /* synthetic */ void sessionNotificationHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job teardownUI() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.main(), null, new TarazedBarUIManager$teardownUI$1(this, null), 2, null);
        return launch$default;
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    public void displayToast(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        throw new UnsupportedOperationException("Tried to display toast on unsupported device.");
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler$TarazedAndroidLibrary_release() {
        return this.sessionNotificationHandler;
    }

    @VisibleForTesting
    @NotNull
    public final Job initializeUI$TarazedAndroidLibrary_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.main(), null, new TarazedBarUIManager$initializeUI$1(this, null), 2, null);
        return launch$default;
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    public void showSessionControls() {
        if (this.isEnabled) {
            this.menuBarManager.showSessionControls();
        }
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    @Nullable
    public Object subscribeToActivityChanges(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object subscribe = this.activityTracker.subscribe(this.activitySubscription, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return subscribe == coroutine_suspended ? subscribe : Unit.INSTANCE;
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    @Nullable
    public Object unsubscribeFromActivityChanges(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object unsubscribe = this.activityTracker.unsubscribe(this.activitySubscription, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return unsubscribe == coroutine_suspended ? unsubscribe : Unit.INSTANCE;
    }
}
