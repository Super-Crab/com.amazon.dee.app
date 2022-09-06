package com.amazon.deecomms.notifications;

import androidx.annotation.NonNull;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.notifications.model.ConversationDeletePush;
import com.amazon.deecomms.notifications.model.ReadReceiptPush;
import com.amazon.deecomms.notifications.model.TranscriptUpdatePush;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class PushTypeHelper {
    private static final String EVENT_TYPE_KEY = "eventType";
    private static final String PAYLOAD_KEY = "payload";
    private static final String TYPE_KEY = "type";

    /* loaded from: classes12.dex */
    public enum PushType {
        Message,
        ConversationDelete,
        ReadReceipt,
        TranscriptUpdate,
        AudioAnnouncement,
        TextAnnouncement,
        Invalid
    }

    private PushTypeHelper() {
    }

    public static PushType determineType(String str) {
        return getPushTypeFromMessageType(getMessageTypeFromMessagePayload((HashMap) new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() { // from class: com.amazon.deecomms.notifications.PushTypeHelper.1
        }.getType())).toLowerCase());
    }

    private static String getMessageTypeFromMessagePayload(HashMap<String, Object> hashMap) {
        if (hashMap.containsKey("type")) {
            return (String) hashMap.get("type");
        }
        if (hashMap.containsKey(EVENT_TYPE_KEY)) {
            return (String) hashMap.get(EVENT_TYPE_KEY);
        }
        if (hashMap.containsKey("payload")) {
            HashMap hashMap2 = (HashMap) hashMap.get("payload");
            if (hashMap2.containsKey("type")) {
                return (String) hashMap2.get("type");
            }
            if (hashMap2.containsKey(EVENT_TYPE_KEY)) {
                return (String) hashMap2.get("type");
            }
        }
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @NonNull
    private static PushType getPushTypeFromMessageType(String str) {
        char c;
        switch (str.hashCode()) {
            case -1330413003:
                if (str.equals("message/text")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1034636608:
                if (str.equals(TranscriptUpdatePush.TYPE)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -966584834:
                if (str.equals("event/reaction")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -681227931:
                if (str.equals(ConversationDeletePush.TYPE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -389653234:
                if (str.equals("announcement/audio")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 213629550:
                if (str.equals("message/contact-invitation")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 560285392:
                if (str.equals(ReadReceiptPush.TYPE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 849467995:
                if (str.equals("event/missed-call")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 939518131:
                if (str.equals("event/call")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1068011914:
                if (str.equals("announcement/text-simple")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1446098011:
                if (str.equals("reaction/default")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1689780174:
                if (str.equals("message/audio")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1700385756:
                if (str.equals("message/media")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1846772201:
                if (str.equals("message/contact-connection-success")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2029986329:
                if (str.equals("message/shared-content")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_TEXT_MESSAGE, 1.0d);
                return PushType.Message;
            case 1:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_INVITE_MESSAGE, 1.0d);
                return PushType.Message;
            case 2:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_INVITE_SUCCESS_MESSAGE, 1.0d);
                return PushType.Message;
            case 3:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_IMAGE_MESSAGE, 1.0d);
                return PushType.Message;
            case 4:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_MISSED_CALL, 1.0d);
                return PushType.Message;
            case 5:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_ENDED_CALL, 1.0d);
                return PushType.Message;
            case 6:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_AUDIO_MESSAGE, 1.0d);
                return PushType.Message;
            case 7:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_REACTION_EVENT_MESSAGE, 1.0d);
                return PushType.Message;
            case '\b':
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_SHARING_REACTION_MESSAGE, 1.0d);
                return PushType.Message;
            case '\t':
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_SHARING_MESSAGE, 1.0d);
                return PushType.Message;
            case '\n':
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_DELETE_CONVERSATION, 1.0d);
                return PushType.ConversationDelete;
            case 11:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_READ_RECEIPT, 1.0d);
                return PushType.ReadReceipt;
            case '\f':
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_TRANSCRIPT_UPDATED, 1.0d);
                return PushType.TranscriptUpdate;
            case '\r':
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_AUDIO_ANNOUNCEMENT, 1.0d);
                return PushType.AudioAnnouncement;
            case 14:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_TEXT_ANNOUNCEMENT, 1.0d);
                return PushType.TextAnnouncement;
            default:
                MetricsHelper.recordCounterMetricOperational(MetricKeys.CNOTIF_MSG_TYPE_INVALID, 1.0d);
                String str2 = "Unable to determine payload type from PushType, defaulting to PushType.Invalid: " + str;
                return PushType.Invalid;
        }
    }
}
