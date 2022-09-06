package com.amazon.alexa.accessorykit.finishsetup.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.notifications.LocalNotification;
import com.amazon.alexa.accessory.notifications.NotificationInteractor;
import com.amazon.alexa.accessorykit.R;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupEvent;
import com.amazon.alexa.accessorykit.finishsetup.metrics.FinishSetupMetricsRecorder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
/* loaded from: classes6.dex */
public final class NotificationPresenter implements FinishSetupPresenter {
    private static final String FAS_GATEWAY_DEEPLINK_URI = "accessories/finish-setup/local-notification/%s/%s/%s/%s/%d";
    private final Context context;
    private final NotificationInteractor interactor;
    private final FinishSetupMetricsRecorder metricsRecorder;

    public NotificationPresenter(Context context, NotificationInteractor notificationInteractor, FinishSetupMetricsRecorder finishSetupMetricsRecorder) {
        this.context = context;
        this.interactor = notificationInteractor;
        this.metricsRecorder = finishSetupMetricsRecorder;
    }

    private static String encodeValue(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Logger.e(e.getMessage(), e);
            return str;
        }
    }

    @SuppressLint({"DefaultLocale"})
    private String generateSetupUrl(FinishSetupEvent finishSetupEvent) {
        return String.format(FAS_GATEWAY_DEEPLINK_URI, encodeValue(finishSetupEvent.getAccessory().getName()), encodeValue(finishSetupEvent.getAccessory().getAddress()), encodeValue(finishSetupEvent.getDeviceType()), encodeValue(finishSetupEvent.getSerialNumber()), finishSetupEvent.getColor());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String getPreferredName(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1730073162:
                if (str.equals("A3GZUE7F9MEB4U")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -286864690:
                if (str.equals("AE9FIEPOC6D9B")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 351013175:
                if (str.equals("A3FSH277H5LMR2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 372957537:
                if (str.equals("AO50AHDYKXRFG")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1650977335:
                if (str.equals("A23ZD3FSVQM5EE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1920799096:
                if (str.equals("A2Y04QPFCANLPQ")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? str2 : "Echo Buds" : "Bose NC700" : "Bose QC35 II" : "Live 650BTNC" : "Sony WH-1000XM2" : "Sony WH-1000XM3";
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public boolean canPresent() {
        return this.interactor.areNotificationsEnabled();
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public void dismiss(FinishSetupEvent finishSetupEvent) {
        this.interactor.cancel(finishSetupEvent.getAccessory().getAddress().hashCode());
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.presenters.FinishSetupPresenter
    public void present(FinishSetupEvent finishSetupEvent) {
        Accessory accessory = finishSetupEvent.getAccessory();
        String deviceType = finishSetupEvent.getDeviceType();
        this.interactor.show(accessory.getAddress().hashCode(), LocalNotification.newBuilder().setTitle(String.format(this.context.getString(R.string.ama_fas_notification_title), getPreferredName(deviceType, accessory.getName()))).setText(this.context.getString(R.string.ama_fas_notification_text)).setDeepLink(generateSetupUrl(finishSetupEvent)).build());
        this.metricsRecorder.recordShowNotification(deviceType);
    }
}
