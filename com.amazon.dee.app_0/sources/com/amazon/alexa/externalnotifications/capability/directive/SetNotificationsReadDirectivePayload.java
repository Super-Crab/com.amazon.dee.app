package com.amazon.alexa.externalnotifications.capability.directive;

import com.amazon.alexa.externalnotifications.capability.directive.AutoValue_SetNotificationsReadDirectivePayload;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class SetNotificationsReadDirectivePayload {
    public static SetNotificationsReadDirectivePayload create(List<NotificationId> list) {
        return new AutoValue_SetNotificationsReadDirectivePayload(list);
    }

    public static TypeAdapter<SetNotificationsReadDirectivePayload> typeAdapter(Gson gson) {
        return new AutoValue_SetNotificationsReadDirectivePayload.GsonTypeAdapter(gson);
    }

    public abstract List<NotificationId> getNotificationIds();
}
