package com.amazon.deecomms.notifications;

import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.util.StopWatch;
import java.util.HashMap;
import java.util.Map;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public final class NotificationLatencyMetricHelper {
    private static final int MAX_LATENCY_DURATION_IN_SECONDS = 60;
    private Map<String, NotificationLatencyDatapoint> mNotificationLatencyDatapointMap = new HashMap();
    private final StopWatch mStopWatch;

    /* loaded from: classes12.dex */
    public enum ClickEndpoint {
        Announcement("comms.notif.endpoint.announce"),
        AnnouncementList("comms.notif.endpoint.announceList"),
        Conversation("comms.notif.endpoint.convo"),
        ConversationList("comms.notif.endpoint.convoList"),
        ImageDetailView("comms.notif.endpoint.imageDetailView");
        
        private String mStringValue;

        ClickEndpoint(String str) {
            this.mStringValue = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mStringValue;
        }
    }

    /* loaded from: classes12.dex */
    public enum ClickSource {
        MessageNotification("comms.notif.msg"),
        AnnouncementNotification("comms.notif.announce");
        
        private String mStringValue;

        ClickSource(String str) {
            this.mStringValue = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mStringValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class NotificationLatencyDatapoint {
        public ClickEndpoint endpoint;
        public String key;
        public ClickSource source;

        private NotificationLatencyDatapoint() {
        }

        /* synthetic */ NotificationLatencyDatapoint(AnonymousClass1 anonymousClass1) {
        }
    }

    public NotificationLatencyMetricHelper(Handler handler) {
        this.mStopWatch = new StopWatch(handler, 60);
        this.mStopWatch.getRemovalObservable().subscribe(new Action1<String>() { // from class: com.amazon.deecomms.notifications.NotificationLatencyMetricHelper.1
            @Override // rx.functions.Action1
            public void call(String str) {
                NotificationLatencyDatapoint notificationLatencyDatapoint;
                if (!TextUtils.isEmpty(str) && (notificationLatencyDatapoint = (NotificationLatencyDatapoint) NotificationLatencyMetricHelper.this.mNotificationLatencyDatapointMap.remove(str)) != null) {
                    if (!str.startsWith(ClickEndpoint.Conversation.toString()) && !str.startsWith(ClickEndpoint.ConversationList.toString())) {
                        return;
                    }
                    CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.ClickStream, MetricKeys.NOTIFICATION_CLICKED_LOST);
                    counterMetric.getMetadata().put("source", notificationLatencyDatapoint.source);
                    counterMetric.getMetadata().put("endpoint", notificationLatencyDatapoint.endpoint);
                    MetricsHelper.recordSingleOccurrence(counterMetric);
                }
            }
        });
    }

    public static String generateAnnouncementListKey(@NonNull String str) {
        return String.format("%s_%s", ClickEndpoint.AnnouncementList.toString(), str);
    }

    private static String generateConversationKey(@NonNull String str, @NonNull String str2) {
        return String.format("%s_%s_%s", ClickEndpoint.Conversation.toString(), str, str2);
    }

    public void startTimer(@NonNull String str, @NonNull String str2, @NonNull ClickSource clickSource, @NonNull ClickEndpoint clickEndpoint) {
        startTimer(generateConversationKey(str, str2), clickSource, clickEndpoint);
    }

    public void stopTimer(@NonNull String str, @NonNull String str2) {
        stopTimer(generateConversationKey(str, str2));
    }

    private void startTimer(@NonNull String str, @NonNull ClickSource clickSource, @NonNull ClickEndpoint clickEndpoint) {
        this.mStopWatch.start(str);
        NotificationLatencyDatapoint notificationLatencyDatapoint = new NotificationLatencyDatapoint(null);
        notificationLatencyDatapoint.key = str;
        notificationLatencyDatapoint.source = clickSource;
        notificationLatencyDatapoint.endpoint = clickEndpoint;
        this.mNotificationLatencyDatapointMap.put(notificationLatencyDatapoint.key, notificationLatencyDatapoint);
    }

    public void stopTimer(@NonNull String str) {
        NotificationLatencyDatapoint remove = this.mNotificationLatencyDatapointMap.remove(str);
        long elapsedTime = this.mStopWatch.elapsedTime(str);
        if (remove == null || elapsedTime == -1) {
            return;
        }
        this.mStopWatch.stop(str);
        TimerMetric generateOperational = TimerMetric.generateOperational(MetricKeys.NOTIFICATION_TO_APP_LATENCY);
        generateOperational.setTimeDelta(elapsedTime);
        generateOperational.getMetadata().put("source", remove.source);
        generateOperational.getMetadata().put("endpoint", remove.endpoint);
        MetricsHelper.recordTimerMetric(generateOperational);
    }

    public void startTimer(@NonNull String str, @NonNull String str2, @NonNull ClickEndpoint clickEndpoint) {
        startTimer(generateConversationKey(str, str2), ClickSource.MessageNotification, clickEndpoint);
    }

    public void startTimer(@NonNull String str, @NonNull ClickEndpoint clickEndpoint) {
        startTimer(str, ClickSource.MessageNotification, clickEndpoint);
    }
}
