package com.amazon.alexa.accessorykit.interprocess;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.Telephony;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor;
import com.amazon.alexa.accessorykit.interprocess.DefaultMessageNotificationStateMonitor;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public class DefaultMessageNotificationStateMonitor implements MessageNotificationStateMonitor {
    private static final String TAG = "DefaultMessageNotificationStateMonitor: ";
    private boolean active;
    private boolean isUnreadMessageAvailable;
    private final Object lock;
    private final MessageNotificationStatusReceiver messageNotificationStatusReceiver;
    private final List<MessageNotificationStateMonitor.Observer> observers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class MessageNotificationStatusReceiver {
        private final ContentResolver contentResolver;
        private final Context context;
        private final Callback messageCallback;
        private boolean registered;
        private final SMSFetchManager smsFetcherManager;
        private boolean startingMonitorStatus = false;
        private int mmsId = Integer.MIN_VALUE;
        private int smsId = Integer.MIN_VALUE;
        private final MessageObserver messageObserver = new MessageObserver(new Handler());
        private final Object lock = new Object();

        /* loaded from: classes6.dex */
        interface Callback {
            void onMessageStateReceived(boolean z);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public class MessageObserver extends ContentObserver {
            private final Object lock;

            public MessageObserver(Handler handler) {
                super(handler);
                this.lock = new Object();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Boolean checkMessages() {
                synchronized (this.lock) {
                    if (!MessageNotificationStatusReceiver.this.newMMSReceived()) {
                        return Boolean.valueOf(MessageNotificationStatusReceiver.this.newSMSReceived());
                    }
                    return true;
                }
            }

            @Override // android.database.ContentObserver
            public boolean deliverSelfNotifications() {
                return true;
            }

            public /* synthetic */ void lambda$onChange$0$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver(Boolean bool) throws Throwable {
                if (bool.booleanValue()) {
                    MessageNotificationStatusReceiver.this.messageCallback.onMessageStateReceived(true);
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                onChange(z, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                super.onChange(z);
                Single.fromCallable(new Callable() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$DQN8e-RLVyAD6_1kqZoE7nm2sqA
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Boolean checkMessages;
                        checkMessages = DefaultMessageNotificationStateMonitor.MessageNotificationStatusReceiver.MessageObserver.this.checkMessages();
                        return checkMessages;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$n1buJWoGtyB9RnHfYmQt-QHNEjU
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        DefaultMessageNotificationStateMonitor.MessageNotificationStatusReceiver.MessageObserver.this.lambda$onChange$0$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver((Boolean) obj);
                    }
                }, $$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s.INSTANCE);
            }
        }

        public MessageNotificationStatusReceiver(Context context, Callback callback) {
            this.context = context;
            this.messageCallback = callback;
            this.smsFetcherManager = new SMSFetchManager(context);
            this.contentResolver = context.getContentResolver();
        }

        private int getMMSConversationRecipientsCount(int i) {
            Cursor conversationRecipient = this.smsFetcherManager.getConversationRecipient(i);
            if (conversationRecipient != null) {
                try {
                    if (conversationRecipient.getCount() != 0) {
                        conversationRecipient.moveToFirst();
                        String string = conversationRecipient.getString(conversationRecipient.getColumnIndex("recipient_ids"));
                        if (string == null) {
                            Logger.e("getMMSConversationRecipientsCount - recipientIds = 0. This should never happen.");
                            conversationRecipient.close();
                            return 0;
                        }
                        int length = (string.length() - string.replaceAll(" ", "").length()) + 1;
                        conversationRecipient.close();
                        return length;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (conversationRecipient != null) {
                            try {
                                conversationRecipient.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
            Logger.e("getMMSConversationRecipientsCount = 0. This should never happen.");
            if (conversationRecipient != null) {
                conversationRecipient.close();
            }
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x007f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean initializeUnreadMessage() {
            /*
                r8 = this;
                r0 = 0
                r1 = 1
                r2 = 0
                com.amazon.alexa.accessorykit.interprocess.SMSFetchManager r3 = r8.smsFetcherManager     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
                android.database.Cursor r3 = r3.getUnreadSMS()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
                com.amazon.alexa.accessorykit.interprocess.SMSFetchManager r4 = r8.smsFetcherManager     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                android.database.Cursor r0 = r4.getUnreadMMS()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                java.lang.String r4 = "_id"
                if (r3 == 0) goto L28
                int r5 = r3.getCount()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                if (r5 <= 0) goto L28
                r3.moveToLast()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                int r5 = r3.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                int r5 = r3.getInt(r5)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                r8.smsId = r5     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                r5 = r1
                goto L29
            L28:
                r5 = r2
            L29:
                if (r0 == 0) goto L40
                int r6 = r0.getCount()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                if (r6 <= 0) goto L40
                r0.moveToLast()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                int r4 = r0.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                int r4 = r0.getInt(r4)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                r8.mmsId = r4     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
                r4 = r1
                goto L41
            L40:
                r4 = r2
            L41:
                if (r5 != 0) goto L47
                if (r4 == 0) goto L46
                goto L47
            L46:
                r1 = r2
            L47:
                if (r3 == 0) goto L4c
                r3.close()
            L4c:
                if (r0 == 0) goto L51
                r0.close()
            L51:
                return r1
            L52:
                r1 = move-exception
                goto L78
            L54:
                r4 = move-exception
                r7 = r3
                r3 = r0
                r0 = r7
                goto L5e
            L59:
                r1 = move-exception
                r3 = r0
                goto L78
            L5c:
                r4 = move-exception
                r3 = r0
            L5e:
                java.lang.String r5 = "%s error in initializeUnreadMessage"
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L74
                java.lang.String r6 = "DefaultMessageNotificationStateMonitor: "
                r1[r2] = r6     // Catch: java.lang.Throwable -> L74
                com.amazon.alexa.accessory.internal.util.Logger.e(r5, r4, r1)     // Catch: java.lang.Throwable -> L74
                if (r0 == 0) goto L6e
                r0.close()
            L6e:
                if (r3 == 0) goto L73
                r3.close()
            L73:
                return r2
            L74:
                r1 = move-exception
                r7 = r3
                r3 = r0
                r0 = r7
            L78:
                if (r3 == 0) goto L7d
                r3.close()
            L7d:
                if (r0 == 0) goto L82
                r0.close()
            L82:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessorykit.interprocess.DefaultMessageNotificationStateMonitor.MessageNotificationStatusReceiver.initializeUnreadMessage():boolean");
        }

        private boolean isNewMMS(Cursor cursor, int i) {
            try {
                return cursor.getInt(cursor.getColumnIndex("_id")) > i;
            } catch (Exception e) {
                Logger.e("isNewMMS error, ", e);
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean newMMSReceived() {
            Cursor unreadMMS = this.smsFetcherManager.getUnreadMMS();
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
                            Cursor mMSAddresses = this.smsFetcherManager.getMMSAddresses(unreadMMS.getInt(unreadMMS.getColumnIndex("_id")));
                            if (mMSAddresses != null && mMSAddresses.getCount() >= mMSConversationRecipientsCount) {
                                unreadMMS.moveToPrevious();
                                mMSAddresses.close();
                            }
                            if (mMSAddresses != null) {
                                mMSAddresses.close();
                            }
                            unreadMMS.close();
                            return false;
                        } while (isNewMMS(unreadMMS, i));
                        unreadMMS.moveToLast();
                        this.mmsId = unreadMMS.getInt(unreadMMS.getColumnIndex("_id"));
                        unreadMMS.close();
                        return true;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            unreadMMS.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            if (unreadMMS != null) {
                unreadMMS.close();
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean newSMSReceived() {
            Cursor unreadSMS = this.smsFetcherManager.getUnreadSMS();
            boolean z = false;
            if (unreadSMS != null) {
                try {
                    if (unreadSMS.getCount() != 0) {
                        unreadSMS.moveToLast();
                        if (this.smsId < unreadSMS.getInt(unreadSMS.getColumnIndex("_id"))) {
                            z = true;
                        }
                        if (z) {
                            this.smsId = unreadSMS.getInt(unreadSMS.getColumnIndex("_id"));
                        }
                        unreadSMS.close();
                        return z;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            unreadSMS.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            if (unreadSMS != null) {
                unreadSMS.close();
            }
            return false;
        }

        boolean activate() {
            synchronized (this.lock) {
                if (this.registered) {
                    Logger.w("MessageNotificationStatusReceiver is already registered");
                    return this.startingMonitorStatus;
                }
                this.registered = true;
                try {
                    this.contentResolver.registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, this.messageObserver);
                } catch (SecurityException e) {
                    Logger.e("%s Exception in activate()", e, DefaultMessageNotificationStateMonitor.TAG);
                }
                this.startingMonitorStatus = initializeUnreadMessage();
                return this.startingMonitorStatus;
            }
        }

        void deactivate() {
            synchronized (this.lock) {
                if (!this.registered) {
                    return;
                }
                this.registered = false;
                this.contentResolver.unregisterContentObserver(this.messageObserver);
            }
        }
    }

    public DefaultMessageNotificationStateMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.lock = new Object();
        this.observers = new ArrayList();
        this.messageNotificationStatusReceiver = new MessageNotificationStatusReceiver(context, new MessageNotificationStatusReceiver.Callback() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultMessageNotificationStateMonitor$3nJytvpvxxN9fnGc7ymYrSW7L4g
            @Override // com.amazon.alexa.accessorykit.interprocess.DefaultMessageNotificationStateMonitor.MessageNotificationStatusReceiver.Callback
            public final void onMessageStateReceived(boolean z) {
                DefaultMessageNotificationStateMonitor.this.newMessageState(z);
            }
        });
    }

    private void ensureActive() {
        synchronized (this.lock) {
            if (!this.active) {
                this.active = true;
                this.isUnreadMessageAvailable = ((Boolean) Single.fromCallable(new Callable() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultMessageNotificationStateMonitor$OmYozFNRNJqr4kQEF-jXcYS6CRQ
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        boolean executeActive;
                        executeActive = DefaultMessageNotificationStateMonitor.this.executeActive();
                        return Boolean.valueOf(executeActive);
                    }
                }).subscribeOn(Schedulers.io()).blockingGet()).booleanValue();
            }
        }
    }

    private void ensureDeactivate() {
        synchronized (this.lock) {
            if (this.active) {
                this.active = false;
                this.messageNotificationStatusReceiver.deactivate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean executeActive() {
        return this.messageNotificationStatusReceiver.activate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void newMessageState(boolean z) {
        synchronized (this.lock) {
            Iterator it2 = new ArrayList(this.observers).iterator();
            while (it2.hasNext()) {
                ((MessageNotificationStateMonitor.Observer) it2.next()).onUnreadMessageStatusChanged(z);
            }
            this.isUnreadMessageAvailable = z;
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public void addObserver(MessageNotificationStateMonitor.Observer observer) {
        synchronized (this.lock) {
            ensureActive();
            this.observers.add(observer);
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public boolean isUnreadMessageAvailable() {
        boolean z;
        synchronized (this.lock) {
            ensureActive();
            z = this.isUnreadMessageAvailable;
        }
        return z;
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public void removeObserver(MessageNotificationStateMonitor.Observer observer) {
        synchronized (this.lock) {
            this.observers.remove(observer);
            if (this.observers.size() == 0) {
                ensureDeactivate();
            }
        }
    }
}
