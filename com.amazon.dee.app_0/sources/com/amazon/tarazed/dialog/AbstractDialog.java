package com.amazon.tarazed.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.activity.ActivityLifecycleAction;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.type.Response;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
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
/* compiled from: AbstractDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 x2\u00020\u0001:\u0001xB7\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eB?\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\b\u0010l\u001a\u00020\u0017H\u0002J\b\u0010m\u001a\u00020nH\u0016J\b\u0010o\u001a\u00020\u0017H$J\u0010\u0010p\u001a\u00020\u00172\u0006\u0010q\u001a\u000202H$J\u0018\u0010r\u001a\u00020\u00172\u0006\u0010s\u001a\u00020t2\u0006\u0010u\u001a\u000202H\u0004J\b\u0010v\u001a\u00020nH\u0016J\b\u0010w\u001a\u00020\u0017H\u0002R5\u0010\u0012\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0018R$\u0010\u0019\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001a8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR&\u0010 \u001a\u0004\u0018\u00010!8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\"\u0010\u001d\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020*X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u000202X¤\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0018\u00105\u001a\u0004\u0018\u000106X\u0094\u0004¢\u0006\n\n\u0002\u00109\u001a\u0004\b7\u00108R\u0012\u0010:\u001a\u000202X¤\u0004¢\u0006\u0006\u001a\u0004\b;\u00104R\u0018\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=X¤\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020>X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020>X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bE\u0010CR\u0014\u0010F\u001a\u00020>X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bG\u0010CR\u0012\u0010H\u001a\u000202X¤\u0004¢\u0006\u0006\u001a\u0004\bI\u00104R\u0018\u0010J\u001a\u0004\u0018\u00010>X\u0094\u0004¢\u0006\n\n\u0002\u0010M\u001a\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020*X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bO\u0010,R\u0014\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0014\u0010\n\u001a\u00020\u000bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020UX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0016\u0010X\u001a\u0004\u0018\u00010YX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u0004\u0018\u00010YX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010[R\u0014\u0010^\u001a\u00020YX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010[R/\u0010`\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010aX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010cR\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020*X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bg\u0010,R\u0012\u0010h\u001a\u000202X¤\u0004¢\u0006\u0006\u001a\u0004\bi\u00104R\u0014\u0010j\u001a\u00020>X\u0094D¢\u0006\b\n\u0000\u001a\u0004\bk\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006y"}, d2 = {"Lcom/amazon/tarazed/dialog/AbstractDialog;", "", "context", "Landroid/content/Context;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "alertDialogBuilderProvider", "Lcom/amazon/tarazed/dialog/AlertDialogBuilderProvider;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lcom/amazon/tarazed/dialog/AlertDialogBuilderProvider;)V", "activitySubscription", "Lkotlin/Function3;", "Landroid/app/Activity;", "Lcom/amazon/tarazed/activity/ActivityLifecycleAction;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function3;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "kotlin.jvm.PlatformType", "activityTracker$annotations", "()V", "getActivityTracker$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/activity/ActivityTracker;", "alertDialog", "Landroid/app/AlertDialog;", "alertDialog$annotations", "getAlertDialog$TarazedAndroidLibrary_release", "()Landroid/app/AlertDialog;", "setAlertDialog$TarazedAndroidLibrary_release", "(Landroid/app/AlertDialog;)V", "getBizMetricsHelper", "()Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "canceledOnTouchOutside", "", "getCanceledOnTouchOutside", "()Z", "getContext", "()Landroid/content/Context;", "getDeviceInfoUtility", "()Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "dialogAcceptedMetric", "", "getDialogAcceptedMetric", "()Ljava/lang/String;", "dialogAutoDismissMs", "", "getDialogAutoDismissMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "dialogDeclinedOrCanceledMetric", "getDialogDeclinedOrCanceledMetric", "dialogMessage", "", "", "getDialogMessage", "()Ljava/util/List;", "dialogNegativeButtonLabel", "getDialogNegativeButtonLabel", "()I", "dialogNeutralButtonLabel", "getDialogNeutralButtonLabel", "dialogPositiveButtonLabel", "getDialogPositiveButtonLabel", "dialogShownMetric", "getDialogShownMetric", "dialogTitle", "getDialogTitle", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "forcedSystemDialog", "getForcedSystemDialog", "getLogger", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getMetricsHelper", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "onKeyListener", "Landroid/content/DialogInterface$OnKeyListener;", "getOnKeyListener", "()Landroid/content/DialogInterface$OnKeyListener;", "onNegativeButtonClickedListener", "Landroid/content/DialogInterface$OnClickListener;", "getOnNegativeButtonClickedListener", "()Landroid/content/DialogInterface$OnClickListener;", "onNeutralButtonClickedListener", "getOnNeutralButtonClickedListener", "onPositiveButtonClickedListener", "getOnPositiveButtonClickedListener", "sessionEndListener", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/jvm/functions/Function2;", "getSessionNotifier", "()Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "shouldDismissOnSessionEnd", "getShouldDismissOnSessionEnd", "tag", "getTag", "windowType", "getWindowType", "buildDialog", "finish", "Lkotlinx/coroutines/Job;", "onAcceptClick", "onDeclineClickOrCancel", "cause", "sendDialogResponseBizMetrics", "dialogResponse", "Lcom/amazon/tarazed/core/type/Response;", "dialogTypeEventName", "show", "showInternal", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AbstractDialog {
    public static final Companion Companion = new Companion(null);
    public static final int EMPTY_RESOURCE_ID = 0;
    private static final String METRIC_DIALOG_DESTROYED_ACTIVITY = "DialogDestroyedActivity";
    private static final String TAG = "AbstractDialog";
    private final Function3<Activity, ActivityLifecycleAction, Continuation<? super Unit>, Object> activitySubscription;
    private final ActivityTracker activityTracker;
    @Nullable
    private AlertDialog alertDialog;
    private final AlertDialogBuilderProvider alertDialogBuilderProvider;
    @NotNull
    private final BizMetricsHelper bizMetricsHelper;
    private final boolean canceledOnTouchOutside;
    @NotNull
    private final Context context;
    @NotNull
    private final DeviceInfoUtility deviceInfoUtility;
    @Nullable
    private final Long dialogAutoDismissMs;
    private final int dialogNegativeButtonLabel;
    private final int dialogNeutralButtonLabel;
    private final int dialogPositiveButtonLabel;
    @Nullable
    private final Integer dialogTitle;
    private final boolean forcedSystemDialog;
    @NotNull
    private final TarazedSessionLogger logger;
    @NotNull
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final DialogInterface.OnKeyListener onKeyListener;
    @Nullable
    private final DialogInterface.OnClickListener onNegativeButtonClickedListener;
    @Nullable
    private final DialogInterface.OnClickListener onNeutralButtonClickedListener;
    @NotNull
    private final DialogInterface.OnClickListener onPositiveButtonClickedListener;
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionEndListener;
    @NotNull
    private final TarazedSessionNotifier sessionNotifier;
    private final boolean shouldDismissOnSessionEnd;
    private final int windowType;

    /* compiled from: AbstractDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/dialog/AbstractDialog$Companion;", "", "()V", "EMPTY_RESOURCE_ID", "", "METRIC_DIALOG_DESTROYED_ACTIVITY", "", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
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
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityLifecycleAction.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ActivityLifecycleAction.PAUSE.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityLifecycleAction.RESUME.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityLifecycleAction.INITIAL.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[Response.values().length];
            $EnumSwitchMapping$1[Response.ACCEPTED.ordinal()] = 1;
            $EnumSwitchMapping$1[Response.DENIED.ordinal()] = 2;
            $EnumSwitchMapping$1[Response.TIMEOUT.ordinal()] = 3;
        }
    }

    public AbstractDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull AlertDialogBuilderProvider alertDialogBuilderProvider) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(alertDialogBuilderProvider, "alertDialogBuilderProvider");
        this.context = context;
        this.deviceInfoUtility = deviceInfoUtility;
        this.sessionNotifier = sessionNotifier;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.bizMetricsHelper = bizMetricsHelper;
        this.alertDialogBuilderProvider = alertDialogBuilderProvider;
        this.activityTracker = LibraryInjector.getComponent().activityTracker();
        this.activitySubscription = new AbstractDialog$activitySubscription$1(this, null);
        this.sessionEndListener = new AbstractDialog$sessionEndListener$1(this, null);
        TarazedSessionNotifier.subscribe$default(this.sessionNotifier, this.sessionEndListener, ListenerPriority.MEDIUM, false, 4, null);
        this.shouldDismissOnSessionEnd = true;
        this.onPositiveButtonClickedListener = new DialogInterface.OnClickListener() { // from class: com.amazon.tarazed.dialog.AbstractDialog$onPositiveButtonClickedListener$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AbstractDialog.this.getLogger().i(AbstractDialog.this.getTag(), "buildDialog - User clicked the button");
                AbstractDialog.this.getMetricsHelper().addCount(AbstractDialog.this.getTag(), AbstractDialog.this.getDialogAcceptedMetric(), 1.0d);
                AbstractDialog.this.finish();
                AbstractDialog.this.onAcceptClick();
            }
        };
        this.onKeyListener = AbstractDialog$onKeyListener$1.INSTANCE;
        this.windowType = 1003;
    }

    @VisibleForTesting
    public static /* synthetic */ void activityTracker$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void alertDialog$annotations() {
    }

    private final void buildDialog() {
        Context currentActivity;
        if (this.deviceInfoUtility.is1PDevice()) {
            currentActivity = this.context;
        } else {
            currentActivity = this.activityTracker.getCurrentActivity();
            if (currentActivity == null) {
                Intrinsics.throwNpe();
            }
        }
        AlertDialogBuilder provideDialogBuilder = this.alertDialogBuilderProvider.provideDialogBuilder(currentActivity);
        provideDialogBuilder.setPositiveButton(getDialogPositiveButtonLabel(), getOnPositiveButtonClickedListener()).setNegativeButton(getDialogNegativeButtonLabel(), getOnNegativeButtonClickedListener()).setNeutralButton(getDialogNeutralButtonLabel(), getOnNeutralButtonClickedListener()).setOnKeyListener(getOnKeyListener()).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.tarazed.dialog.AbstractDialog$buildDialog$1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                AbstractDialog.this.getLogger().i(AbstractDialog.this.getTag(), "buildDialog - User cancelled dialog");
                AbstractDialog.this.getMetricsHelper().addCount(AbstractDialog.this.getTag(), AbstractDialog.this.getDialogDeclinedOrCanceledMetric(), 1.0d);
                AbstractDialog.this.finish();
                AbstractDialog abstractDialog = AbstractDialog.this;
                abstractDialog.onDeclineClickOrCancel(abstractDialog.getDialogDeclinedOrCanceledMetric());
            }
        }).addMessage(getDialogMessage()).setDialogAutoDismissMs(getDialogAutoDismissMs()).setForceSystem(getForcedSystemDialog()).setWindowType(getWindowType()).setSelectPositiveButton(true).setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        if (this.deviceInfoUtility.is1PDevice()) {
            provideDialogBuilder.setTitle(getDialogTitle());
        }
        if (!this.deviceInfoUtility.is1PDevice()) {
            provideDialogBuilder.setDialogWidth(null);
        }
        if (this.deviceInfoUtility.is1PDevice()) {
            provideDialogBuilder.setSelectPositiveButton(true);
        } else {
            provideDialogBuilder.setSelectPositiveButton(false);
        }
        AlertDialog create = provideDialogBuilder.create();
        this.logger.i(getTag(), "alert dialog created");
        if (getDialogTitle() == null) {
            create.requestWindowFeature(1);
        }
        this.alertDialog = create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInternal() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        buildDialog();
        AlertDialog alertDialog2 = this.alertDialog;
        if (alertDialog2 != null) {
            alertDialog2.show();
        }
        this.metricsHelper.addCount(getTag(), getDialogShownMetric(), 1.0d);
    }

    @NotNull
    public Job finish() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AbstractDialog$finish$1(this, null), 2, null);
        return launch$default;
    }

    public final ActivityTracker getActivityTracker$TarazedAndroidLibrary_release() {
        return this.activityTracker;
    }

    @Nullable
    public final AlertDialog getAlertDialog$TarazedAndroidLibrary_release() {
        return this.alertDialog;
    }

    @NotNull
    protected final BizMetricsHelper getBizMetricsHelper() {
        return this.bizMetricsHelper;
    }

    protected boolean getCanceledOnTouchOutside() {
        return this.canceledOnTouchOutside;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final DeviceInfoUtility getDeviceInfoUtility() {
        return this.deviceInfoUtility;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract String getDialogAcceptedMetric();

    @Nullable
    protected Long getDialogAutoDismissMs() {
        return this.dialogAutoDismissMs;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract String getDialogDeclinedOrCanceledMetric();

    @NotNull
    protected abstract List<Integer> getDialogMessage();

    protected int getDialogNegativeButtonLabel() {
        return this.dialogNegativeButtonLabel;
    }

    protected int getDialogNeutralButtonLabel() {
        return this.dialogNeutralButtonLabel;
    }

    protected int getDialogPositiveButtonLabel() {
        return this.dialogPositiveButtonLabel;
    }

    @NotNull
    protected abstract String getDialogShownMetric();

    @Nullable
    protected Integer getDialogTitle() {
        return this.dialogTitle;
    }

    protected boolean getForcedSystemDialog() {
        return this.forcedSystemDialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final TarazedSessionLogger getLogger() {
        return this.logger;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final TarazedMetricsHelper getMetricsHelper() {
        return this.metricsHelper;
    }

    @NotNull
    protected DialogInterface.OnKeyListener getOnKeyListener() {
        return this.onKeyListener;
    }

    @Nullable
    protected DialogInterface.OnClickListener getOnNegativeButtonClickedListener() {
        return this.onNegativeButtonClickedListener;
    }

    @Nullable
    protected DialogInterface.OnClickListener getOnNeutralButtonClickedListener() {
        return this.onNeutralButtonClickedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public DialogInterface.OnClickListener getOnPositiveButtonClickedListener() {
        return this.onPositiveButtonClickedListener;
    }

    @NotNull
    protected final TarazedSessionNotifier getSessionNotifier() {
        return this.sessionNotifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getShouldDismissOnSessionEnd() {
        return this.shouldDismissOnSessionEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract String getTag();

    protected int getWindowType() {
        return this.windowType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onAcceptClick();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onDeclineClickOrCancel(@NotNull String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sendDialogResponseBizMetrics(@NotNull Response dialogResponse, @NotNull String dialogTypeEventName) {
        String str;
        Intrinsics.checkParameterIsNotNull(dialogResponse, "dialogResponse");
        Intrinsics.checkParameterIsNotNull(dialogTypeEventName, "dialogTypeEventName");
        HashMap hashMap = new HashMap();
        int i = WhenMappings.$EnumSwitchMapping$1[dialogResponse.ordinal()];
        if (i == 1) {
            str = BizMetricsConstants.ACCEPTED_BY_CUSTOMER_TRUE;
        } else if (i == 2) {
            str = "denied";
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        } else {
            str = "timeout";
        }
        hashMap.put(BizMetricsConstants.ACCEPTED_BY_CUSTOMER_METADATA_NAME, str);
        this.bizMetricsHelper.publishBizMetric(dialogTypeEventName, hashMap);
    }

    public final void setAlertDialog$TarazedAndroidLibrary_release(@Nullable AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    @NotNull
    public Job show() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AbstractDialog$show$1(this, null), 2, null);
        return launch$default;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AbstractDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        this(context, deviceInfoUtility, sessionNotifier, logger, metricsHelper, bizMetricsHelper, new DefaultAlertDialogBuilderProvider(logger, deviceInfoUtility));
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
    }
}
