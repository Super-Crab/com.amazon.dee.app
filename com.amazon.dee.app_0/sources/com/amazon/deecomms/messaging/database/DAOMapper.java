package com.amazon.deecomms.messaging.database;

import android.content.ContentValues;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public final class DAOMapper {
    private DAOMapper() {
    }

    public static ContentValues convertToConversationDAO(ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        if (contentValues.containsKey("conversation_id")) {
            contentValues2.put("conversation_id", contentValues.getAsString("conversation_id"));
        }
        if (contentValues.containsKey("recipient_id")) {
            contentValues2.put("recipient_id", contentValues.getAsString("recipient_id"));
        }
        if (contentValues.containsKey("last_msg_id")) {
            contentValues2.put("last_msg_id", contentValues.getAsString("last_msg_id"));
        }
        if (contentValues.containsKey("last_sequence_id")) {
            contentValues2.put("last_sequence_id", contentValues.getAsString("last_sequence_id"));
        }
        if (contentValues.containsKey("last_msg")) {
            contentValues2.put("last_msg", contentValues.getAsString("last_msg"));
        }
        if (contentValues.containsKey("last_msg_type")) {
            contentValues2.put("last_msg_type", contentValues.getAsString("last_msg_type"));
        }
        if (contentValues.containsKey("unread_msg_count")) {
            contentValues2.put("unread_msg_count", contentValues.getAsString("unread_msg_count"));
        }
        if (contentValues.containsKey("unread_notif_count")) {
            contentValues2.put("unread_notif_count", contentValues.getAsString("unread_notif_count"));
        }
        if (contentValues.containsKey("last_modified_timestamp")) {
            contentValues2.put("last_modified_timestamp", contentValues.getAsLong("last_modified_timestamp"));
        }
        if (contentValues.containsKey("send_as_comms_id")) {
            contentValues2.put("send_as_comms_id", contentValues.getAsString("send_as_comms_id"));
        }
        if (contentValues.containsKey("view_as_comms_id")) {
            contentValues2.put("view_as_comms_id", contentValues.getAsString("view_as_comms_id"));
        }
        if (contentValues.containsKey("server_last_modified_timestamp")) {
            contentValues2.put("server_last_modified_timestamp", contentValues.getAsLong("server_last_modified_timestamp"));
        }
        if (contentValues.containsKey("server_last_msg_id")) {
            contentValues2.put("server_last_msg_id", contentValues.getAsLong("server_last_msg_id"));
        }
        if (contentValues.containsKey("last_sync_msg_id")) {
            contentValues2.put("last_sync_msg_id", contentValues.getAsLong("last_sync_msg_id"));
        }
        if (contentValues.containsKey("last_msg_sender_id")) {
            contentValues2.put("last_msg_sender_id", contentValues.getAsString("last_msg_sender_id"));
        }
        if (contentValues.containsKey("last_read_msg_id")) {
            contentValues2.put("last_read_msg_id", contentValues.getAsLong("last_read_msg_id"));
        }
        if (contentValues.containsKey("read_message_locally_only")) {
            contentValues2.put("read_message_locally_only", contentValues.getAsLong("read_message_locally_only"));
        }
        return contentValues2;
    }

    private static ContentValues convertToParticipantsDAO(long j, String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("conversation_key_id", Long.valueOf(j));
        contentValues.put("participant_comms_id", str);
        contentValues.put("last_read_msg_id", Integer.valueOf(i));
        return contentValues;
    }

    public static ContentValues[] convertToParticipantsDAOList(ContentValues contentValues) {
        List<ContentValues> participantsDAOList = getParticipantsDAOList(contentValues);
        return (ContentValues[]) participantsDAOList.toArray(new ContentValues[participantsDAOList.size()]);
    }

    private static Map<String, Integer> getLastReadStatusMap(String str) {
        return (Map) new JacksonJSONConverter().fromJson(str, new TypeReference<HashMap<String, Integer>>() { // from class: com.amazon.deecomms.messaging.database.DAOMapper.1
        });
    }

    private static List<ContentValues> getParticipantsDAOList(ContentValues contentValues) {
        Map<String, Integer> lastReadStatusMap = getLastReadStatusMap(contentValues.getAsString(MessagingProviderContract.Conversations.LAST_READ_STATUS_LIST));
        String[] split = contentValues.getAsString(MessagingProviderContract.Conversations.PARTICIPANTS_LIST).split(Constants.GROUP_CONCAT_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        long longValue = contentValues.getAsLong("_id").longValue();
        for (String str : split) {
            arrayList.add(convertToParticipantsDAO(longValue, str, lastReadStatusMap.containsKey(str) ? lastReadStatusMap.get(str).intValue() : 0));
        }
        return arrayList;
    }
}
