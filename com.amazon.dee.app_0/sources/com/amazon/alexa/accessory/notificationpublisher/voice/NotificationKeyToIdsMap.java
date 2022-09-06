package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.text.TextUtils;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class NotificationKeyToIdsMap {
    private final Map<String, Set<NotificationId>> notificationKeyToIdsMap = new HashMap();

    public synchronized Set<NotificationId> getAndRemove(String str) {
        if (!TextUtils.isEmpty(str) && this.notificationKeyToIdsMap.containsKey(str)) {
            return this.notificationKeyToIdsMap.remove(str);
        }
        return new HashSet();
    }

    public synchronized void put(String str, NotificationId notificationId) {
        if (!TextUtils.isEmpty(str) && notificationId != null) {
            if (this.notificationKeyToIdsMap.get(str) == null) {
                this.notificationKeyToIdsMap.put(str, new HashSet());
            }
            this.notificationKeyToIdsMap.get(str).add(notificationId);
        }
    }
}
