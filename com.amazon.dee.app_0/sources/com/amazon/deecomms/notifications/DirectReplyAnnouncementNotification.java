package com.amazon.deecomms.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class DirectReplyAnnouncementNotification extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsNotificationManager.class);
    private ACMSClient acmsClient;

    private static PendingIntent createClickedAnnouncementIntent(String str, int i, Context context) {
        Intent intent = new Intent(context, NotificationActivatedReceiver.class);
        String pathTolaunchAnnouncementList = NotificationUtils.pathTolaunchAnnouncementList(str);
        intent.putExtra("target", Constants.NOTIFICATION_TARGET_ANNOUNCEMENT);
        intent.putExtra("notification_redirect_path", pathTolaunchAnnouncementList);
        return PendingIntent.getBroadcast(context, i, intent, 268435456);
    }

    private ObjectNode prepareJsonObject(String str, String str2, String str3) {
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("directedId", str);
        mo7041createObjectNode.put("type", Constants.TEXT_ANNOUNCEMENT_MESSAGE_TYPE);
        mo7041createObjectNode.put("senderFirstName", str2);
        mo7041createObjectNode.put("messageText", str3);
        return mo7041createObjectNode;
    }

    private boolean sendAnnouncement(String str, String str2, final String str3, String str4) throws InterruptedException {
        final ObjectNode prepareJsonObject = prepareJsonObject(str, str2, str4);
        final boolean[] zArr = {false};
        Thread thread = new Thread() { // from class: com.amazon.deecomms.notifications.DirectReplyAnnouncementNotification.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    IHttpClient.Response mo3640execute = DirectReplyAnnouncementNotification.this.acmsClient.request(str3).authenticatedAsCurrentCommsUser().postJson(prepareJsonObject).mo3640execute();
                    if (mo3640execute != null && mo3640execute.isSuccessful()) {
                        zArr[0] = true;
                    }
                    DirectReplyAnnouncementNotification.LOG.i("Sending announcement was not successful or response was null");
                    zArr[0] = false;
                } catch (ServiceException e) {
                    DirectReplyAnnouncementNotification.LOG.e("Failed to send Announcement in sendAnnouncement", e);
                    zArr[0] = false;
                }
            }
        };
        thread.start();
        thread.join();
        return zArr[0];
    }

    public static void sendReplyIntent(Context context, Intent intent, Boolean bool) {
        String string;
        int color = ContextCompat.getColor(context, R.color.notification_icon_background);
        int intExtra = intent.getIntExtra(Constants.DEFAULT_ANNOUNCEMENT_NOTIFICATION_ID, 3);
        PendingIntent createClickedAnnouncementIntent = createClickedAnnouncementIntent(intent.getStringExtra(Constants.REMOTE_INPUT_ANNOUNCEMENT_ID), intExtra, context);
        NotificationCompat.Builder color2 = new NotificationCompat.Builder(context, Constants.NOTIFICATIONS_COMMS_CHANNEL).setSmallIcon(R.drawable.ic_comms_notification).setColor(color);
        if (bool.booleanValue()) {
            string = context.getResources().getString(R.string.announcement_send_reply_success);
        } else {
            string = context.getResources().getString(R.string.announcement_send_reply_failure);
        }
        NotificationManagerCompat.from(context).notify(intExtra, color2.setContentText(string).setContentIntent(createClickedAnnouncementIntent).build());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String string;
        boolean z;
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent == null) {
            LOG.e("RemoteInput is null, Send Message from Notification failed");
            return;
        }
        CommsNotificationManager commsNotificationManager = CommsDaggerWrapper.getComponent().getCommsNotificationManager();
        String str = (String) resultsFromIntent.getCharSequence(Constants.REMOTE_INPUT_REPLY);
        if (!str.trim().isEmpty() && str.length() <= 148) {
            this.acmsClient = new ACMSClient(MetricKeys.OP_SEND_ANNOUNCEMENT);
            String stringExtra = intent.getStringExtra(Constants.DIRECTED_ID_PREF);
            String stringExtra2 = intent.getStringExtra(Constants.FIRST_NAME_PREF);
            String format = MessageFormat.format(AppUrl.SEND_ANNOUNCEMENTS, stringExtra);
            MetricsHelper.recordCounterMetricOperational(MetricKeys.REPLY_ANNOUNCEMENT_ATTEMPT, 1.0d);
            try {
                z = sendAnnouncement(stringExtra, stringExtra2, format, str);
                commsNotificationManager.clearAnnouncements();
            } catch (InterruptedException e) {
                LOG.e("Failed to send Announcement in sendAnnouncement", e);
                z = false;
            }
            if (z) {
                MetricsHelper.recordCounterMetricOperational(MetricKeys.REPLY_ANNOUNCEMENT_SUCCESS, 1.0d);
            } else {
                MetricsHelper.recordCounterMetricOperational(MetricKeys.REPLY_ANNOUNCEMENT_FAILURE, 1.0d);
            }
            sendReplyIntent(context, intent, Boolean.valueOf(z));
            return;
        }
        LOG.i("Not performing send reply because Announcement is empty or character limit exceeded");
        commsNotificationManager.displayRecentAnnouncementNotifications(true);
        if (str.trim().isEmpty()) {
            string = context.getResources().getString(R.string.send_reply_empty_announcement);
        } else {
            string = context.getResources().getString(R.string.send_reply_announcement_length_exceeded);
        }
        Toast makeText = Toast.makeText(context, string, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }
}
