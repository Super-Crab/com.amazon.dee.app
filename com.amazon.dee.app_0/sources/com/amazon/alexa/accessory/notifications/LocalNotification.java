package com.amazon.alexa.accessory.notifications;

import com.amazon.alexa.accessory.notifications.AutoValue_LocalNotification;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes.dex */
public abstract class LocalNotification {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract LocalNotification build();

        public abstract Builder setDeepLink(String str);

        public abstract Builder setText(String str);

        public abstract Builder setTitle(String str);
    }

    public static Builder newBuilder() {
        return new AutoValue_LocalNotification.Builder();
    }

    public abstract String getDeepLink();

    public abstract String getText();

    public abstract String getTitle();
}
