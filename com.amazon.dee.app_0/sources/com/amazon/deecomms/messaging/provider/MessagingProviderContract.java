package com.amazon.deecomms.messaging.provider;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public final class MessagingProviderContract {

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
        public static final String COLUMN_NAME_READ_MESSAGE_LOCALLY_ONLY = "read_message_locally_only";
        public static final String COLUMN_NAME_RECIPIENT_ID = "recipient_id";
        public static final String COLUMN_NAME_SEND_AS_COMMS_ID = "send_as_comms_id";
        public static final String COLUMN_NAME_SERVER_LAST_MODIFIED_TIMESTAMP = "server_last_modified_timestamp";
        public static final String COLUMN_NAME_SERVER_LAST_MSG_ID = "server_last_msg_id";
        public static final String COLUMN_NAME_UNIQUE_ID = "_id";
        public static final String COLUMN_NAME_UNREAD_MSG_COUNT = "unread_msg_count";
        public static final String COLUMN_NAME_UNREAD_NOTIF_COUNT = "unread_notif_count";
        public static final String COLUMN_NAME_VIEW_AS_COMMS_ID = "view_as_comms_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_ID = "conversations.conversation_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_MODIFIED_TIMESTAMP = "conversations.last_modified_timestamp";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG = "conversations.last_msg";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_ID = "conversations.last_msg_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_SENDER_ID = "conversations.last_msg_sender_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_TYPE = "conversations.last_msg_type";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_SEQUENCE_ID = "conversations.last_sequence_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_LAST_SYNC_MSG_ID = "conversations.last_sync_msg_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_RECIPIENT_ID = "conversations.recipient_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_SEND_AS_COMMS_ID = "conversations.send_as_comms_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_SERVER_LAST_MODIFIED_TIMESTAMP = "conversations.server_last_modified_timestamp";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_SERVER_LAST_MSG_ID = "conversations.server_last_msg_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_UNIQUE_ID = "conversations._id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_UNREAD_MSG_COUNT = "conversations.unread_msg_count";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_UNREAD_NOTIF_COUNT = "conversations.unread_notif_count";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_VIEW_AS_COMMS_ID = "conversations.view_as_comms_id";
        public static final String CONVERSATIONS_WHERE_CLAUSE_UNIQUE_ID = "_id = ?";
        public static final String CONVERSATION_SYNC_URI_PATH = "conversation_sync";
        public static final String CONVERSATION_URI_PATH = "conversation";
        public static final String CONVERSER_COMPANY_NAME = "converser_company_name";
        public static final String CONVERSER_FIRST_NAME = "converser_first_name";
        public static final String CONVERSER_LAST_NAME = "converser_last_name";
        public static final String CONVERSER_NICK_NAME = "converser_nick_name";
        public static final String COUNT_UNREAD_MESSAGES_PATH = "countUnread";
        public static final String LAST_READ_STATUS_LIST = "last_read_status_list";
        public static final String LAST_UPDATED_SERVER_TIME = "last_updated_server_time";
        public static final String LAST_UPDATED_SERVER_TIME_ALIAS = "last_updated_server_time_alias";
        public static final String[] LAST_UPDATED_SERVER_TIME_PROJECTION = {"MAX(server_last_modified_timestamp) AS last_updated_server_time_alias"};
        public static final String MIME_TYPE = "vnd.android.cursor.item/conversation";
        public static final String PARTICIPANTS_DROP_IN_PERMISSION_LIST = "participants_drop_in_permission_list";
        public static final String PARTICIPANTS_HOMEGROUP_LIST = "participants_homegroup_list";
        public static final String PARTICIPANTS_LIST = "participants_list";
        public static final String TABLE_NAME = "conversations";
        public static final String UNREAD_COUNT = "unread_count";

        public static Uri getContentUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append("conversations");
            return Uri.parse(outline1.toString());
        }

        public static Uri getConversationContentUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(CONVERSATION_URI_PATH);
            return Uri.parse(outline1.toString());
        }

        public static Uri getConversationSyncUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(CONVERSATION_SYNC_URI_PATH);
            return Uri.parse(outline1.toString());
        }

        public static Uri getCountUnreadMessagesUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(COUNT_UNREAD_MESSAGES_PATH);
            return Uri.parse(outline1.toString());
        }

        public static Uri getLastUpdatedServerTimeUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(LAST_UPDATED_SERVER_TIME);
            return Uri.parse(outline1.toString());
        }
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
        public static final String MESSAGES_WHERE_CLAUSE_UNIQUE_ID = "_id = ?";
        public static final String MIME_TYPE = "vnd.android.cursor.item/message";
        public static final String MOST_RECENT_MESSAGE_COLUMN_ALIAS = "max_message_id";
        public static final String[] MOST_RECENT_MESSAGE_PROJECTION = {"MAX(message_id) AS max_message_id"};
        public static final String MOST_RECENT_MESSAGE_URI_PATH = "mostRecentMessage";
        public static final String TABLE_NAME = "messages";

        public static Uri getContentUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append("messages");
            return Uri.parse(outline1.toString());
        }

        public static Uri getMostRecentMessageContentUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(MOST_RECENT_MESSAGE_URI_PATH);
            return Uri.parse(outline1.toString());
        }
    }

    /* loaded from: classes12.dex */
    public static final class Participants {
        public static final String COLUMN_NAME_CONVERSATION_KEY_ID = "conversation_key_id";
        public static final String COLUMN_NAME_PARTICIPANT_COMMS_ID = "participant_comms_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_KEY_ID = "participants.conversation_key_id";
        public static final String COLUMN_NAME_WITH_TABLE_NAME_PARTICIPANT_COMMS_ID = "participants.participant_comms_id";
        public static final String[] GET_PARTICIPANTS_NAMES_ONLY_PROJECTION = {ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_COMMS_ID, "firstName", "lastName", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, "company"};
        public static final String[] GET_PARTICIPANTS_NAMES_PROJECTION_WITH_ALIAS = {ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_COMMS_ID, "PhoneNumbers.parentHomeGroup", "Contacts.firstName", "Contacts.lastName", "Contacts.nickName", "Contacts.company", " (IFNULL(ContactsAlias.canIDropInOnHim, Contacts.canIDropInOnHim)) AS canIDropInOnHim", "PhoneNumbers.isHomeGroup"};
        public static final String JOIN_PHONENUMBER_CONTACTS_BY_PARENTHG_COMMSID = "PhoneNumbers AS PhoneNumbersAlias ON PhoneNumbers.parentHomeGroup = PhoneNumbersAlias.commsId LEFT JOIN Contacts AS ContactsAlias ON PhoneNumbersAlias.serverContactId = ContactsAlias.serverContactId";
        public static final String PARTICIPANTS_COMMSID_IN_CONVERSATION = "participantCommsIdListInConversation";
        public static final String PARTICIPANTS_NOT_IN_CONTACTS = "participantsNotInContacts";
        public static final String PARTICIPANTS_WHERE_CLAUSE_CONVERSATION = "conversation_key_id = ?";
        public static final String PARTICIPANT_DETAILS_URI_PATH = "participantdetails";
        public static final String TABLE_NAME = "participants";

        public static Uri getParticipantDetailsContentUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(PARTICIPANT_DETAILS_URI_PATH);
            return Uri.parse(outline1.toString());
        }

        public static Uri getParticipantsCommsidInConversationUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(PARTICIPANTS_COMMSID_IN_CONVERSATION);
            return Uri.parse(outline1.toString());
        }

        public static Uri getParticipantsNotContactsUri(@NonNull Context context) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("content://");
            outline1.append(MessagingProviderContract.getAuthorityId(context));
            outline1.append("/");
            outline1.append(PARTICIPANTS_NOT_IN_CONTACTS);
            return Uri.parse(outline1.toString());
        }
    }

    private MessagingProviderContract() {
    }

    public static String getAuthorityId(@NonNull Context context) {
        return context.getString(R.string.messagingProviderAuthoritiesId);
    }
}
