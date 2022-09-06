package com.amazon.deecomms.messaging.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.CommsDatabaseHelper;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.messaging.database.DAOMapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.amazon.deecomms.messaging.util.MessageUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteQueryBuilder;
/* loaded from: classes12.dex */
public class MessagingContentProvider extends ContentProvider {
    private static final int CONVERSATION = 0;
    private static final int CONVERSATIONS = 1;
    private static final int CONVERSATION_SYNC = 6;
    private static final int COUNT_UNREAD_MESSAGES = 5;
    private static final int LAST_UPDATED_SERVER_TIME_CONVERSATION = 7;
    private static final int MESSAGES = 2;
    private static final int MOST_RECENT_MESSAGE = 4;
    private static final int PARTICIPANTS_COMMSID_LIST_IN_CONVERSATION = 9;
    private static final int PARTICIPANTS_NOT_IN_CONTACTS = 8;
    private static final int PARTICIPANTS_WITH_NAMES = 3;
    private static final String SEP = "<><><>";
    private CommsDatabaseHelper mCommsDbHelper;
    private final UriMatcher sUriMatcher = new UriMatcher(-1);
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingContentProvider.class);
    private static final String[] CONVERSATION_WITH_CONTACTS_PROJECTION = {"_id", "conversation_id", "recipient_id", "last_msg", "last_msg_id", "last_sequence_id", "last_msg_type", "unread_msg_count", "unread_notif_count", "send_as_comms_id", "view_as_comms_id", "last_modified_timestamp", "last_sync_msg_id", "server_last_msg_id", "server_last_modified_timestamp", "last_msg_sender_id", "group_concat(IFNULL(participant_comms_id, ''), '<><><>') AS participants_list", "group_concat(IFNULL(Contacts.firstName, ''), '<><><>') AS converser_first_name", "group_concat(IFNULL(Contacts.lastName, ''), '<><><>') AS converser_last_name", "group_concat(IFNULL(Contacts.nickName, ''), '<><><>') AS converser_nick_name", "group_concat(IFNULL(Contacts.company, ''), '<><><>') AS converser_company_name", "group_concat(IFNULL(ContactsAlias.canIDropInOnHim, Contacts.canIDropInOnHim), '<><><>') AS participants_drop_in_permission_list", "group_concat(IFNULL(PhoneNumbers.isHomeGroup, '-1'), '<><><>') AS participants_homegroup_list"};
    private static final String[] MESSAGES_PROJECTION = {"_id", "client_id", "conversation_id", "message_id", "client_message_id", "recipient_id", "sender_comms_id", "_type", "_time", "_payload", "sync_status", "view_as_comms_id"};
    private static final String[] COUNT_UNREAD_PROJECTION = {"SUM (unread_msg_count) AS unread_count"};
    private static final Map<String, String> sConversationsProjectionMap = new HashMap();

    static {
        sConversationsProjectionMap.put("conversation_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_ID);
        sConversationsProjectionMap.put("_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_UNIQUE_ID);
        sConversationsProjectionMap.put("recipient_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_RECIPIENT_ID);
        sConversationsProjectionMap.put("last_msg", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG);
        sConversationsProjectionMap.put("last_msg_type", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_TYPE);
        sConversationsProjectionMap.put("last_msg_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_ID);
        sConversationsProjectionMap.put("last_sequence_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_SEQUENCE_ID);
        sConversationsProjectionMap.put("last_modified_timestamp", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_MODIFIED_TIMESTAMP);
        sConversationsProjectionMap.put("unread_msg_count", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_UNREAD_MSG_COUNT);
        sConversationsProjectionMap.put("send_as_comms_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_SEND_AS_COMMS_ID);
        sConversationsProjectionMap.put("view_as_comms_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_VIEW_AS_COMMS_ID);
        sConversationsProjectionMap.put("unread_notif_count", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_UNREAD_NOTIF_COUNT);
        sConversationsProjectionMap.put("server_last_modified_timestamp", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_SERVER_LAST_MODIFIED_TIMESTAMP);
        sConversationsProjectionMap.put("server_last_msg_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_SERVER_LAST_MSG_ID);
        sConversationsProjectionMap.put("last_sync_msg_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_SYNC_MSG_ID);
        sConversationsProjectionMap.put("last_msg_sender_id", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_LAST_MSG_SENDER_ID);
    }

    private ContentValues convertToSyncUpdateContentValues(ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("server_last_msg_id", contentValues.getAsInteger("server_last_msg_id"));
        contentValues2.put("last_msg_sender_id", contentValues.getAsString("last_msg_sender_id"));
        contentValues2.put("server_last_modified_timestamp", contentValues.getAsLong("server_last_modified_timestamp"));
        contentValues2.put("unread_msg_count", contentValues.getAsInteger("unread_msg_count"));
        contentValues2.put("unread_notif_count", contentValues.getAsInteger("unread_notif_count"));
        return contentValues2;
    }

    private Cursor countUnreadMessages(Uri uri) {
        LOG.d("In count unread messages query");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("conversations");
        net.sqlcipher.Cursor query = sQLiteQueryBuilder.query(this.mCommsDbHelper.getReadableDatabase(), COUNT_UNREAD_PROJECTION, MessageUtils.getSelectQueryForFilteredAndDeprovisionedUsers(Collections.emptyList()), null, null, null, null);
        if (getContext() != null) {
            query.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return query;
    }

    private int deleteConversations(String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            if (!TextUtils.isEmpty(str)) {
                Cursor conversationCursor = getConversationCursor(str, strArr, null);
                if (conversationCursor != null) {
                    while (conversationCursor.moveToNext()) {
                        writableDatabase.delete("participants", MessagingProviderContract.Participants.PARTICIPANTS_WHERE_CLAUSE_CONVERSATION, new String[]{String.valueOf(conversationCursor.getLong(conversationCursor.getColumnIndex("_id")))});
                    }
                }
                if (conversationCursor != null) {
                    conversationCursor.close();
                }
            } else {
                writableDatabase.delete("participants", str, strArr);
            }
            int delete = writableDatabase.delete("conversations", str, strArr);
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
            }
            return delete;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private int deleteIntoMessages(String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            int delete = writableDatabase.delete("messages", str, strArr);
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Messages.getContentUri(getContext()), null);
            }
            return delete;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private Cursor executeQuery(Uri uri, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str);
        net.sqlcipher.Cursor query = sQLiteQueryBuilder.query(this.mCommsDbHelper.getReadableDatabase(), strArr, str2, strArr2, str3, str4, str5);
        if (getContext() != null) {
            query.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return query;
    }

    private Cursor getConversationCursor(String str, String[] strArr, String[] strArr2) {
        LOG.d("In simple conversation cursor");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("conversations");
        net.sqlcipher.Cursor query = sQLiteQueryBuilder.query(this.mCommsDbHelper.getReadableDatabase(), strArr2, str, strArr, null, null, null, null);
        if (getContext() != null) {
            query.setNotificationUri(getContext().getContentResolver(), MessagingProviderContract.Conversations.getConversationContentUri(getContext()));
        }
        return query;
    }

    private Cursor getConversationLastUpdatedServerTime(Uri uri, String str, String[] strArr) {
        return executeQuery(uri, "conversations", MessagingProviderContract.Conversations.LAST_UPDATED_SERVER_TIME_PROJECTION, str, strArr, null, null, null);
    }

    private Cursor getConversationsCursor(String str, String[] strArr, String[] strArr2, Map<String, String> map) {
        LOG.i("In conversations Joined with three other tables query");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("conversations INNER JOIN participants ON (conversations._id = participants.conversation_key_id) LEFT JOIN PhoneNumbers ON (participants.participant_comms_id = PhoneNumbers.commsId) LEFT JOIN Contacts ON (PhoneNumbers.serverContactId = Contacts.serverContactId) LEFT JOIN PhoneNumbers AS PhoneNumbersAlias ON PhoneNumbers.parentHomeGroup = PhoneNumbersAlias.commsId LEFT JOIN Contacts AS ContactsAlias ON PhoneNumbersAlias.serverContactId = ContactsAlias.serverContactId");
        sQLiteQueryBuilder.setProjectionMap(map);
        net.sqlcipher.Cursor query = sQLiteQueryBuilder.query(this.mCommsDbHelper.getReadableDatabase(), strArr2, str, strArr, "conversations.conversation_id , conversations.recipient_id", null, "last_modified_timestamp DESC", null);
        if (getContext() != null) {
            query.setNotificationUri(getContext().getContentResolver(), MessagingProviderContract.Conversations.getContentUri(getContext()));
        }
        return query;
    }

    private Cursor getMessagesCursor(Uri uri, String str, String[] strArr, String str2) {
        LOG.d("In Messages Query");
        return executeQuery(uri, "messages", MESSAGES_PROJECTION, str, strArr, null, null, str2);
    }

    private Cursor getMostRecentMessageCursor(Uri uri, String str, String[] strArr) {
        LOG.d("In Most recent message query");
        return executeQuery(uri, "messages", MessagingProviderContract.Messages.MOST_RECENT_MESSAGE_PROJECTION, str, strArr, null, null, null);
    }

    private Cursor getParticipantCommsIdList(String[] strArr) {
        return this.mCommsDbHelper.getReadableDatabase().rawQuery(String.format("select %s from %s INNER JOIN %s ON %s == %s where %s = ?", MessagingProviderContract.Participants.COLUMN_NAME_WITH_TABLE_NAME_PARTICIPANT_COMMS_ID, "conversations", "participants", MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_UNIQUE_ID, MessagingProviderContract.Participants.COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_KEY_ID, MessagingProviderContract.Conversations.COLUMN_NAME_WITH_TABLE_NAME_CONVERSATION_ID), strArr);
    }

    private Cursor getParticipantsCursor(String str, String[] strArr, String[] strArr2) {
        LOG.i("In participants Joined with two other tables query");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("participants LEFT JOIN PhoneNumbers ON (participants.participant_comms_id = PhoneNumbers.commsId) LEFT JOIN Contacts ON (PhoneNumbers.serverContactId = Contacts.serverContactId) LEFT JOIN PhoneNumbers AS PhoneNumbersAlias ON PhoneNumbers.parentHomeGroup = PhoneNumbersAlias.commsId LEFT JOIN Contacts AS ContactsAlias ON PhoneNumbersAlias.serverContactId = ContactsAlias.serverContactId");
        net.sqlcipher.Cursor query = sQLiteQueryBuilder.query(this.mCommsDbHelper.getReadableDatabase(), strArr2, str, strArr, null, null, null, null);
        if (getContext() != null) {
            query.setNotificationUri(getContext().getContentResolver(), MessagingProviderContract.Conversations.getContentUri(getContext()));
        }
        return query;
    }

    private Cursor getParticipantsNotInContacts() {
        return this.mCommsDbHelper.getReadableDatabase().rawQuery(" SELECT participants.participant_comms_id FROM participants WHERE participants.participant_comms_id NOT IN ( SELECT PhoneNumbers.commsId FROM PhoneNumbers )", (String[]) null);
    }

    private Uri insertConversation(ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        Uri insertConversationAndParticipants = insertConversationAndParticipants(writableDatabase, contentValues);
        if (insertConversationAndParticipants != null) {
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
            }
        }
        writableDatabase.endTransaction();
        return insertConversationAndParticipants;
    }

    private Uri insertConversationAndParticipants(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        try {
            Uri insertIntoConversations = insertIntoConversations(contentValues, sQLiteDatabase);
            contentValues.put("_id", Long.valueOf(ContentUris.parseId(insertIntoConversations)));
            insertIntoParticipants(contentValues, sQLiteDatabase);
            return insertIntoConversations;
        } catch (SQLException e) {
            LOG.e("Exception occurred in conversations and participants insert", e);
            return null;
        }
    }

    private int insertConversations(ContentValues[] contentValuesArr) {
        if (contentValuesArr == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            int i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                if (insertConversationAndParticipants(writableDatabase, contentValues) != null) {
                    i++;
                }
            }
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
            }
            writableDatabase.setTransactionSuccessful();
            return i;
        } catch (SQLException e) {
            LOG.e("Exception occurred in conversations and participants bulkInsert", e);
            return 0;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private Uri insertIntoConversations(ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        return ContentUris.withAppendedId(MessagingProviderContract.Conversations.getContentUri(getContext()), sQLiteDatabase.insertWithOnConflict("conversations", null, DAOMapper.convertToConversationDAO(contentValues), 5));
    }

    private int insertIntoMessages(ContentValues[] contentValuesArr) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            int i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                try {
                    writableDatabase.insertOrThrow("messages", null, contentValues);
                    i++;
                } catch (SQLException unused) {
                    LOG.w("exception while inserting an entry.");
                }
            }
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Messages.getContentUri(getContext()), null);
            }
            return i;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private int insertIntoParticipants(ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        for (ContentValues contentValues2 : DAOMapper.convertToParticipantsDAOList(contentValues)) {
            sQLiteDatabase.insert("participants", null, contentValues2);
            i++;
        }
        return i;
    }

    private void insertOrReplaceParticipants(ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        Long asLong = contentValues.getAsLong("_id");
        if (asLong != null) {
            sQLiteDatabase.delete("participants", MessagingProviderContract.Participants.PARTICIPANTS_WHERE_CLAUSE_CONVERSATION, new String[]{String.valueOf(asLong)});
        }
        insertIntoParticipants(contentValues, sQLiteDatabase);
    }

    private int insertOrUpdateConversationsSync(ContentValues[] contentValuesArr) {
        String str;
        String str2;
        String str3;
        int i;
        String[] strArr;
        ContentValues[] contentValuesArr2 = contentValuesArr;
        String str4 = "send_as_comms_id";
        String str5 = "recipient_id";
        String str6 = "";
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            int length = contentValuesArr2.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                ContentValues contentValues = contentValuesArr2[i2];
                ContentValues convertToSyncUpdateContentValues = convertToSyncUpdateContentValues(contentValues);
                String asString = contentValues.getAsString("conversation_id");
                ArrayList arrayList = new ArrayList();
                StringBuilder sb = new StringBuilder();
                sb.append(str6);
                int i4 = length;
                sb.append(DatabaseUtils.appendSelection("conversation_id", asString, arrayList));
                String sb2 = sb.toString();
                String[] strArr2 = arrayList.size() == 0 ? null : (String[]) arrayList.toArray(new String[arrayList.size()]);
                int update = writableDatabase.update("conversations", convertToSyncUpdateContentValues, sb2, strArr2);
                if (update == 0) {
                    convertToSyncUpdateContentValues.put("conversation_id", contentValues.getAsString("conversation_id"));
                    ArrayList arrayList2 = new ArrayList();
                    String asString2 = contentValues.getAsString(str5);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str6);
                    str3 = str6;
                    sb3.append(DatabaseUtils.appendSelection(str5, asString2, arrayList2));
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(sb4);
                    sb5.append(" AND ");
                    String sb6 = sb5.toString();
                    String asString3 = contentValues.getAsString(str4);
                    str2 = str5;
                    String str7 = sb6 + DatabaseUtils.appendSelection(str4, asString3, arrayList2);
                    String[] strArr3 = arrayList2.size() == 0 ? null : (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                    i = writableDatabase.update("conversations", convertToSyncUpdateContentValues, str7, strArr3);
                    if (i > 0) {
                        ContentValues contentValues2 = new ContentValues();
                        strArr = strArr3;
                        contentValues2.put("conversation_id", contentValues.getAsString("conversation_id"));
                        str = str4;
                        writableDatabase.update("messages", contentValues2, "recipient_id = ? AND view_as_comms_id = ? ", new String[]{asString2, asString3});
                    } else {
                        strArr = strArr3;
                        str = str4;
                    }
                    sb2 = str7;
                    strArr2 = strArr;
                } else {
                    str = str4;
                    str2 = str5;
                    str3 = str6;
                    i = update;
                }
                if (i == 0) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("Entry not present for convId: ");
                    sb7.append(asString);
                    commsLogger.i(sb7.toString());
                    insertConversationAndParticipants(writableDatabase, contentValues);
                    i3++;
                } else {
                    CommsLogger commsLogger2 = LOG;
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("Updating the existing entry for convId: ");
                    sb8.append(asString);
                    commsLogger2.i(sb8.toString());
                    if (contentValues.containsKey(MessagingProviderContract.Conversations.PARTICIPANTS_LIST) && contentValues.containsKey(MessagingProviderContract.Conversations.LAST_READ_STATUS_LIST)) {
                        Cursor conversationCursor = getConversationCursor(sb2, strArr2, null);
                        if (conversationCursor != null) {
                            if (conversationCursor.moveToNext()) {
                                contentValues.put("_id", Long.valueOf(conversationCursor.getLong(conversationCursor.getColumnIndex("_id"))));
                            }
                            insertOrReplaceParticipants(contentValues, writableDatabase);
                        }
                        if (conversationCursor != null) {
                            conversationCursor.close();
                        }
                    }
                }
                i2++;
                contentValuesArr2 = contentValuesArr;
                length = i4;
                str6 = str3;
                str5 = str2;
                str4 = str;
            }
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
            }
            return i3;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private int updateIntoConversationsAndParticipants(ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            int update = writableDatabase.update("conversations", DAOMapper.convertToConversationDAO(contentValues), str, strArr);
            if (contentValues.containsKey(MessagingProviderContract.Conversations.PARTICIPANTS_LIST) && contentValues.containsKey(MessagingProviderContract.Conversations.LAST_READ_STATUS_LIST)) {
                Cursor conversationCursor = getConversationCursor(str, strArr, null);
                if (conversationCursor != null) {
                    if (conversationCursor.moveToNext()) {
                        contentValues.put("_id", Long.valueOf(conversationCursor.getLong(conversationCursor.getColumnIndex("_id"))));
                    }
                    insertOrReplaceParticipants(contentValues, writableDatabase);
                }
                if (conversationCursor != null) {
                    conversationCursor.close();
                }
            }
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Conversations.getContentUri(getContext()), null);
            }
            return update;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private int updateIntoMessages(ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            int update = writableDatabase.update("messages", contentValues, str, strArr);
            writableDatabase.setTransactionSuccessful();
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(MessagingProviderContract.Messages.getContentUri(getContext()), null);
            }
            return update;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @VisibleForTesting
    void addUri(UriMatcher uriMatcher) {
        String authorityId = MessagingProviderContract.getAuthorityId(getContext());
        uriMatcher.addURI(authorityId, "conversations", 1);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Conversations.CONVERSATION_URI_PATH, 0);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Conversations.CONVERSATION_SYNC_URI_PATH, 6);
        uriMatcher.addURI(authorityId, "messages", 2);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Participants.PARTICIPANT_DETAILS_URI_PATH, 3);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Messages.MOST_RECENT_MESSAGE_URI_PATH, 4);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Conversations.COUNT_UNREAD_MESSAGES_PATH, 5);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Conversations.LAST_UPDATED_SERVER_TIME, 7);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Participants.PARTICIPANTS_NOT_IN_CONTACTS, 8);
        uriMatcher.addURI(authorityId, MessagingProviderContract.Participants.PARTICIPANTS_COMMSID_IN_CONVERSATION, 9);
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] contentValuesArr) {
        int match = this.sUriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                return insertIntoMessages(contentValuesArr);
            }
            if (match == 6) {
                return insertOrUpdateConversationsSync(contentValuesArr);
            }
            return 0;
        }
        return insertConversations(contentValuesArr);
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        int match = this.sUriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                return deleteIntoMessages(str, strArr);
            }
            return 0;
        }
        return deleteConversations(str, strArr);
    }

    @Override // android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        int match = this.sUriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                return MessagingProviderContract.Messages.MIME_TYPE;
            }
            return null;
        }
        return MessagingProviderContract.Conversations.MIME_TYPE;
    }

    @Override // android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        int match = this.sUriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                return insertIntoMessages(contentValues);
            }
            return null;
        }
        return insertConversation(contentValues);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mCommsDbHelper = CommsDatabaseHelper.getInstance(getContext());
        addUri(this.sUriMatcher);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        switch (this.sUriMatcher.match(uri)) {
            case 0:
                return getConversationCursor(str, strArr2, strArr);
            case 1:
                return getConversationsCursor(str, strArr2, CONVERSATION_WITH_CONTACTS_PROJECTION, sConversationsProjectionMap);
            case 2:
                return getMessagesCursor(uri, str, strArr2, str2);
            case 3:
                return getParticipantsCursor(str, strArr2, MessagingProviderContract.Participants.GET_PARTICIPANTS_NAMES_PROJECTION_WITH_ALIAS);
            case 4:
                return getMostRecentMessageCursor(uri, str, strArr2);
            case 5:
                return countUnreadMessages(uri);
            case 6:
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline58("Unknown URI: ", uri));
            case 7:
                return getConversationLastUpdatedServerTime(uri, str, strArr2);
            case 8:
                return getParticipantsNotInContacts();
            case 9:
                return getParticipantCommsIdList(strArr2);
        }
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = this.sUriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                return updateIntoMessages(contentValues, str, strArr);
            }
            return 0;
        }
        return updateIntoConversationsAndParticipants(contentValues, str, strArr);
    }

    private Uri insertIntoMessages(ContentValues contentValues) {
        long insertOrThrow;
        SQLiteDatabase writableDatabase = this.mCommsDbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            insertOrThrow = writableDatabase.insertOrThrow("messages", null, contentValues);
            writableDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            LOG.e("SQLException occurred in messages insert method insertIntoMessages", e);
        } finally {
            writableDatabase.endTransaction();
        }
        if (insertOrThrow > 0) {
            Uri withAppendedId = ContentUris.withAppendedId(MessagingProviderContract.Messages.getContentUri(getContext()), insertOrThrow);
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(withAppendedId, null);
            }
            return withAppendedId;
        }
        return null;
    }
}
