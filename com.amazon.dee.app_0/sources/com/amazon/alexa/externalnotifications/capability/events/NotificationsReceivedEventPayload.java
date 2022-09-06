package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.externalnotifications.capability.events.AutoValue_NotificationsReceivedEventPayload;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class NotificationsReceivedEventPayload {
    public static NotificationsReceivedEventPayload create(List<Notification> list) {
        return new AutoValue_NotificationsReceivedEventPayload(list);
    }

    public static TypeAdapter<NotificationsReceivedEventPayload> typeAdapter(Gson gson) {
        return new AutoValue_NotificationsReceivedEventPayload.GsonTypeAdapter(gson);
    }

    public abstract List<Notification> getNotifications();
}
