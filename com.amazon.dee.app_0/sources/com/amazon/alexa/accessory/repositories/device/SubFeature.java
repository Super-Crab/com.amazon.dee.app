package com.amazon.alexa.accessory.repositories.device;

import java.util.Objects;
/* loaded from: classes6.dex */
public class SubFeature {
    private final int id;

    public SubFeature(int i) {
        this.id = i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == SubFeature.class) {
            return Objects.equals(Integer.valueOf(this.id), Integer.valueOf(((SubFeature) obj).id));
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id));
    }
}
