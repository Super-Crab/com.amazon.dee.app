package com.amazon.clouddrive.cdasdk.onelens;

import com.amazon.clouddrive.cdasdk.onelens.common.OneLensResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetContactInfoResponse extends OneLensResponse {
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("smsServiceAvailable")
    private Boolean smsServiceAvailable;
    @JsonProperty("textPhoneNumber")
    private String textPhoneNumber;

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetContactInfoResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetContactInfoResponse)) {
            return false;
        }
        GetContactInfoResponse getContactInfoResponse = (GetContactInfoResponse) obj;
        if (!getContactInfoResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String email = getEmail();
        String email2 = getContactInfoResponse.getEmail();
        if (email != null ? !email.equals(email2) : email2 != null) {
            return false;
        }
        String name = getName();
        String name2 = getContactInfoResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        Boolean smsServiceAvailable = getSmsServiceAvailable();
        Boolean smsServiceAvailable2 = getContactInfoResponse.getSmsServiceAvailable();
        if (smsServiceAvailable != null ? !smsServiceAvailable.equals(smsServiceAvailable2) : smsServiceAvailable2 != null) {
            return false;
        }
        String textPhoneNumber = getTextPhoneNumber();
        String textPhoneNumber2 = getContactInfoResponse.getTextPhoneNumber();
        return textPhoneNumber != null ? textPhoneNumber.equals(textPhoneNumber2) : textPhoneNumber2 == null;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getSmsServiceAvailable() {
        return this.smsServiceAvailable;
    }

    public String getTextPhoneNumber() {
        return this.textPhoneNumber;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String email = getEmail();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (email == null ? 43 : email.hashCode());
        String name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        Boolean smsServiceAvailable = getSmsServiceAvailable();
        int hashCode4 = (hashCode3 * 59) + (smsServiceAvailable == null ? 43 : smsServiceAvailable.hashCode());
        String textPhoneNumber = getTextPhoneNumber();
        int i2 = hashCode4 * 59;
        if (textPhoneNumber != null) {
            i = textPhoneNumber.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("email")
    public void setEmail(String str) {
        this.email = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("smsServiceAvailable")
    public void setSmsServiceAvailable(Boolean bool) {
        this.smsServiceAvailable = bool;
    }

    @JsonProperty("textPhoneNumber")
    public void setTextPhoneNumber(String str) {
        this.textPhoneNumber = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetContactInfoResponse(email=");
        outline107.append(getEmail());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", smsServiceAvailable=");
        outline107.append(getSmsServiceAvailable());
        outline107.append(", textPhoneNumber=");
        outline107.append(getTextPhoneNumber());
        outline107.append(")");
        return outline107.toString();
    }
}
