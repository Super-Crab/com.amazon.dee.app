package com.amazon.deecomms.smsmessaging.service.fetchsms;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.Telephony;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.smsmessaging.database.SMSMessageContract;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import java.util.List;
import java.util.concurrent.Semaphore;
/* loaded from: classes12.dex */
public class SMSReceiveManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SMSReceiveManager.class);
    private ContentResolver contentResolver;
    private MessagingControllerManager eventProcessor;
    private Context mContext;
    private SMSFetchManager mSMSFetchManager;
    private boolean receivingRunning = false;
    private boolean monitorStatus = false;
    private int mmsId = Integer.MIN_VALUE;
    private int smsId = Integer.MIN_VALUE;
    private int readMMSCount = 0;
    private int readSMSCount = 0;
    private int totalSMSCount = Integer.MAX_VALUE;
    private int totalMMSCount = Integer.MAX_VALUE;
    private ContentObserver messageObserver = new MessageObserver(new Handler(), new Semaphore(1));

    /* loaded from: classes12.dex */
    private class MMSAddresses {
        List<String> receivers;
        String sender;

        private MMSAddresses() {
        }

        /* synthetic */ MMSAddresses(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    private final class MessageObserver extends ContentObserver {
        private Handler handler;
        Semaphore semaphore;

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, @NonNull Uri uri) {
            try {
                this.semaphore.acquire();
                super.onChange(z);
                SMSReceiveManager.this.monitorStatus = true;
                if (SMSReceiveManager.this.newMMSReceived()) {
                    SMSReceiveManager.LOG.i("MMS received - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_NEW_MMS);
                }
                if (SMSReceiveManager.this.newSMSReceived()) {
                    SMSReceiveManager.LOG.i("SMS received - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_NEW_SMS);
                }
                if (SMSReceiveManager.this.newSMSRead()) {
                    SMSReceiveManager.LOG.i("SMS read - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_READ_SMS);
                }
                if (SMSReceiveManager.this.newMMSRead()) {
                    SMSReceiveManager.LOG.i("MMS read - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_READ_MMS);
                }
                if (SMSReceiveManager.this.isSMSDeleted()) {
                    SMSReceiveManager.LOG.i("SMS deleted - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_DELETE_SMS);
                }
                if (SMSReceiveManager.this.isMMSDeleted()) {
                    SMSReceiveManager.LOG.i("MMS deleted - Full sync");
                    SMSReceiveManager.this.eventProcessor.fullSync(SMSReceiveManager.this.mContext, 40);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_DELETE_MMS);
                }
                this.semaphore.release();
            } catch (Exception e) {
                SMSReceiveManager.LOG.i("MessageObserver - onChange() error, ", e);
            }
        }

        private MessageObserver(@NonNull Handler handler, @NonNull Semaphore semaphore) {
            super(handler);
            this.handler = handler;
            this.semaphore = semaphore;
        }
    }

    public SMSReceiveManager(@NonNull Context context, @NonNull MessagingControllerManager messagingControllerManager) {
        this.mContext = context;
        this.contentResolver = context.getContentResolver();
        this.eventProcessor = messagingControllerManager;
        this.mSMSFetchManager = new SMSFetchManager(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager.MMSAddresses getMMSAddress(int r7) {
        /*
            r6 = this;
            com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager$MMSAddresses r0 = new com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager$MMSAddresses
            r1 = 0
            r0.<init>(r1)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            com.amazon.deecomms.smsmessaging.service.fetchsms.SMSFetchManager r3 = r6.mSMSFetchManager     // Catch: java.lang.Throwable -> L5c
            android.database.Cursor r1 = r3.getMMSAddresses(r7)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r7 = ""
            if (r1 == 0) goto L5e
            int r3 = r1.getCount()     // Catch: java.lang.Throwable -> L5c
            if (r3 <= 0) goto L5e
            r1.moveToLast()     // Catch: java.lang.Throwable -> L5c
        L1e:
            java.lang.String r3 = "address"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "type"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = r1.getString(r4)     // Catch: java.lang.Throwable -> L5c
            if (r3 == 0) goto L55
            if (r4 != 0) goto L38
            goto L55
        L38:
            r5 = 151(0x97, float:2.12E-43)
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch: java.lang.Throwable -> L5c
            boolean r5 = r4.equals(r5)     // Catch: java.lang.Throwable -> L5c
            if (r5 == 0) goto L48
            r2.add(r3)     // Catch: java.lang.Throwable -> L5c
            goto L55
        L48:
            r5 = 137(0x89, float:1.92E-43)
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch: java.lang.Throwable -> L5c
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Throwable -> L5c
            if (r4 == 0) goto L55
            r7 = r3
        L55:
            boolean r3 = r1.moveToPrevious()     // Catch: java.lang.Throwable -> L5c
            if (r3 != 0) goto L1e
            goto L5e
        L5c:
            r7 = move-exception
            goto L68
        L5e:
            if (r1 == 0) goto L63
            r1.close()
        L63:
            r0.sender = r7
            r0.receivers = r2
            return r0
        L68:
            if (r1 == 0) goto L6d
            r1.close()
        L6d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager.getMMSAddress(int):com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager$MMSAddresses");
    }

    @NonNull
    private String getMMSContent(int i) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getMMSPayload(i);
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToLast();
                        do {
                            String string = cursor.getString(cursor.getColumnIndex("ct"));
                            if (string != null && string.equalsIgnoreCase(Constants.TEXT_PLAIN_MEDIA_TYPE)) {
                                String string2 = cursor.getString(cursor.getColumnIndex("text"));
                                cursor.close();
                                return string2;
                            }
                        } while (cursor.moveToPrevious());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor == null) {
                return "";
            }
            cursor.close();
            return "";
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private int getMMSConversationRecipientsCount(int i) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getConversationRecipient(i);
            if (cursor != null) {
                try {
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        String string = cursor.getString(cursor.getColumnIndex("recipient_ids"));
                        if (string == null) {
                            LOG.e("getMMSConversationRecipientsCount - recipientIds = 0. This should never happen.");
                            cursor.close();
                            return 0;
                        }
                        int length = (string.length() - string.replaceAll(" ", "").length()) + 1;
                        cursor.close();
                        return length;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            LOG.e("getMMSConversationRecipientsCount = 0. This should never happen.");
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private int getReadMessageCount(boolean z) {
        Throwable th;
        Cursor cursor;
        try {
            if (z) {
                cursor = this.mSMSFetchManager.getReadSMSCount();
            } else {
                cursor = this.mSMSFetchManager.getReadMMSCount();
            }
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            }
            try {
                cursor.moveToFirst();
                int i = cursor.getInt(0);
                cursor.close();
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private int getTotalMessageCount(boolean z) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getTotalMessageCount(z);
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            }
            try {
                cursor.moveToFirst();
                int i = cursor.getInt(0);
                cursor.close();
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private void initializeUnreadMessage() {
        Throwable th;
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = this.mSMSFetchManager.getUnreadSMS();
            try {
                Cursor unreadMMS = this.mSMSFetchManager.getUnreadMMS();
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToLast();
                    this.smsId = cursor.getInt(cursor.getColumnIndex("_id"));
                }
                if (unreadMMS != null && unreadMMS.getCount() > 0) {
                    unreadMMS.moveToLast();
                    this.mmsId = unreadMMS.getInt(unreadMMS.getColumnIndex("_id"));
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (unreadMMS == null) {
                    return;
                }
                unreadMMS.close();
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                if (0 != 0) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMMSDeleted() {
        int totalMessageCount = getTotalMessageCount(false);
        boolean z = totalMessageCount < this.totalMMSCount;
        if (z) {
            this.totalMMSCount = totalMessageCount;
            this.readMMSCount = getReadMessageCount(false);
        }
        return z;
    }

    private boolean isNewMMS(@NonNull Cursor cursor, int i) {
        try {
            return cursor.getInt(cursor.getColumnIndex("_id")) > i;
        } catch (Exception e) {
            LOG.e("isNewMMS error, ", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSMSDeleted() {
        int totalMessageCount = getTotalMessageCount(true);
        boolean z = totalMessageCount < this.totalSMSCount;
        if (z) {
            this.totalSMSCount = totalMessageCount;
            this.readSMSCount = getReadMessageCount(true);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean newMMSRead() {
        boolean z = false;
        int readMessageCount = getReadMessageCount(false);
        if (readMessageCount > this.readMMSCount) {
            z = true;
        }
        if (z) {
            this.readMMSCount = readMessageCount;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean newMMSReceived() {
        Cursor cursor = null;
        try {
            Cursor unreadMMS = this.mSMSFetchManager.getUnreadMMS();
            if (unreadMMS != null) {
                try {
                    if (unreadMMS.getCount() != 0) {
                        unreadMMS.moveToLast();
                        if (this.mmsId >= unreadMMS.getInt(unreadMMS.getColumnIndex("_id"))) {
                            unreadMMS.close();
                            return false;
                        }
                        int i = this.mmsId;
                        int mMSConversationRecipientsCount = getMMSConversationRecipientsCount(unreadMMS.getInt(unreadMMS.getColumnIndex("thread_id")));
                        do {
                            try {
                                Cursor mMSAddresses = this.mSMSFetchManager.getMMSAddresses(unreadMMS.getInt(unreadMMS.getColumnIndex("_id")));
                                if (mMSAddresses != null) {
                                    try {
                                        if (mMSAddresses.getCount() >= mMSConversationRecipientsCount) {
                                            unreadMMS.moveToPrevious();
                                            mMSAddresses.close();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        cursor = mMSAddresses;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        throw th;
                                    }
                                }
                                if (mMSAddresses != null) {
                                    mMSAddresses.close();
                                }
                                unreadMMS.close();
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } while (isNewMMS(unreadMMS, i));
                        unreadMMS.moveToLast();
                        this.totalMMSCount = getTotalMessageCount(false);
                        this.mmsId = unreadMMS.getInt(unreadMMS.getColumnIndex("_id"));
                        unreadMMS.close();
                        return true;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor = unreadMMS;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (unreadMMS != null) {
                unreadMMS.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean newSMSRead() {
        boolean z = true;
        int readMessageCount = getReadMessageCount(true);
        if (readMessageCount <= this.readSMSCount) {
            z = false;
        }
        if (z) {
            this.readSMSCount = readMessageCount;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean newSMSReceived() {
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getUnreadSMS();
            boolean z = false;
            if (cursor != null) {
                try {
                    if (cursor.getCount() != 0) {
                        cursor.moveToLast();
                        if (this.smsId < cursor.getInt(cursor.getColumnIndex("_id"))) {
                            z = true;
                        }
                        if (z) {
                            this.totalSMSCount = getTotalMessageCount(true);
                            this.smsId = cursor.getInt(cursor.getColumnIndex("_id"));
                        }
                        cursor.close();
                        return z;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public void startMessageMonitoring() {
        LOG.i("Trying to start messaging monitor.");
        try {
            if (this.receivingRunning) {
                return;
            }
            this.receivingRunning = true;
            this.monitorStatus = false;
            if (!this.monitorStatus) {
                this.contentResolver.registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, this.messageObserver);
                initializeUnreadMessage();
                this.readSMSCount = getReadMessageCount(true);
                this.readMMSCount = getReadMessageCount(false);
                this.totalSMSCount = getTotalMessageCount(true);
                this.totalMMSCount = getTotalMessageCount(false);
            } else {
                stopMessageMonitoring();
                startMessageMonitoring();
            }
        } catch (Exception e) {
            LOG.e("startMessageMonitoring error, ", e);
        }
    }

    public void stopMessageMonitoring() {
        LOG.i("Trying to stop messaging monitor.");
        try {
            this.receivingRunning = false;
            this.monitorStatus = false;
            if (this.monitorStatus) {
                return;
            }
            this.contentResolver.unregisterContentObserver(this.messageObserver);
        } catch (Exception e) {
            LOG.e("stopMessageMonitoring error, ", e);
        }
    }
}
