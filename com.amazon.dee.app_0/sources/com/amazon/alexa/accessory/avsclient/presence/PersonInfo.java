package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AutoValue_PersonInfo;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes.dex */
public abstract class PersonInfo {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract PersonInfo build();

        public abstract Builder setActivePersonId(String str);
    }

    public static Builder builder() {
        return new AutoValue_PersonInfo.Builder();
    }

    @Nullable
    public abstract String getActivePersonId();
}
