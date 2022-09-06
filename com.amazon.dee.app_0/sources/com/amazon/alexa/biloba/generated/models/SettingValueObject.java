package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class SettingValueObject {
    @SerializedName("value")
    private Object value = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SettingValueObject.class == obj.getClass()) {
            return Objects.equals(this.value, ((SettingValueObject) obj).value);
        }
        return false;
    }

    public Object getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class SettingValueObject {\n", "    value: "), toIndentedString(this.value), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public SettingValueObject value(Object obj) {
        this.value = obj;
        return this;
    }
}
