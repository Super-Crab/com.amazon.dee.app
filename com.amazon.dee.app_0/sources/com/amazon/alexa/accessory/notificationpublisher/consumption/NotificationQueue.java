package com.amazon.alexa.accessory.notificationpublisher.consumption;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class NotificationQueue extends BaseComponent {
    public static final int EVENT_QUEUE_ADD = 1;
    public static final int EVENT_QUEUE_CLEAR = 2;
    private static final String TAG = "NotificationQueue";
    private static final int capacity = 500;
    private static NotificationQueue queueInstance;
    private JSONObject lastNotInvitationNotification;
    private JSONObject lastNotification;
    private LinkedHashMap<String, JSONObject> queue;

    private NotificationQueue() {
        super(5);
        this.lastNotification = null;
        this.lastNotInvitationNotification = null;
        this.queue = new LinkedHashMap<>();
    }

    private synchronized Map.Entry<String, JSONObject> getFirstEntry() {
        if (!this.queue.isEmpty()) {
            return this.queue.entrySet().iterator().next();
        }
        return null;
    }

    public static synchronized NotificationQueue getInstance() {
        NotificationQueue notificationQueue;
        synchronized (NotificationQueue.class) {
            if (queueInstance == null) {
                queueInstance = new NotificationQueue();
            }
            notificationQueue = queueInstance;
        }
        return notificationQueue;
    }

    public static synchronized void releaseInstance() {
        synchronized (NotificationQueue.class) {
            if (queueInstance != null) {
                queueInstance.clearLastNotification();
            }
            queueInstance = null;
        }
    }

    public synchronized boolean add(@NonNull String str, @NonNull JSONObject jSONObject) {
        if (this.queue.size() >= 500) {
            Log.w(TAG, "No Enough Space");
            return false;
        }
        this.queue.put(str, jSONObject);
        postEventMessage(1, jSONObject);
        return true;
    }

    public synchronized void clear() {
        this.queue.clear();
        postEventMessage(2, null);
    }

    public synchronized String clearLastNotInvitationNotification() {
        String optString;
        optString = this.lastNotInvitationNotification != null ? this.lastNotInvitationNotification.optString("uuid") : null;
        this.lastNotInvitationNotification = null;
        return optString;
    }

    public synchronized String clearLastNotification() {
        String optString;
        optString = this.lastNotification != null ? this.lastNotification.optString("uuid") : null;
        this.lastNotification = null;
        return optString;
    }

    public synchronized boolean contains(String str) {
        return this.queue.containsKey(str);
    }

    public synchronized JSONObject get(String str) {
        return this.queue.get(str);
    }

    public synchronized JSONObject getLastNotInvitationNotification() {
        return this.lastNotInvitationNotification;
    }

    public synchronized JSONObject getLastNotification() {
        return this.lastNotification;
    }

    public synchronized JSONObject getLastValue() {
        JSONObject jSONObject = null;
        if (!this.queue.isEmpty()) {
            Iterator<Map.Entry<String, JSONObject>> it2 = this.queue.entrySet().iterator();
            Map.Entry<String, JSONObject> entry = null;
            while (it2.hasNext()) {
                entry = it2.next();
            }
            if (entry != null) {
                jSONObject = entry.getValue();
            }
            return jSONObject;
        }
        return null;
    }

    public synchronized Iterator<JSONObject> getNotificationIterator() {
        return this.queue.values().iterator();
    }

    public synchronized Iterator<String> getNotificationKeysIterator() {
        return this.queue.keySet().iterator();
    }

    public synchronized JSONObject getSecondInQueue() {
        if (!this.queue.isEmpty() && this.queue.size() >= 2) {
            Iterator<Map.Entry<String, JSONObject>> it2 = this.queue.entrySet().iterator();
            it2.next();
            return it2.next().getValue();
        }
        return null;
    }

    public synchronized boolean insertToQueueHead(@NonNull String str, @NonNull JSONObject jSONObject) {
        if (this.queue.size() >= 500) {
            Log.w(TAG, "No Enough Space");
            return false;
        }
        LinkedHashMap<String, JSONObject> linkedHashMap = this.queue;
        this.queue = new LinkedHashMap<>();
        this.queue.put(str, jSONObject);
        this.queue.putAll(linkedHashMap);
        return true;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public synchronized JSONObject peek() {
        Map.Entry<String, JSONObject> firstEntry = getFirstEntry();
        if (firstEntry != null) {
            return firstEntry.getValue();
        }
        return null;
    }

    public synchronized JSONObject poll() {
        Map.Entry<String, JSONObject> firstEntry = getFirstEntry();
        if (firstEntry != null) {
            return this.queue.remove(firstEntry.getKey());
        }
        return null;
    }

    public synchronized JSONObject remove(String str) {
        return this.queue.remove(str);
    }

    public synchronized void setLastNotInvitationNotification(@NonNull JSONObject jSONObject) {
        this.lastNotInvitationNotification = jSONObject;
    }

    public synchronized void setLastNotification(@NonNull JSONObject jSONObject) {
        this.lastNotification = jSONObject;
    }

    public synchronized int size() {
        return this.queue.size();
    }

    /* renamed from: clone */
    public NotificationQueue m338clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
