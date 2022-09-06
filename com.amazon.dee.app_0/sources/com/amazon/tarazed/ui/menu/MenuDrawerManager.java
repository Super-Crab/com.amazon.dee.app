package com.amazon.tarazed.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.amazon.tarazed.ui.ViewGroupManager;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MenuDrawerManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 A2\u00020\u0001:\u0001AB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010-\u001a\u00020\u0011H\u0002J\u0012\u0010.\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u00010\u000eH\u0002J\u0015\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0000¢\u0006\u0002\b3J\u0012\u00104\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u00010\u000eH\u0002J\r\u00105\u001a\u00020\u001eH\u0001¢\u0006\u0002\b6J\u0011\u00107\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u00108J\u0010\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0002J\u0012\u0010=\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u00010\u000eH\u0002J\u0006\u0010>\u001a\u00020\u001eJ\u0010\u0010?\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0002J\u0010\u0010@\u001a\u00020\u001e2\u0006\u00101\u001a\u000202H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u0015R\u001c\u0010\u001d\u001a\u00020\u001e8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b \u0010!R\u001c\u0010\"\u001a\u00020\u001e8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0013\u001a\u0004\b$\u0010!R\u0010\u0010%\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0013\u001a\u0004\b(\u0010\u0015R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, d2 = {"Lcom/amazon/tarazed/ui/menu/MenuDrawerManager;", "", "context", "Landroid/content/Context;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/ui/ViewGroupManager;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "endSessionButton", "Landroid/view/View;", "endSessionButtonHandler", "Lkotlin/Function0;", "", "endSessionButtonHandler$annotations", "()V", "getEndSessionButtonHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function0;", "isSessionPaused", "", "menuView", "pauseSessionButton", "pauseSessionButtonHandler", "pauseSessionButtonHandler$annotations", "getPauseSessionButtonHandler$TarazedAndroidLibrary_release", "reEnablePauseButtonTimer", "Lkotlinx/coroutines/Job;", "reEnablePauseButtonTimer$annotations", "getReEnablePauseButtonTimer$TarazedAndroidLibrary_release", "()Lkotlinx/coroutines/Job;", "reEnableResumeButtonTimer", "reEnableResumeButtonTimer$annotations", "getReEnableResumeButtonTimer$TarazedAndroidLibrary_release", "resumeSessionButton", "resumeSessionButtonHandler", "resumeSessionButtonHandler$annotations", "getResumeSessionButtonHandler$TarazedAndroidLibrary_release", "statusView", "Landroid/widget/TextView;", "transitionResId", "", "bindMenuClickListeners", "disableButton", "button", "handleSessionChange", "notificationEvent", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "handleSessionChange$TarazedAndroidLibrary_release", "hideButton", "hideMenu", "hideMenu$TarazedAndroidLibrary_release", "hideMenuImmediate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSessionStateChange", "action", "", "setStatus", "showButton", "showMenu", "updateButtons", "updateMenuDrawer", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MenuDrawerManager {
    private static final float BUTTON_DISABLED_ALPHA = 0.5f;
    public static final long BUTTON_DISABLED_TIMEOUT = 2000;
    private static final float BUTTON_ENABLED_ALPHA = 1.0f;
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_NULL_VIEW = "NullView";
    private static final String TAG = "MenuDrawerManager";
    private final Context context;
    private final DeviceInfoUtility deviceInfoUtility;
    private final View endSessionButton;
    @NotNull
    private final Function0<Unit> endSessionButtonHandler;
    private boolean isSessionPaused;
    private final TarazedSessionLogger logger;
    private final View menuView;
    private final TarazedMetricsHelper metricsHelper;
    private final View pauseSessionButton;
    @NotNull
    private final Function0<Unit> pauseSessionButtonHandler;
    @NotNull
    private final Job reEnablePauseButtonTimer;
    @NotNull
    private final Job reEnableResumeButtonTimer;
    private final View resumeSessionButton;
    @NotNull
    private final Function0<Unit> resumeSessionButtonHandler;
    private final TextView statusView;
    private final int transitionResId;
    private final ViewGroupManager viewGroupManager;

    /* compiled from: MenuDrawerManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00068\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/ui/menu/MenuDrawerManager$Companion;", "", "()V", "BUTTON_DISABLED_ALPHA", "", "BUTTON_DISABLED_TIMEOUT", "", "BUTTON_DISABLED_TIMEOUT$annotations", "BUTTON_ENABLED_ALPHA", "METRIC_NULL_VIEW", "", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void BUTTON_DISABLED_TIMEOUT$annotations() {
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
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 5;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 6;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 7;
            $EnumSwitchMapping$1 = new int[TarazedNotificationEvent.values().length];
            $EnumSwitchMapping$1[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 2;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER.ordinal()] = 3;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 4;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 5;
            $EnumSwitchMapping$2 = new int[TarazedNotificationEvent.values().length];
            $EnumSwitchMapping$2[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$2[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$2[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 3;
            $EnumSwitchMapping$2[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 4;
            $EnumSwitchMapping$2[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 5;
            $EnumSwitchMapping$2[TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER.ordinal()] = 6;
            $EnumSwitchMapping$2[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 7;
        }
    }

    public MenuDrawerManager(@NotNull Context context, @NotNull ViewGroupManager viewGroupManager, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Job launch$default;
        Job launch$default2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.context = context;
        this.viewGroupManager = viewGroupManager;
        this.deviceInfoUtility = deviceInfoUtility;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.menuView = this.viewGroupManager.inflateStaticView(R.layout.firetv_menu);
        if (this.menuView == null) {
            this.logger.e(TAG, "menuView is null, UI will misbehave");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_NULL_VIEW, 1.0d);
        }
        this.transitionResId = 17760258;
        View view = this.menuView;
        this.endSessionButton = view != null ? view.findViewById(R.id.menu_end_session) : null;
        View view2 = this.menuView;
        this.pauseSessionButton = view2 != null ? view2.findViewById(R.id.menu_pause) : null;
        View view3 = this.menuView;
        this.resumeSessionButton = view3 != null ? view3.findViewById(R.id.menu_resume) : null;
        View view4 = this.menuView;
        this.statusView = view4 != null ? (TextView) view4.findViewById(R.id.menu_status) : null;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new MenuDrawerManager$reEnablePauseButtonTimer$1(this, null), 1, null);
        this.reEnablePauseButtonTimer = launch$default;
        launch$default2 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new MenuDrawerManager$reEnableResumeButtonTimer$1(this, null), 1, null);
        this.reEnableResumeButtonTimer = launch$default2;
        this.endSessionButtonHandler = new MenuDrawerManager$endSessionButtonHandler$1(this);
        this.resumeSessionButtonHandler = new MenuDrawerManager$resumeSessionButtonHandler$1(this);
        this.pauseSessionButtonHandler = new MenuDrawerManager$pauseSessionButtonHandler$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindMenuClickListeners() {
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.amazon.tarazed.ui.menu.MenuDrawerManager$bindMenuClickListeners$backButtonListener$1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                TarazedSessionLogger tarazedSessionLogger;
                Intrinsics.checkExpressionValueIsNotNull(keyEvent, "keyEvent");
                if (keyEvent.getAction() == 0 && 4 == keyEvent.getKeyCode() && keyEvent.getRepeatCount() == 0) {
                    MenuDrawerManager.this.hideMenu$TarazedAndroidLibrary_release();
                    tarazedSessionLogger = MenuDrawerManager.this.logger;
                    tarazedSessionLogger.d("MenuDrawerManager", "Back key pressed on " + view);
                    return true;
                }
                return false;
            }
        };
        View view = this.endSessionButton;
        if (view != null) {
            view.setOnKeyListener(onKeyListener);
        }
        View view2 = this.pauseSessionButton;
        if (view2 != null) {
            view2.setOnKeyListener(onKeyListener);
        }
        View view3 = this.resumeSessionButton;
        if (view3 != null) {
            view3.setOnKeyListener(onKeyListener);
        }
        View view4 = this.endSessionButton;
        if (view4 != null) {
            view4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.tarazed.ui.menu.MenuDrawerManager$bindMenuClickListeners$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view5) {
                    MenuDrawerManager.this.getEndSessionButtonHandler$TarazedAndroidLibrary_release();
                }
            });
        }
        View view5 = this.pauseSessionButton;
        if (view5 != null) {
            view5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.tarazed.ui.menu.MenuDrawerManager$bindMenuClickListeners$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view6) {
                    MenuDrawerManager.this.getPauseSessionButtonHandler$TarazedAndroidLibrary_release();
                }
            });
        }
        View view6 = this.resumeSessionButton;
        if (view6 != null) {
            view6.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.tarazed.ui.menu.MenuDrawerManager$bindMenuClickListeners$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view7) {
                    MenuDrawerManager.this.getResumeSessionButtonHandler$TarazedAndroidLibrary_release();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disableButton(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
        if (view != null) {
            view.setAlpha(BUTTON_DISABLED_ALPHA);
        }
        if (view != null) {
            view.setClickable(false);
        }
    }

    @VisibleForTesting
    public static /* synthetic */ void endSessionButtonHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideButton(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
        if (view != null) {
            view.setAlpha(1.0f);
        }
        if (view != null) {
            view.setClickable(true);
        }
    }

    @VisibleForTesting
    public static /* synthetic */ void pauseSessionButtonHandler$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void reEnablePauseButtonTimer$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void reEnableResumeButtonTimer$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void resumeSessionButtonHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSessionStateChange(String str) {
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(str);
        this.context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatus(TarazedNotificationEvent tarazedNotificationEvent) {
        switch (WhenMappings.$EnumSwitchMapping$2[tarazedNotificationEvent.ordinal()]) {
            case 1:
                TextView textView = this.statusView;
                if (textView == null) {
                    return;
                }
                textView.setText(R.string.screen_sharing_connecting);
                return;
            case 2:
            case 3:
            case 4:
                TextView textView2 = this.statusView;
                if (textView2 == null) {
                    return;
                }
                textView2.setText(R.string.screen_sharing_active);
                return;
            case 5:
            case 6:
            case 7:
                TextView textView3 = this.statusView;
                if (textView3 == null) {
                    return;
                }
                textView3.setText(R.string.screen_sharing_paused);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showButton(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
        if (view != null) {
            view.setAlpha(1.0f);
        }
        if (view != null) {
            view.setClickable(true);
        }
        if (!this.deviceInfoUtility.isFireTV() || view == null) {
            return;
        }
        view.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateButtons(TarazedNotificationEvent tarazedNotificationEvent) {
        int i = WhenMappings.$EnumSwitchMapping$1[tarazedNotificationEvent.ordinal()];
        if (i == 1) {
            showButton(this.pauseSessionButton);
            hideButton(this.resumeSessionButton);
        } else if (i == 2) {
            hideButton(this.pauseSessionButton);
            hideButton(this.resumeSessionButton);
            Job.DefaultImpls.cancel$default(this.reEnablePauseButtonTimer, (CancellationException) null, 1, (Object) null);
        } else if (i == 3) {
            hideButton(this.pauseSessionButton);
            showButton(this.resumeSessionButton);
            Job.DefaultImpls.cancel$default(this.reEnablePauseButtonTimer, (CancellationException) null, 1, (Object) null);
        } else if (i != 4 && i != 5) {
        } else {
            showButton(this.pauseSessionButton);
            hideButton(this.resumeSessionButton);
            Job.DefaultImpls.cancel$default(this.reEnableResumeButtonTimer, (CancellationException) null, 1, (Object) null);
        }
    }

    private final Job updateMenuDrawer(TarazedNotificationEvent tarazedNotificationEvent) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MenuDrawerManager$updateMenuDrawer$1(this, tarazedNotificationEvent, null), 2, null);
        return launch$default;
    }

    @NotNull
    public final Function0<Unit> getEndSessionButtonHandler$TarazedAndroidLibrary_release() {
        return this.endSessionButtonHandler;
    }

    @NotNull
    public final Function0<Unit> getPauseSessionButtonHandler$TarazedAndroidLibrary_release() {
        return this.pauseSessionButtonHandler;
    }

    @NotNull
    public final Job getReEnablePauseButtonTimer$TarazedAndroidLibrary_release() {
        return this.reEnablePauseButtonTimer;
    }

    @NotNull
    public final Job getReEnableResumeButtonTimer$TarazedAndroidLibrary_release() {
        return this.reEnableResumeButtonTimer;
    }

    @NotNull
    public final Function0<Unit> getResumeSessionButtonHandler$TarazedAndroidLibrary_release() {
        return this.resumeSessionButtonHandler;
    }

    public final void handleSessionChange$TarazedAndroidLibrary_release(@NotNull TarazedNotificationEvent notificationEvent) {
        Intrinsics.checkParameterIsNotNull(notificationEvent, "notificationEvent");
        switch (WhenMappings.$EnumSwitchMapping$0[notificationEvent.ordinal()]) {
            case 1:
                updateMenuDrawer(notificationEvent);
                return;
            case 2:
                if (this.isSessionPaused) {
                    return;
                }
                updateMenuDrawer(notificationEvent);
                return;
            case 3:
            case 4:
            case 5:
                updateMenuDrawer(notificationEvent);
                this.isSessionPaused = true;
                return;
            case 6:
            case 7:
                updateMenuDrawer(notificationEvent);
                this.isSessionPaused = false;
                return;
            default:
                this.logger.d(TAG, "Unexpected notification event in Menu Drawer Manager");
                return;
        }
    }

    @VisibleForTesting
    @NotNull
    public final Job hideMenu$TarazedAndroidLibrary_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MenuDrawerManager$hideMenu$1(this, null), 2, null);
        return launch$default;
    }

    @Nullable
    public final Object hideMenuImmediate(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new MenuDrawerManager$hideMenuImmediate$2(this, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return withContext == coroutine_suspended ? withContext : Unit.INSTANCE;
    }

    @NotNull
    public final Job showMenu() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MenuDrawerManager$showMenu$1(this, null), 2, null);
        return launch$default;
    }
}
