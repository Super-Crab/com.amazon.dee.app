package com.amazon.deecomms.smsmessaging.database;

import android.provider.BaseColumns;
/* loaded from: classes12.dex */
public final class SMSMessageContract {
    private static final String C = "comms";
    public static final String COMMS_SMS_DATABASE_KEY_ALIAS = "COMMS_SMS_DATABASE_KEY_ALIAS";
    public static final String CREATE_READ_TABLE = "CREATE TABLE ReadMessages (_id INTEGER PRIMARY KEY AUTOINCREMENT, messageId TEXT, messageType TEXT)";
    private static final String CSMS = "comms.sms";
    public static final String DATABASE_NAME = "commssms.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DEBUG_DATABASE_CREATE = "comms.sms.database.create";
    public static final String DEBUG_DATABASE_CREATE_FAIL = "comms.sms.database.exec.create.fail";
    public static final String DEBUG_DATABASE_GET_WRITABLE_DB_FAIL = "comms.sms.database.getWritableDB.fail";
    public static final String DEBUG_DATABASE_KEY_EXPIRED = "comms.sms.database.key.expired";
    public static final String DEBUG_DATABASE_PRUNE_FAIL = "comms.sms.database.exec.prune.fail";
    public static final String DEBUG_DATABASE_RECREATE = "comms.sms.database.recreate";
    public static final String DELETE_READ_TABLE = "DROP TABLE IF EXISTS ReadMessages";
    private static final String MC = "comms.mc";
    public static final String MC_GENERIC = "comms.mc.sms.directive.generic";
    public static final String MC_LONG_DELIVER_FAIL = "comms.mc.sms.event.updateSendMessageStatus.long.deliver.fail";
    public static final String MC_LONG_SEND_FAIL = "comms.mc.sms.event.updateSendMessageStatus.long.send.fail";
    public static final String MC_LONG_SEND_SUCCESS = "comms.mc.sms.event.updateSendMessageStatus.long.send.success";
    public static final String MC_MISS_PERMISSION = "comms.mc.sms.missPermission";
    private static final String MC_REPORT = "comms.mc.sms.event.updateSendMessageStatus";
    public static final String MC_REQUEST = "comms.mc.sms.directive.requestUploadConversations";
    public static final String MC_SEND = "comms.mc.sms.directive.send";
    public static final String MC_SHORT_DELIVER_FAIL = "comms.mc.sms.event.updateSendMessageStatus.short.deliver.fail";
    public static final String MC_SHORT_SEND_FAIL = "comms.mc.sms.event.updateSendMessageStatus.short.send.fail";
    public static final String MC_SHORT_SEND_SUCCESS = "comms.mc.sms.event.updateSendMessageStatus.short.send.success";
    private static final String MC_SMS = "comms.mc.sms";
    private static final String MC_SMS_DIRECTIVE = "comms.mc.sms.directive";
    private static final String MC_SMS_EVENT = "comms.mc.sms.event";
    public static final String MC_UNSUPPORTED = "comms.mc.sms.directive.unsupported";
    public static final String MC_UPDATE = "comms.mc.sms.directive.updateMessageStatus";
    private static final String MC_UPLOAD = "comms.mc.sms.event.uploadConversations";
    public static final String MC_UPLOAD_CONNECT = "comms.mc.sms.event.uploadConversations.connect";
    public static final String MC_UPLOAD_DELETE_MMS = "comms.mc.sms.event.uploadConversations.deleteMms";
    public static final String MC_UPLOAD_DELETE_SMS = "comms.mc.sms.event.uploadConversations.deleteSms";
    public static final String MC_UPLOAD_NEW_MMS = "comms.mc.sms.event.uploadConversations.newMms";
    public static final String MC_UPLOAD_NEW_SMS = "comms.mc.sms.event.uploadConversations.newSms";
    public static final String MC_UPLOAD_READ_MMS = "comms.mc.sms.event.uploadConversations.readMms";
    public static final String MC_UPLOAD_READ_SMS = "comms.mc.sms.event.uploadConversations.readSms";
    public static final String MC_UPLOAD_REQUEST = "comms.mc.sms.event.uploadConversations.request";
    public static final String MESSAGE_TYPE_MMS = "MMS";
    public static final String MESSAGE_TYPE_SMS = "SMS";
    public static final String META_ERROR_SOURCE = "errorSource";
    public static final String META_EVENT_VALUE = "EventValue";
    public static final String META_SOURCE = "source";
    public static final String PRUNE_READ_TABLE = "DELETE FROM ReadMessages WHERE ROWID IN (SELECT ROWID FROM ReadMessages ORDER BY ROWID DESC LIMIT -1 OFFSET 1000)";
    public static final String READ_TABLE_NAME = "ReadMessages";

    /* loaded from: classes12.dex */
    public static class MessageEntry implements BaseColumns {
        public static final String MESSAGE_ID = "messageId";
        public static final String MESSAGE_TYPE = "messageType";
    }

    private SMSMessageContract() {
    }
}
