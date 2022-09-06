package com.amazon.deecomms.calling.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
/* loaded from: classes12.dex */
public final class DialogUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DialogUtils.class);

    private DialogUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showAnnouncementDeviceDialog$0(ApplicationManager applicationManager, DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_ANNOUNCE_DIALOG_DEVICE_SETTINGS_OPEN);
        dialogInterface.dismiss();
        applicationManager.navigateSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showAnnouncementDeviceDialog$1(DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_ANNOUNCE_DIALOG_DEVICE_SETTINGS_CANCEL);
        dialogInterface.dismiss();
    }

    public static void showAnnouncementDeviceDialog(@Nullable Activity activity, @NonNull final ApplicationManager applicationManager) {
        if (activity != null && !activity.isFinishing()) {
            new AlertDialog.Builder(activity).setMessage(R.string.announcement_device_settings_dialog).setPositiveButton(R.string.announcement_device_settings_dialog_positive_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$DialogUtils$n2jySd3EsFIalvlbhekegbiOhqo
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogUtils.lambda$showAnnouncementDeviceDialog$0(ApplicationManager.this, dialogInterface, i);
                }
            }).setNegativeButton(R.string.announcement_settings_dialog_negative_response, $$Lambda$DialogUtils$ewNe0fES_dvtQrNARwpfdBJXfxY.INSTANCE).show();
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_ANNOUNCE_DIALOG_DEVICE_SETTINGS_SHOW);
            return;
        }
        LOG.w("Missing activity, aborting announcement device dialog");
    }

    public static void showOfflineDialog(@Nullable Activity activity, String str, @NonNull AlertSource alertSource) {
        if (activity != null && !activity.isFinishing()) {
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_OFFLINE, str, alertSource);
            Utils.showDialog(activity, R.string.offline_title, R.string.offline_message);
            return;
        }
        LOG.w("Missing activity, aborting offline dialog");
    }
}
