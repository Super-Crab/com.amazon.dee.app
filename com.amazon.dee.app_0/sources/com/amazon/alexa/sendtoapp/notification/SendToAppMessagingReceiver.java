package com.amazon.alexa.sendtoapp.notification;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.common.base.Optional;
/* loaded from: classes10.dex */
public class SendToAppMessagingReceiver implements MessagingReceiver {
    static final String ACTIONS = "s2aActions";
    static final String METRIC_ID = "s2aMetricId";
    public static final String SEND_TO_APP_NOTIFICATION_TYPE = "SEND_TO_PHONE";
    @VisibleForTesting
    static final String SUPPORTED_VERSION = "1.0";
    private static final String TAG = "SendToAppMessagingReceiver";
    static final String TEXT = "s2aText";
    static final String TITLE = "s2aTitle";
    static final String TOKEN = "s2aToken";
    static final String TYPE = "type";
    static final String VERSION = "version";
    private Context context;
    private SendToAppNotifications sendToAppNotifications;

    public SendToAppMessagingReceiver(@NonNull Context context, @NonNull SendToAppNotifications sendToAppNotifications) {
        this.context = context;
        this.sendToAppNotifications = sendToAppNotifications;
    }

    private boolean createSendToAppNotificationFromMessageBundle(Bundle bundle) {
        Log.i(TAG, "S2A message received");
        Optional optional = ComponentRegistry.getInstance().get(EnvironmentService.class);
        if (!optional.isPresent()) {
            Log.e(TAG, "Environment service is not present.");
            return false;
        }
        DeviceInformation deviceInformation = ((EnvironmentService) optional.get()).getDeviceInformation();
        if (deviceInformation == null) {
            Log.e(TAG, "Device information is not present.");
            return false;
        } else if (deviceInformation.isFireOS()) {
            Log.e(TAG, "Device is FireOS, not supported.");
            return false;
        } else {
            String string = bundle.getString(METRIC_ID);
            if (TextUtils.isEmpty(string)) {
                Log.e(TAG, "Metric ID is null or empty.");
            }
            MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", "Received", 1, string);
            String string2 = bundle.getString("version");
            if (!"1.0".equals(string2)) {
                Log.w(TAG, String.format("Unsupported version: %s", string2));
                MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.VERSION_SUPPORTED_EVENT_NAME, 0, string);
                return false;
            }
            String.format("Supported version: %s", string2);
            MobilyticsMetricsRecorder.recordCounter(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.VERSION_SUPPORTED_EVENT_NAME, 1, string);
            String string3 = bundle.getString(TITLE);
            if (TextUtils.isEmpty(string3)) {
                Log.e(TAG, "Title is null or empty.");
                return false;
            }
            String string4 = bundle.getString(TEXT);
            if (TextUtils.isEmpty(string4)) {
                Log.e(TAG, "Text is null or empty.");
                return false;
            }
            String string5 = bundle.getString(ACTIONS);
            if (TextUtils.isEmpty(string5)) {
                Log.e(TAG, "Actions is null or empty.");
                return false;
            }
            String string6 = bundle.getString(TOKEN);
            if (TextUtils.isEmpty(string6)) {
                Log.e(TAG, "Token is null or empty.");
                return false;
            }
            this.sendToAppNotifications.createSendToAppNotification(string3, string4, string5, string6, string);
            return true;
        }
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
        Log.i(TAG, "Message received.");
        if (message.isCanceled()) {
            Log.i(TAG, "Message is already canceled.");
            return;
        }
        Bundle bundle = message.get();
        if (bundle.keySet() != null && !bundle.keySet().isEmpty()) {
            for (String str : bundle.keySet()) {
                String.format("Message bundle key: %s, value: %s", str, bundle.get(str));
            }
            if (!SEND_TO_APP_NOTIFICATION_TYPE.equals(bundle.getString("type"))) {
                String.format("Message type is not %s, ignore it.", SEND_TO_APP_NOTIFICATION_TYPE);
                return;
            }
            Log.i(TAG, String.format("Notification creation success: %s", Boolean.valueOf(createSendToAppNotificationFromMessageBundle(bundle))));
            message.cancel();
            return;
        }
        Log.e(TAG, "No keySet, or keySet is empty.");
    }
}
