package com.amazon.deecomms.messaging.provider;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.TimePeriodHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Conversation;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class MessagingProviderWrapper {
    private static final long CLIENT_ID_DEFAULT_DB_VALUE = 0;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingContentProvider.class);
    private Context mContext;
    private String mHomeGroupId;
    private String mRegisteredCommsId;
    @Inject
    TranscriptLatencyMetricHelper mTranscriptLatencyMetricHelper;
    private IHttpClient.JSONConverter mJsonConverter = new JacksonJSONConverter();
    private TimePeriodHelper mTimePeriodHelper = new TimePeriodHelper();

    public MessagingProviderWrapper(Context context, String str, String str2) {
        this.mContext = context;
        this.mRegisteredCommsId = str;
        this.mHomeGroupId = str2;
        CommsDaggerWrapper.getComponent().inject(this);
    }

    public static void addClientMessageId(@NonNull Message message, @NonNull ContentValues contentValues) {
        if (message.getClientMessageId() != null) {
            contentValues.put("client_message_id", message.getClientMessageId());
        }
    }

    private ContentValues convertClientConversation(ClientConversation clientConversation) {
        ContentValues convertConversationCommonProperties = convertConversationCommonProperties(clientConversation);
        String recipientId = clientConversation.getRecipientId();
        if (recipientId == null) {
            recipientId = "";
        }
        convertConversationCommonProperties.put("recipient_id", recipientId);
        convertConversationCommonProperties.put("_id", Long.valueOf(clientConversation.getUniqueId()));
        convertConversationCommonProperties.put("server_last_modified_timestamp", Long.valueOf(this.mTimePeriodHelper.convertTimestampStringToMills(clientConversation.getServerLastModifiedTime())));
        convertConversationCommonProperties.put("server_last_msg_id", Long.valueOf(clientConversation.getServerLastMsgId()));
        convertConversationCommonProperties.put("last_sync_msg_id", Long.valueOf(clientConversation.getLastSyncMsgId()));
        convertConversationCommonProperties.put("last_msg_sender_id", clientConversation.getLastMsgSender());
        convertConversationCommonProperties.put("last_msg_type", clientConversation.getLastMsgType());
        return convertConversationCommonProperties;
    }

    private ContentValues convertClientMessage(ClientMessage clientMessage) {
        ContentValues convertMessageCommonProperties = convertMessageCommonProperties(clientMessage);
        convertMessageCommonProperties.put("client_id", Long.valueOf(clientMessage.getClientID()));
        convertMessageCommonProperties.put("sync_status", Integer.valueOf(clientMessage.getSyncStatus()));
        String recipientID = clientMessage.getRecipientID();
        if (recipientID != null) {
            convertMessageCommonProperties.put("recipient_id", recipientID);
        } else {
            convertMessageCommonProperties.put("recipient_id", "");
        }
        return convertMessageCommonProperties;
    }

    private ContentValues convertConversation(Conversation conversation) {
        ContentValues convertConversationCommonProperties = convertConversationCommonProperties(conversation);
        String recipientIDFromParticipants = getRecipientIDFromParticipants(conversation.getParticipants(), this.mRegisteredCommsId, this.mHomeGroupId);
        if (recipientIDFromParticipants == null) {
            recipientIDFromParticipants = "";
        }
        convertConversationCommonProperties.put("recipient_id", recipientIDFromParticipants);
        return convertConversationCommonProperties;
    }

    private ContentValues convertConversationCommonProperties(Conversation conversation) {
        String str;
        ContentValues contentValues = new ContentValues();
        String conversationId = conversation.getConversationId();
        if (conversationId == null) {
            conversationId = "";
        }
        contentValues.put("conversation_id", conversationId);
        contentValues.put("last_msg_id", Long.valueOf(conversation.getLastMsgId()));
        contentValues.put("last_sequence_id", Long.valueOf(conversation.getLastSequenceId()));
        Message lastMsg = conversation.getLastMsg();
        String str2 = null;
        if (lastMsg != null) {
            if (lastMsg.getPayload() != null) {
                str2 = lastMsg.getPayload().getSummaryText(this.mContext);
            }
            str = lastMsg.getSenderCommsId();
        } else {
            str = null;
        }
        contentValues.put("last_msg", str2);
        contentValues.put("last_msg_sender_id", str);
        contentValues.put("unread_notif_count", Integer.valueOf(conversation.getUnreadNotificationCount()));
        contentValues.put("unread_msg_count", Integer.valueOf(conversation.getUnreadMsgCount()));
        contentValues.put("last_modified_timestamp", Long.valueOf(this.mTimePeriodHelper.convertTimestampStringToMills(conversation.getLastModifiedTimestamp())));
        contentValues.put(MessagingProviderContract.Conversations.PARTICIPANTS_LIST, TextUtils.join(Constants.GROUP_CONCAT_SEPARATOR, conversation.getParticipants()));
        contentValues.put("send_as_comms_id", conversation.getSendAsCommsId());
        contentValues.put("view_as_comms_id", conversation.getViewAsCommsId());
        if (conversation.getLastReadStatusMap() != null && conversation.getLastReadStatusMap().containsKey(CommsInternal.getInstance().getCommsId())) {
            contentValues.put("last_read_msg_id", conversation.getLastReadStatusMap().get(CommsInternal.getInstance().getCommsId()));
        }
        contentValues.put(MessagingProviderContract.Conversations.LAST_READ_STATUS_LIST, getLastReadStatusString(conversation.getLastReadStatusMap()));
        return contentValues;
    }

    private ContentValues[] convertConversations(Conversation[] conversationArr) {
        if (conversationArr == null) {
            return null;
        }
        ContentValues[] contentValuesArr = new ContentValues[conversationArr.length];
        for (int i = 0; i < conversationArr.length; i++) {
            contentValuesArr[i] = convertConversation(conversationArr[i]);
        }
        return contentValuesArr;
    }

    private ContentValues convertMessage(Message message, String str, String str2) {
        ContentValues convertMessageCommonProperties = convertMessageCommonProperties(message);
        convertMessageCommonProperties.put("client_id", (Long) 0L);
        convertMessageCommonProperties.put("sync_status", (Integer) 0);
        if (str != null) {
            convertMessageCommonProperties.put("recipient_id", str);
        } else {
            convertMessageCommonProperties.put("recipient_id", "");
        }
        if (str2 != null) {
            convertMessageCommonProperties.put("view_as_comms_id", str2);
        }
        return convertMessageCommonProperties;
    }

    private ContentValues convertMessageCommonProperties(Message message) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("conversation_id", message.getConversationId());
        contentValues.put("message_id", Long.valueOf(message.getMessageId()));
        contentValues.put("_time", Long.valueOf(this.mTimePeriodHelper.convertTimestampStringToMills(message.getTime())));
        contentValues.put("_type", message.getType());
        contentValues.put("sender_comms_id", message.getSenderCommsId());
        contentValues.put("_payload", this.mJsonConverter.toJson(message.getPayload()));
        addClientMessageId(message, contentValues);
        return contentValues;
    }

    private String getLastReadStatusString(Map<String, Integer> map) {
        return this.mJsonConverter.toJson(map);
    }

    public static String getRecipientIDFromParticipants(String[] strArr, String str, String str2) {
        if (strArr == null) {
            return null;
        }
        if (strArr.length == 1 && str.contentEquals(strArr[0])) {
            return str;
        }
        if (strArr.length == 1 && str2.contentEquals(strArr[0])) {
            return str2;
        }
        if (strArr.length != 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        if (arrayList.contains(str)) {
            arrayList.remove(str);
        } else if (arrayList.contains(str2)) {
            arrayList.remove(str2);
        }
        return (String) arrayList.get(0);
    }

    public void deleteAllConversations() {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(MessagingProviderContract.Conversations.getContentUri(this.mContext)).build());
        arrayList.add(ContentProviderOperation.newDelete(MessagingProviderContract.Messages.getContentUri(this.mContext)).build());
        try {
            this.mContext.getContentResolver().applyBatch(MessagingProviderContract.getAuthorityId(this.mContext), arrayList);
        } catch (OperationApplicationException | RemoteException e) {
            LOG.e("Error clearing messages database.", e);
        }
    }

    public int deleteClientMessage(long j) {
        return this.mContext.getContentResolver().delete(MessagingProviderContract.Messages.getContentUri(this.mContext), "_id = ?", new String[]{String.valueOf(j)});
    }

    public int deleteConversation(long j) {
        return this.mContext.getContentResolver().delete(MessagingProviderContract.Conversations.getContentUri(this.mContext), "_id = ?", new String[]{String.valueOf(j)});
    }

    public ClientConversation getClientConversation(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("conversation_id"));
        String string2 = cursor.getString(cursor.getColumnIndex("recipient_id"));
        long j = cursor.getLong(cursor.getColumnIndex("_id"));
        String string3 = cursor.getString(cursor.getColumnIndex("last_msg"));
        String string4 = cursor.getString(cursor.getColumnIndex("last_msg_type"));
        long j2 = cursor.getLong(cursor.getColumnIndex("last_modified_timestamp"));
        int i = cursor.getInt(cursor.getColumnIndex("unread_msg_count"));
        long j3 = cursor.getLong(cursor.getColumnIndex("last_msg_id"));
        long j4 = cursor.getLong(cursor.getColumnIndex("last_sequence_id"));
        int i2 = cursor.getInt(cursor.getColumnIndex("unread_notif_count"));
        String string5 = cursor.getString(cursor.getColumnIndex("send_as_comms_id"));
        String string6 = cursor.getString(cursor.getColumnIndex("view_as_comms_id"));
        long j5 = cursor.getLong(cursor.getColumnIndex("server_last_modified_timestamp"));
        int i3 = cursor.getInt(cursor.getColumnIndex("server_last_msg_id"));
        long j6 = cursor.getLong(cursor.getColumnIndex("last_sync_msg_id"));
        String string7 = cursor.getString(cursor.getColumnIndex("last_msg_sender_id"));
        long j7 = cursor.getLong(cursor.getColumnIndex("last_read_msg_id"));
        long j8 = cursor.getLong(cursor.getColumnIndex("read_message_locally_only"));
        ClientConversation clientConversation = new ClientConversation();
        clientConversation.setUniqueId(j);
        clientConversation.setRecipientId(string2);
        clientConversation.setConversationId(string);
        clientConversation.setLastMsgId(j3);
        clientConversation.setLastSequenceId(j4);
        clientConversation.setLastMsgStr(string3);
        clientConversation.setLastMsgType(string4);
        clientConversation.setLastModifiedTimestamp(this.mTimePeriodHelper.convertToISOFormat(j2));
        clientConversation.setUnreadMsgCount(i);
        clientConversation.setSendAsCommsId(string5);
        clientConversation.setViewAsCommsId(string6);
        clientConversation.setUnreadNotificationCount(i2);
        clientConversation.setServerLastModifiedTime(this.mTimePeriodHelper.convertToISOFormat(j5));
        clientConversation.setServerLastMsgId(i3);
        clientConversation.setLastSyncMsgId(j6);
        clientConversation.setLastMsgSender(string7);
        clientConversation.setLastReadMsgId(j7);
        clientConversation.setReadMsgLocallyOnly(j8);
        return clientConversation;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.deecomms.messaging.model.client.ClientConversation getConversation(java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = ""
            r2 = 0
            if (r10 == 0) goto L20
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r1)
            java.lang.String r12 = "conversations.conversation_id = ?"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r0.add(r10)
        L1e:
            r6 = r11
            goto L54
        L20:
            if (r11 == 0) goto L91
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r1 = "conversations.recipient_id = ?"
            r10.append(r1)
            java.lang.String r10 = r10.toString()
            r0.add(r11)
            if (r12 == 0) goto L53
            java.lang.String r11 = " AND "
            java.lang.String r10 = com.android.tools.r8.GeneratedOutlineSupport.outline0(r10, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r10 = "conversations.send_as_comms_id = ?"
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            r0.add(r12)
            goto L1e
        L53:
            r6 = r10
        L54:
            android.content.Context r10 = r9.mContext
            android.content.ContentResolver r3 = r10.getContentResolver()
            android.content.Context r10 = r9.mContext
            android.net.Uri r4 = com.amazon.deecomms.messaging.provider.MessagingProviderContract.Conversations.getConversationContentUri(r10)
            int r10 = r0.size()
            java.lang.String[] r10 = new java.lang.String[r10]
            java.lang.Object[] r10 = r0.toArray(r10)
            r7 = r10
            java.lang.String[] r7 = (java.lang.String[]) r7
            r5 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            if (r10 == 0) goto L8c
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L80
            if (r11 == 0) goto L8c
            com.amazon.deecomms.messaging.model.client.ClientConversation r2 = r9.getClientConversation(r10)     // Catch: java.lang.Throwable -> L80
            goto L8c
        L80:
            r11 = move-exception
            throw r11     // Catch: java.lang.Throwable -> L82
        L82:
            r12 = move-exception
            r10.close()     // Catch: java.lang.Throwable -> L87
            goto L8b
        L87:
            r10 = move-exception
            r11.addSuppressed(r10)
        L8b:
            throw r12
        L8c:
            if (r10 == 0) goto L91
            r10.close()
        L91:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.messaging.provider.MessagingProviderWrapper.getConversation(java.lang.String, java.lang.String, java.lang.String):com.amazon.deecomms.messaging.model.client.ClientConversation");
    }

    public long getLastUpdatedServerTimeForConversations() {
        Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Conversations.getLastUpdatedServerTimeUri(this.mContext), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    long j = query.getLong(query.getColumnIndex(MessagingProviderContract.Conversations.LAST_UPDATED_SERVER_TIME_ALIAS));
                    query.close();
                    return j;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return -1L;
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getLatestReadMessage(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 1
            java.lang.String[] r5 = new java.lang.String[r0]
            r0 = 0
            r5[r0] = r8
            android.content.Context r8 = r7.mContext
            android.content.ContentResolver r1 = r8.getContentResolver()
            android.content.Context r8 = r7.mContext
            android.net.Uri r2 = com.amazon.deecomms.messaging.provider.MessagingProviderContract.Messages.getMostRecentMessageContentUri(r8)
            r3 = 0
            java.lang.String r4 = "conversation_id = ?"
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            if (r8 == 0) goto L39
            boolean r0 = r8.moveToNext()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L39
            java.lang.String r0 = "max_message_id"
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2d
            long r0 = r8.getLong(r0)     // Catch: java.lang.Throwable -> L2d
            goto L3b
        L2d:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L2f
        L2f:
            r1 = move-exception
            r8.close()     // Catch: java.lang.Throwable -> L34
            goto L38
        L34:
            r8 = move-exception
            r0.addSuppressed(r8)
        L38:
            throw r1
        L39:
            r0 = -1
        L3b:
            if (r8 == 0) goto L40
            r8.close()
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.messaging.provider.MessagingProviderWrapper.getLatestReadMessage(java.lang.String):long");
    }

    public <T extends MessagePayload> T getMessagePayload(@NonNull String str, long j, @NonNull Class<T> cls) {
        T t = null;
        try {
            Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Messages.getContentUri(this.mContext), new String[]{"_payload"}, "conversation_id = ? AND message_id = ? AND _type = ?", new String[]{str, String.valueOf(j), cls.newInstance().getType()}, null);
            if (query != null) {
                try {
                    int columnIndex = query.getColumnIndex("_payload");
                    if (columnIndex != -1 && query.moveToFirst()) {
                        t = (T) this.mJsonConverter.fromJson(query.getString(columnIndex), cls);
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
            return t;
        } catch (IllegalAccessException unused) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Cannot instantiate an object of class " + cls);
            return null;
        } catch (InstantiationException unused2) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e("Cannot instantiate an object of class " + cls);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getMessageType(@androidx.annotation.NonNull java.lang.String r8, long r9) {
        /*
            r7 = this;
            java.lang.String r0 = "_type"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r1 = 2
            java.lang.String[] r5 = new java.lang.String[r1]
            r1 = 0
            r5[r1] = r8
            java.lang.String r8 = java.lang.String.valueOf(r9)
            r9 = 1
            r5[r9] = r8
            android.content.Context r8 = r7.mContext
            android.content.ContentResolver r1 = r8.getContentResolver()
            android.content.Context r8 = r7.mContext
            android.net.Uri r2 = com.amazon.deecomms.messaging.provider.MessagingProviderContract.Messages.getContentUri(r8)
            java.lang.String r4 = "conversation_id = ? AND message_id = ?"
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            if (r8 == 0) goto L46
            int r9 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3a
            r10 = -1
            if (r9 == r10) goto L46
            boolean r10 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L3a
            if (r10 == 0) goto L46
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L3a
            goto L47
        L3a:
            r9 = move-exception
            throw r9     // Catch: java.lang.Throwable -> L3c
        L3c:
            r10 = move-exception
            r8.close()     // Catch: java.lang.Throwable -> L41
            goto L45
        L41:
            r8 = move-exception
            r9.addSuppressed(r8)
        L45:
            throw r10
        L46:
            r9 = 0
        L47:
            if (r8 == 0) goto L4c
            r8.close()
        L4c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.messaging.provider.MessagingProviderWrapper.getMessageType(java.lang.String, long):java.lang.String");
    }

    @NonNull
    public List<String> getParticipantsInConversation(@NonNull String str) {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Participants.getParticipantsCommsidInConversationUri(this.mContext), null, null, new String[]{str}, null);
        try {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                arrayList.add(query.getString(0));
                query.moveToNext();
            }
            query.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public List<ClientConversation> getUnsyncedConversations() {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.mContext.getContentResolver().query(MessagingProviderContract.Conversations.getConversationContentUri(this.mContext), null, GeneratedOutlineSupport1.outline77("last_sync_msg_id", " is NULL OR ", "last_sync_msg_id", " < ", "server_last_msg_id"), null, null);
        while (query.moveToNext()) {
            try {
                arrayList.add(getClientConversation(query));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        query.close();
        return arrayList;
    }

    public long insertClientConversationAndParticipants(ClientConversation clientConversation) {
        if (clientConversation == null) {
            return 0L;
        }
        return ContentUris.parseId(this.mContext.getContentResolver().insert(MessagingProviderContract.Conversations.getContentUri(this.mContext), convertClientConversation(clientConversation)));
    }

    public long insertClientMessage(ClientMessage clientMessage, String str) {
        if (clientMessage == null) {
            return 0L;
        }
        ContentValues convertClientMessage = convertClientMessage(clientMessage);
        convertClientMessage.put("view_as_comms_id", str);
        return ContentUris.parseId(this.mContext.getContentResolver().insert(MessagingProviderContract.Messages.getContentUri(this.mContext), convertClientMessage));
    }

    public void insertMessages(List<Message> list, String str, String str2) {
        if (list == null) {
            return;
        }
        ContentValues[] contentValuesArr = new ContentValues[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);
            contentValuesArr[i] = convertMessage(message, str, str2);
            this.mTranscriptLatencyMetricHelper.startTimer(message);
        }
        this.mContext.getContentResolver().bulkInsert(MessagingProviderContract.Messages.getContentUri(this.mContext), contentValuesArr);
    }

    public void syncConversations(List<Conversation> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Conversation conversation : list) {
            if (conversation.getFirstVisibleMessageId() <= conversation.getLastMsgId()) {
                arrayList.add(conversation);
            }
        }
        ContentValues[] convertConversations = convertConversations((Conversation[]) arrayList.toArray(new Conversation[arrayList.size()]));
        for (ContentValues contentValues : convertConversations) {
            contentValues.put("server_last_modified_timestamp", Long.valueOf(contentValues.getAsLong("last_modified_timestamp").longValue()));
            contentValues.putNull("last_modified_timestamp");
            contentValues.put("server_last_msg_id", Long.valueOf(contentValues.getAsLong("last_msg_id").longValue()));
            contentValues.putNull("last_msg_id");
        }
        this.mContext.getContentResolver().bulkInsert(MessagingProviderContract.Conversations.getConversationSyncUri(this.mContext), convertConversations);
    }

    public void updateClientConversationAndParticipants(long j, ContentValues contentValues) {
        this.mContext.getContentResolver().update(MessagingProviderContract.Conversations.getContentUri(this.mContext), contentValues, "_id = ?", new String[]{String.valueOf(j)});
    }

    public void updateClientMessage(long j, ContentValues contentValues) {
        this.mContext.getContentResolver().update(MessagingProviderContract.Messages.getContentUri(this.mContext), contentValues, "_id = ?", new String[]{String.valueOf(j)});
    }

    public void updateMessagePayload(@NonNull ClientConversation clientConversation, long j, @NonNull MessagePayload messagePayload) {
        String messageType = getMessageType(clientConversation.getConversationId(), j);
        if (messageType != null && messageType.equals(messagePayload.getType())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_payload", this.mJsonConverter.toJson(messagePayload));
            updateClientMessage(clientConversation.getConversationId(), j, contentValues);
            if (clientConversation.getLastMsgId() != j) {
                return;
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("last_msg", messagePayload.getSummaryText(this.mContext));
            updateClientConversationAndParticipants(clientConversation.getConversationId(), contentValues2);
            return;
        }
        LOG.e(String.format("Payload type (%s) doesn't match message type (%s), cannot update payload.", messagePayload.getType(), messageType));
    }

    public int updateClientConversationAndParticipants(String str, ContentValues contentValues) {
        return this.mContext.getContentResolver().update(MessagingProviderContract.Conversations.getContentUri(this.mContext), contentValues, "conversation_id = ?", new String[]{str});
    }

    public void updateClientMessage(@NonNull String str, long j, @NonNull ContentValues contentValues) {
        this.mContext.getContentResolver().update(MessagingProviderContract.Messages.getContentUri(this.mContext), contentValues, "conversation_id = ? AND message_id = ?", new String[]{str, String.valueOf(j)});
    }
}
