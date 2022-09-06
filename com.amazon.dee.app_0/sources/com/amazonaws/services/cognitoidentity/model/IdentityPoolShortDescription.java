package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class IdentityPoolShortDescription implements Serializable {
    private String identityPoolId;
    private String identityPoolName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IdentityPoolShortDescription)) {
            return false;
        }
        IdentityPoolShortDescription identityPoolShortDescription = (IdentityPoolShortDescription) obj;
        if ((identityPoolShortDescription.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        if (identityPoolShortDescription.getIdentityPoolId() != null && !identityPoolShortDescription.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
        if ((identityPoolShortDescription.getIdentityPoolName() == null) ^ (getIdentityPoolName() == null)) {
            return false;
        }
        return identityPoolShortDescription.getIdentityPoolName() == null || identityPoolShortDescription.getIdentityPoolName().equals(getIdentityPoolName());
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public String getIdentityPoolName() {
        return this.identityPoolName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode()) + 31) * 31;
        if (getIdentityPoolName() != null) {
            i = getIdentityPoolName().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public void setIdentityPoolName(String str) {
        this.identityPoolName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityPoolId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1072.append(getIdentityPoolId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIdentityPoolName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("IdentityPoolName: ");
            outline1073.append(getIdentityPoolName());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public IdentityPoolShortDescription withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public IdentityPoolShortDescription withIdentityPoolName(String str) {
        this.identityPoolName = str;
        return this;
    }
}
