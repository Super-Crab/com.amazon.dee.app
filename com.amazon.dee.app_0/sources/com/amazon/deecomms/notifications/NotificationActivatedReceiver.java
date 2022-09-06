package com.amazon.deecomms.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.NotificationLatencyMetricHelper;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class NotificationActivatedReceiver extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NotificationActivatedReceiver.class);
    @Inject
    NotificationLatencyMetricHelper mNotificationLatencyMetricHelper;

    /* renamed from: com.amazon.deecomms.notifications.NotificationActivatedReceiver$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType = new int[NotificationUtils.NotificationPathType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.ANNOUNCEMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.ANNOUNCEMENT_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.CONVERSATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.CONVERSATION_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.IMAGE_DETAIL_VIEW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @VisibleForTesting
    Intent createNotificationRebroadcastIntent(String str, String str2) {
        Intent intent = new Intent(Constants.NOTIFICATION_CLICK_ACTION);
        intent.putExtra("notification_redirect_path", str);
        intent.putExtra("target", str2);
        return intent;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CommsDaggerWrapper.getComponent().inject(this);
        recordNotificationClickedMetric();
        String stringExtra = intent.getStringExtra("notification_redirect_path");
        String stringExtra2 = intent.getStringExtra("target");
        int intExtra = intent.getIntExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, -1);
        if (TextUtils.isEmpty(stringExtra)) {
            LOG.e("Received a notification-clicked action, but with no path, ignoring");
            return;
        }
        recordMetricForPath(stringExtra, this.mNotificationLatencyMetricHelper);
        if (Constants.NOTIFICATION_TARGET_ANNOUNCEMENT.equals(stringExtra2)) {
            CommsDaggerWrapper.getComponent().getCommsNotificationManager().clearAnnouncements();
        }
        if (intExtra == 10) {
            CommsDaggerWrapper.getComponent().getCommsNotificationManager().clearMessages();
        }
        Utils.sendExplicitBroadcastIntent(context, createNotificationRebroadcastIntent(stringExtra, stringExtra2), null);
    }

    @VisibleForTesting
    void recordMetricForPath(String str, NotificationLatencyMetricHelper notificationLatencyMetricHelper) {
        int ordinal = NotificationUtils.determineNotificationPathType(str).ordinal();
        if (ordinal == 0) {
            notificationLatencyMetricHelper.startTimer(NotificationLatencyMetricHelper.ClickEndpoint.Announcement.toString(), NotificationLatencyMetricHelper.ClickEndpoint.Announcement);
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.NOTIFICATION_GOTO_ANNOUNCEMENT);
        } else if (ordinal == 1) {
            String extractAnnouncementIdFromPath = NotificationUtils.extractAnnouncementIdFromPath(str);
            if (extractAnnouncementIdFromPath == null) {
                LOG.e("Received an announcement-notification-clicked action, but with no announcementId.");
                return;
            }
            notificationLatencyMetricHelper.startTimer(NotificationLatencyMetricHelper.generateAnnouncementListKey(extractAnnouncementIdFromPath), NotificationLatencyMetricHelper.ClickEndpoint.AnnouncementList);
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.NOTIFICATION_GOTO_ANNOUNCEMENT_LIST);
        } else if (ordinal == 2) {
            NotificationUtils.ExtractedInfo extractConversationAndRecipientFromPath = NotificationUtils.extractConversationAndRecipientFromPath(str);
            notificationLatencyMetricHelper.startTimer(extractConversationAndRecipientFromPath.conversationId, extractConversationAndRecipientFromPath.recipientId, NotificationLatencyMetricHelper.ClickEndpoint.Conversation);
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.NOTIFICATION_GOTO_MSG_CONVO);
        } else if (ordinal == 3) {
            notificationLatencyMetricHelper.startTimer(NotificationLatencyMetricHelper.ClickEndpoint.ConversationList.toString(), NotificationLatencyMetricHelper.ClickEndpoint.ConversationList);
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.NOTIFICATION_GOTO_MSG_CONVO);
        } else if (ordinal != 4) {
            LOG.i("received a notification type that hasn't had 'activated' metrics applied");
        } else {
            notificationLatencyMetricHelper.startTimer(NotificationLatencyMetricHelper.ClickEndpoint.ImageDetailView.toString(), NotificationLatencyMetricHelper.ClickEndpoint.ImageDetailView);
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.NOTIFICATION_GOTO_IMAGE_DETAIL_VIEW);
        }
    }

    @VisibleForTesting
    void recordNotificationClickedMetric() {
        CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.ClickStream, MetricKeys.NOTIFICATION_CLICKED);
        counterMetric.getMetadata().put("source", NotificationLatencyMetricHelper.ClickSource.MessageNotification.toString());
        MetricsHelper.recordSingleOccurrence(counterMetric);
    }
}
