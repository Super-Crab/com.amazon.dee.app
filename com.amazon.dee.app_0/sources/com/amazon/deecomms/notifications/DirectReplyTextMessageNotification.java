package com.amazon.deecomms.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.app.RemoteInput;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.sync.TextMessageSender;
import java.io.IOException;
/* loaded from: classes12.dex */
public class DirectReplyTextMessageNotification extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsNotificationManager.class);

    public static void sendReplyIntent(CommsNotificationManager commsNotificationManager, Intent intent, Boolean bool) {
        commsNotificationManager.updateDirectReplyToMessageNotification(intent.getStringExtra(Constants.BUNDLE_KEY_CONVERSATION_ID), bool.booleanValue());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String string;
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent == null) {
            LOG.e("RemoteInput is null, Send Message from Notification failed");
            return;
        }
        CommsNotificationManager commsNotificationManager = CommsDaggerWrapper.getComponent().getCommsNotificationManager();
        String stringExtra = intent.getStringExtra(Constants.BUNDLE_KEY_CONVERSATION_ID);
        String str = (String) resultsFromIntent.getCharSequence(Constants.REMOTE_INPUT_REPLY);
        boolean z = false;
        if (!str.trim().isEmpty() && str.length() <= 1000) {
            TextMessageSender textMessageSender = new TextMessageSender(context, null, false);
            MetricsHelper.recordCounterMetricOperational(MetricKeys.DIRECT_REPLY_NOTIF_ATTEMPT, 1.0d);
            try {
                z = textMessageSender.sendDirectReply(stringExtra, str).booleanValue();
            } catch (IOException | InterruptedException e) {
                LOG.e("Failed to send message in sendDirectReply", e);
            }
            if (z) {
                MetricsHelper.recordCounterMetricOperational(MetricKeys.DIRECT_REPLY_NOTIF_SUCCESS, 1.0d);
            } else {
                MetricsHelper.recordCounterMetricOperational(MetricKeys.DIRECT_REPLY_NOTIF_FAILURE, 1.0d);
            }
            sendReplyIntent(commsNotificationManager, intent, Boolean.valueOf(z));
            return;
        }
        LOG.i("Not performing send reply because message is empty or character limit exceeded");
        commsNotificationManager.displayRecentConversationNotifications(true);
        if (str.trim().isEmpty()) {
            string = context.getResources().getString(R.string.send_reply_empty_message);
        } else {
            string = context.getResources().getString(R.string.send_reply_message_length_exceeded);
        }
        Toast makeText = Toast.makeText(context, string, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }
}
