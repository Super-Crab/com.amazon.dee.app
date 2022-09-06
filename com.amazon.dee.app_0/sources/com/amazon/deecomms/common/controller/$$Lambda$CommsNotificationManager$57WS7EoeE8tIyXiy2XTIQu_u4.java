package com.amazon.deecomms.common.controller;

import com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.controller.-$$Lambda$CommsNotificationManager$57-WS7EoeE8tIyXiy2XTI-Qu_u4  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4 implements Comparator {
    public static final /* synthetic */ $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4 INSTANCE = new $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4();

    private /* synthetic */ $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((AnnouncementPushNotification) obj2).getOriginDate().compareTo(((AnnouncementPushNotification) obj).getOriginDate());
        return compareTo;
    }
}
