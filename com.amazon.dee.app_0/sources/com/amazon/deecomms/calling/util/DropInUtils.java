package com.amazon.deecomms.calling.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
import java.util.List;
/* loaded from: classes12.dex */
public final class DropInUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DropInUtils.class);

    private DropInUtils() {
    }

    public static boolean hasAnyDropInEnabledDevice(@NonNull List<DeviceModel> list) {
        for (DeviceModel deviceModel : list) {
            if (isDropInEnabledOnDevice(deviceModel)) {
                return true;
            }
        }
        LOG.i("No drop-in enabled devices found");
        return false;
    }

    public static boolean haveAllDevicesCallingAndMessagingOff(@NonNull List<DeviceModel> list) {
        for (DeviceModel deviceModel : list) {
            if (DeviceCommsAvailability.isCommsEnabled(deviceModel.getDeviceStatus().getDeviceCommsAvailability())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDropInEnabledOnDevice(@NonNull DeviceModel deviceModel) {
        DeviceStatusModel deviceStatus = deviceModel.getDeviceStatus();
        return DropInAvailability.isEnabled(deviceStatus.getDeviceDropInAvailability()) && DeviceCommsAvailability.isCommsEnabled(deviceStatus.getDeviceCommsAvailability());
    }

    public static boolean isDropInEnabledProfile(@NonNull String str) {
        return ContactsProviderUtils.canIDropInOnHomeGroup(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showDeviceDialog$2(ApplicationManager applicationManager, DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_DEVICE_SETTINGS_OPEN);
        dialogInterface.dismiss();
        applicationManager.navigateSettings();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showDeviceDialog$3(DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_DEVICE_SETTINGS_CANCEL);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showLearnMoreDialog$0(String str, Fragment fragment, DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_LEARN_MORE_OPEN);
        dialogInterface.dismiss();
        Utils.openUrlInExternalBrowser(str, fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showLearnMoreDialog$1(DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_LEARN_MORE_CANCEL);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showProfileDialog$4(Context context, String str, ApplicationManager applicationManager, DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_PROFILE_SETTINGS_OPEN);
        dialogInterface.dismiss();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CONTACT_ENTRY_KEY, ContactsProviderUtils.fetchContactEntryForCommId(context, str));
        bundle.putBoolean(Constants.BUNDLE_KEY_MY_PROFILE, true);
        applicationManager.navigateToView(CommsView.ReactNativeContactCard, bundle, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showProfileDialog$5(DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_PROFILE_SETTINGS_CANCEL);
        dialogInterface.dismiss();
    }

    public static void showDeviceDialog(@Nullable Activity activity, @NonNull final ApplicationManager applicationManager) {
        if (activity != null && !activity.isFinishing()) {
            new AlertDialog.Builder(activity).setMessage(R.string.drop_in_banner_device_settings_dialog).setPositiveButton(R.string.drop_in_banner_device_settings_dialog_positive_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$DropInUtils$7kqctD_GwL9Dma7R3uEcO5O6Y9A
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DropInUtils.lambda$showDeviceDialog$2(ApplicationManager.this, dialogInterface, i);
                }
            }).setNegativeButton(R.string.drop_in_banner_settings_dialog_negative_response, $$Lambda$DropInUtils$Ut9yf4EDxVZRhoHGVITt1JdkUi4.INSTANCE).show();
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_DEVICE_SETTINGS_SHOW);
            return;
        }
        LOG.w("Missing activity, aborting drop-in banner device dialog");
    }

    public static void showLearnMoreDialog(@Nullable Activity activity, @NonNull final String str, @NonNull final Fragment fragment) {
        if (activity != null && !activity.isFinishing()) {
            new AlertDialog.Builder(activity).setMessage(R.string.drop_in_banner_learn_more_dialog).setPositiveButton(R.string.drop_in_banner_learn_more_dialog_positive_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$DropInUtils$J9XBXlhhyFgbq56MB3j8wzeKP_Y
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DropInUtils.lambda$showLearnMoreDialog$0(str, fragment, dialogInterface, i);
                }
            }).setNegativeButton(R.string.drop_in_banner_settings_dialog_negative_response, $$Lambda$DropInUtils$kzELMqO2NiJP2bKUKJhHpMF7v0A.INSTANCE).show();
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_LEARN_MORE_SHOW);
            return;
        }
        LOG.w("Missing activity, aborting drop-in banner device dialog");
    }

    public static void showProfileDialog(@Nullable Activity activity, @NonNull final Context context, @NonNull final String str, @NonNull final ApplicationManager applicationManager) {
        if (activity != null && !activity.isFinishing()) {
            new AlertDialog.Builder(activity).setMessage(R.string.drop_in_banner_profile_settings_dialog).setPositiveButton(R.string.drop_in_banner_profile_settings_dialog_positive_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.util.-$$Lambda$DropInUtils$WJPQ09x4U-2Q69xEQOQtDxqBCB8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DropInUtils.lambda$showProfileDialog$4(context, str, applicationManager, dialogInterface, i);
                }
            }).setNegativeButton(R.string.drop_in_banner_settings_dialog_negative_response, $$Lambda$DropInUtils$RajGCrfA44T0gRM_i0LgHCOuxY.INSTANCE).show();
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONVO_DROPIN_DIALOG_PROFILE_SETTINGS_SHOW);
            return;
        }
        LOG.w("Missing activity, aborting drop-in banner profile dialog");
    }
}
