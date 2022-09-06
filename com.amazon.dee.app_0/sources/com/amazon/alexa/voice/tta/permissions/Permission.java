package com.amazon.alexa.voice.tta.permissions;

import com.amazon.alexa.voice.tta.permissions.AutoValue_Permission;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes11.dex */
public abstract class Permission {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Permission build();

        public abstract Builder message(String str);

        public abstract Builder type(PermissionType permissionType);
    }

    public static Builder builder() {
        return new AutoValue_Permission.Builder();
    }

    public abstract String getMessage();

    public abstract PermissionType getType();
}
