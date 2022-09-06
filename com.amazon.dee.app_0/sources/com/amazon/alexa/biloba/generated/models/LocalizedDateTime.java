package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import com.sun.mail.imap.IMAPStore;
import java.util.Objects;
/* loaded from: classes6.dex */
public class LocalizedDateTime {
    @SerializedName(IMAPStore.ID_DATE)
    private String date = null;
    @SerializedName("time")
    private String time = null;
    @SerializedName("zone")
    private String zone = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public LocalizedDateTime date(String str) {
        this.date = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocalizedDateTime.class != obj.getClass()) {
            return false;
        }
        LocalizedDateTime localizedDateTime = (LocalizedDateTime) obj;
        return Objects.equals(this.date, localizedDateTime.date) && Objects.equals(this.time, localizedDateTime.time) && Objects.equals(this.zone, localizedDateTime.zone);
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getZone() {
        return this.zone;
    }

    public int hashCode() {
        return Objects.hash(this.date, this.time, this.zone);
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public LocalizedDateTime time(String str) {
        this.time = str;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class LocalizedDateTime {\n", "    date: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.date), "\n", "    time: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.time), "\n", "    zone: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.zone), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public LocalizedDateTime zone(String str) {
        this.zone = str;
        return this;
    }
}
