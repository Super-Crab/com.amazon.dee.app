package com.amazon.tarazed.session.dialog;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog;
import com.amazon.tarazed.receiver.PrimerResultReceiver;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimerPermissionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 :2\u00020\u0001:\u0001:B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u00102\u001a\u00020\u0015H\u0002J\b\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u000200H\u0016J\b\u00106\u001a\u00020\u0015H\u0002J\b\u00107\u001a\u000200H\u0016J\b\u00108\u001a\u00020\u0015H\u0002J\b\u00109\u001a\u00020\u0015H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R=\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00128\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u001b\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R \u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u00020+8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0018\u001a\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, d2 = {"Lcom/amazon/tarazed/session/dialog/PrimerPermissionDialog;", "Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "context", "Landroid/content/Context;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/arcus/ArcusHelper;)V", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "", "notificationHandler$annotations", "()V", "getNotificationHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "onAcceptSessionCallback", "Lkotlin/Function0;", "getOnAcceptSessionCallback", "()Lkotlin/jvm/functions/Function0;", "setOnAcceptSessionCallback", "(Lkotlin/jvm/functions/Function0;)V", "onDeclineSessionCallback", "getOnDeclineSessionCallback", "setOnDeclineSessionCallback", "onTimeoutCallback", "getOnTimeoutCallback", "setOnTimeoutCallback", "primerShown", "", "primerTimeoutMS", "", "primerTimeoutMS$annotations", "getPrimerTimeoutMS$TarazedAndroidLibrary_release", "()J", "primerTimer", "Lkotlinx/coroutines/Job;", "startPrimerTimeStamp", "closePrimer", "createResultReceiver", "Lcom/amazon/tarazed/receiver/PrimerResultReceiver;", "finish", "sendPrimerTimeSpentBizMetric", "show", "showPrimer", "start", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PrimerPermissionDialog implements SessionAskPermissionDialog {
    public static final Companion Companion = new Companion(null);
    private static final String METRICS_PRIMER_TIMED_OUT = "PrimerTimedOut";
    private static final long PRIMER_FAILURE_TO_LOAD_TIMEOUT_MS = 5000;
    private static final String TAG = "PrimerPermDialog";
    @NotNull
    public static Job primerFailureToLoadBizMetricTimer;
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DispatcherProvider dispatchers;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    @NotNull
    public Function0<Unit> onAcceptSessionCallback;
    @NotNull
    public Function0<Unit> onDeclineSessionCallback;
    @NotNull
    public Function0<Unit> onTimeoutCallback;
    private boolean primerShown;
    private final long primerTimeoutMS;
    private Job primerTimer;
    private long startPrimerTimeStamp;

    /* compiled from: PrimerPermissionDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/session/dialog/PrimerPermissionDialog$Companion;", "", "()V", "METRICS_PRIMER_TIMED_OUT", "", "PRIMER_FAILURE_TO_LOAD_TIMEOUT_MS", "", "TAG", "primerFailureToLoadBizMetricTimer", "Lkotlinx/coroutines/Job;", "getPrimerFailureToLoadBizMetricTimer$TarazedAndroidLibrary_release", "()Lkotlinx/coroutines/Job;", "setPrimerFailureToLoadBizMetricTimer$TarazedAndroidLibrary_release", "(Lkotlinx/coroutines/Job;)V", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Job getPrimerFailureToLoadBizMetricTimer$TarazedAndroidLibrary_release() {
            Job job = PrimerPermissionDialog.primerFailureToLoadBizMetricTimer;
            if (job == null) {
                Intrinsics.throwUninitializedPropertyAccessException("primerFailureToLoadBizMetricTimer");
            }
            return job;
        }

        public final void setPrimerFailureToLoadBizMetricTimer$TarazedAndroidLibrary_release(@NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "<set-?>");
            PrimerPermissionDialog.primerFailureToLoadBizMetricTimer = job;
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
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_ACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_SUSPENDED.ordinal()] = 3;
        }
    }

    public PrimerPermissionDialog(@NotNull Context context, @NotNull TarazedSessionNotifier notifier, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull TarazedMetricsHelper metricsHelper, @NotNull ArcusHelper arcusHelper) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        this.context = context;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.bizMetricsHelper = bizMetricsHelper;
        this.metricsHelper = metricsHelper;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, CoroutineStart.LAZY, new PrimerPermissionDialog$primerTimer$1(null), 1, null);
        this.primerTimer = launch$default;
        this.primerTimeoutMS = arcusHelper.getPrimerTimeOutSec() * 1000;
        this.notificationHandler = new PrimerPermissionDialog$notificationHandler$1(this, null);
        TarazedSessionNotifier.subscribe$default(notifier, this.notificationHandler, ListenerPriority.HIGH, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closePrimer() {
        this.context.sendBroadcast(new Intent(TarazedPrimerActivity.ACTION_CLOSE_PRIMER));
    }

    private final PrimerResultReceiver createResultReceiver() {
        return new PrimerResultReceiver(new PrimerPermissionDialog$createResultReceiver$onAcceptCallback$1(this), new PrimerPermissionDialog$createResultReceiver$onDeclineCallback$1(this));
    }

    @VisibleForTesting
    public static /* synthetic */ void notificationHandler$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void primerTimeoutMS$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPrimerTimeSpentBizMetric() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        hashMap.put(BizMetricsConstants.DURATION_METADATA_NAME, String.valueOf((currentTimeMillis - this.startPrimerTimeStamp) / 1000));
        this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.TIME_SPENT_PRIMER_DECISION_EVENT_NAME, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPrimer() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new PrimerPermissionDialog$showPrimer$1(this, null), 2, null);
        primerFailureToLoadBizMetricTimer = launch$default;
        Intent intent = new Intent(this.context, TarazedPrimerActivity.class);
        intent.putExtra(TarazedPrimerActivity.EXTRA_KEY_PRIMER_RESULT_RECEIVER, createResultReceiver());
        intent.addFlags(268435456);
        try {
            this.context.startActivity(intent);
        } catch (Exception unused) {
            BizMetricsHelper.publishBizMetric$default(this.bizMetricsHelper, BizMetricsConstants.PRIMER_CRASHED_EVENT_NAME, null, 2, null);
            this.metricsHelper.addCountHighPriority(TAG, TarazedPrimerActivity.METRICS_PRIMER_CRASHED, 1.0d);
        }
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    @NotNull
    public Job finish() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new PrimerPermissionDialog$finish$1(this, null), 2, null);
        return launch$default;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getNotificationHandler$TarazedAndroidLibrary_release() {
        return this.notificationHandler;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    @NotNull
    public Function0<Unit> getOnAcceptSessionCallback() {
        Function0<Unit> function0 = this.onAcceptSessionCallback;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onAcceptSessionCallback");
        }
        return function0;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    @NotNull
    public Function0<Unit> getOnDeclineSessionCallback() {
        Function0<Unit> function0 = this.onDeclineSessionCallback;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onDeclineSessionCallback");
        }
        return function0;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    @NotNull
    public Function0<Unit> getOnTimeoutCallback() {
        Function0<Unit> function0 = this.onTimeoutCallback;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onTimeoutCallback");
        }
        return function0;
    }

    public final long getPrimerTimeoutMS$TarazedAndroidLibrary_release() {
        return this.primerTimeoutMS;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    public void setOnAcceptSessionCallback(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onAcceptSessionCallback = function0;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    public void setOnDeclineSessionCallback(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onDeclineSessionCallback = function0;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    public void setOnTimeoutCallback(@NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onTimeoutCallback = function0;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    @NotNull
    public Job show() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new PrimerPermissionDialog$show$1(this, null), 2, null);
        return launch$default;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog
    public void start() {
        Job launch$default;
        this.startPrimerTimeStamp = System.currentTimeMillis();
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new PrimerPermissionDialog$start$1(this, null), 2, null);
        this.primerTimer = launch$default;
    }
}
