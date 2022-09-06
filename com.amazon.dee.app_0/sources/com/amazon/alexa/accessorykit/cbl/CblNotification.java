package com.amazon.alexa.accessorykit.cbl;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessorykit.cbl.AutoValue_CblNotification;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class CblNotification {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract CblNotification build();

        public abstract Builder setAccessory(Accessory accessory);

        public abstract Builder setText(String str);

        public abstract Builder setTitle(String str);
    }

    public static Builder newBuilder() {
        return new AutoValue_CblNotification.Builder();
    }

    public abstract Accessory getAccessory();

    public abstract String getText();

    public abstract String getTitle();
}
