package com.amazon.alexa.accessory.repositories.device.v2;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class Key {
    private final String value;

    private Key(String str) {
        Preconditions.notNull(str, "value");
        this.value = str;
    }

    public static Key from(String str) {
        return new Key(str);
    }

    public static Key random() {
        return new Key(UUID.randomUUID().toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Key.class == obj.getClass()) {
            return this.value.equals(((Key) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value;
    }
}
