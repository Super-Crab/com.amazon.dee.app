package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UnlinkDeveloperIdentityRequest extends AmazonWebServiceRequest implements Serializable {
    private String developerProviderName;
    private String developerUserIdentifier;
    private String identityId;
    private String identityPoolId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnlinkDeveloperIdentityRequest)) {
            return false;
        }
        UnlinkDeveloperIdentityRequest unlinkDeveloperIdentityRequest = (UnlinkDeveloperIdentityRequest) obj;
        if ((unlinkDeveloperIdentityRequest.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (unlinkDeveloperIdentityRequest.getIdentityId() != null && !unlinkDeveloperIdentityRequest.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((unlinkDeveloperIdentityRequest.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        if (unlinkDeveloperIdentityRequest.getIdentityPoolId() != null && !unlinkDeveloperIdentityRequest.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
        if ((unlinkDeveloperIdentityRequest.getDeveloperProviderName() == null) ^ (getDeveloperProviderName() == null)) {
            return false;
        }
        if (unlinkDeveloperIdentityRequest.getDeveloperProviderName() != null && !unlinkDeveloperIdentityRequest.getDeveloperProviderName().equals(getDeveloperProviderName())) {
            return false;
        }
        if ((unlinkDeveloperIdentityRequest.getDeveloperUserIdentifier() == null) ^ (getDeveloperUserIdentifier() == null)) {
            return false;
        }
        return unlinkDeveloperIdentityRequest.getDeveloperUserIdentifier() == null || unlinkDeveloperIdentityRequest.getDeveloperUserIdentifier().equals(getDeveloperUserIdentifier());
    }

    public String getDeveloperProviderName() {
        return this.developerProviderName;
    }

    public String getDeveloperUserIdentifier() {
        return this.developerUserIdentifier;
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31) + (getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode())) * 31) + (getDeveloperProviderName() == null ? 0 : getDeveloperProviderName().hashCode())) * 31;
        if (getDeveloperUserIdentifier() != null) {
            i = getDeveloperUserIdentifier().hashCode();
        }
        return hashCode + i;
    }

    public void setDeveloperProviderName(String str) {
        this.developerProviderName = str;
    }

    public void setDeveloperUserIdentifier(String str) {
        this.developerUserIdentifier = str;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIdentityPoolId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1073.append(getIdentityPoolId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDeveloperProviderName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("DeveloperProviderName: ");
            outline1074.append(getDeveloperProviderName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDeveloperUserIdentifier() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("DeveloperUserIdentifier: ");
            outline1075.append(getDeveloperUserIdentifier());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UnlinkDeveloperIdentityRequest withDeveloperProviderName(String str) {
        this.developerProviderName = str;
        return this;
    }

    public UnlinkDeveloperIdentityRequest withDeveloperUserIdentifier(String str) {
        this.developerUserIdentifier = str;
        return this;
    }

    public UnlinkDeveloperIdentityRequest withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public UnlinkDeveloperIdentityRequest withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }
}
