package com.amazon.tarazed.ui.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.border.StaticBorderManager;
import com.amazon.tarazed.ui.menu.MenuDrawerManager;
import com.amazon.tarazed.ui.toast.ToastManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedDrawerUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 92\u00020\u0001:\u00019Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u0010\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020/H\u0016J\u0011\u00100\u001a\u00020 H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020 H\u0016J\b\u00105\u001a\u00020 H\u0002J\u0011\u00106\u001a\u00020 H\u0096@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u00107\u001a\u000203H\u0002J\u0011\u00108\u001a\u00020 H\u0096@ø\u0001\u0000¢\u0006\u0002\u00101R5\u0010\u001b\u001a$\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0012\u0006\u0012\u0004\u0018\u00010!0\u001cX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\"R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010%\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010)\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020+\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0012\u0006\u0012\u0004\u0018\u00010!0*X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedDrawerUIManager;", "Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "context", "Landroid/content/Context;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "tarazedSessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "tvManager", "Lcom/amazon/tarazed/ui/tv/TVUIManager;", "mainLooperHandler", "Landroid/os/Handler;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "toastManager", "Lcom/amazon/tarazed/ui/toast/ToastManager;", "borderManager", "Lcom/amazon/tarazed/ui/border/StaticBorderManager;", "menuDrawerManager", "Lcom/amazon/tarazed/ui/menu/MenuDrawerManager;", "(Landroid/content/Context;Lcom/amazon/tarazed/ui/ViewGroupManager;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/ui/tv/TVUIManager;Landroid/os/Handler;Lcom/amazon/tarazed/activity/ActivityTracker;Lcom/amazon/tarazed/ui/toast/ToastManager;Lcom/amazon/tarazed/ui/border/StaticBorderManager;Lcom/amazon/tarazed/ui/menu/MenuDrawerManager;)V", "activitySubscription", "Lkotlin/Function3;", "Landroid/app/Activity;", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/jvm/functions/Function3;", "isEnabled", "", "()Z", "setEnabled", "(Z)V", "isPaused", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/jvm/functions/Function2;", "displayToast", "message", "", "hideNavigationDrawer", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializeUI", "Lkotlinx/coroutines/Job;", "showSessionControls", "subscribe", "subscribeToActivityChanges", "teardownUI", "unsubscribeFromActivityChanges", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedDrawerUIManager implements TarazedUIManager {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedDrawerUIManager";
    private final Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> activitySubscription;
    private final ActivityTracker activityTracker;
    private final StaticBorderManager borderManager;
    private final Context context;
    private final DeviceInfoUtility deviceInfoUtility;
    private boolean isEnabled;
    private boolean isPaused;
    private final TarazedSessionLogger logger;
    private final Handler mainLooperHandler;
    private final MenuDrawerManager menuDrawerManager;
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;
    private final TarazedSessionNotifier tarazedSessionNotifier;
    private final ToastManager toastManager;
    private final TVUIManager tvManager;
    private final ViewGroupManager viewGroupManager;

    /* compiled from: TarazedDrawerUIManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedDrawerUIManager$Companion;", "", "()V", "TAG", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
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
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[TarazedNotificationEvent.values().length];
            $EnumSwitchMapping$1[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$1[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 3;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER.ordinal()] = 4;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 5;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 6;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 7;
        }
    }

    public TarazedDrawerUIManager(@NotNull Context context, @NotNull ViewGroupManager viewGroupManager, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier tarazedSessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TVUIManager tvManager, @NotNull Handler mainLooperHandler, @NotNull ActivityTracker activityTracker, @NotNull ToastManager toastManager, @NotNull StaticBorderManager borderManager, @NotNull MenuDrawerManager menuDrawerManager) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(tarazedSessionNotifier, "tarazedSessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(tvManager, "tvManager");
        Intrinsics.checkParameterIsNotNull(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        Intrinsics.checkParameterIsNotNull(toastManager, "toastManager");
        Intrinsics.checkParameterIsNotNull(borderManager, "borderManager");
        Intrinsics.checkParameterIsNotNull(menuDrawerManager, "menuDrawerManager");
        this.context = context;
        this.viewGroupManager = viewGroupManager;
        this.deviceInfoUtility = deviceInfoUtility;
        this.tarazedSessionNotifier = tarazedSessionNotifier;
        this.logger = logger;
        this.tvManager = tvManager;
        this.mainLooperHandler = mainLooperHandler;
        this.activityTracker = activityTracker;
        this.toastManager = toastManager;
        this.borderManager = borderManager;
        this.menuDrawerManager = menuDrawerManager;
        this.activitySubscription = new TarazedDrawerUIManager$activitySubscription$1(this, null);
        this.sessionNotificationHandler = new TarazedDrawerUIManager$sessionNotificationHandler$1(this, null);
        subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job initializeUI() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TarazedDrawerUIManager$initializeUI$1(this, null), 2, null);
        return launch$default;
    }

    private final void subscribe() {
        TarazedSessionNotifier.subscribe$default(this.tarazedSessionNotifier, this.sessionNotificationHandler, ListenerPriority.MEDIUM, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job teardownUI() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TarazedDrawerUIManager$teardownUI$1(this, null), 2, null);
        return launch$default;
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    public void displayToast(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.toastManager.displayToast(message, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object hideNavigationDrawer(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$hideNavigationDrawer$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$hideNavigationDrawer$1 r0 = (com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$hideNavigationDrawer$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$hideNavigationDrawer$1 r0 = new com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$hideNavigationDrawer$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.ui.manager.TarazedDrawerUIManager r0 = (com.amazon.tarazed.ui.manager.TarazedDrawerUIManager) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L52
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            boolean r5 = r4.isEnabled
            if (r5 == 0) goto L49
            com.amazon.tarazed.ui.menu.MenuDrawerManager r5 = r4.menuDrawerManager
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.hideMenuImmediate(r0)
            if (r5 != r1) goto L52
            return r1
        L49:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r5 = r4.logger
            java.lang.String r0 = "TarazedDrawerUIManager"
            java.lang.String r1 = "tried to hide navigation drawer while UI is disabled"
            r5.i(r0, r1)
        L52:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.ui.manager.TarazedDrawerUIManager.hideNavigationDrawer(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    @Override // com.amazon.tarazed.ui.manager.TarazedUIManager
    public void showSessionControls() {
        if (this.isEnabled) {
            this.menuDrawerManager.showMenu();
        } else {
            this.logger.i(TAG, "tried to show navigation drawer while UI is disabled");
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

    public /* synthetic */ TarazedDrawerUIManager(Context context, ViewGroupManager viewGroupManager, DeviceInfoUtility deviceInfoUtility, TarazedSessionNotifier tarazedSessionNotifier, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, TVUIManager tVUIManager, Handler handler, ActivityTracker activityTracker, ToastManager toastManager, StaticBorderManager staticBorderManager, MenuDrawerManager menuDrawerManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, viewGroupManager, deviceInfoUtility, tarazedSessionNotifier, tarazedSessionLogger, tarazedMetricsHelper, tVUIManager, handler, activityTracker, (i & 512) != 0 ? new ToastManager(context, handler, deviceInfoUtility) : toastManager, (i & 1024) != 0 ? new StaticBorderManager(context, viewGroupManager) : staticBorderManager, (i & 2048) != 0 ? new MenuDrawerManager(context, viewGroupManager, deviceInfoUtility, tarazedSessionLogger, tarazedMetricsHelper) : menuDrawerManager);
    }
}
