package com.amazon.clouddrive.cdasdk.cds.common;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ISO8601 {
    @NonNull
    @JsonProperty("value")
    private String value;

    public ISO8601(@NonNull String str) {
        this.value = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ISO8601;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ISO8601)) {
            return false;
        }
        ISO8601 iso8601 = (ISO8601) obj;
        if (!iso8601.canEqual(this)) {
            return false;
        }
        String value = getValue();
        String value2 = iso8601.getValue();
        return value != null ? value.equals(value2) : value2 == null;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        String value = getValue();
        return 59 + (value == null ? 43 : value.hashCode());
    }

    @JsonProperty("value")
    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ISO8601(value=");
        outline107.append(getValue());
        outline107.append(")");
        return outline107.toString();
    }

    public ISO8601() {
        this.value = "";
    }
}
