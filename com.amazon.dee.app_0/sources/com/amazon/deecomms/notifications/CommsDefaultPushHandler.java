package com.amazon.deecomms.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.sync.ConversationDeleteService;
import com.amazon.deecomms.messaging.sync.MessageReadStatusMarkerService;
import com.amazon.deecomms.messaging.sync.TranscriptionUpdateService;
import com.amazon.deecomms.notifications.PushTypeHelper;
import com.amazon.deecomms.notifications.service.CreateNotificationService;
import com.amazon.deecomms.notifications.util.NotificationUtils;
/* loaded from: classes12.dex */
public class CommsDefaultPushHandler extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsDefaultPushHandler.class);
    private static final JacksonJSONConverter jsonConverter = new JacksonJSONConverter();

    private static void saveNotificationIfAppNotRunning(@NonNull Context context, @NonNull String str) {
        if (!Constants.GONE.equals(context.getSharedPreferences(Constants.MAIN_IN_FG, 0).getString(Constants.MAIN_IN_FG, Constants.GONE))) {
            return;
        }
        CommsDaggerWrapper.getComponent().getOfflinePushNotificationRepository().addNotification(context, str);
    }

    private void saveOrShowPushNotification(@NonNull Context context, @NonNull Bundle bundle, @NonNull String str) {
        saveNotificationIfAppNotRunning(context, str);
        Intent intent = new Intent(context, CreateNotificationService.class);
        intent.putExtras(bundle);
        CreateNotificationService.enqueueWork(context, intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LOG.w("Unable to handle notification which was missing the amznMessage payload");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return;
        }
        String string = extras.getString(Constants.AMP_KEY);
        if (TextUtils.isEmpty(string)) {
            LOG.w("Unable to handle notification which was missing the amznMessage payload");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.NOTIFICATION_DROPPED_METRIC);
            return;
        }
        String string2 = extras.getString(Constants.GCM_MESSAGE_ID_KEY);
        PushTypeHelper.PushType determineType = PushTypeHelper.determineType(string);
        if (determineType != PushTypeHelper.PushType.AudioAnnouncement && determineType != PushTypeHelper.PushType.TextAnnouncement) {
            if (determineType == PushTypeHelper.PushType.Message) {
                saveOrShowPushNotification(context, extras, string);
                NotificationUtils.logInfo(LOG, string2, "Notification passed off to the notification creator service.");
                Message message = (Message) jsonConverter.fromJson(string, Message.class);
                if (message == null) {
                    return;
                }
                message.getType();
                return;
            } else if (determineType == PushTypeHelper.PushType.ConversationDelete) {
                Intent intent2 = new Intent(context, ConversationDeleteService.class);
                intent2.putExtras(extras);
                ConversationDeleteService.enqueueWork(context, intent2);
                NotificationUtils.logInfo(LOG, string2, "Notification passed off to the conversation delete service");
                return;
            } else if (determineType == PushTypeHelper.PushType.ReadReceipt) {
                Intent intent3 = new Intent(context, MessageReadStatusMarkerService.class);
                intent3.putExtras(extras);
                intent3.putExtra(MessageReadStatusMarkerService.REMOTE_READ, true);
                MessageReadStatusMarkerService.enqueueWork(context, intent3);
                NotificationUtils.logInfo(LOG, string2, "Notification passed off to the mark as read service");
            } else if (determineType == PushTypeHelper.PushType.TranscriptUpdate) {
                Intent intent4 = new Intent(context, TranscriptionUpdateService.class);
                intent4.putExtras(extras);
                TranscriptionUpdateService.enqueueWork(context, intent4);
                NotificationUtils.logInfo(LOG, string2, "Notification passed off to the transcript-update service");
                return;
            }
        } else {
            saveOrShowPushNotification(context, extras, string);
        }
        CommsLogger commsLogger = LOG;
        NotificationUtils.logWarning(commsLogger, string2, "Notification received does not have an actionable pushType, " + determineType + " , for message: " + LOG.sensitive(string));
    }
}
