package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AutoValue_AccessoryStateChangeEventPayload;
import com.google.auto.value.AutoValue;
import java.util.List;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes.dex */
public abstract class AccessoryStateChangeEventPayload {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract AccessoryStateChangeEventPayload build();

        public abstract Builder setAccessories(List<AccessoryInfo> list);

        public abstract Builder setEventTimestamp(String str);

        public abstract Builder setPersonInfo(PersonInfo personInfo);

        public abstract Builder setToken(String str);
    }

    public static Builder builder() {
        return new AutoValue_AccessoryStateChangeEventPayload.Builder();
    }

    public abstract List<AccessoryInfo> getAccessories();

    public abstract String getEventTimestamp();

    public abstract PersonInfo getPersonInfo();

    @Nullable
    public abstract String getToken();
}
