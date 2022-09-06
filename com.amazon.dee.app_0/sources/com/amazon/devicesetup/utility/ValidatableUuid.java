package com.amazon.devicesetup.utility;

import java.util.UUID;
/* loaded from: classes12.dex */
public class ValidatableUuid {
    private final UUID mValue;

    public ValidatableUuid(UUID uuid) {
        if (uuid != null) {
            this.mValue = uuid;
            return;
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public UUID getValue() {
        return this.mValue;
    }

    public String toString() {
        return this.mValue.toString();
    }
}
