package com.amazon.tarazed.session.dialog;

import android.content.Context;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionEndedUnknownDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/session/dialog/SessionEndedUnknownDialog;", "Lcom/amazon/tarazed/session/dialog/SessionEndedDialog;", "context", "Landroid/content/Context;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "dialogMessage", "", "", "getDialogMessage", "()Ljava/util/List;", "tag", "", "getTag", "()Ljava/lang/String;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SessionEndedUnknownDialog extends SessionEndedDialog {
    @NotNull
    private final List<Integer> dialogMessage;
    @NotNull
    private final String tag;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionEndedUnknownDialog(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        super(context, deviceInfoUtility, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        List<Integer> listOf;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.tag = "EndedUnknownDialog";
        listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(R.string.dialog_ended_error));
        this.dialogMessage = listOf;
    }

    @Override // com.amazon.tarazed.session.dialog.SessionEndedDialog, com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    protected List<Integer> getDialogMessage() {
        return this.dialogMessage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.tarazed.session.dialog.SessionEndedDialog, com.amazon.tarazed.dialog.AbstractDialog
    @NotNull
    public String getTag() {
        return this.tag;
    }
}
