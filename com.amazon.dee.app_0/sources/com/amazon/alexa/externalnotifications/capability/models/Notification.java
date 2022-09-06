package com.amazon.alexa.externalnotifications.capability.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.externalnotifications.capability.models.AutoValue_Notification;
import com.amazon.alexa.externalnotifications.capability.models.C$AutoValue_Notification;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Date;
@AutoValue
/* loaded from: classes7.dex */
public abstract class Notification {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        public abstract Notification build();

        public abstract Builder setApplication(Application application);

        public abstract Builder setContent(Content content);

        public abstract Builder setNotificationId(NotificationId notificationId);

        public abstract Builder setSource(Source source);

        public abstract Builder setTimestamp(Date date);
    }

    public static Builder builder() {
        return new C$AutoValue_Notification.Builder();
    }

    public static TypeAdapter<Notification> typeAdapter(Gson gson) {
        return new AutoValue_Notification.GsonTypeAdapter(gson);
    }

    public abstract Application getApplication();

    public abstract Content getContent();

    public abstract NotificationId getNotificationId();

    @Nullable
    public abstract Source getSource();

    public abstract Date getTimestamp();
}
