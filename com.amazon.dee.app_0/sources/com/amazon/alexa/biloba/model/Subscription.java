package com.amazon.alexa.biloba.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Subscription {
    @SerializedName("id")
    private String id = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("payer")
    private Boolean isPayer = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Subscription.class != obj.getClass()) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equals(this.id, subscription.id) && Objects.equals(this.status, subscription.status);
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.status);
    }

    public Subscription id(String str) {
        this.id = str;
        return this;
    }

    public Subscription isPayer(Boolean bool) {
        this.isPayer = bool;
        return this;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIsPayer(Boolean bool) {
        this.isPayer = bool;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public Subscription status(String str) {
        this.status = str;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Subscription {\n", "    id: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.id), "\n", "    status: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.status), "\n", "    isPayer: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.isPayer), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Boolean isPayer() {
        return this.isPayer;
    }
}
