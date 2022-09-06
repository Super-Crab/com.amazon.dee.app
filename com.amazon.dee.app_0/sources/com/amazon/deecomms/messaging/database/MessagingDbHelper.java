package com.amazon.deecomms.messaging.database;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.common.database.ICommsDatabase;
import com.amazon.deecomms.messaging.database.MessagingTableConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public class MessagingDbHelper implements ICommsDatabase {
    private static final String COLUMN_NAME_CONVERSATION_ID = "conversation_id";
    private static final String COLUMN_NAME_RECIPIENT_ID = "recipient_id";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingDbHelper.class);
    private static final String TEXT_TYPE = " TEXT";

    private void createConversationsTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE conversations (_id INTEGER PRIMARY KEY AUTOINCREMENT,conversation_id TEXT DEFAULT '', recipient_id TEXT DEFAULT '', last_msg_id INTEGER, last_sequence_id INTEGER, last_msg_type TEXT, last_msg TEXT, unread_msg_count INTEGER, unread_notif_count INTEGER, last_read_msg_id INTEGER DEFAULT -1, read_message_locally_only INTEGER DEFAULT 0, send_as_comms_id TEXT, view_as_comms_id TEXT, last_sync_msg_id INTEGER, server_last_msg_id INTEGER, server_last_modified_timestamp INTEGER, last_modified_timestamp INTEGER, last_msg_sender_id TEXT DEFAULT '' )");
        sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex("conversations", MessagingTableConstants.Conversations.CONVERSATION_INDEX_NAME, "conversation_id", "recipient_id", "send_as_comms_id"));
    }

    private String createMessagesClientMessageIdPartialIndex() {
        return DatabaseUtils.createPartialIndex("messages", MessagingTableConstants.Messages.MESSAGES_CLIENT_MESSAGE_ID_INDEX_NAME, "client_message_id IS NOT NULL", "client_message_id");
    }

    private void createMessagesTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE messages (_id INTEGER PRIMARY KEY AUTOINCREMENT,conversation_id TEXT DEFAULT '', recipient_id TEXT DEFAULT '', client_id INTEGER DEFAULT 0, message_id INTEGER DEFAULT 0, sender_comms_id TEXT, view_as_comms_id TEXT, _time INTEGER, _type TEXT, sync_status INTEGER, _payload TEXT, client_message_id TEXT)");
        sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex("messages", MessagingTableConstants.Messages.MESSAGES_INDEX_NAME, "conversation_id", "recipient_id", "message_id", "client_id"));
        sQLiteDatabase.execSQL(createMessagesClientMessageIdPartialIndex());
    }

    private void createOfflinePushNotificationCacheTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE OfflinePushNotificationCache (_id INTEGER PRIMARY KEY AUTOINCREMENT, payload TEXT)");
    }

    private void createParticipantsTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE participants (participant_unique_id INTEGER PRIMARY KEY AUTOINCREMENT,conversation_key_id INTEGER DEFAULT 0, participant_comms_id TEXT, last_read_msg_id INTEGER)");
        sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex("participants", MessagingTableConstants.Participants.PARTICIPANT_INDEX_NAME, "conversation_key_id", "participant_comms_id"));
    }

    private void upgradeFromVersion12toVersion13(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN last_read_msg_id INTEGER DEFAULT -1;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 12 -> 13 ", e);
        }
    }

    private void upgradeFromVersion13toVersion14(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN read_message_locally_only INTEGER DEFAULT 0;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 13 -> 14 ", e);
        }
    }

    private void upgradeFromVersion15toVersion16(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN client_message_id TEXT;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 15 -> 16 ", e);
        }
        sQLiteDatabase.execSQL(createMessagesClientMessageIdPartialIndex());
    }

    private void upgradeFromVersion25toVersion26(@NonNull SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("DELETE FROM conversations;");
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN last_sequence_id INTEGER;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 25 -> 26 ", e);
        }
    }

    private void upgradeFromVersion26toVersion27(@NonNull SQLiteDatabase sQLiteDatabase) {
        try {
            createOfflinePushNotificationCacheTable(sQLiteDatabase);
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 26 -> 27 ", e);
        }
    }

    private void upgradeFromVersion3toVersion4(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN send_as_comms_id TEXT;");
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN view_as_comms_id TEXT;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 3 -> 4 ", e);
        }
    }

    private void upgradeFromVersion4toVersion5(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(DatabaseUtils.dropIndex(MessagingTableConstants.Conversations.CONVERSATION_INDEX_NAME));
            sQLiteDatabase.execSQL(DatabaseUtils.createUniqueIndex("conversations", MessagingTableConstants.Conversations.CONVERSATION_INDEX_NAME, "conversation_id", "recipient_id", "send_as_comms_id"));
            sQLiteDatabase.execSQL("ALTER TABLE participants ADD COLUMN conversation_key_id INTEGER DEFAULT 0;");
            sQLiteDatabase.execSQL("UPDATE participants SET conversation_key_id = (SELECT conversations._id FROM conversations WHERE conversations.conversation_id = participants.conversation_id)");
            sQLiteDatabase.execSQL("ALTER TABLE participants DROP COLUMN conversation_id;");
            sQLiteDatabase.execSQL("ALTER TABLE participants DROP COLUMN recipient_id;");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 4 -> 5 ", e);
        }
    }

    private void upgradeFromVersion7toVersion8(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN view_as_comms_id TEXT;");
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE messages SET view_as_comms_id = (SELECT ");
            sb.append("conv");
            sb.append(".");
            sb.append("view_as_comms_id");
            sb.append(" FROM ");
            sb.append("conversations");
            sb.append(" ");
            sb.append("conv");
            sb.append(" WHERE ");
            sb.append("conv");
            sb.append(".");
            sb.append("conversation_id");
            sb.append(" = ");
            sb.append("conversation_id");
            sb.append(")");
            sQLiteDatabase.execSQL(sb.toString());
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 7 -> 8 ", e);
        }
    }

    private void upgradeFromVersion9toVersion10(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN last_msg_sender_id TEXT DEFAULT '';");
        } catch (SQLException e) {
            LOG.e("Exception upgrading Database from version 9 -> 10 ", e);
        }
    }

    @Override // com.amazon.deecomms.common.database.ICommsDatabase
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LOG.i("Creating Conversations, Participants and Messages table");
        createConversationsTable(sQLiteDatabase);
        createParticipantsTable(sQLiteDatabase);
        createMessagesTable(sQLiteDatabase);
        createOfflinePushNotificationCacheTable(sQLiteDatabase);
    }

    @Override // com.amazon.deecomms.common.database.ICommsDatabase
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        switch (i) {
            case 1:
            case 2:
            case 3:
                upgradeFromVersion3toVersion4(sQLiteDatabase);
            case 4:
                upgradeFromVersion4toVersion5(sQLiteDatabase);
            case 5:
            case 6:
            case 7:
                upgradeFromVersion7toVersion8(sQLiteDatabase);
            case 8:
            case 9:
                upgradeFromVersion9toVersion10(sQLiteDatabase);
            case 10:
            case 11:
            case 12:
                upgradeFromVersion12toVersion13(sQLiteDatabase);
            case 13:
                upgradeFromVersion13toVersion14(sQLiteDatabase);
            case 14:
            case 15:
                upgradeFromVersion15toVersion16(sQLiteDatabase);
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                upgradeFromVersion25toVersion26(sQLiteDatabase);
                break;
            case 26:
                break;
            case 27:
            case 28:
                return;
            default:
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("onUpgrade() with unknown oldVersion ", i));
        }
        upgradeFromVersion26toVersion27(sQLiteDatabase);
    }
}
