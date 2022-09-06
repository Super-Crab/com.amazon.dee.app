package com.amazon.tarazed.session.dialog;

import android.content.Context;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dialog.AbstractAskPermissionDialog;
import com.amazon.tarazed.ui.WindowParamsHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ConfirmEndSessionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0001?B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010<\u001a\u00020-H\u0014J\u0010\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020\u0011H\u0014R\u0014\u0010\u0010\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001aX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001aX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0013R\u0014\u0010$\u001a\u00020\u001aX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R \u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R \u00102\u001a\b\u0012\u0004\u0012\u00020-0,X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R \u00105\u001a\b\u0012\u0004\u0012\u00020-0,X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010/\"\u0004\b7\u00101R\u0014\u00108\u001a\u00020\u0011X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0013R\u0014\u0010:\u001a\u00020\u001aX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u001f¨\u0006@"}, d2 = {"Lcom/amazon/tarazed/session/dialog/ConfirmEndSessionDialog;", "Lcom/amazon/tarazed/dialog/AbstractAskPermissionDialog;", "Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "context", "Landroid/content/Context;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "dialogAcceptedMetric", "", "getDialogAcceptedMetric", "()Ljava/lang/String;", "dialogDeclinedOrCanceledMetric", "getDialogDeclinedOrCanceledMetric", "dialogExpiredMetric", "getDialogExpiredMetric", "dialogMessage", "", "", "getDialogMessage", "()Ljava/util/List;", "dialogNegativeButtonLabel", "getDialogNegativeButtonLabel", "()I", "dialogPositiveButtonLabel", "getDialogPositiveButtonLabel", "dialogShownMetric", "getDialogShownMetric", "dialogTitle", "getDialogTitle", "()Ljava/lang/Integer;", "forcedSystemDialog", "", "getForcedSystemDialog", "()Z", "onAcceptSessionCallback", "Lkotlin/Function0;", "", "getOnAcceptSessionCallback", "()Lkotlin/jvm/functions/Function0;", "setOnAcceptSessionCallback", "(Lkotlin/jvm/functions/Function0;)V", "onDeclineSessionCallback", "getOnDeclineSessionCallback", "setOnDeclineSessionCallback", "onTimeoutCallback", "getOnTimeoutCallback", "setOnTimeoutCallback", "tag", "getTag", "windowType", "getWindowType", "onAcceptClick", "onDeclineClickOrCancel", "cause", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ConfirmEndSessionDialog extends AbstractAskPermissionDialog implements SessionAskPermissionDialog {
    private static final String CONFIRM_END_SESSION_CANCELED = "CONFIRM_END_SESSION_CANCELED";
    private static final String CONFIRM_END_SESSION_CONFIRMED = "CONFIRM_END_SESSION_CONFIRMED";
    private static final String CONFIRM_END_SESSION_DIALOGUE_SHOWN = "CONFIRM_END_SESSION_DIALOGUE_SHOWN";
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String PERMISSION_TIMEOUT = "START_SESSION_DEVICE_PERMISSION_TIMEOUT";
    @NotNull
    private final String dialogAcceptedMetric;
    @NotNull
    private final String dialogDeclinedOrCanceledMetric;
    @NotNull
    private final String dialogExpiredMetric;
    @NotNull
    private final List<Integer> dialogMessage;
    private final int dialogNegativeButtonLabel;
    private final int dialogPositiveButtonLabel;
    @NotNull
    private final String dialogShownMetric;
    private final int dialogTitle;
    private final boolean forcedSystemDialog;
    @NotNull
    public Function0<Unit> onAcceptSessionCallback;
    @NotNull
    public Function0<Unit> onDeclineSessionCallback;
    @NotNull
    public Function0<Unit> onTimeoutCallback;
    @NotNull
    private final String tag;
    private final int windowType;

    /* compiled from: ConfirmEndSessionDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/session/dialog/ConfirmEndSessionDialog$Companion;", "", "()V", ConfirmEndSessionDialog.CONFIRM_END_SESSION_CANCELED, "", ConfirmEndSessionDialog.CONFIRM_END_SESSION_CONFIRMED, ConfirmEndSessionDialog.CONFIRM_END_SESSION_DIALOGUE_SHOWN, "PERMISSION_TIMEOUT", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmEndSessionDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        super(context, deviceInfoUtility, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        List<Integer> listOf;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.dialogTitle = R.string.end_screen_sharing;
        this.tag = "ConfirmEndSessionDialog";
        this.windowType = WindowParamsHelper.INSTANCE.getWindowType(deviceInfoUtility);
        this.forcedSystemDialog = true;
        this.dialogExpiredMetric = PERMISSION_TIMEOUT;
        this.dialogPositiveButtonLabel = R.string.dialog_confirm_end_accept;
        this.dialogNegativeButtonLabel = R.string.dialog_confirm_end_decline;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(R.string.dialog_confirm_end));
        this.dialogMessage = listOf;
        this.dialogShownMetric = CONFIRM_END_SESSION_DIALOGUE_SHOWN;
        this.dialogAcceptedMetric = CONFIRM_END_SESSION_CONFIRMED;
        this.dialogDeclinedOrCanceledMetric = CONFIRM_END_SESSION_CANCELED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getDialogAcceptedMetric() {
        return this.dialogAcceptedMetric;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getDialogDeclinedOrCanceledMetric() {
        return this.dialogDeclinedOrCanceledMetric;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractAskPermissionDialog
    @NotNull
    public String getDialogExpiredMetric() {
        return this.dialogExpiredMetric;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected List<Integer> getDialogMessage() {
        return this.dialogMessage;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    protected int getDialogNegativeButtonLabel() {
        return this.dialogNegativeButtonLabel;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    protected int getDialogPositiveButtonLabel() {
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    public void onAcceptClick() {
        getLogger().d(getTag(), "onAcceptClick - Confirm end session request was accepted");
        getOnAcceptSessionCallback().mo12560invoke();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.dialog.AbstractDialog
    public void onDeclineClickOrCancel(@NotNull String cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        getLogger().d(getTag(), "onDeclineClick - Confirm end session request was declined ");
        getOnDeclineSessionCallback().mo12560invoke();
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
}
