package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.externalnotifications.capability.events.AutoValue_NotificationsReadEventPayload;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class NotificationsReadEventPayload {
    public static NotificationsReadEventPayload create(List<NotificationId> list) {
        return new AutoValue_NotificationsReadEventPayload(list);
    }

    public static TypeAdapter<NotificationsReadEventPayload> typeAdapter(Gson gson) {
        return new AutoValue_NotificationsReadEventPayload.GsonTypeAdapter(gson);
    }

    public abstract List<NotificationId> getNotificationIds();
}
