package com.amazon.alexa.biloba.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Device {
    @SerializedName("name")
    private String name = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Device.class == obj.getClass()) {
            return Objects.equals(this.name, ((Device) obj).name);
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public Device name(String str) {
        this.name = str;
        return this;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class Device {\n", "    name: "), toIndentedString(this.name), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
