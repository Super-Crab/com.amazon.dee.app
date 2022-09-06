package com.amazon.tarazed.dialog;

import android.content.Context;
import android.content.DialogInterface;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractAskPermissionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u0000  2\u00020\u0001:\u0001 B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\u0006\u0010\u001f\u001a\u00020\u001aR\u0012\u0010\u000f\u001a\u00020\u0010X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/tarazed/dialog/AbstractAskPermissionDialog;", "Lcom/amazon/tarazed/dialog/AbstractDialog;", "context", "Landroid/content/Context;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "dialogExpiredMetric", "", "getDialogExpiredMetric", "()Ljava/lang/String;", "onNegativeButtonClickedListener", "Landroid/content/DialogInterface$OnClickListener;", "getOnNegativeButtonClickedListener", "()Landroid/content/DialogInterface$OnClickListener;", "permissionRequestTimer", "Lkotlinx/coroutines/Job;", "cancelDialogTimer", "", "customerDeclinesPermissionDialog", "cause", "finish", "initDialogTimer", "start", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AbstractAskPermissionDialog extends AbstractDialog {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final long PERMISSION_REQUEST_TIMEOUT_MS = 60000;
    @NotNull
    private final DialogInterface.OnClickListener onNegativeButtonClickedListener;
    private Job permissionRequestTimer;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractAskPermissionDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/dialog/AbstractAskPermissionDialog$Companion;", "", "()V", "PERMISSION_REQUEST_TIMEOUT_MS", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAskPermissionDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        super(context, deviceInfoUtility, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new AbstractAskPermissionDialog$permissionRequestTimer$1(null), 1, null);
        this.permissionRequestTimer = launch$default;
        this.onNegativeButtonClickedListener = new DialogInterface.OnClickListener() { // from class: com.amazon.tarazed.dialog.AbstractAskPermissionDialog$onNegativeButtonClickedListener$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AbstractAskPermissionDialog abstractAskPermissionDialog = AbstractAskPermissionDialog.this;
                abstractAskPermissionDialog.customerDeclinesPermissionDialog(abstractAskPermissionDialog.getDialogDeclinedOrCanceledMetric());
            }
        };
    }

    private final void cancelDialogTimer() {
        if (this.permissionRequestTimer.isActive()) {
            getLogger().i(getTag(), "onPermission - cancel permissionRequestTimer");
            Job.DefaultImpls.cancel$default(this.permissionRequestTimer, (CancellationException) null, 1, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void customerDeclinesPermissionDialog(String str) {
        getMetricsHelper().addCount(getTag(), getDialogDeclinedOrCanceledMetric(), 1.0d);
        finish();
        onDeclineClickOrCancel(str);
    }

    private final Job initDialogTimer() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AbstractAskPermissionDialog$initDialogTimer$1(this, null), 3, null);
        return launch$default;
    }

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public Job finish() {
        cancelDialogTimer();
        return super.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract String getDialogExpiredMetric();

    @Override // com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected DialogInterface.OnClickListener getOnNegativeButtonClickedListener() {
        return this.onNegativeButtonClickedListener;
    }

    public final void start() {
        this.permissionRequestTimer = initDialogTimer();
    }
}
