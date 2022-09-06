package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.externalnotifications.capability.events.AutoValue_NotificationsDismissedEventPayload;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class NotificationsDismissedEventPayload {
    public static NotificationsDismissedEventPayload create(List<NotificationId> list) {
        return new AutoValue_NotificationsDismissedEventPayload(list);
    }

    public static TypeAdapter<NotificationsDismissedEventPayload> typeAdapter(Gson gson) {
        return new AutoValue_NotificationsDismissedEventPayload.GsonTypeAdapter(gson);
    }

    public abstract List<NotificationId> getNotificationIds();
}
