package com.amazon.deecomms.messaging.database;

import android.provider.BaseColumns;
/* loaded from: classes12.dex */
public class MessagingTableConstants {

    /* loaded from: classes12.dex */
    public static final class Conversations {
        public static final String COLUMN_NAME_CONVERSATION_ID = "conversation_id";
        public static final String COLUMN_NAME_LAST_MODIFIED_TIMESTAMP = "last_modified_timestamp";
        public static final String COLUMN_NAME_LAST_MSG = "last_msg";
        public static final String COLUMN_NAME_LAST_MSG_ID = "last_msg_id";
        public static final String COLUMN_NAME_LAST_MSG_SENDER_ID = "last_msg_sender_id";
        public static final String COLUMN_NAME_LAST_MSG_TYPE = "last_msg_type";
        public static final String COLUMN_NAME_LAST_READ_MSG_ID = "last_read_msg_id";
        public static final String COLUMN_NAME_LAST_SEQUENCE_ID = "last_sequence_id";
        public static final String COLUMN_NAME_LAST_SYNC_MSG_ID = "last_sync_msg_id";
        public static final String COLUMN_NAME_READ_MSG_LOCALLY_ONLY = "read_message_locally_only";
        public static final String COLUMN_NAME_RECIPIENT_ID = "recipient_id";
        public static final String COLUMN_NAME_SEND_AS_COMMS_ID = "send_as_comms_id";
        public static final String COLUMN_NAME_SERVER_LAST_MODIFIED_TIMESTAMP = "server_last_modified_timestamp";
        public static final String COLUMN_NAME_SERVER_LAST_MSG_ID = "server_last_msg_id";
        public static final String COLUMN_NAME_UNIQUE_ID = "_id";
        public static final String COLUMN_NAME_UNREAD_MSG_COUNT = "unread_msg_count";
        public static final String COLUMN_NAME_UNREAD_NOTIF_COUNT = "unread_notif_count";
        public static final String COLUMN_NAME_VIEW_AS_COMMS_ID = "view_as_comms_id";
        public static final String CONVERSATION_INDEX_NAME = "conversations_comms_unique";
        public static final String TABLE_NAME = "conversations";
    }

    /* loaded from: classes12.dex */
    public static final class Messages {
        public static final String COLUMN_NAME_CLIENT_ID = "client_id";
        public static final String COLUMN_NAME_CLIENT_MESSAGE_ID = "client_message_id";
        public static final String COLUMN_NAME_CONVERSATION_ID = "conversation_id";
        public static final String COLUMN_NAME_MESSAGE_ID = "message_id";
        public static final String COLUMN_NAME_PAYLOAD = "_payload";
        public static final String COLUMN_NAME_RECIPIENT_ID = "recipient_id";
        public static final String COLUMN_NAME_SENDER_COMMS_ID = "sender_comms_id";
        public static final String COLUMN_NAME_SYNC_STATUS = "sync_status";
        public static final String COLUMN_NAME_TIME = "_time";
        public static final String COLUMN_NAME_TYPE = "_type";
        public static final String COLUMN_NAME_UNIQUE_ID = "_id";
        public static final String COLUMN_NAME_VIEW_AS_COMMS_ID = "view_as_comms_id";
        public static final String MESSAGES_CLIENT_MESSAGE_ID_INDEX_NAME = "messages_client_message_ids";
        public static final String MESSAGES_INDEX_NAME = "messages_comms_unique";
        public static final String TABLE_NAME = "messages";
    }

    /* loaded from: classes12.dex */
    public static final class OfflinePushNotificationCache implements BaseColumns {
        public static final String COLUMN_NAME_PAYLOAD = "payload";
        public static final String TABLE_NAME = "OfflinePushNotificationCache";
    }

    /* loaded from: classes12.dex */
    public static final class Participants {
        public static final String COLUMN_NAME_CONVERSATION_KEY_ID = "conversation_key_id";
        public static final String COLUMN_NAME_LAST_READ_MSG_ID = "last_read_msg_id";
        public static final String COLUMN_NAME_PARTICIPANT_COMMS_ID = "participant_comms_id";
        public static final String COLUMN_NAME_UNIQUE_ID = "participant_unique_id";
        public static final String PARTICIPANT_INDEX_NAME = "participant_comms_unique";
        public static final String TABLE_NAME = "participants";
    }
}
