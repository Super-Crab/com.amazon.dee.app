package com.amazon.tarazed.session.dialog;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.session.dialog.SessionDialog;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dialog.AbstractDialog;
import com.amazon.tarazed.ui.WindowParamsHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionEndedDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 E2\u00020\u00012\u00020\u0002:\u0001EB5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010A\u001a\u00020BH\u0017J\u0010\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020\u0015H\u0017R\u0014\u0010\u0010\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u00020\u00198\u0016X\u0097D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u0015X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001c\u0010%\u001a\u00020\"8\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001b\u001a\u0004\b'\u0010(R\u001c\u0010)\u001a\u00020\"8\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u001b\u001a\u0004\b+\u0010(R\u0014\u0010,\u001a\u00020\u0015X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0017R\u0014\u0010.\u001a\u00020\"X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0013R\u001c\u00103\u001a\u0002048\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001b\u001a\u0004\b6\u00107R\u001c\u00108\u001a\u0002048\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001b\u001a\u0004\b:\u00107R\u0014\u0010;\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0013R\u0014\u0010=\u001a\u00020\u0015X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0017R\u0014\u0010?\u001a\u00020\"X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010(¨\u0006F"}, d2 = {"Lcom/amazon/tarazed/session/dialog/SessionEndedDialog;", "Lcom/amazon/tarazed/dialog/AbstractDialog;", "Lcom/amazon/tarazed/core/session/dialog/SessionDialog;", "context", "Landroid/content/Context;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "canceledOnTouchOutside", "", "getCanceledOnTouchOutside", "()Z", "dialogAcceptedMetric", "", "getDialogAcceptedMetric", "()Ljava/lang/String;", "dialogAutoDismissMs", "", "dialogAutoDismissMs$annotations", "()V", "getDialogAutoDismissMs", "()Ljava/lang/Long;", "dialogDeclinedOrCanceledMetric", "getDialogDeclinedOrCanceledMetric", "dialogMessage", "", "", "getDialogMessage", "()Ljava/util/List;", "dialogNeutralButtonLabel", "dialogNeutralButtonLabel$annotations", "getDialogNeutralButtonLabel", "()I", "dialogPositiveButtonLabel", "dialogPositiveButtonLabel$annotations", "getDialogPositiveButtonLabel", "dialogShownMetric", "getDialogShownMetric", "dialogTitle", "getDialogTitle", "()Ljava/lang/Integer;", "forcedSystemDialog", "getForcedSystemDialog", "onNeutralButtonClickedListener", "Landroid/content/DialogInterface$OnClickListener;", "onNeutralButtonClickedListener$annotations", "getOnNeutralButtonClickedListener", "()Landroid/content/DialogInterface$OnClickListener;", "onPositiveButtonClickedListener", "onPositiveButtonClickedListener$annotations", "getOnPositiveButtonClickedListener", "shouldDismissOnSessionEnd", "getShouldDismissOnSessionEnd", "tag", "getTag", "windowType", "getWindowType", "onAcceptClick", "", "onDeclineClickOrCancel", "cause", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class SessionEndedDialog extends AbstractDialog implements SessionDialog {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final long DIALOG_AUTO_DISMISS_MS = 30000;
    private static final String METRIC_CALL_END_CUSTOMER_ACKNOWLEDGES = "CallEndCustomerAcknowledges";
    private static final String METRIC_CALL_END_DIALOG_SHOWN = "CallEndDialogShown";
    private final boolean canceledOnTouchOutside;
    @NotNull
    private final String dialogAcceptedMetric;
    private final long dialogAutoDismissMs;
    @NotNull
    private final String dialogDeclinedOrCanceledMetric;
    @NotNull
    private final List<Integer> dialogMessage;
    private final int dialogNeutralButtonLabel;
    private final int dialogPositiveButtonLabel;
    @NotNull
    private final String dialogShownMetric;
    private final int dialogTitle;
    private final boolean forcedSystemDialog;
    @NotNull
    private final DialogInterface.OnClickListener onNeutralButtonClickedListener;
    @NotNull
    private final DialogInterface.OnClickListener onPositiveButtonClickedListener;
    private final boolean shouldDismissOnSessionEnd;
    @NotNull
    private final String tag;
    private final int windowType;

    /* compiled from: SessionEndedDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/session/dialog/SessionEndedDialog$Companion;", "", "()V", "DIALOG_AUTO_DISMISS_MS", "", "METRIC_CALL_END_CUSTOMER_ACKNOWLEDGES", "", "METRIC_CALL_END_DIALOG_SHOWN", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionEndedDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        super(context, deviceInfoUtility, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        List<Integer> listOf;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.tag = "SessionEndedDialog";
        listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(R.string.live_assist_session_ended_message));
        this.dialogMessage = listOf;
        this.dialogShownMetric = METRIC_CALL_END_DIALOG_SHOWN;
        this.dialogAcceptedMetric = METRIC_CALL_END_CUSTOMER_ACKNOWLEDGES;
        this.dialogDeclinedOrCanceledMetric = METRIC_CALL_END_CUSTOMER_ACKNOWLEDGES;
        this.canceledOnTouchOutside = true;
        this.dialogTitle = R.string.screen_sharing_ended;
        this.forcedSystemDialog = true;
        this.windowType = WindowParamsHelper.INSTANCE.getWindowType(deviceInfoUtility);
        this.onPositiveButtonClickedListener = super.getOnPositiveButtonClickedListener();
        this.onNeutralButtonClickedListener = getOnPositiveButtonClickedListener();
        this.dialogAutoDismissMs = 30000L;
        int i = 0;
        this.dialogPositiveButtonLabel = deviceInfoUtility.isFireTV() ? 0 : R.string.dialog_close;
        this.dialogNeutralButtonLabel = deviceInfoUtility.isFireTV() ? R.string.dialog_close : i;
    }

    @VisibleForTesting
    public static /* synthetic */ void dialogAutoDismissMs$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void dialogNeutralButtonLabel$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void dialogPositiveButtonLabel$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void onNeutralButtonClickedListener$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void onPositiveButtonClickedListener$annotations() {
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    protected boolean getCanceledOnTouchOutside() {
        return this.canceledOnTouchOutside;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getDialogAcceptedMetric() {
        return this.dialogAcceptedMetric;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public Long getDialogAutoDismissMs() {
        return Long.valueOf(this.dialogAutoDismissMs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getDialogDeclinedOrCanceledMetric() {
        return this.dialogDeclinedOrCanceledMetric;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected List<Integer> getDialogMessage() {
        return this.dialogMessage;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    public int getDialogNeutralButtonLabel() {
        return this.dialogNeutralButtonLabel;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    public int getDialogPositiveButtonLabel() {
        return this.dialogPositiveButtonLabel;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected String getDialogShownMetric() {
        return this.dialogShownMetric;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected Integer getDialogTitle() {
        return Integer.valueOf(this.dialogTitle);
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    protected boolean getForcedSystemDialog() {
        return this.forcedSystemDialog;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public DialogInterface.OnClickListener getOnNeutralButtonClickedListener() {
        return this.onNeutralButtonClickedListener;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public DialogInterface.OnClickListener getOnPositiveButtonClickedListener() {
        return this.onPositiveButtonClickedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    public boolean getShouldDismissOnSessionEnd() {
        return this.shouldDismissOnSessionEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getTag() {
        return this.tag;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    protected int getWindowType() {
        return this.windowType;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @VisibleForTesting
    public void onAcceptClick() {
        getLogger().i(getTag(), "Customer dismissed dialog");
        getActivityTracker$TarazedAndroidLibrary_release().unregisterLifecycleCallbacks(getContext());
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @VisibleForTesting
    public void onDeclineClickOrCancel(@NotNull String cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        TarazedSessionLogger logger = getLogger();
        String tag = getTag();
        logger.i(tag, "Customer dismissed dialog: " + cause);
        getActivityTracker$TarazedAndroidLibrary_release().unregisterLifecycleCallbacks(getContext());
    }
}
