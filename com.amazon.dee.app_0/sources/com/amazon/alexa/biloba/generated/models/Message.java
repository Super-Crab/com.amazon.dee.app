package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Message {
    @SerializedName("iconUrl")
    private String iconUrl = null;
    @SerializedName("iconAltText")
    private String iconAltText = null;
    @SerializedName("localizedPrimaryMessage")
    private String localizedPrimaryMessage = null;
    @SerializedName("linkUrl")
    private String linkUrl = null;
    @SerializedName("linkUrlText")
    private String linkUrlText = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Message.class != obj.getClass()) {
            return false;
        }
        Message message = (Message) obj;
        return Objects.equals(this.iconUrl, message.iconUrl) && Objects.equals(this.iconAltText, message.iconAltText) && Objects.equals(this.localizedPrimaryMessage, message.localizedPrimaryMessage) && Objects.equals(this.linkUrl, message.linkUrl) && Objects.equals(this.linkUrlText, message.linkUrlText);
    }

    public String getIconAltText() {
        return this.iconAltText;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public String getLinkUrlText() {
        return this.linkUrlText;
    }

    public String getLocalizedPrimaryMessage() {
        return this.localizedPrimaryMessage;
    }

    public int hashCode() {
        return Objects.hash(this.iconUrl, this.iconAltText, this.localizedPrimaryMessage, this.linkUrl, this.linkUrlText);
    }

    public Message iconAltText(String str) {
        this.iconAltText = str;
        return this;
    }

    public Message iconUrl(String str) {
        this.iconUrl = str;
        return this;
    }

    public Message linkUrl(String str) {
        this.linkUrl = str;
        return this;
    }

    public Message linkUrlText(String str) {
        this.linkUrlText = str;
        return this;
    }

    public Message localizedPrimaryMessage(String str) {
        this.localizedPrimaryMessage = str;
        return this;
    }

    public void setIconAltText(String str) {
        this.iconAltText = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public void setLinkUrlText(String str) {
        this.linkUrlText = str;
    }

    public void setLocalizedPrimaryMessage(String str) {
        this.localizedPrimaryMessage = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Message {\n", "    iconUrl: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconUrl), "\n", "    iconAltText: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconAltText), "\n", "    localizedPrimaryMessage: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedPrimaryMessage), "\n", "    linkUrl: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.linkUrl), "\n", "    linkUrlText: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.linkUrlText), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
