package com.amazon.deecomms.messaging.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.messaging.model.payload.ReactionEventMessagePayload;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes12.dex */
public class ConversationNotification {
    private static final int MAX_CHARACTERS_IN_A_LINE = 50;
    private final String conversationId;
    private final String conversationPath;
    private String displayName;
    private boolean replySent;
    private boolean replySuccess;
    private Comparator<Message> dateComparator = new Comparator<Message>() { // from class: com.amazon.deecomms.messaging.model.ConversationNotification.1
        @Override // java.util.Comparator
        public int compare(Message message, Message message2) {
            if (message == null) {
                return 1;
            }
            if (message2 != null) {
                return message2.getTime().compareTo(message.getTime());
            }
            return -1;
        }
    };
    private final NavigableSet<Message> messages = new TreeSet(this.dateComparator);
    private int notificationId = -1;

    public ConversationNotification(@NonNull Message message, @NonNull String str) {
        this.conversationId = message.getConversationId();
        this.messages.add(message);
        setDisplayName(str);
        this.replySent = false;
        this.replySuccess = false;
        this.conversationPath = getRoutePathForMessageType(message);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void clearMessages() {
        this.messages.clear();
    }

    public boolean containsLongMessage(Context context) {
        int length;
        if (this.messages.size() == 0) {
            return false;
        }
        int i = 0;
        for (Message message : this.messages) {
            String payloadNotificationText = message.getPayloadNotificationText(context);
            if (payloadNotificationText != null && (length = payloadNotificationText.length()) > i) {
                i = length;
            }
        }
        return i > 50;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ConversationNotification.class == obj.getClass()) {
            return Objects.equals(((ConversationNotification) obj).getConversationId(), getConversationId());
        }
        return false;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public String getConversationPath() {
        return this.conversationPath;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Message getMessage() {
        if (this.messages.size() == 0) {
            return null;
        }
        return this.messages.first();
    }

    public NavigableSet<Message> getMessages() {
        return this.messages;
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    @VisibleForTesting
    public String getRoutePathForMessageType(Message message) {
        char c;
        String type = message.getPayload().getType();
        int hashCode = type.hashCode();
        if (hashCode != -966584834) {
            if (hashCode == 1700385756 && type.equals("message/media")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (type.equals("event/reaction")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return NotificationUtils.pathTolaunchConversation(message.getConversationId(), message.getSenderCommsId());
            }
            return NotificationUtils.pathTolaunchImageDetailView(message.getConversationId(), ((ReactionEventMessagePayload) message.getPayload()).getParentMessageId());
        }
        return NotificationUtils.pathTolaunchImageDetailView(message.getConversationId(), message.getMessageId());
    }

    public long getTimeAsLong() {
        Message message = getMessage();
        if (message == null) {
            return -1L;
        }
        try {
            return ISODateTimeFormat.dateTime().parseDateTime(message.getTime()).getMillis();
        } catch (IllegalArgumentException unused) {
            return -1L;
        }
    }

    public int hashCode() {
        return Objects.hash(this.messages);
    }

    public boolean isReplySent() {
        return this.replySent;
    }

    public boolean isReplySuccess() {
        return this.replySuccess;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    public void setReplySent(boolean z) {
        this.replySent = z;
    }

    public void setReplySuccess(boolean z) {
        this.replySuccess = z;
    }
}
