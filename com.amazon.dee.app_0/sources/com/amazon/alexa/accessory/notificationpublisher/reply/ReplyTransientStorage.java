package com.amazon.alexa.accessory.notificationpublisher.reply;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class ReplyTransientStorage {
    public static final String NOTIFICATION_UUID = "NOTIFICATION_UUID";
    public static final String REPLY_TEXT = "REPLY_TEXT";
    public static final String TAG = "ReplyTransientStorage";
    private static ReplyTransientStorage instance;
    private final Map<String, String> payload = new HashMap();
    private int retryCount = 0;

    private ReplyTransientStorage() {
    }

    public static synchronized ReplyTransientStorage getInstance() {
        ReplyTransientStorage replyTransientStorage;
        synchronized (ReplyTransientStorage.class) {
            if (instance == null) {
                instance = new ReplyTransientStorage();
            }
            replyTransientStorage = instance;
        }
        return replyTransientStorage;
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyTransientStorage.class) {
            if (instance != null) {
                instance.payload.clear();
            }
            instance = null;
        }
    }

    public synchronized void clearPayload() {
        if (this.payload != null && !this.payload.isEmpty()) {
            Log.i(TAG, "clearPayload");
            this.payload.clear();
        }
    }

    public String getNotificationUuid() {
        return this.payload.get(NOTIFICATION_UUID);
    }

    public synchronized Map<String, String> getPayload() {
        return this.payload;
    }

    public synchronized int getRetryCount() {
        return this.retryCount;
    }

    public String getTranscribedText() {
        return this.payload.get(REPLY_TEXT);
    }

    public synchronized void incrementCounter() {
        this.retryCount++;
    }

    public boolean isPayloadValid(@NonNull String str) {
        if (Strings.isNullOrEmpty(str)) {
            Log.i(TAG, "isPayloadValid - Empty notificationUuid");
            return false;
        }
        String str2 = this.payload.get(NOTIFICATION_UUID);
        return !Strings.isNullOrEmpty(this.payload.get(REPLY_TEXT)) && !Strings.isNullOrEmpty(str2) && str2.equalsIgnoreCase(str);
    }

    public synchronized void resetCounter() {
        this.retryCount = 0;
    }

    public synchronized void setPayload(@NonNull String str, @NonNull String str2) {
        Log.i(TAG, "setPayload");
        this.payload.put(NOTIFICATION_UUID, str);
        this.payload.put(REPLY_TEXT, str2);
    }

    /* renamed from: clone */
    public ReplyTransientStorage m360clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
