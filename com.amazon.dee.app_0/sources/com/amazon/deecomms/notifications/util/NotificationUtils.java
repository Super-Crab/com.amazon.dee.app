package com.amazon.deecomms.notifications.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.PathMatcher;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.notifications.model.announcement.AudioAnnouncementPayload;
import com.google.common.base.Strings;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public final class NotificationUtils {
    private static final int EXPECTED_ANNOUNCEMENT_PATH_TOKENS = 5;
    private static final int EXPECTED_CONVERSATION_PATH_TOKENS = 7;
    private static final String FULL_CONVERSATION_PATH = "conversations/%2Fusers%2F{0}%2Fconversations%2F{1}%2Frecipient%2F{2}/start/false";
    private static final String GROUP_NOTIFICATION_ANNOUNCEMENT_LIST_PATH = "v2/comms/announcement-list";
    private static final String NOTIFICATION_ANNOUNCEMENT_LIST_PATH = "v2/comms/announcement-list/{0}";
    private static final String NOTIFICATION_ANNOUNCEMENT_PATH = "v2/comms/announcement";
    private static final String NOTIFICATION_CONVERSATION_LIST_PATH = "/users/{0}/conversations";
    private static final String NOTIFICATION_CONVERSATION_PATH = "/users/{0}/conversations/{1}/recipient/{2}";
    private static final String NOTIFICATION_IMAGE_DETAIL_VIEW_PATH = "v2/comms/image-detail-view/{0}?messageId={1}";
    public static final String NOTIFICATION_LOG_FORMAT = "[message_id %s] %s";
    public static final String UNKNOWN_CONVERSATION_TOKEN = "UNKNOWN";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NotificationUtils.class);
    private static final Set<String> SUPPRESSED_CONVERSATION_ID_SET = Collections.synchronizedSet(new HashSet());

    /* loaded from: classes12.dex */
    public static final class ExtractedInfo {
        public final String conversationId;
        public final String recipientId;
        public final String sendAsCommsId;

        private ExtractedInfo(String str, String str2, String str3) {
            this.sendAsCommsId = str;
            this.conversationId = str2;
            this.recipientId = str3;
        }
    }

    /* loaded from: classes12.dex */
    public enum NotificationPathType {
        ANNOUNCEMENT,
        ANNOUNCEMENT_LIST,
        CONVERSATION,
        CONVERSATION_LIST,
        IMAGE_DETAIL_VIEW,
        UNKNOWN
    }

    private NotificationUtils() {
    }

    public static void clearNotificationSuppressions() {
        SUPPRESSED_CONVERSATION_ID_SET.clear();
    }

    @NonNull
    public static NotificationPathType determineNotificationPathType(String str) {
        if (PathMatcher.matches(NOTIFICATION_CONVERSATION_PATH, str)) {
            return NotificationPathType.CONVERSATION;
        }
        if (PathMatcher.matches("/users/{0}/conversations", str)) {
            return NotificationPathType.CONVERSATION_LIST;
        }
        if (PathMatcher.matches("v2/comms/announcement", str)) {
            return NotificationPathType.ANNOUNCEMENT;
        }
        if (PathMatcher.matches(NOTIFICATION_ANNOUNCEMENT_LIST_PATH, str)) {
            return NotificationPathType.ANNOUNCEMENT_LIST;
        }
        if (PathMatcher.matches("v2/comms/announcement-list", str)) {
            return NotificationPathType.ANNOUNCEMENT_LIST;
        }
        if (PathMatcher.matches(NOTIFICATION_IMAGE_DETAIL_VIEW_PATH, str)) {
            return NotificationPathType.IMAGE_DETAIL_VIEW;
        }
        return NotificationPathType.UNKNOWN;
    }

    public static String extractAnnouncementIdFromPath(@NonNull String str) {
        String[] split = TextUtils.split(str, "/");
        if (split != null && split.length >= 5) {
            return split[4];
        }
        LOG.w("Announcements path has less than five parts -- cannot parse");
        return null;
    }

    public static ExtractedInfo extractConversationAndRecipientFromPath(@NonNull String str) {
        String[] split = TextUtils.split(str, "/");
        if (split != null && split.length >= 7) {
            return new ExtractedInfo(split[2], split[4], split[6]);
        }
        LOG.w("Conversations path has less than seven parts -- cannot parse");
        return null;
    }

    public static String fullPathTolaunchConversation(String str, String str2, String str3) {
        return MessageFormat.format(FULL_CONVERSATION_PATH, str, str2, str3);
    }

    public static boolean isNotificationSuppressed(@NonNull String str) {
        return SUPPRESSED_CONVERSATION_ID_SET.contains(str);
    }

    public static void logDebug(@NonNull CommsLogger commsLogger, String str, String str2) {
        commsLogger.d(String.format(NOTIFICATION_LOG_FORMAT, str, str2));
    }

    public static void logInfo(@NonNull CommsLogger commsLogger, String str, String str2) {
        commsLogger.i(String.format(NOTIFICATION_LOG_FORMAT, str, str2));
    }

    public static void logWarning(@NonNull CommsLogger commsLogger, String str, String str2) {
        commsLogger.w(String.format(NOTIFICATION_LOG_FORMAT, str, str2));
    }

    public static String pathTolaunchAnnouncementCompose() {
        return "v2/comms/announcement";
    }

    public static String pathTolaunchAnnouncementList(String str) {
        return Strings.isNullOrEmpty(str) ? "v2/comms/announcement-list" : MessageFormat.format(NOTIFICATION_ANNOUNCEMENT_LIST_PATH, str);
    }

    public static String pathTolaunchConversation(String str, String str2) {
        return MessageFormat.format(NOTIFICATION_CONVERSATION_PATH, CommsInternal.getInstance().getCommsId(), str, str2);
    }

    public static String pathTolaunchConversationList() {
        return MessageFormat.format("/users/{0}/conversations", CommsInternal.getInstance().getCommsId());
    }

    public static String pathTolaunchImageDetailView(@NonNull String str, @NonNull long j) {
        return MessageFormat.format(NOTIFICATION_IMAGE_DETAIL_VIEW_PATH, str, Long.toString(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ClientMessage prepareClientMessage(String str, String str2, String str3) {
        AudioMessagePayload audioMessagePayload;
        if ("announcement/audio".equalsIgnoreCase(str3)) {
            AudioAnnouncementPayload audioAnnouncementPayload = new AudioAnnouncementPayload();
            audioAnnouncementPayload.setMediaId(str);
            audioMessagePayload = audioAnnouncementPayload;
        } else if ("message/audio".equalsIgnoreCase(str3)) {
            AudioMessagePayload audioMessagePayload2 = new AudioMessagePayload();
            audioMessagePayload2.setMediaId(str);
            audioMessagePayload = audioMessagePayload2;
        } else {
            audioMessagePayload = null;
        }
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setType(str3);
        clientMessage.setPayload(audioMessagePayload);
        clientMessage.setViewAsCommsId(str2);
        return clientMessage;
    }

    public static void removeNotificationSuppression(@NonNull String... strArr) {
        for (String str : strArr) {
            SUPPRESSED_CONVERSATION_ID_SET.remove(str);
        }
    }

    public static void suppressNotification(@NonNull String... strArr) {
        for (String str : strArr) {
            SUPPRESSED_CONVERSATION_ID_SET.add(str);
        }
    }
}
