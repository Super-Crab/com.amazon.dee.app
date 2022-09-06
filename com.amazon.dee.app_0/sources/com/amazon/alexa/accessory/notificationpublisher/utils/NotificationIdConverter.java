package com.amazon.alexa.accessory.notificationpublisher.utils;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class NotificationIdConverter {
    private NotificationIdConverter() {
    }

    public static String toNotificationId(String str) {
        return str.substring(0, str.lastIndexOf(AccessoryMetricsConstants.DELIMITER));
    }

    public static String toUUID(String str, long j) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, AccessoryMetricsConstants.DELIMITER);
        outline113.append(String.valueOf(j));
        return outline113.toString();
    }
}
